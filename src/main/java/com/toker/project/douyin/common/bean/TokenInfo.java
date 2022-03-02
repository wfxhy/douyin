package com.toker.project.douyin.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Token信息 包含AccessToken和RefreshToken
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/9 17:41
 * @modified mdmbct
 * @since 1.0
 */
@Data
public class TokenInfo implements Serializable {

    private static final long serialVersionUID = 2288951028147293247L;

    private RefreshToken refreshToken;

    private AccessToken accessToken;
}
