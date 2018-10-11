//# sourceURL=driverCheckingShortOrderFinance.js
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
	data = $.fd.DataTable.loadPageOpts(page,data);
    $.fd.DataTable.ajax(source, data, callback, queryCondition);
  };

  // 短驳运单财务表信息一览
  var tblShortOrderFinance = $("#tblShortOrderFinance").FdDataTable({
    serverData : retrieveData,
    ajaxSource : "api/shortOrderFinances",
    columns : [
    {
      data : "shOrderFinId"//复选框
    }
    ,{
      data : "projectCode"
    }
    ,{
      data : "orderCode"
    }
    ,{
      data : "branchGroupName"
    }
    ,{
      data : "projectType",
      render:function(data,type,full,meta) {
    	  var returnVal = '';
    	  switch (data) {
			case 0:
				returnVal = '集装箱';
				break;
			case 1:
				returnVal = '散堆装';
				break;
			default:
				break;
			}
    	  return returnVal;
      }
    }
    ,{
      data : "financeStatus",
      render:function(data,type,full,meta) {
    	  var returnVal = '';
    	  switch (data) {
			case 0:
				returnVal = '待确认';
				break;
			case 1:
				returnVal = '待计算';
				break;
			case 2:
				returnVal = '待审核';
				break;
			case 3:
				returnVal = '已审核';
				break;
			default:
				break;
			}
    	  return returnVal;
      }
    }
    ,{
      data : "billingDate"
    }
    ,{
      data : "financeAuditDate"
    }
    ,{
      data : "sendCompany"
    }
    ,{
      data : "receiptCompany"
    }
    ,{
      data : "cargoName"
    }
    ,{
      data : "carPlateNumber"
    }
    ,{
      data : "containerOneSendNet",
      render:function(data,type,full,meta) {
    	  var containerOneSendNet = full.containerOneSendNet;
    	  var containerTwoSendNet = full.containerTwoSendNet;
    	  return (parseFloat(containerOneSendNet)+parseFloat(containerTwoSendNet)).toFixed(2);
      }
    }
    ,{
      data : "pieceNumber"
    }
    ,{
      data : "shortBargeCost"
    }
    ,{
      data : "buckleFigure"
    }
    ,{
      data : "subsidy"
    }
    ,{
      data : "shouldPayFigure"
    }
    ],
    columnDefs : [ {
      targets : [ 0 ],
      visible : false
    } ]
  });
  
  // 短驳运单财务表 - 初始化开始对账页面
	$("#btnShortOrderFinanceStartChecking").click(function(e) {
		e.preventDefault();
		var type = "post";
		var url = "projectManagment/getProjectByFinance.do";
		  $.fd.ajax({
			  url : url,
			  type : type,
			  success : function(data) {
				  var selectStr = '';
				  $.each(data.data,function(index,project){
					  selectStr += '<option value="' + project.id + '">' + project.projectCode + '</option>';
				  })
				  // 填充下拉列表
				  $("#chooseProjectFormId [name=projectId]").html(selectStr);
					// 重置内容
					$("#chooseProjectFormId").resetText();
					//自动填充 项目信息
					autoPaddingProjectInfo();
			  }
		  });
		  $("#chooseProjectDivId").modal("show");
	});
	
	// 短驳运单财务表 - 填充开始对账页面
	$("#projectId").change(function(e) {
		e.preventDefault();
		autoPaddingProjectInfo();
	});
	
	//自动填充 项目信息
	function autoPaddingProjectInfo() {
		var projectId=$("#chooseProjectFormId [name=projectId]").children('option:selected').val();
		var type = "post";
		var url = "projectManagment/getProject.do";
		$.ajax({
			url : url,
			type : type,
			data : {id:projectId},
			dataType : "json",
			cache : false,
			success : function(data) {
				var project = data.data;
				$("#chooseProjectFormId").resetText();
				$("#chooseProjectFormId").loadJson2Text(project);
			}
		});
	}
	
	// 选择项目信息后 点击 开始对账按钮
	$("#btnShortOrderFinanceQueryProjectId").click(function(e) {
		var projectId=$("#chooseProjectFormId [name=projectId]").children('option:selected').val();
		//queryCondition.projectId=projectId;//赋值项目主键
		 $("#frmShortOrderFinanceQuery [name=projectId]").val(projectId);
		//tblShortOrderFinance.reload();
		 $("#chooseProjectDivId").modal("hide");
		 $("#btnShortOrderFinanceQuery").click();
	});
	
	// 初始化打包信息
	$("#btnShortOrderFinancePack").click(function(e) {
		e.preventDefault();
		var shOrderFinIds = tblShortOrderFinance.getSelections('shOrderFinId');
		if (!shOrderFinIds || shOrderFinIds.length ==0) {
			  $.fd.msg.notice("请至少选择一条信息");
			  return false;
		  }
		var shOrderFins = tblShortOrderFinance.getSelections();
		$("#tblInitPack").bootstrapTable('load', shOrderFins);
		var shOrderFinss = tblShortOrderFinance.getSelecteds();
		
		$("#tblInitPack").bootstrapTable('load', shOrderFinss);
		$("#initPackDivId").modal("show");
	});


  // 检索
  $("#btnShortOrderFinanceQuery").click(function(e) {
    e.preventDefault();
    // 刷新检索条件
    loadqueryCondition();
    // 刷新数据
    tblShortOrderFinance.reload();
  });

  function loadqueryCondition(){
    queryCondition.projectId = $("#frmShortOrderFinanceQuery [name=projectId]").val();
    return queryCondition;
  }

  });
}(jQuery));
