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
<script src="${pageContext.request.contextPath}/js/finance/settlementAccountability/dotAccounts.js"></script>
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
</head>

<body>
<div class="container-fluid">
    <div class="panel panel-default">
         <div class="panel-body">
					   <form style='display:flex;flex-direction:row;' class="form-inline maxWidth" id="frmDotAccountsQuery">
						   <input type="hidden" name="projectId"/>
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
					            <button class="btn-xs btn-primary btn-query pull-right" id="btnDotAccountsConditionQuery">查询</button>
						    </div>
						  </form>
        </div>
    </div>
    <div class="box box-primary table-responsive">
			<div class="box-tools">
				<button class="btn-xs btn-primary tableActive" id="btnDotAccountsDetail">对账明细</button>
				<button class="btn-xs btn-primary" id="btnDotAccountsHistoryDetail">历史明细</button>
		    <button class="btn-xs btn-primary pull-right" id="btnDotAccountsSettlement">油气结算</button>
		    <!-- <button class="btn-xs btn-primary" id="btnDotAccountsFinanceAudit">财务审核</button>
        <button class="btn-xs btn-danger" id="btnDotAccountsAgainstAudit">反审核</button> -->
		  </div>
			<table id="tblDotAccounts" class="table table-striped table-bordered" cellspacing="0" width="100%">
			  <thead>
					<tr>
						<th><label class="demo--label"><input class="demo--checkbox" type="checkbox" ><span class="demo--checkboxInput"></span></label></th>
						<th>项目编号</th>
						<th>分支机构</th>
						<th>流水号</th>
						<th>执行状态</th>
						<th>油气类型</th>
						<th>油气金额</th>
						<!-- <th>油气卡号</th> -->
						<th>领取方式</th>
						<th>领取人</th>
						<th>联系方式</th>
						<!-- <th>执行人</th>
						<th>执行时间</th>
						<th>交账时间</th> -->
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
                    <h4 class="modal-title">基本信息</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="chooseProjectFormId">
                    <input name="receiveUserId" type="hidden" />
                    <fieldset>
                      <legend>
												<span style="font-size: 12px; color: red;">基本信息
<i class='requireds'>*</i></span>
											</legend>
                        <div class="form-group">
                            <label class="col-md-1 control-label">项目编号</label>
                            <div class="col-md-2">
                            		<input name="projectCode" readonly="readonly" />
                            </div>
                            <label class="col-md-1 control-label">流水号</label>
                            <div class="col-md-2">
                                <input name="shPackId" readonly="readonly" />
                            </div>
                            <label class="col-md-1 control-label">分支机构</label>
                            <div class="col-md-2">
                                <input name="branchGroupName" readonly="readonly" />
                            </div>
                            <label class="col-md-1 control-label">待支付金额</label>
                            <div class="col-md-2">
                                <input name="suppliesAmount" readonly="readonly" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-1 control-label">领取人</label>
                            <div class="col-md-2">
                                <input name="receiveUserName" readonly="readonly" />
                            </div>
                            <label class="col-md-1 control-label">领取方式</label>
                            <div class="col-md-2">
                                <select id="suppliesReceiveType" name="suppliesReceiveType" disabled="disabled">
                                	<option value="0">网点领取</option>
                                	<option value="1">人员配送</option>
                                </select>
                            </div>
                            <label class="col-md-1 control-label">油气类型</label>
                            <div class="col-md-2">
                                <input name="suppliesTypeName" readonly="readonly" />
                            </div>
                        </div>
                        </fieldset>
                        <fieldset id="settleInformation">
                          <legend>
							<span style="font-size: 12px; color: red;">结算信息</span><i class='requireds'>*</i>
						  </legend>
						  <div class="box box-primary">
							<div class="box-tools" name="boxtools">
								<div class="form-group">
	                        		<label class="col-md-1 control-label">油气卡号</label>
	                            	<div class="col-md-3">
	                                <!-- <input name="suppliesNum" /> -->
	                                <select name="suppliesNum"></select>
	                            	</div>
		                            <button class="btn btn-primary" id="btnDotAccountsAddSupplies">添加</button>
		                            <button class="btn btn-primary" id="btnDotAccountsAddSuppliesDel">删除</button>
		                        </div>
	                        </div>
                        <table id="tblPoint" class="table table-striped table-bordered" cellspacing="0" width="100%">
						  <thead>
								<tr>
									<th><label class="demo--label"><input class="demo--checkbox" type="checkbox" ><span class="demo--checkboxInput"></span></label></th>
									<th>流水号</th>
									<th>油气类型</th>
									<th>油气金额</th>
									<th>油气卡号</th>
								</tr>
						  </thead>
						  <tbody></tbody>
						</table>
						</div>
                        </fieldset>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" id="btnDotAccountsQueryProjectId">结算</button>
                    <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
