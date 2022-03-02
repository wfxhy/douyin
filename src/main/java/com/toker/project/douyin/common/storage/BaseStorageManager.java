package com.toker.project.douyin.common.storage;

import com.toker.project.douyin.common.config.DyOpenApiConfig;

/**
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/9 15:49
 * @modified mdmbct
 * @since 1.0
 */
public abstract class BaseStorageManager implements DyStorageManager {

    protected DyOpenApiConfig openApiConfig;

    public BaseStorageManager(DyOpenApiConfig openApiConfig) {
        this.openApiConfig = openApiConfig;
    }

    @Override
    public void setOpenApiConfig(DyOpenApiConfig config) {
        this.openApiConfig = config;
    }

    @Override
    public DyOpenApiConfig getOpenApiConfig() {
        return openApiConfig;
    }

    protected String accessTokenKey(String openId) {
        return "at_" + openId;
    }

    protected String refreshTokenKey(String openId) {
        return "rt_" + openId;
    }

    protected String clientTokenKey() {
        return "ct_" + openApiConfig.getApp().getKey();}
}
