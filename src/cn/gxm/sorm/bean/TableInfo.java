package cn.gxm.sorm.bean;

import java.util.List;
import java.util.Map;

/**
 * @author GXM www.guokangjie.cn
 * @date 2019/5/15
 *
 * 对应数据库中的表结构
 */
public class TableInfo {


    /**
     * 表的名称
     */
    private String name;

    /**
     * 表中的字段,使用map为方便后期获取
     */
    private Map<String,ColumnInfo> columns;

    /**
     * 表中的唯一主键
     */
    private ColumnInfo onlyPriKey;

    /**
     * 联合主键即（多个字段组成的主键）
     */
    private List<ColumnInfo> unionKey;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, ColumnInfo> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, ColumnInfo> columns) {
        this.columns = columns;
    }

    public ColumnInfo getOnlyPriKey() {
        return onlyPriKey;
    }

    public void setOnlyPriKey(ColumnInfo onlyPriKey) {
        this.onlyPriKey = onlyPriKey;
    }

    public List<ColumnInfo> getUnionKey() {
        return unionKey;
    }

    public void setUnionKey(List<ColumnInfo> unionKey) {
        this.unionKey = unionKey;
    }

    @Override
    public String toString() {
        return "TableInfo{" +
                "name='" + name + '\'' +
                ", columns=" + columns +
                ", onlyPriKey=" + onlyPriKey +
                ", unionKey=" + unionKey +
                '}';
    }
}
