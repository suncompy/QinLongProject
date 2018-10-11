<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="UTF-8">
		<title>项目管理</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/public.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/business/project/project.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/config/url.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.validate.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.serializejson.js"></script>
		<script src="${pageContext.request.contextPath}/static/js/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/util.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js"></script>
		<link href="${pageContext.request.contextPath}/css/page/page.css" rel="stylesheet" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/search/get-basic-data.js"></script>
	</head>

	<body class="projectManagment">
		<input type="text" id="mtext" hidden="hidden" />
		<!-- 项目管理表单 -->
		<div class="form project_form container_top">
			<form class="form-inline maxWidth" id="searchForm">
				<div id="wrap">
					<div class="form-group">
						<label>项目编号：</label>
						<input name='projectCode' type="text" maxlength="30" />
					</div>
					<div class="form-group">
						<label>分支机构：</label>
						<select name="branchGroupId">
						</select>
					</div>
					<input name="status" type="hidden" value="1" />
					<div class="form-group">
						<label>项目类型：</label>
						<select name="projectType">
							<option></option>
							<option value="0">集装箱</option>
							<option value="1">散堆装</option>
						</select>
					</div>
					<div class="form-group">
						<label>联运模式：</label>
						<select name="transportType">
							<option></option>
							<option value="0">汽运</option>
							<option value="1">接取</option>
							<option value="2">送达</option>
							<option value="3">火运</option>
							<option value="4">接取+火运</option>
							<option value="5">火运+送达</option>
							<option value="7">接取+送达</option>
							<option value="6">联运</option>
						</select>
					</div>
					<div class="form-group">
						<label>货物品名：</label>
						<select name="cargoId">
						</select>
					</div>
					<div class="form-group dataTimes">
						<label>&emsp;日期从：</label>
						<input name="beginDate" id="d244" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy.MM.dd HH:mm'})" />
						<i class="fa  fa-calendar-check-o " id="calendar2"></i>
					</div>
					<div class="form-group dataTimes secTime">
						<label>至</label>
						<input name="endDate" id="d244" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy.MM.dd HH:mm'})" />
						<i class="fa  fa-calendar-check-o " id="calendar2"></i>
					</div>
					<div class="form-group">
						<label>发货企业：</label>
						<input name='sendCargoCompanyName' type="text" maxlength="30" />
					</div>
					<div class="form-group">
						<label>收货企业：</label>
						<input name='receiveCargoCompanyName' type="text" maxlength="30" />
					</div>
				</div>
				<div class="foot">
					<a class="btn btn-success" href="javascript:;"><em class="search"></em>
						<span id='searchSpan' onclick="projectSearch('/projectManagment/getProjectPage.do')">搜索</span>
					</a>
					<div id="lookMore"></div>
				</div>
			</form>
		</div>
		<!-- 项目管理表格 -->
		<div class=" container_bottom tableDiv">
			<div class="tabbable" id="tabs-529262">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#panel1" data-toggle="tab" onclick="listProject(1)">项目列表</a>
					</li>
					<li>
						<a href="#panel2" data-toggle="tab" onclick="listProject(2)">历史项目</a>
					</li>
					<li>
						<a href="#panel3" data-toggle="tab" onclick="listProject(0)">项目回收站</a>
					</li>
				</ul>

				<div class="tab-content">
					<!--项目列表表格开始-->
					<div class="tab-pane active" id="panel1">

						<div class="domain">
							<div class="tableBg">
								<c:forEach items='${permissions }' var='permission' >
									<c:if test="${permission.code=='creatProject'}">
										<a href="#" class="exportBtn add" onclick="return false;" id="creatProject"><span>新建</span></a>
									</c:if>
									<c:if test="${permission.code=='modifyProject'}">
										<a href="#" class="exportBtn revise" onclick="return false;" id="modify"><span>修改</span></a>
									</c:if>
									<c:if test="${permission.code=='completeProject'}">
										<a href="#" class="exportBtn complete" onclick="return false;" id="complete"><span>完成</span></a>
									</c:if>
									<c:if test="${permission.code=='exportByProject'}">
										<a href="#" class="exportBtn export checks" id="exportByProject"><span>导出</span></a>
									</c:if>
									<c:if test="${permission.code=='delProject'}">
										<a href="#" class="exportBtn del" onclick="return false;" id="delBtn"><span>删除</span></a>
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
							                </label>
											</th>
											<th>项目编号</th>
											<th>项目类型</th>
											<th>联运模式</th>
											<th>分支机构</th>
											<th>发货企业</th>
											<th>收货企业</th>
											<th>货物品名</th>
											<th>短驳承运方式</th>
											<th>短驳承运方</th>
											<th>计价单位</th>
											<th>备注信息</th>
										</tr>
									</thead>
									<input type="hidden" id="delProject" />
									<tbody id="projectTbody">
									</tbody>
								</table>
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-md-12 column paging page_div" id="projectPage">
							</div>
						</div>
					</div>
					<!--项目列表表格结束-->
					<!--历史项目表格开始-->
					<div class="tab-pane" id="panel2">
						<div class="domain">
							<div class="tableBg">
								<!-- <a href="#" class="exportBtn rest" id='recoverBtn'><span>还原</span></a> -->
								<!--<a href="#" class="exportBtn look"><span>查看</span></a>-->
								<c:forEach items='${permissions }' var='permission' >
									<c:if test="${permission.code=='exportByHistoryProject'}">
										<a href="#" class="exportBtn export checks" id="exportByHistory"><span>导出</span></a>
									</c:if>
								</c:forEach>
							</div>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th> <label class="demo--label">
								            <input class="demo--checkbox" type="checkbox"  >
							                  <span class="demo--checkboxInput"></span>
							                         	</label></th>
											<th>项目编号</th>
											<th>项目类型</th>
											<th>联运模式</th>
											<th>分支机构</th>
											<th>发货企业</th>
											<th>收货企业</th>
											<th>货物品名</th>
											<th>短驳承运方式</th>
											<th>短驳承运方</th>
											<th>计价单位</th>
											<th>备注信息</th>
											<th>完成时间</th>
										</tr>
									</thead>
									<tbody id="historyProjectTbody">
										<input type="hidden" id="restoreHistoryProjectId" />
									</tbody>
								</table>
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-md-12 column paging page_div" id="historyPage">
							</div>
						</div>
					</div>
					<!-- 历史项目结束 -->
					<!--项目回收站开始-->
					<div class="tab-pane" id="panel3">
						<div class="domain">
							<div class="tableBg">
								<!-- <a href="#" class="exportBtn rest" onclick="return false;"><span>还原</span></a> -->
								<c:forEach items='${permissions }' var='permission' >
									<c:if test="${permission.code=='exportByrecycleProject'}">
										<a href="#" class="exportBtn export checks" id="exportByrecycle" onclick="return false;"><span>导出</span></a>
									</c:if>
								</c:forEach>
							</div>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th> <label class="demo--label">
								            <input class="demo--checkbox" type="checkbox"  >
							                  <span class="demo--checkboxInput"></span>
							                         	</label></th>
											<th>项目编号</th>
											<th>项目类型</th>
											<th>联运模式</th>
											<th>分支机构</th>
											<th>发货企业</th>
											<th>收货企业</th>
											<th>货物品名</th>
											<th>短驳承运方式</th>
											<th>短驳承运方</th>
											<th>计价单位</th>
											<th>备注信息</th>
											<th>删除人</th>
											<th>删除时间</th>
											<th>删除原因</th>
										</tr>
									</thead>
									<tbody id="recycleTbody">
										<input type="hidden" id="restoreRecycleWayBillId" />
									</tbody>
								</table>
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-md-12 column paging page_div" id="recyclePage">
							</div>
						</div>
					</div>
					<!-- 项目回收站结束 -->
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
		<!-- 失败提醒模态框 -->
		<div style="z-index: 9999;" class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="deletnullModal" data-backdrop="static">
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
								<button type="button" class="btn sureBtn" data-dismiss="modal">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 提醒模态框 -->
		<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="deltialModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="body-content">
							<div><span>您已选择</span><b class="errnums">1</b> <span id="msgSpan"></span></div>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="restProject()">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 成功提醒模态框 -->
		<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="successModal" data-backdrop="static">
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
								<button type="button" class="btn sureBtn" onclick="successSure()">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal" onclick="successSure()">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<input id="restId" type="hidden" />
		<!-- 删除提醒模态框 -->
		<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="deletRemindModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="body-content">
							<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
							<span id="warnMsg">抱歉，不可批量删除</span>
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
		<!--删除原因模态框-->
		<div class="modal fade deletReasonModal" tabindex="-1" role="dialog" id="deletReasonModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">删除原因</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="deletForm">
							<div class="body-content">
								<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
								<span>你是否选择删除此数据，删除后不可还原</span>
							</div>
							<div class="delete_reason">
								<h4>删除原因</h4>
								<textarea class="form-control textareas" id="reason" rows="3"></textarea>
							</div>
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
									<button type="button" class="btn sureBtn" onclick="delProject()">确定</button>
									<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>


		<!--汽运项目详情修改模态框-->
		<div class="modal fade  detailModal modifyModal" tabindex="-1" role="dialog" data-backdrop="static" id="truckModal">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content  ">
					<div class="modal-header">
						<h4 class="modal-title">项目详情</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<input type="hidden" name='id'>
					<div class="modal-body">
						<!-- 项目信息 -->
						<div class="project_info">
							<h5>项目信息</h5>
							<ul class="clearfix">
								<li>
									<label>分支机构：</label>
									<span name="branchGroupName"></span>
								</li>
								<li>
									<label>项目类型：</label>
									<span name="projectType"></span>
								</li>
								<li>
									<label>联运模式：</label>
									<span name="transportType"></span>
								</li>
								<li>
									<label>委托方：</label>
									<span name="principal"></span>
								</li>
								<li class="history_detail">
									<label>完成时间：</label>
									<span name="finishDate"></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 货物信息 -->
						<div class="cargo_info">
							<h5>货物信息</h5>
							<ul class="clearfix">
								<li>
									<label>货物品名：</label>
									<span name="cargoName"></span>
								</li>
								<li>
									<label>货物规格：</label>
									<span name="cargoSpecifications"></span>
								</li>
								<li>
									<label>货物单价：</label>
									<span name="cargoPrice"></span>
								</li>
								<li>
									<label>计价单位：</label>
									<span name="valuationUnitName"></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 收发货企业 -->
						<div class="detail_info cut_apart clearfix">
							<!--   发货企业  -->
							<div class="half splitters">
								<h5>发货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="sendCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="sendCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="sendCargoCompanyAddress"></span>
								</div>

							</div>
							<!--   收货企业    -->
							<div class="half">
								<h5>收货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="receiveCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="receiveCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="receiveCargoCompanyAddress"></span>
								</div>

							</div>
						</div>
						<hr>
						<!-- 汽运短驳  -->
						<div class="receve_truck cut_apart clearfix">
							<h5>汽运短驳</h5>
							<div class="half splitters">

								<ul class="clearfix pad">
									<li class="long">
										<label>发货单位：</label>
										<span name="sendCargoUnitName"></span>
									</li>
									<li>
										<label>&emsp;取货地：</label>
										<span name="sendCargoAreaCode"></span>
									</li>
								</ul>
								<div class="pad">
									<label>取货地址：</label>
									<span name="sendCargoAddress"></span>

								</div>

							</div>
							<div class="half">

								<ul class="clearfix">
									<li class="long">
										<label>收货单位：</label>
										<span name="receivingDepartmentName"></span>
									</li>
									<li>
										<label>运抵地：</label>
										<span name="receivingDepartmentAreaCode"></span>
									</li>
								</ul>
								<div>
									<label>运抵地址：</label>
									<span name="receivingDepartmentAddress"></span>
								</div>

							</div>
							<!--   短驳承运方  -->
							<div class="whole">
								<p>短驳承运方</p>
								<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
								<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
								<form class="form-inline ">
									<div class="form-group ">
										<label>短驳承运方式：</label>
										<select name="shortBargeCarrierMode1" onchange="updateShortBarge(this)">
											<option value="0">平台</option>
											<option value="1">自选</option>
										</select>
									</div>
								</form>
								<div name="shortBargeCarrierName1">
								</div>
							</div>

						</div>
						<hr>
						<!-- 委托方费用 -->
						<div class="client_cost whole">
							<h5>委托方费用</h5>
							<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
							<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
							<form class="form-inline pad">
								<div class="form-group"><label>汽运单价：</label><input type="text" name="truckTransportPrice" maxlength="30" readonly="readonly"><span>元</span></div>
							</form>
						</div>
						<hr>
						<!-- 备注信息 -->
						<div class="remarks_info">
							<h5>备注信息</h5>
							<div name="remark"></div>
						</div>
						<hr>
						<!-- 删除信息 -->
						<div class="del-info">
							<h5>删除信息</h5>
							<ul class="clearfix pad">
								<li>
									<label>删除人：</label>
									<span name='delUser'></span>
								</li>
								<li>
									<label>删除时间：</label>
									<span name='delDate'></span>
								</li>
							</ul>
							<div class="pad">
								<label>删除原因：</label>
								<span name='delReason'></span>
							</div>
							<hr>
						</div>
						<!-- 历史修改记录 -->
						<!-- <div class="history_modify">
							<h5>历史修改记录</h5>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方:合肥深合软件有限公司的“运输单价”为1000元，原“运输单价”为2000元</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改送达短驳承运方:合肥深合软件有限公司的“扣损比率”为1‰，原“扣损比率”为21‰</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方为“合肥深合软件有限公司”，原承运方为“新疆秦龙矿业有限公司”</div>
						</div> -->

						<!--按钮-->
						<div class="row clearfix hidebtn">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="updateProject(0,'truckModal')">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>

		<!--接取项目详情和修改模态框-->
		<div class="modal fade  detailModal modifyModal" tabindex="-1" role="dialog" data-backdrop="static" id="receveModal">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content  ">
					<div class="modal-header">
						<h4 class="modal-title">项目详情（接取）</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<input type="hidden" name='id'>
					<div class="modal-body">
						<!-- 项目信息 -->
						<div class="project_info">
							<h5>项目信息</h5>
							<ul class="clearfix">
								<li>
									<label>分支机构：</label>
									<span name="branchGroupName"></span>
								</li>
								<li>
									<label>项目类型：</label>
									<span name="projectType"></span>
								</li>
								<li>
									<label>联运模式：</label>
									<span name="transportType"></span>
								</li>
								<li>
									<label>委托方：</label>
									<span name="principal"></span>
								</li>
								<li class="history_detail">
									<label>完成时间：</label>
									<span name="finishDate"></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 货物信息 -->
						<div class="cargo_info">
							<h5>货物信息</h5>
							<ul class="clearfix">
								<li>
									<label>货物品名：</label>
									<span name="cargoName"></span>
								</li>
								<li>
									<label>货物规格：</label>
									<span name="cargoSpecifications"></span>
								</li>
								<li>
									<label>货物单价：</label>
									<span name="cargoPrice"></span>
								</li>
								<li>
									<label>计价单位：</label>
									<span name="valuationUnitName"></span>
								</li>
							</ul>
						</div>
						<hr>
						<div class="detail_info cut_apart clearfix">
							<!--   发货企业  -->
							<div class="half splitters">
								<h5>发货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="sendCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="sendCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="sendCargoCompanyAddress"></span>
								</div>

							</div>
							<!--   收货企业    -->
							<div class="half">
								<h5>收货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="receiveCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="receiveCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="receiveCargoCompanyAddress"></span>
								</div>
							</div>
						</div>
						<hr>
						<!-- 接取短驳  -->
						<div class="receve_truck cut_apart clearfix">
							<h5>接取短驳</h5>
							<div class="half splitters">
								<ul class="clearfix pad">
									<li class="long">
										<label>发货单位：</label>
										<span id="" name="sendCargoUnitName"></span>
									</li>
									<li>
										<label>&emsp;取货地：</label>
										<span id="" name="sendCargoAreaCode"></span>
									</li>
								</ul>
								<div class="pad">
									<label>取货地址：</label>
									<span id="" name="sendCargoAddress"></span>
								</div>
							</div>
							<div class="half ">
								<ul class="clearfix">
									<li class="long">
										<label>到达中心站：</label>
										<span name="receiveCenterCargoSiteName"></span>
									</li>
									<li>
										<label>到达站点：</label>
										<span name="receiveCargoSite"></span>
									</li>
								</ul>
								<ul class="clearfix">
									<li class="long">
										<label>卸货货场：</label>
										<span name="receiveCargoSiteFreightYard"></span>
									</li>
									<li>
										<label>到达地址：</label>
										<span name="receiveCargoSiteAddress"></span>
									</li>
								</ul>
							</div>
							<!-- <div class="half">
								<ul class="clearfix pad">
									<li class="long">
										<label>收货站点：</label>
										<span name="receiveCargoSite"></span>
									</li>
									<li>
										<label>&emsp;运抵地：</label>
										<span name="receiveCargoSiteAreaCode"></span>
									</li>
								</ul>
								<div class="pad">
									<label>运抵地址：</label>
									<span name="receiveCargoSiteAddress"></span>
								</div>
							</div> -->
							<!--   短驳承运方  -->
							<div class="whole modify-whole">
								<p>短驳承运方</p>
								<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
								<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
								<form class="form-inline ">
									<div class="form-group ">
										<label>短驳承运方式：</label>
										<select name="shortBargeCarrierMode1" onchange="updateShortBarge(this)">
											<option value="0">平台</option>
											<option value="1">自选</option>
										</select>
									</div>
								</form>
								<div name='shortBargeCarrierName1'></div>
							</div>
						</div>
						<hr>
						<!-- 委托方费用 -->
						<div class="client_cost whole modify-whole">
							<h5>委托方费用</h5>
							<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
							<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
							<form class="form-inline pad">
								<div class="form-group"><label>接取单价：</label><input type="text" name='recevePickUpPrice' maxlength="30" readonly="readonly"><span>元</span></div>
							</form>
						</div>
						<hr>
						<!-- 备注信息 -->
						<div class="remarks_info">
							<h5>备注信息</h5>
							<div class="pad" name='receveRemark'></div>
						</div>
						<hr>
						<!-- 删除信息 -->
						<div class="del-info">
							<h5>删除信息</h5>
							<ul class="clearfix pad">
								<li>
									<label>删除人：</label>
									<span name='delUser'></span>
								</li>
								<li>
									<label>删除时间：</label>
									<span name='delDate'></span>
								</li>
							</ul>
							<div class="pad">
								<label>删除原因：</label>
								<span name='delReason'></span>
							</div>
							<hr>
						</div>
						<!-- 历史修改记录 -->
						<!-- <div class="history_modify">
							<h5>历史修改记录</h5>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方:合肥深合软件有限公司的“运输单价”为1000元，原“运输单价”为2000元</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改送达短驳承运方:合肥深合软件有限公司的“扣损比率”为1‰，原“扣损比率”为21‰</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方为“合肥深合软件有限公司”，原承运方为“新疆秦龙矿业有限公司”</div>
						</div> -->
						<!--按钮-->
						<div class="row clearfix hidebtn">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick='updateProject(1,"receveModal")'>确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>

		<!--送达项目详情和修改模态框-->
		<div class="modal fade  detailModal modifyModal" tabindex="-1" role="dialog" data-backdrop="static" id="sendModal">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content  ">
					<div class="modal-header">
						<h4 class="modal-title">项目详情（送达）</h4>
						<input type="hidden" name='id'>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<!-- 项目信息 -->
						<!-- 项目信息 -->
						<div class="project_info">
							<h5>项目信息</h5>
							<ul class="clearfix">
								<li>
									<label>分支机构：</label>
									<span name="branchGroupName"></span>
								</li>
								<li>
									<label>项目类型：</label>
									<span name="projectType"></span>
								</li>
								<li>
									<label>联运模式：</label>
									<span name="transportType"></span>
								</li>
								<li>
									<label>委托方：</label>
									<span name="principal"></span>
								</li>
								<li class="history_detail">
									<label>完成时间：</label>
									<span name="finishDate"></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 货物信息 -->
						<div class="cargo_info">
							<h5>货物信息</h5>
							<ul class="clearfix">
								<li>
									<label>货物品名：</label>
									<span name="cargoName"></span>
								</li>
								<li>
									<label>货物规格：</label>
									<span name="cargoSpecifications"></span>
								</li>
								<li>
									<label>货物单价：</label>
									<span name="cargoPrice"></span>
								</li>
								<li>
									<label>计价单位：</label>
									<span name="valuationUnitName"></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 收发货企业 -->
						<div class="detail_info cut_apart clearfix">
							<!--   发货企业  -->
							<div class="half splitters">
								<h5>发货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="sendCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="sendCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="sendCargoCompanyAddress"></span>
								</div>

							</div>
							<!--   收货企业    -->
							<div class="half">
								<h5>收货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="receiveCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="receiveCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="receiveCargoCompanyAddress"></span>
								</div>
							</div>
						</div>
						<hr>
						<!-- 送达短驳 -->
						<div class="send_truck cut_apart clearfix">
							<h5>送达短驳</h5>
							<div class="half ">
								<ul class="clearfix">
									<li class="long">
										<label>发货中心站：</label>
										<span name="forwardingCenterSiteName"></span>
									</li>
									<li>
										<label>取货站点：</label>
										<span name="forwardingSiteName"></span>
									</li>
								</ul>
								<ul class="clearfix">
									<li class="long">
										<label>取货货场：</label>
										<span name="forwardingSiteFreightYard"></span>
									</li>
									<li>
										<label>取货地址：</label>
										<span name="forwardingSiteAddress"></span>
									</li>
								</ul>
							</div>
						<!-- 	<div class="half splitters">

								<ul class="clearfix">
									<li class="long">
										<label>接取站点：</label>
										<span name="forwardingSiteName"></span>
									</li>
									<li>
										<label>取货地：</label>
										<span name="forwardingSiteAreaCode"></span>
									</li>
								</ul>
								<div>
									<label>取货地址：</label>
									<span name="forwardingSiteAddress"></span>
								</div>

							</div> -->
							<div class="half">
								<ul class="clearfix">
									<li class="long">
										<label>收货单位：</label>
										<span name="receivingDepartmentName"></span>
									</li>
									<li>
										<label>运抵地：</label>
										<span name="receivingDepartmentAreaCode"></span>
									</li>
								</ul>
								<div>
									<label>运抵地址：</label>
									<span name="receivingDepartmentAddress"></span>
								</div>
							</div>

							<!--   短驳承运方  -->
							<div class="whole modify-whole">
								<p>短驳承运方</p>
								<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
								<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
								<form class="form-inline ">
									<div class="form-group ">
										<label>短驳承运方式：</label>
										<select name="shortBargeCarrierMode2" onchange="updateShortBarge(this)">
											<option value="0">平台</option>
											<option value="1">自选</option>
										</select>
									</div>
								</form>
								<div name='shortBargeCarrierName2'></div>
							</div>
						</div>
						<hr>
						<!-- 委托方费用 -->
						<div class="client_cost whole modify-whole">
							<h5>委托方费用</h5>
							<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
							<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
							<form class="form-inline pad">
								<div class="form-group"><label>送达单价：</label><input type="text" name='sendArrivePrice' maxlength="30" readonly="readonly"><span>元</span></div>
							</form>
						</div>
						<hr>
						<!-- 备注信息 -->
						<div class="remarks_info">
							<h5>备注信息</h5>
							<div name='sendRemark'></div>
						</div>
						<hr>
						<!-- 删除信息 -->
						<div class="del-info">
							<h5>删除信息</h5>
							<ul class="clearfix pad">
								<li>
									<label>删除人：</label>
									<span name='delUser'></span>
								</li>
								<li>
									<label>删除时间：</label>
									<span name='delDate'></span>
								</li>
							</ul>
							<div class="pad">
								<label>删除原因：</label>
								<span name='delReason'></span>
							</div>
							<hr>
						</div>
						<!-- 历史修改记录 -->
					<!-- 	<div class="history_modify">
							<h5>历史修改记录</h5>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方:合肥深合软件有限公司的“运输单价”为1000元，原“运输单价”为2000元</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改送达短驳承运方:合肥深合软件有限公司的“扣损比率”为1‰，原“扣损比率”为21‰</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方为“合肥深合软件有限公司”，原承运方为“新疆秦龙矿业有限公司”</div>
						</div> -->
						<!--按钮-->
						<div class="row clearfix hidebtn">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="updateProject(2,'sendModal')">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!--火运项目详情模态框-->
		<div class="modal fade  detailModal modifyModal" tabindex="-1" role="dialog" data-backdrop="static" id="trainModal">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content  ">
					<div class="modal-header">
						<h4 class="modal-title">项目详情（火运）</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<!-- 项目信息 -->
						<!-- 项目信息 -->
						<div class="project_info">
							<h5>项目信息</h5>
							<ul class="clearfix">
								<li>
									<label>分支机构：</label>
									<span name="branchGroupName"></span>
								</li>
								<li>
									<label>项目类型：</label>
									<span name="projectType"></span>
								</li>
								<li>
									<label>联运模式：</label>
									<span name="transportType"></span>
								</li>
								<li>
									<label>委托方：</label>
									<span name="principal"></span>
								</li>
								<li class="history_detail">
									<label>完成时间：</label>
									<span name="finishDate"></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 货物信息 -->
						<div class="cargo_info">
							<h5>货物信息</h5>
							<ul class="clearfix">
								<li>
									<label>货物品名：</label>
									<span name="cargoName"></span>
								</li>
								<li>
									<label>货物规格：</label>
									<span name="cargoSpecifications"></span>
								</li>
								<li>
									<label>货物单价：</label>
									<span name="cargoPrice"></span>
								</li>
								<li>
									<label>计价单位：</label>
									<span name="valuationUnitName"></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 收发货企业 -->
						<div class="detail_info cut_apart clearfix">
							<!--   发货企业  -->
							<div class="half splitters">
								<h5>发货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="sendCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="sendCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="sendCargoCompanyAddress"></span>
								</div>

							</div>
							<!--   收货企业    -->
							<div class="half">
								<h5>收货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="receiveCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="receiveCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="receiveCargoCompanyAddress"></span>
								</div>
							</div>
						</div>
						<hr>
						<!-- 火运干线  -->
						<div class="train_line cut_apart clearfix">
							<h5>火运干线</h5>
							<!-- 起运中心站 -->
							<div class="half splitters">
								<ul class="clearfix">
									<li class="long">
										<label>起运中心站：</label>
										<span name='beginCenterSiteName'></span>
									</li>
									<li>
										<label>始发站点：</label>
										<span name='beginSiteName'></span>
									</li>
								</ul>
								<ul class="clearfix">
									<li class="long">
										<label>始发货场：</label>
										<span name='beginSiteFreightYardName'></span>
									</li>
									<li>
										<label>始发地：</label>
										<span name='beginAddress'></span>
									</li>
								</ul>
							</div>
							<!-- 到达中心站 -->
							<div class="half ">
								<ul class="clearfix">
									<li class="long">
										<label>到达中心站：</label>
										<span name='endCenterSiteName'></span>
									</li>
									<li>
										<label>到达站点：</label>
										<span name='endSiteName'></span>
									</li>
								</ul>
								<ul class="clearfix">
									<li class="long">
										<label>到达货场：</label>
										<span name='endSiteFreightYardName'></span>
									</li>
									<li>
										<label>到达地：</label>
										<span name='endAddress'></span>
									</li>
								</ul>
							</div>
							<!--  干线费用   -->
							<!--修改div-->
							<div class="whole modify-whole">
								<p>干线费用</p>
								<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
								<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
								<form class="form-inline" name="trainCost">
									<input type="hidden" name="id" />
									<div>
										<div class="form-group">
											<label>&emsp;运费核计：</label>
											<input type="text" maxLength="10" onkeyup="add(this)" name='freight'><span>元</span>
										</div>
										<div class="form-group">
											<label>加固材料费：</label>
											<input type="text" maxLength="10" onkeyup="add(this)" name='materialCost'><span>元</span>
										</div>
										<div class="form-group">
											<label>篷布使用费：</label>
											<input type="text" maxLength="10" onkeyup="add(this)" name='tarpaulinCost'><span>元</span>
										</div>
										<div class="form-group">
											<label>发站装卸费：</label>
											<input type="text" maxLength="10" onkeyup="add(this)" name='beginStevedoringCost'><span>元</span>
										</div>
										<div class="form-group">
											<label>到站装卸费：</label>
											<input type="text" maxLength="10" onkeyup="add(this)" name='endStevedoringCost'><span>元</span>
										</div>
										<div class="form-group">
											<label>&emsp;运费合计：</label>
											<input type="text" name="freightSum" class="inputbg" readonly="readonly" maxLength="30"><span>元</span>
										</div>
										<input type="hidden" name="trainPrice" />
										<input type="hidden" name="transportType" value="3" />
									</div>
								</form>
							</div>
						</div>
						<hr>
						<div class="client_cost whole modify-whole">
							<h5>委托方费用</h5>
							<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
							<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
							<form class="form-inline pad">
								<div class="form-group"><label>火运单价：</label><input type="text" name='trainTrainPrice' maxlength="30" readonly="readonly"><span>元</span></div>
							</form>
						</div>
						<hr>
						<!-- 备注信息 -->
						<div class="remarks_info">
							<h5>备注信息</h5>
							<div name='trainRemark'></div>
						</div>
						<hr>
						<!-- 删除信息 -->
						<div class="del-info">
							<h5>删除信息</h5>
							<ul class="clearfix pad">
								<li>
									<label>删除人：</label>
									<span name='delUser'></span>
								</li>
								<li>
									<label>删除时间：</label>
									<span name='delDate'></span>
								</li>
							</ul>
							<div class="pad">
								<label>删除原因：</label>
								<span name='delReason'></span>
							</div>
							<hr>
						</div>
						<!-- 历史修改记录 -->
						<!-- <div class="history_modify">
							<h5>历史修改记录</h5>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方:合肥深合软件有限公司的“运输单价”为1000元，原“运输单价”为2000元</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改送达短驳承运方:合肥深合软件有限公司的“扣损比率”为1‰，原“扣损比率”为21‰</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方为“合肥深合软件有限公司”，原承运方为“新疆秦龙矿业有限公司”</div>
						</div> -->
						<!--按钮-->
						<div class="row clearfix hidebtn">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="updateProject(3,'trainModal')">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!--接取+火运项目详情模态框-->
		<div class="modal fade  detailModal modifyModal" tabindex="-1" role="dialog" data-backdrop="static" id="receveAndTrainModal">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content  ">
					<div class="modal-header">
						<h4 class="modal-title">项目详情（接取+火运）</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<!-- 项目信息 -->
						<!-- 项目信息 -->
						<div class="project_info">
							<h5>项目信息</h5>
							<ul class="clearfix">
								<li>
									<label>分支机构：</label>
									<span name="branchGroupName"></span>
								</li>
								<li>
									<label>项目类型：</label>
									<span name="projectType"></span>
								</li>
								<li>
									<label>联运模式：</label>
									<span name="transportType"></span>
								</li>
								<li>
									<label>委托方：</label>
									<span name="principal"></span>
								</li>
								<li class="history_detail">
									<label>完成时间：</label>
									<span name="finishDate"></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 货物信息 -->
						<div class="cargo_info">
							<h5>货物信息</h5>
							<ul class="clearfix">
								<li>
									<label>货物品名：</label>
									<span name="cargoName"></span>
								</li>
								<li>
									<label>货物规格：</label>
									<span name="cargoSpecifications"></span>
								</li>
								<li>
									<label>货物单价：</label>
									<span name="cargoPrice"></span>
								</li>
								<li>
									<label>计价单位：</label>
									<span name="valuationUnitName"></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 收发货企业 -->
						<div class="detail_info cut_apart clearfix">
							<!--   发货企业  -->
							<div class="half splitters">
								<h5>发货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="sendCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="sendCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="sendCargoCompanyAddress"></span>
								</div>

							</div>
							<!--   收货企业    -->
							<div class="half">
								<h5>收货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="receiveCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="receiveCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="receiveCargoCompanyAddress"></span>
								</div>
							</div>
						</div>
						<hr>
						<!-- 接取短驳  -->
						<div class="receve_truck cut_apart clearfix">
							<h5>接取短驳</h5>
							<div class="half splitters">
								<ul class="clearfix pad">
									<li class="long">
										<label>发货单位：</label>
										<span id="" name="sendCargoUnitName"></span>
									</li>
									<li>
										<label>&emsp;取货地：</label>
										<span id="" name="sendCargoAreaCode"></span>
									</li>
								</ul>
								<div class="pad">
									<label>取货地址：</label>
									<span id="" name="sendCargoAddress"></span>
								</div>
							</div>
						<div class="half ">
								<ul class="clearfix">
									<li class="long">
										<label>到达中心站：</label>
										<span name="receiveCenterCargoSiteName"></span>
									</li>
									<li>
										<label>到达站点：</label>
										<span name="receiveCargoSite"></span>
									</li>
								</ul>
								<ul class="clearfix">
									<li class="long">
										<label>卸货货场：</label>
										<span name="receiveCargoSiteFreightYard"></span>
									</li>
									<li>
										<label>到达地址：</label>
										<span name="receiveCargoSiteAddress"></span>
									</li>
								</ul>
							</div>
							<!--   短驳承运方  -->
							<div class="whole modify-whole">
								<p>短驳承运方</p>
								<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
								<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
								<form class="form-inline ">
									<div class="form-group ">
										<label>短驳承运方式：</label>
										<select name="shortBargeCarrierMode1" onchange="updateShortBarge(this)">
											<option value="0">平台</option>
											<option value="1">自选</option>
										</select>
									</div>
								</form>
								<div name='shortBargeCarrierName1'></div>
							</div>
						</div>
						<hr>
						<!-- 火运干线  -->
						<div class="train_line cut_apart clearfix">
							<h5>火运干线</h5>
							<!-- 起运中心站 -->
							<!-- 起运中心站 -->
							<div class="half splitters">
								<ul class="clearfix">
									<li class="long">
										<label>起运中心站：</label>
										<span name='beginCenterSiteName'></span>
									</li>
									<li>
										<label>始发站点：</label>
										<span name='beginSiteName'></span>
									</li>
								</ul>
								<ul class="clearfix">
									<li class="long">
										<label>始发货场：</label>
										<span name='beginSiteFreightYardName'></span>
									</li>
									<li>
										<label>始发地：</label>
										<span name='beginAddress'></span>
									</li>
								</ul>
							</div>
							<!-- 到达中心站 -->
							<div class="half ">
								<ul class="clearfix">
									<li class="long">
										<label>到达中心站：</label>
										<span name='endCenterSiteName'></span>
									</li>
									<li>
										<label>到达站点：</label>
										<span name='endSiteName'></span>
									</li>
								</ul>
								<ul class="clearfix">
									<li class="long">
										<label>到达货场：</label>
										<span name='endSiteFreightYardName'></span>
									</li>
									<li>
										<label>到达地：</label>
										<span name='endAddress'></span>
									</li>
								</ul>
							</div>
							<!--  干线费用   -->
							<!--修改div-->
							<div class="whole modify-whole">
								<p>干线费用</p>
								<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
								<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
								<form class="form-inline" name="trainCost">
									<input type="hidden" name="id" />
									<div>
										<div class="form-group">
											<label>&emsp;运费核计：</label>
											<input type="text" maxLength="10" onkeyup="add(this)" name='freight'><span>元</span>
										</div>
										<div class="form-group">
											<label>加固材料费：</label>
											<input type="text" maxLength="10" onkeyup="add(this)" name='materialCost'><span>元</span>
										</div>
										<div class="form-group">
											<label>篷布使用费：</label>
											<input type="text" maxLength="10" onkeyup="add(this)" name='tarpaulinCost'><span>元</span>
										</div>
										<div class="form-group">
											<label>发站装卸费：</label>
											<input type="text" maxLength="10" onkeyup="add(this)" name='beginStevedoringCost'><span>元</span>
										</div>
										<div class="form-group">
											<label>到站装卸费：</label>
											<input type="text" maxLength="10" onkeyup="add(this)" name='endStevedoringCost'><span>元</span>
										</div>
										<div class="form-group">
											<label>&emsp;运费合计：</label>
											<input type="text" name="freightSum" class="inputbg" readonly="readonly" maxLength="30"><span>元</span>
										</div>
										<input type="hidden" name="trainPrice" />
										<input type="hidden" name="pickUpPrice" />
										<input type="hidden" name="transportType" value="4" />
										<!--短驳承运方式-->
										<input type="hidden" name="shortBargeCarrierMode" value="4" />
										<!--短驳承运方-->
										<input type="hidden" name="shortBargeCarrierName" value="4" />
									</div>
								</form>
							</div>
						</div>
						<hr>
						<div class="client_cost whole modify-whole">
							<h5>委托方费用</h5>
							<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
							<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
							<form class="form-inline pad">
								<div class="form-group"><label>接取单价：</label><input type="text" name='recevePickUpPrice' maxlength="30" readonly="readonly"><span>元</span></div>
								<div class="form-group"><label>火运单价：</label><input type="text" name='trainTrainPrice' maxlength="30" readonly="readonly"><span>元</span></div>
							</form>
						</div>
						<hr>
						<!-- 备注信息 -->
						<div class="remarks_info">
							<h5>备注信息</h5>
							<div name='receveAndTrainRemark'></div>
						</div>
						<hr>
						<!-- 删除信息 -->
						<div class="del-info">
							<h5>删除信息</h5>
							<ul class="clearfix pad">
								<li>
									<label>删除人：</label>
									<span name='delUser'></span>
								</li>
								<li>
									<label>删除时间：</label>
									<span name='delDate'></span>
								</li>
							</ul>
							<div class="pad">
								<label>删除原因：</label>
								<span name='delReason'></span>
							</div>
							<hr>
						</div>
						<!-- 历史修改记录 -->
						<!-- <div class="history_modify">
							<h5>历史修改记录</h5>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方:合肥深合软件有限公司的“运输单价”为1000元，原“运输单价”为2000元</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改送达短驳承运方:合肥深合软件有限公司的“扣损比率”为1‰，原“扣损比率”为21‰</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方为“合肥深合软件有限公司”，原承运方为“新疆秦龙矿业有限公司”</div>
						</div> -->
						<!--按钮-->
						<div class="row clearfix hidebtn">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="updateProject(4,'receveAndTrainModal')">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!--火运+送达项目详情模态框-->
		<div class="modal fade  detailModal modifyModal" tabindex="-1" role="dialog" data-backdrop="static" id="trainAndSendModal">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content  ">
					<div class="modal-header">
						<h4 class="modal-title">项目详情（火运+送达）</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<!-- 项目信息 -->
						<!-- 项目信息 -->
						<div class="project_info">
							<h5>项目信息</h5>
							<ul class="clearfix">
								<li>
									<label>分支机构：</label>
									<span name="branchGroupName"></span>
								</li>
								<li>
									<label>项目类型：</label>
									<span name="projectType"></span>
								</li>
								<li>
									<label>联运模式：</label>
									<span name="transportType"></span>
								</li>
								<li>
									<label>委托方：</label>
									<span name="principal"></span>
								</li>
								<li class="history_detail">
									<label>完成时间：</label>
									<span>2017.12.12</span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 货物信息 -->
						<div class="cargo_info">
							<h5>货物信息</h5>
							<ul class="clearfix">
								<li>
									<label>货物品名：</label>
									<span name="cargoName"></span>
								</li>
								<li>
									<label>货物规格：</label>
									<span name="cargoSpecifications"></span>
								</li>
								<li>
									<label>货物单价：</label>
									<span name="cargoPrice"></span>
								</li>
								<li>
									<label>计价单位：</label>
									<span name="valuationUnitName"></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 收发货企业 -->
						<div class="detail_info cut_apart clearfix">
							<!--   发货企业  -->
							<div class="half splitters">
								<h5>发货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="sendCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="sendCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="sendCargoCompanyAddress"></span>
								</div>

							</div>
							<!--   收货企业    -->
							<div class="half">
								<h5>收货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="receiveCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="receiveCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="receiveCargoCompanyAddress"></span>
								</div>
							</div>
						</div>
						<hr>
						<!-- 火运干线  -->
						<div class="train_line cut_apart clearfix">
							<h5>火运干线</h5>
							<!-- 起运中心站 -->
							<!-- 起运中心站 -->
							<div class="half splitters">
								<ul class="clearfix">
									<li class="long">
										<label>起运中心站：</label>
										<span name='beginCenterSiteName'></span>
									</li>
									<li>
										<label>始发站点：</label>
										<span name='beginSiteName'></span>
									</li>
								</ul>
								<ul class="clearfix">
									<li class="long">
										<label>始发货场：</label>
										<span name='beginSiteFreightYardName'></span>
									</li>
									<li>
										<label>始发地：</label>
										<span name='beginAddress'></span>
									</li>
								</ul>
							</div>
							<!-- 到达中心站 -->
							<div class="half ">
								<ul class="clearfix">
									<li class="long">
										<label>到达中心站：</label>
										<span name='endCenterSiteName'></span>
									</li>
									<li>
										<label>到达站点：</label>
										<span name='endSiteName'></span>
									</li>
								</ul>
								<ul class="clearfix">
									<li class="long">
										<label>到达货场：</label>
										<span name='endSiteFreightYardName'></span>
									</li>
									<li>
										<label>到达地：</label>
										<span name='endAddress'></span>
									</li>
								</ul>
							</div>
							<!--  干线费用   -->
							<!--修改div-->
							<div class="whole modify-whole">
								<p>干线费用</p>
								<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
								<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
								<form class="form-inline" name="trainCost">
									<input type="hidden" name="id" />
									<div>
										<div class="form-group">
											<label>&emsp;运费核计：</label>
											<input type="text" maxLength="10" onkeyup="add(this)" name='freight' /><span>元</span>
										</div>
										<div class="form-group">
											<label>加固材料费：</label>
											<input type="text" maxLength="10" onkeyup="add(this)" name='materialCost' /><span>元</span>
										</div>
										<div class="form-group">
											<label>篷布使用费：</label>
											<input type="text" maxLength="10" onkeyup="add(this)" name='tarpaulinCost' /><span>元</span>
										</div>
										<div class="form-group">
											<label>发站装卸费：</label>
											<input type="text" maxLength="10" onkeyup="add(this)" name='beginStevedoringCost' /><span>元</span>
										</div>
										<div class="form-group">
											<label>到站装卸费：</label>
											<input type="text" maxLength="10" onkeyup="add(this)" name='endStevedoringCost' /><span>元</span>
										</div>
										<div class="form-group">
											<label>&emsp;运费合计：</label>
											<input type="text" name="freightSum" class="inputbg" readonly="readonly" maxLength="30" /><span>元</span>
										</div>
										<input type="hidden" name="trainPrice" />
										<input type="hidden" name="arrivePrice" />
										<input type="hidden" name="transportType" value="5" />
										<!--短驳承运方式-->
										<input type="hidden" name="sendShortBargeCarrierMode" value="4" />
										<!--短驳承运方-->
										<input type="hidden" name="sendShortBargeCarrierName" value="4" />
									</div>
								</form>
							</div>
						</div>
						<hr>
						<!-- 送达短驳 -->
						<div class="send_truck cut_apart clearfix">
							<h5>送达短驳</h5>
							<div class="half ">
								<ul class="clearfix">
									<li class="long">
										<label>发货中心站：</label>
										<span name="forwardingCenterSiteName"></span>
									</li>
									<li>
										<label>取货站点：</label>
										<span name="forwardingSiteName"></span>
									</li>
								</ul>
								<ul class="clearfix">
									<li class="long">
										<label>取货货场：</label>
										<span name="forwardingSiteFreightYard"></span>
									</li>
									<li>
										<label>取货地址：</label>
										<span name="forwardingSiteAddress"></span>
									</li>
								</ul>
							</div>
							<div class="half">

								<ul class="clearfix">
									<li class="long">
										<label>收货单位：</label>
										<span name="receivingDepartmentName"></span>
									</li>
									<li>
										<label>运抵地：</label>
										<span name="receivingDepartmentAreaCode"></span>
									</li>
								</ul>
								<div>
									<label>运抵地址：</label>
									<span name="receivingDepartmentAddress"></span>
								</div>
							</div>

							<!--   短驳承运方  -->
							<div class="whole modify-whole">
								<p>短驳承运方</p>
								<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
								<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
								<form class="form-inline ">
									<div class="form-group ">
										<label>短驳承运方式：</label>
										<select name="shortBargeCarrierMode2" onchange="updateShortBarge(this)">
											<option value="0">平台</option>
											<option value="1">自选</option>
										</select>
									</div>
								</form>
								<div name='shortBargeCarrierName2'></div>
							</div>
						</div>

						<hr>
						<!-- 委托方费用 -->
						<div class="client_cost whole modify-whole">
							<h5>委托方费用</h5>
							<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
							<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
							<form class="form-inline pad">
								<div class="form-group"><label>火运单价：</label><input type="text" name='trainTrainPrice' maxlength="30" readonly="readonly"><span>元</span></div>
								<div class="form-group"><label>送达单价：</label><input type="text" name='sendArrivePrice' maxlength="30" readonly="readonly"><span>元</span></div>
							</form>
						</div>
						<hr>
						<!-- 备注信息 -->
						<div class="remarks_info">
							<h5>备注信息</h5>
							<div name="remark"></div>
						</div>
						<hr>
						<!-- 删除信息 -->
						<div class="del-info">
							<h5>删除信息</h5>
							<ul class="clearfix pad">
								<li>
									<label>删除人：</label>
									<span name='delUser'></span>
								</li>
								<li>
									<label>删除时间：</label>
									<span name='delDate'></span>
								</li>
							</ul>
							<div class="pad">
								<label>删除原因：</label>
								<span name='delReason'></span>
							</div>
							<hr>
						</div>
						<!-- 历史修改记录 -->
						<!-- <div class="history_modify">
							<h5>历史修改记录</h5>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方:合肥深合软件有限公司的“运输单价”为1000元，原“运输单价”为2000元</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改送达短驳承运方:合肥深合软件有限公司的“扣损比率”为1‰，原“扣损比率”为21‰</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方为“合肥深合软件有限公司”，原承运方为“新疆秦龙矿业有限公司”</div>
						</div> -->
						<!--按钮-->
						<div class="row clearfix hidebtn">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="updateProject(5,'trainAndSendModal')">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!--接取+送达项目详情模态框-->
		<div class="modal fade  detailModal modifyModal" tabindex="-1" role="dialog" data-backdrop="static" id="receveAndSendModal">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content  ">
					<div class="modal-header">
						<h4 class="modal-title">项目详情</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<!-- 项目信息 -->
						<!-- 项目信息 -->
						<div class="project_info">
							<h5>项目信息</h5>
							<ul class="clearfix">
								<li>
									<label>分支机构：</label>
									<span name="branchGroupName"></span>
								</li>
								<li>
									<label>项目类型：</label>
									<span name="projectType"></span>
								</li>
								<li>
									<label>联运模式：</label>
									<span name="transportType"></span>
								</li>
								<li>
									<label>委托方：</label>
									<span name="principal"></span>
								</li>
								<li class="history_detail">
									<label>完成时间：</label>
									<span name="finishDate"></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 货物信息 -->
						<div class="cargo_info">
							<h5>货物信息</h5>
							<ul class="clearfix">
								<li>
									<label>货物品名：</label>
									<span name="cargoName"></span>
								</li>
								<li>
									<label>货物规格：</label>
									<span name="cargoSpecifications"></span>
								</li>
								<li>
									<label>货物单价：</label>
									<span name="cargoPrice"></span>
								</li>
								<li>
									<label>计价单位：</label>
									<span name="valuationUnitName"></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 收发货企业 -->
						<div class="detail_info cut_apart clearfix">
							<!--   发货企业  -->
							<div class="half splitters">
								<h5>发货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="sendCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="sendCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="sendCargoCompanyAddress"></span>
								</div>

							</div>
							<!--   收货企业    -->
							<div class="half">
								<h5>收货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="receiveCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="receiveCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="receiveCargoCompanyAddress"></span>
								</div>
							</div>
						</div>
						<hr>
						<!-- 接取短驳  -->
						<div class="receve_truck cut_apart clearfix">
							<h5>接取短驳</h5>
							<div class="half splitters">
								<ul class="clearfix pad">
									<li class="long">
										<label>发货单位：</label>
										<span id="" name="sendCargoUnitName"></span>
									</li>
									<li>
										<label>&emsp;取货地：</label>
										<span id="" name="sendCargoAreaCode"></span>
									</li>
								</ul>
								<div class="pad">
									<label>取货地址：</label>
									<span id="" name="sendCargoAddress"></span>
								</div>
							</div>
							<div class="half ">
								<ul class="clearfix">
									<li class="long">
										<label>到达中心站：</label>
										<span name="receiveCenterCargoSiteName"></span>
									</li>
									<li>
										<label>到达站点：</label>
										<span name="receiveCargoSite"></span>
									</li>
								</ul>
								<ul class="clearfix">
									<li class="long">
										<label>卸货货场：</label>
										<span name="receiveCargoSiteFreightYard"></span>
									</li>
									<li>
										<label>到达地址：</label>
										<span name="receiveCargoSiteAddress"></span>
									</li>
								</ul>
							</div>
							<!--   短驳承运方  -->
							<div class="whole modify-whole">
								<p>短驳承运方</p>
								<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
								<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
								<form class="form-inline ">
									<div class="form-group ">
										<label>短驳承运方式：</label>
										<select name="shortBargeCarrierMode1" onchange="updateShortBarge(this)">
											<option value="0">平台</option>
											<option value="1">自选</option>
										</select>
									</div>
								</form>
								<div name='shortBargeCarrierName1'></div>
							</div>
						</div>
						<form class="form-inline" name="trainCost" hidden="hidden">
							<input type="hidden" name="id" />
							<input type="hidden" name="arrivePrice" />
							<input type="hidden" name="pickUpPrice" />
							<input type="hidden" name="transportType" value="7" />
							<!--短驳承运方式-->
							<input type="hidden" name="shortBargeCarrierMode" />
							<!--短驳承运方-->
							<input type="hidden" name="shortBargeCarrierName" />

							<!--短驳承运方式-->
							<input type="hidden" name="sendShortBargeCarrierMode" />
							<!--短驳承运方-->
							<input type="hidden" name="sendShortBargeCarrierName" />
						</form>

						<hr>
						<!-- 送达短驳 -->
						<div class="send_truck cut_apart clearfix">
							<h5>送达短驳</h5>
							<div class="half ">
								<ul class="clearfix">
									<li class="long">
										<label>发货中心站：</label>
										<span name="forwardingCenterSiteName"></span>
									</li>
									<li>
										<label>取货站点：</label>
										<span name="forwardingSiteName"></span>
									</li>
								</ul>
								<ul class="clearfix">
									<li class="long">
										<label>取货货场：</label>
										<span name="forwardingSiteFreightYard"></span>
									</li>
									<li>
										<label>取货地址：</label>
										<span name="forwardingSiteAddress"></span>
									</li>
								</ul>
							</div>
							<div class="half">

								<ul class="clearfix">
									<li class="long">
										<label>收货单位：</label>
										<span name="receivingDepartmentName"></span>
									</li>
									<li>
										<label>运抵地：</label>
										<span name="receivingDepartmentAreaCode"></span>
									</li>
								</ul>
								<div>
									<label>运抵地址：</label>
									<span name="receivingDepartmentAddress"></span>
								</div>
							</div>

							<!--   短驳承运方  -->
							<div class="whole modify-whole">
								<p>短驳承运方</p>
								<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
								<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
								<form class="form-inline ">
									<div class="form-group ">
										<label>短驳承运方式：</label>
										<select name="shortBargeCarrierMode2" onchange="updateShortBarge(this)">
											<option value="0">平台</option>
											<option value="1">自选</option>
										</select>
									</div>
								</form>
								<div name='shortBargeCarrierName2'></div>
							</div>
						</div>
						<!-- 委托方费用 -->
						<div class="client_cost whole modify-whole">
							<h5>委托方费用</h5>
							<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
							<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
							<form class="form-inline pad">
								<div class="form-group"><label>接取单价：</label><input type="text" name='recevePickUpPrice' maxlength="30" readonly="readonly"><span>元</span></div>
								<div class="form-group"><label>送达单价：</label><input type="text" name='sendArrivePrice' maxlength="30" readonly="readonly"><span>元</span></div>
							</form>
						</div>
						<hr>
						<!-- 备注信息 -->
						<div class="remarks_info">
							<h5>备注信息</h5>
							<div class="pad" name="receveAndSendRemark"></div>
						</div>
						<hr>
						<!-- 删除信息 -->
						<div class="del-info">
							<h5>删除信息</h5>
							<ul class="clearfix pad">
								<li>
									<label>删除人：</label>
									<span name='delUser'></span>
								</li>
								<li>
									<label>删除时间：</label>
									<span name='delDate'></span>
								</li>
							</ul>
							<div class="pad">
								<label>删除原因：</label>
								<span name='delReason'></span>
							</div>
							<hr>
						</div>
						<!-- 历史修改记录 -->
						<!-- <div class="history_modify">
							<h5>历史修改记录</h5>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方:合肥深合软件有限公司的“运输单价”为1000元，原“运输单价”为2000元</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改送达短驳承运方:合肥深合软件有限公司的“扣损比率”为1‰，原“扣损比率”为21‰</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方为“合肥深合软件有限公司”，原承运方为“新疆秦龙矿业有限公司”</div>
						</div> -->
						<!--按钮-->
						<div class="row clearfix hidebtn">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="updateProject(7,'receveAndSendModal')">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!--联运项目详情模态框-->
		<div class="modal fade  detailModal modifyModal" tabindex="-1" role="dialog" data-backdrop="static" id="unionModal">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content  ">
					<div class="modal-header">
						<h4 class="modal-title">项目详情（联运）</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<!-- 项目信息 -->
						<div class="project_info">
							<h5>项目信息</h5>
							<ul class="clearfix">
								<li>
									<label>分支机构：</label>
									<span name="branchGroupName"></span>
								</li>
								<li>
									<label>项目类型：</label>
									<span name="projectType"></span>
								</li>
								<li>
									<label>联运模式：</label>
									<span name="transportType"></span>
								</li>
								<li>
									<label>委托方：</label>
									<span name="principal"></span>
								</li>
								<li class="history_detail">
									<label>完成时间：</label>
									<span name="finishDate"></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 货物信息 -->
						<div class="cargo_info">
							<h5>货物信息</h5>
							<ul class="clearfix">
								<li>
									<label>货物品名：</label>
									<span name="cargoName"></span>
								</li>
								<li>
									<label>货物规格：</label>
									<span name="cargoSpecifications"></span>
								</li>
								<li>
									<label>货物单价：</label>
									<span name="cargoPrice"></span>
								</li>
								<li>
									<label>计价单位：</label>
									<span name="valuationUnitName"></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 收发货企业 -->
						<div class="detail_info cut_apart clearfix">
							<!--   发货企业  -->
							<div class="half splitters">
								<h5>发货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="sendCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="sendCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="sendCargoCompanyAddress"></span>
								</div>

							</div>
							<!--   收货企业    -->
							<div class="half">
								<h5>收货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="receiveCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="receiveCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="receiveCargoCompanyAddress"></span>
								</div>

							</div>
						</div>
						<hr>
						<!-- 接取短驳  -->
						<div class="receve_truck cut_apart clearfix">
							<h5>接取短驳</h5>
							<div class="half splitters">
								<ul class="clearfix pad">
									<li class="long">
										<label>发货单位：</label>
										<span id="" name="sendCargoUnitName"></span>
									</li>
									<li>
										<label>&emsp;取货地：</label>
										<span id="" name="sendCargoAreaCode"></span>
									</li>
								</ul>
								<div class="pad">
									<label>取货地址：</label>
									<span id="" name="sendCargoAddress"></span>
								</div>
							</div>
							<div class="half ">
								<ul class="clearfix">
									<li class="long">
										<label>到达中心站：</label>
										<span name="receiveCenterCargoSiteName"></span>
									</li>
									<li>
										<label>到达站点：</label>
										<span name="receiveCargoSite"></span>
									</li>
								</ul>
								<ul class="clearfix">
									<li class="long">
										<label>卸货货场：</label>
										<span name="receiveCargoSiteFreightYard"></span>
									</li>
									<li>
										<label>到达地址：</label>
										<span name="receiveCargoSiteAddress"></span>
									</li>
								</ul>
							</div>
							<!--   短驳承运方  -->
							<div class="whole modify-whole">
								<p>短驳承运方</p>
								<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
								<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
								<form class="form-inline ">
									<div class="form-group ">
										<label>短驳承运方式：</label>
										<select name="shortBargeCarrierMode1" onchange="updateShortBarge(this)">
											<option value="0">平台</option>
											<option value="1">自选</option>
										</select>
									</div>
								</form>
								<div name='shortBargeCarrierName1'></div>
							</div>
						</div>
						<hr>
						<!-- 火运干线  -->
						<div class="train_line cut_apart clearfix">
							<h5>火运干线</h5>
							<!-- 起运中心站 -->
							<!-- 起运中心站 -->
							<div class="half splitters">
								<ul class="clearfix">
									<li class="long">
										<label>起运中心站：</label>
										<span name='beginCenterSiteName'></span>
									</li>
									<li>
										<label>始发站点：</label>
										<span name='beginSiteName'></span>
									</li>
								</ul>
								<ul class="clearfix">
									<li class="long">
										<label>始发货场：</label>
										<span name='beginSiteFreightYardName'></span>
									</li>
									<li>
										<label>始发地：</label>
										<span name='beginAddress'></span>
									</li>
								</ul>
							</div>
							<!-- 到达中心站 -->
							<div class="half ">
								<ul class="clearfix">
									<li class="long">
										<label>到达中心站：</label>
										<span name='endCenterSiteName'></span>
									</li>
									<li>
										<label>到达站点：</label>
										<span name='endSiteName'></span>
									</li>
								</ul>
								<ul class="clearfix">
									<li class="long">
										<label>到达货场：</label>
										<span name='endSiteFreightYardName'></span>
									</li>
									<li>
										<label>到达地：</label>
										<span name='endAddress'></span>
									</li>
								</ul>
							</div>
							<!--  干线费用   -->
							<!--修改div-->
							<div class="whole modify-whole">
								<p>干线费用</p>
								<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
								<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
								<form class="form-inline" name="trainCost">
									<div>
										<input type="hidden" name="id" />
										<div class="form-group">
											<label>&emsp;运费核计：</label>
											<input type="text" maxLength="10" onkeyup="add(this)" name='freight'><span>元</span>
										</div>
										<div class="form-group">
											<label>加固材料费：</label>
											<input type="text" maxLength="10" onkeyup="add(this)" name='materialCost'><span>元</span>
										</div>
										<div class="form-group">
											<label>篷布使用费：</label>
											<input type="text" maxLength="10" onkeyup="add(this)" name='tarpaulinCost'><span>元</span>
										</div>
										<div class="form-group">
											<label>发站装卸费：</label>
											<input type="text" maxLength="10" onkeyup="add(this)" name='beginStevedoringCost'><span>元</span>
										</div>
										<div class="form-group">
											<label>到站装卸费：</label>
											<input type="text" maxLength="10" onkeyup="add(this)" name='endStevedoringCost'><span>元</span>
										</div>
										<div class="form-group">
											<label>&emsp;运费合计：</label>
											<input type="text" name="freightSum" class="inputbg" readonly="readonly" maxLength="30"><span>元</span>
										</div>
										<input type="hidden" name="trainPrice" />
										<input type="hidden" name="pickUpPrice" />
										<input type="hidden" name="arrivePrice" />
										<input type="hidden" name="transportType" value="6" />
										<!--短驳承运方式-->
										<input type="hidden" name="shortBargeCarrierMode" />
										<!--短驳承运方-->
										<input type="hidden" name="shortBargeCarrierName" />
										<!--短驳承运方式-->
										<input type="hidden" name="sendShortBargeCarrierMode" />
										<!--短驳承运方-->
										<input type="hidden" name="sendShortBargeCarrierName" />
									</div>
								</form>
							</div>
						</div>
						<hr>
						<!-- 送达短驳 -->
						<div class="send_truck cut_apart clearfix">
							<h5>送达短驳</h5>
						<div class="half ">
								<ul class="clearfix">
									<li class="long">
										<label>发货中心站：</label>
										<span name="forwardingCenterSiteName"></span>
									</li>
									<li>
										<label>取货站点：</label>
										<span name="forwardingSiteName"></span>
									</li>
								</ul>
								<ul class="clearfix">
									<li class="long">
										<label>取货货场：</label>
										<span name="forwardingSiteFreightYard"></span>
									</li>
									<li>
										<label>取货地址：</label>
										<span name="forwardingSiteAddress"></span>
									</li>
								</ul>
							</div>
							<div class="half">

								<ul class="clearfix">
									<li class="long">
										<label>收货单位：</label>
										<span name="receivingDepartmentName"></span>
									</li>
									<li>
										<label>运抵地：</label>
										<span name="receivingDepartmentAreaCode"></span>
									</li>
								</ul>
								<div>
									<label>运抵地址：</label>
									<span name="receivingDepartmentAddress"></span>
								</div>

							</div>
							<!--   短驳承运方  -->
							<div class="whole modify-whole">
								<p>短驳承运方</p>
								<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
								<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
								<form class="form-inline ">
									<div class="form-group ">
										<label>短驳承运方式：</label>
										<select name="shortBargeCarrierMode2" onchange="updateShortBarge(this)">
											<option value="0">平台</option>
											<option value="1">自选</option>
										</select>
									</div>
								</form>
								<div name='shortBargeCarrierName2'></div>
							</div>
						</div>
						<hr>
						<!-- 委托方费用 -->
						<div class="client_cost whole modify-whole">
							<h5>委托方费用</h5>
							<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
							<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
							<form class="form-inline pad">
								<div class="form-group"><label>接取单价：</label><input type="text" name='recevePickUpPrice' maxlength="30" readonly="readonly"><span>元</span></div>
								<div class="form-group"><label>火运单价：</label><input type="text" name='trainTrainPrice' maxlength="30" readonly="readonly"><span>元</span></div>
								<div class="form-group"><label>送达单价：</label><input type="text" name='sendArrivePrice' maxlength="30" readonly="readonly"><span>元</span></div>
							</form>
						</div>
						<hr>
						<!-- 备注信息 -->
						<div class="remarks_info">
							<h5>备注信息</h5>
							<div name='remark'></div>
						</div>
						<hr>
						<!-- 删除信息 -->
						<div class="del-info">
							<h5>删除信息</h5>
							<ul class="clearfix pad">
								<li>
									<label>删除人：</label>
									<span name='delUser'></span>
								</li>
								<li>
									<label>删除时间：</label>
									<span name='delDate'></span>
								</li>
							</ul>
							<div class="pad">
								<label>删除原因：</label>
								<span name='delReason'></span>
							</div>
							<hr>
						</div>
						<!-- 历史修改记录 -->
						<!-- <div class="history_modify">
							<h5>历史修改记录</h5>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方:合肥深合软件有限公司的“运输单价”为1000元，原“运输单价”为2000元</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改送达短驳承运方:合肥深合软件有限公司的“扣损比率”为1‰，原“扣损比率”为21‰</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方为“合肥深合软件有限公司”，原承运方为“新疆秦龙矿业有限公司”</div>
						</div> -->
						<!--按钮-->
						<div class="row clearfix hidebtn">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="updateProject(6,'unionModal')">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>


		<!-- 切换       新建项目模态框 -->
		<div class="modal fade  creatProjectModal" tabindex="-1" role="dialog" data-backdrop="static" id="creatProjectModal">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content creat_project_a ">
					<div class="modal-header">
						<h4 class="modal-title">新建项目</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="addUnion">
							<!--<form id="createId">-->
							<div class="project_info">
								<div class="info_title">
									<h5>项目信息<i class='requireds'>*</i></h5>
									<div class="form-inline">
										<div class="form-group">
											<label>分支机构：</label>
											<select name="branchGroupId" onchange='changeAndSetValue(this)'><option></option></select>
											<input type="hidden" name="branchGroupName" />
										</div>
										<div class="form-group">
											<label>项目类型：</label>
											<select name="projectType">
												<option></option>
												<option value="0">集装箱</option>
												<option value="1">散装</option>
											</select>
										</div>
										<div class="form-group">
											<label>联运模式：</label>
											<select id="selectField" name="transportType">
												<option></option>
												<option value="0">汽运</option>
												<option value="1">接取</option>
												<option value="2">送达</option>
												<option value="3">火运</option>
												<option value="4">接取+火运</option>
												<option value="5">火运+送达</option>
												<option value="7">接取+送达</option>
												<option value="6">联运</option>
											</select>
										</div>
										<div class="form-group">
											<label>&emsp;委托方：</label>
											<select name="principal">
												<option></option>
												<option value="0">收货企业</option>
												<option value="1">发货企业</option>
											</select>
										</div>
									</div>
								</div>
							</div>
							<hr>
							<!--接取或汽运的短驳承运方-->
							<input type="hidden" name="shortBargeCarrierName" />
							<!--送达短驳承运方-->
							<input type="hidden" name="sendShortBargeCarrierName" />

							<div class="col union">
								<!-- 货物信息 -->
								<div class="cargo_info">
									<h5>货物信息 <i class='requireds'>*</i></h5>
									<div class="form-inline">
										<div class="form-group">
											<label>货物品名：</label>
											<select name="cargoId" onchange="getCargo('addUnion')"><option></option></select>
											<input type="hidden" name="cargoName" />
										</div>
										<div class="form-group">
											<label>货物规格：</label>
											<select name="cargoSpecifications"><option></option></select>
										</div>
										<div class="form-group">
											<label>货物单价：</label>
											<input type="text" name="cargoPrice" maxLength="30"><span>元</span>
										</div>
										<div class="form-group">
											<label>计价单位：</label>
											<select name="valuationUnitName">
												<option></option>
												<option value="1">吨</option>
												<option value="0">件</option>
											</select>
										</div>
									</div>
								</div>
								<hr>
								<!-- 收发货企业 -->
								<div class="detail_info cut_apart clearfix">
									<!--   发货企业  -->
									<div class="half splitters">
										<h5>发货企业 <i class='requireds'>*</i></h5>
										<div class="form-inline">
											<div class="form-group">
												<label>企业名称：</label>
												<select name="sendCargoCompanyId"><option></option></select>
												<input type="hidden" name="sendCargoCompanyName" />
											</div>
											<div class="form-group">
												<label>联系方式：</label>
												<input type="text" name="sendCargoCompanyphone" unselectable="on" readonly class="inputbg">
											</div>
											<div class="form-group">
												<label class="sm-tag">地址：</label>
												<input type="text" name="sendCargoCompanyAddress" unselectable="on" readonly class="addr_info inputbg">
											</div>
										</div>
									</div>
									<!--   收货企业    -->
									<div class="half">
										<h5>收货企业 <i class='requireds'>*</i></h5>
										<div class="form-inline">
											<div class="form-group">
												<label>企业名称：</label>
												<select name="receiveCargoCompanyId"><option></option></select>
												<input type="hidden" name="receiveCargoCompanyName" />
											</div>
											<div class="form-group">
												<label>联系方式：</label>
												<input type="text" unselectable="on" name="receiveCargoCompanyAddress" readonly class="inputbg">
											</div>
											<div class="form-group">
												<label class="sm-tag">地址：</label>
												<input type="text" unselectable="on" name="receiveCargoCompanyAddress" readonly class="addr_info inputbg">
											</div>
										</div>
									</div>
								</div>
								<hr>
								<!-- 接取短驳  -->
								<div class="receve_truck cut_apart clearfix" id='shortBargeTitle'>
									<h5>接取短驳 <i class='requireds'>*</i></h5>
									<div class="half splitters">
										<div class="form-inline">
											<div class="form-group">
												<label class="sm-tag">发货单位：</label>
												<select name="sendCargoUnitId"><option></option></select>
												<input type="hidden" name="sendCargoUnitName" />
											</div>
											<div class="form-group">
												<label class="sm-tag">取货地：</label>
												<input class="inputbg" type="text" unselectable="on" readonly>
											</div>
											<div class="form-group">
												<label class="sm-tag">取货地址：</label>
												<input type="text" class="addr_info inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>
									<div class="half" id="receiveDiv">
										<div class="form-inline ">
											<div class="form-group">
												<label class="sm-tag">到达中心站：</label>
												<select name="receiveCenterCargoSiteId"><option></option></select>
											</div>
											<div class="form-group">
												<label class="sm-tag">到达站点：</label>
												<select name="receiveCargoSiteId"><option></option></select>
											</div>
											<div class="form-group">
												<label class="sm-tag">卸货货场：</label>
													<select name="receiveCargoSiteFreightYardId"><option></option></select>
											</div>
											<div class="form-group">
												<label class="sm-tag">运抵地址：</label>
												<input type="text" class="inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>
									<div class="half" id="truckDiv">
										<div class="form-inline ">
											<div class="form-group">
												<label class="sm-tag">收货单位：</label>
												<select name="receivingDepartmentId"><option></option></select>
												<input type="hidden" name="receivingDepartmentName">
											</div>
											<div class="form-group">
												<label class="sm-tag">运抵地：</label>
												<input class="inputbg" type="text" unselectable="on" readonly="">
											</div>
											<div class="form-group">
												<label class="sm-tag">运抵地址：</label>
												<input type="text" class="addr_info inputbg" unselectable="on" readonly="">
											</div>
										</div>
									</div>
									<!--   短驳承运方  -->
									<div class="whole" id="forms6">
										<p>短驳承运方</p>
										<div class="form-inline ">
											<div class="form-group ">
												<label>短驳承运方式：</label>
												<select name="shortBargeCarrierMode" class="shortSelect shortSelect6">
													<option value="0" selected="selected" name="platform">平台</option>
													<option value="1" name="optional">自选</option>
												</select>
											</div>
										</div>
										<div class="form-inline" name="platformShortBarge6">
										</div>
										<div id="motor6" class="motor">
										</div>
										<button class="addBtns addBtn6 add-more btn btn-success btn-sm pull-right" type="button"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加更多承运方</button>
									</div>
								</div>
								<hr>
								<!-- 火运干线  -->
								<div class="train_line cut_apart clearfix">
									<h5>火运干线<i class='requireds'>*</i></h5>
									<!-- 起运中心站 -->
									<div class="half splitters">
										<div class="form-inline">
											<div class="form-group">
												<label class="lg-tag">起运中心站：</label>
												<select name="beginCenterSiteId"><option></option></select>
												<input name="beginCenterSiteName" type="hidden" />
											</div>
											<div class="form-group">
												<label>始发站点：</label>
												<select name="beginSiteId"><option></option></select>
												<input name="beginSiteName" type="hidden" />
											</div>
											<div class="form-group">
												<label class="lg-tag">始发货场：</label>
												<select name="beginSiteFreightYard"><option></option></select>
											</div>
											<div class="form-group">
												<label class="lg-tag">始发地：</label>
												<input type="text" name="beginAddress" class="inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>
									<!-- 到达中心站 -->
									<div class="half ">
										<div class="form-inline ">
											<div class="form-group">
												<label class="lg-tag">到达中心站：</label>
												<select name="endCenterSiteId"><option></option></select>
												<input name="endCenterSiteName" type="hidden" />
											</div>
											<div class="form-group">
												<label>到达站点：</label>
												<select name="endSiteId"><option></option></select>
												<input name="endSiteName" type="hidden" />
											</div>
											<div class="form-group">
												<label class="lg-tag">到达货场：</label>
												<select name="endSiteFreightYard"><option></option></select>
											</div>
											<div class="form-group">
												<label class="lg-tag">到达地：</label>
												<input type="text" name="endAddress" class="inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>
									<!--  干线费用   -->
									<div class="whole">
										<p>干线费用</p>
										<div class="form-inline">
											<div class="form-group">
												<label>&emsp;运费核计：</label>
												<input type="text" name="freight" maxLength="30" onkeyup="add(this)"><span>元</span>
											</div>
											<div class="form-group">
												<label>加固材料费：</label>
												<input type="text" name="materialCost" maxLength="30" onkeyup="add(this)"><span>元</span>
											</div>
											<div class="form-group">
												<label>篷布使用费：</label>
												<input type="text" name="tarpaulinCost" maxLength="30" onkeyup="add(this)"><span>元</span>
											</div>
											<div class="form-group">
												<label>发站装卸费：</label>
												<input type="text" name="beginStevedoringCost" maxLength="30" onkeyup="add(this)"><span>元</span>
											</div>
											<div class="form-group">
												<label>到站装卸费：</label>
												<input type="text" name="endStevedoringCost" maxLength="30" onkeyup="add(this)"><span>元</span>
											</div>
											<div class="form-group">
												<label>&emsp;运费合计：</label>
												<input type="text" name="freightSum" class="inputbg" readonly="readonly" maxLength="30"><span>元</span>
											</div>
										</div>
									</div>
								</div>
								<hr>
								<!-- 送达短驳 -->
								<div class="send_truck cut_apart clearfix">
									<h5>送达短驳<i class='requireds'>*</i></h5>
									<div class="half splitters">
										<div class="form-inline">
											<div class="form-group">
												<label class="sm-tag">发货中心站：</label>
												<select name="forwardingCenterSiteId"><option></option></select>
											</div>
											<div class="form-group">
												<label class="sm-tag">取货站点：</label>
												<select name="forwardingSiteId"><option></option></select>
											</div>
											<div class="form-group">
												<label class="sm-tag">取货货场：</label>
													<select name="forwardingSiteFreightYardId"><option></option></select>
											</div>
											<div class="form-group">
												<label class="sm-tag">取货地址：</label>
												<input type="text" class="inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>
									<div class="half">
										<div class="form-inline ">
											<div class="form-group">
												<label class="sm-tag">收货单位：</label>
												<select name="receivingDepartmentId"><option></option></select>
												<input type="hidden" name="receivingDepartmentName" />
											</div>
											<div class="form-group">
												<label class="sm-tag">运抵地：</label>
												<input class="inputbg" type="text" unselectable="on" readonly>
											</div>
											<div class="form-group">
												<label class="sm-tag">运抵地址：</label>
												<input type="text" class="addr_info inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>

									<!--   短驳承运方  -->
									<div class="whole" id="forms7">
										<p>短驳承运方</p>
										<div class="form-inline ">
											<div class="form-group ">
												<label>短驳承运方式：</label>
												<select name="sendShortBargeCarrierMode" class="shortSelect shortSelect7">
													<option value="0" selected="selected" name="platform">平台</option>
													<option value="1" name="optional">自选</option>
												</select>
											</div>
										</div>
										<div class="form-inline" name="platformShortBarge7">
										</div>
										<div id="motor7" class="motor">
										</div>
										<button class="addBtns addBtn7 add-more btn btn-success btn-sm pull-right" type="button"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加更多承运方</button>
									</div>
								</div>
								<hr>
								<!-- 委托方费用 -->
								<div class="client_cost">
									<h5>委托方费用<i class='requireds'>*</i></h5>
									<div class="form-inline">
										<div class="form-group">
											<label>汽运单价：</label>
											<input type="text" maxlength="10" name="truckPrice"><span>元</span>
										</div>
										<div class="form-group">
											<label>接取单价：</label>
											<input type="text" maxlength="10" name="pickUpPrice"><span>元</span>
										</div>
										<div class="form-group">
											<label>火运单价：</label>
											<input type="text" maxlength="10" name="trainPrice"><span>元</span>
										</div>
										<div class="form-group">
											<label>送达单价：</label>
											<input type="text" maxlength="10" name="arrivePrice"><span>元</span>
										</div>
									</div>
								</div>
								<hr>
								<!-- 备注信息 -->
								<div class="remarks_info">
									<h5>备注信息</h5>
									<textarea class="form-control" name="remark" rows="3"></textarea>
								</div>
							</div>
						</form>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="addProject()">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</body>

	<script>
		function updateShortBarge(param) {
			var mValue = $(param).val();
			var selectName = $(param).attr('name');
			var num = selectName.substr(selectName.length - 1, 1);

			var divName = "shortBargeCarrierName1";
			if(num == 2) {
				divName = "shortBargeCarrierName2";
			}

			if(mValue == "0") {
				$("div[name='" + divName + "']").html("<form><div class='form-inline'>" +
					"<div class='form-group'><label>&emsp;短驳承运方：</label><input type='text' readonly='readonly' name='shortBargeName' value='平台' /></div>" +
					"<div class='form-group short-client'><label>运输单价：</label><input name='transportPrice' type='text' maxlength=''><span>元</span></div>" +
					"<div class='form-group short-client'><label>扣损比率：</label><input name='deductionRate' type='text'><span>%</span></div>" +
					"<div class='form-group short-client'><label>扣损单价：</label><input name='deductionPrice' type='text'><span>元</span></div>" +
					"<div class='form-group short-client'><label>付款周期：</label> <select name='payment'>" +
					"<option value='0'>日结</option>" +
					"<option value='1'>周结</option>" +
					"<option value='2'>月结</option>" +
					"</select></div>" +
					"</div ></form>")
			} else {
				$("div[name='" + divName + "']").html("<form><div class='form-inline'>" +
					"<div class='form-group'><label>&emsp;短驳承运方：</label> <select name='shortBargeId' onchange='changeAndSetValue(this)' >" +
					"<c:forEach items='${carTeams }' var='carTeam' >" +
					"<option  value='${carTeam.id }'>${carTeam.carItemName }" +
					"</option></c:forEach></select>	<input type='hidden' name='shortBargeName' value='${carTeams[0].carItemName }' /></div>" +
					"<div class='form-group short-client'><label>运输单价：</label><input name='transportPrice' type='text'><span>元</span></div>" +
					"<div class='form-group short-client'><label>扣损比率：</label><input name='deductionRate' type='text'><span>‰</span></div>" +
					"<div class='form-group short-client'><label>扣损单价：</label><input name='deductionPrice' type='text'><span>元</span></div>" +
					"<div class='form-group short-client'><label>付款周期：</label> <select name='payment'>" +
					"<option value='0'>日结</option>" +
					"<option value='1'>周结</option>" +
					"<option value='2'>月结</option>" +
					"</select></div>" +
					"</div ></form>")

			}
		}
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/search/search.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/business/project/project.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/business/project/project-create.js"></script>
	

</html>