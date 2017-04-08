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
<title>开店申请</title>
</head>
<body style="background-color: #997">
	<h5>欢迎来到HOSTEL系统</h5>
	<hr>
	<div class="container box">
		<h2>开店申请</h2>

		<s:form action="applyHotelNew" namespace="/homework">
			<s:textfield name="hotel.name" label="旅馆名称" id="usernameInput" />
			<div>
				<s:textfield name="hotel.address" label=" 地址" id="bankIdInput" />
			</div>
			<div>
				<s:textfield name="hotel.roomNum" label=" 房间总数量" id="bankIdInput" />
			</div>
			<div>
				<s:textfield name="hotel.price" label=" 房间单价" id="bankIdInput" />
			</div>

			<s:textfield name="user.name" label="请输入申请人姓名" />
			<s:password name="user.password" label=" 请输入密码" />
			<s:password name="password2" label=" 请确认密码" />
			<div>
					<s:textfield name="user.bankId" label=" 银行卡号" id="bankIdInput"
						/>
				</div>
			<s:submit value="提交"></s:submit>
		</s:form>

	</div>
</body>
</html>