package com.botech.demoalgorithm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileOperationUtil {

    // 判断文件是否存在
    public static void judeFileExists(File file) {

        if (file.exists()) {
            log.debug("file exists");
        }
        else {
            log.debug("file not exists, create it ...");
            try {
                file.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    // 判断文件夹是否存在
    public static void judeDirExists(File file) {

        if (file.exists()) {
            if (file.isDirectory()) {
                log.debug("dir exists");
            }
            else {
                log.debug("the same name file exists, can not create dir");
            }
        }
        else {
            log.debug("dir not exists, create it ...");
            file.mkdirs();

        }

    }

    /**
     * 获取图片的md5
     */
    public static String getStringMd5(File file) throws FileNotFoundException {
        String value = null;
        FileInputStream in = new FileInputStream(file);
        try {
            MappedByteBuffer byteBuffer = in.getChannel()
                .map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (null != in) {
                try {
                    in.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

}
