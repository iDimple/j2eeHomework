package service;

import java.util.ArrayList;

import javax.ejb.Remote;

/**
* @author 141250149吴秦月
* @date 创建时间：2017年2月6日 下午9:16:15
*/
@Remote
public interface CourseService {
	public ArrayList<String> getCourseList(String id);
}
