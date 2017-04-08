package com.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
* @author 141250149吴秦月
* @date 创建时间：2017年3月7日 上午10:56:28
*/
@Entity
@Table(name = "orderList")
public class Order implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6402970814436919468L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//入住时间
	private Date startday;
	//退房时间
	private Date endday;
	//预定天数
	private int days;
	//预定时间
	private Date ordertime;
	//预定旅馆
	private int hotelId;
	//旅馆名
	private String name;
	//单价
	private double price;
	//总价
	private double summoney;
	//会员卡号
	private String memberId;
	//房间数量
	private int roomNo;
	//订单状态，已完成，未完成（点击取消），正在进行中
	private String state;
	//会员卡
	private String cardState;
	public String getCardState() {
		return cardState;
	}
	public void setCardState(String cardState) {
		this.cardState = cardState;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getStartday() {
		return startday;
	}
	public void setStartday(Date startday) {
		this.startday = startday;
	}
	public Date getEndday() {
		return endday;
	}
	public void setEndday(Date endday) {
		this.endday = endday;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getSummoney() {
		return summoney;
	}
	public void setSummoney(double summoney) {
		this.summoney = summoney;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

}
