package com.toker.project.douyin.basis.body.interaction;

import com.alibaba.fastjson.annotation.JSONField;
import com.toker.project.douyin.common.body.DyOpenApiRequestBody;

/**
 * 发送消息请求体
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/19 18:21
 * @modified mdmbct
 * @since 1.0
 */
public class ImMessageSendReqBody implements DyOpenApiRequestBody {

    private static final long serialVersionUID = 2769448839624944621L;

    /**
     * 内部使用
     */
    @JSONField(name = "client_msg_id")
    private String clientMsgId;

    /**
     * 消息体见schema{@linkplain com.toker.project.douyin.basis.api.InteractionManager#ENTERPRISE_IM_MESSAGE_SEND}<p>
     * 需进行json转义
     */
    private String content;

    /**
     * 消息内容格式:"text"(文本消息) "image"(图片消息) "video"(视频消息) "card"(卡片消息)
     */
    @JSONField(name = "message_type")
    private String messageType;

    /**
     * 客服id，传则走客服会话，否则为普通会话
     */
    @JSONField(name = "persona_id")
    private String personaId;

    /**
     * 消息的接收方openid
     */
    @JSONField(name = "to_user_id")
    private String toUserId;
}
