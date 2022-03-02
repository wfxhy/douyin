package com.toker.project.douyin.basis.service;

import com.toker.project.douyin.basis.service.interfaces.Auth2Service;
import com.toker.project.douyin.common.bean.ClientToken;
import com.toker.project.douyin.common.bean.RefreshToken;
import com.toker.project.douyin.common.config.DyOpenApiConfig;
import com.toker.project.douyin.common.enums.ApiPlatform;
import com.toker.project.douyin.common.enums.Auth2;
import com.toker.project.douyin.common.exception.ApiCallException;
import com.toker.project.douyin.common.exception.LandExpirationException;
import com.toker.project.douyin.common.http.HttpExecutor;
import com.toker.project.douyin.common.response.auth.AccessTokenRes;
import com.toker.project.douyin.common.response.auth.ClientTokenRes;
import com.toker.project.douyin.common.response.auth.RenewRefreshTokenRes;
import com.toker.project.douyin.common.service.BaseDyService;
import com.toker.project.douyin.common.storage.DyStorageManager;
import lombok.extern.slf4j.Slf4j;

/**
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/9 11:31
 * @modified mdmbct
 * @since 1.0
 */
@Slf4j
public class Auth2ServiceImpl extends BaseDyService implements Auth2Service {

    private final DyOpenApiConfig apiConfig;

    public Auth2ServiceImpl(DyStorageManager storageManager, HttpExecutor httpExecutor) {
        super(storageManager, httpExecutor);
        apiConfig = storageManager.getOpenApiConfig();
    }

    @Override
    public AccessTokenRes.AccessTokenResData authAccessToken(String code, ApiPlatform apiPlatform) throws ApiCallException {
        return authService.authAccessToken(code, apiPlatform);
    }

    @Override
    public AccessTokenRes.AccessTokenResData authExchangeAccessToken(String openId, ApiPlatform apiPlatform) throws ApiCallException {

        RefreshToken refreshToken = storageManager.getRefreshToken(openId);
        if (refreshToken == null) {
            log.error(LandExpirationException.DEFAULT_MSG);
            throw new LandExpirationException();
        }
        return authService.requestAccessTokenByRefreshToken(refreshToken, apiPlatform);
    }

    @Override
    public RenewRefreshTokenRes.RenewRefreshTokenResData renewRefreshToken(String openId) throws ApiCallException {

        RefreshToken oldRefreshToken = storageManager.getRefreshToken(openId);
        if (oldRefreshToken == null) {
            throw new LandExpirationException();
        }

        return authService.requestRefreshTokenByOld(oldRefreshToken, openId);
    }

    @Override
    public ClientTokenRes.ClientTokenResData authClientToken(ApiPlatform platform) throws ApiCallException{

        String url = formatRequestUrl(Auth2.OAUTH_CLIENT_TOKEN, platform, apiConfig.getApp().getKey(), apiConfig.getApp().getSecret());
        String result = httpExecutor.executeGet(null, url);
        ClientTokenRes.ClientTokenResData data = handleApiResponse(result, ClientTokenRes.class, ClientTokenRes.ClientTokenResData.class);
        storageManager.saveClientToken(new ClientToken(data.getExpiresIn() * 1000L, data.getAccessToken()));
        return data;
    }






}
