<%if(session.getAttribute("username")==null)
	response.sendRedirect("/user/Login.jsp");%>

<%@ page  language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示所有页面</title>
</head>
<body>
你好 ，<%=session.getAttribute("username") %><br>
你所选课程如下：点击相应课程查看详情<br>
<jsp:useBean id="homework" type="model.Homework" scope="session"></jsp:useBean>
<jsp:useBean id="item"  class="model.Result" scope="page"></jsp:useBean>
<%!String a="";String b=""; 
String url="";%>
<% for(int i=0;i<homework.getResult().size();i++){
	url="";
	pageContext.setAttribute("item", homework.getResult().get(i));
	a=homework.getResult().get(i).getCid();
	b=homework.getResult().get(i).getResult();
	url="/J2ee23/ShowResults?cid="+a+"&result="+b;
%>
<a href=<%=url%>>
<jsp:getProperty property="cid" name="item"/>
</a>

<br>
<%} %>
<form method="GET" action="/J2ee23/Login">
<input type="submit" name="Logout" value="Logout"></form>
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