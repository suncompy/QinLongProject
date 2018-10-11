//# sourceURL=projectCheck.js
/**
 * @title 短驳运单财务表
 * @description 短驳运单财务表
 * @author 123
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
  var tblProjectCheck = $("#tblProjectCheck").FdDataTable({
    serverData : retrieveData,
    ajaxSource : "api/queryProjectCheck2",
    columns : [
    {
      data : "id"//复选框
    }
    ,{
      data : "projectCode"//项目编号
    }
    ,{
	  data : "projectType",//项目类型
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
  	  data : "transportType",//联运模式
  	render:function(data,type,full,meta) {
  	  var returnVal = '';
  	  switch (data) {
			case 0:
				returnVal = '汽运';
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
			case 4:
				returnVal = '接取+火运';
				break;
			case 5:
				returnVal = '火运+送达';
				break;
			case 6:
				returnVal = '联运';
				break;
			case 7:
				returnVal = '接取+送达';
				break;
			default:
				break;
			}
  	  return returnVal;
    }
  	}
    ,{
      data : "branchGroupName"//分支机构
    }
    ,{
      data : "sendCargoCompanyName"//发货企业
    }
    ,{
      data : "receiveCargoCompanyName"//收货企业
    }
    ,{
      data : "cargoName"//货物品名
    }
    ,{
	    data : "receiptCashAmount"//应付现金
	  }
	  ,{
	    data : "receiptSuppliesAmount"//应付油气
	  }
    ,{
	    data : "ccontainerReceiptNet"//汽运到货吨位
	  }
	  ,{
	  	data : "cpickUpMoney"//汽运费用
	  }
	  ,{
	    data : "cdeductionMoney"//汽运扣损金额
	  }
    ,{
      data : "containerReceiptNet"//接取到货吨位
    }
    ,{
    	data : "pickUpMoney"//接取费用
    }
    ,{
      data : "deductionMoney"//接取扣损金额
    }
    ,{
      data : "entruckWeight"//火运发送吨位
    }
    ,{
        data : "trainProduceMoney"//火运费用
      }
    ,{
        data : "entruckArriveWeight"//损耗吨位
      }
    ,{
        data : "sendContainerReceiptNet"//送达到货吨位
      }
    ,{
        data : "sendUpMoney"//送达费用
      }
    ,{
        data : "sendDeductionMoney"//扣损金额
      }
    ],
    columnDefs : [ {
      targets : [ 0 ],
      visible : false
    } ]
  });
  
//跳转到项目核查
  $("#projectCheck2").click(function(e) {
	    e.preventDefault();
	    window.open("projectCheck2.do", "contentIframe");  
 });
	
  // 检索
  $("#btnProjectCheck2ConditionQuery").click(function(e) {
    e.preventDefault();
    // 刷新检索条件
    loadqueryCondition();
    // 刷新数据
    tblProjectCheck.reload();
  });
  
//导出
  $("#btnProjectCheckExport").click(function(e) {
	    e.preventDefault();
	    var conditionObj = objToUrl(loadqueryCondition());
	    window.location.href = "/logistics-manage-web/api/queryProjectCheck1/export"+conditionObj;
	  });

  function loadqueryCondition(){
    queryCondition.projectCode = $("#frmProjectCheck2Query [name=projectCode]").val();
    queryCondition.beginSite = $("#frmProjectCheck2Query [name=beginSite]").val();
    queryCondition.endSite = $("#frmProjectCheck2Query [name=endSite]").val();
    queryCondition.branchGroupName = $("#frmProjectCheck2Query [name=branchGroupName]").val();
    /*queryCondition.beginDate = $("#frmProjectCheck2Query [name=beginDate]").val();
    queryCondition.endDate = $("#frmProjectCheck2Query [name=endDate]").val();*/
    return queryCondition;
  }
  
  var sendCargoCompanyId = $("#frmProjectCheck2Query [name=sendCargoCompanyId]").FdSelect2({type : 'customCompany'});
  var receiveCargoCompanyId = $("#frmProjectCheck2Query [name=receiveCargoCompanyId]").FdSelect2({type : 'customCompany'});
  
  });
}(jQuery));