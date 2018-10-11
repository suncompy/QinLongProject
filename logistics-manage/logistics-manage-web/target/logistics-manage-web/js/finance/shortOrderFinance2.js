//# sourceURL=shortOrderFinance.js
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
  var queryData = {};
  queryData.start = 0;
  queryData.length = 30;

  // 获取短驳运单财务表信息
  var retrieveData = function(source, data, callback) {
    $.fd.DataTable.ajax(source, data, callback, queryData);
  };

  // 根据选中的短驳运单财务表,获得该指标表
  var reloadPoint = function() {
    // 指标表信息列表未创建时
    if (!$.fn.DataTable.isDataTable("#tblPoint")) {
	tblPoint = $("#tblPoint").FdDataTable({
		serverData : getPointData,
		columns : [
		    {
		      data : "id"
		    }
		    ,{
		      data : "pointName"
		    }
		    ,{
		      data : "pointMin"
		    }
		    ,{
		      data : "pointMax"
		    }
		    ,{
		      data : "type"
		    }
		    ,{
		      data : "cargoId"
		    }
		    ]
        });
    }else{
        tblPoint.reload();
    }
  };

  // 短驳运单财务表信息一览
  var tblShortOrderFinance = $("#tblShortOrderFinance").FdDataTable({
    serverData : retrieveData,
    //rowSelect: reloadPoint,
    scrollX:"2200px",
    ajaxSource : "api/shortOrderFinances",
    columns : [
	{
      data : "projectId"
    }
    ,{
      data : "orderId"
    }
    ,{
      data : "financeStatus"
    }
    ,{
      data : "arrearsItem"
    }
    ,{
      data : "addUserId"
    }
    ,{
      data : "addDate"
    }
    ,{
      data : "packFlag"
    }
    ,{
      data : "deleteFlag"
    }
    ,{
      data : "shortBargeCost"
    }
    ,{
      data : "buckleFigure"
    }
    ,{
      data : "subsidy"
    }
    ,{
      data : "shouldPayFigure"
    }
    ,{
      data : "billingDate"
    }
    ,{
      data : "financeAuditDate"
    }
    ,{
      data : "completeDate"
    }
    ,{
      data : "unusualDate"
    }
    ,{
      data : "unusualUserId"
    }
    ,{
      data : "unusualReason"
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
    ]
  	,
    columnDefs : [ {
      targets : [ 0 ],
      visible : false
    } ]
  });

  // 表单校验
  $("#frmShortOrderFinance").bootstrapValidator({
    excluded: [':disabled'],
    fields : {
      
    }
  }).on("success.form.bv", function(e) {
    // 表单校验通过后，保存处理
    e.preventDefault();

    var id = $("#frmShortOrderFinance [name=shOrderFinId]").val();
    var type = id ? "put" : "post";
    var url = "api/shortOrderFinance";

    var shortOrderFinance = $("#frmShortOrderFinance").serializeJson();

    $.fd.ajax({
      url : url,
      type : type,
      data : shortOrderFinance,
      showMsg:true,
      success : function(d) {
        $("#mdlShortOrderFinance").modal("hide");
        // 保存成功后，刷新数据
        tblShortOrderFinance.reload();
        $("#frmShortOrderFinance").loadJson(d.data);
      }
    });
  });

  //$("#mdlShortOrderFinance").fdDraggable();

  // 保存
  $("#btnShortOrderFinanceSave").click(function(e) {
    e.preventDefault();
    $("#frmShortOrderFinance").bootstrapValidator("validate");
  });

  // 检索
  $("#btnShortOrderFinanceQuery").click(function(e) {
    e.preventDefault();
    // 刷新检索条件
    loadqueryCondition();
    queryData.condition=queryCondition;
    // 刷新数据
    tblShortOrderFinance.reload();
  });

  function loadqueryCondition(){
    queryCondition.projectId = $("#frmShortOrderFinanceQuery [name=projectId]").val();
    return queryCondition;
  }



  //根据isEdit，判断页面表单是否可编辑
  var isEdited = function(isEdit){
    if(isEdit){
	$("#btnShortOrderFinanceSave").show();
	$("#frmShortOrderFinance input").removeAttr("disabled");
	$("#frmShortOrderFinance select").removeAttr("disabled");
	$("#frmShortOrderFinance textarea").removeAttr("disabled");
    }else{
	$("#btnShortOrderFinanceSave").hide();
	$("#frmShortOrderFinance input").attr("disabled", "disabled");
	$("#frmShortOrderFinance select").attr("disabled", "disabled");
	$("#frmShortOrderFinance textarea").attr("disabled", "disabled");
    }
  };

  // 短驳运单财务表新增
  $("#btnShortOrderFinanceAdd").click(function(e) {
    e.preventDefault();
    // 重置表单内容
    $("#frmShortOrderFinance").reset();
    isEdited(true);
    
    // 根据选中的短驳运单财务表,获得该指标表
    reloadPoint();
    $("#mdlShortOrderFinance").modal("show");
  });
   
  //编辑查看
  var editShortOrderFinance = function(isEdit) {
    var data = tblShortOrderFinance.getSelected();
    if (!data) {
      alter("请选择短驳运单财务表信息,且只能选择一条记录");
      return false;
    }
    // 重置表单内容
    $("#frmShortOrderFinance").reset();
    $("#frmShortOrderFinance").loadJson(data);
    // 编辑状态 or只读状态
    isEdited(isEdit);

    $("#mdlShortOrderFinance").modal("show");
   };
  
  // 短驳运单财务表查看
  $("#btnShortOrderFinanceView").click(function(e) {
    e.preventDefault();
    editShortOrderFinance(false);
  });
  
  // 短驳运单财务表修改
  $("#btnShortOrderFinanceEdit").click(function(e) {
    e.preventDefault();
    editShortOrderFinance(true);
  });

  // 短驳运单财务表删除
  $("#btnShortOrderFinanceDel").click(function(e) {
    e.preventDefault();
    var data = tblShortOrderFinance.getSelected();
    //msg,url,type,data,showMsg,sucess
    $.confirm({message: "确认要删除选择的数据吗？"},{
    	url:"api/shortOrderFinances/"+data.shOrderFinId,
    	type:"delete",
    	data:{},
    	showMsg:true,
    	sucess:function(d){
    		// 删除成功后，刷新数据
    		tblShortOrderFinance.reload();
    	}
    })
  
   // var datas = tblShortOrderFinance.getSelections('id');

  });


  	/** **指标表****** */
	// 指标表信息一览
	var tblPoint;

	// 获取指标表
	var getPointData = function(source, data, callback) {
		// 选中行
		var d = tblShortOrderFinance.getSelected();
		if(!d){
			callback({data:[], recordsTotal: 0, recordsFiltered: 0});
			return false;
		}
		$.fd.DataTable("api/shortOrderFinances/"+ d["shOrderFinId"] + "/points", data, callback, queryCondition);
	};
	
	//$("#mdlPoint").fdDraggable();

	// 指标表新增
	$("#btnPointAdd").click(function(e) {
	    e.preventDefault();
		
		if (!tblShortOrderFinance || !tblShortOrderFinance.getSelected()) {
			alter("请选择短驳运单财务表信息,且只能选择一条记录");
			return false;
		}
		
		// 重置表单内容
		$("#frmPoint").reset();
		$("#mdlPoint").modal("show");
	});

	// 指标表修改
	$("#btnPointEdit").click(function(e) {
                e.preventDefault();		
		var datas = tblPoint.getSelections('shOrderFinId');
		if (!datas || datas.length != 1) {
			alter("请选择要修改的指标表,且只能选择一条记录");
			return false;
		}

		var data = tblPoint.getSelected();
		// 重置表单内容
		$("#frmPoint").reset();
		$("#frmPoint").loadJson(data);
		$("#mdlPoint").modal("show");
	});

	// 指标表删除
	$("#btnPointDel").click(function(e) {
                e.preventDefault();
		var datas = tblPoint.getSelections('id');


	});
	
	// 指标表表单校验
	$("#frmPoint").bootstrapValidator({
		excluded: [':disabled'],
		fields : {
		}
	}).on("success.form.bv",function(e) {
		// 表单校验通过后，保存处理
		e.preventDefault();

		var id = $("#frmPoint [name='id']").val();
		
		var projectId = id ? $("#frmPoint [name='projectId']").val()
				:tblShortOrderFinance.getSelected()["id"];
		
		// 根据从表id是否存在,判断是新增还是修改
		var type = id ? "put" : "post";
		
		var url = "api/shortOrderFinance/point";

		var point = $("#frmPoint").serializeJson();
		point.projectId = projectId;

		$.fd.ajax({
			url : url,
			type : type,
			data : point,
			showMsg:true,
			success : function(data) {
				$("#mdlPoint").modal("hide");
				// 保存成功后，刷新数据
				tblPoint.reload();
			}
		});
	});

	// 指标表保存
	$("#btnPointSave").click(function(e) {
	        e.preventDefault();
		$("#frmPoint").bootstrapValidator("validate");
	});
  });
}(jQuery));
