package com.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年2月22日 下午7:20:20
 */
@Entity
@Table(name="homework")
public class Homework implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4379124724165361422L;
	//这个一定要有，否则会报No identifier specified for entity: 
	@EmbeddedId
	private HomeworkPK id;
	private String result;
	
	public HomeworkPK getId() {
		return id;
	}
	public void setId(HomeworkPK id) {
		this.id = id;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
}
