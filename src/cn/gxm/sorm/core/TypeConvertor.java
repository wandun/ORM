package cn.gxm.sorm.core;

/**
 * @author GXM www.guokangjie.cn
 * @date 2019/5/15
 *
 * 用于数据数据类型与java数据类型转换
 */
public interface TypeConvertor {

    /**
     * 将java数据类型转为相应的数据库数据类型
     * @param javaType java数据类型
     * @return 与之对应的数据库类型
     */
    String javaType2DBType(String javaType);

    /**
     * 将数据库数据类型转为相应的java数据类型
     * @param columnType 数据库类型
     * @return 与之对应的java类型
     */
    String DBType2javaType(String columnType);
}
