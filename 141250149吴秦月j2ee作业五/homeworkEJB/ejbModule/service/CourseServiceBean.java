package service;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import dao.HomeworkDao;


/**
* @author 141250149吴秦月
* @date 创建时间：2017年2月6日 下午9:17:19
*/
@Stateless
public class CourseServiceBean implements CourseService{
public CourseServiceBean(){}
@EJB
HomeworkDao homeworkDao ;
//HomeworkDao homeworkDao = (HomeworkDao) EJBFactory.getEJB("ejb:/homeworkEJB//HomeworkDaoImpl!homework_result.dao.HomeworkDao");
	@Override
	public ArrayList<String> getCourseList(String id) {
		// TODO Auto-generated method stub
		return homeworkDao.getCourseList(id);
	}

}
