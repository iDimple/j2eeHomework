package com.action.member;

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
* @date 创建时间：2017年3月7日 上午11:36:40
*/
@Controller
public class MyOrderAction extends BaseAction{
	private static final long serialVersionUID = 451012942432141494L;
	@Autowired
	OrderService orderService;
	@Autowired
	HotelService hotelService;
	private int orderid;
	private int roomno;
	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRoomno() {
		return roomno;
	}

	public void setRoomno(int roomno) {
		this.roomno = roomno;
	}
	private ArrayList<Order> myOrders;
	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	private String message="";
	
	public ArrayList<Order> getMyOrders() {
		return myOrders;
	}

	public void setMyOrders(ArrayList<Order> myOrders) {
		this.myOrders = myOrders;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String myOrder(){
		System.out.println("我的订单");
		UserBase userBase = (UserBase) sessionMap.get("userBase");
		myOrders=orderService.getMyOrder(userBase.getId());
		
		if(myOrders==null){
			message="没有订单";
			System.out.println("dingdanweikong");
		}else if(myOrders.size()==0){
			System.out.println("meiyoudingdan");
		}else{
			for(int i=0;i<myOrders.size();i++){
				System.out.print(myOrders.get(i).getId());
				System.out.print(myOrders.get(i).getName());
			}
		}
		return SUCCESS;
	}
	public String cancel(){
		//取消订单，将订单状态改为已取消，将hotel的房间数量加上
		
		System.out.println("orderid"+orderid);
		System.out.println("fangjianshu"+roomno);
		System.out.println(orderid);
		Order order=orderService.getOrder(orderid);
		order.setState("已取消");
		orderService.updateOrder(order);
		Hotel hotels = hotelService.getByName(name);
		//修改房间数量
		hotels.setRemainNum(hotels.getRemainNum()+roomno);
		hotelService.cuxiaoplan(hotels);
		return SUCCESS;
	}
}
