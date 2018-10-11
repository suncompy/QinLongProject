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
<script src="${pageContext.request.contextPath}/js/finance/driverChecking/driverCheckingShortOrderFinance.js"></script>
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
		<button class="btn-xs btn-primary tableActive" id="btnDriverCheckLink1">对账列表</button>  
        <button class="btn-xs btn-primary" id="btnDriverCheckDetailLink">对账明细</button>
         <per:hasUrlPerm code="driverShortOrderFinancePack">
		    <button class="btn-xs btn-primary pull-right" id="btnShortOrderFinancePack">打包</button>
		 </per:hasUrlPerm>
		  <per:hasUrlPerm code="driverShortOrderFinanceStartChecking">
		    <button class="btn-xs btn-primary pull-right" id="btnShortOrderFinanceStartChecking">开始对账</button>
		 </per:hasUrlPerm>
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
                    <h5 class="modal-title">开始对账</h5>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="chooseProjectFormId">
                     <fieldset>
                        <legend><span style="font-size:12px;color:red;">项目信息</span></legend>
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
                                <p name="totalCarNum"></p>
                            </div>
                            <label class="col-md-2 control-label">总净重</label>
                            <div class="col-md-4">
                                <p name="totalWeight"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">总件数</label>
                            <div class="col-md-4">
                                <p name="totalPiece"></p>
                            </div>
                            <label class="col-md-2 control-label" style="display:none">总金额</label>
                            <div class="col-md-4">
                                <p name=""></p>
                            </div>
                        </div>
                    </fieldset>     
                    <fieldset>
                        <legend><span style="font-size:12px;color:red;">其他信息</span></legend>
                        <div class="form-group">
                            <label class="col-md-2 control-label">接取待对账运单</label>
                            <div class="col-md-4">
                                <p name="reciveOrderNum"></p>
                            </div>
                            <label class="col-md-2 control-label">送达待对账运单</label>
                            <div class="col-md-4">
                                <p name="sendOrderNum"></p>
                            </div>
                        </div>
                      </fieldset> 
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
                <form class="form-horizontal" id="initPackFormId">	
						<input type="hidden" name="shOrderFinIds"/>
						<input type="hidden" name="projectId"/>
						<input type="hidden" name="receiveUserType"/>
                      <div class="form-group">
                          <label class="col-md-1 control-label">项目编号</label>
                          <div class="col-md-2">
                              <input name="projectCode" readonly="readonly"/>
                          </div>
                          <label class="col-md-1 control-label">分支机构</label>
                          <div class="col-md-2">
                          	  <input name="branchGroupName" readonly="readonly"/>
                          </div>
                          <label class="col-md-1 control-label">打包车辆</label>
                          <div class="col-md-2">
                              <input name="packTruckNum" readonly="readonly"/>
                          </div>
                          <label class="col-md-1 control-label">打包车次</label>
                          <div class="col-md-2">
                              <input name="packTruckDegree" readonly="readonly"/>
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
						<th data-field="deductionMoney">扣损金额</th>
						<th data-field="subsidyTransitMoney">补加运费</th>
						<th data-field="payableTransitMoney">应付运费</th>
					</tr>
				    </thead>
				    <tbody></tbody>
					</table>
					<br>
					
						<div class="form-group">
                          <label class="col-md-1 control-label">支付模式</label>
                          <div class="col-md-2">
                              <select name="paymentId">
                              <option value="">请选择</option>
                              <option value="0">全额现金</option>
                         	  <option value="1">全额油卡</option>
                         	  <option value="2">全额气卡</option>
                         	  <option value="3">钱卡混合</option>
                              </select>
                          </div>
                          <label class="col-md-1 control-label">油气比例%</label>
                          <div class="col-md-2">
                              <input name="payRatio"/>
                          </div>
                          <label class="col-md-1 control-label">现金支付方式</label>
                          <div class="col-md-2">
                          	  <select name="cashPayType">
                          	    <option value="">请选择</option>
                          	  	<option value="0">现金</option>
                          	  	<option value="1">转账</option>
                          	  	<option value="2">支票</option>
                          	  </select>
                          </div>
                          <label class="col-md-1 control-label">油气领取方式</label>
                          <div class="col-md-2">
                              <select name="suppliesReceiveType">
                                <option value="">请选择</option>
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
                              <input name="openBank" readonly="readonly"/>
                              <!-- 
                              <select class="form-control" name="openBank"></select>-->
                          </div>
                          <label class="col-md-1 control-label">账号信息</label>
                          <div class="col-md-2">
                              <input name="bankAccount" readonly="readonly"/>
                              <!--  
                              <select class="form-control" name="bankAccount"></select>-->
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
