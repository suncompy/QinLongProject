$(function(){
//选项切换清空复选框
	$(".tabbable .nav li").click(function(){
		$("input[type=checkbox]").prop("checked",false);
	});
 // 项目运营点击标签表单隐藏 
                $(".depot_form").hide();
        		$("#items").click(function(){
        			$(".project-form").show();
        			$(".depot_form").hide();
        		});
        		$(".clickBtn").click(function(){
        			$(".project-form").hide();
        			$(".depot_form").show();
        		})
		
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
    })  
	
	
	
	
})
