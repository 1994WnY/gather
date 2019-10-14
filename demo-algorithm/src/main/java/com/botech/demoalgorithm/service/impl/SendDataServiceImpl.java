/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: SendDataServiceImpl
 * Author:   王呐宇
 * Date:     2019/7/20 16:31
 * Description: 数据发送业务接口实现类
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.botech.demoalgorithm.dao.ImageMapper;
import com.botech.demoalgorithm.dao.TargetMapper;
import com.botech.demoalgorithm.dao.TaskMapper;
import com.botech.demoalgorithm.domain.FrameHeader;
import com.botech.demoalgorithm.domain.ImageDao;
import com.botech.demoalgorithm.domain.TargetDao;
import com.botech.demoalgorithm.domain.TaskDao;
import com.botech.demoalgorithm.domain.attribute.AttrResult;
import com.botech.demoalgorithm.domain.attribute.Attributes;
import com.botech.demoalgorithm.domain.capture.CaptureResult;
import com.botech.demoalgorithm.domain.capture.FaceInfo;
import com.botech.demoalgorithm.domain.distinguish.FaceAngle;
import com.botech.demoalgorithm.domain.distinguish.FaceRect;
import com.botech.demoalgorithm.domain.distinguish.RecogResult;
import com.botech.demoalgorithm.domain.distinguish.Similar;
import com.botech.demoalgorithm.domain.distinguish.Users;
import com.botech.demoalgorithm.domain.taskSubclass.paramSubclass.ResultInfo;
import com.botech.demoalgorithm.domain.taskSubclass.paramSubclass.privateSubclass.TargetInfo;
import com.botech.demoalgorithm.service.SendDataService;
import com.botech.demoalgorithm.service.TaskService;
import com.botech.demoalgorithm.vo.CreateParamVO;
import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 〈一句话功能简述〉<br> 
 * 〈数据发送业务接口实现类〉
 *
 * @author 王呐宇
 * @date 2019/7/20
 * @since 1.0.0
 */
@Service
@Slf4j
public class SendDataServiceImpl implements SendDataService {

    @Value("${service.ip}")
    private String serviceIp;
    @Value("${service.port}")
    private String ServicePort;
    @Value("${image.path.cap-image}")
    private String capImagePath;

    @Autowired
    private FrameHeader frameHeader;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private TargetMapper targetMapper;
    @Autowired
    private TaskMapper taskMapper;

    private File getAnyCaptureFile(String dirPath){
        File dir = new File(dirPath);
        if (dir.isDirectory() && !dir.exists()) {
            dir.mkdirs();
        }
        File[] files = dir.listFiles();
        if (files == null || files.length <= 0) {
            return null;
        }
        Optional<File> anyFile = Arrays.stream(files).skip(new Random().nextInt(files.length))
            .findAny();
        return anyFile.isPresent() ? anyFile.get() : null;
    }

    private TaskDao getAnyRuningTask(){
        List<TaskDao> runingTasks = taskMapper.findAllByIsDeleteAndIsRun(0,1);
        log.debug("正在执行中的任务有：" + runingTasks);
        if (runingTasks == null || runingTasks.isEmpty()) {
            return null;
        }
        Optional<TaskDao> anyRuningVideoTask = runingTasks.stream().filter(x -> {
            CreateParamVO createParamVO = JSON.parseObject(x.getTaskParam(), CreateParamVO.class);
            return createParamVO.getParam().getSource().getSourceType() == 0;
        }).findAny();
        if (anyRuningVideoTask.isPresent()) {
            return anyRuningVideoTask.get();
        }
        Optional<TaskDao> anyRnunigTask = runingTasks.stream()
            .skip(new Random().nextInt(runingTasks.size())).findAny();
        return anyRnunigTask.isPresent() ? anyRnunigTask.get() : null;
    }

