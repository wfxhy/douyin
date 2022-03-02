package com.toker.project.douyin.common.exception;

import com.toker.project.douyin.common.response.DefaultResponseData;

/**
 * api响应结果中的error_code{@link DefaultResponseData#errorCode}值不是0时抛出该 异常
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/10 17:05
 * @modified mdmbct
 * @since 1.0
 */
public class ApiResponseFailedException extends ApiCallException{

    public ApiResponseFailedException(String message) {
        super(message);
    }
}
