$(function(){
	var arrayObj = new Array(); // 复选勾选
	//选项切换清空复选框
	$(".tabbable .nav li").click(function(){
		$("input[type=checkbox]").prop("checked",false);
	});
//	新增计费模态框
      $("#addBilling").click(function(){
      	$("#billId").validate().resetForm();
		$("#billId")[0].reset();
	$("#addBillingModal").modal();
      })
//计费调整模态框      
      $("#charging").click(function(){
      	$("#chargingId").validate().resetForm();
		$("#chargingId")[0].reset();
      	$("#chargingModal").modal();
      })

	 	
	//开始
	  $(".start").click(function(){ 
    	if($("#panel1  input[type=checkbox]:checked").length > 0) {
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
			$("#deltialModal").modal();
			$("#msgSpan").html("条数据,确认暂停？")
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
    }) ;
      //取消
	$(".cancel").click(function(){ 
    	if($("#panel1  input[type=checkbox]:checked").length > 0) {
			$("#deltialModal").modal();
			$("#msgSpan").html("条数据,是否取消执行！")
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
    }) ;
    //	导出模态框
    $(".export").click(function(){ 
   var id=$(this).parent().parent().parent().attr("id");
    	if($("#"+ id +"  input[type=checkbox]:checked").length > 0) {
			$("#exportModal").modal();
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
    });
})