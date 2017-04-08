package com.action.hotel;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.action.BaseAction;
import com.model.Hotel;
import com.model.Order;
import com.service.HotelService;
import com.service.OrderService;
import com.util.UserBase;

/**
* @author 141250149吴秦月
* @date 创建时间：2017年3月14日 下午9:27:14
*/
@Controller
public class CheckoutAction extends BaseAction{
	@Autowired
	OrderService orderService;
	@Autowired
	HotelService hotelService;
	private String name;
	private int roomno;
	private int orderid;
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	private ArrayList<Order> myOrders;
	public ArrayList<Order> getMyOrders() {
		return myOrders;
	}
	public void setMyOrders(ArrayList<Order> myOrders) {
		this.myOrders = myOrders;
	}
	public int getRoomno() {
		return roomno;
	}
	public void setRoomno(int roomno) {
		this.roomno = roomno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private static final long serialVersionUID = 1L;
	public String initOrder(){
		UserBase userBase = (UserBase) sessionMap.get("userBase");
		Hotel hh=hotelService.getHotels(userBase.getId()).get(0);
		//得到状态为正在进行中，且hotelId为本店的订单
		myOrders=orderService.getOut("正在进行中",hh.getName());
		System.out.println(hh.getName());
		return SUCCESS;
	}
	public String execute(){
		//把订单状态改成已完成
		Order oo=orderService.getOrder(orderid);
		//退房日期填上
		oo.setState("已完成");
		oo.setEndday(new Date(System.currentTimeMillis()));
		orderService.updateOrder(oo);
		//把房间数量还给旅馆
		Hotel ho=hotelService.getByName(name);
		ho.setRemainNum(ho.getRemainNum()+roomno);
		hotelService.cuxiaoplan(ho);;
		return SUCCESS;
	}
}
