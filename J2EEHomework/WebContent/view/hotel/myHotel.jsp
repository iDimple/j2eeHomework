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
<title>编辑我的店</title>
</head>
<body style="background-color: #997">
<s:include value="/common/header.jsp"></s:include>
	<div class="container box" align="center">
		<ul class="nav nav-tabs nav-stacked sidebar">
			<li >编辑我的店</li>
		</ul>
		<s:form action="edit" namespace="/homework">
				<s:textfield name="hotel.name" label="旅馆名称" id="usernameInput"
					 />
				<div>
					<s:textfield name="hotel.address" label=" 地址" id="bankIdInput"
						/>
				</div>
				<div>
					<s:textfield name="hotel.roomNum" label=" 房间总数量" id="bankIdInput"
						/>
				</div>
				<div>
					<s:textfield name="hotel.owner" label=" 申请人ID" id="bankIdInput"
						/>
				</div>
				<s:textfield name="hotel.state" label=" 状态" readonly="true"
					cssStyle="background-color:grey" />		
				<s:submit value="编辑" ></s:submit>
			</s:form>
			
		</div>
</body>
</html>