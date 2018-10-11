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
<script src="${pageContext.request.contextPath}/js/finance/custCheck/custCheckTrain.js"></script>
</head>

<body>
  <div class="box box-primary table-responsive">
   	<div class="box-tools">
      	<button class="btn-xs btn-primary" key="0" id="btnCustCheckCar">汽运对账</button>
        <button class="btn-xs btn-primary" key="1" id="btnCustCheckRecive">接取对账</button>
    	<button class="btn-xs btn-primary" key="2" id="btnCustCheckTrain">火运对账</button>
        <button class="btn-xs btn-danger" key="3" id="btnCustCheckSend">送达对账</button>
        <c:forEach items='${permissions }' var='permission' >
			<c:if test="${permission.code=='custCheckPack'}">
				<button class="btn-xs btn-primary pull-right" id="btnCustCheckPack">打包</button>
			</c:if>
		</c:forEach>
        
    </div>
	<table id="tblShortPack" class="table table-striped table-bordered" cellspacing="0" width="100%">
	    <thead>
		<tr>
		<th></th>
		    <th>请车单号</th>
		    <th>状态</th>
		    <th>发货站点</th>
		    <th>始发地</th>
		    <th>收货站点</th>
		    <th>运抵地</th>
		    <th>货物品名</th>
		    <th>起始日期</th>
		    <th>终止日期</th>
		    <th>发货净重</th>
		    <th>产生金额</th>
		    <th>应开税额</th>
		</tr>
	    </thead>
	    <tbody></tbody>
	</table>
	</div>
	<!-- 模态框（Modal） -->
    <div class="modal fade" id="initPackDivId" tabindex="-1" role="dialog" data-keyboard="false" data-backdrop="static" aria-hidden="true">
        <div class="modal-dialog large" style="width:1100px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">打包</h4>
                </div>
                <div class="modal-body">
                <form class="form-horizontal" id="initPackFormId">
                      <div class="form-group">
                          <input type="hidden" name="projectId"/>
                          <input type="hidden" name="beginAddress"/>
                          <input type="hidden" name="endAddress"/>
                          <label class="col-md-1 control-label">项目编号</label>
                          <div class="col-md-2">
                              <input name="projectCode" readonly="readonly"/>
                          </div>
                          <label class="col-md-1 control-label">分支机构</label>
                          <div class="col-md-2">
                          	  <input name="branchGroupName" readonly="readonly"/>
                          </div>
                          <label class="col-md-1 control-label">打包车辆</label>
                          <div class="col-md-2">
                              <input name="packTruckNum" readonly="readonly"/>
                          </div>
                          <label class="col-md-1 control-label">打包车次</label>
                          <div class="col-md-2">
                              <input name="packTruckDegree" readonly="readonly"/>
                          </div>
                      </div>
                      <br>
                   <table id="tblInitPack" data-toggle="table" class="table table-striped table-bordered" cellspacing="0" width="100%">
				    <thead>
					<tr>
						<th data-field="pleaseTrainNumber">请车单号</th>
						<!--  
						<th data-field="sendCompany">发货企业</th>
						<th data-field="receiptCompany">收货企业</th>
						<th data-field="cargoName">货物品名</th>
						<th data-field="sendTare">发货皮重</th>
						<th data-field="sendGross">发货毛重</th>
						<th data-field="carNumber">车号</th>-->
						<th data-field="entruckWeight">发货净重</th>
						<th data-field="shouldTaxMoney">应开税额</th>
					</tr>
				    </thead>
				    <tbody></tbody>
					</table>
					<br>
						<input type="hidden" name="orderIds"/>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" name="btnInitPackSave">确定</button>
                    <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">取消</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
