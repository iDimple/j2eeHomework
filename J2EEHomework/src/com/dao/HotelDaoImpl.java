package com.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.Caiwu;
import com.model.Hotel;

/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年3月13日 下午5:45:22
 */
@Repository
public class HotelDaoImpl implements HotelDao{
	@Autowired
	private BaseDao baseDao;
	private Session session;
	@Override
	public ArrayList<Hotel> getHotels(String owner) {
		session = baseDao.getSession();
		Criteria c = session.createCriteria(Hotel.class); 
		c.add(Restrictions.eq("owner", owner)); 
		@SuppressWarnings("unchecked")
		ArrayList<Hotel> hotels=(ArrayList<Hotel>)c.list();
		if (hotels == null) {
			System.out.println("名下没有旅馆");
		}
		return hotels;
	}

	@Override
	public void saveHotel(Hotel hotel) {
		baseDao.save(hotel);
	}
	
	//查看财务情况，把他名下的所有旅馆的财务情况分类都找到
	@Override
	public ArrayList<Caiwu> getcaiwu(String id) {
		session = baseDao.getSession();
		Criteria c = session.createCriteria(Caiwu.class); 
		@SuppressWarnings("unchecked")
		ArrayList<Caiwu> hotel=(ArrayList<Caiwu>)c.list();
		return hotel;
	}

	@Override
	public ArrayList<Hotel> getHotels() {
		session = baseDao.getSession();
		Criteria c = session.createCriteria(Hotel.class); 
		@SuppressWarnings("unchecked")
		ArrayList<Hotel> hotel=(ArrayList<Hotel>)c.list();
		return hotel;
	}

	@Override
	public void update(Hotel hotel) {
		baseDao.update(hotel);
		
	}

	@Override
	public Hotel getHotelByHotelId(int hotelId) {
		session = baseDao.getSession();
		Criteria c = session.createCriteria(Hotel.class); 
		c.add(Restrictions.eq("id", hotelId)); 
		@SuppressWarnings("unchecked")
		ArrayList<Hotel> hotels=(ArrayList<Hotel>)c.list();
		if (hotels == null) {
			System.out.println("没有旅馆");
			return null;
		}
		return hotels.get(0);
	}

	@Override
	public Hotel getByName(String name) {
		session = baseDao.getSession();
		Criteria c = session.createCriteria(Hotel.class); 
		c.add(Restrictions.eq("name", name)); 
		@SuppressWarnings("unchecked")
		ArrayList<Hotel> hotels=(ArrayList<Hotel>)c.list();
		if (hotels == null) {
			System.out.println("没有旅馆");
			return null;
		}
		return hotels.get(0);
	}

	@Override
	public ArrayList<Hotel> getHotelByState(String state) {
		session = baseDao.getSession();
		Criteria c = session.createCriteria(Hotel.class); 
		c.add(Restrictions.eq("state", state)); 
		@SuppressWarnings("unchecked")
		ArrayList<Hotel> hotel=(ArrayList<Hotel>)c.list();
		return hotel;
	}

	@Override
	public Hotel getByHotelId(int hotelid) {
		session = baseDao.getSession();
		Criteria c = session.createCriteria(Hotel.class); 
		c.add(Restrictions.eq("id", hotelid)); 
		@SuppressWarnings("unchecked")
		ArrayList<Hotel> hotel=(ArrayList<Hotel>)c.list();
		return hotel.get(0);
	}

}
