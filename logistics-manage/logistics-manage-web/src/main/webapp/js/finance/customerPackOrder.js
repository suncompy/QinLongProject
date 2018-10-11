//# sourceURL=customerPackOrder.js
/**
 * @title 客户打包-运单中间表
 * @description 客户打包-运单中间表
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

  // 获取客户打包-运单中间表信息
  var retrieveData = function(source, data, callback) {
    $.fd.DataTable.ajax(source, data, callback, queryData);
  };


  // 客户打包-运单中间表信息一览
  var tblCustomerPackOrder = $("#tblCustomerPackOrder").FdDataTable({
    serverData : retrieveData,
    ajaxSource : "api/customerPackOrders",
    columns : [
    {
      data : "id"
    }
    ,{
      data : "custPackOrderId"
    }
    ,{
      data : "custPackId"
    }
    ,{
      data : "orderId"
    }
    ,{
      data : "deleteFlag"
    }
    ],
    columnDefs : [ {
      targets : [ 0 ],
      visible : false
    } ]
  });

  // 表单校验
  $("#frmCustomerPackOrder").bootstrapValidator({
    excluded: [':disabled'],
    fields : {
      custPackId : {
        validators : {
          notEmpty : {}
        }
      }
    }
  }).on("success.form.bv", function(e) {
    // 表单校验通过后，保存处理
    e.preventDefault();

    var id = $("#frmCustomerPackOrder [name=id]").val();
    var type = id ? "put" : "post";
    var url = "api/customerPackOrder";

    var customerPackOrder = $("#frmCustomerPackOrder").serializeJson();

    $.fd.ajax({
      url : url,
      type : type,
      data : customerPackOrder,
      success : function(d) {
        $("#mdlCustomerPackOrder").modal("hide");
        // 保存成功后，刷新数据
        tblCustomerPackOrder.reload();
	$("#frmCustomerPackOrder").loadJson(d.data);
      }
    });
  });

  //$("#mdlCustomerPackOrder").fdDraggable();

  // 保存
  $("#btnCustomerPackOrderSave").click(function(e) {
    e.preventDefault();
    $("#frmCustomerPackOrder").bootstrapValidator("validate");
  });

  // 检索
  $("#btnCustomerPackOrderQuery").click(function(e) {
    e.preventDefault();
    // 刷新检索条件
    loadqueryCondition();
    queryData.condition=queryCondition;
    // 刷新数据
    tblCustomerPackOrder.reload();
  });

  function loadqueryCondition(){
    queryCondition.custPackId = $("#frmCustomerPackOrderQuery [name=custPackId]").val();
    return queryCondition;
  }



  //根据isEdit，判断页面表单是否可编辑
  var isEdited = function(isEdit){
    if(isEdit){
	$("#btnCustomerPackOrderSave").show();
	$("#frmCustomerPackOrder input").removeAttr("disabled");
	$("#frmCustomerPackOrder select").removeAttr("disabled");
	$("#frmCustomerPackOrder textarea").removeAttr("disabled");
    }else{
	$("#btnCustomerPackOrderSave").hide();
	$("#frmCustomerPackOrder input").attr("disabled", "disabled");
	$("#frmCustomerPackOrder select").attr("disabled", "disabled");
	$("#frmCustomerPackOrder textarea").attr("disabled", "disabled");
    }
  };

  // 客户打包-运单中间表新增
  $("#btnCustomerPackOrderAdd").click(function(e) {
    e.preventDefault();
    // 重置表单内容
    $("#frmCustomerPackOrder").reset();
    isEdited(true);
    
    $("#mdlCustomerPackOrder").modal("show");
  });
   
  //编辑查看
  var editCustomerPackOrder = function(isEdit) {
    var data = tblCustomerPackOrder.getSelected();
    if (!data) {
      $.fd.msg.notice("请选择客户打包-运单中间表信息,且只能选择一条记录");
      return false;
    }
    // 重置表单内容
    $("#frmCustomerPackOrder").reset();
    $("#frmCustomerPackOrder").loadJson(data);
    // 编辑状态 or只读状态
    isEdited(isEdit);

    $("#mdlCustomerPackOrder").modal("show");
   };
  
  // 客户打包-运单中间表查看
  $("#btnCustomerPackOrderView").click(function(e) {
    e.preventDefault();
    editCustomerPackOrder(false);
  });
  
  // 客户打包-运单中间表修改
  $("#btnCustomerPackOrderEdit").click(function(e) {
    e.preventDefault();
    editCustomerPackOrder(true);
  });

  // 客户打包-运单中间表删除
  $("#btnCustomerPackOrderDel").click(function(e) {
    e.preventDefault();
    var datas = tblCustomerPackOrder.getSelections('id');

    if (datas.length == 0) {
      $.fd.msg.notice("请选择要删除的客户打包-运单中间表信息");
      return false;
    }
    $.confirm({
	content : "请确认删除选中客户打包-运单中间表信息",
	confirm : function() {
	    var ajaxOpts = {
	        showMsg:true,
		success : function(data) {
		    // 删除成功后，刷新数据
		    tblCustomerPackOrder.reload();
		}
	    };
	    // 选择记录数大于1时，使用批量删除
	    if (datas.length == 1) {
		ajaxOpts.type = "delete";
		ajaxOpts.url = "api/customerPackOrders/" + datas[0];
	    } else {
		ajaxOpts.type = "batchDel";
		ajaxOpts.url = "api/customerPackOrders/batch";
		ajaxOpts.data = {ids : datas};
	    }
	    $.fd.ajax(ajaxOpts);
	}
    });
  });


  });
}(jQuery));
