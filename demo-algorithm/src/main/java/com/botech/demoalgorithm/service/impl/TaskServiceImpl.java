/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: TaskServiceImpl
 * Author:   王呐宇
 * Date:     2019/7/16 16:19
 * Description: 任务业务处理层
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.service.impl;

import com.alibaba.fastjson.JSON;
import com.botech.demoalgorithm.dao.TaskMapper;
import com.botech.demoalgorithm.domain.TaskDao;
import com.botech.demoalgorithm.service.TaskService;
import com.botech.demoalgorithm.vo.CreateParamVO;
import com.botech.demoalgorithm.vo.UpdateParamVO;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉<br> 
 * 〈任务业务处理层〉
 *
 * @author 王呐宇
 * @date 2019/7/16
 * @since 1.0.0
 */
@Slf4j
@Service
@CacheConfig(cacheNames = {"myCache"})
public class TaskServiceImpl implements TaskService {

    @Value("${task.totalNum}")
    private String totalNum;

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public Map<String,Object> createTask(CreateParamVO createParamVO) {
        Map<String, Object> result = new HashMap<>();
        try {
            int count = taskMapper.countRunningTask();
            if (count >= Integer.valueOf(totalNum)) {
                result.put("returnCode", 11001);
                log.info("资源不足，无法创建任务");
                return result;
            }
            String num = String.format("%02d", count);
            LocalDateTime now = LocalDateTime.now();
            Long milliSecond = now.toInstant(ZoneOffset.of("+8")).toEpochMilli();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
            String format = now.format(formatter);
            String taskId = format + milliSecond + num;
            TaskDao taskDao = new TaskDao();
            taskDao.setTaskId(taskId);
            taskDao.setTaskParam(JSON.toJSONString(createParamVO));
            taskDao.setIsDelete(0);
            taskDao.setIsRun(1);
            taskMapper.save(taskDao);

            result.put("returnCode", 0);
            result.put("taskID", taskId);
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("系统异常：" + e.toString());
            result.put("returnCode", 1003);
            return result;
        }
    }

    @Override
    public Map<String, Object> deleteTask(String taskID) {
        Map<String,Object> result = new HashMap<>();
        try {
            TaskDao taskDao = taskMapper.findByTaskIdAndIsDeleteAndIsRun(taskID,0,1);
            if (taskDao == null) {
                log.info("任务不存在：" + taskID);
                result.put("returnCode", 1002);
                return result;
            }
            taskMapper.deleteByTaskId(taskID);

            result.put("returnCode", 0);
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("系统异常：" + e.toString());
            result.put("returnCode", 1003);
            return result;
        }
    }

    @Override
    public Map<String, Object> UpdateParam(UpdateParamVO updateParamVO) {
        Map<String,Object> result = new HashMap<>();
        try{
            TaskDao taskDao = taskMapper
                .findByTaskIdAndIsDeleteAndIsRun(updateParamVO.getTaskID(), 0, 1);
            if (taskDao == null) {
                log.info("任务不存在：" + updateParamVO.getTaskID());
                result.put("returnCode", 1002);
                return result;
            }
            CreateParamVO createParamVO = new CreateParamVO();
            createParamVO.setTaskType(updateParamVO.getTaskType());
            createParamVO.setParam(updateParamVO.getFullParam());
            taskMapper.updateParamById(JSON.toJSONString(createParamVO), updateParamVO.getTaskID());

            result.put("returnCode", 0);
            return result;
        }catch (Exception e) {
            e.printStackTrace();
            log.error("系统异常：" + e.toString());
            result.put("returnCode", 1003);
            return result;
        }
    }

    @Override
    public Map<String, Object> QueryResource() {
        Map<String, Object> result = new HashMap<>();
        List<String> taskIds = new ArrayList<>();
        try {
            result.put("returnCode", 0);
            result.put("totalNum", totalNum);
            int count = taskMapper.countRunningTask();
            result.put("usedNum", count);
            if (count == 0) {
                result.put("taskIds", taskIds);
                return result;
            }
            List<TaskDao> tasks = taskMapper.findAllByIsDeleteAndIsRun(0, 1);
            if (tasks != null && !tasks.isEmpty()) {
                tasks.stream().forEach(x -> {
                    taskIds.add(x.getTaskId());
                });
            }
            result.put("taskIds", taskIds);
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("系统异常：" + e.toString());
            result.put("returnCode", 1003);
            return result;
        }
    }

    @Override
    public Map<String, Object> getTaskInfo(String taskID) {
        Map<String,Object> result = new HashMap<>();
        try{
            TaskDao taskDao = taskMapper.findByTaskIdAndIsDeleteAndIsRun(taskID, 0, 1);
            if (taskDao == null) {
                log.info("任务不存在：" + taskID);
                result.put("returnCode", 1002);
                return result;
            }
            result.put("returnCode", 0);
            result.put("projectId",1000);
            String taskParam = taskDao.getTaskParam();
            CreateParamVO createParamVO = JSON.parseObject(taskParam, CreateParamVO.class);
            result.put("param",createParamVO.getParam());
            return result;
        }catch (Exception e) {
            e.printStackTrace();
            log.error("系统异常：" + e.toString());
            result.put("returnCode", 1003);
            return result;
        }
    }
}