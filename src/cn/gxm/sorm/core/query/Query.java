package cn.gxm.sorm.core.query;

import cn.gxm.sorm.bean.TableInfo;

import java.util.List;
import java.util.function.Function;

/**
 * @author GXM www.guokangjie.cn
 * @date 2019/5/15
 *
 * 操作数据库的核心接口
 */
public interface Query/*<T extends TableInfo>*/ {

    /**
     * 直接执行一个sql语句
     * @param sql 需要执行的sql语句，参数用 ? 代替
     * @param params sql语句的参数，用于替换 ?
     * @return 执行sql语句影响的行数
     */
    int executeDML(String sql,Object[] params);

    /**
     * 向数据库中插入一条数据
     * @param table 即java中的pojo对应的数据库中的表名称
     */
    void insert(Object table);

    /**
     * 根据 id 删除数据
     * @param clazz 数据库中表对应的java对象
     * @param primaryKey 主键
     *  delete from User where id = ?
     */
    void delete(Class clazz,Object primaryKey);

    /**
     * 根据与数据库对应的表的对象来删除
     * 根据主键删除 主键值在传入的Object对象中
     * @param table
     */
    void delete(Object table);

    /**
     * 跟新表数据
     * @param table 数据库中表对应的java对象
     * @param fieldNames 需要修改的值的属性名称，不需要传入主键的fieldName
     * @return 影响的条数
     * update User set name='' where id = ''
     */
    int update(Object table,String[] fieldNames);

    /**
     * 指定查询多条记录，并将记录封装到指定的class对象中
     * @param sql 需要执行查询的sql语句
     * @param clazz 查询结果封装的对象类型
     * @param params sql语句的参数值
     * @return 将封装的class对象变为集合返回
     * select id,name from User where empName like ''
     */
    List queryRows(String sql,Class clazz,String[] params);

    /**
     * 指定查询多条记录
     * @param sql 需要执行查询的sql语句
     * @param clazz 查询结果封装的对象类型
     * @param params sql语句的参数
     * @return 将封装的class对象返回
     */
    Object queryOne(String sql,Class clazz,String[] params);

    /**
     * 查询数值
     * @param sql 需要执行查询的sql语句
     * @param params sql语句的参数
     * @return 返回查询的数值
     * select count(*) from User where id = ''
     */
    Number queryNumber(String sql,String[] params);
}
