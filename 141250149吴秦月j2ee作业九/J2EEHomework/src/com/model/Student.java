package com.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年2月22日 下午7:26:22
 */
@Entity
@Table(name="student")
public class Student implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8447113004133577973L;
	@Id
	private String sid;
	private String username;
	private String password;
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
