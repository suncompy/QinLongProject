$(function(){
	
//添加分支模态框 
   $("#addBranch").click(function(){
   	 $("#addBranchId").val("");
   	$("#addForm").validate().resetForm();
	$("#addForm")[0].reset();
   	$("#addBranchModal").modal();
   })
	
})
