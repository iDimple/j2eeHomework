package com.action.hotel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.action.BaseAction;
import com.model.Caiwu;
import com.model.Hotel;
import com.model.Order;
import com.service.HotelService;
import com.service.OrderService;
import com.util.UserBase;

/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年3月14日 下午10:21:29
 */
@Controller
public class TongjiAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	@Autowired
	OrderService orderService;
	@Autowired
	HotelService hotelService;
	private ArrayList<Order> myOrders;
	private ArrayList<Order> myResOrder;
	private ArrayList<Caiwu> caiwu;
	private double daozhang;
	private double jiesuan;
	
	public double getDaozhang() {
		return daozhang;
	}
	public void setDaozhang(double daozhang) {
		this.daozhang = daozhang;
	}
	public double getJiesuan() {
		return jiesuan;
	}
	public void setJiesuan(double jiesuan) {
		this.jiesuan = jiesuan;
	}
	public ArrayList<Caiwu> getCaiwu() {
		return caiwu;
	}
	public void setCaiwu(ArrayList<Caiwu> caiwu) {
		this.caiwu = caiwu;
	}
	//预定房间总数
	private int resNo;
	//入住房间总数
	private int inNo;
	public int getInNo() {
		return inNo;
	}
	public void setInNo(int inNo) {
		this.inNo = inNo;
	}
	public int getResNo() {
		return resNo;
	}
	public void setResNo(int resNo) {
		this.resNo = resNo;
	}
	public ArrayList<Order> getMyResOrder() {
		return myResOrder;
	}
	public void setMyResOrder(ArrayList<Order> myResOrder) {
		this.myResOrder = myResOrder;
	}
	public ArrayList<Order> getMyOrders() {
		return myOrders;
	}
	public void setMyOrders(ArrayList<Order> myOrders) {
		this.myOrders = myOrders;
	}
	public String execute(){
		System.out.println("入住情况");
		UserBase userBase = (UserBase) sessionMap.get("userBase");
		Hotel hh=hotelService.getHotels(userBase.getId()).get(0);
		//得到状态为正在进行中，且hotelId为本店的订单
		myOrders=orderService.getOut("正在进行中",hh.getName());
		inNo=0;
		if(myOrders==null){
			System.out.println("dingdanweikong");
		}else if(myOrders.size()==0){
			System.out.println("meiyoudingdan");
		}else{
			for(int i=0;i<myOrders.size();i++){
				inNo+=myOrders.get(i).getRoomNo();
				System.out.print(myOrders.get(i).getId());
				System.out.print(myOrders.get(i).getName());
			}
		}
		System.out.println("预定情况");
		//得到状态为未完成中，且hotelId为本店的订单
		myResOrder=orderService.getOut("未完成,点击取消预定",hh.getName());
		resNo=0;
		if(myResOrder==null){
			System.out.println("dingdanweikong");
		}else if(myResOrder.size()==0){
			System.out.println("meiyoudingdan");
		}else{
			for(int i=0;i<myResOrder.size();i++){
				resNo+=myResOrder.get(i).getRoomNo();
				System.out.print(myResOrder.get(i).getId());
				System.out.print(myResOrder.get(i).getName());
			}
		}
		//财物表格
		System.out.println("财物状况");
		caiwu=orderService.getByHotel(hh.getId());
		daozhang=0;
		jiesuan=0;
		for(int i=0;i<caiwu.size();i++){
			if(caiwu.get(i).getState().equals("到账")){
				daozhang+=caiwu.get(i).getSum();
			}else{
				jiesuan+=caiwu.get(i).getSum();
			}
		}
		return SUCCESS;
	}
}
