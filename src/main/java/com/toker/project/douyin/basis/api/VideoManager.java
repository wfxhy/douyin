package com.toker.project.douyin.basis.api;

import com.toker.project.douyin.basis.body.video.*;
import com.toker.project.douyin.basis.response.video.*;
import com.toker.project.douyin.common.body.DyOpenApiRequestBody;
import com.toker.project.douyin.common.body.VoidBody;
import com.toker.project.douyin.common.enums.ApiPlatform;
import com.toker.project.douyin.common.enums.DyOpenApi;
import com.toker.project.douyin.common.enums.RequestMethod;
import com.toker.project.douyin.common.response.DefaultResponse;
import com.toker.project.douyin.common.response.DyOpenApiResponse;
import com.toker.project.douyin.common.storage.DyStorageManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 视频open api
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/8 12:16
 * @modified mdmbct
 * @since 1.0
 */

@Getter
@RequiredArgsConstructor
public enum VideoManager implements DyOpenApi {

    /**
     * <a href="https://open.douyin.com/platform/doc/6848798087398295555">上传视频到文件服务器</a>
     * <p>
     * Scope: `video.create`需要申请权限需要用户授权
     * <p>
     * 该接口用于上传视频文件到文件服务器，获取视频文件video_id。该接口适用于抖音。
     * <p>
     * 注意：
     * <p>
     * 抖音的OAuth API以https://open.douyin.com/开头。
     * <p>
     * 超过50m的视频建议采用分片上传，可以降低网关超时造成的失败。超过128m的视频必须采用分片上传。视频总大小4GB以内。单个分片建议20MB，最小5MB。
     * <p>
     * 视频文件要求：
     * <p>
     * 为了更好的观看体验，推荐上传16:9，分辨率为720p（1280x720）及以上的竖版视频。
     * <p>
     * 支持常用视频格式，推荐使用 mp4 、webm。
     * <p>
     * 视频文件大小不超过128M，时长在15分钟以内。
     * <p>
     * 带品牌logo或品牌水印的视频，会命中抖音的审核逻辑，有比较大的概率导致分享视频推荐降权处理/分享视频下架处理/分享账号被封禁处理。强烈建议第三方应用自行处理好分享内容中的不合规水印。
     * <p>
     * 视频审核逻辑与端上一致。
     */
    VIDEO_UPLOAD(RequestMethod.POST,
            VideoUploadReqBody.class,
            VideoUploadRes.class,
            "/video/upload?open_id=%s&"),

    /**
     * <a href="https://open.douyin.com/platform/doc/6848798087398393859">分片初始化上传</a>
     * <p>
     * Scope: `video.create`需要申请权限需要用户授权
     * <p>
     * 该接口用于分片上传视频文件到文件服务器，先初始化上传获取upload_id。该接口适用于抖音。
     * <p>
     * 注意：
     * <p>
     * 抖音的OAuth API以https://open.douyin.com/开头。
     * <p>
     * 超过50m的视频建议采用分片上传，可以降低网关超时造成的失败。超过128m的视频必须采用分片上传。视频总大小4GB以内。单个分片建议20MB，最小5MB。
     * <p>
     * 视频文件要求：
     * <p>
     * 为了更好的观看体验，推荐上传16:9，分辨率为720p（1280x720）及以上的竖版视频。
     * <p>
     * 支持常用视频格式，推荐使用 mp4 、webm。
     * <p>
     * 带品牌logo或品牌水印的视频，会命中抖音的审核逻辑，有比较大的概率导致分享视频推荐降权处理/分享视频下架处理/分享账号被封禁处理。强烈建议第三方应用自行处理好分享内容中的不合规水印。
     * <p>
     * 视频审核逻辑与端上一致。
     */
    VIDEO_PART_INIT(RequestMethod.POST,
            VoidBody.class,
            VideoPartInitRes.class,
            "/video/part/init?open_id=%s&"),

    /**
     * <a href="https://open.douyin.com/platform/doc/6848798087226460172">上传视频分片到文件服务器</a>
     * <p>
     * Scope: `video.create`需要申请权限需要用户授权
     * <p>
     * 该接口用于分片上传视频文件到文件服务器，上传阶段。该接口适用于抖音。
     * <p>
     * 注意：
     * <p>
     * 抖音的OAuth API以https://open.douyin.com/开头。
     * <p>
     * 超过50m的视频建议采用分片上传，可以降低网关超时造成的失败。超过128m的视频必须采用分片上传。视频总大小4GB以内。单个分片建议20MB，最小5MB。
     * <p>
     * 注意参数中upload_id作为url参数时，必须encode，只对upload_id进行encode
     * <p>
     * 视频文件要求：
     * <p>
     * 为了更好的观看体验，推荐上传16:9，分辨率为720p（1280x720）及以上的竖版视频。
     * <p>
     * 支持常用视频格式，推荐使用 mp4 、webm。
     * <p>
     * 带品牌logo或品牌水印的视频，会命中抖音的审核逻辑，有比较大的概率导致分享视频推荐降权处理/分享视频下架处理/分享账号被封禁处理。强烈建议第三方应用自行处理好分享内容中的不合规水印。
     * <p>
     * 视频审核逻辑与端上一致。
     */
    VIDEO_PART_UPLOAD(RequestMethod.POST,
            VideoUploadReqBody.class,
            DefaultResponse.class,
            "/video/part/upload?open_id=%s&upload_id=%s&part_number=%d&"),


