<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head lang="zh-CN">
<meta charset="UTF-8">
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/invoke.js"></script>
<!-- Bootstrap 3.3.5 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.min.css"/>-->
  <!-- Font Awesome -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/font-awesome.css">
  <!-- Datatables -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/lib/jquery/DataTables/css/dataTables.bootstrap.min.css" />
  <!-- confirm -->
  <link href="${pageContext.request.contextPath}/static/lib/jquery/jquery-confirm/css/jquery-confirm.css" rel="stylesheet"/>
  <!-- validator -->
  <link href="${pageContext.request.contextPath}/static/lib/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/common.css">
<!-- jQuery 1.12.2 -->
<script src="${pageContext.request.contextPath}/static/lib/jquery/jquery.min.js"></script>
<!-- Bootstrap 3.3.5 -->
<script src="${pageContext.request.contextPath}/static/lib/bootstrap/js/bootstrap.min.js"></script>
<!-- confirm -->
<script src="${pageContext.request.contextPath}/static/lib/jquery/jquery-confirm/js/jquery-confirm.js"></script>
<!-- Datatables -->
<script src="${pageContext.request.contextPath}/static/lib/jquery/DataTables/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/lib/jquery/DataTables/js/dataTables.bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/lib/jquery/fd/js/jquery.fddatatable.js"></script>
<!-- bootstrapValidator -->
<script src="${pageContext.request.contextPath}/static/lib/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script src="${pageContext.request.contextPath}/static/lib/bootstrapValidator/js/language/zh_CN.js"></script>
<script src="${pageContext.request.contextPath}/static/js/tips.js"></script>
<script src="${pageContext.request.contextPath}/static/js/common.js"></script>
<script src="${pageContext.request.contextPath}/js/finance/shortOrderFinance.js"></script>
</head>

<body>
<div class="container-fluid">
    <div class="panel panel-default">
         <div class="panel-body">
	   <form class="form-horizontal" id="frmShortOrderFinanceQuery">
	    <div class="form-group">
                <label class="col-md-1 control-label">项目id</label>
                <div class="col-md-2">
                    <input class="form-control" name="projectId" type="text">
                </div>
	        <div class="col-md-4">
                    <button class="btn btn-primary" id="btnShortOrderFinanceQuery">查询</button>
                </div>
            </div>
	  </form>
        </div>
    </div>
    <div class="box box-primary">
    <!--  
	<div class="box-header with-border">
	    <h6 class="box-title">短驳运单财务表信息</h6>
	</div>
	-->
	<div class="box-tools">
        <button class="btn-xs btn-primary" id="btnShortOrderFinanceBillingFreight">计算运费</button>
        <button class="btn-xs btn-primary" id="btnShortOrderFinanceBillingVerify">计费确认</button>
        <button class="btn-xs btn-danger" id="btnShortOrderFinanceAgainstVerify">反确认</button>
    	<button class="btn-xs btn-primary" id="btnShortOrderFinanceFinanceAudit">财务审核</button>
        <button class="btn-xs btn-danger" id="btnShortOrderFinanceAgainstAudit">反审核</button>
    </div>
	<table id="tblShortOrderFinance" class="table table-striped table-bordered" cellspacing="0" width="100%">
	    <thead>
		<tr>
			<th>
				<label class="demo--label"><input class="demo--checkbox" type="checkbox" ><span class="demo--checkboxInput"></span></label>
			</th>
			<th>项目编号</th>
			<th>运单编号</th>
			<th>分支机构</th>
			<th>项目类型</th>
			<th>财务状态</th>
			<th>确认计费时间</th>
			<th>财务审核时间</th>
			<th>发货企业</th>
			<th>收货企业</th>
			<th>货物品名</th>
			<th>承运车辆</th>
			<th>计费吨数</th>
			<th>计费件数</th>
			<th>运输费用</th>
			<th>扣损金额</th>
			<th>补加运费</th>
			<th>应付运费</th>
		</tr>
	    </thead>
	    <tbody></tbody>
	</table>
    </div>
    <div class="box box-primary">
    <!--<div class="panel-heading">支出明细</div>  -->
	
        <div class="panel-body">
            <button class="btn-xs btn-primary" id="btnPointEdit">计费变更</button>
        </div>
	<table id="tblPoint" class="table table-striped table-bordered" cellspacing="0" width="100%">
	    <thead>
		<tr>
			<th>
				<label class="demo--label"><input class="demo--checkbox" type="checkbox" ><span class="demo--checkboxInput"></span></label>
			</th>
			<th>运单编号</th>
			<th>承运车辆</th>
			<th>财务状态</th>
			<th>发货净重</th>
			<th>收货净重</th>
			<th>计费重量</th>
			<th>计费件数</th>
			<th>单价</th>
			<th>运输费用</th>
			<th>耗损</th>
			<th>是否加损</th>
			<th>扣损系数</th>
			<th>扣损单价</th>
			<th>扣损金额</th>
			<th>补加项目</th>
			<th>补加金额</th>
			<th>应付运费</th>
			<th>确认人</th>
			<th>审核人</th>
			<th>确认计费时间</th>
		</tr>
	    </thead>
        <tbody></tbody>
	</table>
    </div>
