/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: HotRegionInfo
 * Author:   王呐宇
 * Date:     2019/7/16 14:16
 * Description:  
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.domain.taskSubclass.paramSubclass.privateSubclass.algSubclass;

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
public class HotRegionInfo implements Serializable {

    private static final long serialVersionUID = 2550401557904301891L;

    private Integer index;
    private Integer pointX;
    private Integer pointY;

}