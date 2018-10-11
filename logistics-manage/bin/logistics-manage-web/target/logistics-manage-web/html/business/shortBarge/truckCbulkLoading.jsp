<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>散装箱管理</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/business/shortBarge/truck_container.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/public.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/business/shortBarge/truckCbulkLoading.js"></script>
		<!--分页-->
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.serializejson.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js" ></script>
		<link href="${pageContext.request.contextPath}/css/page/page.css" rel="stylesheet"/>
		<script type="text/javascript">
			$(function(){
				$(".abnorm").click(function() {
					var id = $(this).parent().parent().parent().attr("id");
					if($("#" + id + "  input[type=checkbox]:checked").length > 1) {
						$("#nullModal").modal();
						$("#warnMsg").html("不可批量处理！");
						return;
					} else if($("#" + id + "  input[type=checkbox]:checked").length == 0) {
						$("#nullModal").modal();
						$("#warnMsg").html("请选择一条要操作的数据!");
						return;
					} else {
						$("#abnormalModal").modal();
						//异常运单		
					 		var id = $("input[type=checkbox]:checked").val();
							$.ajax({
								type : "GET",
								url : "${pageContext.request.contextPath}/business/short/boxManager/exception/get.do?id="+id,
								success : function(data) {
									$("#exceptionMsg").append("<li>提报时间：<span>"+data.data.exceptionTime+"</span></li>"
											+"<li>异常提报人：<span>"+data.data.exceptionReoportName+"</span></li>"
											+"<li>异常原因：<span>"+data.data.exceptionReoportReason+"</span></li>");
								}
							});
					}
				});
				
				
				//新建运单模态框
				$("#waybill").click(function() {
					$("#addId")[0].reset();
					$('#myModal').modal();
					$("#select_project").change();	
					//$("#car_select").change();	
				});
				
				//隐藏等待发运图片位置
				$("#forward_add_img").hide();
				//确认操作后页面刷新
				$("#sure_msg").click(function() {
					window.location.reload();
					$("#promptIdModal").modal("hide");
				});
				
				//项目下拉
				 $.ajax({  
		                type: "GET",  
		                url:"${pageContext.request.contextPath}/business/short/bulkPacking/project/getAll.do?projectType="+1,  
		                success: function(data) {  
		                	$("#select_project").empty();
		    				$.each(data.data, function(index, its) {
		    					$("#select_project").append(
		    							"<option value="+its.id+">" + its.projectCode
		    									+ "</option>");
		    					
		    				});
		                }
		            });
				

				 //车辆下拉
				 $.ajax({  
		                type: "GET",  
		                url:"${pageContext.request.contextPath}/business/short/bulkPacking/carTeam/select.do",  
		                success: function(data) {  
		                	$("#car_select").empty();
		    				$.each(data.data, function(index, its) {
		    							$("#car_select").append(
				    							"<option value="+its.driverId+">" + its.plateNumber
				    									+ "</option>");
		    				});
		                }
		            });
				

				//货位引导中的货场下拉
				/*  $.ajax({  
		                type: "GET",  
		                url:"${pageContext.request.contextPath}/siteManager/freight/getAll.do",  
		                success: function(data) {  
		                	$("#select_freight_guide").empty();
		    				$.each(data.data, function(index, its) {
		    						$("#select_freight_guide").append(
			    							"<option value="+its.id+">" + its.name
			    									+ "</option>");
		    				});
		    				var  one = $("#select_freight_guide option:selected").html();
		    				$("#distributionCargoPlace").val(one);
		                }
		            });  */
				
				
				//详情
				$("body").on("click", "#panel1 tbody tr td:not(:nth-child(1)):not(:nth-child(5))", function() {
							$(this).parent().toggleClass("bgclass").siblings("#panel1 tbody tr").removeClass("bgclass");
							   var projectId = $(this).parent().attr("id");
								$.ajax({
									type : "GET",
									url : "${pageContext.request.contextPath}/business/short/bulkPacking/get.do?id="+projectId,
									success : function(data) {
						        				var sta = "";
						        				if(data.data.status ==1){
						        					sta = "等待调度";
						        				}
						        				if(data.data.status ==2){
						        					sta = "等待发运";
						        				}
						        				if(data.data.status ==3){
						        					sta = "在途运载";
						        				}
						        				if(data.data.status ==4){
						        					sta = "货位引导";
						        				}
						        				if(data.data.status ==5){
						        					sta = "等待回单";
						        				}
						        				if(data.data.status ==6){
						        					sta = "等待确认";
						        				}
						        				if(data.data.status ==7){
						        					sta = "已完成";
						        				}
										var orgin="";
										if(data.data.orderOrigin ==1){
											orgin="PC端";
										}else if(data.data.orderOrigin ==2){
											orgin="APP端";
										}
										var proType="";
										if(data.data.projectType ==0){
											proType="集装箱";
										}else if(data.data.projectType ==1){
											proType ="散装";
										}
										
										
											$("#lookModal").modal();
											$("#appendDiv").append("<div class='project_info'><h5>项目信息 </h5> <ul class='detailsUl'>"
														+"<li><label>项目编号：</label><span>"+ data.data.projectCode +"</span></li>"
														+"<li><label>项目类型：</label><span>"
														+ proType
														+"</span></li>"
														+"<li><label>分支机构：</label><span>"+data.data.branchGroupName+"</span></li>"
														+"<li><label>调度员：</label><span>"+data.data.userDispatchName+"</span></li></ul></div>"
												+"<hr />"
												+"<div class='project_info'><h5>运单信息</h5>	<ul class='detailsUl'>"
												+"<li><label>运单编号：</label><span>"+data.data.orderCode+"</span></li>"
												+"<li><label>创建时间：</label><span>"+data.data.createDate+"</span></li>"	
												+"<li class='state'><label>运单状态：</label><span>"+sta+"</span></li>"		
												+"<li><label>状态更新时间：</label><span>"+data.data.updateDate+"</span></li></ul>	</div>"
												+"<hr />"	
												+"	<div class='project_info'><h5>货物信息</h5><ul class='detailsUl'>"
												+"<li><label>货物品名：</label><span>"+data.data.cargoName+"</span></li>"
												+"<li><label>化验指标：</label><span>"+data.data.testIndicators+"</span></li>"		
												+"<li><label>运费：</label><span>"+data.data.shortBargeCost+"万元</span></li>"
												+"<li><label>补贴：</label><span>"+data.data.subsidy+"元</span></li></ul></div>"		
												+"<hr />"	
												+"<div class='project_info'><h5>收发货信息</h5>	<ul class='msgUl'>"
												+"<li><label>发货单位：</label><span>"+data.data.sendCompany+"</span></li>"
												+"<li><label>取货站：</label><span>"+data.data.pickupPlace+"</span></li>"		
												+"<li><label>收货单位：</label><span>"+data.data.receiptCompany+"</span></li>"		
												+"<li><label>运抵地：</label><span>"+data.data.arrivePlace+"</span></li>"	
												+"<li><label>运抵货场：</label><span>"+data.data.arriveFreightYrad+"</span></li>"		
												+"<li><label>运抵货位：</label><span>"+data.data.arriveFreightSite+"</span></li>"		
												+"<li><label>发货皮重：</label><span>"+data.data.sendTare+"吨</span></li>"		
												+"<li class='pul'><label>发货毛重：</label><span>"+data.data.sendGross+"吨</span></li>"		
												+"<li class='pul'><label>发货净重：</label><span>"+(data.data.containerOneSendNet+data.data.containerTwoSendNet)+"吨</span></li>"		
												+"<li><label>收货皮重：</label><span>"+data.data.receiptTare+"吨</span></li>"		
												+"<li class='pul'><label>收货毛重：</label><span>"+data.data.receiptGross+"吨</span></li>"		
												+"<li class='pul'><label>收货净重：</label><span>"+(data.data.containerOneReceiptNet+data.data.containerTwoReceiptNet)+"吨</span></li></ul></div>"		
												+"<hr/>"
												+"<div class='project_info'><h5>车辆信息</h5><ul class='detailsUl'>"
												+"<li><label>承运车辆：</label><span>"+data.data.carPlateNumber+"</span></li>"
												+"<li><label>车辆类型：</label><span>货车</span></li>"
												+"<li><label>驾驶员：</label><span>"+data.data.driverName+"</span></li>"
												+"<li><label>联系方式：</label><span>"+data.data.driverPhone+"</span></li>"		
												+"<hr />"
												+"<h4 class='blueH4'>订单来源："+orgin+"</h4>"
												+"<hr />"
												+"<div class='project_info localdiv'><h5>位置信息</h5>"
												+"<ul class='localUl'>"
												+"<li>2017-09-08 10:00</li>	<li>马鞍山市马鞍山东路</li><li>停留10分钟休息</li></ul>	<ul class='localUl'><li>2017-09-07 10:00</li><li>南京雨花台</li><li>汽车旅馆休息一夜</li>"
												+"</ul>	<ul class='localUl'><li>2017-09-08 10:00</li><li>南京市南站广场与机场东路交叉口</li><li>发生重大交通事故，行程耽误4小时</li>"
												+"</ul>	</div>");
											
										}
									})
								});
			
			})
		
			
			//等待调度点击获取详情
			function dispatcher(id){
					$("#dispatchId")[0].reset();
					$('#dispatchModal').modal();
					$.ajax({  
	   	                type: "GET",  
	   	                url:"${pageContext.request.contextPath}/business/short/bulkPacking/dispatch/getInfo.do?id="+id,  
	   	             	success: function(data) { 
	   	                	$("#dispatchContent").empty();
	   	                	$("#dispatchContent").append("<input type='hidden' value='"+data.data.tbOrder.id+"' name='orderId'>"
	   	                			+"<li><label>申请项目：</label><span>"+data.data.tbOrder.projectCode+"</span></li>"
	   								+"<li><label>申请人：</label><span>"+data.data.tbOrder.driverName+"</span></li>"
	   								+"<li><label>联系方式：</label><span>"+data.data.tbOrder.driverPhone+"</span></li>"
	   								+"<li><label>承运车辆：</label><span>"+data.data.tbOrder.carPlateNumber+"</span></li>"
	   								+"<li><label>货品名称：</label><span>"+data.data.tbOrder.cargoName+"</span></li>");
	   	                	$("#take_carogo_place_name2").empty();
	   	                	$.each(data.data.list, function(index, its) {
								$("#take_carogo_place_name2").append(
		    							"<option value="+its.id+">" + its.name + "</option>");
 						});
							$("#take_carogo_place_name2").change();
	   	                }
	   	            }); 
			}
			
			//等待发运点击获取详情
			function forwardinger(id){
				$("#forward_add_img").attr('src',"");
				$("#img_div").hide();
				
				$("#sendId")[0].reset();
				$('#waitModal').modal();
				 $.ajax({  
		                type: "GET",  
		                url:"${pageContext.request.contextPath}/business/short/bulkPacking/dispatch/getMsg.do?id="+id,  
		                success: function(data) {  
		                	$("#forwardings").append("<input type='hidden' value='"+data.data.id+"' name='orderId'>"
		                			+"<li><label>申请项目：</label><span>"+data.data.projectCode+"</span></li>"
									+"<li><label>申请人：</label><span>"+data.data.driverName+"</span></li>"
									+"<li><label>联系方式：</label><span>"+data.data.driverPhone+"</span></li>"
									+"<li><label>承运车辆：</label><span>"+data.data.carPlateNumber+"</span></li>"
									+"<li><label>货品名称：</label><span>"+data.data.cargoName+"</span></li>");
		                }
		            });
				 
				 $("#forwardings").empty();
			}
			
			
			//回收站运单还原
			function resert(){
				var idList = new Array();
				$.each($('input:checkbox'), function() {
					if (this.checked) {
						idList.push($(this).val());
					}
				});
				$.ajax({
					type : "POST",
					url : "${pageContext.request.contextPath}/business/short/bulkPacking/order/revert.do",
					dataType : 'json',
					data : {
						"idList" : idList
					},
					success : function(data) {
						if (data.status != 1) {
							$("#msg").html(data.msg);
							$("#promptIdModal").modal();
						} else {
							$("#msg").html(data.msg);
							$("#promptIdModal").modal();
						}
					}
				});
				
			}
			
			//在途运载获取详情
			function carryer(id){
				$("#carry_img").attr('src','');
				$("#carryId")[0].reset();
				$('#carryModal').modal();
				 $.ajax({  
		                type: "GET",  
		                url:"${pageContext.request.contextPath}/business/short/bulkPacking/dispatch/getMsg.do?id="+id,  
		                success: function(data) {  
		                	$("#carryProject").append("<input type='hidden' value='"+data.data.id+"' name='orderId'>"
		                			+"<li><label>申请项目：</label><span>"+data.data.projectCode+"</span></li>"
									+"<li><label>申请人：</label><span>"+data.data.driverName+"</span></li>"
									+"<li><label>联系方式：</label><span>"+data.data.driverPhone+"</span></li>"
									+"<li><label>承运车辆：</label><span>"+data.data.carPlateNumber+"</span></li>"
									+"<li><label>货品名称：</label><span>"+data.data.cargoName+"</span></li>");
		                	
		                	$("#carryContent").append("<li><label>发货皮重：</label><span>"+data.data.sendTare+"</span></li>"
									+"<li><label>发货毛重：</label><span>"+data.data.sendGross+"</span></li>"
									+"<li><label>"+"发货净重：</label><span>"+data.data.containerOneSendNet+"</span></li>");
		                	 var url = "${pageContext.request.contextPath}/upload/photo/"+data.data.orderImg;
		                	$("#carry_img").attr('src',url);
		                }
		            });
				 $("#carryProject").empty();
				 $("#carryContent").empty();
			}
			
			
			//货位引导获取详情
			function guideContent(id){
				$("#select_freight_guide").change();	
				$("#guideId")[0].reset();
				$('#guideModal').modal();
				 $.ajax({  
		                type: "GET",  
		                url:"${pageContext.request.contextPath}/business/short/bulkPacking/dispatch/guideGetInfo.do?id="+id,  
		                success: function(data) {  
		                	$("#guideContentId").empty();
		       			 	$("#guideContentId2").empty();
		                	$("#guideContentId").append("<input type='hidden' value='"+data.data.tbOrder.id+"' name='orderId'>"
		                			+"<li><label>申请项目：</label><span>"+data.data.tbOrder.projectCode+"</span></li>"
									+"<li><label>申请人：</label><span>"+data.data.tbOrder.driverName+"</span></li>"
									+"<li><label>联系方式：</label><span>"+data.data.tbOrder.driverPhone+"</span></li>"
									+"<li><label>承运车辆：</label><span>"+data.data.tbOrder.carPlateNumber+"</span></li>"
									+"<li><label>货品名称：</label><span>"+data.data.tbOrder.cargoName+"</span></li>");
		                	
		                	$("#guideContentId2").append("<li><label>发货皮重：</label><span>"+data.data.tbOrder.sendTare+"</span></li>"
									+"<li><label>发货毛重：</label><span>"+data.data.tbOrder.sendGross+"</span></li>"
									+"<li><label>发货净重：</label><span>"+data.data.tbOrder.containerOneSendNet+"</span></li>");
		                	 var url = "${pageContext.request.contextPath}/upload/photo/"+data.data.tbOrder.orderImg;
		                	$("#guide_img").attr('src',url);
		                	
		                	$("#select_freight_guide").empty();
	   	                	$.each(data.data.list, function(index, its) {
								$("#select_freight_guide").append(
		    							"<option value="+its.id+">" + its.name + "</option>");
							});
							$("#select_freight_guide").change();
		                }
		            });
			}
			
			
			//等待回单 获取详情
			function waitReceipter(id){
				$("#forward_add_img2").attr('src',"");
				$("#img_div2").hide();
				$("#receiptId")[0].reset();
				$('#receiptModal').modal();
				 $.ajax({  
		                type: "GET",  
		                url:"${pageContext.request.contextPath}/business/short/bulkPacking/dispatch/getMsg.do?id="+id,  
		                success: function(data) {  
		                	$("#receiptContent").append("<input type='hidden' value='"+data.data.id+"' name='orderId'>"
		                			+"<li><label>申请项目：</label><span>"+data.data.projectCode+"</span></li>"
									+"<li><label>申请人：</label><span>"+data.data.driverName+"</span></li>"
									+"<li><label>联系方式：</label><span>"+data.data.driverPhone+"</span></li>"
									+"<li><label>承运车辆：</label><span>"+data.data.carPlateNumber+"</span></li>"
									+"<li><label>货品名称：</label><span>"+data.data.cargoName+"</span></li>");
		                	//$("#receipt_1").html(data.data.containerNumber1);
		                	//$("#receipt_2").html(data.data.containerNumber2);
		                }
		            });
				 $("#receiptContent").empty();
				 //$("#receipt_1").empty();
	         	//$("#receipt_2").empty();
			}
			
			
			//货位 下拉
			function cargoLoExchange(){
				var id = $("#select_freight_guide option:selected").val();
				 var  freight = $("#select_freight_guide option:selected").html();
				 $("#distributionCargoPlace").val(freight);
				 $("#distributionCargoPlaceId").val(id);
				 $.ajax({  
		             type: "GET",  
		             url:"${pageContext.request.contextPath}/siteManager/cargo/location/getAll.do?id="+id,  
		             success: function(data) {  
		            		$("#select_cargo_guide").empty();
		 				$.each(data.data, function(index, its) {
		 							$("#select_cargo_guide").append(
				    							"<option value="+its.id+">" + its.name
				    									+ "</option>");
		 				});
		 				var  yardId = $("#select_cargo_guide option:selected").val();
		 				var  yardName = $("#select_cargo_guide option:selected").html();
		 				$("#distributionCargoSite").val(yardName);
		 				$("#distributionCargoSiteId").val(yardId);
		             }
		         });   
			} 
			
			//新建-提货货场货位切换效果
			function set_selectValue1(param){
				var  cont =$("#take_carogo_place_name"+param+" option:selected").val();
				var  contName =$("#take_carogo_place_name"+param+" option:selected").text();
				$("#take_cargo_place_id"+param+"").val(cont);
				$("#take_carogo_place_name"+(param+2)+"").val(contName);
				$.ajax({  
		             type: "GET",  
		             url:"${pageContext.request.contextPath}/siteManager/cargo/location/getAll.do?id="+cont,  
		             success: function(data) {  
		            		$("#take_cargo_site_name"+param+"").empty();
		 				$.each(data.data, function(index, its) {
		 							$("#take_cargo_site_name"+param+"").append(
				    							"<option value="+its.id+">" + its.name + "</option>");
		 				});
		 				$("#take_cargo_site_name"+param+"").change();
		             }
		         }); 
			}
			function set_selectValue2(param){
				var  cont = $("#take_cargo_site_name"+param+" option:selected").val();
				var  contName = $("#take_cargo_site_name"+param+" option:selected").text();
				$("#take_cargo_site_id"+param+"").val(cont);
				$("#take_cargo_site_name"+(param+2)+"").val(contName);
			}
			
			// 运单新增
			function  orderAdds(){
				$.ajax({
					type : "POST",
					url : "${pageContext.request.contextPath}/business/short/bulkPacking/order/add.do",
					dataType : 'json',
					data : $("#addId").serialize(),
					success : function(data) {
						if (data.status != 1) {
							$("#msg").html(data.msg);
							$("#promptIdModal").modal();
						} else {
							$("#myModal").hide();
							$("#msg").html(data.msg);
							$("#promptIdModal").modal();
						}
					}
				});
			}
			
			// 货位引导 提交
			function guideFuns(){
				$.ajax({
					type : "POST",
					url : "${pageContext.request.contextPath}/business/short/bulkPacking/guide/add.do",
					data : $('#guideId').serialize(),
					async : false,
					success : function(data) {
						if (data.status != 1) {
							$("#msg").html(data.msg);
							$("#promptIdModal").modal();
						} else {
							$("#msg").html(data.msg);
							$("#promptIdModal").modal();
							$('#guideModal').hide();
						}
					}
				});
			}
			
			//新增-车辆下拉信息详情
			function carSelect(){
				//清空车辆信息文本框
				$("input[name='carrierVehicleId']").val("");
	        	$("input[name='carrierVehicleName']").val("");
	        	$("input[name='driverName']").val("");
	        	$("input[name='driverPhone']").val("");
				$("input[name='carType']").val("");
				$("input[name='carPlateNumber']").val("");
				$("input[name='shortBargeCost']").val("");
				$("input[name='deductionPrice']").val("");
				
				var id = $("#car_select option:selected").val(); 
				 $.ajax({  
		                type: "GET",  
		                url:"${pageContext.request.contextPath}/business/short/boxManager/carTeam/select.do?driverId="+id,  
		                success: function(data) {  
		                	$("input[name='carrierVehicleId']").val(data.data[0].driverId);
		                	$("input[name='carrierVehicleName']").val(data.data[0].driverName);
		                	$("input[name='driverId']").val(data.data[0].driverId);
		                	$("input[name='driverName']").val(data.data[0].driverName);
		                	$("input[name='driverPhone']").val(data.data[0].driverPhone);
		    				$("input[name='carType']").val(data.data[0].motorcycleType);
		    				$("input[name='carPlateNumber']").val(data.data[0].plateNumber);
		    				$("input[name='shortBargeCost']").val(data.data[0].transportPrice);
							$("input[name='deductionPrice']").val(data.data[0].deductionPrice);
		                }
		            }); 
			}
			
			//获取项目详情
			function exchangeProject(){
				$("#stepSelectCodes").empty();
				
				//清空车辆信息文本框
				$("input[name='carrierVehicleId']").val("");
	        	$("input[name='carrierVehicleName']").val("");
	        	$("input[name='driverName']").val("");
	        	$("input[name='driverPhone']").val("");
				$("input[name='carType']").val("");
				$("input[name='carPlateNumber']").val("");
				$("input[name='shortBargeCost']").val("");
				$("input[name='deductionPrice']").val("");
				$("input[name='forwardingSiteId']").val("");
				
				var id = $("#select_project option:selected").val();
				$.ajax({
					type : "GET",
					url : "${pageContext.request.contextPath}/business/short/boxManager/selectProject.do?id="+id,
					dataType : 'json',
					success : function(data) {
						if(data.data.projectType ==0){
							$("input[name='projectTypeName']").val("集装箱");	
						}else{
							$("input[name='projectTypeName']").val("散装");	
						}
						$("input[name='projectCode']").val(data.data.projectCode);
						$("input[name='projectType']").val(data.data.projectType);		 
						$("input[name='branchGroupId']").val(data.data.branchGroupId);			
						$("input[name='branchGroupName']").val(data.data.branchGroupName);			
						$("input[name='cargoName']").val(data.data.cargoName);
						$("input[name='specifications']").val(data.data.cargoSpecifications);
						$("input[name='forwardingSiteId']").val(data.data.forwardingSiteId);
						
						if(data.data.transportType  == 1){
							//接取
							$("input[name='sendCompany']").val(data.data.sendCargoUnit.companyName);
							$("input[name='pickupPlace']").val(data.data.sendCargoUnit.addressCode);
							$("input[name='pickupPlaceAddress']").val(data.data.sendCargoUnit.detailAddress);
							$("input[name='receiptCompany']").val(data.data.receiveTrainStation.stationName);
							$("input[name='arrivePlace']").val(data.data.receiveTrainStation.adressCode);
							$("input[name='arriveAddress']").val(data.data.receiveTrainStation.detailAddress);
						}
						//送达
						if(data.data.transportType  == 3){
							$("input[name='sendCompany']").val(data.data.sendTrainStation.stationName);
							$("input[name='pickupPlace']").val(data.data.sendTrainStation.adressCode);
							$("input[name='pickupPlaceAddress']").val(data.data.sendTrainStation.detailAddress);
							$("input[name='receiptCompany']").val(data.data.receivingDepartment.companyName);
							$("input[name='arrivePlace']").val(data.data.receivingDepartment.addressCode);
							$("input[name='arriveAddress']").val(data.data.receivingDepartment.detailAddress);
						}
						//汽运
						if(data.data.transportType  == 0){
							$("input[name='sendCompany']").val(data.data.sendCargoUnit.companyName);
							$("input[name='pickupPlace']").val(data.data.sendCargoUnit.adressCode);
							$("input[name='pickupPlaceAddress']").val(data.data.sendCargoUnit.detailAddress);
							$("input[name='receiptCompany']").val(data.data.receivingDepartment.companyName);
							$("input[name='arrivePlace']").val(data.data.receivingDepartment.addressCode);
							$("input[name='arriveAddress']").val(data.data.receivingDepartment.detailAddress);
						}
						//接取+火运
						if(data.data.transportType  == 4){
							$("input[name='sendCompany']").val(data.data.sendCargoUnit.companyName);
							$("input[name='pickupPlace']").val(data.data.sendCargoUnit.adressCode);
							$("input[name='pickupPlaceAddress']").val(data.data.sendCargoUnit.detailAddress);
							$("input[name='receiptCompany']").val(data.data.receiveTrainStation.stationName);
							$("input[name='arrivePlace']").val(data.data.receiveTrainStation.addressCode);
							$("input[name='arriveAddress']").val(data.data.receiveTrainStation.detailAddress);
						}
						
						//火运+送达
						if(data.data.transportType  == 5){
							$("input[name='sendCompany']").val(data.data.sendTrainStation.stationName);
							$("input[name='pickupPlace']").val(data.data.sendTrainStation.adressCode);
							$("input[name='pickupPlaceAddress']").val(data.data.sendTrainStation.detailAddress);
							$("input[name='receiptCompany']").val(data.data.receivingDepartment.companyName);
							$("input[name='arrivePlace']").val(data.data.receivingDepartment.addressCode);
							$("input[name='arriveAddress']").val(data.data.receivingDepartment.detailAddress);
						}
						//联运
						if(data.data.transportType  == 6){
							$("input[name='sendCompany']").val(data.data.sendCargoUnit.companyName);
							$("input[name='pickupPlace']").val(data.data.sendCargoUnit.adressCode);
							$("input[name='pickupPlaceAddress']").val(data.data.sendCargoUnit.detailAddress);
							$("input[name='receiptCompany']").val(data.data.receiveTrainStation.stationName);
							$("input[name='arrivePlace']").val(data.data.receiveTrainStation.addressCode);
							$("input[name='arriveAddress']").val(data.data.receiveTrainStation.detailAddress);
						}
						
						//接取+送达
						if(data.data.transportType  == 7){
							$("input[name='sendCompany']").val(data.data.sendCargoUnit.companyName);
							$("input[name='pickupPlace']").val(data.data.sendCargoUnit.adressCode);
							$("input[name='pickupPlaceAddress']").val(data.data.sendCargoUnit.detailAddress);
							$("input[name='receiptCompany']").val(data.data.receiveTrainStation.stationName);
							$("input[name='arrivePlace']").val(data.data.receiveTrainStation.addressCode);
							$("input[name='arriveAddress']").val(data.data.receiveTrainStation.detailAddress);
						}
						if(data.data.valuationUnitName ==0){
							$("input[name='valuationUnitType']").val("件");
						}else if(data.data.valuationUnitName ==1){
							$("input[name='valuationUnitType']").val("吨");
						}
						$("input[name='carTeamId']").val(data.data.shortBargeId);
						
						if(data.data.transportType == 6 || data.data.transportType == 7){
							$("#stepSelectCodes").append("<option value='0'>接取</option> <option value='1'>送达</option>");
							$("#stepSelectCodes").change();
						} 
						if(data.data.transportType == 0){
							$("#stepSelectCodes").append("<option value='2'>汽运</option>");
						}
						if(data.data.transportType == 1){
							$("#stepSelectCodes").append("<option value='0'>接取</option>");
							$("#stepSelectCodes").change();
						}
						if(data.data.transportType == 2){
							$("#stepSelectCodes").append("<option value='1'>送达</option>");
							$("#stepSelectCodes").change();
						}
						 //车辆下拉
						 $.ajax({  
				                type: "GET",  
				                url:"${pageContext.request.contextPath}/business/short/boxManager/carTeam/select.do?id="+data.data.id+"&mode="+data.data.shortBargeCarrierMode,  
				                success: function(data) {  
				                	$("#car_select").empty();
				    				$.each(data.data, function(index, its) {
				    							$("#car_select").append(
						    							"<option value="+its.driverId+">" + its.plateNumber
						    									+ "</option>");
				    				});
				    				$("#car_select").change();	
				                }
				           }); 
					}
				});
			}
			
			
			
			//等待发运度提交
			function subForwarding(){
				$.ajax({
					type : "POST",
					url : "${pageContext.request.contextPath}/business/short/bulkPacking/subForwarding/add.do",
					data : $('#sendId').serialize(),
					async : false,
					success : function(data) {
						$("#forward_add_img").attr('src','');
						if (data.status != 1) {
							$("#msg").html(data.msg);
							$("#promptIdModal").modal();
						} else {
							$("#msg").html(data.msg);
							$("#promptIdModal").modal();
							$('#waitModal').hide();
						}
					}
				});
			}
			
			//等待调度提交
			function submits(){
				$.ajax({
					type : "POST",
					url : "${pageContext.request.contextPath}/business/short/bulkPacking/dispatch/add.do",
					data : $('#dispatchId').serialize(),
					async : false,
					success : function(data) {
						if (data.status != 1) {
							$("#msg").html(data.msg);
							$("#promptIdModal").modal();
						} else {
							$("#msg").html(data.msg);
							$("#promptIdModal").modal();
							$('#dispatchModal').hide();
						}
					}
				});
			}
		
			// 等待回单 提交
			function receipterFuns(){
				$.ajax({
					type : "POST",
					url : "${pageContext.request.contextPath}/business/short/bulkPacking/receipter/add.do",
					data : $('#receiptId').serialize(),
					async : false,
					success : function(data) {
						if (data.status != 1) {
							$("#msg").html(data.msg);
							$("#promptIdModal").modal();
						} else {
							$("#msg").html(data.msg);
							$("#promptIdModal").modal();
							$('#receiptModal').hide();
						}
					}
				});
			}
			
			//删除订单
			function delsOrder(){
				$("#deletReasonModal").hide();
				var idList = new Array();
				$.each($('input:checkbox'), function() {
					if (this.checked) {
						idList.push($(this).val());
					}
				});
				var reason = $("#delete_reason").val();
				$.ajax({
					type : "POST",
					url : "${pageContext.request.contextPath}/business/short/bulkPacking/order/delete.do",
					dataType : 'json',
					data : {
						"idList" : idList,
						"reason":reason
					},
					success : function(data) {
						if (data.status != 1) {
							$("#msg").html(data.msg);
							$("#promptIdModal").modal();
						} else {
							$("#msg").html(data.msg);
							$("#promptIdModal").modal();
						}
					}
				});
			}
			
			//在途运载 提交
			function carrySub(){
				$.ajax({
					type : "POST",
					url : "${pageContext.request.contextPath}/business/short/bulkPacking/carry/add.do",
					data : $('#carryId').serialize(),
					async : false,
					success : function(data) {
						if (data.status != 1) {
							$("#msg").html(data.msg);
							$("#promptIdModal").modal();
						} else {
							$("#msg").html(data.msg);
							$("#promptIdModal").modal();
							$('#carryModal').hide();
						}
					}
				});
			}
			
			
			
			//阶段切换
			function stepChange(){
				$("input[name='sendCompany']").val("");
				$("input[name='pickupPlace']").val("");
				$("input[name='pickupPlaceAddress']").val("");
				$("input[name='receiptCompany']").val("");
				$("input[name='arrivePlace']").val("");
				$("input[name='arriveAddress']").val("");
				var id = $("#select_project option:selected").val();
				$.ajax({
					type : "GET",
					url : "${pageContext.request.contextPath}/business/short/boxManager/selectProject.do?id="+id,
					dataType : 'json',
					success : function(data) {
						var step = $("#stepSelectCodes option:selected").val();
						
						if(data.data.transportType  == 1){
							//接取
							$("input[name='sendCompany']").val(data.data.sendCargoUnit.companyName);
							$("input[name='pickupPlace']").val(data.data.sendCargoUnit.addressCode);
							$("input[name='pickupPlaceAddress']").val(data.data.sendCargoUnit.detailAddress);
							$("input[name='receiptCompany']").val(data.data.receiveTrainStation.stationName);
							$("input[name='arrivePlace']").val(data.data.receiveTrainStation.adressCode);
							$("input[name='arriveAddress']").val(data.data.receiveTrainStation.detailAddress);
						}
						//送达
						if(data.data.transportType  == 3){
							$("input[name='sendCompany']").val(data.data.sendTrainStation.stationName);
							$("input[name='pickupPlace']").val(data.data.sendTrainStation.adressCode);
							$("input[name='pickupPlaceAddress']").val(data.data.sendTrainStation.detailAddress);
							$("input[name='receiptCompany']").val(data.data.receivingDepartment.companyName);
							$("input[name='arrivePlace']").val(data.data.receivingDepartment.addressCode);
							$("input[name='arriveAddress']").val(data.data.receivingDepartment.detailAddress);
						}
						//汽运
						if(data.data.transportType  == 0){
							$("input[name='sendCompany']").val(data.data.sendCargoUnit.companyName);
							$("input[name='pickupPlace']").val(data.data.sendCargoUnit.adressCode);
							$("input[name='pickupPlaceAddress']").val(data.data.sendCargoUnit.detailAddress);
							$("input[name='receiptCompany']").val(data.data.receivingDepartment.companyName);
							$("input[name='arrivePlace']").val(data.data.receivingDepartment.addressCode);
							$("input[name='arriveAddress']").val(data.data.receivingDepartment.detailAddress);
						}
						//接取+火运
						if(data.data.transportType  == 4){
							$("input[name='sendCompany']").val(data.data.sendCargoUnit.companyName);
							$("input[name='pickupPlace']").val(data.data.sendCargoUnit.adressCode);
							$("input[name='pickupPlaceAddress']").val(data.data.sendCargoUnit.detailAddress);
							$("input[name='receiptCompany']").val(data.data.receiveTrainStation.stationName);
							$("input[name='arrivePlace']").val(data.data.receiveTrainStation.addressCode);
							$("input[name='arriveAddress']").val(data.data.receiveTrainStation.detailAddress);
						}
						
						//火运+送达
						if(data.data.transportType  == 5){
							$("input[name='sendCompany']").val(data.data.sendTrainStation.stationName);
							$("input[name='pickupPlace']").val(data.data.sendTrainStation.adressCode);
							$("input[name='pickupPlaceAddress']").val(data.data.sendTrainStation.detailAddress);
							$("input[name='receiptCompany']").val(data.data.receivingDepartment.companyName);
							$("input[name='arrivePlace']").val(data.data.receivingDepartment.addressCode);
							$("input[name='arriveAddress']").val(data.data.receivingDepartment.detailAddress);
						}
						//联运
						if(data.data.transportType  == 6){
							if(step == 0){
								$("input[name='sendCompany']").val(data.data.sendCargoUnit.companyName);
								$("input[name='pickupPlace']").val(data.data.sendCargoUnit.adressCode);
								$("input[name='pickupPlaceAddress']").val(data.data.sendCargoUnit.detailAddress);
								$("input[name='receiptCompany']").val(data.data.receiveTrainStation.stationName);
								$("input[name='arrivePlace']").val(data.data.receiveTrainStation.addressCode);
								$("input[name='arriveAddress']").val(data.data.receiveTrainStation.detailAddress);
							}else if(step == 1){
								$("input[name='sendCompany']").val(data.data.sendTrainStation.stationName);
								$("input[name='pickupPlace']").val(data.data.sendTrainStation.adressCode);
								$("input[name='pickupPlaceAddress']").val(data.data.sendTrainStation.detailAddress);
								$("input[name='receiptCompany']").val(data.data.receivingDepartment.companyName);
								$("input[name='arrivePlace']").val(data.data.receivingDepartment.addressCode);
								$("input[name='arriveAddress']").val(data.data.receivingDepartment.detailAddress);
							}
						
						}
						
						//接取+送达
						if(data.data.transportType  == 7){
							if(step == 0){
								$("input[name='sendCompany']").val(data.data.sendCargoUnit.companyName);
								$("input[name='pickupPlace']").val(data.data.sendCargoUnit.adressCode);
								$("input[name='pickupPlaceAddress']").val(data.data.sendCargoUnit.detailAddress);
								$("input[name='receiptCompany']").val(data.data.receiveTrainStation.stationName);
								$("input[name='arrivePlace']").val(data.data.receiveTrainStation.addressCode);
								$("input[name='arriveAddress']").val(data.data.receiveTrainStation.detailAddress);
							}else if(step == 1){
								$("input[name='sendCompany']").val(data.data.sendTrainStation.stationName);
								$("input[name='pickupPlace']").val(data.data.sendTrainStation.adressCode);
								$("input[name='pickupPlaceAddress']").val(data.data.sendTrainStation.detailAddress);
								$("input[name='receiptCompany']").val(data.data.receivingDepartment.companyName);
								$("input[name='arrivePlace']").val(data.data.receivingDepartment.addressCode);
								$("input[name='arriveAddress']").val(data.data.receivingDepartment.detailAddress);
							}
						}
						 //阶段选择为接取  查询所有独立货场 为送达 查询接取站点货场forwarding_site_id
						 if(step == 0){
							 $.ajax({
									type : "GET",
									url : "${pageContext.request.contextPath}/siteManager/getAllFreightYardOfIsolated.do",
									dataType : 'json',
									success : function(data) {
										$("#take_carogo_place_name1").empty();
										$.each(data.data, function(index, its) {
											$("#take_carogo_place_name1").append(
					    							"<option value="+its.id+">" + its.name + "</option>");
			    						});
										$("#take_carogo_place_name1").change();
									}
							 });
						 }else if(step == 1){
							 var trainStationId = $("#forwardingSiteId").val();
							 $.ajax({
									type : "POST",
									url : "${pageContext.request.contextPath}/siteManager/getFreightYardByStationId.do",
									data :{id:trainStationId},
									dataType : 'json',
									success : function(data) {
										$("#take_carogo_place_name1").empty();
										$.each(data.data, function(index, its) {
											$("#take_carogo_place_name1").append(
					    							"<option value="+its.id+">" + its.name + "</option>");
			    						});
										$("#take_carogo_place_name1").change();
									}
							 });
						 }
		                }
		            }); 
				
			}
			
		</script>
		
		
	<script type="text/javascript">
	function clickImg(param){
	    $(param).parent('div').children('input').click();    		
	}
	
 	<!--图片上传-->
        function imgUpload(file){
        	$("#forward_add_img").show();
        	$("#forward_add_img2").show();
        	$("#img_div").show();
        	$("#img_div2").show();
			run(file, function (data) {
				$("#add_up_freightYardImg").val(data);
				$("#add_up_freightYardImg2").val(data);
				$("#forward_add_img").attr('src',data);
				$("#forward_add_img2").attr('src',data);
				$("#clickImgShow").empty();
            });
		}
        function run(input_file, get_data) { 
            /*input_file：文件按钮对象*/  
            /*get_data: 转换成功后执行的方法*/  
            if (typeof (FileReader) === 'undefined') {  
                $("#pointResult").html("");
     			$("#promptModal").modal();
     			$("#pointResult").html("抱歉，你的浏览器不支持 FileReader，不能将图片转换为Base64，请使用现代浏览器操作！");
            } else {  
                try {  
                    /*图片转Base64 核心代码*/  
                    var file = input_file.files[0];
                    //这里我们判断下类型如果不是图片就返回 去掉就可以上传任意文件  
                    if (!/image\/\w+/.test(file.type)) {  
                        $("#pointResult").html("");
             			$("#promptModal").modal();
             			$("#pointResult").html("请确保文件为图像类型");
                        return false;  
                    } 
                    if(file.size>204800){
                    	 $("#pointResult").html("");
             			$("#promptModal").modal();
             			$("#pointResult").html("请上传大小小于200k的图片");
                    	 return false;  
                    }
                    var reader = new FileReader();  
                    reader.onload = function () {  
                        get_data(this.result);  
                    }  
                    reader.readAsDataURL(file);  
                } catch (e) {  
                	$("#pointResult").html("");
        			$("#promptModal").modal();
        			$("#pointResult").html('图片转Base64出错啦！');
                   // alert( + e.toString())  
                }  
            }  
        }  
	
	</script>	
	</head>

	<!--搜索-->
	<script>
	function search(param) {
		var search = JSON.stringify($(param).parent('a').parent('div').parent('form').serializeJSON());
		var type = $('#type').val();
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/business/short/bulkPacking/byPage/list.do',
			data: {
				page: 1,
				type: type,
				order: search
			},
			cache: false,
			dataType: 'json',
			success: function(data) {
				var boxOrderList = data.data;

				var tbody = "tbody_1";
				if(type == 1) {
					tbody = "tbody_1";
				} else if(type == 2) {
					tbody = "tbody_2";
				} else if(type == 3) {
					tbody = "tbody_3";
				}
				$("#" + tbody + "").html("");

				$.each(boxOrderList, function(index, it) {
					if(it.exceptionStatus == 0 && it.status != 7) {
						var sta = "";
						if(it.status == 1) {
							sta = "<td><a href='javascript:void(0)' id='dispatch' onclick='dispatcher(it.id)'>等待调度</a></td>";
						}
						if(it.status == 2) {
							sta = "<td><a href='javascript:void(0)' id='forwarding' onclick='forwardinger(it.id)'>等待发运</a></td>";
						}
						if(it.status == 3) {
							sta = "<td><a href='javascript:void(0)'  id='carry' onclick='carryer(it.id)'>在途运载</a></td>";
						}
						if(it.status == 4) {
							sta = "<td><a href='javascript:void(0)' id='guide' onclick='guideContent(it.id)'>货物引导</a></td>";
						}
						if(it.status == 5) {
							sta = "<td><a href='javascript:void(0)' id='waitReceipt' onclick='waitReceipter(it.id)'>等待回单</a></td>";
						}
						if(it.status == 6) {
							sta = "<td>计费确认</td>";
						}
						if(it.status == 7) {
							sta = "已完成";
						}
						if(type == 1) {
							$("#" + tbody + "").append("<tr id='" + it.id + "'><td><label class='demo--label'>" +
								" <input class='demo--checkbox' name='WaybillName' type='checkbox' value='" + it.id + "'>" +
								" <span class='demo--checkboxInput'></span></label></td>" +
								"<td>" + it.projectCode + "</td>" +
								"<td> <a href='#'>" + it.orderCode + "</a></td>" +
								"<td>" + it.createDate + "</td>" +
								sta +
								"<td>" + it.updateDate + "</td>" +
								"<td>" + it.userDispatchName + "</td>" +
								"<td>" + it.branchGroupName + "</td>" +
								"<td>" + it.cargoName + "</td>" +
								"<td>" + it.testIndicators + "</td>" +
								"<td>" + it.sendCompany + "</td>" +
								"<td>" + it.containerOneSendNet + "吨</td>" +
								"<td>" + it.receiptCompany + "</td>" +
								"<td>" + it.containerOneReceiptNet + "吨</td>" +
								"<td>" + it.arriveFreightYrad + "</td>" +
								"<td>" + it.arriveFreightSite + "</td>" +
								"<td>" + it.carPlateNumber + "</td>"
								/* +"<td>"+it.containerNumber1 +"</td>"	
								+"<td>"+it.containerNumber2+"</td> */
								+ "</tr>");
						} else if(type == 2) {
							$('#tbody_2').append("<tr><td><label class='demo--label'>" +
								" <input class='demo--checkbox' name='WaybillName' type='checkbox' value='" + it.id + "'>" +
								" <span class='demo--checkboxInput'></span></label></td>" +
								"<td>" + it.projectCode + "</td>" +
								"<td> <a href='#'>" + it.orderCode + "</a></td>" +
								"<td>" + it.createDate + "</td>" +
								sta +
								"<td>" + it.updateDate + "</td>" +
								"<td>" + it.userDispatchName + "</td>" +
								"<td>" + it.branchGroupName + "</td>" +
								"<td>" + it.cargoName + "</td>" +
								"<td>" + it.testIndicators + "</td>" +
								"<td>" + it.sendCompany + "</td>" +
								"<td>" + it.containerOneSendNet + "吨</td>" +
								"<td>" + it.receiptCompany + "</td>" +
								"<td>" + it.containerOneReceiptNet + "吨</td>" +
								"<td>" + it.arriveFreightYrad + "</td>" +
								"<td>" + it.arriveFreightSite + "</td>" +
								"<td>" + it.carPlateNumber + "</td>" +
								"<td>" + it.containerNumber1 + "</td>" +
								"<td>" + it.containerNumber2 + "</td>" +
								"<td>" + it.exceptionTime + "</td>" +
								"<td>" + it.exceptionReoportName + "</td>" +
								"<td>" + it.exceptionReoportReason + "</td></tr>");
						} else if(type == 3) {
							$('#tbody_3').append("<tr><td><label class='demo--label'>" +
								" <input class='demo--checkbox' name='WaybillName' type='checkbox' value='" + it.id + "'>" +
								" <span class='demo--checkboxInput'></span></label></td>" +
								"<td>" + it.projectCode + "</td>" +
								"<td> <a href='#'>" + it.orderCode + "</a></td>" +
								"<td>" + it.createDate + "</td>" +
								sta +
								"<td>" + it.updateDate + "</td>" +
								"<td>" + it.userDispatchName + "</td>" +
								"<td>" + it.branchGroupName + "</td>" +
								"<td>" + it.cargoName + "</td>" +
								"<td>" + it.testIndicators + "</td>" +
								"<td>" + it.sendCompany + "</td>" +
								"<td>" + it.containerOneSendNet + "吨</td>" +
								"<td>" + it.receiptCompany + "</td>" +
								"<td>" + it.containerOneReceiptNet + "吨</td>" +
								"<td>" + it.arriveFreightYrad + "</td>" +
								"<td>" + it.arriveFreightSite + "</td>" +
								"<td>" + it.carPlateNumber + "</td>" +
								"<td>" + it.containerNumber1 + "</td>" +
								"<td>" + it.containerNumber2 + "</td>");
						}
					}
				})
			}
		})
	}
