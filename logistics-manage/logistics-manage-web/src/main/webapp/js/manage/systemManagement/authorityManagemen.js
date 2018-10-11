$(function(){
	
/*	//角色详情模态框
	$("body").on("click", "#panel1 tbody tr td:not(:nth-child(1))", function() {
			$(this).parent().toggleClass("bgclass").siblings("#panel1 tbody tr").removeClass("bgclass");
		$("#roleDetail").modal();
	});*/
//	新建角色模态框
/*   $("#addRole").click(function(){
   	   $("#addForm").validate().resetForm();
   	  $("[name=permissionId]:checkbox").attr("checked", false);
	   $("#addForm")[0].reset();
   	   $("#addRoleModal").modal();
   })*/
 /*	//删除
	$("#delBtn").click(function() {
		if($("input:checkbox[type='checkbox']:checked").length > 1) {
			$("#deletRemindModal").modal();
		} else if($("input:checkbox[type='checkbox']:checked").length == 0) {
			$("#deletnullModal").modal();
		} else{
			$("#deletForm")[0].reset();
			$("#deletReasonModal").modal();
		}
	});	  */
	

})
//# sourceURL=authorityManagemen.js
/**
 * @title 油气卡表
 * @author Jys
 * @date 2018-01-26
 */
;
(function($) {
  'use strict';	
  $(function() {
		//导入
	  $("#btnPermissionImp").click(function(e){
		e.preventDefault();
		$.fd.FdFileupload({url:"api/OilGasCard/imp/",
		    acceptFileTypes:["xls"],
	        maxFileSize:10,
			success:function(){
			$.fd.msg.notice("上传成功!");
		}});
	  });
  });
}(jQuery));
