//# sourceURL=threePartiesReceivables.js
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
  
  //根据选中的短驳运单财务表,获得该指标表
  var reloadPoint = function() {
    // 指标表信息列表未创建时
    if (!$.fn.DataTable.isDataTable("#tblPoint")) {
	tblPoint = $("#tblPoint").FdDataTable({
		serverData : getPointData,
		columns : [
		    {
		      data : "threePartiesSettleId"//复选框
		    }
		    ,{
		      data : "payAccountName"//支出账户
		    }
		    ,{
		      data : "payAccountNum"//支出账号	
		    }
		    ,{
		    	data : "receiveAccountName"//存入账户
		    }
		    ,{
		      data : "receiveAccountNum"//存入账号
		    }
		    ,{
		      data : "settleStatus",//财务状态
		          render:function(data,type,full,meta) {
		        	  var returnVal = '';
		        	  switch (data) {
		    			case 0:
		    				returnVal = '结算财务待审核';
		    				break;
		    			case 1:
		    				returnVal = '结算审核通过';
		    				break;
		    			case 2:
		    				returnVal = '结算审核不通过';
		    				break;
		    			default:
		    				break;
		    			}
		        	  return returnVal;
		          }
		  	 }		    
		    ,{
		      data : "settleMoney"//结算金额
		    }
		    ,{
		      data : "createUserName"//操作人
		    }
		    ,{
		      data : "auitUserName"//审核人	
		    }
		    ,{
		      data : "auitDate"//审核时间
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
  var tblShortOrderFinance = $("#tblShortOrderFinance").FdDataTable({
    serverData : retrieveData,
    rowSelect: reloadPoint,
    displayLength : 20,
    ajaxSource : "api/getAllThreePartiesReceivables",
    columns : [
    {
      data : "threeId"//复选框
    }
    ,{
        data : "projectCode"
      }
    ,{
      data : "custPackId"
    }
    ,{
  	  data : "settleAuditType",//财务状态
        render:function(data,type,full,meta) {
      	  var returnVal = '';
      	  switch (data) {
  			case 0:
  				returnVal = '待结算';
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
      data : "cargoType"//货款类型
    }
    ,{
      data : "produceMoney",//发票金额
    }
    ,{
      data : "paymentRatio"//支付比例
    }
    ,{
      data : "payableMoney"//总金额
    }
    ,{
      data : "settledMoney"//已结金额
    }
    ,{
      data : "besettledMoney"//待结金额
    }
    ,{
      data : "threeCompanies"//三方企业
    } ,{
      data : "createDate"
    }
    ],
    columnDefs : [ {
      targets : [ 0 ],
      visible : false
    } ]
  });
  
  // 检索
  $("#btnShortOrderFinanceQuery").click(function(e) {
    e.preventDefault();
    // 刷新检索条件
    loadqueryCondition();
    // 刷新数据
    tblShortOrderFinance.reload();
  });
  
  //新增收款页面
  $("#btnAddReceiveMoney").click(function(e){
	  e.preventDefault();
	var type = "get";
	var url = "projectManagment/getProjectByFinance.do";
	  $.fd.ajax({
		  url : url,
		  type : type,
		  success : function(data) {
			  var selectStr = '<option></option>';
			  $.each(data.data,function(index,project){
				  selectStr += '<option value="' + project.id + '">' + project.projectCode + '</option>';
			  })
			  // 填充下拉列表
			  $("#addReceiveMoneyFormId [name=projectId]").html(selectStr);
				// 重置内容
				$("#addReceiveMoneyFormId").resetText();
		  }
	  });
	  url = "customerManagement/listCustomers.do";
	  type = "POST";
	  $.fd.ajax({
		  url : url,
		  type : type,
		  success : function(data) {
			  var selectStr = '<option></option>';
			  $.each(data.data,function(index,customer){
				  selectStr += '<option value="' + customer.id + '">' + customer.companyName + '</option>';
			  })
			  // 填充下拉列表
			  $("#addReceiveMoneyFormId [name=threeCompaniesId]").html(selectStr);
		  }
	  });
	  
	  $("#addReceiveMoneyModal").modal("show");
  })
  
  
  //保存收款
  $("#btnThreePartiesReceivablesSave").click(function(e){
	  var type = "post";
	  var url = "api/threePartiesReceivables";
	  var threePartiesReceivables = $("#addReceiveMoneyFormId").serializeJson();
	  $.fd.ajax({
		  url : url,
		  type : type,
		  data : threePartiesReceivables,
		  showMsg:true,
		  success : function(d) {
			  // 保存成功后，刷新数据
			  tblShortOrderFinance.reload();
		  }
	  });
	  $("#addReceiveMoneyModal").modal("hide");
  })
  
  
  //结算页面
	$("#btnCustomerAccountsSettlement").click(function(e) {
		var custPackIds = tblShortOrderFinance.getSelections('custPackId');
		if (custPackIds.length != 1) {
			  $.fd.msg.notice("请选择其中一条信息");
			  return false;
		  }
		/*var cashSettleStatus = tblShortOrderFinance.getSelections('cashSettleStatus');
		for (var i in cashSettleStatus) {
			  if(cashSettleStatus[i] != 0) {
				  $.fd.msg.notice("只能选择未结算完的信息");
			      return false;
			  }
		  }*/
		// 重置内容
		$("#chooseProjectFormId").resetText();
		
		//1table data
		var shOrderFinss = tblShortOrderFinance.getSelecteds(custPackIds);
		
		//4表单赋值
		setValue(shOrderFinss);
		$("#settlementModal").modal("show");
	});
  
  //保存结算信息
  $("#btnCustomerAccountsSave").click(function(e){
	  var type = "post";
	  var url = "api/threePartiesSettle";
	  var costPack = $("#chooseProjectFormId").serializeJson();
	  var besettledMoneyVal = $("#chooseProjectFormId [name=besettledMoney]").val();
	  var settleMoneyVal = $("#chooseProjectFormId [name=settleMoney]").val();
	  if(parseFloat(besettledMoneyVal) < parseFloat(settleMoneyVal) ){
		  $.fd.msg.notice("结算金额不可大于待结算金额！");
	      return false;
	  }
	  delete costPack.projectCode;
	  delete costPack.threeCompanies;
	  delete costPack.threeCompaniesId;
	  delete costPack.besettledMoney;
	  $.fd.ajax({
		  url : url,
		  type : type,
		  data : costPack,
		  showMsg:true,
		  success : function(d) {
			  // 保存成功后，刷新数据
			  tblShortOrderFinance.reload();
		  }
	  });
	  $("#settlementModal").modal("hide");
	  
  })
  
  
	//表单赋值
	function setValue(shOrderFinss){
		$("#chooseProjectFormId [name=projectId]").val(shOrderFinss[0].projectId);
		$("#chooseProjectFormId [name=projectCode]").val(shOrderFinss[0].projectCode);
		$("#chooseProjectFormId [name=custPackId]").val(shOrderFinss[0].custPackId);
		$("#chooseProjectFormId [name=threePartiesReceivablesId]").val(shOrderFinss[0].threeId);
		$("#chooseProjectFormId [name=besettledMoney]").val(shOrderFinss[0].besettledMoney);
		$("#chooseProjectFormId [name=threeCompaniesId]").val(shOrderFinss[0].threeCompaniesId);
		$("#chooseProjectFormId [name=threeCompanies]").val(shOrderFinss[0].threeCompanies);
		
		//存入账户 （公司 ）
		getTaxById1();
		
		// 支出账户
		getTaxById2(shOrderFinss[0].threeCompaniesId);
		
	}
	
	//根据支付比例 和发票金额 获取应付金额
	$("#paymentRatio").keyup(function(e){
		//获取发票金额
	  var produceMoney = parseFloat($("#produceMoney").val());
	 
	  //支付比例
	  var paymentRatio = parseFloat($("#paymentRatio").val());
	  
	  $("#payableMoney").val(accMul(produceMoney,paymentRatio));
	})
	
	//js乘法 获取更精确的值
	function accMul(arg1,arg2){   
	    var m=0,s1=arg1.toString(),s2=arg2.toString();   
	    try{m+=s1.split(".")[1].length}catch(e){}   
	    try{m+=s2.split(".")[1].length}catch(e){}   
	    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)   
	}
	
	
  	$("#projectId").change(function(e) {
  		 $("#addReceiveMoneyFormId [name=produceMoney]").val(0);
		e.preventDefault();
		var id=$("#addReceiveMoneyFormId [name=projectId]").children('option:selected').val();
		
		if(id == "" ){
			return ;
		}
		var type = "GET";
		var url = "api/getInvoiceManagementByProjectId.do?projectId="+id;
		$.fd.ajax({
			url : url,
			type : type,
			dataType :"json",
			cache : false,
			success : function(data) {
				  var selectStr = '<option></option>';
				  if(data.data !== ""){
					  $.each(data.data.data,function(index,customerPack){
						  selectStr += '<option value="' + customerPack.custPackId + '">' + customerPack.custPackId + '</option>';
					  })
				  }
				  // 填充下拉列表
				  $("#addReceiveMoneyFormId [name=customerPackId]").html(selectStr);
			}
		});
	});
  	
  	$("#customerPackId").change(function(e) {
  		$("#addReceiveMoneyFormId [name=produceMoney]").val("");
		e.preventDefault();
		var id=$("#addReceiveMoneyFormId [name=customerPackId]").children('option:selected').val();
		var projectId=$("#addReceiveMoneyFormId [name=projectId]").children('option:selected').val();
		if(id == "" ){
			return ;
		}
		
		var type = "GET";
		var url = "api/getInvoiceManagementByProjectId?projectId="+projectId+"&custPackId="+id;
		$.fd.ajax({
			url : url,
			type : type,
			dataType :"json",
			cache : false,
			success : function(data) {
					$.each(data.data.data,function(index,invoice){
					$("#addReceiveMoneyFormId [name=produceMoney]").val(invoice.invoiceMoney);
				  })
			}
		});
	});
  	
  
  function loadqueryCondition(){
    queryCondition.projectId = $("#frmShortOrderFinanceQuery [name=projectId]").val();
    queryCondition.projectCode = $("#frmShortOrderFinanceQuery [name=projectCode]").val();
    queryCondition.branchGroupName = $("#frmShortOrderFinanceQuery [name=branchGroupName]").val();
    queryCondition.beginDate = $("#frmShortOrderFinanceQuery [name=beginDate]").val();
    queryCondition.endDate = $("#frmShortOrderFinanceQuery [name=endDate]").val();
    queryCondition.packFlag = 1;
    return queryCondition;
  }
  //解包
  $("#btnDriverCheckDetailUnpack").click(function(e) {
	    e.preventDefault();
	    var shPackIds = tblShortOrderFinance.getSelections('shPackId');
	    if (!shPackIds || shPackIds.length ==0) {
	      $.fd.msg.notice("请至少选择一条信息");
	      return false;
	    }
	    var ids = $.arrToSplit(shPackIds);
		var type = "put";
		var url = "api/shortPacks/dissolve/"+ids;
		
		$.confirm("解包","你已选择DSAC-1234312，是否还原到未打包状态",{
		    success:function(){
				$.fd.ajax({
					url : url,
					type : type,
					showMsg:true,
					success : function(d) {
						// 保存成功后，刷新数据
						tblShortOrderFinance.reload();
						tblPoint.reload();
					}
				});
		    }
	    });
		
   });
  //财务审核
  $("#btnDriverCheckDetailAproveFinance").click(function(e) {
	    e.preventDefault();
	    var shPackIds = tblPoint.getSelections('threePartiesSettleId');
	    if (!shPackIds || shPackIds.length ==0) {
	      $.fd.msg.notice("请至少选择一条信息");
	      return false;
	    }
	    
	    var settleStatus = tblPoint.getSelections('settleStatus');
		for (var i in settleStatus) {
			  if(settleStatus[i] != 0) {
				  $.fd.msg.notice("只能选择结算财务待审核的信息");
			      return false;
			  }
		  }
	    
	    var ids = $.arrToSplit(shPackIds);
		var type = "put";
		var url = "api/threePartiesSettle/financeAudit/"+ids;
		
		$.confirm("财务审核","你已选择N条数据，是否审核？",{
		    success:function(){
				$.fd.ajax({
					url : url,
					type : type,
					showMsg:true,
					success : function(d) {
						// 保存成功后，刷新数据
						tblShortOrderFinance.reload();
						tblPoint.reload();
					}
				});
		    }
	    });
		
 });
  //反审核
  $("#btnDriverCheckDetailDeaproveFinance").click(function(e) {
	    e.preventDefault();
	    var shPackIds = tblPoint.getSelections('threePartiesSettleId');
	    if (!shPackIds || shPackIds.length ==0) {
	      $.fd.msg.notice("请至少选择一条信息");
	      return false;
	    }
	    var settleStatus = tblPoint.getSelections('settleStatus');
		for (var i in settleStatus) {
			  if(settleStatus[i] != 1) {
				  $.fd.msg.notice("只能选择结算财务已审核的信息");
			      return false;
			  }
		  }
	    
	    var ids = $.arrToSplit(shPackIds);
		var type = "put";
		var url = "api/threePartiesSettle/againstAudit/"+ids;
		
		$.confirm("反审核","你已选择DSAC-1234312，是否还原到未审核状态",{
		    success:function(){
				$.fd.ajax({
					url : url,
					type : type,
					showMsg:true,
					success : function(d) {
						// 保存成功后，刷新数据
						tblShortOrderFinance.reload();
						tblPoint.reload();
					}
				});
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
		queryCondition.threePartiesReceivablesId=d["threeId"];//赋值财务信息主键
		$.fd.DataTable.ajax("api/getAllThreePartiesSettle", data, callback, queryCondition);
	};
	
	//页面初始化操作
   $("#btnShortOrderFinanceQuery").click();
   
   
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
	function getAccountDetailById2(){
		var id = $("#receiveAccountId option:selected").val();
		 if(id == "" || id == null || id == undefined){
			 $("#receiveNumber").val("");
			 $("#receiveBankNumber").val("");
			 $("#receiveTaxNumber").val("");
			 $("#receiveMoney").val("");
		 }else{
			 $("#receiveNumber").val("");
			 $("#receiveBankNumber").val("");
			 $("#receiveTaxNumber").val("");
			 $("#receiveMoney").val("");
			 $.fd.ajax({
					type : 'GET',
					url : "api/getAccountDetailById",
					data : {"id":id},
					async:false, 
					success : function(data) {
						$("#receiveNumber").val(data.data.accountNum);
						$("#receiveBankNumber").val(data.data.bankNum);
						$("#receiveTaxNumber").val(data.data.taxIdentificationNumber);
						$("#receiveMoney").val(data.data.accountBalance);
					},
				}); 
		 }
	}
	
	function getAccountDetailById1(){
		var id = $("#payAccountId option:selected").val();
		 if(id == "" || id == null || id == undefined){
			 $("#payNumber").val("");
		 }else{
			 $("#payNumber").val("");
			 $.fd.ajax({
					type : 'GET',
					url : "api/getAccountDetailById",
					data : {"id":id},
					async:false, 
					success : function(data) {
						$("#payNumber").val(data.data.accountNum);
						$("#payBankNumber").val(data.data.bankNum);
						$("#payTaxNumber").val(data.data.taxIdentificationNumber);
						$("#payBalance").val(data.data.accountBalance);
					},
				}); 
		 }
	}
	
	//根据支出单位获取该单位下的支出账户列表  
	function getTaxById2(cId){
		var type = 0;//客户
		 if(cId == "" || cId == null || cId == undefined){
			 $("#payAccountId").empty();
		 }else{
			 $.fd.ajax({
				type : 'GET',
				url : "api/getAccountByUnitId",
				data : {"type":type,"id":cId,"accountType":"-1"},
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
		var type = 2;//公司
		var cId = 1 ;
		 if(cId == "" || cId == null || cId == undefined){
			 $("#receiveAccountId").empty();
		 }else{
			 $.fd.ajax({
				type : 'GET',
				url : "api/getAccountByUnitId",
				data : {"type":type,"id":cId,"accountType":"-1"},
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
		 }
	}
   
	$("#btnCustomerAccountsDetail").click(function(e) {
	    e.preventDefault();
	    window.open("threePartiesReceivables.do", "contentIframe");  
    });
	
  });
}(jQuery));
