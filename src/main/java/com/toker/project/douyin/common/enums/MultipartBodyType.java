package com.toker.project.douyin.common.enums;

/**
 * MultipartBodyType
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/11 11:14
 * @modified mdmbct
 * @since 1.0
 */
public enum MultipartBodyType {


    MIXED("multipart/mixed"),

    ALTERNATIVE("multipart/alternative"),

    DIGEST("multipart/digest"),

    PARALLEL("multipart/parallel"),

    FORM("multipart/form-data"),
    ;


    private final String value;

    MultipartBodyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
