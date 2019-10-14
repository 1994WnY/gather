/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: SourceInfo
 * Author:   王呐宇
 * Date:     2019/7/16 13:59
 * Description: 接入源信息
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.domain.taskSubclass.paramSubclass;

import java.io.Serializable;
import lombok.Data;

/**
 * 〈一句话功能简述〉<br> 
 * 〈接入源信息〉
 *
 * @author 王呐宇
 * @date 2019/7/16
 * @since 1.0.0
 */

@Data
public class SourceInfo implements Serializable {

    private static final long serialVersionUID = -2032209674101263860L;

    //0视频文件 2rtsp流
    private Integer SourceType;

    private String RtspUrl;
    private Integer ProtoType;

    private String VideoFile;
    private Integer LoopPlay;

}