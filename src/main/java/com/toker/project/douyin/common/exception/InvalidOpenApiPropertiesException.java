package com.toker.project.douyin.common.exception;

/**
 * 无效的open api配置异常
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/9 14:38
 * @modified mdmbct
 * @since 1.0
 */
public class InvalidOpenApiPropertiesException extends RuntimeException {

    public InvalidOpenApiPropertiesException(String message) {
        super(message);
    }
}
