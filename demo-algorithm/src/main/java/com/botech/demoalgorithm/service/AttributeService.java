/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: AttributeService
 * Author:   王呐宇
 * Date:     2019/7/18 22:14
 * Description: 属性业务层接口
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.service;

import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 * 〈一句话功能简述〉<br> 
 * 〈属性业务层接口〉
 *
 * @author 王呐宇
 * @date 2019/7/18
 * @since 1.0.0
 */
public interface AttributeService {

    Map<String,Object> getAttribute(MultipartFile file) throws Exception;
}
