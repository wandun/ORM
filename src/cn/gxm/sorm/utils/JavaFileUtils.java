package cn.gxm.sorm.utils;

import cn.gxm.sorm.bean.ColumnInfo;
import cn.gxm.sorm.bean.JavaFieldSetGet;
import cn.gxm.sorm.bean.TableInfo;
import cn.gxm.sorm.core.DBManager;
import cn.gxm.sorm.core.MySqlTypeConvertor;
import cn.gxm.sorm.core.TableContext;
import cn.gxm.sorm.core.TypeConvertor;
import cn.gxm.sorm.exception.SormException;
import com.sun.istack.internal.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author GXM www.guokangjie.cn
 * @date 2019/5/15
 *
 * 操作文件（暂时先将columnInfo转为对应的java代码）
 */
public class JavaFileUtils {


    /**
     * 根据传入的数据库字段信息生成java的bean代码
     * varchar id -> private String id 以及相应set与get方法
     * @param columnInfo 数据库字段信息
     * @param typeConvertor 对应的数据库字段数据类型转换
     * @return 一个属性的bean代码
     */
    private static JavaFieldSetGet generateJavaFieldSetGet(@NotNull ColumnInfo columnInfo,
                                                          @NotNull TypeConvertor typeConvertor){
        if(columnInfo==null || typeConvertor == null){
            throw new SormException("ColumnInfo or TypeConvertor is null");
        }

        JavaFieldSetGet fieldSetGet = new JavaFieldSetGet();

        // 字段名称 id
        String columnName = columnInfo.getName();
        String javaFieldType = typeConvertor.DBType2javaType(columnInfo.getDataType());

        //private String id
        String fieldInfo = "\tprivate "+ javaFieldType +" "+ columnName +";\n";

        /**
         * public void getId(){
         *      return id;
         * }
         */
        StringBuilder fieldGetInfo = new StringBuilder("\tpublic  "+javaFieldType+" get"+StringUtils.toUpperCaseHeadChar(columnName)+" (){\n");
        fieldGetInfo.append("\t\treturn "+ columnName+";\n");
        fieldGetInfo.append("\t}\n");

        /**
         * public void setId(String id){
         *      this.id = id;
         * }
         */
        StringBuilder fieldSetInfo = new StringBuilder("\tpublic void set"+StringUtils.toUpperCaseHeadChar(columnName)+" ("+javaFieldType+" "+columnName+"){\n");
        fieldSetInfo.append("\t\tthis."+columnName+"="+ columnName+";\n");
        fieldSetInfo.append("\t}\n");

        fieldSetGet.setFieldInfo(fieldInfo);
        fieldSetGet.setFieldGetInfo(fieldGetInfo.toString());
        fieldSetGet.setFieldSetInfo(fieldSetInfo.toString());

        return fieldSetGet;
    }


    /**
     * 根据表结构生成对应的pojo
     * @param tableInfo 表信息
     * @param typeConvertor java与数据库字段类型转化器
     * @param author 生成的Pojo的作者
     * @return pojo字符串
     */
    private static String generateJavaSrc(@NotNull TableInfo tableInfo,
                                         @NotNull TypeConvertor typeConvertor,String author){

        if(tableInfo==null || typeConvertor == null){
            throw new SormException("tableInfo or TypeConvertor is null");
        }

        StringBuilder javaResourse = new StringBuilder();
        // 生成package 例如 package cn.gxm.sorm.pojo
        javaResourse.append("package "+ DBManager.getConfiguration().getPojoPackage()+";\n\n");

        // 生成import 例如 import java.sql.Date
        javaResourse.append("import java.sql.*;\n");
        javaResourse.append("import java.lang.*;\n");

        // 生成autor 以及日期等java文件说明
        /**
         * @author GXM www.guokangjie.cn
         * @date 2019/5/17
         */
        javaResourse.append("/**\n")
                .append(" * @author "+author+"\n")
                .append(" * @date "+new SimpleDateFormat("yyyy/MM/dd").format(new Date())+"\n")
                .append(" */\n");
        // 生成类的开始 例如 public class Emp {
        javaResourse.append("public class "+StringUtils.toUpperCaseHeadChar(tableInfo.getName())+"{\n");

        // 生成类的属性
        Set<Map.Entry<String, ColumnInfo>> entries = tableInfo.getColumns().entrySet();
        for (Map.Entry<String, ColumnInfo> entry: entries){
            JavaFieldSetGet fieldSetGet = generateJavaFieldSetGet(entry.getValue(), typeConvertor);
            javaResourse.append(fieldSetGet+"\n");
        }

        // 生成类的结束  }
        javaResourse.append("}");
        return javaResourse.toString();

    }


    /**
     * 生成与数据库字段匹配的java的pojo
     * @param tableInfo 表信息
     * @param typeConvertor java与数据库字段类型转化器
     * @param author 生成的Pojo的作者
     */
    public static void generatePOJOFile(TableInfo tableInfo,TypeConvertor typeConvertor,String author){
        String javaSrc = generateJavaSrc(tableInfo, typeConvertor, author);
        String dirPath = DBManager.getConfiguration().getSrcPath()+"//"+DBManager.getConfiguration().getPojoPackage().replaceAll("\\.","//");
        File pojoDir = new File(dirPath);
        //包不存在则创建包
        if(!pojoDir.exists()){
            pojoDir.mkdirs();
        }

        Writer writer = null;
        try {
            writer = new FileWriter(pojoDir.getAbsoluteFile()+"//"+StringUtils.toUpperCaseHeadChar(tableInfo.getName())+".java");
            writer.write(javaSrc);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                writer.close();
                System.out.println("pojo创建成功!!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
//        ColumnInfo columnInfo = new ColumnInfo("id","varchar",1);
//        JavaFieldSetGet fieldSetGet = JavaFileUtils.generateJavaFieldSetGet(
//                columnInfo, new MySqlTypeConvertor());
//        System.out.println(fieldSetGet);
        String emp = generateJavaSrc(TableContext.getAllTables().get("emp"),
                new MySqlTypeConvertor(), "GXM www.guokangjie.cn");
        System.out.println(emp);
    }
}
