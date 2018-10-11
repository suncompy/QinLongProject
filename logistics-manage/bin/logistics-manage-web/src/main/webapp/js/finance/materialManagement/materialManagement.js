//# sourceURL=materialManagement.js
/**
 * @title 油气卡表
 * @author Jys
 * @date 2018-02-07
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

  //油气表信息一览
  var tblMaterialManagement = $("#tblMaterialManagement").FdDataTable({
    serverData : retrieveData,
    ajaxSource : "api/queryMaterial",
    columns : [
    	{
	      data : "id"//复选框
	    }
	    ,{
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
	      data : "materialName"//货物品名
	    }
	    ,{
	        data : "materialType"//规格型号
	      }
	    ,{
	      data : "materialNum"//购入数量
	    }
	    ,{
	      data : "materialUnit"//单位
	    }
	    ,{
	      data : "materialUnitPrice"//单价
	    }
	    ,{
	      data : "totalMoney"//总金额
	    }
	    ,{
	      data : "purchaseName"//采购人
	    }
	    ,{
	      data : "purchaseDate"//采购时间
	    }
	    ,{
	      data : "auditName"//审核人
	    }
	    ,{
	      data : "auditDate"//审核时间
	    }
	    ,{
	      data : "serialNumber"//流水号
	    }
    ],
    columnDefs : [ {
      targets : [ 0 ],
      visible : false
    } ]
  });

	// 点击进入采购登入页面
	$("#btnAddMaterialManagement").click(function(e) {
		e.preventDefault();
		 /* $.fd.ajax({
			  url : "api/queryBranchGroupName",
			  type : "get",
			  success : function(data) {
				  var selectStr = '';
				  $.each(data.data,function(index,branchGroup){
					  selectStr += '<option value="' + branchGroup.id + '">' + branchGroup.branchGroupName + '</option>';
				  })
				  // 填充下拉列表
				  $("#chooseProjectFormId [name=branchGroupId]").html(selectStr);
			  }
		  });*/
		  $.fd.ajax({
			  url : "api/queryCompanyAccount",
			  type : "get",
			  success : function(data) {
				  var selectStr = '';
				  $.each(data.data,function(index,companyAccount){
					  selectStr += '<option value="' + companyAccount.id + '">' + companyAccount.accountName + '</option>';
				  })
				  // 填充下拉列表
				  $("#chooseProjectFormId [name=companyAccount]").html(selectStr);
			  }
		  });
		  $("#addMaterialManagementModal").modal("show");
	});
	
	//光标离开后计算总金额
	$("[name=materialNum],[name=materialUnitPrice]").blur(function(e){
		e.preventDefault();
		var materialNum = $("#materialNum").val();
		var materialUnitPrice = $("#materialUnitPrice").val();
		var totalMoneyInt;
		if(materialNum !=null && materialNum != ""){
			if(materialUnitPrice !=null && materialUnitPrice != ""){
				var materialNumStr = parseInt(materialNum);
				var materialNumInt = Number(materialNumStr);
				var materialUnitPriceStr = parseInt(materialUnitPrice);
				var materialUnitPriceInt = Number(materialUnitPriceStr);
				totalMoneyInt = materialUnitPriceInt * materialNumInt;
				$("#totalMoney").val(totalMoneyInt)
			}/*else{
				$.fd.msg.notice("请输入单价");
				  return false;
			}*/
		}/*else{
			$.fd.msg.notice("请输入购买数量");
			  return false;
		}*/
	});
	
	// 物料登入
	$("#btnMaterialModel").click(function() {
		var branchGroupId = $("#branchGroupId").val();
		var salesUnit = $("#salesUnit").val();
		var materialName = $("#materialName").val();
		var materialType = $("#materialType").val();
		var materialUnit = $("#materialUnit").val();
		var materialNum = $("#materialNum").val();
		var materialUnitPrice = $("#materialUnitPrice").val();
		var totalMoney = $("#totalMoney").val();
		var accountId = $("#companyAccount").val();
		var type = "post";
		var url = "api/addMaterial";
		$.fd.ajax({
			url : url,
			type : type,
			//data : $('#chooseProjectFormId').serialize(),
			data : {branchGroupId:branchGroupId,salesUnit:salesUnit,materialName:materialName,materialType:materialType,materialUnit:materialUnit,materialNum:materialNum,materialUnitPrice:materialUnitPrice,totalMoney:totalMoney,accountId:accountId},
			dataType: "json",
			// showMsg:true,
			success : function(data) {
				// 保存成功后，刷新数据
				tblMaterialManagement.reload();
			}
		});
		$("#addMaterialManagementModal").modal("hide");
	});
  
	// 审核
	  $("#btnMaterialManagementAudit").click(function(e) {
		  e.preventDefault();
		  var idS = tblMaterialManagement.getSelections('id');
		  var auditStatus = tblMaterialManagement.getSelections('auditStatus');//状态
		  if (!idS || idS.length == 0) {
			  $.fd.msg.notice("请选择一条信息");
			  return false;
		  }
		  for (var i in auditStatus) {
			  if(auditStatus[i] == 1) {
				  $.fd.msg.notice("只能选择待审核或审核不通过的信息");
				  return false;
			  }
		  }
		  var passFlag;
		  $.confirm("财务审核","你已选择1条数据，是否审核通过？",{
		    success:function(){
		    	passFlag = "1";
		    	var type = "put";
				var url = "api/updateMaterialAuditStatus/" + passFlag + "/" + idS ;
				  $.fd.ajax({
					  url : url,
					  type : type,
					  //showMsg:true,
					  success : function(data) {
						  // 保存成功后，刷新数据
						  tblMaterialManagement.reload();
					  }
				  });
		    },
		    failure:function(){
		    	passFlag = "2";
		    	var type = "put";
				var url = "api/updateMaterialAuditStatus/" + passFlag + "/" + idS ;
				  $.fd.ajax({
					  url : url,
					  type : type,
					  //showMsg:true,
					  success : function(data) {
						  // 保存成功后，刷新数据
						  tblMaterialManagement.reload();
					  }
				  });
		    }
		  });
	  });
	  
	  // 反审核
	  $("#btnMaterialManagementAgainstAudit").click(function(e) {
		  e.preventDefault();
		  var idS = tblMaterialManagement.getSelections('id');
		  var auditStatus = tblMaterialManagement.getSelections('auditStatus');//状态
		  if (!idS || idS.length == 0) {
			  $.fd.msg.notice("请选择一条信息");
			  return false;
		  }
		  for (var i in auditStatus) {
			  if(auditStatus[i] == 0) {
				  $.fd.msg.notice("只能选择已审核信息");
				  return false;
			  }
		  }
		  $.confirm("反审核","你已选择1条数据，是否还原到未审核状态",{
			    success:function(){
			    var passFlag = "0";
			    var type = "put";
				var url = "api/updateMaterialAuditStatus/" + passFlag + "/" + idS ;
				$.fd.ajax({
					url : url,
					type : type,
					//showMsg:true,
					success : function(data) {
						// 保存成功后，刷新数据
						tblMaterialManagement.reload();
					}
				});
			   }
		    });
	  });
	  
	  // 检索
	  $("#btnMaterialManagementConditionQuery").click(function(e) {
	    e.preventDefault();
	    // 刷新检索条件
	    loadqueryCondition();
	    // 刷新数据
	    tblMaterialManagement.reload();
	  });

	  function loadqueryCondition(){
	    queryCondition.projectId = $("#frmMaterialManagementQuery [name=projectId]").val();
	    queryCondition.projectCode = $("#frmMaterialManagementQuery [name=projectCode]").val();
	    queryCondition.branchGroupName = $("#frmMaterialManagementQuery [name=branchGroupName]").val();
	    queryCondition.beginDate = $("#frmMaterialManagementQuery [name=beginDate]").val();
	    queryCondition.endDate = $("#frmMaterialManagementQuery [name=endDate]").val();
	    return queryCondition;
	  }
	  var branchGroupId = $("#chooseProjectFormId [name=branchGroupId]").FdSelect2({type : 'sysOrgPanel'});
  });
}(jQuery));
