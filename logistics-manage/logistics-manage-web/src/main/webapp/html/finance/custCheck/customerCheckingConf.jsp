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
<style type="text/css">
#tblCustomerCheckingConf .dataTables_wrapper {
    position: relative;
    clear: both;
    zoom: 1;
    overflow-x: auto;
}

#tblCustomerCheckingConf table{
    width: 3200px;
}
</style>
<script src="${pageContext.request.contextPath}/static/js/invoke.js"></script>
<script src="${pageContext.request.contextPath}/js/finance/custCheck/customerCheckingConf.js"></script>
</head>

<body>
<div class="container-fluid">
    <div class="panel panel-default">
         <div class="panel-body">
	  <form class="form-inline maxWidth" id="frmCustomerCheckingConfQuery">
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
            <button class="btn-xs btn-primary btn-query pull-right" id="btnCustomerCheckingConfQuery">查询</button>
	    </div>
	  </form>
        </div>
    </div>
    <div class="box box-primary table-responsive">
	<div class="box-tools">
        <button class="btn-xs btn-primary" id="btnCustCheck">对账明细</button>
        <button class="btn-xs btn-primary" id="btnCustCheckCar">汽运对账明细</button>
 		<button class="btn-xs btn-primary" id="btnCustCheckRecive">接取对账明细</button>
        <button class="btn-xs btn-danger" id="btnCustCheckTrain">干线对账明细</button>
        <button class="btn-xs btn-danger" id="btnCustCheckSend">送达对账明细</button> 
        <per:hasUrlPerm code="custCheckDeAudit">
		    <button class="btn-xs btn-danger pull-right" id="btnCustCheckDeAudit" style="display:none">反审核</button>
		 </per:hasUrlPerm>
		  <per:hasUrlPerm code="custCheckAudit">
		     <button class="btn-xs btn-primary pull-right checks" id="btnCustCheckAudit">财务审核</button>
		 </per:hasUrlPerm>
		  <per:hasUrlPerm code="custCheckDeConfirmCheck">
		   	<button class="btn-xs btn-danger pull-right" id="btnCustCheckDeConfirmCheck">反确认</button>
		 </per:hasUrlPerm>
		  <per:hasUrlPerm code="custCheckConfirmCheck">
		   <button class="btn-xs btn-primary pull-right" id="btnCustCheckConfirmCheck">确认对账</button>
		 </per:hasUrlPerm>
		  <per:hasUrlPerm code="custCheckStartChecking">
		    <button class="btn-xs btn-primary pull-right" id="btnCustCheckStartChecking">开始对账</button>
		 </per:hasUrlPerm> 	
        </div>
	<table id="tblCustomerCheckingConf" class="table table-striped table-bordered" cellspacing="0" width="100%" style="white-space: nowrap; ">
	    <thead>
		<tr>
			<th></th>
		    <th>项目编号</th>
		    <th>分支机构</th>
		    <th>对账单号</th>
		    <th>对账状态</th>
		    <th>发货企业</th>
		    <th>始发地</th>
		    <th>收货企业</th>
		    <th>运抵地</th>
		    <th>货物品名</th>
		    <th>起始日期</th>
		    <th>终止日期</th>
		    <th>运单总数</th>
		    <th>总净重</th>
		    <th>总件数</th>
		    <th>产生金额</th>
		    <th>应开税额</th>
		    <th>汽运</th>
		    <th>汽运对账金额</th>
		    <th>接取</th>
		    <th>接取对账金额</th>
		    <th>火运</th>
		    <th>火运对账金额</th>
		    <th>送达</th>
		    <th>送达对账金额</th>
		    <th>对账人</th>
		    <th>对账时间</th>
		</tr>
	    </thead>
	    <tbody></tbody>
	</table>
    </div>
    <iframe name="custCheckFrame"  
    scrolling="no" frameborder="0" style="padding: 0px; width: 100%; height: 1000px;"></iframe>