</script>
	
	<body>
		<!-- 散堆装管理表单 -->
		<div class="form project_form container_top">
			<form class="form-inline maxWidth" id='orderSearchForm'>
				<div id="wrap">
					<div class="form-group">
						<label>项目编号：</label>
						<input name='projectCode' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>分支机构：</label>
						<select name="branchGroupName">
							<option></option>
							<c:forEach items="${branchGroups}" var="branchGroup">
								<option value="${branchGroup.name}">${branchGroup.name}</option>	
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label>项目类型：</label>
						<select name="projectType">
							<option></option>
							<!-- <option value="0">集装箱</option> -->
							<option value="1">散装</option>
						</select>
					</div>
					<div class="form-group">
						<label>运单编号：</label>
						<input name='orderCode' type="text" maxlength="30"/>
					</div>

					<div class="form-group">
						<label>货物品名：</label>
						<select name="cargoName">
							<option></option>
							<c:forEach items="${cargos}" var="cargo">
								<option value="${cargo.cargoName}">${cargo.cargoName}</option>	
							</c:forEach>
						</select>
					</div>
					<div class="form-group dataTimes">
						<label>&emsp;日期从：</label>
						<input type="text" name="beginDate" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-M-d'})" />
						<i class="fa fa-calendar-check-o " id="calendar2"></i>
					</div>
					<div class="form-group dataTimes">
						<label>至</label>
						<input type="text" name='endDate' class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-M-d'})" />
						<i class="fa  fa-calendar-check-o " id="calendar2"></i>
					</div>
					<div class="form-group">
						<label>发货单位：</label>
						<input name='sendCompany' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>收货单位：</label>
						<input name='receiptCompany' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>阶段选择：</label>
						<select name='stepSelectCode'>
							<option></option>
							<option value='0'>接取</option>
							<option value='1'>送达</option>
							<option value="2">汽运</option>
						</select>
					</div>
					<div class="form-group">
						<label>承运车辆：</label>
						<input name='carPlateNumber' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>&emsp;&emsp;货场：</label>
						<select></select>
					</div>
					<div class="form-group">
						<label>&emsp;&emsp;货位：</label>
						<select></select>
					</div>
				</div>
				<div class="foot">
					<a class="btn btn-success" href="#"><em class="search"></em>
						<span  onclick="search(this)">搜索</span>
					</a>
					<div id="lookMore"></div>
				</div>
			</form>
			<input type="hidden" id="type" value="1" />
		</div>
		<!--散堆装管理表格-->
		<div class="container_bottom tableDiv">
			<div class="tabbable" id="tabs-529262">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#panel1" data-toggle="tab">运单列表</a>
					</li>
					<li>
						<a href="#panel2" data-toggle="tab">异常运单</a>
					</li>
					<li>
						<a href="#panel3" data-toggle="tab">历史运单</a>
					</li>
					<li>
						<a href="#panel4" data-toggle="tab">运单回收站</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="panel1">
						<div class="domain">
							<div class="tableBg">
								<a href="#" class="exportBtn add" id="waybill" onclick="return false;"><span>新建</span></a>
								<!--<a href="#" class="exportBtn look"><span>查看</span></a>-->
								<a href="#" class="exportBtn export" onclick="return false;"><span>导出</span></a>
								<a href="#" class="exportBtn del" id="delBtn" onclick="return false;"><span>删除</span></a>
								<a href="#" class="exportBtn msgbtn msg" onclick="return false;"><span>短信告知</span></a>
							</div>
							<div class="table-responsive">
								<!--运单列表表格开始-->
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th>
												<label class="demo--label">
													  <input class="demo--checkbox" name="WaybillName" type="checkbox" >
                                                 <span class="demo--checkboxInput"></span>
	                                          </label>
											</th>
											<th data-field="id">项目编号</th>
											<th>运单编号</th>
											<th>创建时间</th>
											<th>运单状态</th>
											<th>状态更新时间</th>
											<th>调度员</th>
											<th>分支机构</th>
											<th>货物品名</th>
											<th>化验指标</th>
											<th>发货单位</th>
											<th>发货净重</th>
											<th>收货企业</th>
											<th>收货净重</th>
											<th>货场</th>
											<th>货位</th>
											<th>承运车辆</th>
										</tr>
									</thead>
									<tbody id="tbody_1">
									<c:forEach items="${bulkOrderList.rows}" var="it">
										<c:if test="${it.exceptionStatus ==0 and it.status !=7}">
										<tr id="${it.id}">
											<td>
												<label class="demo--label">
													  <input class="demo--checkbox" name="WaybillName" type="checkbox" value="${it.id}">
                                                 <span class="demo--checkboxInput"></span>
	                                          </label>
											</td>
											<td>${it.projectCode}</td>
											<td>
												<a href="#">${it.orderCode}</a>
											</td>
											<td><fmt:formatDate value="${it.createDate}"
														pattern="yyyy-MM-dd HH:mm" />
											</td>
											<td>
												<c:if test="${it.status ==1}">
														<a href="#" id="dispatch" onclick="dispatcher(${it.id})">等待调度</a>
													</c:if>
													<c:if test="${it.status ==2}">
														<a href="#" id="forwarding" onclick="forwardinger(${it.id})">等待发运</a>
													</c:if>
													<c:if test="${it.status ==3}">
															<a href="#"  id="carry" onclick="carryer(${it.id})">在途运载</a>
													</c:if>
													<c:if test="${it.status ==4}">
														<a href="#" id="guide" onclick="guideContent(${it.id})">货物引导</a>
													</c:if>
													<c:if test="${it.status ==5}">
													<a href="#" id="waitReceipt" onclick="waitReceipter(${it.id})">等待回单</a>
													</c:if>
													<c:if test="${it.status ==6}">
														计费确认
													</c:if>
													<c:if test="${it.status ==7}">
														已完成
													</c:if>
											</td>
											<td><fmt:formatDate value="${it.updateDate}"
														pattern="yyyy-MM-dd HH:mm" /></td>
											<td>${it.userDispatchName}</td>
											<td>${it.branchGroupName}</td>
											<td>${it.cargoName}</td>
											<td>${it.testIndicators}</td>
											<td>${it.sendCompany}</td>
											<td>${it.containerOneSendNet}吨</td>
											<td>${it.receiptCompany}</td>
											<td>${it.containerOneReceiptNet }吨</td>
											<td>${it.arriveFreightYrad}</td>
											<td>${it.arriveFreightSite}</td>
											<td>${it.carPlateNumber}</td>
										</tr>
										</c:if>
									</c:forEach>

									</tbody>
								</table>
								<!--运单列表表格结束-->
							</div>
						</div>
						<div class="row clearfix">
						<div class="col-md-12 column paging page_div" id="page_1">
							</div>
						</div>
					</div>

					<div class="tab-pane" id="panel2">
						<div class="domain">
							<div class="tableBg">
								<a href="#" class="exportBtn abnorm" onclick="return false;"><span>异常处理</span></a>
								<!--<a href="#" class="exportBtn"><em class="look"></em><span>查看</span></a>-->
								<a href="#" class="exportBtn export" onclick="return false;"><span>导出</span></a>
								<!-- <a href="#" class="exportBtn del" id='abnormalBtn' onclick="return false;"><span>删除</span></a> -->
								<a href="#" class="exportBtn msgbtn msg" onclick="return false;"><span>短信告知</span></a>
							</div>
							<div class="table-responsive">
								<!--异常运单列表表格开始-->
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th>
												<label class="demo--label">
													  <input class="demo--checkbox" name="abnormalName" type="checkbox" >
                                                 <span class="demo--checkboxInput"></span>
	                                           </label>
												<th>项目编号</th>
												<th>运单编号</th>
												<th>创建时间</th>
												<th>运单状态</th>
												<th>状态更新时间</th>
												<th>调度员</th>
												<th>分支机构</th>
												<th>货物品名</th>
												<th>化验指标</th>
												<th>发货单位</th>
												<th>发货净重</th>
												<th>收货企业</th>
												<th>收货净重</th>
												<th>货场</th>
												<th>货位</th>
												<th>承运车辆</th>
												<th>提报时间</th>
												<th>异常提报人</th>
												<th>异常原因</th>
										</tr>
									</thead>
									<tbody id="tbody_2">
										<c:forEach items="${bulkOrderList.rows}" var="it">
										<c:if test="${it.exceptionStatus ==1}">
										<tr>
											<td>
												<label class="demo--label">
													  <input class="demo--checkbox" name="WaybillName" type="checkbox" value="${it.id}">
                                                 <span class="demo--checkboxInput"></span>
	                                          </label>
											</td>
											<td>${it.projectCode}</td>
											<td>
												<a href="#">${it.orderCode}</a>
											</td>
											<td><fmt:formatDate value="${it.createDate}"
														pattern="yyyy-MM-dd HH:mm" />
											</td>
											<td>
													<c:if test="${it.status ==1}">
														等待调度
													</c:if>
													<c:if test="${it.status ==2}">
														等待发运
													</c:if>
													<c:if test="${it.status ==3}">
														在途运载
													</c:if>
													<c:if test="${it.status ==4}">
														货物引导
													</c:if>
													<c:if test="${it.status ==5}">
													   	等待回单
													</c:if>
													<c:if test="${it.status ==6}">
														计费确认
													</c:if>
													<c:if test="${it.status ==7}">
														已完成
													</c:if>
											</td>
											<td><fmt:formatDate value="${it.updateDate}"
														pattern="yyyy-MM-dd HH:mm" /></td>
											<td>${it.userDispatchName}</td>
											<td>${it.branchGroupName}</td>
											<td>${it.cargoName}</td>
											<td>${it.testIndicators}</td>
											<td>${it.sendCompany}</td>
											<td>${it.containerOneSendNet + it.containerTwoSendNet}吨</td>
											<td>${it.receiptCompany}</td>
											<td>${it.containerOneReceiptNet + it.containerTwoReceiptNet}吨</td>
											<td>${it.arriveFreightYrad}</td>
											<td>${it.arriveFreightSite}</td>
											<td>${it.carPlateNumber}</td>
											<td><fmt:formatDate value="${it.exceptionTime}"
														pattern="yyyy-MM-dd HH:mm" /></td>
											<td>${it.exceptionReoportName}</td>
											<td>${it.exceptionReoportReason}</td>
										</tr>
										</c:if>
									</c:forEach>
									</tbody>
								</table>
								<!--异常运单列表表格结束-->
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-md-12 column paging page_div" id="page_2">
							</div>
						</div>

					</div>

					<div class="tab-pane" id="panel3">
						<div class="domain">
							<div class="tableBg">
								<a href="#" class="exportBtn export" onclick="return false;"><span>导出</span></a>
								<a href="#" class="exportBtn msgbtn msg" onclick="return false;"><span>短信告知</span></a>
							</div>
							<div class="table-responsive">
								<!--历史运单开始-->
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th>
												<label class="demo--label">
													  <input class="demo--checkbox" type="checkbox" >
                                                 <span class="demo--checkboxInput"></span>
	                                           </label>
											</th>
											<th>项目编号</th>
											<th>运单编号</th>
											<th>创建时间</th>
											<th>状态更新时间</th>
											<th>到货时间</th>
											<th>调度员</th>
											<th>分支机构</th>
											<th>货物品名</th>
											<th>化验指标</th>
											<th>发货单位</th>
											<th>发货净重</th>
											<th>收货企业</th>
											<th>收货净重</th>
											<th>货场</th>
											<th>货位</th>
											<th>承运车辆</th>
										</tr>
									</thead>
									<tbody id="tbody_3">

									<c:forEach items="${bulkDeleteList.rows}" var="it">
										<c:if test="${it.exceptionStatus ==0 and it.status ==7}">
											<tr>
											<td>
												<label class="demo--label">
											     <input class="demo--checkbox"  type="checkbox"  value="${it.id}">
                                                 <span class="demo--checkboxInput"></span>
	                                          </label>
											</td>
											<td>${it.projectCode}</td>
											<td>
												<a>${it.orderCode}</a>
											</td>
											<td><fmt:formatDate value="${it.createDate}"
														pattern="yyyy-MM-dd HH:mm" /></td>
											<td><fmt:formatDate value="${it.updateDate}"
														pattern="yyyy-MM-dd HH:mm" /></td>
											<td><fmt:formatDate value="${it.updateDate}"
														pattern="yyyy-MM-dd HH:mm" /></td>
											<td>${it.userDispatchName}</td>
											<td>${it.branchGroupName}</td>
											<td>${it.cargoName}</td>
											<td>${it.testIndicators}</td>
											<td>${it.sendCompany}</td>
											<td>${it.containerOneSendNet + it.containerTwoSendNet}吨</td>
											<td>${it.receiptCompany}</td>
											<td>${it.containerOneReceiptNet + it.containerTwoReceiptNet}吨</td>
											<td>${it.arriveFreightYrad}</td>
											<td>${it.arriveFreightSite}</td>
											<td>${it.carPlateNumber}</td>
										</tr>
										</c:if>
									</c:forEach>									</tbody>
								</table>
								<!--历史运单结束-->
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-md-12 column paging page_div" id="page_3">
							</div>
						</div>

					</div>

					<div class="tab-pane" id="panel4">
						<div class="domain">
							<div class="tableBg">
								<a href="#" class="exportBtn rest" onclick="return false;"><span>还原</span></a>
								<a href="#" class="exportBtn export" onclick="return false;"><span>导出</span></a>
								<!-- <a href="#" class="exportBtn del" id="recoveryBtn" onclick="return false;"><span>删除</span></a> -->
								<a href="#" class="exportBtn msgbtn msg" onclick="return false;"><span>短信告知</span></a>

							</div>
							<div class="table-responsive">
								<!-- 项目回收站结束-->
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th>
												<label class="demo--label">
													  <input class="demo--checkbox" type="checkbox" name="recoveryName" >
                                                 <span class="demo--checkboxInput"></span>
	                                           </label>
											</th>
											<th>项目编号</th>
											<th>运单编号</th>
											<th>创建时间</th>
											<th>运单状态</th>
											<th>状态更新时间</th>
											<th>调度员</th>
											<th>分支机构</th>
											<th>货物品名</th>
											<th>化验指标</th>
											<th>发货单位</th>
											<th>发货净重</th>
											<th>收货企业</th>
											<th>收货净重</th>
											<th>货场</th>
											<th>货位</th>
											<th>承运车辆</th>
											<th>删除时间</th>
											<th>删除人</th>
											<th>删除原因</th>
										</tr>
									</thead>
									<tbody id="tbody_4">
										<c:forEach items="${bulkDeleteList.rows}" var="it">
											<tr>
											<td>
												<label class="demo--label">
											     <input class="demo--checkbox"  type="checkbox"  value="${it.id}">
                                                 <span class="demo--checkboxInput"></span>
	                                          </label>
											</td>
											<td>${it.projectCode}</td>
											<td>
												<a>${it.orderCode}</a>
											</td>
											<td><fmt:formatDate value="${it.createDate}"
														pattern="yyyy-MM-dd HH:mm" /></td>
											<td><fmt:formatDate value="${it.updateDate}"
														pattern="yyyy-MM-dd HH:mm" /></td>
											<td><fmt:formatDate value="${it.updateDate}"
														pattern="yyyy-MM-dd HH:mm" /></td>
											<td>${it.userDispatchName}</td>
											<td>${it.branchGroupName}</td>
											<td>${it.cargoName}</td>
											<td>${it.testIndicators}</td>
											<td>${it.sendCompany}</td>
											<td>${it.containerOneSendNet + it.containerTwoSendNet}吨</td>
											<td>${it.receiptCompany}</td>
											<td>${it.containerOneReceiptNet + it.containerTwoReceiptNet}吨</td>
											<td>${it.arriveFreightYrad}</td>
											<td>${it.arriveFreightSite}</td>
											<td>${it.carPlateNumber}</td>
											<td>
											<fmt:formatDate value="${it.deleteTime}"
														pattern="yyyy-MM-dd HH:mm" />
											</td>
											<td>${it.deleteName}</td>
											<td>${it.deleteReason}</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
								<!--  项目回收站结束-->
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-md-12 column paging page_div" id="page_4">
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>

		<!--查看模态框-->
		<div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="lookModal" data-backdrop="static">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">
					  		运单详情
						</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div id="appendDiv" class="modal-body">
					<!-- 	<div class="project_info">
							<h5>项目信息</h5>
							<ul class="detailsUl">
								<li>
									<label>项目编号：</label>
									<span>J00001</span>
								</li>
								<li>
									<label>项目类型：</label>
									<span>集装箱</span>
								</li>
								<li>
									<label>分支机构：</label>
									<span>合肥物流分支</span>
								</li>
								<li>
									<label>调度员：</label>
									<span>李三海</span>
								</li>
							</ul>
						</div>
						<hr />
						<div class="project_info">
							<h5>运单信息</h5>
							<ul class="detailsUl">
								<li><label>运单编号:</label><span>WTE1289887</span></li>
								<li><label>创建时间：</label><span>2017-09-09 10:00</span></li>
								<li class="state"><label>运单状态：</label><span>等待调度</span></li>
								<li><label>状态更新时间：</label><span>2017-09-10 10:00</span></li>
							</ul>
						</div>
						<hr />
						<div class="project_info">
							<h5>货物信息</h5>
							<ul class="detailsUl">
								<li><label>货物品名：</label><span>煤炭</span></li>
								<li><label>化验指标：</label><span>GTV87968989</span></li>
								<li><label>运费：</label><span>2万元</span></li>
								<li><label>补贴：</label><span>2000元</span></li>
							</ul>
						</div>
						<hr />
						<div class="project_info">
							<h5>收发货信息</h5>
							<ul class="msgUl">
								<li><label>发货单位：</label><span>安徽合肥深合软件有限公司</span></li>
								<li><label>取货站：</label><span>合肥火车站</span></li>
								<li><label>收货单位：</label><span>新疆秦龙矿业有限公司</span></li>
								<li><label>运抵地：</label><span>乌鲁木齐火车站</span></li>
								<li><label>运抵货场：</label><span>库里火车站</span></li>
								<li><label>运抵货位：</label><span>货A-12</span></li>
								<li><label>发货皮重：</label><span>2000吨</span></li>
								<li class="pul"><label>发货毛重：</label><span>2000吨</span></li>
								<li class="pul"><label>发货净重：</label><span>2000吨</span></li>
								<li><label>收货皮重：</label><span>2000吨</span></li>
								<li class="pul"><label>收货毛重：</label><span>2000吨</span></li>
								<li class="pul"><label>收货净重：</label><span>2000吨</span></li>
							</ul>
						</div>
						<hr/>
						<div class="project_info">
							<h5>车辆信息</h5>
							<ul class="detailsUl">
								<li><label>承运车辆：</label><span>皖A12345</span></li>
								<li><label>车辆类型：</label><span>货车</span></li>
								<li><label>驾驶员：</label><span>顾长春</span></li>
								<li><label>联系方式：</label><span>17818267657</span></li>
							</ul>
						</div>
						<hr />
						<h4 class="blueH4">订单来源：PC端</h4>
						<hr />
						<div class="project_info localdiv">
							<h5>位置信息</h5>
							<ul class="localUl">
								<li>2017-09-08 10:00</li>
								<li>马鞍山市马鞍山东路</li>
								<li>停留10分钟休息</li>

							</ul>
							<ul class="localUl">
								<li>2017-09-07 10:00</li>
								<li>南京雨花台</li>
								<li>汽车旅馆休息一夜</li>

							</ul>
							<ul class="localUl">
								<li>2017-09-08 10:00</li>
								<li>南京市南站广场与机场东路交叉口</li>
								<li>发生重大交通事故，行程耽误4小时</li>

							</ul>
						</div> -->
					</div>
				</div>
			</div>
		</div>

		<!--运单新增模态框-->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-hidden="true" id="myModal" data-backdrop="static">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<span data-dismiss="modal" aria-label="Close"></span>
						<h4 class="modal-title" class="myModalLabel">
							新建运单
						</h4>
					</div>
					<div class="modal-body">
						<form id="addId">
							<!--	项目信息-->
							<div class="project_info">
								<h5>项目信息</h5>
								<div class="form-inline">
									<div class="form-group">
									<input  name="projectCode" type="hidden" />
										<label>项目编号：</label>
										<select id="select_project"  onchange="exchangeProject()" name="projectId"></select>
									</div>
									<div class="form-group">
										<label>项目类型：</label>
										<input id="projectType" name="projectType" type="hidden"  value='1'/> 
										<input  id="projectTypeName" name="projectTypeName" type="text" unselectable="on" class="inputbg" readonly="readonly" />
									</div>
									<div class="form-group">
										<label>阶段选择：</label>
										<select id="stepSelectCodes" name="stepSelectCode" onchange="stepChange()">
										<!-- 	<option value="1">接取</option>
											<option value="2">送达</option>
											<option value="3">汽运</option> -->
										</select>
									</div>
									<div class="form-group">
										<label>分支机构：</label>
										<input id="branchGroupName" unselectable="on" class="inputbg" readonly="readonly" name="branchGroupName"/>
										<input id="branchGroupId" type="hidden" name="branchGroupId" />
									</div>
								</div>
							</div>
							<hr />
							<!--   收发货信息-->
							<div class="detail_info cut_apart clearfix">
								<div class="half splitters">
									<h5>收发货信息</h5>
									<div class="form-inline">
										<div class="form-group">
											<label>发货单位：</label>
											<input id="sendCompany" unselectable="on" class="inputbg" readonly="readonly" name="sendCompany"/>
											<input id="sendCargoUnitId" type="hidden" name="sendCargoUnitId" />
										</div>
										<div class="form-group">
											<label>&emsp;取货地：</label>
											<input id="" unselectable="on" class="inputbg" readonly="readonly" name="pickupPlace"/>
										</div>
										<div class="form-group">
											<label class="sm-tag">取货地址：</label>
											<input type="text" unselectable="on" readonly="" class="addr_info inputbg" name="pickupPlaceAddress" value="">
										</div>
									</div>
								</div>
								<div class="half">
									<h5>&nbsp;</h5>
									<div class="form-inline">
										<div class="form-group">
											<label>收货站点：</label>
											<input name="receiveCargoSiteId" type="hidden"  value=""/>
											<input unselectable="on" class="inputbg" readonly="readonly" name="receiptCompany"/>
										</div>
										<div class="form-group">
											<label>&emsp;运抵地：</label>
											<input  name="arrivePlace" type="text" value="" unselectable="on" readonly="" class="inputbg" name="arrivePlace">
										</div>
										<div class="form-group">
											<label class="sm-tag">运抵地址：</label>
											<input type="text" unselectable="on" readonly="" class="addr_info inputbg" value="" name="arriveAddress">
										</div>
									</div>
								</div>
							</div>
							<hr />
							<!--货物信息-->
							<div class="project_info">
								<h5>货物信息</h5>
								<div class="form-inline">
									<div class="form-group">
										<label>货物品名：</label>
										<input type="text" unselectable="on" class="inputbg" readonly="readonly" name="cargoName"/>
									</div>
									<div class="form-group">
										<label>货物规格：</label>
										<input type="text" unselectable="on" class="inputbg" readonly="readonly" name="specifications"/>
									</div>
									<div class="form-group">
										<label>计件单位：</label>
										<input type="text" unselectable="on" class="inputbg" readonly="readonly" name="valuationUnitType"/>
									</div>

								</div>
							</div>
							<hr />
							<!--车辆信息-->
							<div class="project_info">
								<h5>车辆信息</h5>
								<div class="form-inline">
									<div class="form-group">
										<label>承运车辆：</label>
										<select id="car_select" onchange="carSelect()"></select>
									</div>
									<div class="form-group">
										<label>车辆类型：</label>
										<input name="carTeamId" type="hidden"/>
										<input name="carPlateNumber" type="hidden"/>
										<input name="driverId" type="hidden"/>
										<input name="carrierVehicleId" type="hidden"/>
										<input name="carrierVehicleName" type="hidden"/>
										<input name="carType" type="text" unselectable="on" class="inputbg" readonly="readonly" />
									</div>
									<div class="form-group">
										<label>&emsp;驾驶员：</label>
										<input name="driverName" type="text" unselectable="on" class="inputbg" readonly="readonly" />
									</div>
									<div class="form-group">
										<label>联系方式：</label>
										<input name="driverPhone" type="text" unselectable="on" class="inputbg" readonly="readonly" />
									</div>
								</div>
								<input type="hidden" id="forwardingSiteId" name="forwardingSiteId">
								<div class="form-inline">
									<div class="form-group">
										<label>&emsp;&emsp;货场：</label>
										<select id="take_carogo_place_name1" onchange="set_selectValue1(1)"></select>
										<input type="hidden" id="take_cargo_place_id1" name="takeCargoPlaceId" >
										<input type="hidden" id="take_carogo_place_name3" name="takeCarogoPlaceName" >
									</div>
									<div class="form-group">
										<label>&emsp;&emsp;货位：</label>
										<select id="take_cargo_site_name1"  onchange="set_selectValue2(1)"></select>
										<input type="hidden" id="take_cargo_site_id1" name="takeCargoSiteId" >
										<input type="hidden" id="take_cargo_site_name3" name="takeCargoSiteName" >
									</div>
								</div>
							</div>
							<hr />
							<!--   费用信息-->
							<div class="project_info">
								<h5>费用信息</h5>
								<div class="form-inline">
									<div class="form-group">
										<label>短驳运费：</label>
										<input type="text" unselectable="on" class="inputbg" readonly="readonly"  name="shortBargeCost"/>
									</div>
									<div class="form-group">
										<label>折损单价：</label>
										<input type="text" unselectable="on" class="inputbg" readonly="readonly"  name="deductionPrice"/>
									</div>
									<div class="form-group">
										<label>&emsp;&emsp;补贴：</label>
										<input type="text" maxlength="30" name="subsidy"/>
									</div>
								</div>
							</div>
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
									<input type="button"  class="btn sureBtn" value="确认" onclick="orderAdds()" />
									<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<!--等待发运-->
		<div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="waitModal" data-backdrop="static">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" class="myModalLabel">
							等待发运
						</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="sendId">
							<div class="project_info">
								<h5>项目信息</h5>
								<ul id="forwardings" class="waitUl">
									<!-- <li><label>申请项目：</label><span>J00001</span></li>
									<li><label>申请人：</label><span>王大锤</span></li>
									<li><label>联系方式：</label><span>18855451245</span></li>
									<li><label>承运车辆：</label><span>皖A123456</span></li>
									<li><label>货品名称：</label><span>煤炭</span></li> -->
								</ul>
							</div>
							<hr />
							<div class="project_info">
								<h5>发运信息</h5>
								<div class="form-inline ">
									<div class="form-group">
										<label>发货皮重：</label>
										<input type="text" maxlength="30" name="sendTare"/>
									</div>
									<div class="form-group">
										<label>发货毛重：</label>
										<input type="text" maxlength="30" name="sendGross"/>
									</div>
									<div class="form-group">
										<label>发货净重：</label>
										<input type="text" maxlength="30" name="containerOneSendNet"/>
									</div>
									<div class="form-group">
										<label>化验指标：</label>
										<input type="text" maxlength="30" name="testIndicators"/>
									</div>
								</div>
							</div>
							<hr />
							<div class="project_info">
								<h5>发运单上传</h5>
								<img  id="clickImgShow" onclick="clickImg(this)" src="${pageContext.request.contextPath}/img/updownBg.png" /> 
								<input type="file" onchange="imgUpload(this)" style="display:none;" >
								<input id="add_up_freightYardImg" type="hidden" name="img"/>
								<div id="img_div" style="display: inline-block;">
									<img id="forward_add_img" width="150px" height="150px"  src="" />
								</div>
								<!-- <ul class="updownUl">
									<li></li>
								</ul> -->
							</div>
							<hr />
							<div class="project_info">
								<h5>取消发运</h5> 是否取消：
								<label class="demo--label">
        					<input class="demo--radio" type="radio" name="demo-radio" value="1">
        					<span class="demo--radioInput"></span>是
    						</label>
								<label class="demo--label">
        					<input class="demo--radio" type="radio" name="demo-radio" checked="checked" value="0">
        					<span class="demo--radioInput"></span>否
    						</label>
							</div>
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
									<input type="button"  class="btn sureBtn" value="确认" onclick="subForwarding()" />
									<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!--等待回单模态框-->
		<div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="receiptModal" data-backdrop="static">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" class="myModalLabel">
							等待回单
						</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="receiptId">
							<div class="project_info">
								<h5>项目信息</h5>
								<ul id="receiptContent" class="waitUl">
									<!-- <li><label>申请项目：</label><span>J00001</span></li>
									<li><label>申请人：</label><span>王大锤</span></li>
									<li><label>联系方式：</label><span>18855451245</span></li>
									<li><label>承运车辆：</label><span>皖A123456</span></li>
									<li><label>货品名称：</label><span>煤炭</span></li>
									<li><label>计件单位：</label><span id="ton">吨</span></li> -->
								</ul>
							</div>
							<hr />
							<div class="project_info">
								<h5>发运信息</h5>
								<div class="form-inline ">
									<div class="form-group">
										<label class="pLeft">收货皮重：</label>
										<input type="text" maxlength="30" name="receiptTare"/>
									</div>
									<div class="form-group">
										<label>收货毛重：</label>
										<input type="text" maxlength="30" name="receiptGross" />
									</div>
									<div class="form-group">
										<label class="pLeft">收货净重：</label>
										<input type="text" maxlength="30"  name="containerOneReceiptNet"/>
									</div>
									<!-- <div class="form-group piece">
										<label>计件单位：</label>
										<input type="text" maxlength="30" />
									</div> -->
								</div>
							</div>
							<hr />
							<div class="project_info">
								<h5>到货单上传</h5>
								<img  id="clickImgShow" onclick="clickImg(this)" src="${pageContext.request.contextPath}/img/updownBg.png" /> 
								<input type="file" onchange="imgUpload(this)" style="display:none;" >
								<input id="add_up_freightYardImg2" type="hidden" name="img"/>
								<div id="img_div2" style="display: inline-block;">
									<img id="forward_add_img2" width="150px" height="150px"  src="" />
								</div>
							<!-- 	<ul class="updownUl">
									<li></li>
								</ul> -->
							</div>
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
									<button type="button" class="btn sureBtn" onclick="receipterFuns()">确定</button>
									<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<!--等待调度模态框-->
		<div class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" aria-hidden="true" id="dispatchModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" class="myModalLabel">
							等待调度
						</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="dispatchId">
							<div class="project_info">
								<h5>项目信息</h5>
								<ul id="dispatchContent" class="waitUl">
									<!-- <li><label>申请项目：</label><span>J00001</span></li>
									<li><label>申请人：</label><span>王大锤</span></li>
									<li><label>联系方式：</label><span>18855451245</span></li>
									<li><label>承运车辆：</label><span>皖A123456</span></li>
									<li><label>货品名称：</label><span>煤炭</span></li> -->
								</ul>
							</div>
							<hr />
							<div class="project_info">
								<h5>承运信息</h5>
								<div class="form-inline ">
									<div class="form-group">
										<label>货场：</label>
										<select id="take_carogo_place_name2"  onchange="set_selectValue1(2)"></select>
										<input type="hidden" id="take_cargo_place_id2" name="takeCargoPlaceId" >
										<input type="hidden" id="take_carogo_place_name4" name="takeCarogoPlaceName" >
									</div>
									<div class="form-group">
										<label>货位：</label>
										<select id="take_cargo_site_name2"  onchange="set_selectValue2(2)"></select>
										<input type="hidden" id="take_cargo_site_id2" name="takeCargoSiteId" >
										<input type="hidden" id="take_cargo_site_name4" name="takeCargoSiteName" >
									</div>
								</div>
							</div>
							<hr />
							<div class="project_info">
								<h5>调度审核</h5>
								<div class="project_info">
									<label>是否同意：</label>
									<label class="demo--label">
        						<input class="demo--radio" type="radio" name="isAgree" checked="checked" id="checkboxagree" value="1">
        						<span class="demo--radioInput"></span>是
    							</label>
									<label class="demo--label">
        						<input class="demo--radio" type="radio" name="isAgree" id="checkboxDisagree" value="0">
        						<span class="demo--radioInput"></span>否
   								</label>
								</div>
								<div class="project_info hideDiv">
									<label>驳回原因： </label>
									<textarea id="rejectTextarea" name="remark"></textarea>
								</div>
							</div>
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
										<input type="button"  class="btn sureBtn" value="确认" onclick="submits()" />
									<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!--货物引导-->
		<div class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" aria-hidden="true" id="guideModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" class="myModalLabel">
						货物引导
						</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="guideId">
							<div class="project_info">
								<h5>项目信息</h5>
								<ul id="guideContentId" class="waitUl">
							<!-- 		<li><label>申请项目：</label><span>J00001</span></li>
									<li><label>申请人：</label><span>王大锤</span></li>
									<li><label>联系方式：</label><span>18855451245</span></li>
									<li><label>承运车辆：</label><span>皖A123456</span></li>
									<li><label>货品名称：</label><span>煤炭</span></li> -->
								</ul>
							</div>
							<hr />
							<div class="project_info">
								<h5>承运信息</h5>
								<ul id="guideContentId2" class="waitUl">
								<!-- 	<li><label>到货皮重：</label><span>2万吨</span></li>
									<li><label>到货毛重：</label><span>2万吨</span></li>
									<li><label>到货净重：</label><span>2万吨</span></li>
 -->
								</ul>
							</div>
							<hr />
							<div class="project_info">
								<h5>发运单上传</h5>
								<ul class="guideUl">
									<li><img id="guide_img" src="" /></li>
								</ul>
							</div>
							<hr />
							<div class="project_info">
								<h5>分配货场货位</h5>
								<div class="form-inline guideForm">
									<div class="form-group">
										<label>货场选择：</label>
										<select id="select_freight_guide" onchange="cargoLoExchange()"></select>
									</div>
									<div class="form-group">
										<label>货位选择：</label>
										<select id="select_cargo_guide"></select>
									</div>
								</div>
								<input id="distributionCargoPlace" name="distributionCargoPlace" type="hidden"/>
								<input id="distributionCargoSite" name="distributionCargoSite" type="hidden"/>
								<input id="distributionCargoPlaceId" name="distributionCargoPlaceId" type="hidden"/>
								<input id="distributionCargoSiteId" name="distributionCargoSiteId" type="hidden"/>
							</div>
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
									<button type="button" class="btn sureBtn" onclick="guideFuns()">确定</button>
									<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
								</div>

							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!--在途运载-->
		<div class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" aria-hidden="true" id="carryModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" class="myModalLabel">
						确认到货
						</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="carryId">
							<div class="project_info">
								<h5>项目信息</h5>
								<ul id="carryProject" class="waitUl">
									<!-- <li><label>申请项目：</label><span>J00001</span></li>
									<li><label>申请人：</label><span>王大锤</span></li>
									<li><label>联系方式：</label><span>18855451245</span></li>
									<li><label>承运车辆：</label><span>皖A123456</span></li>
									<li><label>货品名称：</label><span>煤炭</span></li> -->
								</ul>
							</div>
							<hr />
							<div class="project_info">
								<h5>承运信息</h5>
								<ul id="carryContent" class="waitUl">
									<!-- <li><label>到货皮重：</label><span>2万吨</span></li>
									<li><label>到货毛重：</label><span>2万吨</span></li>
									<li><label>WTB 1234567到货净重：</label><span>2万吨</span></li>
									<li><label>WTB 1234567到货净重：</label><span>2万吨</span></li> -->
								</ul>
							</div>
							<hr />
							<div class="project_info">
								<h5>发运运单</h5>
								<ul class="guideUl">
										<li><img id="carry_img" src="" /></li>
								</ul>
							</div>
							<hr />
							<div class="project_info">
								<h5>确认到货</h5><label> 是否到货：</label>
								<label class="demo--label">
        					<input class="demo--radio" type="radio" name="IsAgree"  checked="checked"  value="1">
        					<span class="demo--radioInput"></span>是
    						</label>
								<label class="demo--label">
        					<input class="demo--radio" type="radio" name="IsAgree" value="0">
        					<span class="demo--radioInput"></span>否
    						</label>
							</div>
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
									<button type="button" class="btn sureBtn" onclick="carrySub()">确定</button>
									<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- 异常弹框 -->
		<div class="modal fade addBilling" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" id="abnormalModal">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">异常处理</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form>
							<h5>异常信息</h5>
							<div class="project_info">
								<ul id="exceptionMsg" class="abnormalUl">
									
								</ul>
							</div>
							<hr/>
							<h5>补加信息</h5>
							<div class="project_info">
								<label class="newColor">须知:</label><span>请确认此异常是否有误，如果有误，请选择驳回，如果无误，请选择确认无误。</span>
							</div>
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
									<button type="button" class="btn sureBtn" onclick="rejectFun()">驳回</button>
									<button type="button" class="btn cancleBtn" data-dismiss="modal" onclick="">确认无误</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<!-- 删除提醒模态框 -->
		<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="deletRemindModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="body-content">
							<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
							<span>抱歉，不可批量删除</span>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<!-- <button type="button" class="btn sureBtn">确定</button> -->
								<button type="button" class="btn sureBtn" data-dismiss="modal">确定</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 未选择删除提醒模态框 -->
		<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="deletnullModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="body-content">
							<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
							<span>请选择一条要删除的数据</span>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" data-dismiss="modal">确定</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--删除原因模态框-->
		<div class="modal fade deletReasonModal" tabindex="-1" role="dialog" id="deletReasonModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">删除原因</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="deletForm">
							<div class="body-content">
								<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
								<span>你是否选择删除此数据，删除后不可还原</span>
							</div>
							<div class="delete_reason">
								<h4>删除原因</h4>
								<textarea id="delete_reason" class="form-control textareas" rows="3"></textarea>
							</div>
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
									<button type="button" class="btn sureBtn" onclick="delsOrder()">确定</button>
									<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- 未选择数据提醒模态框 -->
		<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="nullModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="body-content">
							<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
							<span id="warnMsg">请选择一条要操作的数据</span>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" data-dismiss="modal">确定</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 导出模态框 -->
		<div class="modal fade exportModal" tabindex="-1" role="dialog" id="exportModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="body-content">
							<i class="fa fa-question-circle" aria-hidden="true"></i>
							<span>选择了<b id="exportNums">13</b>条记录，确认导出全部数据？</span>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
		<!-- 提醒模态框 -->
		<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="deltialModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="body-content">
							<div><span>您已选择</span><b class="errnums"></b> <span id="msgSpan"></span></div>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="resert()">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		
			<div class="modal fade promptModal" tabindex="-1" role="dialog"
		id="promptIdModal">
		<div class="modal-dialog " role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">提示</h4>
					<span data-dismiss="modal" aria-label="Close"></span>
				</div>
				<div class="modal-body">
					<p id="msg"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn sureBtn" id="sure_msg">
						确定</button>
				</div>
			</div>
		</div>
	</div>
	</body>

