$(function(){   
//详情模态框
	$("body").on("click", "#panel1 tbody tr td:not(:nth-child(1))", function() {
			$(this).parent().toggleClass("bgclass").siblings("#panel1 tbody tr").removeClass("bgclass");
		$("#detailModal").modal();
	});
	//新增收款
	$("#addbtn").click(function(){
		$("#addmodal").modal();
		$("#threeForm")[0].reset();
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
});
