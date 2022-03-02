package com.toker.project.merchants.service;

import java.util.List;
import com.toker.project.merchants.domain.Merchants;

/**
 * 商户信息Service接口
 * 
 * @author wf
 * @date 2022-02-28
 */
public interface IMerchantsService 
{
    /**
     * 查询商户信息
     * 
     * @param id 商户信息主键
     * @return 商户信息
     */
    public Merchants selectMerchantsById(Long id);

    /**
     * 查询商户信息列表
     * 
     * @param merchants 商户信息
     * @return 商户信息集合
     */
    public List<Merchants> selectMerchantsList(Merchants merchants);

    /**
     * 新增商户信息
     * 
     * @param merchants 商户信息
     * @return 结果
     */
    public int insertMerchants(Merchants merchants);

    /**
     * 修改商户信息
     * 
     * @param merchants 商户信息
     * @return 结果
     */
    public int updateMerchants(Merchants merchants);

    /**
     * 批量删除商户信息
     * 
     * @param ids 需要删除的商户信息主键集合
     * @return 结果
     */
    public int deleteMerchantsByIds(Long[] ids);

    /**
     * 删除商户信息信息
     * 
     * @param id 商户信息主键
     * @return 结果
     */
    public int deleteMerchantsById(Long id);

    /**
     * 视频余量减1
     *
     * @param merchantId
     * @return
     */
    public int minusVideoCount(Long merchantId, int videoCount);
}
