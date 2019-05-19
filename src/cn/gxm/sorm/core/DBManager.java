package cn.gxm.sorm.core;

import cn.gxm.sorm.bean.Configuration;
import cn.gxm.sorm.pool.DBConnPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @author GXM www.guokangjie.cn
 * @date 2019/5/15
 *
 * 根据配置信息，维持连接对象的管理（增加连接词功能）
 */
public class DBManager {

    private static Configuration configuration = null;

    private static Properties properties = null;
    public DBManager() {
    }

    // 将数据加载到Configuration类中
    static {
        try {
            properties = new Properties();
            configuration = new Configuration();
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));

            configuration.setDriver(properties.getProperty("driver"));
            configuration.setUrl(properties.getProperty("url"));
            configuration.setUsername(properties.getProperty("username"));
            configuration.setPassword(properties.getProperty("password"));
            configuration.setSrcPath(properties.getProperty("srcPath"));
            configuration.setPojoPackage(properties.getProperty("pojoPackage"));
            configuration.setMaxConnection(Integer.parseInt(properties.getProperty("maxConnection")));
            configuration.setMinConnection(Integer.parseInt(properties.getProperty("minConnection")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从连接池中获取connection，向外提供connection
     * @return
     */
    public static Connection getConnection(){
        return DBConnPool.poolCreateConnection();
    }

    /**
     * 创建真正的连接
     * @return
     */
    public static Connection createConnection(){
        try {
            Class.forName(configuration.getDriver());
            return DriverManager.getConnection(configuration.getUrl(),
                    configuration.getUsername(), configuration.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从连接池中关闭连接
     */
    public static void closeConnection(Connection connection){
        DBConnPool.poolCloseConnection(connection);
    }

    /**
     * 返回db.properties对应的bean
     * @return
     */
    public static Configuration getConfiguration() {
        return configuration;
    }
}
