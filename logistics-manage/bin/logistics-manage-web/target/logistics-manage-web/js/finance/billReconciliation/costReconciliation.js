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
