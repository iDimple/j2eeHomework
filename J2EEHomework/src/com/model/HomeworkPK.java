package com.model;

import java.io.Serializable;


/**
* @author 141250149吴秦月
* @date 创建时间：2017年2月23日 上午10:34:07
* 关于联合主键类，大家一定要遵守以下几点JPA规范：

必须提供一个public的无参数构造函数。
必须实现序列化接口。
必须重写hashCode()和equals()这两个方法。这两个方法应该采用复合主键的字段作为判断这个对象是否相等的。
联合主键类的类名结尾一般要加上PK两个字母代表一个主键类，不是要求而是一种命名风格。
*/
public class HomeworkPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3472283392849605641L;
	private String sid;
	private String cid;
	
	public HomeworkPK(String sid, String cid) {
		super();
		this.sid = sid;
		this.cid = cid;
	}
	public HomeworkPK() {
		super();
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	 @Override
	    public int hashCode() {
	        final int PRIME = 31;
	        int result = 1;
	        result = PRIME * result + ((sid == null) ? 0 : sid.hashCode());
	        result = PRIME * result
	                + ((cid == null) ? 0 : cid.hashCode());
	        return result;
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (getClass() != obj.getClass())
	            return false;
	        final HomeworkPK other = (HomeworkPK) obj;
	        if (sid == null) {
	            if (other.sid != null)
	                return false;
	        } else if (!sid.equals(other.sid))
	            return false;
	        if (cid == null) {
	            if (other.cid != null)
	                return false;
	        } else if (!cid.equals(other.cid))
	            return false;
	        return true;
	    }


}
