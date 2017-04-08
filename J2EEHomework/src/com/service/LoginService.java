package com.service;

import java.util.ArrayList;

import com.model.User;
import com.util.ResultMsg;

/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年2月6日 下午9:37:27
 */
public interface LoginService {
	/**
	 * 通过客户输入的id和密码判断是否正确
	 * @param sid
	 * @param password
	 * @return
	 */
	 boolean isValid(String sid,String password);
	/**
	 * 通过用户输入的id得到用户的用户名
	 * @param sid
	 * @return
	 */
	 String getName(String sid);
	 /**
	  * 注册用户插入数据库
	  * @param user
	  * @return
	  */
	 ResultMsg register(User user);
	/**
	 * 通过会员号得到会员信息
	 * @param id
	 * @return
	 */
	User getUserById(String id);
	/**
	 * 办卡后，一次交纳1000元以上激活
	 * @param id
	 * @return
	 */
	ResultMsg active(String id);
	/**
	 * 充值
	 * @param id
	 * @param money
	 * @return
	 */
	ResultMsg chongzhi(String id,double money);
	
	String getIdbyuser(User user);
	
	String updateUser(User user);
	
	String changePassword(User user);
	
	void updateAll(User user);
	
	ArrayList<User>getMember();
	
}
