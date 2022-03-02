package com.toker.project.api.service.impl;

import com.toker.common.constant.Constants;
import com.toker.common.exception.ServiceException;
import com.toker.common.utils.DateUtils;
import com.toker.framework.config.RuoYiConfig;
import com.toker.project.api.service.ApiService;
import com.toker.project.douyin.basis.body.video.AutoCreateVideoParam;
import com.toker.project.douyin.basis.response.user.UserOpenInfoRes;
import com.toker.project.douyin.basis.response.video.VideoCreateRes;
import com.toker.project.douyin.basis.response.video.VideoListRes;
import com.toker.project.douyin.basis.response.video.VideoPoiRes;
import com.toker.project.douyin.basis.service.interfaces.DyBasisService;
import com.toker.project.douyin.common.enums.ApiPlatform;
import com.toker.project.douyin.common.enums.ErrorCode;
import com.toker.project.douyin.common.response.auth.AccessTokenRes;
import com.toker.project.douyin.common.response.auth.ClientTokenRes;
import com.toker.project.douyin.domain.DouyinUser;
import com.toker.project.douyin.domain.DouyinUserVideo;
import com.toker.project.douyin.service.IDouyinUserService;
import com.toker.project.douyin.service.IDouyinUserVideoService;
import com.toker.project.merchants.domain.MerCouponActivity;
import com.toker.project.merchants.domain.MerResource;
import com.toker.project.merchants.domain.Merchants;
import com.toker.project.merchants.service.IMerResourceService;
import com.toker.project.merchants.service.IMerchantsService;
import com.toker.project.video.config.FFmpegConfig;
import com.toker.project.video.service.IFfmpegBgmService;
import com.toker.project.video.utils.VideoUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@EnableAsync
public class ApiServiceImpl implements ApiService {

    @Autowired
    private IMerResourceService merResourceService;

    @Resource
    private DyBasisService dyBasisService;
    @Resource
    private IDouyinUserVideoService douyinUserVideoService;
    @Resource
    private IDouyinUserService douyinUserService;

    @Resource
    private IMerchantsService merchantsService;
    @Resource
    private IFfmpegBgmService ffmpegBgmService;

    /**
     * 合并活动视频
     * @param merCouponActivity
     * @return
     */
    @Override
    public String mergeActivityVideo(MerCouponActivity merCouponActivity) {
        // 本地资源路径
        String localPath = RuoYiConfig.getProfile();
        //合成视频
        //按视频分组查找视频，每个分组随机取一个进行合成，把取到的视频记录下来，下次取的时候过滤掉
        String resourceGroupIds = merCouponActivity.getResourceGroupId();
        String[] groupIdAry = StringUtils.split(resourceGroupIds,",");

        List<String> videoPaths = new ArrayList<String>();
        if(groupIdAry!=null && groupIdAry.length>0) {
            for (int i = 0; i < groupIdAry.length; i++) {
                if(i>4){//最多合成5个分组的视频
                    break;
                }
                long resoureGroupId = Long.parseLong(groupIdAry[i]);
                MerResource merResource = this.merResourceService.getRandMerReource(merCouponActivity.getMerchantId(),resoureGroupId, merCouponActivity.getId());
                if(merResource==null){
                    continue;
                }
                videoPaths.add((localPath + StringUtils.substringAfter(merResource.getResourceUrl(), Constants.RESOURCE_PREFIX)).replaceAll("/", "\\\\"));

//                MerActivityVideo merActivityVideo = new MerActivityVideo();
//                merActivityVideo.setActivityId(merCouponActivity.getId());
//                merActivityVideo.setResourceId(merResource.getId());
//
//                this.merActivityVideoService.insertMerActivityVideo(merActivityVideo);
            }
            //合成视频
//            String mergePath = FfmpegUtils.mergeVideos(videoPaths);
            String audioUrl = ffmpegBgmService.getRandFfmpegBgm().getAudioUrl();
            String audioPath = null;
            if(StringUtils.isNotEmpty(audioUrl)){
                audioPath = localPath + StringUtils.substringAfter(audioUrl, Constants.RESOURCE_PREFIX).replaceAll("/", "\\\\");
            }

            String mergePath = FFmpegConfig.getTempAbsolutePath() + VideoUtil.nowTime()+".mp4" ;
            if(VideoUtil.mergeVideo(mergePath,videoPaths,true,audioPath)){
                return mergePath;
            }else{
                throw new ServiceException("视频合成异常");
            }

        }
        return null;
    }

