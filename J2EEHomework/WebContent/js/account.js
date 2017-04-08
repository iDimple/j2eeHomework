function check(){
	var check = document.getElementById("errorInfo");	
	if(changeUsername()&&changeBankId()){
		check.style.color = "red"; 
		check.innerText = "修改成功";
		alert("修改成功");
	}
	check.style.color = "red"; 
	alert("修改失败");
	check.innerText = "修改shibai";
	return false;
}

//验证用户名是否符合规则
function changeUsername() {
	var nameInput = document.getElementById("usernameInput");
	var check = document.getElementById("errorInfo");
	var newName = nameInput.value;
	if (newName != null && (/\w{1,10}/.test(newName)) ) {
		check.innerHTML = "";
		return true;
	} else {
		check.style.color = "red";  
		check.innerText = "用户名不能超过10个字符且不能为空，由字母数字下滑线或汉字组成";  
		return false;
	}
}


//改变或绑定银行卡号
function changeBankId() {
	var bankIdInput = document.getElementById("bankIdInput");
	var newBankId = bankIdInput.value;
	var check = document.getElementById("errorInfo");  
	//验证是否为空  
	if (newBankId ==　"") {  
		check.style.color = "red";  
		System.out.println("银行卡号不能为空!"  );
		check.innerHTML = "银行卡号不能为空!"            ;  
		return false;
	}  
	//验证格式  
	else if (/\d{16}/.test(newBankId)) {  
//格式正确
		check.innerHTML = "";
		System.out.println("英航卡号格式正确");
		return true;
	} else {
		check.style.color = "red";  
		check.innerHTML = "请输入正确的银行卡格式16位";  
		System.out.println("请输入正确的银行卡格式16位");
		return false;
	}
}





function changePassword() {
	var newPasswordInput = document.getElementById("newPasswordInput");
	var passwordConfirmInput = document.getElementById("passwordConfirmInput");
	var newPassword = newPasswordInput.value;
	var passwordConfirm = passwordConfirmInput.value;
	if (newPassword.length >= 8 && newPassword.length <= 16) {
		if (newPassword == passwordConfirm) {
			var oldPasswordInput = document.getElementById("oldPasswordInput");
			$.post("user/checkOldPassword.action", {
				"map.oldPassword" : oldPasswordInput.value
			}, function(json) {
				if (json.map.result == "success") {
					$.post("user/repeatStringField.action", {
						"map.field" : "password",
						"map.value" : newPassword
					}, function(json) {
						if (json.map.result == "success") {
							oldPasswordInput.value = "";
							newPasswordInput.value = "";
							passwordConfirmInput.value = "";
							messageShow.innerHTML = "密码设置成功";
						} else {
							oldPasswordInput.value = "";
							newPasswordInput.value = "";
							passwordConfirmInput.value = "";
							messageShow.innerHTML = "密码设置失败";
						}
					});
				} else {
					messageShow.innerHTML = "原密码输入错误";
				}
			});
		} else {
			newPasswordInput.value = "";
			passwordConfirmInput.value = "";
			messageShow.innerHTML = "两次输入的新密码必须相同";
		}
	} else {
		newPasswordInput.value = "";
		passwordConfirmInput.value = "";
		messageShow.innerHTML = "新密码为8-16位";
	}
}




function inactiveAccount() {
	var messageShow = document.getElementById("message");
	$.post("user/inactiveAccount.action", {}, function(json) {
		if (json.map.result == "success") {
			document.getElementById("userStateInput").value = "正常使用";
			document.getElementById("balanceInput").value = json.map.balance;
			messageShow.innerHTML = "账户激活成功";
		} else {
			messageShow.innerHTML = "账户激活失败";
		}
	});
}// 激活账户

function renewalAccount() {
	var messageShow = document.getElementById("message");
	$.post("user/renewalAccount.action", {}, function(json) {
		if (json.map.result == "success") {
			document.getElementById("userStateInput").value = "正常使用";
			document.getElementById("balanceInput").value = json.map.balance;
			document.getElementById("userLevelInput").value = json.map.level;
			messageShow.innerHTML = "账户续费成功";
		} else {
			messageShow.innerHTML = "账户续费失败";
		}
	});
}// 续费账户

function rechargeAccount() {
	var messageShow = document.getElementById("message");
	var amount = document.getElementById("amountInput").value;// 充值金额
	if (amount == null || isNaN(amount) || amount.length > 5) {
		document.getElementById("amountInput").value = "";
		document.getElementById("amountInput").placeholder = "请输入数字,且不超过99999";
	} else {
		$
		.post(
				"user/rechargeAccount.action",
				{
					"map.amount" : amount
				},
				function(json) {
					if (json.map.result == "success") {
						document.getElementById("balanceInput").value = json.map.balance;
						document.getElementById("userLevelInput").value = json.map.level;
						messageShow.innerHTML = "账户充值成功";
					} else {
						messageShow.innerHTML = "账户充值失败";
					}
				});
		$('#rechargeAccountModel').modal('hide');// 隐藏模态框
	}
}

function cancleMembership() {
	var messageShow = document.getElementById("message");
	var password = document.getElementById("cancleConfirmInput").value;
	if (password == null || password.length < 8 || password.length > 16) {
		document.getElementById("cancleConfirmInput").value = "";
		document.getElementById("cancleConfirmInput").placeholder = "请输入密码，8-16位";
	} else {
		$
		.post(
				"user/cancleMembership.action",
				{
					"map.password" : password
				},
				function(json) {
					if (json.map.result == "success") {
						messageShow.innerHTML = "会员资格已终止";
						document.getElementById("userStateInput").value = "停止";
						document.getElementById("cancleMemberBtn").disabled = "disabled";
						var btns = [ "inactiveAccountBtn",
							"renewalAccountBtn",
							"rechargeAccountBtn" ];
						var i = 0;
						for (i = 0; i < btns.length; i++) {
							if (document.getElementById(btns[i]) != null) {
								document.getElementById(btns[i]).disabled = "disabled";
							}
						}
						$('#cancleMembershipModel').modal('hide');// 隐藏模态框
					} else if (json.map.result == "passwordError") {
						document.getElementById("cancleConfirmInput").value = "";
						document.getElementById("cancleConfirmInput").placeholder = "密码错误";
					} else {
						document.getElementById("cancleConfirmInput").value = "";
						document.getElementById("cancleConfirmInput").placeholder = "未知错误请重新尝试";
					}
				});
	}
}// 取消会员资格
