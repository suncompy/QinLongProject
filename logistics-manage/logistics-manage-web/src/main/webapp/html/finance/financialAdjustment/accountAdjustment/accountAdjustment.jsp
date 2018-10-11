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
		<script src="${pageContext.request.contextPath}/js/finance/accountAdjustment/accountAdjustment.js"></script>

	</head>

	<body>
		<div class="container-fluid">
			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-inline maxWidth" id="frmCustomerAccountsQuery">
						<input type="hidden" name="projectId" />
						<div class="form-group">
							<label>项目编号：</label>
							<input name="projectCode" type="text">
						</div>
						<div class="form-group">
							<label>始发地：</label>
							<input name="beginSite" type="text">
						</div>
						<div class="form-group">
							<label>终点地：</label>
							<input name="endSite" type="text">
						</div>
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
							<button class="btn-xs btn-primary btn-query pull-right" id="btnCustomerAccountsConditionQuery">查询</button>
						</div>
					</form>
				</div>
			</div>
			<div class="box box-primary" id="panel1">
				<div class="box-tools">
				 <per:hasUrlPerm code="accountAdjustment">
				    <button class="btn-xs btn-primary pull-right" id="btnCustomerAccountsSettlement">账户调整</button>
				 </per:hasUrlPerm>
				</div>
				<table id="tblCustomerAccounts" class="table table-striped table-bordered table-responsive" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th><label class="demo--label"><input class="demo--checkbox" type="checkbox" ><span class="demo--checkboxInput"></span></label></th>
							<th>支出账户</th>
							<th>支出账号</th>
							<th>存入账户</th>
							<th>存入账号</th>
							<th>支付方式</th>
							<th>调整金额</th>
							<th>调整人</th>
							<th>调整时间</th>
							<th>流水号</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>

			<!-- 账户调整模态框-->
			<div class="modal fade" id="settlementModal" tabindex="-1" role="dialog" data-keyboard="false" data-backdrop="static" aria-hidden="true">
				<div class="modal-dialog" style="width: 1100px">
					<div class="modal-content">
						<div class="modal-header">
                    		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    		<h4 class="modal-title">账户调整</h4>
                		</div>
						<div class="modal-body">
							<form class="form-horizontal" id="depositForm">
								<fieldset>
									<legend>
										<span style="font-size: 12px; color: red;">支出方<i class='requireds'>*</i></span>
									</legend>
									<div class="form-group">
										<label class="col-md-1 control-label">账户种类</label>
										<div class="col-md-2">
											<select id="payType" >
												<option></option>
												<option value="0">客户</option>
												<option value="1">站点</option>
												<option value="2">公司</option>
											</select>
										</div>
										<label class="col-md-1 control-label">支出单位</label>
										<div class="col-md-2">
											<select id="payUnitId" ></select>
												<option></option>
											</select>
										</div>
										<label class="col-md-1 control-label">支出账户</label>
										<div class="col-md-2">
											<select id="payAccountId" name="expenditureAccountId"></select>
												<option></option>
											</select>
										</div>
										<label class="col-md-1 control-label">支出账号</label>
										<div class="col-md-2">
											<input type="text" class="inputbg" id="payNumber"  unselectable="on" readonly/>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-1 control-label">支出行号</label>
										<div class="col-md-2">
											<input type="text" class="inputbg"id="payBankNumber"  unselectable="on" readonly/>
										</div>
										<label class="col-md-1 control-label">纳税识别号</label>
										<div class="col-md-2">
											<input type="text" class="inputbg inputLenth" id="payTaxNumber" unselectable="on" readonly/>
										</div>
										<label class="col-md-1 control-label">账户余额</label>
										<div class="col-md-2">
											<input type="text" class="inputbg inputLenth" id="payBalance"  unselectable="on" readonly/>
										</div>
									</div>
								</fieldset>
								<fieldset>
									<legend>
										<span style="font-size: 12px; color: red;">存入方<i class='requireds'>*</i></span>
									</legend>
									<div class="form-group">
										<label class="col-md-1 control-label">账户种类</label>
										<div class="col-md-2">
											<select id="receiveType"   >
												<option></option>
												<option value="0">客户</option>
												<option value="1">站点</option>
												<option value="2">公司</option>
											</select>
										</div>
										<label class="col-md-1 control-label">收款单位</label>
										<div class="col-md-2">
											<select id="receiveUnitId" ></select>
												<option></option>
											</select>
										</div>
										<label class="col-md-1 control-label">收款账户</label>
										<div class="col-md-2">
											<select id="receiveAccountId" name="depositAccountId"></select>
												<option></option>
											</select>
										</div>
										<label class="col-md-1 control-label">收款账号</label>
										<div class="col-md-2">
											<input type="text" class="inputbg" id="receiveNumber"  unselectable="on" readonly/>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-1 control-label">收款行号</label>
										<div class="col-md-2">
											<input type="text" class="inputbg" id="receiveBankNumber"  unselectable="on" readonly/>
										</div>
										<label class="col-md-1 control-label">纳税识别号</label>
										<div class="col-md-2">
											<input type="text" class="inputbg inputLenth" id="receiveTaxNumber" unselectable="on" readonly/>
										</div>
										<label class="col-md-1 control-label">账户余额</label>
										<div class="col-md-2">
											<input type="text" class="inputbg inputLenth" id="receiveMoney" unselectable="on" readonly/>
										</div>
									</div>
								</fieldset>
								
								<fieldset>
									<legend>
										<span style="font-size: 12px; color: red;">金额信息<i class='requireds'>*</i></span>
									</legend>
									<div class="form-group">
										<label class="col-md-1 control-label">支付方式</label>
										<div class="col-md-2">
											<select name="payment">
												<option></option>
												<option value="1">现金</option>
												<option value="2">支票</option>
												<option value="3">网银</option>
											</select>
										</div>
										<label class="col-md-1 control-label">金额</label>
										<div class="col-md-2">
											<input name="adjustMoney"  />
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
			<!-- 未选择数据提醒模态框 -->
			<div class="modal fade deletRemindModal" id="accountDetailsModalNo" tabindex="-1" role="dialog" id="nullModal" data-backdrop="static">
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
									<button type="button" class="btn sureBtn">确定</button>
									<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 提醒模态框 -->
			<div class="modal fade deletRemindModal" id="accountDetailsModal" tabindex="-1" role="dialog" id="deltialModal" data-backdrop="static">
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
			<!-- 历史对账导出 -->
			<!-- <div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="historyExportModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="body-content">
							<div><span>您已选择</span><b class="errnums">14</b> <span>条数据，确认导出？</span></div>
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
		</div> -->

		</div>
	</body>

</html>