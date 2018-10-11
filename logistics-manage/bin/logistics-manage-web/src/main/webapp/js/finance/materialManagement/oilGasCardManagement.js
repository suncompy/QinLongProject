//# sourceURL=oilGasCardManagement.js
/**
 * @title 油气卡表
 * @author Jys
 * @date 2018-01-26
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

  // 根据选中的短驳运单财务表,获得该指标表
  var reloadPoint = function() {
    // 指标表信息列表未创建时
	  if (!$.fn.DataTable.isDataTable("#tblPoint")) {
		tblPoint = $("#tblPoint").FdDataTable({
		serverData : getPointData,
		columns : [
			{
		      data : "id"//复选框
		    }
		    ,{
		      data : "cardNum"//卡号
		    }
		    ,{
		      data : "cardMoney"//金额
		    }
		    ,{
	    	  data : "cardStatus",//使用状态
	          render:function(data,type,full,meta) {
	        	  var returnVal = '';
	        	  switch (data) {
					case 0:
						returnVal = '未使用 ';
						break;
					case 1:
						returnVal = '已使用';
						break;
					default:
						break;
					}
	        	  return returnVal;
	          }
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

  //油气表信息一览
  var tblOilGasCard = $("#tblOilGasCard").FdDataTable({
    serverData : retrieveData,
    rowSelect: reloadPoint,
    displayLength : 20,
    ajaxSource : "api/queryOilGasCard",
    columns : [
    	{
	      data : "id"//复选框
	    }
	    ,{
		  data : "purchaseDate"//日期
		}
	    ,{
			data : "id"// 复选框
		},{
	      data : "branchGroupName"//分支机构
	    }
	    ,{
	      data : "auditStatus",//状态
	      render:function(data,type,full,meta) {
	    	  var returnVal = '';
	    	  switch (data) {
				case 0:
					returnVal = '待审核 ';
					break;
				case 1:
					returnVal = '审核通过';
					break;
				case 2:
					returnVal = '审核不通过';
					break;
				default:
					break;
				}
	    	  return returnVal;
	      }
	    }
	    ,{
	      data : "salesUnit"//销售单位
	    }
	    ,{
	      data : "cardType",//采购品类
	      render:function(data,type,full,meta) {
	    	  var returnVal = '';
	    	  switch (data) {
				case 0:
					returnVal = '油卡 ';
					break;
				case 1:
					returnVal = '气卡';
					break;
				default:
					break;
				}
	    	  return returnVal;
	      }
	    }
	    ,{
	      data : "cardCount"//购入数量
	    }
	    ,{
	      data : "cardUnit"//单位
	    }
	    ,{
	      data : "totalMoney"//总金额
	    }
	    ,{
	      data : "purchaseName"//采购人
	    }
	    /*,{
	      data : "purchaseDate"//采购时间
	    }*/
	    ,{
	      data : "auditName"//审核人
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

	// 点击进入采购登入页面
	$("#btnAddOilGasCard").click(function(e) {
		e.preventDefault();
		  $("#addOilGasCardModal").modal("show");
	});
	
	// 采购确定
	$("#btnOilGasCardModel").click(function() {
		var branchGroupId = $("#branchGroupId").val();
		var salesUnit = $("#salesUnit").val();
		var cardType = $("#cardType").val();
		var cardUnit = $("#cardUnit").val();
		var accountId = $("#companyAccount").val();
		if(!branchGroupId){
			  $.fd.msg.notice("分支机构不能为空!");
			  return false;
		  }
		if(!salesUnit){
			  $.fd.msg.notice("销售单位不能为空!");
			  return false;
		  }
		if(!cardType){
			  $.fd.msg.notice("采购品类不能为空!");
			  return false;
		  }
		if(!cardUnit){
			  $.fd.msg.notice("单位不能为空!");
			  return false;
		  }
		if(!accountId){
			  $.fd.msg.notice("账户不能为空!");
			  return false;
		  }
		var type = "post";
		var url = "api/addOilGasCard";
		$.fd.ajax({
			url : url,
			type : type,
			//data : $('#chooseProjectFormId').serialize(),
			data : {branchGroupId:branchGroupId,salesUnit:salesUnit,cardType:cardType,cardUnit:cardUnit,accountId:accountId},
			dataType: "json",
			// showMsg:true,
			success : function(data) {
				// 保存成功后，刷新数据
				tblOilGasCard.reload();
			}
		});
		$("#addOilGasCardModal").modal("hide");
	});
  
	//下载模板
	  $("#btnOilGasCardmdl").click(function(e){
	  	e.preventDefault();
	  	window.location.href = "model/xls/oilGasCardModel.xls";
	  });
	  
	  //导入
	  $("#btnOilGasCardImp").click(function(e){
		e.preventDefault();
		var auditStatus = tblOilGasCard.getSelections('auditStatus');//状态
		var id = tblOilGasCard.getSelections('id');
		for (var i in auditStatus) {
			  if(auditStatus[i] != 0) {
				  $.fd.msg.notice("只能选择待审核的信息");
				  return false;
			  }
		  }
		$.fd.FdFileupload({url:"api/OilGasCard/imp/"+id,
		    acceptFileTypes:["xls"],
	        maxFileSize:10,
			success:function(){
			$.fd.msg.notice("上传成功!");
			// 上传完毕后，刷新数据
			tblOilGasCard.reload();
			tblPoint.reload();
		}});
	  });
	
	// 审核
	  $("#btnOilGasCardAudit").click(function(e) {
		  e.preventDefault();
		  var id = tblOilGasCard.getSelections('id');
		  var totalMoney = tblOilGasCard.getSelections('totalMoney');
		  var auditStatus = tblOilGasCard.getSelections('auditStatus');//状态
		  if (!id || id.length == 0) {
			  $.fd.msg.notice("请选择一条信息");
			  return false;
		  }
		  for (var i in auditStatus) {
			  if(auditStatus[i] == 1) {
				  $.fd.msg.notice("只能选择待审核或审核不通过的信息");
				  return false;
			  }
		  }
		  for (var i in totalMoney) {
			  if(totalMoney[i] == 0) {
				  $.fd.msg.notice("请先导入油气卡号");
				  return false;
			  }
		  }
		  var passFlag;
		  $.confirm("财务审核","你已选择1条数据，是否审核通过？",{
		    success:function(){
		    	passFlag = "1";
		    	var type = "put";
				var url = "api/updateOilGasCardAuditStatus/" + passFlag + "/" + id ;
				  $.fd.ajax({
					  url : url,
					  type : type,
					  //showMsg:true,
					  success : function(data) {
						  // 保存成功后，刷新数据
						  tblOilGasCard.reload();
					  }
				  });
		    },
		    failure:function(){
		    	passFlag = "2";
		    	var type = "put";
		    	var url = "api/updateOilGasCardAuditStatus/" + passFlag + "/" + id ;
				  $.fd.ajax({
					  url : url,
					  type : type,
					  //showMsg:true,
					  success : function(data) {
						  // 保存成功后，刷新数据
						  tblOilGasCard.reload();
					  }
				  });
		    }
		  });
	  });
	  
	  // 反审核
	  $("#btnOilGasCardAgainstAudit").click(function(e) {
		  e.preventDefault();
		  var id = tblOilGasCard.getSelections('id');
		  var totalMoney = tblOilGasCard.getSelections('totalMoney');
		  var auditStatus = tblOilGasCard.getSelections('auditStatus');//状态
		  if (!id || id.length == 0) {
			  $.fd.msg.notice("请选择一条信息");
			  return false;
		  }
		  for (var i in auditStatus) {
			  if(auditStatus[i] != 1) {
				  $.fd.msg.notice("只能选择审核通过的信息");
				  return false;
			  }
		  }
		  for (var i in totalMoney) {
			  if(totalMoney[i] == 1) {
				  $.fd.msg.notice("请先导入油气卡号");
				  return false;
			  }
		  }
		  $.confirm("反审核","你已选择1条数据，是否还原到未审核状态",{
			    success:function(){
			    var passFlag = "0";
			    var type = "put";
			    var url = "api/updateOilGasCardAuditStatus/" + passFlag + "/" + id ;
				$.fd.ajax({
					url : url,
					type : type,
					//showMsg:true,
					success : function(data) {
						// 保存成功后，刷新数据
						tblOilGasCard.reload();
					}
				});
			   }
		    });
	  });
	  
	var tblPoint;
	//获取指标表
	var getPointData = function(source, data, callback) {
		// 选中行
		var d = tblOilGasCard.getSelected();
		if(!d){
			callback({data:[], recordsTotal: 0, recordsFiltered: 0});
			return false;
		}
		queryCondition.id=d["id"];
		$.fd.DataTable.ajax("api/queryOilGasCardByDate", data, callback, queryCondition);
		delete queryCondition.id;
	};

	  // 检索
	  $("#btnOilGasCardConditionQuery").click(function(e) {
	    e.preventDefault();
	    // 刷新检索条件
	    loadqueryCondition();
	    // 刷新数据
	    tblOilGasCard.reload();
	  });

	  function loadqueryCondition(){
	    queryCondition.projectId = $("#frmOilGasCardQuery [name=projectId]").val();
	    queryCondition.projectCode = $("#frmOilGasCardQuery [name=projectCode]").val();
	    queryCondition.branchGroupName = $("#frmOilGasCardQuery [name=branchGroupName]").val();
	    queryCondition.beginDate = $("#frmOilGasCardQuery [name=beginDate]").val();
	    queryCondition.endDate = $("#frmOilGasCardQuery [name=endDate]").val();
	    return queryCondition;
	  }
	 var branchGroupId = $("#chooseProjectFormId [name=branchGroupId]").FdSelect2({type : 'sysOrgPanel'});
	 var provideCompanyId = $("#chooseProjectFormId [name=companyAccount]").FdSelect2({type : 'provideCompanyId'});
  });
}(jQuery));
