//# sourceURL=invoice.js
/**
 * @title 发票
 * @description 发票
 * @author LiuJiefeng
 * @date 2018-03-29
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

  // 获取发票信息
  var retrieveData = function(source, data, callback) {
	  data = $.fd.DataTable.loadPageOpts(page, data);
    $.fd.DataTable.ajax(source, data, callback, queryCondition);
  };


  // 发票信息一览
  var tblInvoice = $("#tblInvoice").FdDataTable({
    serverData : retrieveData,
    ajaxSource : "api/invoices",
    columns : [
	{
      data : "invoiceId"
    }, {
		data : "projectCode"// 项目编号
	}
    ,{
      data : "packId"
    }, {
		data : "branchGroupName"// 发生网点
	}, {
		data : "createDate"// 发生时间(发票新建时间)
	}, {
		data : "trainFlag",// 业务类型
		render : function(data, type, full, meta) {
			var returnVal = '联运';
			switch (data) {
			case 0:
				returnVal = '汽运 ';
				break;
			case 1:
				returnVal = '接取';
				break;
			case 2:
				returnVal = '送达';
				break;
			case 3:
				returnVal = '火运';
				break;
			default:
				break;
			}
			return returnVal;
		}
	}, {
		data : "startDate"// 业务时间
	},{
		data : "beginAddress"// 起始地
	},{
		data : "endAddress"// 运抵地
	}
    ,{
      data : "invoiceTypeName"// 发票类型
    }, {
		data : "invoiceStatus",// 状态
		render : function(data, type, full, meta) {
			var returnVal = '';
			switch (data) {
			case 0:
				returnVal = '待登入 ';
				break;
			case 1:
				returnVal = '已登入';
				break;
			case 2:
				returnVal = '发票作废待审核';
				break;
			case 3:
				returnVal = '已作废';
				break;
			case 4:
				returnVal = '作废审核不通过';
				break;
			default:
				break;
			}
			return returnVal;
		}
	}, {
		data : "receiveCompanyName"// 受票方
	}, {
		data : "receiveDutyParagraph"// 纳税识别号
	}, {
		data : "openInvoiceProject"// 开票项目
	}
    ,{
      data : "invoiceMoney"
    }
    ,{
      data : "taxRate"
    }
    ,{
      data : "taxMoney"
    }
    ,{
      data : "totalMoney"
    }
    , {
		data : "provideCompanyName"// 出具方
	}, {
		data : "provideDutyParagraph"// 纳税识别号
	}, {
		data : "createUserName"// 经办人
	}, {
		data : "auditUserId"// 审核人
	}, {
		data : "auditDate"// 审核时间
	}, {
		data : "backName"// 退回人
	}, {
		data : "backDepartment"// 退回部门
	}, {
		data : "backDate"// 退回时间
	}, {
		data : "remark"// 备注
	}, {
		data : "modifiyDate"// 更新时间
	}
    ],
    columnDefs : [ {
      targets : [ 0 ],
      visible : false
    } ]
  });

	// 发票登入页面
	$("#btnInvoiceBind").click(function(e) {
		e.preventDefault();
		var data = tblInvoice.getSelected();
	    if (!data) {
			$.fd.msg.notice("请选择其中一条信息");
			return false;
		}
		var invoiceStatus = tblInvoice.getSelections('invoiceStatus');//状态
		for (var i in invoiceStatus) {
		  if(invoiceStatus[i] != 0) {
			  $.fd.msg.notice("只能选择待登入信息");
		      return false;
		  }
		}
		
	   $("#chooseProjectFormId").resetText();
	   $("#chooseProjectFormId").loadJson(data);
	   //$("#receiveType").val(0);
	   chooseKind1();
	   $("#InvoiceAddModal").modal("show");
	});
	
	// 发票登入
	$("#btnInvoiceQueryProjectId").click(function() {
		var data = tblInvoice.getSelected();
		var invoice = {};
		invoice.invoiceId = data.invoiceId;
		invoice.invoiceStatus = 1;
		invoice.provideCompanyId = $("#receiveAccountId").val();
		invoice.provideType = 0;
		invoice.receiveCompanyName = $("#payUnitName").val();
		invoice.provideCompanyName = $("#receiveUnitName").val();
		invoice.receiveCompanyId = $("#payAccountId").val();
		var receiveType = $("#payType").val();
		var receiveCompanyName = $("#payUnitName").val();
		
		var payAccountId = $("#payAccountId").val();
		var payTaxNumber = $("#payTaxNumber").val();
		var payBankNumber = $("#payBankNumber").val();
		var payNumber = $("#payNumber").val();
		var receiveAccountId = $("#receiveAccountId").val();
		var receiveTaxNumber = $("#receiveTaxNumber").val();
		var receiveBankNumber = $("#receiveBankNumber").val();
		var receiveNumber = $("#receiveNumber").val();
		
		if (!payAccountId) {
			  $.fd.msg.notice("支出账户不能为空!");
			  return false;
		}
		if (!payTaxNumber) {
			  $.fd.msg.notice("纳税识别号不能为空!");
			  return false;
		}
		if (!payBankNumber) {
			  $.fd.msg.notice("支出行号不能为空!");
			  return false;
		}
		if (!payNumber) {
			  $.fd.msg.notice("支出账号不能为空!");
			  return false;
		}
		if (!receiveAccountId) {
			  $.fd.msg.notice("收款账户不能为空!");
			  return false;
		}
		if (!receiveTaxNumber) {
			  $.fd.msg.notice("纳税识别号不能为空!");
			  return false;
		}
		if (!receiveBankNumber) {
			  $.fd.msg.notice("收款行号不能为空!");
			  return false;
		}
		if (!receiveNumber) {
			  $.fd.msg.notice("收款账号不能为空!");
			  return false;
		}
		
		var type = "put";
		var url = "api/invoice";
		$.fd.ajax({
			url : url,
			type : type,
			data : invoice,
			showMsg:true,
			success : function(data) {
				// 保存成功后，刷新数据
				tblInvoice.reload();
			}
		});
		$("#InvoiceAddModal").modal("hide");
	});
	
	//发票作废
	$("#btnInvoiceCancel").click(function(e) {
		e.preventDefault();
		var data = tblInvoice.getSelected();
		if (!data) {
			$.fd.msg.notice("请选择其中一条待登入信息");
			return false;
		}
		var invoiceStatus = tblInvoice.getSelections('invoiceStatus');//状态
		for (var i in invoiceStatus) {
		  if(invoiceStatus[i] != 0) {
			  $.fd.msg.notice("只能选择待登入信息");
		      return false;
		  }
		}
		var invoice = {};
		invoice.invoiceId = data.invoiceId;
		invoice.invoiceStatus = 2;
		var type = "put";
		var url = "api/invoice";
		$.confirm("发票作废","你已选择1条数据，是否改为发票作废状态",{
		    success:function(){
			$.fd.ajax({
				url : url,
				type : type,
				data : invoice,
				showMsg:true,
				success : function(data) {
					// 保存成功后，刷新数据
					tblInvoice.reload();
				}
			});
		   }
	    });
	})
	
	//发票作废审核
	$("#btnInvoiceCancelAudit").click(function(e) {
		e.preventDefault();
		var data = tblInvoice.getSelected();
		if (!data) {
			$.fd.msg.notice("请选择其中一条待登入信息");
			return false;
		}
		var invoiceStatus = tblInvoice.getSelections('invoiceStatus');//状态
		for (var i in invoiceStatus) {
		  if(invoiceStatus[i] != 2 && invoiceStatus[i] != 4) {
			  $.fd.msg.notice("只能选择作废待审核或不通过信息");
		      return false;
		  }
		}
		var invoice = {};
		invoice.invoiceId = data.invoiceId;
		invoice.invoiceStatus = 3;
		var type = "put";
		var url = "api/invoice";
		$.confirm("发票作废","你已选择1条数据，发票作废审核是否通过",{
			success:function(){
				$.fd.ajax({
					url : url,
					type : type,
					data : invoice,
					showMsg:true,
					success : function(data) {
						// 保存成功后，刷新数据
						tblInvoice.reload();
					}
				});
		    },
		    failure:function(){
		    	invoice.invoiceStatus = 4;
				$.fd.ajax({
					url : url,
					type : type,
					data : invoice,
					showMsg:true,
					success : function(data) {
						// 保存成功后，刷新数据
						tblInvoice.reload();
					}
				});
		    }
	    },{btnOk:"同意",btnCancel:"拒绝"});
	})
	
	//发票作废反审核(改为已登入状态)
	$("#btnInvoiceCancelNoAudit").click(function(e) {
		e.preventDefault();
		var data = tblInvoice.getSelected();
		if (!data) {
			$.fd.msg.notice("请选择其中一条待登入信息");
			return false;
		}
		var invoiceStatus = tblInvoice.getSelections('invoiceStatus');//状态
		for (var i in invoiceStatus) {
		  if(invoiceStatus[i] != 3) {
			  $.fd.msg.notice("只能选择已作废信息");
		      return false;
		  }
		}
		var invoice = {};
		invoice.invoiceId = data.invoiceId;
		invoice.invoiceStatus = 0;
		var type = "put";
		var url = "api/invoice";
		$.confirm("发票作废","你已选择1条数据，是否改为待审核状态",{
		    success:function(){
			$.fd.ajax({
				url : url,
				type : type,
				data : invoice,
				showMsg:true,
				success : function(data) {
					// 保存成功后，刷新数据
					tblInvoice.reload();
				}
			});
		   }
	    });
	})
	
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
						$("#receiveNumber").val(data.data.accountNum);
						$("#receiveBankNumber").val(data.data.bankNum);
						$("#receiveTaxNumber").val(data.data.taxIdentificationNumber);
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
		 }
		 /*else if(type == 2){
			 $("#payUnitId").empty();
			 $("#payUnitId").append("<option value='1'>新疆秦龙矿业有限公司</option>");
			 getTaxById2();
		 }*/
		 else{
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
						}else if(type == 2){
							if(data && data.data && data.data.data){
								$("#payUnitId").append("<option value="+data.data.data.id+">" + data.data.data.name+ "</option>");
							}
							
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
		$("#payUnitName").val(cName);
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
		var type = $("#receiveType option:selected").val();
		var cId = $("#receiveUnitId option:selected").val();
		var cName = $("#receiveUnitId option:selected").text();
		$("#receiveUnitName").val(cName);
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
	
	function chooseKind1(){
		//0;
		 var type = $("#receiveType option:selected").val();
		 type = !type?99:type;
		 $.ajax({
			type : 'POST',
			url : "account/getAccountNameByType.do",
			data : {"type":type},
			dataType : "json",
			async:false, 
			success : function(data) {
				if(data.status==200){
					$("#receiveUnitId").empty();
					if(type == 0){
						$.each(data.data.data, function(index, its) {
							$("#receiveUnitId").append("<option value="+its.id+">" + its.companyName+ "</option>");
 						});
					}else if(type == 1){
						$.each(data.data.data, function(index, its) {
							$("#receiveUnitId").append("<option value="+its.id+">" + its.stationName+ "</option>");
 						});
					}else if(type == 2){
						if(data && data.data && data.data.data){
							$("#receiveUnitId").append("<option value="+data.data.data.id+">" + data.data.data.name+ "</option>");
						}
					}
				}
				getTaxById1();
			},
		}); 
	}
	
	

	// 检索
	  $("#btnInvoiceQuery").click(function(e) {
	    e.preventDefault();
	    // 刷新检索条件
	    loadqueryCondition();
	    // 刷新数据
	    tblInvoice.reload();
	  });

	  function loadqueryCondition(){
	    queryCondition.projectCode = $("#frmInvoiceQuery [name=projectCode]").val();
	    queryCondition.branchGroupName = $("#frmInvoiceQuery [name=branchGroupName]").val();
	    queryCondition.beginDate = $("#frmInvoiceQuery [name=beginDate]").val();
	    queryCondition.endDate = $("#frmInvoiceQuery [name=endDate]").val();
	    return queryCondition;
	  }

  });
}(jQuery));
