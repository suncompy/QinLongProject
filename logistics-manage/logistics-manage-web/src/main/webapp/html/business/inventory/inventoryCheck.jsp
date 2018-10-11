<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
	<meta charset="UTF-8">
	<title>库存盘查</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/public/public.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/business/inventory/inventory.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/public/layer-v3.1.1/layer/mobile/need/layer.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/config/url.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/public/search/search.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.validate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/layer-v3.1.1/layer/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.serializejson.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js" ></script>
	<script src="${pageContext.request.contextPath}/static/js/common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/util.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/search/get-basic-data.js"></script>
	<link href="${pageContext.request.contextPath}/css/page/page.css" rel="stylesheet"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/business/inventory/inventoryCheck.js"></script>
</head>

	<body class="inventoryCheck">
		<!-- 库存盘查表单 -->
		<div class="form project_form container_top" id="container_top">
			<!--项目列表表单-->
			<form class="form-inline maxWidth list-form" id="searchForm">
				<div id="wrap">
					<div class="form-group">
						<label>项目编号：</label>
						<input name='projectCode' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>分支机构：</label>
						<select name="branchGroupId">
						</select>
					</div>
					<div class="form-group">
						<label>项目类型：</label>
						<select name="projectType">
							<option></option>
							<option value="0">集装箱</option>
							<option value="1">散装</option>
						</select>
					</div>
				<!--	<div class="form-group">
						<label>运单编号：</label>
						<select></select>
					</div>-->
					<div class="form-group">
						<label>货物品名：</label>
						<select name="cargoId">
						</select>
					</div>
					<div class="form-group">
						<label>发货企业：</label>
						<input name='sendCargoCompanyName' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>收货企业：</label>
						<input name='receiveCargoCompanyName' type="text" maxlength="30"/>
					</div>
				</div>
				<div class="foot">
					<a class="btn btn-success" href="javascript:;"><em class="search"></em>
						<span id="searchSpan" onclick="search('/inventory/listProjectInventoryByPage.do')">搜索</span>
					</a>
					<div id="lookMore"></div>
				</div>
			</form>
			<!--仓位平面图表单-->
			<form class="form-inline position-form maxWidth">
				<div id="wrap1">
					<div class="form-group">
						<label>项目编号：</label>
						<select name='freightSpace'  onchange="getCargoLocationImg(this)">
							<c:forEach items="${inventoryChecks.rows}" var="stock">
								<c:if  test="${stock.beginCurrentQty!=null || stock.endCurrentQty!=null }">
									<c:if  test="${stock.beginCurrentQty!=0 || stock.endCurrentQty!=0 }">
										<option value="${stock.projectId}">${stock.projectCode}</option>
									</c:if>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="foot">
					<a class="btn btn-success" href="javascript:;"><em class="search"></em>
						<span>搜索</span>
					</a>
				</div>
			</form>
		</div>
		<!-- 库存盘查表格 -->
		<div class="container_bottom tableDiv">
			<div class="tabbable" id="tabs-529262">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#panel1" data-toggle="tab" id="items" onclick="myrefresh()">项目列表</a>
					</li>
					<li>
						<a href="#panel2" data-toggle="tab" id="position-plane">仓位平面图</a>
					</li>
					<li>
						<!-- <a href="#panel3" data-toggle="tab" id="historyInventory">历史盘查</a> -->
					</li>
				</ul>
				<div class="tab-content">
					<!--项目列表表格开始-->
					<div class="tab-pane active" id="panel1">

						<div class="domain">
							<div class="tableBg">
							<c:forEach items='${permissions }' var='permission' >
								<c:if test="${permission.code=='storageEnter'}">
									<a href="javascript:;" class="exportBtn storage" id="storageEnter"><span>入库</span></a>
								</c:if>
								<c:if test="${permission.code=='stockCheck'}">
									<a href="javascript:;" class="exportBtn check"  id="stockCheck"><span>库存盘查</span></a>
								</c:if>
								<c:if test="${permission.code=='stockAdjust'}">
									<a href="javascript:;" class="exportBtn adjust" id=""><span>库存调整</span></a>
								</c:if>
								<c:if test="${permission.code=='exportStock'}">
									<a href="javascript:;" class="exportBtn export checks" id=""><span>导出</span></a>
								</c:if>
							</c:forEach>
							
							</div>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th> 
												<label class="demo--label">
								            <input class="demo--checkbox" type="checkbox"  >
							                  <span class="demo--checkboxInput"></span>
							                         	</label> </th>
											<th>项目编号</th>
											<th>项目类型</th>
											<th>联运模式</th>
											<th>分支机构</th>
											<th>货物品名</th>
											<th>发货企业</th>
											<th>始发站点</th>
											<th>货场</th>
											<th>货位</th>
											<th>入库吨位</th>
											<th>出库吨位</th>
											<th>库存吨位</th>
											<th>库存调整吨位</th>
											<th>收货企业</th>
											<th>到达站点</th>
											<th>货场</th>
											<th>货位</th>
											<th>入库吨位</th>
											<th>出库吨位</th>
											<th>库存吨位</th>
											<th>库存调整吨位</th>
											<th>盘查时间</th>
										</tr>
									</thead>
									<tbody id='projectTbody'>
									</tbody>
								</table>
							</div>
						</div>
						 <div class="row clearfix">
							<div class="col-md-12 column paging page_div" id="page">
							</div>
						</div>
					</div>
					<!--项目列表表格结束-->
					<!-- 仓位平面图开始 -->
					<div class="tab-pane position-plane" id="panel2">
						<div class="containers">
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<div class="original-site">
										<label>始发站点：</label><span id="">库里火车站</span>
										<label>所在货场：</label><span id="">库里火车站</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-2 col-md-2 col-lg-2 ">
									<div class="plane-pictrue">
										<img class="img-responsive"  id='bigImg' src="${pageContext.request.contextPath}/img/position-plane.png" alt="" />
										<div>
											<span>库里火车站货场</span>
											<br>
											<a href="javascript:;" class="lookBigImg">(点击查看大图)</a>
										</div>
									</div>
								</div>
								<div class="col-xs-10">
									<ul class="clearfix detail-plane">
										<li>
											<div>货位A-01</div>
											<ul class="clearfix">
												<li><label>项目编号：</label><span id="">J0001</span></li>
												<li><label>货物品名：</label><span id="">煤炭</span></li>
												<li><label>集装箱组：</label><span id="">20组</span></li>
												<li><label>集装箱库存：</label><span id="">1200吨</span></li>
												<li><label>散堆装库存：</label><span id="">200吨</span></li>
											</ul>
										</li>
									</ul>
								</div>
							</div>
						</div>
						<%-- <div class="containers">
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<div class="original-site">
										<label>始发站点：</label><span id="">库里火车站</span>
										<label>所在货场：</label><span id="">库里火车站</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-2 col-md-2 col-lg-2 ">
									<div class="plane-pictrue">
										<img class="img-responsive" src="${pageContext.request.contextPath}/img/position-plane.png" alt="" />
										<div>
											<span>库里火车站货场</span>
											<br>
											<a href="javascript:;">(点击查看大图)</a>
										</div>
									</div>
								</div>
								<div class="col-xs-10">
									<ul class="clearfix detail-plane">
										<li>
											<div>货位A-01</div>
											<!--<div>-->
											<ul class="clearfix">
												<li><label>项目编号：</label><span id="">J0001</span></li>
												<li><label>货物品名：</label><span id="">煤炭</span></li>
												<li><label>集装箱组：</label><span id="">20组</span></li>
												<li><label>集装箱库存：</label><span id="">1200吨</span></li>
												<li><label>散堆装库存：</label><span id="">200吨</span></li>
											</ul>
											<!--</div>-->
										</li>
									</ul>
								</div>
							</div>
						</div> --%>

					</div>
					<!-- 仓位平面图结束 -->
					<!-- 历史盘查开始 -->
					<div class="tab-pane" id="panel3">
						<div class="domain">
							<div class="tableBg">
								<!--<a href="javascript:;" class="exportBtn look"><span>查看</span></a>-->
								<a href="javascript:;" class="exportBtn export"><span>导出</span></a>
							</div>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th> <label class="demo--label">
								            <input class="demo--checkbox" type="checkbox"  >
							                  <span class="demo--checkboxInput"></span>
							                         	</label> </th>
											<th>项目编号</th>
											<th>项目类型</th>
											<th>联运模式</th>
											<th>分支机构</th>
											<th>货物品名</th>
											<th>发货企业</th>
											<th>始发站点</th>
											<th>货场</th>
											<th>货位</th>
											<th>入库吨位</th>
											<th>出库吨位</th>
											<th>库存吨位</th>
											<th>库存调整吨位</th>
											<th>收货企业</th>
											<th>到达站点</th>
											<th>货场</th>
											<th>货位</th>
											<th>入库吨位</th>
											<th>出库吨位</th>
											<th>库存吨位</th>
											<th>库存调整吨位</th>
											<th>损耗调整</th>
											<th>增益调整</th>
											<th>盘查时间</th>
											<th>盘查人</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><label class="demo--label">
								            <input class="demo--checkbox" type="checkbox"  >
							                  <span class="demo--checkboxInput"></span>
							                         	</label></td>
											<td id="">
												<a href="javascript:;">J0001</a>
											</td>
											<td id="">集装箱</td>
											<td id="">联运</td>
											<td id="">合肥物流分支</td>
											<td id="">焦炭</td>
											<td id="">宝钢新疆八一钢铁</td>
											<td id="">合肥南站</td>
											<td id="">库里货场</td>
											<td id="">货A-12</td>
											<td id="">200吨</td>
											<td id="">150吨</td>
											<td id="">50吨</td>
											<td id="">50吨</td>
											<td id="">宝钢新疆八一钢铁</td>
											<td id="">合肥南站</td>
											<td id="">库里货场</td>
											<td id="">货A-12</td>
											<td id="">200吨</td>
											<td id="">150吨</td>
											<td id="">50吨</td>
											<td id="">50吨</td>
											<td id="">20吨</td>
											<td id="">80吨</td>
											<td id="">2017.08.27 10:00</td>
											<td id="">王大锤</td>

										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-md-12 column paging">
								<ul class="pagination clearfix">
									<li>
										<a href="javascript:;" class="newA">上一页</a>
									</li>
									<li>
										<a href="javascript:;" class="newA actives">1</a>
									</li>
									<li>
										<a href="javascript:;" class="newA">2</a>
									</li>
									<li>
										<a href="javascript:;" class="newA">3</a>
									</li>
									<li>
										<a href="javascript:;" class="newA">4</a>
									</li>
									<li>
										<a href="javascript:;" class="newA">5</a>
									</li>

									<li>
										<a href="javascript:;" class="newA">...</a>
									</li>
									<li>
										<a href="javascript:;" class="newA">10</a>
									</li>

									<li>
										<a href="javascript:;" class="newA">下一页 </a>
									</li>

									<li>
										<a class="pageSpan">跳转到 <input type="text" id="pageInput" />页</a>
									</li>
									<li>

									</li>
									<li>
										<a href="javascript:;" class="pageGo">GO</a>
									</li>

								</ul>
							</div>
						</div>
					</div>
					<!-- 历史盘查结束 -->
				</div>
			</div>
		</div>

		<!-- 库存调整模态框 -->
		<div class="modal fade stockAdjustModal" id="stockAdjustModal" tabindex="-1" role="dialog" data-backdrop="static">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">库存调整</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
					<form id="adjustId">	
						<div class="project_info">
							<h5>项目信息</h5>
							<ul class="clearfix">
								<li>
									<label>项目编号：</label>
									<input type="hidden" name="id" />
									<span name='updateProjectCodeSpan'></span>
								</li>
								<li>
									<label>项目类型：</label>
									<span name='updateProjectTypeSpan'></span>
								</li>
								<li>
									<label>联运模式：</label>
									<span name='updateTransportTypeSpan'></span>
								</li>
								<li>
									<label>发货企业：</label>
									<span name='updateSendCargoCompanyNameSpan'></span>
								</li>
								<li>
									<label>收货企业：</label>
									<span name='updateReceiveCargoCompanyNameSpan'></span>
								</li>
								<li>
									<label>货物品名：</label>
									<span name='updateCargoNameSpan'></span>
								</li>
							</ul>

						</div>
						<hr>
						<div class="form-inline ">
							<div class="form-group ">
								<label>调整站点：</label>
								<select name='updateStation'></select>
							</div>
							<div class="form-group ">
								<label>调整货场：</label>
								<select name="updateFreightYard"></select>
							</div>

						</div>
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th>序号</th>
										<th>货位</th>
										<th>入库吨位</th>
										<th>出库吨位</th>
										<th>库存吨位</th>
										<th>历史库存调整吨位</th>
										<th>调整吨位</th>
									</tr>
								</thead>
								<tbody id='updateStockDetail'>
									<!--<tr>
										<td name='id' hidden="hidden"></td>
										<td>1</td>
										<td>货A-12</td>
										<td>200吨</td>
										<td>200吨</td>
										<td>200吨</td>
										<td>200吨</td>
										<td></td>
									</tr>-->
								</tbody>
							</table>
						</div>

						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="updateStock()">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
					</form>
					</div>
				</div>
			</div>
		</div>
		<!-- 库存盘查模态框 -->
		<div class="modal fade stockCheckModal" id="stockCheckModal" tabindex="-1" role="dialog" data-backdrop="static">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">库存盘查</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="checkId">
						<div class="project_info">
							<h5>项目信息</h5>
							<ul class="clearfix">
								<li>
									<label>项目编号：</label>
									<span name='projectCodeSpan'></span>
								</li>
								<li>
									<label>项目类型：</label>
									<span name='projectTypeSpan'></span>
								</li>
								<li>
									<label>联运模式：</label>
									<span name='transportTypeSpan'></span>
								</li>
								<li>
									<label>发货企业：</label>
									<span name='sendCargoCompanyNameSpan'></span>
								</li>
								<li>
									<label>收货企业：</label>
									<span name='receiveCargoCompanyNameSpan'></span>
								</li>
								<li>
									<label>货物品名：</label>
									<span name='cargoNameSpan'></span>
								</li>
							</ul>
						</div>
						<hr>
						<div class="originating_info">
							<h5>始发信息</h5>
								<ul class="clearfix">
								<li>
									<label>始发站点：</label>
									<span name='beginStationNameSpan'></span>
								</li>
								<li>
									<label>入库吨位：</label>
									<span name='beginEnterQtySpan'></span>
								</li>
								<li>
									<label>出库吨位：</label>
									<span name='beginOutQtySpan'></span>
								</li>
								<li>
									<label>库存吨位：</label>
									<span name='beginCurrentQtySpan'></span>
								</li>
								<li>
									<label>调整吨位：</label>
									<span name='beginAdjustQtySpan'></span>
								</li>
							</ul>
						</div>
						<hr>
						<div class="arrival_info">
							<h5>到达信息</h5>
							<ul class="clearfix">
								<li>
									<label>到达站点：</label>
									<span name='endStationNameSpan'></span>
								</li>
								<li>
									<label>入库吨位：</label>
									<span name='endEnterQtySpan'></span>
								</li>
								<li>
									<label>出库吨位：</label>
									<span name='endOutQtySpan'></span>
								</li>
								<li>
									<label>库存吨位：</label>
									<span name='endCurrentQtySpan'></span>
								</li>
								<li>
									<label>调整吨位：</label>
									<span name='endAdjustQtySpan'></span>
								</li>
							</ul>
						</div>
						<hr />
								<div class="project_info">
							<h5>库存明细</h5>
							<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th>站点</th>
										<th>货场</th>
										<th>货位</th>
										<th>入库吨位</th>
										<th>出库吨位</th>
										<th>库存吨位</th>
										<th>库存调整吨位</th>
									</tr>
								</thead>
								<tbody id='stockDetail'>
									<!--<tr>
										<td>库里火车站</td>
										<td>库里火车站</td>
										<td>货A-12</td>
										<td>200吨</td>
										<td>200吨</td>
										<td>200吨</td>
										<td>200吨</td>
									</tr>-->
								</tbody>
							</table>
						</div>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
						</form>
					</div>

				</div>
			</div>
		</div>

		<!-- 成功提醒模态框 -->
		<div class="modal fade deletRemindModal" style="z-index:99999;" tabindex="-1" role="dialog" id="successModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="body-content">
							<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
							<span id="successMsg"></span>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="successSure()" >确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal" onclick="successSure()" >取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	
	<!--入库-->
	<div class="modal fade storageEnterModal" id="storageEnterModal" tabindex="-1" role="dialog" data-backdrop="static">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">入库</h4>
					<span data-dismiss="modal" aria-label="Close"></span>
				</div>
				<div class="modal-body">
				<form id="enterQty">	
					<div class="project_info">
						<h5>项目信息<i class='requireds'>*</i></h5>
						<div class="form-inline ">
						<input name="projectId" type="hidden" />
						<input name="type" type="hidden"  value="0" />
							<div class="form-group ">
								<label>项目编号：</label>
								<input type="text" name="projectCode" readonly="readonly"/>
							</div>
							<div class="form-group ">
								<label>项目类型：</label>
								<input class="bg" name="projectType" type="hidden"/>
								<input type="text" class="bg" name="projectType1" readonly="readonly"/>
							</div>
							<div class="form-group ">
								<label>分支机构：</label>
								<input type="text" class="bg" name="branchGroupName" readonly="readonly"/>
							</div>
						</div>
						<div class="form-inline ">
							<div class="form-group ">
								<label>中心站点：</label>
								<input type="text" class="bg" name="centerStation" readonly="readonly"/>
							</div>
							<div class="form-group ">
								<label>&emsp;&emsp;站点：</label>
								<input name="stationId" type="hidden" />
								<input type="text" class="bg" name="stationName" readonly="readonly"/>
							</div>
							<div class="form-group ">
								<label>&emsp;发运地：</label>
								<input type="text" class="bg" name="address" readonly="readonly"/>
							</div>
						</div>

					</div>
					<hr>
					<div class="form-inline ">
						<div class="form-group ">
							<label>&emsp;&emsp;货场：</label>
							<select name="freightYardId" onchange="getCargoLocation(this)">
							
							</select>
							<input type="hidden" name="freightYardName" />
						</div>
						<div class="form-group ">
							<label>&emsp;&emsp;货位：</label>
							<select name="cargoLocationId">
							</select>
							<input type="hidden" name="cargoLocationName" />
						</div>
						<div class="form-group ">
							<label>入库数量：</label>
							<input type="text" name="enterQty" onkeyup="value=value.replace(/[^\d.]/g,'')"/>
						</div>
						<div class="addInput" ></div>
					</div>
					<div class="row clearfix ">
						<div class="col-md-12 column modal_btn">
							<button type="button" class="btn sureBtn" onclick="submitEnterQty()">确认入库</button>
							<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
						</div>
					</div>
					<input type="hidden" name="type" value="1" />
				</form>
				</div>
			</div>
		</div>
	</div>
		<!-- 导出模态框 -->
		<div class="modal fade exportModal" tabindex="-1" role="dialog" id="exportModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="body-content">
							<i class="fa fa-question-circle" aria-hidden="true"></i>
							<span>选择了<b id="">13</b>条记录，确认导出全部数据？</span>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
		<!--失败提醒框-->
	<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="deletnullModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="msgTitle">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="body-content">
							<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
							<span id='msgContent'></span>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button"class="btn sureBtn" data-dismiss="modal">确定</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	<!--查看大图-->
	 <div class="modal fade " tabindex="-1" role="dialog" id="ImgModal"  data-backdrop="static">
			<div class="modal-dialog  ImgModal" role="document">
				<div class="modal-content">
					<span data-dismiss="modal" id="closeImg">X</span>
					<img width="100%" height="100%" />
				</div>
			</div>
		</div> 

	
	<!--入库模态框初始化-->
	<script>
		$("#storageEnter").click(function() {
			if($("input:checkbox[type='checkbox']:checked").length > 1) {
				$("#msgContent").html("抱歉，不可批量操作");
				$("#deletnullModal").modal();
				return ;
			} else if($("input:checkbox[type='checkbox']:checked").length == 0) {
				$("#msgContent").html("请选择一条要操作的数据");
				$("#deletnullModal").modal();
				return ;
			} else{
				$("#checkId").validate().resetForm();
				$("#checkId")[0].reset();
			}
			var projectId = $("input:checkbox[type='checkbox']:checked").val();
			//判断项目的联运模式
			var transportType = $("input:checkbox[type='checkbox']:checked").attr('transportType');
			//获取所有的 接取+火运  火运 送达 火运+送达 接取+送达的项目
			if(transportType==0 || transportType ==1 || transportType ==6){
				$("#msgContent").html("");
    			$("#msgContent").html("此项目不能进行入库操作!");
    			$("#deletnullModal").modal();
				return;
			}
			if(transportType==3 || transportType ==5 ){//火运 、火运+送达
				$("#storageEnterModal input[name=type]").val(0);
			}else{
				$("#storageEnterModal input[name=type]").val(1);
			}
			//获取项目信息
			 $.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/projectManagment/getProject.do",
					data : {id:projectId},
					dataType : "json",
					cache : false,
					success : function(data) {
						if(data.status==200){
							var project = data.data;
							$("#storageEnterModal input[name=projectId]").val(project.id);
							$("#storageEnterModal input[name=projectCode]").val(project.projectCode);
							$("#storageEnterModal input[name=projectType]").val(project.projectType);
							$("#transportType").val(project.transportType);
							var projectType = "集装箱";
							if(project.projectType==1){
								projectType = "散装"
							}
							$("#storageEnterModal input[name=projectType1]").val(projectType);
							$("#storageEnterModal input[name=branchGroupName]").val(project.branchGroupName);
							
							var stationId ="";
							var stationName ="";
							var centerStation="";
							var address="";
							
							//接取+火运 接取+送达  火运 火运+送达
							if(project.transportType==4 ){//接取+火运 
								stationId=project.receiveCargoSiteId;
								stationName=project.receiveTrainStation.stationName;
								centerStation=project.receiveCenterCargoSiteName;
								if(project.receiveCargoSiteFreightYard !="" && project.receiveCargoSiteFreightYard !=null ){
									var addressCode = (project.receiveCargoSiteFreightYard.addressCode).split(",");
									if(addressCode.length==3){
										if(addressCode[2]==""){
											address=addressCode[1]+project.receiveCargoSiteFreightYard.address;
										}else{
											address=addressCode[2]+project.receiveCargoSiteFreightYard.address;
										}
									}
								}
								$("#storageEnterModal select[name=freightYardId]").html("<option value='"+project.receiveCargoSiteFreightYard.id+"'>"+project.receiveCargoSiteFreightYard.name+"</option>");
							}else if(project.transportType==3 || project.transportType==5){//火运 火运+送达
								stationId=project.beginSiteId;
								stationName=project.beginSiteName;
								centerStation=project.beginCenterSiteName;
								address=project.beginAddress;
								$("#storageEnterModal select[name=freightYardId]").html("<option value='"+project.beginSiteFreightYard+"'>"+project.beginSiteFreightYardName+"</option>");
							}else if(project.transportType== 7 || project.transportType== 2){//接取+送达
								stationId=project.forwardingSiteId;
								stationName=project.sendTrainStation.stationName;
								centerStation=project.forwardingCenterSiteName;
								if(project.forwardingSiteFreightYard !="" && project.forwardingSiteFreightYard !=null ){
									var addressCode = (project.forwardingSiteFreightYard.addressCode).split(",");
									if(addressCode.length==3){
										if(addressCode[2]==""){
											address=addressCode[1]+project.forwardingSiteFreightYard.address;
										}else{
											address=addressCode[2]+project.forwardingSiteFreightYard.address;
										}
									}
								}
								$("#storageEnterModal select[name=freightYardId]").html("<option value='"+project.forwardingSiteFreightYard.id+"'>"+project.forwardingSiteFreightYard.name+"</option>");
							}
							
							$("#storageEnterModal input[name=stationId]").val(stationId);
							$("#storageEnterModal input[name=stationName]").val(stationName);
							$("#storageEnterModal input[name=centerStation]").val(centerStation);
							$("#storageEnterModal input[name=address]").val(address);
							
							//getFreight(stationId);
							
							$("#storageEnterModal select[name=freightYardId]").change();
						}else{
							$("#msgContent").html("");
			    			$("#msgContent").html(data.msg);
			    			$("#deletnullModal").modal();
			    			return;
						}
					},
			}); 	
			
			$("#storageEnterModal").modal();
		})
		
	</script>
	
	<!--根据站点id获取货场-->
	<script>
		function getFreight(stationId){
			$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/siteManager/getFreightYardByStationId.do",
					data : {id:stationId},
					dataType : "json",
					cache : false,
					success : function(data) {
						if(data.status==200){
							var freights = data.data;
							$("#storageEnterModal select[name=freightYardId]").html("");
							$.each(freights, function(index,freight) {
								$("#storageEnterModal select[name=freightYardId]").append("<option value='"+freight.id+"'>"+freight.name+"</option>");
							});
							$("#storageEnterModal select[name=freightYardId]").change();
						}else{
							$("#msgContent").html("");
			    			$("#msgContent").html(data.msg);
			    			$("#deletnullModal").modal();
						}
					},
			});
		}
	</script>
	
	<!--根据货场 id获取货位信息-->
	<script>
		function getCargoLocation(param){
			var id = $(param).val();
			$(param).next('input').val($(param).find('option:checked').text());
			$.fd.ajax({
				type : 'GET',
				url : "${pageContext.request.contextPath}/siteManager/cargo/location/getAll.do",
				data : {id:id},
				dataType : "json",
				cache : false,
				success : function(data) {
					if(data.status==200){
						var cargoLocations = data.data;
						$("#storageEnterModal select[name=cargoLocationId]").html("");
							$.each(cargoLocations, function(index,cargoLocation) {
								$("#storageEnterModal select[name=cargoLocationId]").append("<option value='"+cargoLocation.id+"'>"+cargoLocation.code+ "  "+cargoLocation.name+"</option>");
							});
					}else{
						$("#msgContent").html("");
		    			$("#msgContent").html(data.msg);
		    			$("#deletnullModal").modal();
					}
				},
			});
		}
	</script>
	
	<!--入库-->
	<script>
		function submitEnterQty(){
			//获取选中的货位名称
			var cargoLocationName = $("select[name=cargoLocationId] option:selected").text();
			$("input[name=cargoLocationName]").val(cargoLocationName);
			var transportType = $("#transportType").val();
			
			var  formParam = $('#enterQty').serialize();
			$.ajax({
				type : 'POST',
				url : "${pageContext.request.contextPath}/inventory/storageInventory.do",
				data : formParam,
				dataType : "json",
				cache : false,
				success : function(data) {
					if(data.status==200){
						$("#successMsg").html("");
		    			$("#successMsg").html("入库成功!");
		    			$("#storageEnterModal").modal("hide");
		    			$("#successModal").modal();
					}else{
						$("#msgContent").html("");
		    			$("#msgContent").html(data.msg);
		    			$("#deletnullModal").modal();
					}
				},
			});
		}
		
	</script>
	
	<!--仓位图-->
	<script>
		$('#position-plane').click(function(){
			$('select[name=freightSpace]').change();
		})
	
	 	function getCargoLocationImg(param){
	 		$('#page').show();
	 		
			 var projectId = $(param).val();
			 $.ajax({
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/inventory/getCargoLocationImg.do',  
		        data:{id:projectId},
		        cache:false,
		        dataType:'json',
	        	success:function(data){
	        		if(data.status==200){
	        		var resultList = data.data;
	        		$('#panel2').html("");
	        		var stocks = resultList[0];
	        		if(stocks!=undefined && stocks!=null){
	        			$.each(stocks.stockList,function(index,stock){
	        			var cargoLocations ="";
	        			$.each(stock.stockList,function(index,cargoLocation){
	        				var kc="";
	        				if(stock.projectType==0){
	        					kc="<li><label>集装箱组：</label><span id=''>"+cargoLocation.containerNum+"组</span></li>"
	        					+"<li><label>集装箱库存：</label><span id=''>"+cargoLocation.currentQty+"吨</span></li>"
	        				}
	        				
	        				if(stock.projectType==1){
	        					kc="<li><label>散堆装库存：</label><span id=''>"+cargoLocation.currentQty+"吨</span></li>"
	        				}
	        				
	        				cargoLocations = cargoLocations +"<li><div>"+cargoLocation.cargoLocationName+"</div><ul class='clearfix'><li>"
							+"<li><label>项目编号：</label><span id=''>"+stock.projectCode+"</span></li>"
							+"<li><label>货物品名：</label><span id=''>"+stock.cargoName+"</span></li>"
							+kc
							+"</ul></li>"
	        			})
	        			
	        		
	        			$('#panel2').append("<div class='containers'>"
						+"<div class='row'><div class='col-xs-12 col-sm-12 col-md-12 col-lg-12'><div class='original-site'>"
						+"<label>始发站点：</label><span id=''>"+stock.stationName+"</span>"
						+"<label>所在货场：</label><span id=''>"+stock.freightYardName+"</span>"
						+"</div></div></div>"
						+"<div class='row'><div class='col-xs-2 col-md-2 col-lg-2 '><div class='plane-pictrue'>"
						+"<img height='218px' width='220px' class='img-responsive' src='/upload/photo/"+stock.img+"' alt='' />"
						+"<div><span>"+stock.freightYardName+"</span><br>"
						+"<a href='javascript:;' class='lookBigImg' >(点击查看大图)</a>"
						+"</div></div></div>"
						+"<div class='col-xs-10'>"
						+"<ul class='clearfix detail-plane'>"
						+cargoLocations+"</ul></div></div></div>")
	        			})
	        		}
	        		stocks=resultList[1];
	        		if(stocks!=undefined && stocks!=null){
	        			$.each(stocks.stockList,function(index,stock){
	        				var cargoLocations ="";
	        			$.each(stock.stockList,function(index,cargoLocation){
	        				var kc="";
	        				if(stock.projectType==0){
	        					kc="<li><label>集装箱组：</label><span id=''>"+cargoLocation.containerNum+"组</span></li>"
	        					+"<li><label>集装箱库存：</label><span id=''>"+cargoLocation.currentQty+"吨</span></li>"
	        				}
	        				
	        				if(stock.projectType==1){
	        					kc="<li><label>散堆装库存：</label><span id=''>"+cargoLocation.currentQty+"吨</span></li>"
	        				}
	        				
	        				cargoLocations = cargoLocations +"<li><div>"+cargoLocation.cargoLocationName+"</div><ul class='clearfix'><li>"
							+"<li><label>项目编号：</label><span id=''>"+stock.projectCode+"</span></li>"
							+"<li><label>货物品名：</label><span id=''>"+stock.cargoName+"</span></li>"
							+kc
							+"</ul></li>"
	        			})
	        		
	        			$('#panel2').append("<div class='containers'>"
						+"<div class='row'><div class='col-xs-12 col-sm-12 col-md-12 col-lg-12'><div class='original-site'>"
						+"<label>始发站点：</label><span id=''>"+stock.stationName+"</span>"
						+"<label>所在货场：</label><span id=''>"+stock.freightYardName+"</span>"
						+"</div></div></div>"
						+"<div class='row'><div class='col-xs-2 col-md-2 col-lg-2 '><div class='plane-pictrue'>"
						+"<img height='218px' width='220px' class='img-responsive' src='/upload/photo/"+stock.img+"' alt='' />"
						+"<div><span>"+stock.freightYardName+"</span><br>"
						+"<a href='javascript:;' class='lookBigImg'  >(点击查看大图)</a>"
						+"</div></div></div>"
						+"<div class='col-xs-10'>"
						+"<ul class='clearfix detail-plane'>"
						+cargoLocations+"</ul></div></div></div>")
	        			})
	        		}
	        		}	
	        	  } 
	        	}) 
	        }	
	</script>
	<!--库存盘查-->
	<script>
		//库存盘查模态框
	     $("#stockCheck").click(function(){
			if($("input:checkbox[type='checkbox']:checked").length > 1) {
				$("#msgContent").html("抱歉，不可批量操作");
				$("#deletnullModal").modal();
				return ;
			} else if($("input:checkbox[type='checkbox']:checked").length == 0) {
				$("#msgContent").html("请选择一条要操作的数据");
				$("#deletnullModal").modal();
				return ;
			} else{
				$("#checkId").validate().resetForm();
				$("#checkId")[0].reset();
			}
			
			//获取库存盘查的信息
			var projectId = $("input:checkbox[type='checkbox']:checked").val();
			
			 $.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/inventory/getInventoryCheckDetail.do",
					data : {"projectId":projectId},
					dataType : "json",
					cache : false,
					success : function(data) {
						if(data.status==200){
							var project = data.data;
							$('#stockCheckModal span[name=projectCodeSpan]').html(project.projectCode);
							//项目类型
			    			var projectType="";
			    			if(project.projectType==0){
			    				projectType="集装箱";
			    			}else if(project.projectType==1){
			    				projectType="散装";
			    			}
			    			//联运模式
			    			var transportType="";
			    			if(project.transportType==0){
			    				transportType="汽运";
			    			}else if(project.transportType==1){
			    				transportType="接取";
			    			}else if(project.transportType==2){
			    				transportType="送达";
			    			}else if(project.transportType==3){
			    				transportType="火运";
			    			}else if(project.transportType==4){
			    				transportType="接取+火运";
			    			}else if(project.transportType==5){
			    				transportType="火运+送达";
			    			}else if(project.transportType==6){
			    				transportType="联运";
			    			}else if(project.transportType==7){
			    				transportType="接取+送达";
			    			}
							
							$('#stockCheckModal span[name=projectTypeSpan]').html(projectType);
							$('#stockCheckModal span[name=transportTypeSpan]').html(transportType);	
							$('#stockCheckModal span[name=sendCargoCompanyNameSpan]').html(project.sendCargoCompanyName);
							$('#stockCheckModal span[name=receiveCargoCompanyNameSpan]').html(project.receiveCargoCompanyName);
							$('#stockCheckModal span[name=cargoNameSpan]').html(project.cargoName);
							$('#stockCheckModal span[name=beginStationNameSpan]').html(project.beginStationName);
							$('#stockCheckModal span[name=beginEnterQtySpan]').html(project.beginEnterQty);
							$('#stockCheckModal span[name=beginOutQtySpan]').html(project.beginOutQty);	
							$('#stockCheckModal span[name=beginCurrentQtySpan]').html(project.beginCurrentQty);	
							$('#stockCheckModal span[name=beginAdjustQtySpan]').html(project.beginAdjustQty);
							
							$('#stockCheckModal span[name=endStationNameSpan]').html(project.endStationName);
							$('#stockCheckModal span[name=endEnterQtySpan]').html(project.endEnterQty);
							$('#stockCheckModal span[name=endOutQtySpan]').html(project.endOutQty);	
							$('#stockCheckModal span[name=endCurrentQtySpan]').html(project.endCurrentQty);	
							$('#stockCheckModal span[name=endAdjustQtySpan]').html(project.endAdjustQty);
								
							$('#stockDetail').html("");	
							$.each(project.stocks,function(index,stock){
									$("#stockDetail").append("<tr>"
										+"<td>"+stock.stationName+"</td>"
										+"<td>"+stock.freightYardName+"</td>"
										+"<td>"+stock.cargoLocationName+"</td>"
										+"<td>"+stock.enterQty+"吨</td>"
										+"<td>"+stock.outQty+"吨</td>"
										+"<td>"+stock.currentQty+"吨</td>"
										+"<td>"+stock.adjustQty+"吨</td>"
									+"</tr>");
							});
							$("#stockCheckModal").modal();
						}
					},
			}); 
			
	     
	     })
		
	</script>
	
	<!--库存调整 查看信息-->
	<script>
		//库存调整模态框
 		$("#stockAdjust").click(function(){
			if($("input:checkbox[type='checkbox']:checked").length > 1) {
				$("#msgContent").html("抱歉，不可批量操作");
				$("#deletnullModal").modal();
				return ;
			} else if($("input:checkbox[type='checkbox']:checked").length == 0) {
				$("#msgContent").html("请选择一条要操作的数据");
				$("#deletnullModal").modal();
				return ;
			} else{
				$("#adjustId").validate().resetForm();
				$("#adjustId")[0].reset();
			}
			
			//获取库存盘查的信息
			var projectId = $("input:checkbox[type='checkbox']:checked").val();
			
			 $.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/inventory/getInventoryCheckDetail.do",
					data : {"projectId":projectId},
					dataType : "json",
					cache : false,
					success : function(data) {
						if(data.status==200){
							var project = data.data;
							$('#stockAdjustModal span[name=updateProjectCodeSpan]').html(project.projectCode);
							$('#stockAdjustModal input[name=id]').val(project.projectId);
							//项目类型
			    			var projectType="";
			    			if(project.projectType==0){
			    				projectType="集装箱";
			    			}else if(project.projectType==1){
			    				projectType="散装";
			    			}
			    			//联运模式
			    			var transportType="";
			    			var station="";
			    			if(project.transportType==0){
			    				transportType="汽运";
			    			}else if(project.transportType==1){
			    				transportType="接取";
			    				station="<option value='"+project.beginStationId+"'>"+project.beginStationName+"</option>";
			    			}else if(project.transportType==2){
			    				transportType="送达";
			    				station="<option value='"+project.endStationId+"'>"+project.endStationName+"</option>";
			    			}else if(project.transportType==3){
			    				transportType="火运";
			    				station="<option value='"+project.beginStationId+"'>"+project.beginStationName+"</option>"
			    				+"<option value='"+project.endStationId+"'>"+project.endStationName+"</option>";
			    			}else if(project.transportType==4){
			    				transportType="接取+火运";
			    				station="<option value='"+project.beginStationId+"'>"+project.beginStationName+"</option>"
			    				+"<option value='"+project.endStationId+"'>"+project.endStationName+"</option>";
			    			}else if(project.transportType==5){
			    				transportType="火运+送达";
			    				station="<option value='"+project.beginStationId+"'>"+project.beginStationName+"</option>"
			    				+"<option value='"+project.endStationId+"'>"+project.endStationName+"</option>";
			    			}else if(project.transportType==6){
			    				transportType="联运";
			    				station="<option value='"+project.beginStationId+"'>"+project.beginStationName+"</option>"
			    				+"<option value='"+project.endStationId+"'>"+project.endStationName+"</option>";
			    			}else if(project.transportType==7){
			    				transportType="接取+送达";
			    				station="<option value='"+project.beginStationId+"'>"+project.beginStationName+"</option>"
			    				+"<option value='"+project.endStationId+"'>"+project.endStationName+"</option>";
			    			}
			    			//alert(transportType);
			    			$('#stockAdjustModal span[name=updateTransportTypeSpan]').html(transportType);
							$('#stockAdjustModal span[name=updateProjectTypeSpan]').html(projectType);
							$('#stockAdjustModal span[name=updateSendCargoCompanyNameSpan]').html(project.sendCargoCompanyName);
							$('#stockAdjustModal span[name=updateReceiveCargoCompanyNameSpan]').html(project.receiveCargoCompanyName);
							$('#stockAdjustModal span[name=updateCargoNameSpan]').html(project.cargoName);
								
							/*显示站点*/
							$("#stockAdjustModal select[name=updateStation]").html("");
							$("#stockAdjustModal select[name=updateStation]").html(station);
							
							$("select[name='updateStation']").change();
							$("#stockAdjustModal").modal();
						}
					},
			}); 
	     })
		
		
		$("select[name='updateStation']").change(function(){
			var value = $(this).find("option:checked").val();
			$.ajax({
		        	type:'post',      
		       		url:'${pageContext.request.contextPath}/siteManager/getFreightYardByStationId.do', 
			        data:{id:value},
			        cache:false,
			        dataType:'json',
		        	success:function(data){
		        		if(data.status==200){
		        			$('select[name=updateFreightYard]').html("");
		        			var frightYards = data.data;
		        			$.each(frightYards,function(index,frightYard){
		        				$('select[name=updateFreightYard]').append("<option value='"+frightYard.id+"'>"+frightYard.name+"</option>");
		        			})
		        			$("select[name=updateFreightYard]").change();
			        	}  
				 	}
			})
		})
		
		
		/*通过货场id 获取该项目下的货位信息*/
		$("select[name=updateFreightYard]").change(function(){
			var freightYardId = $(this).find("option:checked").val();
			var projectId = $('#stockAdjustModal input[name=id]').val();
			$.ajax({
		        	type:'post',      
		       		url:'${pageContext.request.contextPath}/inventory/getStockByFreightYardId.do', 
			        data:{projectId:projectId,freightYardId:freightYardId},
			        cache:false,
			        dataType:'json',
		        	success:function(data){
		        		if(data.status==200){
		        			var stocks = data.data;
		        			$('#updateStockDetail').html("");
							$.each(stocks,function(index,stock){
									$("#updateStockDetail").append("<tr>"
										+"<td>"+(index+1)+"</td>"
										+"<td>"+stock.cargoLocationName+"</td>"
										+"<td>"+stock.enterQty+"吨</td>"
										+"<td>"+stock.outQty+"吨</td>"
										+"<td>"+stock.currentQty+"吨</td>"
										+"<td>"+stock.adjustQty+"吨</td>"
										+"<td name='adjustQty' id='"+stock.id+"'></td>"
									+"</tr>");
							});
			        	}  
				 	}
			})
		});
	</script>
	
	<!-- 库存调整 -->
	<script type="text/javascript">
		function updateStock(){
			var array = new Array();
			$("#updateStockDetail td[name=adjustQty]").each(function(i){
				var id=$(this).attr("id");
			 	var adjustQty= $(this).text();
				var stockJson="{id: '"+id+"',adjustQty: '"+adjustQty+"'}";  
				stockJson = eval("(" + stockJson + ")"); 
				var data =  JSON.stringify(stockJson);
				array[i]=data;
			});
			var stockJson = "["+array+"]";
			//alert(stockJson);
			
			 $.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/inventory/updateStock.do",
					data : {"stocks":stockJson},
					dataType : "json",
					cache : false,
					success : function(data) {
						if(data.status==200){
							$("#stockAdjustModal").hide("");
							$("#successMsg").html("");
		        			$("#successMsg").html("库存调整成功!");
		        			$("#successModal").modal();
						}else{
							$("#msgContent").html("");
			    			$("#msgContent").html(data.msg);
			    			$("#deletnullModal").modal();
						}
					},
			}); 
			
		}
	
		function successSure(){
			$("#successModal").modal();
			window.location.reload();
		}
	</script>
</script>
	
<!--刷新-->	
<script>
	function myrefresh(){
		window.location.reload();
	}	
</script>
	</body>
</html>