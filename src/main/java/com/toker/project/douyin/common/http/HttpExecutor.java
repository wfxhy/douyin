package com.toker.project.douyin.common.http;

import com.toker.project.douyin.common.enums.HttpClientType;
import com.toker.project.douyin.common.enums.RequestBodyType;
import com.toker.project.douyin.common.exception.ApiRequestFailedException;
import com.toker.project.douyin.common.exception.InvalidRequestParamException;

/**
 * http请求执行器
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/8 17:37
 * @modified mdmbct
 * @since 1.0
 */
public interface HttpExecutor {


    /**
     * 获取http实现
     *
     * @return {@link HttpClientType}
     */
    HttpClientType getHttpType();


    /**
     * 执行get请求
     *
     * @param heads 请求头
     * @param url  url
     * @return get请求返回的数据
     * @throws InvalidRequestParamException 无效的请求参数、url异常
     * @throws ApiRequestFailedException    api请求失败异常
     */
    String executeGet(Head[] heads, String url) throws InvalidRequestParamException, ApiRequestFailedException;


    /**
     * 执行post请求
     *
     * @param <D>             data的类型
     * @param heads            请求头
     * @param url             url
     * @param data            数据
     * @param requestBodyType 请求体类型 {@link RequestBodyType}
     * @return post请求返回的数据
     * @throws InvalidRequestParamException 无效的请求参数、url异常
     * @throws ApiRequestFailedException    api请求失败异常
     */
    <D> String executePost(Head[] heads, String url, D data, RequestBodyType requestBodyType) throws InvalidRequestParamException, ApiRequestFailedException;


}
