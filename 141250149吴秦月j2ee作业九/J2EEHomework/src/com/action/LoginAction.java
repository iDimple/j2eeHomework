package com.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.model.CourseList;
import com.model.Student;
import com.service.CourseService;
import com.service.LoginService;
import com.util.LoginHelper;

/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年2月26日 下午3:51:35
 */
//@Controller用于标注控制层组件（如struts中的action）
@Controller
public class LoginAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private Student student;
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Autowired
	private  LoginService loginService;
	@Autowired
	private  CourseService courseService;

	public String execute(){

		HttpSession session=request.getSession();
	
		//判断之前是否有退出
		boolean isLogout=false;
		if (null != request.getParameter("Logout")) {
			if (null != session) {
				session.invalidate();
				session = null;
			}
			System.out.println("logout!");
			isLogout=true;
		}
		if(isLogout){
			session.setAttribute("logout", "logout");
		}
		//判断是否登陆，不存在不会创建
		boolean loginResult=true;
		try {
			System.out.println("判断是否登陆");
			loginResult = LoginHelper.isValidLogin(request,response);
			System.out.println("loginresult"+loginResult);
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//如果登录是有效登录
		if(true){
			String sid=student.getSid();
			String password=student.getPassword();
			boolean isValid=loginService.isValid(sid,password);
			if(isValid){//数据库里有这个学生,转到课程列表页面
				session.setAttribute("sid", sid);
				String username=loginService.getName(sid);
				session.setAttribute("username",username );
				System.out.println(sid);
				System.out.println(username);
				CourseList courseList=new CourseList(courseService.getCourseList(sid));
				session.setAttribute("courseList", courseList);
				System.out.println("即将跳转");
				return "success";
			}else{//数据库里没有这个学生，错误页面，提示用户名密码错误
				return "error";
			}
		}
		return "login";
	}
	}
	
