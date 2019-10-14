/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: TaskParam
 * Author:   王呐宇
 * Date:     2019/7/16 14:07
 * Description: 任务参数
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.domain.taskSubclass;

import com.botech.demoalgorithm.domain.taskSubclass.paramSubclass.PrivateInfo;
import com.botech.demoalgorithm.domain.taskSubclass.paramSubclass.ResultInfo;
import com.botech.demoalgorithm.domain.taskSubclass.paramSubclass.SourceInfo;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * 〈一句话功能简述〉<br> 
 * 〈任务参数〉
 *
 * @author 王呐宇
 * @date 2019/7/16
 * @since 1.0.0
 */
@Data
public class TaskParam implements Serializable {

    private static final long serialVersionUID = -3600262779096184131L;

    private SourceInfo Source;
    private List<ResultInfo> Result;
    private PrivateInfo Private;
}