    @Override
    public Map<String, Object> sendCaptureData() throws Exception{
        Map<String, Object> result = new HashMap<>();
        File anyCaptureFile = getAnyCaptureFile(capImagePath);
        if (anyCaptureFile == null) {
            log.info("无抓拍图片");
            result.put("result", "error");
            return result;
        }
        TaskDao anyRuningTask = getAnyRuningTask();
        if (anyRuningTask == null) {
            log.info("无执行中任务");
            result.put("result", "error");
            return result;
        }
        LocalDateTime now = LocalDateTime.now();
        Long milliSecond = now.toInstant(ZoneOffset.of("+8")).toEpochMilli();

        JSONObject jsonObject = new JSONObject();
        frameHeader.setTime(milliSecond);
        jsonObject.put("frameHeader", frameHeader);

        CaptureResult captureResult = new CaptureResult();
        List<FaceInfo> faceInfos = new ArrayList<>();
        FaceInfo faceInfo = new FaceInfo();
        faceInfo.setBottom(500 + (int) (Math.random() * 1000));
        faceInfo.setLeft(100 + (int) (Math.random() * 200));
        faceInfo.setObjBottom(100 + (int) (Math.random() * 200));

        faceInfo.setObjImgUrl(
            "http://" + serviceIp + ":" + ServicePort + "/capture/getCapture/" + anyCaptureFile
                .getName());
        faceInfo.setObjLeft(100 + (int) (Math.random() * 200));
        faceInfo.setObjRight(100 + (int) (Math.random() * 200));
        faceInfo.setObjTop(100 + (int) (Math.random() * 200));
        faceInfo.setPitch(10 + (int) (Math.random() * 50) + new Random().nextFloat());
        faceInfo.setQualityScore(new Random().nextFloat());
        faceInfo.setRight(500 + (int) (Math.random() * 1000));
        faceInfo.setRoll(10 + (int) (Math.random() * 50) + new Random().nextFloat());
        faceInfo.setTop(500 + (int) (Math.random() * 1000));
        String subStr = String.valueOf(milliSecond).substring(0, 6);
        faceInfo.setTrackIdx(Integer.valueOf(subStr));
        faceInfo.setYaw(10 + (int) (Math.random() * 50) + new Random().nextFloat());
        faceInfos.add(faceInfo);
        captureResult.setFaceInfos(faceInfos);
        captureResult.setFrameIdx(Integer.valueOf(subStr));
        captureResult.setImgMode(1);
        captureResult.setTime(milliSecond);
        captureResult.setMsTime(milliSecond);
        captureResult.setTaskIdx(anyRuningTask.getTaskId());

        jsonObject.put("captureResult", captureResult);
        jsonObject.put("resultIdx", Integer.valueOf(subStr));
        jsonObject.put("type", 0);
        log.debug("抓拍数据：" + jsonObject.toJSONString());
        log.info("推送抓拍数据");
        if (sendData(jsonObject, anyRuningTask)) {
            result.put("result", "success");
            return result;
        }
        result.put("result", "error");
        return result;
    }

