<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head lang="zh-CN">
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/finance/billReconciliation/costReconciliation.css" />
<script src="${pageContext.request.contextPath}/static/js/invoke.js"></script>
<script src="${pageContext.request.contextPath}/js/finance/billReconciliation/costCheckingShortOrderFinance.js"></script>
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
        <c:forEach items='${permissions }' var='permission' >
			<c:if test="${permission.code=='costReconciliationPack'}">
				  <button class="btn-xs btn-primary pull-right" id="btnShortOrderFinancePack">打包</button>
			</c:if>
	 	</c:forEach>
	 	<c:forEach items='${permissions }' var='permission' >
	 	<c:if test="${permission.code=='costReconciliationStartChecking'}">
				 <button class="btn-xs btn-primary pull-right" id="btnShortOrderFinanceStartChecking">开始对账</button>
			</c:if>
	 	</c:forEach>
   </div>
	<table id="tblShortOrderFinance" class="table table-striped table-bordered" cellspacing="0" width="100%">
	    <thead>
		<tr>
			<th>
				<label class="demo--label"><input class="demo--checkbox" type="checkbox" ><span class="demo--checkboxInput"></span></label>
			</th>
			<th>项目编号</th>
			<th>运单编号</th>
			<th>网点分支</th>
			<th>状态</th>
			<th>货物品名</th>
			<th>货物单价</th>
			<th>承运车辆</th>
			<th>发货皮重</th>
			<th>发货毛重</th>
			<th>发货净重</th>
			<th>运单来源</th>
			<th>创建时间</th>
			<th>取货地</th>
			<th>装货时间</th>
			<th>运抵地</th>
			<th>到货时间</th>
			<th>调度员</th>
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
                                <p name="pickupPlace"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">收货企业</label>
                            <div class="col-md-4">
                                <p name="receiveCargoCompanyName"></p>
                            </div>
                            <label class="col-md-2 control-label">运抵地</label>
                            <div class="col-md-4">
                                <p name="arrivePlace"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">货物单价</label>
                            <div class="col-md-4">
                                <p name="cargoPrice"></p>
                            </div>
                            <label class="col-md-2 control-label">总车数</label>
                            <div class="col-md-4">
                                <p name="totalCarNum"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">总净重</label>
                            <div class="col-md-4">
                                <p name="totalWeight"></p>
                            </div>
                            <label class="col-md-2 control-label">总件数</label>
                            <div class="col-md-4">
                                <p name="totalPiece"></p>
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
						<input type="hidden" name="costOrderFinIds"/>
						<input type="hidden" name="projectId"/>
                      <div class="form-group">
                          <label class="col-md-1 control-label">项目编号</label>
                          <div class="col-md-2">
                              <input name="projectCode" readonly="readonly"/>
                          </div>
                          <label class="col-md-1 control-label">分支机构</label>
                          <div class="col-md-2">
                          	  <input name="branchGroupName" readonly="readonly"/>
                          </div>
                          <label class="col-md-1 control-label">打包车次</label>
                          <div class="col-md-2">
                              <input name="packTruckNum" readonly="readonly"/>
                          </div>
                          <label class="col-md-1 control-label">货物单价</label>
                          <div class="col-md-2">
                              <input name="cargoPrice" readonly="readonly"/>
                          </div>
                      </div>
                      <br>
                   <table id="tblInitPack" data-toggle="table" class="table table-striped table-bordered" cellspacing="0" width="100%">
				    <thead>
					<tr>
						<th data-field="orderCode">运单编号</th>
						<th data-field="cargoName">货物品名</th>
						<th data-field="carPlateNumber">承运车辆</th>
						<th data-field="totalWeight">总净重</th>
						<th data-field="totalPiece">总件数</th>
					</tr>
				    </thead>
				    <tbody></tbody>
					</table>
					<br>
					<div class="form-group">
                          <label class="col-md-1 control-label"><span style="color:red;">*</span>税率</label>
                          <div class="input-group col-md-2">
                              <input name="taxRate" style="width: 136px; margin-left: 5px;"/>
                              <span class="input-group-addon">%</span>
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
