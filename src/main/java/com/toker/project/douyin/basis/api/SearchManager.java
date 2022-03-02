package com.toker.project.douyin.basis.api;


import com.toker.project.douyin.basis.response.video.VideoListRes;
import com.toker.project.douyin.common.body.DyOpenApiRequestBody;
import com.toker.project.douyin.common.body.VoidBody;
import com.toker.project.douyin.common.enums.DyOpenApi;
import com.toker.project.douyin.common.enums.RequestMethod;
import com.toker.project.douyin.common.response.DyOpenApiResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 搜索管理api
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/18 11:18
 * @modified mdmbct
 * @since 1.0
 */
@Getter
@RequiredArgsConstructor
public enum SearchManager implements DyOpenApi {

    /**
     * <a href="https://open.douyin.com/platform/doc/6848806544931358733">关键词视频搜索</a> <p>
     * Scope: `video.search `需要申请权限需要用户授权<p>
     * <p>
     * 该接口用于通过关键词搜索全站视频,类似抖音端上搜索。使用前请到 管理中心-应用详情-关键词视频管理-关键词管理 创建关键词。<p>
     * <p>
     * 该接口只返回最近1天的视频
     */
    VIDEO_SEARCH(
            RequestMethod.GET,
            VoidBody.class,
            VideoListRes.class,
            "/video/search?open_id=%s&cursor=%d&count=%d&keyword=%s&"
    ),


    ;


    private final RequestMethod requestMethod;

    private final Class<? extends DyOpenApiRequestBody> requestBodyClass;

    private final Class<? extends DyOpenApiResponse> responseClass;

    private final String path;
}
