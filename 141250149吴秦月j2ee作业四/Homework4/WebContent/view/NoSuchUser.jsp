<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="checkLogin" uri="/WEB-INF/tlds/checkLogin.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户名密码错误，请重新输入</title>
</head>
<body>
	<checkLogin:checkLogin></checkLogin:checkLogin>
	用户名密码错误，请重新输入
	<hr>

	<form action="/Homework4/Login" method=post>
		学号： <input type=text name=sid><br> 密码： <input
			type=password name=password><br> <input type=submit
			value="登陆">
	</form>
	<%
		ServletContext Context = getServletContext();
		int allNumCounter = Integer.parseInt((String) Context.getAttribute("allNumCounter"));
		int onlineCounter = Integer.parseInt((String) Context.getAttribute("onlineCounter"));
		int guestCounter = Integer.parseInt((String) Context.getAttribute("guestCounter"));
		String message = "在线总人数：" + allNumCounter + "，在线登陆人数：" + (onlineCounter) + "，在线游客数：" + (guestCounter);
		out.println("<p>" + message + "</p>");
	%>
</body>
</html>