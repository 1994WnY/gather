/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: TargetService
 * Author:   王呐宇
 * Date:     2019/7/18 11:30
 * Description: 目标库业务逻辑接口
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.service;

import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈目标库业务逻辑接口〉
 *
 * @author 王呐宇
 * @date 2019/7/18
 * @since 1.0.0
 */
public interface TargetService {

    Map<String,Object> createTarget(String dbName,int size) throws Exception;

    Map<String,Object> getTargets() throws Exception;

    Map<String,Object> clearTarget(String dbName) throws Exception;

    Map<String,Object> deleteTarget(String dbName) throws Exception;

}
