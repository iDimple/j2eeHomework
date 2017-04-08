package com.action.manager;

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
* @date 创建时间：2017年3月15日 下午3:11:20
*/
@Controller
public class RuzhuAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	@Autowired
	OrderService orderService;
	@Autowired
	HotelService hotelService;
	private ArrayList<Order> myOrders;
	public ArrayList<Order> getMyOrders() {
		return myOrders;
	}
	public void setMyOrders(ArrayList<Order> myOrders) {
		this.myOrders = myOrders;
	}
	public String ruzhu(){
		System.out.println("入住情况");
		//得到所有order
		myOrders=orderService.getAllOrder();
		if(myOrders==null){
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
}
