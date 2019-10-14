/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: ImageController
 * Author:   王呐宇
 * Date:     2019/7/18 19:40
 * Description: 图片业务控制器
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.controller;

import com.botech.demoalgorithm.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 〈一句话功能简述〉<br> 
 * 〈图片业务控制器〉
 *
 * @author 王呐宇
 * @date 2019/7/18
 * @since 1.0.0
 */
@Api(tags = "图片相关接口")
@RestController
@RequestMapping(value = "verify/face")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @ApiOperation("人脸图片检测、质量评分")
    @PostMapping(value = "detectAndQuality",consumes = "multipart/form-data")
    public Map<String, Object> detectAndQuality(
        @RequestParam(required = true, value = "imageData") MultipartFile file)
        throws Exception {
        return imageService.detectAndQuality(file);
    }

    @ApiOperation("同步人脸图片入库")
    @PostMapping(value = "synAdd",consumes = "multipart/form-data")
    public Map<String, Object> synAdd(
        @RequestParam(required = true, value = "imageDatas") MultipartFile[] files,
        @RequestParam(required = true, value = "dbName") String dbName, Integer getFeature,
        Integer qualityThreshold) throws Exception {
        return imageService.asyncAdd(files, dbName, getFeature, qualityThreshold);
    }

    @ApiOperation("获取单张人脸图片")
    @GetMapping(value = "gets")
    void getImage(String imageId, HttpServletResponse response) throws Exception{
        imageService.getImage(imageId,response);
    }

    @ApiOperation("删除单张人脸图片")
    @PostMapping(value = "deletes")
    public Map<String, Object> deleteImage(String dbName, String imageId) throws Exception {
        return imageService.deleteImage(dbName, imageId);
    }

    @ApiOperation("1:1人脸比对")
    @PostMapping(value = "verification",consumes = "multipart/form-data")
    public Map<String, Object> verification(
        @RequestParam(required = true, value = "imageOne") MultipartFile firstFile,
        @RequestParam(required = true, value = "imageTwo") MultipartFile secondFile) throws Exception{
        return imageService.verification(firstFile, secondFile);
    }

    @ApiOperation("1:N人脸搜索")
    @PostMapping(value = "search",consumes = "multipart/form-data")
    public Map<String,Object> search(
        @RequestParam(required = true, value = "imageData") MultipartFile file,
        @RequestParam(required = true) String dbName, @RequestParam(required = true) int topNum,
        @RequestParam(required = true) double score) throws Exception {
        return imageService.search(file,dbName,topNum,score);
    }
}