package com.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.service.ResultService;
import com.util.LoginHelper;

/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年2月26日 下午4:49:18
 */
//@Controller用于标注控制层组件（如struts中的action）
@Controller
public class ShowResultsAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	@Autowired
	private  ResultService resultService;
	public String execute(){
		HttpSession session=request.getSession(false);
		//判断之前是否有退出
				boolean isLogout=false;
				if (null != request.getParameter("Logout")) {
					if (null != session) {
						session.invalidate();
						session = null;
					}
					System.out.println("logout!");
					isLogout=true;
				}
				if(isLogout){
					session.setAttribute("logout", "logout");
				}
		boolean loginResult=true;
		try {
			loginResult = LoginHelper.checkLoginState(request, response);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}	
		if(loginResult){
			//已登陆
			String cid=request.getParameter("cid");
			String sid=(String)session.getAttribute("sid");
			System.out.println(sid+cid);
			String result=resultService.getResult(sid, cid);
			System.out.println("result"+result);
			String msg="当前查看的课程为："+cid+"<br>";
			switch (result) {
			case "unsubmitted":
				msg+="您有未提交的作业，请及时提交！";
				break;
			case "passed":
				msg+="您的作业已按时提交并通过啦~";
				break;
			case "failed":
				msg+="您的作业不及格，请及时修改订正哦~";
				break;
			default:
				msg+="网页发生了错误/(ㄒ_ㄒ)/~~";
				break;
			}
			session.setAttribute("msg", msg);
			return "success";
		}
		return "error";
	}
}
