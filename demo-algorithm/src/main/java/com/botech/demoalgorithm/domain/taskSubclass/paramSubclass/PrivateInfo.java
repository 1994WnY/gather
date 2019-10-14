/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: PrivateInfo
 * Author:   王呐宇
 * Date:     2019/7/16 14:11
 * Description: 私有参数信息
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.domain.taskSubclass.paramSubclass;

import com.botech.demoalgorithm.domain.taskSubclass.paramSubclass.privateSubclass.AlgParamInfo;
import com.botech.demoalgorithm.domain.taskSubclass.paramSubclass.privateSubclass.TargetInfo;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * 〈一句话功能简述〉<br> 
 * 〈私有参数信息〉
 *
 * @author 王呐宇
 * @date 2019/7/16
 * @since 1.0.0
 */
@Data
public class PrivateInfo implements Serializable {

    private static final long serialVersionUID = 5866552014865986687L;

    private List<TargetInfo> targets;
    private AlgParamInfo algParam;

}