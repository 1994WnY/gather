/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: TargetDao
 * Author:   王呐宇
 * Date:     2019/7/16 14:21
 * Description:  
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.domain.taskSubclass.paramSubclass.privateSubclass;

import java.io.Serializable;
import lombok.Data;

/**
 * 〈一句话功能简述〉<br> 
 * 〈 〉
 *
 * @author 王呐宇
 * @date 2019/7/16
 * @since 1.0.0
 */
@Data
public class TargetInfo implements Serializable {

    private static final long serialVersionUID = 3136318274476073089L;

    private String dbId;
    private Double score;
}