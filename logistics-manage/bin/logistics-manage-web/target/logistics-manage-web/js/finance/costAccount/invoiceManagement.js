$(function(){
	//发票登入	
	$("#invoiceEntry").click(function(){
    $("#materialForm")[0].reset();
		$("#invoiceEntryModal").modal();
	});
		//对账明细计算运费
    $("#invoiceInvalidation").click(function() {
    	 $("#invalidationForm")[0].reset();
			$("#invoiceInvalidationModal").modal();
	});
	//历史对账导出
	$("#historyExport").click(function() {
		if($("#panel2 input[type=checkbox]:checked").length > 0) {
			$("#historyExportModal").modal();
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
	});
})
