$(function() {
	//获取项目列表
	$('#searchSpan').click();

	//获取搜索条件下拉框
	listSearchParam();

	//选项切换清空复选框
	$(".tabbable .nav li").click(function() {
		$("input[type=checkbox]").prop("checked", false);
	});

	$(".modal tr").each(function() {
		$(this).children('td:eq(0)').css('white-space', 'nowrap');
	});

	/**
	 * 修改项目信息模态框
	 */
	$("#modify").click(function() {
		//获取当前选中的对象
		if($("input:checkbox[name='WaybillName']:checked").length > 1) {
			$('#warnMsg').html('抱歉,不能批量修改!');
			$("#deletRemindModal").modal();
			return;
		} else if($("input:checkbox[name='WaybillName']:checked").length == 0) {
			$("#msgContent").html("请选择一条您要操作的数据");
			$("#deletnullModal").modal();
			return;
		}
		$("input:checkbox[name='WaybillName']:checked").parent().parent().parent().find("td").eq(2).click();
		var mtext = $("input:checkbox[name='WaybillName']:checked").parent().parent().parent().find("td").eq(3).html();
		$("#mtext").val(mtext);
		$(".modifyModal .modal-title").html("项目修改");
		$(".history_modify").hide();
		$(".confirm-modify").show();
		$(".modify-info").show();
		$('.hidebtn').show();

		if($("#mtext").val() == "汽运") {
			$("#truckModal").modal();
			$("#mtext").val("");
		} else if($("#mtext").val() == "接取") {
			$("#receveModal").modal();
			$("#mtext").val("");
		} else if($("#mtext").val() == "送达") {
			$("#sendModal").modal();
			$("#mtext").val("");
		} else if($("#mtext").val() == "火运") {
			$("#trainModal").modal();
			$("#mtext").val("");
		} else if($("#mtext").val() == "接取+火运") {
			$("#receveAndTrainModal").modal();
			$("#mtext").val("");
		} else if($("#mtext").val() == "火运+送达") {
			$("#trainAndSendModal").modal();
			$("#mtext").val("");
		} else if($("#mtext").val() == "接取+送达") {
			$("#receveAndSendModal").modal();
			$("#mtext").val("");
		} else if($("#mtext").val() == "联运") {
			$("#unionModal").modal();
			$("#mtext").val("");
		}
	});
	//	修改
	$(".modifyModal input").attr("readonly", true);
	$(".modifyModal input").addClass("disabledBg");
	$(".modifyModal select").attr("readonly", true);
	$(".modifyModal select").addClass("disabledBg");
	$(".modify-info").click(function() {
		$(this).parent().find("input").removeAttr("readonly");
		$(this).parent().find("input").removeAttr("readonly").removeClass("disabledBg");
		$(this).parent().find("select").removeAttr("readonly").removeClass("disabledBg");
		$(this).hide().prev().show();
	})

	$(".confirm-modify").click(function() {
		$(this).hide().next().show();
		$(this).parent().find("input").attr("readonly", "true");
		$(this).parent().find("input").attr("readonly", "true").addClass("disabledBg");
		$(this).parent().find("select").attr("readonly", "true").addClass("disabledBg");
	})

	//删除
	$("#delBtn").click(function() {
		$('#showMask', window.parent.document).hide();
		$("#delProject").val("");
		if($("input:checkbox[name='WaybillName']:checked").length > 1) {
			$("#deletRemindModal").modal();
		} else if($("input:checkbox[name='WaybillName']:checked").length == 0) {
			$("#nullModal").modal();
		} else {
			//			$("#deletId")[0].reset();
			$(".textareas").val("");
			$("#delProject").val($("input:checkbox[name='WaybillName']:checked").val());
			$("#deletReasonModal").modal();
		}
	});
	//	导出模态框
	$(".export").click(function() {
		var id = $(this).parent().parent().parent().attr("id");
		if($("#" + id + "  input[type=checkbox]:checked").length > 0) {
			$("#exportModal").modal();
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
	})
	//	还原
	$(".rest").click(function() {
		$('#restId').val("");
		var id = $(this).parent().parent().parent().attr("id");
		var value = $("#" + id + " input[type=checkbox]:checked").val();
		$('#restId').val(value);
		if($("#" + id + " input[type=checkbox]:checked").length == 0) {
			$("#nullModal").modal();
			return;
		} else if($("#" + id + " input[type=checkbox]:checked").length > 1) {
			$("#deletRemindModal").modal();
			$("#warnMsg").html("抱歉！不可批量还原！")
		} else {
			$("#deltialModal").modal();
			$("#msgSpan").html("条数据，是否还原？")
			$("#nullModal").modal("hide");
		}
	})

	//运单查看
	$("body").on("click", "#panel1 tbody tr td:not(:nth-child(1))", function() {
		$(".history_detail").hide();
		$(".del-info").hide();
		$('#showMask', window.parent.document).show();
		$(".detailModal .modal-title").html("项目详情");
		$(".history_modify").show();
		$(".confirm-modify").hide();
		$(".modify-info").hide();
		$('.hidebtn').hide();
		$(".addCarrier").hide(); 
		var projectId = "";
		if($(this).parent().find("td").eq(3).html() == "联运") {
			projectId = $(this).parent().attr("id");
		} else if($(this).parent().find("td").eq(3).html() == "接取") {
			projectId = $(this).parent().attr("id");
		} else if($(this).parent().find("td").eq(3).html() == "汽运") {
			projectId = $(this).parent().attr("id");
		} else if($(this).parent().find("td").eq(3).html() == "送达") {
			projectId = $(this).parent().attr("id");
		} else if($(this).parent().find("td").eq(3).html() == "火运") {
			projectId = $(this).parent().attr("id");
		} else if($(this).parent().find("td").eq(3).html() == "接取+火运") {
			projectId = $(this).parent().attr("id");
		} else if($(this).parent().find("td").eq(3).html() == "火运+送达") {
			projectId = $(this).parent().attr("id");
		} else if($(this).parent().find("td").eq(3).html() == "接取+送达") {
			projectId = $(this).parent().attr("id");
		}
		if(projectId == undefined || projectId == null || projectId == "") {
			$("#msgContent").html("");
			$("#msgContent").html("查询不到此项目的详细信息");
			$("#deletnullModal").modal();
			return;
		}
		getProjectDetail(projectId);
		$(this).parent().toggleClass("bgclass").siblings("#panel1 tbody tr").removeClass("bgclass");
	});
	//历史项目
	$("body").on("click", "#panel2 tbody tr td:not(:nth-child(1))", function() {
		$(".history_detail").show();
		$(".del-info").hide();
		$('#showMask', window.parent.document).show();
		$(".detailModal .modal-title").html("项目详情");
		$(".history_modify").show();
		$(".confirm-modify").hide();
		$(".modify-info").hide();
		$('.hidebtn').hide();
		$(".addCarrier").hide();
		var projectId = "";
		if($(this).parent().find("td").eq(3).html() == "联运") {
			projectId = $(this).parent().attr("id");
		} else if($(this).parent().find("td").eq(3).html() == "接取") {
			projectId = $(this).parent().attr("id");
		} else if($(this).parent().find("td").eq(3).html() == "汽运") {
			projectId = $(this).parent().attr("id");
		} else if($(this).parent().find("td").eq(3).html() == "送达") {
			projectId = $(this).parent().attr("id");
		} else if($(this).parent().find("td").eq(3).html() == "火运") {
			projectId = $(this).parent().attr("id");
		} else if($(this).parent().find("td").eq(3).html() == "接取+火运") {
			projectId = $(this).parent().attr("id");
		} else if($(this).parent().find("td").eq(3).html() == "火运+送达") {
			projectId = $(this).parent().attr("id");
		} else if($(this).parent().find("td").eq(3).html() == "接取+送达") {
			projectId = $(this).parent().attr("id");
		}
		if(projectId == undefined || projectId == null || projectId == "") {
			$("#msgContent").html("");
			$("#msgContent").html("查询不到此项目的详细信息");
			$("#deletnullModal").modal();
			return;
		}
		getProjectDetail(projectId);
		return;
		$(this).parent().toggleClass("bgclass").siblings("#panel1 tbody tr").removeClass("bgclass");
	});
	//项目回收站
	$("body").on("click", "#panel3 tbody tr td:not(:nth-child(1))", function() {
		$(".history_detail").hide();
		$(".del-info").show();
		$('#showMask', window.parent.document).show();
		$(".detailModal .modal-title").html("项目详情");
		$(".history_modify").show();
		$(".confirm-modify").hide();
		$(".modify-info").hide();
		$('.hidebtn').hide();
		$(".addCarrier").hide();
		var projectId = "";
		if($(this).parent().find("td").eq(3).html() == "联运") {
			projectId = $(this).parent().attr("id");
		} else if($(this).parent().find("td").eq(3).html() == "接取") {
			projectId = $(this).parent().attr("id");
		} else if($(this).parent().find("td").eq(3).html() == "汽运") {
			projectId = $(this).parent().attr("id");
		} else if($(this).parent().find("td").eq(3).html() == "送达") {
			projectId = $(this).parent().attr("id");
		} else if($(this).parent().find("td").eq(3).html() == "火运") {
			projectId = $(this).parent().attr("id");
		} else if($(this).parent().find("td").eq(3).html() == "接取+火运") {
			projectId = $(this).parent().attr("id");
		} else if($(this).parent().find("td").eq(3).html() == "火运+送达") {
			projectId = $(this).parent().attr("id");
		} else if($(this).parent().find("td").eq(3).html() == "接取+送达") {
			projectId = $(this).parent().attr("id");
		}
		if(projectId == undefined || projectId == null || projectId == "") {
			$("#msgContent").html("");
			$("#msgContent").html("查询不到此项目的详细信息");
			$("#deletnullModal").modal();
			return;
		}
		getProjectDetail(projectId);
		return;
		$(this).parent().toggleClass("bgclass").siblings("#panel1 tbody tr").removeClass("bgclass");
	});
	/**
	 * 新建项目添加更多承运方式-
	 * */
	for(var i = 1; i <= 9; i++) {
		$(".addBtn" + i).hide();
		$("#select" + i).addClass("bgclass");
	}

	$(".shortSelect").change(function() {
		var className = this.className.split(" ")[1];
		var type = className.substr(className.length - 1, 1);
		var mValue = $(this).val();

		if(mValue == "0") {
			$("div[name='platformShortBarge" + type + "']").html("<div class='form-inline'>" +
				"<div class='form-group'><label>&emsp;短驳承运方：</label><input type='text' readonly='readonly' name='shortBargeName' value='平台' /></div>" +
				"<div class='form-group short-client'><label>运输单价：</label><input name='transportPrice' type='text'><span>元</span></div>" +
				"<div class='form-group short-client'><label>扣损比率：</label><input name='deductionRate' type='text'><span>‰</span></div>" +
				"<div class='form-group short-client'><label>扣损单价：</label><input name='deductionPrice' type='text'><span>元</span></div>" +
				"<div class='form-group short-client'><label>付款周期：</label> <select name='payment'>" +
				"<option value='0'>日结</option>" +
				"<option value='1'>周结</option>" +
				"<option value='2'>月结</option>" +
				"</select></div>" +
				"</div >")
			$("#select" + type).attr("readOnly", "true");
			$("#select" + type).addClass("bgclass")
			$(".addBtn" + type).hide();
			$("#motor" + type).children().remove();
		} else if(mValue == "1") {
			$("div[name='platformShortBarge" + type + "']").html("");
			$("#select" + type).attr("readOnly", "false");
			$("#select" + type).removeClass("bgclass")
			$(".addBtn" + type).show();
			$("#motor" + type).children().remove();
		}
	})
})

/**
 * 查看项目列表 或历史项目或项目回收站
 * */
function listProject(status) {
	var page = 'projectPage';
	if(status == 1) {
		$("#searchForm input[name=status]").val("1"); //项目列表
		page = 'projectPage';
	} else if(status == 2) {
		$("#searchForm input[name=status]").val("2"); //历史项目
		page = 'historyPage';
	} else {
		$("#searchForm input[name=status]").val("0"); //项目回收站
		page = 'recyclePage';
	}

	$.ajax({
		type: 'post',
		url: $.getUrl('/projectManagment/getProjectPage.do'),
		data: {
			page: 1,
			status: status,
			search: JSON.stringify($('#searchForm').serializeJSON())
		},
		cache: false,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				htmlTable(data.data.rows);
				$("#" + page + "").paging({
					pageNo: 1,
					totalPage: data.data.totalPage,
					totalSize: data.data.limit,
					callback: function(num) {
						searchByPage(num,'/projectManagment/getProjectPage.do');
					}
				})
			} else {
				$("#msgContent").html("");
				$("#msgContent").html(data.msg);
				$("#deletnullModal").modal();
			}
		}
	})
}

