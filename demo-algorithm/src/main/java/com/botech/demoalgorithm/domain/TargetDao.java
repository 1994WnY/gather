/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: TargetDao
 * Author:   王呐宇
 * Date:     2019/7/16 13:43
 * Description: 目标库信息
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

/**
 * 〈一句话功能简述〉<br> 
 * 〈目标库信息〉
 *
 * @author 王呐宇
 * @date 2019/7/16
 * @since 1.0.0
 */
@Entity
@Table(name = "TARGET_INFO")
@Data
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class TargetDao implements Serializable {

    private static final long serialVersionUID = 6231151983215138837L;

    @Id
    @Column(length = 32)
    @GeneratedValue(generator = "jpa-uuid")
    private String dbId;
    @Column(length = 32,nullable = false)
    private String dbName;
    @Column(length = 7,nullable = false,columnDefinition = "INT default 0")
    private Integer count;
    @Column(length = 7,nullable = false)
    private Integer maxCount;
    @Column(length = 1,nullable = false,columnDefinition = "INT default 0")
    private Integer isDelete;

}