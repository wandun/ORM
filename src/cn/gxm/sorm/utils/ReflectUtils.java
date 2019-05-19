package cn.gxm.sorm.utils;

import com.sun.istack.internal.NotNull;

import java.lang.reflect.Method;

/**
 * @author GXM www.guokangjie.cn
 * @date 2019/5/15
 *
 * 反射的工具类
 */
public class ReflectUtils {


    /**
     * 根据属性名称调用其get方法
     * @param object 该属性属于那个对象
     * @param fieldName 属性的名称
     * @return 调用该属性的get方法的返回值
     */
    public static Object getMethodResult(Object object,
                                  String fieldName){
        try {
            Method method = object.getClass().getDeclaredMethod("get" + StringUtils.toUpperCaseHeadChar(fieldName),null);
            return method.invoke(object, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过反射调用属性的set方法
     * @param object 该属性属于那个对象
     * @param fieldName 该属性的名称
     * @param clazz 该属性的类型
     * @param value 该属性需要被赋予的值
     */
    public static void setMethodResult(Object object,
                                         String fieldName,
                                         Class clazz,
                                         Object value){
        try {
            Method method = object.getClass().getDeclaredMethod("set" + StringUtils.toUpperCaseHeadChar(fieldName),clazz);
            method.invoke(object, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
