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
    $(".export").click(function(){ 
   var id=$(this).parent().parent().parent().attr("id");
    	if($("#"+ id +"  input[type=checkbox]:checked").length > 0) {
			$("#exportModal").modal();
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
    })
    
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
    
    
})