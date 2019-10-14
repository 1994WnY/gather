package com.botech.demoalgorithm;

import com.alibaba.fastjson.JSONObject;
import com.botech.demoalgorithm.domain.FrameHeader;
import com.botech.demoalgorithm.domain.attribute.AttrResult;
import com.botech.demoalgorithm.domain.attribute.Attributes;
import com.botech.demoalgorithm.domain.capture.CaptureResult;
import com.botech.demoalgorithm.domain.capture.FaceInfo;
import com.botech.demoalgorithm.domain.distinguish.FaceAngle;
import com.botech.demoalgorithm.domain.distinguish.FaceRect;
import com.botech.demoalgorithm.domain.distinguish.RecogResult;
import com.botech.demoalgorithm.domain.distinguish.Similar;
import com.botech.demoalgorithm.domain.distinguish.Users;
import com.botech.demoalgorithm.vo.CreateParamVO;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoAlgorithmApplicationTests {

    @Autowired
    private CreateParamVO createParamVO;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test1(){

        //System.out.println("taskInfoVO："+createParamVO);
        System.out.println(new Random().nextInt(1));
    }

    @Test
    public void captureData(){
        JSONObject jsonObject = new JSONObject();
        FrameHeader frameHeader = new FrameHeader();
        frameHeader.setData(0);
        frameHeader.setDataLen(0);
        frameHeader.setFlag(0);
        frameHeader.setHeight(1088);
        frameHeader.setIndex(632221498L);
        frameHeader.setRebackInfo(0);
        frameHeader.setTime(1796473347L);
        frameHeader.setWidth(1920);
        jsonObject.put("frameHeader",frameHeader);

        CaptureResult captureResult = new CaptureResult();
        List<FaceInfo> faceInfos = new ArrayList<>();
        FaceInfo faceInfo = new FaceInfo();
        faceInfo.setBottom(838);
        faceInfo.setLeft(868);
        faceInfo.setObjBottom(132);
        faceInfo.setObjImgUrl("http://172.16.1.34:18080/g1/M01/01/B5/rBABIl0tJq2IB4DYAAAarpoFUu0AACq1QNpJhcAABrG426.jpg");
        faceInfo.setObjLeft(57);
        faceInfo.setObjRight(113);
        faceInfo.setObjTop(76);
        faceInfo.setPitch(20.211967468261719F);
        faceInfo.setQualityScore(0.3468436598777771F);
        faceInfo.setRight(924);
        faceInfo.setRoll(-16.95008659362793F);
        faceInfo.setTop(782);
        faceInfo.setTrackIdx(6778100);
        faceInfo.setYaw(20.09080696105957F);
        faceInfos.add(faceInfo);
        captureResult.setFaceInfos(faceInfos);
        captureResult.setFrameIdx(632221498);
        captureResult.setImgMode(1);
        captureResult.setTime(1563240109L);
        captureResult.setMsTime(1563240109922L);
        captureResult.setTaskIdx("201809211137450001000095020");
        jsonObject.put("captureResult",captureResult);
        jsonObject.put("resultIdx",3874286);
        jsonObject.put("type",0);

        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void attrData(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("resultIdx",1778008);
        jsonObject.put("type",2);

        FrameHeader frameHeader = new FrameHeader();
        frameHeader.setData(0);
        frameHeader.setDataLen(0);
        frameHeader.setFlag(0);
        frameHeader.setHeight(1088);
        frameHeader.setIndex(632221448L);
        frameHeader.setRebackInfo(0);
        frameHeader.setTime(1796471345L);
        frameHeader.setWidth(1920);
        jsonObject.put("frameHeader",frameHeader);

        Attributes attributes = new Attributes();
        attributes.setAge(19);
        attributes.setBeard(0);
        attributes.setEmotion(0);
        attributes.setGender(1);
        attributes.setGlass(1);
        attributes.setMask(0);
        attributes.setRace(0);
        AttrResult attrResult = new AttrResult();
        attrResult.setAttributes(attributes);
        attrResult.setFrameIdx(632221448);
        attrResult.setMsTime(1563240109219L);
        attrResult.setTaskIdx("201809211137450001000095020");
        attrResult.setTime(1563240109L);
        attrResult.setTrackIdx(6778101);
        jsonObject.put("attrResult",attrResult);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void recogData(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("resultIdx",3556018);
        jsonObject.put("type",1);

        FrameHeader frameHeader = new FrameHeader();
        frameHeader.setData(0);
        frameHeader.setDataLen(0);
        frameHeader.setFlag(0);
        frameHeader.setHeight(1088);
        frameHeader.setIndex(632221480L);
        frameHeader.setRebackInfo(0);
        frameHeader.setTime(1796472627L);
        frameHeader.setWidth(1920);
        jsonObject.put("frameHeader",frameHeader);

        RecogResult recogResult = new RecogResult();
        FaceAngle faceAngle = new FaceAngle();
        faceAngle.setPitch(18.78514289855957F);
        faceAngle.setRoll(-12.581486701965332F);
        faceAngle.setYaw(23.884513854980469F);
        recogResult.setFaceAngle(faceAngle);
        FaceRect faceRect = new FaceRect();
        faceRect.setBottom(146);
        faceRect.setLeft(63);
        faceRect.setRight(125);
        faceRect.setTop(84);
        recogResult.setFaceRect(faceRect);
        recogResult.setFeature("NO_FEATURE");
        recogResult.setFrameIdx(632221480);
        recogResult.setImgHeight(230);
        recogResult.setImgMode(0);
        recogResult.setImgUrl("http://172.16.1.34:18080/g1/M00/01/B5/rBABIl0tJqyIazrCAAAfk_LzOGoAACq0wXVvecAAB-r046.jpg");
        recogResult.setImgWidth(188);
        recogResult.setMsTime(1563240109261L);
        recogResult.setQualityScore(0.31321153044700623F);

        List<Similar> similars = new ArrayList<>();
        Similar similar = new Similar();
        similar.setDbId("0c3109c1a105462d96f855de63babe7f");
        List<Users> usersList = new ArrayList<>();
        Users users = new Users();
        users.setScore(0.6425514817237854F);
        users.setUser_idx("696294e722d54bec9e9bcb8c8a89848f");
        usersList.add(users);
        similar.setUsers(usersList);
        similars.add(similar);
        recogResult.setSimilars(similars);
        recogResult.setTaskIdx("201809211137450001000095020");
        recogResult.setTime(1563240109L);
        recogResult.setTrackIdx(6778100);

        jsonObject.put("recogResult",recogResult);
        System.out.println(jsonObject.toJSONString());
    }

}
