package com.toker.project.douyin.common.http;


import com.toker.project.douyin.common.config.HttpConfig;

/**
 * HttpExecutor工厂类
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/16 18:08
 * @modified mdmbct
 * @since 1.0
 */
public interface HttpExecutorFactory {

    /**
     * 创建HttpExecutor实例
     *
     * @param httpConfig@return
     */
    HttpExecutor createHttpExecutor(HttpConfig httpConfig);
}
