//# sourceURL=custCheckCarDetail.js
/**
 * @title 短驳打包信息表
 * @description 短驳打包信息表
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

  // 获取短驳打包信息表信息
  var retrieveData = function(source, data, callback) {
	  queryCondition.packType = "0";
	  queryCondition.packFlag = $.getRequest("packFlag");
	  data = $.fd.DataTable.loadPageOpts(page,data);
    $.fd.DataTable.ajax(source, data, callback, queryCondition);
  };

  // 短驳打包信息表信息一览
  var tblShortPack = $("#tblShortPack").FdDataTable({
    serverData : retrieveData,
    rowSelect: reloadPoint,
    displayLength : 20,
    ajaxSource : "api/customerPacks",
    columns : [
    {
      data : "custPackId"
    }
    ,{
      data : "projectCode"
    }
    ,{
      data : "branchGroupName"
    }
    ,{
        data : "custPackId"
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
        data : "orderCount"
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
    	    data : "totalCheckMoney",
    	    render:function(data,type,full,meta) {
    	  	  var returnVal = full.produceMoney;
    	  	  return returnVal;
    	    }
     }
      ,{
          data : "checkAuditName"
        }
        ,{
      	     data : "checkAuditDate"
      	} 
      ,{
        data : "packer"
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
  $("#btnShortPackQuery").click(function(e) {
    e.preventDefault();
    // 刷新检索条件
    loadqueryCondition();
    // 刷新数据
    tblShortPack.reload();
  });

  function loadqueryCondition(){
    queryCondition.projectId = $("#frmShortPackQuery [name=projectId]").val();
    return queryCondition;
  }
  function reloadPoint(){
	  var data = tblShortPack.getSelected();
	  //data.transportType = "0";
	  var transportType = data.transportType;
	  var projectId = data.projectId;
	  var custPackId = data.custPackId;
	  var src = "";
	  var param = "?transportType=1"+"&projectId="+projectId+"&custCheckConId="+data.custCheckConId;

	  src = "custCheckCarPack.do"+param+"&orderType=0&custPackId="+custPackId;
	  
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
//解包
  $("#btnCustCheckDetailUnpack").click(function(e) {
	    e.preventDefault();
	    var shPackIds = tblShortPack.getSelections('custPackId');
	    if (!shPackIds || shPackIds.length ==0) {
	      $.fd.msg.notice("请至少选择一条信息");
	      return false;
	    }
	    var financeStatuss = tblShortPack.getSelections('checkingStatus');
		  for (var i in financeStatuss) {
			  if(financeStatuss[i] == 1) {
				  $.fd.msg.notice("只能选择待审核或审核不通过的信息");
			      return false;
			  }
		  }
	    var ids = $.arrToSplit(shPackIds);
		var type = "put";
		var url = "api/customerPack/dissolve/"+ids;
		
		$.confirm("解包","是否还原到未打包状态",{
		    success:function(){
				$.fd.ajax({
					url : url,
					type : type,
					showMsg:true,
					success : function(d) {
						// 保存成功后，刷新数据
						tblShortPack.reload();
						//tblPoint.reload();
						$(" [name=custCheckFrame]").attr('src',$(" [name=custCheckFrame]").attr('src'));
					}
				});
		    }
	    });
		
   });
  //财务审核 passFlag0通过1不通过
  $("#btnCustCheckDetailAproveFinance").click(function(e) {
	    e.preventDefault();
	    var shPackIds = tblShortPack.getSelections('custPackId');
	    if (!shPackIds || shPackIds.length ==0) {
	      $.fd.msg.notice("请至少选择一条信息");
	      return false;
	    }
	    var financeStatuss = tblShortPack.getSelections('checkingStatus');
		  for (var i in financeStatuss) {
			  if(financeStatuss[i] != 0 && financeStatuss[i] != 2) {
				  $.fd.msg.notice("只能选择待审核信息");
				  return false;
			  }
		  }
	    var ids = $.arrToSplit(shPackIds);
	    
	    $.confirm("财务审核","是否审核？",{
		    success:function(){
		    	doAuditFinance(ids,"0");
		    },
		    failure:function(){
		    	doAuditFinance(ids,"1");
		    }
		});
	    

  });
  function doAuditFinance(ids,passFlag){
	    var type = "put";
		var url = "api/customerPack/initialFinanceAudit/"+ids+"/"+passFlag;
		
		$.fd.ajax({
			url : url,
			type : type,
			showMsg:true,
			success : function(d) {
				// 保存成功后，刷新数据
				tblShortPack.reload();
				//tblPoint.reload();
				$(" [name=custCheckFrame]").attr('src',$(" [name=custCheckFrame]").attr('src'));
			}
		});
  }
  //反审核
  $("#btnCustCheckDetailDeaproveFinance").click(function(e) {
	    e.preventDefault();
	    var shPackIds = tblShortPack.getSelections('custPackId');
	    if (!shPackIds || shPackIds.length ==0) {
	      $.fd.msg.notice("请至少选择一条信息");
	      return false;
	    }
	    var ids = $.arrToSplit(shPackIds);
		var type = "put";
		var url = "api/customerPack/initialAgainstAudit/"+ids;
		
		$.confirm("反审核","是否还原到未审核状态",{
		    success:function(){
				$.fd.ajax({
					url : url,
					type : type,
					showMsg:true,
					success : function(d) {
						// 保存成功后，刷新数据
						tblShortPack.reload();
						//tblPoint.reload();
						$(" [name=custCheckFrame]").attr('src',$(" [name=custCheckFrame]").attr('src'));
					}
				});
		    }
	    });
		
   });
  $("#btnCustCheckCar").click(function(e) {
	    e.preventDefault();
	    window.open("custCheckCarDetail.do?transportType="+getTransportType(), "contentIframe");  
});  
  $("#btnCustCheckRecive").click(function(e) {
	    e.preventDefault();
	    window.open("custCheckReciveDetail.do?transportType="+getTransportType(), "contentIframe");  
});
$("#btnCustCheck").click(function(e) {
	    e.preventDefault();
	    window.open("customerCheckingConf.do", "contentIframe");  
});
$("#btnCustCheckSend").click(function(e) {
	    e.preventDefault();
	    window.open("custCheckSendDetail.do?transportType="+getTransportType(), "contentIframe");  
});
$("#btnCustCheckTrain").click(function(e) {
	    e.preventDefault();
	    window.open("custCheckTrainDetail.do?transportType="+getTransportType(), "contentIframe");  
});
function initPage(){
  var transportType=$.getRequest("transportType");
  if(transportType == "0"){         //汽运
	  hideButton();
	  $("#btnCustCheckCar").show();
  }else if(transportType == "1"){   //接取
	  hideButton();
	  $("#btnCustCheckRecive").show();
  }else if(transportType == "2"){   //送达
	  hideButton();
	  $("#btnCustCheckSend").show();
  }else if(transportType == "3"){   //火运
	  hideButton();
	  $("#btnCustCheckTrain").show();
  }else if(transportType == "4"){   //接取+火运
	  hideButton();
	  $("#btnCustCheckRecive").show();
	  $("#btnCustCheckTrain").show();
  }else if(transportType == "5"){   //火运+送达
	  hideButton();
	  $("#btnCustCheckTrain").show();
	  $("#btnCustCheckSend").show();
  }else if(transportType == "6"){   //联运（接取火运送达）
	  showButton();
	  $("#btnCustCheckCar").hide();
  }else if(transportType == "7"){   //接取+送达
	  hideButton();
	  $("#btnCustCheckRecive").show();
	  $("#btnCustCheckSend").show();
  }
}
function getTransportType(){
	  return $.getRequest("transportType");
}

  //initPage();

  });
}(jQuery));
