package com.toker.project.task.service.impl;

import com.toker.common.utils.DateUtils;
import com.toker.project.task.domain.PublishTask;
import com.toker.project.task.mapper.PublishTaskMapper;
import com.toker.project.task.service.IPublishTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 待发布视频的任务Service业务层处理
 *
 * @author huohuzhihui
 * @date 2021-06-28
 */
@Service
public class PublishTaskServiceImpl implements IPublishTaskService {
    @Autowired
    private PublishTaskMapper publishTaskMapper;

    /**
     * 查询待发布视频的任务
     *
     * @param id 待发布视频的任务ID
     * @return 待发布视频的任务
     */
    @Override
    public PublishTask selectPublishTaskById(Long id) {
        return publishTaskMapper.selectPublishTaskById(id);
    }

    /**
     * 查询待发布视频的任务列表
     *
     * @param publishTask 待发布视频的任务
     * @return 待发布视频的任务
     */
    @Override
    public List<PublishTask> selectPublishTaskList(PublishTask publishTask) {
        return publishTaskMapper.selectPublishTaskList(publishTask);
    }

    /**
     * 新增待发布视频的任务
     *
     * @param publishTask 待发布视频的任务
     * @return 结果
     */
    @Override
    public int insertPublishTask(PublishTask publishTask) {
        publishTask.setCreateTime(DateUtils.getNowDate());
        return publishTaskMapper.insertPublishTask(publishTask);
    }

    /**
     * 修改待发布视频的任务
     *
     * @param publishTask 待发布视频的任务
     * @return 结果
     */
    @Override
    public int updatePublishTask(PublishTask publishTask) {
        publishTask.setUpdateTime(DateUtils.getNowDate());
        return publishTaskMapper.updatePublishTask(publishTask);
    }

    /**
     * 批量删除待发布视频的任务
     *
     * @param ids 需要删除的待发布视频的任务ID
     * @return 结果
     */
    @Override
    public int deletePublishTaskByIds(Long[] ids) {
        return publishTaskMapper.deletePublishTaskByIds(ids);
    }

    /**
     * 删除待发布视频的任务信息
     *
     * @param id 待发布视频的任务ID
     * @return 结果
     */
    @Override
    public int deletePublishTaskById(Long id) {
        return publishTaskMapper.deletePublishTaskById(id);
    }


    @Override
    public long getJoinCount(PublishTask publishTask) {
        return publishTaskMapper.getJoinCount(publishTask);
    }


}
