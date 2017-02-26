<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="checkLogin" uri="/WEB-INF/tlds/checkLogin.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程列表</title>
</head>
<body>
	<checkLogin:checkLogin></checkLogin:checkLogin>
	你好 ，<%=session.getAttribute("username")%><br> 你所选课程如下：点击相应课程查看详情
	<br>
	<jsp:useBean id="courseList" type="com.model.CourseList" scope="session"></jsp:useBean>
	<%!String a = "";
	String url = "";%>
	<%
		for (int i = 0; i < courseList.getCourseList().size(); i++) {
			url = "";
			a = courseList.getCourseList().get(i);
			url = request.getContextPath()+"/ShowResults?cid=" + a;
	%>
	<a href=<%=url%>> <%=a%>
	</a>

	<br>
	<%
		}
	%>
	<form method="GET"  action="<%=response.encodeURL(request.getContextPath()
					+ "/view/Login.jsp")%>">
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