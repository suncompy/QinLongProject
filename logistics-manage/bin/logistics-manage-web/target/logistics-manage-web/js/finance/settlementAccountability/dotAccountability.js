$(function(){
	//点击查看底部
   $(".tableDivs").hide();
	$("body").on("click", "#panel1 tbody tr td", function() {
	$(".tableDivs").show();
		$(this).parent().toggleClass("bgclass").siblings("#panel1 tbody tr").removeClass("bgclass");
	});
	$(".hideDiv").click(function(){
	 $(".tableDivs").hide();
	});
	//查看更多
	$("#lookAll").click(function(){
		$("#lookModal").modal();
	});
		//交账明细财务审核
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
	//交账明细反审核
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
		//打包模态框
	$("#accountpacking").click(function(){
		  $("#packForm")[0].reset();
		$("#accountpackingModal").modal();
	});
	//油气结算模态框
	$("#oilGasAccount").click(function(){
		  $("#oilGasForm")[0].reset();
		$("#oilGasAccountModal").modal();
	});
	//关联号增加
	
	$("body").on("click",".addImg",function(){
//		$(".marks").css("contenteditable","true");
		$(".marks").removeAttr("readonly");
	 	$(".marks").removeClass("bgclass");
	 	$(this).hide();
		$(".addInput").append("<div class='form-inline'>\
        <div class='form-group'><label>添加油气卡：&nbsp; </label><select></select></div>\
        <div class='form-group'><label>&emsp;金额：&nbsp; </label><input type='text'/></div><img class='addImg' src='../../../img/add1.png'/>\</div>")
	});
	
	
})
