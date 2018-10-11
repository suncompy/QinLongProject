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
<script src="${pageContext.request.contextPath}/js/finance/custSettle.js"></script>
</head>

<body>
<div class="container-fluid">
    <div class="panel panel-default">
         <div class="panel-body">
	  <form class="form-inline maxWidth" id="frmCustSettleQuery">
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
            <button class="btn-xs btn-primary btn-query pull-right" id="btnCustSettleQuery">查询</button>
	    </div>
	  </form>
        </div>
    </div>
    <div class="box box-primary table-responsive">
	<div class="box-tools">
		<button class="btn-xs btn-primary tableActive" id="btnCustSettleList">结算列表</button>
        <button class="btn-xs btn-primary" id="btnCustSettleListHis">历史结算</button>
        <button class="btn-xs btn-primary pull-right" id="btnCustSettleAdd">结算</button>
    </div>
	<table id="tblCustSettle" class="table table-striped table-bordered" cellspacing="0" width="100%">
	    <thead>
		<tr>
		    <th>主键</th>
		    <th>日期</th>
		    <th>往来类别</th>
		    <th>结算状态</th>
		    <th>存入账号</th>
		    <th>项目编号</th>
		    <th>流水号</th>
		    <th>总数量</th>
		    <th>发票金额</th>
		    <th>已结算金额</th>
		    <th>待结算金额</th>
		    <th>更新时间</th>
		</tr>
	    </thead>
	    <tbody></tbody>
	</table>
    </div>
    <div class="box box-primary">
        <div class="box-tools">
            <button class="btn-xs btn-primary pull-right" id="btnCustSettleFinAudit">财务审核</button>
        </div>
	<table id="tblCustSettleDetail" class="table table-striped table-bordered" cellspacing="0" width="100%">
	    <thead>
		<tr>
		    <th>主键</th>
		    <th>项目编号</th>
		    <th>流水号</th>
		    <th>结算状态</th>
		    <th>结算单位</th>
		    <th>人员</th>
		    <th>申请结算金额</th>
		    <th>结算方式</th>
		    <th>操作人</th>
		    <th>审核人</th>
		    <th>审核时间</th>
		</tr>
	    </thead>
        <tbody></tbody>
	</table>
    </div>
</div>
  	<!-- 结算模态框-->
	<div class="modal fade" id="mdlCustSettleDetail" tabindex="-1" role="dialog" data-keyboard="false" data-backdrop="static" aria-hidden="true">
		<div class="modal-dialog" style="width: 1100px">
			<div class="modal-content">
				<div class="modal-body">
					<form class="form-horizontal" id="frmCustSettleDetail">
					 <input class="hidden" name="settleId" />
						<fieldset>
							<legend>
								<span style="font-size: 12px; color: red;">项目信息</span>
							</legend>
							<div class="form-group">
								<label class="col-md-1 control-label">项目编号</label>
								<div class="col-md-2">
									<input name="projectCode" disabled="disabled" />
								</div>
								<label class="col-md-1 control-label">总数量</label>
								<div class="col-md-2">
									<input name="totalOrder" disabled="disabled" />
								</div>
								<label class="col-md-1 control-label">往来类别</label>
								<div class="col-md-2">
									<input name="provideCompanyName" disabled="disabled" />
								</div>
								<label class="col-md-1 control-label">流水号</label>
								<div class="col-md-2">
									<input name="packId" disabled="disabled" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-1 control-label">发票金额</label>
								<div class="col-md-2">
									<input name="totalMoney" disabled="disabled" />
								</div>
								<label class="col-md-1 control-label">待结算金额</label>
								<div class="col-md-2">
									<input name="settingMoney" disabled="disabled" />
								</div>
								<label class="col-md-1 control-label">已申请金额</label>
								<div class="col-md-2">
									<input name="auditingMoney" disabled="disabled" />
								</div>
								<label class="col-md-1 control-label">已审核金额</label>
								<div class="col-md-2">
									<input name="auditedMoney" disabled="disabled" />
								</div>
							</div>
						</fieldset>
						<fieldset>
							<legend>
								<span style="font-size: 12px; color: red;">结算信息<i class='requireds'>*</i></span>
							</legend>
							<div class="form-group">
								<label class="col-md-1 control-label">结算单位</label>
								<div class="col-md-2">
									<input name="settleOrg" />
								</div>
								<label class="col-md-1 control-label">人员</label>
								<div class="col-md-2">
									<select name="settleUserId" ></select>
								</div>
								<label class="col-md-1 control-label">结算方式</label>
								<div class="col-md-2">
									<select name="settleModel" id="settleModel">
										<option value="">请选择</option>
										<option value="0">现金</option>
										<option value="1">支票</option>
										<option value="2">转账</option>
									</select>
								</div>
								<label class="col-md-1 control-label">结算金额</label>
								<div class="col-md-2">
									<input name="applyMoney" />
								</div>
							</div>
						</fieldset>
					</form>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" id="btnCustSettleDetailSave">确定</button>
					<button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">取消</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
