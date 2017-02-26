package com.tag;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年1月2日 下午5:17:33
 */
public class CheckLogin extends TagSupport{
	private static final long serialVersionUID = 1L;
	private boolean logIn = false;
	private HttpServletResponse response;
	/**
	 * 遇到标签开始时调用，判断用户是否登录
	 * 
	 * @return EVAL_BODY_INCLUDE:显示标签间的文字，即标签体 SKIP_BODY:不显示标签间的文字
	 */
	public int doStartTag() throws JspException {
		// 得到 pageContext对象 因为所有的值都放在pageContext里面
		HttpServletRequest request = (HttpServletRequest) this.pageContext
				.getRequest();
		response=(HttpServletResponse) this.pageContext
				.getResponse();
		HttpSession session = request.getSession(false);
//TODO
		if (session!=null&&session.getAttribute("login")!=null) {
			logIn = true;
		}
		return TagSupport.SKIP_BODY;
	}

	/**
	 * 遇到标签结束时调用
	 * 
	 * @return EVAL_PAGE，处理完标签后继续执行以下的JSP页面 SKIP_PAGE，不处理接下来的JSP页面
	 */
	public int doEndTag() throws JspException {
		if (logIn) {
			return Tag.EVAL_PAGE;
		} else {//返回登陆页面
			try {
				System.out.println("返回登陆页面");
				response.sendRedirect("/Homework4/view/Login.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return Tag.SKIP_PAGE;
		}

	}

	/**
	 * 显示完标签间文字之后调用
	 * 
	 * @return EVAL_BODY_AGAIN，再显示一次标签间的文字 SKIP_BODY，继续执行标签处理的下一步
	 */
	public int doAfterBody() throws JspException {
		return super.doAfterBody();
	}
}
