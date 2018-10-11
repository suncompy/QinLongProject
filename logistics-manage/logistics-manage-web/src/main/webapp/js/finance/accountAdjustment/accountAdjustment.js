//# sourceURL=accountAdjustment.js
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
  var tblCustomerAccounts = $("#tblCustomerAccounts").FdDataTable({
    serverData : retrieveData,
    ajaxSource : "api/adjustAccounts",
    columns : [
   {
     data : "adjustId"//复选框
   }
   ,{
     data : "expenditureName"//支出账户
   }
   ,{
     data : "expenditureAccountNum"//存入账户
   }
   ,{
     data : "depositName"//存入账号
   }
   ,{
       data : "expenditureAccountNum"//货物单价
     }
   ,{
   	data : "payment",//状态
       render:function(data,type,full,meta) {
     	  var returnVal = '';
     	  switch (data) {
 			case 0:
 				returnVal = '现金 ';
 				break;
 			case 1:
 				returnVal = '支票';
 				break;
 			case 2:
 				returnVal = '网银';
 				break;
 			default:
 				break;
 			}
     	  return returnVal;
       }
   },{
     data : "adjustMoney"//调整金额	
   }
   ,{
     data : "adjustName"//调整人
   }
   ,{
     data : "adjustDate"//调整时间
   }
   ,{
     data : "adjustCode"//流水号
   }
   ],
   columnDefs : [ {
     targets : [ 0 ],
     visible : false
   } ]
  });
  
  //结算页面
	$("#btnCustomerAccountsSettlement").click(function(e) {
		// 重置内容
		$("#chooseProjectFormId").resetText();
		$("#settlementModal").modal("show");
	});
	
	// 短驳运单财务表 - 填充结算页面
	$("#projectId").change(function(e) {
		e.preventDefault();
		autoPaddingProjectInfo();
	});
	
	//自动填充 项目信息
	function autoPaddingProjectInfo(costPackIds) {
		/*$("#chooseProjectFormId [name=shPackId]").val(costPackIds[0].costPackId);//项目编号
		$("#chooseProjectFormId [name=projectCode]").val(costPackIds[0].projectCode);//项目编号
		$("#chooseProjectFormId [name=costPackCode]").val(costPackIds[0].costPackCode);//对账单号
		$("#chooseProjectFormId [name=cargoUnitPrice]").val(costPackIds[0].cargoUnitPrice);//货物单价
		$("#chooseProjectFormId [name=totalMoney]").val(costPackIds[0].totalMoney);//发票金额
		$("#chooseProjectFormId [name=settledMoney]").val(costPackIds[0].settledMoney);//已结算金额
		$("#chooseProjectFormId [name=besettledMoney]").val(costPackIds[0].besettledMoney);//待结算金额
		$("#chooseProjectFormId [name=provideBankAccount]").val(costPackIds[0].provideBankAccount);//存入账户
		//$("#chooseProjectFormId [name=settleType]").val(costPackIds[0].settleType);//结算方式
		$("#chooseProjectFormId [name=costPackId]").val(costPackIds[0].costPackId);//主键
*/	}
	
	//选择项目信息后 点击确定按钮
	$("#btnCustomerAccountsSave").click(function() {
	  var type = "post";
	  var url = "api/adjustAccount";
	  var adjustAccount = $("#depositForm").serializeJson();
	  
	  //判断调整金额是否大于支出方账户余额
	  var payBalance = $("#payBalance").val(); //支出账户余额
	  var adjustMoney = $("#settlementModal input[name=adjustMoney]").val(); //调整金额
	  
	  if(isRealNum(adjustMoney)){//true 不是数字 	//false 是数字
		  $.fd.msg.notice("调整金额必须为数字");
		  return false;
	  }
	  
	  if(parseInt(adjustMoney)>parseInt(payBalance)){//如果调整金额大于支出账户余额 返回false
		  $.fd.msg.notice("调整金额不能大于支出账户余额");
		  return false;
	  }
	  
	 /* delete costPack.projectCode;
	  delete costPack.branchGroupName;
	  delete costPack.totalMoney;*/
	  $.fd.ajax({
		  url : url,
		  type : type,
		  data : adjustAccount,
		  showMsg:true,
		  success : function(d) {
			  // 保存成功后，刷新数据
			  tblCustomerAccounts.reload();
		  }
	  });
	  $("#settlementModal").modal("hide");
	});

  //检索
  $("#btnCustomerAccountsConditionQuery").click(function(e) {
    e.preventDefault();
    // 刷新检索条件
    loadqueryCondition();
    // 刷新数据
    tblCustomerAccounts.reload();
  });
  
  //判断是否是数字
  function isRealNum(val){ //true 不是数字
	  						//false 是数字
	    // isNaN()函数 把空串 空格 以及NUll 按照0来处理 所以先去除
	    if(val === "" || val ==null){
	        return true;
	    }
	    if(!isNaN(val)){
	        return false;
	    }else{
	        return true;
	    }
	}  
  
  function loadqueryCondition(){
    queryCondition.projectId = $("#frmCustomerAccountsQuery [name=projectId]").val();
    queryCondition.projectCode = $("#frmCustomerAccountsQuery [name=projectCode]").val();
    queryCondition.beginSite = $("#frmCustomerAccountsQuery [name=beginSite]").val();
    queryCondition.endSite = $("#frmCustomerAccountsQuery [name=endSite]").val();
    queryCondition.branchGroupName = $("#frmCustomerAccountsQuery [name=branchGroupName]").val();
    queryCondition.beginDate = $("#frmCustomerAccountsQuery [name=beginDate]").val();
    queryCondition.endDate = $("#frmCustomerAccountsQuery [name=endDate]").val();
    return queryCondition;
  }
  
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
			var name = $("#payAccountId option:selected").text();
			$("#payAccountName").val(name);
			 if(id == "" || id == null || id == undefined){
				 $("#payNumber").val("");
				 $("#payBankNumber").val("");
				 $("#payTaxNumber").val("");
				 $("#payBalance").val("");
			 }else{
				 $("#payNumber").val("");
				 $("#payBankNumber").val("");
				 $("#payTaxNumber").val("");
				 $("#payBalance").val("");
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
		
		//账户种类选择 0:客户  1:站点 2:公司
		function chooseKind2(){
			 var type = $("#payType option:selected").val();
			 if(type == "" || type == null || type == undefined){
				 $("#payUnitId").empty();
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
							}else if(type==2){//公司
								var company = data.data.data;
								$("#receiveUnitId").append('<option value="' + company.id + '">' + company.name + '</option>');
							}
						}
						getTaxById1();
					},
				}); 
			 }
		}
  });
}(jQuery));


