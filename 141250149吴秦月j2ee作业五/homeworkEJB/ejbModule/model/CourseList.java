package model;

import java.util.ArrayList;

/**
* @author 141250149吴秦月
* @date 创建时间：2017年1月2日 下午3:33:00
*/
public class CourseList {
private ArrayList<String> CourseList;

public ArrayList<String> getCourseList() {
	return CourseList;
}

public void setCourseList(ArrayList<String> courseList) {
	CourseList = courseList;
}

public CourseList(ArrayList<String> courseList) {
	super();
	CourseList = courseList;
}

}
