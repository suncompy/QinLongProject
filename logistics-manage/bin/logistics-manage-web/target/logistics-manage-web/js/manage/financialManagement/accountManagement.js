$(function(){
	//新增货物模态框	
	$("#addAccount").click(function(){
		$("#userForm").validate().resetForm();
	    $("#userForm")[0].reset();
		$("#addAccountModal").modal();
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
})
