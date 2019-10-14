/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: TargetServiceImpl
 * Author:   王呐宇
 * Date:     2019/7/18 11:31
 * Description: 目标库业务逻辑接口实现类
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.service.impl;

import com.botech.demoalgorithm.dao.ImageMapper;
import com.botech.demoalgorithm.dao.TargetMapper;
import com.botech.demoalgorithm.domain.TargetDao;
import com.botech.demoalgorithm.exception.DbAlreadyExistException;
import com.botech.demoalgorithm.exception.DbNotExistException;
import com.botech.demoalgorithm.service.TargetService;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉<br> 
 * 〈目标库业务逻辑接口实现类〉
 *
 * @author 王呐宇
 * @date 2019/7/18
 * @since 1.0.0
 */
@Service
@Slf4j
public class TargetServiceImpl implements TargetService {

    @Value("${image.path.db-image}")
    private String prePath;

    @Autowired
    private TargetMapper targetMapper;
    @Autowired
    private ImageMapper imageMapper;

    @Override
    public Map<String, Object> createTarget(String dbName, int size) throws Exception{
        int count = targetMapper.countByDbNameAndIsDelete(dbName,0);
        if (count > 0) {
            throw new DbAlreadyExistException();
        }
        Map<String, Object> result = new HashMap<>();
        TargetDao targetDao = new TargetDao();
        targetDao.setDbName(dbName);
        targetDao.setMaxCount(size);
        targetDao.setCount(0);
        targetDao.setIsDelete(0);
        targetMapper.save(targetDao);
        result.put("result", "success");
        return result;
    }

    @Override
    public Map<String, Object> getTargets() throws Exception{
        Map<String, Object> result = new HashMap<>();
        List<TargetDao> allByIsDelete = targetMapper.findAllByIsDelete(0);
        result.put("result", "success");
        result.put("data", allByIsDelete);
        return result;
    }

    @Override
    public Map<String, Object> clearTarget(String dbName) throws Exception{
        TargetDao byDbName = targetMapper.getByDbNameAndIsDelete(dbName,0);
        if (byDbName == null) {
            throw new DbNotExistException();
        }
        Map<String, Object> result = new HashMap<>();
        imageMapper.deleteByTargetId(byDbName.getDbId());
        targetMapper.updateCountByDbId(byDbName.getDbId());
        File dir = new File(prePath + File.separator + dbName);
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            Arrays.stream(files).parallel().forEach(File::delete);
        }
        result.put("result", "success");
        return result;
    }

    @Override
    public Map<String, Object> deleteTarget(String dbName) throws Exception {
        TargetDao byDbName = targetMapper.getByDbNameAndIsDelete(dbName,0);
        if (byDbName == null) {
            throw new DbNotExistException();
        }
        Map<String, Object> result = new HashMap<>();
        targetMapper.deleteByDbId(byDbName.getDbId());
        File dir = new File(prePath + File.separator + dbName);
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            Arrays.stream(files).parallel().forEach(File::delete);
            dir.delete();
        }
        result.put("result", "success");
        return result;
    }

}