package com.action.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.action.BaseAction;
import com.model.Hotel;
import com.service.HotelService;
import com.util.UserBase;

/**
* @author 141250149吴秦月
* @date 创建时间：2017年3月13日 上午11:00:31
*/
@Controller
public class MyHotelAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Hotel hotel;
	@Autowired
	HotelService hotelService;
	public Hotel getHotel() {
			return hotel;
		}

		public void setHotel(Hotel hotel) {
			this.hotel = hotel;
		}
	public String execute(){
		System.out.println("我的店");
		UserBase userBase = (UserBase) sessionMap.get("userBase");
		hotel = hotelService.getHotels(userBase.getId()).get(0);
		return "success";
	}
	//编辑我的店
	public String edit(){
		//状态改为审批中
		hotel.setState("等待审批");
		hotelService.cuxiaoplan(hotel);
		return "success";
	}
}
