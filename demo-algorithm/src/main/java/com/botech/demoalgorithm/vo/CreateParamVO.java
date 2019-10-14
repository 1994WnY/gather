/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: TaskInfoVO
 * Author:   王呐宇
 * Date:     2019/7/16 13:54
 * Description: 任务信息接收
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
 * 〈任务信息接收〉
 *
 * @author 王呐宇
 * @date 2019/7/16
 * @since 1.0.0
 */
@Component
@ConfigurationProperties(prefix = "task.create")
@Data
public class CreateParamVO implements Serializable {

    private static final long serialVersionUID = 9207648972813256324L;

    private Integer taskType;
    private TaskParam param;
}