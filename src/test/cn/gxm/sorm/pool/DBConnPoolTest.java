package test.cn.gxm.sorm.pool; 

import cn.gxm.sorm.core.DBManager;
import cn.gxm.sorm.core.TableContext;
import cn.gxm.sorm.core.query.MysqlQuery;
import cn.gxm.sorm.pojo.Emp;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* DBConnPool Tester. 
* 
* @author GXM www.guokangjie.cn
* @date 2019/5/19
* @version 1.0 
*/ 
public class DBConnPoolTest {
    private MysqlQuery mysqlQuery;
    @Before
    public void before() throws Exception {
        mysqlQuery = new MysqlQuery();
        // 测试之前将 db.properties加载到对应映射类中
        DBManager.getConnection();
        // 加载所有的表信息
        TableContext.getClazzAllTable();
    }

    @After
    public void after() throws Exception {

    }

    /**
    *
    * Method: poolCreateConnection()
    *
    */
    @Test
    public void testPoolCreateConnection() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: poolCloseConnection(Connection connection)
    *
    */
    @Test
    public void testPoolCloseConnection() throws Exception {
        long start = System.currentTimeMillis();
        String sql = "SELECT id,empName FROM emp WHERE id=?";
        for (int i=0;i<3000;i++){
            Object queryOne = mysqlQuery.queryOne(sql, Emp.class, new String[]{"1"});
            System.out.println(queryOne);
        }
        long end = System.currentTimeMillis();
        // 使用连接池 2737 不用连接池 21112
        System.out.println(end-start);
    }


    /**
    *
    * Method: initPool()
    *
    */
    @Test
    public void testInitPool() throws Exception {
    //TODO: Test goes here...
    /*
    try {
       Method method = DBConnPool.getClass().getMethod("initPool");
       method.setAccessible(true);
       method.invoke(<Object>, <Parameters>);
    } catch(NoSuchMethodException e) {
    } catch(IllegalAccessException e) {
    } catch(InvocationTargetException e) {
    }
    */
    }

} 
