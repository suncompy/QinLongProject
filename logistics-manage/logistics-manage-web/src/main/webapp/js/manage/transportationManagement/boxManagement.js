$(function(){
//	$("body").on("click",".addMore",function(){
//		$("#boxManageModal tbody").append("<tr><td class='tdClass' contenteditable='true'><label class='demo--label'>\
//								            <input class='demo--checkbox' type='checkbox'  >\
//							                  <span class='demo--checkboxInput'></span>\
//							                         	</label></td>\
//		<td contenteditable='true'></td>\
//		<td contenteditable='true'></td></tr>")
//	});
	
//厢型管理模态框	
	$("#boxManage").click(function(){
		$("#boxManageModal").modal();
		$("#boxManageForm").validate().resetForm();
	    $("#boxManageForm")[0].reset();
	})
		
//添加集装箱模态框	
	/*$("#addBox").click(function(){
		$("#addBoxModal").modal();
		$("#addForm").validate().resetForm();
		 $("#addForm")[0].reset();
		 $("#hiddenContainerId").val("");
	})*/
			
//添加集装箱模态框	
	$("#addbox").click(function(){
		$("#addboxModal").modal();
		$("#addFormId").validate().resetForm();
		 $("#addFormId")[0].reset();
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

	$("#modalDel").click(function() {
		if($("#boxManageModal input:checkbox[type='checkbox']:checked").length > 1) {
			$("#deletRemindModal").modal();
		} else if($("input:checkbox[type='checkbox']:checked").length == 0) {
			$("#deletnullModal").modal();
		} else{
			$("#deletForm")[0].reset();
			$("#boxDelModal").modal();
		}
	});	


});
