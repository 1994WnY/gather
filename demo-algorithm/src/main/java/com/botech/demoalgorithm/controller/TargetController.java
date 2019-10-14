/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: TargetController
 * Author:   王呐宇
 * Date:     2019/7/18 11:58
 * Description: 目标库业务控制器
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.controller;

import com.botech.demoalgorithm.service.TargetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br> 
 * 〈目标库业务控制器〉
 *
 * @author 王呐宇
 * @date 2019/7/18
 * @since 1.0.0
 */
@Api(tags = "目标库相关接口")
@RestController
@RequestMapping(value = "verify/target")
public class TargetController {

    @Autowired
    private TargetService targetService;

    @ApiOperation("创建目标库")
    @PostMapping(value = "add")
    public Map<String,Object> addTarget(
        @RequestParam(value = "dbName", required = true) String dbName,
        @RequestParam(value = "size", required = true) int size) throws Exception {
        return targetService.createTarget(dbName, size);
    }

    @ApiOperation("读取目标库信息")
    @PostMapping(value = "gets")
    public Map<String,Object> getTargets() throws Exception {
        return targetService.getTargets();
    }

    @ApiOperation("清空目标库")
    @PostMapping(value = "clear")
    public Map<String, Object> clearTarget(@RequestParam(required = true) String dbName)
        throws Exception {
        return targetService.clearTarget(dbName);
    }

    @ApiOperation("删除目标库")
    @PostMapping(value = "deletes")
    public Map<String, Object> deleteTarget(@RequestParam(required = true) String dbName)
        throws Exception {
        return targetService.deleteTarget(dbName);
    }


}