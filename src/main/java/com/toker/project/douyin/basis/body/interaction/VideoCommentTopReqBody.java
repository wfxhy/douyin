package com.toker.project.douyin.basis.body.interaction;

import com.alibaba.fastjson.annotation.JSONField;
import com.toker.project.douyin.common.body.DyOpenApiRequestBody;

/**
 * 置顶评论请求体
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/19 17:54
 * @modified mdmbct
 * @since 1.0
 */

public class VideoCommentTopReqBody implements DyOpenApiRequestBody {

    private static final long serialVersionUID = 6593480640587300335L;

    /**
     * true: 置顶, false: 取消置顶
     */
    private boolean top;

    /**
     * 需要置顶、取消置顶的评论id
     */
    @JSONField(name = "comment_id")
    private String commentId;

    @JSONField(name = "item_id")
    private String itemId;
}
