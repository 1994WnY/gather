/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: TaskMapper
 * Author:   王呐宇
 * Date:     2019/7/16 13:29
 * Description: 任务数据持久层
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.dao;

import com.botech.demoalgorithm.domain.TaskDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * 〈一句话功能简述〉<br> 
 * 〈任务数据持久层〉
 *
 * @author 王呐宇
 * @date 2019/7/16
 * @since 1.0.0
 */
@Transactional
public interface TaskMapper extends JpaRepository<TaskDao,String> {

    @Query(nativeQuery = true, value = "SELECT COUNT(1) FROM task_info WHERE is_delete = 0 AND is_run = 1")
    int countRunningTask();

    List<TaskDao> findAllByIsDeleteAndIsRun(int isDelete, int isRun);

    TaskDao findByTaskIdAndIsDeleteAndIsRun(String taskId, int isDelete, int isRun);

    @Query(nativeQuery = true, value = "UPDATE task_info SET task_param = :taskParam  WHERE task_id = :taskId")
    @Modifying
    int updateParamById(@Param(value = "taskParam") String taskParam,
        @Param(value = "taskId") String taskId);

    @Query(nativeQuery = true,value = "UPDATE task_info SET is_delete = 1  WHERE task_id = :taskId")
    @Modifying
    int deleteByTaskId(@Param(value = "taskId") String taskId);
}