package com.toker.project.douyin.common.http;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * 请求参数封装 代替HashMap节约内存
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/11 13:22
 * @modified mdmbct
 * @since 1.0
 */
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class RequestParameter<T> implements Serializable {

    private static final long serialVersionUID = 6197858292934636016L;

    /**
     * 参数名
     */
    private final String name;

    /**
     * 参数值
     */
    private final T value;



}

