package com.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.HomeworkDao;


/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年2月6日 下午9:17:19
 */
@Service
public class CourseServiceBean implements CourseService{

	@Autowired
	HomeworkDao homeworkDao;
	@Override
	public ArrayList<String> getCourseList(String id) {
		return homeworkDao.getCourseList(id);
	}

}
