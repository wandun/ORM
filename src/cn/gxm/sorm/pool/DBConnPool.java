package cn.gxm.sorm.pool;

import cn.gxm.sorm.core.DBManager;
import cn.gxm.sorm.utils.JDBCUtils;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author GXM www.guokangjie.cn
 * @date 2019/5/19
 *
 * 管理数据库的连接的连接池
 */
public class DBConnPool {
    /**
     * 连接池对象
     */
    private static List<Connection> pool;

    /**
     * 连接池中连接的最大连接数
     */
    private static Integer maxConnection = DBManager.getConfiguration().getMaxConnection();
    /**
     * 连接池中连接的最小连接数
     */
    private static Integer minConnection = DBManager.getConfiguration().getMinConnection();

    static {
        pool = new LinkedList<>();
        initPool();
    }

    /**
     * 初始化连接池
     */
    private static void initPool(){
        for(int i=0;i<maxConnection;i++){
            pool.add(DBManager.createConnection());
        }
    }

    /**
     * 从连接词中获取连接
     * @return
     */
    public static synchronized Connection poolCreateConnection(){
        // 连接池中的连接数量已经小于最低值,需要增加connection
        while (pool.size()<minConnection){
            pool.add(DBManager.createConnection());
        }

        Connection connection = pool.get(pool.size()-1);
        //从连接池中关闭
        pool.remove(connection);
        return connection;
    }

    /**
     * 从连接池中关闭连接
     * @param connection
     */
    public static void poolCloseConnection(Connection connection){
        // 连接池中的连接已经超过最大值，就真的关闭连接
        if(pool.size()>maxConnection){
            JDBCUtils.close(null,null,connection);
            return;
        }
        pool.add(connection);
    }

}
