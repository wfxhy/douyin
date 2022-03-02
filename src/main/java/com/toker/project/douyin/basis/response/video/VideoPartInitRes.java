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
 * 分片上传视频返回
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/8 13:17
 * @modified mdmbct
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoPartInitRes implements DyOpenApiResponse {

    private ResponseExtra extra;

    private VideoPartInitResData data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class VideoPartInitResData extends DefaultResponseData {

        /**
         * 上传id
         * <p>
         * 比如 上传id
         */
        @JSONField(name = "upload_id")
        private String uploadId;
    }
}
