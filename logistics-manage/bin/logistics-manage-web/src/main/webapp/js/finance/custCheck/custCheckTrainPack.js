//# sourceURL=custCheckTrainPack.js
/**
 * @title 短驳打包信息表
 * @description 短驳打包信息表
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

  // 获取短驳打包信息表信息
  var retrieveData = function(source, data, callback) {
	  queryCondition.projectId = $.getRequest("projectId");
	  queryCondition.orderType = "3";
	  queryCondition.custCheckConId = $.getRequest("custCheckConId");
	  queryCondition.custPackId = $.getRequest("custPackId");
	  data = $.fd.DataTable.loadPageOpts(page,data);
    $.fd.DataTable.ajax(source, data, callback, queryCondition);
  };


  // 短驳打包信息表信息一览
  var tblShortPack = $("#tblShortPack").FdDataTable({
    serverData : retrieveData,
    ajaxSource : "api/customerPacks/orderInfo",
    columns : [
    {
      data : "id"
    }
    ,{
      data : "pleaseTrainNumber"
    }
    ,{
      data : "checkingStatus"
    }
    ,{
        data : "beginSite"
      }
      ,{
        data : "beginPlace"
      }
      ,{
        data : "endSite"
      }
      ,{
        data : "endPlace"
      }
    ,{
      data : "cargoName"
    }
    ,{
      data : "createDate"
    }
    ,{
      data : "arriveDate"
    }
    ,{
      data : "entruckWeight"
    }
    ,{
        data : "prodectMoney"
      }
      ,{
        data : "shouldTaxMoney"
      }
    ],
    columnDefs : [ {
      targets : [ 0,2 ],
      visible : false
    } ]
  });

  // 检索
  $("#btnShortPackQuery").click(function(e) {
    e.preventDefault();
    // 刷新检索条件
    loadqueryCondition();
    // 刷新数据
    tblShortPack.reload();
  });

  function loadqueryCondition(){
    queryCondition.projectId = $("#frmShortPackQuery [name=projectId]").val();
    return queryCondition;
  }
//初始化打包信息
	$("#btnCustCheckPack").click(function(e) {
		e.preventDefault();
		var shOrderFinIds = tblShortPack.getSelections('id');
		if (!shOrderFinIds || shOrderFinIds.length ==0) {
			  $.fd.msg.notice("请至少选择一条信息");
			  return false;
		}
		var ids = $.arrToSplit(shOrderFinIds);
		//1table data
		var shOrderFinss = tblShortPack.getSelecteds(shOrderFinIds);
		$("#tblInitPack").bootstrapTable('load', shOrderFinss);

		//4表单赋值
		setValue(shOrderFinss,ids);
		$("#initPackDivId").modal("show");
});
	//表单赋值
	function setValue(shOrderFinss,ids){
		$("#initPackDivId [name=projectId]").val(shOrderFinss[0].projectId);
		$("#initPackDivId [name=projectCode]").val(shOrderFinss[0].projectCode);
		$("#initPackDivId [name=branchGroupName]").val(shOrderFinss[0].branchGroupName);
		$("#initPackFormId [name=orderIds]").val(ids);
	}
	$("#initPackDivId [name=btnInitPackSave]").click(function(e) {
	    e.preventDefault();
		var type = "post";
		var url = "api/customerPack";
		var shortOrderFinance = $("#initPackFormId").serializeJson();
		shortOrderFinance.packType = "1";
		shortOrderFinance.projectId = $.getRequest("projectId");
		shortOrderFinance.custCheckConId = $.getRequest("custCheckConId");
		
		$.fd.ajax({
			url : url,
			type : type,
			data : shortOrderFinance,
			success : function(d) {
				$("#initPackDivId").modal("hide");
				$("#btnCustomerCheckingConfQuery", parent.document).click();
				// 保存成功后，刷新数据
				tblShortPack.reload();
			}
		});
 });

  function initPage(){
	  var transportType=$.getRequest("transportType");
	  var projectId = $.getRequest("projectId");
	  if(transportType == "0"){         //汽运
		  hideButton();
		  $("#btnCustCheckCar").show();
	  }else if(transportType == "1"){   //接取
		  hideButton();
		  $("#btnCustCheckRecive").show();
	  }else if(transportType == "2"){   //送达
		  hideButton();
		  $("#btnCustCheckSend").show();
	  }else if(transportType == "3"){   //火运
		  hideButton();
		  $("#btnCustCheckTrain").show();
	  }else if(transportType == "4"){   //接取+火运
		  hideButton();
		  $("#btnCustCheckRecive").show();
		  $("#btnCustCheckTrain").show();
	  }else if(transportType == "5"){   //火运+送达
		  hideButton();
		  $("#btnCustCheckTrain").show();
		  $("#btnCustCheckSend").show();
	  }else if(transportType == "6"){   //联运（接取火运送达）
		  showButton();
		  $("#btnCustCheckCar").hide();
	  }else if(transportType == "7"){   //接取+送达
		  hideButton();
		  $("#btnCustCheckRecive").show();
		  $("#btnCustCheckSend").show();
	  }
  }
  $("#btnCustCheckCar").click(function(e) {
	    e.preventDefault();
	    window.open("custCheckCar.do"+getParam(), "custCheckFrame");
  });
  $("#btnCustCheckRecive").click(function(e) {
	    e.preventDefault();
	    window.open("custCheckRecive.do"+getParam(), "custCheckFrame");
  });
  $("#btnCustCheckSend").click(function(e) {
	    e.preventDefault();
	    window.open("custCheckSend.do"+getParam(), "custCheckFrame");
  });
  function getParam(){
    var transportType=$.getRequest("transportType");
	var projectId = $.getRequest("projectId");
	var custCheckConId = $.getRequest("custCheckConId");
	var param = "?transportType="+transportType+"&projectId="+projectId+"&custCheckConId="+custCheckConId;
	return param;
  }

  function hideButton(){
	  $("#btnCustCheckCar").hide();
	  $("#btnCustCheckRecive").hide();
	  $("#btnCustCheckTrain").hide();
	  $("#btnCustCheckSend").hide();
  }
  function showButton(){
	  $("#btnCustCheckCar").show();
	  $("#btnCustCheckRecive").show();
	  $("#btnCustCheckTrain").show();
	  $("#btnCustCheckSend").show();
  }
  initPage();

  });
}(jQuery));
