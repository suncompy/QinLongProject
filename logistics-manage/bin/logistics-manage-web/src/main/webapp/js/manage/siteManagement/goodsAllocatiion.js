$(function(){	
	var add = (function(start) {
		count = start;
		var iHtml = '';
		return function() {
			iHtml = "<tr class='newTr'><td id=" + (count++) + ">" + count + "</td><td title='双击可编辑'></td><td title='双击可编辑'></td><td style='display:none;'></td>" +
					"<td style='text-align:center;'><button type=button style='padding: 8px; border: 1px solid #dde3ef;' onclick='checkCargoIsUse(this)'>删除<button></td></tr>";
			$(this).prev().append(iHtml);			
		}
	})(0);
	$("body").on("click", ".moreAdd", add);	
//新建货场
   $("#addYard").click(function(){
   	$("#addForm").validate().resetForm();
	$("#addForm")[0].reset();
	$("#city_first").citySelect({
		prov:"新疆", 
		city:"乌鲁木齐",
		dist:"天山区",
		nodata:"none"
	});
	
   	$("#addYardModal").modal();
   	
   	$("#tBod").empty();
   	$("#edit_tBod").empty();
   	var add = (function(start){  
   	     count = 0;
   	    var iHtml = '';  
   	    return function(){
   	    	iHtml = "<tr class='newTr'><td id=" + (count++) + ">" + count + "</td><td title='双击可编辑'></td><td title='双击可编辑'></td><td style='display:none;'></td>" +
   	    			"<td style='text-align:center;'><button type=button style='padding: 8px; border: 1px solid #dde3ef;' onclick='checkCargoIsUse(this)'>删除<button></td></tr>";
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
   $("body").on("dblclick", "#pointTable td:not(:nth-child(1))", function() {
					var td = $(this);
					// 根据表格文本创建文本框 并加入表表中--文本框的样式自己调整
					var text = td.text();
					var txt = $("<input type='text' class='newInput' style='text-align:center';>").val(text);
					txt.blur(function() {
						// 失去焦点，保存值。于服务器交互自己再写,最好ajax
						var newText = $(this).val();

						// 移除文本框,显示新值
						$(this).remove();
						td.text(newText);
					});
					td.text("");
					td.append(txt);
					txt.focus();
				});
   
   $("body").on("dblclick", "#goodInfo td:not(:nth-child(1))", function() {
		var td = $(this);
		// 根据表格文本创建文本框 并加入表表中--文本框的样式自己调整
		var text = td.text();
		var txt = $("<input type='text' class='newInput' style='text-align:center';>").val(text);
		txt.blur(function() {
			// 失去焦点，保存值。于服务器交互自己再写,最好ajax
			var newText = $(this).val();

			// 移除文本框,显示新值
			$(this).remove();
			td.text(newText);
		});
		td.text("");
		td.append(txt);
		txt.focus();
	});
   
});
