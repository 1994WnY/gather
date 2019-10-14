/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: ResultInfo
 * Author:   王呐宇
 * Date:     2019/7/16 13:58
 * Description: 推送信息
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.domain.taskSubclass.paramSubclass;

import java.io.Serializable;
import lombok.Data;

/**
 * 〈一句话功能简述〉<br> 
 * 〈推送信息〉
 *
 * @author 王呐宇
 * @date 2019/7/16
 * @since 1.0.0
 */
@Data
public class ResultInfo implements Serializable {

    private static final long serialVersionUID = 3191481505172098932L;

    private Integer Index;
    private Integer ProtocolType;
    private String URL;
    private String IP;
    private String Port;
    private Integer FilterNoImg;
}