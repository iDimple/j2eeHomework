package com.dao;

import java.util.ArrayList;

import com.model.Caiwu;
import com.model.Order;

/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年3月7日 上午11:19:33
 */
public interface OrderDao {
	ArrayList<Order> getAllOrder();
	//根据会员号得到他的订单
	ArrayList<Order> getMyOrder(String memberId);
	//保存订单
	void saveOrders(ArrayList<Order> order);
	//更新订单
	void update(ArrayList<Order> orders);
	//查看入住情况,把他名下的所有旅馆的入住情况都找到,即把order中state是正在进行中的找到
	ArrayList<Order> getIn(String id);
	//查看预定情况,把他名下的所有旅馆的预定情况都找到,即把order中state是未完成的找到
	ArrayList<Order> getReserve(String id);
	//通过orderid得到订单
	Order getOrder(int orderId);
	void updateOrder(Order order);
	ArrayList<Order> getOut(String state,String name);
	void saveCaiwu(Caiwu caiwu);
	ArrayList<Caiwu> getJiesuan(String state);
	Caiwu getCaiwuById(int id);
	void updateCaiwu(Caiwu caiwu);
	ArrayList<Caiwu> getByHotel(int hotelid);
	ArrayList<Caiwu> getAllCai();
}
