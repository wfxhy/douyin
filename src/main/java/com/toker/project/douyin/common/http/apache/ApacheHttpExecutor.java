package com.toker.project.douyin.common.http.apache;

import com.toker.project.douyin.common.enums.HttpClientType;
import com.toker.project.douyin.common.enums.RequestBodyType;
import com.toker.project.douyin.common.exception.ApiRequestFailedException;
import com.toker.project.douyin.common.exception.InvalidRequestParamException;
import com.toker.project.douyin.common.http.Head;
import com.toker.project.douyin.common.http.HttpExecutor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/10 11:27
 * @modified mdmbct
 * @since 1.0
 */
@Slf4j
public class ApacheHttpExecutor implements HttpExecutor {


    @Override
    public HttpClientType getHttpType() {
        return HttpClientType.APACHE_HTTP;
    }


    @Override
    public <D> String executePost(Head[] heads, String url, D data, RequestBodyType requestBodyType) throws InvalidRequestParamException, ApiRequestFailedException {

        return null;
    }

    @Override
    public String executeGet(Head[] heads, String url) throws InvalidRequestParamException, ApiRequestFailedException {
        return null;
    }
}
