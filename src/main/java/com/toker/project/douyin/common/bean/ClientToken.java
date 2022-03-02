package com.toker.project.douyin.common.bean;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * ClientToken
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/19 14:23
 * @modified mdmbct
 * @since 1.0
 */
@Getter
@RequiredArgsConstructor
public class ClientToken implements Serializable {

    /**
     * 过期时间 ms
     */
    private final long expireIn;

    private final String value;
}
