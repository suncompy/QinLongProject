<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
	<title>欢迎页</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.min.css">
	<style type="text/css">
   .page{
   	text-align: center;
   	background-color: #EEF5FD; 
   }
   .page-wrap{
   	margin: 0 auto;
   	padding-top: 15%;
   }
	</style>
</head>
<body class="page">
<!-- 欢迎页 -->
 <div class="page-wrap"><img src="${pageContext.request.contextPath}/img/map.png"></div>
</body>
<script language="JavaScript"> 
function myrefresh() 
{ 
	window.location.href='${pageContext.request.contextPath}/html/index.do';
} 
setTimeout('myrefresh()',3000); //指定1秒刷新一次 
</script> 
</html>