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
 * 创建视频返回
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/8 13:29
 * @modified mdmbct
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoCreateRes implements DyOpenApiResponse {

    private ResponseExtra extra;

    private VideoCreateResData data;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class VideoCreateResData extends DefaultResponseData {

        /**
         * 抖音视频id	@8hxdhauTCMppanGnM4ltGM780mDqPP+KPpR0qQOmLVAXb/T060zdRmYqig357zEBq6CZRp4NVe6qLIJW/V/x1w==
         */
        @JSONField(name = "item_id")
        private String itemId;
    }
}
