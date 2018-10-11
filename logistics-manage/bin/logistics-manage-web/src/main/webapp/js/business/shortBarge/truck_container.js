$(function() {
	//选项切换清空复选框
	$(".tabbable .nav li").click(function(){
		var type = $(this).attr('name');
		if(type=="type1"){
			$('#type').val('1');
		}else if(type=="type2"){
			$('#type').val('2');
		}else if(type=="type3"){
			$('#type').val('3');
		}
		$("input[type=checkbox]").prop("checked",false);
	});
	//新建运单模态框
/*	$("#waybill").click(function() {
		$("#addId")[0].reset();
		$('#myModal').modal();
		
	});*/
	/*//等待发运
	$("#forwarding").click(function() {	
		$("#sendId")[0].reset();
		$('#waitModal').modal();	
	});*/
	//等待回单
	/*$("#waitReceipt").click(function() {
		$("#receiptId")[0].reset();
		$('#receiptModal').modal();
	});*/
	if($("#ton").html()=="吨"){
		$(".piece").hide();
	}
	else{
		$(".piece").show();
	}
	/*//等待调度
	$("#dispatch").click(function() {
		$("#dispatchId")[0].reset();
		$('#dispatchModal').modal();
	});*/
	//等待调度驳回
	$("#checkboxagree").click(function() {
		$(".hideDiv").hide();
	});
	$("#checkboxDisagree").click(function() {
		$(".hideDiv").show();
	});
	//货物引导
	/*$("#guide").click(function() {
		$("#guideId")[0].reset();
		$('#guideModal').modal();
	});*/
	/*	//在途运载
	$("#carry").click(function() {
		$("#carryId")[0].reset();
		$('#carryModal').modal();
	});*/
	//运单删除
	$("#delBtn").click(function() {
		if($("input:checkbox[name='WaybillName']:checked").length > 1) {
			$("#deletRemindModal").modal();
		}
		else if($("input:checkbox[name='WaybillName']:checked").length == 0) {
			$("#deletnullModal").modal();
		}
		else {
			$("#deletReasonModal").modal();
		}
	});
	/*//运单查看
	$("body").on("click", "#panel1 tbody tr td:not(:nth-child(1)):not(:nth-child(5))", function() {
			$(this).parent().toggleClass("bgclass").siblings("#panel1 tbody tr").removeClass("bgclass");
		$("#lookModal").modal();
	});*/
	//异常运单删除
	$("#abnormalBtn").click(function() {
		if($("input:checkbox[name='abnormalName']:checked").length > 1) {
			$("#deletRemindModal").modal();
		}
		else if($("input:checkbox[name='abnormalName']:checked").length == 0) {
			$("#deletnullModal").modal();
		}
		else {
			$("#deletReasonModal").modal();
		}
	});
		//回收运单删除
	$("#recoveryBtn").click(function() {
		if($("input:checkbox[name='recoveryName']:checked").length > 1) {
			$("#deletRemindModal").modal();
		}
		else if($("input:checkbox[name='recoveryName']:checked").length == 0) {
			$("#deletnullModal").modal();
		}
		else {
			$("#deletReasonModal").modal();
		}
	});
	
//	导出模态框
 /*   $(".export").click(function(){ 
   var id=$(this).parent().parent().parent().attr("id");
    	if($("#"+ id +"  input[type=checkbox]:checked").length > 0) {
			$("#exportModal").modal();
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
    })*/
    
      // 导出
	  $("#export1").click(function(e) {
	    e.preventDefault();
	    var currentPage = $("#page_1 .current").html();
	    var condition = $('#orderSearchForm').serializeJSON();
	    var conditionObj = objToUrl(condition)+"&pageNo="+currentPage;
	    window.location.href = "/logistics-manage-web/business/short/boxManager/export1"+conditionObj;
	  });
	// 导出
	  $("#export2").click(function(e) {
	    e.preventDefault();
	    var currentPage = $("#page_2 .current").html();
	    var condition = $('#orderSearchForm').serializeJSON();
	    var conditionObj = objToUrl(condition)+"&pageNo="+currentPage;
	    window.location.href = "/logistics-manage-web/business/short/boxManager/export2"+conditionObj;
	  });
	  // 导出
	  $("#export3").click(function(e) {
	    e.preventDefault();
	    var currentPage = $("#page_3 .current").html();
	    var condition = $('#orderSearchForm').serializeJSON();
	    var conditionObj = objToUrl(condition)+"&pageNo="+currentPage;
	    window.location.href = "/logistics-manage-web/business/short/boxManager/export3"+conditionObj;
	  });
	// 导出
	  $("#export4").click(function(e) {
	    e.preventDefault();
	    var currentPage = $("#page_4 .current").html();
	    var condition = $('#orderSearchForm').serializeJSON();
	    var conditionObj = objToUrl(condition)+"&pageNo="+currentPage;
	    window.location.href = "/logistics-manage-web/business/short/boxManager/export4"+conditionObj;
	  });
    
     //	还原
    $(".rest").click(function(){   	
    	 var id=$(this).parent().parent().parent().attr("id");
    	if($("#"+ id +" input[type=checkbox]:checked").length > 0) {
    		var length = $("#"+ id +" input[type=checkbox]:checked").length;
			$("#deltialModal").modal();
			$("#msgSpan").html(length+"条数据，是否还原？")
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
    })
    
    
  //异常处理
	/*$(".abnorm").click(function() {
		var id = $(this).parent().parent().parent().attr("id");
		if($("#" + id + "  input[type=checkbox]:checked").length > 1) {
			$("#nullModal").modal();
			$("#warnMsg").html("不可批量处理！");
			return;
		} else if($("#" + id + "  input[type=checkbox]:checked").length == 0) {
			$("#nullModal").modal();
			$("#warnMsg").html("请选择一条要操作的数据!");
			return;
		} else {
			$("#abnormalModal").modal();
		}
	});*/
    
  //发短信
    //正常运单列表
	$("#msgbtn1").click(function() {
		if($("#tbody_1 input:checkbox[type='checkbox']:checked").length >0) {
			var length=$(" #tbody_1  input:checkbox[type='checkbox']:checked").length;
			$("#chooseNum").html(length);
			$("#messgageModal").modal();
			$("#noBussinss").html("");
			$("#sms_content").val("");
			$(".tips").hide();
   		//$("#MsgForm").validate().resetForm();
	    //$("#MsgForm")[0].reset();
		} else if($("#tbody_1 input:checkbox[type='checkbox']:checked").length == 0) {
			$("#promptIdModalNof").modal();
			$("#msgNof").html("");
			$("#msgNof").html("请选择要操作的数据");
		} 
	});	
    
    //异常列表
    $("#msgbtn2").click(function() {
		if($("#tbody_2 input:checkbox[type='checkbox']:checked").length >0) {
			var length=$(" #tbody_2  input:checkbox[type='checkbox']:checked").length;
			$("#chooseNum").html(length);
			$("#messgageModal").modal();
			$("#noBussinss").html("");
			$("#sms_content").val("");
			$(".tips").hide();
   		//$("#MsgForm").validate().resetForm();
	    //$("#MsgForm")[0].reset();
		} else if($("#tbody_2 input:checkbox[type='checkbox']:checked").length == 0) {
			$("#promptIdModalNof").modal();
			$("#msgNof").html("");
			$("#msgNof").html("请选择要操作的数据");
		} 
	});	
    
    //历史列表
    $("#msgbtn3").click(function() {
		if($("#tbody_3 input:checkbox[type='checkbox']:checked").length >0) {
			var length=$(" #tbody_3  input:checkbox[type='checkbox']:checked").length;
			$("#chooseNum").html(length);
			$("#messgageModal").modal();
			$("#noBussinss").html("");
			$("#sms_content").val("");
			$(".tips").hide();
   		//$("#MsgForm").validate().resetForm();
	    //$("#MsgForm")[0].reset();
		} else if($("#tbody_3 input:checkbox[type='checkbox']:checked").length == 0) {
			$("#promptIdModalNof").modal();
			$("#msgNof").html("");
			$("#msgNof").html("请选择要操作的数据");
		} 
	});	
    
    //回收站列表
    $("#msgbtn4").click(function() {
		if($("#tbody_4 input:checkbox[type='checkbox']:checked").length >0) {
			var length=$(" #tbody_4  input:checkbox[type='checkbox']:checked").length;
			$("#chooseNum").html(length);
			$("#messgageModal").modal();
			$("#noBussinss").html("");
			$("#sms_content").val("");
			$(".tips").hide();
   		//$("#MsgForm").validate().resetForm();
	    //$("#MsgForm")[0].reset();
		} else if($("#tbody_4 input:checkbox[type='checkbox']:checked").length == 0) {
			$("#promptIdModalNof").modal();
			$("#msgNof").html("");
			$("#msgNof").html("请选择要操作的数据");
		} 
	});	
})