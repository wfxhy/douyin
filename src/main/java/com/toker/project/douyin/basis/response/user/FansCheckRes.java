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
 * 粉丝检查返回
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/18 22:08
 * @modified mdmbct
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FansCheckRes implements DyOpenApiResponse {

    private static final long serialVersionUID = -5413733943754950592L;

    private ResponseExtra extra;

    private FansCheckResData data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class FansCheckResData extends DefaultResponseData {

        private static final long serialVersionUID = -4127791847854953739L;

        @JSONField(name = "is_follower")
        private boolean isFollower;

        /**
         * 若关注了，则返回关注时间。单位为秒级时间戳
         */
        @JSONField(name = "follow_time")
        private long followTime;
    }
}
