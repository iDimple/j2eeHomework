package model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 141250149吴秦月
 * @date 创建时间：2016年12月23日 上午10:32:55
 */
public class LoginHelper {
	/**
	 * 检查用户是否登录，登录信息同时存储在cookie和session中
	 * 有些就是加不上cookie
	 * @param request
	 * @param response
	 * @param cookieName cookie的名称
	 * @param url 跳转到登录界面的url，如：request.getContextPath()+"/Index"
	 * @return 是否登录
	 * @throws ServletException
	 * @throws IOException
	 */
	public static boolean checkLogIn(HttpServletRequest request, HttpServletResponse response, String cookieName, String url) throws ServletException, IOException {
		boolean cookieFound = false;
		Cookie cookie = null;
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				System.out.println(cookie.getName());
				if (cookie.getName().equals(cookieName)) {
					cookieFound = true;
					break;
				}
			}
		}
		System.out.println("Cookies Found?"+cookieFound);

		HttpSession session = request.getSession(false);
		//gai
		if(session==null){
			request.getRequestDispatcher(url).forward(request, response);
			return false;
		}
		if (cookieFound || session != null) {
			return true;
		} 


		return false;
	}

	/**
	 * 登录过程中的cookie、session以及对request参数的设置，cookie名另外传，
	 * session和request参数的变量名全部是login
	 * 
	 * @param request
	 * @param response
	 * @param cookieName cookie的名称
	 * @param requestParameter request传来的登录名的参数名称
	 * @param url 跳转到的登录的地址，如"/Index";
	 * @return 登录是否有效（如果用户没有输入studentID则为无效登录）
	 * @throws IOException
	 * @throws ServletException
	 */
	public static boolean logInCookieAndSession(HttpServletRequest request,HttpServletResponse response, String cookieName,String requestParameter, String url) throws IOException,ServletException {
		boolean cookieFound = false;
		Cookie cookie = null;
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				System.out.println("LoginHelper cookie name:"+cookie.getName());
				if (cookie.getName().equals(cookieName)) {
					cookieFound = true;
					break;
				}
			}
		}
		System.out.println("Cookies Found?"+cookieFound);

		HttpSession session = request.getSession(false);
		System.out.println("Session Found?"+(session!=null));

		if (session == null) {//如果会话不存在
			String loginValue = request.getParameter(requestParameter);
			boolean isLoginAction = (loginValue == null) ? false : true;
			if (isLoginAction) { // 如果用户输入了studentID
				if (cookieFound) { // 如果cookie存在, 检查一下它的值有没有改变
					if (!loginValue.equals(cookie.getValue())) {// 如果loginValue改变了, 重置它的值
						cookie.setValue(loginValue);
						response.addCookie(cookie);
						System.out.println("Change a new value "+loginValue);
					}
					else{ // 如果loginValue没改变, 什么也不做
						System.out.println("Do not do anything ");
					}
				}
				else { // 如果cookie不存在，创建一个新的cookie并赋值
					cookie = new Cookie(cookieName, loginValue);
					cookie.setMaxAge(Integer.MAX_VALUE);
					response.addCookie(cookie);
					System.out.println("Create a new cookie and set value ");
				}

				// 创建一个新的session
				session = request.getSession(true);
				session.setAttribute("login", loginValue);
				request.setAttribute("login", loginValue);

				return true;
			} 

			else {// 如果用户没有输入studentID, 重定向到登录界面
				response.sendRedirect(url);
			}
		}
		else { // 如果会话已经存在
			String loginValue = (String) session.getAttribute("login");
			if (loginValue == null || loginValue != request.getParameter("login")) {// 如果loginValue改变了, 重置它的值
				loginValue = request.getParameter(requestParameter);
				session.setAttribute("login", loginValue);
			}
			request.setAttribute("login", loginValue);
			return true;
		}
		return false;
	}
}
