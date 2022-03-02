package com.toker.project.douyin.basis.response.user;

import com.alibaba.fastjson.annotation.JSONField;
import com.toker.project.douyin.common.response.DefaultResponseData;
import com.toker.project.douyin.common.response.DyOpenApiResponse;
import com.toker.project.douyin.common.response.ResponseExtra;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 获取用户公开信息返回
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/8 12:01
 * @modified mdmbct
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOpenInfoRes implements DyOpenApiResponse {

    private static final long serialVersionUID = -4854052327159265838L;

    private GetUserOpenInfoResData data;

    private ResponseExtra extra;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class GetUserOpenInfoResData extends DefaultResponseData {

        private static final long serialVersionUID = 8866797897304598710L;

        private String province;

        /**
         * 用户在当前开发者账号下的唯一标识（未绑定开发者账号没有该字段）
         */
        @JSONField(name = "union_id")
        private String unionId;

        private String avatar;

        /**
         * 类型: * `EAccountM` - 普通企业号 * `EAccountS` - 认证企业号 * `EAccountK` - 品牌企业号
         */
        @JSONField(name = "e_account_role")
        private String eAccountRole;

        /**
         * 用户在当前应用的唯一标识
         */
        @JSONField(name = "open_id")
        private String openId;

        @JSONField(name = "nickname")
        private String nickName;

        private String city;

        private String country;

        /**
         * 性别:
         * <p>
         * `0` - 未知
         * <p>
         * `1` - 男性
         * <p>
         * `2` - 女性
         */
        private int gender;

    }
}
