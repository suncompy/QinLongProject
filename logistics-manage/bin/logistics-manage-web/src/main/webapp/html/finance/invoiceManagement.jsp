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
		<script src="${pageContext.request.contextPath}/js/finance/invoiceManagement.js"></script>

	</head>

	<body>
		<div class="container-fluid">
			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-inline maxWidth" id="frmInvoiceManagementQuery">
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
							<button class="btn-xs btn-primary btn-query pull-right" id="btnInvoiceManagementQuery">查询</button>
						</div>
					</form>
				</div>
			</div>
			<div class="box box-primary table-responsive">
				<div class="box-tools">
					<button class="btn-xs btn-primary tableActive" id="btnInvoiceManagementDetail">发票列表</button>
          			
          			<per:hasUrlPerm code="invoiceManagementCancelNoAudit">
						<button class="btn-xs btn-primary pull-right" id="btnInvoiceManagementCancelNoAudit">发票作废反审核</button>
					</per:hasUrlPerm>
          			<per:hasUrlPerm code="invoiceManagementCancelAudit">
						<button class="btn-xs btn-primary pull-right" id="btnInvoiceManagementCancelAudit">发票作废审核</button>
					</per:hasUrlPerm>
          			<per:hasUrlPerm code="invoiceManagementCancel">
						<button class="btn-xs btn-primary pull-right" id="btnInvoiceManagementCancel">发票作废</button>
					</per:hasUrlPerm>
          			<per:hasUrlPerm code="invoiceManagementAdd">
						<button class="btn-xs btn-primary pull-right" id="btnInvoiceManagementAdd">发票登入</button>
					</per:hasUrlPerm>
				</div>
				<table id="tblInvoiceManagement" class="table table-striped table-bordered" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th><label class="demo--label"><input
								class="demo--checkbox" type="checkbox"><span
								class="demo--checkboxInput"></span></label></th>
							<th>项目编号</th>
							<th>流水号</th>
							<th>发生网点</th>
							<th>发生时间</th>
							<th>业务类型</th>
							<th>业务时间</th>
							<th>起始地</th>
							<th>运抵地</th>
							<th>发票类型</th>
							<th>状态</th>
							<th>受票方</th>
							<th>纳税识别号</th>
							<th>开票项目</th>
							<th>开票金额</th>
							<th>税率</th>
							<th>税额</th>
							<th>合计金额</th>
							<th>出具方</th>
							<th>纳税识别号</th>
							<th>经办人</th>
							<th>审核人</th>
							<th>审核时间</th>
							<th>退回人</th>
							<th>退回部门</th>
							<th>退回时间</th>
							<th>备注</th>
							<th>更新时间</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>

			<!-- 发票登入模态框-->
			<div class="modal fade" id="invoiceManagementAddModal" tabindex="-1" role="dialog" data-keyboard="false" data-backdrop="static" aria-hidden="true">
				<div class="modal-dialog" style="width: 1100px">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h5 class="modal-title">发票登入</h5>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" id="chooseProjectFormId">
								<fieldset>
									<legend>
										<span style="font-size: 12px; color: red;">基本信息<i class='requireds'>*</i></span>
									</legend>
									<div class="form-group">
										<label class="col-md-1 control-label">编号</label>
										<div class="col-md-2">
											<input name="projectCode" readonly="readonly"/>
										</div>
										<label class="col-md-1 control-label">发生网点</label>
										<div class="col-md-2">
											<input name="branchGroupName" readonly="readonly"/>
										</div>
										<label class="col-md-1 control-label">发生时间</label>
										<div class="col-md-2">
											<input name="createDate" readonly="readonly"/>
										</div>
										<label class="col-md-1 control-label">业务类型</label>
										<div class="col-md-2">
											<input name="packType" readonly="readonly"/>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-1 control-label">业务时间</label>
										<div class="col-md-2">
											<input name="startDate" readonly="readonly"/>
										</div>
										<label class="col-md-1 control-label">开票项目</label>
										<div class="col-md-2">
											<input name="invoiceType" id="invoiceType" readonly="readonly"/>
										</div>
										<!-- <label class="col-md-1 control-label">名称</label>
										<div class="col-md-2">
											<input name="" />
										</div> -->
										<label class="col-md-1 control-label">起始地</label>
										<div class="col-md-2">
											<input name="beginAddress" readonly="readonly"/>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-1 control-label">运抵地</label>
										<div class="col-md-2">
											<input name="arrivePlaceAddress" readonly="readonly"/>
										</div>
									</div>
								</fieldset>
								<fieldset>
									<legend>
										<span style="font-size: 12px; color: red;">登入信息
