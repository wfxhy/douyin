package com.toker.project.douyin.common.enums;

/**
 * 用户数据接口 data_type字段枚举
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/10 17:55
 * @modified mdmbct
 * @since 1.0
 */
public enum ExternalDataApiDataType {

    /**
     * 7天
     */
    SEVEN(7),

    /**
     * 15天
     */
    FIFTEEN(15),

    /**
     * 30天
     */
    THIRTY(30),

    ;

    private final int days;

    ExternalDataApiDataType(int days) {
        this.days = days;
    }

    public int getDays() {
        return days;
    }
}