<!--运单列表分页-->
<script type="text/javascript">
$("#page_1").paging({
	pageNo:1, 
	totalPage: ${bulkOrderList.totalPage},
	totalSize:${bulkOrderList.limit},
	callback: function(num) {
			$.ajax({  
        	type:'post',      
       		url:'${pageContext.request.contextPath}/business/short/bulkPacking/byPage/list.do',  
	        data:{page:num,type:1,order: JSON.stringify($('#orderSearchForm').serializeJSON())},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
        		var boxOrderList = data.data;
        		$('#tbody_1').html("");
        		$.each(boxOrderList,function(index,it){
        			if(it.exceptionStatus ==0 && it.status !=7){
        				var sta = "";
        				if(it.status ==1){
        					sta = "<td><a href='javascript:void(0)' id='dispatch' onclick='dispatcher(it.id)'>等待调度</a></td>";
        				}
        				if(it.status ==2){
        					sta = "<td><a href='javascript:void(0)' id='forwarding' onclick='forwardinger(it.id)'>等待发运</a></td>";
        				}
        				if(it.status ==3){
        					sta = "<td><a href='javascript:void(0)'  id='carry' onclick='carryer(it.id)'>在途运载</a></td>";
        				}
        				if(it.status ==4){
        					sta = "<td><a href='javascript:void(0)' id='guide' onclick='guideContent(it.id)'>货物引导</a></td>";
        				}
        				if(it.status ==5){
        					sta = "<td><a href='javascript:void(0)' id='waitReceipt' onclick='waitReceipter(it.id)'>等待回单</a></td>";
        				}
        				if(it.status ==6){
        					sta = "<td>计费确认</td>";
        				}	
        				if(it.status ==7){
        					sta = "已完成";
        				}
        				$('#tbody_1').append("<tr id='"+it.id+"'><td><label class='demo--label'>"
								 +" <input class='demo--checkbox' name='WaybillName' type='checkbox' value='"+it.id+"'>"
                          		 +" <span class='demo--checkboxInput'></span></label></td>"
						+"<td>"+it.projectCode+"</td>"
						+"<td> <a href='#'>"+it.orderCode+"</a></td>"	
						+"<td>"+it.createDate+"</td>"	
						+sta
						+"<td>"+it.updateDate+"</td>"
						+"<td>"+it.userDispatchName+"</td>"	
						+"<td>"+it.branchGroupName+"</td>"	
						+"<td>"+it.cargoName +"</td>"	
						+"<td>"+it.testIndicators+"</td>"	
						+"<td>"+it.sendCompany+"</td>"	
						+"<td>"+it.containerOneSendNet+"吨</td>"	
						+"<td>"+it.receiptCompany+"</td>"	
						+"<td>"+it.containerOneReceiptNet+"吨</td>"	
						+"<td>"+it.arriveFreightYrad+"</td>"	
						+"<td>"+it.arriveFreightSite+"</td>"
						+"<td>"+it.carPlateNumber+"</td>"	
						+"<td>"+it.containerNumber1 +"</td>"	
						+"<td>"+it.containerNumber2+"</td></tr>");
        			}
        		})	
        	}  
    	}
	})
}		
})

