//# sourceURL=shPackOrder.js
/**
 * @title 短驳打包-运单中间表
 * @description 短驳打包-运单中间表
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

  // 获取短驳打包-运单中间表信息
  var retrieveData = function(source, data, callback) {
    $.fd.DataTable.ajax(source, data, callback, queryData);
  };


  // 短驳打包-运单中间表信息一览
  var tblShPackOrder = $("#tblShPackOrder").FdDataTable({
    serverData : retrieveData,
    ajaxSource : "api/shPackOrders",
    columns : [
    {
      data : "id"
    }
    ,{
      data : "shPackOrderId"
    }
    ,{
      data : "shPackId"
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
  $("#frmShPackOrder").bootstrapValidator({
    excluded: [':disabled'],
    fields : {
      shPackId : {
        validators : {
          notEmpty : {}
        }
      }
    }
  }).on("success.form.bv", function(e) {
    // 表单校验通过后，保存处理
    e.preventDefault();

    var id = $("#frmShPackOrder [name=id]").val();
    var type = id ? "put" : "post";
    var url = "api/shPackOrder";

    var shPackOrder = $("#frmShPackOrder").serializeJson();

    $.fd.ajax({
      url : url,
      type : type,
      data : shPackOrder,
      //showMsg:true,
      success : function(d) {
        $("#mdlShPackOrder").modal("hide");
        // 保存成功后，刷新数据
        tblShPackOrder.reload();
	$("#frmShPackOrder").loadJson(d.data);
      }
    });
  });

  //$("#mdlShPackOrder").fdDraggable();

  // 保存
  $("#btnShPackOrderSave").click(function(e) {
    e.preventDefault();
    $("#frmShPackOrder").bootstrapValidator("validate");
  });

  // 检索
  $("#btnShPackOrderQuery").click(function(e) {
    e.preventDefault();
    // 刷新检索条件
    loadqueryCondition();
    queryData.condition=queryCondition;
    // 刷新数据
    tblShPackOrder.reload();
  });

  function loadqueryCondition(){
    queryCondition.shPackId = $("#frmShPackOrderQuery [name=shPackId]").val();
    return queryCondition;
  }



  //根据isEdit，判断页面表单是否可编辑
  var isEdited = function(isEdit){
    if(isEdit){
	$("#btnShPackOrderSave").show();
	$("#frmShPackOrder input").removeAttr("disabled");
	$("#frmShPackOrder select").removeAttr("disabled");
	$("#frmShPackOrder textarea").removeAttr("disabled");
    }else{
	$("#btnShPackOrderSave").hide();
	$("#frmShPackOrder input").attr("disabled", "disabled");
	$("#frmShPackOrder select").attr("disabled", "disabled");
	$("#frmShPackOrder textarea").attr("disabled", "disabled");
    }
  };

  // 短驳打包-运单中间表新增
  $("#btnShPackOrderAdd").click(function(e) {
    e.preventDefault();
    // 重置表单内容
    $("#frmShPackOrder").reset();
    isEdited(true);
    
    $("#mdlShPackOrder").modal("show");
  });
   
  //编辑查看
  var editShPackOrder = function(isEdit) {
    var data = tblShPackOrder.getSelected();
    if (!data) {
      $.fd.msg.notice("请选择短驳打包-运单中间表信息,且只能选择一条记录");
      return false;
    }
    // 重置表单内容
    $("#frmShPackOrder").reset();
    $("#frmShPackOrder").loadJson(data);
    // 编辑状态 or只读状态
    isEdited(isEdit);

    $("#mdlShPackOrder").modal("show");
   };
  
  // 短驳打包-运单中间表查看
  $("#btnShPackOrderView").click(function(e) {
    e.preventDefault();
    editShPackOrder(false);
  });
  
  // 短驳打包-运单中间表修改
  $("#btnShPackOrderEdit").click(function(e) {
    e.preventDefault();
    editShPackOrder(true);
  });

  // 短驳打包-运单中间表删除
  $("#btnShPackOrderDel").click(function(e) {
    e.preventDefault();
    var datas = tblShPackOrder.getSelections('id');

    if (datas.length == 0) {
      $.fd.msg.notice("请选择要删除的短驳打包-运单中间表信息");
      return false;
    }
    $.confirm({
	content : "请确认删除选中短驳打包-运单中间表信息",
	confirm : function() {
	    var ajaxOpts = {
	        //showMsg:true,
		success : function(data) {
		    // 删除成功后，刷新数据
		    tblShPackOrder.reload();
		}
	    };
	    // 选择记录数大于1时，使用批量删除
	    if (datas.length == 1) {
		ajaxOpts.type = "delete";
		ajaxOpts.url = "api/shPackOrders/" + datas[0];
	    } else {
		ajaxOpts.type = "batchDel";
		ajaxOpts.url = "api/shPackOrders/batch";
		ajaxOpts.data = {ids : datas};
	    }
	    $.fd.ajax(ajaxOpts);
	}
    });
  });


  });
}(jQuery));
