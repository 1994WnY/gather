/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: SendDataService
 * Author:   王呐宇
 * Date:     2019/7/20 16:30
 * Description: 数据发送业务接口
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.service;

import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈数据发送业务接口〉
 *
 * @author 王呐宇
 * @date 2019/7/20
 * @since 1.0.0
 */
public interface SendDataService {

    Map<String,Object> sendCaptureData() throws Exception;

    Map<String,Object> sendRecogData() throws Exception;

    Map<String,Object> sendAttrData() throws Exception;

    Map<String,Object> sendEndData() throws Exception;
}
