$(function(){
	//选项切换清空复选框
	$(".tabbable .nav li").click(function(){
		$("input[type=checkbox]").prop("checked",false);
	});
	//点击查看底部
   $(".tableDivs").hide();
	$("body").on("click", "#panel2 tbody tr td:not(:nth-child(1))", function() {
	$(".tableDivs").show();
		$(this).parent().toggleClass("bgclass").siblings("#panel2 tbody tr").removeClass("bgclass");
	});
		$(".hideDiv").click(function(){
	 $(".tableDivs").hide();
	})
	//计费变更
	$("#pricingChanges").click(function(){
	    $("#changeForm")[0].reset();
		$("#changeModal").modal();
			if($("#resonId").val()!==null){
			$("#moneyinput").removeAttr("disabled");
		}
		else{
			$("#moneyinput").attr("disabled","disabled");
		}
	});
	//	开始对账
	$("#startReconciliation").click(function(){
	    $("#startDetail")[0].reset();
		$("#startModal").modal();
	});
//	打包
	$("#pack").click(function(){
		    $("#packForm")[0].reset();
			$("#packModal").modal();
		});
		
$("#accountDetails").click(function() {
		if($("#panel2 tbody input[type=checkbox]:checked").length > 0) {
			$(".errnums").html($("#panel2 input[type=checkbox]:checked").length);
			$("#deltialModal").modal();
			$("#msgSpan").html("条数据，是否确认审核 ？");
			$("#nullModal").modal("hide");
		} else {
			$("#warnMsg").html("请选择一条要操作的数据 !");
			$("#nullModal").modal();
			return;
		}
	});
	//对账明细反审核
	$("#backaccountDetails").click(function() {
		if($("#panel2 tbody input[type=checkbox]:checked").length > 0) {
			$(".errnums").html($("#panel2 input[type=checkbox]:checked").length);
			$("#deltialModal").modal();
			$("#msgSpan").html("条数据，是否还原到未审核状态 ？");
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			$("#warnMsg").html("请选择一条要操作的数据 !");
			return;
		}
	});
//	解包
	$("#unpack").click(function() {
		if($("#panel2 tbody input[type=checkbox]:checked").length > 0) {
			$(".errnums").html($("#panel2 input[type=checkbox]:checked").length);
			$("#deltialModal").modal();
			$("#msgSpan").html("条数据，是否还原到未打包状态 ？");
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			$("#warnMsg").html("请选择一条要操作的数据 !");
			return;
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
    });
})
