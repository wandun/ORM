package cn.gxm.sorm.utils;

/**
 * @author GXM www.guokangjie.cn
 * @date 2019/5/15
 *
 * 操作字符串的工具类
 */
public class StringUtils {


    /**
     * 判断字符串是否为空
     * @param s
     * @return
     */
    public boolean isEmpty(String s){
        return s == null || "".equals(s.trim());
    }


    /**
     * 将字符串的首字母大写
     * @param src
     * @return
     */
    public static String toUpperCaseHeadChar(String src){
        return src.toUpperCase().substring(0,1)+src.substring(1);
    }
}
