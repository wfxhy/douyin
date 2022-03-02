package com.toker.project.douyin.basis.response.video;

import com.toker.project.douyin.common.response.DyOpenApiResponse;
import com.toker.project.douyin.common.response.ResponseExtra;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分片上传视频结束返回
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/8 13:27
 * @modified mdmbct
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoPartCompleteRes implements DyOpenApiResponse {

    private ResponseExtra extra;

    private VideoUploadRes.VideoUploadResponseData data;
}
