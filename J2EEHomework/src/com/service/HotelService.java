package com.service;

import java.util.ArrayList;

import com.model.Caiwu;
import com.model.Hotel;
import com.model.Order;

/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年3月13日 下午5:31:23
 */
public interface HotelService {
	//根据拥有者的id得到他拥有的旅馆
	ArrayList<Hotel> getHotels(String id);
	Hotel getByName(String name);
	//保存旅馆，hotel里有拥有者的id
	void saveHotel(Hotel hotel);
	//发布促销计划，将hotel里的price改了
	void cuxiaoplan(Hotel hotel);
	//入住登记，将order的state改成正在进行中
	void checkin(ArrayList<Order> orders);
	//退房登记，将order的state改成已完成
	void checkout(Order orders);
	//查看入住情况,把他名下的所有旅馆的入住情况都找到,即把order中state是正在进行中的找到
	ArrayList<Order> in(String id);
	//查看预定情况,把他名下的所有旅馆的预定情况都找到,即把order中state是未完成的找到
	ArrayList<Order> reserve(String id);
	//查看财务情况，把他名下的所有旅馆的财务情况分类都找到
	ArrayList<Caiwu> caiwu(String id);
	ArrayList<Hotel> getHotelByState(String state);
	void update(Hotel hotel);
	Hotel getByHotelId(int hotelid);
}
