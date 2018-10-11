$(function(){
	//选项切换清空复选框
	$(".tabbable .nav li").click(function(){
		$("input[type=checkbox]").prop("checked",false);
	});
	//关联号增加
	$("body").on("click",".addImg",function(){
		$("#relationUl").append("<li>&nbsp;<select></select>\
		<img src='../../../img/add1.png' class='addImg'/>\
		<img src='../../../img/reduce.png'  class='reduceImg'/></li>")
	});
	//关联号减少
	$("body").on("click",".reduceImg",function(){
		$(this).parent().remove()
	});
	
	// 新增 计划模态框 
	$("#addPlans").click(function(){
   		$("#addPlansModal").modal();
   			$("#planForm").validate().resetForm();
	    $("#planForm")[0].reset();
   		
     });
	//新增模板模态框
	$("#addTemplate").click(function(){
   		$("#addTemplateModal").modal();
   			$("#modalForm").validate().resetForm();
	    $("#modalForm")[0].reset();
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
	//	导出模态框
    $(".export").click(function(){ 
   var id=$(this).parent().parent().parent().attr("id");
    	if($("#"+ id +"  input[type=checkbox]:checked").length > 0) {
    		var length=$("input:checkbox[type='checkbox']:checked").length;
			$("#exportNum").html(length);
			$("#exportModal").modal();
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
    })    
		//开始
	  $(".start").click(function(){ 
    	if($("#panel2  input[type=checkbox]:checked").length > 0) {
    		var length=$("input:checkbox[type='checkbox']:checked").length;
			$("#startNum").html(length);
			$("#startModal").modal();
			$("#msgSpan").html("条数据,确认开始？")
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
    });
  //暂停
	$(".pause").click(function(){ 
    	if($("#panel2  input[type=checkbox]:checked").length > 0) {
    		var length=$("input:checkbox[type='checkbox']:checked").length;
			$("#parseNum").html(length);
			$("#pauseModal").modal();
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
    }) ;
    
    //发短信
	$("#sendMessage").click(function() {
		if($("input:checkbox[type='checkbox']:checked").length >0) {
			var length=$(" tbody  input:checkbox[type='checkbox']:checked").length;
			$("#chooseNum").html(length);
			$("#sendMessageModal").modal();
   		$("#MsgForm").validate().resetForm();
	    $("#MsgForm")[0].reset();
		} else if($("input:checkbox[type='checkbox']:checked").length == 0) {
			$("#nullModal").modal();
		} 
	});	

})
