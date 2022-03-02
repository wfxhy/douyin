package com.toker.project.merchants.service.impl;

import java.util.List;
import com.toker.common.utils.DateUtils;
import com.toker.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.toker.project.merchants.mapper.MerchantsMapper;
import com.toker.project.merchants.domain.Merchants;
import com.toker.project.merchants.service.IMerchantsService;

/**
 * 商户信息Service业务层处理
 * 
 * @author wf
 * @date 2022-02-28
 */
@Service
public class MerchantsServiceImpl implements IMerchantsService 
{
    @Autowired
    private MerchantsMapper merchantsMapper;

    /**
     * 查询商户信息
     * 
     * @param id 商户信息主键
     * @return 商户信息
     */
    @Override
    public Merchants selectMerchantsById(Long id)
    {
        return merchantsMapper.selectMerchantsById(id);
    }

    /**
     * 查询商户信息列表
     * 
     * @param merchants 商户信息
     * @return 商户信息
     */
    @Override
    public List<Merchants> selectMerchantsList(Merchants merchants)
    {
        return merchantsMapper.selectMerchantsList(merchants);
    }

    /**
     * 新增商户信息
     * 
     * @param merchants 商户信息
     * @return 结果
     */
    @Override
    public int insertMerchants(Merchants merchants)
    {
        merchants.setCreateTime(DateUtils.getNowDate());
        merchants.setCreateBy(SecurityUtils.getUsername());
        merchants.setAgentId(SecurityUtils.getLoginUser().getUser().getAgentId());
        return merchantsMapper.insertMerchants(merchants);
    }

    /**
     * 修改商户信息
     * 
     * @param merchants 商户信息
     * @return 结果
     */
    @Override
    public int updateMerchants(Merchants merchants)
    {
        merchants.setUpdateTime(DateUtils.getNowDate());
        return merchantsMapper.updateMerchants(merchants);
    }

    /**
     * 批量删除商户信息
     * 
     * @param ids 需要删除的商户信息主键
     * @return 结果
     */
    @Override
    public int deleteMerchantsByIds(Long[] ids)
    {
        return merchantsMapper.deleteMerchantsByIds(ids);
    }

    /**
     * 删除商户信息信息
     * 
     * @param id 商户信息主键
     * @return 结果
     */
    @Override
    public int deleteMerchantsById(Long id)
    {
        return merchantsMapper.deleteMerchantsById(id);
    }

    /**
     * 视频余量减1
     *
     * @param merchantId
     * @return
     */
    public int minusVideoCount(Long merchantId, int videoCount){
        return this.merchantsMapper.minusVideoCount(merchantId,videoCount);
    }
}
