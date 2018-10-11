//# sourceURL=dotAccounts.js
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
	  queryCondition.suppliesSettleStatus=0;
	  queryCondition.checkingStatus=1;
	data = $.fd.DataTable.loadPageOpts(page,data);
    $.fd.DataTable.ajax(source, data, callback, queryCondition);
  };

//根据选中的短驳运单财务表,获得该指标表
 var reloadPoint = function() {
   // 指标表信息列表未创建时
   if (!$.fn.DataTable.isDataTable("#tblPoint")) {
	tblPoint = $("#tblPoint").FdDataTable({
	    serverData : getOilGasCards,
	    columns : [
	    {
	      data : "id"//复选框
	    }
	    ,{
	      data : "shPackId"//对账单号
	    }
	    ,{
	      data : "cardTypeName"//
	    }
	    ,{
	      data : "cardMoney"//
	    }
	    ,{
	      data : "cardNum"//油气卡号
	    }
	    ],
	    columnDefs : [ {
	      targets : [ 0,1 ],
	      visible : false
	    } ]
	  });
   }else{
       tblPoint.reload();
   }
 };
  // 短驳运单财务表信息一览
  var tblDotAccounts = $("#tblDotAccounts").FdDataTable({
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
      data : "shPackId"//对账单号
    }
    ,{
      data : "suppliesSettleStatus",//执行状态
	  render:function(data,type,full,meta) {
      	  var returnVal = '';
      	  switch (data) {
  			case 0:
  				returnVal = '待领取 ';
  				break;
  			case 1:
  				returnVal = '已领取';
  				break;
  			case 2:
  				returnVal = '无需领取';
  				break;
  			default:
  				break;
  			}
      	  return returnVal;
        }
    }
    ,{
        data : "suppliesType",
        render:function(data,type,full,meta) {
      	  var returnVal = '';
      	  switch (data) {
  			case 0:
  				returnVal = '油卡';
  				break;
  			case 1:
  				returnVal = '气卡';
  				break;
  			default:
  				break;
  			}
      	  if(0==full.paymentId||3==full.paymentId){
      		  return '-';
      	  }
      	  return returnVal;
        }
      }
    ,{
      data : "suppliesAmount"//油气金额
    }
    /*,{
      data : "--"//油气卡号
    }*/
    ,{
      data : "suppliesReceiveType",//领取方式
	  render:function(data,type,full,meta) {
      	  var returnVal = '';
      	  switch (data) {
  			case 0:
  				returnVal = '网点领取';
  				break;
  			case 1:
  				returnVal = '人员配送';
  				break;
  			default:
  				break;
  			}
      	  return returnVal;
        }
    }
    ,{
      data : "receiveUserName"//领取人
    }
    ,{
      data : "receiveUserPhone"//联系方式
    }
    /*,{
      data : "suppliesExecuteName"//执行人
    }
    ,{
      data : "suppliesExecuteDate"//执行时间
    }
    ,{
      data : "--"//交账时间
    }*/
    ],
    columnDefs : [ {
      targets : [ 0 ],
      visible : false
    } ]
  });
  
  //结算页面
	$("#btnDotAccountsSettlement").click(function(e) {
		var shPackId = tblDotAccounts.getSelections('shPackId');
		if (shPackId.length != 1) {
			  $.fd.msg.notice("请选择其中一条信息");
			  return false;
		  }
		var type = "get";
		var url = "api/shortPacks/"+shPackId;
		  $.fd.ajax({
			  url : url,
			  type : type,
			  success : function(data) {
				 var paymentId = data.data.paymentId;
				 if(paymentId=="0"){
					 $("#settleInformation").hide();
					 $("#btnDotAccountsAddSupplies").hide();
					 $("#chooseProjectFormId [name=suppliesTypeName]").hide();
				 }else if(paymentId=="3"){
					 $("#settleInformation").show();
					 $("#btnDotAccountsAddSupplies").show();
					 $("#chooseProjectFormId [name=suppliesTypeName]").hide();
				 }else{
					 $("#settleInformation").show();
					 $("#btnDotAccountsAddSupplies").show();
					 $("#chooseProjectFormId [name=suppliesTypeName]").show();
				 }
				// 重置内容
					$("#chooseProjectFormId").resetText();
					var data = tblDotAccounts.getSelected();
					$("#chooseProjectFormId").loadJson(data); 
					var oilGasCardUnused = $("#chooseProjectFormId [name=suppliesNum]").FdSelect2({url:'api/basicdata/types/oilGasCardUnused/ids/'+data.paymentId,type : 'oilGasCardUnused'});
					reloadPoint();
					var suppliesSettleStatus = tblDotAccounts.getSelections('suppliesSettleStatus');//状态
					for (var i in suppliesSettleStatus) {
						  if(suppliesSettleStatus[i] != 0) {
							  $("#chooseProjectFormId [name=boxtools]").hide();
							  $("#btnDotAccountsQueryProjectId").hide();
						  }else{
							  $("#chooseProjectFormId [name=boxtools]").show();
							  $("#btnDotAccountsQueryProjectId").show();
						  }
					  }
					$("#settlementModal").modal("show");
			  }
		  });
		  
		
	});
	
	// 短驳运单财务表 - 填充结算页面
	$("#projectId").change(function(e) {
		e.preventDefault();
		autoPaddingProjectInfo();
	});
	
	//自动填充 项目信息
	function autoPaddingProjectInfo(shPackIds) {
		$("#chooseProjectFormId [name=projectCode]").val(shPackIds[0].projectCode);//项目编号
		//$("#chooseProjectFormId [name=--]").val(shPackIds[0].--);//对账单号
		$("#chooseProjectFormId [name=branchGroupName]").val(shPackIds[0].branchGroupName);//分支机构
		$("#chooseProjectFormId [name=suppliesAmount]").val(shPackIds[0].suppliesAmount);//待支付金额
		$("#chooseProjectFormId [name=receiveUserId]").val(shPackIds[0].driverName);//领取人
		$("#chooseProjectFormId [name=suppliesReceiveType]").val(shPackIds[0].suppliesReceiveType);//领取方式
	}

	  // 删除
	  $("#btnDotAccountsAddSuppliesDel").click(function(e) {
	    e.preventDefault();
	    var datas = tblPoint.getSelections('id');

	    if (datas.length == 0) {
	      $.fd.msg.notice("是否删除选中油气卡");
	      return false;
	    }
	    var url = "api/shortPacks/oilGasCards/batch";
	    var type="batchDel";
	    $.confirm("删除","是否删除选中油气卡",{
		    success:function(){
				$.fd.ajax({
					url : url,
					type : type,
					data :{ids :datas},
					success : function(d) {
						tblPoint.reload();
					}
				});
		    }
	    });
	  });
	//点击添加按钮
	$("#btnDotAccountsAddSupplies").click(function(e){
		e.preventDefault();
		var data = tblDotAccounts.getSelected();
		var cardId = $("#chooseProjectFormId [name=suppliesNum]").val();
		var url="api/shortPacks/"+data.shPackId+"/oilGasCards/"+cardId;
		var type="put";
		$.fd.ajax({
			  url : url,
			  type : type,
			  success : function(d) {
				  tblPoint.reload();
			  }
		});
	});
	
	$("#chooseProjectFormId [name=suppliesNum]").off('change.dotAccounts').on('change.dotAccounts', function (e) {
  		  e.preventDefault();
  		var suppliesNumTemp =$("#chooseProjectFormId [name=suppliesNum]").find("option:selected").text();
  		var cardMoney = suppliesNumTemp?suppliesNumTemp.split("#")[1]:null;
  		var cardMoneyObj = $(this).parent().next().next().children();
  		var idObj = $(this).parent().next().next().children().next();
  		cardMoneyObj.val(cardMoney);
		idObj.val($(this).val());  		  	   
   });
	
	//选择项目信息后 点击 结算按钮
	$("#btnDotAccountsQueryProjectId").click(function() {
	  var shPackId = tblDotAccounts.getSelections('shPackId');
	  var type = "put";
	  var url = "api/shortPacks/settlePassNode/" + shPackId;
	  var cardMoneyVal = tblPoint.getAllRowData("cardMoney");
	  var suppliesAmount = $("#chooseProjectFormId [name=suppliesAmount]").val();
	  var suppliesAmountStr = parseInt(suppliesAmount);
	  var suppliesAmountInt = Number(suppliesAmountStr);
	  var sum = 0;
	  for(var i = 0; i < cardMoneyVal.length; i ++){
		  
			 var cardMoneyVar = cardMoneyVal[i];
			 if(cardMoneyVar!=""){
				 var  cardMoneyStr = parseInt(cardMoneyVar);
				 var  cardMoneyInt = Number(cardMoneyStr);
				 sum = sum + cardMoneyInt;
			 }
		  }
	  if(sum == suppliesAmountInt){
		  $.fd.ajax({
			  url : url,
			  type : type,
			  showMsg:true,
			  success : function(data) {
				  // 保存成功后，刷新数据
				  tblDotAccounts.reload();
				  $("#settlementModal").modal("hide");
			  }
		  });
	  }else{
		  $.fd.msg.notice("油气卡总金额不等于待支付金额，请核实后重新结算！");
	      return false;
	  }
	});
	
  // 检索
  $("#btnDotAccountsConditionQuery").click(function(e) {
    e.preventDefault();
    // 刷新检索条件
    loadqueryCondition();
    // 刷新数据
    tblDotAccounts.reload();
  });

  function loadqueryCondition(){
    queryCondition.projectId = $("#frmDotAccountsQuery [name=projectId]").val();
    queryCondition.projectCode = $("#frmDotAccountsQuery [name=projectCode]").val();
    queryCondition.sendCargoCompanyId = $("#frmDotAccountsQuery [name=sendCargoCompanyId]").val();
    queryCondition.receiveCargoCompanyId = $("#frmDotAccountsQuery [name=receiveCargoCompanyId]").val();
    queryCondition.branchGroupName = $("#frmDotAccountsQuery [name=branchGroupName]").val();
    queryCondition.beginDateCheck = $("#frmDotAccountsQuery [name=beginDate]").val();
    queryCondition.endDateCheck = $("#frmDotAccountsQuery [name=endDate]").val();
    return queryCondition;
  }
  
  var tblPoint;
  //获取指标表
  var getOilGasCards = function(source, data, callback) {
		// 选中行
		var d = tblDotAccounts.getSelected();
		if(!d){
			callback({data:[], recordsTotal: 0, recordsFiltered: 0});
			return false;
		}
		queryCondition.shPackId=d["shPackId"];//赋值财务信息主键
		$.fd.DataTable.ajax("api/shortPacks/oilGasCards", data, callback, queryCondition);
		delete queryCondition.shPackId;
	};
  
	 $("#btnDotAccountsHistoryDetail").click(function(e) {
		    e.preventDefault();
		    window.open("dotAccountsHistory.do", "contentIframe");  
	  });
	 
	  var sendCargoCompanyId = $("#frmDotAccountsQuery [name=sendCargoCompanyId]").FdSelect2({type : 'customCompany'});
	  var receiveCargoCompanyId = $("#frmDotAccountsQuery [name=receiveCargoCompanyId]").FdSelect2({type : 'customCompany'});
  });
}(jQuery));
