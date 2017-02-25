package daoFactory;

import service.CourseService;
import service.CourseServiceBean;
import service.LoginService;
import service.LoginServiceBean;
import service.ResultService;
import service.ResultServiceBean;

/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年2月25日 下午1:57:51
 */
public class ServiceFactory {
	public static CourseService getCourseService(){
		return CourseServiceBean.getInstance();
	}

	public static LoginService getLoginService(){
		return LoginServiceBean.getInstance();
	}

	public static ResultService getResultService(){
		return ResultServiceBean.getInstance();
	}
}
