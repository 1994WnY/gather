/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: TaskDao
 * Author:   王呐宇
 * Date:     2019/7/16 13:38
 * Description: 任务信息
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 〈一句话功能简述〉<br> 
 * 〈任务信息〉
 *
 * @author 王呐宇
 * @date 2019/7/16
 * @since 1.0.0
 */
@Entity
@Table(name = "TASK_INFO")
@Data
public class TaskDao implements Serializable {

    private static final long serialVersionUID = -1674314820504588194L;

    @Id
    @Column(length = 32)
    private String taskId;
    @Column(length = 2000,nullable = false)
    private String taskParam;
    @Column(length = 1,nullable = false)
    private Integer isRun;
    @Column(length = 1,nullable = false,columnDefinition = "INT default 0")
    private Integer isDelete;

}