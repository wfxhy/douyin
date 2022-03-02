package com.toker.project.douyin.basis.response.interaction;

import com.toker.project.douyin.common.response.DyOpenApiResponse;
import com.toker.project.douyin.common.response.PageResponse;
import com.toker.project.douyin.common.response.ResponseExtra;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 评论回复列表返回
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/19 16:52
 * @modified mdmbct
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCommentReplyListRes implements DyOpenApiResponse {

    private static final long serialVersionUID = -3470349372777769572L;

    private ResponseExtra extra;

    private ItemCommentReplyListResData data;

    public static class ItemCommentReplyListResData extends PageResponse {

        private static final long serialVersionUID = 3492930244235129846L;

        private List<ItemCommentListRes.ItemCommentList> list;
    }


}
