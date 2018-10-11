$(function(){
	//选项切换清空复选框
	$(".tabbable .nav li").click(function(){
		if($(this).children().text()=="已离职员工信息"){
			$(".dataTimes").hide();
			$("#lookMore").hide();
			$("#wrap").css("height","29px")
		}
		else{
			$(".dataTimes").show();	
			$("#lookMore").show();
			$("#wrap").css("height","25px");
			$('#lookMore').html("显示筛选<em class='arrows'></em>");
			
		}
		
		$("input[type=checkbox]").prop("checked",false);
	});
//添加员工模态框 
   $("#addemployee").click(function(){
   	$('#addemployeeModal input[name="phone"]').attr('readonly',false);
	$('#addemployeeModal input[name="phone"]').removeClass('inputbg');
    $('#addemployeeModal input[name="idcard"]').attr('readonly',false);
	$('#addemployeeModal input[name="idcard"]').removeClass('inputbg');    			
   	$("#addEmpId").val("");
   	$("#cityAddress").citySelect({
    	prov:"安徽", 
    	city:"合肥",
		dist:"蜀山区",
		nodata:"none"
	}); 
   	$('#myModalLabel').html('添加员工');
	    $("#addForm")[0].reset();
   	$("#addemployeeModal").modal();
   })
   //删除
	$("#delBtn").click(function() {
		if($("input:checkbox[type='checkbox']:checked").length > 1) {
			$("#deletRemindModal").modal();
		} else if($("input:checkbox[type='checkbox']:checked").length == 0) {
			$("#msgContent").html("请选择一条您要禁用的员工信息");
			$("#deletnullModal").modal();
		} else {
			$("#deletId")[0].reset();
			$("#deletReasonModal").modal();
		}
	});	
   
   
})