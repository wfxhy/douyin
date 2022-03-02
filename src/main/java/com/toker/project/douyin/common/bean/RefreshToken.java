package com.toker.project.douyin.common.bean;

import lombok.Getter;

/**
 * 刷新AccessToken用到的Token
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/9 10:59
 * @modified mdmbct
 * @since 1.0
 */
@Getter
public class RefreshToken extends BaseToken {

    private static final long serialVersionUID = -1577061407072926831L;


    public RefreshToken(long takeEffect, long expireIn, String value) {
        super(takeEffect, expireIn, value);
    }
}
