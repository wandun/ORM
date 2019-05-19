package cn.gxm.sorm.utils;

import cn.gxm.sorm.core.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author GXM www.guokangjie.cn
 * @date 2019/5/15
 *
 * 封装数据库连接操作工具类
 */
public class JDBCUtils {

    /**
     * 处理执行sql式preparedStatement的?传参问题
     * @param preparedStatement PreparedStatement对象
     * @param params 参数值数组对象
     */
    public static void handleParams(PreparedStatement preparedStatement, Object[] params){
        // 注意PreparedStatement设置下表是从1开始的
        for (int i =0;i<params.length;i++){
            try {
                preparedStatement.setObject(i+1,params[i]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理jdbc关闭问题
     * @param preparedStatement PreparedStatement对象
     * @param connection    Connection 对象
     */
    public static void close(PreparedStatement preparedStatement,
                             ResultSet resultSet,
                             Connection connection){
        // 这里关闭的顺序需要注意一下，还有最重要的就是不能放在同一个try代码块里处理
        // 因为一旦前面的关闭错误，后面的会无法关闭

        try {
            if(resultSet!=null){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(preparedStatement!=null){
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //使用连接池的关闭
        DBManager.closeConnection(connection);

    }

}
