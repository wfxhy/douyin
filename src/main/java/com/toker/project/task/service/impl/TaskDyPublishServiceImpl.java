package com.toker.project.task.service.impl;

import com.toker.common.utils.DateUtils;
import com.toker.project.task.domain.PublishTask;
import com.toker.project.task.domain.TaskDyPublish;
import com.toker.project.task.mapper.TaskDyPublishMapper;
import com.toker.project.task.service.ITaskDyPublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 抖音推送记录Service业务层处理
 *
 * @author huohuzhihui
 * @date 2021-06-28
 */
@Service
public class TaskDyPublishServiceImpl implements ITaskDyPublishService {
    @Autowired
    private TaskDyPublishMapper taskDyPublishMapper;

    /**
     * 查询抖音推送记录
     *
     * @param id 抖音推送记录ID
     * @return 抖音推送记录
     */
    @Override
    public TaskDyPublish selectTaskDyPublishById(Long id) {
        return taskDyPublishMapper.selectTaskDyPublishById(id);
    }

    /**
     * 查询抖音推送记录列表
     *
     * @param taskDyPublish 抖音推送记录
     * @return 抖音推送记录
     */
    @Override
    public List<TaskDyPublish> selectTaskDyPublishList(TaskDyPublish taskDyPublish) {
        return taskDyPublishMapper.selectTaskDyPublishList(taskDyPublish);
    }

    /**
     * 新增抖音推送记录
     *
     * @param taskDyPublish 抖音推送记录
     * @return 结果
     */
    @Override
    public int insertTaskDyPublish(TaskDyPublish taskDyPublish) {
        taskDyPublish.setCreateTime(DateUtils.getNowDate());
        return taskDyPublishMapper.insertTaskDyPublish(taskDyPublish);
    }

    /**
     * 修改抖音推送记录
     *
     * @param taskDyPublish 抖音推送记录
     * @return 结果
     */
    @Override
    public int updateTaskDyPublish(TaskDyPublish taskDyPublish) {
        return taskDyPublishMapper.updateTaskDyPublish(taskDyPublish);
    }

    /**
     * 批量删除抖音推送记录
     *
     * @param ids 需要删除的抖音推送记录ID
     * @return 结果
     */
    @Override
    public int deleteTaskDyPublishByIds(Long[] ids) {
        return taskDyPublishMapper.deleteTaskDyPublishByIds(ids);
    }

    /**
     * 删除抖音推送记录信息
     *
     * @param id 抖音推送记录ID
     * @return 结果
     */
    @Override
    public int deleteTaskDyPublishById(Long id) {
        return taskDyPublishMapper.deleteTaskDyPublishById(id);
    }


    @Override
    public long getPlayCount(PublishTask publishTask) {
        return taskDyPublishMapper.getPlayCount(publishTask);
    }

    @Override
    public long getDiggCount(PublishTask publishTask) {
        return taskDyPublishMapper.getDiggCount(publishTask);
    }

    @Override
    public long getCommentCount(PublishTask publishTask) {
        return taskDyPublishMapper.getCommentCount(publishTask);
    }
}
