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
  var queryData = {};
  queryData.start = 0;
  queryData.length = 30;

  // 获取客户对账设置表信息
  var retrieveData = function(source, data, callback) {
    $.fd.DataTable.ajax(source, data, callback, queryData);
  };


  // 客户对账设置表信息一览
  var tblCustomerCheckingConf = $("#tblCustomerCheckingConf").FdDataTable({
    serverData : retrieveData,
    ajaxSource : "api/customerCheckingConfs",
    columns : [
    {
      data : "id"
    }
    ,{
      data : "custCheckConId"
    }
    ,{
      data : "projectId"
    }
    ,{
      data : "startDate"
    }
    ,{
      data : "endDate"
    }
    ,{
      data : "checkingStatus"
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
  $("#frmCustomerCheckingConf").bootstrapValidator({
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

    var id = $("#frmCustomerCheckingConf [name=id]").val();
    var type = id ? "put" : "post";
    var url = "api/customerCheckingConf";

    var customerCheckingConf = $("#frmCustomerCheckingConf").serializeJson();

    $.fd.ajax({
      url : url,
      type : type,
      data : customerCheckingConf,
      //showMsg:true,
      success : function(d) {
        $("#mdlCustomerCheckingConf").modal("hide");
        // 保存成功后，刷新数据
        tblCustomerCheckingConf.reload();
	$("#frmCustomerCheckingConf").loadJson(d.data);
      }
    });
  });

  //$("#mdlCustomerCheckingConf").fdDraggable();

  // 保存
  $("#btnCustomerCheckingConfSave").click(function(e) {
    e.preventDefault();
    $("#frmCustomerCheckingConf").bootstrapValidator("validate");
  });

  // 检索
  $("#btnCustomerCheckingConfQuery").click(function(e) {
    e.preventDefault();
    // 刷新检索条件
    loadqueryCondition();
    queryData.condition=queryCondition;
    // 刷新数据
    tblCustomerCheckingConf.reload();
  });

  function loadqueryCondition(){
    queryCondition.projectId = $("#frmCustomerCheckingConfQuery [name=projectId]").val();
    return queryCondition;
  }



  //根据isEdit，判断页面表单是否可编辑
  var isEdited = function(isEdit){
    if(isEdit){
	$("#btnCustomerCheckingConfSave").show();
	$("#frmCustomerCheckingConf input").removeAttr("disabled");
	$("#frmCustomerCheckingConf select").removeAttr("disabled");
	$("#frmCustomerCheckingConf textarea").removeAttr("disabled");
    }else{
	$("#btnCustomerCheckingConfSave").hide();
	$("#frmCustomerCheckingConf input").attr("disabled", "disabled");
	$("#frmCustomerCheckingConf select").attr("disabled", "disabled");
	$("#frmCustomerCheckingConf textarea").attr("disabled", "disabled");
    }
  };

  // 客户对账设置表新增
  $("#btnCustomerCheckingConfAdd").click(function(e) {
    e.preventDefault();
    // 重置表单内容
    $("#frmCustomerCheckingConf").reset();
    isEdited(true);
    
    $("#mdlCustomerCheckingConf").modal("show");
  });
   
  //编辑查看
  var editCustomerCheckingConf = function(isEdit) {
    var data = tblCustomerCheckingConf.getSelected();
    if (!data) {
      $.fd.msg.notice("请选择客户对账设置表信息,且只能选择一条记录");
      return false;
    }
    // 重置表单内容
    $("#frmCustomerCheckingConf").reset();
    $("#frmCustomerCheckingConf").loadJson(data);
    // 编辑状态 or只读状态
    isEdited(isEdit);

    $("#mdlCustomerCheckingConf").modal("show");
   };
  
  // 客户对账设置表查看
  $("#btnCustomerCheckingConfView").click(function(e) {
    e.preventDefault();
    editCustomerCheckingConf(false);
  });
  
  // 客户对账设置表修改
  $("#btnCustomerCheckingConfEdit").click(function(e) {
    e.preventDefault();
    editCustomerCheckingConf(true);
  });

  // 客户对账设置表删除
  $("#btnCustomerCheckingConfDel").click(function(e) {
    e.preventDefault();
    var datas = tblCustomerCheckingConf.getSelections('id');

    if (datas.length == 0) {
      $.fd.msg.notice("请选择要删除的客户对账设置表信息");
      return false;
    }
    $.confirm({
	content : "请确认删除选中客户对账设置表信息",
	confirm : function() {
	    var ajaxOpts = {
	        //showMsg:true,
		success : function(data) {
		    // 删除成功后，刷新数据
		    tblCustomerCheckingConf.reload();
		}
	    };
	    // 选择记录数大于1时，使用批量删除
	    if (datas.length == 1) {
		ajaxOpts.type = "delete";
		ajaxOpts.url = "api/customerCheckingConfs/" + datas[0];
	    } else {
		ajaxOpts.type = "batchDel";
		ajaxOpts.url = "api/customerCheckingConfs/batch";
		ajaxOpts.data = {ids : datas};
	    }
	    $.fd.ajax(ajaxOpts);
	}
    });
  });


  });
}(jQuery));
