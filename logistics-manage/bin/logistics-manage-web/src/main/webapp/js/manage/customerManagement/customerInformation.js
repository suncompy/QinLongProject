$(function(){
	/*//关联号增加
	$("body").on("click",".addImg",function(){
		$("#relationUl").append("<li><select></select>\
		<img src='../../../img/add1.png' class='addImg'/>\
		<img src='../../../img/reduce.png'  class='reduceImg'/></li>")
	});
	//关联号减少
	$("body").on("click",".reduceImg",function(){
		$(this).parent().remove()
	});*/
//添加用户模态框
	/*$("#addUser").click(function(){
	$("#addUserForm")[0].reset();
		$("#addUserModal").modal();
	});*/
	//删除
	$("#delBtn").click(function() {
		if($("input:checkbox[type='checkbox']:checked").length > 1) {
			$("#deletRemindModal").modal();
		} else if($("input:checkbox[type='checkbox']:checked").length == 0) {
			$("#deletnullModal").modal();
		} else{
			$("#deletReasonModal").modal();
//			$("#deletForm")[0].reset();
			$(".textareas").val("");
		}
	});	
	
})
