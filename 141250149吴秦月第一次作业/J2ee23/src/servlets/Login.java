package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import connection.SQLHelper;
import model.LoginHelper;
import model.Student;
/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource dataSource = null; 
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dataSource = SQLHelper.getDataSource();
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
		request.setCharacterEncoding("utf-8");

		String login = "";
		HttpSession session = request.getSession(false);
		Cookie cookie = null;
		Cookie[] cookies = request.getCookies();

		if (null != cookies) {
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if (cookie.getName().equals("LoginCookie")) {
					login = cookie.getValue();
					break;
				}
			}
		}

		if (null != request.getParameter("Logout")) {
			if (null != session) {
				session.invalidate();
				session = null;
			}
		}
		
		
		
		//判断是否登陆，不存在不会创建
		boolean loginResult = LoginHelper.logInCookieAndSession(request,response, "LoginCookie", "sid", request.getContextPath()+"/user/Login.jsp");

		//如果登录是有效登录
		if(loginResult){
			String sid=request.getParameter("sid");
			String password=request.getParameter("password");

			RequestDispatcher rd;

			Student student=new Student(sid,password);
			if(student.isValid(dataSource)){//数据库里有这个学生,转到成绩显示页面
				session.setAttribute("username", student.getUsername());
				session.setAttribute("homework", student.getHomework());
				rd=request.getRequestDispatcher("user/Homework.jsp");
				rd.forward(request, response);
			}else{//数据库里没有这个学生，错误页面，提示用户名密码错误
				rd=request.getRequestDispatcher("user/NoSuchUser.jsp");
				rd.forward(request, response);
			}
		}

	}

}
