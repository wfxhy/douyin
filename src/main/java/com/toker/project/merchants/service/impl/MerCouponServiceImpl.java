package com.toker.project.merchants.service.impl;

import com.toker.project.merchants.domain.MerCoupon;
import com.toker.project.merchants.mapper.MerCouponMapper;
import com.toker.project.merchants.service.IMerCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 优惠券Service业务层处理
 *
 * @author huohuzhihui
 * @date 2021-03-30
 */
@Service
public class MerCouponServiceImpl implements IMerCouponService {
    @Autowired
    private MerCouponMapper merCouponMapper;

    /**
     * 查询优惠券
     *
     * @param id 优惠券ID
     * @return 优惠券
     */
    @Override
    public MerCoupon selectMerCouponById(Long id) {
        return merCouponMapper.selectMerCouponById(id);
    }

    /**
     * 查询优惠券列表
     *
     * @param merCoupon 优惠券
     * @return 优惠券
     */
    @Override
    public List<MerCoupon> selectMerCouponList(MerCoupon merCoupon) {
        return merCouponMapper.selectMerCouponList(merCoupon);
    }

    /**
     * 新增优惠券
     *
     * @param merCoupon 优惠券
     * @return 结果
     */
    @Override
    public int insertMerCoupon(MerCoupon merCoupon) {
        return merCouponMapper.insertMerCoupon(merCoupon);
    }

    /**
     * 修改优惠券
     *
     * @param merCoupon 优惠券
     * @return 结果
     */
    @Override
    public int updateMerCoupon(MerCoupon merCoupon) {
        return merCouponMapper.updateMerCoupon(merCoupon);
    }

    /**
     * 批量删除优惠券
     *
     * @param ids 需要删除的优惠券ID
     * @return 结果
     */
    @Override
    public int deleteMerCouponByIds(Long[] ids) {
        return merCouponMapper.deleteMerCouponByIds(ids);
    }

    /**
     * 删除优惠券信息
     *
     * @param id 优惠券ID
     * @return 结果
     */
    @Override
    public int deleteMerCouponById(Long id) {
        return merCouponMapper.deleteMerCouponById(id);
    }

    @Override
    public int offilneMerCoupon(Long[] ids) {
        return merCouponMapper.offlineMerCoupon(ids);
    }

}
