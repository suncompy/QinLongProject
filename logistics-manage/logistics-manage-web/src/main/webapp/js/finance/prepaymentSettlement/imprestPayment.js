$(function(){
	//存入
	$("#imprestPayment").click(function(){
		$("#depositForm")[0].reset();
		$("#imprestPaymentModal").modal();		
	});	
	//抵用
	$("#imprestPurpose").click(function(){
		$("#imprestPurposeModal").modal();		
	});	
    //存入明细财务审核
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
	//存入明细反审核
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
	//票务名称
	$("#changeName").change(function(){
		if($("#changeName").val()=="货物费用"){
			$(".trainMoney").hide();
			$(".goodsMoney").show();
		}
		else{
			$(".trainMoney").show();
			$(".goodsMoney").hide();
		}
	})
	//详情
	$("body").on("click", "#panel1 tbody tr td:not(:nth-child(1))", function() {
	$("#detailModal").modal();

		if($(this).parent().find("td").eq(5).html()=='存入'){//存入
			$("#receInfo").show();
			$("#serveModal").hide();
			$("#changLabel").html("&emsp;流水号：");
		}
		else{
			//抵用
			if($("#ticketName").val()=='火运费用'){
				$(".hideTrain").hide();
				$(".hideGoods").show();
			}
			else{
				$(".hideTrain").show();
				$(".hideGoods").hide();
			}
			$("#receInfo").hide();
			$("#serveModal").show();
			$("#changLabel").html("票务名称：");
		}
	})
	
	
})
