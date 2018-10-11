//# sourceURL=shortOrderFinance.js
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

  // 根据选中的短驳运单财务表,获得该指标表
  var reloadPoint = function() {
    // 指标表信息列表未创建时
    if (!$.fn.DataTable.isDataTable("#tblPoint")) {
	tblPoint = $("#tblPoint").FdDataTable({
		serverData : getPointData,
		columns : [
		    {
		      data : "shOrderFinId"//复选框
		    }
		    ,{
		      data : "orderCode"//运单编号
		    }
		    ,{
		      data : "carPlateNumber"//承运车辆
		    }
		    ,{
	    	  data : "financeStatus",//财务状态
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
		      data : "containerOneSendNet",//发货净重
		      render:function(data,type,full,meta) {
		    	  var containerOneSendNet = full.containerOneSendNet;
		    	  var containerTwoSendNet = !full.containerTwoSendNet?'0':full.containerTwoSendNet;
		    	  return (parseFloat(containerOneSendNet)+parseFloat(containerTwoSendNet)).toFixed(2);
		      }
		    }
		    ,{
		      data : "containerOneReceiptNet",//收货净重
		      render:function(data,type,full,meta) {
		    	  var containerOneReceiptNet = full.containerOneReceiptNet;
		    	  var containerTwoReceiptNet = !full.containerTwoReceiptNet?'0':full.containerTwoReceiptNet;
		    	  return (parseFloat(containerOneReceiptNet)+parseFloat(containerTwoReceiptNet)).toFixed(2);
		      }
		    }
		    ,{
		    	data : "chargedWeight"//计费重量
		    }
		    ,{
		    	data : "pieceNumber"//计费件数
		    }
		    ,{
		    	data : "shortBargeCost"//运输单价
		    }
		    ,{
		    	data : "transitMoney"//运输费用
		    }
		    ,{
		    	data : "deductionWeight"//耗损亏吨
		    }
		    ,{
		    	data : "deductioned"//是否扣损
		    }
		    ,{
		    	data : "deductionRate"//扣损系数
		    }
		    ,{
		    	data : "deductionPrice"//扣损单价
		    }
		    ,{
		    	data : "deductionMoney"//扣损金额
		    }
		    ,{
		    	data : "arrearsItem"//补加项目
		    }
		    ,{
		    	data : "subsidy"//补加金额
		    }
		    ,{
		    	data : "payableTransitMoney"//应付运费
		    }
		    ,{
		    	data : "billingUserName"//确认人
		    }
		    ,{
		    	data : "auditUserName"//审核人
		    }
		    ,{
		    	data : "billingDate"//确认计费时间
		    }
		    ],
		    columnDefs : [ {
		        targets : [ 0,15 ],
		        visible : false
		      } ]
        });
    }else{
        tblPoint.reload();
    }
  };

  // 短驳运单财务表信息一览
  var tblShortOrderFinance = $("#tblShortOrderFinance").FdDataTable({
    serverData : retrieveData,
    rowSelect: reloadPoint,
    displayLength : 20,
    "scrollX": true,
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
			case 4:
				returnVal = '计费确认不通过';
				break;
			case 5:
				returnVal = '财务审核不通过';
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
      data : "transitMoney"
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

  // 表单校验
  $("#frmShortOrderFinance").bootstrapValidator({
	excluded : [ ':disabled' ],
	fields : {
		projectId : {
			validators : {
				notEmpty : {}
			}
		}
	}
  }).on("success.form.bv", function(e) {
	// 表单校验通过后，保存处理
	e.preventDefault();
	var id = $("#frmShortOrderFinance [name=id]").val();
	var type = id ? "put" : "post";
	var url = "api/shortOrderFinance";
	var shortOrderFinance = $("#frmShortOrderFinance").serializeJson();

	$.fd.ajax({
		url : url,
		type : type,
		data : shortOrderFinance,
		// showMsg:true,
		success : function(d) {
			$("#mdlShortOrderFinance").modal("hide");
			// 保存成功后，刷新数据
			tblShortOrderFinance.reload();
			$("#frmShortOrderFinance").loadJson(d.data);
		}
	});
});

  // $("#mdlShortOrderFinance").fdDraggable();

  // 保存
  $("#btnShortOrderFinanceSave").click(function(e) {
    e.preventDefault();
    $("#frmShortOrderFinance").bootstrapValidator("validate");
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
    return queryCondition;
  }



  //根据isEdit，判断页面表单是否可编辑
  var isEdited = function(isEdit){
    if(isEdit){
	$("#btnShortOrderFinanceSave").show();
	$("#frmShortOrderFinance input").removeAttr("disabled");
	$("#frmShortOrderFinance select").removeAttr("disabled");
	$("#frmShortOrderFinance textarea").removeAttr("disabled");
    }else{
	$("#btnShortOrderFinanceSave").hide();
	$("#frmShortOrderFinance input").attr("disabled", "disabled");
	$("#frmShortOrderFinance select").attr("disabled", "disabled");
	$("#frmShortOrderFinance textarea").attr("disabled", "disabled");
    }
  };

  // 短驳运单财务表新增
  $("#btnShortOrderFinanceAdd").click(function(e) {
    e.preventDefault();
    // 重置表单内容
    $("#frmShortOrderFinance").reset();
    isEdited(true);
    
    // 根据选中的短驳运单财务表,获得该指标表
    reloadPoint();
    $("#mdlShortOrderFinance").modal("show");
  });
   
  //编辑查看
  var editShortOrderFinance = function(isEdit) {
    var data = tblShortOrderFinance.getSelected();
    if (!data) {
      $.fd.msg.notice("请选择短驳运单财务表信息,且只能选择一条记录");
      return false;
    }
    // 重置表单内容
    $("#frmShortOrderFinance").reset();
    $("#frmShortOrderFinance").loadJson(data);
    // 编辑状态 or只读状态
    isEdited(isEdit);

    $("#mdlShortOrderFinance").modal("show");
   };
  
  // 短驳运单财务表查看
  $("#btnShortOrderFinanceView").click(function(e) {
    e.preventDefault();
    editShortOrderFinance(false);
  });
  
  // 短驳运单财务表修改
  $("#btnShortOrderFinanceEdit").click(function(e) {
    e.preventDefault();
    editShortOrderFinance(true);
  });

  // 短驳运单财务表删除
  $("#btnShortOrderFinanceDel").click(function(e) {
    e.preventDefault();
    var datas = tblShortOrderFinance.getSelections('id');

  });
  
  // 短驳运单财务表 - 计费确认
  $("#btnShortOrderFinanceBillingVerify").click(function(e) {
	  e.preventDefault();
	  var shOrderFinIds = tblShortOrderFinance.getSelections('shOrderFinId');
	  var financeStatuss = tblShortOrderFinance.getSelections('financeStatus');
	  if (!shOrderFinIds || shOrderFinIds.length ==0) {
	      $.fd.msg.notice("请至少选择一条信息");
	      return false;
	   }
	  for (var i in financeStatuss) {
		  if(financeStatuss[i] != 0 && financeStatuss[i] != 4) {
			  $.fd.msg.notice("只能选择待确认或者计费确认不通过信息");
		      return false;
		  }
	  }
		
		$.confirm("计费确认","是否确认？",{
		    success:function(){
		    	doShortFinance(shOrderFinIds,"0");
		    },
		    failure:function(){
		    	doShortFinance(shOrderFinIds,"1");
		    }
	 },{btnOk:"同意",btnCancel:"拒绝"});
  });
  
  function doShortFinance(shOrderFinIds,flag){
	    var type = "put";
		var url = "api/shortOrderFinance/billingVerify/"+shOrderFinIds+"/"+flag;
		$.fd.ajax({
			url : url,
			type : type,
			showMsg:true,
			success : function(data) {
				// 保存成功后，刷新数据
				tblShortOrderFinance.reload();
			}
		});
  }
  // 短驳运单财务表 - 反确认
  $("#btnShortOrderFinanceAgainstVerify").click(function(e) {
	  e.preventDefault();
	  var shOrderFinIds = tblShortOrderFinance.getSelections('shOrderFinId');
	  var financeStatuss = tblShortOrderFinance.getSelections('financeStatus');
	  if (!shOrderFinIds || shOrderFinIds.length ==0) {
		  $.fd.msg.notice("请至少选择一条信息");
		  return false;
	  }
	  for (var i in financeStatuss) {
		  if(financeStatuss[i] != 2) {
			  $.fd.msg.notice("只能选择待审核信息");
			  return false;
		  }
	  }
	  var type = "put";
	  var url = "api/shortOrderFinance/againstVerify/"+shOrderFinIds;
	  $.fd.ajax({
		  url : url,
		  type : type,
		  showMsg:true,
		  success : function(data) {
			  // 保存成功后，刷新数据
			  tblShortOrderFinance.reload();
		  }
	  });
  });
  
  // 短驳运单财务表 - 财务审核
  $("#btnShortOrderFinanceFinanceAudit").click(function(e) {
	  e.preventDefault();
	  var shOrderFinIds = tblShortOrderFinance.getSelections('shOrderFinId');
	  var financeStatuss = tblShortOrderFinance.getSelections('financeStatus');
	  if (!shOrderFinIds || shOrderFinIds.length ==0) {
		  $.fd.msg.notice("请至少选择一条信息");
		  return false;
	  }
	  for (var i in financeStatuss) {
		  if(financeStatuss[i] != 2 && financeStatuss[i] != 5 ) {
			  $.fd.msg.notice("只能选择待审核或者审核不通过信息");
			  return false;
		  }
	  }

	  $.confirm("财务审核","是否审核？",{
		    success:function(){
		    	doFinance(shOrderFinIds,"0");
		    },
		    failure:function(){
		    	doFinance(shOrderFinIds,"1");
		    }
	 },{btnOk:"同意",btnCancel:"拒绝"});
  });
  
  function doFinance(shOrderFinIds,flag){
	  var type = "put";
	  var url = "api/shortOrderFinance/financeAudit/"+shOrderFinIds+"/"+flag;
	  $.fd.ajax({
		  url : url,
		  type : type,
		  showMsg:true,
		  success : function(data) {
			  // 保存成功后，刷新数据
			  tblShortOrderFinance.reload();
		  }
	  });
  }
  
  // 短驳运单财务表 - 财务反审核
  $("#btnShortOrderFinanceAgainstAudit").click(function(e) {
	  e.preventDefault();
	  var shOrderFinIds = tblShortOrderFinance.getSelections('shOrderFinId');
	  var financeStatuss = tblShortOrderFinance.getSelections('financeStatus');
	  if (!shOrderFinIds || shOrderFinIds.length ==0) {
		  $.fd.msg.notice("请至少选择一条信息");
		  return false;
	  }
	  for (var i in financeStatuss) {
		  if(financeStatuss[i] != 3) {
			  $.fd.msg.notice("只能选择已审核信息");
			  return false;
		  }
	  }
	  var type = "put";
	  var url = "api/shortOrderFinance/againstAudit/"+shOrderFinIds;
	  $.fd.ajax({
		  url : url,
		  type : type,
		  showMsg:true,
		  success : function(data) {
			  // 保存成功后，刷新数据
			  tblShortOrderFinance.reload();
		  }
	  });
  });
  // 短驳运单财务表 - 计算运费
  $("#btnShortOrderFinanceBillingFreight").click(function(e) {
	  e.preventDefault();
	  var shOrderFinIds = tblShortOrderFinance.getSelections('shOrderFinId');
	  var financeStatuss = tblShortOrderFinance.getSelections('financeStatus');
	  if (!shOrderFinIds || shOrderFinIds.length ==0) {
		  $.fd.msg.notice("请至少选择一条信息");
		  return false;
	  }
	  for (var i in financeStatuss) {
		  if(financeStatuss[i] != 1) {
			  $.fd.msg.notice("只能选择待计算信息");
			  return false;
		  }
	  }
	  var type = "put";
	  var url = "api/shortOrderFinance/billingFreight/"+shOrderFinIds;
	  $.fd.ajax({
		  url : url,
		  type : type,
		  showMsg:true,
		  success : function(data) {
			  // 保存成功后，刷新数据
			  tblShortOrderFinance.reload();
		  }
	  });
  });


  	/** **指标表****** */
	// 指标表信息一览
	var tblPoint;

	// 获取指标表
	var getPointData = function(source, data, callback) {
		// 选中行
		var d = tblShortOrderFinance.getSelected();
		if(!d){
			callback({data:[], recordsTotal: 0, recordsFiltered: 0});
			return false;
		}
		queryCondition.shOrderFinId=d["shOrderFinId"];//赋值财务信息主键
		$.fd.DataTable.ajax("api/shortOrderFinances", data, callback, queryCondition);
		delete queryCondition.shOrderFinId;
	};
	
	//$("#mdlPoint").fdDraggable();

	// 指标表新增
	$("#btnPointAdd").click(function(e) {
	    e.preventDefault();
		
		if (!tblShortOrderFinance || !tblShortOrderFinance.getSelected()) {
			$.fd.msg.notice("请选择短驳运单财务表信息,且只能选择一条记录");
			return false;
		}
		
		// 重置表单内容
		$("#frmPoint").reset();
		$("#mdlPoint").modal("show");
	});

	// 计费变更修改
	$("#btnPointEdit").click(function(e) {
                e.preventDefault();		
		var datas = tblPoint.getSelections('id');
		if (!datas || datas.length != 1) {
			$.fd.msg.notice("请选择要修改的指标表,且只能选择一条记录");
			return false;
		}

		var data = tblPoint.getSelected();
		if(data.financeStatus !=0 && data.financeStatus != 1) {
			$.fd.msg.notice("只有待确认和待计算的信息可以进行计费变更");
			return false;
		}
		// 重置表单内容
		$("#frmPoint").reset();
		$("#frmPoint").loadJson2Text(data);
		$("#frmPoint").loadJson(data);
		//加载补交项目
		var type = "get";
		var url = "api/shortOrderFinance/retroactivelyPayProject";
		$.fd.ajax({
			url : url,
			type : type,
			success : function(data) {
				console.log(data);
				var selectStr = '';
				$.each(data.data, function(index, obj) {
					selectStr += '<option value="' + obj
							+ '">' + obj
							+ '</option>';
				})
				// 填充下拉列表
				$("#frmPoint [name=arrearsItem]").html(selectStr);
			}
		});
		$("#mdlPoint").modal("show");
	});

	// 指标表删除
	$("#btnPointDel").click(function(e) {
                e.preventDefault();
		var datas = tblPoint.getSelections('id');


	});
	
	// 指标表表单校验
	$("#frmPoint").bootstrapValidator({
		excluded: [':disabled'],
		fields : {
		}
	}).on("success.form.bv",function(e) {
		// 表单校验通过后，保存处理
		e.preventDefault();

		// 根据从表id是否存在,判断是新增还是修改
		var type = "put";
		
		var url = "api/shortOrderFinance/subsidyShortOrderFinance";

		var point = $("#frmPoint").serializeJson();

		$.fd.ajax({
			url : url,
			type : type,
			data : point,
			success : function(data) {
				$("#mdlPoint").modal("hide");
				// 保存成功后，刷新数据
				tblShortOrderFinance.reload();
				tblPoint.reload();
			}
		});
	});

	// 指标表保存
	$("#btnPointSave").click(function(e) {
	    e.preventDefault();
		$("#frmPoint").bootstrapValidator("validate");
	});
  });
}(jQuery));
