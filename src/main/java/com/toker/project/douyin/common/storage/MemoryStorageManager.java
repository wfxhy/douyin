package com.toker.project.douyin.common.storage;


import com.toker.project.douyin.common.bean.AccessToken;
import com.toker.project.douyin.common.bean.BaseToken;
import com.toker.project.douyin.common.bean.ClientToken;
import com.toker.project.douyin.common.bean.RefreshToken;
import com.toker.project.douyin.common.config.DyOpenApiConfig;
import com.toker.project.douyin.common.utils.file.FileUtils;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;

/**
 * 内存配置存储管理器
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/9 14:00
 * @modified mdmbct
 * @since 1.0
 */
@Slf4j
public class MemoryStorageManager extends BaseStorageManager {

    private final String memoryLocalFilePath;

    private ApiConfigAndTokenPool pool;


    public MemoryStorageManager(DyOpenApiConfig openApiConfig, String memoryLocalSave) {
        super(openApiConfig);

        File file = new File(memoryLocalSave);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                log.warn("无法创建文件夹" + memoryLocalSave + "，检查权限！");
            }
        }

        this.memoryLocalFilePath = memoryLocalSave + "/configAndToken.bin";
        if (new File(memoryLocalFilePath).exists()) {
            try {
                recoveryApiConfigAndToken();
            } catch (IOException | ClassNotFoundException e) {
                log.warn("恢复配置和Token失败：" + e.getMessage());
                this.pool = new ApiConfigAndTokenPool(openApiConfig);
            }
        } else {
            log.info("没有本地配置和Token文件");
            this.pool = new ApiConfigAndTokenPool(openApiConfig);
        }
    }

    @Override
    public void setOpenApiConfig(DyOpenApiConfig config) {
        super.setOpenApiConfig(config);
        pool.setApiConfig(config);
    }

    @Override
    public void saveAccessToken(String openId, AccessToken token) {
        pool.setToken(accessTokenKey(openId), token);
    }

    @Override
    public AccessToken getAccessToken(String openId) {
        return (AccessToken) getToken(accessTokenKey(openId));
    }

    @Override
    public void saveRefreshToken(String openId, RefreshToken token) {
        pool.setToken(refreshTokenKey(openId), token);
    }

    @Override
    public RefreshToken getRefreshToken(String openId) {
        return (RefreshToken) getToken(refreshTokenKey(openId));
    }

    @Override
    public void saveClientToken(ClientToken token) {
        pool.setClientToken(token);
    }

    @Override
    public ClientToken getClientToken() {
        return pool.getClientToken();
    }

    @PreDestroy
    public void storeApiConfigAndTokenLocal() {
        if (pool != null) {
            try {
                FileUtils.writeObject(memoryLocalFilePath, pool);
                log.info("成功保存配置和Token至本地");
            } catch (IOException e) {
                e.printStackTrace();
                log.error("保存配置和Token至本地失败：" + e.getMessage());
            }
        }
    }


    /////////////////////////////////////////// private protected method ///////////////////////////////////////////



    private void recoveryApiConfigAndToken() throws IOException, ClassNotFoundException {
        this.pool = FileUtils.readObjet(memoryLocalFilePath);
    }

    private BaseToken getToken(String tokenKey) {
        BaseToken token = pool.getToken(tokenKey);
        if (token != null && token.getExpireIn() + token.getTakeEffect() <= System.currentTimeMillis()) {
            pool.deleteToken(tokenKey);
            return null;
        }
        return token;
    }



}
