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
		<script src="${pageContext.request.contextPath}/static/js/util.js"></script>
		<script src="${pageContext.request.contextPath}/js/business/project/projectCheck.js"></script>

	</head>

	<body>
		<div class="container-fluid">
			<div class="panel panel-default">
				<div class="panel-body">
					<form style='display:flex;flex-direction:row;' class="form-inline maxWidth" id="frmProjectCheck2Query">
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
						<!-- <div class="form-group dataTimes">
							<label>&emsp;日期从：</label>
							<input name="beginDate" id="d244" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
							<i class="fa fa-calendar-check-o"></i>
						</div>
						<div class="form-group dataTimes">
							<label>至</label>
							<input id="d244" name='endDate' type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
							<i class="fa  fa-calendar-check-o"></i>
						</div> -->
						<div class="form-group">
							<button class="btn-xs btn-primary btn-query pull-right" id="btnProjectCheck2ConditionQuery">查询</button>
						</div>
					</form>
				</div>
			</div>
			<div class="box box-primary table-responsive">
				<div class="box-tools">
					<button class="btn-xs btn-primary tableActive" id="projectCheck1">项目明细</button>
					<button class="btn-xs btn-primary" id="projectCheck2">项目核查</button>
					<c:forEach items='${permissions }' var='permission' >
						<c:if test="${permission.code=='btnProjectCheckExport'}">
							<button class="btn-xs btn-primary pull-right exprotbtn" id="btnProjectCheckExport">导出</button>
						</c:if>
					</c:forEach>
				</div>
				<table id="tblProjectCheck" class="table table-striped table-bordered" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>
								<label class="demo--label"><input class="demo--checkbox" type="checkbox" ><span class="demo--checkboxInput"></span></label>
							</th>
							<th>项目编号</th>
							<th>项目类型</th>
							<th>联运模式</th>
							<th>分支机构</th>
							<th>发货企业</th>
							<th>收货企业</th>
							<th>货物品名</th>
							<th>应付现金</th>
							<th>应付油气</th>
							<th>汽运到货吨位</th>
							<th>汽运费用</th>
							<th>汽运扣损金额</th>
							<th>接取到货吨位</th>
							<th>接取费用</th>
							<th>接取扣损金额</th>
							<th>火运发送吨位</th>
							<th>火运费用</th>
							<th>损耗吨位</th>
							<th>送达到货吨位</th>
							<th>送达费用</th>
							<th>扣损金额</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
		</div>
	</body>

</html>