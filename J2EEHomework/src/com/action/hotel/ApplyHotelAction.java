package com.action.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.action.BaseAction;
import com.model.Hotel;
import com.model.User;
import com.service.HotelService;
import com.service.LoginService;
import com.util.ResultMsg;
import com.util.UserBase;
import com.util.UserCategory;

/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年3月7日 下午5:58:52
 */
@Controller
public class ApplyHotelAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private String password2;
	@Autowired
	private  LoginService loginService;
	@Autowired 
	HotelService hotelService;
	private Hotel hotel;

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public String execute(){
		return "success";
	}
	//要保存两个东东，一个是申请开店人的个人信息，一个是店的信息
	public String apply(){
		String message = "";
		System.out.println(user.getPassword());
		System.out.println(password2);
		if (user.getPassword().equals(password2)) {
			System.out.println("两次密码输入相同");
		} else {
			message = "两次输入密码不同";
			sessionMap.put("registerMsg", message);
			return INPUT;
		}
		//服务员
		user.setCategory(UserCategory.WAITER);
		ResultMsg result = loginService.register(user);
		if (result==ResultMsg.Success) {
			sessionMap.put("user", user);
			message =user.getId() ;
			sessionMap.put("registerMsg", message);
			//开始保存店的信息
			
			hotel.setOwner(user.getId());
			hotelService.saveHotel(hotel);
			UserBase userBase = new UserBase(user);
			super.sessionMap.put("userBase", userBase);
			return SUCCESS;
		} else {
			message = "注册失败，请重新注册";
			sessionMap.put("registerMsg", message);
			return INPUT;
		}
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
}
