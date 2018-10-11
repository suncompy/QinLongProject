$(function() {
	//分配任务模态框
	/* $(".distribtion").click(function(){
      	$("#distributionId")[0].reset();
	$("#distributionModal").modal();
      })*/

	//获取项目列表
	$('#searchSpan').click();

	//获取搜索条件下拉框
	listSearchParam();

	//开始
	$(".start").click(function() {
		if($("#panel1  input[type=checkbox]:checked").length > 0) {
			var length = $("#panel1  input[type=checkbox]:checked").length;
			$(".errnums").html(length);
			$('#deltialModal input[name=param]').val(1);
			$("#msgSpan").html("条数据,确认开始？")
			$("#deltialModal").modal();
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
	});
	//暂停
	$(".pause").click(function() {
		if($("#panel1  input[type=checkbox]:checked").length > 0) {
			var length = $("#panel1  input[type=checkbox]:checked").length;
			$(".errnums").html(length);
			$("#msgSpan").html("条数据,确认暂停？")
			$('#deltialModal input[name=param]').val(2);
			$("#deltialModal").modal();
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
	});

	//确认操作后页面刷新
	$("#sure_msg").click(function() {
		window.location.reload();
		$("#promptIdModal").hide();
		$('#showMask', window.parent.document).hide();
	}); 
})

/**
 * 分配获取详情
 * */
function openDis(id, curType) {
	
	
	var isDistribution = $("#isDistribution").val();
	if(isDistribution==1){
		$.fd.ajax({
			type: "GET",
			url: $.getUrl('/business/short/job/distribute/getMsg.do?id='+id),
			success: function(data) {
				var unit = "";
				if(data.data.valuationUnitName == 1) {
					unit = "吨";
				} else if(data.data.valuationUnitName == 0) {
					unit = "件";
				}
				$("#showPro").append("<li>项目编号：<span>" + data.data.projectCode + "</span></li>" +
					"<li>货物品名：<span>" + data.data.cargoName + "</span></li>" +
					"<li>货物规格：<span>" + data.data.cargoSpecifications + "</span></li>" +
					"<li>货物单价：<span>" + data.data.cargoPrice + "元</span></li>" +
					"<li>计价单位：<span>" + unit + "</span></li>");
				$("#projectId").val(data.data.id);
				$("#projectType").val(curType);
				$("#distributionModal").modal();
			}
		});
		$("#showPro").html("");
	}else{
		$("#msg").html("您没有该权限，请与管理员联系!");
		$("#promptIdModal").modal();
	}
	
}

/**
 * 任务分配
 * */
function subPut() {
	$.ajax({
		cache: true,
		type: "POST",
		url: $.getUrl('/business/short/job/distribute/put.do'),
		data: $('#distributionId').serialize(),
		dataType: 'json',
		async: false,
		success: function(data) {
			if(data.status != 1) {
				$("#distributionModal").modal("hide");
				$("#msg").html(data.msg);
				$("#promptIdModal").modal();
			} else {
				$("#distributionModal").modal("hide");
				$("#msg").html(data.msg);
				$("#promptIdModal").modal();
				$("#projectId").val("");
				$("#projectType").val("");
			}
		}
	});
}

/**
 * 控制开始还是暂停任务
 * */
function controller() {
	var type = $('#deltialModal input[name=param]').val(); //1 开始 2 暂停
	//开始
	if(type == 1) {
		beginTask()
	}
	//暂停
	if(type == 2) {
		stopTask()
	}
}

/**
 * 开始任务
 * */
function beginTask() {
	var flag = true;
	var idList = new Array();
	var typeList = new Array();

	$.each($('input:checkbox'), function() {
		if(this.checked) {
			var str = $(this).val();
			var arr = new Array();
			arr = str.split(",");
			//取，判断是否是暂停状态
			if(arr[1]==0 || arr[1]==1){//判断状态
				$("#msg").html("勾选有误,请勾选暂停状态下的项目");
				$("#promptIdModal").modal();
				flag = false;
				return;
			}
			typeList.push(arr[2]);
			idList.push(arr[0]);
		}
	});
	if(flag) {
		$.ajax({
			type: "POST",
			url: $.getUrl('/business/short/job/distribute/begin.do'),
			dataType: 'json',
			data: {
				"idList": idList,
				"typeList": typeList
			},
			async: false,
			success: function(data) {
				if(data.status != 1) {
					$("#msg").html(data.msg);
					$("#promptIdModal").modal();
				} else {
					$("#msg").html(data.msg);
					$("#promptIdModal").modal();
				}
			}
		});
	}

}

/**
 * 暂停任务
 * */
function stopTask() {
	var flag = true;
	var idList = new Array();
	var typeList = new Array();
	$.each($('input:checkbox'), function() {
		if(this.checked) {
			var str = $(this).val();
			var arr = new Array();
			arr = str.split(","); //arr[0] projectId ; arr[1] status ; arr[2] taskType 
			//alert(arr); 74,2,1
			if(arr[1]==0 || arr[1]==2){//判断状态
				$("#msg").html("勾选有误,请勾选分配中状态下的项目");
				$("#promptIdModal").modal();
				flag = false;
				return;
			}
			typeList.push(arr[2]);
			idList.push(arr[0]);
		}
	});
	if(flag) {
		$.ajax({
			type: "POST",
			url: $.getUrl('/business/short/job/distribute/stop.do'),
			dataType: 'json',
			data: {
				"idList": idList,
				"typeList": typeList
			},
			async: false,
			success: function(data) {
				if(data.status != 1) {
					$("#msg").html(data.msg);
					$("#promptIdModal").modal();
				} else {
					$("#msg").html(data.msg);
					$("#promptIdModal").modal();
				}
			}
		});
	}
}

/**
 * 将查询结果写入到Table中
 * */
function htmlTable(results) {
	var projectList = results;
	$('#listbody').html("");
	$.each(projectList, function(index, it) {

		var proType = "";
		if(it.projectType == 0) {
			proType = "集装箱";
		} else if(it.projectType == 1) {
			proType = "散装";
		}

		var stu = "";
		if(it.status == 0) {
			stu = "未分配";
		}else if(it.status == 1) {
			stu = "分配中";
		}else if(it.status == 2){
			stu = "暂停分配";
		}

		var types = "";
		if(it.taskType == 1) {
			types = "接取";
		}
		if(it.taskType == 2) {
			types = "送达";
		}
		if(it.taskType == 3) {
			types = "汽运";
		}

		var plat = "";
		if(it.shortBargeCarrierMode == 0) {
			plat = "平台";
		}
		if(it.shortBargeCarrierMode == 1) {
			plat = "自选";
		}
		$('#listbody').append("<tr ><td style='text-align: center;'><label class='demo--label'>" +
			"<input class='demo--checkbox' type='checkbox' value='" + it.id + "," + it.status + "," + it.taskType + "'>" +
			"<span class='demo--checkboxInput'></span></label></td>" +
			"<td><a href='javascript:;'>" + it.projectCode + "</a></td>" +
			"<td>" + proType + "</td>" +
			"<td>" + types + "</td>" +
			"<td><a href='#' class='distribtion' onclick='openDis(" + it.id + "," + it.taskType + ")'>分配</a></td>" +
			"<td>" + stu + "</td>" +
			"<td>" + it.alreadyRecNum + "</td>" +
			"<td>" + it.waitRecNum + "</td>" +
			"<td>" + it.completeTodayNum + "</td>" +
			"<td>" + it.completeNumSum + "</td>" +
			"<td>" + it.branchGroupName + "</td>" +
			"<td>" + it.sendCargoCompanyName + "</td>" +
			"<td>" + it.receiveCargoCompanyName + "</td>" +
			"<td>" + it.cargoName + "</td>" +
			"<td>" + plat + "</td>" +
			"<td>" + it.shortBargeCarrierName + "</td>" +
			"</tr>");
	})
}
