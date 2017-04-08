package com.service;

import java.util.ArrayList;

import com.model.Caiwu;
import com.model.Hotel;
import com.model.Order;

/**
 * 订单相关
 * @author 141250149吴秦月
 * @date 创建时间：2017年3月7日 上午10:55:01
 */
public interface OrderService {
	ArrayList<Order> getAllOrder();
	// 通过用户id查看订单情况
	ArrayList<Order> getMyOrder(String memberId);
	//得到客栈列表
	ArrayList<Hotel> getHotels();
	//通过orderid得到订单
	Order getOrder(int orderId);
	//产生订单
	boolean generateOrders(ArrayList<Order> order);
	//更新
	void updateOrder(Order order);
	//退房，得到状态为正在进行中，且hotelId为本店的订单
	ArrayList<Order> getOut(String state,String name);
	
	
	void generateCaiwu(Caiwu caiwu);
	ArrayList<Caiwu> getJiesuan(String state);
	Caiwu getCaiwuById(int id);
	void updateCaiwu(Caiwu caiwu);
	ArrayList<Caiwu> getByHotel(int hotelid);
	ArrayList<Caiwu> getAllCai();
	
}
