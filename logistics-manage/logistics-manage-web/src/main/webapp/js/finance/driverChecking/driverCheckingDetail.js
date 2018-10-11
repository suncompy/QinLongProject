//# sourceURL=driverCheckingDetail.js
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
		    	  if(!full.containerOneReceiptNet){
		    		 return '-'; 
		    	  }
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
		    	data : "shortBargeCost"//单价
		    }
		    ,{
		    	data : "transitMoney"//运输费用
		    }
		    ,{
		    	data : "deductionWeight"//耗损
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
    displayLength : 20,
    rowSelect: reloadPoint,
    ajaxSource : "api/shortPacks",
    columns : [
    {
      data : "shPackId"//复选框
    }
    ,{
      data : "projectCode"
    }
    ,{
      data : "branchGroupName"
    }
    ,{
      data : "shPackId"
    }
    ,{
      data : "checkingStatus",
      render:function(data,type,full,meta) {
    	  var returnVal = '';
    	  switch (data) {
			case 0:
				returnVal = '待审核';
				break;
			case 1:
				returnVal = '审核通过';
				break;
			case 2:
				returnVal = '审核不通过';
				break;
			case 3:
				returnVal = '已解包';
				break;	
			default:
				break;
			}
    	  return returnVal;
      }
    }
    ,{
      data : "packTruckNum"
    }
    ,{
      data : "packTruckDegree"
    }
    ,{
      data : "freightChargeAmount"
    }
    ,{
      data : "paymentId",
      render:function(data,type,full,meta) {
    	  var returnVal = '';
    	  switch (data) {
			case 0:
				returnVal = '全额现金';
				break;
			case 1:
				returnVal = '全额油卡';
				break;
			case 2:
				returnVal = '全额气卡';
				break;
			case 3:
				returnVal = '钱卡混合';
				break;	
			default:
				break;
			}
    	  return returnVal;
      }
    }
    ,{
      data : "payRatio"
    }
    ,{
      data : "cashAmount"
    }
    ,{
      data : "receiveUserName"
    }
    ,{
      data : "cashPayType",
      render:function(data,type,full,meta) {
    	  var returnVal = '';
    	  switch (data) {
			case 0:
				returnVal = '现金';
				break;
			case 1:
				returnVal = '转账';
				break;
			case 2:
				returnVal = '支票';
				break;
			default:
				break;
			}
    	  return returnVal;
      }
    }
    ,{
      data : "openBank"
    }
    ,{
        data : "bankAccount"
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
        data : "suppliesAmount"
      }
      ,{
          data : "suppliesReceiveType",
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
          data : "createUserName"
        }
        ,{
          data : "checkingAuditorName"
        }
        ,{
            data : "checkingAuditDate"
          }
        ,{
            data : "remark"
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
	    var shPackIds = tblShortOrderFinance.getSelections('shPackId');
	    if (!shPackIds || shPackIds.length ==0) {
	      $.fd.msg.notice("请至少选择一条信息");
	      return false;
	    }
	    var ids = $.arrToSplit(shPackIds);
		var type = "put";
		var url = "api/shortPacks/dissolve/"+ids;
		
		$.confirm("解包","是否还原到未打包状态",{
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
	    var shPackIds = tblShortOrderFinance.getSelections('shPackId');
	    var statuss = tblShortOrderFinance.getSelections('checkingStatus');
	    if (!shPackIds || shPackIds.length ==0) {
	      $.fd.msg.notice("请至少选择一条信息");
	      return false;
	    }
	    for (var i in statuss) {
		  if(statuss[i] != 0 && statuss[i] != 2 ) {
			  $.fd.msg.notice("只能选择待审核或者审核不通过信息");
			  return false;
		  }
		}
	    var ids = $.arrToSplit(shPackIds);
		$.confirm("财务审核","是否审核？",{
		    success:function(){
		    	doDriveCheckFinance(ids,"0");
		    },
		    failure:function(){
		    	doDriveCheckFinance(ids,"1");
		    }
	 },{btnOk:"同意",btnCancel:"拒绝"});
 });
  
  function doDriveCheckFinance(ids,flag){
	  var type = "put";
		var url = "api/shortPacks/financeAudit/"+ids+"/"+flag;
		
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
	    var shPackIds = tblShortOrderFinance.getSelections('shPackId');
	    if (!shPackIds || shPackIds.length ==0) {
	      $.fd.msg.notice("请至少选择一条信息");
	      return false;
	    }
	    var ids = $.arrToSplit(shPackIds);
		var type = "put";
		var url = "api/shortPacks/againstAudit/"+ids;
		
		$.confirm("反审核","是否还原到未审核状态",{
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
	    window.open("driverCheckingShortOrderFinance.do", "contentIframe");  
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
		var conditionPoint = JSON.parse(JSON.stringify(queryCondition));
		conditionPoint.shPackId=d["shPackId"];//赋值财务信息主键
		$.fd.DataTable.ajax("api/shortOrderFinances", data, callback, conditionPoint);
	};
	
	//页面初始化操作
   $("#btnShortOrderFinanceQuery").click();
   
  });
}(jQuery));
