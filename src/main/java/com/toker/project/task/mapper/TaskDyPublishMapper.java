package com.toker.project.task.mapper;

import com.toker.project.task.domain.PublishTask;
import com.toker.project.task.domain.TaskDyPublish;

import java.util.List;

/**
 * 抖音推送记录Mapper接口
 *
 * @author huohuzhihui
 * @date 2021-06-28
 */
public interface TaskDyPublishMapper {
    /**
     * 查询抖音推送记录
     *
     * @param id 抖音推送记录ID
     * @return 抖音推送记录
     */
    public TaskDyPublish selectTaskDyPublishById(Long id);

    /**
     * 查询抖音推送记录列表
     *
     * @param taskDyPublish 抖音推送记录
     * @return 抖音推送记录集合
     */
    public List<TaskDyPublish> selectTaskDyPublishList(TaskDyPublish taskDyPublish);

    /**
     * 新增抖音推送记录
     *
     * @param taskDyPublish 抖音推送记录
     * @return 结果
     */
    public int insertTaskDyPublish(TaskDyPublish taskDyPublish);

    /**
     * 修改抖音推送记录
     *
     * @param taskDyPublish 抖音推送记录
     * @return 结果
     */
    public int updateTaskDyPublish(TaskDyPublish taskDyPublish);

    /**
     * 删除抖音推送记录
     *
     * @param id 抖音推送记录ID
     * @return 结果
     */
    public int deleteTaskDyPublishById(Long id);

    /**
     * 批量删除抖音推送记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTaskDyPublishByIds(Long[] ids);

    public long getPlayCount(PublishTask publishTask);

    public long getDiggCount(PublishTask publishTask);

    public long getCommentCount(PublishTask publishTask);
}
