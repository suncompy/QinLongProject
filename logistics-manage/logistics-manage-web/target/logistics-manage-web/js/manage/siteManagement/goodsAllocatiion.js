$(function(){
	
	var add = (function(start) {
		count = start;
		var iHtml = '';
		return function() {
			iHtml = "<tr class='newTr'><td id=" + (count++) + ">" + count + "</td><td contentEditable='true'></td><td contentEditable='true'></td></tr>";
			$(this).prev().append(iHtml);
			
		}
	})(0);
	$("body").on("click", ".moreAdd", add);
	
//新建货场
   $("#addYard").click(function(){
   	$("#addForm").validate().resetForm();
	$("#addForm")[0].reset();
	$("#city_first").citySelect({
		prov:"安徽", 
		city:"合肥",
		dist:"蜀山区",
		nodata:"none"
	});
	
   	$("#addYardModal").modal();
   	
   	$("#tBod").empty();
   	$("#edit_tBod").empty();
   	var add = (function(start){  
   	     count = 0;
   	    var iHtml = '';  
   	    return function(){
   	    	iHtml = "<tr class='newTr'><td id=" + (count++) + ">" + count + "</td><td contentEditable='true'></td><td contentEditable='true'></td></tr>";
   	      $(this).prev().append(iHtml);
   	    }
   	  })(0);
   })	
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
	
   
   
});
