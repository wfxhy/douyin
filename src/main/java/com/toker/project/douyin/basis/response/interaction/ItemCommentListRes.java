package com.toker.project.douyin.basis.response.interaction;

import com.alibaba.fastjson.annotation.JSONField;
import com.toker.project.douyin.common.response.DyOpenApiResponse;
import com.toker.project.douyin.common.response.PageResponse;
import com.toker.project.douyin.common.response.ResponseExtra;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 视频评论列表返回
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/19 16:41
 * @modified mdmbct
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCommentListRes implements DyOpenApiResponse {

    private static final long serialVersionUID = -736238723715180000L;

    private ResponseExtra extra;

    private ItemCommentListResData data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class ItemCommentListResData extends PageResponse {

        private static final long serialVersionUID = 3756984655908680692L;

        private List<ItemCommentList> list;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemCommentList implements Serializable {

        private static final long serialVersionUID = 3755851216780083670L;

        @JSONField(name = "comment_id")
        private String commentId;

        @JSONField(name = "comment_user_id")
        private String commentUserId;

        private String content;

        @JSONField(name = "create_time")
        private long createTime;

        /**
         * 评论点赞数
         */
        @JSONField(name = "digg_count")
        private int diggCount;

        /**
         * 评论回复数量
         */
        @JSONField(name = "reply_comment_total")
        private int replyCommentTotal;

        /**
         * 评论是否被置顶
         */
        private boolean top;

    }
}
