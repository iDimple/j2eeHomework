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
<script src="<%=basePath%>/lib/jquery/jquery-1.12.1.min.js"></script>
<script src="<%=basePath%>/lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/calendar.js"></script>
<title>预定房间</title>
<script type="text/javascript">
function sum(){
	var check = document.getElementById("summoney");
	var days = document.getElementById("days");
	var discount = document.getElementById("discount");
	var price = document.getElementById("price");
	var number = document.getElementById("number");
	check.value = days.value*price.value*number.value*discount.value;
	return true;
}
</script>
</head>
<body style="background-color: #997">
	<s:include value="../common/header.jsp"></s:include>
	<div class="container box">
		<ul class="nav nav-tabs nav-stacked sidebar">
			<li>预定房间</li>
		</ul>

		<div>
			<div class="content">
				<s:form action="generateOrders" namespace="/homework">
					<%
						String hotelid;
					%>
					<%
						hotelid = request.getParameter("hotelid");
					%>
					<s:textfield name="hotelname" label="预定客栈" />
					入住时间
<select id="selYear" name="selYear"></select>
					<select id="selMonth" name="selMonth"></select>
					<select id="selDay" name="selDay"></select>
					<script type="text/javascript">
						var selYear = window.document.getElementById("selYear");
						var selMonth = window.document
								.getElementById("selMonth");
						var selDay = window.document.getElementById("selDay");
						//新建一个DateSelector类的实例，将三个select对象传进去 
						var dt = new Date();
						new DateSelector(selYear, selMonth, selDay, dt);
					</script>
<div>
						<s:textfield name="order.days" label=" 预订天数" id='days' onchange="return sum()"/>
					</div>
					<div>
						<s:textfield name="order.roomNo" label=" 房间数量" id='number' 
						onchange="return sum()"/>
					</div>
					<div>
						<s:textfield name="order.price" label=" 房间单价" id='price' readonly="true"
					cssStyle="background-color:grey"/>
					</div>
					<s:textfield name="discount" label=" 会员折扣" readonly="true"
					cssStyle="background-color:grey" id="discount" value="0.95"/>
					<s:textfield name="order.summoney" label=" 总价" readonly="true"
					cssStyle="background-color:grey" id="summoney"/>
					<s:submit value="预定"></s:submit>
				</s:form>
			</div>
		</div>
	</div>
</body>
</html>