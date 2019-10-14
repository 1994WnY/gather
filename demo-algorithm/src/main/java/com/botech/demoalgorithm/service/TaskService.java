/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: TaskService
 * Author:   王呐宇
 * Date:     2019/7/16 16:18
 * Description: 任务业务处理层
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.service;

import com.botech.demoalgorithm.vo.CreateParamVO;
import com.botech.demoalgorithm.vo.UpdateParamVO;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈任务业务处理层〉
 *
 * @author 王呐宇
 * @date 2019/7/16
 * @since 1.0.0
 */
public interface TaskService {

    Map<String,Object> createTask(CreateParamVO createParamVO);

    Map<String,Object> deleteTask(String taskID);

    Map<String,Object> UpdateParam(UpdateParamVO updateParamVO);

    Map<String,Object> QueryResource();

    Map<String,Object> getTaskInfo(String taskID);

}