package com.toker.project.douyin.basis.service;

import com.toker.project.douyin.basis.api.VideoManager;
import com.toker.project.douyin.basis.body.video.*;
import com.toker.project.douyin.basis.response.video.*;
import com.toker.project.douyin.basis.service.interfaces.VideoManagerService;
import com.toker.project.douyin.common.bean.PageCount;
import com.toker.project.douyin.common.enums.ApiPlatform;
import com.toker.project.douyin.common.enums.RequestBodyType;
import com.toker.project.douyin.common.exception.ApiCallException;
import com.toker.project.douyin.common.exception.InvalidRequestParamException;
import com.toker.project.douyin.common.http.ByteRequestBody;
import com.toker.project.douyin.common.http.FileRequestBody;
import com.toker.project.douyin.common.http.Head;
import com.toker.project.douyin.common.http.HttpExecutor;
import com.toker.project.douyin.common.response.DefaultResponse;
import com.toker.project.douyin.common.response.DefaultResponseData;
import com.toker.project.douyin.common.service.BaseDyService;
import com.toker.project.douyin.common.storage.DyStorageManager;
import com.toker.project.douyin.common.utils.file.*;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;

/**
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/9 11:31
 * @modified mdmbct
 * @since 1.0
 */
@Slf4j
public class VideoManagerServiceImpl extends BaseDyService implements VideoManagerService {


    public VideoManagerServiceImpl(DyStorageManager storageManager, HttpExecutor httpExecutor) {
        super(storageManager, httpExecutor);
    }

    @Override
    public VideoUploadRes.VideoUploadResponseData videoUpload(String openId, File videoFile, ApiPlatform platform) throws ApiCallException {

        if (videoFile.length() > MAX_SINGLE_VIDEO_FILE_SIZE) {
            throw new InvalidRequestParamException("大于128MB的视频,请使用分片上传!");
        }

        if (!videoFile.exists()) {
            throw new InvalidRequestParamException("视频文件不存在!");
        }

        String url = formatRequestUrl(VideoManager.VIDEO_UPLOAD, platform, openId);

        String fileName = videoFile.getName();
        String fileExtension = FileUtils.getFileExtension(fileName, false);
        FileRequestBody body = new FileRequestBody("video", fileName, "video/" + fileExtension, videoFile);

        String result = postRequest(url, body, openId, platform, RequestBodyType.FROM, Head.MULTIPART_FORM_DATA);
        return handleApiResponse(result, VideoUploadRes.class, VideoUploadRes.VideoUploadResponseData.class);
    }

    @Override
    public VideoPartInitRes.VideoPartInitResData videoPartInit(String openId, ApiPlatform platform) {
        return jsonPostReqForDy(VideoManager.VIDEO_PART_INIT,
                openId,
                null,
                VideoPartInitRes.VideoPartInitResData.class,
                openId
        );
    }

    @Override
    public DefaultResponseData videoPartUpload(String openId, String uploadId, int partNumber, byte[] part, File videoFile, ApiPlatform platform) {

        if (part.length < MIN_PART_VIDEO_FILE_SIZE || part.length > DEFAULT_PART_VIDEO_FILE_SIZE) {
            throw new InvalidRequestParamException("一个视频分片的大小在5MB至20MB之间");
        }

        log.info("part_number:{}, part_length:{}, upload_id:{}, fileName:{}", partNumber, part.length, uploadId, videoFile.getAbsolutePath());

        String url = formatRequestUrl(VideoManager.VIDEO_PART_UPLOAD, platform, openId, uploadId, partNumber);
        ByteRequestBody body = new ByteRequestBody("video", videoFile.getName(), part);

        String result = postRequest(url, body, openId, platform, RequestBodyType.FROM, Head.MULTIPART_FORM_DATA);
        return handleApiResponse(result, DefaultResponse.class, DefaultResponseData.class);
    }

    @Override
    public VideoUploadRes.VideoUploadResponseData videoPartComplete(String openId, String uploadId, ApiPlatform platform) {
        return jsonPostReqForDy(VideoManager.VIDEO_PART_COMPLETE,
                openId,
                null,
                VideoUploadRes.VideoUploadResponseData.class,
                openId, uploadId
        );
    }


    @Override
    public VideoCreateRes.VideoCreateResData videoCreate(String openId, VideoCreateReqBody body, ApiPlatform platform) {
        return jsonPostReqForDy(VideoManager.VIDEO_CREATE,
                openId,
                body,
                VideoCreateRes.VideoCreateResData.class,
                openId
        );
    }


    @Override
    public DefaultResponseData videoDelete(String openId, String itemId, ApiPlatform platform) throws ApiCallException {
        return jsonPostReqForDy(VideoManager.VIDEO_DELETE,
                openId,
                new VideoDeleteRequestBody(itemId),
                DefaultResponseData.class,
                openId
        );
    }


