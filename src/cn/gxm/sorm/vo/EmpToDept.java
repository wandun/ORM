package cn.gxm.sorm.vo;

/**
 * @author GXM www.guokangjie.cn
 * @date 2019/5/18
 */
public class EmpToDept {
    private Integer empId;
    private String empName;
    private String deptName;
    private Integer deptId;

    @Override
    public String toString() {
        return "EmpToDept{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", deptName='" + deptName + '\'' +
                ", deptId=" + deptId +
                '}';
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}
