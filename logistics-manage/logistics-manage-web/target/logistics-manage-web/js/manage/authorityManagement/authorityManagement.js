$(function(){
	$("#addRole")
	//角色详情模态框
	$("body").on("click", "#panel1 tbody tr td:not(:nth-child(1))", function() {
			$(this).parent().toggleClass("bgclass").siblings("#panel1 tbody tr").removeClass("bgclass");
		$("#roleDetail").modal();
	});
})
