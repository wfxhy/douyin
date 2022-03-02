package com.toker.project.douyin.basis.body.video;

import com.toker.project.douyin.common.body.DyOpenApiRequestBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 上传视频请求体
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/8 13:56
 * @modified mdmbct
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoUploadReqBody implements DyOpenApiRequestBody {

    private byte[] video;
}
