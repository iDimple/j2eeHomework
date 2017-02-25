package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginHelper;
import model.Result;

/**
 * Servlet implementation class ShowResults
 */
@WebServlet("/ShowResults")
public class ShowResults extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowResults() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean loginResult = LoginHelper.checkLogIn(request, response, "LoginCookie", "/user/Login.jsp");	
		if(loginResult){
			HttpSession session=request.getSession(false);
			String username=(String) session.getAttribute("username");


			PrintWriter out=response.getWriter();
			out.println("<html><body>");
			switch (request.getParameter("result")) {
			case "unsubmitted":
				out.println("<p>你好： " + username + "</p>");
				out.println("<p>当前课程:  " + request.getParameter("cid") + "</p>");
				out.println("<p>您有未提交的作业，请及时提交！</p>");
				break;
			case "passed":
				out.println("<p>你好： " + username + "</p>");
				out.println("<p>当前课程:  " + request.getParameter("cid") + "</p>");
				out.println("<p>您的作业已按时提交并通过啦~</p>");
				break;
			case "failed":
				out.println("<p>你好： " + username + "</p>");
				out.println("<p>当前课程:  " + request.getParameter("cid") + "</p>");
				out.println("<p>您的作业不及格，请及时修改订正哦~</p>");
				break;
			default:
				out.println("<p>网页发生了错误/(ㄒ_ㄒ)/~~</p>");
				break;
			}
			out.println("<form method='GET' action='" + response.encodeURL(request.getContextPath() + "/user/Homework.jsp")	+ "'>");
			out.println("<input type='submit' name='Back' value='Back'>");
			out.println("</form>");
			out.println("</form>");
			out.println("<form method='GET' action='" + response.encodeURL(request.getContextPath() + "/Login")	+ "'>");
			out.println("<input type='submit' name='Logout' value='Logout'>");
			out.println("</form>");
			//==============展示人数=====================================
			ServletContext Context = getServletContext();
			int allNumCounter = Integer.parseInt((String) Context
					.getAttribute("allNumCounter"));
			int onlineCounter = Integer.parseInt((String) Context
					.getAttribute("onlineCounter"));
			int guestCounter = Integer.parseInt((String) Context
					.getAttribute("guestCounter"));
			String message = "在线总人数：" + allNumCounter + "，在线登陆人数："
					+ onlineCounter + "，在线游客数：" + guestCounter;
			out.println("<p>" + message + "</p>");
			//=========================================================
			out.println("</body></html>");
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
