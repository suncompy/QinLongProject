<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head lang="zh-CN">
<meta charset="UTF-8">
<script src="${pageContext.request.contextPath}/static/js/invoke.js"></script>
<script src="${pageContext.request.contextPath}/js/finance/receivablesSettlement/threePartiesReceivablesHistory.js"></script>
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
		<button class="btn-xs btn-primary tableActive" id="btnCustomerAccountsHistoryDetail">历史明细</button>
      <!--   <button class="btn-xs btn-danger pull-right" id="btnDriverCheckDetailDeaproveFinance">反审核</button>
        <button class="btn-xs btn-primary pull-right checks" id="btnDriverCheckDetailAproveFinance">财务审核</button>
        <button class="btn-xs btn-danger pull-right" id="btnCustomerAccountsSettlement">结算</button>
    	<button class="btn-xs btn-primary pull-right" id="btnAddReceiveMoney">新增收款</button>    -->
    </div>
	<table id="tblShortOrderFinance" class="table table-striped table-bordered" cellspacing="0" width="100%">
	    <thead>
		<tr>
			<th>
				<label class="demo--label"><input class="demo--checkbox" type="checkbox" ><span class="demo--checkboxInput"></span></label>
			</th>
			<th>项目编号</th>
			<th>流水号</th>
			<th>状态</th>
			<th>货款类型</th>
			<th>发票金额</th>
			<th>支付比例</th>
			<th>总金额</th>
			<th>已结金额</th>
			<th>待结金额</th>
			<th>三方企业</th>
			<th>创建时间</th>
		</tr>
	    </thead>
	    <tbody></tbody>
	</table>
    </div>
    <div class="box box-primary">
    <!--<div class="panel-heading">支出明细</div>  -->
	
        <div class="panel-body">
            <button class="btn-xs btn-primary tableActive" id="btnPointEdit" disabled="disabled">详情</button>
        </div>
	<table id="tblPoint" class="table table-striped table-bordered" cellspacing="0" width="100%">
	    <thead>
		<tr>
			<th>
				<label class="demo--label"><input class="demo--checkbox" type="checkbox" ><span class="demo--checkboxInput"></span></label>
			</th>
			<th>支出账户</th>
			<th>支出账号</th>
			<th>存入账户</th>
			<th>存入账号</th>
			<th>结算状态</th>
			<th>结算金额</th>
			<th>操作人</th>
			<th>审核人</th>
			<th>审核时间</th>
		</tr>
	    </thead>
        <tbody></tbody>
	</table>
    </div>
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="addReceiveMoneyModal" tabindex="-1" role="dialog" data-keyboard="false" data-backdrop="static" aria-hidden="true">
		<div class="modal-dialog" style="width: 1100px">
			<div class="modal-content">
				<div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">新增收款</h4>
                </div>
				<div class="modal-body">
					<form class="form-horizontal" id="addReceiveMoneyFormId">
						<fieldset>
							<legend>
								<span style="font-size: 12px; color: red;">基本信息<i class='requireds'>*</i></span>
							</legend>
							<div class="form-group">
								<label class="col-md-1 control-label">项目编号</label>
								<div class="col-md-2">
									<select name="projectId" id="projectId"></select>
								</div>
								<label class="col-md-1 control-label">流水号</label>
								<div class="col-md-2">
									<select name="customerPackId" id="customerPackId">
										<option></option>
									</select>
								</div>
								<label class="col-md-1 control-label">发票金额</label>
								<div class="col-md-2">
									<input name="produceMoney" id="produceMoney" readonly="readonly" />
								</div>
								<label class="col-md-1 control-label">三方企业</label>
								<div class="col-md-2">
									<select name="threeCompaniesId" ></select>
								</div>
							</div>
						</fieldset>
						<fieldset>
							<legend>
								<span style="font-size: 12px; color: red;">支付信息<i class='requireds'>*</i></span>
							</legend>
							<div class="form-group">
								<label class="col-md-1 control-label">支付比例</label>
								<div class="col-md-2">
									<input id="paymentRatio"  name="paymentRatio" />
								</div>
								<label class="col-md-1 control-label">应付金额</label>
								<div class="col-md-2">
									<input id="payableMoney" name="payableMoney" readonly="readonly" />
								</div>	
							</div>
						</fieldset>
					</form>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" id="btnThreePartiesReceivablesSave">确定</button>
					<button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">取消</button>
				</div>
			</div>
		</div>
	</div>
    
    <!-- 结算模态框-->
	<div class="modal fade" id="settlementModal" tabindex="-1" role="dialog" data-keyboard="false" data-backdrop="static" aria-hidden="true">
		<div class="modal-dialog" style="width: 1100px">
			<div class="modal-content">
				<div class="modal-body">
					<form class="form-horizontal" id="chooseProjectFormId">
					<input type="hidden" name="threePartiesReceivablesId" >
						<fieldset>
							<legend>
								<span style="font-size: 12px; color: red;">基本信息</span>
							</legend>
							<div class="form-group">
								<label class="col-md-1 control-label">项目编号</label>
								<div class="col-md-2">
									<input name="projectCode" readonly="readonly" />
								</div>
								<label class="col-md-1 control-label">流水号</label>
								<div class="col-md-2">
									<input name="custPackId" readonly="readonly" />
								</div>
								<label class="col-md-1 control-label">待结金额</label>
								<div class="col-md-2">
									<input name="besettledMoney" readonly="readonly" />
								</div>
							</div>
						</fieldset>
						<fieldset>
							<legend>
								<span style="font-size: 12px; color: red;">结算信息</span>
							</legend>
							<div class="form-group">
								<label class="col-md-1 control-label">支出单位</label>
								<div class="col-md-2">
									<input id="threeCompanies" name="threeCompanies" readonly="readonly" />
								</div>
								<input name="threeCompaniesId" type="hidden"> 
								<label class="col-md-1 control-label">支出账户</label>
								<div class="col-md-2">
									<select name="payAccountId" id="payAccountId">
										<option></option>
									</select>
								</div>	
								<label class="col-md-1 control-label">支出账号</label>
								<div class="col-md-2">
									<input id="payNumber"  readonly="readonly" />
								</div>		
							</div>
							<div class="form-group">
								<label class="col-md-1 control-label">存入单位</label>
								<div class="col-md-2">
									<input id="receiveUnitId" value="新疆秦龙矿业有限公司" readonly="readonly" />
								</div>
								<label class="col-md-1 control-label">存入账户</label>
								<div class="col-md-2">
									<select name="receiveAccountId" id="receiveAccountId">
										<option></option>
									</select>
								</div>	
								<label class="col-md-1 control-label">存入账号</label>
								<div class="col-md-2">
									<input id=receiveNumber  readonly="receiveNumber" />
								</div>		
							</div>	
							<div class="form-group">
								<label class="col-md-1 control-label">结算金额</label>
								<div class="col-md-2">
									<input name="settleMoney"  />
								</div>
							</div>
							
						</fieldset>
					</form>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" id="btnCustomerAccountsSave">确定</button>
					<button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">取消</button>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
