<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎来到学生作业查询系统</title>
</head>
<body>
	欢迎来到学生作业查询系统
	<hr>
	<%
		if (null != request.getParameter("Logout")) {
			if (null != session) {
				session.removeAttribute("login");
			}
		}
	%>
	<s:form action="login" namespace="/homework">
		<s:textfield name="student.sid" label=" 学号：" />
		<s:password name="student.password" label=" 密码：" />
		<s:submit value="Submit" />
	</s:form>

	<%
		ServletContext Context = getServletContext();
		int allNumCounter = Integer.parseInt((String) Context.getAttribute("allNumCounter"));
		int onlineCounter = Integer.parseInt((String) Context.getAttribute("onlineCounter"));
		int guestCounter = Integer.parseInt((String) Context.getAttribute("guestCounter"));
		String message = "在线总人数：" + allNumCounter + "，在线登陆人数：" + onlineCounter + "，在线游客数：" + guestCounter;
		out.println("<p>" + message + "</p>");
	%>
</body>
</html>