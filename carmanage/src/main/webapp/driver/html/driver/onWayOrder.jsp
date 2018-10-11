<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>在途订单</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.min.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/public/css/sweetalert2.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/driver/css/driver/onWayOrder.css" />
	<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
	<script src="${pageContext.request.contextPath}/public/js/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/driver/js/driver/sweetalert2.min.js"></script>
	<script src="${pageContext.request.contextPath}/driver/js/driver/base.js"></script>
	<script src="${pageContext.request.contextPath}/driver/js/driver/onWayOrder.js"></script>
	<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.3&key=sfci50a7s86fiLfgSvijWYxv76a"></script>
	<script src="${pageContext.request.contextPath}/driver/js/driver/mapPoint.js"></script>
</head>
<body>
	<div class="container-fluit">
		<div class="row">
		<c:if test="${orderDetail == null}">
			<script type="text/javascript">
			var tale = new $.tale();
			tale.alertWarn({
            	title:"提示信息",
            	text:"暂时没有运输中的运单!"
             });
			</script>
		</c:if>
		<!--在途订单详情-->
		    <div class="col-md-6 col-sm-6"  >
			    <div class="detail" style="padding:20px;">
			        <div>
			            <h4>项目信息</h4>
			            <input id="hiddenOrderId" type="hidden" value="${orderDetail.id}"/>
			            <ul class="clearfix">
			                <li><label >运单编号：</label><span id="">${orderDetail.orderCode}</span></li>
			                <li><label >项目类型：</label><span id=""><c:if test="${orderDetail.projectType eq 0}">集装箱</c:if>
								<c:if test="${orderDetail.projectType eq 1}">散堆装</c:if></span></li>
			                <li>
				                <div class="col-sm-4"><label >发货单位：</label><span id="">${orderDetail.sendCompany}</span></div>
					           <%--  <div class="col-sm-5"><label >发货地址：</label><span id="">${orderDetail.pickupPlace}${orderDetail.pickupPlaceAddress}</span></div> --%>
					            <div class="col-sm-3"><label >联系方式：</label><span id="">${orderDetail.sendCompanyPhone}</span></div></li>
					                  <li><div class="col-sm-4"><label >收货单位：</label><span id="">${orderDetail.receiptCompany}</span></div>
					            <%-- <div class="col-sm-5"><label >收货地址：</label><span id="">${orderDetail.arriveAddress}</span></div> --%>
					            <div class="col-sm-3"><label >联系方式：</label><span id="">${orderDetail.receiveCompanyPhone}</span></div>
				            </li>
			                <li><label >分支机构：</label><span id="">${orderDetail.branchGroupName}</span></li>
			                <li><label >调度员：</label><span id="">${orderDetail.userDispatchName}</span></li>
			                <li><label >创建时间：</label><span id=""><fmt:formatDate
													value="${orderDetail.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></span></li>
			                <li><label >运单状态：</label><span id="" class="state"><c:if test="${orderDetail.status eq 1}">等待调度</c:if><c:if test="${orderDetail.status eq 2}">等待发运</c:if>
			                <c:if test="${orderDetail.status eq 3}">在途运载</c:if><c:if test="${orderDetail.status eq 4}">货位引导</c:if><c:if test="${orderDetail.status eq 5}">等待回单</c:if>
			                <c:if test="${orderDetail.status eq 6}">等待确认</c:if></span></li>
			                <li><label >更新时间：</label><span id=""><fmt:formatDate
													value="${orderDetail.updateDate}" pattern="yyyy-MM-dd HH:mm:ss" /></span></li>
			            </ul>
		            </div>
		            <hr>
		            <div>
			            <h4>货物信息</h4>
			            <ul  class="clearfix">
				            <li><label >货物品名：</label><span id="">${orderDetail.cargoName}</span></li>
				            <li><label >货物规格：</label><span id="">${orderDetail.specifications}</span></li>
				            <li><label >化验指标：</label><span id="">${orderDetail.testIndicators}</span></li>
			            </ul>
		            </div>
		            <hr>
		            <c:if test="${orderDetail.projectType eq 0}"><!-- 集装箱显示 -->
		            <div>
			            <h4>集装箱信息</h4>
			            <ul  class="clearfix">
				            <li>
					            <div class="col-sm-4"><label >集装箱号：</label><span id="">${orderDetail.containerNumber1}</span></div>
					            <div class="col-sm-4"><label >发货净重：</label><span id="">${orderDetail.containerOneSendNet}吨</span></div>
				            </li>
				            <li>
					            <div class="col-sm-4"><label >集装箱号：</label><span id="">${orderDetail.containerNumber2}</span></div>
					            <div class="col-sm-4"><label >发货净重：</label><span id="">${orderDetail.containerTwoSendNet}吨</span></div>

				            </li>
			            </ul>
		            </div>
		            </c:if>
		            <hr>
		            <div>
			            <h4>收发货信息</h4>
			            <ul class="clearfix">
							<li>
								<div class="col-sm-4"><label >发货皮重：</label><span id="">${orderDetail.sendTare}吨</span></div>
								<div class="col-sm-4"><label >发货毛重：</label><span id="">${orderDetail.sendGross}吨</span></div>
								<div class="col-sm-4"><label >发货净重：</label><span id="">${orderDetail.containerOneSendNet+orderDetail.containerTwoSendNet}吨</span></div>
							</li>
			            </ul>
		            </div>
		            <hr>
		            <div>
			            <h4>运费信息</h4>
			            <ul class="clearfix">
				            <li><label >运费单价：</label><span id="">${orderDetail.shortBargeCost}元</span></li>
				            <li><label >计费重量：</label><span id="">${orderDetail.containerOneSendNet+orderDetail.containerTwoSendNet}吨</span></li>
				            <c:if test="${orderDetail.valuationUnitType eq 2}">
				            <li><label >运费件数：</label><span id="">${orderDetail.pieceNumber}件</span></li>
				            </c:if>
			            </ul>
		            </div>
	            </div>
		    </div>
		    <style>
		    	.c-col-md{
			    	display:flex;
			    	flex-direction:column;
			    }
			    .localUl{
			    	display:flex;
			    	flex-direction:row;
			    	
			    }
		    </style>
		    
		    
		     <div class="col-md-6 col-sm-6 c-col-md" style='position:relative;margin-top:20px;height:600px;'>
			    <div class="map" id="container"></div>
			    <div style=''>
			    	<div  id="positionDetail" style='height:350px;overflow:auto;position:absolute;top:650px;width:100%;'></div>
			    </div>
		    </div> 
		</div>
	</div>
	
	
</body>
</html>