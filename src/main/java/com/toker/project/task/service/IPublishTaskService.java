package com.toker.project.task.service;

import com.toker.project.task.domain.PublishTask;

import java.util.List;

/**
 * 待发布视频的任务Service接口
 *
 * @author huohuzhihui
 * @date 2021-06-28
 */
public interface IPublishTaskService {
    /**
     * 查询待发布视频的任务
     *
     * @param id 待发布视频的任务ID
     * @return 待发布视频的任务
     */
    public PublishTask selectPublishTaskById(Long id);

    /**
     * 查询待发布视频的任务列表
     *
     * @param publishTask 待发布视频的任务
     * @return 待发布视频的任务集合
     */
    public List<PublishTask> selectPublishTaskList(PublishTask publishTask);

    /**
     * 新增待发布视频的任务
     *
     * @param publishTask 待发布视频的任务
     * @return 结果
     */
    public int insertPublishTask(PublishTask publishTask);

    /**
     * 修改待发布视频的任务
     *
     * @param publishTask 待发布视频的任务
     * @return 结果
     */
    public int updatePublishTask(PublishTask publishTask);

    /**
     * 批量删除待发布视频的任务
     *
     * @param ids 需要删除的待发布视频的任务ID
     * @return 结果
     */
    public int deletePublishTaskByIds(Long[] ids);

    /**
     * 删除待发布视频的任务信息
     *
     * @param id 待发布视频的任务ID
     * @return 结果
     */
    public int deletePublishTaskById(Long id);


    public long getJoinCount(PublishTask publishTask);


}
