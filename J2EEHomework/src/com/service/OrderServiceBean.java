package com.service;

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
 * @date 创建时间：2017年3月7日 上午11:18:38
 */
@Service
public class OrderServiceBean implements OrderService{
	@Autowired
	OrderDao orderDao;
	@Autowired
	HotelDao hotelDao;
	@Override
	public ArrayList<Order> getMyOrder(String memberId) {
		return orderDao.getMyOrder(memberId);
	}
	@Override
	public ArrayList<Hotel> getHotels() {
		return hotelDao.getHotels();
	}
	@Override
	public boolean generateOrders(ArrayList<Order> order) {
		//需要更改一下客栈的剩余房间数
		Hotel ho=hotelDao.getHotelByHotelId(order.get(0).getHotelId());
		int remain=ho.getRemainNum();
		Order or=order.get(0);
		if(or.getRoomNo()>remain){
			System.out.println("没有那么多房间了");
			return false;
		}
		orderDao.saveOrders(order);
		ho.setRemainNum(remain-or.getRoomNo());
		hotelDao.update(ho);
		return true;

	}
	@Override
	public Order getOrder(int orderId) {
		return orderDao.getOrder(orderId);
	}
	@Override
	public void updateOrder(Order order) {
		orderDao.updateOrder(order);		
	}
	@Override
	public ArrayList<Order> getOut(String state, String name) {
		return orderDao.getOut(state, name);
	}
	@Override
	public void generateCaiwu(Caiwu caiwu) {
		orderDao.saveCaiwu(caiwu);		
	}
	@Override
	public ArrayList<Caiwu> getJiesuan(String state) {
		return orderDao.getJiesuan(state);
	}
	@Override
	public Caiwu getCaiwuById(int id) {
		return orderDao.getCaiwuById(id);
	}
	@Override
	public void updateCaiwu(Caiwu caiwu) {
		orderDao.updateCaiwu(caiwu);		
	}
	@Override
	public ArrayList<Caiwu> getByHotel(int hotelid) {
		return orderDao.getByHotel(hotelid);
	}
	@Override
	public ArrayList<Order> getAllOrder() {
		return orderDao.getAllOrder();
	}
	@Override
	public ArrayList<Caiwu> getAllCai() {
		return orderDao.getAllCai();
	}

}
