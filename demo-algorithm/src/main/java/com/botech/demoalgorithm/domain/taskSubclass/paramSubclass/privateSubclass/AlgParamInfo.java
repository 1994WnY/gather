/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: AlgParamInfo
 * Author:   王呐宇
 * Date:     2019/7/16 14:14
 * Description:  
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.domain.taskSubclass.paramSubclass.privateSubclass;

import com.botech.demoalgorithm.domain.taskSubclass.paramSubclass.privateSubclass.algSubclass.HotRegionInfo;
import java.io.Serializable;
import java.util.List;
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
public class AlgParamInfo implements Serializable {

    private static final long serialVersionUID = -1058235017109543502L;

    private Integer minDetectWidth;
    private Integer minDetectHeight;
    private Integer maxDetectWidth;
    private Integer maxDetectHeight;
    private List<HotRegionInfo> hotRegion;

}