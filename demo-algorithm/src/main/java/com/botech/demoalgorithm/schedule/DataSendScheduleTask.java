/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: DataSendScheduleTask
 * Author:   王呐宇
 * Date:     2019/7/20 15:15
 * Description: 数据推送定时任务
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.schedule;

import com.botech.demoalgorithm.service.SendDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br> 
 * 〈数据推送定时任务〉
 *
 * @author 王呐宇
 * @date 2019/7/20
 * @since 1.0.0
 */
@Component
@Slf4j
public class DataSendScheduleTask {

    @Autowired
    private SendDataService sendDataService;

    @Scheduled(cron = "*/10 * * * * ?")
    public void sendCaptureData(){
        try {
            int random = (int) (Math.random() * 10);
            if (random == 0) {
                sendDataService.sendCaptureData();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("抓拍数据推送异常：" + e.toString());
        }

    }

    @Scheduled(cron = "*/10 * * * * ?")
    public void sendRecogData(){
        try {
            int random = (int) (Math.random() * 20);
            if (random == 1) {
                sendDataService.sendRecogData();
                sendDataService.sendAttrData();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("告警数据推送异常：" + e.toString());
        }

    }

//    @Scheduled(cron = "*/10 * * * * ?")
//    public void sendAttrData(){
//        int random = (int) (Math.random() * 20);
//        if(random == 2){
//            sendDataService.sendAttrData();
//        }
//    }

    @Scheduled(cron = "0 */5 * * * ?")
    public void sendEndData() {
        try {
            sendDataService.sendEndData();
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("抓拍数据推送异常：" + e.toString());
        }

    }
}