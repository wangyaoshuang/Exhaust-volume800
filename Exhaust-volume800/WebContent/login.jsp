<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<meta charset="UTF-8">

<title>登录</title>
<link rel="stylesheet" href="${ctx }/css/login.css" media="screen" type="text/css" />
<script type="text/javascript">
window.choice="";
window.checkOn=0;
</script>

<link rel="stylesheet" href="css/haha.css" media="screen" type="text/css" />

</head>

<body>
<div id="window" style="display:none;">

	<div class="page page-front">
		<div class="page-content">
			<div class="input-row">
				<label class="label">用户名</label>
				<input name="username" id="username" type="text" class="input" value="${username }"/>
			</div>
			<div class="input-row">
				<label class="label">密码</label>
				<input name="password" id="password" type="password" class="input" value="${password }"/>
			</div>
			
			<div class="input-row">
				<label class="label">验证码</label>
				<input id="checkcode" name="checkcode" type="text" class="input"/>
				<img id="codepic" src="${ctx}/teacher/generatecheckcode" title="点击更换" alt="验证码占位图" onclick="this.src='${ctx}/teacher/generatecheckcode?tm='+Math.random()"/>
			</div>

			

         	<div class="jiaowu">
				<input type="radio" name="choice"  value="教务" onclick="check_power(this)"/>教务&nbsp;&nbsp;&nbsp;
				
				
				<input type="radio" name="choice" value="教师" onclick="check_power(this)"/>教师&nbsp;&nbsp;&nbsp;
				
				<input type="radio" name="che" value="on" onclick="remember()"/>记住密码
			</div>	
			
			<div class="input-row perspective">
				<button id="submit" class="button">
					<span class="default"><i class="ion-arrow-right-b"></i>登录</span>
					<div class="load-state">
						<div class="ball"></div>
						<div class="ball"></div>
						<div class="ball"></div>
					</div>
				</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src='js/jquery-1.8.3.min.js'></script>
<script type="text/javascript" src="js/haha.js"></script>
<script type="text/javascript">
function remember(){
	if(checkOn==0){
		checkOn=1;
	}else{
		checkOn=0;
	}
}


$(document).ready(function(){ 
	$.ajax({
		type: "POST",
		url: "${ctx}/teacher/showCookie",
		dataType: "json",
		success: function(data){
			var arr=eval(data);
			if(arr.length!=0){
				$("#username").val(arr[0].username);
				$("#password").val(arr[1].password);
			}
		}
	});
});

function checkCode(){
	var code=$('#checkcode').val();
    $.ajax({
		type: "POST",
		url: "${ctx}/teacher/checkcode",
		data: "code="+code,
		dataType: "json",
		success: function(data){
			var arr=eval(data);
			if(arr[0].result=='nook'){
		   		alert("验证码错误");
		   	   window.location.href="${ctx }/teacher/toLogin";
			}
			
		}
	});
}
function usernameOut(){
	var username=$("#username").val();
	if(username==""){
		alert("用户名不能为空")
	}
}
function passwordOut(){
	var password=$("#password").val();
	if(password==""){
		alert("密码不能为空")
	}
}
function check_power(obj) {
	choice=obj.value;
}
$("#submit").live("click",function(){
	
	usernameOut();
	
	passwordOut();
	
	if(choice==0||choice==""){
		alert("请选择登陆权限");
		return ;
	}
	
	
	var username=$("#username").val();
	var password=$("#password").val();
	var code=$("#checkcode").val();
	$.ajax({
		type: "POST",
		url: "${ctx}/teacher/checkcode",
		data: "code="+code,
		dataType: "json",
		success: function(data){
			var arr=eval(data);
			if(arr[0].result=='nook'){
				alert("验证码错误");
			}
		   		
			else{
				$.ajax
			    ({
			        type: "POST",
			        data: {"username":username ,"password":password ,"choice": choice ,"code":code ,"checkOn":checkOn},
			        url: "${ctx}/teacher/login",
			        dataType: "json",
			        async: true,
			        success: function (messages)
			        {
			        	var arr=eval(messages); 
			            if(arr.length==0)
			            {
			            	window.location.href="${ctx}/teacher/toIndex";
			            }else{
			            	alert(arr[0].messages);
			            }
			        }
			    });
			}
		}
	});
	
})
</script>
<div style="text-align:center;">
</div>
</form>
</body>
</html>