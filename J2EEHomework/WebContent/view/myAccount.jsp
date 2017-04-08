<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.model.User"%>
<%@page import="com.util.UserState"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getContextPath();
String state=(String)request.getAttribute("user.state");
System.out.println(state);
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
<script type="text/javascript" src="<%=basePath%>/js/account.js"></script>
<script type="text/javascript">
function rechargeAccount() {
	var old=document.getElementById("balance").value;
	var amount = document.getElementById("amountInput").value;// 充值金额
	if (amount == null || isNaN(amount) || amount.length > 5) {
		document.getElementById("amountInput").value = "";
		document.getElementById("amountInput").placeholder = "请输入数字,且不超过99999";
	} else {
		
		$
				.post(
						"homework/chongzhi.action",
						{
							"map.amount" : amount
						});
		$('#rechargeAccountModel').modal('hide');// 隐藏模态框
	}
	var c = Number(old) + Number(amount) 
	document.getElementById("balance").value = c;
}
</script>
<title>账户信息</title>
</head>
<body style="background-color: #997">
	<s:include value="../common/header.jsp"></s:include>
	<div class="container" align="center">

		<div>

			<label>设置用户信息</label>

			<s:form action="saveAccount" namespace="/homework">
				<s:textfield name="user.name" label="姓名" id="usernameInput"
					 />
				<div>
					<s:textfield name="user.bankId" label=" 银行卡号" id="bankIdInput"
						/>
				</div>
				<div class="input-group">
					<s:textfield name="user.birthday" label=" 出生日期" id='birthdayInput' />
				</div>
				<s:submit value="修改" ></s:submit>
			</s:form>

			<label id="errorInfo"></label>

			<s:form action="changePassword" namespace="/homework">
				<div class="input-group">
					<s:password name="passwordOld" label=" 原密码 " placeholder="输入原密码"
						id="oldPasswordInput" />
				</div>
				<div class="input-group">
					<s:password name="passwordNew" label=" 新密码 " placeholder="输入新密码"
						id="newPasswordInput" />
				</div>
				<div class="input-group">
					<s:password name="passwordNew2" label=" 确认密码" placeholder="再次输入新密码"
						id="passwordConfirmInput" />
					<s:submit value="修改密码"></s:submit>
				</div>
			</s:form>

			<s:form action="changePassword" namespace="/homework">
				<s:textfield name="user.consumption" label=" 已消费金额" readonly="true"
					cssStyle="background-color:grey" />
				<s:textfield name="user.level" label=" 会员等级" readonly="true"
					cssStyle="background-color:grey" />
				<s:textfield name="user.balance" label=" 账户余额" readonly="true"
					cssStyle="background-color:grey" id="balance" />

			</s:form>
		</div>
		<div>
			<%
					if (state.equals(UserState.INACTIVE.toString())) {
						System.out.println("1111111111");
				%>
			<span class="input-group-btn">
				<button id="inactiveAccountBtn" class="btn" type="button"
					data-toggle="modal" data-target="#inactiveAccountModel">激活账户</button>
			</span>

			<%
					} else if (state.equals( UserState.STOP.toString())) {
				%>
			<span class="input-group-btn">
				<button class="btn btn-primary" type="button" disabled="disabled">账户停止</button>
			</span>
			<%
					} else if (state.equals( UserState.USPEND.toString())) {
				%>
			<span class="input-group-btn">
				<button id="renewalAccountBtn" class="btn
						type="
					button" data-toggle="modal" data-target="#renewalAccountModel">账户续费</button>
			</span>
			<%
					} else {
						System.out.println("zhengchang");
				%>
			<span class="input-group-btn">
				<button id="rechargeAccountBtn" class="btn" type="button"
					data-toggle="modal" data-target="#rechargeAccountModel">账户充值</button>
			</span>
			<%
					}
				%>
			<div class="modal fade" id="inactiveAccountModel" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">激活账户</h4>
						</div>
						<div class="modal-body">激活账户需要通过绑定银行卡充值1000元，是否现在激活？</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<s:form action="active" namespace="/homework">
								<s:submit value="确定">
								</s:submit>
							</s:form>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade" id="renewalAccountModel" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">账户续费</h4>
						</div>
						<div class="modal-body">会员记录已暂停,是否充值1000元恢复？</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<s:form action="active" namespace="/homework">
								<s:submit value="确定">
								</s:submit>
							</s:form>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade" id="rechargeAccountModel" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">账户充值</h4>
						</div>
						<div class="modal-body">
							<input id="amountInput" type="text" class="form-control"
								name="amountInput" placeholder="请输入充值金额">
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary"
								onclick="rechargeAccount()">确定</button>

						</div>
					</div>
				</div>
			</div>
		</div>
		<s:form action="duihuan" namespace="/homework">
			<div class="input-group">
				<s:textfield name="user.point" label=" 账户积分" readonly="true"
					cssStyle="background-color:grey"></s:textfield>
				<span class="input-group-btn"> <s:submit value="兑换积分"></s:submit>
				</span>
			</div>
		</s:form>
	</div>




	<div>
		<button id="cancleMemberBtn" class="btn btn-danger btn-block"
			type="button" data-toggle="modal"
			data-target="#cancleMembershipModel">取消会员资格</button>
		<div class="modal fade" id="cancleMembershipModel" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">取消会员资格</h4>
					</div>
					<div class="modal-body">会员资格取消无法恢复，确定取消？</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<s:form action="cancelmember" namespace="/homework">
								<s:submit value="确定">
								</s:submit>
							</s:form>
						</div>
					</div>
					
			</div>
		</div>
	</div>

</body>
</html>