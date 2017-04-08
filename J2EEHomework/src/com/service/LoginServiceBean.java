package com.service;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.HomeworkDao;
import com.model.User;
import com.util.ResultMsg;
import com.util.UserState;


/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年2月6日 下午9:38:06
 */
@Service
public class LoginServiceBean implements LoginService{
	@Autowired
	HomeworkDao homeworkDao;

	String username;
	public boolean isValid(String sid,String password){
		boolean isValid=homeworkDao.isValid(sid,password);
		if(!isValid){//用户名密码错误
			return false;
		}
		//id密码正确

		return isValid;
	}
	@Override
	public String getName(String sid) {
		return homeworkDao.getUsername(sid);
	}
	@Override
	public ResultMsg register(User user) {
		UUID uuid=UUID.randomUUID();
		String str=uuid.toString().substring(0,7);
		user.setId(str);
		//设置时间戳
		user.setCreatedTime(new Timestamp(System.currentTimeMillis()));
		return homeworkDao.save(user);
	}
	@Override
	public User getUserById(String id) {
		return homeworkDao.getUserById(id);
	}
	@Override
	public ResultMsg active(String id) {
		User user = homeworkDao.getUserById(id);
		user.setState(UserState.NORMAL.toString());
		homeworkDao.update(user);
		return ResultMsg.Success;
	}
	@Override
	public ResultMsg chongzhi(String id, double money) {
		User user = homeworkDao.getUserById(id);
		user.setBalance(user.getBalance()+money);;
		homeworkDao.update(user);
		return ResultMsg.Success;
	}
	@Override
	public String getIdbyuser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	//这个方法只会改变用户的姓名，银行卡号和出生日期
	@Override
	public String updateUser(User user) {
		User userOld = homeworkDao.getUserById(user.getId());
		userOld.setBankId(user.getBankId());
		userOld.setName(user.getName());
		userOld.setBirthday(user.getBirthday());
		homeworkDao.update(userOld);
		return "success";
	}
	@Override
	public String changePassword(User user) {
		User userOld = homeworkDao.getUserById(user.getId());
		userOld.setPassword(user.getPassword());
		homeworkDao.update(userOld);
		return "success";
	}
	@Override
	public void updateAll(User user) {
		homeworkDao.update(user);
	}
	@Override
	public ArrayList<User> getMember() {
		return homeworkDao.getMember();
	}
}
