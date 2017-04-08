<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<s:include value="../common/header.jsp"></s:include>
<%
	String basePath = request.getContextPath();
%>
<%
		session = request.getSession(false);
		String msg = (String) session.getAttribute("registerMsg");
		String name = (String) session.getAttribute("username");
	%>
	欢迎,<%=name %>！会员号：
	<p><%=msg%></p>
	 请记下会员号，本系统只支持用会员号登陆！
</body>
</html>