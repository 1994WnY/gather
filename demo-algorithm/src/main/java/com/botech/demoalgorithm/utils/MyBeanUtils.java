package com.botech.demoalgorithm.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class MyBeanUtils {

    public static void copyProperties(Object source,Object target) throws Exception {
        if (source==null){
            throw new Exception("Source can bot be null");
        }
        if (target==null){
            throw new Exception("target can bot be null");
        }

        Class<?> sourceClazz = source.getClass();
        Class<?> targetClazz = target.getClass();

        if (!sourceClazz.equals(targetClazz)){
            throw new Exception("类型不一致");
        }

        BeanInfo beanInfo = Introspector.getBeanInfo(source.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor p:propertyDescriptors){
            Method readMethod = p.getReadMethod();
            Method writeMethod = p.getWriteMethod();
            Object value = readMethod.invoke(source, new Object[]{});
            if (value==null||readMethod.getName().equals("getClass")){
                continue;
            }
            writeMethod.invoke(target,value);
        }
    }
}