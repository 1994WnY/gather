/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: FeatureService
 * Author:   王呐宇
 * Date:     2019/7/18 21:50
 * Description: 特征业务处理层接口
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.service;

import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 * 〈一句话功能简述〉<br> 
 * 〈特征业务处理层接口〉
 *
 * @author 王呐宇
 * @date 2019/7/18
 * @since 1.0.0
 */
public interface FeatureService {

    Map<String,Object> getFeature(MultipartFile file) throws Exception;

    Map<String, Object> asyncAdd(String dbName, String feature) throws Exception;

    Map<String,Object> verification(String featureOne, String featureTwo) throws Exception;

    Map<String,Object> search(String feature,String dbName,int topNum,double score) throws Exception;
}