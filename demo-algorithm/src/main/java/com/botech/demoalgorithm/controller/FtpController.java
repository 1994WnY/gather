/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: FtpController
 * Author:   王呐宇
 * Date:     2019/7/19 1:38
 * Description: FTP业务控制器
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br> 
 * 〈FTP业务控制器〉
 *
 * @author 王呐宇
 * @date 2019/7/19
 * @since 1.0.0
 */
@Api(tags = "Ftp相关接口")
@RestController
@RequestMapping(value = "Ftp")
public class FtpController {

    @Value("${ftp.ip}")
    private String ftpIp;
    @Value("${ftp.port}")
    private String ftpPort;
    @Value("${ftp.user}")
    private String ftpUser;
    @Value("${ftp.passwd}")
    private String ftpPasswd;

    @ApiOperation("查询ftp上传地址信息")
    @GetMapping(value = "QueryFtpAddr")
    public Map<String, Object> getVersion() {
        Map<String, Object> result = new HashMap<>();
        result.put("returnCode", 0);
        result.put("ftpIp", ftpIp);
        result.put("ftpPort", ftpPort);
        result.put("user", ftpUser);
        result.put("passwd", ftpPasswd);
        return result;
    }
}