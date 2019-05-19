package cn.gxm.sorm.core;

import java.sql.Date;

/**
 * @author GXM www.guokangjie.cn
 * @date 2019/5/16
 *
 * 实现mysql数据库与java数据类型的转换
 */
public class MySqlTypeConvertor implements TypeConvertor{

    /**
     * 暂时不实现
     * @param javaType java数据类型
     * @return
     */
    @Override
    public String javaType2DBType(String javaType) {

        return null;
    }

    /**
     * 现mysql数据库到java数据类型的转换
     * @param columnType 数据库类型
     * @return 与之对应的Java类型
     */
    @Override
    public String DBType2javaType(String columnType) {
        if(MySqlType.VARCHAR.equalsIgnoreCase(columnType)){
            return "String";
        }else if(MySqlType.INT.equalsIgnoreCase(columnType)
                || MySqlType.BIGINT.equalsIgnoreCase(columnType)
                || MySqlType.INTEGER.equalsIgnoreCase(columnType)){
            return "Integer";
        }else if(MySqlType.DATE.equalsIgnoreCase(columnType)
                || MySqlType.TIME.equalsIgnoreCase(columnType)){
            // 注意这里转为java.sql.Date而不是java.util.date
            // 如果直接转换为utils.date，则需要具体的实现，用到时，我们再转化即可
            return "java.sql.Date";
        }else if(MySqlType.DOUBLE.equalsIgnoreCase(columnType)){
            return "Double";
        }else if(MySqlType.FLOAT.equalsIgnoreCase(columnType)){
            return "Float";
        }

        // 这里不全部转化
        return "";
    }

}
