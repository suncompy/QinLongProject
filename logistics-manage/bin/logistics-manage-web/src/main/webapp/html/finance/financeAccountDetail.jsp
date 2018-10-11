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
		<script src="${pageContext.request.contextPath}/js/finance/financeAccountDetail.js"></script>

	</head>

	<body>
		<div class="container-fluid">
			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-inline maxWidth" id="frmFinanceAccountDetailQuery">
						<input type="hidden" name="projectId" />
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
							<button class="btn-xs btn-primary btn-query pull-right" id="btnFinanceAccountDetailQuery">查询</button>
						</div>
					</form>
				</div>
			</div>
			<div class="box box-primary table-responsive">
				<table id="tblFinanceAccountDetail" class="table table-striped table-bordered" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th><label class="demo--label"><input
								class="demo--checkbox" type="checkbox"><span
								class="demo--checkboxInput"></span></label></th>
							<th>时间</th>
							<th>支出方</th>
							<th>支出账户</th>
							<th>存入方</th>
							<th>存入账户</th>
							<th>金额</th>
							<th>操作人</th>
							<th>流水号</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
		</div>
	</body>

</html>