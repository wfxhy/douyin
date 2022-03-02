package com.toker.project.task.mapper;

import com.toker.project.task.domain.TaskVideoMerge;

import java.util.List;
import java.util.Map;

/**
 * 视频合成记录Mapper接口
 *
 * @author huohuzhihui
 * @date 2021-06-28
 */
public interface TaskVideoMergeMapper {
    /**
     * 查询视频合成记录
     *
     * @param id 视频合成记录ID
     * @return 视频合成记录
     */
    public TaskVideoMerge selectTaskVideoMergeById(Long id);

    /**
     * 查询视频合成记录列表
     *
     * @param taskVideoMerge 视频合成记录
     * @return 视频合成记录集合
     */
    public List<TaskVideoMerge> selectTaskVideoMergeList(TaskVideoMerge taskVideoMerge);

    /**
     * 新增视频合成记录
     *
     * @param taskVideoMerge 视频合成记录
     * @return 结果
     */
    public int insertTaskVideoMerge(TaskVideoMerge taskVideoMerge);

    /**
     * 修改视频合成记录
     *
     * @param taskVideoMerge 视频合成记录
     * @return 结果
     */
    public int updateTaskVideoMerge(TaskVideoMerge taskVideoMerge);

    /**
     * 删除视频合成记录
     *
     * @param id 视频合成记录ID
     * @return 结果
     */
    public int deleteTaskVideoMergeById(Long id);

    /**
     * 批量删除视频合成记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTaskVideoMergeByIds(Long[] ids);

    /**
     * 查询待合并的资源id字符串：每个分组中随机去一个，并且和之前发过的去对比，以避免重复
     *
     * @param param
     * @return
     */
    public void getMergeResourceIds(Map<String, Object> param);
}
