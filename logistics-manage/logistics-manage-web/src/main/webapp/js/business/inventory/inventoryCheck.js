$(function() {
	
	 //	导出
	$("#export").click(function(e) {
	    e.preventDefault();
	    var currentPage = $("#page .current").html();
	    var condition = $('#searchForm').serializeJSON();
	    var conditionObj = objToUrl(condition)+"&pageNo="+currentPage;
	    window.location.href = "/logistics-manage-web/inventory/export1"+conditionObj;
	  });
	
	//选项切换清空复选框
	$(".tabbable .nav li").click(function() {
		$("input[type=checkbox]").prop("checked", false);
	});
	var slideHeight1 = 25; // px 定义折叠的最小高度
	var defHeight1 = $('#wrap1').height();
	if(defHeight1 >= slideHeight1) {
		$('#wrap1').css('height', slideHeight1 + 'px');
		$('#lookMore1').append('显示筛选<i class="fa fa-chevron-down" aria-hidden="true"></i>');
		$('#lookMore1').click(function() {
			var curHeight = $('#wrap1').height();
			if(curHeight == slideHeight1) {
				$('#wrap1').animate({
					height: defHeight1
				}, "normal");
				$(this).html('收起筛选<i class="fa fa-chevron-up" aria-hidden="true"></i>');
			} else {
				$('#wrap1').animate({
					height: slideHeight1
				}, "normal");
				$(this).html('显示筛选<i class="fa fa-chevron-down" aria-hidden="true"></i>');
			}
			return false;
		});
	}

	$(".position-form").hide();
	$("#items").click(function() {
		$(".list-form").show();
		$(".position-form").hide();
	});
	$("#position-plane").click(function() {
		$(".list-form").hide();
		$(".position-form").show();
	});
	$("#historyInventory").click(function() {
		$(".list-form").show();
		$(".position-form").hide()
	})

	/*//	导出模态框
	$(".export").click(function() {
		var id = $(this).parent().parent().parent().attr("id");
		if($("#" + id + "  input[type=checkbox]:checked").length > 0) {
			$("#exportModal").modal();
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
	})*/

	/*<!-- 库存调整 双击可编辑表格  -->*/
	$(function() {
		$("body").on("dblclick", '#adjustId table td:nth-child(7)', function() {
			var td = $(this);
			// 根据表格文本创建文本框 并加入表表中--文本框的样式自己调整
			var text = td.text();
			var txt = $("<input type='text'>").val(text);
			txt.blur(function() {
				// 失去焦点，保存值。于服务器交互自己再写,最好ajax
				var newText = $(this).val();
				// 移除文本框,显示新值
				$(this).remove();
				td.text(newText);
			});
			td.text("");
			td.append(txt);
		});
	})

	/**
	 * @author shilvfei
	 * @date 2018/01/22
	 * @description -获取项目列表
	 * */
	$('#searchSpan').click();

	/**
	 * @author shilvfei
	 * @date 2018/01/22
	 * @description -获取搜索条件下拉框
	 * */
	listSearchParam();

	/*<!--查看大图-->*/
	 $("body").on("click", ".lookBigImg", function() {
	 	var imgUrl = $(this).parent('div').parent('div').children('img').attr('src');
		$('#showMask', window.parent.document).show();
		$('#closeImg').next('img').attr('src',imgUrl);
		$("#ImgModal").modal()
	 });
	$("body").on("click", "#closeImg", function() {
		 $('#showMask', window.parent.document).hide();
	});
	
})
	/**
	 * 将查询结果写入到Table中 
	 * */
	function htmlTable(results) {
		$('#projectTbody').html("");
		$.each(results, function(index, stock) {	//项目类型
		var projectType="";
		if(stock.projectType==0){
			projectType="集装箱";
		}else if(stock.projectType==1){
			projectType="散装";
		}
		//联运模式
		var transportType="";
		if(stock.transportType==0){
			transportType="汽运";
		}else if(stock.transportType==1){
			transportType="接取";
		}else if(stock.transportType==2){
			transportType="送达";
		}else if(stock.transportType==3){
			transportType="火运";
		}else if(stock.transportType==4){
			transportType="接取+火运";
		}else if(stock.transportType==5){
			transportType="火运+送达";
		}else if(stock.transportType==6){
			transportType="联运";
		}else if(stock.transportType==7){
			transportType="接取+送达";
		}
		//计价单位
		var  valuationUnitName="";
		if(stock.valuationUnitName==0){
			valuationUnitName="吨";
		}else if(stock.valuationUnitName==1){
			valuationUnitName="位";
		}
		//短驳承运方式
		var shortBargeCarrierMode=""
		if(stock.shortBargeCarrierMode==0){
			shortBargeCarrierMode="平台";
		}else if(stock.shortBargeCarrierMode==1){
			shortBargeCarrierMode="自选";
		}
		
		$('#projectTbody').append("<tr id='"+stock.projectId+"' ><td><label class='demo--label'>"
		+"<input class='demo--checkbox' type='checkbox' value='"+stock.projectId+"' transportType='"+stock.transportType+"' name='WaybillName'>"
		+"<span class='demo--checkboxInput'></span></label></td>"
		+"<td ><a href='' data-toggle='modal' data-target='#detailModal-one'>"+stock.projectCode+"</a></td>"	                   
		+"<td >"+projectType+"</td>"
		+"<td >"+transportType+"</td>"
		+"<td >"+stock.branchGroupName+"</td>"
		+"<td >"+stock.cargoName+"</td>"
		+"<td >"+stock.sendCargoCompanyName+"</td>"
		+"<td >"+stock.beginStationName+"</td>"
		+"<td >"+stock.beginFreightYardName+"</td>"
		+"<td >"+stock.beginCargoLocationName+"</td>"
		+"<td >"+stock.beginEnterQty+"</td>"
		+"<td >"+stock.beginOutQty+"</td>"
		+"<td >"+stock.beginCurrentQty+"</td>"
		+"<td >"+stock.beginAdjustQty+"</td>"
		+"<td >"+stock.receiveCargoCompanyName+"</td>"
		+"<td >"+stock.endStationName+"</td>"
		+"<td >"+stock.endFreightYardName+"</td>"
		+"<td >"+stock.endCargoLocationName+"</td>"
		+"<td >"+stock.endEnterQty+"</td>"
		+"<td >"+stock.endOutQty+"</td>"
		+"<td >"+stock.endCurrentQty+"</td>"
		+"<td >"+stock.endAdjustQty+"</td>"
		+"<td >"+stock.adjustDate+"</td>"
		+"</tr>")
		})
	}