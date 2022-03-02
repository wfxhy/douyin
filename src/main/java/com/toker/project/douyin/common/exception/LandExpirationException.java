package com.toker.project.douyin.common.exception;

/**
 * 登陆授权过期异常
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/10 10:36
 * @modified mdmbct
 * @since 1.0
 */
public class LandExpirationException extends ApiCallException {

    private static final long serialVersionUID = 4668072888028183868L;

    public static final String DEFAULT_MSG = "登陆已过期，请重新扫码登陆！";

    public LandExpirationException(String message) {
        super(message);
    }

    public LandExpirationException() {
        super(DEFAULT_MSG);
    }
}
