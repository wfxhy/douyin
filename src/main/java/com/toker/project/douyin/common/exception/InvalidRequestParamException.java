package com.toker.project.douyin.common.exception;

/**
 * 无效的请求参数异常
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/10 14:44
 * @modified mdmbct
 * @since 1.0
 */
public class InvalidRequestParamException extends RuntimeException{

    private static final String DEFAULT_MSG = "无效的请求参数";

    public InvalidRequestParamException() {
        super(DEFAULT_MSG);
    }

    public InvalidRequestParamException(String message) {
        super(message);
    }
}
