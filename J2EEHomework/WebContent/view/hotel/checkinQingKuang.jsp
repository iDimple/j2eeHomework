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
<title>查看入住情况</title>
</head>
<body>
<s:include value="/common/header.jsp"></s:include>
	<div class="container box">
		<ul class="nav nav-tabs nav-stacked sidebar">
			<li >入住情况</li>
		</ul>
		<div >
			<!-- 入住情况 -->
			<div  id="myHotels">
				<div class="content">
					<table class="list">
						<tr>
							<th>序号</th>
							<th>房间号</th>
							<th>顾客姓名</th>
							<th>房间单价</th>
							<th>入住时间</th>
							<th>入住天数</th>
						</tr>
						<s:iterator id="hotel" value="myHotels">
							<tr>
								<td><s:property value="#hotel.id" /></td>
								<td>${hotel.name}</td>
								<td>${hotel.address}</td>
								<td>${hotel.roomNum}</td>
								<td>${hotel.remainNum}</td>
								<td>${hotel.price}</td>
								<td>${hotel.time}</td>
								<td>${hotel.state}</td>
							</tr>
						</s:iterator>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>