/**
 * 逻辑删除项目
 * */
function delProject() {
	var id = $("#delProject").val();
	var reason = $("#reason").val();
	$.ajax({
		type: 'post',
		url: $.getUrl('/projectManagment/delProject.do'),
		data: {
			id: id,
			reason: reason
		},
		cache: false,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				$("#deletReasonModal").modal("hide");
				$("#successMsg").html("");
				$("#successMsg").html("删除项目成功");
				$("#successModal").modal();
			} else {
				$("#msgContent").html("");
				$("#msgContent").html(data.msg);
				$("#deletnullModal").modal();
			}
		}
	})
}

/**
 * 项目完成
 * */
$("#complete").click(function() {
	$("#delProject").val("");
	if($("input:checkbox[name='WaybillName']:checked").length > 1) {
		//$("#deletRemindModal").modal();
	} else if($("input:checkbox[name='WaybillName']:checked").length == 0) {
		//$("#nullModal").modal();
	} else {
		//$(".textareas").val("");
		$("#delProject").val($("input:checkbox[name='WaybillName']:checked").val());
		//$("#deletReasonModal").modal();
	}
	var id = $("#delProject").val();
	$.ajax({
		type: 'post',
		url: $.getUrl('/projectManagment/completeProject.do'),
		data: {
			id: id
		},
		cache: false,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				$("#deletReasonModal").modal("hide");
				$("#successMsg").html("");
				$("#successMsg").html("项目确认完成!");
				$("#successModal").modal();
			} else {
				$("#msgContent").html("");
				$("#msgContent").html(data.msg);
				$("#deletnullModal").modal();
			}
		}
	})
})

