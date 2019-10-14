/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: AttributeServiceImpl
 * Author:   王呐宇
 * Date:     2019/7/18 22:15
 * Description: 属性业务层接口实现类
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.service.impl;

import com.botech.demoalgorithm.service.AttributeService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 〈一句话功能简述〉<br> 
 * 〈属性业务层接口实现类〉
 *
 * @author 王呐宇
 * @date 2019/7/18
 * @since 1.0.0
 */
@Service
public class AttributeServiceImpl implements AttributeService {

    @Override
    public Map<String, Object> getAttribute(MultipartFile file) throws Exception {
        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        Map<String, Object> attribute = new HashMap<>();
        attribute.put("age", 1 + (int) (Math.random() * 100));
        int i = (int) (Math.random() * 100);
        attribute.put("gender", i % 2 == 0 ? 0 : 1);
        attribute.put("attractive", (int) (Math.random() * 100));
        attribute.put("eyeglass", i % 3 == 0 ? 0 : 1);
        attribute.put("sunglass", i % 4 == 0 ? 0 : 1);
        attribute.put("smile", i % 5 == 0 ? 0 : 1);
        attribute.put("mask", i % 6 == 0 ? 0 : 1);
        attribute.put("race", i % 7 == 0 ? 0 : 1);
        attribute.put("eyeOpen", i % 8 == 0 ? 0 : 1);
        attribute.put("mouthOpen", i % 9 == 0 ? 0 : 1);
        attribute.put("beard", i % 10 == 0 ? 0 : 1);
        result.put("data", attribute);
        return result;
    }
}