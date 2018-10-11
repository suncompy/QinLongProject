$(function(){
	//切换去除选中效果
	$(".tabbable .nav li").click(function(){
		$("input[type=checkbox]").prop("checked",false);
	});
});
