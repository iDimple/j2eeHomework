package com.service;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.HotelDao;
import com.dao.OrderDao;
import com.model.Caiwu;
import com.model.Hotel;
import com.model.Order;

/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年3月13日 下午5:37:55
 */
@Service
public class HotelServiceBean implements HotelService{
	@Autowired
	HotelDao hotelDao;
	@Autowired
	OrderDao orderDao;
	@Override
	public ArrayList<Hotel> getHotels(String id) {
		return hotelDao.getHotels(id);
	}

	@Override
	public void saveHotel(Hotel hotel) {
		//设定状态为0是等待审批
		hotel.setState("等待审批");
		//剩余房间数量为房间总数量
		hotel.setRemainNum(hotel.getRoomNum());
		//设置时间戳
		//设置时间戳
		hotel.setTime(new Date(System.currentTimeMillis()));
		hotelDao.saveHotel(hotel);		
	}

	@Override
	public void cuxiaoplan(Hotel hotel) {
		hotelDao.update(hotel);
	}

	@Override
	public void checkin(ArrayList<Order> orders) {
		orderDao.update(orders);	
	}

	@Override
	public void checkout(Order orders) {
		orderDao.updateOrder(orders);;	
	}

	@Override
	public ArrayList<Order> in(String id) {
		return orderDao.getIn(id);
	}

	@Override
	public ArrayList<Order> reserve(String id) {
		return orderDao.getReserve(id);
	}

	@Override
	public ArrayList<Caiwu> caiwu(String id) {
		return hotelDao.getcaiwu(id);
	}

	@Override
	public Hotel getByName(String name) {
		return hotelDao.getByName(name);
	}

	@Override
	public ArrayList<Hotel> getHotelByState(String state) {
		return hotelDao.getHotelByState(state);
	}

	@Override
	public void update(Hotel hotel) {
		hotelDao.update(hotel);		
	}

	@Override
	public Hotel getByHotelId(int hotelid) {
		return hotelDao.getByHotelId(hotelid);
	}



}
