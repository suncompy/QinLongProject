$(function(){
	
	//添加情景
		//$("#formula").hide();
	
	$("#resonId").change(function(){
		$("#formula").show();
		if($("#resonId").val()==1){
			$("#newDate").html("卸载损耗:");
			$("#unit").html("%");
		}
		else if($("#resonId").val()==2){
			$("#newDate").html("运单时长:");
			$("#unit").html("时");
		}
		else if($("#resonId").val()==3){
			$("#newDate").html("停车时长:");
			$("#unit").html("分");
		}
		else if($("#resonId").val()==""){
			$("#formula").hide();
			$("#unit").html("");
		}
	});
	
	
	//关联号减少
	$("body").on("click",".reduceImg",function(){
		$(this).parent().remove()
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
		
})