    @Override
    public VideoUploadRes.VideoUploadResponseData autoUploadVideo(String openId, File videoFile, ApiPlatform platform) throws FileNotFoundException {
        if (!videoFile.exists()) {
            throw new InvalidRequestParamException("视频文件不存在!");
        }

        long fileSize = videoFile.length();
        if (fileSize >= MAX_ALLOW_VIDEO_FILE_SIZE) {
            throw new InvalidRequestParamException("抖音允许上传的视频文件最大为4GB");
        }

        // 小于128MB 直接上传
        if (fileSize < MAX_SINGLE_VIDEO_FILE_SIZE) {
            return videoUpload(openId, videoFile, platform);
        }

        // 分片上传
        String uploadId = videoPartInit(openId, platform).getUploadId();

        int threadCount = storageManager.getOpenApiConfig().getUploadThreadNum();
        FileSplit fileSplit = new FileSplit(videoFile, DEFAULT_PART_VIDEO_FILE_SIZE, MIN_PART_VIDEO_FILE_SIZE, threadCount);
        // 防止线程数超过分块数
        threadCount = Math.min(threadCount, fileSplit.getChunkCount());

        if (threadCount == 1) {
            // 单线程上传文件
            fileSplit.split(chunkGroup -> Collections.singletonList(
                    videoPartUpload(openId, uploadId, chunkGroup.get(0).getChunkNum(), chunkGroup.get(0).getBytes(), videoFile, platform)
            ));

        } else {
            // 多线程上传文件
            FileChunkHandler<DefaultResponseData> fileChunkHandler = chunk -> videoPartUpload(openId, uploadId, chunk.getChunkNum(), chunk.getBytes(), videoFile, platform);
            MultiThreadFileChunkGroupHandler<DefaultResponseData> multiThreadFileChunkHandler = new MultiThreadFileChunkGroupHandler<>(threadCount, fileSplit.getChunkCount(), fileChunkHandler);
            fileSplit.split(multiThreadFileChunkHandler);
            // 通知线程池中线程结束
            multiThreadFileChunkHandler.finishUpload();
        }

        fileSplit.finishedSplit();
        return videoPartComplete(openId, uploadId, platform);
    }

    @Override
    public VideoCreateRes.VideoCreateResData autoCreateVideo(String openId, File videoFile, AutoCreateVideoParam param, ApiPlatform platform) throws ApiCallException, FileNotFoundException {

        long fileSize = videoFile.length();
        if (fileSize >= MAX_ALLOW_VIDEO_FILE_SIZE) {
            throw new InvalidRequestParamException("抖音允许上传的视频文件最大为4GB");
        }

        VideoUploadRes.VideoUploadResponseData videoUploadResponseData = autoUploadVideo(openId, videoFile, platform);
        String videoId = videoUploadResponseData.getVideo().getVideoId();

        VideoCreateReqBody body = VideoCreateReqBody.build(param);
        body.setVideoId(videoId);

        return videoCreate(openId, body, platform);
    }

    @Override
    public VideoListRes.VideoListResData getVideoList(String openId, long cursor, PageCount count, ApiPlatform platform) throws ApiCallException {
        return simpleGetReq(VideoManager.VIDEO_LIST,
                openId,
                platform,
                VideoListRes.VideoListResData.class,
                openId, cursor, count.getValue()
        );
    }

    @Override
    public VideoListRes.VideoListResData getVideoData(String openId, String[] videoIds, ApiPlatform platform) throws ApiCallException {
        VideoDataReqBody body = new VideoDataReqBody(videoIds);
        return jsonPostReqForDy(VideoManager.VIDEO_DATA,
                openId,
                body,
                VideoListRes.VideoListResData.class,
                openId
        );
    }

    @Override
    public ImageUploadRes.ImageUploadResData imageUpload(String openId, File imageFile) throws ApiCallException {

        if (imageFile.length() >= MAX_ALLOW_IMAGE_SIZE) {
            throw new InvalidRequestParamException("抖音允许上传的图片大小不能超过100MB!");
        }

        if (!imageFile.exists()) {
            throw new InvalidRequestParamException("图片文件不存在!");
        }

        String url = formatRequestUrl(VideoManager.IMAGE_UPLOAD, ApiPlatform.DOU_YIN, openId);

        String fileName = imageFile.getName();
        String extension = FileUtils.getFileExtension(fileName, false);
        FileRequestBody body = new FileRequestBody("image", fileName, "image/" + extension, imageFile);

        String result = postRequest(url, body, openId, ApiPlatform.DOU_YIN, RequestBodyType.FROM, Head.MULTIPART_FORM_DATA);
        return handleApiResponse(result, ImageUploadRes.class, ImageUploadRes.ImageUploadResData.class);
    }

    @Override
    public VideoCreateRes.VideoCreateResData imageCreate(String openId, ImageCreateBody body) throws ApiCallException {
        return jsonPostReqForDy(VideoManager.IMAGE_CREATE,
                openId,
                body,
                VideoCreateRes.VideoCreateResData.class,
                openId
        );
    }

    @Override
    public VideoCreateRes.VideoCreateResData autoCreateImage(String openId, File imageFile, AutoCreateImageParam autoCreateImageParam) throws ApiCallException {

        ImageUploadRes.ImageUploadResData imageUploadResData = imageUpload(openId, imageFile);

        ImageCreateBody body = ImageCreateBody.build(autoCreateImageParam);
        body.setImageId(imageUploadResData.getImage().getImageId());

        return imageCreate(openId, body);
    }

    @Override
    public VideoPoiRes.VideoPoiResData searchPOI(String clientToken,String keyword) throws ApiCallException {
        return simpleClientTokenGetReq(VideoManager.VIDEO_POI,clientToken,ApiPlatform.DOU_YIN,VideoPoiRes.VideoPoiResData.class,keyword );
    }


    /////////////////////////////////////////// private protected method ///////////////////////////////////////////

//    @Override
//    protected <T extends DyOpenApiResponse, D extends DefaultResponseData> D handleApiResponse(String responseResult, Class<T> responseClass, Class<D> responseDataClass) throws ApiResponseFailedException {
//
//        T t = JSONObject.parseObject(responseResult, responseClass);
//
//        if (t.getData().getErrorCode() == ErrorCode.SUCCESS.getValue()) {
//            return (D) t.getData();
//        }
//
//        throw new ApiResponseFailedException(t.getErrorMsg());
//    }




}
