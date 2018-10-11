//# sourceURL=customerAccounts.js
/**
 * @title 短驳运单财务表
 * @description 短驳运单财务表
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 */
;
(function($) {
  'use strict';	
  $(function() {
  // 检索条件
  var queryCondition = {};
  var page = {};
  page.start = 0;
  page.length = 30;

  // 获取短驳运单财务表信息
  var retrieveData = function(source, data, callback) {
	queryCondition.invoiceStatus = 1;
	data = $.fd.DataTable.loadPageOpts(page,data);
    $.fd.DataTable.ajax(source, data, callback, queryCondition);
  };

  // 短驳运单财务表信息一览
  var tblCustomerAccounts = $("#tblCustomerAccounts").FdDataTable({
    serverData : retrieveData,
    ajaxSource : "api/customerPacks",
    columns : [
    {
      data : "custPackId"//复选框
    }
    ,{
      data : "createDate"//日期(即产生发票日期)
    }
    ,{
      data : "provideName"//往来类别(受票方)
    }
    ,{
      data : "settleAuditType",//结算状态
      render:function(data,type,full,meta) {
    	  var returnVal = '';
    	  switch (data) {
			case 0:
				returnVal = '待结算 ';
				break;
			case 1:
				returnVal = '结算财务待审核';
				break;
			case 2:
				returnVal = '结算审核通过';
				break;
			case 3:
				returnVal = '结算审核不通过';
				break;	
			default:
				break;
			}
    	  return returnVal;
      }
    }
    ,{
      data : "customerUnit"//结算单位(待确认)
    }
    ,{
      data : "customerName"//人员(待确认)
    }
    ,{
      data : "projectCode"//项目编号
    }
    ,{
      data : "custPackId"//对账单号(对账打包后产生的)
    }
    ,{
      data : "orderCount"//运单总数
    }
   /* ,{
      data : "--"//单价(项目表中)
    }*/
    ,{
      data : "totalMoney"//发票金额(即合计金额)
    }
    ,{
      data : "settledMoney"//已结金额
    }
    ,{
      data : "besettledMoney"//待结金额
    }
    ,{
      data : "settleType",//结算方式(以最后一次结算为准)
      render:function(data,type,full,meta) {
    	  var returnVal = '';
    	  switch (data) {
			case 0:
				returnVal = '现金 ';
				break;
			case 1:
				returnVal = '银行卡';
				break;
			case 2:
				returnVal = '信用卡';
				break;
			default:
				break;
			}
    	  return returnVal;
      }
    }
    ,{
      data : "receiveShortName"//存入账户(出具方账户)
    }
    ,{
      data : "auditName"//审核人
    }
    ,{
      data : "auditDate"//审核时间
    }
    ,{
      data : "settleDate"//更新时间
    }
    ],
    columnDefs : [ {
      targets : [ 0 ],
      visible : false
    } ]
  });
  
  //结算页面
	$("#btnCustomerAccountsSettlement").click(function(e) {
		var custPackId = tblCustomerAccounts.getSelections('custPackId');
		if (custPackId.length != 1) {
			  $.fd.msg.notice("请选择其中一条信息");
			  return false;
		  }
		var besettledMoney = tblCustomerAccounts.getSelections('besettledMoney');//判断待结金额大于0则可以结算
		for (var i in besettledMoney) {
			  if(besettledMoney[i] == 0) {
				  $.fd.msg.notice("只能选择未结算完的信息");
			      return false;
			  }
		  }
		// 重置内容
		$("#chooseProjectFormId").resetText();
		var custPackIds = tblCustomerAccounts.getSelecteds(custPackId);
		//自动填充 项目信息
		autoPaddingProjectInfo(custPackIds);
			 
		$("#settlementModal").modal("show");
	});
	
	// 短驳运单财务表 - 填充结算页面
	$("#projectId").change(function(e) {
		e.preventDefault();
		autoPaddingProjectInfo();
	});
	
	//自动填充 项目信息
	function autoPaddingProjectInfo(custPackIds) {
		$("#chooseProjectFormId [name=projectCode]").val(custPackIds[0].projectCode);//项目编号
		//$("#chooseProjectFormId [name=--]").val(custPackIds[0].--);//对账单号
		$("#chooseProjectFormId [name=orderCount]").val(custPackIds[0].orderCount);//总数量
		//$("#chooseProjectFormId [name=--]").val(custPackIds[0].--);//单价
		$("#chooseProjectFormId [name=receiveCompanyName]").val(custPackIds[0].receiveCompanyName);//往来类别
		//$("#chooseProjectFormId [name=--]").val(custPackIds[0].--);//结算单位
		//$("#chooseProjectFormId [name=suppliesAmount]").val(custPackIds[0].suppliesAmount);//人员
		//$("#chooseProjectFormId [name=--]").val(custPackIds[0].--);//流水号
		$("#chooseProjectFormId [name=totalMoney]").val(custPackIds[0].totalMoney);//发票金额
		$("#chooseProjectFormId [name=settledMoney]").val(custPackIds[0].settledMoney);//已结算金额
		$("#chooseProjectFormId [name=besettledMoney]").val(custPackIds[0].besettledMoney);//待结算金额
		$("#chooseProjectFormId [name=provideBankAccount]").val(custPackIds[0].provideBankAccount);//存入账户
		$("#chooseProjectFormId [name=settleType]").val(custPackIds[0].settleType);//结算方式
		$("#chooseProjectFormId [name=custPackId]").val(custPackIds[0].custPackId);//主键
	}
	
	//选择项目信息后 点击确定按钮
	$("#btnCustomerAccountsSave").click(function() {
	  var type = "post";
	  var url = "api/customerPack/settlePass";
	  var customerPack = $("#chooseProjectFormId").serializeJson();
	  var besettledMoneyVal = $("#chooseProjectFormId [name=besettledMoney]").val();
	  var settleMoneyVal = $("#chooseProjectFormId [name=settleMoney]").val();
	  if(besettledMoneyVal < settleMoneyVal){
		  $.fd.msg.notice("结算金额不可大于待结算金额！");
	      return false;
	  }
	  
	  delete customerPack.projectCode;
	  delete customerPack.branchGroupName;
	  $.fd.ajax({
		  url : url,
		  type : type,
		  data : customerPack,
		  success : function(d) {
			  // 保存成功后，刷新数据
			  tblCustomerAccounts.reload();
		  }
	  });
	  $("#settlementModal").modal("hide");
	});
	
	// 短驳运单财务表 - 财务审核
	  $("#btnCustomerAccountsFinanceAudit").click(function(e) {
		  e.preventDefault();
		  var custPackId = tblCustomerAccounts.getSelections('custPackId');
		  var settleAuditTypes = tblCustomerAccounts.getSelections('settleAuditType');//状态
		  if (!custPackId || custPackId.length == 0) {
			  $.fd.msg.notice("请至少选择一条信息");
			  return false;
		  }
		  for (var i in settleAuditTypes) {
			  if(settleAuditTypes[i] != 1 && settleAuditTypes[i] != 3) {
				  $.fd.msg.notice("只能选择待审核或审核不通过的信息");
			      return false;
			  }
		  }
		  var passFlag;
		  $.confirm("财务审核","你已选择1条数据，是否审核？",{
			    success:function(){
			    	passFlag = "0";
			    	var type = "put";
					var url = "api/customerPack/settleFinanceAudit/"+custPackId+ "/" + passFlag;
					$.fd.ajax({
						url : url,
						type : type,
						//showMsg:true,
						success : function(data) {
							// 保存成功后，刷新数据
							tblCustomerAccounts.reload();
						}
					});
			    },
			    failure:function(){
			    	passFlag = "1";
			    	var type = "put";
					var url = "api/customerPack/settleFinanceAudit/"+custPackId+ "/" + passFlag;
					$.fd.ajax({
						url : url,
						type : type,
						//showMsg:true,
						success : function(data) {
							// 保存成功后，刷新数据
							tblCustomerAccounts.reload();
						}
					});
			    }
			    });

		  
	  });
	
	  // 短驳运单财务表 - 财务反审核
	  $("#btnCustomerAccountsAgainstAudit").click(function(e) {
		  e.preventDefault();
		  var custPackId = tblCustomerAccounts.getSelections('custPackId');
		  var settleAuditTypes = tblCustomerAccounts.getSelections('settleAuditType');//状态
		  if (!custPackId || custPackId.length == 0) {
			  $.fd.msg.notice("请至少选择一条信息");
			  return false;
		  }
		  for (var i in settleAuditTypes) {
			  if(settleAuditTypes[i] != 2) {
				  $.fd.msg.notice("只能选择审核通过的信息");
			      return false;
			  }
		  }
		  
		  $.confirm("反审核","你已选择1条数据，是否还原到未审核状态",{
			    success:function(){
			    	var type = "put";
					var url = "api/customerPack/settleAgainstAudit/"+custPackId;
					$.fd.ajax({
						url : url,
						type : type,
						//showMsg:true,
						success : function(data) {
							// 保存成功后，刷新数据
							tblCustomerAccounts.reload();
						}
					});
			    }
		    });
		  
	  });

  //检索
  $("#btnCustomerAccountsConditionQuery").click(function(e) {
    e.preventDefault();
    // 刷新检索条件
    loadqueryCondition();
    // 刷新数据
    tblCustomerAccounts.reload();
  });

  function loadqueryCondition(){
    queryCondition.projectId = $("#frmCustomerAccountsQuery [name=projectId]").val();
    queryCondition.projectCode = $("#frmCustomerAccountsQuery [name=projectCode]").val();
    queryCondition.beginSite = $("#frmCustomerAccountsQuery [name=beginSite]").val();
    queryCondition.endSite = $("#frmCustomerAccountsQuery [name=endSite]").val();
    queryCondition.branchGroupName = $("#frmCustomerAccountsQuery [name=branchGroupName]").val();
    queryCondition.beginDate = $("#frmCustomerAccountsQuery [name=beginDate]").val();
    queryCondition.endDate = $("#frmCustomerAccountsQuery [name=endDate]").val();
    return queryCondition;
  }
  });
}(jQuery));
