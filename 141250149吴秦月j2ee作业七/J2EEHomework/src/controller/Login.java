package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CourseList;
import model.LoginHelper;
import model.Student;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
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
			System.out.println("输好用户名和密码l ");
			//正确登陆
			String sid=request.getParameter("sid");
			String password=request.getParameter("password");
			Student student=new Student(sid,password);
			boolean isValid=student.isValid();
			ServletContext context = getServletContext();
			String url="";

			if(isValid){//数据库里有这个学生,转到课程列表页面
				session.setAttribute("sid", sid);
				session.setAttribute("username", student.getUsername());
				CourseList courseList=new CourseList(student.getCourseList());
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
