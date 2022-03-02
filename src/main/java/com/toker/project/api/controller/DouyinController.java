package com.toker.project.api.controller;


import com.toker.common.constant.Constants;
import com.toker.common.utils.DateUtils;
import com.toker.framework.web.controller.BaseController;
import com.toker.framework.web.domain.AjaxResult;
import com.toker.framework.web.page.TableDataInfo;
import com.toker.project.api.service.ApiService;
import com.toker.project.douyin.basis.response.video.VideoListRes;
import com.toker.project.douyin.basis.response.video.VideoPoiRes;
import com.toker.project.douyin.domain.DouyinUser;
import com.toker.project.douyin.service.IDouyinUserService;
import com.toker.project.merchants.domain.MerCoupon;
import com.toker.project.merchants.domain.MerCouponActivity;
import com.toker.project.merchants.domain.MerCouponGet;
import com.toker.project.merchants.domain.Merchants;
import com.toker.project.merchants.service.IMerCouponActivityService;
import com.toker.project.merchants.service.IMerCouponGetService;
import com.toker.project.merchants.service.IMerchantsService;
import com.toker.project.task.domain.PublishTask;
import com.toker.project.task.domain.TaskDyPublish;
import com.toker.project.task.service.IPublishTaskService;
import com.toker.project.task.service.ITaskDyPublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/douyin")
public class DouyinController extends BaseController {

    @Autowired
    private IDouyinUserService douyinUserService;

    @Autowired
    private IMerCouponActivityService merCouponActivityService;

    @Autowired
    private IMerCouponGetService merCouponGetService;

    @Autowired
    private IMerchantsService merchantsService;

    @Autowired
    private ITaskDyPublishService taskDyPublishService;

    @Autowired
    private IPublishTaskService publishTaskService;
    @Autowired
    private ApiService apiService;


    /**
     * 根据code获取抖音用户信息
     *
     * @param code
     * @return
     */
    @GetMapping("/getDouyinUserByCode")
    @ResponseBody
    public AjaxResult getDouyinUserByCode(String code) {
        System.out.println("--------------------------getDouyinUserByCode------------------------------" + code);
        //根据code查询添加顾客
        DouyinUser douyinUser = this.douyinUserService.saveDouyinUserByCode(code);
        return AjaxResult.success("根据code获取抖音用户信息成功", douyinUser);
    }


    /**
     * 手机端顾客发布视频
     *
     * @param id         抖音用户id
     * @param activityId
     * @return
     */
    @GetMapping("/createMerchantVideo")
    @ResponseBody
    public AjaxResult createMerchantVideo(Long id, Long activityId) {
        //先去校验商户视频转发次数是否还有剩余
        //查询营销活动
        MerCouponActivity merCouponActivity = this.merCouponActivityService.selectMerCouponActivityById(activityId);
        //判断参与限制
        long limit = merCouponActivity.getJoinTimes();
        if (limit != 0) {//为0那么不限次
            //查询是否参与过
            MerCouponGet merCouponGet = new MerCouponGet();
            merCouponGet.setActivityId(merCouponActivity.getId());
            merCouponGet.setUserId(id);
            List getRecord = merCouponGetService.selectMerCouponGetList(merCouponGet);
            if (getRecord != null && getRecord.size() >= limit) {
                return AjaxResult.error("每人只能参与" + limit + "次,谢谢您的参与。");
            }
        }

        Merchants merchant = this.merchantsService.selectMerchantsById(merCouponActivity.getMerchantId());
        int videoCount = merchant.getVideoCount();
        if (videoCount <= 0) {
            return AjaxResult.error("商家视频转发余额不足，请联系商家");
        }
        try {
            //添加一个待发布的任务
            PublishTask task = new PublishTask();
            task.setActivityId(activityId);
            task.setUserId(id);
            task.setStatus(0);
            this.publishTaskService.insertPublishTask(task);

        } catch (Exception e) {
            e.printStackTrace();
        }


        //查询活动模式，
        String mode = merCouponActivity.getActityType();

        if ("1".equals(mode)) {//推广模式不领券
            return AjaxResult.success("分享成功");
        } else if ("2".equals(mode)) {//抽奖模式不领券
            return AjaxResult.success("成功获取一次抽奖机会");
        }
        DouyinUser douyinUser = this.douyinUserService.selectDouyinUserById(id);
        // 如果是领券模式则领券
        //查询活动中的优惠券
        long couponId = merCouponActivity.getCouponId();
        MerCoupon merCoupon = null;
        try {
            merCoupon = merCouponGetService.receiveCoupon(douyinUser.getId(), activityId, couponId);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }
        merCoupon.setRemark(douyinUser.getId() + "");
        merCoupon.setMerchantName(merCouponActivity.getMerchantName());
        return AjaxResult.success("恭喜您获得一张优惠券", merCoupon);
    }