    @Async
    @Override
    public void pushVideo(MerCouponActivity merCouponActivity, String openId) {
        String videoPath = mergeActivityVideo(merCouponActivity);

        AutoCreateVideoParam autoCreateVideoParam = new AutoCreateVideoParam();
        //查询活动关联的商户抖音信息
        Merchants merchants = this.merchantsService.selectMerchantsById(merCouponActivity.getMerchantId());
        //添加话题
        autoCreateVideoParam = setText(autoCreateVideoParam,merCouponActivity,merchants);
        //添加位置
        autoCreateVideoParam.setPoiId(merchants.getPoiId());
        autoCreateVideoParam.setPoiName(merchants.getPoiName());
        //添加小程序
        autoCreateVideoParam.setMicroAppId(merchants.getMicroAppId());
        autoCreateVideoParam.setMicroAppTitle(merchants.getMicroAppTitle());
        try {
            VideoCreateRes.VideoCreateResData videoCreateResData = dyBasisService.getVideoService().autoCreateVideo(openId,new File(videoPath),autoCreateVideoParam, ApiPlatform.DOU_YIN);
            //获取发布结果，如果发布成功，那么商户的余额减1
            if(videoCreateResData.getErrorCode()== ErrorCode.SUCCESS.getValue()){
                merchantsService.minusVideoCount(merCouponActivity.getMerchantId(),1);
                //保存用户视频发送记录
                DouyinUserVideo douyinUserVideo = new DouyinUserVideo() ;
                douyinUserVideo.setDouyinUserId(this.douyinUserService.selectDouyinUserByOpenId(openId).getId());
                douyinUserVideo.setCreateTime(DateUtils.getNowDate());
                douyinUserVideo.setItemId(videoCreateResData.getItemId());
                douyinUserVideo.setMerchantId(merCouponActivity.getMerchantId());
                douyinUserVideo.setActivityId(merCouponActivity.getId());
                douyinUserVideo.setVideoPath(videoPath);//保存发送的视频
                douyinUserVideo.setStatus(1L);//默认发布成功
                this.douyinUserVideoService.insertDouyinUserVideo(douyinUserVideo);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<VideoPoiRes.Poi> searchPOI(String address) {
        ClientTokenRes.ClientTokenResData clientTokenResData = dyBasisService.getAuth2Service().authClientToken(ApiPlatform.DOU_YIN);
        if(clientTokenResData.getErrorCode()== ErrorCode.SUCCESS.getValue()){
            String clientToken = clientTokenResData.getAccessToken();
            VideoPoiRes.VideoPoiResData res = dyBasisService.getVideoService().searchPOI(clientToken,address);
            if(res.getErrorCode()== ErrorCode.SUCCESS.getValue()){
                return res.getPois();
            }
        }

        return null;
    }

    /**
     * 查询已发布视频的相关数据：点赞数, 播放数等
     * @param openId
     * @param videoId
     * @return
     */
    @Override
    public VideoListRes.VideoListResData getVideoData(String openId, String videoId) {
        String[] videoIds = {videoId};
        VideoListRes.VideoListResData data = dyBasisService.getVideoService().getVideoData(openId,videoIds,ApiPlatform.DOU_YIN);
        return data;
    }

    /**
     * 添加话题
     * @param autoCreateVideoParam
     * @param merCouponActivity
     * @return
     */
    private AutoCreateVideoParam setText(AutoCreateVideoParam autoCreateVideoParam, MerCouponActivity merCouponActivity, Merchants merchants) {
        String text = merCouponActivity.getVideoTitle();
        if(StringUtils.isNotEmpty(merCouponActivity.getDouyinTitle())){//话题
            text = text + merCouponActivity.getDouyinTitle();
        }
        if(StringUtils.isNotEmpty(merchants.getOpenId())){//@他人
            String[] atNickNames = merchants.getNickname().split(",");
            String[]  atOpenIds = merchants.getOpenId().split(",");

            autoCreateVideoParam.setAtUsers(atOpenIds);
            for(int i = 0 ; i < atNickNames.length; i++){
                text = text + "@"+ atNickNames[i];
            }
        }
        autoCreateVideoParam.setText(text);
        return autoCreateVideoParam;
    }

    @Override
    public DouyinUser queryDouyinUserByCode(String code) {
        //查询抖音AccessToken
        AccessTokenRes.AccessTokenResData accessTokenResData = dyBasisService.getAuth2Service().authAccessToken(code, ApiPlatform.DOU_YIN);
        //查询抖音用户开放信息
        UserOpenInfoRes.GetUserOpenInfoResData userOpenInfoResData = dyBasisService.getUserManagerService().getUserOpenInfo(accessTokenResData.getOpenId(), ApiPlatform.DOU_YIN);

        DouyinUser douyinUser = new DouyinUser();
        douyinUser.setOpenId(userOpenInfoResData.getOpenId());
        douyinUser.setUnionId(userOpenInfoResData.getUnionId());
        douyinUser.setAvatar(userOpenInfoResData.getAvatar());
        douyinUser.setCity(userOpenInfoResData.getCity());
        douyinUser.setCountry(userOpenInfoResData.getCountry());
        douyinUser.setGender(userOpenInfoResData.getGender());
        douyinUser.setNickname(userOpenInfoResData.getNickName());
        douyinUser.setProvince(userOpenInfoResData.getProvince());

        return douyinUser;
    }

}