/**
 * 项目还原
 * */
function restProject() {
	var id = $("#restId").val();
	$.ajax({
		type: 'post',
		url: $.getUrl('/projectManagment/restoreProject.do'),
		data: {
			id: id
		},
		cache: false,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				$("#deletRemindModal").modal("hide");
				$("#successMsg").html("");
				$("#successMsg").html("项目还原成功!");
				$("#successModal").modal();
			} else {
				$("#deltialModal").modal("hide");
				$("#msgContent").html("");
				$("#msgContent").html(data.msg);
				$("#deletnullModal").modal();
			}
		}
	})
}

/**
 * 搜索
 * */
function projectSearch(path) {
	var status = $('#searchForm input[name=status]').val();
	var page = 'projectPage';
	if(status == 1) {
		page = 'projectPage';
	} else if(status == 2) {
		page = 'historyPage';
	} else {
		page = 'recyclePage';
	}
	$.ajax({
		type: 'post',
		url: $.getUrl(path),
		data: {
			page: 1,
			status: status,
			search: JSON.stringify($('#searchForm').serializeJSON())
		},
		cache: false,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				htmlTable(data.data.rows);
				$("#" + page + "").paging({
					pageNo: 1,
					totalPage: data.data.totalPage,
					totalSize: data.data.limit,
					callback: function(num) {
						searchByPage(num, path);
					}
				})
			}
		}
	})
}

/**
 * 获取项目详情
 * */
