package com.toker.project.douyin.basis.body.video;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自动上传视频并创建 的参数
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/12 14:38
 * @modified mdmbct
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutoCreateVideoParam{

    private float coverTsp;

    /**
     * 小程序id	ttef9b112671b152ba	false
     */
    private String microAppId;

    /**
     * 地理位置id		false
     */
    private String poiId;

    /**
     * 地理位置名称		false
     */
    private String poiName;


    /**
     * 如果需要at其他用户。将text中@nickname对应的open_id放到这里。		false
     */
    private String[] atUsers;

    /**
     * 游戏个性化参数		false
     */
    private String gameContent;

    /**
     * 游戏id。暂不开放		false
     */
    private String gameId;

    /**
     * 小程序标题	小程序标题	false
     */
    private String microAppTitle;

    /**
     * 吊起小程序时的url参数	pages/index?param=123	false
     */
    private String microAppUrl;

    /**
     * 视频标题， 可以带话题,@用户。 如title1#话题1 #话题2 @openid1 注意： 1. 话题审核依旧遵循抖音的审核逻辑，强烈建议第三方谨慎拟定话题名称，避免强导流行为。
     */
    private String text;

}
