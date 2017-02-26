package com.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年2月26日 下午3:18:20
 */
public class BaseAction extends ActionSupport implements SessionAware,  
ServletRequestAware, ServletResponseAware {  

	private static final long serialVersionUID = 1L;  

	public HttpServletRequest   request;  
	public HttpServletResponse  response;  
	@SuppressWarnings("unchecked")  
	public Map sessionMap;  

	public void setSession(Map session) {  
		this.sessionMap = session;  
	}  

	public void setServletRequest(HttpServletRequest request) {  
		this.request = request;  
	}  

	public void setServletResponse(HttpServletResponse response) {  
		this.response = response;  
	}
}
