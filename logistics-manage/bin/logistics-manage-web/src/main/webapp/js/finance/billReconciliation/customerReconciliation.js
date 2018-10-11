$(function() {
	//选项切换清空复选框
	$(".tabbable .nav li").click(function() {
		$("input[type=checkbox]").prop("checked", false);
	});
	//点击查看底部
	$(".tableDivs").hide();
	//对账明细
	$("body").on("click", "#panel1 tbody tr td:not(:nth-child(1))", function() {
		$("#detailDiv").show();
		$(this).parent().toggleClass("bgclass").siblings("#panel1 tbody tr").removeClass("bgclass");
	});
	//汽运历史对账
	$("body").on("click", "#panel2 tbody tr td:not(:nth-child(1))", function() {
		$("#automotiveHistory").show();
		$(this).parent().toggleClass("bgclass").siblings("#panel2 tbody tr").removeClass("bgclass");
	});
	//接取历史对账
	$("body").on("click", "#panel3 tbody tr td:not(:nth-child(1))", function() {
		$("#pickHistory").show();
		$(this).parent().toggleClass("bgclass").siblings("#panel3 tbody tr").removeClass("bgclass");
	});
	$("body").on("click", "#panel4 tbody tr td:not(:nth-child(1))", function() {
		$("#fireHistory").show();
		$(this).parent().toggleClass("bgclass").siblings("#panel4 tbody tr").removeClass("bgclass");
	});
	$("body").on("click", "#panel5 tbody tr td:not(:nth-child(1))", function() {
		$("#serviceHistory").show();
		$(this).parent().toggleClass("bgclass").siblings("#panel5 tbody tr").removeClass("bgclass");
	});
	$(".hideDiv").click(function() {
			$(".tableDivs").hide();
			$("#detailDiv").hide();
	});
	//确认对账模态框
	//对账明细确认对账
	$("#sureAccount").click(function() {
		if($("#panel1 input[type=checkbox]:checked").length > 0) {
			$("#deltialModal").modal();
			$("#msgSpan").html("条数据，是否确认对账？");
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
	//汽运历史确认对账
	$("#motorAccount").click(function() {
		if($("#panel2 input[type=checkbox]:checked").length > 0) {
			$("#deltialModal").modal();
			$("#msgSpan").html("条数据，是否确认对账？");
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
	});
	//汽运历史反对账
	$("#motorBackaccount").click(function() {
		if($("#panel2 input[type=checkbox]:checked").length > 0) {
			$("#deltialModal").modal();
			$("#msgSpan").html("条数据，是否驳回对账？");
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
	});
	//汽运历史财务审核
	$("#motorExamine").click(function() {
		if($("#panel2 input[type=checkbox]:checked").length > 0) {
			$("#deltialModal").modal();
			$("#msgSpan").html("条数据，是否确认审核？");
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
	});
	//汽运历史反审核
	$("#motorbackExamine").click(function() {
		if($("#panel2 input[type=checkbox]:checked").length > 0) {
			$("#deltialModal").modal();
			$("#msgSpan").html("条数据，是否驳回审核申请？");
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
	});	
	//接取历史确认对账
	$("#accessAmount").click(function() {
		if($("#panel3 input[type=checkbox]:checked").length > 0) {
			$("#deltialModal").modal();
			$("#msgSpan").html("条数据，是否确认对账？");
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
	});
	//接取历史反对账
	$("#accessbackAmount").click(function() {
		if($("#panel3 input[type=checkbox]:checked").length > 0) {
			$("#deltialModal").modal();
			$("#msgSpan").html("条数据，是否驳回对账？");
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
	});
	//接取历史财务审核
	$("#accessExamine").click(function() {
		if($("#panel3 input[type=checkbox]:checked").length > 0) {
			$("#deltialModal").modal();
			$("#msgSpan").html("条数据，是否确认审核？");
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
	});
	//接取历史反审核
	$("#accessbackExamine").click(function() {
		if($("#panel3 input[type=checkbox]:checked").length > 0) {
			$("#deltialModal").modal();
			$("#msgSpan").html("条数据，是否驳回审核申请？");
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
	});			
	//火运历史确认对账
	$("#fireAmount").click(function() {
		if($("#panel4 input[type=checkbox]:checked").length > 0) {
			$("#deltialModal").modal();
			$("#msgSpan").html("条数据，是否确认对账？");
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
	});
	//火运历史反对账
	$("#firebackAmount").click(function() {
		if($("#panel4 input[type=checkbox]:checked").length > 0) {
			$("#deltialModal").modal();
			$("#msgSpan").html("条数据，是否驳回对账？");
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
	});
	//火运历史财务审核
	$("#fireExamine").click(function() {
		if($("#panel4 input[type=checkbox]:checked").length > 0) {
			$("#deltialModal").modal();
			$("#msgSpan").html("条数据，是否确认审核？");
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
	});
	//火运历史反审核
	$("#firebackExamine").click(function() {
		if($("#panel4 input[type=checkbox]:checked").length > 0) {
			$("#deltialModal").modal();
			$("#msgSpan").html("条数据，是否驳回审核申请？");
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
	});
	//送达历史确认对账
	$("#serviceAmount").click(function() {
		if($("#panel5 input[type=checkbox]:checked").length > 0) {
			$("#deltialModal").modal();
			$("#msgSpan").html("条数据，是否确认对账？");
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
	});
	//送达历史反对账
	$("#servicebackAmount").click(function() {
		if($("#panel5 input[type=checkbox]:checked").length > 0) {
			$("#deltialModal").modal();
			$("#msgSpan").html("条数据，是否驳回对账？");
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
	});
	//送达历史财务审核
	$("#serviceExamine").click(function() {
		if($("#panel5 input[type=checkbox]:checked").length > 0) {
			$("#deltialModal").modal();
			$("#msgSpan").html("条数据，是否确认审核？");
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
	});
	//送达历史反审核
	$("#servicebackExamine").click(function() {
		if($("#panel5 input[type=checkbox]:checked").length > 0) {
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
		if($("#panel6 input[type=checkbox]:checked").length > 0) {
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
		if($("#panel7 input[type=checkbox]:checked").length > 0) {
			$("#historyExportModal").modal();
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
	});
});