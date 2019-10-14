/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: FeatureController
 * Author:   王呐宇
 * Date:     2019/7/18 21:54
 * Description: 特征业务控制器
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.controller;

import com.botech.demoalgorithm.service.FeatureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 〈一句话功能简述〉<br> 
 * 〈特征业务控制器〉
 *
 * @author 王呐宇
 * @date 2019/7/18
 * @since 1.0.0
 */
@Api(tags = "特征相关接口")
@RestController
@RequestMapping(value = "verify/feature")
public class FeatureController {

    @Autowired
    private FeatureService featureService;

    @ApiOperation("获取单张人脸特征")
    @PostMapping(value = "gets",consumes = "multipart/form-data")
    public Map<String,Object> getFeature(@RequestParam(required = true,value = "imageData") MultipartFile file) throws Exception{
        return featureService.getFeature(file);
    }

    @ApiOperation("人脸特征导入目标库")
    @PostMapping(value = "synAdd")
    public Map<String, Object> asyncAdd(String dbName, String feature) throws Exception{
        return featureService.asyncAdd(dbName,feature);
    }

    @ApiOperation("1:1人脸比对")
    @PostMapping(value = "verification")
    public Map<String, Object> verification(String featureOne, String featureTwo) throws Exception{
        return featureService.verification(featureOne, featureTwo);
    }

    @ApiOperation("1:N人脸搜索")
    @PostMapping(value = "search")
    public Map<String, Object> search(@RequestParam(required = true) String feature,
        @RequestParam(required = true) String dbName, @RequestParam(required = true) int topNum,
        @RequestParam(required = true) double score) throws Exception {
        return featureService.search(feature, dbName, topNum, score);
    }
}