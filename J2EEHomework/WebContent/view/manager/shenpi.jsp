<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="<%=basePath%>/lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=basePath%>/lib/bootstrap-datetimepicker.min.css">
<script src="<%=basePath%>/lib/jquery/jquery-1.12.1.min.js"></script>
<script src="<%=basePath%>/lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/lib/moment.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/lib/bootstrap-datetimepicker.js"></script>
<title>审批</title>
</head>
<body style="background-color: #997">
<s:include value="/common/header.jsp"></s:include>
	<div class="container box">
		<ul class="nav nav-tabs nav-stacked sidebar">
			<li >审批</li>
		</ul>
		<div >
			<div  id="myOrder">
				<div class="content">
					<table class="list" border="1">
						<tr>
							<th>客 栈 id</th>
							<th>地 址</th>
							<th>名 称</th>
							<th>申 请 人 id</th>
							<th>房 间 单 价</th>
							<th>房 间 数 量</th>
							<th>申 请 时 间</th>
							<th>状 态</th>
						</tr>
						<s:iterator id="order" value="hotels">
							<tr>
								<td><s:property value="#order.id" /></td>
								<td>${order.address}</td>
								<td>${order.name}</td>
								<td>${order.owner}</td>
								<td>${order.price}</td>
								<td>${order.roomNum}</td>
								<td>${order.time}</td>
								<td>
								<s:a namespace="/homework" action="approve" >
								<s:param name="hotelid" value="#order.id"></s:param>
								<s:param name="roomno" value="#order.roomNo"></s:param>
								<s:param name="name" value="#order.name"></s:param>
								点击审批通过
							</s:a></td>
							</tr>
						</s:iterator>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>