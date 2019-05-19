package cn.gxm.sorm.bean;

/**
 * @author GXM www.guokangjie.cn
 * @date 2019/5/15
 *
 * 映射资源文件（数据源 ，dataSource）
 */
public class Configuration {
    /**
     * 数据库驱动
     */
    private String driver;

    /**
     * 连接数据库url
     */
    private String url;

    /**
     * 数据库用户名
     */
    private String username;

    /**
     * 数据库密码
     */
    private String password;

    /**
     * 项目src绝对路径
     */
    private String srcPath;

    /**
     * 生成的表对象的包路径
     */
    private String pojoPackage;

    /**
     * 连接池最大的连接数量
     */
    private Integer maxConnection;

    /**
     * 连接池最小的连接数量
     */
    private Integer minConnection;

    @Override
    public String toString() {
        return "Configuration{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", srcPath='" + srcPath + '\'' +
                ", pojoPackage='" + pojoPackage + '\'' +
                '}';
    }

    public Configuration() {
    }

    public Configuration(String driver, String url, String username, String password, String srcPath, String pojoPackage) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
        this.srcPath = srcPath;
        this.pojoPackage = pojoPackage;
    }

    public Configuration(String driver, String url, String username, String password, String srcPath, String pojoPackage, Integer maxConnection, Integer minConnection) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
        this.srcPath = srcPath;
        this.pojoPackage = pojoPackage;
        this.maxConnection = maxConnection;
        this.minConnection = minConnection;
    }

    public Integer getMaxConnection() {
        return maxConnection;
    }

    public void setMaxConnection(Integer maxConnection) {
        this.maxConnection = maxConnection;
    }

    public Integer getMinConnection() {
        return minConnection;
    }

    public void setMinConnection(Integer minConnection) {
        this.minConnection = minConnection;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSrcPath() {
        return srcPath;
    }

    public void setSrcPath(String srcPath) {
        this.srcPath = srcPath;
    }

    public String getPojoPackage() {
        return pojoPackage;
    }

    public void setPojoPackage(String pojoPackage) {
        this.pojoPackage = pojoPackage;
    }
}
