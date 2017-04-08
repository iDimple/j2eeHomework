package com.action.manager;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.action.BaseAction;
import com.model.Caiwu;
import com.service.OrderService;

/**
* @author 141250149吴秦月
* @date 创建时间：2017年3月15日 下午4:06:46
*/
@Controller
public class CaiwuAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	@Autowired
	OrderService orderService;
	private double daozhang;
	public double getDaozhang() {
		return daozhang;
	}
	public void setDaozhang(double daozhang) {
		this.daozhang = daozhang;
	}
	private ArrayList<Caiwu> caiwu;
	public ArrayList<Caiwu> getCaiwu() {
		return caiwu;
	}
	public void setCaiwu(ArrayList<Caiwu> caiwu) {
		this.caiwu = caiwu;
	}
	public String caiwu(){
		//财物表格
				System.out.println("财物状况");
				caiwu=orderService.getAllCai();
				daozhang=0;
				for(int i=0;i<caiwu.size();i++){
						daozhang+=caiwu.get(i).getSum();
				}
		return SUCCESS;
	}

}
