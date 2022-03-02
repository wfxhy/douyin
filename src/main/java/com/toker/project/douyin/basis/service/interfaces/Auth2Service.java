package com.toker.project.douyin.basis.service.interfaces;

import com.toker.project.douyin.common.enums.ApiPlatform;
import com.toker.project.douyin.common.exception.ApiAuthFailedException;
import com.toker.project.douyin.common.exception.ApiCallException;
import com.toker.project.douyin.common.exception.LandExpirationException;
import com.toker.project.douyin.common.response.auth.AccessTokenRes;
import com.toker.project.douyin.common.response.auth.ClientTokenRes;
import com.toker.project.douyin.common.response.auth.RenewRefreshTokenRes;

/**
 * <a href="https://open.douyin.com/platform/doc/6848806493387606024">抖音OpenAPI 帐号授权 https://open.douyin.com/platform/doc/6848834666171009035</a>
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/9 11:12
 * @modified mdmbct
 * @since 1.0
 */
public interface Auth2Service {


    /**
     * 获取AccessToken
     *
     * @param code 授权码
     * @param apiPlatform 授权接口所属平台 {@link ApiPlatform}
     * @return {@link AccessTokenRes.AccessTokenResData}
     * @throws ApiAuthFailedException 认证失败异常
     */
    AccessTokenRes.AccessTokenResData authAccessToken(String code, ApiPlatform apiPlatform) throws ApiCallException;

    /**
     * 用openid的RefreshToken换取新的AccessToken
     *  @param openId open id
     * @param apiPlatform 授权接口所属平台 {@link ApiPlatform}
     * @return {@link AccessTokenRes.AccessTokenResData}
     * @throws ApiAuthFailedException 认证失败异常
     * @throws LandExpirationException 登陆信息过期异常
     */
    AccessTokenRes.AccessTokenResData authExchangeAccessToken(String openId, ApiPlatform apiPlatform) throws ApiCallException;

    /**
     * 通过旧的refresh_token获取新的refresh_token，调用后旧refresh_token会失效，新refresh_token有30天有效期。最多只能获取5次新的refresh_token，5次过后需要用户重新授权。<p>
     * 这里通过open id获取refresh token
     *
     * @param openId open id
     * @return {@link RenewRefreshTokenRes.RenewRefreshTokenResData}
     * @throws ApiCallException 接口调用异常
     */
    RenewRefreshTokenRes.RenewRefreshTokenResData renewRefreshToken(String openId) throws ApiCallException;

    /**
     * 获取接口调用的凭证client_access_token，主要用于调用不需要用户授权就可以调用的接口；该接口适用于抖音/头条授权。<p></p>
     * 调用之后会保存起来
     *
     * @param platform 抖音/头条
     * @return {@link ClientTokenRes.ClientTokenResData}
     * @throws ApiCallException 接口调用异常
     */
    ClientTokenRes.ClientTokenResData authClientToken(ApiPlatform platform) throws ApiCallException;
}
