//# sourceURL=dotAccountability.js
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
	  queryCondition.suppliesSettleStatus=1;
	  queryCondition.checkingStatus=1;
	  queryCondition.dotAccountPackId="null";
	data = $.fd.DataTable.loadPageOpts(page,data);
    $.fd.DataTable.ajax(source, data, callback, queryCondition);
  };

  // 短驳运单财务表信息一览
  var tblDotAccountability = $("#tblDotAccountability").FdDataTable({
    serverData : retrieveData,
    ajaxSource : "api/shortPacks",
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
  
  
//打包模态框
	$("#accountpacking").click(function(){
		  $("#packForm")[0].reset();
		  var shPackId = tblDotAccountability.getSelections('shPackId');
		  if (shPackId.length < 1) {
			  $.fd.msg.notice("请选择其中一条信息");
			  return false;
		  }  
		  var branchGroupNames = tblDotAccountability.getSelections('branchGroupName');
		  //alert(branchGroupNames);
		  //var arr=branchGroupNames.split(","); 
		  var branch = branchGroupNames[0];
		  for(var i=1;i<branchGroupNames.length;i++){
		    if(branchGroupNames[i] != branch){
		    	 $.fd.msg.notice("请选择相同网点分支的信息进行网点交账！");
				  return false;
		    }
		  }
		  
		  var shPackIds =  tblDotAccountability.getSelecteds(shPackId);
		  
		  autoPaddingProjectInfo(shPackIds);
		  
		  $("#accountpackingModal").modal();
	});
  
	//自动填充 项目信息
	function autoPaddingProjectInfo(shPackIds) {
		$("#accountpackingModal [name=shPackIds]").val(tblDotAccountability.getSelections('shPackId'));//
		$("#accountpackingModal [name=branchName]").val(shPackIds[0].branchGroupName);//分支机构
		
		var myDate = new Date().format("yyyy-MM-dd");;
		$("#accountpackingModal [name=nowDate]").val(myDate);//日期
		$("#accountpackingModal [name=packTruckNum]").val(shPackIds.length);//包内总单数
		
		var suppliesAmounts = tblDotAccountability.getSelections('suppliesAmount');
		
		var totalMoney = 0;
		
		for(var i=0;i<suppliesAmounts.length;i++){
		    var j = parseInt(suppliesAmounts[i]);
		    totalMoney += j;
		  }

		$("#accountpackingModal [name=suppliesAmount]").val(totalMoney);//包内总金额
		
		var branchGroupName = shPackIds[0].branchGroupName;
		
		//获取剩余油气卡
		var url = "api/getOilGasCardMoney.do";
		  $.fd.ajax({
			  url : url,
			  type : 'GET',
			  data :{branchGroupName:branchGroupName},
			  success : function(data) {
				 $("#accountpackingModal [name=surplusOilCard]").val(data.data.oilCardMoney);
				 $("#accountpackingModal [name=surplusGasCard]").val(data.data.gasCardMoney);
			  }
		  });
	}
	
	//提交打包信息
	$("#btnAddDotAccountPack").click(function(){
		var url = "api/addDotAccountPack.do";
		  $.fd.ajax({
			  url : url,
			  type : 'POST',
			  showMsg:true,
			  data : $("#packForm").serializeJson(),
			  success : function(data) {
				// 保存成功后，刷新数据
				tblDotAccountability.reload();
				$("#accountpackingModal").modal("hide");
			  }
		  });
	})
	
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
	
	$("#btnDotAccountabilityDetail").click(function(e) {
	    e.preventDefault();
	    window.open("dotAccountabilityDetail.do", "contentIframe");  
  });
})

Date.prototype.format = function(fmt) { 
     var o = { 
        "M+" : this.getMonth()+1,                 //月份 
        "d+" : this.getDate(),                    //日 
        "h+" : this.getHours(),                   //小时 
        "m+" : this.getMinutes(),                 //分 
        "s+" : this.getSeconds(),                 //秒 
        "q+" : Math.floor((this.getMonth()+3)/3), //季度 
        "S"  : this.getMilliseconds()             //毫秒 
    }; 
    if(/(y+)/.test(fmt)) {
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
    }
     for(var k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
             fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
         }
     }
    return fmt; 
} 
