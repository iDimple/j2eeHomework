package dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import model.Homework;
import model.HomeworkPK;
import model.Student;




/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年1月2日 下午2:01:41
 * 单例模式
 */
public class HomeworkDaoImpl implements HomeworkDao{
	private Configuration config;
	private ServiceRegistry serviceRegistry;
	private SessionFactory sessionFactory;
	private Session session;
	private static HomeworkDaoImpl homeworkDao = new HomeworkDaoImpl();

	private HomeworkDaoImpl(){}
	
	public static HomeworkDaoImpl getInstance(){
		return homeworkDao;
	}
	
	public String getResult(String sid,String cid){
		config = new Configuration().configure();
		//编程配置映射
		//否则org.hibernate.MappingException: Unknown entity:
		config.addAnnotatedClass(Homework.class);
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		sessionFactory = config.buildSessionFactory(serviceRegistry);
		//获得一个session对象
		session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		//查询符合条件的homework
		String result = "";
		HomeworkPK pk=new HomeworkPK(sid,cid);
		Homework homework= session.get(Homework.class,pk);
		result=homework.getResult();
		tx.commit();
		session.close();
		sessionFactory.close();
		return result;
	}

	public ArrayList<String> getCourseList(String sid){
		config = new Configuration().configure();
		//编程配置映射
		//否则org.hibernate.MappingException: Unknown entity:
		config.addAnnotatedClass(Homework.class);
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		sessionFactory = config.buildSessionFactory(serviceRegistry);
		//获得一个session对象
		session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		@SuppressWarnings("deprecation")
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
		tx.commit();
		session.close();
		sessionFactory.close();
		return courseList;
	}
	
	public boolean isValid(String sid,String password){
		config = new Configuration().configure();
		//编程配置映射
		//否则org.hibernate.MappingException: Unknown entity:
		config.addAnnotatedClass(Student.class);
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		sessionFactory = config.buildSessionFactory(serviceRegistry);
		//获得一个session对象
		session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Student student=session.get(Student.class, sid);
		tx.commit();
		session.close();
		sessionFactory.close();
		if(student==null)
			return false;
		if(student.getPassword().equals(password)){
			return true;
		}else{
			return false;
		}
	}
	
	public String getUsername(String sid) {
		config = new Configuration().configure();
		//编程配置映射
		//否则org.hibernate.MappingException: Unknown entity:
		config.addAnnotatedClass(Student.class);
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		sessionFactory = config.buildSessionFactory(serviceRegistry);
		//获得一个session对象
		session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Student student=session.get(Student.class, sid);
		tx.commit();
		session.close();
		sessionFactory.close();
		return student.getUsername();
	}
}
