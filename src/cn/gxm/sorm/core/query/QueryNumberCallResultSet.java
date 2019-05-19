package cn.gxm.sorm.core.query;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author GXM www.guokangjie
 * @date 2019/5/19
 *
 * 处理ResultSet查询一行一列的结果集
 */
class QueryNumberCallResultSet implements CallResultSet{

    @Override
    public <T> Object handleResultSet(ResultSet resultSet, Class<T> clazz) {
        try {
            while (resultSet.next()){
                return resultSet.getObject(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}