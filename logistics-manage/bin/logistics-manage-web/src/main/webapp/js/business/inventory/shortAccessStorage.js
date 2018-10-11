	$(function(){
	      //	导出模态框
	   /* $(".export").click(function(){ 
	   var id=$(this).parent().parent().parent().attr("id");
	    	if($("#"+ id +"  input[type=checkbox]:checked").length > 0) {
				$("#exportModal").modal();
				$("#nullModal").modal("hide");
			} else {
				$("#nullModal").modal();
				return;
			}
	    });*/
		
		$("#export").click(function(e) {
		    e.preventDefault();
		    var currentPage = $("#page .current").html();
		    var condition = $('#searchForm').serializeJSON();
		    var conditionObj = objToUrl(condition)+"&pageNo="+currentPage;
		    window.location.href = "/logistics-manage-web/inventory/export"+conditionObj;
		  });
		
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
		
	

	/**
	 * @author shilvfei
	 * @date 2018/01/22
	 * @description -查询统计
	 * */
	$("#queryStatistics").click(function() {
		if($("input:checkbox[type='checkbox']:checked").length > 1) {
			$("#msgContent").html("");
			$("#msgContent").html("抱歉,不可批量查询");
			$("#deletnullModal").modal();
			return;
		} else if($("input:checkbox[type='checkbox']:checked").length == 0) {
			$("#msgContent").html("");
			$("#msgContent").html("请选择一条要查询的数据");
			$("#deletnullModal").modal();
			return;
		}
		var projectId = $("input:checkbox[type='checkbox']:checked").val();
		$.ajax({
			type: 'post',
			url:$.getUrl('/inventory/shortAccessStorageStatistics.do'),  
			data: {
				projectId: projectId
			},
			cache: false,
			dataType: 'json',	
			success: function(data) {
				if(data.status == 200) {
					var orderHelpPojo = data.data;
					$('span[name=projectCode]').html(orderHelpPojo.projectCode);
					$('span[name=branchGroupName]').html(orderHelpPojo.branchGroupName);
	
					//项目类型
					var projectType = "";
					if(orderHelpPojo.projectType == 0) {
						projectType = "集装箱";
					} else if(orderHelpPojo.projectType == 1) {
						projectType = "散装";
					}
					//联运模式
					var transportType = "";
					if(orderHelpPojo.transportType == 0) {
						transportType = "汽运";
					} else if(orderHelpPojo.transportType == 1) {
						transportType = "接取";
					} else if(orderHelpPojo.transportType == 2) {
						transportType = "送达";
					} else if(orderHelpPojo.transportType == 3) {
						transportType = "火运";
					} else if(orderHelpPojo.transportType == 4) {
						transportType = "接取+火运";
					} else if(orderHelpPojo.transportType == 5) {
						transportType = "火运+送达";
					} else if(orderHelpPojo.transportType == 6) {
						transportType = "联运";
					} else if(orderHelpPojo.transportType == 7) {
						transportType = "接取+送达";
					}
					//计价单位
					var valuationUnitName = "";
					if(orderHelpPojo.valuationUnitName == 1) {
						valuationUnitName = "吨";
					} else if(orderHelpPojo.valuationUnitName == 0) {
						valuationUnitName = "件";
					}
	
					$('span[name=cargoName]').html(orderHelpPojo.cargoName);
					$('span[name=cargoSpecifications]').html(orderHelpPojo.cargoSpecifications);
					$('span[name=cargoPrice]').html(orderHelpPojo.cargoPrice);
					$('span[name=valuationUnitName]').html(valuationUnitName);
					$('span[name=transportType]').html(transportType);
					$('span[name=projectType]').html(projectType);
	
					/*运单统计*/
					$('#orderDetailTable').html("<tr><th>序号</th><th>运单编号</th><th>车号</th><th>阶段</th><th>提货时间</th><th>提货吨位</th><th>到货时间</th><th>到货吨位</th><th>损耗</th><th>货场</th><th>货位</th></tr>");
	
					$.each(orderHelpPojo.orders, function(index, order) {
						var stepSelectCode = "";
	
						if(order.stepSelectCode == 0) {
							stepSelectCode = "接取";
						} else if(order.stepSelectCode == 1) {
							stepSelectCode = "送达";
						}else{
							stepSelectCode = "汽运";
						}
						
						var wastage="";
						if((order.containerOneReceiptNet + order.containerTwoReceiptNet) == "" || (order.containerOneReceiptNet + order.containerTwoReceiptNet) == null){
						
						}else{
							wastage=((order.containerOneSendNet+order.containerTwoSendNet)-(order.containerOneReceiptNet+order.containerTwoReceiptNet));
						}
						
						$('#orderDetailTable').append("<tr>" +
							"<td id='' style='text-align: center;'>" + (index + 1) + "</td>" +
							"<td id=''>" + order.orderCode + "</td>" +
							"<td id=''>" + order.carPlateNumber + "</td>" +
							"<td id=''>" + stepSelectCode + "</td>" +
							"<td id=''>" + order.takeDeliveryDate + "</td>" /*提货时间*/ +
							"<td id=''>" + (order.containerOneSendNet+order.containerTwoSendNet) + "吨</td>" /*提货吨位*/ +
							"<td id=''>" + order.receipterDate + "</td>" /*到货时间*/ +
							"<td id=''>" + (order.containerOneReceiptNet+order.containerTwoReceiptNet) + "吨</td>" /*到货吨位*/ +
							"<td id=''>" + wastage + "</td>" /*损耗*/ +
							"<td name='resaonCargos" + index + "'></td>" /*货场*/ +
							"<td name='resaonSites" + index + "'></td>" /*货位*/ +
							"</tr>");
						$("td[name='resaonCargos" + index + "']").html("");
						$("td[name='resaonSites" + index + "']").html("");
						if(order.stepSelectCode == 0) {
							$("td[name='resaonCargos" + index + "']").append("" + order.distributionCargoPlace + "");
							$("td[name='resaonSites" + index + "']").append("" + order.distributionCargoSite + "");
						} else if(order.stepSelectCode == 1) {
							$("td[name='resaonCargos" + index + "']").append("" + order.takeCarogoPlaceName + "");
							$("td[name='resaonSites" + index + "']").append("" + order.takeCargoSiteName + "");
						}
						//(order.sendGross - order.receiptGross)
					});
					$('span[name=sumOrderSize]').html(orderHelpPojo.totalOrder);
					$('span[name=sumSendGross]').html(orderHelpPojo.totalPickUpWeight + "吨");
					$('span[name=sumWastage]').html(orderHelpPojo.totalWastageWeight + "吨");
					$('span[name=sunReceiptGross]').html(orderHelpPojo.totalArriveWeight + "吨");
	
					$("#queryStatisticsModal").modal();
				} else {
					$("#msgContent").html("");
					$("#msgContent").html(data.msg);
					$("#deletnullModal").modal();
				}
			}
		})
	});
	})
	/**
	 * @author shilvfei
	 * @date 2018/01/22
	 * @description -将分页查询出来的结果写入html
	 * */
	function htmlTable(results){
			var orders = results;
			$('#orderTbody').html("");
			$.each(orders,function(index,order){
				var type="";
				if(order.type==1){
					type="集装箱";
				}else if(order.type==2){
					type="散装箱";
				}
				
				var stepSelectCode="";
				if(order.stepSelectCode==0){
					stepSelectCode="接取";
				}else if(order.stepSelectCode==1){
					stepSelectCode="送达";
				}else{
					stepSelectCode = "汽运";
				}
				
				var wastage="";
				if((order.containerOneReceiptNet + order.containerTwoReceiptNet) == "" || (order.containerOneReceiptNet + order.containerTwoReceiptNet) == null){
				
				}else{
					wastage=((order.containerOneSendNet+order.containerTwoSendNet)-(order.containerOneReceiptNet+order.containerTwoReceiptNet));
				}
				
				$('#orderTbody').append("<tr id='"+order.projectId+"'>"
					+"<td style='text-align: center;'><label class='demo--label'>"
					 +" <input class='demo--checkbox' value='"+order.projectId+"' type='checkbox'   >"
	                  +"<span class='demo--checkboxInput'></span>"
	                 +"</label></td>"
				  +"<td id=''>"+order.projectCode+"</td>"
				  +"<td id=''>"+type+"</td>"
				  +"<td >"+order.orderCode+"</td>"
				  +"<td name='resaonType"+index+"'></td>"
				   +"<td >"+order.branchGroupName+"</td>"
				     +"<td >"+stepSelectCode+"</td>"
				  +"<td >"+order.sendCompany+"</td>"
				  +"<td id=''>"+order.receiptCompany+"</td>"
				  +"<td id=''>"+order.cargoName+"</td>"
				  +"<td id=''>"+order.carPlateNumber+"</td>"
				  +"<td id=''>"+order.takeDeliveryDate+"</td>"
				  +"<td id=''>"+(order.containerOneSendNet+order.containerTwoSendNet)+"T</td>"
				  +"<td id=''>"+order.receipterDate+"</td>"/*到货时间*/
	              +"<td id=''>"+(order.containerOneReceiptNet+order.containerTwoReceiptNet)+"</td>"
	              +"<td id=''>"+wastage+"T</td>"
	              +"<td name='resaonCargo"+index+"'></td>"
	              +"<td name='resaonSite"+index+"'></td>"
				 +"</tr>");
				$("td[name='resaonCargo"+index+"']").html("");
				$("td[name='resaonSite"+index+"']").html("");
				if(order.stepSelectCode==0){
					$("td[name='resaonCargo"+index+"']").append(""+order.distributionCargoPlace+""); 
					$("td[name='resaonSite"+index+"']").append(""+order.distributionCargoSite+""); 
				}else if(order.stepSelectCode==1){
					$("td[name='resaonCargo"+index+"']").append(""+order.takeCarogoPlaceName+""); 
					$("td[name='resaonSite"+index+"']").append(""+order.takeCargoSiteName+"");
				}
				$("td[name='resaonType"+index+"']").html("");
				if(order.transportType==0){
					$("td[name='resaonType"+index+"']").append("汽运"); 
				}else if(order.transportType==1){
					$("td[name='resaonType"+index+"']").append("接取"); 
				}else if(order.transportType==2){
					$("td[name='resaonType"+index+"']").append("送达"); 
				}else if(order.transportType==3){
					$("td[name='resaonType"+index+"']").append("火运"); 
				}else if(order.transportType==4){
					$("td[name='resaonType"+index+"']").append("接取+火运"); 
				}else if(order.transportType==5){
					$("td[name='resaonType"+index+"']").append("火运+送达"); 
				}else if(order.transportType==6){
					$("td[name='resaonType"+index+"']").append("联运"); 
				}else if(order.transportType==7){
					$("td[name='resaonType"+index+"']").append("接取+送达"); 
				}
			});	
		} 