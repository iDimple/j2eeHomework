package com.action.hotel;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.action.BaseAction;
import com.model.Caiwu;
import com.model.Hotel;
import com.model.Order;
import com.model.User;
import com.service.HotelService;
import com.service.LoginService;
import com.service.OrderService;
import com.util.UserBase;

/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年3月14日 下午8:37:39
 */
@Controller
public class CheckInAction extends BaseAction{
	@Autowired
	OrderService orderService;
	@Autowired
	LoginService loginService;
	@Autowired
	HotelService hotelService;
	private User user;
	private Order order;
	private Order notM;
	public Order getNotM() {
		return notM;
	}
	public void setNotM(Order notM) {
		this.notM = notM;
	}
	private int paytype;
	public int getPaytype() {
		return paytype;
	}
	public void setPaytype(int paytype) {
		this.paytype = paytype;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	private static final long serialVersionUID = 1L;
	public String execute(){
		//将订单的state改成，正在进行中
		Order oo=orderService.getOrder(order.getId());
		oo.setState("正在进行中");
		//会员订单
		oo.setCardState("会员订单");
		orderService.updateOrder(oo);
		//生成财物数据
		Caiwu cw=new Caiwu();
		cw.setState("到账");
		//付款方式是0现金结账，只用将会员的consumption和point增加
		//付款方式是1会员卡结账，将会员的consumption和point增加，balance减少
		//判断balance是否小于金额
		User uu=loginService.getUserById(user.getId());
		System.out.println(uu.getId());
		uu.setConsumption(uu.getConsumption()+oo.getSummoney());
		uu.setPoint(uu.getPoint()+oo.getSummoney());
		if(paytype==1){
			if(uu.getBalance()<oo.getSummoney()){
				return "error";
			}
			uu.setBalance(uu.getBalance()-oo.getSummoney());
			cw.setState("等待结算");
		}

		loginService.updateAll(uu);

		cw.setOwner(user.getId());
		cw.setHotel(oo.getHotelId());
		cw.setOrderId(oo.getId());
		cw.setSum(oo.getSummoney());
		orderService.generateCaiwu(cw);
		return "success";
	}
	public String checkinNot(){
		//产生一张新的正在进行中的订单，memberid天非会员姓名
		String year=(String)request.getParameter("selYear");
		String month=(String)request.getParameter("selMonth");
		String day=(String)request.getParameter("selDay");
		String startday=year+"-"+month+"-"+day;
		notM.setStartday(Date.valueOf(startday));
		notM.setState("正在进行中");
		//会员订单
		notM.setCardState("非会员订单");
		notM.setOrdertime(new Date(System.currentTimeMillis()));
		//生成财物数据
		Caiwu cw=new Caiwu();
		cw.setState("到账");
		cw.setHotel(notM.getHotelId());
		cw.setOrderId(notM.getId());
		cw.setSum(notM.getSummoney());
		ArrayList<Order> ooo=new ArrayList<Order>();
		ooo.add(notM);
		orderService.generateOrders(ooo);
		cw.setOrderId(notM.getId());
		orderService.generateCaiwu(cw);
		return "success";
	}
	
	public String initnotMember(){
		notM=new Order();
		UserBase userBase = (UserBase) sessionMap.get("userBase");
		Hotel hhhh=hotelService.getHotels(userBase.getId()).get(0);
		notM.setPrice(hhhh.getPrice());
		notM.setDays(1);
		notM.setRoomNo(1);
		notM.setSummoney(hhhh.getPrice());
		notM.setHotelId(hhhh.getId());
		notM.setName(hhhh.getName());
		return SUCCESS;
	}

}
