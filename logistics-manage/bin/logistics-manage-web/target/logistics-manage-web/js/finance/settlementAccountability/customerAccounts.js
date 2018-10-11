$(function(){
	//结算模态框
	$("#settlement").click(function(){
		$("#settlementModal").modal();
		$("#myForm")[0].reset();
	});
		//结算明细财务审核
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
	//结算明细反审核
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
