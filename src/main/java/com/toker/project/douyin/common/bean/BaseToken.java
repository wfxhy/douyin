package com.toker.project.douyin.common.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/9 10:57
 * @modified mdmbct
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public abstract class BaseToken implements Serializable {

    private static final long serialVersionUID = 4870826807079258047L;

    /**
     * 生效时间 存入的ms
     */
    private final long takeEffect;

    /**
     * token过期时间 ms
     */
    protected final long expireIn;

    /**
     * token值
     */
    protected final String value;


}
