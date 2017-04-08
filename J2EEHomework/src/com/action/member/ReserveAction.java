package com.action.member;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.action.BaseAction;
import com.model.Hotel;
import com.model.Order;
import com.service.OrderService;
import com.util.UserBase;

/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年3月7日 下午1:44:55
 */
@Controller
public class ReserveAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4464002755808223517L;
	@Autowired
	OrderService orderService;

	private ArrayList<Hotel> hotels;
	private String message="";
	private String hotelid ="";
	private Order order;
	private String hotelname;
	private double price;
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getHotelname() {
		return hotelname;
	}
	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}


	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public String getHotelid() {
		return hotelid;
	}
	public void setHotelid(String hotelid) {
		this.hotelid = hotelid;
	}
	public ArrayList<Hotel> getHotels() {
		return hotels;
	}
	public void setHotels(ArrayList<Hotel> hotels) {
		this.hotels = hotels;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String hotelList(){
		System.out.println("客栈列表");
		hotels=orderService.getHotels();

		if(hotels==null){
			message="没有客栈";
			System.out.println("dingdanweikong");
		}else if(hotels.size()==0){
			System.out.println("meiyoudingdan");
		}else{
			for(int i=0;i<hotels.size();i++){
				System.out.print(hotels.get(i).getId());
				System.out.print(hotels.get(i).getName());
			}
		}
		return SUCCESS;
	}
	public String chooseTime(){
		order=new Order();
		hotelid=(String) request.getAttribute("hotelid");
		hotelname=(String) request.getAttribute("hotelname");
		price=(Double) request.getAttribute("price");
		order.setRoomNo(1);
		order.setPrice(price);
		order.setDays(1);
		order.setSummoney(price*0.95);
		System.out.println("hotelid"+hotelid);
		System.out.println("hotelname"+hotelname);
		System.out.println("price"+price);
		return SUCCESS;
	}
	public String generateOrders(){

		System.out.println("chanshengdingdan");
		ArrayList<Order> oo=new ArrayList<Order>();
		String year=(String)request.getParameter("selYear");
		System.out.println(year);
		String month=(String)request.getParameter("selMonth");
		System.out.println(month);
		String day=(String)request.getParameter("selDay");
		System.out.println(day);
		String startday=year+"-"+month+"-"+day;
		order.setStartday(Date.valueOf(startday));
		System.out.println(order.getStartday());
		order.setHotelId(Integer.parseInt(hotelid));
		order.setName(hotelname);
		order.setOrdertime(new Date(System.currentTimeMillis()));
		order.setState("未完成,点击取消预定");
		UserBase userBase = (UserBase) sessionMap.get("userBase");
		order.setMemberId(userBase.getId());
		//分配房间号
		//TODO
		oo.add(order);
		orderService.generateOrders(oo);
		return SUCCESS;
	}
}
