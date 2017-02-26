package com.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.model.CourseList;
import com.service.CourseService;
import com.service.LoginService;
import com.util.LoginHelper;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ApplicationContext appliationContext;
	private static LoginService loginService;
	private static CourseService courseService;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
	}
	public void init() throws ServletException {  
		super.init();
		appliationContext=new ClassPathXmlApplicationContext("applicationContext.xml"); 
		loginService=(LoginService)appliationContext.getBean("loginService");
		courseService=(CourseService)appliationContext.getBean("courseService");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		boolean isLogout=false;
		//判断之前是否有退出
		if (null != request.getParameter("Logout")) {
			if (null != session) {
				session.invalidate();
				session = null;
			}
			System.out.println("logout!");
			isLogout=true;
		}
		//判断是否登陆，不存在不会创建
		boolean loginResult = LoginHelper.isValidLogin(request,response);
		if(isLogout){
			session.setAttribute("logout", "logout");
		}
		//如果登录是有效登录
		if(loginResult){
			//正确登陆
			String sid=request.getParameter("sid");
			String password=request.getParameter("password");
			boolean isValid=loginService.isValid(sid,password);


			ServletContext context = getServletContext();
			String url="";

			if(isValid){//数据库里有这个学生,转到课程列表页面
				session.setAttribute("sid", sid);
				//因为是无状态的所以取不到
				session.setAttribute("username", loginService.getName(sid));
				System.out.println(sid);
				System.out.println(loginService.getName(sid));
				CourseList courseList=new CourseList(courseService.getCourseList(sid));
				session.setAttribute("courseList", courseList);
				System.out.println("即将跳转");
				url = "/view/CourseList.jsp";
				System.out.println("成功跳转");
			}else{//数据库里没有这个学生，错误页面，提示用户名密码错误
				url = "/view/NoSuchUser.jsp";
			}
			context.getRequestDispatcher(url).forward(request, response);
		}
	}
}
