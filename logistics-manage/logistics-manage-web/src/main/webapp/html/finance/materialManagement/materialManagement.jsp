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
<script src="${pageContext.request.contextPath}/js/finance/materialManagement/materialManagement.js"></script>
</head>

<body>
<div class="container-fluid">
    <div class="panel panel-default">
         <div class="panel-body">
	  <form class="form-inline maxWidth" id="frmMaterialManagementQuery">
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
					         <button class="btn-xs btn-primary btn-query pull-right" id="btnMaterialManagementConditionQuery">查询</button>
						    </div>
						  </form>
        </div>
    </div>
    <div class="box box-primary table-responsive">
			<div class="box-tools">
					<button class="btn-xs btn-primary tableActive" id="btnMaterialManagementDetail">采购明细</button>
	        <!-- <button class="btn-xs btn-primary pull-right" id="btnOilGasCardQuery">查看</button> -->
	        <button class="btn-xs btn-danger pull-right" id="btnMaterialManagementAgainstAudit" style="display:none">反审核</button>
	        <per:hasUrlPerm code="btnMaterialManagementAudit">
		    	<button class="btn-xs btn-danger pull-right" id="btnMaterialManagementAudit">提交审核</button>
		 	</per:hasUrlPerm>
	        <per:hasUrlPerm code="btnAddMaterialManagement">
		    	 <button class="btn-xs btn-primary pull-right" id="btnAddMaterialManagement">采购登入</button>
		 	</per:hasUrlPerm>
	    </div>
			<table id="tblMaterialManagement" class="table table-striped table-bordered" cellspacing="0" width="100%">
		    <thead>
					<tr>
						<th>
							<label class="demo--label"><input class="demo--checkbox" type="checkbox" ><span class="demo--checkboxInput"></span></label>
						</th>
						<th>分支机构</th>
						<th>状态</th>
						<th>销售单位</th>
						<th>货物品名</th>
						<th>规格型号</th>
						<th>购入数量</th>
						<th>单位</th>
						<th>单价</th>
						<th>总金额</th>
						<th>采购人</th>
						<th>采购时间</th>
						<th>审核人</th>
						<th>审核时间</th>
					</tr>
		    </thead>
		    <tbody></tbody>
			</table>
    </div>
    
    <!-- 采购登入模态框-->
    <div class="modal fade" id="addMaterialManagementModal" tabindex="-1" role="dialog" data-keyboard="false" data-backdrop="static" aria-hidden="true">
        <div class="modal-dialog" style="width: 1100px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">采购登入</h4>
                </div>
                <div class="modal-body">
                <h5 class='h5Class'>采购登入<i class='requireds'>*</i></h5>
                    <form class="form-horizontal" id="chooseProjectFormId">
                        <div class="form-group">
                            <label class="col-md-1 control-label">分支机构</label>
                            <div class="col-md-2">
                               <select name="branchGroupId" id="branchGroupId"></select>
                            </div>
                           <!--  <label class="col-md-1 control-label">账户</label>
                            <div class="col-md-2">
                               <select name="companyAccount" id="companyAccount"></select>
                            </div> -->
                            <label class="col-md-1 control-label">销售单位</label>
                            <div class="col-md-2">
                                <input name="salesUnit" id="salesUnit" />
                            </div>
                            <label class="col-md-1 control-label">货物名称</label>
                            <div class="col-md-2">
                            		<input name="materialName" id="materialName" />
                            </div>
                            <label class="col-md-1 control-label">规格型号</label>
                            <div class="col-md-2">
                                <input name="materialType" id="materialType" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-1 control-label">单位</label>
                            <div class="col-md-2">
                                <input name="materialUnit" id="materialUnit" />
                            </div>
                            <label class="col-md-1 control-label">购入数量</label>
                            <div class="col-md-2">
                                <input name="materialNum" id="materialNum" />
                            </div>
                            <label class="col-md-1 control-label">单价</label>
                            <div class="col-md-2">
                                <input name="materialUnitPrice" id="materialUnitPrice" />
                            </div>
                            <label class="col-md-1 control-label">总金额</label>
                            <div class="col-md-2">
                                <input name="totalMoney" id="totalMoney" readonly="readonly" />
                            </div>
                        </div>
                        <!-- <div class="form-group">
                        		
                        </div> -->
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" id="btnMaterialModel">确定</button>
                    <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
