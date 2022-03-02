package com.toker.project.merchants.service.impl;

import java.util.List;
import com.toker.common.utils.DateUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.toker.project.merchants.mapper.MerResourceMapper;
import com.toker.project.merchants.domain.MerResource;
import com.toker.project.merchants.service.IMerResourceService;

/**
 * 商户资源Service业务层处理
 * 
 * @author wf
 * @date 2022-02-28
 */
@Service
public class MerResourceServiceImpl implements IMerResourceService 
{
    @Autowired
    private MerResourceMapper merResourceMapper;

    /**
     * 查询商户资源
     * 
     * @param id 商户资源主键
     * @return 商户资源
     */
    @Override
    public MerResource selectMerResourceById(Long id)
    {
        return merResourceMapper.selectMerResourceById(id);
    }

    /**
     * 查询商户资源列表
     * 
     * @param merResource 商户资源
     * @return 商户资源
     */
    @Override
    public List<MerResource> selectMerResourceList(MerResource merResource)
    {
        return merResourceMapper.selectMerResourceList(merResource);
    }

    /**
     * 新增商户资源
     * 
     * @param merResource 商户资源
     * @return 结果
     */
    @Override
    public int insertMerResource(MerResource merResource)
    {
        merResource.setCreateTime(DateUtils.getNowDate());
        return merResourceMapper.insertMerResource(merResource);
    }

    /**
     * 修改商户资源
     * 
     * @param merResource 商户资源
     * @return 结果
     */
    @Override
    public int updateMerResource(MerResource merResource)
    {
        merResource.setUpdateTime(DateUtils.getNowDate());
        return merResourceMapper.updateMerResource(merResource);
    }

    /**
     * 批量删除商户资源
     * 
     * @param ids 需要删除的商户资源主键
     * @return 结果
     */
    @Override
    public int deleteMerResourceByIds(Long[] ids)
    {
        return merResourceMapper.deleteMerResourceByIds(ids);
    }

    /**
     * 删除商户资源信息
     * 
     * @param id 商户资源主键
     * @return 结果
     */
    @Override
    public int deleteMerResourceById(Long id)
    {
        return merResourceMapper.deleteMerResourceById(id);
    }

    @Override
    public MerResource getRandomAudio(@Param("merchantId") Long merchantId){
        return this.merResourceMapper.getRandomAudio(merchantId);
    }

    /**
     * 获取一条随机视频
     *
     * @param resoureGroupId
     * @param activityId
     * @return
     */
    public MerResource getRandMerReource(Long merchantId, Long resoureGroupId, Long activityId){
        return this.merResourceMapper.getRandMerReource(merchantId,resoureGroupId,activityId);
    }
}
