$(function(){
//	查询统计模态框
    /*  $("#queryStatistics").click(function(){
	$("#queryStatisticsModal").modal();
      });*/
      
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
    })   ; 
})