function getProjectDetail(projectId) {
	var carTeams = "";
	/*异步提交*/
	$.ajax({
		type: 'post',
		url: $.getUrl('/transport/listCarTeams.do'),
		dataType: 'json',
		asasync: false,
		success: function(data) {
			if(data.status == 200) {
				$.each(data.data, function(index, carTeam) {
					carTeams = carTeams + "<option  value='" + carTeam.id + "'>" + carTeam.carItemName + "</option>"
				})
			}
		}
	})
	var shortBarges = "";

	$.ajax({
		type: 'post',
		url: $.getUrl('/projectManagment/getProject.do'),
		data: {
			id: projectId
		},
		cache: false,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				var projectDetail = data.data;

				//项目类型
				var projectType = "";
				if(projectDetail.projectType == 0) {
					projectType = "集装箱";
				} else if(projectDetail.projectType == 1) {
					projectType = "散装";
				}

				//模态框id
				var modal = "";

				//联运模式
				var transportType = "";
				if(projectDetail.transportType == 0) {
					transportType = "汽运";
					modal = "truckModal";
				} else if(projectDetail.transportType == 1) {
					transportType = "接取";
					modal = "receveModal";
				} else if(projectDetail.transportType == 2) {
					transportType = "送达";
					modal = "sendModal";
				} else if(projectDetail.transportType == 3) {
					transportType = "火运";
					modal = "trainModal";
				} else if(projectDetail.transportType == 4) {
					transportType = "接取+火运";
					modal = "receveAndTrainModal";
				} else if(projectDetail.transportType == 5) {
					transportType = "火运+送达";
					modal = "trainAndSendModal";
				} else if(projectDetail.transportType == 6) {
					transportType = "联运";
					modal = "unionModal";
				} else if(projectDetail.transportType == 7) {
					transportType = "接取+送达";
					modal = "receveAndSendModal";
				}
				if(modal == "") {
					return;
				}
				$("#" + modal + " span[name=branchGroupName]").html(projectDetail.branchGroupName);

				$("#" + modal + " span[name=projectType").html(projectType);

				$("#" + modal + " input[name=id]").val(projectDetail.id);

				$("#" + modal + " span[name=branchGroupName]").html(projectDetail.branchGroupName);

				$("#" + modal + " span[name=branchGroupName]").html(projectDetail.branchGroupName);

				$("#" + modal + " span[name=transportType]").html(transportType);
				//委托方
				var principal = "";
				if(projectDetail.principal == 0) {
					principal = "收货企业";
				} else if(projectDetail.principal == 1) {
					principal = "发货企业";
				}
				$("#" + modal + " span[name=principal]").html(principal);

				//完成时间
				$("#" + modal + " span[name=finishDate]").html(projectDetail.finishDate);

				//货物品名
				$("#" + modal + " span[name=cargoName]").html(projectDetail.cargoName);
				//货物规格
				$("#" + modal + " span[name=cargoSpecifications]").html(projectDetail.cargoSpecifications);
				//货物价格
				$("#" + modal + " span[name=cargoPrice]").html(projectDetail.cargoPrice);
				//计价单位
				var valuationUnitName = "";
				if(projectDetail.valuationUnitName == 0) {
					valuationUnitName = "件";
				} else if(projectDetail.valuationUnitName == 1) {
					valuationUnitName = "吨";
				}
				$("#" + modal + " span[name=valuationUnitName]").html(valuationUnitName);

				//发货企业
				$("#" + modal + " span[name=sendCargoCompanyName]").html(projectDetail.sendCargoCompanyName);
				$("#" + modal + " span[name=sendCargoCompanyPhone]").html(projectDetail.sendCargoCompany.stationPhone);
				$("#" + modal + " span[name=sendCargoCompanyAddress]").html(projectDetail.sendCargoCompany.addressCode + projectDetail.sendCargoCompany.detailAddress);
				//收货企业
				$("#" + modal + " span[name=receiveCargoCompanyName]").html(projectDetail.receiveCargoCompanyName);
				$("#" + modal + " span[name=receiveCargoCompanyPhone]").html(projectDetail.receiveCargoCompany.stationPhone);
				$("#" + modal + " span[name=receiveCargoCompanyAddress]").html(projectDetail.receiveCargoCompany.addressCode + projectDetail.receiveCargoCompany.detailAddress);

				if(projectDetail.transportType == 1 || projectDetail.transportType == 4 || projectDetail.transportType == 6 || projectDetail.transportType == 7) {
					//发货单位
					$("#" + modal + " span[name=sendCargoUnitName]").html(projectDetail.sendCargoUnitName);
					$("#" + modal + " span[name=sendCargoAreaCode]").html(projectDetail.sendCargoUnit.addressCode);
					$("#" + modal + " span[name=sendCargoAddress]").html(projectDetail.sendCargoUnit.detailAddress);
					//收货站点
					$("#" + modal + " span[name=receiveCenterCargoSiteName]").html(projectDetail.receiveCenterCargoSiteName);
					$("#" + modal + " span[name=receiveCargoSite]").html(projectDetail.receiveTrainStation.stationName);
					$("#" + modal + " span[name=receiveCargoSiteFreightYard]").html(projectDetail.receiveCargoSiteFreightYard.name);
					if(projectDetail.receiveCargoSiteFreightYard !="" && projectDetail.receiveCargoSiteFreightYard !=null ){
						var addressCode = (projectDetail.receiveCargoSiteFreightYard.addressCode).split(",");
						if(addressCode.length==3){
							if(addressCode[2]==""){
								$("#" + modal + " span[name=receiveCargoSiteAddress]").html(addressCode[1]+projectDetail.receiveCargoSiteFreightYard.address);
							}else{
								$("#" + modal + " span[name=receiveCargoSiteAddress]").html(addressCode[2]+projectDetail.receiveCargoSiteFreightYard.address);
							}
						}
					}
				}

				if(projectDetail.transportType == 0) { //汽运
					//发货单位
					$("#" + modal + " span[name=sendCargoUnitName]").html(projectDetail.sendCargoUnitName);
					$("#" + modal + " span[name=sendCargoAreaCode]").html(projectDetail.sendCargoUnit.addressCode);
					$("#" + modal + " span[name=sendCargoAddress]").html(projectDetail.sendCargoUnit.detailAddress);
					//收货单位
					$("#" + modal + " span[name=receivingDepartmentName]").html(projectDetail.receivingDepartmentName);
					$("#" + modal + " span[name=receivingDepartmentAreaCode]").html(projectDetail.receivingDepartment.addressCode);
					$("#" + modal + " span[name=receivingDepartmentAddress]").html(projectDetail.receivingDepartment.detailAddress);
				}

				//短驳承运方式
				$("#" + modal + " select[name=shortBargeCarrierMode1] option[value='" + projectDetail.shortBargeCarrierMode + "']").attr("selected", "selected");
				//短驳承运方
				$("#" + modal + " div[name=shortBargeCarrierName1]").html("");
				$.each(projectDetail.shortBarges, function(index, shortBarge) {
					if(projectDetail.shortBargeCarrierMode == 1) {
						shortBarges = "<select name='shortBargeId' onchange='changeAndSetValue(this)' >" + carTeams + "</select>"
					} else {
						shortBarges = "<select name='shortBargeId' onchange='changeAndSetValue(this)'><option  value='0'>平台</option></select>";
					}
					if(shortBarge.type == 1 || shortBarge.type == 3) {
						$("#" + modal + " div[name=shortBargeCarrierName1]").append("<form class='form-inline' name='" + modal + "Form1" + index + "'><div class='form-group '><label>&emsp;短驳承运方：</label>" +
							shortBarges +
							"<input type='hidden' name='shortBargeName'  /></div>" +
							"<div class='form-group short-client'><label>运输单价：</label><input type='text' name='transportPrice' value='" + shortBarge.transportPrice + "' maxlength='30' ><span>元</span></div>" +
							"<div class='form-group short-client'><label>扣损比率：</label><input type='text'  name='deductionRate'  value='" + shortBarge.deductionRate + "' maxlength='30' ><span>‰</span></div>" +
							"<div class='form-group short-client'><label>扣损单价：</label><input type='text'  name='deductionPrice' value='" + shortBarge.deductionPrice + "' maxlength='30' ><span>元</span></div>" +
							"<div class='form-group short-client'><label>付款周期：</label><select name='payment' ><option value='0'>日结</option>" +
							"<option value='1'>周结</option>" +
							"<option value='2'>月结</option></select></div></form>");
						$("#" + modal + " form[name='" + modal + "Form1" + index + "'] select[name=payment] option[value='" + shortBarge.payment + "']").attr("selected", "selected");
						$("#" + modal + " form[name='" + modal + "Form1" + index + "'] select[name=shortBargeId] option[value='" + shortBarge.shortBargeId + "']").attr("selected", "selected");
					}

				});
				$("#" + modal + " select[name=shortBargeId]").change();

				//起始中心站
				$("#" + modal + " span[name=beginCenterSiteName]").html(projectDetail.beginCenterSiteName);
				$("#" + modal + " span[name=beginSiteName]").html(projectDetail.beginSiteName);
				$("#" + modal + " span[name=beginSiteFreightYardName]").html(projectDetail.beginSiteFreightYardName);
				$("#" + modal + " span[name=beginAddress]").html(projectDetail.beginAddress);

				//到达中心站
				$("#" + modal + " span[name=endCenterSiteName]").html(projectDetail.endCenterSiteName);
				$("#" + modal + " span[name=endSiteName]").html(projectDetail.endSiteName);
				$("#" + modal + " span[name=endSiteFreightYardName]").html(projectDetail.endSiteFreightYardName);
				$("#" + modal + " span[name=endAddress]").html(projectDetail.endAddress);

				//运费
				$("#" + modal + " input[name=freight]").val(projectDetail.freight);
				$("#" + modal + " input[name=materialCost]").val(projectDetail.materialCost);
				$("#" + modal + " input[name=tarpaulinCost]").val(projectDetail.tarpaulinCost);
				$("#" + modal + " input[name=beginStevedoringCost]").val(projectDetail.beginStevedoringCost);
				$("#" + modal + " input[name=endStevedoringCost]").val(projectDetail.endStevedoringCost);
				$("#" + modal + " input[name=freightSum]").val(projectDetail.freightSum);

				//接取站点
				if(projectDetail.transportType == 2 || projectDetail.transportType == 5 || projectDetail.transportType == 6 || projectDetail.transportType == 7) {
					$("#" + modal + " span[name=forwardingCenterSiteName]").html(projectDetail.forwardingCenterSiteName);
					$("#" + modal + " span[name=forwardingSiteName]").html(projectDetail.sendTrainStation.stationName);
					$("#" + modal + " span[name=forwardingSiteFreightYard]").html(projectDetail.forwardingSiteFreightYard.name);
					if(projectDetail.forwardingSiteFreightYard!=""&&projectDetail.forwardingSiteFreightYard!=null){
						var addressCode = (projectDetail.forwardingSiteFreightYard.addressCode).split(",");
						if(addressCode.length==3){
							if(addressCode[2]==""){
								$("#" + modal + " span[name=forwardingSiteAddress]").html(addressCode[1]+projectDetail.forwardingSiteFreightYard.address);
							}
							$("#" + modal + " span[name=forwardingSiteAddress]").html(addressCode[2]+projectDetail.forwardingSiteFreightYard.address);
						}	
					}
					
					//收货单位
					$("#" + modal + " span[name=receivingDepartmentName]").html(projectDetail.receivingDepartmentName);
					$("#" + modal + " span[name=receivingDepartmentAreaCode]").html(projectDetail.receivingDepartment.addressCode);
					$("#" + modal + " span[name=receivingDepartmentAddress]").html(projectDetail.receivingDepartment.detailAddress);
				}

				//短驳承运方式
				$("#" + modal + " select[name=shortBargeCarrierMode2] option[value='" + projectDetail.sendShortBargeCarrierMode + "']").attr("selected", "selected");
				//短驳承运方
				$("#" + modal + " div[name=shortBargeCarrierName2]").html("");
				$.each(projectDetail.shortBarges, function(index, shortBarge) {
					if(projectDetail.sendShortBargeCarrierMode == 1) { //自选
						shortBarges = "<select name='shortBargeId' onchange='changeAndSetValue(this)' >" + carTeams + "</select>"
					} else {
						shortBarges = "<select name='shortBargeId' onchange='changeAndSetValue(this)'><option  value='0'>平台</option></select>";
					}

					if(shortBarge.type == 2) {
						$("#" + modal + " div[name=shortBargeCarrierName2]").append("<form class='form-inline' name='" + modal + "Form2" + index + "'><div class='form-group '><label>&emsp;短驳承运方：</label>" +
							shortBarges +
							"<input type='hidden' name='shortBargeName'  /></div>" +
							"<div class='form-group short-client'><label>运输单价：</label><input type='text' name='transportPrice' value='" + shortBarge.transportPrice + "' maxlength='30'  id='select02'><span>元</span></div>" +
							"<div class='form-group short-client'><label>扣损比率：</label><input type='text'  name='deductionRate'  value='" + shortBarge.deductionRate + "' maxlength='30' id='sele03'><span>‰</span></div>" +
							"<div class='form-group short-client'><label>扣损单价：</label><input type='text'  name='deductionPrice' value='" + shortBarge.deductionPrice + "' maxlength='30' ><span>元</span></div>" +
							"<div class='form-group short-client'><label>付款周期：</label><select name='payment' ><option value='0'>日结</option>" +
							"<option value='1'>周结</option>" +
							"<option value='2'>月结</option></select></div></form>");
						$("#" + modal + "  form[name='" + modal + "Form2" + index + "'] select[name=payment] option[value='" + shortBarge.payment + "']").attr("selected", "selected");
						$("#" + modal + "  form[name='" + modal + "Form2" + index + "'] select[name=shortBargeId] option[value='" + shortBarge.shortBargeId + "']").attr("selected", "selected");
					}
				});
				$("#" + modal + " select[name=shortBargeId]").change();

				//送达单价
				$("#" + modal + " input[name=sendArrivePrice]").val(projectDetail.arrivePrice);

				//接取单价
				$("#" + modal + " input[name=recevePickUpPrice]").val(projectDetail.pickUpPrice);

				//火运单价
				$("#" + modal + " input[name=trainTrainPrice]").val(projectDetail.trainPrice);

				//汽运单价
				$("#" + modal + " input[name=truckTransportPrice]").val(projectDetail.transportPrice);

				//备注
				$("#" + modal + " div[name=remark]").html(projectDetail.remark);

				if(projectDetail.status == 0) {
					var operatorName = ""; //操作人
					var createDate = ""; //删除时间
					var content = ""; //删除原因
					$.each(projectDetail.operationLogs, function(index, operator) {
						if(index == 0) {
							operatorName = operator.operatorName;
							createDate = operator.createDate;
							content = operator.content;
						}
					})
					$("#" + modal + " span[name=delUser]").html(operatorName);
					$("#" + modal + " span[name=delDate]").html(createDate);
					$("#" + modal + " span[name=delReason]").html(content);
				}

				$(".modifyModal input").attr("readonly", true);
				$(".modifyModal input").addClass("disabledBg");
				$(".modifyModal select").attr("readonly", true);
				$(".modifyModal select").addClass("disabledBg");
				if($("#mtext").val() == null || $("#mtext").val() == undefined || $("#mtext").val() == "") {
					$("#" + modal).modal();
				}
			} else {
				$("#msgContent").html("");
				$("#msgContent").html(data.msg);
				$("#deletnullModal").modal();
			}
		}
	})
}

