package com.dao;

import java.util.ArrayList;


/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年2月25日 下午1:23:50
 */
public interface HomeworkDao {
	String getResult(String sid,String cid);
	ArrayList<String> getCourseList(String sid);
	boolean isValid(String sid,String password);
	String getUsername(String sid);
}
