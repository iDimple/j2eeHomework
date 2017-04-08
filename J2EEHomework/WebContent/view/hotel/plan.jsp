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
<script type="text/javascript" src="<%=basePath%>/js/calendar.js"></script>
<title>发布促销计划</title>
</head>
<body style="background-color: #997" >
<s:include value="/common/header.jsp"></s:include>
发布促销计划
<br>

选择开始日期
<select id="selYear" ></select> 
<select id="selMonth"></select> 
<select id="selDay"></select> 
<script type="text/javascript"> 
var selYear = window.document.getElementById("selYear"); 
var selMonth = window.document.getElementById("selMonth"); 
var selDay = window.document.getElementById("selDay");
//新建一个DateSelector类的实例，将三个select对象传进去 
var dt = new Date(); 
new DateSelector(selYear, selMonth ,selDay, dt); 
</script> 
选择结束日期
<select id="endYear" ></select> 
<select id="endMonth"></select> 
<select id="endDay"></select> 
<script type="text/javascript"> 
var selYear = window.document.getElementById("endYear"); 
var selMonth = window.document.getElementById("endMonth"); 
var selDay = window.document.getElementById("endDay");
//新建一个DateSelector类的实例，将三个select对象传进去 
var dt = new Date(); 
new DateSelector(endYear, endMonth ,endDay, dt); 
</script> 
<s:form action="plan" namespace="/homework">

<div class="input-group">
					<s:textfield name="hotel.price" label=" 房间单价" id='birthdayInput' />
				</div>
<s:submit value="修改" ></s:submit>
			</s:form>
</body>
</html>