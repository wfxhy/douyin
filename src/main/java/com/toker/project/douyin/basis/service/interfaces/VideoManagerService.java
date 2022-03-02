package com.toker.project.douyin.basis.service.interfaces;

import com.toker.project.douyin.basis.body.video.AutoCreateImageParam;
import com.toker.project.douyin.basis.body.video.AutoCreateVideoParam;
import com.toker.project.douyin.basis.body.video.ImageCreateBody;
import com.toker.project.douyin.basis.body.video.VideoCreateReqBody;
import com.toker.project.douyin.basis.response.video.*;
import com.toker.project.douyin.common.bean.PageCount;
import com.toker.project.douyin.common.enums.ApiPlatform;
import com.toker.project.douyin.common.exception.ApiCallException;
import com.toker.project.douyin.common.response.DefaultResponseData;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * <a href="https://open.douyin.com/platform/doc/6848798087398295555">视频管理API接口</a>
 * <p>
 * 支持抖音、头条、西瓜<p>
 * platform参数用来控制平台
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/9 11:16
 * @modified mdmbct
 * @since 1.0
 */
public interface VideoManagerService {

    /**
     * 最大单个视频文件 128MB
     */
    int MAX_SINGLE_VIDEO_FILE_SIZE = 128 * 1024 * 1024;

    /**
     * 默认单个分片视频文件大小20MB
     */
    int DEFAULT_PART_VIDEO_FILE_SIZE = 20 * 1024 * 1024;

    /**
     * 最小单个分片视频文件大小5MB
     */
    int MIN_PART_VIDEO_FILE_SIZE = 5 * 1024 * 1024;

    /**
     * 允许的视频文件大小最大为4GB
     */
    long MAX_ALLOW_VIDEO_FILE_SIZE = 4L * 1024 * 1024 * 1024;

    /**
     * 图片最大不超过100MB
     */
    int MAX_ALLOW_IMAGE_SIZE = 100 * 1024 * 1024;

    /**
     * 上传视频 128M以下
     *
     * @param openId    open id
     * @param videoFile 不超过128MB的视频文件
     * @param platform
     * @return
     * @throws ApiCallException
     */
    VideoUploadRes.VideoUploadResponseData videoUpload(String openId, File videoFile, ApiPlatform platform) throws ApiCallException;

    /**
     * 视频分片上传初始化
     *
     * @param openId
     * @param platform
     * @return
     */
    VideoPartInitRes.VideoPartInitResData videoPartInit(String openId, ApiPlatform platform) throws ApiCallException;

    /**
     * 视频分片上传
     *
     * @param openId
     * @param uploadId
     * @param partNumber
     * @param part
     * @param videoFile
     * @param platform
     * @return
     */
    DefaultResponseData videoPartUpload(String openId, String uploadId, int partNumber, byte[] part, File videoFile, ApiPlatform platform) throws ApiCallException;

    /**
     * 视频分片上传完后
     *
     * @param openId
     * @param uploadId
     * @param platform
     * @return
     */
    VideoUploadRes.VideoUploadResponseData videoPartComplete(String openId, String uploadId, ApiPlatform platform) throws ApiCallException;

    /**
     * 创建视频
     *
     * @param openId
     * @param body
     * @param platform
     * @return
     */
    VideoCreateRes.VideoCreateResData videoCreate(String openId, VideoCreateReqBody body, ApiPlatform platform) throws ApiCallException;

    DefaultResponseData videoDelete(String openId, String itemId, ApiPlatform platform) throws ApiCallException;

    /**
     * 自动上传视频 根据视频大小自动决定是否分片上传
     *
     * @param openId
     * @param videoFile
     * @param platform
     * @return
     * @throws FileNotFoundException 视频文件不存在
     */
    VideoUploadRes.VideoUploadResponseData autoUploadVideo(String openId, File videoFile, ApiPlatform platform) throws ApiCallException, FileNotFoundException;

    /**
     * 自动上传视频 根据视频大小自动决定是否分片上传 上传完成后自动创建视频
     *
     * @param openId   open id
     * @param videFile 视频文件
     * @param param
     * @param platform
     * @return
     * @throws ApiCallException
     */
    VideoCreateRes.VideoCreateResData autoCreateVideo(String openId, File videFile, AutoCreateVideoParam param, ApiPlatform platform) throws ApiCallException, FileNotFoundException;

    VideoListRes.VideoListResData getVideoList(String openId, long cursor, PageCount count, ApiPlatform platform) throws ApiCallException;

    VideoListRes.VideoListResData getVideoData(String openId, String[] videoIds, ApiPlatform platform) throws ApiCallException;

    ImageUploadRes.ImageUploadResData imageUpload(String openId, File imageFile) throws ApiCallException;

    VideoCreateRes.VideoCreateResData imageCreate(String openId, ImageCreateBody body) throws ApiCallException;

    VideoCreateRes.VideoCreateResData autoCreateImage(String openId, File imageFile, AutoCreateImageParam autoCreateImageParam) throws ApiCallException;

    VideoPoiRes.VideoPoiResData searchPOI(String clientToken,String keyword)throws ApiCallException;


}
