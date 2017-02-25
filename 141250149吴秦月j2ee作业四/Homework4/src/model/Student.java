package model;

import java.util.ArrayList;
import dao.HomeworkDao;
import daoFactory.DaoFactory;

/**
* @author 141250149吴秦月
* @date 创建时间：2017年1月2日 下午2:29:12
*/
public class Student {
	private String sid;
	private String password;
	private String username;
	private HomeworkDao homeworkDao=DaoFactory.getHomeworkDao();
	private ArrayList<String> courseList;
	
	public ArrayList<String> getCourseList() {
		return courseList;
	}
	public void setCourseList(ArrayList<String> courseList) {
		this.courseList = courseList;
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
	public boolean isValid(){
		boolean isValid=homeworkDao.isValid(sid,password);
		if(!isValid){//用户名密码错误
			return false;
		}
		//id密码正确
		//得到用户名
		username=homeworkDao.getUsername();
		//得到courselist
		courseList=homeworkDao.getCourseList(sid);
		return isValid;
	}
}
