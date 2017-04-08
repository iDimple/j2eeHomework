package com.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年3月1日 上午11:31:32
 */
@Entity
@Table(name = "user")
public class User implements Serializable{

	private static final long serialVersionUID = -8603850515164057242L;
	//会员卡号，7位识别码，系统自动分配
	@Id
	private String id;
	//会员姓名
	private String name;
	//密码
	private String password;
	//银行卡号，办卡后，一次交纳1000元以上激活；
	private String bankId;
	// 用户种类
	private int category;
	// 用户等级
	private int level;
	// 会员资格状态
	private String state;
	// 余额
	private double balance;
	// 已消费金额
	private double consumption;
	// 现有积分
	private double point;
	// 出生日期
	private Date birthday;
	// 创建日期
	private Timestamp createdTime;
	public String getName() {
		
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getConsumption() {
		return consumption;
	}

	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}




}
