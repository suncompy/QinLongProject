//# driverAccountsHistory.js
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
	  queryCondition.checkingStatus=1;
	  queryCondition.cashSettleStatus=2;
	data = $.fd.DataTable.loadPageOpts(page,data);
    $.fd.DataTable.ajax(source, data, callback, queryCondition);
  };

  // 短驳运单财务表信息一览
  var tblDriverAccounts = $("#tblDriverAccounts").FdDataTable({
    serverData : retrieveData,
    ajaxSource : "api/shortPacks",
    columns : [
    {
      data : "shPackId"//复选框
    }
    ,{
      data : "projectCode"//项目编号
    }
    ,{
      data : "branchGroupName"//分支机构
    }
    ,{
      data : "shPackId"//流水号
    }
    ,{
        data : "settleStatus",//费用状态
        render:function(data,type,full,meta) {
        	if(full.paymentId==1 || full.paymentId==2){
        		  return '无需领取';
        	  }else if(full.cashSettleStatus==2){
        		 return '已领取现金'; 
        	  }else{
        		 return '待领取现金';
        	  }
        }
      }
    ,{
      data : "suppliesSettleStatus",//油气卡状态
      render:function(data,type,full,meta) {
    	  if(full.paymentId==0){
    		  return '无需领取';
    	  }else if(data==1){
    		 return '已领取'; 
    	  }else{
    		 return '待领取 ';
    	  }
      }
    }
    ,{
    	data : "cashSettleStatus",//状态
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
      data : "freightChargeAmount"//费用合计
    }
    ,{
      data : "paymentId",//支付模式id
      render:function(data,type,full,meta) {
      	  var returnVal = '';
      	  switch (data) {
  			case 0:
  				returnVal = '全额现金 ';
  				break;
  			case 1:
  				returnVal = '全额油卡';
  				break;
  			case 2:
  				returnVal = '全额气卡';
  				break;
  			case 3:
  				returnVal = '钱卡混合';
  				break;
  			default:
  				break;
  			}
      	  return returnVal;
        }
    }
    ,{
      data : "payRatio"//支付比例
    }
    ,{
      data : "cashAmount"//应付现金
    }
    ,{
      data : "suppliesType",//油气类型
      render:function(data,type,full,meta) {
      	  var returnVal = '';
      	  switch (data) {
  			case 0:
  				returnVal = '油卡 ';
  				break;
  			case 1:
  				returnVal = '气卡';
  				break;
  			default:
  				break;
  			}
      	  return returnVal;
        }
    }
    ,{
      data : "suppliesAmount"//油气金额
    }
    ,{
        data : "provideBankAccount"//支付账户
      }
    ,{
        data : "receiver"//收款人
      }
    ,{
        data : "openBank"//开户行
      }
    ,{
        data : "bankAccount"//账号
      }
    ,{
        data : "suppliesExecuteName"//出纳
      }
    ,{
        data : "auditName"//审核人
      }
    ,{
        data : "settleAuditDate"//审核时间
      }
    ,{
        data : "createDate"//操作时间
      }
    ],
    columnDefs : [ {
      targets : [ 0 ],
      visible : false
    } ]
  });
  
  //结算页面
	$("#btnDriverAccountsSettlement").click(function(e) {
		var shPackIds = tblDriverAccounts.getSelections('shPackId');
		if (shPackIds.length != 1) {
			  $.fd.msg.notice("请选择其中一条信息");
			  return false;
		  }
		var cashSettleStatuss = tblDriverAccounts.getSelections('cashSettleStatus');//状态
		for (var i in cashSettleStatuss) {
			  if(cashSettleStatuss[i] != 0) {
				  $.fd.msg.notice("只能选择待结算信息");
			      return false;
			  }
		  }
		// 重置内容
		$("#chooseProjectFormId").resetText();
		var shPackIdss = tblDriverAccounts.getSelecteds(shPackIds);
		//自动填充 项目信息
		autoPaddingProjectInfo(shPackIdss);
			 
		$("#settlementModal").modal("show");
	});
	
	// 短驳运单财务表 - 填充结算页面
	$("#projectId").change(function(e) {
		e.preventDefault();
		autoPaddingProjectInfo();
	});
	
	//自动填充 项目信息
	function autoPaddingProjectInfo(shPackIdss) {
		$("#chooseProjectFormId [name=shPackId]").val(shPackIdss[0].shPackId);//对账单号
		$("#chooseProjectFormId [name=freightChargeAmount]").val(shPackIdss[0].freightChargeAmount);//费用合计
		//支付模式
		if(shPackIdss[0].paymentId == 0){
			$("#chooseProjectFormId [name=paymentId]").val("全额现金");
		}else if(shPackIdss[0].paymentId == 1){
			$("#chooseProjectFormId [name=paymentId]").val("全额油卡");
		}else if(shPackIdss[0].paymentId == 2){
			$("#chooseProjectFormId [name=paymentId]").val("全额气卡");
		}else if(shPackIdss[0].paymentId == 3){
			$("#chooseProjectFormId [name=paymentId]").val("钱卡混合");
		}
		$("#chooseProjectFormId [name=payRatio]").val(shPackIdss[0].payRatio);//支付比例
		$("#chooseProjectFormId [name=cashAmount]").val(shPackIdss[0].cashAmount);//应付现金
		$("#chooseProjectFormId [name=suppliesAmount]").val(shPackIdss[0].suppliesAmount);//油气金额
		//油气类型
		if(shPackIdss[0].suppliesType == 0){
			$("#chooseProjectFormId [name=suppliesType]").val("油卡");
		}else if(shPackIdss[0].suppliesType == 1){
			$("#chooseProjectFormId [name=suppliesType]").val("气卡");
		}
		$("#chooseProjectFormId [name=provideBankAccount]").val(shPackIdss[0].provideBankAccount);//支出账户
		$("#chooseProjectFormId [name=createUserId]").val(shPackIdss[0].auditName);//操作人
		$("#chooseProjectFormId [name=receiveUserId]").val(shPackIdss[0].driverName);//收款人
		$("#chooseProjectFormId [name=receiveOpenBank]").val(shPackIdss[0].openBank);//开户行
		$("#chooseProjectFormId [name=receiveOpenBankNum]").val(shPackIdss[0].bankNum);//行号
		$("#chooseProjectFormId [name=receiveBankAccount]").val(shPackIdss[0].accountNum);//银行账号
	}
	
	//选择项目信息后 点击 结算按钮
	$("#btnDriverAccountsQueryProjectId").click(function() {
	  var shPackIds = tblDriverAccounts.getSelections('shPackId');
	  var type = "put";
	  var url = "api/shortPacks/settlePass/"+shPackIds;
	  $.fd.ajax({
		  url : url,
		  type : type,
		  success : function(data) {
			  // 保存成功后，刷新数据
			  tblDriverAccounts.reload();
		  }
	  });
	  $("#settlementModal").modal("hide");
	});
	
	// 短驳运单财务表 - 财务审核
	  $("#btnDriverAccountsFinanceAudit").click(function(e) {
		  e.preventDefault();
		  var shPackIds = tblDriverAccounts.getSelections('shPackId');
		  var cashSettleStatuss = tblDriverAccounts.getSelections('cashSettleStatus');//状态
		  if (!shPackIds || shPackIds.length == 0) {
			  $.fd.msg.notice("请至少选择一条信息");
			  return false;
		  }
		  for (var i in cashSettleStatuss) {
			  if(cashSettleStatuss[i] != 1 && cashSettleStatuss[i] != 3) {
				  $.fd.msg.notice("只能选择待审核或审核不通过的信息");
				  return false;
			  }
		  }
		  var passFlag;
		  $.confirm("财务审核","你已选择1条数据，是否审核？",{
		    success:function(){
		    	passFlag = "0";
		    	var type = "put";
				  var url = "api/shortPacks/settleFinanceAudit/"+shPackIds+ "/" + passFlag;
				  $.fd.ajax({
					  url : url,
					  type : type,
					  //showMsg:true,
					  success : function(data) {
						  // 保存成功后，刷新数据
						  tblDriverAccounts.reload();
					  }
				  });
		    },
		    failure:function(){
		    	passFlag = "1";
		    	var type = "put";
				  var url = "api/shortPacks/settleFinanceAudit/"+shPackIds+ "/" + passFlag;
				  $.fd.ajax({
					  url : url,
					  type : type,
					  //showMsg:true,
					  success : function(data) {
						  // 保存成功后，刷新数据
						  tblDriverAccounts.reload();
					  }
				  });
		    }
		  });
	  });
	  
	  // 短驳运单财务表 - 财务反审核
	  $("#btnDriverAccountsAgainstAudit").click(function(e) {
		  e.preventDefault();
		  var shPackIds = tblDriverAccounts.getSelections('shPackId');
		  var cashSettleStatuss = tblDriverAccounts.getSelections('cashSettleStatus');//状态
		  if (!shPackIds || shPackIds.length ==0) {
			  $.fd.msg.notice("请至少选择一条信息");
			  return false;
		  }
		  for (var i in cashSettleStatuss) {
			  if(cashSettleStatuss[i] != 2) {
				  $.fd.msg.notice("只能选择已审核信息");
				  return false;
			  }
		  }
		  $.confirm("反审核","你已选择1条数据，是否还原到未审核状态",{
			    success:function(){
			    var type = "put";
				var url = "api/shortPacks/settleAgainstAudit/"+shPackIds;
				$.fd.ajax({
					url : url,
					type : type,
					//showMsg:true,
					success : function(data) {
						// 保存成功后，刷新数据
						tblDriverAccounts.reload();
					}
				});
			   }
		    });
	  });

  // 检索
  $("#btnDriverAccountsConditionQuery").click(function(e) {
    e.preventDefault();
    // 刷新检索条件
    loadqueryCondition();
    // 刷新数据
    tblDriverAccounts.reload();
  });

  function loadqueryCondition(){
    queryCondition.projectId = $("#frmDriverAccountsQuery [name=projectId]").val();
    queryCondition.projectCode = $("#frmDriverAccountsQuery [name=projectCode]").val();
    queryCondition.sendCargoCompanyId = $("#frmDriverAccountsQuery [name=sendCargoCompanyId]").val();
    queryCondition.receiveCargoCompanyId = $("#frmDriverAccountsQuery [name=receiveCargoCompanyId]").val();
    queryCondition.branchGroupName = $("#frmDriverAccountsQuery [name=branchGroupName]").val();
    queryCondition.beginDateSettle = $("#frmDriverAccountsQuery [name=beginDate]").val();
    queryCondition.endDateSettle = $("#frmDriverAccountsQuery [name=endDate]").val();
    return queryCondition;
  }
  
  $("#btnDriverAccountsDetail").click(function(e) {
	    e.preventDefault();
	    window.open("driverAccounts.do", "contentIframe");  
  });

  var sendCargoCompanyId = $("#frmDriverAccountsQuery [name=sendCargoCompanyId]").FdSelect2({type : 'customCompany'});
  var receiveCargoCompanyId = $("#frmDriverAccountsQuery [name=receiveCargoCompanyId]").FdSelect2({type : 'customCompany'});
  
  });
}(jQuery));
