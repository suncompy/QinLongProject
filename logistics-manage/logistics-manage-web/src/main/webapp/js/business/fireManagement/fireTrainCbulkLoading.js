$(function(){  
	//选项切换清空复选框
	$(".tabbable .nav li").click(function(){
		$("input[type=checkbox]").prop("checked",false);
	});
	  	var count="";
	  //等待装车添加更多货场货位
	  $("body").on("click",".addBtn",function(){
		  var rowNum=$(".newTr").length;
		  var chengrencheshu = $("#chengrencheshu").val();
		  if(rowNum>=chengrencheshu){
			  $.fd.msg.notice("增加的车辆数不能超过承认车数！");
		  }else{
			  
	  	 // $(this).parent().parent().next().next().hide();
	  	   $(this).hide();
	  	    //$(this).parent().children().find("select").addClass("selectSpan");
	  	count=1;
	  	//<tr class='newTr'><td  contentEditable='true' style='text-align:center'>1</td><td><select class='form-control' name='chexing' onchange='gainSelectValue(this);'></select></td><td contentEditable='true' oninput='restrictLength(this)'></td><td contentEditable='true' onblur='sumWeight(this)'></td>\
		//<td style='display:none'></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td></tr>
	  	$(".bigDiv").append("<div class='project_info'><ul class='waitUl'><li>装车货场：<select  name='freightYard' class='freightYard'></select></li><li>装车货位：<select  name='freightAllocation' class='freightAllocation'></select></li>\
	  	<li class='addBtn pull-right'><button class='btn btn-success' type='button'><img src='/logistics-manage-web/img/addbtn.png'/>添加更多货场货位</button></li></ul></div>\
		<table class='table loadingTable'><thead><tr class='tableTop'><th>序号</th><th>车型</th><th>车号</th><th>装车载重</th><th>操作</th></tr></thead>\
		<tbody></tbody></table>\
		<div class='moreAdd' name='moreAddBind' style='display:none'><img src='/logistics-manage-web/img/more.png'/>添加更多装车信息</div>"
	  	+"<div class='moreAdd' name='moreAddVali'><img src='/logistics-manage-web/img/more.png'/>添加更多装车信息</div>");
	  var checkValues="";
	  	var newFreightYard=$(this).parent().parent().next().next().next().next().children().find("li").eq(0).find("select");
	  	var newFreightAllocation=$(this).parent().parent().next().next().next().next().children().find("li").eq(1).find("select");
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
		    							"<option value="+its.id+">"+its.code+ "  " + its.name + "</option>");
				});
           }
       });  
		  }
	  });
	  	//等待装车序列号递增加一行	
	 var add = (function(start){ 
		  count = start;
		    var iHtml = '';  
		    return function(){
		      iHtml = "<tr class='newTr'><td id="+ (count++) + " contentEditable='true'>"+ count +"</td><td style='width:150px'><select class='form-control'  name='chexing' onchange='gainSelectValue(this);'></select></td><td style='text-align: center'><input type='text'  maxLength='7' onkeyup='RepNumber(this)' style='width:80%;height:28px;border:1px solid #dde3ef; border-radius: 4px;'></td>" +
		      		"<td style='text-align: center'><input type='text'  oninput='entruckWeightLength(this)' style='width:80%;height:28px;border:1px solid #dde3ef; border-radius: 4px;'></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td><td style='text-align:center;'><button style='padding: 8px; border: 1px solid #dde3ef;' type=button onclick='$(this).parent().parent().remove()'>删除<button></td></tr>";//onkeydown='restrictLength(this)' oninput='if(value.length>7)value=value.slice(0,7)'
		      $(this).prev().append(iHtml);
		    	 $(".newTr [name=chexing]").FdSelect2({url:'/logistics-manage-web/api/basicdata/types/trainType ',type : 'trainType'});
		      
		    }//<td style='display:none'></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td>
	  })(0);
	 $("body").on("click","[name=moreAddBind]",add);
	  $("body").on("click","[name=moreAddVali]",addVali);
	  function addVali(){
		  var rowNum=$(".newTr").length;
		  var chengrencheshu = $("#chengrencheshu").val();
		  if(rowNum>=chengrencheshu){
			  $.fd.msg.notice("增加的车辆数不能超过承认车数！");
		  }else{
			  $(this).prev().click();
		  }
	  }
	  
	   //等待卸货添加更多货场货位
	  $("body").on("click",".addBtns",function(){
	  	  // $(this).parent().parent().next().next().hide();
	  	   $(this).hide();
	  	    //$(this).parent().children().find("select").addClass("selectSpan");
	  	count=1;
	  	$("#bigDivs").append("<div class='project_info'><ul class='waitUl'><li>卸货货场：<select></select></li><li>卸货货位：<select></select></li>\
	  	<li class='addBtns pull-right'><button class='btn btn-success' type='button'><img src='${pageContext.request.contextPath}/img/addbtn.png'/>添加更多货场货位</button></li></ul></div>\
		<table class='table loadingTable'><thead><tr class='tableTop'><th>序号</th><th>车型</th><th>车号</th><th>发货载重</th><th>到货载重</th><th>发货运单上传</th><th>到货运单上传</th></tr></thead>\
		<tbody><tr><td style='text-align:center'>1</td><td></td><td></td><td></td>\
		<td></td><td style='text-align:center'><a href='#'>点击上传</a></td><td style='text-align:center'><a href='#'>点击上传</a></td></tr></tbody></table>\
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
		$("#addModal").modal();
	})*/
//	等待承认
	/*$("#admit").click(function(){
		$("#admitId")[0].reset();
		$("#waitModal").modal();
		$('#showMask', window.parent.document).show();
	})*/
//	等待装车
	/*$("#loading").click(function(){
		$("#loadingId")[0].reset();
		$("#entruckingModal").modal();
		$('#showMask', window.parent.document).show();
	})*/
//	等待发运
		/*$("#send").click(function(){
		$("#sendId")[0].reset();
		$("#forwardingModal").modal();
		$('#showMask', window.parent.document).show();
	})*/
//	在途运载
	/*$("#carry").click(function(){
		$("#carryId")[0].reset();
		$("#locationModal").modal();
		$('#showMask', window.parent.document).show();
	})*/
	//等待卸货
	/*$("#unloading").click(function(){
		$("#unloadingId")[0].reset();
		$('#showMask', window.parent.document).show();
		$("#unloadingModal").modal();
		$('#showMask', window.parent.document).show();
	})	*/
//	等待回单
	/*$("#receipt").click(function(){
		$("#receiptId")[0].reset();
		$('#showMask', window.parent.document).show();
		$("#receiptModal").modal();
		$('#showMask', window.parent.document).show();
	})	*/	
	
	$("#allocate").click(function(){
		$(".allocate").hide();
		$(".bigDivs").show();
		$(".row").show()
	})
	
	//	导出模态框
  /*  $(".export").click(function(){ 
   var id=$(this).parent().parent().parent().attr("id");
    	if($("#"+ id +"  input[type=checkbox]:checked").length > 0) {
			$("#exportModal").modal();
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
    });*/
	
	// 导出
	  $("#export1").click(function(e) {
	    e.preventDefault();
	    var currentPage = $("#page_normal .current").html();
	    var condition = $("#searchForm").serializeJSON();
	    var conditionObj = objToUrl(condition)+"&pageNo="+currentPage;
	    window.location.href = "/logistics-manage-web/fireTrainCbulkLoading/export1"+conditionObj;
	  });
	// 导出
	  $("#export2").click(function(e) {
	    e.preventDefault();
	    var currentPage = $("#page_exception .current").html();
	    var condition = $("#searchForm").serializeJSON();
	    var conditionObj = objToUrl(condition)+"&pageNo="+currentPage;
	    window.location.href = "/logistics-manage-web/fireTrainCbulkLoading/export2"+conditionObj;
	  });
	  // 导出
	  $("#export3").click(function(e) {
	    e.preventDefault();
	    var currentPage = $("#page_history .current").html();
	    var condition = $("#searchForm").serializeJSON();
	    var conditionObj = objToUrl(condition)+"&pageNo="+currentPage;
	    window.location.href = "/logistics-manage-web/fireTrainCbulkLoading/export3"+conditionObj;
	  });
	// 导出
	  $("#export4").click(function(e) {
	    e.preventDefault();
	    var currentPage = $("#page_delete .current").html();
	    var condition = $("#searchForm").serializeJSON();
	    var conditionObj = objToUrl(condition)+"&pageNo="+currentPage;
	    window.location.href = "/logistics-manage-web/fireTrainCbulkLoading/export4"+conditionObj;
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
    });
    
  //发短信
    //正常运单列表
	$("#msgbtn1").click(function() {
		if($("#normalOrderBody input:checkbox[type='checkbox']:checked").length >0) {
			var length=$(" #normalOrderBody  input:checkbox[type='checkbox']:checked").length;
			$("#chooseNum").html(length);
			$("#messgageModal").modal();
			$("#noBussinss").html("");
			$("#sms_content").val("");
			$(".tips").hide();
   		//$("#MsgForm").validate().resetForm();
	    //$("#MsgForm")[0].reset();
		} else if($("#normalOrderBody input:checkbox[type='checkbox']:checked").length == 0) {
			$("#promptIdModal").modal();
			$("#errorMsg").html("");
			$("#errorMsg").html("请选择要操作的数据");
		} 
	});	
    
    //异常列表
    $("#msgbtn2").click(function() {
		if($("#exceptionBody input:checkbox[type='checkbox']:checked").length >0) {
			var length=$(" #exceptionBody  input:checkbox[type='checkbox']:checked").length;
			$("#chooseNum").html(length);
			$("#messgageModal").modal();
			$("#noBussinss").html("");
			$("#sms_content").val("");
			$(".tips").hide();
   		//$("#MsgForm").validate().resetForm();
	    //$("#MsgForm")[0].reset();
		} else if($("#exceptionBody input:checkbox[type='checkbox']:checked").length == 0) {
			$("#promptIdModal").modal();
			$("#errorMsg").html("");
			$("#errorMsg").html("请选择要操作的数据");
		} 
	});	
    
    //历史列表
    $("#msgbtn3").click(function() {
		if($("#historyOrderBody input:checkbox[type='checkbox']:checked").length >0) {
			var length=$(" #historyOrderBody  input:checkbox[type='checkbox']:checked").length;
			$("#chooseNum").html(length);
			$("#messgageModal").modal();
			$("#noBussinss").html("");
			$("#sms_content").val("");
			$(".tips").hide();
   		//$("#MsgForm").validate().resetForm();
	    //$("#MsgForm")[0].reset();
		} else if($("#historyOrderBody input:checkbox[type='checkbox']:checked").length == 0) {
			$("#promptIdModal").modal();
			$("#errorMsg").html("");
			$("#errorMsg").html("请选择要操作的数据");
		} 
	});	
    
    //回收站列表
    $("#msgbtn4").click(function() {
		if($("#deleteOrderBody input:checkbox[type='checkbox']:checked").length >0) {
			var length=$(" #deleteOrderBody  input:checkbox[type='checkbox']:checked").length;
			$("#chooseNum").html(length);
			$("#messgageModal").modal();
			$("#noBussinss").html("");
			$("#sms_content").val("");
			$(".tips").hide();
   		//$("#MsgForm").validate().resetForm();
	    //$("#MsgForm")[0].reset();
		} else if($("#deleteOrderBody input:checkbox[type='checkbox']:checked").length == 0) {
			$("#promptIdModal").modal();
			$("#errorMsg").html("");
			$("#errorMsg").html("请选择要操作的数据");
		} 
	});	
});
function RepNumber(obj) {
    var reg = /^[\d]+$/g;
     if (!reg.test(obj.value)) {
         var txt = obj.value;
         txt.replace(/[^0-9]+/, function (char, index, val) {//匹配第一次非数字字符
            obj.value = val.replace(/\D/g, "");//将非数字字符替换成""
            var rtextRange = null;
            if (obj.setSelectionRange) {
                obj.setSelectionRange(index, index);
            } else {//支持ie
                rtextRange = obj.createTextRange();
                rtextRange.moveStart('character', index);
                rtextRange.collapse(true);
                rtextRange.select();
            }
        })
     }
 }