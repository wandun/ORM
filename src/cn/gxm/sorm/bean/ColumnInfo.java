package cn.gxm.sorm.bean;

/**
 * @author GXM www.guokangjie.cn
 * @date 2019/5/15
 *
 * 对应数据库中的每一个字段结构
 */
public class ColumnInfo {

    /**
     * 字段名称 例如 id,name,age
     */
    private String name;

    /**
     * 字段类型 例如 varchar
     */
    private String dataType;

    /**
     * 字段键类型 例如 1：普通键 2：主键 3：外键
     */
    private Integer keyType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getKeyType() {
        return keyType;
    }

    public void setKeyType(Integer keyType) {
        this.keyType = keyType;
    }

    @Override
    public String toString() {
        return "ColumnInfo{" +
                "name='" + name + '\'' +
                ", dataType='" + dataType + '\'' +
                ", keyType=" + keyType +
                '}';
    }

    public ColumnInfo() {
    }

    public ColumnInfo(String name, String dataType, Integer keyType) {
        this.name = name;
        this.dataType = dataType;
        this.keyType = keyType;
    }
}
