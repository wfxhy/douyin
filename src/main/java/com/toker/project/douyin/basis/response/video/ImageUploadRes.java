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
 * 图像上传返回
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/18 14:15
 * @modified mdmbct
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageUploadRes implements DyOpenApiResponse {

    private ResponseExtra extra;

    private ImageUploadResData data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class ImageUploadResData extends DefaultResponseData{

        private static final long serialVersionUID = 1973817186151536486L;

        private Image image;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Image {

        private int height;

        private int width;

        @JSONField(name = "image_id")
        private String imageId;
    }
}
