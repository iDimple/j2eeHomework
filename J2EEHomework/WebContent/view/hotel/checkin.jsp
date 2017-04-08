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
<title>入住登记</title>
<link rel="stylesheet"
	href="<%=basePath%>/lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=basePath%>/lib/bootstrap-datetimepicker.min.css">
<script src="<%=basePath%>/lib/jquery/jquery-1.12.1.min.js"></script>
<script src="<%=basePath%>/lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/lib/moment.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/calendar.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/lib/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/account.js"></script>
<script type="text/javascript">
	window.onload = function() {
		var radios = document.getElementsByName('object');
		for (var i = 0; i < radios.length; i++) {
			radios[i].indexs = i + 1;
			radios[i].onchange = function() {
				if (this.checked) {
					document.getElementById("id1").style.display = "none";
					document.getElementById("id2").style.display = "none";
					document.getElementById("id" + this.indexs).style.display = "block";
				}
			}
		}
	}
	function sum(){
		var check = document.getElementById("summoney");
		var days = document.getElementById("days");
		var price = document.getElementById("price");
		var number = document.getElementById("number");
		check.value = days.value*price.value*number.value;
		return true;
	}
</script>

</head>
<body style="background-color: #997">
	<s:include value="/common/header.jsp"></s:include>

	<label><input type="radio" name="object" value="1" id="users">会员</label>

	<input type="radio" id="usergroup" name="object" value="0" />
	<label for="radioB">非会员</label>


	<span id="id1" style="display: none;"> <s:form
			action="checkInMember" namespace="/homework">
			<s:textfield name="user.id" label=" 会员号" />
			<s:radio label="付款方式" name="paytype"
				list="%{#{'1':'会员卡付款','0':'现金结账'}}" value="1"></s:radio>
			<s:textfield name="order.id" label=" 订单标志符" />
			<s:submit value="Submit" />
		</s:form>
	</span>

	<span id="id2" style="display: none;"> <s:form
			action="checkinNot" namespace="/homework">
			<s:textfield name="notM.memberId" label=" 姓名" />
			选择入住时间 <select id="selYear" name="selYear"></select>
			<select id="selMonth" name="selMonth"></select>
			<select id="selDay" name="selDay"></select>
			<script type="text/javascript">
				var selYear = window.document.getElementById("selYear");
				var selMonth = window.document.getElementById("selMonth");
				var selDay = window.document.getElementById("selDay");
				//新建一个DateSelector类的实例，将三个select对象传进去 
				var dt = new Date();
				new DateSelector(selYear, selMonth, selDay, dt);
			</script>
			<s:textfield name="notM.days" label=" 入住天数" onchange="return sum()" id="days"/>
			<s:textfield name="notM.roomNo" label=" 预定房间数" onchange="return sum()" id="number"/>
			<div>
				<s:textfield name="notM.price" label=" 房间单价" id="price"
					readonly="true" cssStyle="background-color:grey" />
			</div>
			<s:textfield name="notM.summoney" label=" 总价" readonly="true"
				cssStyle="background-color:grey" id="summoney" />
			<s:submit value="Submit" />
		</s:form>
	</span>


</body>
</html>