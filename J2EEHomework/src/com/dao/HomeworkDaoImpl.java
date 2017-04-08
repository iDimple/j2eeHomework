package com.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.Homework;
import com.model.HomeworkPK;
import com.model.User;
import com.util.ResultMsg;




/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年1月2日 下午2:01:41
 * 单例模式
 */
@Repository
public class HomeworkDaoImpl implements HomeworkDao {
	@Autowired
	private BaseDao baseDao;
	private Session session;



	public String getResult(String sid,String cid){
		//获得一个session对象
		//用getsession会自动关闭session
		session = baseDao.getSession();
		//查询符合条件的homework
		String result = "";
		HomeworkPK pk=new HomeworkPK(sid,cid);
		Homework homework= session.get(Homework.class,pk);
		result=homework.getResult();
		return result;
	}

	public ArrayList<String> getCourseList(String sid){
		//获得一个session对象
		session = baseDao.getSession();
		Criteria c = session.createCriteria(Homework.class); 
		c.add(Restrictions.eq("id.sid", sid)); 
		@SuppressWarnings("unchecked")
		ArrayList<Homework> homeworks=(ArrayList<Homework>)c.list();
		ArrayList<String> courseList = new ArrayList<String>();
		if (homeworks != null) {
			for (Homework homework : homeworks) {
				courseList.add(homework.getId().getCid());
			}
		}
		return courseList;
	}

	public boolean isValid(String sid,String password){
		//获得一个session对象
		session = baseDao.getSession();
		User user=session.get(User.class, sid);
		if(user==null)
			return false;
		if(user.getPassword().equals(password)){
			return true;
		}else{
			return false;
		}
	}

	public String getUsername(String sid) {
		//获得一个session对象
		session = baseDao.getSession();
		User user=session.get(User.class, sid);
		return user.getName();
	}

	@Override
	public User getUserById(String id) {
		session=baseDao.getSession();
		return session.get(User.class, id);
	}

	@Override
	public ResultMsg save(User user) {
		return baseDao.save(user);
	}

	@Override
	public void update(User user) {
		baseDao.update(user);
	}

	@Override
	public ArrayList<User> getMember() {
		session = baseDao.getSession();
		Criteria c = session.createCriteria(User.class); 
		c.add(Restrictions.eq("category", 0)); 
		@SuppressWarnings("unchecked")
		ArrayList<User> hotels=(ArrayList<User>)c.list();
		if (hotels == null) {
			System.out.println("名下没有旅馆");
		}
		return hotels;
	}
}
