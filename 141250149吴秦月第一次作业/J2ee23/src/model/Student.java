package model;

import javax.sql.DataSource;

/**
 * @author 141250149吴秦月
 * @date 创建时间：2016年12月22日 上午10:56:30
 */
public class Student {
	private String sid;
	private String password;
	private String username;
	private Homework homework;
	
	public Homework getHomework() {
		return homework;
	}
	public void setHomework(Homework homework) {
		this.homework = homework;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Student(String sid, String password) {
		super();
		this.sid = sid;
		this.password = password;
	}
	public boolean isValid(DataSource dataSource){
		Data data=new Data(dataSource);
		boolean isValid=data.isValid(sid,password);
		if(!isValid){//用户名密码错误
			return false;
		}
		//用户名密码正确
		username=data.getUsername();
		homework=data.getHomework();
		return isValid;
	}
}
