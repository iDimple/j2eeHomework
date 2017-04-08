package com.action.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.action.BaseAction;
import com.model.User;
import com.service.LoginService;
import com.util.UserBase;
import com.util.UserState;


/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年3月5日 下午1:55:38
 */
public class MemberAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6965717626547609130L;
	private Map<String, String> map = new HashMap<String, String>();
	@Autowired
	private LoginService loginService;
	private User user;
	private String passwordOld;
	private String passwordNew;
	private String passwordNew2;


	public Map<String, String> getMap() {
		return map;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	public String getPasswordOld() {
		return passwordOld;
	}
	public void setPasswordOld(String passwordOld) {
		this.passwordOld = passwordOld;
	}
	public String getPasswordNew() {
		return passwordNew;
	}
	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
	}
	public String getPasswordNew2() {
		return passwordNew2;
	}
	public void setPasswordNew2(String passwordNew2) {
		this.passwordNew2 = passwordNew2;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	//我的账户
	public String myAccount(){
		System.out.println("我的账户");
		UserBase userBase = (UserBase) sessionMap.get("userBase");
		user = loginService.getUserById(userBase.getId());
		return SUCCESS;
	}
	//保存修改过的我的账户
	public String saveAccount(){
		System.out.println("保存修改我的账户");
		UserBase userBase = (UserBase) sessionMap.get("userBase");
		user.setId(userBase.getId());
		System.out.println(user.getId());
		System.out.println(user.getBankId());
		System.out.println(user.getName());
		System.out.println(user.getBirthday());
		loginService.updateUser(user);
		return SUCCESS;
	}
	
	public String changePassword(){
		System.out.println("保存修改密码");
		String message = "";
		UserBase userBase = (UserBase) sessionMap.get("userBase");
			if (loginService.isValid(userBase.getId(), passwordOld)) {
				System.out.println("两次密码输入相同");
				if(passwordNew.equals(passwordNew2)){
					user.setPassword(passwordNew);
					loginService.changePassword(user);
					return "success";
				}else{
					message = "两次输入密码不同";
				}
			} else {
				System.out.println("老密码输错！");
				message = "老密码输错！";
		}
			sessionMap.put("registerMsg", message);
		return "error";
	}
	//激活账户,默认点了这个按钮将state改成NORMAL("正常使用")，balance加1000
	public String active(){
		UserBase userBase = (UserBase) sessionMap.get("userBase");
		user = loginService.getUserById(userBase.getId());
		user.setState(UserState.NORMAL.toString());
		user.setBalance(user.getBalance()+1000);
		loginService.updateAll(user);
		return SUCCESS;
	}
	//账户充值
	public String chongzhi(){
		double amountInput = Double.parseDouble(map.get("amount"));
		System.out.println(amountInput);
		UserBase userBase = (UserBase) sessionMap.get("userBase");
		user = loginService.getUserById(userBase.getId());
		user.setState(UserState.NORMAL.toString());
		user.setBalance(user.getBalance()+amountInput);
		loginService.updateAll(user);
		map.put("balance", String.valueOf(user.getBalance()));
		return SUCCESS;
	}
	//取消会员资格
		public String cancelmember(){
			//将状态改为stop
			UserBase userBase = (UserBase) sessionMap.get("userBase");
			user = loginService.getUserById(userBase.getId());
			user.setState(UserState.STOP.toString());
			loginService.updateAll(user);
			return SUCCESS;
		}
		//兑换积分，100积分1元，不满100也清0
		public String duihuan(){
			UserBase userBase = (UserBase) sessionMap.get("userBase");
			user = loginService.getUserById(userBase.getId());
			int yuan=(int) (user.getPoint()/100);
			System.out.println(yuan);
			user.setBalance(user.getBalance()+yuan);
			user.setPoint(0);
			loginService.updateAll(user);
			return SUCCESS;
		}
}
