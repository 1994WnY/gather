/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: OtherController
 * Author:   王呐宇
 * Date:     2019/7/19 1:25
 * Description: 其他业务控制器
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
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br> 
 * 〈其他业务控制器〉
 *
 * @author 王呐宇
 * @date 2019/7/19
 * @since 1.0.0
 */
@Api(tags = "其他业务接口")
@RestController
public class OtherController {

    @Value("${target.size.total-size}")
    private String dbMaxImageCount;
    @Value("${service.version}")
    private String version;

    @ApiOperation("获取库容量信息")
    @GetMapping(value = "verify/detail")
    public Map<String, Object> getDetail() {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("dbMaxImageCount", Integer.valueOf(dbMaxImageCount));
        result.put("result", "success");
        result.put("data", data);
        return result;
    }

    @ApiOperation("查询版本号信息")
    @GetMapping(value = "verify/version")
    public Map<String, Object> getVersion() {
        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("version", version);
        return result;
    }
}