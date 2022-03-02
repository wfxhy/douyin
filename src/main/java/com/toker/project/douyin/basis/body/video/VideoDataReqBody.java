package com.toker.project.douyin.basis.body.video;

import com.alibaba.fastjson.annotation.JSONField;
import com.toker.project.douyin.common.body.DyOpenApiRequestBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询指定视频数据
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/8 13:40
 * @modified mdmbct
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoDataReqBody implements DyOpenApiRequestBody {

    private static final long serialVersionUID = 1531932827864127347L;

    /**
     * item_id数组，仅能查询access_token对应用户上传的视频
     */
    @JSONField(name = "item_ids")
    private String[] itemIds;

}
