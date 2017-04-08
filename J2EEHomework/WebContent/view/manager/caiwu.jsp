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
<title>财物状况</title>
</head>
<body style="background-color: #997">
<s:include value="/common/header.jsp"></s:include>
	
	<div class="container box">
		<ul class="nav nav-tabs nav-stacked sidebar">
			<li >财物状况</li>
			<li >总交易额：<s:property value="daozhang" /></li>
		</ul>
		<div >
			<!-- 我的订单 -->
			<div  id="myOrder">
				<div class="content">
					<table class="list" border="1">
						<tr>
							<th>财 物 id</th>
							<th>订 单 id</th>
							<th>总 价</th>
							<th>状 态</th>
						</tr>
						<s:iterator id="order" value="caiwu">
							<tr>
								<td><s:property value="#order.id" /></td>
								<td>${order.orderId}</td>
								<td>${order.sum}</td>
								<td>${order.state}</td>
							</tr>
						</s:iterator>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>