package com.toker.framework.task;

import com.toker.common.constant.Constants;
import com.toker.framework.config.RuoYiConfig;
import com.toker.project.douyin.basis.body.video.AutoCreateVideoParam;
import com.toker.project.douyin.basis.response.video.VideoCreateRes;
import com.toker.project.douyin.basis.response.video.VideoListRes;
import com.toker.project.douyin.basis.service.interfaces.DyBasisService;
import com.toker.project.douyin.common.enums.ApiPlatform;
import com.toker.project.douyin.common.enums.ErrorCode;
import com.toker.project.douyin.domain.DouyinUser;
import com.toker.project.douyin.service.IDouyinUserService;
import com.toker.project.merchants.domain.MerCouponActivity;
import com.toker.project.merchants.domain.MerResource;
import com.toker.project.merchants.domain.Merchants;
import com.toker.project.merchants.service.IMerCouponActivityService;
import com.toker.project.merchants.service.IMerResourceService;
import com.toker.project.merchants.service.IMerchantsService;
import com.toker.project.task.domain.PublishTask;
import com.toker.project.task.domain.TaskDyPublish;
import com.toker.project.task.domain.TaskVideoMerge;
import com.toker.project.task.service.IPublishTaskService;
import com.toker.project.task.service.ITaskDyPublishService;
import com.toker.project.task.service.ITaskVideoMergeService;
import com.toker.project.video.config.FFmpegConfig;
import com.toker.project.video.utils.VideoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.toker.common.utils.StringUtils;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 定时任务调度测试
 * 
 * @author ruoyi
 */
@Component("ryTask")
public class RyTask
{
    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams()
    {
        System.out.println("执行无参方法");
    }

    private AtomicInteger count = new AtomicInteger();

    @Autowired
    private IPublishTaskService publishTaskService;
    @Autowired
    private IMerResourceService merResourceService;
    @Autowired
    private IMerCouponActivityService merCouponActivityService;
    @Resource
    private DyBasisService dyBasisService;
    @Resource
    private IDouyinUserService douyinUserService;

    @Resource
    private ITaskVideoMergeService taskVideoMergeService;
    @Resource
    private ITaskDyPublishService taskDyPublishService;
    @Resource
    private IMerchantsService merchantsService;

