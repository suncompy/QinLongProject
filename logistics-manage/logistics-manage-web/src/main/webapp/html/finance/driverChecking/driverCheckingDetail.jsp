<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/permission.tld" prefix="per"%>
<!DOCTYPE html>
<html>
<head lang="zh-CN">
<meta charset="UTF-8">
<script src="${pageContext.request.contextPath}/static/js/invoke.js"></script>
<script src="${pageContext.request.contextPath}/js/finance/driverChecking/driverCheckingDetail.js"></script>
</head>

<body>
<div class="container-fluid">
    <div class="panel panel-default">
         <div class="panel-body">
	  <form class="form-inline maxWidth" id="frmShortOrderFinanceQuery">
	   <input type="hidden" name="projectId"/>
	    <div class="form-group">
	        <label>项目编号：</label>
	        <input name="projectCode" type="text">
        </div>
        <!-- <div class="form-group">
            <label>始发地：</label>
            <input name="beginSite" type="text">
 		</div>
        <div class="form-group">
            <label>终点地：</label>
			<input name="endSite" type="text">
		</div> -->
        <div class="form-group">
            <label>分支机构：</label>
            <input name="branchGroupName" type="text">
        </div>
        <div class="form-group dataTimes">
			<label>&emsp;日期从：</label>
			<input name="beginDate" id="d244" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
			<i class="fa fa-calendar-check-o"></i>
		</div>
		<div class="form-group dataTimes">
			<label>至</label>
			<input id="d244" name='endDate' type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
			<i class="fa  fa-calendar-check-o"></i>
		</div>
		<div class="form-group"> 
            <button class="btn-xs btn-primary btn-query pull-right" id="btnShortOrderFinanceQuery">查询</button>
	    </div>
	  </form>
        </div>
    </div>
    <div class="box box-primary table-responsive">
	<div class="box-tools">
		<button class="btn-xs btn-primary" id="btnDriverCheckLink">对账列表</button>   
		<button class="btn-xs btn-primary  tableActive" id="btnDriverCheckDetailLink1">对账明细</button>    
        <button class="btn-xs btn-danger pull-right" id="btnDriverCheckDetailDeaproveFinance" style="display:none">反审核</button>
        
         <per:hasUrlPerm code="driverCheckDetailAproveFinance">
		    <button class="btn-xs btn-primary pull-right checks" id="btnDriverCheckDetailAproveFinance">财务审核</button>
		 </per:hasUrlPerm>
		  <per:hasUrlPerm code="driverCheckDetailUnpack">
		     <button class="btn-xs btn-danger pull-right" id="btnDriverCheckDetailUnpack">解包</button>
		 </per:hasUrlPerm>
    </div>
	<table id="tblShortOrderFinance" class="table table-striped table-bordered" cellspacing="0" width="100%">
	    <thead>
		<tr>
			<th>
				<label class="demo--label"><input class="demo--checkbox" type="checkbox" ><span class="demo--checkboxInput"></span></label>
			</th>
			<th>项目编号</th>
			<th>分支机构</th>
			<th>对账单号</th>
			<th>财务状态</th>
			<th>打包车辆</th>
			<th>打包车次</th>
			<th>运费合计</th>
			<th>支付模式</th>
			<th>支付比例</th>
			<th>应付现金</th>
			<th>支付对象</th>
			<th>支付方式</th>
			<th>银行信息</th>
			<th>账号信息</th>
			<th>油气类型</th>
			<th>油气金额</th>
			<th>领取方式</th>
			<th>打包人</th>
			<th>审核人</th>
			<th>审核日期</th>
			<th>备注</th>
		</tr>
	    </thead>
	    <tbody></tbody>
	</table>
    </div>
    <div class="box box-primary">
    <!--<div class="panel-heading">支出明细</div>  -->
	
        <div class="panel-body">
            <button class="btn-xs btn-primary tableActive" id="btnPointEdit" disabled="disabled">打包明细</button>
        </div>
	<table id="tblPoint" class="table table-striped table-bordered" cellspacing="0" width="100%">
	    <thead>
		<tr>
			<th>
				<label class="demo--label"><input class="demo--checkbox" type="checkbox" ><span class="demo--checkboxInput"></span></label>
			</th>
			<th>运单编号</th>
			<th>承运车辆</th>
			<th>财务状态</th>
			<th>发货净重</th>
			<th>收货净重</th>
			<th>计费重量</th>
			<th>计费件数</th>
			<th>单价</th>
			<th>运输费用</th>
			<th>耗损</th>
			<th>是否扣损</th>
			<th>扣损系数</th>
			<th>扣损单价</th>
			<th>扣损金额</th>
			<th>补加项目</th>
			<th>补加金额</th>
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
                              <input name="projectId"/>
                          </div>
                          <label class="col-md-1 control-label">分支机构</label>
                          <div class="col-md-2">
                          	  <input name="branchGroupName"/>
                          </div>
                          <label class="col-md-1 control-label">打包车辆</label>
                          <div class="col-md-2">
                              <input name="packTruckNum"/>
                          </div>
                          <label class="col-md-1 control-label">打包车次</label>
                          <div class="col-md-2">
                              <input name="packTruckDegree"/>
                          </div>
                      </div>
                      <br>
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
					<br>
					<form class="form-horizontal" id="initPackFormId">	
						<input type="hidden" name="shOrderFinIds"/>
						<div class="form-group">
                          <label class="col-md-1 control-label">支付模式</label>
                          <div class="col-md-2">
                              <select name="paymentId"></select>
                          </div>
                          <label class="col-md-1 control-label">支付比例</label>
                          <div class="col-md-2">
                              <p name="payRatio"></p>
                          </div>
                          <label class="col-md-1 control-label">现金支付方式</label>
                          <div class="col-md-2">
                          	  <select name="cashPayType">
                          	  	<option value="0">现金</option>
                          	  	<option value="1">转账</option>
                          	  	<option value="2">支票</option>
                          	  </select>
                          </div>
                          <label class="col-md-1 control-label">油气领取方式</label>
                          <div class="col-md-2">
                              <select name="suppliesReceiveType">
                          	  	<option value="0">网点领取</option>
                          	  	<option value="1">人员配送</option>
                          	  </select>
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-md-1 control-label">领取人</label>
                          <div class="col-md-2">
                              <select name="receiveUserId"></select>
                          </div>
                          <label class="col-md-1 control-label">银行信息</label>
                          <div class="col-md-2">
                              <input name="openBank"/>
                          </div>
                          <label class="col-md-1 control-label">账号信息</label>
                          <div class="col-md-2">
                              <input name="bankAccount"/>
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-md-1 control-label">备注</label>
                          <div class="col-md-11">
                              <textarea class="form-control" name="remark" rows="3"></textarea>
                          </div>
                      </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" name="btnInitPackSave">确定</button>
                    <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">取消</button>
                </div>
            </div>
        </div>
    </div>
    
</div>
</body>
</html>