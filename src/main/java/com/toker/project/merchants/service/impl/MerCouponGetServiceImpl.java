package com.toker.project.merchants.service.impl;

import com.toker.common.utils.DateUtils;
import com.toker.common.utils.RandomUtil;
import com.toker.project.merchants.domain.MerCoupon;
import com.toker.project.merchants.domain.MerCouponGet;
import com.toker.project.merchants.exception.CouponCheckException;
import com.toker.project.merchants.mapper.MerCouponGetMapper;
import com.toker.project.merchants.mapper.MerCouponMapper;
import com.toker.project.merchants.service.IMerCouponGetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 优惠券领取Service业务层处理
 *
 * @author huohuzhihui
 * @date 2021-03-30
 */
@Service
public class MerCouponGetServiceImpl implements IMerCouponGetService {
    @Autowired
    private MerCouponGetMapper merCouponGetMapper;
    @Autowired
    private MerCouponMapper merCouponMapper;

    /**
     * 查询优惠券领取
     *
     * @param id 优惠券领取ID
     * @return 优惠券领取
     */
    @Override
    public MerCouponGet selectMerCouponGetById(Long id) {
        return merCouponGetMapper.selectMerCouponGetById(id);
    }

    /**
     * 查询优惠券领取列表
     *
     * @param merCouponGet 优惠券领取
     * @return 优惠券领取
     */
    @Override
    public List<MerCouponGet> selectMerCouponGetList(MerCouponGet merCouponGet) {
        return merCouponGetMapper.selectMerCouponGetList(merCouponGet);
    }

    /**
     * 新增优惠券领取
     *
     * @param merCouponGet 优惠券领取
     * @return 结果
     */
    @Override
    public int insertMerCouponGet(MerCouponGet merCouponGet) {
        merCouponGet.setCreateTime(DateUtils.getNowDate());
        return merCouponGetMapper.insertMerCouponGet(merCouponGet);
    }

    /**
     * 修改优惠券领取
     *
     * @param merCouponGet 优惠券领取
     * @return 结果
     */
    @Override
    public int updateMerCouponGet(MerCouponGet merCouponGet) {
        return merCouponGetMapper.updateMerCouponGet(merCouponGet);
    }

    /**
     * 批量删除优惠券领取
     *
     * @param ids 需要删除的优惠券领取ID
     * @return 结果
     */
    @Override
    public int deleteMerCouponGetByIds(Long[] ids) {
        return merCouponGetMapper.deleteMerCouponGetByIds(ids);
    }

    /**
     * 删除优惠券领取信息
     *
     * @param id 优惠券领取ID
     * @return 结果
     */
    @Override
    public int deleteMerCouponGetById(Long id) {
        return merCouponGetMapper.deleteMerCouponGetById(id);
    }

    @Override
    public MerCoupon receiveCoupon(Long userId, long activityId, long couponId) throws Exception {
        MerCoupon merCoupon = this.merCouponMapper.selectMerCouponById(couponId);
        //判断优惠券库存
        if (merCoupon.getQuantity() <= 0) {
            throw new Exception("来晚了，优惠券已经被领光了，谢谢您的参与");
        }

        //插入优惠券领取记录
        MerCouponGet merCouponGet = new MerCouponGet();
        merCouponGet.setCouponId(couponId);
        merCouponGet.setUserId(userId);
        merCouponGet.setActivityId(activityId);
        Date now = DateUtils.getNowDate();
        merCouponGet.setCreateTime(now);
        merCouponGet.setCouponCode(RandomUtil.generateOnlyNumber(10));

        //设置优惠券到期时间

        int timeType = merCoupon.getTimeType();
        if (timeType == 0) {//基于领取时间的有效天数days
            merCouponGet.setBeginDate(now);
            merCouponGet.setEndDate(DateUtils.addDays(now, merCoupon.getDays()));
        } else if (timeType == 1) {
            merCouponGet.setBeginDate(merCoupon.getStartTime());
            merCouponGet.setEndDate(merCoupon.getOverTime());
        }

        int ret = insertMerCouponGet(merCouponGet);
        if (ret == 1) {
            //优惠券库存减1
            merCoupon.setQuantity(merCoupon.getQuantity() - 1);
            this.merCouponMapper.updateMerCoupon(merCoupon);
            return merCoupon;
        } else {
            return null;
        }
    }


    @Override
    public Long selectMerCouponGetCount(MerCouponGet merCouponGet) {
        return this.merCouponGetMapper.selectMerCouponGetCount(merCouponGet);
    }

    @Override
    public Long selectMerCouponCheckCount(MerCouponGet merCouponGet) {
        return this.merCouponGetMapper.selectMerCouponCheckCount(merCouponGet);
    }

    @Override
    public int checkCouponByCouponCode(String couponCode) throws CouponCheckException {
        MerCouponGet merCouponGet = this.merCouponGetMapper.selectMerCouponGetByCouponCode(couponCode);
        //校验优惠券日期
        if (merCouponGet != null) {
            if (merCouponGet.getUseStatus() == 0) {
                Date beginDate = merCouponGet.getBeginDate();
                Date endDate = merCouponGet.getEndDate();
                Date now = DateUtils.getNowDate();

                if(now.before(beginDate)){
                    throw new CouponCheckException(5004, "优惠券不能提前使用");
                }else if (now.after(beginDate) && now.before(endDate)) {
                    merCouponGet.setUseTime(now);
                    merCouponGet.setUseStatus(1);
                    return this.merCouponGetMapper.updateMerCouponGet(merCouponGet);
                } else {
                    throw new CouponCheckException(5002, "已经过期的优惠券");
                }
            } else if (merCouponGet.getUseStatus() == 1) {
                throw new CouponCheckException(5003, "已经用过的优惠券");
            } else {
                throw new CouponCheckException(5002, "已经过期的优惠券");
            }
        } else {
            throw new CouponCheckException(5001, "非本店优惠券");
        }
    }
}
