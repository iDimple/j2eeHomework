package com.time;
import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
/**
* @author 141250149吴秦月
* @date 创建时间：2017年3月15日 下午5:21:35
*/
import org.springframework.scheduling.annotation.Scheduled;  
import org.springframework.stereotype.Component;

import com.model.User;
import com.service.LoginService;
import com.util.UserState;  
  
@Component("TestJob")    
public class Shuaxin { 
	@Autowired
	LoginService loginService;
        @Scheduled(cron = "0 0 2 * * ?")//每天凌晨2点  
    public void refresh()  
    {  
//取得所有会员的上次订单时间
        	
        	ArrayList<User> users=loginService.getMember();
        	for(int i=0;i<users.size();i++){
        		String state=users.get(i).getState();
        		Date today=new Date(System.currentTimeMillis());
        		int days=(int)(today.getTime()-users.get(i).getCreatedTime().getTime())/1000/24/60/60;
        		if(days>365){
        			if(users.get(i).getBalance()<0){
        				if(state.equals(UserState.USPEND.toString())){
        					users.get(i).setState(UserState.STOP.toString());
        				}else if(state.equals(UserState.NORMAL.toString())){
        					users.get(i).setState(UserState.USPEND.toString());
        				}
        				loginService.updateAll(users.get(i));
        			}
        		}
        	}
    }   
        
}  