package com.toker.project.merchants.service.impl;

import com.toker.project.merchants.domain.MerCouponActivity;
import com.toker.project.merchants.mapper.MerCouponActivityMapper;
import com.toker.project.merchants.service.IMerCouponActivityService;
import com.toker.project.merchants.service.IMerResourceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 优惠券活动Service业务层处理
 *
 * @author huohuzhihui
 * @date 2021-03-30
 */
@Service
public class MerCouponActivityServiceImpl implements IMerCouponActivityService {
    @Autowired
    private MerCouponActivityMapper merCouponActivityMapper;
    @Autowired
    private IMerResourceService merResourceService;

    /**
     * 查询优惠券活动
     *
     * @param id 优惠券活动ID
     * @return 优惠券活动
     */
    @Override
    public MerCouponActivity selectMerCouponActivityById(Long id) {
        return merCouponActivityMapper.selectMerCouponActivityById(id);
    }

    /**
     * 查询优惠券活动列表
     *
     * @param merCouponActivity 优惠券活动
     * @return 优惠券活动
     */
    @Override
    public List<MerCouponActivity> selectMerCouponActivityList(MerCouponActivity merCouponActivity) {
        return merCouponActivityMapper.selectMerCouponActivityList(merCouponActivity);
    }

    /**
     * 新增优惠券活动
     *
     * @param merCouponActivity 优惠券活动
     * @return 结果
     */
    @Override
    public int insertMerCouponActivity(MerCouponActivity merCouponActivity) {
        merCouponActivity.setResourceGroupId(StringUtils.join(merCouponActivity.getResourceGroupIds(), ","));
        return merCouponActivityMapper.insertMerCouponActivity(merCouponActivity);
    }

    /**
     * 修改优惠券活动
     *
     * @param merCouponActivity 优惠券活动
     * @return 结果
     */
    @Override
    public int updateMerCouponActivity(MerCouponActivity merCouponActivity) {
        merCouponActivity.setResourceGroupId(StringUtils.join(merCouponActivity.getResourceGroupIds(), ","));
        int ret = merCouponActivityMapper.updateMerCouponActivity(merCouponActivity);
        //插入活动视频
        /*if(merCouponActivity.getVideoSource()==0){//按资源分组查询视频
            Long[] groupIdAry = merCouponActivity.getResourceGroupIds();
            if(groupIdAry!=null && groupIdAry.length>0){
                for(int i = 0 ; i < groupIdAry.length;i++){
                    long groupId = groupIdAry[i];
                    MerResource merResource=new MerResource();
                    merResource.setGroupId(groupId);
                    List<MerResource> merResourceList = merResourceService.selectMerResourceList(merResource);
                    if(merResourceList!=null && merResourceList.size()>0){
                        for(int j = 0 ; j < merResourceList.size();j++){
                            MerResource resource = merResourceList.get(j);
                            //保存到待分享视频中
                            MerActivityVideo merActivityVideo = new MerActivityVideo();
                            merActivityVideo.setActityId(merCouponActivity.getId());
                            merActivityVideo.setResourceGroupId(groupId);
                            merActivityVideo.setVideoUrl(resource.getResourceUrl());
                            this.merActivityVideoMapper.insertMerActivityVideo(merActivityVideo);
                        }
                    }
                }
            }
        }*/
        return ret;
    }

    /**
     * 批量删除优惠券活动
     *
     * @param ids 需要删除的优惠券活动ID
     * @return 结果
     */
    @Override
    public int deleteMerCouponActivityByIds(Long[] ids) {
        return merCouponActivityMapper.deleteMerCouponActivityByIds(ids);
    }

    /**
     * 删除优惠券活动信息
     *
     * @param id 优惠券活动ID
     * @return 结果
     */
    @Override
    public int deleteMerCouponActivityById(Long id) {
        return merCouponActivityMapper.deleteMerCouponActivityById(id);
    }

    @Override
    public int offilneMerCouponActivity(Long[] ids) {
        return merCouponActivityMapper.offilneMerCouponActivity(ids);
    }

    @Override
    public int onlineMerCouponActivity(Long[] ids) {
        return merCouponActivityMapper.onlineMerCouponActivity(ids);
    }

    @Override
    public long selectMerCouponActivityCount(MerCouponActivity activity) {
        return merCouponActivityMapper.selectMerCouponActivityCount(activity);
    }
}
