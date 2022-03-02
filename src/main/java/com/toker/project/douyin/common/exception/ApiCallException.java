package com.toker.project.douyin.common.exception;

/**
 * api调用异常
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/10 17:42
 * @modified mdmbct
 * @since 1.0
 */
public class ApiCallException extends RuntimeException {

    public static final String DEFAULT_API_CALL_ERROR_MSG = "Open API调用失败！";

    private static final long serialVersionUID = 1898643123321310671L;

    public ApiCallException() {
        super(DEFAULT_API_CALL_ERROR_MSG);
    }

    public ApiCallException(String message) {
        super(message);
    }
}
