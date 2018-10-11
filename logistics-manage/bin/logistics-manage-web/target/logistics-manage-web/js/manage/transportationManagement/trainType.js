$(function(){
//添加车型模态框	
	$("#addType").click(function(){
		$("#addTypeForm").validate().resetForm();
	    $("#addTypeForm")[0].reset();
		$("#addTypeModal").modal();
	})
			
	//删除
	$("#delBtn").click(function() {
		if($("input:checkbox[type='checkbox']:checked").length > 1) {
			$("#deletRemindModal").modal();
		} else if($("input:checkbox[type='checkbox']:checked").length == 0) {
			$("#deletnullModal").modal();
		} else{
			$("#deletForm")[0].reset();
			$("#deletReasonModal").modal();
		}
	});		
	
});