    @Override
    public Map<String, Object> sendRecogData() throws Exception{
        Map<String, Object> result = new HashMap<>();
        File anyCaptureFile = getAnyCaptureFile(capImagePath);
        if (anyCaptureFile == null) {
            log.info("无抓拍图片");
            result.put("result", "error");
            return result;
        }
        TaskDao anyRuningTask = getAnyRuningTask();
        if (anyRuningTask == null) {
            log.info("无执行中任务");
            result.put("result", "error");
            return result;
        }
        LocalDateTime now = LocalDateTime.now();
        Long milliSecond = now.toInstant(ZoneOffset.of("+8")).toEpochMilli();

        JSONObject jsonObject = new JSONObject();
        frameHeader.setTime(milliSecond);
        jsonObject.put("frameHeader", frameHeader);

        RecogResult recogResult = new RecogResult();
        FaceAngle faceAngle = new FaceAngle();
        faceAngle.setPitch(10 + (int) (Math.random() * 50) + new Random().nextFloat());
        faceAngle.setRoll(10 + (int) (Math.random() * 50) + new Random().nextFloat());
        faceAngle.setYaw(10 + (int) (Math.random() * 50) + new Random().nextFloat());
        recogResult.setFaceAngle(faceAngle);
        FaceRect faceRect = new FaceRect();
        faceRect.setBottom(100 + (int) (Math.random() * 200));
        faceRect.setLeft(100 + (int) (Math.random() * 200));
        faceRect.setRight(100 + (int) (Math.random() * 200));
        faceRect.setTop(100 + (int) (Math.random() * 200));
        recogResult.setFaceRect(faceRect);
        recogResult.setFeature("NO_FEATURE");
        String subStr = String.valueOf(milliSecond).substring(0, 6);
        recogResult.setFrameIdx(Integer.valueOf(subStr));
        recogResult.setImgHeight(100 + (int) (Math.random() * 200));
        recogResult.setImgMode(0);
        recogResult.setImgUrl(
            "http://" + serviceIp + ":" + ServicePort + "/capture/getCapture/" + anyCaptureFile
                .getName());
        recogResult.setImgWidth(100 + (int) (Math.random() * 200));
        recogResult.setMsTime(milliSecond);
        recogResult.setQualityScore(new Random().nextFloat());

        CreateParamVO createParamVO = JSON
            .parseObject(anyRuningTask.getTaskParam(), CreateParamVO.class);

        List<Similar> similars = new ArrayList<>();
        Similar similar = new Similar();
        List<TargetInfo> targets = createParamVO.getParam().getPrivate().getTargets();
        if (targets == null || targets.isEmpty()) {
            log.info("未找到布控库");
            result.put("result", "error");
            return result;
        }
        Optional<TargetInfo> anyTargetInfo = targets.stream().skip(new Random().nextInt(targets.size()))
            .findAny();
        if(!anyTargetInfo.isPresent() ){
            log.info("未找到布控库");
            result.put("result", "error");
            return result;
        }
        TargetInfo targetInfo = anyTargetInfo.get();
        TargetDao byDbNameAndIsDelete = targetMapper
            .getByDbNameAndIsDelete(targetInfo.getDbId(), 0);
        if (byDbNameAndIsDelete == null) {
            log.info("目标库不存在");
            result.put("result", "error");
            return result;
        }
        List<ImageDao> allByTargetIdAndIsDelete = imageMapper
            .findAllByTargetIdAndIsDelete(byDbNameAndIsDelete.getDbId(), 0);
        if (allByTargetIdAndIsDelete == null || allByTargetIdAndIsDelete.isEmpty()) {
            log.info("目标库中没有图片");
            result.put("result", "error");
            return result;
        }

        similar.setDbId(targetInfo.getDbId());
        List<Users> usersList = new ArrayList<>();
        Users users = new Users();
        users.setScore(new Random().nextFloat());
        users.setUser_idx(allByTargetIdAndIsDelete.stream()
            .skip(new Random().nextInt(allByTargetIdAndIsDelete.size())).findAny().get()
            .getImageId());
        usersList.add(users);
        similar.setUsers(usersList);
        similars.add(similar);
        recogResult.setSimilars(similars);
        recogResult.setTaskIdx(anyRuningTask.getTaskId());
        recogResult.setTime(milliSecond);
        recogResult.setTrackIdx(Integer.valueOf(subStr));

        jsonObject.put("recogResult", recogResult);
        jsonObject.put("resultIdx", Integer.valueOf(subStr));
        jsonObject.put("type", 1);
        log.debug("识别数据：" + jsonObject.toJSONString());
        log.info("推送识别数据");
        if (sendData(jsonObject, anyRuningTask)) {
            result.put("result", "success");
            return result;
        }
        result.put("result", "error");
        return result;
    }

