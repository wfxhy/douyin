package com.toker.project.merchants.service;

import com.toker.project.merchants.domain.MerCouponActivity;

import java.util.List;

/**
 * 优惠券活动Service接口
 *
 * @author huohuzhihui
 * @date 2021-03-30
 */
public interface IMerCouponActivityService {
    /**
     * 查询优惠券活动
     *
     * @param id 优惠券活动ID
     * @return 优惠券活动
     */
    public MerCouponActivity selectMerCouponActivityById(Long id);

    /**
     * 查询优惠券活动列表
     *
     * @param merCouponActivity 优惠券活动
     * @return 优惠券活动集合
     */
    public List<MerCouponActivity> selectMerCouponActivityList(MerCouponActivity merCouponActivity);

    /**
     * 新增优惠券活动
     *
     * @param merCouponActivity 优惠券活动
     * @return 结果
     */
    public int insertMerCouponActivity(MerCouponActivity merCouponActivity);

    /**
     * 修改优惠券活动
     *
     * @param merCouponActivity 优惠券活动
     * @return 结果
     */
    public int updateMerCouponActivity(MerCouponActivity merCouponActivity);

    /**
     * 批量删除优惠券活动
     *
     * @param ids 需要删除的优惠券活动ID
     * @return 结果
     */
    public int deleteMerCouponActivityByIds(Long[] ids);

    /**
     * 删除优惠券活动信息
     *
     * @param id 优惠券活动ID
     * @return 结果
     */
    public int deleteMerCouponActivityById(Long id);

    /**
     * 下架优惠券活动
     *
     * @param ids
     * @return
     */
    public int offilneMerCouponActivity(Long[] ids);

    /**
     * 上线优惠券活动
     *
     * @param ids
     * @return
     */
    public int onlineMerCouponActivity(Long[] ids);

    /**
     * 查询活动数量
     *
     * @param activity
     * @return
     */
    public long selectMerCouponActivityCount(MerCouponActivity activity);
}
