package cn.gxm.sorm.core.query;

import cn.gxm.sorm.bean.ColumnInfo;
import cn.gxm.sorm.bean.TableInfo;
import cn.gxm.sorm.core.DBManager;
import cn.gxm.sorm.core.TableContext;
import cn.gxm.sorm.utils.JDBCUtils;
import cn.gxm.sorm.utils.ReflectUtils;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GXM www.guokangjie.cn
 * @date 2019/5/17
 */
public class MysqlQuery implements Query {

    @Override
    public int executeDML(String sql, Object[] params) {
        PreparedStatement preparedStatement = null;
        Connection connection = DBManager.getConnection();
        try {
            preparedStatement  = connection.prepareStatement(sql);
            JDBCUtils.handleParams(preparedStatement,params);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
           JDBCUtils.close(preparedStatement,null,connection);
        }
        return 0;
    }

    @Override
    public void insert(Object table) {
        Class<?> clazz = table.getClass();
        // 获取该对象的表信息
        TableInfo tableInfo = TableContext.getClazzAllTable().get(clazz);
        //我们这里处理主键是自动增长，且属性值为空我们不传入
        //TODO 处理主键不是自增
        //insert into emp(name,age,salary,birthday,deptId) values(?,?,?,?,?)
        StringBuilder sql = new StringBuilder("insert into "+tableInfo.getName()+"(");
        Field[] fields = clazz.getDeclaredFields();
        int fieldValueNotNullCount = 0;
        List<Object> fieldValues = new ArrayList<>();
        for (Field field:fields){
            Object methodResult = ReflectUtils.getMethodResult(table, field.getName());
            if(methodResult!=null){
                fieldValueNotNullCount++;
                fieldValues.add(methodResult);
                sql.append(field.getName()+",");
            }
        }
        //去掉多余的 , 变为 )
        sql.replace(sql.lastIndexOf(","),sql.length(),") ");
        // 拼接 ?
        sql.append("values(");
        for (int i =0;i<fieldValueNotNullCount;i++){
            sql.append("?,");
        }
        //去掉多余的 , 变为 )
        sql.replace(sql.lastIndexOf(","),sql.length(),") ");
        executeDML(sql.toString(),fieldValues.toArray());
    }

    @Override
    public void delete(Class clazz, Object primaryKey) {
        //根据class找到数据库中对应的表（这里不能根据类名来直接得出表名）
        //因为你之后还要获取主键
        TableInfo tableInfo = TableContext.getClazzAllTable().get(clazz);
        //拼接sql,暂时不处理联合主键
        String sql = "delete from "+tableInfo.getName()+" where "+tableInfo.getOnlyPriKey().getName()+" =?";
        executeDML(sql,new Object[]{primaryKey});
    }

    @Override
    public void delete(Object table) {
        Class<?> clazz = table.getClass();
        // 获取主键
        TableInfo tableInfo = TableContext.getClazzAllTable().get(clazz);
        ColumnInfo onlyPriKey = tableInfo.getOnlyPriKey();

        //通过反射的值获取主键的值(这里无法调用table的get方法，因为你不知道具体的对象)
        Object methodResult = ReflectUtils.getMethodResult(table, onlyPriKey.getName());
        //拼接sql,暂时不处理联合主键
        String sql = "delete from "+tableInfo.getName()+" where "+onlyPriKey.getName()+" =?";
        executeDML(sql,new Object[]{methodResult});
    }

    @Override
    public int update(Object table, String[] fieldNames) {
        //{empName,age} ---->  update emp set empName=?,age=? where 主键=?
        Class<?> clazz = table.getClass();
        TableInfo tableInfo = TableContext.getClazzAllTable().get(clazz);
        //遍历 fieldName 拼装 update emp set empName=?,age=?
        StringBuilder sql = new StringBuilder("update emp set ");
        //属性的值列表
        List<Object> fieldValues = new ArrayList<>();
        for (String fieldName:fieldNames){
            sql.append(fieldName+"=?,");
            Object methodResult = ReflectUtils.getMethodResult(table, fieldName);
            fieldValues.add(methodResult);
        }
        //将最后一个 , ---> 空格
        sql.replace(sql.lastIndexOf(","),sql.length()," ");
        //拼接 where 主键=?
        String priKeyName = tableInfo.getOnlyPriKey().getName();
        sql.append("where "+priKeyName+"=?");
        fieldValues.add(ReflectUtils.getMethodResult(table,priKeyName));
        return executeDML(sql.toString(), fieldValues.toArray());
    }

    @Override
    public List queryRows(String sql, Class clazz, String[] params) {
        return (List) baseQuery(sql,params,clazz,new QueryRowsCallResultSet());
    }

    @Override
    public Object queryOne(String sql,Class clazz, String[] params) {
        return queryRows(sql,clazz,params).size()>0?queryRows(sql,clazz,params).get(0):null;
    }

    @Override
    public Number queryNumber(@NotNull String sql,
                              @Nullable String[] params) {
        return (Number) baseQuery(sql,params,null,new QueryNumberCallResultSet());
    }


    /**
     * 基础的查询
     * @param sql 查询的sql语句
     * @param params sql语句的参数值
     * @param callResultSet 查询的结果集处理器
     * @return
     */
    @Nullable private <T> Object baseQuery(@NotNull  String sql,
                                 @Nullable String[] params,
                                 @NotNull  Class<T> clazz,
                                 @NotNull  CallResultSet callResultSet) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet =null;
        Connection connection = DBManager.getConnection();
        try {
            //sql =>>> SELECT COUNT(*) FROM emp WHERE deptId>?
            preparedStatement  = connection.prepareStatement(sql);
            // SELECT COUNT(*) FROM emp WHERE deptId>1
            JDBCUtils.handleParams(preparedStatement,params);
            resultSet = preparedStatement.executeQuery();

            return callResultSet.handleResultSet(resultSet,clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(preparedStatement,resultSet,connection);
        }
        return null;
    }
}
