package cn.gxm.sorm.pojo;

import java.sql.*;
import java.lang.*;
/**
 * @author GXM www.guokangjie.cn
 * @date 2019/05/17
 */
public class Emp{
	private String empName;
	public  String getEmpName (){
		return empName;
	}
	public void setEmpName (String empName){
		this.empName=empName;
	}

	private Integer deptId;
	public  Integer getDeptId (){
		return deptId;
	}
	public void setDeptId (Integer deptId){
		this.deptId=deptId;
	}

	private java.sql.Date birthady;
	public  java.sql.Date getBirthady (){
		return birthady;
	}
	public void setBirthady (java.sql.Date birthady){
		this.birthady=birthady;
	}

	private Integer id;
	public  Integer getId (){
		return id;
	}
	public void setId (Integer id){
		this.id=id;
	}

	private Double salary;
	public  Double getSalary (){
		return salary;
	}
	public void setSalary (Double salary){
		this.salary=salary;
	}

	private Integer age;
	public  Integer getAge (){
		return age;
	}
	public void setAge (Integer age){
		this.age=age;
	}

	@Override
	public String toString() {
		return "Emp{" +
				"empName='" + empName + '\'' +
				", deptId=" + deptId +
				", birthady=" + birthady +
				", id=" + id +
				", salary=" + salary +
				", age=" + age +
				'}';
	}
}