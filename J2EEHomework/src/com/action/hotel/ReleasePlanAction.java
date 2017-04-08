package com.action.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.action.BaseAction;
import com.model.Hotel;
import com.service.HotelService;
import com.util.UserBase;

/**
* @author 141250149吴秦月
* @date 创建时间：2017年3月14日 上午11:09:37
* 发布促销计划
*/
@Controller
public class ReleasePlanAction extends BaseAction{
	private Hotel hotel;
	@Autowired
	HotelService hotelService;
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	private static final long serialVersionUID = 1L;
	public String execute(){
		//需要店审批通过才可以发布
		UserBase userBase = (UserBase) sessionMap.get("userBase");
		hotel = hotelService.getHotels(userBase.getId()).get(0);
		if(hotel.getState().equals("审批通过")){
			return SUCCESS;
		}
		return ERROR;
		}
	public String plan(){
		UserBase userBase = (UserBase) sessionMap.get("userBase");
		Hotel hotels = hotelService.getHotels(userBase.getId()).get(0);
		//修改房间单价
		hotels.setPrice(hotel.getPrice());
		hotelService.cuxiaoplan(hotels);
		return SUCCESS;
	}
}