function updateProject(type, modal) {
	var projectJson = ""; //提交到后台的json数据
	//获取项目id的value
	var projectId = $("#" + modal + " input[name=id]").val();
	//获取短驳承运方式
	var shortBargeCarrierMode = $("#" + modal + "  select[name=shortBargeCarrierMode1]").val();
	//获取短驳承运方
	var array = new Array();
	$("#" + modal + " div[name=shortBargeCarrierName1] form").each(function(j, item) {
		var json = $(item).serialize();
		var data = JSON.stringify($(this).serializeJSON());
		array[j] = data;
	})
	var shortBargeCarrierName = "[" + array + "]";
	if(type == 0) { //汽运
		//获取汽运单价
		var transportPrice = $('#truckModal input[name=truckTransportPrice]').val();
		projectJson = "{id: " + projectId + ",transportType: '" + type + "',shortBargeCarrierMode: '" + shortBargeCarrierMode + "',shortBargeCarrierName:'" + shortBargeCarrierName + "',transportPrice:'" + transportPrice + "'}";
		projectJson = eval("(" + projectJson + ")");
		projectJson = JSON.stringify(projectJson);
	} else if(type == 1) { //更新接取项目
		//获取接取单价
		var pickUpPrice = $('#receveModal input[name=recevePickUpPrice]').val();
		var projectJson = "{id: " + projectId + ",transportType: '" + type + "',shortBargeCarrierMode: '" + shortBargeCarrierMode + "',shortBargeCarrierName:'" + shortBargeCarrierName + "',pickUpPrice:'" + pickUpPrice + "'}";
		projectJson = eval("(" + projectJson + ")");
		projectJson = JSON.stringify(projectJson);
	} else if(type == 2) { //更新送达项目
		//获取短驳承运方式
		shortBargeCarrierMode = $('#sendModal select[name=shortBargeCarrierMode2]').val();
		//获取短驳承运方
		$('#sendModal   div[name=shortBargeCarrierName2] form').each(function(j, item) {
			var data = JSON.stringify($(this).serializeJSON());
			array[j] = data;
		})
		shortBargeCarrierName = "[" + array + "]";
		//获取送达单价
		var arrivePrice = $('#sendModal input[name=sendArrivePrice]').val();
		var projectJson = "{id: " + projectId + ",transportType: '" + type + "',sendShortBargeCarrierMode: '" + shortBargeCarrierMode + "',sendShortBargeCarrierName:'" + shortBargeCarrierName + "',arrivePrice:'" + arrivePrice + "'}";
		projectJson = eval("(" + projectJson + ")");
		projectJson = JSON.stringify(projectJson);
	} else if(type == 3) { //更新火运项目
		//获取项目id的value
		var trainTrainPrice = $('#trainModal input[name=trainTrainPrice]').val();
		$('#trainModal input[name=trainPrice]').val(trainTrainPrice);
		projectJson = JSON.stringify($('#trainModal form[name=trainCost]').serializeJSON());
	} else if(type == 4) { //更新接取+火运项目
		$('#receveAndTrainModal input[name=shortBargeCarrierName]').val(shortBargeCarrierName);

		$('#receveAndTrainModal input[name=shortBargeCarrierMode]').val(shortBargeCarrierMode);
		
		/*接取单价*/
		var pickUpPrice = $('#receveAndTrainModal input[name=recevePickUpPrice]').val();
		$('#receveAndTrainModal input[name=pickUpPrice]').val(pickUpPrice);
		/*火运单价*/
		var trainTrainPrice = $('#receveAndTrainModal input[name=trainTrainPrice]').val();
		$('#receveAndTrainModal input[name=trainPrice]').val(trainTrainPrice);
		/*表单序列化*/
		projectJson = JSON.stringify($('#receveAndTrainModal form[name=trainCost]').serializeJSON());
	} else if(type == 5) { //更新火运+送达
		/*短驳承运方式*/
		shortBargeCarrierMode = $('#trainAndSendModal select[name=shortBargeCarrierMode2]').val();
		$('#trainAndSendModal input[name=sendShortBargeCarrierMode]').val(shortBargeCarrierMode);
		/*短驳承运方*/
		$('#trainAndSendModal   div[name=shortBargeCarrierName2] form').each(function(j, item) {
			var data = JSON.stringify($(this).serializeJSON());
			array[j] = data;
		})
		shortBargeCarrierName = "[" + array + "]";
		$('#trainAndSendModal input[name=sendShortBargeCarrierName]').val(sendShortBargeCarrierName);
		/*送达单价*/
		var arrivePrice = $('#trainAndSendModal input[name=sendArrivePrice]').val();
		$('#trainAndSendModal input[name=arrivePrice]').val(arrivePrice);
		/*火运单价*/
		var trainTrainPrice = $('#trainAndSendModal input[name=trainTrainPrice]').val();
		$('#trainAndSendModal input[name=trainPrice]').val(trainTrainPrice);
		/*表单序列化*/
		var projectJson = JSON.stringify($('#trainAndSendModal form[name=trainCost]').serializeJSON());
	} else if(type == 6) { //更新联运
		$('#unionModal input[name=shortBargeCarrierName]').val(shortBargeCarrierName);
		/*送达短驳承运方式*/
		var sendShortBargeCarrierMode = $('#unionModal select[name=shortBargeCarrierMode2]').val();
		$('#unionModal input[name=sendShortBargeCarrierMode]').val(sendShortBargeCarrierMode);
		$('#unionModal input[name=shortBargeCarrierMode]').val(shortBargeCarrierMode);
		/*送达短驳承运方*/
		var sendArray = new Array();
		$('#unionModal   div[name=shortBargeCarrierName2] form').each(function(j, item) {
			var data = JSON.stringify($(this).serializeJSON());
			sendArray[j] = data;
		})
		var sendShortBargeCarrierName = "[" + sendArray + "]";
		$('#unionModal input[name=sendShortBargeCarrierName]').val(sendShortBargeCarrierName);
		/*接取单价*/
		var pickUpPrice = $('#unionModal input[name=recevePickUpPrice]').val();
		$('#unionModal input[name=pickUpPrice]').val(pickUpPrice);
		/*送达单价*/
		var arrivePrice = $('#unionModal input[name=sendArrivePrice]').val();
		$('#unionModal input[name=arrivePrice]').val(arrivePrice);
		/*火运单价*/
		var trainTrainPrice = $('#unionModal input[name=trainTrainPrice]').val();
		$('#unionModal input[name=trainPrice]').val(trainTrainPrice);
		/*表单序列化*/
		projectJson = JSON.stringify($('#unionModal form[name=trainCost]').serializeJSON());
	} else if(type == 7) { //更新接取+送达
		$('#receveAndSendModal input[name=shortBargeCarrierName]').val(shortBargeCarrierName);

		/*送达短驳承运方式*/
		var sendShortBargeCarrierMode = $('#receveAndSendModal select[name=shortBargeCarrierMode2]').val();
		$('#receveAndSendModal input[name=sendShortBargeCarrierMode]').val(sendShortBargeCarrierMode);
		/*短驳承运方*/
		var sendArray = new Array();
		$('#receveAndSendModal   div[name=shortBargeCarrierName2] form').each(function(j, item) {
			var data = JSON.stringify($(this).serializeJSON());
			sendArray[j] = data;
		})
		var sendShortBargeCarrierName = "[" + sendArray + "]";
		$('#receveAndSendModal input[name=sendShortBargeCarrierName]').val(sendShortBargeCarrierName);
		/*接取单价*/
		var pickUpPrice = $('#receveAndSendModal input[name=recevePickUpPrice]').val();
		$('#receveAndSendModal input[name=pickUpPrice]').val(pickUpPrice);
		/*送达单价*/
		var arrivePrice = $('#receveAndSendModal input[name=sendArrivePrice]').val();
		$('#receveAndSendModal input[name=arrivePrice]').val(arrivePrice);
		/*表单序列化*/
		projectJson = JSON.stringify($('#receveAndSendModal form[name=trainCost]').serializeJSON());
	}

	/*异步提交*/
	$.ajax({
		type: 'post',
		url: $.getUrl('/projectManagment/updateProject.do'),
		data: {
			projectJson: projectJson
		},
		cache: false,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				$("#" + modal).hide();
				$("#successMsg").html("");
				$("#successMsg").html("修改项目信息成功!");
				$("#successModal").modal();
			} else {
				$("#msgContent").html("");
				$("#msgContent").html(data.msg);
				$("#deletnullModal").modal();
			}
		}
	})
}

