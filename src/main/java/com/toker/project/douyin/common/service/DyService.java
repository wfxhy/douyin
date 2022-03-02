package com.toker.project.douyin.common.service;

import com.toker.project.douyin.common.bean.AccessToken;
import com.toker.project.douyin.common.bean.RefreshToken;
import com.toker.project.douyin.common.constant.RequestParameterName;
import com.toker.project.douyin.common.enums.ApiPlatform;
import com.toker.project.douyin.common.enums.RequestBodyType;
import com.toker.project.douyin.common.exception.ApiCallException;
import com.toker.project.douyin.common.exception.InvalidRequestParamException;
import com.toker.project.douyin.common.exception.LandExpirationException;
import com.toker.project.douyin.common.http.Head;
import com.toker.project.douyin.common.http.HttpExecutor;
import com.toker.project.douyin.common.http.okhttp.OkHttpExecutor;
import com.toker.project.douyin.common.storage.DyStorageManager;
import com.toker.project.douyin.common.storage.MemoryStorageManager;
import com.toker.project.douyin.common.storage.RedisStorageManager;

/**
 * 被外调用的接口
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/9 9:12
 * @modified mdmbct
 * @since 1.0
 */
public interface DyService {


    /**
     * get请求 自动拼接请求url中的access_token参数
     *
     * @param openId open id 方便自动获取token
     * @param url  拼装完数据的url 但是该url中不能含有{@link RequestParameterName#ACCESS_TOKEN}参数
     * @param apiPlatform 调用的授权api所属平台 {@link ApiPlatform}
     * @param heads 请求头
     * @return 返回的json数据
     * @throws ApiCallException 接口调用异常
     * @throws InvalidRequestParamException 无效的请求参数、url异常
     */
    String getRequest(String openId, String url, ApiPlatform apiPlatform, Head[] heads) throws ApiCallException, InvalidRequestParamException;

    /**
     * post请求 自动拼接请求url中的access_token参数
     *
     * @param <T>  body类型
     * @param url  拼装完数据的url 但是该url中不能含有access_token参数
     * @param body 请求体
     * @param openId open id 方便自动获取token
     * @param apiPlatform 调用的授权api所属平台 {@link ApiPlatform}
     * @param requestBodyType 请求体类型 {@link RequestBodyType}
     * @param heads 请求头
     * @return 返回的json数据
     * @throws ApiCallException 接口调用异常
     * @throws InvalidRequestParamException 无效的请求参数、url异常
     */
    <T> String postRequest(String url, T body, String openId, ApiPlatform apiPlatform, RequestBodyType requestBodyType, Head[] heads) throws ApiCallException, InvalidRequestParamException;


    /**
     * 用open id获取AccessToken 如果AccessToken过期会尝试拿RefreshToken换取
     * 如果RefreshToken也过期的话 抛出{@link LandExpirationException}登录信息过期异常
     *
     * @param openId    open id
     * @param apiPlatform 授权api 平台 {@link ApiPlatform}
     * @param autoRenew 当AccessToken过期时 是否自动获取新的 如果为false时 且AccessToken过期 返回空
     * @return AccessToken {@link AccessToken}
     * @throws LandExpirationException 登陆信息过期异常
     *                                 当authRew = true时，且RefreshToken失效时抛出该异常
     */
    AccessToken getAccessToken(String openId, ApiPlatform apiPlatform, boolean autoRenew) throws LandExpirationException;

    /**
     * 用open id获取RefreshToken
     *
     * @param openId open id
     * @return RefreshToken {@link RefreshToken}
     * @throws LandExpirationException 登陆信息过期异常 获取不到RefreshToken时抛出该异常
     */
    RefreshToken getRefreshToken(String openId) throws LandExpirationException;

    /**
     * 获取存储管理器
     *
     * @return 存储管理器 {@link DyStorageManager}
     * @see MemoryStorageManager
     * @see RedisStorageManager
     */
    DyStorageManager getDyStorageManager();

    /**
     * 获取HttpExecutor
     *
     * @return {@link HttpExecutor}
     * @see OkHttpExecutor
     */
    HttpExecutor getHttpExecutor();

}
