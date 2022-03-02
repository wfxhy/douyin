package com.toker.project.merchants.service;

import java.util.List;
import com.toker.project.merchants.domain.MerResourceGroup;

/**
 * 商户资源分组Service接口
 * 
 * @author wf
 * @date 2022-02-28
 */
public interface IMerResourceGroupService 
{
    /**
     * 查询商户资源分组
     * 
     * @param id 商户资源分组主键
     * @return 商户资源分组
     */
    public MerResourceGroup selectMerResourceGroupById(Long id);

    /**
     * 查询商户资源分组列表
     * 
     * @param merResourceGroup 商户资源分组
     * @return 商户资源分组集合
     */
    public List<MerResourceGroup> selectMerResourceGroupList(MerResourceGroup merResourceGroup);

    /**
     * 新增商户资源分组
     * 
     * @param merResourceGroup 商户资源分组
     * @return 结果
     */
    public int insertMerResourceGroup(MerResourceGroup merResourceGroup);

    /**
     * 修改商户资源分组
     * 
     * @param merResourceGroup 商户资源分组
     * @return 结果
     */
    public int updateMerResourceGroup(MerResourceGroup merResourceGroup);

    /**
     * 批量删除商户资源分组
     * 
     * @param ids 需要删除的商户资源分组主键集合
     * @return 结果
     */
    public int deleteMerResourceGroupByIds(Long[] ids);

    /**
     * 删除商户资源分组信息
     * 
     * @param id 商户资源分组主键
     * @return 结果
     */
    public int deleteMerResourceGroupById(Long id);
}
