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
<script src="${pageContext.request.contextPath}/js/finance/shPackOrder.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="panel panel-default">
         <div class="panel-body">
	   <form class="form-horizontal" id="frmShPackOrderQuery">
	    <div class="form-group">
                <label class="col-md-1 control-label">打包信息id</label>
                <div class="col-md-2">
                    <input class="form-control" name="shPackId" type="text">
                </div>
	        <div class="col-md-4">
                    <button class="btn btn-primary" id="btnShPackOrderQuery">查询</button>
                </div>
            </div>
	  </form>
        </div>
    </div>
    <div class="box box-primary table-responsive">
	<div class="box-header with-border">
	    <i class="fa fa-list-alt"></i>
	    <h3 class="box-title">短驳打包-运单中间表信息</h3>
	</div>
	<div class="box-tools">
            <button class="btn btn-primary" id="btnShPackOrderAdd">新增</button>
            <button class="btn btn-primary" id="btnShPackOrderEdit">修改</button>
	    <button class="btn btn-primary" id="btnShPackOrderView">查看</button>
            <button class="btn btn-danger" id="btnShPackOrderDel">删除</button>
        </div>
	<table id="tblShPackOrder" class="table table-striped table-bordered" cellspacing="0" width="100%">
	    <thead>
		<tr>
		    <th>主键</th>
		    <th>打包信息id</th>
		    <th>运单id</th>
		    <th>是否删除(0-未删除 1-删除)</th>
		</tr>
	    </thead>
	    <tbody></tbody>
	</table>
    </div>
</div>
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="mdlShPackOrder" tabindex="-1" role="dialog" data-keyboard="false" data-backdrop="static" aria-hidden="true">
        <div class="modal-dialog large">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">短驳打包-运单中间表信息</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="frmShPackOrder">
                        <input class="hidden" name="id" />
                        <div class="form-group">
                            <label class="col-md-2 control-label">主键</label>
                            <div class="col-md-4">
                                <input class="form-control" name="shPackOrderId" maxlength="32" type="text"/>
                            </div>
                            <label class="col-md-2 control-label">打包信息id</label>
                            <div class="col-md-4">
                                <input class="form-control" name="shPackId" maxlength="32" type="text"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">运单id</label>
                            <div class="col-md-4">
                                <input class="form-control" name="orderId" maxlength="9" type="number"/>
                            </div>
                            <label class="col-md-2 control-label">是否删除(0-未删除 1-删除)</label>
                            <div class="col-md-4">
                                <input class="form-control" name="deleteFlag" maxlength="9" type="number"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" id="btnShPackOrderSave">保存</button>
                    <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">取消</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
