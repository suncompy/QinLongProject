//# sourceURL=shortPack.js
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
  var queryData = {};
  queryData.start = 0;
  queryData.length = 30;

  // 获取短驳打包信息表信息
  var retrieveData = function(source, data, callback) {
    $.fd.DataTable.ajax(source, data, callback, queryData);
  };


  // 短驳打包信息表信息一览
  var tblShortPack = $("#tblShortPack").FdDataTable({
    serverData : retrieveData,
    ajaxSource : "api/shortPacks",
    columns : [
    {
      data : "id"
    }
    ,{
      data : "shPackId"
    }
    ,{
      data : "projectId"
    }
    ,{
      data : "checkingStatus"
    }
    ,{
      data : "cashSettleStatus"
    }
    ,{
      data : "suppliesSettleStatus"
    }
    ,{
      data : "packTruckNum"
    }
    ,{
      data : "paymentId"
    }
    ,{
      data : "packTruckDegree"
    }
    ,{
      data : "payRatio"
    }
    ,{
      data : "cashPayType"
    }
    ,{
      data : "suppliesReceiveType"
    }
    ,{
      data : "receiveUserId"
    }
    ,{
      data : "openBank"
    }
    ,{
      data : "bankAccount"
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
  $("#frmShortPack").bootstrapValidator({
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

    var id = $("#frmShortPack [name=id]").val();
    var type = id ? "put" : "post";
    var url = "api/shortPack";

    var shortPack = $("#frmShortPack").serializeJson();

    $.fd.ajax({
      url : url,
      type : type,
      data : shortPack,
      //showMsg:true,
      success : function(d) {
        $("#mdlShortPack").modal("hide");
        // 保存成功后，刷新数据
        tblShortPack.reload();
	$("#frmShortPack").loadJson(d.data);
      }
    });
  });

  //$("#mdlShortPack").fdDraggable();

  // 保存
  $("#btnShortPackSave").click(function(e) {
    e.preventDefault();
    $("#frmShortPack").bootstrapValidator("validate");
  });

  // 检索
  $("#btnShortPackQuery").click(function(e) {
    e.preventDefault();
    // 刷新检索条件
    loadqueryCondition();
    queryData.condition=queryCondition;
    // 刷新数据
    tblShortPack.reload();
  });

  function loadqueryCondition(){
    queryCondition.projectId = $("#frmShortPackQuery [name=projectId]").val();
    return queryCondition;
  }



  //根据isEdit，判断页面表单是否可编辑
  var isEdited = function(isEdit){
    if(isEdit){
	$("#btnShortPackSave").show();
	$("#frmShortPack input").removeAttr("disabled");
	$("#frmShortPack select").removeAttr("disabled");
	$("#frmShortPack textarea").removeAttr("disabled");
    }else{
	$("#btnShortPackSave").hide();
	$("#frmShortPack input").attr("disabled", "disabled");
	$("#frmShortPack select").attr("disabled", "disabled");
	$("#frmShortPack textarea").attr("disabled", "disabled");
    }
  };

  // 短驳打包信息表新增
  $("#btnShortPackAdd").click(function(e) {
    e.preventDefault();
    // 重置表单内容
    $("#frmShortPack").reset();
    isEdited(true);
    
    $("#mdlShortPack").modal("show");
  });
   
  //编辑查看
  var editShortPack = function(isEdit) {
    var data = tblShortPack.getSelected();
    if (!data) {
      $.fd.msg.notice("请选择短驳打包信息表信息,且只能选择一条记录");
      return false;
    }
    // 重置表单内容
    $("#frmShortPack").reset();
    $("#frmShortPack").loadJson(data);
    // 编辑状态 or只读状态
    isEdited(isEdit);

    $("#mdlShortPack").modal("show");
   };
  
  // 短驳打包信息表查看
  $("#btnShortPackView").click(function(e) {
    e.preventDefault();
    editShortPack(false);
  });
  
  // 短驳打包信息表修改
  $("#btnShortPackEdit").click(function(e) {
    e.preventDefault();
    editShortPack(true);
  });

  // 短驳打包信息表删除
  $("#btnShortPackDel").click(function(e) {
    e.preventDefault();
    var datas = tblShortPack.getSelections('id');

    if (datas.length == 0) {
      $.fd.msg.notice("请选择要删除的短驳打包信息表信息");
      return false;
    }
    $.confirm({
	content : "请确认删除选中短驳打包信息表信息",
	confirm : function() {
	    var ajaxOpts = {
	        //showMsg:true,
		success : function(data) {
		    // 删除成功后，刷新数据
		    tblShortPack.reload();
		}
	    };
	    // 选择记录数大于1时，使用批量删除
	    if (datas.length == 1) {
		ajaxOpts.type = "delete";
		ajaxOpts.url = "api/shortPacks/" + datas[0];
	    } else {
		ajaxOpts.type = "batchDel";
		ajaxOpts.url = "api/shortPacks/batch";
		ajaxOpts.data = {ids : datas};
	    }
	    $.fd.ajax(ajaxOpts);
	}
    });
  });


  });
}(jQuery));
