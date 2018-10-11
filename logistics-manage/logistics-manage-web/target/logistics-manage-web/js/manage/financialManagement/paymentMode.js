$(function(){
//新建模式
   $("#addMode").click(function(){
   	$("#addModeForm").validate().resetForm();
   	$("#addModeForm")[0].reset();
   	$("#addModeModal").modal();
   });
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
