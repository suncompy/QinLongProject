$(function() {
	//选项切换清空复选框
	$(".tabbable .nav li").click(function(){
		$("input[type=checkbox]").prop("checked",false);
	});

	$(".modal tr").each(function() {
		$(this).children('td:eq(0)').css('white-space', 'nowrap');
	});
	
	//删除
	$("#delBtn").click(function() {
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
	//	新建项目切换
	$('#selectField').change(function(event) {
		var value = $('#selectField option:selected').attr("id");
		$('.col').hide();
		$('.motor').children().remove();
		$('.' + value).show();
		
	});
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
    })    
//	还原
    $(".rest").click(function(){
    	$('#restId').val("");
    	 var id=$(this).parent().parent().parent().attr("id");
    	var value = $("#"+ id +" input[type=checkbox]:checked").val();
    	$('#restId').val(value);
    	if($("#"+ id +" input[type=checkbox]:checked").length == 0) {
			$("#nullModal").modal();
			return;
		}
    	else if($("#"+ id +" input[type=checkbox]:checked").length > 1) {
		$("#deletRemindModal").modal();
		$("#warnMsg").html("抱歉！不可批量还原！")
		}else {
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
			var projectId ="";
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
			}else if($(this).parent().find("td").eq(3).html() == "接取+送达") {
				projectId = $(this).parent().attr("id");
			}
			if(projectId==undefined || projectId==null || projectId =="" ){
				$("#msgContent").html("");
    			$("#msgContent").html("查询不到此项目的详细信息");
    			$("#deletnullModal").modal();
    			return;
			}
			getProjectDetail(projectId);
			$(this).parent().toggleClass("bgclass").siblings("#panel1 tbody tr").removeClass("bgclass");
		}
	);
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
			var projectId ="";
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
			if(projectId==undefined || projectId==null || projectId =="" ){
				$("#msgContent").html("");
    			$("#msgContent").html("查询不到此项目的详细信息");
    			$("#deletnullModal").modal();
    			return;
			}
			getProjectDetail(projectId);
			return;
			$(this).parent().toggleClass("bgclass").siblings("#panel1 tbody tr").removeClass("bgclass");
		}
	);
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
			var projectId ="";
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
			if(projectId==undefined || projectId==null || projectId =="" ){
				$("#msgContent").html("");
    			$("#msgContent").html("查询不到此项目的详细信息");
    			$("#deletnullModal").modal();
    			return;
			}
			getProjectDetail(projectId);
			return;
			$(this).parent().toggleClass("bgclass").siblings("#panel1 tbody tr").removeClass("bgclass");
		}
	);
})
