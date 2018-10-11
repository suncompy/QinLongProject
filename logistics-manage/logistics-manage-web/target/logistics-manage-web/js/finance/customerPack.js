//# sourceURL=customerPack.js
/**
 * @title 客户对账打包信息表
 * @description 客户对账打包信息表
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 */
;
(function($) {
  'use strict';	
  $(function() {
  // 检索条件
  var queryCondition = {};
  var queryData = {};
  queryData.start = 0;
  queryData.length = 30;

  // 获取客户对账打包信息表信息
  var retrieveData = function(source, data, callback) {
    $.fd.DataTable.ajax(source, data, callback, queryData);
  };


  // 客户对账打包信息表信息一览
  var tblCustomerPack = $("#tblCustomerPack").FdDataTable({
    serverData : retrieveData,
    ajaxSource : "api/customerPacks",
    columns : [
    {
      data : "id"
    }
    ,{
      data : "custPackId"
    }
    ,{
      data : "projectId"
    }
    ,{
      data : "packType"
    }
    ,{
      data : "checkingStatus"
    }
    ,{
      data : "settleAuditType"
    }
    ,{
      data : "packTruckNum"
    }
    ,{
      data : "packTruckDegree"
    }
    ,{
      data : "invoiceStatus"
    }
    ,{
      data : "remark"
    }
    ,{
      data : "deleteFlag"
    }
    ,{
      data : "createDate"
    }
    ,{
      data : "createUserId"
    }
    ,{
      data : "modifiyDate"
    }
    ],
    columnDefs : [ {
      targets : [ 0 ],
      visible : false
    } ]
  });

  // 表单校验
  $("#frmCustomerPack").bootstrapValidator({
    excluded: [':disabled'],
    fields : {
      projectId : {
        validators : {
          notEmpty : {}
        }
      }
    }
  }).on("success.form.bv", function(e) {
    // 表单校验通过后，保存处理
    e.preventDefault();

    var id = $("#frmCustomerPack [name=id]").val();
    var type = id ? "put" : "post";
    var url = "api/customerPack";

    var customerPack = $("#frmCustomerPack").serializeJson();

    $.fd.ajax({
      url : url,
      type : type,
      data : customerPack,
      //showMsg:true,
      success : function(d) {
        $("#mdlCustomerPack").modal("hide");
        // 保存成功后，刷新数据
        tblCustomerPack.reload();
	$("#frmCustomerPack").loadJson(d.data);
      }
    });
  });

  //$("#mdlCustomerPack").fdDraggable();

  // 保存
  $("#btnCustomerPackSave").click(function(e) {
    e.preventDefault();
    $("#frmCustomerPack").bootstrapValidator("validate");
  });

  // 检索
  $("#btnCustomerPackQuery").click(function(e) {
    e.preventDefault();
    // 刷新检索条件
    loadqueryCondition();
    queryData.condition=queryCondition;
    // 刷新数据
    tblCustomerPack.reload();
  });

  function loadqueryCondition(){
    queryCondition.projectId = $("#frmCustomerPackQuery [name=projectId]").val();
    return queryCondition;
  }



  //根据isEdit，判断页面表单是否可编辑
  var isEdited = function(isEdit){
    if(isEdit){
	$("#btnCustomerPackSave").show();
	$("#frmCustomerPack input").removeAttr("disabled");
	$("#frmCustomerPack select").removeAttr("disabled");
	$("#frmCustomerPack textarea").removeAttr("disabled");
    }else{
	$("#btnCustomerPackSave").hide();
	$("#frmCustomerPack input").attr("disabled", "disabled");
	$("#frmCustomerPack select").attr("disabled", "disabled");
	$("#frmCustomerPack textarea").attr("disabled", "disabled");
    }
  };

  // 客户对账打包信息表新增
  $("#btnCustomerPackAdd").click(function(e) {
    e.preventDefault();
    // 重置表单内容
    $("#frmCustomerPack").reset();
    isEdited(true);
    
    $("#mdlCustomerPack").modal("show");
  });
   
  //编辑查看
  var editCustomerPack = function(isEdit) {
    var data = tblCustomerPack.getSelected();
    if (!data) {
      $.fd.msg.notice("请选择客户对账打包信息表信息,且只能选择一条记录");
      return false;
    }
    // 重置表单内容
    $("#frmCustomerPack").reset();
    $("#frmCustomerPack").loadJson(data);
    // 编辑状态 or只读状态
    isEdited(isEdit);

    $("#mdlCustomerPack").modal("show");
   };
  
  // 客户对账打包信息表查看
  $("#btnCustomerPackView").click(function(e) {
    e.preventDefault();
    editCustomerPack(false);
  });
  
  // 客户对账打包信息表修改
  $("#btnCustomerPackEdit").click(function(e) {
    e.preventDefault();
    editCustomerPack(true);
  });

  // 客户对账打包信息表删除
  $("#btnCustomerPackDel").click(function(e) {
    e.preventDefault();
    var datas = tblCustomerPack.getSelections('id');

    if (datas.length == 0) {
      $.fd.msg.notice("请选择要删除的客户对账打包信息表信息");
      return false;
    }
    $.confirm({
	content : "请确认删除选中客户对账打包信息表信息",
	confirm : function() {
	    var ajaxOpts = {
	        //showMsg:true,
		success : function(data) {
		    // 删除成功后，刷新数据
		    tblCustomerPack.reload();
		}
	    };
	    // 选择记录数大于1时，使用批量删除
	    if (datas.length == 1) {
		ajaxOpts.type = "delete";
		ajaxOpts.url = "api/customerPacks/" + datas[0];
	    } else {
		ajaxOpts.type = "batchDel";
		ajaxOpts.url = "api/customerPacks/batch";
		ajaxOpts.data = {ids : datas};
	    }
	    $.fd.ajax(ajaxOpts);
	}
    });
  });


  });
}(jQuery));