    /**
     * 抖音视频推送任务
     */
    public void publishDyVideoTask() {

        try {
            boolean canGo = count.compareAndSet(0, 1);
            if (!canGo) {
                return;
            }

            //查询待发布的视频任务
            PublishTask publishTask = new PublishTask();
            publishTask.setStatus(0);//任务执行状态：0待执行，1执行成功 2执行失败
            List<PublishTask> publishTaskList = this.publishTaskService.selectPublishTaskList(publishTask);

            //合成视频并发送抖音
            if (publishTaskList != null && publishTaskList.size() > 0) {
                for (int i = 0; i < publishTaskList.size(); i++) {
                    PublishTask task = publishTaskList.get(i);
                    //修改任务为执行中
                    task.setStatus(1);
                    this.publishTaskService.updatePublishTask(task);

                    MerCouponActivity merCouponActivity = this.merCouponActivityService.selectMerCouponActivityById(task.getActivityId());

                    DouyinUser douyinUser = this.douyinUserService.selectDouyinUserById(task.getUserId());
                    String openId = douyinUser.getOpenId();
                    try {
                        String videoPath = mergeActivityVideo(merCouponActivity, task.getId(), merCouponActivity.getMerchantId());
                        if (StringUtils.isNotEmpty(videoPath)) {
                            AutoCreateVideoParam autoCreateVideoParam = new AutoCreateVideoParam();
                            //查询活动关联的商户抖音信息
                            Merchants merchants = this.merchantsService.selectMerchantsById(merCouponActivity.getMerchantId());
                            //添加话题
                            autoCreateVideoParam = setText(autoCreateVideoParam, merCouponActivity, merchants);
                            //添加位置
                            autoCreateVideoParam.setPoiId(merchants.getPoiId());
                            autoCreateVideoParam.setPoiName(merchants.getPoiName());
                            //添加小程序
                            autoCreateVideoParam.setMicroAppId(merchants.getMicroAppId());
                            autoCreateVideoParam.setMicroAppTitle(merchants.getMicroAppTitle());

                            VideoCreateRes.VideoCreateResData videoCreateResData = dyBasisService.getVideoService().autoCreateVideo(openId, new File(videoPath), autoCreateVideoParam, ApiPlatform.DOU_YIN);
                            System.out.println("videoCreateResData==="+videoCreateResData.toString());
                            //videoCreateResData.getItemId()
                            //保存抖音推送记录
                            TaskDyPublish taskDyPublish = new TaskDyPublish();
                            taskDyPublish.setActivityId(merCouponActivity.getId());
                            taskDyPublish.setTaskId(task.getId());
                            taskDyPublish.setOpenId(openId);
                            //获取发布结果，如果发布成功，那么商户的余额减1
                            if (videoCreateResData.getErrorCode() == ErrorCode.SUCCESS.getValue()) {
                                merchantsService.minusVideoCount(merCouponActivity.getMerchantId(), 1);

                                taskDyPublish.setItemId(videoCreateResData.getItemId());
                                taskDyPublish.setStatus(1L);
                            } else {
                                taskDyPublish.setStatus(2L);
                            }
                            this.taskDyPublishService.insertTaskDyPublish(taskDyPublish);

                            //修改任务状态
                            task.setStatus(2);
                            this.publishTaskService.updatePublishTask(task);
                        } else {
                            //修改任务状态
                            task.setStatus(3);//执行失败
                            this.publishTaskService.updatePublishTask(task);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        //修改任务状态
                        task.setStatus(3);
                        this.publishTaskService.updatePublishTask(task);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            count.decrementAndGet();
        }
    }

    /**
     * 查询已发布的抖音视频的点赞评论播放数据
     */
    public void dyVideoDataTask() {
        try{
            TaskDyPublish taskDyPublish = new TaskDyPublish();
            taskDyPublish.setStatus(1L);//发布成功的任务
            List<TaskDyPublish> list = taskDyPublishService.selectTaskDyPublishList(taskDyPublish);

            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    TaskDyPublish dyPublish = list.get(i);

                    String itemId = dyPublish.getItemId();
                    String openId = dyPublish.getOpenId();

                    String[] videoIds = {itemId};
                    VideoListRes.VideoListResData data = dyBasisService.getVideoService().getVideoData(openId, videoIds, ApiPlatform.DOU_YIN);
                    if(data!=null){
                        List<VideoListRes.VideoDetail> details = data.getList();
                        if (details != null && details.size() > 0) {
                            VideoListRes.VideoStatistics statistics = details.get(0).getStatistics();
                            if (statistics != null) {
                                int diggCount = statistics.getDiggCount();
                                dyPublish.setDiggCount(diggCount!=0?diggCount:0);

                                int downloadCount = statistics.getDownloadCount();
                                dyPublish.setDownloadCount(downloadCount!=0?downloadCount:0);

                                int forwardCount = statistics.getForwardCount();
                                dyPublish.setForwardCount(forwardCount!=0?forwardCount:0);

                                int playCount = statistics.getPlayCount();
                                dyPublish.setPlayCount(playCount!=0?playCount:0);

                                int shareCount = statistics.getShareCount();
                                dyPublish.setShareCount(shareCount!=0?shareCount:0);

                                int commentCount = statistics.getCommentCount();
                                dyPublish.setCommentCount(commentCount!=0?commentCount:0);

                                this.taskDyPublishService.updateTaskDyPublish(dyPublish);
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 根据多个分组合成待发布的视频
     *
     * @param merCouponActivity
     * @return
     */
    private String mergeActivityVideo(MerCouponActivity merCouponActivity, long taskId, long merchantId) {
        //添加合成记录
        TaskVideoMerge merge = new TaskVideoMerge();
        merge.setTaskId(taskId);
        merge.setMerchantId(merchantId);
        try {
            // 本地资源路径
            String localPath = RuoYiConfig.getProfile();
            //获取生成的可用的视频片段字符串
            String resoureIds = this.taskVideoMergeService.getMergeResourceIds(merCouponActivity.getId());
            //resoureIds = "399,400,401";
            merge.setResourceIds(resoureIds);
            //merge.setResourceIds("399,400,401");

            if (StringUtils.isNotEmpty(resoureIds)) {
                String[] resouceIdAry = resoureIds.split(",");

                List<String> videoPaths = new ArrayList<String>();
                for (String resourceId : resouceIdAry) {
                    MerResource merResource = this.merResourceService.selectMerResourceById(Long.parseLong(resourceId));
                    videoPaths.add((localPath + StringUtils.substringAfter(merResource.getResourceUrl(), Constants.RESOURCE_PREFIX)).replaceAll("/", "\\\\"));
                }
                //String audioUrl = ffmpegBgmService.getRandFfmpegBgm().getAudioUrl();


                MerResource audioResource = merResourceService.getRandomAudio(merchantId);
                String audioUrl = audioResource!=null?audioResource.getResourceUrl():"";
                String audioPath = null;
                if (StringUtils.isNotEmpty(audioUrl)) {
                    audioPath = localPath + StringUtils.substringAfter(audioUrl, Constants.RESOURCE_PREFIX).replaceAll("/", "\\\\");
                }
                //生成合并完的视频路径
                String mergePath = FFmpegConfig.getTempAbsolutePath() + VideoUtil.nowTime() + ".mp4";
                //合并视频

                if (VideoUtil.mergeVideo(mergePath, videoPaths, true, audioPath)) {
                    merge.setStatus(0);//合成成功
                    this.taskVideoMergeService.insertTaskVideoMerge(merge);
                    return mergePath;
                } else {
                    merge.setStatus(1);//合成失败
                    this.taskVideoMergeService.insertTaskVideoMerge(merge);
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            merge.setStatus(1);//合成失败
            merge.setErrorMsg(e.getMessage());
            this.taskVideoMergeService.insertTaskVideoMerge(merge);
        }
        return null;
    }

    /**
     * 添加话题
     *
     * @param autoCreateVideoParam
     * @param merCouponActivity
     * @return
     */
    private AutoCreateVideoParam setText(AutoCreateVideoParam autoCreateVideoParam, MerCouponActivity merCouponActivity, Merchants merchants) {
        String text = merCouponActivity.getVideoTitle();
        if (StringUtils.isNotEmpty(merCouponActivity.getDouyinTitle())) {//话题
            text = text + merCouponActivity.getDouyinTitle();
        }
        if (StringUtils.isNotEmpty(merchants.getOpenId())) {//@他人
            String[] atNickNames = merchants.getNickname().split(",");
            String[] atOpenIds = merchants.getOpenId().split(",");

            autoCreateVideoParam.setAtUsers(atOpenIds);
            for (int i = 0; i < atNickNames.length; i++) {
                text = text + "@" + atNickNames[i];
            }
        }
        autoCreateVideoParam.setText(text);
        return autoCreateVideoParam;
    }
}
