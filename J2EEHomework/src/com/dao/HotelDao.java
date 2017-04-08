package com.dao;

import java.util.ArrayList;

import com.model.Caiwu;
import com.model.Hotel;

/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年3月13日 下午5:29:35
 * dao里应该只有增删改查的
 */
public interface HotelDao {
	//得到系统中所有的旅馆
	ArrayList<Hotel> getHotels();
	//根据拥有者的id得到他拥有的旅馆
	ArrayList<Hotel> getHotels(String id);
	//根据hotelid得到他拥有的旅馆
	Hotel getHotelByHotelId(int hotelId);
	//保存旅馆，假设一个人只有一个旅馆
	void saveHotel(Hotel hotel);
	//更新旅馆
	void update(Hotel hotel);
	//查看财务情况，把他财务情况都找到
	ArrayList<Caiwu> getcaiwu(String id);
	Hotel getByName(String name);
	ArrayList<Hotel> getHotelByState(String state);
	Hotel getByHotelId(int hotelid);
}