    /**
     * <a href="https://open.douyin.com/platform/doc/6848798087398361091">分片完成上传</a>
     * <p>
     * Scope: `video.create`需要申请权限需要用户授权
     * <p>
     * 该接口用于分片上传视频文件到文件服务器，完成上传。该接口适用于抖音。
     * <p>
     * 注意：
     * <p>
     * 抖音的OAuth API以https://open.douyin.com/开头。
     * <p>
     * 超过50m的视频建议采用分片上传，可以降低网关超时造成的失败。超过128m的视频必须采用分片上传。视频总大小4GB以内。单个分片建议20MB，最小5MB。
     * <p>
     * 视频文件要求：
     * <p>
     * 为了更好的观看体验，推荐上传16:9，分辨率为720p（1280x720）及以上的竖版视频。
     * <p>
     * 支持常用视频格式，推荐使用 mp4 、webm。
     * <p>
     * 带品牌logo或品牌水印的视频，会命中抖音的审核逻辑，有比较大的概率导致分享视频推荐降权处理/分享视频下架处理/分享账号被封禁处理。强烈建议第三方应用自行处理好分享内容中的不合规水印。
     * <p>
     * 视频审核逻辑与端上一致。
     */
    VIDEO_PART_COMPLETE(RequestMethod.POST,
            VoidBody.class,
            VideoPartCompleteRes.class,
            "/video/part/complete?open_id=%s&upload_id=%s&"),

    /**
     * <a href="https://open.douyin.com/platform/doc/6848798087398328323">创建抖音视频</a>
     * <p>
     * Scope: `video.create`需要申请权限需要用户授权
     * <p>
     * 该接口用于创建抖音视频（支持话题, 小程序等功能）。该接口适用于抖音。
     * <p>
     * 注意：
     * <p>
     * 抖音的OAuth API以https://open.douyin.com/开头。
     * <p>
     * 挂载小程序请先完成申请->申请及使用说明
     * <p>
     * 创建抖音视频后, 会有一个审核过程, 期间只有自己可见。
     * <p>
     * 如果发布视频想@用户，需要获取昵称与open_id。
     * <p>
     * 目前发布视频接口，支持三种锚点类型，包括：小程序、游戏、poi。不支持携带多种类型的锚点。
     * <p>
     * 如需代用户创建视频，除授权外，每次调用都需要在产品设计中让用户明确感知相关操作。如发现未经用户感知代用户创建视频，或将回收相关接口权限并处罚应用及账号。
     */
    VIDEO_CREATE(RequestMethod.POST,
            VideoCreateReqBody.class,
            VideoCreateRes.class,
            "/video/create?open_id=%s&"),

    /**
     * <a href="https://open.douyin.com/platform/doc/6848798493293807628">上传图片到文件服务器</a>
     * <p>
     * Scope: `video.create`需要申请权限需要用户授权
     * 该接口用于上传图片到文件服务器，得到图片的唯一标志image_id。该接口适用于抖音。
     * <p>
     * 注意：
     * <p>
     * 抖音的OAuth API以https://open.douyin.com/开头。<p>
     * 图片文件要求：
     * <p>
     * 图片大小不超过100M。<p>
     * 强烈建议自行处理好分享内容中的不合规水印<p>
     * 图片审核逻辑与端上一致。
     */
    IMAGE_UPLOAD(RequestMethod.POST,
            ImageUploadReqBody.class,
            ImageUploadRes.class,
            "/image/upload?open_id=%s&"),

    /**
     * <a href="https://open.douyin.com/platform/doc/6848798493293774860">发布图片</a>
     * Scope: `video.create`需要申请权限需要用户授权<p>
     * 该接口用于发布图片抖音（支持话题，小程序等功能）；该接口适用于抖音。<p>
     * <p>
     * 注意：
     * <p>
     * 抖音的OAuth API以https://open.douyin.com/开头。<p>
     * 调用/image/create/发布图片到抖音。会有一个审核过程，期间只有自己可见。<p>
     */
    IMAGE_CREATE(RequestMethod.POST,
            ImageCreateBody.class,
            VideoCreateRes.class,
            "/image/create?open_id=%s&"),

