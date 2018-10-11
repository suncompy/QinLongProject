$(function() {

	var tale = new $.tale();
	
	//获取项目列表
   	$('#searchSpan').click();
   	
   	/**
	 * -添加分支模态框 -
	 * */
	$("#addBranch").click(function() {
		$("#addBranchId").val("");
		$("#ascriptionIdDiv").show();
		$('#myModalLabel').html("添加分支");
		$('#addBranchModal input[name="name"]').attr("readonly",false);
		$('#addBranchModal input[name="shortName"]').attr("readonly",false);
		$('#addBranchModal input[name="unrealCode"]').attr("readonly",false);
		$("#city_first").citySelect({
			prov: "新疆",
			city: "乌鲁木齐",
			dist: "新市区",
			nodata: "none"
		});
		$("#addForm").validate().resetForm();
		$("#addForm")[0].reset();
		$("#addBranchModal").modal();
		$("#relationUl").html("");
		
		var imgUrl = $.getUrl('/img/add1.png')
		
		$("#relationUl").append("<li>" +
			"<img src='"+imgUrl+"' class='addImg' />" +
			"</li>")
	})
   	
	/**
	 * -新建网点分支-
	 * */
	$("#addBranchBtn").click(function() {
		var hideBranchId = $("#addBranchId").val();
		if(hideBranchId == null || hideBranchId == "" || hideBranchId == undefined) {
			var array = new Array();
			$("#relationUl form").each(function(j, item) {
				var data = JSON.stringify($(this).serializeJSON());
				array[j] = data;
			})
			$('input[name="relationBeginLocation"]').val("[" + array + "]");
			$('#addBranchModal input[name=ascriptionName]').val($('select[name=ascriptionId] option:selected').text()); //归属名
			
			//code
			var codePrifex = $('#addBranchModal span[name=codePrifex]').text();
			var code =  $('#addBranchModal input[name=unrealCode]').val();
			if(codePrifex!=undefined && codePrifex != null && codePrifex != ""){
				code = codePrifex +  code ;
			}
			$('#addBranchModal input[name=code]').val(code); //code
			
			var formParam = $("#addForm").serialize(); //序列化表格内容为字符串 
			$.ajax({
				type: 'post',
				url: $.getUrl('/humanOrganization/addDotBranch.do'),
				data: formParam,
				cache: false,
				dataType: 'json',
				success: function(data) {
					if(data.status == 200) {
						$("#addBranchModal").modal("hide");
	                	tale.alertOkAndReload("新增网点分支成功");
					} else {
						tale.alertWarn( data.msg || '登录失败');
						$('#showMask', window.parent.document).hide();
					}
				}
			})
		} else {
			var array = new Array();
			$("#relationUl form").each(function(j, item) {
				var data = JSON.stringify($(this).serializeJSON());
				array[j] = data;
			})
			$('input[name="relationBeginLocation"]').val("[" + array + "]");
			$('input[name=ascriptionName]').val($('select[name=ascriptionId] option:selected').text()); //归属名
			var formParam = $("#addForm").serialize(); //序列化表格内容为字符串 
			$.ajax({
				type: 'post',
				url: $.getUrl('/humanOrganization/updateDotBranch.do'),
				data: formParam,
				cache: false,
				dataType: 'json',
				success: function(data) {
					if(data.status == 200) {
						$("#addBranchModal").modal("hide");
	                	tale.alertOkAndReload("修改网点分支成功");
					} else {
						tale.alertWarn( data.msg || '登录失败');
						$('#showMask', window.parent.document).hide();
					}
				}
			})
		}
	})
	
	/**
	 * --删除网点分支校验--
	 * */
	$("#delBtn").click(function() {
		if($("input:checkbox[type='checkbox']:checked").length > 1) {
			tale.alertWarn("抱歉,不能批量删除!");
			$('#showMask', window.parent.document).hide();
		} else if($("input:checkbox[type='checkbox']:checked").length == 0) {
	    	tale.alertWarn("请选择一条您要删除的网点分支信息");
	    	$('#showMask', window.parent.document).hide();
		} else {
			 tale.alertConfirm({
	            title:"确认删除该网点分支?",
	            then: function () {
	            	delBranchGroup();
	            }
	        });
		}
	});
	
})

/**
 * --选网点归属之后 编码增加前缀--
 * */
function changeCode(){
	var code = $("#addBranchModal select[name=ascriptionId] option:selected").attr("code");
	if(code==undefined || code == null || code == ""){
		$("#addBranchModal span[name=codePrifex]").css("display","none");
		$("#addBranchModal span[name=codePrifex]").html("");
	}else{
		$("#addBranchModal span[name=codePrifex]").css("display","");
		$("#addBranchModal span[name=codePrifex]").html(code);
	}
}

/**
 * --增加或修改 窗口 点击取消之后的操作--
 * */
function cancle(){
	$('input[name=level]').val("");//二级
	$('input[name=ascriptionId]').val("");//归属id
	$('input[name=ascriptionName]').val("");//归属名
}

/**
 * --删除网点分支方法--
 * */
function delBranchGroup(){
	var tale = new $.tale();
 	var id = $("input:checkbox[type='checkbox']:checked").val();
 	var url = $.getUrl('/humanOrganization/delDotBranch.do');
	$.ajax({  
	type:'post',      
	url: url , 
    data:{id:id},
    cache:false,
    dataType:'json',
	success:function(result){
		if(result.status==200){
    			tale.alertOkAndReload("删除网点分支成功!");
    			$('#showMask', window.parent.document).hide();
    		}else{
    			tale.alertError(result.msg || '登录失败');
    			$('#showMask', window.parent.document).hide();
    		}
   	 	}
	})
}

/**
 * --将分页返回结果写入表格--
 * */
function htmlTable(results){
	var dotBranchs = results;
	$('#dotBranchTbody').html("");
	$.each(dotBranchs,function(index,dotBranch){
		$('#dotBranchTbody').append("<tr>"
			+"<td style='text-align: center;'><label class='demo--label'>"
			 +" <input class='demo--checkbox' value='"+dotBranch.id+"' type='checkbox'   >"
              +"<span class='demo--checkboxInput'></span>"
             +"</label></td>"
		 +"<td id=''>"+dotBranch.code+"</td>"
		  +"<td id=''>"+dotBranch.name+"</td>"
		  +"<td >"+dotBranch.shortName+"</td>"
		  +"<td >"+dotBranch.shortCode+"</td>"
		 +"<td name='parentBotBranch"+index+"'></td>"
		  +"<td >"+dotBranch.responsibler+"</td>"
		  +"<td >"+dotBranch.employeeNum+"</td>"
		  +"<td id=''>"+dotBranch.areaId+dotBranch.address+"</td>"
		 +"<td name='relationStation"+index+"'></td>"
		  +"<td id=''>"+dotBranch.relationBeginLocation+"</td>"
		  +"<td id=''>"+dotBranch.comment+"</td>"
		 +"</tr>")
		//判断上级分支是否为null
		if(dotBranch.branchGroup!=null){
			$("td[name='parentBotBranch"+index+"']").html(dotBranch.branchGroup.name);
		}
		//判断火车站点是否为null
		if(dotBranch.station!=null){
			$("td[name='relationStation"+index+"']").html(dotBranch.station.stationName);
		}
	})	
}