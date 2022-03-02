package com.toker.project.douyin.common.http;

import com.toker.project.douyin.common.enums.MultipartBodyType;
import com.toker.project.douyin.common.enums.MultipartBodyType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 请求头
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/18 17:29
 * @modified mdmbct
 * @since 1.0
 */
@Getter
@RequiredArgsConstructor
public class Head {

    private final String name;

    private final String value;

    public static Head APPLICATION_JSON = new Head("Content-Type", "application/json");

    public static Head MULTIPART_FORM_DATA = new Head("Content-Type", MultipartBodyType.FORM.getValue());

}
