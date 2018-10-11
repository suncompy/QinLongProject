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
		<script src="${pageContext.request.contextPath}/js/finance/settlementAccountability/driverAccountsHistory.js"></script>
	</head>

	<body>
		<div class="container-fluid">
			<div class="panel panel-default">
				<div class="panel-body">
					<form style='display:flex;flex-direction:row;' class="form-inline maxWidth" id="frmDriverAccountsQuery">
						<input type="hidden" name="projectId" />
						<div class="form-group">
							<label>项目编号：</label>
							<input name="projectCode" type="text">
						</div>
						<div class="form-group c-form-0424" style='width:200px;height:19px;display:flex;flex-direction:row;'>
							<label style='display:inline-block;width:120px;height:100%;'>发货企业：</label>
							<select style='height:100%;' name="sendCargoCompanyId"></select>
						</div>
						<div class="form-group c-form-0424" style='width:200px;display:flex;flex-direction:row;'>
							<label style='display:inline-block;width:120px;'>收货企业：</label>
							<select style='height:100%;' name="receiveCargoCompanyId"></select>
						</div>
						<style>
							.c-form-0424 .select2-selection{
								height:19px;
								line-height:19px;
								font-size: 12px;
							}
							.c-form-0424 .select2-container--default .select2-selection--single .select2-selection__arrow{
							top:-3px;
							}
							.c-form-0424 .select2-selection__rendered{
							height:19px;font-size:12px;margin-top:-5px
							}
						</style>
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
							<button class="btn-xs btn-primary btn-query pull-right" id="btnDriverAccountsConditionQuery">查询</button>
						</div>
					</form>
				</div>
			</div>
			<div class="box box-primary table-responsive">
				<div class="box-tools">					
					<button class="btn-xs btn-primary " id="btnDriverAccountsDetail">结算明细</button>
					<button class="btn-xs btn-primary tableActive" id="btnDriverAccountsHistoryDetail">历史明细</button>
					<!-- <button class="btn-xs btn-danger pull-right" id="btnDriverAccountsAgainstAudit">反审核</button>
					<button class="btn-xs btn-primary pull-right checks" id="btnDriverAccountsFinanceAudit">财务审核</button>
					<button class="btn-xs btn-primary pull-right" id="btnDriverAccountsSettlement">结算</button> -->
				</div>
				<table id="tblDriverAccounts" class="table table-striped table-bordered" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>
								<label class="demo--label"><input class="demo--checkbox" type="checkbox" ><span class="demo--checkboxInput"></span></label>
							</th>
							<th>项目编号</th>
							<th>分支机构</th>
							<th>流水号</th>
							<th>费用状态</th>
							<th>油气卡状态</th>
							<th>状态</th>
							<th>费用合计</th>
							<th>支付模式</th>
							<th>油气比例</th>
							<th>应付现金</th>
							<th>油气类型</th>
							<th>油气金额</th>
							<th>支付账户</th>
							<th>收款人</th>
							<th>开户行</th>
							<th>账号</th>
							<th>出纳</th>
							<th>审核人</th>
							<th>审核时间</th>
							<th>完成时间</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>

			<!-- 结算模态框-->
			<div class="modal fade" id="settlementModal" tabindex="-1" role="dialog" data-keyboard="false" data-backdrop="static" aria-hidden="true">
				<div class="modal-dialog" style="width: 1100px">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title">结算</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" id="chooseProjectFormId">
								<fieldset>
									<legend>
										<span style="font-size: 12px; color: red;">基本信息<i class='requireds'>*</i></span>
									</legend>
									<div class="form-group">
										<label class="col-md-1 control-label">流水号</label>
										<div class="col-md-2">
											<input name="shPackId" readonly="readonly" />
										</div>
										<label class="col-md-1 control-label">费用合计</label>
										<div class="col-md-2">
											<input name="freightChargeAmount" readonly="readonly" />
										</div>
										<label class="col-md-1 control-label">支付账户</label>
										<div class="col-md-2">
											<input name="provideBankAccount" readonly="readonly" />
										</div>
										<label class="col-md-1 control-label">操作人</label>
										<div class="col-md-2">
											<input name="createUserId" readonly="readonly" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-1 control-label">收款人</label>
										<div class="col-md-2">
											<input name="receiveUserId" readonly="readonly" />
										</div>
										<label class="col-md-1 control-label">开户行</label>
										<div class="col-md-2">
											<input name="receiveOpenBank" readonly="readonly" />
										</div>
										<label class="col-md-1 control-label">行号</label>
										<div class="col-md-2">
											<input name="receiveOpenBankNum" readonly="readonly" />
										</div>
										<label class="col-md-1 control-label">银行账号</label>
										<div class="col-md-2">
											<input name="receiveBankAccount" readonly="readonly" />
										</div>
									</div>
								</fieldset>
								<fieldset>
									<legend>
										<span style="font-size: 12px; color: red;">对账明细
<i class='requireds'>*</i></span>
									</legend>
									<div class="form-group">
										<label class="col-md-1 control-label">支付模式</label>
										<div class="col-md-2">
											<input name="paymentId" readonly="readonly" />
										</div>
										<label class="col-md-1 control-label">支付比例</label>
										<div class="col-md-2">
											<input name="payRatio" readonly="readonly" />
										</div>
										<label class="col-md-1 control-label">应付现金</label>
										<div class="col-md-2">
											<input name="cashAmount" readonly="readonly" />
										</div>
										<label class="col-md-1 control-label">油气类型</label>
										<div class="col-md-2">
											<input name="suppliesType" readonly="readonly" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-1 control-label">油气金额</label>
										<div class="col-md-2">
											<input name="suppliesAmount" readonly="readonly" />
										</div>
									</div>
								</fieldset>
							</form>
						</div>
						<div class="modal-footer">
							<button class="btn btn-primary" id="btnDriverAccountsQueryProjectId">结算</button>
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