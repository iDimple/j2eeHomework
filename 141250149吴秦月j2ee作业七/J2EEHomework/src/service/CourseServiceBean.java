package service;

import java.util.ArrayList;


import dao.HomeworkDao;
import dao.HomeworkDaoImpl;


/**
* @author 141250149吴秦月
* @date 创建时间：2017年2月6日 下午9:17:19
*/
public class CourseServiceBean implements CourseService{
private CourseServiceBean(){}

private static CourseServiceBean courseService=new CourseServiceBean();

public static CourseService getInstance(){
	return courseService;
}

HomeworkDao homeworkDao=HomeworkDaoImpl.getInstance() ;
	@Override
	public ArrayList<String> getCourseList(String id) {
		// TODO Auto-generated method stub
		return homeworkDao.getCourseList(id);
	}

}
