<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<title>取消的订单</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/public/css/sweetalert2.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/driver/css/driver/cancelOrder.css" />
<script
	src="${pageContext.request.contextPath}/public/js/jquery-3.2.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/driver/js/driver/sweetalert2.min.js"></script>
<script
	src="${pageContext.request.contextPath}/driver/js/driver/base.js"></script>
<script
	src="${pageContext.request.contextPath}/driver/js/driver/onWayOrder.js"></script>
<script
	src="${pageContext.request.contextPath}/driver/js/driver/angular.js"></script>
<%-- <script src="${pageContext.request.contextPath}/public/angular.js"></script> --%>
</head>

<body ng-controller="MyController">
	<div>
		<form>
			<input type="text" ng-model="username" /> 
			<input type="text" ng-model="email" />
		</form>
		<table>
			<tr ng-repeat="user in users">
				<td>{{user.username}}</td>
				<td>{{user.email}}</td>
			</tr>
		</table>
		<button ng-click="get_more();">get more</button>
		<button ng-click="getUser()">提交</button>
		 <ul>
	        <li ng-repeat="x in infos">
	            {{ x.username + x.phone + x.anchoredTime }}
	   		 </li>
	    </ul>
	</div>

	<script>
		function MyController($scope,$http){
			$scope.getAccount=function(){
				$http({
					method:"post",
					url:"http://localhost:8088/logistics-carmanage-web/driver/account.do",
				}).success(function(data){
					$scope.infos = data;
					alert(data);
				})
			}
		}
	</script>

</body>

</html>