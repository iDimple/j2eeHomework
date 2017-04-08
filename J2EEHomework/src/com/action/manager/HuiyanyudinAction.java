package com.action.manager;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.action.BaseAction;
import com.model.User;
import com.service.LoginService;

/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年3月15日 下午3:44:35
 */
@Controller
public class HuiyanyudinAction extends BaseAction{
	@Autowired
	LoginService loginService;
	ArrayList<User> users;
	public ArrayList<User> getUsers() {
		return users;
	}
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	private static final long serialVersionUID = 1L;
	public String xiaofei(){
		//拿出所有会员的记录
		users=loginService.getMember();
		return SUCCESS;
	}
}
