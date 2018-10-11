$(function(){  
	//选项切换清空复选框
	$(".tabbable .nav li").click(function(){
		$("input[type=checkbox]").prop("checked",false);
	});
	  	var count="";
	  //等待装车添加更多货场货位
	  $("body").on("click",".addBtn",function(){
	  	 // $(this).parent().parent().next().next().hide();
	  	   $(this).hide();
	  	    //$(this).parent().children().find("select").addClass("selectSpan");
	  	count=1;
	  	//var paths = $("#hiddenPath").val();
	  	$(".bigDiv").append("<div class='project_info'><ul class='waitUl'><li>装车货场：<select  name='freightYard' class='freightYard'></select></li><li>装车货位：<select name='freightAllocation' class='freightAllocation'></select></li>\
	  	<li class='addBtn pull-right'><button class='btn btn-success' type='button'><img src='/img/addbtn.png'/>添加更多货场货位</button></li></ul></div>\
		<table class='table loadingTable'><thead><tr class='tableTop'><th>序号</th><th>车型</th><th>车号</th><th>集装箱号</th><th>集装箱号</th><th>装车载重</th></tr></thead>\
		<tbody><tr><td contentEditable='true' style='text-align:center'>1</td><td contentEditable='true' onclick='gainSelectValue(this);'></td><td contentEditable='true'></td><td contentEditable='true'></td>\
		<td contentEditable='true'></td><td contentEditable='true'></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td></tr></tbody></table>\
		<div class='moreAdd'><img src='/img/more.png'/>添加更多装车信息</div>");
		var checkValues="";
	  	var newFreightYard=$(this).parent().parent().next().next().next().children().find("li").eq(0).find("select");
	  	var newFreightAllocation=$(this).parent().parent().next().next().next().children().find("li").eq(1).find("select");
		var idss = $("#orderId").val();
		$.fd.ajax({  
               type: "GET",  
               url:"/logistics-manage-web/trainOrder/getCarrierMsg.do?id="+idss,  
               async : false,
               success: function(data) {  
	               	var freightYardLists = data.data["freightYardLists"];
	               	$.each(freightYardLists,function(index,fylts){
	               		newFreightYard.append("<option value="+fylts.id+">" + fylts.name + "</option>");
					});
	               	checkValues +=newFreightYard.val();
               }
           }); 
		
		//等待装车 货位下拉
		$.fd.ajax({  
             type: "GET",  
             url:"/logistics-manage-web/siteManager/cargo/location/getAll.do?id="+checkValues,  
             async : false,
             success: function(data) {  
 				$.each(data.data, function(index, its) {
 					newFreightAllocation.append(
		    							"<option value="+its.id+">" + its.name + "</option>");
 				});
             }
         });  
	  });
	  	//等待装车序列号递增加一行	
   var add = (function(start){  
     count = start;
    var iHtml = '';  
    return function(){
      iHtml = "<tr class='newTr'><td id="+ (count++) + " contentEditable='true'>"+ count +"</td><td contentEditable='true' onclick='gainSelectValue(this);'></td><td contentEditable='true'></td><td contentEditable='true'></td><td contentEditable='true'></td>" +
      		"<td contentEditable='true'></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td></tr>";
      $(this).prev().append(iHtml);
    }//<td style='display:none'></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td>
  })(0);
	  $("body").on("click",".moreAdd",add);
	  
	   //等待卸货添加更多货场货位
	  $("body").on("click",".addBtns",function(){
	  	  // $(this).parent().parent().next().next().hide();
	  	   $(this).hide();
	  	    //$(this).parent().children().find("select").addClass("selectSpan");
	  	count=1;
	  	$("#bigDivs").append("<div class='project_info'><ul class='waitUl'><li>卸货货场：<select></select></li><li>卸货货位：<select></select></li>\
	  	<li class='addBtns pull-right'><button class='btn btn-success' type='button'><img src='${pageContext.request.contextPath}/img/addbtn.png'/>添加更多货场货位</button></li></ul></div>\
		<table class='table loadingTable'><thead><tr class='tableTop'><th>序号</th><th>车型</th><th>车号</th><th>集装箱号</th><th>集装箱号</th><th>发货载重</th><th>到货载重</th><th>发货运单上传</th><th>到货运单上传</th></tr></thead>\
		<tbody><tr><td style='text-align:center'>1</td><td></td><td></td><td></td>\
		<td></td><td></td><td></td><td style='text-align:center'><a href='#'>点击上传</a></td><td style='text-align:center'><a href='#'>点击上传</a></td></tr></tbody></table>\
		");
	  });
	  
   var adds = (function(start){  
     count = start;
    var iHtml = '';  
    return function(){
      iHtml = "<tr class='newTr'><td contentEditable='true' id="+ (count++) + ">"+ count +"</td><td contentEditable='true'></td><td contentEditable='true'></td><td contentEditable='true'></td><td contentEditable='true'></td><td contentEditable='true'></td><td contentEditable='true'></td><td style='text-align:center'><a href='#'>点击上传</a></td><td style='text-align:center'><a href='#'>点击上传</a></td></tr>";
      $(this).prev().append(iHtml);
    }
  })(0);
	  $("body").on("click",".moreAdds",adds);
		//运单删除
	$("#delBtn").click(function() {
		if($("input:checkbox[name='WaybillName']:checked").length > 1) {
			$("#deletRemindModal").modal();
		}
		else if($("input:checkbox[name='WaybillName']:checked").length == 0) {
			$("#deletnullModal").modal();
		}else{
			$("#delete_reason").html("");
			$("#deletReasonModal").modal();
		}
	});  
	
//	新建请车
	/*$("#add").click(function(){
		$("#addId")[0].reset();
		$("#addModal").modal();
	})*/
//	等待承认
	/*$("#admit").click(function(){
		$("#admitId")[0].reset();
		$("#waitModal").modal();
	})*/
//	等待装车
	/*$("#loading").click(function(){
		$("#loadingId")[0].reset();
		$("#entruckingModal").modal();
	})*/
//	等待发运
		/*$("#send").click(function(){
		$("#sendId")[0].reset();
		$("#forwardingModal").modal();
	})*/
//	在途运载
	/*$("#carry").click(function(){
		$("#carryId")[0].reset();
		$("#locationModal").modal();
	})*/
	//等待卸货
	/*$("#unloading").click(function(){
		$("#unloadingId")[0].reset();
		$('#showMask', window.parent.document).show();
		$("#unloadingModal").modal();
	})	*/
/*//	等待回单
	$("#receipt").click(function(){
		$("#receiptId")[0].reset();
		$('#showMask', window.parent.document).show();
		$("#receiptModal").modal();
	})*/		
	
	$("#allocate").click(function(){
		$(".allocate").hide();
		$(".bigDivs").show();
		$(".row").show()
	})
	
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
	
	
	//还原
    $(".rest").click(function(){   	
    	 var id=$(this).parent().parent().parent().attr("id");
    	if($("#"+ id +" input[type=checkbox]:checked").length > 1) {
    		$("#promptIdModal").modal();
			$("#errorMsg").html("不可批量处理！");
		} 
		else if($("input:checkbox[type=checkbox]:checked").length == 0) {
			$("#promptIdModal").modal();
			$("#errorMsg").html("请选择一条要操作的数据！");
		}
		else {
			$("#deltialModal").modal();
			$("#resetOrder").html($("#"+ id +" input[type=checkbox]:checked").length);
			$("#msgSpan").html("条数据，是否还原？")
		}
    })
});