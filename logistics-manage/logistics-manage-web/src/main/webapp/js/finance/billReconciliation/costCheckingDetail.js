//# sourceURL=costCheckingDetail.js
/**
 * @title 费用对账财务表
 * @description 费用对账财务表
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
		      data : "shOrderFinId"//复选框
		    }
		    ,{
		      data : "orderCode"//运单编号
		    }
		   ,{
		      data : "carPlateNumber"//承运车辆
		    },{
		      data : "sendTare"//发货皮重
		    },{
		      data : "sendGross"//发货毛重
		    },{
		      data : "sendNet"//发货净重
		    }
		    ,{
		    	 data : "orderOrign",
		         render:function(data,type,full,meta) {//运单来源
		       	  var returnVal = '';
		       	  switch (data) {
		   			case 1:
		   				returnVal = 'PC端';
		   				break;
		   			case 2:
		   				returnVal = 'APP端';
		   				break;
		   			default:
		   				break;
		   			}
		       	  return returnVal;
		         }	
		    },
		    {
		        data : "createDate" //创建时间
		      }
		      ,{
		        data : "pickupPlace" //取货地
		      }
		      ,{
		        data : "takeDeliveryDate" //装货时间
		      }
		      ,{
		        data : "arrivePlace"  //运抵地
		      }
		      ,{
		        data : "receipterDate" //到货时间
		      }
		      ,{
		        data : "userDispatchName"  //调度员
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
    ajaxSource : "api/costPacks",
    columns :[
	    {
	      data : "costPackId"//复选框
	    }
	    ,{
	      data : "projectCode"//项目编号
	    }
	    ,{
	      data : "packTruckNum"//打包单数
	    },{
	      data : "branchGroupName"//分支机构
	    },{
	      data : "costPackCode"//对账单号
	    }
	    ,{
    	  data : "financeStatus",//财务状态
          render:function(data,type,full,meta) {
        	  var returnVal = '';
        	  switch (data) {
    			case 0:
    				returnVal = '待审核';
    				break;
    			case 1:
    				returnVal = '已审核';
    				break;
    			case 2:
    				returnVal = '审核不通过';
    				break;
    			default:
    				break;
    			}
        	  return returnVal;
          }
	    },{
	      data : "cargoName"//货物品名
	    },{
	      data : "cargoUnitPrice"//单价
	    },{
	      data : "cargoPrice"
	    },{
	      data : "sendNet"//总吨位
	    },{
	      data : "packTruckNum"//总车次
	    },{
	      data : "taxRate"
	    }
	    ,{
	    	data : "createUserName",//打包人
	    },
	    {
	    	data : "createDate"//打包时间
	    }
	    ,{
	    	data : "auditUserName",//审核人
	    }
	    ,{
	    	data : "checkingAuditDate"//审核时间
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
	    var costPackIds = tblShortOrderFinance.getSelections('costPackId');
	    if (!costPackIds || costPackIds.length ==0) {
	      $.fd.msg.notice("请至少选择一条信息");
	      return false;
	    }
	    var ids = $.arrToSplit(costPackIds);
		var type = "put";
		var url = "api/costPacks/dissolve/"+ids;
		
		$.confirm("解包","你已选择一条记录，是否还原到未打包状态",{
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
	    var costPackIds = tblShortOrderFinance.getSelections('costPackId');
	    var statuss = tblShortOrderFinance.getSelections('financeStatus');
	    if (!costPackIds || costPackIds.length ==0) {
	      $.fd.msg.notice("请至少选择一条信息");
	      return false;
	    }
	    for (var i in statuss) {
			  if(statuss[i] != 0 && statuss[i] != 2 ) {
				  $.fd.msg.notice("只能选择待审核或者审核不通过信息");
				  return false;
			  }
			}
	    var ids = $.arrToSplit(costPackIds);
	    $.confirm("财务审核","是否审核？",{
		    success:function(){
		    	doCostCheckFinance(ids,"0");
		    },
		    failure:function(){
		    	doCostCheckFinance(ids,"1");
		    }
	 },{btnOk:"同意",btnCancel:"拒绝"});
		
 });
  function doCostCheckFinance(ids,flag){
	  var type = "put";
		var url = "api/costPacks/financeAudit/"+ids+"/"+flag;
		
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
  
  //反审核
  $("#btnDriverCheckDetailDeaproveFinance").click(function(e) {
	    e.preventDefault();
	    var costPackIds = tblShortOrderFinance.getSelections('costPackId');
	    if (!costPackIds || costPackIds.length ==0) {
	      $.fd.msg.notice("请至少选择一条信息");
	      return false;
	    }
	    var ids = $.arrToSplit(costPackIds);
		var type = "put";
		var url = "api/costPacks/againstAudit/"+ids;
		
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
   
   $("#btnDriverCheckLink").click(function(e) {
	    e.preventDefault();
	    window.open("costReconciliation.do", "contentIframe");  
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
		queryCondition.tbCostPackId=d["costPackId"];//赋值财务信息主键
		$.fd.DataTable.ajax("api/costOrderFinances", data, callback, queryCondition);
	};
	
	//页面初始化操作
   $("#btnShortOrderFinanceQuery").click();
   
  });
}(jQuery));