/*
 * 将查询结果写入到Table中
 * */
function htmlTable(results) {
	var projectList = results;
	var tbody = "projectTbody";
	if($('#searchForm input[name=status]').val() == 1) { /*项目列表*/
		tbody = "projectTbody";
	} else if($('#searchForm input[name=status]').val() == 2) { /*历史项目*/
		tbody = "historyProjectTbody";
	} else { /*项目回收站*/
		tbody = "recycleTbody"
	}

	$("#" + tbody + "").html("");

	$.each(projectList, function(index, project) {
		//项目类型
		var projectType = "";
		if(project.projectType == 0) {
			projectType = "集装箱";
		} else if(project.projectType == 1) {
			projectType = "散装";
		}
		//联运模式
		var transportType = "";
		if(project.transportType == 0) {
			transportType = "汽运";
		} else if(project.transportType == 1) {
			transportType = "接取";
		} else if(project.transportType == 2) {
			transportType = "送达";
		} else if(project.transportType == 3) {
			transportType = "火运";
		} else if(project.transportType == 4) {
			transportType = "接取+火运";
		} else if(project.transportType == 5) {
			transportType = "火运+送达";
		} else if(project.transportType == 6) {
			transportType = "联运";
		} else if(project.transportType == 7) {
			transportType = "接取+送达";
		}
		//计价单位
		var valuationUnitName = "";
		if(project.valuationUnitName == 0) {
			valuationUnitName = "件";
		} else if(project.valuationUnitName == 1) {
			valuationUnitName = "吨";
		}
		//短驳承运方式
		var shortBargeCarrierMode = ""
		if(parseInt(project.shortBargeCarrierMode) == 0) {
			shortBargeCarrierMode = "平台; ";
		} else if(parseInt(project.shortBargeCarrierMode) == 1) {
			shortBargeCarrierMode = "自选; ";
		}

		var sendShortBargeCarrierMode = ""
		if(parseInt(project.sendShortBargeCarrierMode) == 0) {
			sendShortBargeCarrierMode = "平台";
		} else if(parseInt(project.sendShortBargeCarrierMode) == 1) {
			sendShortBargeCarrierMode = "自选";
		}

		var operatorName = ""; //操作人
		var operatorDate = ""; //删除时间
		var content = ""; //删除原因

		if($('#searchForm input[name=status]').val() == 0) { /*项目回收站*/
			$.each(project.operationLogs, function(index, operator) {
				if(index == 0) {
					operatorName = operator.operatorName;
					operatorDate = operator.createDate;
					content = operator.content;
				}
			})
		} else {
			operatorDate = project.finishDate;
		}

		$("#" + tbody + "").append("<tr id='" + project.id + "'><td  style='text-align: center;' ><label class='demo--label' style='text-align:center;'>" +
			"<input class='demo--checkbox'   type='checkbox' value='" + project.id + "' name='WaybillName'>" +
			"<span class='demo--checkboxInput'></span></label></td>" +
			"<td ><a href='' data-toggle='modal' data-target='#detailModal-one'>" + project.projectCode + "</a></td>" +
			"<td >" + projectType + "</td>" +
			"<td >" + transportType + "</td>" +
			"<td >" + project.branchGroupName + "</td>" +
			"<td >" + project.sendCargoCompanyName + "</td>" +
			"<td >" + project.receiveCargoCompanyName + "</td>" +
			"<td >" + project.cargoName + "</td>" +
			"<td >" + shortBargeCarrierMode + sendShortBargeCarrierMode + "</td>" +
			"<td >" + project.shortBargeCarrierName + "</td>" +
			"<td >" + valuationUnitName + "</td>" +
			"<td >" + project.remark + "</td>" +
			"<td name='delUser'>" + operatorName + "</td>" /*删除人*/ +
			"<td name='operatedate'>" + operatorDate + "</td>" /*完成时间或删除时间*/ +
			"<td name='delReason'>" + content + "</td>" /*删除原因*/ +
			"</tr>")
		if($('#searchForm input[name=status]').val() == 1) {
			$("#" + tbody + " td[name='delUser']").remove();
			$("#" + tbody + " td[name='operatedate']").remove();
			$("#" + tbody + " td[name='delReason']").remove();
		} else if($('#searchForm input[name=status]').val() == 2) {
			$("#" + tbody + " td[name='delUser']").remove();
			$("#" + tbody + " td[name='delReason']").remove();
		}
	})
}

