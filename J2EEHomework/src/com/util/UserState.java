package com.util;
/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年3月4日 下午4:28:37
 */
public enum UserState {
	// 未激活,正常使用,暂停,停止
	INACTIVE("未激活(充值1000元激活)") ,NORMAL("正常使用") ,USPEND("暂停"),STOP("停止") ;
	private String str;
	private UserState(String s){
		str=s;
	}
	public String toString(){  //覆盖了父类Enum的toString()  
        return super.toString()+str;  
        } 

}
