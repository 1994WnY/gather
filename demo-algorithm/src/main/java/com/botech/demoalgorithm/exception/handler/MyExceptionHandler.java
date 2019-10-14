/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: MyExceptionHandler
 * Author:   王呐宇
 * Date:     2019/7/18 14:28
 * Description: 异常信息处理类
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.exception.handler;

import com.botech.demoalgorithm.exception.DbAlreadyExistException;
import com.botech.demoalgorithm.exception.DbNotExistException;
import com.botech.demoalgorithm.exception.ImageNotExistException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 〈一句话功能简述〉<br> 
 * 〈异常信息处理类〉
 *
 * @author 王呐宇
 * @date 2019/7/18
 * @since 1.0.0
 */
@ControllerAdvice
@Slf4j
public class MyExceptionHandler {

//    @ExceptionHandler(DbNotExistException.class)
    @ExceptionHandler
    @ResponseBody
    public Map<String,Object> handleException(Exception e, HttpServletRequest request){
        //传入我们自己的错误状态码  4xx 5xx，否则就不会进入定制错误页面的解析流程
        Map<String,Object> result = new HashMap<>();
        result.put("result", "error");
        if(e instanceof DbNotExistException){
            request.setAttribute("javax.servlet.error.status_code",500);
            log.error("DB_NOT_EXIST");
            result.put("errorMessage", "DB_NOT_EXIST");
        }else if(e instanceof DbAlreadyExistException){
            request.setAttribute("javax.servlet.error.status_code",500);
            log.error("DB_ALREADY_EXIST");
            result.put("errorMessage", "DB_ALREADY_EXIST");
        }else if (e instanceof ImageNotExistException){
            request.setAttribute("javax.servlet.error.status_code",500);
            log.error("IMAGE_NOT_EXIST");
            result.put("errorMessage", "IMAGE_NOT_EXIST");
        }else {
            e.printStackTrace();
            log.error("系统异常：" + e.toString());
            result.put("errorMessage", e.getMessage());
        }
        return result;
    }
}