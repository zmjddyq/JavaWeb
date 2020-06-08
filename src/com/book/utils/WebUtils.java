package com.book.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * @author zmj
 * @date 2020/4/30 14:43
 * @Description
 */
public class WebUtils {
    public static <T>T copyParamToBean(Map map, T t){
        //方式一：
        //String username = req.getParameter("username");
        //String password = req.getParameter("password");
        //String email = req.getParameter("email");
        //String code = req.getParameter("code");
        //User user = new User();
        //方式二：解耦，通过BeanUtils实现
        //populate填充，将元素填充到JavaBean
        try {
            BeanUtils.populate(t,map);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    /**
     * 将字符串转换成为int类型的数据
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt,int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
