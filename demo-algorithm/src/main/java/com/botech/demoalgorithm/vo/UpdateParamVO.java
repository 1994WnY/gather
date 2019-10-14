/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: UpdateParamVO
 * Author:   王呐宇
 * Date:     2019/7/16 17:31
 * Description: 任务修改参数
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.vo;

import com.botech.demoalgorithm.domain.taskSubclass.TaskParam;
import java.io.Serializable;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br> 
 * 〈任务修改参数〉
 *
 * @author 王呐宇
 * @date 2019/7/16
 * @since 1.0.0
 */
@Component
@ConfigurationProperties(prefix = "task.update")
@Data
public class UpdateParamVO implements Serializable {

    private static final long serialVersionUID = -7363342709472345519L;

    private Integer taskType;
    private String taskID;
    private Integer isFullUpdate;
    private TaskParam fullParam;
}