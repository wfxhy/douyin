package com.toker.project.douyin.common.exception;

/**
 * 抖音open api请求失败
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/10 14:42
 * @modified mdmbct
 * @since 1.0
 */
public class ApiRequestFailedException extends ApiCallException{

    private static final String DEFAULT_MSG = "Open API请求失败";

    public ApiRequestFailedException() {
        super(DEFAULT_MSG);
    }

    public ApiRequestFailedException(String message) {
        super(message);
    }
}
