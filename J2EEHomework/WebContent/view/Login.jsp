<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎来到HOSTEL系统</title>
</head>
<body style="background-color: #997">
	欢迎来到HOSTEL系统
	<hr>
	<%
		if (null != request.getParameter("Logout")) {
			if (null != session) {
				session.removeAttribute("login");
			}
		}
	%>
	<s:form action="login" namespace="/homework">
		<s:textfield name="user.id" label=" 用户Id" />
		<s:password name="user.password" label=" 密码" />
		<s:submit value="Submit" />
	</s:form>
	<%
	String basePath = request.getContextPath();
%>
<label>
				
				<a href="<%=basePath%>/view/register.jsp">没有账号,立即注册</a>
			</label>
			<label>
				
				<a href="<%=basePath%>/view/hotel/applyHotel.jsp">我要开店</a>
			</label>
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