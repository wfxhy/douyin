package com.toker.project.merchants.mapper;

import com.toker.project.merchants.domain.MerCoupon;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 优惠券Mapper接口
 *
 * @author huohuzhihui
 * @date 2021-03-30
 */
@Repository
public interface MerCouponMapper {
    /**
     * 查询优惠券
     *
     * @param id 优惠券ID
     * @return 优惠券
     */
    public MerCoupon selectMerCouponById(Long id);

    /**
     * 查询优惠券列表
     *
     * @param merCoupon 优惠券
     * @return 优惠券集合
     */
    public List<MerCoupon> selectMerCouponList(MerCoupon merCoupon);

    /**
     * 新增优惠券
     *
     * @param merCoupon 优惠券
     * @return 结果
     */
    public int insertMerCoupon(MerCoupon merCoupon);

    /**
     * 修改优惠券
     *
     * @param merCoupon 优惠券
     * @return 结果
     */
    public int updateMerCoupon(MerCoupon merCoupon);

    /**
     * 删除优惠券
     *
     * @param id 优惠券ID
     * @return 结果
     */
    public int deleteMerCouponById(Long id);

    /**
     * 批量删除优惠券
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMerCouponByIds(Long[] ids);

    /**
     * 下架优惠券
     *
     * @param ids
     * @return
     */
    public int offlineMerCoupon(Long[] ids);
}
