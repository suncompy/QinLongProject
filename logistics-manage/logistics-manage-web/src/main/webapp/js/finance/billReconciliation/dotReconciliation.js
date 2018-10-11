$(function(){
	//选项切换清空复选框
	$(".tabbable .nav li").click(function(){
		$("input[type=checkbox]").prop("checked",false);
	});
//	点击查看底部
   $(".tableDivs").hide();
	$("body").on("click", "#panel2 tbody tr td:not(:nth-child(2))", function() {
	$(".tableDivs").show();
		$(this).parent().toggleClass("bgclass").siblings("#panel2 tbody tr").removeClass("bgclass");
	});
	$(".hideDiv").click(function(){
	 $(".tableDivs").hide();
	})


})