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
<script src="${pageContext.request.contextPath}/js/finance/materialManagement/oilGasCardManagement.js"></script>
</head>

<body>
<div class="container-fluid">
    <div class="panel panel-default">
         <div class="panel-body">
	  <form class="form-inline maxWidth" id="frmOilGasCardQuery">
						   <input type="hidden" name="projectId"/>
						    <div class="form-group" style="display:none">
						        <label>项目编号：</label>
						        <input name="projectCode" type="text">
					        </div>
					        <div class="form-group" style="display:none">
					          <label>始发地：</label>
					          <input name="beginSite" type="text">
							 		</div>
							    <div class="form-group" style="display:none">
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
							   <button class="btn-xs btn-primary btn-query pull-right" id="btnOilGasCardmdl" style='margin-left:5px'>模板下载</button>
				         <button class="btn-xs btn-primary btn-query pull-right" id="btnOilGasCardConditionQuery">查询</button>
					    </div>
						  </form>
        </div>
    </div>
    <div class="box box-primary table-responsive">
			<div class="box-tools">
					<button class="btn-xs btn-primary tableActive" id="btnOilGasCardDetail">采购明细</button>
	        <button class="btn-xs btn-danger pull-right" id="btnOilGasCardAgainstAudit" style="display:none">反审核</button>
	        <per:hasUrlPerm code="btnOilGasCardAudit">
		    	 <button class="btn-xs btn-danger pull-right" id="btnOilGasCardAudit">提交审核</button>
		 	</per:hasUrlPerm>
	        <per:hasUrlPerm code="btnAddOilGasCard">
		    	 <button class="btn-xs btn-primary pull-right" id="btnAddOilGasCard">采购登入</button>
		 	</per:hasUrlPerm>
	    </div>
			<table id="tblOilGasCard" class="table table-striped table-bordered" cellspacing="0" width="100%">
		    <thead>
					<tr>
						<th>
							<label class="demo--label"><input class="demo--checkbox" type="checkbox" ><span class="demo--checkboxInput"></span></label>
						</th>
						<th>日期</th>
						<th>流水号</th>
						<th>分支机构</th>
						<th>状态</th>
						<th>销售单位</th>
						<th>采购品类</th>
						<th>购入数量</th>
						<th>单位</th>
						<th>总金额</th>
						<th>采购人</th>
						<!-- <th>采购时间</th> -->
						<th>审核人</th>
						<th>审核时间</th>
					</tr>
		    </thead>
		    <tbody></tbody>
			</table>
    </div>
    
    <div class="box box-primary">
    <div class="box-tools">
		<per:hasUrlPerm code="btnOilGasCardImp">
		    <button class="btn-xs btn-primary" id="btnOilGasCardImp">导入卡号</button>
		 </per:hasUrlPerm>
    </div>
			<table id="tblPoint" class="table table-striped table-bordered" cellspacing="0" width="100%">
			    <thead>
						<tr>
							<th>
								<label class="demo--label"><input class="demo--checkbox" type="checkbox" ><span class="demo--checkboxInput"></span></label>
							</th>
							<th>油气卡号</th>
							<th>油气金额</th>
							<th>使用状态</th>
						</tr>
			    </thead>
		      <tbody></tbody>
			</table>
    </div>
    
    <!-- 采购登入模态框-->
    <div class="modal fade" id="addOilGasCardModal" tabindex="-1" role="dialog" data-keyboard="false" data-backdrop="static" aria-hidden="true">
        <div class="modal-dialog" style="width: 1100px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">采购登入</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="chooseProjectFormId">
                    <fieldset>
                    	<legend>
												<span style="font-size: 12px; color: red;">基本信息<i class='requireds'>*</i>
											</span></legend>
                        <div class="form-group">
                            <label class="col-md-1 control-label">分支机构</label>
                            <div class="col-md-2">
                               <select name="branchGroupId" id="branchGroupId"></select>
                            </div>
                            <label class="col-md-1 control-label">销售单位</label>
                            <div class="col-md-2">
                                <input name="salesUnit" id="salesUnit" />
                            </div>
                            <label class="col-md-1 control-label">采购品类</label>
                            <div class="col-md-2">
                                <select name="cardType" id="cardType">
                                	<option value="0">油卡</option>
                                	<option value="1">气卡</option>
                                </select>
                            </div>
                            <label class="col-md-1 control-label">单位</label>
                            <div class="col-md-2">
                                <select name="cardUnit" id="cardUnit">
                                  <option value="张">张</option>
                                </select>
                            </div>
                        </div>
                       <div class="form-group">
                            <label class="col-md-1 control-label">账户</label>
                            <div class="col-md-2">
                               <select name="companyAccount" id="companyAccount"></select>
                            </div>
                            <!-- <label class="col-md-1 control-label">总数量</label>
                            <div class="col-md-2">
                                <input name="cardCount" />
                            </div>
                            <label class="col-md-1 control-label">总金额</label>
                            <div class="col-md-2">
                                <input name="cardMoneyAll" />
                            </div> -->
                        </div>
                        </fieldset>
                       <!--  <fieldset>
                        	<legend>
														<span style="font-size: 12px; color: red;">卡号导入</span>
													</legend>
                        <div class="form-group">
                            <button class="btn btn-primary" id="btnOilGasCardImp">导入卡号</button>
                            <button class="btn btn-primary" id="btnOilGasCardmdl">模板下载</button>
                        </div>
                        </fieldset> -->
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" id="btnOilGasCardModel">确定</button>
                    <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">取消</button>
                </div>
            </div>
        </div>
    </div>
    <!-- 查看详情模态框（Modal） -->
    <!-- <div class="modal fade" id="queryOilGasCardModal" tabindex="-1" role="dialog" data-keyboard="false" data-backdrop="static" aria-hidden="true">
        <div class="modal-dialog large" style="width:1100px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">详情</h4>
                </div>
                <div class="modal-body">
                  <form class="form-horizontal" id="oilGasCardFormId">
                      <div class="form-group">
                          <label class="col-md-1 control-label">分支机构</label>
                          <div class="col-md-2">
                              <input name="branchGroupName" id="branchGroupName" readonly="readonly" />
                          </div>
                          <label class="col-md-1 control-label">销售单位</label>
                          <div class="col-md-2">
                              <input name="salesUnit" readonly="readonly" />
                          </div>
                          <label class="col-md-1 control-label">采购品类</label>
                          <div class="col-md-2">
                              <select name="cardType" disabled="disabled">
                              	<option value="0">油卡</option>
                              	<option value="1">气卡</option>
                              </select>
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-md-1 control-label">购入数量</label>
                          <div class="col-md-2">
                              <input name="cardCount" readonly="readonly" />
                          </div>
                          <label class="col-md-1 control-label">单位</label>
                          <div class="col-md-2">
                              <input name="cardUnit" readonly="readonly" />
                          </div>
                          <label class="col-md-1 control-label">总金额</label>
                          <div class="col-md-2">
                              <input name="cardMoneyAll" readonly="readonly" />
                          </div>
                          <label class="col-md-1 control-label">日期</label>
                          <div class="col-md-2">
                              <input name="purchaseDateD" readonly="readonly" />
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-md-1 control-label">采购人</label>
                          <div class="col-md-2">
                              <input name="purchaseName" readonly="readonly" />
                          </div>
                          <label class="col-md-1 control-label">采购时间</label>
                          <div class="col-md-2">
                              <input name="purchaseDate" readonly="readonly" />
                          </div>
                          <label class="col-md-1 control-label">审核人</label>
                          <div class="col-md-2">
                              <input name="auditName" readonly="readonly" />
                          </div>
                          <label class="col-md-1 control-label">审核时间</label>
                          <div class="col-md-2">
                              <input name="auditDate" readonly="readonly" />
                          </div>
                      </div>
                      
           <table id="queryOilGasCardByDate" data-toggle="table" class="table table-striped table-bordered" cellspacing="0" width="100%">
				    <thead>
							<tr>
								<th data-field="cardNum">卡号</th>
								<th data-field="cardMoney">金额</th>
								<th data-field="auditStatus">状态</th>
							</tr>
				    </thead>
				    <tbody></tbody>
					</table>
				</form>
                </div>
            </div>
        </div>
    </div> -->
    
</div>
</body>
</html>
