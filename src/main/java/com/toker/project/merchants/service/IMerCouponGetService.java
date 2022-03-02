package com.toker.project.merchants.service;

import com.toker.project.merchants.domain.MerCoupon;
import com.toker.project.merchants.domain.MerCouponGet;
import com.toker.project.merchants.exception.CouponCheckException;

import java.util.List;

/**
 * 优惠券领取Service接口
 *
 * @author huohuzhihui
 * @date 2021-03-30
 */
public interface IMerCouponGetService {
    /**
     * 查询优惠券领取
     *
     * @param id 优惠券领取ID
     * @return 优惠券领取
     */
    public MerCouponGet selectMerCouponGetById(Long id);

    /**
     * 查询优惠券领取列表
     *
     * @param merCouponGet 优惠券领取
     * @return 优惠券领取集合
     */
    public List<MerCouponGet> selectMerCouponGetList(MerCouponGet merCouponGet);

    /**
     * 新增优惠券领取
     *
     * @param merCouponGet 优惠券领取
     * @return 结果
     */
    public int insertMerCouponGet(MerCouponGet merCouponGet);

    /**
     * 修改优惠券领取
     *
     * @param merCouponGet 优惠券领取
     * @return 结果
     */
    public int updateMerCouponGet(MerCouponGet merCouponGet);

    /**
     * 批量删除优惠券领取
     *
     * @param ids 需要删除的优惠券领取ID
     * @return 结果
     */
    public int deleteMerCouponGetByIds(Long[] ids);

    /**
     * 删除优惠券领取信息
     *
     * @param id 优惠券领取ID
     * @return 结果
     */
    public int deleteMerCouponGetById(Long id);

    /**
     * 领取优惠券
     *
     * @param userId   用户ID
     * @param couponId 优惠券ID
     * @return
     */
    MerCoupon receiveCoupon(Long userId, long activityId, long couponId) throws Exception;


    /**
     * 优惠券领取数量统计
     *
     * @param merCouponGet
     * @return
     */
    public Long selectMerCouponGetCount(MerCouponGet merCouponGet);

    /**
     * 优惠券核销总数
     *
     * @param merCouponGet
     * @return
     */
    public Long selectMerCouponCheckCount(MerCouponGet merCouponGet);


    int checkCouponByCouponCode(String couponCode) throws CouponCheckException;
}
