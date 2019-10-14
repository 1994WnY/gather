/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: ImageService
 * Author:   王呐宇
 * Date:     2019/7/18 11:31
 * Description: 图片业务逻辑接口
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.service;

import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * 〈一句话功能简述〉<br> 
 * 〈图片业务逻辑接口〉
 *
 * @author 王呐宇
 * @date 2019/7/18
 * @since 1.0.0
 */
public interface ImageService {

    Map<String,Object> detectAndQuality(MultipartFile file) throws Exception;

    Map<String, Object> asyncAdd(MultipartFile[] files, String dbName, Integer getFeature,
        Integer qualityThreshold) throws Exception;

    void getImage(String imageId, HttpServletResponse response) throws Exception;

    Map<String,Object> deleteImage(String dbName,String imageId) throws Exception;

    Map<String,Object> verification(MultipartFile firstFile,MultipartFile secondFile) throws Exception;

    Map<String,Object> search(MultipartFile file,String dbName,int topNum,double score) throws Exception;
}
