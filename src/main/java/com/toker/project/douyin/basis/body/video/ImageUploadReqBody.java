package com.toker.project.douyin.basis.body.video;

import com.toker.project.douyin.common.body.DyOpenApiRequestBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 上传图片请求体
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/18 14:12
 * @modified mdmbct
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageUploadReqBody implements DyOpenApiRequestBody {

    private byte[] image;
}
