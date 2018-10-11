<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>取消的订单详情</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/driver/css/driver/detailCancelOrder.css" />
	<script src="${pageContext.request.contextPath}/public/js/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.min.js"></script>
	 <!-- <script src="../../js/fleet/fleet.js"></script> -->
	
</head>
<body>
	<div class="container-fluit">
	 
	    <a href="${pageContext.request.contextPath}/order/cancelOrder.do" type="button" class="btn btn-link" aria-label="Left Align">
		    <span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>
        </a>
		<div class="row">
		<!--取消的订单详情-->
		    <div class=" col-md-12 col-sm-12" >
			    <div class="detail"  >
		            <div>
			            <h4>项目信息</h4>
			            <ul class="clearfix">
			                <li><label >运单编号：</label><span id="">${orderDetail.orderCode}</span></li>
			                <li><label >项目类型：</label><span id=""><c:if test="${orderDetail.projectType eq 0}">集装箱</c:if>
								<c:if test="${orderDetail.projectType eq 1}">散堆装</c:if></span></li>
			                <li>
				                <div class="col-sm-4"><label >发货单位：</label><span id="">${orderDetail.sendCompany}</span></div>
					           <%--  <div class="col-sm-5"><label >发货地址：</label><span id="">${orderDetail.pickupPlace}${orderDetail.pickupPlaceAddress}</span></div> --%>
					            <div class="col-sm-3"><label >联系方式：</label><span id="">${orderDetail.sendCompanyPhone}</span></div>
					        </li>
					        <li>
					            <div class="col-sm-4"><label >收货单位：</label><span id="">${orderDetail.receiptCompany}</span></div>
					            <%-- <div class="col-sm-5"><label >收货地址：</label><span id="">${orderDetail.arriveAddress}</span></div> --%>
					            <div class="col-sm-3"><label >联系方式：</label><span id="">${orderDetail.receiveCompanyPhone}</span></div>
				            </li>
			                <li><label >分支机构：</label><span id="">${orderDetail.branchGroupName}</span></li>
			                <li><label >调度员：</label><span id="">${orderDetail.userDispatchName}</span></li>
			                <li><label >运单状态：</label><span id="" class="state" style="color: #4C88EA">已取消</span></li>
			                <li><label >创建时间：</label><span id=""><fmt:formatDate
													value="${orderDetail.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></span></li>
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
		              <c:if test="${orderDetail.projectType eq 0}">
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
		            <hr>
		            <div>
			            <h4>车辆信息</h4>
			            <ul class="clearfix">
			            	<li><label >驾驶员：</label><span id="">${orderDetail.driverName}</span></li>
			            	<li><label >车牌号：</label><span id="">${orderDetail.carPlateNumber}</span></li>
			            	<li><label >联系方式：</label><span id="">${orderDetail.driverPhone}</span></li>
			            </ul>
		            </div>
		            <hr />
		            <div>
			            <h4>取消原因</h4>
			            <ul class="clearfix">
			            	<li><label >取消原因：</label><span id="">${orderDetail.cancelReason}</span></li>
			            	<li><label >取消阶段：</label><span id=""><c:if test="${orderDetail.status eq 1}">等待调度</c:if><c:if test="${orderDetail.status eq 2}">等待发运</c:if>
			                <c:if test="${orderDetail.status eq 3}">在途运载</c:if><c:if test="${orderDetail.status eq 4}">货位引导</c:if><c:if test="${orderDetail.status eq 5}">等待回单</c:if>
			                <c:if test="${orderDetail.status eq 6}">等待确认</c:if></span></li>
			            	<li><label >取消时间：</label><span id=""><fmt:formatDate
													value="${orderDetail.cancelDate}" pattern="yyyy-MM-dd HH:mm:ss" /></span></li>
			            </ul>
		            </div>
				</div>
		    </div>
		   
		</div>
	</div>
	
</body>
</html>