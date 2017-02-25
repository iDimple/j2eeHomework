package service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import dao.HomeworkDao;

/**
* @author 141250149吴秦月
* @date 创建时间：2017年2月6日 下午9:38:06
*/
@Stateless
public class LoginServiceBean implements LoginService{
	@EJB
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
}
