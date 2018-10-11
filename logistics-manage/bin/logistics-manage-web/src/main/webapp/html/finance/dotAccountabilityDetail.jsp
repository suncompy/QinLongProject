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
<script src="${pageContext.request.contextPath}/js/finance/settlementAccountability/dotAccountabilityDetail.js"></script>
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
						   <!--  <div class="form-group">
						        <label>项目编号：</label>
						        <input name="projectCode" type="text">
					        </div> -->
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
    			<button class="btn-xs btn-primary " id="btnDotAccountability">列表</button>
				<button class="btn-xs btn-primary tableActive" id="btnDotAccountabilityDetail">交账明细</button>
				
				<c:forEach items='${permissions }' var='permission' >
					<c:if test="${permission.code=='dotAccountabilityAuit'}">
						  <button class="btn-xs btn-primary pull-right" id="accountDetails">财务审核</button>
					</c:if>
			 	</c:forEach>
				
				<!-- <button class="btn-xs btn-primary pull-right" >解包</button> -->
				<!-- <button class="btn-xs btn-primary pull-right" id="accountpacking">交账打包</button> -->
		  </div>
			<table id="tblDotAccountability" class="table table-striped table-bordered" cellspacing="0" width="100%">
			  <thead>
					<tr>
						<th><label class="demo--label"><input class="demo--checkbox" type="checkbox" ><span class="demo--checkboxInput"></span></label></th>
						<th>分支机构</th>
						<th>状态</th>
						<th>交账日期</th>
						<th>总单数</th>
						<th>油气总金额</th>
					    <th>交账人</th>
					    <th>审核人</th>
					    <th>审核时间</th>
					</tr>
			  </thead>
			  <tbody></tbody>
			</table>
    </div>
	<div class="box box-primary">
        <div class="panel-body">
            <button class="btn-xs btn-primary tableActive" id="btnPointEdit" disabled="disabled">详情</button>
        </div>
		<table id="tblPoint" class="table table-striped table-bordered" cellspacing="0" width="100%">
	    <thead>
		<tr>
			<th>
				<label class="demo--label"><input class="demo--checkbox" type="checkbox" ><span class="demo--checkboxInput"></span></label>
			</th>
			<th>项目编号</th>
			<th>分支机构</th>
			<th>流水号</th>
			<th>执行状态</th>
			<th>油气类型</th>
			<th>油气金额</th>
			<th>领取方式</th>
			<th>领取人</th>
			<th>联系方式</th>
		</tr>
	    </thead>
        <tbody></tbody>
	</table>
    </div>    
</div>
</body>
</html>
