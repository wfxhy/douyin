package com.toker.project.douyin.common.bean;

import lombok.Getter;

/**
 * 接口调用凭证
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/9 10:56
 * @modified mdmbct
 * @since 1.0
 */
@Getter
public class AccessToken extends BaseToken{

    private static final long serialVersionUID = -8541238287096212480L;


    public AccessToken(long takeEffect, long expireIn, String value) {
        super(takeEffect, expireIn, value);
    }
}
