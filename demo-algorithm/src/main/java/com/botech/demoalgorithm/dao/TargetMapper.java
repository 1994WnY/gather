/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: TargetMapper
 * Author:   王呐宇
 * Date:     2019/7/18 11:28
 * Description: 目标库数据持久层
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.dao;

import com.botech.demoalgorithm.domain.TargetDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * 〈一句话功能简述〉<br> 
 * 〈目标库数据持久层〉
 *
 * @author 王呐宇
 * @date 2019/7/18
 * @since 1.0.0
 */
@Transactional
public interface TargetMapper extends JpaRepository<TargetDao,String> {

    int countByDbNameAndIsDelete(String dbName,int isDelete);

    TargetDao getByDbNameAndIsDelete(String dbName,int isDelete);

    List<TargetDao> findAllByIsDelete(int isDelete);

    @Query(nativeQuery = true,value = "update target_info set is_delete = 1 where db_id = :dbId")
    @Modifying
    int deleteByDbId(@Param(value = "dbId") String dbId);

    @Query(nativeQuery = true, value = "update target_info set count =(SELECT COUNT(*) FROM "
        + "image_info WHERE target_id = :dbId and is_delete = 0) where db_id = :dbId")
    @Modifying
    int updateCountByDbId(@Param(value = "dbId") String dbId);

}
