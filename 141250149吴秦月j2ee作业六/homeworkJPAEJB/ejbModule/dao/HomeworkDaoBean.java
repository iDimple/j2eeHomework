package dao;


import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Homework;
import model.HomeworkPK;
import model.Student;






/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年1月2日 下午2:01:41
 * 通过persistence.xml和jboss的配置文件陪好数据源
 */
@javax.ejb.Stateless
public class HomeworkDaoBean implements HomeworkDao{
	@PersistenceContext
	protected EntityManager em;
	@Override
	public String getResult(String sid, String cid) {
		try{
			//这里的sql语句不能加;
			HomeworkPK pk=new HomeworkPK(sid,cid);
			Homework homework=em.find(Homework.class, pk);
			String result=homework.getResult();
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> getCourseList(String sid) {
		ArrayList<String> courseList=new ArrayList<>();
		try{
			//注意，这里h.id.Cid的cid是getCid后的Cid要大写
			Query query=em.createQuery("select distinct h.id.cid from Homework h where sid=h.id.sid");
			courseList=(ArrayList<String>)query.getResultList();
			for(int i=0;i<courseList.size();i++){
				System.out.println(i+courseList.get(i));
			}
			em.clear();
			return courseList;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean isValid(String sid, String password) {
		try{
			//这里使用的是hql与sql语法不同
			//这里是类名，不是表名
			Student student=em.find(Student.class, sid);
			if(student==null)
				return false;
			if(student.getPassword().equals(password)){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String getUsername(String sid) {
		try{
			Student student=em.find(Student.class, sid);
			String result=student.getUsername();
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}



}
