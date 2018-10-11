$(function(){   
		var count="";
		//化验指标序列号递增加一行	
	   var add = (function(start){  
	     count = start;
	    var iHtml = '';  
	    return function(){
	      iHtml = "<tr class='newTr'><td><label class='demo--label'>\
				<input class='demo--checkbox moneyCheck' type='checkbox'  name='checkname' value='1'>\
	          <span class='demo--checkboxInput'></span>\
	                  </label></td><td id="+ (count++) + ">"+ count +"</td><td title='双击可编辑'></td><td title='双击可编辑' onkeyup='placeHtml(this)'>~</td></tr>";
	      $(this).prev().append(iHtml);
	    }
	  })($("#addSerial").val());
	   $("body").on("click",".moreAdd",add);
	  
//规格序列号递增加一行
   var adds = (function(start){  
     counts = start;
    var iHtmls = '';  
    return function(){
      iHtmls = "<tr class='newTr'><td id="+ (counts++) + ">"+ counts +"</td><td title='双击可编辑'></td></tr>";
      $(this).prev().append(iHtmls);
    }
  })(0);
	  $("body").on("click",".moreAdds",adds);
	  
		//新增货物模态框	
		$("#addCargo").click(function() {
			//$("#addForm").validate().resetForm();
			$("#addForm")[0].reset();
			$("#myModalLabels").html("新增货物");
			$("#tPointBod").empty();
			$("#tSpecBod").empty();
			var add = (function(start){  
			     count = 0;
			    var iHtml = '';  
			    return function(){
			      iHtml = "<tr class='newTr'><td><label class='demo--label'>\
						<input class='demo--checkbox moneyCheck' type='checkbox'  name='checkname' >\
			          <span class='demo--checkboxInput'></span>\
			                  </label></td><td id="+ (count++) + ">"+ count +"</td><td title='双击可编辑'></td><td title='双击可编辑' onkeyup='placeHtml(this)'>~</td></tr>";
			      $(this).prev().append(iHtml);
			    }
			  })(0);
			var adds = (function(start){  
			     counts = 0;
			    var iHtmls = '';  
			    return function(){
			      iHtmls = "<tr class='newTr'><td id="+ (counts++) + ">"+ counts +"</td><td title='双击可编辑'></td></tr>";
			      $(this).prev().append(iHtmls);
			    }
			  })(0);
			$("#addCargoModal").modal();
		})
		//删除
		$("#delBtn").click(function() {
			if($("#operatorTable input:checkbox[type='checkbox']:checked").length > 1) {
				$("#deletRemindModal").modal();
			} else if($("#operatorTable input:checkbox[type='checkbox']:checked").length == 0) {
				$("#deletnullModal").modal();
			} else{
				$("#deletForm")[0].reset();
				$("#deletReasonModal").modal();
			}
		});	
});
