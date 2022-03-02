package com.toker.project.douyin.basis.body.video;

import com.alibaba.fastjson.annotation.JSONField;
import com.toker.project.douyin.common.body.DyOpenApiRequestBody;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建视频请求体
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/8 14:35
 * @modified mdmbct
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoCreateReqBody implements DyOpenApiRequestBody {

    private static final long serialVersionUID = 4949635353245996619L;

    /**
     * 将传入的指定时间点对应帧设置为视频封面（单位：秒）	2.3	false
     */
    @JSONField(name = "cover_tsp")
    private float coverTsp;

    /**
     * 小程序id	ttef9b112671b152ba	false
     */
    @JSONField(name = "micro_app_id")
    private String microAppId;

    /**
     * 地理位置id		false
     */
    @JSONField(name = "poi_id")
    private String poiId;

    /**
     * 地理位置名称		false
     */
    @JSONField(name = "poi_name")
    private String poiName;


    /**
     *  通过/video/upload/接口得到。注意每次调用/video/create/都要调用/video/upload/生成新的video_id。	v0201f510000smhdsr0ggl1v4a2b2ps1	true
     */
    @JSONField(name = "video_id")
    private String videoId;

    /**
     * 如果需要at其他用户。将text中@nickname对应的open_id放到这里。		false
     */
    @JSONField(name = "at_users")
    private String[] atUsers;

    /**
     * 游戏个性化参数		false
     */
    @JSONField(name = "game_content")
    private String gameContent;

    /**
     * 游戏id。暂不开放		false
     */
    @JSONField(name = "game_id")
    private String gameId;

    /**
     * 小程序标题	小程序标题	false
     */
    @JSONField(name = "micro_app_title")
    private String microAppTitle;

    /**
     * 吊起小程序时的url参数	pages/index?param=123	false
     */
    @JSONField(name = "micro_app_url")
    private String microAppUrl;

    /**
     * 视频标题， 可以带话题,@用户。 如title1#话题1 #话题2 @openid1 注意： 1. 话题审核依旧遵循抖音的审核逻辑，强烈建议第三方谨慎拟定话题名称，避免强导流行为。
     */
    private String text;


    public static VideoCreateReqBody build(AutoCreateVideoParam param) {
        return VideoCreateReqBody.builder()
                .coverTsp(param.getCoverTsp())
                .microAppId(param.getMicroAppId())
                .poiId(param.getPoiId())
                .poiName(param.getPoiName())
                .atUsers(param.getAtUsers())
                .text(param.getText())
                .gameContent(param.getGameContent())
                .gameId(param.getGameId())
                .microAppTitle(param.getMicroAppTitle())
                .microAppUrl(param.getMicroAppUrl())
                .build();
    }
}
