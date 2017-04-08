<%@page import="com.util.UserBase"%>
<%@page import="com.util.UserCategory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	UserBase userbase = null;//session中的用户信息
	String basePath = request.getContextPath();
%>
<nav class="navbar navbar-inverse navbar-static-top" role="navigation">
	<div class="navbar-header">
		<a class="navbar-brand" >Hostel</a>
	</div>
	<div>
		<ul class="nav navbar-nav">
			<%
				Object obj = session.getAttribute("userBase");
				if (obj == null) {
			%>
			<li class="active"><a href="#">欢迎来到Hostel</a></li>
			<%
				} else {
					userbase = (UserBase) obj;
					switch (userbase.getCategory()) {
					case UserCategory.MENBER:
			%>
			<li><a href="<%=basePath%>/homework/memberAccount.action">我的账户</a></li>
			<li><a href="<%=basePath%>/homework/myOrder.action">我的订单</a></li>
			<li><a href="<%=basePath%>/homework/reserveRoom.action">预定房间</a></li>
			<%
				break;
					case UserCategory.MANAGER:
			%>
			<li><a href="<%=basePath%>/homework/shenpi.action"> 审批</a></li>
			<li><a href="<%=basePath%>/homework/jiesuan.action">结算</a></li>
			<li><a href="<%=basePath%>/homework/ruzhu.action">查看各店入住情况</a></li>
			<li><a href="<%=basePath%>/homework/xiaofei.action">查看会员消费情况</a></li>
			<li><a href="<%=basePath%>/homework/caiwu.action">查看财务情况</a></li>
			<%
				break;
					case UserCategory.WAITER:
			%>
			<li><a href="<%=basePath%>/homework/myHotelAction.action">我的店</a></li>
			<li><a href="<%=basePath%>/homework/releasePlan.action">发布计划</a></li>
			<li><a href="<%=basePath%>/homework/initnotMember.action">入店登记</a></li>
			<li><a href="<%=basePath%>/homework/initOrder.action">退房登记</a></li>
			<li><a href="<%=basePath%>/homework/tongji.action">查看本店统计信息</a></li>
<%
				break;
					}
				}
			%>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<%
				if (userbase == null) {
			%>
			<li><a href="<%=basePath%>/view/Login.jsp">登录/注册</a></li>
			<%
				} else {
			%>
			<li><a id="username-show"><%=userbase.getUsername()%></a></li>
			<li><a id="username-show"><%=userbase.getId()%></a></li>
			<li><a href="<%=basePath%>/view/Login.jsp">退出</a></li>
			<%
				}
			%>
		</ul>
	</div>
</nav>
