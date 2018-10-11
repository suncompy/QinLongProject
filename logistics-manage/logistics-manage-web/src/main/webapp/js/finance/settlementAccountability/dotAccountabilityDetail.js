//# sourceURL=dotAccountabilityDetail.js
/**
 * @title 短驳运单财务表
 * @description 短驳运单财务表
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * 
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
		    data : "shPackId"//复选框
		  }
		  ,{
		    data : "projectCode"//项目编号
		  }
		  ,{
		    data : "branchGroupName"//分支机构
		  }
		  ,{
		    data : "shPackId"//对账单号
		  }
		  ,{
		    data : "suppliesSettleStatus",//执行状态
			  render:function(data,type,full,meta) {
		    	  var returnVal = '';
		    	  switch (data) {
					case 0:
						returnVal = '待领取 ';
						break;
					case 1:
						returnVal = '已领取';
						break;
					case 2:
						returnVal = '无需领取';
						break;
					default:
						break;
					}
		    	  return returnVal;
		      }
		  }
		  ,{
		      data : "suppliesType",
		      render:function(data,type,full,meta) {
		    	  var returnVal = '';
		    	  switch (data) {
					case 0:
						returnVal = '油卡';
						break;
					case 1:
						returnVal = '气卡';
						break;
					default:
						break;
					}
		    	  if(0==full.paymentId||3==full.paymentId){
		    		  return '-';
		    	  }
		    	  return returnVal;
		      }
		    }
		  ,{
		    data : "suppliesAmount"//油气金额
		  }
		  /*,{
		    data : "--"//油气卡号
		  }*/
		  ,{
		    data : "suppliesReceiveType",//领取方式
			  render:function(data,type,full,meta) {
		    	  var returnVal = '';
		    	  switch (data) {
					case 0:
						returnVal = '网点领取';
						break;
					case 1:
						returnVal = '人员配送';
						break;
					default:
						break;
					}
		    	  return returnVal;
		      }
		  }
		  ,{
		    data : "receiveUserName"//领取人
		  }
		  ,{
		    data : "receiveUserPhone"//联系方式
		  }
		  /*,{
		    data : "suppliesExecuteName"//执行人
		  }
		  ,{
		    data : "suppliesExecuteDate"//执行时间
		  }
		  ,{
		    data : "--"//交账时间
		  }*/
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
  var tblDotAccountability = $("#tblDotAccountability").FdDataTable({
    serverData : retrieveData,
    rowSelect: reloadPoint,
    ajaxSource : "api/getDotAccountPacks",
    columns : [
    {
      data : "dotAccountPackId"//复选框
    }
    ,{
        data : "branchName"//分支机构
      }
    ,{
        data : "status",//状态
    	render:function(data,type,full,meta) {
       	  var status = '';
       	  switch (data) {
   			case 0:
   				status = '未审核';
   				break;
   			case 1:
   				status = '已审核';
   				break;
   			default:
   				break;
   			}
       	  return status;
         }
    }
    ,{
      data : "createDate"//交账日期
    }
    ,{
      data : "packTruckNum"//总单数
    }
    ,{
      data : "suppliesAmount"//油气总金额
    }
    ,{
      data : "createUser"//交账人
    }
    ,{
      data : "auditUser"//审核人
    }
    ,{
      data : "auditDate"//审核时间
    }
    ],
    columnDefs : [ {
      targets : [ 0 ],
      visible : false
    } ]
  });
  
  // 检索
  $("#btnDotAccountsConditionQuery").click(function(e) {
    e.preventDefault();
    // 刷新检索条件
    loadqueryCondition();
    // 刷新数据
    tblDotAccountability.reload();
  });

  function loadqueryCondition(){
    queryCondition.projectId = $("#frmDotAccountsQuery [name=projectId]").val();
    queryCondition.projectCode = $("#frmDotAccountsQuery [name=projectCode]").val();
    queryCondition.sendCargoCompanyId = $("#frmDotAccountsQuery [name=sendCargoCompanyId]").val();
    queryCondition.receiveCargoCompanyId = $("#frmDotAccountsQuery [name=receiveCargoCompanyId]").val();
    queryCondition.branchGroupName = $("#frmDotAccountsQuery [name=branchGroupName]").val();
    queryCondition.beginDateCheck = $("#frmDotAccountsQuery [name=beginDate]").val();
    queryCondition.endDateCheck = $("#frmDotAccountsQuery [name=endDate]").val();
    return queryCondition;
  }
  var sendCargoCompanyId = $("#frmDotAccountsQuery [name=sendCargoCompanyId]").FdSelect2({type : 'customCompany'});
  var receiveCargoCompanyId = $("#frmDotAccountsQuery [name=receiveCargoCompanyId]").FdSelect2({type : 'customCompany'});
  
  
  /** **指标表****** */
	// 指标表信息一览
	var tblPoint;

	// 获取指标表
	var getPointData = function(source, data, callback) {
		// 选中行
		var d = tblDotAccountability.getSelected();
		if(!d){
			callback({data:[], recordsTotal: 0, recordsFiltered: 0});
			return false;
		}
		queryCondition.dotAccountPackId=d["dotAccountPackId"];//赋值财务信息主键
		$.fd.DataTable.ajax("api/shortPacks", data, callback, queryCondition);
	};
	
	//页面初始化操作
	$("#btnDotAccountsConditionQuery").click();
	
	
	//交账明细财务审核
	$("#accountDetails").click(function() {
		 var dotAccountPackIds = tblDotAccountability.getSelections('dotAccountPackId');
		  if (dotAccountPackIds.length < 1) {
			  $.fd.msg.notice("请选择其中一条信息");
			  return false;
		  }
	  var status = tblDotAccountability.getSelections('status');
		for (var i in status) {
			  if(status[i] != 0) {
				  $.fd.msg.notice("只能选择财务待审核的信息");
			      return false;
			  }
		  }
		    
	    var ids = $.arrToSplit(dotAccountPackIds);
		var type = "put";
		var url = "api/dotAccountPack/financeAudit/"+ids;
			
		$.confirm("财务审核","你已选择N条数据，是否审核？",{
		    success:function(){
				$.fd.ajax({
					url : url,
					type : type,
					showMsg:true,
					success : function(d) {
						// 保存成功后，刷新数据
						tblDotAccountability.reload();
						tblPoint.reload();
					}
				});
		    }
	    });
		
	});
	
  });
}(jQuery));

$(function(){
	//点击查看底部
   $(".tableDivs").hide();
	$("body").on("click", "#panel1 tbody tr td", function() {
	$(".tableDivs").show();
		$(this).parent().toggleClass("bgclass").siblings("#panel1 tbody tr").removeClass("bgclass");
	});
	$(".hideDiv").click(function(){
	 $(".tableDivs").hide();
	});
	//查看更多
	$("#lookAll").click(function(){
		$("#lookModal").modal();
		$('#showMask', window.parent.parent.document).show();
	});

	 $("#btnDotAccountability").click(function(e) {
		    e.preventDefault();
		    window.open("dotAccountability.do", "contentIframe");  
	  });
})