</script>

<!--异常运单列表分页-->
<script type="text/javascript">
$("#page_2").paging({
	pageNo:1, 
	totalPage: ${bulkOrderList.totalPage},
	totalSize:${bulkOrderList.limit},
	callback: function(num) {
			$.ajax({  
        	type:'post',      
       		url:'${pageContext.request.contextPath}/business/short/bulkPacking/byPage/list.do',  
	        data:{page:num,type:2,order: JSON.stringify($('#orderSearchForm').serializeJSON())},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
        		var boxOrderList = data.data;
        		$('#tbody_2').html("");
        		$.each(boxOrderList,function(index,it){
        				var sta = "";
        				if(it.status ==1){
        					sta = "<td><a href='javascript:void(0)' id='dispatch' onclick='dispatcher(it.id)'>等待调度</a></td>";
        				}
        				if(it.status ==2){
        					sta = "<td><a href='javascript:void(0)' id='forwarding' onclick='forwardinger(it.id)'>等待发运</a></td>";
        				}
        				if(it.status ==3){
        					sta = "<td><a href='javascript:void(0)'  id='carry' onclick='carryer(it.id)'>在途运载</a></td>";
        				}
        				if(it.status ==4){
        					sta = "<td><a href='javascript:void(0)' id='guide' onclick='guideContent(it.id)'>货物引导</a></td>";
        				}
        				if(it.status ==5){
        					sta = "<td><a href='javascript:void(0)' id='waitReceipt' onclick='waitReceipter(it.id)'>等待回单</a></td>";
        				}
        				if(it.status ==6){
        					sta = "<td>计费确认</td>";
        				}	
        				if(it.status ==7){
        					sta = "已完成";
        				}
        				$('#tbody_2').append("<tr><td><label class='demo--label'>"
								 +" <input class='demo--checkbox' name='WaybillName' type='checkbox' value='"+it.id+"'>"
                          		 +" <span class='demo--checkboxInput'></span></label></td>"
                   		+"<td>"+it.projectCode+"</td>"
 						+"<td> <a href='#'>"+it.orderCode+"</a></td>"	
 						+"<td>"+it.createDate+"</td>"
 						+sta
						+"<td>"+it.updateDate+"</td>"
						+"<td>"+it.userDispatchName+"</td>"	
						+"<td>"+it.branchGroupName+"</td>"	
						+"<td>"+it.cargoName +"</td>"	
						+"<td>"+it.testIndicators+"</td>"	
						+"<td>"+it.sendCompany+"</td>"	
						+"<td>"+it.containerOneSendNet+"吨</td>"	
						+"<td>"+it.receiptCompany+"</td>"	
						+"<td>"+it.containerOneReceiptNet +"吨</td>"	
						+"<td>"+it.arriveFreightYrad+"</td>"	
						+"<td>"+it.arriveFreightSite+"</td>"
						+"<td>"+it.carPlateNumber+"</td>"	
						+"<td>"+it.containerNumber1 +"</td>"	
						+"<td>"+it.containerNumber2+"</td>"
						+"<td>"+it.exceptionTime+"</td>"
						+"<td>"+it.exceptionReoportName+"</td>"
						+"<td>"+it.exceptionReoportReason+"</td></tr>");
        		})	
        	}  
    	}
	})
}		
})

