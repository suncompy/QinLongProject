//# sourceURL=invoiceManagement.js
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
			data = $.fd.DataTable.loadPageOpts(page, data);
			$.fd.DataTable.ajax(source, data, callback, queryCondition);
		};

		// 短驳运单财务表信息一览
		var tblInvoiceManagement = $("#tblInvoiceManagement").FdDataTable({
			serverData : retrieveData,
			ajaxSource : "api/getInvoiceManagement",
			columns : [ {
				data : "custPackId"// 复选框
			}, {
				data : "projectCode"// 项目编号
			},{
				data : "custCheckConId"// 流水号
			}, {
				data : "branchGroupName"// 发生网点
			}, {
				data : "createDate"// 发生时间(发票新建时间)
			}, {
				data : "packType",// 业务类型
				render : function(data, type, full, meta) {
					var returnVal = '';
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
				data : "arrivePlaceAddress"// 运抵地
			}, {
				data : "invoiceType",// 发票类型
				render : function(data, type, full, meta) {
					var returnVal = '';
					switch (data) {
					case 1:
						returnVal = '客户发票 ';
						break;
					case 2:
						returnVal = '货款发票';
						break;
					default:
						break;
					}
					return returnVal;
				}
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
				data : "receiveAccountName"// 受票方
			}, {
				data : "receiveDutyParagraph"// 纳税识别号
			}, {
				data : "invoiceType",// 开票项目
				render : function(data, type, full, meta) {
					var returnVal = '';
					switch (data) {
					case 1:
						returnVal = '运费 ';
						break;
					case 2:
						returnVal = '货款';
						break;
					default:
						break;
					}
					return returnVal;
				}
			}, {
				data : "invoiceMoney"// 开票金额
			}, {
				data : "taxRate"// 税率
			}, {
				data : "taxMoney"// 税额
			},{
				data : "totalMoney"// 合计金额
			}, {
				data : "provideAccountName"// 出具方
			}, {
				data : "provideDutyParagraph"// 纳税识别号
			}, {
				data : "createUserName"// 经办人
			}, {
				data : "auditUserId"// 审核人
			}, {
				data : "iAuditDate"// 审核时间
			}, {
				data : "backName"// 退回人
			}, {
				data : "backDepartment"// 退回部门
			}, {
				data : "backDate"// 退回时间
			}, {
				data : "remark"// 备注
			}, {
				data : "updateDate"// 更新时间
			}],
			columnDefs : [ {
				targets : [ 0 ],
				visible : false
			} ]
		});

		/*//自动填充受票人信息
		$("#receiveCompanyId").change(function(){
			var receiveCompanyId = $("#chooseProjectFormId [name=receiveCompanyId]").children('option:selected').val();
			$.ajax({
				url : "api/customerPackInvoices/getCustomerInfoByCid/" + receiveCompanyId,
				type : "get",
				dataType : "json",
				cache : false,
				success : function(data) {
					$.each(data,function(index,receiveCompany){
						 $("#chooseProjectFormId [name=receiveDutyParagraph]").val(receiveCompany.dutyParagraph);
						 $("#chooseProjectFormId [name=receiveOpenBank]").val(receiveCompany.openBank);
						 $("#chooseProjectFormId [name=receiveBankAccount]").val(receiveCompany.bankAccount);
						 $("#chooseProjectFormId [name=receiveAddressCode]").val(receiveCompany.addressCode);
						 $("#chooseProjectFormId [name=receiveStationPhone]").val(receiveCompany.stationPhone);
					  })
				}
			});
		});
		
		//自动填充出具方信息
		$("#provideCompanyId").change(function(){
			var provideCompanyId = $("#chooseProjectFormId [name=provideCompanyId]").children('option:selected').val();
			$.ajax({
				url : "api/customerPackInvoices/getCustomerInfoByCid/" + provideCompanyId,
				type : "get",
				dataType : "json",
				cache : false,
				success : function(data) {
					$.each(data,function(index,provideCompany){
						 $("#chooseProjectFormId [name=provideDutyParagraph]").val(provideCompany.dutyParagraph);
						 $("#chooseProjectFormId [name=provideOpenBank]").val(provideCompany.openBank);
						 $("#chooseProjectFormId [name=provideBankAccount]").val(provideCompany.bankAccount);
						 $("#chooseProjectFormId [name=provideAddressCode]").val(provideCompany.addressCode);
						 $("#chooseProjectFormId [name=provideStationPhone]").val(provideCompany.stationPhone);
					  })
				}
			});
		});*/
		
		// 发票登入页面
		$("#btnInvoiceManagementAdd").click(function(e) {
			e.preventDefault();
			var data = tblInvoiceManagement.getSelected();
		    if (!data) {
				$.fd.msg.notice("请选择其中一条信息");
				return false;
			}
			var invoiceStatus = tblInvoiceManagement.getSelections('invoiceStatus');//状态
			for (var i in invoiceStatus) {
			  if(invoiceStatus[i] != 0) {
				  $.fd.msg.notice("只能选择待登入信息");
			      return false;
			  }
			}
		   $("#chooseProjectFormId").resetText();
		   $("#chooseProjectFormId").loadJson(data);
		   $("#invoiceManagementAddModal").modal("show");
		});
		
		//光标离开后计算金额
		/*$("[name=taxRate]").blur(function(e){
			e.preventDefault();
			var taxRate = $("#taxRate").val();//税率
			var invoiceMoney = $("#invoiceMoney").val();//开票金额
			var taxMoneyInt;//税额
			var totalMoneyInt;//合计金额
			if(taxRate !=null && taxRate != ""){
				var taxRateStr = parseFloat(taxRate);
				var invoiceMoneyStr = parseFloat(invoiceMoney);
				taxMoneyInt = taxRateStr * invoiceMoneyStr;
				totalMoneyInt = invoiceMoneyStr - taxMoneyInt;
				$("#totalMoney").val(totalMoneyInt);
				$("#taxMoney").val(taxMoneyInt);
			}
		});*/
		
		// 发票登入
		$("#btnInvoiceManagementQueryProjectId").click(function() {
			var custPackIdO = tblInvoiceManagement.getSelections('custPackId');
			var custPackIds = tblInvoiceManagement.getSelecteds(custPackIdO);
			var custPackId = custPackIds[0].custPackId;
			var provideCompanyId = $("#payAccountId").val();
			var receiveCompanyId = $("#receiveAccountId").val();
			var invoiceMoney = $("#invoiceMoney").val();//开票金额
			var taxRate = $("#taxRate").val();//税率
			var taxMoney = $("#taxMoney").val();//税额
			var totalMoney = $("#totalMoney").val();//合计金额
			var invoiceType = $("#invoiceType").val();
			var type = "post";
			var url = "api/insertCustomerPackInvoice";
			$.fd.ajax({
				url : url,
				type : type,
				//data : $('#chooseProjectFormId').serialize(),
				data : {custPackId:custPackId,provideCompanyId:provideCompanyId,receiveCompanyId:receiveCompanyId,invoiceMoney:invoiceMoney,taxRate:taxRate,taxMoney:taxMoney,totalMoney:totalMoney,invoiceType:invoiceType},
				dataType: "json",
				showMsg:true,
				success : function(data) {
					// 保存成功后，刷新数据
					tblInvoiceManagement.reload();
				}
			});
			$("#invoiceManagementAddModal").modal("hide");
		});
		
		//发票作废
		$("#btnInvoiceManagementCancel").click(function(e) {
			e.preventDefault();
			var custPackIdO = tblInvoiceManagement.getSelections('custPackId');
			var custPackIds = tblInvoiceManagement.getSelecteds('custPackIdO');
			if(custPackIds == "" || custPackIds == null || custPackIds == undefined){
				
			}else{
				var custPackId = custPackIds[0].custPackId;
			}
			if (custPackIdO.length != 1) {
				$.fd.msg.notice("请选择其中一条信息");
				return false;
			}
			/*var data = tblInvoiceManagement.getSelected();
		    if (!data) {
				$.fd.msg.notice("请选择其中一条信息");
				return false;
			}*/
			var invoiceStatus = tblInvoiceManagement.getSelections('invoiceStatus');//状态
			for (var i in invoiceStatus) {
			  if(invoiceStatus[i] != 0) {
				  $.fd.msg.notice("只能选择待登入信息");
			      return false;
			  }
			}
			invoiceStatus = 2;
			$.confirm("发票作废","你已选择1条数据，是否改为发票作废状态",{
			    success:function(){
		    	var type = "post";
				var url = "api/updateInvoiceManagementCancel";
				$.fd.ajax({
					url : url,
					type : type,
					data : {custPackId:custPackId,invoiceStatus:invoiceStatus},
					dataType: "json",
					// showMsg:true,
					success : function(data) {
						// 保存成功后，刷新数据
						tblInvoiceManagement.reload();
					}
				});
			   }
		    });
		})
		
		//发票作废审核
		$("#btnInvoiceManagementCancelAudit").click(function(e) {
			e.preventDefault();
			var custPackIdO = tblInvoiceManagement.getSelections('custPackId');
			var custPackIds = tblInvoiceManagement.getSelecteds('custPackIdO');
			if(custPackIds == "" || custPackIds == null || custPackIds == undefined){
				
			}else{
				var custPackId = custPackIds[0].custPackId;
			}
			if (custPackIdO.length != 1) {
				$.fd.msg.notice("请选择其中一条信息");
				return false;
			}
			var invoiceStatus = tblInvoiceManagement.getSelections('invoiceStatus');//状态
			for (var i in invoiceStatus) {
			  if(invoiceStatus[i] != 2 && invoiceStatus[i] != 4) {
				  $.fd.msg.notice("只能选择发票作废待审核或者审核不通过信息");
			      return false;
			  }
			}
			$.confirm("发票作废","你已选择1条数据，发票作废审核是否通过",{
				success:function(){
					invoiceStatus = 3;
					var type = "post";
					var url = "api/updateInvoiceManagementCancel";
					$.fd.ajax({
						url : url,
						type : type,
						data : {custPackId:custPackId,invoiceStatus:invoiceStatus},
						dataType: "json",
						// showMsg:true,
						success : function(data) {
							// 保存成功后，刷新数据
							tblInvoiceManagement.reload();
						}
					});
			    },
			    failure:function(){
			    	invoiceStatus = 4;
			    	var type = "post";
					var url = "api/updateInvoiceManagementCancel";
					$.fd.ajax({
						url : url,
						type : type,
						data : {custPackId:custPackId,invoiceStatus:invoiceStatus},
						dataType: "json",
						// showMsg:true,
						success : function(data) {
							// 保存成功后，刷新数据
							tblInvoiceManagement.reload();
						}
					});
			    }
		    },{btnOk:"同意",btnCancel:"拒绝"});
		})
		
		//发票作废反审核(改为已登入状态)
		$("#btnInvoiceManagementCancelNoAudit").click(function(e) {
			e.preventDefault();
			var custPackIdO = tblInvoiceManagement.getSelections('custPackId');
			var custPackIds = tblInvoiceManagement.getSelecteds(custPackIdO);
			if(custPackIds == "" || custPackIds == null || custPackIds == undefined){
				
			}else{
				var custPackId = custPackIds[0].custPackId;
			}
			if (custPackIdO.length != 1) {
				$.fd.msg.notice("请选择其中一条信息");
				return false;
			}
			var invoiceStatus = tblInvoiceManagement.getSelections('invoiceStatus');//状态
			for (var i in invoiceStatus) {
			  if(invoiceStatus[i] != 3) {
				  $.fd.msg.notice("只能选择已作废信息");
			      return false;
			  }
			}
			invoiceStatus = 1;
			$.confirm("发票作废","你已选择1条数据，是否改为待审核状态",{
			    success:function(){
		    	var type = "post";
				var url = "api/updateInvoiceManagementCancel";
				$.fd.ajax({
					url : url,
					type : type,
					data : {custPackId:custPackId,invoiceStatus:invoiceStatus},
					dataType: "json",
					// showMsg:true,
					success : function(data) {
						// 保存成功后，刷新数据
						tblInvoiceManagement.reload();
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
			 }else if(type == 2){
				 $("#payUnitId").empty();
				 $("#payUnitId").append("<option value='1'>新疆秦龙矿业有限公司</option>");
				 getTaxById2();
			 }else{
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
			 var type = $("#receiveType option:selected").val();
			 if(type == "" || type == null || type == undefined){
				 $("#receiveUnitId").empty();
			 }else if(type == 2){
				 $("#receiveUnitId").empty();
				 $("#receiveUnitId").append("<option value='1'>新疆秦龙矿业有限公司</option>");
				 getTaxById1();
			 }else{
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
							}
						}
						getTaxById1();
					},
				}); 
			 }
		}
		
		

		// 检索
		  $("#btnInvoiceManagementQuery").click(function(e) {
		    e.preventDefault();
		    // 刷新检索条件
		    loadqueryCondition();
		    // 刷新数据
		    tblInvoiceManagement.reload();
		  });

		  function loadqueryCondition(){
		    queryCondition.projectId = $("#frmInvoiceManagementQuery [name=projectId]").val();
		    queryCondition.projectCode = $("#frmInvoiceManagementQuery [name=projectCode]").val();
		    queryCondition.beginSite = $("#frmInvoiceManagementQuery [name=beginSite]").val();
		    queryCondition.endSite = $("#frmInvoiceManagementQuery [name=endSite]").val();
		    queryCondition.branchGroupName = $("#frmInvoiceManagementQuery [name=branchGroupName]").val();
		    queryCondition.beginDate = $("#frmInvoiceManagementQuery [name=beginDate]").val();
		    queryCondition.endDate = $("#frmInvoiceManagementQuery [name=endDate]").val();
		    return queryCondition;
		  }
		
	});
}(jQuery));