    @Override
    public Map<String, Object> sendAttrData() throws Exception{
        Map<String, Object> result = new HashMap<>();
        File anyCaptureFile = getAnyCaptureFile(capImagePath);
        if (anyCaptureFile == null) {
            log.info("无抓拍图片");
            result.put("result", "error");
            return result;
        }
        TaskDao anyRuningTask = getAnyRuningTask();
        if (anyRuningTask == null) {
            log.info("无执行中任务");
            result.put("result", "error");
            return result;
        }
        LocalDateTime now = LocalDateTime.now();
        Long milliSecond = now.toInstant(ZoneOffset.of("+8")).toEpochMilli();

        JSONObject jsonObject = new JSONObject();
        frameHeader.setTime(milliSecond);
        jsonObject.put("frameHeader", frameHeader);

        Attributes attributes = new Attributes();
        attributes.setAge(1 + (int) (Math.random() * 100));
        int i = (int) (Math.random() * 100);
        attributes.setBeard(i % 2 == 0 ? 0 : 1);
        attributes.setEmotion(i % 3 == 0 ? 0 : 1);
        attributes.setGender(i % 4 == 0 ? 0 : 1);
        attributes.setGlass(i % 5 == 0 ? 0 : 1);
        attributes.setMask(i % 6 == 0 ? 0 : 1);
        attributes.setRace(i % 7 == 0 ? 0 : 1);
        AttrResult attrResult = new AttrResult();
        attrResult.setAttributes(attributes);
        String subStr = String.valueOf(milliSecond).substring(0, 6);
        attrResult.setFrameIdx(Integer.valueOf(subStr));
        attrResult.setMsTime(milliSecond);
        attrResult.setTaskIdx(anyRuningTask.getTaskId());
        attrResult.setTime(milliSecond);
        attrResult.setTrackIdx(Integer.valueOf(subStr));
        jsonObject.put("attrResult", attrResult);
        jsonObject.put("resultIdx", Integer.valueOf(subStr));
        jsonObject.put("type", 2);
        log.debug("属性数据：" + jsonObject.toJSONString());
        log.info("推送属性数据");
        if (sendData(jsonObject, anyRuningTask)) {
            result.put("result", "success");
            return result;
        }
        result.put("result", "error");
        return result;
    }

    @Override
    public Map<String, Object> sendEndData() throws Exception{
        Map<String, Object> result = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        List<TaskDao> runingTasks = taskMapper.findAllByIsDeleteAndIsRun(0,1);
        log.debug("正在执行中的任务有：" + runingTasks);
        if (runingTasks == null || runingTasks.isEmpty()) {
            log.info("无执行中任务");
            result.put("result", "error");
            return result;
        }
        Optional<TaskDao> anyRuningVideoTask = runingTasks.stream().filter(x -> {
            CreateParamVO createParamVO = JSON.parseObject(x.getTaskParam(), CreateParamVO.class);
            return createParamVO.getParam().getSource().getSourceType() == 0;
        }).findAny();
        if (!anyRuningVideoTask.isPresent()) {
            log.info("无执行中的视频分析任务");
            result.put("result", "error");
            return result;
        }
        jsonObject.put("type", 4);
        jsonObject.put("taskIdx", anyRuningVideoTask.get().getTaskId());
        log.info("推送結束标识");
        if (sendData(jsonObject, anyRuningVideoTask.get())) {
            result.put("result", "success");
            return result;
        }
        result.put("result", "error");
        return result;
    }

    private boolean sendData(JSONObject jsonObject, TaskDao anyRuningTask) {
        CreateParamVO createParamVO = JSON
            .parseObject(anyRuningTask.getTaskParam(), CreateParamVO.class);
        List<ResultInfo> resultInfos = createParamVO.getParam().getResult();
        if (resultInfos == null || resultInfos.isEmpty()) {
            log.info("未找到推送地址");
            return false;
        }
        Optional<ResultInfo> anyResultInfo = resultInfos.stream().findAny();
        if (anyResultInfo.isPresent()) {
            ResultInfo resultInfo = anyResultInfo.get();
            if (resultInfo != null && resultInfo.getURL() != null) {
                String body = restTemplate
                    .postForEntity(resultInfo.getURL(), jsonObject, String.class).getBody();
                log.info("数据推送地址：" + resultInfo.getURL());
                log.info("数据推送结果：" + body);
                return true;
            }
        }
        return false;
    }
}