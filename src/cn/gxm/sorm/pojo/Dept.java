package cn.gxm.sorm.pojo;

import java.sql.*;
import java.lang.*;
/**
 * @author GXM www.guokangjie.cn
 * @date 2019/05/18
 */
public class Dept{
	private String deptName;
	public  String getDeptName (){
		return deptName;
	}
	public void setDeptName (String deptName){
		this.deptName=deptName;
	}

	private Integer id;
	public  Integer getId (){
		return id;
	}
	public void setId (Integer id){
		this.id=id;
	}

	@Override
	public String toString() {
		return "Dept{" +
				"deptName='" + deptName + '\'' +
				", id=" + id +
				'}';
	}
}