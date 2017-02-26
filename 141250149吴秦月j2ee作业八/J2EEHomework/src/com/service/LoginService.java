package com.service;


/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年2月6日 下午9:37:27
 */
public interface LoginService {
	public boolean isValid(String sid,String password);
	public String getName(String sid);
}
