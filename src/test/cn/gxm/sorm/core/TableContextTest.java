package test.cn.gxm.sorm.core; 

import cn.gxm.sorm.bean.TableInfo;
import cn.gxm.sorm.core.TableContext;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.Map;

/** 
* TableContext Tester. 
* 
* @author
* @since
* @version 1.0 
*/ 
public class TableContextTest { 

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: generateOrUpdateJavaFilePojo(String author)
    *
    */
    @Test
    public void testGenerateOrUpdateJavaFilePojo() throws Exception {
        TableContext.generateOrUpdateJavaFilePojo("GXM www.guokangjie.cn");
    }

    /**
    *
    * Method: getAllTables()
    *
    */
    @Test
    public void testGetAllTables() throws Exception {

    }

    /**
    *
    * Method: getClazzAllTable()
    *
    */
    @Test
    public void testGetClazzAllTable() throws Exception {
        Map<Class, TableInfo> clazzAllTable = TableContext.getClazzAllTable();
        System.out.println(clazzAllTable);
    }


} 
