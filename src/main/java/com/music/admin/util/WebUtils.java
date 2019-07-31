package com.music.admin.util;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by donald.tang on 2019/7/17.
 */
public class WebUtils {

    public static <T> T request2Bean(HttpServletRequest request, Class<T> clazz){
        try{
            T bean = clazz.newInstance();
            Enumeration<String> e = request.getParameterNames();
            while(e.hasMoreElements()){
                String name = (String) e.nextElement();
                String value = request.getParameter(name);
                BeanUtils.setProperty(bean, name, value);
            }
            return bean;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
