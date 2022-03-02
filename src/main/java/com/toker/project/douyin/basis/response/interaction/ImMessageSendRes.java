package com.toker.project.douyin.basis.response.interaction;

import com.alibaba.fastjson.annotation.JSONField;
import com.toker.project.douyin.common.response.DefaultResponseData;
import com.toker.project.douyin.common.response.DyOpenApiResponse;
import com.toker.project.douyin.common.response.ResponseExtra;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 发送信息返回
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/19 18:34
 * @modified mdmbct
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImMessageSendRes implements DyOpenApiResponse {

    private static final long serialVersionUID = -1041157678528225108L;

    private ResponseExtra extra;

    private ImMessageSendResData data;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class ImMessageSendResData extends DefaultResponseData {

        private static final long serialVersionUID = 8335877486223020543L;

        /**
         * 内部使用
         */
        @JSONField(name = "server_msg_id")
        private String serverMsgId;
    }

}
