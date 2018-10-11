//# sourceURL=enterpriseReceivablesHistory.js
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
	  queryCondition.cashSettleStatus=2;
	   queryCondition.besettledMoney=0; 
	data = $.fd.DataTable.loadPageOpts(page,data);
    $.fd.DataTable.ajax(source, data, callback, queryCondition);
  };

//根据选中的短驳运单财务表,获得该指标表
  var reloadPoint = function() {
    // 指标表信息列表未创建时
    if (!$.fn.DataTable.isDataTable("#tblPoint")) {
	tblPoint = $("#tblPoint").FdDataTable({
		serverData : getPointData,
		columns : [
		  {
		    data : "enterpriseReceivablesId"//复选框
		  }
		  ,{
		    data : "settleMoney"//结算金额
		  }
		  ,{
		    data : "auditStatus",//执行状态
			  render:function(data,type,full,meta) {
		    	  var returnVal = '';
		    	  switch (data) {
					case 0:
						returnVal = '未审核 ';
						break;
					case 1:
						returnVal = '已审核';
						break;
					default:
						break;
					}
		    	  return returnVal;
		      }
		  }
		  ,{
		    data : "receiveMoneyAccount"//收款账户
		  }
		  ,{
		    data : "settleUser"//结算人
		  }
		  ,{
		    data : "settleDate"//结算时间
		  }
		  ,{
		    data : "auditUser"//审核人
		  }
		  ,{
		    data : "auditDate"//审核时间
		  }
		  ],
		    columnDefs : [ {
		        targets : [ 0 ],
		        visible : false
		      } ]
        });
    }else{
        tblPoint.reload();
    }
  };
  
  // 短驳运单财务表信息一览
  var tblCustomerAccounts = $("#tblCustomerAccounts").FdDataTable({
    serverData : retrieveData,
    rowSelect: reloadPoint,
    ajaxSource : "api/costPacks",
    columns : [
   {
     data : "costPackId"//复选框
   }
   ,{
     data : "projectCode"//项目编号
   }
   ,{
     data : "branchGroupName"//分支机构
   }
   ,{
     data : "cargoName"//货物品名
   }
   ,{
       data : "cargoUnitPrice"//货物单价
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
 				returnVal = '结算中';
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
     data : "costPackCode"//对账单号
   }
   /*,{
     data : "paymentId",//费用名称
   }*/
   ,{
     data : "totalMoney"//发票金额
   }
   ,{
     data : "settledMoney"//已结金额
   }
   ,{
     data : "besettledMoney",//待结金额
   } 
   ,{
     data : "settleDate",//更新时间
   }
   ],
   columnDefs : [ {
     targets : [ 0 ],
     visible : false
   } ]
  });
  
  //结算页面
	$("#btnCustomerAccountsSettlement").click(function(e) {
		var costPackId = tblCustomerAccounts.getSelections('costPackId');
		if (costPackId.length != 1) {
			  $.fd.msg.notice("请选择其中一条信息");
			  return false;
		  }
		var cashSettleStatus = tblCustomerAccounts.getSelections('cashSettleStatus');
		/*for (var i in cashSettleStatus) {
			  if(cashSettleStatus[i] != 0) {
				  $.fd.msg.notice("只能选择未结算完的信息");
			      return false;
			  }
		  }*/
		// 重置内容
		$("#chooseProjectFormId").resetText();
		e.preventDefault();
		var type = 2;
		var url = "api/getAccountByUnitId.do";
		  $.fd.ajax({
			  url : url,
			  type : 'GET',
			  data :{type:type,id:1,,"accountType":"-1"},
			  success : function(data) {
				  var selectStr = '<option></option>';
				  $.each(data.data,function(index,account){
					  selectStr += '<option value="' + account.id + '">' + account.name + '</option>';
				  })
				  // 填充下拉列表
				  $("#chooseProjectFormId [name=receiveAccountId]").html(selectStr);
					// 重置内容
					$("#chooseProjectFormId").resetText();
					//自动填充 项目信息
			  }
		  });
		var costPackIds = tblCustomerAccounts.getSelecteds(costPackId);
		//自动填充 项目信息
		autoPaddingProjectInfo(costPackIds);
			 
		$("#settlementModal").modal("show");
	});
	
	// 短驳运单财务表 - 填充结算页面
	$("#receiveAccountId").change(function(e) {
		e.preventDefault();
		autoPaddingAccountInfo();
	});
	
	function autoPaddingAccountInfo(){
		var id = $("#receiveAccountId").val();
		 $("#receiveNumber").val("");
		 $("#receiveBankNumber").val("");
		 $.fd.ajax({
				type : 'GET',
				url : "api/getAccountDetailById",
				data : {"id":id},
				async:false, 
				success : function(data) {
					$("#receiveNumber").val(data.data.accountNum);
					$("#receiveBankNumber").val(data.data.bankNum);
				},
			}); 
	}
	
	//自动填充 项目信息
	function autoPaddingProjectInfo(costPackIds) {
		$("#chooseProjectFormId [name=shPackId]").val(costPackIds[0].costPackId);//项目编号
		$("#chooseProjectFormId [name=projectCode]").val(costPackIds[0].projectCode);//项目编号
		$("#chooseProjectFormId [name=costPackCode]").val(costPackIds[0].costPackCode);//对账单号
		$("#chooseProjectFormId [name=cargoUnitPrice]").val(costPackIds[0].cargoUnitPrice);//货物单价
		$("#chooseProjectFormId [name=totalMoney]").val(costPackIds[0].totalMoney);//发票金额
		$("#chooseProjectFormId [name=settledMoney]").val(costPackIds[0].settledMoney);//已结算金额
		$("#chooseProjectFormId [name=besettledMoney]").val(costPackIds[0].besettledMoney);//待结算金额
		$("#chooseProjectFormId [name=provideBankAccount]").val(costPackIds[0].provideBankAccount);//存入账户
		//$("#chooseProjectFormId [name=settleType]").val(costPackIds[0].settleType);//结算方式
		$("#chooseProjectFormId [name=costPackId]").val(costPackIds[0].costPackId);//主键
	}
	
	//选择项目信息后 点击确定按钮
	$("#btnCustomerAccountsSave").click(function() {
	  var type = "post";
	  var url = "api/costPacks/settlePass";
	  var costPack = $("#chooseProjectFormId").serializeJson();
	  var besettledMoneyVal = $("#chooseProjectFormId [name=besettledMoney]").val();
	  var settleMoneyVal = $("#chooseProjectFormId [name=settleMoney]").val();
	  if(parseFloat(besettledMoneyVal) < parseFloat(settleMoneyVal) ){
		  $.fd.msg.notice("结算金额不可大于待结算金额！");
	      return false;
	  }
	  
	  delete costPack.projectCode;
	  delete costPack.branchGroupName;
	  delete costPack.totalMoney;
	  $.fd.ajax({
		  url : url,
		  type : type,
		  data : costPack,
		  success : function(d) {
			  // 保存成功后，刷新数据
			  tblCustomerAccounts.reload();
			  tblPoint.reload();
		  }
	  });
	  $("#settlementModal").modal("hide");
	});
	
	// 短驳运单财务表 - 财务审核
	  $("#btnCustomerAccountsFinanceAudit").click(function(e) {
		  e.preventDefault();
		  var enterpriseReceivablesIds = tblPoint.getSelections('enterpriseReceivablesId');
		  var cashSettleStatuss = tblPoint.getSelections('auditStatus');//状态
		  if (!enterpriseReceivablesIds || enterpriseReceivablesIds.length == 0) {
			  $.fd.msg.notice("请至少选择一条信息");
			  return false;
		  }
		  for (var i in cashSettleStatuss) {
			  if(cashSettleStatuss[i] != 0 ) {
				  $.fd.msg.notice("只能选择待审核或审核不通过的信息");
			      return false;
			  }
		  }
		  var passFlag;
		  $.confirm("财务审核","你已选择1条数据，是否审核？",{
			    success:function(){
			    	passFlag = "0";
			    	var type = "put";
					var url = "api/enterpriseReceivables/settleFinanceAudit/"+enterpriseReceivablesIds+ "/" + passFlag;
					$.fd.ajax({
						url : url,
						type : type,
						//showMsg:true,
						success : function(data) {
							// 保存成功后，刷新数据
							tblCustomerAccounts.reload();
							tblPoint.reload();
						}
					});
			    },
			    failure:function(){
			    	passFlag = "1";
			    	var type = "put";
					var url = "api/enterpriseReceivables/settleFinanceAudit/"+enterpriseReceivablesIds+ "/" + passFlag;
					$.fd.ajax({
						url : url,
						type : type,
						//showMsg:true,
						success : function(data) {
							// 保存成功后，刷新数据
							tblCustomerAccounts.reload();
							tblPoint.reload();
						}
					});
			    }
			    });
		  
	  });
	
	  // 短驳运单财务表 - 财务反审核
	  $("#btnCustomerAccountsAgainstAudit").click(function(e) {
		  e.preventDefault();
		  var costPackId = tblCustomerAccounts.getSelections('costPackId');
		  var cashSettleStatuss = tblCustomerAccounts.getSelections('cashSettleStatus');//状态
		  if (!costPackId || costPackId.length == 0) {
			  $.fd.msg.notice("请至少选择一条信息");
			  return false;
		  }
		  for (var i in cashSettleStatuss) {
			  if(cashSettleStatuss[i] != 2) {
				  $.fd.msg.notice("只能选择审核通过的信息");
			      return false;
			  }
		  }
		  
		  $.confirm("反审核","你已选择1条数据，是否还原到未审核状态",{
			    success:function(){
			    	var type = "put";
					var url = "api/costPacks/settleAgainstAudit/"+costPackId;
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
  
  $("#btnCustomerAccountsDetail").click(function(e) {
	    e.preventDefault();
	    window.open("enterpriseReceivables.do", "contentIframe");  
  });
  
  /** **指标表****** */
	// 指标表信息一览
	var tblPoint;

	// 获取指标表
	var getPointData = function(source, data, callback) {
		// 选中行
		var d = tblCustomerAccounts.getSelected();
		if(!d){
			callback({data:[], recordsTotal: 0, recordsFiltered: 0});
			return false;
		}
		queryCondition.costPackId=d["costPackId"];//赋值财务信息主键
		$.fd.DataTable.ajax("api/getAllEnterpriseReceivables", data, callback, queryCondition);
	};
	
	//页面初始化操作
	$("#btnCustomerAccountsConditionQuery").click();
  
  });
}(jQuery));
