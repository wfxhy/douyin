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
 * 上传视频返回
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/8 13:06
 * @modified mdmbct
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoUploadRes implements DyOpenApiResponse {

    private ResponseExtra extra;

    private VideoUploadResponseData data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class VideoUploadResponseData extends DefaultResponseData {

        private VideoUploadResponseVideo video;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VideoUploadResponseVideo {

        /**
         * 视频分辨率高
         */
        private int height;

        private int width;

        /**
         * 视频文件id
         */
        @JSONField(name = "video_id")
        private String videoId;
    }
}
