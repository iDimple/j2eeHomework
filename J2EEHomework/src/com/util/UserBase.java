package com.util;

import java.io.Serializable;

import com.model.User;

/**
* @author 141250149吴秦月
* @date 创建时间：2017年3月4日 下午6:16:06
*/
public class UserBase implements Serializable{

	private static final long serialVersionUID = 6182707352778653613L;
	private final String id;// 用户id
	private final String username;// 用户名
	private final int category;// 用户种类

	public UserBase(User user) {
		this.id = user.getId();
		this.username = user.getName();
		this.category = user.getCategory();
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public int getCategory() {
		return category;
	}
	
}