</script>

<!--历史运单分页-->
<script type="text/javascript">
$("#page_3").paging({
	pageNo:1, 
	totalPage: ${bulkOrderList.totalPage},
	totalSize:${bulkOrderList.limit},
	callback: function(num) {
			$.ajax({  
        	type:'post',      
       		url:'${pageContext.request.contextPath}/business/short/bulkPacking/byPage/list.do',  
	        data:{page:num,type:3,order: JSON.stringify($('#orderSearchForm').serializeJSON())},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
        		var boxOrderList = data.data;
        		$('#tbody_3').html("");
        		$.each(boxOrderList,function(index,it){
        				var sta = "";
        				if(it.status ==1){
        					sta = "<td><a href='javascript:void(0)' id='dispatch' onclick='dispatcher(it.id)'>等待调度</a></td>";
        				}
        				if(it.status ==2){
        					sta = "<td><a href='javascript:void(0)' id='forwarding' onclick='forwardinger(it.id)'>等待发运</a></td>";
        				}
        				if(it.status ==3){
        					sta = "<td><a href='javascript:void(0)'  id='carry' onclick='carryer(it.id)'>在途运载</a></td>";
        				}
        				if(it.status ==4){
        					sta = "<td><a href='javascript:void(0)' id='guide' onclick='guideContent(it.id)'>货物引导</a></td>";
        				}
        				if(it.status ==5){
        					sta = "<td><a href='javascript:void(0)' id='waitReceipt' onclick='waitReceipter(it.id)'>等待回单</a></td>";
        				}
        				if(it.status ==6){
        					sta = "<td>计费确认</td>";
        				}	
        				if(it.status ==7){
        					sta = "已完成";
        				}
        				$('#tbody_3').append("<tr><td><label class='demo--label'>"
								 +" <input class='demo--checkbox' name='WaybillName' type='checkbox' value='"+it.id+"'>"
                         		 +" <span class='demo--checkboxInput'></span></label></td>"
                  		+"<td>"+it.projectCode+"</td>"
						+"<td> <a href='#'>"+it.orderCode+"</a></td>"	
						+"<td>"+it.createDate+"</td>"	
						+sta
						+"<td>"+it.updateDate+"</td>"
						+"<td>"+it.userDispatchName+"</td>"	
						+"<td>"+it.branchGroupName+"</td>"	
						+"<td>"+it.cargoName +"</td>"	
						+"<td>"+it.testIndicators+"</td>"	
						+"<td>"+it.sendCompany+"</td>"	
						+"<td>"+it.containerOneSendNet +"吨</td>"	
						+"<td>"+it.receiptCompany+"</td>"	
						+"<td>"+it.containerOneReceiptNet +"吨</td>"	
						+"<td>"+it.arriveFreightYrad+"</td>"	
						+"<td>"+it.arriveFreightSite+"</td>"
						+"<td>"+it.carPlateNumber+"</td>"	
						+"<td>"+it.containerNumber1 +"</td>"	
						+"<td>"+it.containerNumber2+"</td>");
        		})	
        	}  
    	}
	})
}		
})
</script>

