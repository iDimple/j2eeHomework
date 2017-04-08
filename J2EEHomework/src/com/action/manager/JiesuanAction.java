package com.action.manager;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.action.BaseAction;
import com.model.Caiwu;
import com.service.OrderService;

/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年3月15日 下午1:25:06
 */
@Controller
public class JiesuanAction extends BaseAction{
	@Autowired
	OrderService orderService;
	private ArrayList<Caiwu> caiwu;
	private int caiwuid;
	
	public int getCaiwuid() {
		return caiwuid;
	}
	public void setCaiwuid(int caiwuid) {
		this.caiwuid = caiwuid;
	}
	public ArrayList<Caiwu> getCaiwu() {
		return caiwu;
	}
	public void setCaiwu(ArrayList<Caiwu> caiwu) {
		this.caiwu = caiwu;
	}
	private static final long serialVersionUID = 1L;
	public String jiesuan(){
		caiwu=orderService.getJiesuan("等待结算");
		//把财物表的state是等待结算的都拿上来
		return SUCCESS;
	}
	public String pay(){
		//将该条财物的state改为到账
		System.out.println(caiwuid);
		Caiwu cc=orderService.getCaiwuById(caiwuid);
		cc.setState("到账");
		orderService.updateCaiwu(cc);
		return SUCCESS;
	}

}
