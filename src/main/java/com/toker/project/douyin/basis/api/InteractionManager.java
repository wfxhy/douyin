package com.toker.project.douyin.basis.api;


import com.toker.project.douyin.basis.body.interaction.ImMessageSendReqBody;
import com.toker.project.douyin.basis.body.interaction.ItemCommentReplyReqBody;
import com.toker.project.douyin.basis.body.interaction.VideoCommentTopReqBody;
import com.toker.project.douyin.basis.response.interaction.ImMessageSendRes;
import com.toker.project.douyin.basis.response.interaction.ItemCommentListRes;
import com.toker.project.douyin.basis.response.interaction.ItemCommentReplyListRes;
import com.toker.project.douyin.basis.response.interaction.ItemCommentReplyRes;
import com.toker.project.douyin.common.body.DyOpenApiRequestBody;
import com.toker.project.douyin.common.body.VoidBody;
import com.toker.project.douyin.common.enums.ApiPlatform;
import com.toker.project.douyin.common.enums.CallerRole;
import com.toker.project.douyin.common.enums.DyOpenApi;
import com.toker.project.douyin.common.enums.RequestMethod;
import com.toker.project.douyin.common.response.DefaultResponse;
import com.toker.project.douyin.common.response.DyOpenApiResponse;
import com.toker.project.douyin.common.storage.DyStorageManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 互动管理
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/18 16:21
 * @modified mdmbct
 * @since 1.0
 */
@Getter
@RequiredArgsConstructor
public enum InteractionManager implements DyOpenApi {


    /**
     * <a href="https://open.douyin.com/platform/doc/6848798514797938700">评论列表</a>
     * <p>
     * Scope: <p>
     * `item.comment` 普通用户 <p>
     * `video.comment` 企业号 <p>
     * 需要申请权限需要用户授权<p>
     * 该接口用于获取评论列表。<p>
     * <p>
     * 注意：
     * <p>
     * 抖音的OAuth API以https://open.douyin.com/开头。<p>
     * 注意参数中item_id作为url参数时，必须encode，只对item_id单独进行encode
     */
    ITEM_COMMENT_LIST(RequestMethod.GET,
            VoidBody.class,
            ItemCommentListRes.class,
            "/item/comment/list?open_id=%s&cursor=%d&count=%d&item_id=%s&sort_type=%s&"
    ),

    /**
     * <a href="https://open.douyin.com/platform/doc/6848806819897411591">评论回复列表</a>
     * <p>
     * Scope: <p>
     * `item.comment` 普通用户 <p>
     * `video.comment` 企业号 <p>
     * 需要申请权限需要用户授权<p>
     * 该接口用于获取评论回复列表。<p>
     * <p>
     * 注意：
     * <p>
     * 抖音的OAuth API以https://open.douyin.com/开头。<p>
     * 注意参数中item_id作为url参数时，必须encode，只对item_id单独进行encode<p>
     * 注意参数中comment_id作为url参数时，必须encode，只对comment_id单独进行encode<p>
     */
    ITEM_COMMENT_REPLY_LIST(RequestMethod.GET,
            VoidBody.class,
            ItemCommentReplyListRes.class,
            "/item/comment/reply/list??open_id=%s&cursor=%d&count=%d&item_id=%s&comment_id=%s&sort_type=%s&"),


    /**
     * <a href="https://open.douyin.com/platform/doc/6848798514797971468">回复视频评论</a>
     * <p>
     * Scope: <p>
     * `item.comment` 普通用户 <p>
     * `video.comment` 企业号 <p>
     * 需要申请权限需要用户授权<p>
     * 该接口用于回复视频评论。
     */
    ITEM_COMMENT_REPLY(RequestMethod.POST,
            ItemCommentReplyReqBody.class,
            ItemCommentReplyRes.class,
            "/item/comment/reply?open_id=%s&"
    ),


    /**
     * <a href="https://open.douyin.com/platform/doc/6848806544931391501">置顶、取消置顶视频评论(企业号)</a>
     * <p>
     * Scope: `video.comment`需要申请权限需要用户授权<p>
     * 该接口用于置顶视频评论。<p>
     * <p>
     * 注意：
     * <p>
     * 抖音的OAuth API以https://open.douyin.com/开头。<p>
     * 调用本接口，需要授权的抖音用户是企业号企业号 。
     */
    VIDEO_COMMENT_TOP(RequestMethod.POST,
            VideoCommentTopReqBody.class,
            DefaultResponse.class,
            "/video/comment/top?open_id=%s&"
    ),


    /**
     * <a href="https://open.douyin.com/platform/doc/6848798087226329100">发送消息</a>
     * <p>
     * Scope: `enterprise.im`需要申请权限需要用户授权<p>
     * 该接口用于主动发送私信至抖音用户，支持文本、图片（需上传至素材库）、视频消息和企业消息卡片。<p>
     * 可结合客服管理接口，提升客服场景体验<p>
     * <p>
     * 注意：使用该能力，需要授权的抖音用户是企业号
     * <p>
     * 具体私信消息类型见下方Schema：<p>
     * text
     * {
     * text: string  //文字内容
     * }
     * <p>
     * image
     * {
     * media_id: string //素材id
     * }
     * <p>
     * video
     * {
     * item_id: string //视频id
     * }
     * <p>
     * card
     * {
     * card_id: string //卡片id
     * }
     * <p>
     * fancy_card
     * {
     * card_id: string //动态卡片id（内测中）
     * }
     */
    ENTERPRISE_IM_MESSAGE_SEND(RequestMethod.POST,
            ImMessageSendReqBody.class,
            ImMessageSendRes.class,
            "/enterprise/im/message/send?open_id=%s&"
    ),


    ;


    private final RequestMethod requestMethod;

    private final Class<? extends DyOpenApiRequestBody> requestBodyClass;

    private final Class<? extends DyOpenApiResponse> responseClass;

    private final String path;


    public String getUrl(DyStorageManager storageManager, CallerRole role) {
        String url = getUrl(storageManager, ApiPlatform.DOU_YIN);
        if (role != CallerRole.ORDINARY) {
            return url.replaceFirst(CallerRole.ORDINARY.getInteractionApiPrefix(), role.getInteractionApiPrefix());
        }
        return url;
    }
}
