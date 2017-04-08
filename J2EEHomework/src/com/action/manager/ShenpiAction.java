package com.action.manager;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.action.BaseAction;
import com.model.Hotel;
import com.service.HotelService;

/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年3月15日 上午11:15:10
 */
@Controller
public class ShenpiAction extends BaseAction{
	@Autowired
	HotelService hotelService;
	private ArrayList<Hotel> hotels;
	private int hotelid;
	public int getHotelid() {
		return hotelid;
	}

	public void setHotelid(int hotelid) {
		this.hotelid = hotelid;
	}

	public ArrayList<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(ArrayList<Hotel> hotels) {
		this.hotels = hotels;
	}

	private static final long serialVersionUID = 1L;

	public String shenpi(){
		System.out.println("审批");
		//把hotel表里state是审批中的拿过来
		hotels=hotelService.getHotelByState("等待审批");
		System.out.println(hotels.size());
		return SUCCESS;
	}
	//审批通过
	public String approve(){
		System.out.println("审批通过");
		//把旅馆的state改成审批通过
		Hotel hh=hotelService.getByHotelId(hotelid);
		hh.setState("审批通过");
		hotelService.update(hh);
		return SUCCESS;
	}
}
