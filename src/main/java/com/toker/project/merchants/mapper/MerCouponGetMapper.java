package com.toker.project.merchants.mapper;

import com.toker.project.merchants.domain.MerCouponGet;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 优惠券领取Mapper接口
 *
 * @author huohuzhihui
 * @date 2021-03-30
 */
@Repository
public interface MerCouponGetMapper {
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
     * 删除优惠券领取
     *
     * @param id 优惠券领取ID
     * @return 结果
     */
    public int deleteMerCouponGetById(Long id);

    /**
     * 批量删除优惠券领取
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMerCouponGetByIds(Long[] ids);

    /**
     * 统计优惠券领取数量
     *
     * @param merCouponGet
     * @return
     */
    public Long selectMerCouponGetCount(MerCouponGet merCouponGet);

    /**
     * 统计优惠券核销总数
     *
     * @param merCouponGet
     * @return
     */
    public Long selectMerCouponCheckCount(MerCouponGet merCouponGet);

    /**
     * 根据优惠券码查询优惠券
     *
     * @param couponCode
     * @return
     */
    public MerCouponGet selectMerCouponGetByCouponCode(String couponCode);
}
