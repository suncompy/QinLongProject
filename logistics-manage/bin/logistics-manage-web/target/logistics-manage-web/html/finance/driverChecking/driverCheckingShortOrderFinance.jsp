<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head lang="zh-CN">
<meta charset="UTF-8">
<!-- Bootstrap 3.3.5 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.min.css"/>
  <!-- Font Awesome -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/font-awesome.css">
  <!-- Datatables -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/lib/jquery/DataTables/css/dataTables.bootstrap.min.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/common.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/lib/bootstrap-table/bootstrap-table.css"/>
  <!-- confirm -->
  <link href="${pageContext.request.contextPath}/static/lib/jquery/jquery-confirm/css/jquery-confirm.css" rel="stylesheet"/>
  <!-- validator -->
  <link href="${pageContext.request.contextPath}/static/lib/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet"/>
<!-- jQuery 1.12.2 -->
<script src="${pageContext.request.contextPath}/static/lib/jquery/jquery.min.js"></script>
<!-- Bootstrap 3.3.5 -->
<script src="${pageContext.request.contextPath}/static/lib/bootstrap/js/bootstrap.min.js"></script>
<!-- confirm -->
<script src="${pageContext.request.contextPath}/static/lib/jquery/jquery-confirm/js/jquery-confirm.js"></script>

<!-- echarts -->
<script src="${pageContext.request.contextPath}/static/lib/graphic/echart/echarts.js"></script>
<!-- Datatables -->
<script src="${pageContext.request.contextPath}/static/lib/jquery/DataTables/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/lib/jquery/DataTables/js/dataTables.bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/lib/jquery/fd/js/jquery.fddatatable.js"></script>
<script src="${pageContext.request.contextPath}/static/lib/bootstrap-table/bootstrap-table.min.js"></script>
<!-- bootstrapValidator -->
<script src="${pageContext.request.contextPath}/static/lib/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script src="${pageContext.request.contextPath}/static/lib/bootstrapValidator/js/language/zh_CN.js"></script>
<script src="${pageContext.request.contextPath}/static/js/tips.js"></script>
<script src="${pageContext.request.contextPath}/static/js/common.js"></script>
<script src="${pageContext.request.contextPath}/js/finance/driverChecking/driverCheckingShortOrderFinance.js"></script>
</head>

