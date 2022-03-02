package com.toker.project.douyin.basis.api;

import com.toker.project.douyin.basis.response.user.*;
import com.toker.project.douyin.common.body.*;
import com.toker.project.douyin.common.enums.DyOpenApi;
import com.toker.project.douyin.common.enums.RequestMethod;
import com.toker.project.douyin.common.response.DyOpenApiResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 用户管理open api
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/8 11:58
 * @modified mdmbct
 * @since 1.0
 */
@Getter
@RequiredArgsConstructor
public enum UserManager implements DyOpenApi {

    /**
     * <a href="https://open.douyin.com/platform/doc/6848806527751489550">获取用户信息</a>
     * <p>
     * Scope: `user_info`需要用户授权
     * <p>
     * 该接口获取用户的抖音公开信息，包含昵称、头像、性别和地区；适用于抖音。
     * <p>
     * 注意：
     * <p>
     * 抖音的OAuth API以https://open.douyin.com/开头。
     * <p>
     * 头条的OAuth API以https://open.snssdk.com/开头。
     * <p>
     * 目前只支持返回用户头像、昵称及open_id。
     * <p>
     * 西瓜的OAuth API以https://open-api.ixigua.com/开头。
     * <p>
     * 目前只支持返回用户头像、昵称及open_id。
     * <p>
     * 获取用户手机号：
     * <p>
     * 需要用户授权mobile_alert 权限
     * <p>
     * 用户授权后该接口会返回encrypt_mobile
     * <p>
     * 解密手机号，使用Aes算法解密，秘钥是clientSecret， 向量lv 是clientSecret前16字节
     */
    OAUTH_USER_OPEN_INFO(RequestMethod.GET,
            VoidBody.class,
            UserOpenInfoRes.class,
            "/oauth/userinfo?open_id=%s&"),

    /**
     * <a href="https://open.douyin.com/platform/doc/6848806544893675533">粉丝列表</a>
     * <p>
     * Scope: `fans.list`需要用户授权
     * <p>
     * 获取用户最近的粉丝列表，不保证顺序；目前可查询的粉丝数上限5千。该接口适用于抖音。
     */
    FANS_LIST(RequestMethod.GET,
            VoidBody.class,
            FansListRes.class,
            "/fans/list?open_id=%s&cursor=%d&count=%d&"),


    /**
     * <a href="https://open.douyin.com/platform/doc/6848806523481737229">获取关注列表</a>
     * <p>
     * Scope: `following.list`需要用户授权
     * <p>
     * 获取用户的关注列表；该接口适用于抖音。
     */
    FOLLOWING_LIST(RequestMethod.GET,
            VoidBody.class,
            FollowingListRes.class,
            "/following/list?open_id=%s&cursor=%d&count=%d&"),

    /**
     * <a href="https://open.douyin.com/platform/doc/6940930610949048334">粉丝判断</a> <p>
     * Scope: `fans.check`需要用户授权<p>
     * 开发者应用下授权的抖音账号可根据用户的openid识别其是否关注其账号，并返回关注与否结果；（follower_open_id是否关注了open_id）<p>
     * 如关注：返回是，并返回关注时间<p>
     * 未关注：返回否<p>
     */
    FANS_CHECK(RequestMethod.GET,
            VoidBody.class,
            FansCheckRes.class,
            " /fans/check?open_id=%s&follower_open_id=%s&"
    ),

    ;

    private final RequestMethod requestMethod;

    private final Class<? extends DyOpenApiRequestBody> requestBodyClass;

    private final Class<? extends DyOpenApiResponse> responseClass;

    private final String path;

}
