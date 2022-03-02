package com.toker.project.douyin.basis.body.interaction;

import com.alibaba.fastjson.annotation.JSONField;
import com.toker.project.douyin.common.body.DyOpenApiRequestBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 回复视频评论请求体
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/19 17:00
 * @modified mdmbct
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCommentReplyReqBody implements DyOpenApiRequestBody {

    private static final long serialVersionUID = 5059628652848964014L;

    /**
     * 需要回复的评论id （如果需要回复的是视频不传此字段）
     */
    @JSONField(name = "comment_id")
    private String commentId;

    private String content;

    /**
     * 视频id
     */
    private String itemId;

}
