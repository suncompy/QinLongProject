<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>登录</title>
<link href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/public/css/login.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/public/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
//在top页面中打开
if(window.top != window.self) {
	window.top.location.href = window.self.location.href;
}
</script>
<style type="text/css">
</style>
</head>
<script>
	
	$(function(){
		$("#loginSubmitBtn").click(function(){
			//校验用户名和密码是否为空	
				
	   		 var formParam = $("#loginForm").serialize();//序列化表格内容为字符串  
	   		 $.ajax({  
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/user/login',  
		        data:formParam,  
		        cache:false,
		        dataType:'json',  
	        	success:function(data){
	        		if(data.rset.bool == false){
	        			alert(data.rset.message);
	        		}else{
	        			alert(data.rset.message);
	        			window.location.href="${pageContext.request.contextPath}/user/driverindex"; 
	        		}
	        	}  
	    	});  
		})
	})
	$(document).keyup(function (e) {
		if (e.keyCode == 13){
			$("#loginSubmitBtn").click();
		}
	})
			
	
</script>
<body>
		<div >
			<div class="top" >
				<div >
					<div class="img"><img src="${pageContext.request.contextPath}/public/img/login-logo.png" alt="" /></div>
					<div class="title">车辆管理平台</div>
					<div class="small-title">The vehicle management platform</div>
				</div>
			</div>
			<div class="bottom">
				<div class="prompt" id="msg"></div>
				<div  class="clearfix">
					<form id="loginForm"
					action="#" method="post">
						<div class="form-group">
    						<label >用户名：</label>
    						<input type="text" class="form-control" name="username" >
 						</div>
 						<div class="form-group">
    						<label >密码</label>
    						<input  type="password" class="form-control" name="password" >
 						</div>
 						<button type="button" id="loginSubmitBtn" class=" btn btn-block">登录</button>
					</form>
					<div class="pull-left"><a href="${pageContext.request.contextPath}/forgetPassword.do">忘记密码</a></div>
					<div class="pull-right">没有账号？立即<a href="${pageContext.request.contextPath}/user/toregister">注册</a></div>
			</div>
			</div>
		</div>

	</body>
	<script>
		
	</script>