/*
 * 火运费用合计运算
 * */
function add(param) {
	var freight = $(param).parent('div').parent('div').find('input[name=freight]').val();
	var materialCost = $(param).parent('div').parent('div').find('input[name=materialCost]').val();
	var tarpaulinCost = $(param).parent('div').parent('div').find('input[name=tarpaulinCost]').val();
	var beginStevedoringCost = $(param).parent('div').parent('div').find('input[name=beginStevedoringCost]').val();
	var endStevedoringCost = $(param).parent('div').parent('div').find('input[name=endStevedoringCost]').val();

	if(freight == undefined || freight == null || freight == "") {
		freight = 0;
	}
	if(materialCost == undefined || materialCost == null || materialCost == "") {
		materialCost = 0;
	}
	if(tarpaulinCost == undefined || tarpaulinCost == null || tarpaulinCost == "") {
		tarpaulinCost = 0;
	}
	if(beginStevedoringCost == undefined || beginStevedoringCost == null || beginStevedoringCost == "") {
		beginStevedoringCost = 0;
	}
	if(endStevedoringCost == undefined || endStevedoringCost == null || endStevedoringCost == "") {
		endStevedoringCost = 0;
	}
	var freightSum = parseFloat(freight) + parseFloat(materialCost) + parseFloat(tarpaulinCost) +
		parseFloat(beginStevedoringCost) + parseFloat(endStevedoringCost);

	$(param).parent('div').parent('div').find('input[name=freightSum]').val(freightSum);
}


// 导出
$("#exportByProject").click(function(e) {
  e.preventDefault();
  var condition = $('#searchForm').serializeJSON();
  var conditionObj = objToUrl(condition);
  window.location.href =$.getUrl('/projectManagment/export.do')+conditionObj;
});
$("#exportByHistory").click(function(e) {
  e.preventDefault();
  var condition = $('#searchForm').serializeJSON();
  var conditionObj = objToUrl(condition);
  window.location.href = $.getUrl('/projectManagment/export.do')+conditionObj;
});
$("#exportByrecycle").click(function(e) {
  e.preventDefault();
  var condition = $('#searchForm').serializeJSON();
  var conditionObj = objToUrl(condition);
  window.location.href = $.getUrl('/projectManagment/export.do')+conditionObj;
});