<body>
<div class="container-fluid">
    <div class="panel panel-default">
         <div class="panel-body">
	   <form class="form-horizontal" id="frmShortOrderFinanceQuery">
	    <div class="form-group">
                <label class="col-md-1 control-label">项目id</label>
                <div class="col-md-2">
                    <input class="form-control" name="projectId" type="text">
                </div>
	        <div class="col-md-4">
                    <button class="btn btn-primary" id="btnShortOrderFinanceQuery">查询</button>
                </div>
            </div>
	  </form>
        </div>
    </div>
    <div class="box box-primary">
	<div class="box-header with-border">
	    <i class="fa fa-list-alt"></i>
	    <h3 class="box-title">开始对账</h3>
	</div>
	<div class="box-tools">
        <button class="btn btn-primary" id="btnShortOrderFinanceStartChecking">开始对账</button>
        <button class="btn btn-primary" id="btnShortOrderFinancePack">打包</button>
    </div>
	<table id="tblShortOrderFinance" class="table table-striped table-bordered" cellspacing="0" width="100%">
	    <thead>
		<tr>
			<th>
				<label class="demo--label"><input class="demo--checkbox" type="checkbox" ><span class="demo--checkboxInput"></span></label>
			</th>
			<th>项目编号</th>
			<th>运单编号</th>
			<th>分支机构</th>
			<th>项目类型</th>
			<th>财务状态</th>
			<th>确认计费时间</th>
			<th>财务审核时间</th>
			<th>发货企业</th>
			<th>收货企业</th>
			<th>货物品名</th>
			<th>承运车辆</th>
			<th>计费吨数</th>
			<th>计费件数</th>
			<th>运输费用</th>
			<th>扣损金额</th>
			<th>补加运费</th>
			<th>应付运费</th>
		</tr>
	    </thead>
	    <tbody></tbody>
	</table>
    </div>
    
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="chooseProjectDivId" tabindex="-1" role="dialog" data-keyboard="false" data-backdrop="static" aria-hidden="true">
        <div class="modal-dialog large">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">开始对账</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="chooseProjectFormId">
                        <div class="form-group">
                            <label class="col-md-2 control-label">项目编号</label>
                            <div class="col-md-4">
                                <select name="projectId" id="projectId"></select>
                            </div>
                            <label class="col-md-2 control-label">分支机构</label>
                            <div class="col-md-4">
                                <p name="branchGroupName"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">发货企业</label>
                            <div class="col-md-4">
                                <p name="sendCargoCompanyName"></p>
                            </div>
                            <label class="col-md-2 control-label">始发地</label>
                            <div class="col-md-4">
                                <p name="beginAddress"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">收货企业</label>
                            <div class="col-md-4">
                                <p name="receiveCargoCompanyName"></p>
                            </div>
                            <label class="col-md-2 control-label">运抵地</label>
                            <div class="col-md-4">
                                <p name="endAddress"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">总车数</label>
                            <div class="col-md-4">
                                <p name=""></p>
                            </div>
                            <label class="col-md-2 control-label">总净重</label>
                            <div class="col-md-4">
                                <p name=""></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">总件数</label>
                            <div class="col-md-4">
                                <p name=""></p>
                            </div>
                            <label class="col-md-2 control-label">总金额</label>
                            <div class="col-md-4">
                                <p name=""></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">接取待对账运单</label>
                            <div class="col-md-4">
                                <p name=""></p>
                            </div>
                            <label class="col-md-2 control-label">送达待对账运单</label>
                            <div class="col-md-4">
                                <p name=""></p>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" id="btnShortOrderFinanceQueryProjectId">开始对账</button>
                    <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">取消</button>
                </div>
            </div>
        </div>
    </div>
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="initPackDivId" tabindex="-1" role="dialog" data-keyboard="false" data-backdrop="static" aria-hidden="true">
        <div class="modal-dialog large" style="width:1100px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">打包</h4>
                </div>
                <div class="modal-body">
                      <div class="form-group">
                          <label class="col-md-1 control-label">项目编号</label>
                          <div class="col-md-2">
                              <select name="projectId" id="projectId"></select>
                          </div>
                          <label class="col-md-1 control-label">分支机构</label>
                          <div class="col-md-2">
                              <p name="branchGroupName"></p>
                          </div>
                          <label class="col-md-1 control-label">打包车辆</label>
                          <div class="col-md-2">
                              <p name="sendCargoCompanyName"></p>
                          </div>
                          <label class="col-md-1 control-label">打包车次</label>
                          <div class="col-md-2">
                              <p name="beginAddress"></p>
                          </div>
                      </div>
                   <table id="tblInitPack" data-toggle="table" class="table table-striped table-bordered" cellspacing="0" width="100%">
				    <thead>
					<tr>
						<th data-field="orderCode">运单编号</th>
						<th data-field="branchGroupName">分支机构</th>
						<th data-field="sendCompany">发货企业</th>
						<th data-field="receiptCompany">收货企业</th>
						<th data-field="cargoName">货物品名</th>
						<th data-field="carPlateNumber">承运车辆</th>
						<th data-field="shortBargeCost">运输费用</th>
						<th data-field="buckleFigure">扣损金额</th>
						<th data-field="subsidy">补加运费</th>
						<th data-field="shouldPayFigure">应付运费</th>
					</tr>
				    </thead>
				    <tbody></tbody>
					</table>
					<form class="form-horizontal" id="initPackFormId">	
						<div class="form-group">
                          <label class="col-md-1 control-label">支付模式</label>
                          <div class="col-md-2">
                              <select name="projectId"></select>
                          </div>
                          <label class="col-md-1 control-label">支付比例</label>
                          <div class="col-md-2">
                              <p name="branchGroupName"></p>
                          </div>
                          <label class="col-md-1 control-label">现金支付方式</label>
                          <div class="col-md-2">
                              <p name="sendCargoCompanyName"></p>
                          </div>
                          <label class="col-md-1 control-label">油气领取方式</label>
                          <div class="col-md-2">
                              <p name="beginAddress"></p>
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-md-1 control-label">领取人</label>
                          <div class="col-md-2">
                              <select name="projectId"></select>
                          </div>
                          <label class="col-md-1 control-label">银行信息</label>
                          <div class="col-md-2">
                              <p name="branchGroupName"></p>
                          </div>
                          <label class="col-md-1 control-label">账号信息</label>
                          <div class="col-md-2">
                              <p name="sendCargoCompanyName"></p>
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-md-1 control-label">备注</label>
                          <div class="col-md-11">
                              <textarea class="form-control" name="remarks" rows="3"></textarea>
                          </div>
                      </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    
</div>
</body>
</html>
