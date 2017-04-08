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
<link rel="stylesheet" href="<%=basePath%>/lib/bootstrap/css/bootstrap.min.css">
<script src="<%=basePath%>/lib/jquery/jquery-1.12.1.min.js"></script>
<script src="<%=basePath%>/lib/bootstrap/js/bootstrap.min.js"></script>
<title>选择客栈</title>
</head>
<body style="background-color: #997">
<s:include value="../common/header.jsp"></s:include>
	<div class="container box">
		<ul class="nav nav-tabs nav-stacked sidebar">
			<li >选择客栈</li>
		</ul>
		<div >
			<!-- 我的订单 -->
			<div  id="myOrder">
				<div class="content">
					<table class="list" border="1">
						<tr>
							<th>序号</th>
							<th>客栈名</th>
							<th>客栈地址</th>
							<th>剩余房间数</th>
							<th>价格</th>
							<th>选择</th>
						</tr>
						<s:iterator id="hotellist" value="hotels">
							<tr>
								<td><s:property value="#hotellist.id" /></td>
								<td>${hotellist.name}</td>
								<td>${hotellist.address}</td>
								<td>${hotellist.remainNum}</td>
								<td>${hotellist.price}</td>
								<td><s:a namespace="/homework" action="chooseTime" >
								<s:param name="hotelid" value="#hotellist.id"></s:param>
								<s:param name="hotelname" value="#hotellist.name"></s:param>
								<s:param name="price" value="#hotellist.price"></s:param>
								<s:param name="remainNum" value="#hotellist.remainNum"></s:param>
								预定
								
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