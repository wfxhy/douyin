package com.toker.project.douyin.common.enums;


import com.toker.project.douyin.common.body.DyOpenApiRequestBody;
import com.toker.project.douyin.common.body.DyOpenApiRequestBody;
import com.toker.project.douyin.common.response.DyOpenApiResponse;
import com.toker.project.douyin.common.storage.DyStorageManager;

/**
 * DouYin Open Api 接口中的参数access_token必须全部省略 默认不做是否存在检查
 * 由{@link DyService#getRequest(String, String, ApiPlatform, com.toker.project.douyin.common.http.Head[])}自动拼接
 * <p>
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/8 10:47
 * @modified mdmbct
 * @since 1.0
 */
public interface DyOpenApi {

    RequestMethod getRequestMethod();

    /**
     * 获取具体api地址
     */
    String getPath();

    /**
     * 请求体 如果没有请求体 应返回{@link VoidBody}的class
     *
     * @return
     */
    Class<? extends DyOpenApiRequestBody> getRequestBodyClass();

    /**
     * 响应结果class
     *
     * @return
     */
    Class<? extends DyOpenApiResponse> getResponseClass();



    /**
     * 获取授权时完整url prefix + path
     *
     * @param storageManager  配置存储管理器
     * @param apiPlatform 认证api平台 调用认证接口时 赋值，否则为空
     * @return prefix + path
     */
    default String getAuthUrl(DyStorageManager storageManager, ApiPlatform apiPlatform) {

        if (apiPlatform != null && storageManager != null) {
            return storageManager.getOpenApiConfig().getApp().getAuthApisPrefix().get(apiPlatform.getCode()) + getPath();
        }

        if (storageManager != null) {
            return storageManager.getOpenApiConfig().getApp().getOpenUrl() + getPath();
        }

        return DEFAULT_PREFIX + getPath();
    }

    /**
     * 获取完整url prefix + path
     *
     * @param storageManager  配置存储管理器
     * @param apiPlatform 认证api平台 调用认证接口时 赋值，否则为空
     * @return prefix + path
     */
    default String getUrl(DyStorageManager storageManager, ApiPlatform apiPlatform) {

        if (storageManager != null) {
            return storageManager.getOpenApiConfig().getApp().getOpenUrl() + getPath();
        }

        return DEFAULT_PREFIX + getPath();
    }



    String DEFAULT_PREFIX = "https://open.douyin.com";

}