<i class='requireds'>*</i></span>
									</legend>
									<div class="form-group">
										<label class="col-md-1 control-label">受票方种类</label>
										<div class="col-md-2">
											<!-- <select id="receiveCompanyId" name="receiveCompanyId"></select> -->
											<select id="payType" name="payType">
												<option value=""></option>
												<option value="0">客户</option>
												<option value="1">站点</option>
												<option value="2">公司</option>
											</select>
										</div>
										<label class="col-md-1 control-label">受票方单位</label>
										<div class="col-md-2">
											<select id="payUnitId" name="payUnitId"></select>
											<input type="hidden" class="inputbg" id="payUnitName" name="payUnitName" />
										</div>
										<label class="col-md-1 control-label">支出账户</label>
										<div class="col-md-2">
											<!-- <input id="receiveBankAccount" name="receiveBankAccount" /> -->
											<select id="payAccountId" name="payAccountId"></select>
											<input type="hidden" class="inputbg" id="payAccountName" name="payAccountName" />
										</div>
										<label class="col-md-1 control-label">纳税识别号</label>
										<div class="col-md-2">
											<!-- <input id="receiveDutyParagraph" name="receiveDutyParagraph" /> -->
											<input type="text" class="inputbg inputLenth" id="payTaxNumber" name="payTaxNumber" unselectable="on" readonly/>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-1 control-label">支出行号</label>
										<div class="col-md-2">
											<!-- <input id="receiveOpenBank" name="receiveOpenBank" /> -->
											<input type="text" class="inputbg"id="payBankNumber" name="payBankNumber" unselectable="on" readonly/>
										</div> 
										<label class="col-md-1 control-label">支出账号</label>
										<div class="col-md-2">
											<input type="text" class="inputbg" id="payNumber" name="payNumber" unselectable="on" readonly/>
										</div>
										<!-- <label class="col-md-1 control-label">地址</label>
										<div class="col-md-2">
											<input id="receiveAddressCode" name="receiveAddressCode" />
										</div>
										<label class="col-md-1 control-label">电话</label>
										<div class="col-md-2">
											<input id="receiveStationPhone" name="receiveStationPhone" />
										</div> -->
									</div>
									<div class="form-group">
										<label class="col-md-1 control-label">出具方种类</label>
										<div class="col-md-2">
											<!-- <select id="provideCompanyId" name="provideCompanyId"></select> -->
											<select id="receiveType" name="receiveType"  >
												<option value=""></option>
												<option value="0">客户</option>
												<option value="1">站点</option>
												<option value="2">公司</option>
											</select>
										</div>
										<label class="col-md-1 control-label">出具方单位</label>
										<div class="col-md-2">
											<select id="receiveUnitId" name="receiveUnitId"></select>
											<input type="hidden" class="inputbg" id="receiveUnitName" name="receiveUnitName" />
										</div>
										<label class="col-md-1 control-label">收款账户</label>
										<div class="col-md-2">
											<select id="receiveAccountId" name="receiveAccountId"></select>
											<input type="hidden" class="inputbg" id="receiveAccountName" name="receiveAccountName" />
											<!-- <input id="provideDutyParagraph" name="provideDutyParagraph" /> -->
										</div>
										<label class="col-md-1 control-label">纳税识别号</label>
										<div class="col-md-2">
											<!-- <input id="provideDutyParagraph" name="provideDutyParagraph" /> -->
											<input type="text" class="inputbg inputLenth" id="receiveTaxNumber" name="receiveTaxNumber" unselectable="on" readonly/>
										</div>
										<!-- <label class="col-md-1 control-label">开户银行</label>
										<div class="col-md-2">
											<input id="provideOpenBank" name="provideOpenBank" />
										</div>
										<label class="col-md-1 control-label">开户账户</label>
										<div class="col-md-2">
											<input id="provideBankAccount" name="provideBankAccount" />
										</div> -->
									</div>
									<div class="form-group">
										<label class="col-md-1 control-label">收款行号</label>
										<div class="col-md-2">
											<input type="text" class="inputbg" id="receiveBankNumber" name="receiveBankNumber" unselectable="on" readonly/>
										</div> 
										<label class="col-md-1 control-label">收款账号</label>
										<div class="col-md-2">
											<input type="text" class="inputbg" id="receiveNumber" name="receiveNumber" unselectable="on" readonly/>
										</div>
										<!-- <label class="col-md-1 control-label">地址</label>
										<div class="col-md-2">
											<input id="provideAddressCode" name="provideAddressCode" />
										</div>
										<label class="col-md-1 control-label">电话</label>
										<div class="col-md-2">
											<input id="provideStationPhone" name="provideStationPhone" />
										</div> -->
									</div>
								</fieldset>
								<fieldset>
									<legend>
										<span style="font-size: 12px; color: red;">金额
<i class='requireds'>*</i></span>
									</legend>
									<div class="form-group">
										<label class="col-md-1 control-label">开票金额</label>
										<div class="col-md-2">
											<input name="invoiceMoney" id="invoiceMoney" readonly="readonly" />
										</div>
										<label class="col-md-1 control-label">税率</label>
										<div class="col-md-2">
											<input name="taxRate" id="taxRate" readonly="readonly"/>
										</div>
										<label class="col-md-1 control-label">税额</label>
										<div class="col-md-2">
											<input name="taxMoney" id="taxMoney" readonly="readonly" />
										</div>
										<label class="col-md-1 control-label">合计金额</label>
										<div class="col-md-2">
											<input name="totalMoney" id="totalMoney" readonly="readonly" />
										</div>
									</div>
								</fieldset>
							</form>
						</div>
						<div class="modal-footer">
							<button class="btn-xs btn-primary" id="btnInvoiceManagementQueryProjectId">登入</button>
							<button class="btn-xs btn-danger" data-dismiss="modal" aria-hidden="true">取消</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>