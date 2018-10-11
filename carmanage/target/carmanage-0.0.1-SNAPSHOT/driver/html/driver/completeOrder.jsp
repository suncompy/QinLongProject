<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>已完成订单</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.min.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/public/css/sweetalert2.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/driver/css/driver/completeOrder.css" />
	<script src="${pageContext.request.contextPath}/public/js/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.min.js"></script>
	 <script src="${pageContext.request.contextPath}/public/js/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/driver/js/driver/sweetalert2.min.js"></script>
	<script src="${pageContext.request.contextPath}/driver/js/driver/base.js"></script>
	
</head>
<body>
	<div class="container-fluit">
		<div class="row">
		    <div class="col-md-12 col-sm-12" id="compleOrders">
		    </div>
		</div>
	</div>
	
	<script type="text/javascript">
	window.onload=function(){
		var tale = new $.tale();
		tale.get({
		         url: '${pageContext.request.contextPath}/order/listCompleteOrder.do',
		        /*  data: $("#saveBranchGroupFormId").serialize(), */
		         success: function (result) {
		             if (result.status == 200) {
		            	 $("#compleOrders").html("");
		            	 $.each(result.data,function(index,list){
		            	 var projectType = "";
		            	 if(list.projectType == 0){
		            		 projectType="集装箱";
		            	 }else if(list.projectType == 1){
		            		 projectType="散堆装";
		            	 }
		            	 $("#compleOrders").append('<div class="completeOrder"> <div class="clearfix">'+
		            	 '<h4 class="pull-left">运单编号：<span id="">'+list.orderCode+'</span></h4>'+
						' <a class="read-more pull-right" href="${pageContext.request.contextPath}/order/completeOrderDetail.do?id='+list.orderCode+'">查看详情&gt;&gt;</a></div><hr><ul class="clearfix"><li><label>项目类型：</label>'+
						'<span id="">'+projectType+'</span></li><li><label>货物品名：</label>'+
						'<span id="">'+list.cargoName+'</span></li><li><label>创建时间：</label>'+	
						'<span id="">'+list.createDate+'</span></li><li><label>分支机构：</label>	'+
						'<span id="">'+list.branchGroupName+'</span></li><li><label>联系方式：</label>	'+		
						'<span id="">'+list.driverPhone+'</span></li><li><label>完成时间：</label>	'+	
						'<span id="">'+list.receipterDate+'</span></li><li><label>发货单位：</label>'+
						'<span id="">'+list.sendCompany+'</span></li><li><label>收货单位：</label>	'+
						'<span id="">'+list.receiptCompany+'</span></li></ul></div>	');
		           	 		})
		            	 } else {
		                 tale.alertWarn({
		                	title:"提示信息",
		                	text:result.msg
		                 });
		             }
		        	 
	            	
		         }
		     });
		}
	</script>
</body>
</html>