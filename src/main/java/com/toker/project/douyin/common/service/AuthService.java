package com.toker.project.douyin.common.service;

import com.alibaba.fastjson.JSONObject;
import com.toker.project.douyin.common.bean.AccessToken;
import com.toker.project.douyin.common.bean.RefreshToken;
import com.toker.project.douyin.common.enums.ApiPlatform;
import com.toker.project.douyin.common.enums.Auth2;
import com.toker.project.douyin.common.enums.ErrorCode;
import com.toker.project.douyin.common.exception.ApiAuthFailedException;
import com.toker.project.douyin.common.exception.ApiCallException;
import com.toker.project.douyin.common.http.HttpExecutor;
import com.toker.project.douyin.common.response.auth.AccessTokenRes;
import com.toker.project.douyin.common.response.auth.RenewRefreshTokenRes;
import com.toker.project.douyin.common.storage.DyStorageManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 认证
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/18 22:26
 * @modified mdmbct
 * @since 1.0
 */
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final DyStorageManager storageManager;

    private final HttpExecutor httpExecutor;

    /**
     * 获取用户授权第三方接口调用的凭证access_token；该接口适用于抖音/头条授权。
     *
     * @param code 授权码
     * @param apiPlatform 抖音/头条
     * @return {@link AccessTokenRes.AccessTokenResData }
     * @throws ApiCallException 接口调用异常
     */
    public AccessTokenRes.AccessTokenResData authAccessToken(String code, ApiPlatform apiPlatform) throws ApiCallException {

        String url = String.format(Auth2.OAUTH_ACCESS_TOKEN.getAuthUrl(storageManager, apiPlatform),
                storageManager.getOpenApiConfig().getApp().getKey(),
                storageManager.getOpenApiConfig().getApp().getSecret(),
                code
        );

        String result = httpExecutor.executeGet(null, url);

        return getAndSaveTokenFromAuthApiResponse(JSONObject.parseObject(result, AccessTokenRes.class));
    }

    /**
     * 使用refreshToken刷新AccessToken
     *
     * @param refreshToken 通过access_token获取到的refresh_token参数<p/>
     *                     （在<a href="https://open.douyin.com/platform/doc/6848806493387606024">抖音OpenAPI 帐号授权 获取access_token</a>）
     *                     返回的json中的“refresh_token”字段
     * @param apiPlatform  授权api 平台 {@link ApiPlatform}
     * @return 刷新后的accessToken
     */
    public AccessToken renewAccessToken(RefreshToken refreshToken, ApiPlatform apiPlatform) throws ApiCallException{

        AccessTokenRes.AccessTokenResData accessTokenResData = requestAccessTokenByRefreshToken(refreshToken, apiPlatform);
        return new AccessToken(System.currentTimeMillis(), accessTokenResData.getExpiresIn() * 1000L, accessTokenResData.getAccessToken());
    }

    /**
     * 用RefreshToken请求AccessToken和新的RefreshToken，并将新的token存起来
     *
     * @param refreshToken 旧的RefreshToken
     * @param apiPlatform  调用的授权api所属平台 {@link ApiPlatform}
     * @return {@link AccessTokenRes.AccessTokenResData}
     */
    public AccessTokenRes.AccessTokenResData requestAccessTokenByRefreshToken(RefreshToken refreshToken, ApiPlatform apiPlatform) throws ApiCallException {

        String url = String.format(Auth2.OAUTH_REFRESH_TOKEN.getAuthUrl(storageManager, apiPlatform),
                storageManager.getOpenApiConfig().getApp().getKey(),
                "refresh_token",
                refreshToken.getValue()
        );

        String result = httpExecutor.executeGet(null, url);

        return getAndSaveTokenFromAuthApiResponse(JSONObject.parseObject(result, AccessTokenRes.class));
    }

    /**
     * 从授权API中获取AccessToken和RefreshToken并保存
     *
     * @param accessTokenRes@return AccessTokenResData 包含 AccessToken 和 RefreshToken
     */
    private AccessTokenRes.AccessTokenResData getAndSaveTokenFromAuthApiResponse(AccessTokenRes accessTokenRes) throws ApiAuthFailedException {

        AccessTokenRes.AccessTokenResData data = accessTokenRes.getData();

        if (data.getErrorCode() == ErrorCode.SUCCESS.getValue()) {
            saveToken(data);
            return data;
        }

        log.error(accessTokenRes.getErrorMsg());
        throw new ApiAuthFailedException(accessTokenRes.getErrorMsg());
    }

    private void saveToken(AccessTokenRes.AccessTokenResData data) {
        // 保存AccessToken和RefreshToken
        long currentTimeMillis = System.currentTimeMillis();
        storageManager.saveAccessToken(data.getOpenId(), new AccessToken(currentTimeMillis, data.getExpiresIn() * 1000L, data.getAccessToken()));
        storageManager.saveRefreshToken(data.getOpenId(), new RefreshToken(currentTimeMillis, data.getRefreshExpiresIn() * 1000L, data.getRefreshToken()));
    }



    ////////////////////////////////////////////////////// refresh token //////////////////////////////////////////////////////

    /**
     * 使用旧refreshToken刷新refreshToken
     *
     * @param oldRefreshToken 旧refreshToken
     * @param openId open id
     * @return 新的RefreshToken
     */
    public RefreshToken renewRefreshToken(RefreshToken oldRefreshToken, String openId) throws ApiCallException {
        RenewRefreshTokenRes.RenewRefreshTokenResData data = requestRefreshTokenByOld(oldRefreshToken, openId);
        return new RefreshToken(System.currentTimeMillis(), data.getExpiresIn() * 1000L, data.getRefreshToken());
    }

    /**
     * 使用旧refreshToken刷新refreshToken
     *
     * @param oldRefreshToken 旧的refreshToken
     * @param openId open id
     * @return {@link RenewRefreshTokenRes.RenewRefreshTokenResData}
     */
    public RenewRefreshTokenRes.RenewRefreshTokenResData requestRefreshTokenByOld(RefreshToken oldRefreshToken, String openId) throws ApiCallException {

        String url = String.format(Auth2.OAUTH_RENEW_REFRESH_TOKEN.getAuthUrl(storageManager, ApiPlatform.DOU_YIN), storageManager.getOpenApiConfig().getApp().getKey(), oldRefreshToken.getValue());
        String result = httpExecutor.executeGet(null, url);
        RenewRefreshTokenRes renewRefreshTokenRes = JSONObject.parseObject(result, RenewRefreshTokenRes.class);

        RenewRefreshTokenRes.RenewRefreshTokenResData data = renewRefreshTokenRes.getData();
        if (data.getErrorCode() == ErrorCode.SUCCESS.getValue()) {
            RefreshToken refreshToken = new RefreshToken(System.currentTimeMillis(), data.getExpiresIn() * 1000L, data.getRefreshToken());
            storageManager.saveRefreshToken(openId, refreshToken);
            return data;
        }

        log.error(renewRefreshTokenRes.getErrorMsg());
        throw new ApiAuthFailedException(renewRefreshTokenRes.getErrorMsg());
    }

}
