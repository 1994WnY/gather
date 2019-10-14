/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: AttributeController
 * Author:   王呐宇
 * Date:     2019/7/18 22:29
 * Description: 属性业务处理器
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.controller;

import com.botech.demoalgorithm.service.AttributeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 〈一句话功能简述〉<br> 
 * 〈属性业务处理器〉
 *
 * @author 王呐宇
 * @date 2019/7/18
 * @since 1.0.0
 */
@Api(tags = "属性相关接口")
@RestController
@RequestMapping(value = " verify/attribute")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @ApiOperation("单张图片人脸属性")
    @PostMapping(value = "gets",consumes = "multipart/form-data")
    public Map<String,Object> getAttribute(MultipartFile file) throws Exception{
        return attributeService.getAttribute(file);
    }

}