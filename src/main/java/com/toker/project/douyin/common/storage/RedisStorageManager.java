package com.toker.project.douyin.common.storage;

import com.alibaba.fastjson.JSONObject;
import com.toker.framework.redis.RedisCache;
import com.toker.project.douyin.common.bean.AccessToken;
import com.toker.project.douyin.common.bean.ClientToken;
import com.toker.project.douyin.common.bean.RefreshToken;
import com.toker.project.douyin.common.config.DyOpenApiConfig;

import java.util.concurrent.TimeUnit;

/**
 * redis配置存储管理器
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/9 14:02
 * @modified mdmbct
 * @since 1.0
 */
public class RedisStorageManager extends BaseStorageManager {
    private final RedisCache redisCache;

    /**
     * 构造函数 顺便将配置存储redis
     *
     */
    public RedisStorageManager(RedisCache redisCache,DyOpenApiConfig config) {
        super(config);
        this.redisCache = redisCache;
        saveOpenApiConfigToCache();
    }

    void saveOpenApiConfigToCache() {
        String s = JSONObject.toJSONString(openApiConfig);
        redisCache.setCacheObject(openApiConfig.getConfigCacheKey(), s);
    }

    /**
     * 设置配置 同时保存配置至redis <p>
     *
     * @param config {@link DyOpenApiConfig}
     */
    @Override
    public void setOpenApiConfig(DyOpenApiConfig config) {
        super.setOpenApiConfig(config);
        saveOpenApiConfigToCache();
    }

    public DyOpenApiConfig getOpenApiConfigFromCache() {
        return redisCache.getCacheObject(openApiConfig.getConfigCacheKey());
    }

    /**
     * 保存AccessToken token过期后自动被redis删除
     *
     * @param openId open id
     * @param token  AccessToken
     */
    @Override
    public void saveAccessToken(String openId, AccessToken token) {
        redisCache.setCacheObject(accessTokenKey(openId), token);
        redisCache.expire(accessTokenKey(openId), token.getExpireIn(), TimeUnit.MILLISECONDS);

    }

    @Override
    public AccessToken getAccessToken(String openId) {
        return redisCache.getCacheObject(accessTokenKey(openId));
    }

    /**
     * 保存RefreshToken token过期后自动被redis删除
     *
     * @param openId open id
     * @param token  RefreshToken
     */
    @Override
    public void saveRefreshToken(String openId, RefreshToken token) {
        redisCache.setCacheObject(refreshTokenKey(openId), token);
        redisCache.expire(refreshTokenKey(openId), token.getExpireIn(), TimeUnit.MILLISECONDS);

    }

    @Override
    public RefreshToken getRefreshToken(String openId) {
        return redisCache.getCacheObject(refreshTokenKey(openId));
    }

    @Override
    public void saveClientToken(ClientToken token) {
        redisCache.setCacheObject(clientTokenKey(), token);
        redisCache.expire(clientTokenKey(),token.getExpireIn(), TimeUnit.MILLISECONDS);
    }

    @Override
    public ClientToken getClientToken() {
        return redisCache.getCacheObject(clientTokenKey());
    }
}