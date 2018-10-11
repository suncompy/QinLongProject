$(function(){
//	查询统计模态框
    /*  $("#queryStatistics").click(function(){
	$("#queryStatisticsModal").modal();
      });*/
      
      //	导出模态框
	$("#export").click(function(e) {
	    e.preventDefault();
	    var currentPage = $("#page .current").html();
	    var condition = $('#searchForm').serializeJSON();
	    var conditionObj = objToUrl(condition)+"&pageNo="+currentPage;
	    window.location.href = "/logistics-manage-web/trainAccessStorage/export"+conditionObj;
	  });
})