</div>
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="mdlShortOrderFinance" tabindex="-1" role="dialog" data-keyboard="false" data-backdrop="static" aria-hidden="true">
        <div class="modal-dialog large">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">短驳运单财务表信息</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="frmShortOrderFinance">
                        <input class="hidden" name="id" />
                        <div class="form-group">
                            <label class="col-md-2 control-label">主键</label>
                            <div class="col-md-4">
                                <input class="form-control" name="shOrderFinId" maxlength="32" type="text"/>
                            </div>
                            <label class="col-md-2 control-label">项目id</label>
                            <div class="col-md-4">
                                <input class="form-control" name="projectId" maxlength="9" type="number"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">运单id</label>
                            <div class="col-md-4">
                                <input class="form-control" name="orderId" maxlength="9" type="number"/>
                            </div>
                            <label class="col-md-2 control-label">财务状态(0-待确认、1-待计算、2-待审核、3-已审核)</label>
                            <div class="col-md-4">
                                <input class="form-control" name="financeStatus" maxlength="9" type="number"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">补交项目</label>
                            <div class="col-md-4">
                                <input class="form-control" name="arrearsItem" maxlength="200" type="text"/>
                            </div>
                            <label class="col-md-2 control-label">追加人id</label>
                            <div class="col-md-4">
                                <input class="form-control" name="addUserId" maxlength="9" type="number"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">追加人时间</label>
                            <div class="col-md-4">
                              <span style="position: relative; z-index: 9999;">
				<input class="form-control" name="addDate" type="text" data-provide="datepicker"/>
			      </span>
                            </div>
                            <label class="col-md-2 control-label">是否被打包(0-未打包 1-已打包)</label>
                            <div class="col-md-4">
                                <input class="form-control" name="packFlag" maxlength="9" type="number"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">是否删除(0-未删除 1-删除)</label>
                            <div class="col-md-4">
                                <input class="form-control" name="deleteFlag" maxlength="9" type="number"/>
                            </div>
                            <label class="col-md-2 control-label">运输费用</label>
                            <div class="col-md-4">
                                <input class="form-control" name="shortBargeCost" maxlength="10" type="number"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">扣损费用</label>
                            <div class="col-md-4">
                                <input class="form-control" name="buckleFigure" maxlength="10" type="number"/>
                            </div>
                            <label class="col-md-2 control-label">补加金额</label>
                            <div class="col-md-4">
                                <input class="form-control" name="subsidy" maxlength="10" type="number"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">应付费用</label>
                            <div class="col-md-4">
                                <input class="form-control" name="shouldPayFigure" maxlength="10" type="number"/>
                            </div>
                            <label class="col-md-2 control-label">确认计费时间</label>
                            <div class="col-md-4">
                              <span style="position: relative; z-index: 9999;">
				<input class="form-control" name="billingDate" type="text" data-provide="datepicker"/>
			      </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">财务审核时间</label>
                            <div class="col-md-4">
                              <span style="position: relative; z-index: 9999;">
				<input class="form-control" name="financeAuditDate" type="text" data-provide="datepicker"/>
			      </span>
                            </div>
                            <label class="col-md-2 control-label">完成时间</label>
                            <div class="col-md-4">
                              <span style="position: relative; z-index: 9999;">
				<input class="form-control" name="completeDate" type="text" data-provide="datepicker"/>
			      </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">异常时间</label>
                            <div class="col-md-4">
                              <span style="position: relative; z-index: 9999;">
				<input class="form-control" name="unusualDate" type="text" data-provide="datepicker"/>
			      </span>
                            </div>
                            <label class="col-md-2 control-label">异常提报人id</label>
                            <div class="col-md-4">
                                <input class="form-control" name="unusualUserId" maxlength="9" type="number"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">异常原因</label>
                            <div class="col-md-4">
                                <input class="form-control" name="unusualReason" maxlength="300" type="text"/>
                            </div>
                            <label class="col-md-2 control-label">创建时间</label>
                            <div class="col-md-4">
                              <span style="position: relative; z-index: 9999;">
				<input class="form-control" name="createDate" type="text" data-provide="datepicker"/>
			      </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">创建人</label>
                            <div class="col-md-4">
                                <input class="form-control" name="createUserId" maxlength="9" type="number"/>
                            </div>
                            <label class="col-md-2 control-label">修改时间</label>
                            <div class="col-md-4">
                              <span style="position: relative; z-index: 9999;">
				<input class="form-control" name="modifiyDate" type="text" data-provide="datepicker"/>
			      </span>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" id="btnShortOrderFinanceSave">保存</button>
                    <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">取消</button>
                </div>
            </div>
        </div>
    </div>
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="mdlPoint" tabindex="-1" role="dialog" data-keyboard="false" data-backdrop="static" aria-hidden="true">
        <div class="modal-dialog large">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">计费变更</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="frmPoint">
                    	<input name="shOrderFinId"  type="hidden"/>
                        <div class="form-group">
                            <label class="col-md-2 control-label">项目编号</label>
                            <div class="col-md-4">
                                <p name="projectCode" style="float:none;display: inline-block;vertical-align: middle;"></p>
                            </div>
                            <label class="col-md-2 control-label">运单编号</label>
                            <div class="col-md-4">
                                <p name="orderCode"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">分支机构</label>
                            <div class="col-md-4">
                                <p name="branchGroupName"></p>
                            </div>
                            <label class="col-md-2 control-label">承运车辆</label>
                            <div class="col-md-4">
                            <p name="carPlateNumber"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">发货企业</label>
                            <div class="col-md-4">
                                <p name="sendCompany"></p>
                            </div>
                            <label class="col-md-2 control-label">收货企业</label>
                            <div class="col-md-4">
                            <p name="receiptCompany"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">货物品名</label>
                            <div class="col-md-4">
                                <p name="cargoName"></p>
                            </div>
                        </div>
                         <div class="form-group">
                            <label class="col-md-2 control-label">补加项目</label>
                            <div class="col-md-4">
                                <select name="arrearsItem"></select>
                            </div>
                            <label class="col-md-2 control-label">追加金额</label>
                            <div class="col-md-4">
                                <input class="form-control" name="subsidy" maxlength="200" type="number"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" id="btnPointSave">确定</button>
                    <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">取消</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
