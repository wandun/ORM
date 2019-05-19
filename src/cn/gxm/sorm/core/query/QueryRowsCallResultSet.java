package cn.gxm.sorm.core.query;

import cn.gxm.sorm.utils.ReflectUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GXM www.guokangjie
 * @date 2019/5/19
 *
 * 处理ResultSet查询多行多列的结果集
 */
class QueryRowsCallResultSet implements CallResultSet{

    @Override
    public <T> Object handleResultSet(ResultSet resultSet, Class<T> clazz) {
        try {
            //存储结果
            List<T> result = new ArrayList<>();
            //得到查询结果的数据库元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            while (resultSet.next()){
                T newInstance = clazz.newInstance();
                for (int i = 0; i< metaData.getColumnCount(); i++){
                    //得到查询结果列的标签也就是列名  如 empName,age 下表从一开始
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    //得到查询结果列的指定column的值  如 empName='小米' 下表从一开始
                    Object columnValue = resultSet.getObject(i + 1);
                    //通过反射调用该方法的指定属性的set方法将其赋值  如果返回值 empName='小米' 那么columnValue.getClass() 就是java.lang.string
                    ReflectUtils.setMethodResult(newInstance,columnLabel,columnValue.getClass(),columnValue);
                }
                //一个对象完成，将其加入result集合中去
                result.add(newInstance);
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}