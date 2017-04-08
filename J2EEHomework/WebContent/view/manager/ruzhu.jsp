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
<title>统计信息</title>
</head>
<body style="background-color: #997">
	<s:include value="/common/header.jsp"></s:include>
	<div class="container box">
		<ul class="nav nav-tabs nav-stacked sidebar">
			<li>入住情况</li>
		</ul>
		<div>
			<!-- 我的订单 -->
			<div id="myOrder">
				<div class="content">
					<table class="list" border="1">
						<tr>
							<th>客 栈 id</th>
							<th>订 单 标 志 符</th>
							<th>入 住 时 间</th>
							<th>预 定 时 间</th>
							<th>预 定 天 数</th>
							<th>预 定 旅 馆</th>
							<th>房 间 数 量</th>
							<th>总 价</th>
							<th>是 否 会 员</th>
							<th>会 员 编号／非会员名称</th>
						</tr>
						<s:iterator id="order" value="myOrders">
							<tr>
							<td><s:property value="#order.hotelId" /></td>
								<td><s:property value="#order.id" /></td>
								<td>${order.startday}</td>
								<td>${order.ordertime}</td>
								<td>${order.days}</td>
								<td>${order.name}</td>
								<td>${order.roomNo}</td>
								<td>${order.summoney}</td>
								<td>${order.cardState}</td>
								<td>${order.memberId}</td>
							</tr>
						</s:iterator>
					</table>
				</div>
			</div>
		</div>
	</div>

</body>
</html>