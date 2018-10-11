$(function(){
//分配任务模态框
     /* $(".distribtion").click(function(){
      	$("#distributionId")[0].reset();
	$("#distributionModal").modal();
      })*/
	
		//开始
	  $(".start").click(function(){ 
    	if($("#panel1  input[type=checkbox]:checked").length > 0) {
    		var length=$("#panel1  input[type=checkbox]:checked").length ;
    		$(".errnums").html(length);
			$("#deltialModal").modal();
			$("#msgSpan").html("条数据,确认开始？")
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
    });
  //暂停
	$(".pause").click(function(){ 
    	if($("#panel1  input[type=checkbox]:checked").length > 0) {
    		var length=$("#panel1  input[type=checkbox]:checked").length ;
    		$(".errnums").html(length);
			$("#deltialModal2").modal();
			$("#msgSpan2").html("条数据,确认暂停？")
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
    }) ;
	
})