    /**
     * 抖音商家授权
     *
     * @param code
     * @return
     */
    @GetMapping("/merchantsAuth")
    public AjaxResult merchantsAuth(String code, Long merId) {
        //根据code查询添加顾客
        DouyinUser douyinUser = this.apiService.queryDouyinUserByCode(code);

        Merchants merchants = this.merchantsService.selectMerchantsById(merId);
        if (merchants != null) {
            merchants.setOpenId(douyinUser.getOpenId());
            merchants.setNickname(douyinUser.getNickname());
            merchants.setUpdateTime(DateUtils.getNowDate());
            this.merchantsService.updateMerchants(merchants);
            return AjaxResult.success();
        }
        return AjaxResult.error("授权失败");

    }


    /**
     * 查询某次活动下的转发记录
     *
     * @param activityId 活动ID
     * @return
     */
    @GetMapping("/findVideoListByActivityId")
    @ResponseBody
    public TableDataInfo findVideoListByActivityId(Long activityId) {
        startPage();
        PublishTask publishTask = new PublishTask();
        publishTask.setActivityId(activityId);
        List<PublishTask> list = publishTaskService.selectPublishTaskList(publishTask);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                PublishTask task = list.get(i);
                DouyinUser douyinUser = this.douyinUserService.selectDouyinUserById(task.getUserId());
                task.setDouyinUser(douyinUser);
            }
        }
        return getDataTable(list);
    }


    /**
     * 查询已发布视频的相关数据：点赞数, 播放数等
     *
     * @param id
     * @return
     */
    @GetMapping("/findVideoData")
    @ResponseBody
    public AjaxResult findVideoData(Long id) {
        TaskDyPublish taskDyPublish = new TaskDyPublish();
        taskDyPublish.setTaskId(id);
        List<TaskDyPublish> taskDyPublishList = this.taskDyPublishService.selectTaskDyPublishList(taskDyPublish);
        if (taskDyPublishList != null && taskDyPublishList.size() > 0) {
            String itemId = taskDyPublishList.get(0).getItemId();
            String openId = taskDyPublishList.get(0).getOpenId();

            VideoListRes.VideoListResData videoData = apiService.getVideoData(openId, itemId);

            return AjaxResult.success("查询视频数据成功", videoData);
        } else {
            return AjaxResult.error("查询视频数据失败，此视频可能已被删除");
        }
    }


    public static void main(String[] args) {
        String videoUrl = "/profile/upload/2021/03/28/8a261ccc-45e0-4c3a-82ad-67fa9d1c1e5d.mp4";
        videoUrl = videoUrl.replace(Constants.RESOURCE_PREFIX, "");
        videoUrl = "D:/ruoyi/uploadPath" + videoUrl;
        System.out.println(videoUrl);
    }




//    @GetMapping("/testMerge")
//    @ResponseBody
//    public AjaxResult testMerge(Long id, Long activityId) {
//        try {
//            //查询营销活动
//            MerCouponActivity merCouponActivity = this.merCouponActivityService.selectMerCouponActivityById(activityId);
//            DouyinUser douyinUser = this.douyinUserService.selectDouyinUserById(id);
//            //发送活动视频到抖音
//            dyActivityVideoService.pushVideo(merCouponActivity, douyinUser.getOpenId());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return AjaxResult.success("测试视频合成成功");
//
//    }
}
