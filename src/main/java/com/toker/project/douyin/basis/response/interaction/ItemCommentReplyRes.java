package com.toker.project.douyin.basis.response.interaction;

import com.alibaba.fastjson.annotation.JSONField;
import com.toker.project.douyin.common.response.DefaultResponseData;
import com.toker.project.douyin.common.response.DyOpenApiResponse;
import com.toker.project.douyin.common.response.ResponseExtra;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 回复视频评论响应
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/19 17:02
 * @modified mdmbct
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCommentReplyRes implements DyOpenApiResponse {

    private static final long serialVersionUID = -2159600221255810950L;

    private ResponseExtra extra;

    private ItemCommentReplyResData data;

    private String message;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static  class ItemCommentReplyResData extends DefaultResponseData {

        private static final long serialVersionUID = -3584401751854804065L;

        @JSONField(name = "comment_id")
        private String commentId;
    }
}
