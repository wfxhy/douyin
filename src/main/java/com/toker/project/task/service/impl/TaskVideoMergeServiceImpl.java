package com.toker.project.task.service.impl;

import com.toker.common.utils.DateUtils;
import com.toker.project.task.domain.TaskVideoMerge;
import com.toker.project.task.mapper.TaskVideoMergeMapper;
import com.toker.project.task.service.ITaskVideoMergeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 视频合成记录Service业务层处理
 *
 * @author huohuzhihui
 * @date 2021-06-28
 */
@Slf4j
@Service
public class TaskVideoMergeServiceImpl implements ITaskVideoMergeService {
    @Autowired
    private TaskVideoMergeMapper taskVideoMergeMapper;

    /**
     * 查询视频合成记录
     *
     * @param id 视频合成记录ID
     * @return 视频合成记录
     */
    @Override
    public TaskVideoMerge selectTaskVideoMergeById(Long id) {
        return taskVideoMergeMapper.selectTaskVideoMergeById(id);
    }

    /**
     * 查询视频合成记录列表
     *
     * @param taskVideoMerge 视频合成记录
     * @return 视频合成记录
     */
    @Override
    public List<TaskVideoMerge> selectTaskVideoMergeList(TaskVideoMerge taskVideoMerge) {
        return taskVideoMergeMapper.selectTaskVideoMergeList(taskVideoMerge);
    }

    /**
     * 新增视频合成记录
     *
     * @param taskVideoMerge 视频合成记录
     * @return 结果
     */
    @Override
    public int insertTaskVideoMerge(TaskVideoMerge taskVideoMerge) {
        taskVideoMerge.setCreateTime(DateUtils.getNowDate());
        return taskVideoMergeMapper.insertTaskVideoMerge(taskVideoMerge);
    }

    /**
     * 修改视频合成记录
     *
     * @param taskVideoMerge 视频合成记录
     * @return 结果
     */
    @Override
    public int updateTaskVideoMerge(TaskVideoMerge taskVideoMerge) {
        return taskVideoMergeMapper.updateTaskVideoMerge(taskVideoMerge);
    }

    /**
     * 批量删除视频合成记录
     *
     * @param ids 需要删除的视频合成记录ID
     * @return 结果
     */
    @Override
    public int deleteTaskVideoMergeByIds(Long[] ids) {
        return taskVideoMergeMapper.deleteTaskVideoMergeByIds(ids);
    }

    /**
     * 删除视频合成记录信息
     *
     * @param id 视频合成记录ID
     * @return 结果
     */
    @Override
    public int deleteTaskVideoMergeById(Long id) {
        return taskVideoMergeMapper.deleteTaskVideoMergeById(id);
    }

    @Override
    public String getMergeResourceIds(Long activityId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("activityId", activityId);
        taskVideoMergeMapper.getMergeResourceIds(param);
        log.info("activityId==="+activityId);
        log.info("resourceIds==="+param.toString());
        return param.get("resourceIds") != null ? param.get("resourceIds").toString() : null;
    }
}
