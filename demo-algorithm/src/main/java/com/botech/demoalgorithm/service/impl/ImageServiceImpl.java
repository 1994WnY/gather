/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: ImageServiceImpl
 * Author:   王呐宇
 * Date:     2019/7/18 11:32
 * Description: 图片业务接口实现类
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
import com.botech.demoalgorithm.exception.ImageNotExistException;
import com.botech.demoalgorithm.service.ImageService;
import com.botech.demoalgorithm.utils.FileOperationUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 〈一句话功能简述〉<br> 
 * 〈图片业务接口实现类〉
 *
 * @author 王呐宇
 * @date 2019/7/18
 * @since 1.0.0
 */
@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    @Value("${image.path.db-image}")
    private String prePath;
    @Value("${image.getfeature}")
    private String defGetFeature;
    @Value("${image.qualityScore}")
    private String defQualityThreshold;

    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private TargetMapper targetMapper;

    @Override
    public Map<String, Object> detectAndQuality(MultipartFile file) throws Exception {
        Map<String, Object> result = new HashMap<>();
        List datas = new ArrayList();
        if(!file.isEmpty()){
            int count = 1 + (int) (Math.random() * 3);
            IntStream.range(0,count).forEach(x->{
                Map<String, Object> data = new HashMap<>();
                List rect = new ArrayList();
                for (int i = 0; i < 4; i++) {
                    rect.add(1 + (int) (Math.random() * 500));
                }
                data.put("rect", rect);
                data.put("quality_score", 1 + (int) (Math.random() * 100));
                datas.add(data);
            });
        }
        result.put("result", "success");
        result.put("data", datas);
        return result;
    }

    @Override
    public Map<String, Object> asyncAdd(MultipartFile[] files, String dbName,
        Integer getFeature, Integer qualityThreshold) throws Exception {
        TargetDao byDbName = targetMapper.getByDbNameAndIsDelete(dbName,0);
        if (byDbName == null) {
            throw new DbNotExistException();
        }
        if(getFeature == null){
            getFeature = Integer.valueOf(this.defGetFeature);
        }
        int threshold = qualityThreshold == null?Integer.valueOf(this.defQualityThreshold)
            :qualityThreshold;
        Map<String, Object> result = new HashMap<>();
        List successs = new ArrayList();
        List fails = new ArrayList();
        Arrays.stream(files).forEach(x -> {
            Map<String, Object> success = new HashMap<>();
            Map<String, Object> fail = new HashMap<>();
            int qualityScore = 1 + (int) (Math.random() * 100);
            String fileName = x.getOriginalFilename();
            try {
                if(qualityScore >= threshold){
                    FileOperationUtil.judeDirExists(new File(prePath + File.separator + dbName));
                    String filePath = prePath + File.separator + dbName + File.separator + fileName;
                    File image = new File(filePath);
                    FileOperationUtil.judeFileExists(image);
                    x.transferTo(image);
                    ImageDao imageDao = new ImageDao();
                    imageDao.setImageUrl(filePath);
                    imageDao.setTargetId(byDbName.getDbId());
                    imageDao.setIsDelete(0);
                    imageDao.setFeatureBuild(0);
                    String feature = "NO_FEATURES";
                    imageDao.setFeature(feature);
                    ImageDao save = imageMapper.save(imageDao);
                    log.info("图片上传成功：" + fileName);
                    success.put("name", fileName);
                    success.put("imageId", save.getImageId());
                    success.put("qualityScore", qualityScore);
                    success.put("feature", feature);
                }else{
                    log.info("图片上传失败："+fileName+"，未检测到人脸");
                    fail.put("name", fileName);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                log.error("图片上传失败：" + fileName);
                fail.put("name", fileName);
            }
            successs.add(success);
            if (!fail.isEmpty()) {
                fails.add(fail);
            }


            targetMapper.updateCountByDbId(byDbName.getDbId());

        });
        result.put("result", "success");
        result.put("success", successs);
        result.put("fail", fails);
        return result;
    }

    @Override
    public void getImage(String imageId, HttpServletResponse response) throws Exception {
        ImageDao byImageIdAndIsDelete = imageMapper.getByImageIdAndIsDelete(imageId, 0);
        if(byImageIdAndIsDelete == null){
            throw new ImageNotExistException();
        }
        if(StringUtils.isNotBlank(byImageIdAndIsDelete.getImageUrl())){
            File file = new File(byImageIdAndIsDelete.getImageUrl());
            InputStream in = new FileInputStream(file);
            ServletOutputStream out = response.getOutputStream();
            // 创建缓冲区
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
        }
    }

    @Override
    public Map<String, Object> deleteImage(String dbName, String imageId) throws Exception {
        TargetDao byDbName = targetMapper.getByDbNameAndIsDelete(dbName,0);
        if (byDbName == null) {
            throw new DbNotExistException();
        }
        ImageDao byImageIdAndIsDeleteAndTargetId = imageMapper
            .getByImageIdAndIsDeleteAndTargetId(imageId, 0, byDbName.getDbId());
        if(byImageIdAndIsDeleteAndTargetId == null){
            throw new ImageNotExistException();
        }
        Map<String, Object> result = new HashMap<>();
        imageMapper.deleteByImageId(imageId);
        targetMapper.updateCountByDbId(byDbName.getDbId());
        File file = new File(byImageIdAndIsDeleteAndTargetId.getImageUrl());
        if (file.exists() && file.isFile()) {
            file.delete();
        }
        result.put("result", "success");
        return result;
    }

    @Override
    public Map<String, Object> verification(MultipartFile firstFile, MultipartFile secondFile)
        throws Exception {
        Map<String, Object> result = new HashMap<>();
        double random = Math.random();
        String score = String.format("%.2f", random);
        result.put("result", "success");
        result.put("score", Double.valueOf(score));
        return result;
    }

    @Override
    public Map<String, Object> search(MultipartFile file, String dbName, int topNum, double score) throws Exception{
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