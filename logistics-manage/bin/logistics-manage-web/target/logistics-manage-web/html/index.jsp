<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<c:set var="ctxPath1" value='${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/' />
<c:set var="ctxPath" value='//${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/' />
<base href="${ctxPath1 }" />
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="shortcut icon" href="${ctxPath }favicon.ico" type="image/x-icon" />
		<title>首页</title>

		<!-- Bootstrap core CSS -->
		<link href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.css" rel="stylesheet">

		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<link href="${pageContext.request.contextPath}/public/bootstrap/css/ie10-viewport-bug-workaround.css" rel="stylesheet ">
		<link href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet " />
		<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
		<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js "></script><![endif]-->
		<script src="${pageContext.request.contextPath}/public/bootstrap/js/ie10-viewport-bug-workaround.js"></script>

		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js "></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js "></script>
    <![endif]-->
    <script type="text/javascript">
		//定义一级菜单数组
		var arrFirstMenu=new Array();
		//定义一级菜单样式
		var arrFirstMenuClass = new Array();
		<c:forEach items="${firstMenuDetailList}" var="tbMenu" varStatus="st">
			arrFirstMenu[${st.index}] = '${tbMenu.code }';
			arrFirstMenuClass[${st.index}] = '${tbMenu.iconClass }';
		</c:forEach>
    </script>
	</head>
	<body>
		<nav class="navbar navbar-inverse navbar-fixed-top nav_top">
			<div class="container-fluid" style="height: 70px;">
				<div class="navbar-header">
					<!--<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                   </button>-->
					<a class="navbar-brand" href="javascript:void(0);">
						<div id="logo" class="clearfix">
							<div id="logo_img"><img src="${pageContext.request.contextPath}/img/logo.png " /></div>
							<div id="logo_name">第一物流中心</div>
						</div>
					</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-left top_nav_ul" id="firstMenuUlId">
						<c:forEach items="${firstMenuDetailList}" var="tbMenu" varStatus="st">
							<li class="nav_left" id="${tbMenu.code }">
								<a  <c:if test="${st.index == 0 }">class="actives" href="${tbMenu.url }" target="contentIframe"</c:if>><em class="${tbMenu.iconClass }s"  ></em>${tbMenu.name }</a>
							</li>
						</c:forEach>
						<!-- <li class="nav_left" id="business">
							<a href="javascript:void(0);" class="actives"><em class="nav_img1"></em>业务</a>
						</li>
						<li class="nav_left" id="finance">
							<a href="javascript:void(0);"><em class="nav_img2"></em>财务</a>
						</li>
						<li class="nav_left" id="manage">
							<a href="javascript:void(0);">
								<em class="nav_img3"></em>设置
							</a>
						</li> -->
					</ul>
					<ul class="nav navbar-nav navbar-right top_left_ul  clearfix">
						<li>
							<a href="javascript:void(0);">
								<img src="${pageContext.request.contextPath}/img/user.png" />
								<span class="nav_right">您好！<em id="userName">${userName }</em></span>
							</a>
						</li>

						<li class="tree_line">
							<a href="javascript:void(0);">|</a>
						</li>
						<li>
							<a href="javascript:void(0);">
								<img src="${pageContext.request.contextPath}/img/messg.png" />
								<span class="nav_right">未读<em id="unreadMsg">0</em>封</span>
							</a>
						</li>
						<li class="tree_line">
							<a href="javascript:void(0);">|</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/logout.do">
								<img src="${pageContext.request.contextPath}/img/quit.png" />
								<span id="quit" class="nav_right">注销</span>
							</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		</nav>
		<div class="sidebar">
			<c:forEach items="${firstMenuDetailList}" var="menuDetail" varStatus="st">
				<ul class="nav nav-sidebar" id="ul_${menuDetail.code }" >
				<c:forEach items="${menuDetail.menus}" var="secondMenuDetail" varStatus="st2">
					<li class="card">
						<a class="card-link" data-toggle="collapse" data-parent="#accordion" href="#${secondMenuDetail.id}">
							<i class="${secondMenuDetail.iconClass}"></i>
							<span class="mar">${secondMenuDetail.name}</span>
							<em class="arrow iconClass"></em>
						</a>
						<ul id="${secondMenuDetail.id}" class="collapse left_nav">
							<c:forEach items="${secondMenuDetail.menus}" var="thirdMenuDetail" varStatus="st3">
								<li>
									<c:choose>
									   <c:when test="${fn:contains(thirdMenuDetail.url,'.html')}"> 
									   	<a target="contentIframe" href="${ctxPath}${thirdMenuDetail.url}">${thirdMenuDetail.name}</a>
									   </c:when>
									   <c:otherwise>
									       <a target="contentIframe" href="${pageContext.request.contextPath}${thirdMenuDetail.url}">${thirdMenuDetail.name}</a>
									   </c:otherwise>
									</c:choose>
								</li>
							</c:forEach>
						</ul>
					</li>
				</c:forEach>
			</ul>
			</c:forEach>
			
			
			
			<!--业务-->
		 	<%--  <ul class="nav nav-sidebar" id="ul_business">
				<li class="card">
					<a class="card-link" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
						<i class="leftIcon projectIcon"></i>
						<span class="mar">项目管理</span>
						<em class="arrow iconClass"></em>
					</a>

					<ul id="collapseOne" class="collapse left_nav">
						<li>
							<a target="contentIframe" href="html/business/project/projectManagment.html">项目管理</a>
						</li>
						<li>
							<a href="html/business/project/projectOperation.html" target="contentIframe">项目运营管理</a>
						</li>
						<li>
							<a href="html/business/project/projectCheck.html" target="contentIframe">项目核查</a>
						</li>
					</ul>
				</li>
				<li class="card">
					<a class="card-link" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
						<i class="leftIcon shortImg"></i>
						<span class="mar">短驳管理</span>
						<em class="arrow iconClass"></em>
					</a>

					<ul id="collapseTwo" class="collapse left_nav">

						<li>
							<a class="leftBg" href="html/business/shortBarge/distribution.html" target="contentIframe">发布任务</a>
						</li>

						<li>
							<a class="leftBg" target="contentIframe" href="${pageContext.request.contextPath}/business/short/boxManager/list.do" target="contentIframe">集装箱管理</a>
						</li>
						<li>
							<a class="leftBg" href="html/business/shortBarge/truckCbulkLoading.html" target="contentIframe">散装箱管理</a>
						</li>
					</ul>
				</li>
				<li class="card">
					<a data-toggle="collapse" class="card-link" data-parent="#accordion" href="#collapseThree">
						<i class="leftIcon train"></i>
						<span class="mar">火运管理</span>
						<em class="arrow iconClass"></em>
					</a>
					<ul id="collapseThree" class="collapse left_nav">
						<li>
							<a class="leftBg" href="html/business/trianTransport/trainContainer.html" target="contentIframe">集装箱管理</a>
						</li>
						<li>
							<a class="leftBg" href="html/business/trianTransport/fireTrainCbulkLoading.html" target="contentIframe">散装箱管理</a>
						</li>
					</ul>
				</li>
				<li class="card">
					<a data-toggle="collapse" class="card-link" data-parent="#accordion" href="#collapseFour">
						<i class="leftIcon stockImg"></i>
						<span class="mar">库存管理</span>
						<em class="arrow iconClass"></em>
					</a>
					<ul id="collapseFour" class="collapse left_nav">
						<li>
							<a class="leftBg" href="html/business/inventory/shortAccessStorage.html" target="contentIframe">短驳出入库查询</a>
						</li>
						<li>
							<a class="leftBg" href="html/business/inventory/trainAccessStorage.html" target="contentIframe">火运出入库查询</a>
						</li>
						<li>
							<a class="leftBg" href="html/business/inventory/inventoryCheck.html" target="contentIframe">库存盘查</a>
						</li>
						<li>
							<a class="leftBg" href="html/business/inventory/inventoryBilling.html" target="contentIframe">库存计费</a>
						</li>
					</ul>
				</li>
				<li class="card">
					<a class="card-link" data-toggle="collapse" data-parent="#accordion" href="#collapseFive">
						<i class="leftIcon businessImg"></i>
						<span class="mar">业务查询</span>
						<em class="arrow iconClass"></em>
					</a>
					<ul id="collapseFive" class="collapse left_nav">
						<li>
							<a class="leftBg" href="" target="contentIframe">项目查询</a>
						</li>
						<li>
							<a class="leftBg" href="" target="contentIframe">运单查询</a>
						</li>
						<li>
							<a class="leftBg" href="" target="contentIframe">车辆查询</a>
						</li>
						<li>
							<a class="leftBg" href="" target="contentIframe">异常查询</a>
						</li>
					</ul>
				</li>
				<li class="card">
					<a class="card-link" data-toggle="collapse" data-parent="#accordion">
						<i class="leftIcon logImg"></i>
						<span class="mar">日志系统</span>
					</a>
				</li>
				<li class="card">
					<a class="card-link" data-toggle="collapse" data-parent="#accordion">
						<i class="leftIcon toolImg"></i>
						<span class="mar">工具</span>
					</a>
				</li>
			</ul> 
			<!--  财务	-->

			 <ul class="nav nav-sidebar" id="ul_finance">
				<li class="card">
					<a class="card-link" data-toggle="collapse" data-parent="#accordionMoney" href="#financeOne" status="1">
						<i class="financeIcon charging"></i>
						<span class="mar">计费与对账</span>
						<em class="arrow iconClass"></em>
					</a>
					<ul id="financeOne" class="collapse left_nav">
						<li>
							<a target="contentIframe" href="html/finance/billReconciliation/freightPayout.html">运费支出</a>
						</li>
						<li>
							<a href="html/finance/billReconciliation/driverReconciliation.html" target="contentIframe">司机对账</a>
						</li>
						<li>
							<a href="html/finance/billReconciliation/customerReconciliation.html" target="contentIframe">客户对账</a>
						</li>
						<li>
							<a href="html/finance/billReconciliation/costReconciliation.html" target="contentIframe">费用对账</a>
						</li>
						<li>
							<a href="html/finance/billReconciliation/dotReconciliation.html" target="contentIframe">网点对账</a>
						</li>
					</ul>
				</li>
				<li class="card">
					<a class="card-link" data-toggle="collapse" data-parent="#accordion" href="#financeTwo" status="1">
						<i class="financeIcon Settlement"></i>
						<span class="mar">结算与交账</span>
						<em class="arrow iconClass"></em>
					</a>

					<ul id="financeTwo" class="collapse left_nav">
						<li>
							<a class="leftBg" href="html/finance/settlementAccountability/driverAccounts.html" target="contentIframe">司机结算</a>
						</li>
						<li>
							<a class="leftBg" href="html/finance/settlementAccountability/customerAccounts.html" target="contentIframe">客户结算</a>
						</li>
						<li>
							<a class="leftBg" href="html/finance/settlementAccountability/dotAccountability.html" target="contentIframe">网点交账</a>
						</li>

					</ul>
				</li>
				<li class="card">
					<a class="card-link" data-toggle="collapse" data-parent="#accordionMoney" href="#financeThree">
						<i class="financeIcon advance"></i>
						<span class="mar">预付款结算</span>
						<em class="arrow iconClass"></em>
					</a>
					<ul id="financeThree" class="collapse left_nav">
						<li>
							<a class="leftBg" href="html/finance/prepaymentSettlement/imprestPayment.html" target="contentIframe">预付款存入</a>
						</li>
						<li>
							<a class="leftBg" href="html/finance/prepaymentSettlement/imprestOffset.html" target="contentIframe">预付款抵用</a>
						</li>
						<li>
							<a class="leftBg" href="html/finance/prepaymentSettlement/imprestDetail.html" target="contentIframe">预付款支抵明细</a>
						</li>
					</ul>
				</li>
				<li class="card">
					<a class="card-link" data-toggle="collapse" data-parent="#accordionMoney" href="#financeFour">
						<i class="financeIcon cost"></i>
						<span class="mar">费用账目</span>
						<em class="arrow iconClass"></em>
					</a>
					<ul id="financeFour" class="collapse left_nav">
						<li>
							<a class="leftBg" href="html/finance/costAccount/invoiceManagement.html" target="contentIframe">发票管理</a>
						</li>
						<li>
							<a class="leftBg" href="html/finance/costAccount/balanceJournal.html" target="contentIframe">收支序时帐</a>
						</li>
					</ul>
				</li>
				<li class="card">
					<a class="card-link" data-toggle="collapse" data-parent="#accordionMoney" href="#financeFours">
						<i class="financeIcon payable"></i>
						<span class="mar">应收款结算</span>
						<em class="arrow iconClass"></em>
					</a>
					<ul id="financeFours" class="collapse left_nav">
						<li>
							<a class="leftBg" href="html/finance/receivablesSettlement/threePartiesReceivables.html" target="contentIframe">三方应收款管理</a>
						</li>
						<li>
							<a class="leftBg" href="html/finance/receivablesSettlement/enterpriseReceivables.html" target="contentIframe">企业应收款</a>
						</li>
					</ul>
				</li>
				<li class="card">
					<a class="card-link" data-toggle="collapse" data-parent="#accordionMoney" href="#financeFive">
						<i class="financeIcon materiel"></i>
						<span class="mar">物料管理</span>
						<em class="arrow iconClass"></em>
					</a>
					<ul id="financeFive" class="collapse left_nav">
						<li>
							<a class="leftBg" href="html/finance/materialManagement/oilgasManagement.html" target="contentIframe">油气卡管理</a>
						</li>
						<li>
							<a class="leftBg" href="html/finance/materialManagement/materialManagement.html" target="contentIframe">物料管理</a>
						</li>
					</ul>
				</li>
				<li class="card">
					<a class="card-link" data-toggle="collapse" data-parent="#accordionMoney" href="#financeSix">
						<i class="financeIcon adjustment"></i>
						<span class="mar">财务调整</span>
						<em class="arrow iconClass"></em>
					</a>
					<ul id="financeSix" class="collapse left_nav">
						<li>
							<a class="leftBg" href="" target="contentIframe">运单调整</a>
						</li>
						<li>
							<a class="leftBg" href="" target="contentIframe">结算调整</a>
						</li>
						<li>
							<a class="leftBg" href="" target="contentIframe">费用调整</a>
						</li>
						<li>
							<a class="leftBg" href="" target="contentIframe">预付款调整</a>
						</li>
					</ul>
				</li>
				<li class="card">
					<a class="card-link" data-toggle="collapse" data-parent="#accordionMoney" href="#financeFour">
						<i class="financeIcon financeQuery"></i>
						<span class="mar">财务查询</span>
					</a>
				</li>
				<li class="card">
					<a class="card-link" data-toggle="collapse" data-parent="#accordionMoney" href="#financeFour">
						<i class="financeIcon statistics"></i>
						<span class="mar">统计报表</span>
					</a>
				</li>
			</ul> -->

			<!-- 设置	-->
			<ul class="nav nav-sidebar" id="ul_manage">
				<li class="card">
					<a class="card-link" data-toggle="collapse" data-parent="#accordionManage" href="#manageOne" status="1">
						<i class="financeIcon personnel"></i>
						<span class="mar">人与组织</span>
						<em class="arrow iconClass"></em>
					</a>

					<ul id="manageOne" class="collapse left_nav">
						<li>
							<a target="contentIframe" href="${pageContext.request.contextPath}/humanOrganization/dotBranch.do">网点分支</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/humanOrganization/employeeInformation.do" target="contentIframe">员工信息</a>
						</li>
					</ul>
				</li>
				<li class="card">
					<a class="card-link" data-toggle="collapse" data-parent="#accordionManage" href="#manageTwo" status="1">
						<i class="financeIcon customer"></i>
						<span class="mar">客户管理</span>
						<em class="arrow iconClass"></em>
					</a>

					<ul id="manageTwo" class="collapse left_nav">
						<li>
							<a class="leftBg" href="${pageContext.request.contextPath}/customerManagement/customerInformation.do" target="contentIframe">客户信息</a>
						</li>
					</ul>
				</li>
				<li class="card">
					<a class="card-link" data-toggle="collapse" data-parent="#accordionManage" href="#manageThree">
						<i class="financeIcon sites"></i>
						<span class="mar">站点管理</span>
						<em class="arrow iconClass"></em>
					</a>
					<ul id="manageThree" class="collapse left_nav">
						<li>
							<a class="leftBg" href="${pageContext.request.contextPath}/siteManager/freight/yard/list.do" target="contentIframe">货场货位</a>
						</li>
						<li>
							<a class="leftBg" href="${pageContext.request.contextPath}/trainStation/list.do" target="contentIframe">站点信息</a>
						</li>
					</ul>
				</li>
				<li class="card">
					<a class="card-link" data-toggle="collapse" data-parent="#accordionManage" href="#manageFour">
						<i class="financeIcon transport"></i>
						<span class="mar">运输管理</span>
						<em class="arrow iconClass"></em>
					</a>
					<ul id="manageFour" class="collapse left_nav">
						<li>
							<a class="leftBg" href="${pageContext.request.contextPath}/transport/anchord/an_list.do" target="contentIframe">车辆授权</a>
						</li>
						<li>
							<a class="leftBg" href="${pageContext.request.contextPath}/trainType/list.do" target="contentIframe">火车车型</a>
						</li>
						<li>
							<a class="leftBg" href="${pageContext.request.contextPath}/container/list.do" target="contentIframe">集装箱管理</a>
						</li>
					</ul>
				</li>
				<li class="card">
					<a class="card-link" data-toggle="collapse" data-parent="#accordionMoney" href="#manageFive">
						<i class="financeIcon locations"></i>
						<span class="mar">货位管理</span>
						<em class="arrow iconClass"></em>
					</a>
					<ul id="manageFive" class="collapse left_nav">
						<li>
							<a class="leftBg" href="${pageContext.request.contextPath}/cargo/cargoList.do" target="contentIframe">货物信息</a>
						</li>
					</ul>
				</li>
				<li class="card">
					<a class="card-link" data-toggle="collapse" data-parent="#accordionMoney" href="#manageSix">
						<i class="financeIcon systems"></i>
						<span class="mar">系统管理</span>
						<em class="arrow iconClass"></em>
					</a>
					<ul id="manageSix" class="collapse left_nav">
						<li>
							<a class="leftBg" href="${pageContext.request.contextPath}/systemManagement/authorityManagemen.do" target="contentIframe">权限管理</a>
						</li>
						<li>
							<a class="leftBg" href="${pageContext.request.contextPath}/system/sms/list.do" target="contentIframe">短信管理</a>
						</li>
					</ul>
				</li>
				<li class="card">
					<a class="card-link" data-toggle="collapse" data-parent="#accordionMoney" href="#manageSeven">
						<i class="financeIcon financialManagement"></i>
						<span class="mar">财务管理</span>
						<em class="arrow iconClass"></em>
					</a>
					<ul id="manageSeven" class="collapse left_nav">
						<li>
							<a class="leftBg" href="${pageContext.request.contextPath}/account/accountList.do" target="contentIframe">账户管理</a>
						</li>
						<li>
							<a class="leftBg" href="${pageContext.request.contextPath}/payment/paymentList.do" target="contentIframe">支付模式</a>
						</li>
					</ul>
				</li>
				<li class="card">
					<a class="card-link" data-toggle="collapse" data-parent="#accordionMoney" href="#manageEight">
						<i class="financeIcon abnormalIcon"></i>
						<span class="mar">异常管理</span>
						<em class="arrow iconClass"></em>
					</a>
					<ul id="manageEight" class="collapse left_nav">
						<li>
							<a class="leftBg" href="${pageContext.request.contextPath}/abnormal/abnormalList.do" target="contentIframe">系统情景设定</a>
						</li>
						<li>
							<a class="leftBg" href="" target="contentIframe">人工上报设定</a>
						</li>
					</ul>
				</li>
			</ul>  --%>

		</div>
		</div>
		<div class="main ">

			<div class="right_div" id="allRight">
				<iframe src="html/business/businessHome.html" id="contentIframe" name="contentIframe" frameborder="0" width="100%" height="100%">		
  	</iframe>

			</div>
		</div>
		</div>

		<!-- Bootstrap core JavaScript
    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script>
			window.jQuery || document.write('<script src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"><\/script>')
		</script>
		<script src="${pageContext.request.contextPath}/js/index.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
		<script src="${pageContext.request.contextPath}/public/bootstrap/js/holder.min.js"></script>
		<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
		<script src="${pageContext.request.contextPath}/public/bootstrap/js/holder.min.js"></script>
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<script src="${pageContext.request.contextPath}/public/bootstrap/js/ie10-viewport-bug-workaround.js"></script>

	</body>

</html>