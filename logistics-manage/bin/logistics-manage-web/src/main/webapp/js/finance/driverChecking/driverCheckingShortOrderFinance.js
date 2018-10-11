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
    ajaxSource : "api/getShortOrderFinancesByProjectId",
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
      data : "chargedWeight"
    }
    ,{
      data : "pieceNumber"
    }
    ,{
      data : "shortBargeCost"
    }
    ,{
      data : "deductionMoney"
    }
    ,{
      data : "subsidyTransitMoney"
    }
    ,{
      data : "payableTransitMoney"
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
		var type = "get";
		var url = "projectManagment/getProjectByFinance.do";
		  $.fd.ajax({
			  url : url,
			  type : type,
			  data : {checkFlag:2},
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
		 /* $('#chooseProjectFormId [name=projectId]').combobox({
	            url: 'projectManagment/getProjectByFinance.do',
	            valueField: 'id',
	            textField: 'projectCode',
	            callback :function(){
	            	// 重置内容
	    			$("#chooseProjectFormId").resetText();
	    			//自动填充 项目信息
	    			autoPaddingProjectInfo();
	            }
		  });*/
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
		/*$.ajax({
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
		});*/
		$.fd.ajax({
			url : "api/checkConf/projectId/"+projectId+"/2",
			type: "get",
			success : function(data) {
				var project = data.data;
				$("#chooseProjectFormId").resetText();
				$("#chooseProjectFormId").loadJson2Text(project);
			}
		})
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
		//1table data
		var shOrderFinss = tblShortOrderFinance.getSelecteds(shOrderFinIds);
		$("#tblInitPack").bootstrapTable('load', shOrderFinss);
		
		//2支付模式
		//loadPayModeSelect();
		//3领取人
		var ids = $.arrToSplit(shOrderFinIds);
		loadReceiverSelect(ids);
		//4表单赋值
		setValue(shOrderFinss,ids);
		$("#initPackDivId").modal("show");
	});
	
	$("#initPackFormId [name=paymentId]").change(function(e) {
		e.preventDefault();
		var paymentId = $("#initPackFormId [name=paymentId]").val();
		if('0'==paymentId){
			$("#initPackFormId [name=payRatio]").attr("readonly","readonly");
			$("#initPackFormId [name=cashPayType]").removeAttr("disabled"); 
			$("#initPackFormId [name=suppliesReceiveType]").attr("disabled","disabled"); 
		}else if('3'==paymentId){
			$("#initPackFormId [name=payRatio]").removeAttr("readonly");
			$("#initPackFormId [name=cashPayType]").removeAttr("disabled"); 
			$("#initPackFormId [name=suppliesReceiveType]").removeAttr("disabled"); 
		}else{
			$("#initPackFormId [name=payRatio]").attr("readonly","readonly"); 
			$("#initPackFormId [name=cashPayType]").attr("disabled","disabled"); 
			$("#initPackFormId [name=suppliesReceiveType]").removeAttr("disabled"); 
		}	
	});
	
	//表单赋值
	function setValue(shOrderFinss,ids){
		$("#initPackFormId [name=projectId]").val(shOrderFinss[0].projectId);
		$("#initPackFormId [name=projectCode]").val(shOrderFinss[0].projectCode);
		$("#initPackFormId [name=branchGroupName]").val(shOrderFinss[0].branchGroupName);
		$("#initPackFormId [name=shOrderFinIds]").val(ids);

		var carPlateNumbers = tblShortOrderFinance.getSelections('carPlateNumber');
		var carNum = $.uniqueArr(carPlateNumbers).length;
		$("#initPackFormId [name=packTruckNum]").val(carNum);
		$("#initPackFormId [name=packTruckDegree]").val(carPlateNumbers.length);
	}

	//加载支付模式下拉框
	function loadPayModeSelect(){
		//加载补交项目
		$('#initPackFormId [name=paymentId]').combobox({
            url: 'payment/listAllPayment.do',
            valueField: 'id',
            textField: 'name'
	    });
	}	
	var reciverFinance;
	//加载领取人下拉框
  function loadReceiverSelect(ids){
	  reciverFinance=$("#initPackFormId [name=receiveUserId]").FdSelect2({url:'api/basicdata/types/reciverFinance/ids/'+ids,type : 'reciverFinance'});
  }
  $("#initPackFormId [name=receiveUserId]").off('change.driver').on('change.driver', function (e) {
		  e.preventDefault();
		var driverId = $(this).val();
		var url = "api/shortPacks/driverBank/"+driverId;
		var type="get";
		$.fd.ajax({
				url : url,
				type : type,
				success : function(d) {
					if(d && d.data){
						$("#initPackDivId [name=receiveUserType]").val(d.data.receiveUserType);
						$("#initPackDivId [name=openBank]").val(d.data.openBank);
						$("#initPackDivId [name=bankAccount]").val(d.data.bankCardNo);
					}
				}
		}); 		  	   
});
   $("#initPackFormId [name=receiveUserId]").click(function(e) {
	    e.preventDefault();
	    var driverId = $("#initPackFormId [name=receiveUserId]").val();
	    var url = "api/shortPacks/driverBank/"+driverId;
	    var type="get";
	    $.fd.ajax({
			url : url,
			type : type,
			success : function(d) {
				if(d && d.data){
					$("#initPackDivId [name=receiveUserType]").val(d.data.receiveUserType);
					$("#initPackDivId [name=openBank]").val(d.data.openBank);
					$("#initPackDivId [name=bankAccount]").val(d.data.bankCardNo);
				}
			}
		});
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
    queryCondition.projectCode = $("#frmShortOrderFinanceQuery [name=projectCode]").val();
    queryCondition.branchGroupName = $("#frmShortOrderFinanceQuery [name=branchGroupName]").val();
    queryCondition.beginDate = $("#frmShortOrderFinanceQuery [name=beginDate]").val();
    queryCondition.endDate = $("#frmShortOrderFinanceQuery [name=endDate]").val();
    queryCondition.packFlag = 0;
    return queryCondition;
  }

  
  $("#initPackDivId [name=btnInitPackSave]").click(function(e) {
	    e.preventDefault();
		var type = "post";
		var url = "api/shortPack";
		var shortOrderFinance = $("#initPackFormId").serializeJson();
		var receiver = $("#initPackFormId [name=receiveUserId]").text();
		if(receiver){
			shortOrderFinance.receiver = receiver.substring(receiver.indexOf(":")+1,receiver.length);
		}
		delete shortOrderFinance.projectCode;
		delete shortOrderFinance.branchGroupName;
		
		if (!shortOrderFinance.paymentId) {
			  $.fd.msg.notice("支付模式不能为空!");
			  return false;
		}
		if (shortOrderFinance.paymentId=="3" && !shortOrderFinance.payRatio) {
			  $.fd.msg.notice("油气比例不能为空!");
			  return false;
		}
		if ((shortOrderFinance.paymentId=="0"||shortOrderFinance.paymentId=="3") && !shortOrderFinance.cashPayType) {
			  $.fd.msg.notice("现金支付方式不能为空!");
			  return false;
		}
		if (shortOrderFinance.paymentId!="0" && !shortOrderFinance.suppliesReceiveType) {
			  $.fd.msg.notice("油气领取方式不能为空!");
			  return false;
		}
		if (!shortOrderFinance.receiveUserId) {
			  $.fd.msg.notice("领取人不能为空!");
			  return false;
		}
		if (!shortOrderFinance.openBank) {
			  $.fd.msg.notice("开户行不能为空!");
			  return false;
		}
		if (!shortOrderFinance.bankAccount) {
			  $.fd.msg.notice("账号信息不能为空!");
			  return false;
		}
		/*if (!shortOrderFinance.remark) {
			  $.fd.msg.notice("备注不能为空!");
			  return false;
		}*/
		$.fd.ajax({
			url : url,
			type : type,
			data : shortOrderFinance,
			showMsg:true,
			success : function(d) {
				$("#initPackDivId").modal("hide");
				// 保存成功后，刷新数据
				tblShortOrderFinance.reload();
			}
		});
   });
   $("#btnDriverCheckDetailLink").click(function(e) {
	    e.preventDefault();
	    window.open("driverCheckDetail.do", "contentIframe");  
   });
  
   //var trainStatus = $("#initPackDivId [name=bankAccount]").FdSelect2({type : 'trainStatus2'});
   //var bulkStatus = $("#initPackDivId [name=openBank]").FdSelect2({type : 'bulkStatus'});
  });
}(jQuery));
