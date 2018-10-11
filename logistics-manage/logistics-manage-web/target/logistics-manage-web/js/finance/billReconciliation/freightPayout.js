$(function(){
	//选项切换清空复选框
	$(".tabbable .nav li").click(function(){
		$("input[type=checkbox]").prop("checked",false);
	});
	//点击查看底部
   $(".tableDivs").hide();
	$("body").on("click", "#panel1 tbody tr td:not(:nth-child(1))", function() {
	$(".tableDivs").show();
		$(this).parent().toggleClass("bgclass").siblings("#panel1 tbody tr").removeClass("bgclass");
	});
	$(".hideDiv").click(function(){
	 $(".tableDivs").hide();
	})
	//计费变更
	$("#pricingChanges").click(function(){
		$("#changeModal").modal();
			if($("#resonId").val()!==null){
			$("#moneyinput").removeAttr("disabled");
		}
		else{
			$("#moneyinput").attr("disabled","disabled");
		}
	});
	//对账明细计算运费
    $("#sureAccount").click(function() {
		if($("#panel1 input[type=checkbox]:checked").length > 0) {
			$("#deltialModal").modal();
			$("#msgSpan").html("条数据，是否确认计费？");
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
	});
	//对账明细反对账
	$("#backAccount").click(function() {
		if($("#panel1 input[type=checkbox]:checked").length > 0) {
			$("#deltialModal").modal();
			$("#msgSpan").html("条数据，是否驳回对账？");
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
	});
	//对账明细财务审核
	$("#accountDetails").click(function() {
		if($("#panel1 input[type=checkbox]:checked").length > 0) {
			$("#deltialModal").modal();
			$("#msgSpan").html("条数据，是否确认审核？");
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
	});
	//对账明细反审核
	$("#backaccountDetails").click(function() {
		if($("#panel1 input[type=checkbox]:checked").length > 0) {
			$("#deltialModal").modal();
			$("#msgSpan").html("条数据，是否驳回审核申请？");
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
	});
	//异常对账导出
	$("#abnormalExport").click(function() {
		if($("#panel2 input[type=checkbox]:checked").length > 0) {
			$("#abnormalExportModal").modal();
			$("#msgSpans").html("条数据，确认导出？");
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
	});
	//历史对账导出
	$("#historyExport").click(function() {
		if($("#panel3 input[type=checkbox]:checked").length > 0) {
			$("#historyExportModal").modal();
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
	});
})
