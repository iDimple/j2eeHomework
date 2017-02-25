package service;


import dao.HomeworkDao;
import dao.HomeworkDaoImpl;

/**
* @author 141250149吴秦月
* @date 创建时间：2017年2月6日 下午9:38:06
*/
public class LoginServiceBean implements LoginService{
	HomeworkDao homeworkDao=HomeworkDaoImpl.getInstance();
	private LoginServiceBean(){}
	private static LoginServiceBean loginService=new LoginServiceBean();
	public static LoginService getInstance(){
		return  loginService;
	}
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
