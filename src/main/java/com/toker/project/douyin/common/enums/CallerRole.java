package com.toker.project.douyin.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * API调用方角色 普通用户 or 企业号
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/19 17:13
 * @modified mdmbct
 * @since 1.0
 */
@Getter
@RequiredArgsConstructor
public enum CallerRole {

    /**
     * 普通用户
     */
    ORDINARY("item"),

    /**
     * 企业号
     */
    ENTERPRISE("video");

    /**
     * 互动管理api路径开头
     * <a href="https://open.douyin.com/platform/doc/6848798514797938700"></a>
     */
    private final String interactionApiPrefix;

}