<!--运单回收站分页-->
<script type="text/javascript">
$("#page_4").paging({
	pageNo:1, 
	totalPage: ${bulkOrderList.totalPage},
	totalSize:${bulkOrderList.limit},
	callback: function(num) {
			$.ajax({
        	type:'post',      
       		url:'${pageContext.request.contextPath}/business/short/bulkPacking/delete/byPage/list.do',  
	        data:{page:num,order:JSON.stringify($('#orderSearchForm').serializeJSON())},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
        		var boxOrderList = data.data;
        		$('#tbody_4').html("");
        		$.each(boxOrderList,function(index,it){
	       				$('#tbody_4').append("<tr><td><label class='demo--label'>"
								 +" <input class='demo--checkbox'  type='checkbox' value='"+it.id+"'>"
	                        		 +" <span class='demo--checkboxInput'></span></label></td>"
	                  		+"<td>"+it.projectCode+"</td>"
	 						+"<td> <a href='#'>"+it.orderCode+"</a></td>"	
	 						+"<td>"+it.createDate+"</td>"	
						+"<td>"+it.updateDate+"</td>"
						+"<td>"+it.updateDate+"</td>"
						+"<td>"+it.userDispatchName+"</td>"	
						+"<td>"+it.branchGroupName+"</td>"	
						+"<td>"+it.cargoName +"</td>"	
						+"<td>"+it.testIndicators+"</td>"	
						+"<td>"+it.sendCompany+"</td>"	
						+"<td>"+it.containerOneSendNet +"吨</td>"	
						+"<td>"+it.receiptCompany+"</td>"	
						+"<td>"+it.containerOneReceiptNet +"吨</td>"	
						+"<td>"+it.arriveFreightYrad+"</td>"	
						+"<td>"+it.arriveFreightSite+"</td>"
						+"<td>"+it.carPlateNumber+"</td>"
						+"<td>"+it.containerNumber1+"</td>"
						+"<td>"+it.containerNumber2+"</td>"
						+"<td>"+it.deleteTime+"</td>"	
						+"<td>"+it.deleteName +"</td>"	
						+"<td>"+it.deleteReason+"</td>");
        			
        		})	
        	}  
    	}
	})
}		
})
</script>

</html>