package test.cn.gxm.sorm.core; 

import cn.gxm.sorm.core.DBManager;
import cn.gxm.sorm.core.query.MysqlQuery;
import cn.gxm.sorm.core.TableContext;
import cn.gxm.sorm.pojo.Emp;
import cn.gxm.sorm.vo.EmpToDept;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.sql.Date;
import java.util.List;

/** 
* MysqlQuery Tester. 
* 
* @author GXM www.guokangjie.cn
* @since
* @version 1.0 
*/ 
public class MysqlQueryTest { 
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
    * Method: executeDML(String sql, Object[] params)
    *
    */
    @Test
    public void testExecuteDML() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: insert(Object table)
    *
    */
    @Test
    public void testInsert() throws Exception {
        Emp emp = new Emp();
        emp.setAge(22);
        emp.setBirthady(new Date(19200000));
        emp.setEmpName("阿豹");
        emp.setSalary(160000d);
        mysqlQuery.insert(emp);
    }

    /**
    *
    * Method: delete(Class clazz, Object primaryKey)
    *
    */
    @Test
    public void testDeleteForClazzPrimaryKey() throws Exception {
        mysqlQuery.delete(Emp.class,1);
    }

    /**
    *
    * Method: delete(Object table)
    *
    */
    @Test
    public void testDeleteTable() throws Exception {
        Emp emp = new Emp();
        emp.setId(2);
        mysqlQuery.delete(emp);
    }

    /**
    *
    * Method: update(Object table, String[] params)
    *
    */
    @Test
    public void testUpdate() throws Exception {
        Emp emp = new Emp();
        emp.setId(3);
        emp.setSalary(20000d);
        emp.setEmpName("明正");
        int update = mysqlQuery.update(emp, new String[]{"salary", "empName"});
        System.out.println(update!=0);
    }

    /**
    *
    * Method: queryRows(String sql, Class clazz, String[] params)
    *
    */
    @Test
    public void testQueryRows() throws Exception {
        List queryRows = mysqlQuery.queryRows("select empName,age from emp where age>? and age<?",
                Emp.class, new String[]{"21", "30"});
        System.out.println(queryRows);

//        String sql = "SELECT e.id empId,e.empName,d.deptName,d.id deptId FROM emp AS e LEFT JOIN dept AS d ON e.deptId = d.id WHERE d.id > ?";
//        List queryRows1 = mysqlQuery.queryRows(sql, EmpToDept.class, new String[]{"1"});
//        System.out.println(queryRows1);
    }

    /**
    *
    * Method: queryOne(String sql, String[] params)
    *
    */
    @Test
    public void testQueryOne() throws Exception {
        String sql = "SELECT id,empName FROM emp WHERE id=?";
        Object queryOne = mysqlQuery.queryOne(sql, Emp.class, new String[]{"1"});
        System.out.println(queryOne);
    }

    /**
    *
    * Method: queryNumber(String sql, String[] params)
    *
    */
    @Test
    public void testQueryNumber() throws Exception {
        String sql = "SELECT COUNT(*) FROM emp where deptId>?";
        Number number = mysqlQuery.queryNumber(sql, new String[]{"1"});
        System.out.println(number);
    }

} 
