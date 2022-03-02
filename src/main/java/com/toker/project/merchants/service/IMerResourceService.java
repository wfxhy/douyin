package com.toker.project.merchants.service;

import java.util.List;
import com.toker.project.merchants.domain.MerResource;
import org.apache.ibatis.annotations.Param;

/**
 * 商户资源Service接口
 * 
 * @author wf
 * @date 2022-02-28
 */
public interface IMerResourceService 
{
    /**
     * 查询商户资源
     * 
     * @param id 商户资源主键
     * @return 商户资源
     */
    public MerResource selectMerResourceById(Long id);

    /**
     * 查询商户资源列表
     * 
     * @param merResource 商户资源
     * @return 商户资源集合
     */
    public List<MerResource> selectMerResourceList(MerResource merResource);

    /**
     * 新增商户资源
     * 
     * @param merResource 商户资源
     * @return 结果
     */
    public int insertMerResource(MerResource merResource);

    /**
     * 修改商户资源
     * 
     * @param merResource 商户资源
     * @return 结果
     */
    public int updateMerResource(MerResource merResource);

    /**
     * 批量删除商户资源
     * 
     * @param ids 需要删除的商户资源主键集合
     * @return 结果
     */
    public int deleteMerResourceByIds(Long[] ids);

    /**
     * 删除商户资源信息
     * 
     * @param id 商户资源主键
     * @return 结果
     */
    public int deleteMerResourceById(Long id);

    public MerResource getRandomAudio(Long merchantId);

    /**
     * 获取一条随机视频
     *
     * @param resoureGroupId
     * @param activityId
     * @return
     */
    public MerResource getRandMerReource(Long merchantId, Long resoureGroupId, Long activityId);
}
