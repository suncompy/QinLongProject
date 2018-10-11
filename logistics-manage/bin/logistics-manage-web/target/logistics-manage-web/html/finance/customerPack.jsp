<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head lang="zh-CN">
<meta charset="UTF-8">
<!-- Bootstrap 3.3.5 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.min.css"/>
  <!-- Font Awesome -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/font-awesome.css">
  <!-- Datatables -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/lib/jquery/DataTables/css/dataTables.bootstrap.min.css" />
  <!-- confirm -->
  <link href="${pageContext.request.contextPath}/static/lib/jquery/jquery-confirm/css/jquery-confirm.css" rel="stylesheet"/>
  <!-- validator -->
  <link href="${pageContext.request.contextPath}/static/lib/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet"/>
<!-- jQuery 1.12.2 -->
<script src="${pageContext.request.contextPath}/static/lib/jquery/jquery.min.js"></script>
<!-- Bootstrap 3.3.5 -->
<script src="${pageContext.request.contextPath}/static/lib/bootstrap/js/bootstrap.min.js"></script>
<!-- confirm -->
<script src="${pageContext.request.contextPath}/static/lib/jquery/jquery-confirm/js/jquery-confirm.js"></script>

<!-- echarts -->
<script src="${pageContext.request.contextPath}/static/lib/graphic/echart/echarts.js"></script>
<!-- Datatables -->
<script src="${pageContext.request.contextPath}/static/lib/jquery/DataTables/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/lib/jquery/DataTables/js/dataTables.bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/lib/jquery/fd/js/jquery.fddatatable.js"></script>
<!-- bootstrapValidator -->
<script src="${pageContext.request.contextPath}/static/lib/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script src="${pageContext.request.contextPath}/static/lib/bootstrapValidator/js/language/zh_CN.js"></script>
<script src="${pageContext.request.contextPath}/static/js/common.js"></script>
<script src="${pageContext.request.contextPath}/js/finance/customerPack.js"></script>
</head>

<body>
<div class="container-fluid">
    <div class="panel panel-default">
         <div class="panel-body">
	   <form class="form-horizontal" id="frmCustomerPackQuery">
	    <div class="form-group">
                <label class="col-md-1 control-label">项目id</label>
                <div class="col-md-2">
                    <input class="form-control" name="projectId" type="text">
                </div>
	        <div class="col-md-4">
                    <button class="btn btn-primary" id="btnCustomerPackQuery">查询</button>
                </div>
            </div>
	  </form>
        </div>
    </div>
    <div class="box box-primary">
	<div class="box-header with-border">
	    <i class="fa fa-list-alt"></i>
	    <h3 class="box-title">客户对账打包信息表信息</h3>
	</div>
	<div class="box-tools">
            <button class="btn btn-primary" id="btnCustomerPackAdd">新增</button>
            <button class="btn btn-primary" id="btnCustomerPackEdit">修改</button>
	    <button class="btn btn-primary" id="btnCustomerPackView">查看</button>
            <button class="btn btn-danger" id="btnCustomerPackDel">删除</button>
        </div>
	<table id="tblCustomerPack" class="table table-striped table-bordered" cellspacing="0" width="100%">
	    <thead>
		<tr>
		    <th>主键</th>
		    <th>项目id</th>
		    <th>打包类型(0 汽运 1 接取 2 送达 3 火运)</th>
		    <th>对账状态(0-对账待审核 1-对账财务审核通过 2-对账财务审核不通过 3-已解包 )</th>
		    <th>结算审核状态(0-待结算 1-结算财务待审核 2-结算审核通过 3-结算审核不通过)</th>
		    <th>打包车辆</th>
		    <th>打包车次</th>
		    <th>发票状态(0-新建 1-已登入 2-发票作废待审核 3-已作废 4-作废审核不通过)</th>
		    <th>备注</th>
		    <th>是否删除(0-未删除 1-删除)</th>
		    <th>创建时间</th>
		    <th>创建人</th>
		    <th>修改时间</th>
		</tr>
	    </thead>
	    <tbody></tbody>
	</table>
    </div>
</div>
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="mdlCustomerPack" tabindex="-1" role="dialog" data-keyboard="false" data-backdrop="static" aria-hidden="true">
        <div class="modal-dialog large">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">客户对账打包信息表信息</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="frmCustomerPack">
                        <input class="hidden" name="id" />
                        <div class="form-group">
                            <label class="col-md-2 control-label">主键</label>
                            <div class="col-md-4">
                                <input class="form-control" name="custPackId" maxlength="32" type="text"/>
                            </div>
                            <label class="col-md-2 control-label">项目id</label>
                            <div class="col-md-4">
                                <input class="form-control" name="projectId" maxlength="9" type="number"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">打包类型(0 汽运 1 接取 2 送达 3 火运)</label>
                            <div class="col-md-4">
                                <input class="form-control" name="packType" maxlength="9" type="number"/>
                            </div>
                            <label class="col-md-2 control-label">对账状态(0-对账待审核 1-对账财务审核通过 2-对账财务审核不通过 3-已解包 )</label>
                            <div class="col-md-4">
                                <input class="form-control" name="checkingStatus" maxlength="9" type="number"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">结算审核状态(0-待结算 1-结算财务待审核 2-结算审核通过 3-结算审核不通过)</label>
                            <div class="col-md-4">
                                <input class="form-control" name="settleAuditType" maxlength="9" type="number"/>
                            </div>
                            <label class="col-md-2 control-label">打包车辆</label>
                            <div class="col-md-4">
                                <input class="form-control" name="packTruckNum" maxlength="9" type="number"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">打包车次</label>
                            <div class="col-md-4">
                                <input class="form-control" name="packTruckDegree" maxlength="9" type="number"/>
                            </div>
                            <label class="col-md-2 control-label">发票状态(0-新建 1-已登入 2-发票作废待审核 3-已作废 4-作废审核不通过)</label>
                            <div class="col-md-4">
                                <input class="form-control" name="invoiceStatus" maxlength="9" type="number"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">备注</label>
                            <div class="col-md-4">
                                <input class="form-control" name="remark" maxlength="2000" type="text"/>
                            </div>
                            <label class="col-md-2 control-label">是否删除(0-未删除 1-删除)</label>
                            <div class="col-md-4">
                                <input class="form-control" name="deleteFlag" maxlength="9" type="number"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">创建时间</label>
                            <div class="col-md-4">
                              <span style="position: relative; z-index: 9999;">
				<input class="form-control" name="createDate" type="text" data-provide="datepicker"/>
			      </span>
                            </div>
                            <label class="col-md-2 control-label">创建人</label>
                            <div class="col-md-4">
                                <input class="form-control" name="createUserId" maxlength="9" type="number"/>
                            </div>
                        </div>
                        <div class="form-group">
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
                    <button class="btn btn-primary" id="btnCustomerPackSave">保存</button>
                    <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">取消</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>