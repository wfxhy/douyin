package com.toker.project.douyin.common.storage;


import com.toker.project.douyin.common.bean.AccessToken;
import com.toker.project.douyin.common.bean.ClientToken;
import com.toker.project.douyin.common.bean.RefreshToken;
import com.toker.project.douyin.common.config.DyOpenApiConfig;

/**
 * 配置存储
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/9 13:54
 * @modified mdmbct
 * @since 1.0
 */
public interface DyStorageManager {

    /**
     * 设置配置 当配置变动时 可调用该方法更新配置</p>
     * @see RedisStorageManager#setOpenApiConfig(DyOpenApiConfig)
     * @see MemoryStorageManager#setOpenApiConfig(DyOpenApiConfig)
     *
     * @param config {@link DyOpenApiConfig}
     */
    void setOpenApiConfig(DyOpenApiConfig config);

    /**
     * 获取配置
     * @return {@link DyOpenApiConfig}
     */
    DyOpenApiConfig getOpenApiConfig();

    /**
     * 保存AccessToken
     *
     * @param openId open id
     * @param token  AccessToken
     */
    void saveAccessToken(String openId, AccessToken token);

    /**
     * 依据open id获取AccessToken过期就返回空
     * @param openId open id
     * @return AccessToken
     */
    AccessToken getAccessToken(String openId);


    /**
     * 保存RefreshToken
     * @param openId open id
     * @param token RefreshToken
     */
    void saveRefreshToken(String openId, RefreshToken token);

    /**
     * 依据open id获取RefreshToken 过期就返回空
     * @param openId open id
     * @return RefreshToken
     */
    RefreshToken getRefreshToken(String openId);

    /**
     * 保存client token
     * @param token
     */
    void saveClientToken(ClientToken token);

    /**
     * 获取client token
     * @return client token
     */
    ClientToken getClientToken();

}
