package com.toker.project.douyin.common.enums;

/**
 * 抖音open api响应的错误码
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/10 14:08
 * @modified mdmbct
 * @since 1.0
 */
public enum ErrorCode {

    /**
     * 响应成功
     */
    SUCCESS(0),

    /**
     * 用户未授权该api
     */
    API_UNAUTHORIZED(2190003),

    /**
     * 应用未获得该能力, 请去https://open.douyin.com/申请
     */
    NO_ABILITY(2190004),

    ;


    private final int value;

    ErrorCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