    /**
     * <a href="https://open.douyin.com/platform/doc/6848806536383383560">删除授权用户发布的视频</a>
     * <p>
     * scope：'video.delete'需要申请权限需要用户授权
     * <p>
     * 该接口用于删除授权用户该抖音账号下的视频。该接口适用于抖音。
     * <p>
     * 注意：
     * <p>
     * 抖音的OAuth API以https://open.douyin.com/开头。
     * <p>
     * 可以删除已授权用户自己发布的视频。
     * <p>
     * 删除的视频无法恢复，请谨慎操作！
     */
    VIDEO_DELETE(RequestMethod.POST,
            VideoDeleteRequestBody.class,
            DefaultResponse.class,
            "/video/delete?open_id=%s&"),

    /**
     * <a href="https://open.douyin.com/platform/doc/6848806536383318024">询授权账号视频数据</a>查
     * <p>
     * Scope：'video.list'需要用户授权需要申请权限
     * <p>
     * 该接口用于分页获取用户所有视频的数据，返回的数据是实时的。该接口适用于抖音。
     * <p>
     * 注意：
     * <p>
     * 抖音的OAuth API以https://open.douyin.com/开头。
     */
    VIDEO_LIST(RequestMethod.GET,
            VoidBody.class,
            VideoListRes.class,
            "/video/list?open_id=%s&cursor=%d&count=%d&"),

    /**
     * <a href="https://open.douyin.com/platform/doc/6848806544931325965">查询指定视频数据</a>
     * <p>
     * Scope: `video.data`需要用户授权需要申请权限
     * <p>
     * 该接口 用于查询用户特定视频的数据, 如点赞数, 播放数等，返回的数据是实时的。该接口适用于抖音。
     * <p>
     * 注意：
     * <p>
     * 抖音的OAuth API以https://open.douyin.com/开头。
     * <p>
     * 有两种方式获取item_id(抖音视频id):
     * <p>
     * 查询视频分享结果和数据;
     * <p>
     * <a href="https://open.douyin.com/platform/doc/6848798087398328323">发布抖音视频</a>
     */
    VIDEO_DATA(RequestMethod.POST,
            VideoDataReqBody.class,
            VideoListRes.class,
            "/video/data?open_id=%s&"),

    /**
     * <a href="https://open.douyin.com/platform/doc/6848798622172121099">查询视频分享结果及数据获取share-id</a>
     * <p>
     * Scope: `aweme.share`不需要用户授权
     * <p>
     * 该接口用获取share_id；该接口适用于抖音。
     * <p>
     * share_id 可以用于：
     * <p>
     * 追踪分享的视频是否成功。
     * <p>
     * 获取分享视频的数据,如点赞数, 评论数等。
     * <p>
     * 接入步骤：
     * <p>
     * 去http://open.douyin.com/platform/business申请发布视频的能力。
     * <p>
     * 分享前获取标识单次分享的share_id。
     * <p>
     * 调用分享sdk时传入share_id。
     * <p>
     * 基于Webhooks接收分享成功的回调信息
     * <p>
     * item_id可用于查询<a href="https://open.douyin.com/platform/doc/6848806544931325965">视频数据</a>。
     */
    VIDEO_SHARE_ID(RequestMethod.GET,
            VoidBody.class,
            VideoShareDataRes.class,
            "/share-id?need_callback=%b&source_style_id=%s&default_hashtag=%s&link_param=%s"
    ),

    /**
     * <a href="https://open.douyin.com/platform/doc/6848806527751555086">查询视频携带的地点信息</a>
     * <p>
     * Scope: `poi.search`不需要用户授权需要申请权限<p>
     * 该接口用于poi信息的查询，应用场景为在发布内容时查询并携带该poi信息发布至抖音，该接口当前定向开放给媒体行业第三方；若其他行业有相关诉求，也可发起权限申请，会有相关工作人员尽快给予反馈。<p>
     * <p>
     * 申请步骤：前往管理中心-应用详情-特殊权限中申请
     * <p>
     * 查询POI信息：通过用POI的关键词进行条件搜索，获取匹配POI列表，例如：肯德基、朝阳公园等；<p>
     * <p>
     * 注意：
     * <p>
     * 抖音的OAuth API以https://open.douyin.com/开头。
     */
    VIDEO_POI(RequestMethod.GET,
            VoidBody.class,
            VideoPoiRes.class,
            "/poi/search/keyword?cursor=0&count=10&keyword=%s");


    private final RequestMethod requestMethod;

    private final Class<? extends DyOpenApiRequestBody> requestBodyClass;

    private final Class<? extends DyOpenApiResponse> responseClass;

    private final String path;


    public String getUrl(DyStorageManager storageManager, ApiPlatform apiPlatform) {


        String prefix = DEFAULT_PREFIX;

        if (storageManager != null) {
            prefix = storageManager.getOpenApiConfig().getApp().getOpenUrl();
        }
        if (apiPlatform == ApiPlatform.DOU_YIN) {
            return prefix + getPath();
        }
        // 西瓜或者头条需要加响应的前缀
        return prefix + apiPlatform.getPathPrefix() + getPath();
    }
}
