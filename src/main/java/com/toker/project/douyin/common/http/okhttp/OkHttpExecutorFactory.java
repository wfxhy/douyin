package com.toker.project.douyin.common.http.okhttp;



import com.toker.project.douyin.common.http.HttpExecutorFactory;
import com.toker.project.douyin.common.config.HttpConfig;
import com.toker.project.douyin.common.http.HttpExecutor;
import com.toker.project.douyin.common.http.HttpExecutorFactory;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/16 18:13
 * @modified mdmbct
 * @since 1.0
 */
public class OkHttpExecutorFactory implements HttpExecutorFactory {

    @Override
    public HttpExecutor createHttpExecutor(HttpConfig httpConfig) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new RetryInterceptor(httpConfig.getMaxRetry()))
                .callTimeout(httpConfig.getResponseTimeout(), TimeUnit.MILLISECONDS)
                .connectTimeout(httpConfig.getConnectTimeout(), TimeUnit.MILLISECONDS);
        return new OkHttpExecutor(builder.build());
    }
}
