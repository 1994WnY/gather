package com.botech.demoalgorithm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试相关接口")
@Slf4j
@RequestMapping("test")
@RestController
public class TestController {

    @ApiOperation("测试接口1")
    @GetMapping("testAuthentication")
    public Map<String, Object> testAuthentication() {
        Map<String, Object> result = new HashMap<>();
        result.put("returnCode", 0);
        return result;
    }
}
