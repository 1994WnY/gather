/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: SendDataController
 * Author:   王呐宇
 * Date:     2019/7/20 23:06
 * Description: 数据推送业务接口
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.controller;

import com.botech.demoalgorithm.service.SendDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br> 
 * 〈数据推送业务接口〉
 *
 * @author 王呐宇
 * @date 2019/7/20
 * @since 1.0.0
 */
@Api(tags = "数据推送业务接口")
@RestController
@RequestMapping(value = "send")
public class SendDataController {

    @Autowired
    private SendDataService sendDataService;

    @ApiOperation("推送抓拍数据")
    @PostMapping(value = "sendCaptureData")
    public Map<String,Object> sendCaptureData() throws Exception{
        return sendDataService.sendCaptureData();
    }

    @ApiOperation("推送告警数据")
    @PostMapping(value = "sendRecogData")
    public Map<String,Object> sendRecogData() throws Exception{
        Map<String, Object> result = new HashMap<>();
        sendDataService.sendRecogData();
        sendDataService.sendAttrData();
        result.put("result", "success");
        return result;
    }

//    @ApiOperation("推送属性数据")
//    @PostMapping(value = "sendAttrData")
//    public Map<String,Object> sendAttrData() throws Exception{
//        return sendDataService.sendAttrData();
//    }

}