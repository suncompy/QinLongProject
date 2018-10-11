//# sourceURL=customerCheckingConf.js
/**
 * @title 客户对账设置表
 * @description 客户对账设置表
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

  // 获取客户对账设置表信息
  var retrieveData = function(source, data, callback) {
	  data = $.fd.DataTable.loadPageOpts(page,data);
    $.fd.DataTable.ajax(source, data, callback, queryCondition);
  };


  // 客户对账设置表信息一览
  var tblCustomerCheckingConf = $("#tblCustomerCheckingConf").FdDataTable({
    serverData : retrieveData,
    rowSelect: reloadPoint,
    "scrollX": true,
    displayLength : 20,
    ajaxSource : "api/customerCheckingConfs",
    columns : [
	{
      data : "custCheckConId"
    }
    ,{
      data : "projectCode"
    }
    ,{
      data : "branchGroupName"
    }
    ,{
      data : "custCheckConId"
    }
    ,{
      data : "checkingStatus",
      render:function(data,type,full,meta) {
    	  var returnVal = '';
    	  switch (data) {
			case 0:
				returnVal = '待确认';
				break;
			case 1:
				returnVal = '待审核';
				break;
			case 2:
				returnVal = '财务审核通过';
				break;
			case 3:
				returnVal = '确认不通过';
				break;
			case 4:
				returnVal = '审核不通过';
				break;	
			default:
				break;
			}
    	  return returnVal;
      }
    }
    ,{
      data : "sendCargoCompanyName"
    }
    ,{
      data : "beginAddress"
    }
    ,{
      data : "receiveCargoCompanyName"
    }
    ,{
      data : "endAddress"
    }
    ,{
        data : "cargoName"
      }
      ,{
        data : "startDate"
      }
      ,{
        data : "endDate"
      }
      ,{
        data : "totalOrder"
      }
      ,{
        data : "totalWeight"
      }
      ,{
        data : "totalPiece"
      }
      ,{
        data : "produceMoney"
      }
      ,{
        data : "taxMoney"
      }
      ,{
          data : "busCheckingStatus"
        }
        ,{
          data : "prodectMoneyCar"
        }
        ,{
          data : "receiveCheckingStatus"
        }
        ,{
          data : "prodectMoneyRecive"
        }
        ,{
          data : "trainCheckingStatus"
        }
        ,{
          data : "prodectMoneyTrain"
        }
        ,{
          data : "deliCheckingStatus"
        }
        ,{
          data : "prodectMoneySend"
        }
        ,{
            data : "createUserName"
          }
          ,{
            data : "createDate"
          }
    ],
    columnDefs : [ {
      targets : [ 0 ],
      visible : false
    } ]
  });

  // 检索
  $("#btnCustomerCheckingConfQuery").click(function(e) {
    e.preventDefault();
    // 刷新检索条件
    loadqueryCondition();
    // 刷新数据
    tblCustomerCheckingConf.reload();
  });

  function loadqueryCondition(){
    queryCondition.projectId = $("#frmCustomerCheckingConfQuery [name=projectId]").val();
    queryCondition.projectCode = $("#frmCustomerCheckingConfQuery [name=projectCode]").val();
    queryCondition.branchGroupName = $("#frmCustomerCheckingConfQuery [name=branchGroupName]").val();
    queryCondition.beginDate = $("#frmCustomerCheckingConfQuery [name=beginDate]").val();
    queryCondition.endDate = $("#frmCustomerCheckingConfQuery [name=endDate]").val();
    return queryCondition;
  }
	// 短驳运单财务表 - 填充开始对账页面
	$("#chooseProjectFormId [name=projectId]").change(function(e) {
		e.preventDefault();
		autoPaddingProjectInfo();
	});
//自动填充 项目信息
	function autoPaddingProjectInfo() {
		var projectId=$("#chooseProjectFormId [name=projectId]").children('option:selected').val();
		$.fd.ajax({
			url : "api/checkConf/projectId/"+projectId+"/2",
			type: "get",
			success : function(data) {
				var project = data.data;
				$("#chooseProjectFormId [name=startDate]").val(null);
				$("#chooseProjectFormId [name=endDate]").val(null);
				$("#chooseProjectFormId [name=taxRate]").val(null);
				$("#chooseProjectFormId").loadJson(project);
			}
		})
	}
  //开始对账 modal
  $("#btnCustCheckStartChecking").click(function(e) {
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
					//自动填充 项目信息
					autoPaddingProjectInfo();
			  }
		  });
		  /*$('#chooseProjectFormId [name=projectId]').combobox({
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
		  //自动填充 项目信息
		  autoPaddingProjectInfo();
		  $("#chooseProjectDivId").modal("show");
  });
  
   //选择项目信息后 点击 开始对账按钮
	$("#btnShortOrderFinanceQueryProjectId").click(function(e) {
		var projectId=$("#chooseProjectFormId [name=projectId]").children('option:selected').val();
	 
		var type = "post";
		var url = "api/customerCheckingConf";
		var projectId = $("#chooseProjectFormId [name=projectId]").val();
		var taxRate = $("#chooseProjectFormId [name=taxRate]").val();
		var startDate = $("#chooseProjectFormId [name=startDate]").val();
		var endDate = $("#chooseProjectFormId [name=endDate]").val();
		if(!endDate){
			$.fd.msg.notice("请填入对账结束日期！");
			return false;
		}
		if(startDate && startDate>=endDate){
			$.fd.msg.notice("对账结束日期要大于开始日期！");
			return false;
		}
		if(!taxRate){
			$.fd.msg.notice("请填入税率！");
			return false;
		}
		
		$.fd.ajax({
			  url : url,
			  type : type,
			  data : {projectId:projectId,taxRate:taxRate,startDate:startDate,endDate:endDate},
			  success : function(data) {
				  tblCustomerCheckingConf.reload();
				  $("#chooseProjectDivId").modal("hide");
			  }
		  });
		  
	});

  function reloadPoint(){
	  var data = tblCustomerCheckingConf.getSelected();
	  //data.transportType = "0";
	  var transportType = data.transportType;
	  var projectId = data.projectId;
	  var src = "";
	  var param = "?transportType="+transportType+"&projectId="+projectId+"&custCheckConId="+data.custCheckConId;
	  if(data.transportType == "0"){         //汽运
		  //hideButton();
		  //$("#btnCustCheckCar").show();
		  src = "custCheckCar.do"+param+"&orderType=2";
	  }else if(data.transportType == "1"){   //接取
		  //hideButton();
		  //$("#btnCustCheckRecive").show();
		  src = "custCheckRecive.do"+param+"&orderType=0";
	  }else if(data.transportType == "2"){   //送达
		  //hideButton();
		  //$("#btnCustCheckSend").show();
		  src = "custCheckSend.do"+param+"&orderType=1";
	  }else if(data.transportType == "3"){   //火运
		  //hideButton();
		  //$("#btnCustCheckTrain").show();
		  src = "custCheckTrain.do"+param+"&orderType=3";
	  }else if(data.transportType == "4"){   //接取+火运
		  //$("#btnCustCheckRecive").show();
		  //$("#btnCustCheckTrain").show();
		  src = "custCheckRecive.do"+param+"&orderType=0";
	  }else if(data.transportType == "5"){   //火运+送达
		  //$("#btnCustCheckTrain").show();
		  //$("#btnCustCheckSend").show();
		  src = "custCheckTrain.do"+param+"&orderType=3";
	  }else if(data.transportType == "6"){   //联运（接取火运送达）
		  //showButton();
		  //$("#btnCustCheckCar").hide();
		  src = "custCheckRecive.do"+param+"&orderType=0";
	  }else if(data.transportType == "7"){   //接取+送达
		  //$("#btnCustCheckRecive").show();
		  //$("#btnCustCheckSend").show();
		  src = "custCheckRecive.do"+param+"&orderType=0";
	  }

	  $("[name=custCheckFrame]").attr("src",src);
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
  
  $("#btnCustCheckCar").click(function(e) {
	    e.preventDefault();
	    window.open("custCheckCarDetail.do?packFlag=null&transportType="+getTransportType(), "contentIframe");  
 });
  $("#btnCustCheckRecive").click(function(e) {
	    e.preventDefault();
	    window.open("custCheckReciveDetail.do?packFlag=null&transportType="+getTransportType(), "contentIframe");  
 });
  $("#btnCustCheckTrain").click(function(e) {
	    e.preventDefault();
	    window.open("custCheckTrainDetail.do?packFlag=null&transportType="+getTransportType(), "contentIframe");  
 });
  $("#btnCustCheckSend").click(function(e) {
	    e.preventDefault();
	    window.open("custCheckSendDetail.do?packFlag=null&transportType="+getTransportType(), "contentIframe");  
 });
  function getTransportType(){
	  var data = tblCustomerCheckingConf.getSelected();
	  return data&&data.transportType?data.transportType:0;
  }
  
  //客户对账 - 确认对账
  $("#btnCustCheckConfirmCheck").click(function(e) {
	  e.preventDefault();
	  var custCheckConIds = tblCustomerCheckingConf.getSelections('custCheckConId');
	  var financeStatuss = tblCustomerCheckingConf.getSelections('checkingStatus');
	  if (!custCheckConIds || custCheckConIds.length ==0) {
	      $.fd.msg.notice("请至少选择一条信息");
	      return false;
	   }
	  for (var i in financeStatuss) {
		  if(financeStatuss[i] != 0 && financeStatuss[i] != 3) {
			  $.fd.msg.notice("只能选择待确认信息");
		      return false;
		  }
	  }
		$.confirm("确认对账","是否进行确认对账？",{
		    success:function(){
		    	doConfirm(custCheckConIds,"1");
		    },
		    failure:function(){
		    	doConfirm(custCheckConIds,"3");
		    }
		},{btnOk:"同意",btnCancel:"拒绝"});
		
	  });
      function doConfirm(custCheckConIds,checkingStatus){
    	  var type = "put";
  		  var url = "api/customerCheckingConf/sureChecking/"+custCheckConIds+"/"+checkingStatus;
    	  $.fd.ajax({
				url : url,
				type : type,
				showMsg:true,
				success : function(data) {
					// 保存成功后，刷新数据
					tblCustomerCheckingConf.reload();
				}
		});
     }
	  
	  // 客户对账 - 反确认
	  $("#btnCustCheckDeConfirmCheck").click(function(e) {
		  e.preventDefault();
		  var custCheckConIds = tblCustomerCheckingConf.getSelections('custCheckConId');
		  var financeStatuss = tblCustomerCheckingConf.getSelections('checkingStatus');
		  if (!custCheckConIds || custCheckConIds.length ==0) {
			  $.fd.msg.notice("请至少选择一条信息");
			  return false;
		  }
		  for (var i in financeStatuss) {
			  if(financeStatuss[i] != 1) {
				  $.fd.msg.notice("只能选择待审核信息");
				  return false;
			  }
		  }
		  var type = "put";
		  var url = "api/customerCheckingConf/againstVerify/"+custCheckConIds;
		  $.confirm("反确认对账","是否进行反确认对账",{
			    success:function(){
				  $.fd.ajax({
					  url : url,
					  type : type,
					  showMsg:true,
					  success : function(data) {
						  // 保存成功后，刷新数据
						  tblCustomerCheckingConf.reload();
					  }
				  });
			    }
		    });	  
	  });
	  
	  // 客户对账 - 财务审核
	  $("#btnCustCheckAudit").click(function(e) {
		  e.preventDefault();
		  var custCheckConIds = tblCustomerCheckingConf.getSelections('custCheckConId');
		  var financeStatuss = tblCustomerCheckingConf.getSelections('checkingStatus');
		  if (!custCheckConIds || custCheckConIds.length ==0) {
			  $.fd.msg.notice("请至少选择一条信息");
			  return false;
		  }
		  for (var i in financeStatuss) {
			  if(financeStatuss[i] != 1 &&financeStatuss[i] != 4) {
				  $.fd.msg.notice("只能选择待审核信息");
				  return false;
			  }
		  }
		  $.confirm("财务审核","是否进行财务审核",{
			    success:function(){
			    	doAuditFinance(custCheckConIds,"2");
			    },
			    failure:function(){
			    	doAuditFinance(custCheckConIds,"4");
			    }
		 },{btnOk:"同意",btnCancel:"拒绝"});
			
	  });
	  function doAuditFinance(custCheckConIds,checkingStatus){
    	  var type = "put";
  		  var url = "api/customerCheckingConf/financeAudit/"+custCheckConIds+"/"+checkingStatus;
    	  $.fd.ajax({
				url : url,
				type : type,
				showMsg:true,
				success : function(data) {
					// 保存成功后，刷新数据
					tblCustomerCheckingConf.reload();
				}
		});
     }
	  
	  // 客户对账 -  财务反审核
	  $("#btnCustCheckDeAudit").click(function(e) {
		  e.preventDefault();
		  var custCheckConIds = tblCustomerCheckingConf.getSelections('custCheckConId');
		  var financeStatuss = tblCustomerCheckingConf.getSelections('checkingStatus');
		  if (!custCheckConIds || custCheckConIds.length ==0) {
			  $.fd.msg.notice("请至少选择一条信息");
			  return false;
		  }
		  for (var i in financeStatuss) {
			  if(financeStatuss[i] != 2) {
				  $.fd.msg.notice("只能选择已审核信息");
				  return false;
			  }
		  }
		  var type = "put";
		  var url = "api/customerCheckingConf/againstAudit/"+custCheckConIds;
		  $.confirm("财务反审核","是否进行财务反审核",{
			    success:function(){
				  $.fd.ajax({
					  url : url,
					  type : type,
					  showMsg:true,
					  success : function(data) {
						  // 保存成功后，刷新数据
						  tblCustomerCheckingConf.reload();
					  }
				  });
			    }
		    });	  
	  });
  });
}(jQuery));
