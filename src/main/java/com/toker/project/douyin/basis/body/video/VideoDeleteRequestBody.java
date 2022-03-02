package com.toker.project.douyin.basis.body.video;

import com.alibaba.fastjson.annotation.JSONField;
import com.toker.project.douyin.common.body.DyOpenApiRequestBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 删除抖音视频的请求体参数
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/15 13:25
 * @modified mdmbct
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoDeleteRequestBody implements DyOpenApiRequestBody {

    private static final long serialVersionUID = 3249888780730067674L;

    @JSONField(name = "item_id")
    private String itemId;

}
