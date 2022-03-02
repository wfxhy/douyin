package com.toker.project.task.service;

import com.toker.project.task.domain.TaskVideoMerge;

import java.util.List;

/**
 * 视频合成记录Service接口
 *
 * @author huohuzhihui
 * @date 2021-06-28
 */
public interface ITaskVideoMergeService {
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
     * 批量删除视频合成记录
     *
     * @param ids 需要删除的视频合成记录ID
     * @return 结果
     */
    public int deleteTaskVideoMergeByIds(Long[] ids);

    /**
     * 删除视频合成记录信息
     *
     * @param id 视频合成记录ID
     * @return 结果
     */
    public int deleteTaskVideoMergeById(Long id);

    /**
     * 查询待合并的资源id字符串
     *
     * @return
     */
    public String getMergeResourceIds(Long activityId);

}
