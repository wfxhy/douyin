package com.toker.project.douyin.common.storage;

import com.toker.project.douyin.common.bean.BaseToken;
import com.toker.project.douyin.common.bean.ClientToken;
import com.toker.project.douyin.common.config.DyOpenApiConfig;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 存储APi配置和用户Token
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/18 0:16
 * @modified mdmbct
 * @since 1.0
 */
public class ApiConfigAndTokenPool implements Serializable {

    private static final long serialVersionUID = 2576316088999667221L;

    @Getter(AccessLevel.PACKAGE)
    @Setter(AccessLevel.PACKAGE)
    private DyOpenApiConfig apiConfig;

    @Getter(AccessLevel.PACKAGE)
    @Setter(AccessLevel.PACKAGE)
    private ClientToken clientToken;

    private final ConcurrentHashMap<String, BaseToken> tokenPool;

    public ApiConfigAndTokenPool(DyOpenApiConfig apiConfig) {
        this.apiConfig = apiConfig;
        tokenPool = new ConcurrentHashMap<>();
    }

    protected void setToken(String key, BaseToken token) {
        tokenPool.put(key, token);
    }

    protected void deleteToken(String key) {
        tokenPool.remove(key);
    }

    public BaseToken getToken(String key) {
        return tokenPool.get(key);
    }



}
