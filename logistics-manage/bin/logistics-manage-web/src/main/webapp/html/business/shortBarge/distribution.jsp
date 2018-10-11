<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>分配任务</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/public.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/business/shortBarge/distribtion.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/config/url.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/public/search/search.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/My97DatePicker/WdatePicker.js"></script>
		<script src="${pageContext.request.contextPath}/static/js/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.serializejson.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js" ></script>
		<link href="${pageContext.request.contextPath}/css/page/page.css" rel="stylesheet"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/search/get-basic-data.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/business/shortBarge/distribution.js"></script>
	</head>
	
	<body>
		<!-- 分配任务表单 -->
		<div class="form project_form container_top">
			<form class="form-inline maxWidth" id="searchForm">
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
					<div class="form-group">
						<label>货物品名：</label>
						<select name="cargoId">
						</select>
					</div>
					<div class="form-group">
						<label>发货单位：</label>
						<input name='sendCargoCompanyName' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>收货单位：</label>
						<input name='receiveCargoCompanyName' type="text" maxlength="30"/>
					</div>
				</div>
				<div class="foot">
					<a class="btn btn-success" href="javascript:void(0)"><em class="search"></em>
						<span id="searchSpan"  onclick="search('/business/short/job/byPage/list.do')">搜索</span>
					</a>
					<div id="lookMore" style="display: none;"></div>
				</div>
			</form>
		</div>
		<!-- 分配任务表格 -->
		<div class="container_bottom tableDiv">
			<div class="tabbable" id="tabs-529262">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#panel1" data-toggle="tab" >分配列表</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="panel1">
						<div class="domain">
							<div class="tableBg">
							<c:forEach items='${permissions }' var='permission' >
								<c:if test="${permission.code=='distributionStart'}">
									<a href="javascript:void(0)" class="exportBtn start" ><span>开始</span></a>
								</c:if>
								<c:if test="${permission.code=='distributionPause'}">
									<a href="javascript:void(0)" class="exportBtn pause" ><span>暂停</span></a>
								</c:if>
								<c:if test="${permission.code=='isDistribution'}">
									<input type="hidden" id = "isDistribution" value = "1">
								</c:if>
							</c:forEach>
								<!-- <a href="javascript:void(0)" class="exportBtn complete" onclick="return false;"><span>完成</span></a> -->
							</div>
							<div class="table-responsive">
								<!--运单列表表格开始-->
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th>
												<label class="demo--label">
								            <input class="demo--checkbox" type="checkbox" name="WaybillName" >
							                  <span class="demo--checkboxInput"></span>
							                         	</label>
											</th>
											<th data-field="id">项目编号</th>
											<th>项目类型</th>
											<th>阶段</th>
											<th>今日分配</th>
											<th>状态</th>
											<th>已领任务</th>
											<th>待领任务</th>
											<th>完成任务</th>
											<th>累计完成任务</th>
											<th>分支机构</th>
											<th>发货单位</th>
											<th>收货单位</th>
											<th>货物品名</th>
											<th>短驳承运方式</th>
											<th>短驳承运方</th>
										</tr>
									</thead>
									<tbody id='listbody'>
									</tbody>
								</table>
								<!--运单列表表格结束-->
							</div>
						</div>
						<div class="row clearfix">
								<div class="col-md-12 column paging page_div" id="page"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--分配任务模态框-->
		<div class="modal fade distributionModal" tabindex="-1" role="dialog" aria-hidden="true" id="distributionModal" data-backdrop="static">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" class="myModalLabel">
							每日任务分配
						</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="distributionId">
							<input type="hidden" id="projectId" name="projectId"/>
							<input type="hidden" id="projectType" name="projectType"/>
							<div class="project_info">
								<h5>运单信息</h5>
								<ul id="showPro" class="waitUl">
								</ul>
							</div>
							<hr />
							<div class="project_info">
								<h5>任务分配</h5>
								<div class="form-inline pLeft">
									<div class="form-group">
										<label>今日分配车辆：</label>
										<input  type="number" class="form-control" placeholder="只能输入数字" name="num"/>
										<span class="vehicle">辆</span>
									</div>
								</div>
							</div>
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
								    <button type="button" class="btn sureBtn" onclick="subPut()">确定</button>
									<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
								</div>
	
							</div>
						</form>	
					</div>
				</div>
			</div>
		</div>
		<!-- 未选择数据提醒模态框 -->
		<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="nullModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="body-content">
							<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
							<span>请选择一条要操作的数据</span>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" data-dismiss="modal">确定</button>
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
							<div><span>您已选择</span><b class="errnums">13</b> <span id="msgSpan"></span></div>
						</div>
						<input type="hidden" name="param" value="1" />
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="controller()">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
				
		<!--确认提示模态框-->		
	 <div class="modal fade promptModal" tabindex="-1" role="dialog" id="promptIdModal">
		<div class="modal-dialog " role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">提示</h4>
					<span data-dismiss="modal" aria-label="Close"></span>
				</div>
				<div class="modal-body">
					<p id="msg"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn sureBtn" id="sure_msg">确定</button>
				</div>
			</div>
		</div>
	 </div>
	</body>
	
	
	
</html>