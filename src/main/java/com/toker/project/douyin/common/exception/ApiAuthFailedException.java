package com.toker.project.douyin.common.exception;

/**
 * 认证授权抖音失败
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/10 14:09
 * @modified mdmbct
 * @since 1.0
 */
public class ApiAuthFailedException extends ApiCallException {

    private static final long serialVersionUID = -7602491789229675977L;

    public ApiAuthFailedException(String message) {
        super(message);
    }
}
