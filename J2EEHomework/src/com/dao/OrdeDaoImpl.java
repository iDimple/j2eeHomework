package com.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.Caiwu;
import com.model.Order;



/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年3月7日 上午11:21:24
 */
@Repository
public class OrdeDaoImpl implements OrderDao{
	@Autowired
	private BaseDao baseDao;
	private Session session;
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Order> getMyOrder(String memberId) {
		session = baseDao.getSession();
		Criteria c = session.createCriteria(Order.class); 
		c.add(Restrictions.eq("memberId", memberId)); 
		ArrayList<Order> homeworks=(ArrayList<Order>)c.list();
		if (homeworks == null) {
			System.out.println("kkon");
		}
		return homeworks;
	}

	@Override
	public void saveOrders(ArrayList<Order> order) {
		for(int i=0;i<order.size();i++){
			baseDao.save(order.get(i));
		}
	}

	@Override
	public void update(ArrayList<Order> orders) {
		for(int i=0;i<orders.size();i++){
			baseDao.update(orders.get(i));
		}		
	}
	//查看入住情况,把他名下的所有旅馆的入住情况都找到,即把order中state是正在进行中的找到
	@Override
	public ArrayList<Order> getIn(String id) {
		session = baseDao.getSession();
		Criteria c = session.createCriteria(Order.class); 
		c.add(Restrictions.eq("memberId", id)); 
		c.add(Restrictions.eq("state", "正在进行中")); 
		@SuppressWarnings("unchecked")
		ArrayList<Order> homeworks=(ArrayList<Order>)c.list();
		if (homeworks == null) {
			System.out.println("kkon");
		}
		return homeworks;
	}
	//查看预定情况,把他名下的所有旅馆的预定情况都找到,即把order中state是未完成的找到
	@Override
	public ArrayList<Order> getReserve(String id) {
		session = baseDao.getSession();
		Criteria c = session.createCriteria(Order.class); 
		c.add(Restrictions.eq("memberId", id)); 
		c.add(Restrictions.eq("state", "未完成")); 
		@SuppressWarnings("unchecked")
		ArrayList<Order> homeworks=(ArrayList<Order>)c.list();
		if (homeworks == null) {
			System.out.println("kkon");
		}
		return homeworks;
	}

	@Override
	public Order getOrder(int orderId) {
		session = baseDao.getSession();
		Criteria c = session.createCriteria(Order.class); 
		c.add(Restrictions.eq("id", orderId)); 
		@SuppressWarnings("unchecked")
		ArrayList<Order> homeworks=(ArrayList<Order>)c.list();
		if (homeworks == null) {
			System.out.println("kkon");
		}
		return homeworks.get(0);
	}

	@Override
	public void updateOrder(Order order) {
		baseDao.update(order);		
	}

	@Override
	public ArrayList<Order> getOut(String state, String name) {
		session = baseDao.getSession();
		Criteria c = session.createCriteria(Order.class); 
		c.add(Restrictions.eq("state", state)); 
		c.add(Restrictions.eq("name", name)); 
		@SuppressWarnings("unchecked")
		ArrayList<Order> homeworks=(ArrayList<Order>)c.list();
		if (homeworks == null) {
			System.out.println("kkon");
		}
		return homeworks;
	}

	@Override
	public void saveCaiwu(Caiwu caiwu) {
		baseDao.save(caiwu);		
	}

	@Override
	public ArrayList<Caiwu> getJiesuan(String state) {
		session = baseDao.getSession();
		Criteria c = session.createCriteria(Caiwu.class); 
		c.add(Restrictions.eq("state", state)); 
		@SuppressWarnings("unchecked")
		ArrayList<Caiwu> homeworks=(ArrayList<Caiwu>)c.list();
		if (homeworks == null) {
			System.out.println("kkon");
		}
		return homeworks;
	}

	@Override
	public Caiwu getCaiwuById(int id) {
		session = baseDao.getSession();
		Criteria c = session.createCriteria(Caiwu.class); 
		c.add(Restrictions.eq("id", id)); 
		System.out.println(id);
		@SuppressWarnings("unchecked")
		ArrayList<Caiwu> homeworks=(ArrayList<Caiwu>)c.list();
		if (homeworks == null) {
			System.out.println("kkon");
			return null;
		}
		return homeworks.get(0);
	}

	@Override
	public void updateCaiwu(Caiwu caiwu) {
		baseDao.update(caiwu);		
	}

	@Override
	public ArrayList<Caiwu> getByHotel(int hotel) {
		session = baseDao.getSession();
		Criteria c = session.createCriteria(Caiwu.class); 
		c.add(Restrictions.eq("hotel", hotel)); 
		@SuppressWarnings("unchecked")
		ArrayList<Caiwu> homeworks=(ArrayList<Caiwu>)c.list();
		if (homeworks == null) {
			System.out.println("kkon");
			return null;
		}
		return homeworks;
	}

	@Override
	public ArrayList<Order> getAllOrder() {
		session = baseDao.getSession();
		Criteria c = session.createCriteria(Order.class); 
		@SuppressWarnings("unchecked")
		ArrayList<Order> hotel=(ArrayList<Order>)c.list();
		return hotel;
	}

	@Override
	public ArrayList<Caiwu> getAllCai() {
		session = baseDao.getSession();
		Criteria c = session.createCriteria(Caiwu.class); 
		@SuppressWarnings("unchecked")
		ArrayList<Caiwu> hotel=(ArrayList<Caiwu>)c.list();
		return hotel;
	}


}
