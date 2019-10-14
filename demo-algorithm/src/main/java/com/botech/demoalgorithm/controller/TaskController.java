/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: TaskController
 * Author:   王呐宇
 * Date:     2019/7/16 14:30
 * Description: 任务相关操作
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.controller;

import com.alibaba.fastjson.JSONObject;
import com.botech.demoalgorithm.service.TaskService;
import com.botech.demoalgorithm.vo.CreateParamVO;
import com.botech.demoalgorithm.vo.UpdateParamVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br> 
 * 〈任务相关操作〉
 *
 * @author 王呐宇
 * @date 2019/7/16
 * @since 1.0.0
 */
@Api(tags = "任务相关接口")
@Slf4j
@RestController
@RequestMapping(value="Task")
public class TaskController {

    @Autowired
    private CreateParamVO defaultCreateParamVO;

    @Autowired
    private TaskService taskService;

    @ApiOperation("创建任务")
    @PostMapping(value="CreateTask")
    public Map<String, Object> createTask(@RequestBody String content,
        @RequestParam(required = true) int ProjectID) {
        Map<String, Object> result = new HashMap<>();
        if (ProjectID != 1000) {
            log.info("产品ID错误");
            result.put("returnCode", 1002);
            return result;
        }
        try {
            // 接收请求数据
            //String content = getRequestContent(request);

            JSONObject jsonObject = JSONObject.parseObject(content);
            log.info("jsonString：" + jsonObject.toJSONString());
            CreateParamVO createParamVO = JSONObject
                .parseObject(jsonObject.toJSONString(), CreateParamVO.class);
            log.info("createParamVO：" + createParamVO);
            if (createParamVO.getParam().getPrivate().getAlgParam() == null) {
                createParamVO.getParam().getPrivate()
                    .setAlgParam(defaultCreateParamVO.getParam().getPrivate().getAlgParam());
            }
            log.info("createParamVO：" + createParamVO);
            log.info("ProjectID：" + ProjectID);
            result = taskService.createTask(createParamVO);
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("系统异常：" + e.toString());
            result.put("returnCode", 1003);
        }
        return result;
    }

    private String getRequestContent(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        char[] buf = new char[1024];
        int len = 0;
        StringBuffer contentBuffer = new StringBuffer();
        while ((len = reader.read(buf)) != -1) {
            contentBuffer.append(buf, 0, len);
        }
        String content = contentBuffer.toString();
        if (content == null) {
            content = "";
        }
        return content;
    }

    @ApiOperation("删除任务")
    @PostMapping(value="DeleteTask")
    public Map<String, Object> deleteTask(@RequestBody String content,
        @RequestParam(required = true) int ProjectID) {
        Map<String, Object> result = new HashMap<>();
        if (ProjectID != 1000) {
            log.info("产品ID错误");
            result.put("returnCode", 1002);
            return result;
        }

        try{
            // 接收请求数据
            //String content = getRequestContent(request);

            JSONObject jsonObject = JSONObject.parseObject(content);
            String taskID = jsonObject.getString("taskID");
            if (StringUtils.isBlank(taskID)) {
                log.info("任务ID为空");
                result.put("returnCode", 1002);
                return result;
            }
            return taskService.deleteTask(taskID);
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("系统异常：" + e.toString());
            result.put("returnCode", 1003);
            return result;
        }

    }

    @ApiOperation("更新分析任务参数")
    @PostMapping(value="UpdateConfig")
    public Map<String, Object> updateConfig(@RequestBody String content,
        @RequestParam(required = true) int ProjectID) {
        Map<String, Object> result = new HashMap<>();
        if (ProjectID != 1000) {
            log.info("产品ID错误");
            result.put("returnCode", 1002);
            return result;
        }
        try {
            // 接收请求数据
            //String content = getRequestContent(request);

            JSONObject jsonObject = JSONObject.parseObject(content);
            log.info("jsonString：" + jsonObject.toJSONString());
            UpdateParamVO updateParamVO = JSONObject
                .parseObject(jsonObject.toJSONString(), UpdateParamVO.class);
            log.info("updateParamVO：" + updateParamVO);
            if (updateParamVO.getFullParam().getPrivate().getAlgParam() == null) {
                updateParamVO.getFullParam().getPrivate()
                    .setAlgParam(defaultCreateParamVO.getParam().getPrivate().getAlgParam());
            }
            log.info("updateParamVO：" + updateParamVO);
            log.info("ProjectID：" + ProjectID);
            result = taskService.UpdateParam(updateParamVO);
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("系统异常：" + e.toString());
            result.put("returnCode", 1003);
        }
        return result;
    }

    @ApiOperation("查询任务资源")
    @GetMapping(value="QueryResource")
    public Map<String, Object> queryResource(@RequestParam(required = true) int ProjectID) {
        Map<String, Object> result = new HashMap<>();
        if(ProjectID != 1000){
            log.info("产品ID错误");
            result.put("returnCode", 1002);
            return result;
        }
        return taskService.QueryResource();
    }

    @ApiOperation("查询任务信息")
    @GetMapping(value="GetTaskInfo")
    public Map<String, Object> getTaskInfo(@RequestParam(required = true) String taskID) {
        return taskService.getTaskInfo(taskID);
    }
}