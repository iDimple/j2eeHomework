package com.action;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.model.User;
import com.service.LoginService;
import com.util.UserBase;
import com.util.UserCategory;

/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年2月26日 下午3:51:35
 */
//@Controller用于标注控制层组件（如struts中的action）
@Controller
public class LoginAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private User user;
	
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Autowired
	private  LoginService loginService;

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
		
		
		//如果登录是有效登录
		
			String id=user.getId();
			String password=user.getPassword();
			boolean isValid=loginService.isValid(id,password);
			if(isValid){//数据库里有这个人
				user=loginService.getUserById(id);
				session.setAttribute("id", id);
				String username=loginService.getName(id);
				session.setAttribute("username",username );
				System.out.println(id);
				System.out.println(username);
				System.out.println("即将跳转");
				UserBase userBase = new UserBase(user);
				super.sessionMap.put("userBase", userBase);
//不同角色跳转不同
				switch (userBase.getCategory()) {
				case UserCategory.MENBER:
					System.out.println("memeber");
					return "member";
				case UserCategory.WAITER:
					System.out.println("hostel");
					return "waiter";
				case UserCategory.MANAGER:
					System.out.println("manager");
					return "manager";
				}
			}else{//数据库里没有这个人，错误页面，提示用户名密码错误
				System.out.println("用户名密码错误");
				return "error";
			}
			System.out.println("登陆时出错");
			return "error";
	}
	}
	
