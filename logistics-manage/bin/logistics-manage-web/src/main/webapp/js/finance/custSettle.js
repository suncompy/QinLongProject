//# sourceURL=custSettle.js
/**
 * @title 客户结算表
 * @description 客户结算表
 * @author LiuJiefeng
 * @date 2018-03-29
 */
;
(function($) {
  'use strict';	
  $(function() {
  // 检索条件
  var queryCondition = {};
  var queryData = {};
  queryData.start = 0;
  queryData.length = 30;

  // 获取客户结算表信息
  var retrieveData = function(source, data, callback) {
    $.fd.DataTable.ajax(source, data, callback, queryCondition);
  };

  // 根据选中的客户结算表,获得该客户结算明细
  var reloadCustSettleDetail = function() {
    // 客户结算明细信息列表未创建时
    if (!$.fn.DataTable.isDataTable("#tblCustSettleDetail")) {
	tblCustSettleDetail = $("#tblCustSettleDetail").FdDataTable({
		serverData : getCustSettleDetailData,
		columns : [
		    {
		      data : "id"
		    }
		    ,{
		      data : "projectCode"
		    }
		    ,{
		        data : "packId"
		     }
		    ,{
		      data : "applyStatus",// 状态
				render : function(data, type, full, meta) {
					var returnVal = '';
					switch (data) {
					case 0:
						returnVal = '待结算';
						break;
					case 1:
						returnVal = '待审核';
						break;
					case 2:
						returnVal = '已审核';
						break;
					case 3:
						returnVal = '审核不通过';
						break;	
					default:
						break;
					}
					return returnVal;
				}
		    }
		    ,{
		      data : "settleOrg"
		    }
		    ,{
		      data : "settleUserName"
		    }
		    ,{
		      data : "applyMoney"
		    }
		    ,{
		      data : "settleModel",
		      render : function(data, type, full, meta) {
					var returnVal = '';
					switch (data) {
					case 0:
						returnVal = '现金';
						break;
					case 1:
						returnVal = '支票';
						break;
					case 2:
						returnVal = '转账';
						break;
					default:
						break;
					}
					return returnVal;
				}
		    }
		    ,{
		      data : "optUserName"
		    }
		    ,{
		      data : "auditUserName"
		    }
		    ,{
		      data : "auditTime"
		    }
		    ]
        });
    }else{
        tblCustSettleDetail.reload();
    }
  };

  // 客户结算表信息一览
  var tblCustSettle = $("#tblCustSettle").FdDataTable({
    serverData : retrieveData,
    rowSelect: reloadCustSettleDetail,
    displayLength : 20,
    ajaxSource : "api/custSettles",
    columns : [
    {
      data : "id"
    }
    ,{
        data : "createTime"
      }
    ,{
      data : "provideCompanyName"
    }
    ,{
      data : "settleStatus",// 状态
		render : function(data, type, full, meta) {
			var returnVal = '';
			switch (data) {
			case 0:
				returnVal = '待结算';
				break;
			case 1:
				returnVal = '结算中';
				break;
			case 2:
				returnVal = '已结清';
				break;
			default:
				break;
			}
			return returnVal;
		}
    },{
    	data : "accountNo"
    }
    ,{
        data : "projectCode"
      }
    ,{
      data : "packId"
    }
    ,{
        data : "totalOrder"
      }
    ,{
        data : "totalMoney"
      }
    ,{
      data : "settledMoney"
    }
    ,{
      data : "settingMoney"
    }
    ,{
      data : "updateTime"
    }
    ],
    columnDefs : [ {
      targets : [ 0 ],
      visible : false
    } ]
  });

  // 检索
  $("#btnCustSettleQuery").click(function(e) {
    e.preventDefault();
    // 刷新检索条件
    loadqueryCondition();
    // 刷新数据
    tblCustSettle.reload();
  });

  function loadqueryCondition(){
    queryCondition.projectCode = $("#frmCustSettleQuery [name=projectCode]").val();
    queryCondition.branchGroupName = $("#frmCustSettleQuery [name=branchGroupName]").val();
    queryCondition.beginDate = $("#frmCustSettleQuery [name=beginDate]").val();
    queryCondition.endDate = $("#frmCustSettleQuery [name=endDate]").val();
    return queryCondition;
  }

  /** **客户结算明细****** */
	// 客户结算明细信息一览
	var tblCustSettleDetail;

	// 获取客户结算明细
	var getCustSettleDetailData = function(source, data, callback) {
		// 选中行
		var d = tblCustSettle.getSelected();
		if(!d){
			callback({data:[], recordsTotal: 0, recordsFiltered: 0});
			return false;
		}
		var queryConditionDetail = {};
		queryConditionDetail.settleId=d["id"];
		$.fd.DataTable.ajax("api/custSettleDetails", data, callback, queryConditionDetail);
	};
	
  // 客户结算
  $("#btnCustSettleAdd").click(function(e) {
    e.preventDefault();
    var data = tblCustSettle.getSelected();
    // 重置表单内容
    $("#frmCustSettleDetail").reset();
    $("#frmCustSettleDetail").loadJson(data);
    $("#frmCustSettleDetail [name=settleId]").val(data.id);
    loadBizContactorSelect(data.provideCompanyId);
    $("#mdlCustSettleDetail").modal("show");
  });

  // 客户结算保存
  $("#btnCustSettleDetailSave").click(function(e) {
        e.preventDefault();
        var data = tblCustSettle.getSelected();
        var type = "post";
		var url = "api/custSettleDetail";
		var custSettleDetail = $("#frmCustSettleDetail").serializeJson();
		custSettleDetail.settleId=data.id;
		if(!custSettleDetail.settleOrg){
			$.fd.msg.notice("结算单位不能为空");
			return false;
		}
		if(!custSettleDetail.settleUserId){
			$.fd.msg.notice("人员不能为空");
			return false;
		}
		if(!custSettleDetail.settleModel){
			$.fd.msg.notice("结算方式不能为空");
			return false;
		}
		if(!custSettleDetail.applyMoney || Number(custSettleDetail.applyMoney)<=0){
			$.fd.msg.notice("申请结算金额不能为空且大于0");
			return false;
		}
		if(custSettleDetail.applyMoney>data.totalMoney-data.auditingMoney){
			$.fd.msg.notice("申请结算金额不能大于(发票金额-已申请金额)");
			return false;
		}
		$.fd.ajax({
			url : url,
			type : type,
			data : custSettleDetail,
			success : function(data) {
				$("#mdlCustSettleDetail").modal("hide");
				// 保存成功后，刷新数据
				tblCustSettle.reload();
				tblCustSettleDetail.reload();
			}
		});
  });
 //客户结算-财务审核
 $("#btnCustSettleFinAudit").click(function(e) {
   e.preventDefault();
   	var data = tblCustSettleDetail.getSelected();
	  var applyStatus = tblCustSettleDetail.getSelections('applyStatus');
	  if (!data) {
		  $.fd.msg.notice("请选择一条信息");
		  return false;
	  }
	  for (var i in applyStatus) {
		  if(applyStatus[i] != 1) {
			  $.fd.msg.notice("只能选择待审核信息");
			  return false;
		  }
	  }
	  $.confirm("财务审核","是否进行财务审核",{
		    success:function(){
		    	doAuditFinance(data,2);
		    },
		    failure:function(){
		    	doAuditFinance(data,3);
		    }
	 },{btnOk:"同意",btnCancel:"拒绝"});
  });
 function doAuditFinance(data,applyStatus){
	 var custSettleDetail = {};
	  custSettleDetail.applyStatus=applyStatus;
	  custSettleDetail.id=data.id;
	  custSettleDetail.applyMoney=data.applyMoney;
	  custSettleDetail.settleId=data.settleId;
	  var type = "put";
	  var url = "api/custSettleDetail";
	  $.fd.ajax({
			url : url,
			type : type,
			data : custSettleDetail,
			showMsg:true,
			success : function(data) {
				// 保存成功后，刷新数据
				tblCustSettle.reload();
				tblCustSettleDetail.reload();
			}
	});
  }
 /*$("#btnCustSettleFinAudit").click(function(e) {
   e.preventDefault();
   	var ids = tblCustSettleDetail.getSelections('id');
	  var applyStatus = tblCustSettleDetail.getSelections('applyStatus');
	  if (!ids || ids.length ==0) {
		  $.fd.msg.notice("请至少选择一条信息");
		  return false;
	  }
	  for (var i in applyStatus) {
		  if(applyStatus[i] != 1 &&applyStatus[i] != 3) {
			  $.fd.msg.notice("只能选择待审核信息");
			  return false;
		  }
	  }
	  $.confirm("财务审核","是否进行财务审核",{
		    success:function(){
		    	doAuditFinance(ids,2);
		    },
		    failure:function(){
		    	doAuditFinance(ids,3);
		    }
	 },{btnOk:"同意",btnCancel:"拒绝"});
  });
 function doAuditFinance(ids,applyStatus){
	 var data = tblCustSettle.getSelected();
	 var custSettleDetail = {};
	 custSettleDetail.applyStatus=applyStatus;
	  var type = "put";
	  var url = "api/custSettleDetail/"+ids;
	  $.fd.ajax({
			url : url,
			type : type,
			data : custSettleDetail,
			showMsg:true,
			success : function(data) {
				// 保存成功后，刷新数据
				tblCustSettle.reload();
				tblCustSettleDetail.reload();
			}
	});
  }
 */
  var bizContactor;
	//加载业务联系人下拉框
  function loadBizContactorSelect(customId){
	  bizContactor=$("#frmCustSettleDetail [name=settleUserId]").FdSelect2({url:'api/basicdata/types/bizContactor/ids/'+customId,type : 'bizContactor'});
  }
  $("#btnCustSettleListHis").click(function(e) {
	    e.preventDefault();
	    window.open("custSettleHistory.do", "contentIframe");  
  });
  });
}(jQuery));
