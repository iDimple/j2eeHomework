package com.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.model.User;
import com.service.LoginService;
import com.util.ResultMsg;
import com.util.UserBase;
import com.util.UserCategory;
import com.util.UserState;


/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年3月4日 下午4:57:40
 */
@Controller
public class RegisterAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7714801342635005623L;
	private User user;
	private String password2;
	@Autowired
	private  LoginService loginService;
	//这里的注册是会员注册
	public String execute(){
		String message = "";
		System.out.println(user.getPassword());
		System.out.println(password2);
		if (user.getPassword().equals(password2)) {
			System.out.println("两次密码输入相同");
		} else {
			message = "两次输入密码不同";
			sessionMap.put("registerMsg", message);
			return INPUT;
		}
		//会员
		user.setCategory(UserCategory.MENBER);
		//设置会员状态，一开始是未激活
		user.setState(UserState.INACTIVE.toString());
		ResultMsg result = loginService.register(user);
		if (result==ResultMsg.Success) {
			sessionMap.put("user", user);
			message =user.getId() ;
			sessionMap.put("registerMsg", message);
			UserBase userBase = new UserBase(user);
			super.sessionMap.put("userBase", userBase);
			return SUCCESS;
		} else {
			message = "注册失败，请重新注册";
			sessionMap.put("registerMsg", message);
			return INPUT;
		}

	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}

}
