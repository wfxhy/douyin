package com.toker.project.douyin.common.http.okhttp;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * okhttp重试拦截器 <p>
 * 重试次数小于等于0 被设置为默认3次重试 <p>
 * 大于10 被设置成10次
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/19 15:06
 * @modified mdmbct
 * @since 1.0
 */
@Slf4j
public class RetryInterceptor implements Interceptor {

    /**
     * 最大重试次数<p>
     * 设置为3次重试的话，则最大可能请求4次（默认1次+3次重试）
     */
    private final int maxRetry;

    private int curRetryCount = 0;

    public RetryInterceptor(int maxRetry) {
        if (maxRetry <= 0) {
            this.maxRetry = 3;
        } else {
            this.maxRetry = Math.min(maxRetry, 10);
        }
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        while (!response.isSuccessful() && curRetryCount < maxRetry) {
            curRetryCount++;
            log.info("当前重试次数：" + curRetryCount);
            chain.proceed(request);
        }
        return response;
    }
}
