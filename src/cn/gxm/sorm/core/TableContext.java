package cn.gxm.sorm.core;

import cn.gxm.sorm.bean.ColumnInfo;
import cn.gxm.sorm.bean.Configuration;
import cn.gxm.sorm.bean.TableInfo;
import cn.gxm.sorm.utils.JavaFileUtils;
import cn.gxm.sorm.utils.StringUtils;

import java.sql.*;
import java.util.*;

/**
 * @author GXM www.guokangjie.cn
 * @date 2019/5/15
 *
 * 管理数据库中的所有表和java中的对象的关系
 * 可以根据表生成java对象
 */
public class TableContext {

    /**
     * 对应数据库中所有的表
     * key 为表的名称， value为表对应的bean
     */
    private static Map<String, TableInfo> allTables = new HashMap<>();

    /**
     * 将生成的pojo用class与数据库中的表关联起来
     */
    private static Map<Class ,TableInfo> clazzAllTable = new HashMap<>();
    private TableContext(){
    }


    /**
     * 获取数据库中的所有表以及相关字段，并生成对应的pojo
     */
    static {
        Connection connection = DBManager.getConnection();
        ResultSet rs = null;
        try {
            // 根据metaData就可以获取到数据库的源信息(表，字段等等)
            // 具体请查看 https://blog.csdn.net/chen_zw/article/details/18816599
            DatabaseMetaData metaData = connection.getMetaData();

            //处理表
            rs = metaData.getTables(null, "%", "%", new String[]{ "TABLE" });
            while (rs.next()) {
                TableInfo tableInfo = new TableInfo();
                String tableName = rs.getString("TABLE_NAME");
                tableInfo.setName(tableName);

                //处理一个表中的所有列
                ResultSet set = metaData.getColumns(null, "%", tableName, "%");
                Map<String, ColumnInfo> columnInfoMap = new HashMap<>();
                while (set.next()){
                    ColumnInfo columnInfo = new ColumnInfo(set.getString("COLUMN_NAME"),
                            set.getString("TYPE_NAME"),1);
                    columnInfoMap.put(columnInfo.getName(),columnInfo);
                }

                //处理表中的主键，跟新字段的键类型
                List<ColumnInfo> columnInfoList = new ArrayList<>();
                ResultSet primaryKeys = metaData.getPrimaryKeys(null, "%", tableName);
                while (primaryKeys.next()){
                    ColumnInfo columnInfo = columnInfoMap.get(primaryKeys.getObject("COLUMN_NAME"));
                    columnInfo.setKeyType(2);
                    columnInfoMap.put((String)primaryKeys.getObject("COLUMN_NAME"),columnInfo);
                    columnInfoList.add(columnInfo);

                    if(columnInfoList.size() == 1){
                        tableInfo.setOnlyPriKey(columnInfo);
                    }else{
                        // 说明为联合主键
                        tableInfo.setUnionKey(columnInfoList);
                        tableInfo.setOnlyPriKey(null);
                    }
                }

                tableInfo.setColumns(columnInfoMap);
                allTables.put(tableName,tableInfo);

                //配置clazzAllTales
                Class<?> clazz = Class.forName(DBManager.getConfiguration().getPojoPackage() + "." + StringUtils.toUpperCaseHeadChar(tableName));
                clazzAllTable.put(clazz,tableInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 生成或者跟新java的pojo与数据库的对应关系
     * @param author 作者
     */
    public static void generateOrUpdateJavaFilePojo(String author){
        Set<Map.Entry<String, TableInfo>> entries = allTables.entrySet();
        for (Map.Entry<String, TableInfo> entry: entries){
            JavaFileUtils.generatePOJOFile(entry.getValue(),new MySqlTypeConvertor(),author);
        }
    }

    /**
     * 对外提供获取数据库表信息的方法(AllTables)
     * @return
     */
    public static Map<String, TableInfo> getAllTables() {
        return allTables;
    }

    /**
     * 对外提供获取数据库表信息的方法(clazzAllTables)
     * @return
     */
    public static Map<Class, TableInfo> getClazzAllTable() {
        return clazzAllTable;
    }

}
