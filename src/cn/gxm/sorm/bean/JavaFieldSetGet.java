package cn.gxm.sorm.bean;

/**
 * @author GXM www.guokangjie.cn
 * @date 2019/5/17
 *
 * 每一个pojo都有的java属性，set,get等等
 */
public class JavaFieldSetGet {

    /**
     * 例如  (private String id)
     */
    private String fieldInfo;

    /**
     * 例如
     * public String getId(){
     *     return id;
     * }
     */
    private String fieldGetInfo;

    /**
     * 例如
     * public void setId(String id){
     *     this.id = id;
     * }
     */
    private String fieldSetInfo;


    public String getFieldInfo() {
        return fieldInfo;
    }

    public void setFieldInfo(String fieldInfo) {
        this.fieldInfo = fieldInfo;
    }

    public String getFieldGetInfo() {
        return fieldGetInfo;
    }

    public void setFieldGetInfo(String fieldGetInfo) {
        this.fieldGetInfo = fieldGetInfo;
    }

    public String getFieldSetInfo() {
        return fieldSetInfo;
    }

    public void setFieldSetInfo(String fieldSetInfo) {
        this.fieldSetInfo = fieldSetInfo;
    }

    @Override
    public String toString() {
        return fieldInfo+fieldGetInfo+fieldSetInfo;
    }
}
