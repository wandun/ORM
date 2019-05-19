package cn.gxm.sorm.core.query;

import java.sql.ResultSet;

interface CallResultSet{

    /**
     * 根据需求完成对查询结果的分装
     * @param resultSet 查询结果集
     * @param clazz 封装数据的对象
     * @param <T>
     * @return
     */
    <T> Object handleResultSet(ResultSet resultSet, Class<T> clazz);

}