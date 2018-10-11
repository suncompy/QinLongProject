//# sourceURL=custCheckCar.js
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
	  queryCondition.orderType = "2";
	  queryCondition.custCheckConId = $.getRequest("custCheckConId");
	  data = $.fd.DataTable.loadPageOpts(page,data);
    $.fd.DataTable.ajax(source, data, callback, queryCondition);
  };
  // 短驳打包信息表信息一览
  var tblShortPack = $("#tblShortPack").FdDataTable({
    serverData : retrieveData,
    checkbox : false,
    ajaxSource : "api/customerCheckingConf/getOrdersByCustCheckConId",
    columns : [
    {
      data : "id"
    }
    ,{
      data : "orderCode"
    }
    ,{
      data : "carPlateNumber"
    }
    ,{
      data : "checkingStatus"
    }
    ,{
      data : "sendCompany"
    }
    ,{
      data : "receiptCompany"
    }
    ,{
      data : "pickupPlace"
    }
    ,{
      data : "arrivePlace"
    }
    ,{
      data : "cargoName"
    }
    ,{
      data : "createDate"
    }
    ,{
      data : "receipterDate"
    }
    ,{
      data : "sendTare"
    }
    ,{
      data : "sendGross"
    }
    ,{
      data : "containerTwoSendNet",
      render:function(data,type,full,meta) {
    	  var containerOneSendNet = full.containerOneSendNet;
    	  var containerTwoSendNet = !full.containerTwoSendNet?'0':full.containerTwoSendNet;
    	  return (parseFloat(containerOneSendNet)+parseFloat(containerTwoSendNet)).toFixed(2);
      }
    }
    ,{
        data : "prodectMoney"
      }
      ,{
        data : "shouldTaxMoney"
      }
    ],
    columnDefs : [ {
      targets : [ 0,3 ],
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
		setTableValue();
		$("#initPackDivId").modal("show");
		
  });
	function setTableValue(){
		var shortOrderFinance = {};
		shortOrderFinance.projectId = $.getRequest("projectId");
		shortOrderFinance.orderType = "2";
		shortOrderFinance.custCheckConId = $.getRequest("custCheckConId");
		var data = {};
		data.start = 0;
		data.length = 100000;
		data.condition = shortOrderFinance;
		var url = "api/customerCheckingConf/getOrdersByCustCheckConId";
		var type="get";
		$.fd.ajax({
			url : url,
			type : type,
			data : data,
			success : function(d) {
				$("#tblInitPack").bootstrapTable('load', d.data.data);
				var carPlateNumbers=[];
				if(!d.data || !d.data.data || d.data.data.length==0){
					return;
				}
				$("#initPackDivId [name=projectCode]").val(d.data.data[0].projectCode);
				$("#initPackDivId [name=branchGroupName]").val(d.data.data[0].branchGroupName);
				$("#initPackDivId [name=beginAddress]").val(d.data.data[0].pickupPlace);
				$("#initPackDivId [name=endAddress]").val(d.data.data[0].arrivePlace);
				$("#initPackDivId [name=packTruckDegree]").val(d.data.data.length);
				var trs = $("#tblInitPack tr");
				for(var i=1;i<trs.length;i++){
					var carPlateNumber = $(trs[i]).children().eq(1).text();
					carPlateNumbers.push(carPlateNumber);
				}
				var carNum = $.uniqueArr(carPlateNumbers).length;
				$("#initPackDivId [name=packTruckNum]").val(carNum);
			}
		});
	}

	$("#initPackDivId [name=btnInitPackSave]").click(function(e) {
	    e.preventDefault();
		var type = "post";
		var url = "api/customerPack";
		var shortOrderFinance = $("#initPackFormId").serializeJson();
		shortOrderFinance.packType = "0";
		shortOrderFinance.projectId = $.getRequest("projectId");
		shortOrderFinance.custCheckConId = $.getRequest("custCheckConId");
		shortOrderFinance.orderType = "2";
		delete shortOrderFinance.projectCode;
		delete shortOrderFinance.branchGroupName;
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
	  //$("#frmShortPackQuery [name=projectId]").val(projectId);
	  //$("#btnShortPackQuery").click();
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
  $("#btnCustCheckRecive").click(function(e) {
	    e.preventDefault();
	    window.open("custCheckRecive.do"+getParam(), "custCheckFrame");
});
$("#btnCustCheckTrain").click(function(e) {
	    e.preventDefault();
	    window.open("custCheckTrain.do"+getParam(), "custCheckFrame");
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
