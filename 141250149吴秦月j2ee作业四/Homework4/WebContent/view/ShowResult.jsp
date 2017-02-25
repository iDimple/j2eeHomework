<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="checkLogin" uri="/WEB-INF/tlds/checkLogin.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>作业提交情况</title>
</head>
<body>
	<checkLogin:checkLogin></checkLogin:checkLogin>
	你好：
	<%
		session = request.getSession(false);
		String msg = (String) session.getAttribute("msg");
	%>
	<%=session.getAttribute("username")%>
	<br>

	<p><%=msg%></p>

	<form method="GET" action="/Homework4/view/CourseList.jsp">
		<input type="submit" name="Back" value="Back">
	</form>
	<form method="GET" action="/Homework4/view/Login.jsp">
		<input type="submit" name="Logout" value="Logout">
	</form>

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