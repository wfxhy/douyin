package com.toker.project.douyin.basis.response.video;

import com.alibaba.fastjson.annotation.JSONField;
import com.toker.project.douyin.common.response.DefaultResponseData;
import com.toker.project.douyin.common.response.DyOpenApiResponse;
import com.toker.project.douyin.common.response.ResponseExtra;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 查询视频分享结果及数据返回
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/8 13:43
 * @modified mdmbct
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoShareDataRes implements DyOpenApiResponse {

    private ResponseExtra extra;

    private VideoShareDataResData data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class VideoShareDataResData extends DefaultResponseData {

        @JSONField(name = "share_id")
        private String shareId;
    }
}
