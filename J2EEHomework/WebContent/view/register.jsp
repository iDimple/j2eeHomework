<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎注册Hostel客栈系统</title>
</head>
<body style="background-color: #997">
欢迎来到HOSTEL系统
	<hr>
<%
	String basePath = request.getContextPath();
%>
<%
		session = request.getSession(false);
		String msg = (String) session.getAttribute("registerMsg");
		//if(msg==null)msg="欢迎";
	%>
	
<s:form action="register" namespace="/homework" method="post" >
			<h2 >会员注册</h2>
			<s:textfield name="user.name" label="请输入昵称，不超过10个字符"  />
			<s:password name="user.password" label=" 请输入密码" />
			<s:password name="password2" label=" 请确认密码" />
			<div>
					<s:textfield name="user.bankId" label=" 银行卡号" id="bankIdInput"
						/>
				</div>
			<s:submit value="Submit" />
		</s:form>
		
		<div >
			<label>
				
				<a href="<%=basePath%>/view/Login.jsp">已有账号,直接登陆</a>
			</label>
		</div>
</body>
</html>