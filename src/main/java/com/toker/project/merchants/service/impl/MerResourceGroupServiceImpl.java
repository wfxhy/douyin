package com.toker.project.merchants.service.impl;

import java.util.List;
import com.toker.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.toker.project.merchants.mapper.MerResourceGroupMapper;
import com.toker.project.merchants.domain.MerResourceGroup;
import com.toker.project.merchants.service.IMerResourceGroupService;

/**
 * 商户资源分组Service业务层处理
 * 
 * @author wf
 * @date 2022-02-28
 */
@Service
public class MerResourceGroupServiceImpl implements IMerResourceGroupService 
{
    @Autowired
    private MerResourceGroupMapper merResourceGroupMapper;

    /**
     * 查询商户资源分组
     * 
     * @param id 商户资源分组主键
     * @return 商户资源分组
     */
    @Override
    public MerResourceGroup selectMerResourceGroupById(Long id)
    {
        return merResourceGroupMapper.selectMerResourceGroupById(id);
    }

    /**
     * 查询商户资源分组列表
     * 
     * @param merResourceGroup 商户资源分组
     * @return 商户资源分组
     */
    @Override
    public List<MerResourceGroup> selectMerResourceGroupList(MerResourceGroup merResourceGroup)
    {
        return merResourceGroupMapper.selectMerResourceGroupList(merResourceGroup);
    }

    /**
     * 新增商户资源分组
     * 
     * @param merResourceGroup 商户资源分组
     * @return 结果
     */
    @Override
    public int insertMerResourceGroup(MerResourceGroup merResourceGroup)
    {
        merResourceGroup.setCreateTime(DateUtils.getNowDate());
        return merResourceGroupMapper.insertMerResourceGroup(merResourceGroup);
    }

    /**
     * 修改商户资源分组
     * 
     * @param merResourceGroup 商户资源分组
     * @return 结果
     */
    @Override
    public int updateMerResourceGroup(MerResourceGroup merResourceGroup)
    {
        merResourceGroup.setUpdateTime(DateUtils.getNowDate());
        return merResourceGroupMapper.updateMerResourceGroup(merResourceGroup);
    }

    /**
     * 批量删除商户资源分组
     * 
     * @param ids 需要删除的商户资源分组主键
     * @return 结果
     */
    @Override
    public int deleteMerResourceGroupByIds(Long[] ids)
    {
        return merResourceGroupMapper.deleteMerResourceGroupByIds(ids);
    }

    /**
     * 删除商户资源分组信息
     * 
     * @param id 商户资源分组主键
     * @return 结果
     */
    @Override
    public int deleteMerResourceGroupById(Long id)
    {
        return merResourceGroupMapper.deleteMerResourceGroupById(id);
    }
}
