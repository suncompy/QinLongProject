$(function(){
//新建站点
   $("#addSite").click(function(){
   	$("#addForm").validate().resetForm();
	    $("#addForm")[0].reset();
   	$("#addSiteModal").modal();
   })
	//删除
	$("#delBtn").click(function() {
		if($("input:checkbox[type='checkbox']:checked").length > 1) {
			
			$("#deletRemindModal").modal();
		} else if($("input:checkbox[type='checkbox']:checked").length == 0) {
			$("#asdf").html("请选择一条要删除的数据");
			$("#deletnullModal").modal();
		} else{
			$("#deletForm")[0].reset();
			$("#deletReasonModal").modal();
		}
	});	
		   
   
});
