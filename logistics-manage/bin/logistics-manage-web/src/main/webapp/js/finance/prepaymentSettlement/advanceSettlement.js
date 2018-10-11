//# sourceURL=advanceSettlement.js
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

  // 获取预付款结算表信息
  var retrieveData = function(source, data, callback) {
	  data = $.fd.DataTable.loadPageOpts(page,data);
    $.fd.DataTable.ajax(source, data, callback, queryCondition);
  };

  // 根据选中的预付款结算,获得该详情存入与抵用明细
  var reloadPoint = function() {
    // 指标表信息列表未创建时
    if (!$.fn.DataTable.isDataTable("#tbDepositOrPurposeDetail")) {
	tbDepositOrPurposeDetail = $("#tbDepositOrPurposeDetail").FdDataTable({
		serverData : getPointData,
		columns : [
		    {
		      data : "id"//复选框
		    }
		    ,{
		      data : "serialNumber"//流水号
		    }
		    ,{
	    	  data : "status",//财务状态
	          render:function(data,type,full,meta) {
	        	  var returnVal = '';
	        	  switch (data) {
	    			case 0:
	    				returnVal = '已审核';
	    				break;
	    			case 1:
	    				returnVal = '待审核';
	    				break;
	    			case 3:
	    				returnVal = '审核不通过';
	    				break;
	    			case 4:
	    				returnVal = '待抵用';
	    				break;
	    			default:
	    				break;
	    			}
	        	  return returnVal;
	          }
		    }
		    ,{
		    	  data : "type",//操作类型 0：存入 1：抵用  3：提现
		          render:function(data,type,full,meta) {
		        	  var returnVal = '';
		        	  switch (data) {
		    			case 0:
		    				returnVal = full.receiveNumber;
		    				break;
		    			case 1:
		    				returnVal = full.alreadyDeposeAccount;
		    				break;
		    			case 3:
		    				returnVal = full.receiveNumber;
		    				break;
		    			default:
		    				break;
		    			}
		        	  return returnVal;
		          }
			    }
		    ,{
		      data : "depositAmount",//存入金额
		    }
		    ,{
		      data : "purposeAmount",//抵用金额
		    }
		    ,{
			  data : "cashAmount",//退款金额
			}
		    ,{
		    	data : "produceTime"//生成时间
		    }
		    ,{
		    	data : "operationPerson",//操作人
		    }
		    ,{
		    	data : "operationDate"//操作时间
		    }
		    ,{
		    	data : "assessor",//审核人
		    }
		    ,{
		    	data : "assessorDate",//审核时间
		    }
		    ],
		    columnDefs : [ {
		        targets : [ 0 ],
		        visible : false
		      } ]
        });
    }else{
        tbDepositOrPurposeDetail.reload();
    }
  };

  // 预付款信息一览
  var tableAdvanceDetail = $("#tableAdvanceDetail").FdDataTable({
    serverData : retrieveData,
    rowSelect: reloadPoint,
    displayLength : 20,
    ajaxSource : "api/projectAdvance",
    columns : [
    {
      data : "projectId"//复选框
    }
    ,{
      data : "projectCode"
    }
    ,{
      data : "depositAmount"//总存入金额
    }
    ,{
      data : "purposeAmount"//总抵用金额
    }
    ,{
      data : "cashAmount"//总退款金额
    }
    ,{
      data : "blance",//余额
    }
    ,{
      data : "needHandle",//待申请处理
    }
    ,{
      data : "assessorDate"//更新时间 以最新审核时间为准
    }
    ],
    columnDefs : [ {
      targets : [ 0 ],
      visible : false
    } ]
  });


	// 表信息一览
	var tbDepositOrPurposeDetail;

	// 获取表
	var getPointData = function(source, data, callback) {
		// 选中行
		var d = tableAdvanceDetail.getSelected();
		if(!d){
			callback({data:[], recordsTotal: 0, recordsFiltered: 0});
			return false;
		}
		queryCondition.projectId=d["projectId"];//赋值财务信息主键
		$.fd.DataTable.ajax("api/projectAdvanceByProjectId", data, callback, queryCondition);
		delete queryCondition.projectId;
		$("#detailsByProject").show();
	};
	
	// 检索
	  $("#btnAdvanceChargeQuery").click(function(e) {
	    e.preventDefault();
	    // 刷新检索条件
	    loadqueryCondition();
	    // 刷新数据
	    tableAdvanceDetail.reload();
	  });

	  function loadqueryCondition(){
	    //queryCondition.projectId = $("#frmShortOrderFinanceQuery [name=projectId]").val();
	    queryCondition.projectCode = $("#frmAdvanceChargeQuery [name=projectCode]").val();
	    return queryCondition;
	  }
	  
	//存入
	$("#imprestPayment").click(function(e) {
	    e.preventDefault();
		if (!tableAdvanceDetail || !tableAdvanceDetail.getSelected()) {
			$.fd.msg.notice("请选择项目预付款信息,且只能选择一条记录");
			return false;
		}
		var data = tableAdvanceDetail.getSelected();
		
		// 重置表单内容
		$("#depositForm").reset();
		$("#depositForm").loadJson(data);
		$("#imprestPaymentModal").modal("show");
		$("#depositForm [name=depositAmount]").val("");
		$("#depositForm [name=receiveUnitId]").empty();
		$("#depositForm [name=receiveAccountId]").empty();
		$("#depositForm [name=payUnitId]").empty();
		$("#depositForm [name=payAccountId]").empty();
		$("#depositForm [name=receiveAgent]").val("");
	});
	
	//抵用
	$("#imprestPurpose").click(function(e) {
	    e.preventDefault();
		if (!tbDepositOrPurposeDetail || !tbDepositOrPurposeDetail.getSelected()) {
			$.fd.msg.notice("请选择计费明细信息,且只能选择一条记录");
			return false;
		}
		var data = tbDepositOrPurposeDetail.getSelected();
		if(data.status != 4) {
			$.fd.msg.notice("只能选择待抵用信息");
			return false;
		}
		
		//var projectId = tableAdvanceDetail.getSelections('projectId'); 
		var projectId = data.projectId;
		//pursChooseAccnount(projectId);
		// 重置表单内容
		var type = "GET";
		var url = "api/getDetailInfoByAcId";
		$.fd.ajax({
			url : url,
			type : type,
			data : {"id":data.id},
			async:false,
			success : function(data) {
				if(data.data.billName == 0){
					// 重置表单内容
					$("#purposeForm2").reset();
					$("#purposeForm2").loadJson(data.data);
					$("#dYBillName2").val("货款");
					$("#imprestPurposeModal2").modal("show");
					diyongAlreadyAccount(data.data.projectId,"0");
				}else{
					$("#purposeForm").reset();
					$("#purposeForm").loadJson(data.data);
					$("#dYBillName").val("运费");
					queryChildrenStation(data.data.receiveUnitId);
					$("#imprestPurposeModal1").modal("show");
				}
				
			}
		});
	});
	
	//根据中心站点查询子站点
	function queryChildrenStation(param){
		$.ajax({
			type : "POST",
			url : "trainStation/getchildrenStationById",
			data : {"id":param},
			dataType : "json",
			async:false,
			success : function(data) {
				$("#purposeForm select[name=receiveAccountId]").empty();
				$("#purposeForm select[name=receiveAccountId]").append("<option value=''></option>");
				$.each(data.data, function(index, its) {
					$("#purposeForm select[name=receiveAccountId]").append("<option value="+its.id+">" + its.stationName+ "</option>");
				});
			}
		});
	}
	
	$("#purposeForm select[name=receiveAccountId]").change(function(e){
		e.preventDefault();
		var id = $("#purposeForm select[name=receiveAccountId] option:selected").val();
		var name = $("#purposeForm select[name=receiveAccountId] option:selected").text();
		if(id == "" || id == null || id == undefined){
			 $("#purposeForm [name=receiveAccountName]").val("");
		}else{
			$("#purposeForm [name=receiveAccountName]").val(name);
		}
	});
	//退款
	$("#withdrawPayment").click(function(e) {
	    e.preventDefault();
		if (!tableAdvanceDetail || !tableAdvanceDetail.getSelected()) {
			$.fd.msg.notice("请选择项目预付款信息,且只能选择一条记录");
			return false;
		}
		var data = tableAdvanceDetail.getSelected();
		var projectId = data.projectId;
		//取出账户
		//cashChooseAccnount(projectId);
		//退款账户
		withdrawChooseAccnount(projectId);
		// 重置表单内容
		$("#withdrawForm").reset();
		$("#withdrawForm").loadJson(data);
		$("#withdrawForm [name=cashAmount]").val("");
		$("#withdrawModal").modal("show");
	});
	
	//删除
	$("#deleteAdvacne").click(function(e) {
	    e.preventDefault();
	    if (!tbDepositOrPurposeDetail || !tbDepositOrPurposeDetail.getSelected()) {
			$.fd.msg.notice("请选择计费明细信息,且只能选择一条记录");
			return false;
		}
		var data = tbDepositOrPurposeDetail.getSelected();
		if(data.type != 0 && data.type != 3){
			$.fd.msg.notice("只能选择存入、退款待审核或者审核不通过信息");
			return false;
		}
		if(data.status != 1 && data.status != 3) {
			$.fd.msg.notice("只能选择存入、退款待审核或者审核不通过信息");
			return false;
		}
		  
		  $.confirm("预付款删除","是否删除？",{
			    success:function(){
			    	doAdvanceDelete(data.id);
			    },
		 },{btnOk:"同意",btnCancel:"取消"});
	});
	
	//删除 点击确定
	function doAdvanceDelete(accountChargeId){
		  var type = "put";
		  var url = "api/deleteAdvance/"+accountChargeId;
		  $.fd.ajax({
			  url : url,
			  type : type,
			  success : function(data) {
				  // 保存成功后，刷新数据
				  tableAdvanceDetail.reload();
		          tbDepositOrPurposeDetail.reload();
			  }
		  });
	  }
	
	//退款 预付款类型改变
	$("#cashAdvanceType").change(function(e) {
		e.preventDefault();
		var advancetType = $("#withdrawForm select[name=advanceType] option:selected").val();
		var projectId = $("#withdrawForm [name=projectId]").val();
		cashChooseAccnount(projectId,advancetType);
	});
	
	//退款选择已存入账户
	function cashChooseAccnount(projectId,advancetType){
		if(advancetType == "" || advancetType == null || advancetType == undefined){
			 $("#withdrawForm select[name=payAccountId]").val("");
			 $("#cashAccountBalance").val("");
			 $("#withdrawForm [name=payNumber]").val("");
			 $("#withdrawForm [name=payBankNumber]").val("");
			 return;
		 }
		$.fd.ajax({
	        url : "api/getDepostAccountByProjectId",
	        type : "GET",
	        data :  {"projectId":projectId,"advancetType":advancetType},
	        success : function(data) {
	        	$("#withdrawForm [name=payAccountId]").empty();
	        	$.each(data.data, function(index, its) {
	        		$("#withdrawForm [name=payAccountId]").append("<option value="+its.id+">" + its.name+ "</option>");
				});
	        	getCashChooseAccountById();
	        }
	      })
	}
	
	//抵用 预付类型是货款 选择抵用账户
	function diyongAlreadyAccount(projectId,advancetType){
		$.fd.ajax({
	        url : "api/getDepostAccountByProjectId",
	        type : "GET",
	        data :  {"projectId":projectId,"advancetType":advancetType},
	        success : function(data) {
	        	$("#purposeForm2 [name=payAccountId]").empty();
	        	$("#purposeForm2 [name=payAccountId]").append("<option value=''></option>");
	        	$.each(data.data, function(index, its) {
	        		$("#purposeForm2 [name=payAccountId]").append("<option value="+its.id+">" + its.name+ "</option>");
				});
	        	//diyongAlreadyAccountInfo();
	        }
	      })
	}
	//退款选择退款账户
	function withdrawChooseAccnount(param){
		$.fd.ajax({
	        url : "api/getCashAccountByProjectId",
	        type : "GET",
	        data :  {"projectId":param},
	        success : function(data) {
	        	$("#withdrawForm [name=receiveAccountId]").empty();
	        	$("#withdrawForm [name=receiveAccountId]").append("<option value=''></option>");
	        	$.each(data.data, function(index, its) {
	        		$("#withdrawForm [name=receiveAccountId]").append("<option value="+its.id+">" + its.name+ "</option>");
				});
	        }
	      })
	}
	//退款取出账户改变
	$("#withdrawForm [name=payAccountId]").change(function(e) {
		e.preventDefault();
		getCashChooseAccountById();
	});
	
	//退款 退款账户改变
	$("#withdrawForm [name=receiveAccountId]").change(function(e) {
		e.preventDefault();
		getWithdrawChooseAccountById();
	});
	
	//货款抵用账户改变
	$("#purposeForm2 [name=payAccountId]").change(function(e) {
		e.preventDefault();
		diyongAlreadyAccountInfo();
	});
	
	function diyongAlreadyAccountInfo(){
		var advancetType = 0;
		var projectId = $("#purposeForm2 [name=projectId]").val();
		var id = $("#purposeForm2 select[name=payAccountId] option:selected").val();
		var name = $("#purposeForm2 select[name=payAccountId] option:selected").text();
		var cId = "";
		if(id == "" || id == null || id == undefined){
			 $("#purposeForm2 [name=payNumber]").val("");
			 //$("#purposeForm2 [name=payBankNumber]").val("");
			 $("#purposeForm2 [name=payAccountName]").val("");
			 $("#purposeForm2 [name=receiveUnitId]").val("");
			 $("#purposeForm2 [name=receiveUnitName]").val("");
			 $("#purposeForm2 [name=receiveAccountId]").val("");
			 $("#purposeForm2 [name=receiveAccountName]").val("");
			 $("#purposeForm2 select[name=receiveAgent]").empty();
		}else{
			 $("#purposeForm2 [name=payAccountName]").val(name);
			 $("#purposeForm2 [name=alreadyDeposeAccount]").val(name);
			$.fd.ajax({
				type : 'GET',
				url : "api/getDepostAccountByProjectIdDetail",
				data : {"id":id,"advanceType":advancetType,"projectId":projectId},
				async:false, 
				success : function(data) {
					 $("#purposeForm2 [name=payNumber]").val(data.data.receiveNumber);
					 $("#purposeForm2 [name=receiveUnitId]").val(data.data.receiveUnitId);
					 $("#purposeForm2 [name=receiveUnitName]").val(data.data.receiveUnitName);
					 $("#purposeForm2 [name=receiveAccountId]").val(data.data.receiveUnitId);
					 $("#purposeForm2 [name=receiveAccountName]").val(data.data.receiveUnitName);
					 cId = data.data.receiveUnitId;
				},
			});
			$.fd.ajax({
				type : 'GET',
				url : "api/getCousterAgentById",
				data : {"relateId":cId},
				async:false, 
				success : function(data) {
					$("#purposeForm2 select[name=receiveAgent]").empty();
					if(data.data == "" || data.data == null){
						$("#purposeForm2 select[name=receiveAgent]").append("<option value=''>未有经办人</option>");
					}else{
						$.each(data.data, function(index, its) {
							$("#purposeForm2 select[name=receiveAgent]").append("<option value="+its.id+">" + its.name+ "</option>");
						});
					}
				},
			 }); 
		}
	}
	
	function getCashChooseAccountById(){
		var advancetType = $("#withdrawForm select[name=advanceType] option:selected").val();
		var projectId = $("#withdrawForm [name=projectId]").val();
		var id = $("#withdrawForm select[name=payAccountId] option:selected").val();
		var name = $("#withdrawForm select[name=payAccountId] option:selected").text();
		$("#withdrawForm [name=payAccountName]").val("");
		$("#withdrawForm [name=payAccountName]").val(name);
		
		 if(id == "" || id == null || id == undefined){
			 $("#cashAccountBalance").val("");
			 $("#withdrawForm [name=payNumber]").val("");
			 $("#withdrawForm [name=payBankNumber]").val("");
		 }else{
			 $("#cashAccountBalance").val("");
				 $.fd.ajax({
					type : 'GET',
					url : "api/getDepostAccountByProjectIdDetail",
					data : {"id":id,"advanceType":advancetType,"projectId":projectId},
					async:false, 
					success : function(data) {
						$("#cashAccountBalance").val(data.data.depositAmount);
						$("#withdrawForm [name=payNumber]").val(data.data.receiveNumber);
						$("#withdrawForm [name=payBankNumber]").val(data.data.receiveBankNumber);
					},
				});
		 }
	}
	
	function getWithdrawChooseAccountById(){
		var id = $("#withdrawForm select[name=receiveAccountId] option:selected").val();
		var name = $("#withdrawForm select[name=receiveAccountId] option:selected").text();
		$("#withdrawForm [name=receiveAccountName]").val("");
		$("#withdrawForm [name=receiveAccountName]").val(name);
		 if(id == "" || id == null || id == undefined){
			 $("#withdrawForm [name=receiveNumber]").val("");
			 $("#withdrawForm [name=receiveBankNumber]").val("");
			 $("#withdrawForm [name=receiveTaxNumber]").val("");
		 }else{
			 $.fd.ajax({
					type : 'GET',
					url : "api/getAccountDetailById",
					data : {"id":id},
					async:false, 
					success : function(data) {
						$("#withdrawForm [name=receiveNumber]").val(data.data.accountNum);
						$("#withdrawForm [name=receiveBankNumber]").val(data.data.bankNum);
						$("#withdrawForm [name=receiveTaxNumber]").val(data.data.taxIdentificationNumber);
					},
				}); 
		 }
	}
	
	//抵用选择已存入账户
	/*function pursChooseAccnount(param){
		$.fd.ajax({
	        url : "api/getDepostAccountByProjectId",
	        type : "GET",
	        data :  {"projectId":param},
	        success : function(data) {
	        	$("#purposeChooseAccount").empty();
	        	$("#purposeChooseAccount").append("<option value=''></option>");
	        	$.each(data.data, function(index, its) {
					$("#purposeChooseAccount").append("<option value="+its.id+">" + its.name+ "</option>");
				});
	        }
	      })
	}*/
	
	//抵用选择存入账户改变
	/*$("#purposeChooseAccount").change(function(e) {
		e.preventDefault();
		getPurposeChooseAccountById2();
	});*/
	
	function getPurposeChooseAccountById2(){
		var id = $("#purposeChooseAccount option:selected").val();
		var name = $("#purposeChooseAccount option:selected").text();
		$("#purposeForm [name=purposeChooseAccountName]").val("");
		$("#purposeForm [name=purposeChooseAccountName]").val(name);
		 if(id == "" || id == null || id == undefined){
			 $("#purposeForm [name=alreadyDeposeAccount]").val("");
		 }else{
			 $("#purposeForm [name=alreadyDeposeAccount]").val("");
			 $.fd.ajax({
					type : 'GET',
					url : "api/getAccountDetailById",
					data : {"id":id},
					async:false, 
					success : function(data) {
						$("#purposeForm [name=alreadyDeposeAccount]").val(data.data.accountNum);
					},
				}); 
		 }
	}
	
	//票据名称改变
	/*$("#billName").change(function(e) {
		e.preventDefault();
		//getInfoByBillName();
	});*/
	
	//请车单号改变
	/*$("#trainOrderId").change(function(e) {
		e.preventDefault();
		//getTrainInfoByTrainId();
	});*/
	
	//对账单号改变
	/*$("#costPackId").change(function(e) {
		e.preventDefault();
		getCostInfoByCostPackId();
	});*/
	
	function getInfoByBillName(){
		 var projectId = $("#purposeForm [name=projectId]").val();
		 var bill = $("#billName option:selected").val();
		 if(bill == "" || bill == null || bill == undefined){
			 $("#trainOrderId").empty();
		 }else if(bill == 0){
			 $.fd.ajax({
					type : 'GET',
					url : "api/getTrainInfoByBillName",
					data : {"projectId":projectId},
					async:false, 
					success : function(data) {
						$("#trainOrderId").empty();
						$("#trainOrderId").append("<option value=''></option>");
						$.each(data.data, function(index, its) {
							$("#trainOrderId").append("<option value="+its.id+">" + its.pleaseTrainNum+ "</option>");
						});
					},
				}); 
		 }else{
			 $.fd.ajax({
					type : 'GET',
					url : "api/getCostByBillName",
					data : {"projectId":projectId},
					async:false, 
					success : function(data) {
						$("#costPackId").empty();
						$("#costPackId").append("<option value=''></option>");
						$.each(data.data, function(index, its) {
							$("#costPackId").append("<option value="+its.shPackId+">" + its.costPackCode+ "</option>");
						});
					},
				}); 
		 }
	}
	
	//根据请车单号查询详情
	function getTrainInfoByTrainId(){
		 var trainOrderId = $("#trainOrderId option:selected").val();
		 var pleaseTrainNum = $("#trainOrderId option:selected").text();
		 if(trainOrderId == "" || trainOrderId == null || trainOrderId == undefined){
			 $("#purposeForm [name=sheetNumber]").val("");
			 $("#purposeForm [name=invalidNumber]").val("");
			 $("#purposeForm [name=pleaseTrainNum]").val("");
		 }else{
			 $("#purposeForm [name=pleaseTrainNum]").val(pleaseTrainNum);
			 var projectId = $("#projectId").val();
			 $.fd.ajax({
					type : 'GET',
					url : "api/getTrainInfoByTrainId",
					data : {"trainOrderId":trainOrderId},
					async:false, 
					success : function(data) {
						 $("#purposeForm [name=sheetNumber]").val("");
						 $("#purposeForm [name=invalidNumber]").val("");
						 var sheetNumber = data.data.train.entruckNumbe;
						 var allowerNumber = data.data.train.sureCarNum;
						 var cancleNumber = parseInt(allowerNumber) - parseInt(sheetNumber);
						 $("#purposeForm [name=sheetNumber]").val(sheetNumber);
						 $("#purposeForm [name=invalidNumber]").val(cancleNumber);
					},
				}); 
		 }
	}
	
	//根据对账单号查询详情
	function getCostInfoByCostPackId(){
		 var costPackId = $("#costPackId option:selected").val();
		 var costPackNum = $("#costPackId option:selected").text();
		 if(costPackId == "" || costPackId == null || costPackId == undefined){
			 $("#purposeForm [name=cargoName]").val("");
			 $("#purposeForm [name=tonnage]").val("");
			 $("#purposeForm [name=totalAmount]").val("");
		 }else{
			 $("#purposeForm [name=costPackNum]").val(costPackNum);
			 var projectId = $("#projectId").val();
			 $.fd.ajax({
					type : 'GET',
					url : "api/getCostInfoByCostId",
					data : {"costPackId":costPackId},
					async:false, 
					success : function(data) {
						 $("#purposeForm [name=cargoName]").val("");
						 $("#purposeForm [name=tonnage]").val("");
						 $("#purposeForm [name=totalAmount]").val("");
						 $("#purposeForm [name=cargoName]").val(data.data.cargoName);
						 $("#purposeForm [name=tonnage]").val(data.data.sendNet);
						 $("#purposeForm [name=totalAmount]").val(data.data.cargoPrice);
					},
				}); 
		 }
	}
	
	//存入 点击确定
	$("#addDepostAmountBtn").click(function(e) {
	    e.preventDefault();
	    var advanceType = $("#depositForm select[name=advanceType]").val();
	    var receiveAccountId = $("#depositForm select[name=receiveAccountId]").val();
	    var payAccountId = $("#depositForm select[name=payAccountId]").val();
	    var payment = $("#depositForm select[name=payment]").val();
	    var receiveAgent = $("#depositForm [name=receiveAgent]").val();
	    var depositAmount = $("#depositForm [name=depositAmount]").val();
	    if(advanceType == "" || advanceType == null || advanceType == undefined){
	    	$.fd.msg.notice("请选择预付类型");
			return false;
	    }
	    if(receiveAccountId == "" || receiveAccountId == null || receiveAccountId == undefined){
	    	$.fd.msg.notice("请选择收款账户");
			return false;
	    }
	    if(receiveAgent == "" || receiveAgent == null || receiveAgent == undefined){
	    	$.fd.msg.notice("收款经办人不能为空");
			return false;
	    }
	    if(payAccountId == "" || payAccountId == null || payAccountId == undefined){
	    	$.fd.msg.notice("请选择支出账户");
			return false;
	    }
	    if(payment == "" || payment == null || payment == undefined){
	    	$.fd.msg.notice("请选择支付方式");
			return false;
	    }
	    if(depositAmount == "" || depositAmount == null || depositAmount == undefined){
	    	$.fd.msg.notice("存入金额不能为空");
			return false;
	    }
	    var advanceCharge = $("#depositForm").serializeJson();
	    $.fd.ajax({
	        url : "api/addAdvanceCharge",
	        type : "POST",
	        data : advanceCharge,
	        success : function(d) {
	          $("#imprestPaymentModal").modal("hide");
	          $('#showMask', window.parent.document).hide();
	          // 保存成功后，刷新数据
	          tableAdvanceDetail.reload();
	          tbDepositOrPurposeDetail.reload();
	        }
	      })

	});
	
	//抵用 点击确定
	$("#addPurposeBtn1").click(function(e) {
	    e.preventDefault();
	    var receiveAccountId = $("#purposeForm select[name=receiveAccountId]").val();
	    var receiveAgent = $("#purposeForm [name=receiveAgent]").val();
	    if(receiveAccountId == "" || receiveAccountId == null || receiveAccountId == undefined){
	    	$.fd.msg.notice("请选择办理单位");
			return false;
	    }
	    if(receiveAgent == "" || receiveAgent == null || receiveAgent == undefined){
	    	$.fd.msg.notice("经办人不能为空");
			return false;
	    }
	    var advanceCharge = $("#purposeForm").serializeJson();
	    $.fd.ajax({
	        url : "api/addAdvanceCharge",
	        type : "POST",
	        data : advanceCharge,
	        success : function(d) {
	          $("#imprestPurposeModal1").modal("hide");
	          $('#showMask', window.parent.document).hide();
	          // 保存成功后，刷新数据
	          tableAdvanceDetail.reload();
	          tbDepositOrPurposeDetail.reload();
	        }
	      })

	});
	
	//抵用 点击确定
	$("#addPurposeBtn2").click(function(e) {
	    e.preventDefault();
	    var payAccountId = $("#purposeForm2 select[name=payAccountId]").val();
	    var receiveAgent = $("#purposeForm2 [name=receiveAgent]").val();
	    if(payAccountId == "" || payAccountId == null || payAccountId == undefined){
	    	$.fd.msg.notice("请选择抵用账户");
			return false;
	    }
	    if(receiveAgent == "" || receiveAgent == null || receiveAgent == undefined){
	    	$.fd.msg.notice("请选择经办人");
			return false;
	    }
	    var advanceCharge = $("#purposeForm2").serializeJson();
	    $.fd.ajax({
	        url : "api/addAdvanceCharge",
	        type : "POST",
	        data : advanceCharge,
	        success : function(d) {
	          $("#imprestPurposeModal2").modal("hide");
	          // 保存成功后，刷新数据
	          tableAdvanceDetail.reload();
	          tbDepositOrPurposeDetail.reload();
	        }
	      })

	});
	
	//退款 点击确定
	$("#addCashBtn").click(function(e) {
	    e.preventDefault();
	    var payId = $("#withdrawForm select[name=payAccountId] option:selected").val();
		var receiveId = $("#withdrawForm select[name=receiveAccountId] option:selected").val();
		if(payId == "" || payId == null || payId == undefined){
			$.fd.msg.notice("请选择取出账户");
			return false;
		}
		if(receiveId == "" || receiveId == null || receiveId == undefined){
			$.fd.msg.notice("请选择退款账户");
			return false;
		}
		var blance = $("#cashAccountBalance").val();
		var amount = $("#withdrawForm [name=cashAmount]").val();
		if(blance == "" || blance == null || blance == undefined){
			$.fd.msg.notice("请检查取出账户余额");
			return false;
		}
		if(amount == "" || amount == null || amount == undefined){
			$.fd.msg.notice("退款余额不能为空");
			return false;
		}
		if(parseFloat(amount)>parseFloat(blance)){
			$.fd.msg.notice("退款余额不能大于取出账户的余额");
			return false;
		}
	    var advanceCharge = $("#withdrawForm").serializeJson();
	    $.fd.ajax({
	        url : "api/addAdvanceCharge",
	        type : "POST",
	        data : advanceCharge,
	        success : function(d) {
	          $("#withdrawModal").modal("hide");
	          // 保存成功后，刷新数据
	          tableAdvanceDetail.reload();
	          tbDepositOrPurposeDetail.reload();
	        }
	    })

	});
	
	//抵用票据名称改变
	$("#billName").change(function(e) {
		e.preventDefault();
		var billVal = $("#billName option:selected").val();
		if(billVal == "" || billVal == null || billVal == undefined){
			 $("#billOne").show();
			 $("#billTwo").hide();
			 resetEmpty();
		}else if(billVal == 0){
			$("#billOne").show();
			$("#billTwo").hide();
			resetEmpty();
		}else{
			$("#billOne").hide();
			$("#billTwo").show();
			resetEmpty();
		}
	});
	
	function resetEmpty(){
		$("#purposeForm [name=sheetNumber]").val("");
		$("#purposeForm [name=invalidNumber]").val("");
		$("#purposeForm [name=pleaseTrainNum]").val("");
		$("#purposeForm [name=startNumber]").val("");
		$("#purposeForm [name=endNumber]").val("");
		$("#purposeForm [name=costPackNum]").val("");
		$("#purposeForm [name=cargoName]").val("");
		$("#purposeForm [name=tonnage]").val("");
		$("#purposeForm [name=totalAmount]").val("");
		//$("#trainOrderId").empty();
		//$("#costPackId").empty();
	}
	
	//预付款类型改变
	$("#advanceType").change(function(e) {
		e.preventDefault();
		var advancetType = $("#depositForm select[name=advanceType] option:selected").val();
		$("#receiveType").val(advancetType);
		chooseKind1();
	});
	//存入账户种类改变
	$("#receiveType").change(function(e) {
		e.preventDefault();
		chooseKind1();
	});
	
	//支出账户种类改变
	$("#payType").change(function(e) {
		e.preventDefault();
		chooseKind2();
	});
	
	//收款单位改变
	$("#receiveUnitId").change(function(e) {
		e.preventDefault();
		getTaxById1();
	});
	
	//支出单位改变
	$("#payUnitId").change(function(e) {
		e.preventDefault();
		getTaxById2();
	});
	
	//收款账户改变
	$("#receiveAccountId").change(function(e) {
		e.preventDefault();
		getAccountDetailById2();
	});
	
	//支出账户改变
	$("#payAccountId").change(function(e) {
		e.preventDefault();
		getAccountDetailById1();
	});
	
	//支出账户改变
	$("#receiveAgentByKind").change(function(e) {
		e.preventDefault();
		getConcreteReceiveAgent();
	});
	
	function getAccountDetailById2(){
		var id = $("#receiveAccountId option:selected").val();
		var name = $("#receiveAccountId option:selected").text();
		$("#receiveAccountName").val(name);
		 if(id == "" || id == null || id == undefined){
			 $("#depositForm [name=receiveNumber]").val("");
			 $("#depositForm [name=receiveBankNumber]").val("");
			 $("#depositForm [name=receiveTaxNumber]").val("");
		 }else{
			 $("#depositForm [name=receiveNumber]").val("");
			 $("#depositForm [name=receiveBankNumber]").val("");
			 $("#depositForm [name=receiveTaxNumber]").val("");
			 $.fd.ajax({
					type : 'GET',
					url : "api/getAccountDetailById",
					data : {"id":id},
					async:false, 
					success : function(data) {
						$("#depositForm [name=receiveNumber]").val(data.data.accountNum);
						$("#depositForm [name=receiveBankNumber]").val(data.data.bankNum);
						$("#depositForm [name=receiveTaxNumber]").val(data.data.taxIdentificationNumber);
					},
				}); 
		 }
	}
	
	function getAccountDetailById1(){
		var id = $("#payAccountId option:selected").val();
		var name = $("#payAccountId option:selected").text();
		$("#payAccountName").val(name);
		 if(id == "" || id == null || id == undefined){
			 $("#payNumber").val("");
			 $("#payBankNumber").val("");
			 $("#payTaxNumber").val("");
		 }else{
			 $("#payNumber").val("");
			 $("#payBankNumber").val("");
			 $("#payTaxNumber").val("");
			 $.fd.ajax({
					type : 'GET',
					url : "api/getAccountDetailById",
					data : {"id":id},
					async:false, 
					success : function(data) {
						$("#payNumber").val(data.data.accountNum);
						$("#payBankNumber").val(data.data.bankNum);
						$("#payTaxNumber").val(data.data.taxIdentificationNumber);
					},
				}); 
		 }
	}
	
	//账户种类选择 0:客户  1:站点 2:公司
	function chooseKind2(){
		 var type = $("#payType option:selected").val();
		 if(type == "" || type == null || type == undefined){
			 $("#payUnitId").empty();
			 $("#payAccountId").empty();
		 }/*else if(type == 2){
			 $("#payUnitId").empty();
			 $("#payUnitId").append("<option value='1'>新疆秦龙矿业有限公司</option>");
			 getTaxById2();
		 }*/else{
			 $.ajax({
				type : 'POST',
				url : "account/getAccountNameByType",
				data : {"type":type},
				dataType : "json",
				async:false, 
				success : function(data) {
					if(data.status==200){
						$("#payUnitId").empty();
						if(type == 0){
							$.each(data.data.data, function(index, its) {
								$("#payUnitId").append("<option value="+its.id+">" + its.companyName+ "</option>");
	 						});
						}else if(type == 1){
							$.each(data.data.data, function(index, its) {
								$("#payUnitId").append("<option value="+its.id+">" + its.stationName+ "</option>");
	 						});
						}else{
								$("#payUnitId").append("<option value="+data.data.data.id+">" + data.data.data.name+ "</option>");
						}
					}
					getTaxById2();
				},
			}); 
		 }
	}
	
	//根据支出单位获取该单位下的支出账户列表  
	function getTaxById2(){
		var type = $("#payType option:selected").val();
		var cId = $("#payUnitId option:selected").val();
		var cName = $("#payUnitId option:selected").text();
		var accountType = "";
		$("#payUnitName").val(cName);
		 if(cId == "" || cId == null || cId == undefined){
			 $("#payAccountId").empty();
		 }else{
			 if(type==2){
				 accountType = -1;
			 }else{
				 accountType = 1;
			 }
			 $.fd.ajax({
				type : 'GET',
				url : "api/getAccountByUnitId",
				data : {"type":type,"id":cId,"accountType":accountType},
				async:false, 
				success : function(data) {
					$("#payAccountId").empty();
					if(data.data == "" || data.data == null){
						$("#payAccountId").append("<option value=''>未有账号</option>");
					}else{
						$.each(data.data, function(index, its) {
							$("#payAccountId").append("<option value="+its.id+">" + its.name+ "</option>");
						});
					}
					getAccountDetailById1();
				},
			}); 
		 }
	}
	
	//根据收款单位获取该单位下的收款账户列表  
	function getTaxById1(){
		var type = $("#receiveType option:selected").val();
		var cId = $("#receiveUnitId option:selected").val();
		var cName = $("#receiveUnitId option:selected").text();
		var accountType = "";
		$("#receiveUnitName").val(cName);
		 if(cId == "" || cId == null || cId == undefined){
			 $("#receiveAccountId").empty();
		 }else{
			 if(type==2){
				 accountType = -1;
			 }else{
				 accountType = 1;
			 }
			 $.fd.ajax({
				type : 'GET',
				url : "api/getAccountByUnitId",
				data : {"type":type,"id":cId,"accountType":accountType},
				async:false, 
				success : function(data) {
					$("#receiveAccountId").empty();
					if(data.data == "" || data.data == null){
						$("#receiveAccountId").append("<option value=''>未有账号</option>");
					}else{
						$.each(data.data, function(index, its) {
							$("#receiveAccountId").append("<option value="+its.id+">" + its.name+ "</option>");
						});
					}
					getAccountDetailById2();
				},
			 }); 
			 //客户 经办人选择
			 if(type == 0){
				 $.fd.ajax({
					type : 'GET',
					url : "api/getCousterAgentById",
					data : {"relateId":cId},
					async:false, 
					success : function(data) {
						$("#receiveAgentByKind").empty();
						if(data.data == "" || data.data == null){
							$("#receiveAgentByKind").append("<option value=''>未有经办人</option>");
						}else{
							$.each(data.data, function(index, its) {
								$("#receiveAgentByKind").append("<option value="+its.id+">" + its.name+ "</option>");
							});
						}
						getConcreteReceiveAgent();
					},
				 }); 
			 }
		 }
	}
	
	function chooseKind1(){
		 var type = $("#receiveType option:selected").val();
		 $("#depositForm [name=receiveType]").val(type);
		 var projectId =  $("#depositForm [name=projectId]").val();
		 if(type == "" || type == null || type == undefined){
			 $("#receiveUnitId").empty();
			 $("#receiveAgentByKind").empty();
			 $("#receiveAgent").val("");
			 $("#receiveAgentByKind").show();
			 $("#receiveAgent").hide();
			 $("#receiveAccountId").empty();
			 $("#receiveNumber").val("");
			 $("#receiveBankNumber").val("");
			 $("#receiveTaxNumber").val("");
		 }/*else if(type == 2){
			 $("#receiveUnitId").empty();
			 $("#receiveUnitId").append("<option value='1'>新疆秦龙矿业有限公司</option>");
			 $("#receiveAgentByKind").hide();
			 $("#receiveAgent").show();
			 $("#receiveAgentByKind").empty();
			 $("#receiveAgent").val("");
			 getTaxById1();
		 }*/else{
			 $.ajax({
				type : 'POST',
				url : "account/getAccountNameByTypeAndProjectId.do",
				data : {"type":type,"projectId":projectId},
				dataType : "json",
				async:false, 
				success : function(data) {
					if(data.status==200){
						$("#receiveUnitId").empty();
						if(type == 0){
							$.each(data.data.data, function(index, its) {
								$("#receiveUnitId").append("<option value="+its.id+">" + its.companyName+ "</option>");
	 						});
							$("#receiveAgentByKind").show();
							$("#receiveAgent").hide();
							$("#receiveAgent").val("");
						}else if(type == 1){
							$.each(data.data.data, function(index, its) {
								$("#receiveUnitId").append("<option value="+its.id+">" + its.stationName+ "</option>");
	 						});
							$("#receiveAgentByKind").hide();
							$("#receiveAgent").show();
							$("#receiveAgentByKind").empty();
							$("#receiveAgent").val("");
						}
					}
					getTaxById1();
				},
			}); 
		 }
	}
	
	function getConcreteReceiveAgent(){
		var param = $("#receiveAgentByKind option:selected").text();
		var id = $("#receiveAgentByKind option:selected").val();
		$("#receiveAgent").val("");
		if(id == "" || id == undefined){
			return;
		}
		$("#receiveAgent").val(param);
	}
	
	//  财务审核
	  $("#accountDetails").click(function(e) {
		  e.preventDefault();
		  //var data = tableAdvanceDetail.getSelected();
		  var accountChargeIds = tbDepositOrPurposeDetail.getSelections('id');
		  var accountStatuss = tbDepositOrPurposeDetail.getSelections('status');
		  if (!accountChargeIds || accountChargeIds.length ==0) {
			  $.fd.msg.notice("请至少选择一条信息");
			  return false;
		  }
		  for (var i in accountStatuss) {
			  if(accountStatuss[i] != 1 && accountStatuss[i] != 3) {
				  $.fd.msg.notice("只能选择待审核或者审核不通过信息");
				  return false;
			  }
		  }
		  
		  $.confirm("财务审核","是否审核？",{
			    success:function(){
			    	doAdvanceFinance(accountChargeIds,"0");
			    },
			    failure:function(){
			    	doAdvanceFinance(accountChargeIds,"1");
			    }
		 },{btnOk:"同意",btnCancel:"拒绝"});

	  });
	
	  function doAdvanceFinance(accountChargeIds,flag){
		  var type = "put";
		  var url = "api/accountAudit/"+accountChargeIds+"/"+flag;
		  $.fd.ajax({
			  url : url,
			  type : type,
			  success : function(data) {
				  // 保存成功后，刷新数据
				  tableAdvanceDetail.reload();
		          tbDepositOrPurposeDetail.reload();
			  }
		  });
	  }
	//  反审核
	  $("#backaccountDetails").click(function(e) {
		  e.preventDefault();
		  var accountChargeIds = tbDepositOrPurposeDetail.getSelections('id');
		  var accountStatuss = tbDepositOrPurposeDetail.getSelections('status');
		  if (!accountChargeIds || accountChargeIds.length ==0) {
			  $.fd.msg.notice("请至少选择一条信息");
			  return false;
		  }
		  for (var i in accountStatuss) {
			  if(accountStatuss[i] != 0) {
				  $.fd.msg.notice("只能选择已审核信息");
				  return false;
			  }
		  }
		  var type = "put";
		  var url = "api/backAccountAudit/"+accountChargeIds;
		  $.fd.ajax({
			  url : url,
			  type : type,
			  success : function(data) {
				  // 保存成功后，刷新数据
				  tableAdvanceDetail.reload();
		          tbDepositOrPurposeDetail.reload();
			  }
		  });
	  });
	  
	 // 查看详情
	  $("#lookDetails").click(function(e) {
		  e.preventDefault();
		  var accountChargeIds = tbDepositOrPurposeDetail.getSelections('id');
		  if (!accountChargeIds || accountChargeIds.length ==0) {
			  $.fd.msg.notice("请至少选择一条信息");
			  return false;
		  }
		  if (!accountChargeIds || accountChargeIds.length > 1) {
			  $.fd.msg.notice("只能选择一条信息");
			  return false;
		  }
		  var d = tbDepositOrPurposeDetail.getSelected();
		  var type = "GET";
		  var url = "api/getDetailInfoByAcId";
		  $.fd.ajax({
			  url : url,
			  type : type,
			  data : {"id":d.id},
			  async:false,
			  success : function(data) {
				  // 重置表单内容
				  $("#detailForm").reset();
				  $("#detailForm").loadJson(data.data);
				  
				  if(data.data.status == 0){
					  $("#detailForm [name=status]").val("已审核");
				  }else if(data.data.status == 1){
					  $("#detailForm [name=status]").val("待审核");
				  }else if(data.data.status == 3){
					  $("#detailForm [name=status]").val("审核不通过");
				  }else if(data.data.status == 4){
					  $("#detailForm [name=status]").val("待抵用");
				  }
				  if(data.data.payment == 0){
					  $("#detailForm [name=payment]").val("现金");
				  }else if(data.data.payment == 1){
					  $("#detailForm [name=payment]").val("支票");
				  }else if(data.data.payment == 2){
					  $("#detailForm [name=payment]").val("转账");
				  }else if(data.data.payment == 3){
					  $("#detailForm [name=payment]").val("承兑汇票");
				  }
				  if(data.data.advanceType == 0){
					  $("#detailForm [name=advanceType]").val("货款");
				  }else if(data.data.advanceType == 1){
					  $("#detailForm [name=advanceType]").val("运费");
				  }else if(data.data.advanceType == 2){
					  $("#detailForm [name=advanceType]").val("其他");
				  }
				  if(data.data.type == 0){//存入
						$("#receInfo").show();
						$("#serveModal").hide();
						$("#cashInfo").hide();
						//$("#changLabel").html("&emsp;流水号：");
				  }
				  else if(data.data.type == 1){
					  $("#receInfo").hide();
						$("#serveModal").show();
						$("#cashInfo").hide();
						//抵用
						if(data.data.billName == 1){
							$(".hideTrain").hide();
							$(".hideGoods").show();
						}
						else{
							$(".hideTrain").show();
							$(".hideGoods").hide();
						}
						$("#receInfo").hide();
						$("#serveModal").show();
						//$("#changLabel").html("票务名称：");
				}else{
					$("#detailTakeId").val("");
					var accId = data.data.payAccountId;
					$.fd.ajax({
						type : 'GET',
						url : "api/getAccountDetailById",
						data : {"id":accId},
						async:false, 
						success : function(data) {
							$("#detailTakeId").val(data.data.accountBalance);
						},
					}); 
					$("#receInfo").hide();
					$("#serveModal").hide();
					$("#cashInfo").show();
				}
				  $("#detailModal").modal("show");
			  }
		  });
	  });
	  
  });
}(jQuery));
