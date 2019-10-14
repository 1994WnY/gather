/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: ImageDao
 * Author:   王呐宇
 * Date:     2019/7/16 13:44
 * Description: 图片信息
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
 * 〈图片信息〉
 *
 * @author 王呐宇
 * @date 2019/7/16
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "IMAGE_INFO")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class ImageDao implements Serializable {

    private static final long serialVersionUID = 4919280306869565059L;

    @Id
    @Column(length = 32)
    @GeneratedValue(generator = "jpa-uuid")
    private String imageId;
    @Column(length = 32,nullable = false)
    private String targetId;
    @Column(length = 200)
    private String imageUrl;
    @Column(length = 4000)
    private String feature;
    @Column(length = 1,nullable = false,columnDefinition = "INT default 0")
    private Integer featureBuild;
    @Column(length = 1,nullable = false,columnDefinition = "INT default 0")
    private Integer isDelete;
}