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
<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/finance/prepaymentSettlement/imprestPayment.css" />
<script src="${pageContext.request.contextPath}/static/js/invoke.js"></script>
<script src="${pageContext.request.contextPath}/js/finance/prepaymentSettlement/advanceSettlement.js"></script>
<%-- <script src="${pageContext.request.contextPath}/js/finance/prepaymentSettlement/imprestPayment.js"></script> --%>
</head>
<body>
<div class="container-fluid">
	<!-- 预付款存入表单 -->
	<div class="panel panel-default">
         <div class="panel-body">
	   <form class="form-inline maxWidth" id="frmAdvanceChargeQuery">
	   	<input type="hidden" name="projectId"/>
	    <div class="form-group">
	        <label>项目编号：</label>
	        <input name="projectCode" type="text">
        </div>
       <!--  <div class="form-group">
            <label>始发地：</label>
            <input name="beginSite" type="text">
 		</div>
        <div class="form-group">
            <label>终点地：</label>
			<input name="endSite" type="text">
		</div> -->
        <!-- <div class="form-group">
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
		</div> -->
		<div class="form-group"> 
            <button class="btn-xs btn-primary btn-query pull-right" id="btnAdvanceChargeQuery">查询</button>
	    </div>
	  </form>
        </div>
    </div>
		<!-- 预付款存入表格 -->
		<div class="box box-primary table-responsive">
    <!--  
	<div class="box-header with-border">
	    <h6 class="box-title">短驳运单财务表信息</h6>
	</div>
	-->
	<div class="box-tools">
		<button class="btn-xs btn-primary tableActive" id="btnAdvanceDetail">明细</button>
    </div>
	<table id="tableAdvanceDetail" class="table table-striped table-bordered " cellspacing="0" width="100%">
	    <thead>
		<tr>
			<th>
				<label class="demo--label"><input class="demo--checkbox" type="checkbox" ><span class="demo--checkboxInput"></span></label>
			</th>
			<th>项目编号</th>
			<th>存入金额</th>
			<th>抵用金额</th>
			<th>退款金额</th>
			<th>余额</th>
			<th>待处理申请</th>
			<th>更新时间</th>
		</tr>
	    </thead>
	    <tbody></tbody>
	</table>
    </div>
	<div class="box box-primary" id="detailsByProject" style="display: none;">
    <!--<div class="panel-heading">支出明细</div>  -->
	
        <div class="panel-body box-tools">
        	<button class="btn-xs btn-primary">计费明细</button>
        	
        	<!-- <button class="btn-xs btn-danger pull-right" id="backaccountDetails">反审核</button> -->
        	<per:hasUrlPerm code="lookDetails">
		    	<button class="btn-xs btn-danger pull-right" id="lookDetails">详情</button>
		 	</per:hasUrlPerm>
        	<per:hasUrlPerm code="accountDetails">
		    	<button class="btn-xs btn-danger pull-right checks" id="accountDetails">财务审核</button>
		 	</per:hasUrlPerm>
        	<per:hasUrlPerm code="deleteAdvacne">
		    	<button class="btn-xs btn-danger pull-right" id="deleteAdvacne">删除</button>
		 	</per:hasUrlPerm>
        	<per:hasUrlPerm code="withdrawPayment">
		    	<button class="btn-xs btn-danger pull-right" id="withdrawPayment">退款</button>
		 	</per:hasUrlPerm>
        	<per:hasUrlPerm code="imprestPurpose">
		    	<button class="btn-xs btn-danger pull-right" id="imprestPurpose">抵用</button>
		 	</per:hasUrlPerm>
        	<per:hasUrlPerm code="imprestPayment">
		    	<button class="btn-xs btn-danger pull-right" id="imprestPayment">存入</button>
		 	</per:hasUrlPerm>
        </div>
	<table id="tbDepositOrPurposeDetail" class="table table-striped table-bordered" cellspacing="0" width="100%">
	    <thead>
		<tr>
			<th>
				<label class="demo--label"><input class="demo--checkbox" type="checkbox" ><span class="demo--checkboxInput"></span></label>
			</th>
			<th>流水号</th>
			<th>状态</th>
			<th>账户</th>
			<th>存入金额</th>
			<th>抵用金额</th>
			<th>退款金额</th>
			<th>生成时间</th>
			<th>操作人</th>
			<th>操作时间</th>
			<th>审核人</th>
			<th>审核时间</th>
		</tr>
	    </thead>
        <tbody></tbody>
	</table>
    </div>
