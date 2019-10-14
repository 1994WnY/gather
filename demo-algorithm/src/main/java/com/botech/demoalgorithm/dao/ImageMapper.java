/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: ImageMapper
 * Author:   王呐宇
 * Date:     2019/7/18 11:29
 * Description: 图片数据持久层
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.dao;

import com.botech.demoalgorithm.domain.ImageDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * 〈一句话功能简述〉<br> 
 * 〈图片数据持久层〉
 *
 * @author 王呐宇
 * @date 2019/7/18
 * @since 1.0.0
 */
@Transactional
public interface ImageMapper extends JpaRepository<ImageDao,String> {

    @Query(nativeQuery = true,value = "update image_info set is_delete = 1 where target_id = :targetId")
    @Modifying
    int deleteByTargetId(@Param(value = "targetId") String targetId);

    ImageDao getByImageIdAndIsDelete(String imageId,int isDelete);

    ImageDao getByImageIdAndIsDeleteAndTargetId(String imageId,int isDelete,String targetId);

    @Query(nativeQuery = true,value = "update image_info set is_delete = 1 where image_id = :imageId")
    @Modifying
    int deleteByImageId(@Param(value = "imageId") String imageId);

    Page<ImageDao> findAllByTargetIdAndIsDelete(String targetId,int isDelete, Pageable pageable);

    List<ImageDao> findAllByTargetIdAndIsDelete(String targetId,int isDelete);
}
