package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.ResultService;
import com.util.LoginHelper;

/**
 * Servlet implementation class ShowResults
 */
@WebServlet("/ShowResults")
public class ShowResults extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ApplicationContext appliationContext;
	private static ResultService resultService;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowResults() {
		super();
	}
	public void init() throws ServletException {  
		super.init();
		appliationContext=new ClassPathXmlApplicationContext("applicationContext.xml"); 
		resultService=(ResultService)appliationContext.getBean("resultService");
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean loginResult = LoginHelper.checkLoginState(request, response);	
		if(loginResult){
			//已登陆
			String cid=request.getParameter("cid");
			HttpSession session=request.getSession(false);
			String sid=(String)session.getAttribute("sid");
			System.out.println(sid+cid);
			String result=resultService.getResult(sid, cid);
			System.out.println("result"+result);
			String msg="当前查看的课程为："+cid+"<br>";
			switch (result) {
			case "unsubmitted":
				msg+="您有未提交的作业，请及时提交！";
				break;
			case "passed":
				msg+="您的作业已按时提交并通过啦~";
				break;
			case "failed":
				msg+="您的作业不及格，请及时修改订正哦~";
				break;
			default:
				msg+="网页发生了错误/(ㄒ_ㄒ)/~~";
				break;
			}
			session.setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath()+"/view/ShowResult.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