</div>
		<!--预付款存入模态框-->
		<div class="modal fade imprestPaymentModal" id="imprestPaymentModal" tabindex="-1" role="dialog" data-backdrop="static">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">预付款存入</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="depositForm">
							<div class="form-inline ">
								<h5>基本信息<i class='requireds'>*</i>
								</h5>
								<div class="form-group ">
									<label>项目编号：</label>
									<input type="hidden" class="inputbg"  name="projectId" unselectable="on" readonly/>
									<input type="text" class="inputbg"  name="projectCode" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>分支机构：</label>
									<input type="hidden" class="inputbg"  name="branchId" unselectable="on" readonly/>
									<input type="text" class="inputbg"  name="branchName" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>&emsp;预付类型：</label>
									<select id="advanceType" name="advanceType">
										<option value=""></option>
										<option value="0">货款</option>
										<option value="1">运费</option>
									</select>
								</div>
							</div>
							<hr/>
							<div class="form-inline ">
								<h5>存入信息<i class='requireds'>*</i>
								</h5>
								<div class="form-group ">
									<label>账户种类：</label>
									<select id="receiveType" disabled="disabled">
										<option value=""></option>
										<option value="0">客户</option>
										<option value="1">站点</option>
										<!-- <option value="2">公司</option> -->
									</select>
									<input type="hidden" class="inputbg" name="receiveType"/>
								</div>
								<div class="form-group ">
									<label>收款单位：</label>
									<select id="receiveUnitId" name="receiveUnitId"></select>
									<input type="hidden" class="inputbg" id="receiveUnitName" name="receiveUnitName"/>
								</div>
								<div class="form-group ">
									<label>&emsp;收款账户：</label>
									<select id="receiveAccountId" name="receiveAccountId"></select>
									<input type="hidden" class="inputbg" id="receiveAccountName" name="receiveAccountName" />
								</div>
								<div class="form-group ">
									<label>收款经办人：</label>
									<select id="receiveAgentByKind" >
									</select> 
									<input type="text"  id="receiveAgent" name="receiveAgent" style="display: none;"/>
								</div>
								<div class="form-group ">
									<label>收款账号：</label>
									<input type="text" class="inputbg" id="receiveNumber" name="receiveNumber" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>收款行号：</label>
									<input type="text" class="inputbg" id="receiveBankNumber" name="receiveBankNumber" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>纳税识别号：</label>
									<input type="text" class="inputbg inputLenth" id="receiveTaxNumber" name="receiveTaxNumber" unselectable="on" readonly/>
								</div>
							</div>
							<hr/>
							<div class="form-inline ">
								<h5>支出信息 <i class='requireds'>*</i>
								</h5>
								<div class="form-group ">
									<label>账户种类：</label>
									<select id="payType" name="payType">
										<option value=""></option>
										<!-- <option value="0">客户</option>
										<option value="1">站点</option> -->
										<option value="2">公司</option>
									</select>
								</div>
								<div class="form-group ">
									<label>支出单位：</label>
									<select id="payUnitId" name="payUnitId"></select>
									<input type="hidden" class="inputbg" id="payUnitName" name="payUnitName" />
								</div>
								<div class="form-group ">
									<label>&emsp;支出账户：</label>
									<select id="payAccountId" name="payAccountId"></select>
									<input type="hidden" class="inputbg" id="payAccountName" name="payAccountName" />
								</div>
								<div class="form-group ">
									<!-- <label>支出经办人：</label> -->
									<!-- <input type="text" class="inputbg" name="payAgent" /> -->
								</div>
								<div class="form-group ">
									<label>支出账号：</label>
									<input type="text" class="inputbg" id="payNumber" name="payNumber" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>支出行号：</label>
									<input type="text" class="inputbg"id="payBankNumber" name="payBankNumber" unselectable="on" readonly/>
								</div> 
								<div class="form-group ">
									<label>纳税识别号：</label>
									<input type="text" class="inputbg inputLenth" id="payTaxNumber" name="payTaxNumber" unselectable="on" readonly/>
								</div>
							</div>
							<hr/>
							<div class="form-inline ">
								<h5>其他信息 <i class='requireds'>*</i>
								</h5>
								<div class="form-group ">
									<label>对方凭证：</label>
									<select name="otherProof">
										<option value=""></option>
										<option value="0">财支</option>
										<option value="1">财收</option>
									</select>
									<label>&emsp;——</label>
								</div>
								<div class="form-group ">
									<input type="text"   name="otherProofStart" />
									<label>&emsp;——</label>
								</div>
								<div class="form-group ">
									<input type="text"   name="otherProofEnd" />
								</div>
								<div class="form-group ">
									<label>编号：</label>
									<input type="text"   name="otherProofNum" />
								</div>
								<div class="form-group ">
									<label>己方凭证：</label>
									<select name="selfProof">
										<option value=""></option>
										<option value="0">财支</option>
										<option value="1">财收</option>
									</select>
									<label>&emsp;——</label>
								</div>
								<div class="form-group ">
									<input type="text"   name="selfProofStart" />
									<label>&emsp;——</label>
								</div>
								<div class="form-group ">
									<input type="text"   name="selfProofEnd" />
								</div>
								<div class="form-group ">
									<label>编号：</label>
									<input type="text"   name="selfProofNum" />
								</div>
							</div>						
							<hr/>
							<div class="form-inline ">
								<h5>存入金额 <i class='requireds'>*</i>
								</h5>
								<div class="form-group ">
									<label>支付方式：</label>
									<select name="payment">
										<option value=""></option>
										<option value="0">现金</option>
										<option value="1">支票</option>
										<option value="2">转账</option>
										<option value="3">承兑汇票</option>
									</select>
								</div>
								<div class="form-group ">
									<label>&emsp;&emsp;金额：</label>
									<input type="text"   name="depositAmount" onkeyup="value=value.replace(/[^\d.]/g,'')"/>
								</div>
							</div>						
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
									<button type="button" class="btn sureBtn" id="addDepostAmountBtn">存入</button>
									<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
		<!--退款-->
		<div class="modal fade withdrawModal" id="withdrawModal" tabindex="-1" role="dialog" data-backdrop="static">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">退款</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="withdrawForm">
							<div class="form-inline ">
								<h5>基本信息<i class='requireds'>*</i>
								</h5>
								<div class="form-group ">
									<label>项目编号：</label>
									<input type="hidden" class="inputbg"  name="projectId" unselectable="on" readonly/>
									<input type="text" class="inputbg" name="projectCode" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>分支机构：</label>
									<input type="hidden" class="inputbg"  name="branchId" unselectable="on" readonly/>
									<input type="text" class="inputbg"  name="branchName" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>预付类型：</label>
									<select id="cashAdvanceType" name="advanceType">
										<option value=""></option>
										<option value="0">货款</option>
										<option value="1">运费</option>
									</select>
								</div>
							</div>
							<hr/>
							<div class="form-inline ">
								<h5>剩余金额<i class='requireds'>*</i>
								</h5>
								<div class="form-group ">
									<label>取出账户：</label>
									<select  name=payAccountId>
									</select>
									<input type="hidden" class="inputbg"  name="payAccountName" />
								</div>
								<div class="form-group ">
									<label>&emsp;&emsp;账号：</label>
									<input type="text" class="inputbg"  name="payNumber" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>&emsp;&emsp;行号：</label>
									<input type="text" class="inputbg"  name="payBankNumber" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>&emsp;&emsp;余额：</label>
									<input type="text" class="inputbg" id="cashAccountBalance" unselectable="on" readonly/>
								</div>
							</div>
							<hr/>
							<div class="form-inline ">
								<h5>退款账户 <i class='requireds'>*</i>
								</h5>
								<div class="form-group ">
									<label>退款账户：</label>
									<select name="receiveAccountId">
									</select>
									<input type="hidden" class="inputbg"  name="receiveAccountName" />
								</div>
								<div class="form-group ">
									<label>退款账号：</label>
									<input type="text" class="inputbg" name="receiveNumber" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>&emsp;&emsp;行号：</label>
									<input type="text" class="inputbg" name="receiveBankNumber" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>纳税识别号：</label>
									<input type="text" class="inputbg" name="receiveTaxNumber" unselectable="on" readonly/>
								</div>
							</div>
							<hr/>
							<div class="form-inline ">
								<h5>其他信息 <i class='requireds'>*</i>
								</h5>
								<div class="form-group ">
									<label>对方凭证：</label>
									<select name="otherProof">
										<option value=""></option>
										<option value="0">财支</option>
										<option value="1">财收</option>
									</select>
									<label>&emsp;——</label>
								</div>
								<div class="form-group ">
									<input type="text"   name="otherProofStart" />
									<label>&emsp;——</label>
								</div>
								<div class="form-group ">
									<input type="text"   name="otherProofEnd" />
								</div>
								<div class="form-group ">
									<label>编号：</label>
									<input type="text"   name="otherProofNum" />
								</div>
								<div class="form-group ">
									<label>己方凭证：</label>
									<select name="selfProof">
										<option value=""></option>
										<option value="0">财支</option>
										<option value="1">财收</option>
									</select>
									<label>&emsp;——</label>
								</div>
								<div class="form-group ">
									<input type="text"   name="selfProofStart" />
									<label>&emsp;——</label>
								</div>
								<div class="form-group ">
									<input type="text"   name="selfProofEnd" />
								</div>
								<div class="form-group ">
									<label>编号：</label>
									<input type="text"   name="selfProofNum" />
								</div>
							</div>						
							<hr/>
							<div class="form-inline ">
								<h5>退款金额<i class='requireds'>*</i>
								</h5>
								<div class="form-group ">
									<label>支付方式：</label>
									<select name="payment">
										<option value=""></option>
										<option value="0">现金</option>
										<option value="1">支票</option>
										<option value="2">转账</option>
										<option value="3">承兑汇票</option>
									</select>
								</div>
								<div class="form-group ">
									<label>&emsp;&emsp;金额：</label>
									<input type="text" name="cashAmount"/>
									<strong>&emsp;注：不得大于取出账户的余额</strong>
								</div>
							</div>
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
									<button type="button" class="btn sureBtn" id="addCashBtn">退款</button>
									<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
		
		
			<!--运费抵用模态框-->
		<div class="modal fade imprestPaymentModal" id="imprestPurposeModal1" tabindex="-1" role="dialog" data-backdrop="static">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">预付款抵用</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="purposeForm">
							<div class="form-inline ">
								<h5>基本信息
								<i class='requireds'>*</i>
								</h5>
								<div class="form-group ">
									<label>项目编号：</label>
									<input type="hidden" class="inputbg"  name="id" unselectable="on" readonly/>
									<input type="hidden" class="inputbg" id="projectId" name="projectId" unselectable="on" readonly/>
									<input type="text" class="inputbg" id="projectCode" name="projectCode" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>分支机构：</label>
									<input type="hidden" class="inputbg"  name="branchId" unselectable="on" readonly/>
									<input type="text" class="inputbg"  name="branchName" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>请车单号：</label>
									<input type="hidden" class="inputbg" name="trainOrderId" unselectable="on" readonly/>									
									<input type="text" class="inputbg" name="pleaseTrainNum" unselectable="on" readonly/>									
								</div>
								<div class="form-group ">
									<label>抵用类型：</label>
									<input type="text" class="inputbg" id="dYBillName" unselectable="on" readonly/>
								</div>
							</div>
							
							<hr/>
							<div class="form-inline ">
								<h5>装车信息
								<i class='requireds'>*</i>
								</h5>																						
								<div class="form-group ">
									<label> 装车吨数：</label>
									<input type="text" name="entruckWeight" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>&emsp;&emsp;单价：</label>
									<input type="text" name="sumCost" unselectable="on" readonly/>
								</div>
							</div>	
							<hr/>
							<div class="form-inline ">
								<h5>单位信息
								<i class='requireds'>*</i>
								</h5>																						
								<div class="form-group ">
									<label>支出单位：</label>
									<input type="hidden" class="inputbg"  name="payUnitId"/>
									<input type="text" name="payUnitName" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>存入单位：</label>
									<input type="hidden" class="inputbg"  name="receiveUnitId"/>
									<input type="text" name="receiveUnitName" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>办理单位：</label>
									<input type="hidden" class="inputbg"  name="receiveAccountName"/>
									<select name="receiveAccountId">
									</select>
								</div>
								<div class="form-group ">
									<label>经办人：</label>
									<input type="text" name="receiveAgent" unselectable="on" />
								</div>
							</div>							
							<hr/>
							<div class="form-inline ">
								<h5>抵用信息
								<i class='requireds'>*</i>
								</h5>																						
								<div class="form-group ">
									<label>抵用账户：</label>
									<input type="text" name="payAccountName" unselectable="on" readonly/>
									<input type="hidden" class="inputbg"  name="payAccountId"/>
								</div>
								<div class="form-group ">
									<label>抵用账号：</label>
									<input type="text" name="payNumber" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>抵用金额：</label>
									<input type="text" name="purposeAmount" unselectable="on" readonly/>
								</div>
							</div>
							<div class="form-inline ">
								<h5>其他信息
								<i class='requireds'>*</i>
								</h5>																						
								<div class="form-group ">
									<label>&emsp;&emsp;起号：</label>
									<input type="text"  name="startNumber"/>									
								</div>
								<div class="form-group ">
									<label>&emsp;&emsp;止号：</label>
									<input type="text" name="endNumber"/>						
								</div>
								<div class="form-group ">
									<label>&emsp;&emsp;张数：</label>
									<input type="text" class="inputbg" name="sheetNumber" unselectable="on" readonly/>						
								</div>
								<div class="form-group ">
									<label>作废张数：</label>
									<input type="text" class="inputbg" name="invalidNumber" unselectable="on" readonly/>						
								</div>
								<div class="form-group ">
									<label>对方凭证：</label>
									<select name="otherProof">
										<option value=""></option>
										<option value="0">财支</option>
										<option value="1">财收</option>
									</select>
									<label>&emsp; —— </label>
								</div>
								<div class="form-group ">
									<input type="text"   name="otherProofStart" />
									<label>&emsp; —— </label>
								</div>
								<div class="form-group ">
									<input type="text"   name="otherProofEnd" />
								</div>
								<div class="form-group ">
									<label>编号：</label>
									<input type="text"   name="otherProofNum" />
								</div>
								<div class="form-group ">
									<label>己方凭证：</label>
									<select name="selfProof">
										<option value=""></option>
										<option value="0">财支</option>
										<option value="1">财收</option>
									</select>
									<label>&emsp; —— </label>
								</div>
								<div class="form-group ">
									<input type="text"   name="selfProofStart" />
									<label>&emsp; —— </label>
								</div>
								<div class="form-group ">
									<input type="text"   name="selfProofEnd" />
								</div>
								<div class="form-group ">
									<label>编号：</label>
									<input type="text"   name="selfProofNum" />
								</div>
							</div>							
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
									<button type="button" class="btn sureBtn" id="addPurposeBtn1">抵用</button>
									<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
		<!--货款抵用模态框-->
		<div class="modal fade imprestPaymentModal" id="imprestPurposeModal2" tabindex="-1" role="dialog" data-backdrop="static">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">预付款抵用</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="purposeForm2">
							<div class="form-inline ">
								<h5>基本信息
								<i class='requireds'>*</i>
								</h5>
								<div class="form-group ">
									<label>项目编号：</label>
									<input type="hidden" class="inputbg"  name="id" unselectable="on" readonly/>
									<input type="hidden" class="inputbg"  name="projectId" unselectable="on" readonly/>
									<input type="text" class="inputbg"  name="projectCode" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>分支机构：</label>
									<input type="hidden" class="inputbg"  name="branchId" unselectable="on" readonly/>
									<input type="text" class="inputbg"  name="branchName" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>对账单号：</label>
									<input type="hidden" class="inputbg" name="costPackId" unselectable="on" readonly/>									
									<input type="text" class="inputbg" name="costPackNum" unselectable="on" readonly/>									
								</div>
								<div class="form-group ">
									<label>抵用类型：</label>
									<input type="text" class="inputbg" id="dYBillName2" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>&emsp;&emsp;起号：</label>
									<input type="text"  name="startNumber"/>									
								</div>
								<div class="form-group ">
									<label>&emsp;&emsp;止号：</label>
									<input type="text" name="endNumber"/>						
								</div>
								<div class="form-group ">
									<label>&emsp;&emsp;单数：</label>
									<input type="text" class="inputbg" name="sheetNumber" unselectable="on" readonly/>						
								</div>
							</div>
							
							<hr/>
							<div class="form-inline ">
								<h5>装车信息
								<i class='requireds'>*</i>
								</h5>																						
								<div class="form-group ">
									<label> 装车吨数：</label>
									<input type="text" name="entruckWeight" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>合计费用：</label>
									<input type="text" name="sumCost" unselectable="on" readonly/>
								</div>
							</div>	
							<hr/>
							<div class="form-inline ">
								<h5>抵用信息
								<i class='requireds'>*</i>
								</h5>																						
								<div class="form-group ">
									<label>抵用账户：</label>
									<input type="hidden" name="payAccountName" unselectable="on" readonly/>
									<input type="hidden" name="alreadyDeposeAccount" unselectable="on" readonly/>
									<select name="payAccountId">
									</select>
								</div>
								<div class="form-group ">
									<label>抵用账号：</label>
									<input type="text" name="payNumber" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>抵用金额：</label>
									<input type="text" name="purposeAmount" unselectable="on" readonly/>
								</div>
							</div>	
							<div class="form-inline ">
								<h5>单位信息
								<i class='requireds'>*</i>
								</h5>																						
								<div class="form-group ">
									<label>支出单位：</label>
									<input type="hidden" class="inputbg"  name="payUnitId"/>
									<input type="text" name="payUnitName" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>存入单位：</label>
									<input type="hidden" class="inputbg"  name="receiveUnitId"/>
									<input type="text" name="receiveUnitName" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>办理单位：</label>
									<input type="text"   name="receiveAccountName" unselectable="on" readonly/>
									<input type="hidden"   name="receiveAccountId" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>经办人：</label>
									<select name="receiveAgent">
									</select>
								</div>
							</div>							
							
							<hr/>
							<div class="form-inline ">
								<h5>其他信息
								<i class='requireds'>*</i>
								</h5>																						
								<div class="form-group ">
									<label>对方凭证：</label>
									<select name="otherProof">
										<option value=""></option>
										<option value="0">财支</option>
										<option value="1">财收</option>
									</select>
									<label>&emsp; —— </label>
								</div>
								<div class="form-group ">
									<input type="text"   name="otherProofStart" />
									<label>&emsp; —— </label>
								</div>
								<div class="form-group ">
									<input type="text"   name="otherProofEnd" />
								</div>
								<div class="form-group ">
									<label>编号：</label>
									<input type="text"   name="otherProofNum" />
								</div>
								<div class="form-group ">
									<label>己方凭证：</label>
									<select name="selfProof">
										<option value=""></option>
										<option value="0">财支</option>
										<option value="1">财收</option>
									</select>
									<label>&emsp; —— </label>
								</div>
								<div class="form-group ">
									<input type="text"   name="selfProofStart" />
									<label>&emsp; —— </label>
								</div>
								<div class="form-group ">
									<input type="text"   name="selfProofEnd" />
								</div>
								<div class="form-group ">
									<label>编号：</label>
									<input type="text"   name="selfProofNum" />
								</div>
							</div>							
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
									<button type="button" class="btn sureBtn" id="addPurposeBtn2">抵用</button>
									<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
		<!--详情框 class="inputbg"-->
		<div class="modal fade imprestPaymentModal" id="detailModal" tabindex="-1" role="dialog" data-backdrop="static">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">详情</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="detailForm">
							<div class="form-inline ">
								<h5>基本信息</h5>
								<div class="form-group ">
									<label>&emsp;项目信息：</label>
									<input type="text" name="projectCode" class="inputbg" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>分支机构：</label>
									<input type="text" name="branchName" class="inputbg" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label id="changLabel">&emsp;流水号：</label>
									<input id='ticketName' name="serialNumber" type="text" class="inputbg" unselectable="on" readonly />
								</div>
								<div class="form-group ">
									<label>&emsp;&emsp;状态：</label>
									<input type="text" name="status" class="inputbg" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>&emsp;&emsp;操作人：</label>
									<input type="text" name="operationPerson" class="inputbg" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>操作时间：</label>
									<input type="text" name="operationDate" class="inputbg" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>&emsp;审核人：</label>
									<input type="text" name="assessor" class="inputbg" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>审核时间：</label>
									<input type="text" name="assessorDate" class="inputbg" unselectable="on" readonly/>
								</div>
							</div>	
							<hr/>
							<!--存入-->
							<div id='receInfo'>
                            <div class="form-inline" >
								<h5>收款信息</h5>
								<div class="form-group ">
									<label>&emsp;收款单位：</label>
									<input type="text" name="receiveUnitName" class="inputbg" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>收款账户：</label>
									<input type="text" name="receiveAccountName" class="inputbg" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>收款账号：</label>
									<input type="text" name="receiveNumber" class="inputbg" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>收款行号：</label>
									<input type="text" name="receiveBankNumber" class="inputbg" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>收款经办人：</label>
									<input type="text" name="receiveAgent" class="inputbg" unselectable="on" readonly/>
								</div>
							</div>
							<div class="form-inline ">
								<h5>存入信息</h5>
								<div class="form-group ">
									<label>&emsp;支出账户：</label>
									<input type="text" name="payAccountName" class="inputbg" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>支出方式：</label>
									<input type="text" name="payment" class="inputbg" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>预付类型：</label>
									<input type="text" name="advanceType" class="inputbg" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>&emsp;&emsp;金额：</label>
									<input type="text" name="depositAmount" class="inputbg" unselectable="on" readonly/>
								</div>
							</div>
							</div>
							<!--抵用-->
							<div id='serveModal'>
                   
							<div class="form-inline ">
								<h5>抵用信息</h5>
								<div class="form-group ">
									<label>&emsp;支出单位：</label>
									<input type="text" name="payUnitName" class="inputbg" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>支出账户：</label>
									<input type="text" name="alreadyDeposeAccount" class="inputbg" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>抵用金额：</label>
									<input type="text" name="purposeAmount" class="inputbg" unselectable="on" readonly/>
								</div>
								<!--火运费用-->
								
									<!-- <div class="form-group  hideTrain">
									<label>货物品名：</label>
									<input type="text" name="cargoName" class="inputbg" unselectable="on" readonly/>
								</div> -->
								<div class="form-group ">
									<label>装车吨数：</label>
									<input type="text" name="entruckWeight" class="inputbg" unselectable="on" readonly/>
								</div>
								<div class="form-group hideGoods">
									<label>&emsp;合计费用：</label>
									<input type="text" name="sumCost" class="inputbg" unselectable="on" readonly/>
								</div> 
								<div class="form-group hideTrain">
									<label>&emsp;&emsp;&emsp;单价：</label>
									<input type="text" name="sumCost" class="inputbg" unselectable="on" readonly/>
								</div> 
							</div>
							<hr />
							<div class="form-inline hideTrain">
								<h5>抵用金额</h5>
								<div class="form-group ">
									<label>&emsp;抵用金额：</label>
									<input type="text" name="purposeAmount" class="inputbg" unselectable="on" readonly/>
								</div>
							</div>
								<div class="form-inline ">
								<h5>其他信息</h5>
								<div class="form-group ">
									<label>&emsp;&emsp;&emsp;起号：</label>
									<input type="text" name="startNumber" class="inputbg" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>&emsp;&emsp;止号：</label>
									<input type="text" name="endNumber" class="inputbg" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>&emsp;&emsp;单数：</label>
									<input type="text" name="sheetNumber" class="inputbg" unselectable="on" readonly/>
								</div>
								<div class="form-group hideGoods">
									<label>作废张数：</label>
									<input type="text" name="invalidNumber" class="inputbg" unselectable="on" readonly/>
								</div>
							</div>
							</div>
							<!--退款-->
							<div id='cashInfo'>
                            <div class="form-inline" >
								<h5>剩余余额</h5>
								<div class="form-group ">
									<label>&emsp;取出账户：</label>
									<input type="text" name="payAccountName" class="inputbg" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>&emsp;&emsp;余额：</label>
									<input type="text" id="detailTakeId" class="inputbg" unselectable="on" readonly/>
								</div>
							</div>
							<div class="form-inline ">
								<h5>退款账户</h5>
								<div class="form-group ">
									<label>&emsp;退款账户：</label>
									<input type="text" name="receiveAccountName" class="inputbg" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>退款账号：</label>
									<input type="text" name="receiveNumber" class="inputbg" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>&emsp;&emsp;行号：</label>
									<input type="text" name="receiveBankNumber" class="inputbg" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>纳税识别号：</label>
									<input type="text" name="receiveTaxNumber" class="inputbg" unselectable="on" readonly/>
								</div>
							</div>
							<div class="form-inline" >
								<h5>退款金额</h5>
								<div class="form-group ">
									<label>&emsp;&emsp;&emsp;金额：</label>
									<input type="text" name="cashAmount" class="inputbg" unselectable="on" readonly/>
								</div>
							</div>
							</div>
							<hr/>
							<div class="form-inline ">
								<h5>其他信息
								<i class='requireds'>*</i>
								</h5>																						
								<div class="form-group ">
									<label>对方凭证：</label>
									<select name="otherProof" disabled="disabled">
										<option value="0">财支</option>
										<option value="1">财收</option>
									</select>
									<label>&emsp; —— </label>
								</div>
								<div class="form-group ">
									<input type="text"   name="otherProofStart" unselectable="on" readonly/>
									<label>&emsp; —— </label>
								</div>
								<div class="form-group ">
									<input type="text"   name="otherProofEnd" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>编号：</label>
									<input type="text"   name="otherProofNum" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>己方凭证：</label>
									<select name="selfProof" disabled="disabled">
										<option value="0">财支</option>
										<option value="1">财收</option>
									</select>
									<label>&emsp; —— </label>
								</div>
								<div class="form-group ">
									<input type="text"   name="selfProofStart" unselectable="on" readonly/>
									<label>&emsp; —— </label>
								</div>
								<div class="form-group ">
									<input type="text"   name="selfProofEnd" unselectable="on" readonly/>
								</div>
								<div class="form-group ">
									<label>编号：</label>
									<input type="text"   name="selfProofNum" unselectable="on" readonly/>
								</div>
							</div>		
						</form>
					</div>
				</div>
			</div>
		</div>
		        <!-- 未选择数据提醒模态框 -->
				<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="nullModal" data-backdrop="static">
					<div class="modal-dialog " role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">提示</h4>
								<span data-dismiss="modal" aria-label="Close"></span>
							</div>
							<div class="modal-body">
								<div class="body-content">
									<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
									<span>请选择一条要操作的数据</span>
								</div>
								<div class="row clearfix ">
									<div class="col-md-12 column modal_btn">
										<button type="button" class="btn sureBtn">确定</button>
										<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 提醒模态框 -->
				<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="deltialModal" data-backdrop="static">
					<div class="modal-dialog " role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">提示</h4>
								<span data-dismiss="modal" aria-label="Close"></span>
							</div>
							<div class="modal-body">
								<div class="body-content">
									<div><span>您已选择</span><b class="errnums">13</b> <span id="msgSpan"></span></div>
								</div>
								<div class="row clearfix ">
									<div class="col-md-12 column modal_btn">
										<button type="button" class="btn sureBtn">确定</button>
										<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
	
	</body>
</html>