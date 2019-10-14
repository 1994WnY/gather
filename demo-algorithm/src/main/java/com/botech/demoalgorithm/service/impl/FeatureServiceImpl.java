/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: FeatureServiceImpl
 * Author:   王呐宇
 * Date:     2019/7/18 21:51
 * Description: 特征业务处理层接口实现类
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.service.impl;

import com.botech.demoalgorithm.dao.ImageMapper;
import com.botech.demoalgorithm.dao.TargetMapper;
import com.botech.demoalgorithm.domain.ImageDao;
import com.botech.demoalgorithm.domain.TargetDao;
import com.botech.demoalgorithm.exception.DbNotExistException;
import com.botech.demoalgorithm.service.FeatureService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 〈一句话功能简述〉<br> 
 * 〈特征业务处理层接口实现类〉
 *
 * @author 王呐宇
 * @date 2019/7/18
 * @since 1.0.0
 */
@Service
public class FeatureServiceImpl implements FeatureService {

    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private TargetMapper targetMapper;

    @Override
    public Map<String, Object> getFeature(MultipartFile file) throws Exception {
        Map<String,Object> result = new HashMap<>();
        result.put("result","success");
        result.put("feature","NO_FEATURES");
        return result;
    }

    @Override
    public Map<String, Object> asyncAdd(String dbName, String feature) throws Exception {
        TargetDao byDbName = targetMapper.getByDbNameAndIsDelete(dbName,0);
        if (byDbName == null) {
            throw new DbNotExistException();
        }
        Map<String, Object> result = new HashMap<>();
        ImageDao imageDao = new ImageDao();
        imageDao.setTargetId(byDbName.getDbId());
        imageDao.setIsDelete(0);
        imageDao.setFeatureBuild(1);
        imageDao.setFeature(feature);
        ImageDao save = imageMapper.save(imageDao);
        result.put("result", "success");
        result.put("featureId", save.getImageId());
        return result;
    }

    @Override
    public Map<String, Object> verification(String featureOne, String featureTwo) throws Exception{
        Map<String, Object> result = new HashMap<>();
        double random = Math.random();
        String score = String.format("%.2f", random);
        result.put("result", "success");
        result.put("score", Double.valueOf(score));
        return result;
    }

    @Override
    public Map<String, Object> search(String feature, String dbName, int topNum,
        double score) throws Exception {
        TargetDao byDbName = targetMapper.getByDbNameAndIsDelete(dbName,0);
        if (byDbName == null) {
            throw new DbNotExistException();
        }
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> datas = new ArrayList();
        Pageable pageable = new PageRequest(0, topNum, Direction.ASC, "imageId");
        Page<ImageDao> allByTargetIdAndIsDelete = imageMapper
            .findAllByTargetIdAndIsDelete(byDbName.getDbId(), 0, pageable);
        List<ImageDao> content = allByTargetIdAndIsDelete.getContent();
        content.stream().forEach(x -> {
            Map<String, Object> data = new HashMap<>();
            double random = Double.valueOf(String.format("%.2f", Math.random()));
            if (random >= score) {
                data.put("imageId", x.getImageId());
                data.put("score", random);
                datas.add(data);
            }
        });
        List<Map<String, Object>> images = datas.stream()
            .sorted(Comparator.comparing(x -> Double.valueOf((x.get("score").toString()))))
            .collect(Collectors.toList());
        result.put("result", "success");
        result.put("feature", "NO_FEATURES");
        result.put("data", images);
        return result;
    }
}