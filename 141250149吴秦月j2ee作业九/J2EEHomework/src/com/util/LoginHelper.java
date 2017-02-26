
package com.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年1月3日 下午4:42:38
 */
public class LoginHelper {

	//这个给LoginServlet用，判断是否从.jsp过来
	//session的attribute的login关系着计数
	public static boolean isValidLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException,ServletException {
		HttpSession session=request.getSession(false);
		if(session==null||session.getAttribute("login")==null){//退出再登陆,或第一次打开页面
			//通过有没有sid来判断是否从login.jsp过来
			String loginValue=request.getParameter("student.sid");
			if(loginValue!=null){//user is logging in
				session=request.getSession();
				session.setAttribute("login", loginValue);
				request.setAttribute("login", loginValue);
				// 10分钟后失效，如果用户不访问
				session.setMaxInactiveInterval(10*60);
				return true;
			}else{//不是从login.jsp到loginServlet的都跳转到登陆页面
				response.sendRedirect(request.getContextPath()+"/view/Login.jsp");
			}
		}else{//有session说明肯定是从某个jsp过来
			String loginValue=(String) session.getAttribute("login");
			session.setAttribute("login", loginValue);
			request.setAttribute("login", loginValue);
			return true;
		}
		return false;

	}
	//检查登陆状态，就是是否在没登陆的时候访问其它sevlet
	public static boolean checkLoginState(HttpServletRequest request,
			HttpServletResponse response) throws IOException,ServletException {
		HttpSession session=request.getSession(false);
		if(session==null||session.getAttribute("login")==null||session.getAttribute("logout")!=null){
			response.sendRedirect(request.getContextPath()+"/view/Login.jsp");
			return false;
		}
		return true;
	}

}