</div>
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="chooseProjectDivId" tabindex="-1" role="dialog" data-keyboard="false" data-backdrop="static" aria-hidden="true">
        <div class="modal-dialog" style="width:1100px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h5 class="modal-title">开始对账</h5>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="chooseProjectFormId">
                     <fieldset>
                        <legend><span style="font-size:12px;color:red;">项目信息<i class='requireds'>*</i></span></legend>
                        <div class="form-group">
                            <label class="col-md-1 control-label">项目编号</label>
                            <div class="col-md-2">
                                <select name="projectId" ></select>
                            </div>
                            <label class="col-md-1 control-label">分支机构</label>
                            <div class="col-md-2">
                                <input name="branchGroupName" type="text" readonly="readonly"/>
                            </div>
                            <label class="col-md-1 control-label"><span style="color:red;">*</span>起始日期</label>
                            <div class="col-md-2">
                                <input name="startDate" type="text" readonly="readonly"/>
                            </div>
                            <label class="col-md-1 control-label"><span style="color:red;">*</span>终止日期</label>
                            <div class="col-md-2">
                                <input name="endDate" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-1 control-label">发货企业</label>
                            <div class="col-md-2">
                                <input name="sendCargoCompanyName" type="text" readonly="readonly"/>
                            </div>
                            <label class="col-md-1 control-label">始发地</label>
                            <div class="col-md-2">
                                <input name="beginAddress" readonly="readonly"/>
                            </div>
                            <label class="col-md-1 control-label">收货企业</label>
                            <div class="col-md-2">
                                <input name="receiveCargoCompanyName" readonly="readonly"/>
                            </div>
                            <label class="col-md-1 control-label">运抵地</label>
                            <div class="col-md-2">
                                <input name="endAddress" readonly="readonly"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-1 control-label" style="display:none">总车数</label>
                            <div class="col-md-2" style="display:none">
                                <input name="totalCarNum" readonly="readonly"/>
                            </div>
                            <label class="col-md-1 control-label" style="display:none">总净重</label>
                            <div class="col-md-2" style="display:none">
                                <input name="totalWeight" readonly="readonly"/>
                            </div>
                            <label class="col-md-1 control-label" style="display:none">总件数</label>
                            <div class="col-md-2" style="display:none">
                                <input name="totalPiece" readonly="readonly"/>
                            </div>
                            <label class="col-md-1 control-label"><span style="color:red;">*</span>税率</label>
                            <div class="input-group col-md-2">
                                <input name="taxRate" style="width: 136px; margin-left: 5px;"/>
                                <span class="input-group-addon">%</span>
                            </div>
                        </div>
                    </fieldset>     
                    <fieldset>
                        <legend><span style="font-size:12px;color:red;">其他信息<i class='requireds'>*</i></span></legend>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">接取待对账运单</label>
                            <div class="col-sm-2">
                                <input name="reciveOrderNum" readonly="readonly"/>
                            </div>
                            <label class="col-sm-2 control-label">火运待对账运单</label>
                            <div class="col-sm-2">
                                <input name="trainOrderNum" readonly="readonly"/>
                            </div>
                            <label class="col-sm-2 control-label">送达待对账运单</label>
                            <div class="col-sm-2">
                                <input name="sendOrderNum" readonly="readonly"/>
                            </div>
                        </div>
                      </fieldset>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn-xs btn-primary" id="btnShortOrderFinanceQueryProjectId">开始对账</button>
                    <button class="btn-xs btn-danger" data-dismiss="modal" aria-hidden="true">取消</button>
                </div>
            </div>
        </div>
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
                      <div class="form-group">
                          <label class="col-md-1 control-label">项目编号</label>
                          <div class="col-md-2">
                              <input name="projectId"/>
                          </div>
                          <label class="col-md-1 control-label">分支机构</label>
                          <div class="col-md-2">
                          	  <input name="branchGroupName"/>
                          </div>
                          <label class="col-md-1 control-label">打包车辆</label>
                          <div class="col-md-2">
                              <input name="packTruckNum"/>
                          </div>
                          <label class="col-md-1 control-label">打包车次</label>
                          <div class="col-md-2">
                              <input name="packTruckDegree"/>
                          </div>
                      </div>
                      <br>
                   <table id="tblInitPack" data-toggle="table" class="table table-striped table-bordered" cellspacing="0" width="100%">
				    <thead>
					<tr>
						<th data-field="orderCode">运单编号</th>
						<th data-field="sendCompany">发货企业</th>
						<th data-field="receiptCompany">收货企业</th>
						<th data-field="cargoName">货物品名</th>
						<th data-field="carPlateNumber">承运车辆</th>
						<th data-field="shortBargeCost">发货皮重</th>
						<th data-field="buckleFigure">发货毛重</th>
						<th data-field="subsidy">发货净重</th>
						<th data-field="shouldTaxMoney">应开税额</th>
					</tr>
				    </thead>
				    <tbody></tbody>
					</table>
					<br>
					<form class="form-horizontal" id="initPackFormId">	
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
