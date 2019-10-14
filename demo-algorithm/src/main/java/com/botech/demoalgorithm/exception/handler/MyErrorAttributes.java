/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: MyErrorAttributes
 * Author:   王呐宇
 * Date:     2019/7/18 15:30
 * Description: 自定义错误属性
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.exception.handler;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 〈一句话功能简述〉<br> 
 * 〈自定义错误属性〉
 *
 * @author 王呐宇
 * @date 2019/7/18
 * @since 1.0.0
 */
//@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

    @Autowired
    private ErrorAttributes errorAttributes;

    private Map<String, Object> getErrorAttributes(HttpServletRequest request,
        boolean includeStackTrace) {
        ServletWebRequest servletWebRequest = new ServletWebRequest(request);
        Map<String, Object> errorAttributes = this.errorAttributes
            .getErrorAttributes(servletWebRequest, includeStackTrace);
        errorAttributes.put("company", "haibo");
        errorAttributes.put("result", request.getAttribute("result"));
        errorAttributes.put("errorMessage", request.getAttribute("errorMessage"));
        return errorAttributes;
    }

}