<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>集装箱管理</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/public.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/business/shortBarge/truck_container.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/business/shortBarge/truck_container.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/util.js"></script>
		<link href="${pageContext.request.contextPath}/static/lib/select2/css/select2.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/static/lib/select2/css/select2-bootstrap.css" rel="stylesheet"/>
		<script src="${pageContext.request.contextPath}/static/lib/common/select.js"></script>
		<script src="${pageContext.request.contextPath}/static/lib/select2/js/select2.full.js"></script>
		<script src="${pageContext.request.contextPath}/static/lib/select2/js/i18n/zh-CN.js"></script>
		<script src="${pageContext.request.contextPath}/js/baseData.js"></script>
		<script src="${pageContext.request.contextPath}/static/js/common.js"></script>
		<script src="${pageContext.request.contextPath}/static/lib/jquery/fd/js/jquery.fdselect2.js"></script>
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
						$.fd.ajax({
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
				//$("#select_project").change();	
				$("#car_select").empty();
				$("#take_carogo_place_name1").empty();
				$("#take_cargo_site_name1").empty();
				$("#addId [name=set_containerNumber1]").empty();
				$("#addId [name=set_containerNumber2]").empty();
			//	$("#car_select").change();	
				
			});
			
			
			 //车辆下拉
			/*  $.fd.ajax({  
	                type: "GET",  
	                url:"${pageContext.request.contextPath}/business/short/boxManager/carTeam/select.do",  
	                success: function(data) {  
	                	$("#car_select").empty();
	    				$.each(data.data, function(index, its) {
	    							$("#car_select").append(
			    							"<option value="+its.driverId+">" + its.plateNumber
			    									+ "</option>");
	    				});
	                }
	            }); */
			
			//隐藏等待发运图片位置
			$("#forward_add_img").hide();
			//确认操作后页面刷新
			$("#sure_msg").click(function() {
				window.location.reload();
				$("#promptIdModal").modal("hide");
			});
			$("#sure_msgNof").click(function() {
				$("#promptIdModalNof").modal("hide");
			});
			
			//等待调度模块集装箱号下拉
			 /* $.fd.ajax({  
	                type: "GET",  
	                url:"${pageContext.request.contextPath}/business/short/boxManager/boxNumber/getMsg.do",  
	                success: function(data) {  
	                	$("#selectType1").empty();
	                	$("#selectType2").empty();
	    				$.each(data.data, function(index, its) {
	    					if(index %2==0){
	    						$("#selectType1").append(
		    							"<option value="+its.id+">" + its.containerId
		    									+ "</option>");
	    					}else{
	    						$("#selectType2").append(
		    							"<option value="+its.id+">" + its.containerId
		    									+ "</option>");
	    					}
	    				});
	    				var  cont1 =$("#selectType1 option:selected").text();
	    				$("#selectVal1").val(cont1);
	    				var  cont2 = $("#selectType2 option:selected").text();
	    				$("#selectVal2").val(cont2);
	                }
	            });  */
			
			
			 //新建中的  --集装箱号下拉
			/*  $.fd.ajax({  
	                type: "GET",  
	                url:"${pageContext.request.contextPath}/business/short/boxManager/boxNumber/getMsg.do",  
	                success: function(data) {  
	                	$("#set_container_one").empty();
	                	$("#set_container_two").empty();
	    				$.each(data.data, function(index, its) {
	    					if(index %2==0){
	    						$("#set_container_one").append(
		    							"<option value="+its.id+">" + its.containerId
		    									+ "</option>");
	    					}else{
	    						$("#set_container_two").append(
		    							"<option value="+its.id+">" + its.containerId
		    									+ "</option>");
	    					}
	    				});
	    				var  cont1 =$("#set_container_one option:selected").text();
	    				$("#set_selectVal1").val(cont1);
	    				var  cont2 = $("#set_container_two option:selected").text();
	    				$("#set_selectVal2").val(cont2);
	                }
	            });   */
			
			//项目下拉
			 $.fd.ajax({  
	                type: "GET",  
	                url:"${pageContext.request.contextPath}/business/short/boxManager/project/getAll.do?projectType="+0,  
	                success: function(data) {  
	                	$("#select_project").empty();
	                	$("#select_project").html("<option></option>");
	    				$.each(data.data, function(index, its) {
	    					$("#select_project").append(
	    							"<option value="+its.id+">" + its.projectCode
	    									+ "</option>");
	    				});
	                
	                }
	            });
				
			
			
			
			 
			//货位引导中的货场下拉
			/*  $.fd.ajax({  
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
	            });   */
			
		
			 
			
			//详情
			$("body").on("click", ".needLookDetail tr td:not(:nth-child(1)):not(:nth-child(5))", function() {
						$(this).parent().toggleClass("bgclass").siblings("tbody tr").removeClass("bgclass");
						   var projectId = $(this).parent().attr("id");
							$.fd.ajax({
								type : "GET",
								url : "${pageContext.request.contextPath}/business/short/boxManager/get.do?id="+projectId,
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
					        					if(data.data.receipterDate == ""){
					        						sta = "等待回单";
					       						}else{
					       							sta = "等待确认"; 
					       						}
					        				}
					        				if(data.data.status ==6){
					        					sta = "计费确认";
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
									
									$.fd.ajax({
										url : "${pageContext.request.contextPath}/bussinessCount/map/order/position",
										type : "get",
										data : {orderId: data.data.id},
										// showMsg:true,
										success : function(d) {
											$("#lookModal").modal();
											$('#showMask', window.parent.document).show();
											$("#appendDiv").empty();
											var htmlbulkInfo="<div class='project_info'><h5>项目信息 </h5> <ul class='detailsUl'>"
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
												+"<li><label>提货货场：</label><span>"+data.data.takeCarogoPlaceName+"</span></li>"		
												+"<li><label>提货货位：</label><span>"+data.data.takeCargoSiteName+"</span></li>"
												+"<li><label>运抵货场：</label><span>"+data.data.distributionCargoPlace+"</span></li>"		
												+"<li><label>运抵货位：</label><span>"+data.data.distributionCargoSite+"</span></li>"		
												+"<li><label>发货皮重：</label><span>"+data.data.sendTare+"吨</span></li>"		
												+"<li><label>发货毛重：</label><span>"+data.data.sendGross+"吨</span></li>"		
												+"<li><label>发货净重：</label><span>"+(data.data.containerOneSendNet+data.data.containerTwoSendNet)+"吨</span></li>"		
												+"<li><label>发货运单：</label><span><a name='"+data.data.orderImg+"' onclick='lookSendImg(this)'>"+data.data.orderImg+"</a></span></li>"
												+"<li><label>收货皮重：</label><span>"+data.data.receiptTare+"吨</span></li>"		
												+"<li><label>收货毛重：</label><span>"+data.data.receiptGross+"吨</span></li>"		
												+"<li><label>收货净重：</label><span>"+(data.data.containerOneReceiptNet+data.data.containerTwoReceiptNet)+"吨</span></li>"
												+"<li><label>收货运单：</label><span><a name='"+data.data.arriveredImg+"' onclick='lookSendImg(this)'>"+data.data.arriveredImg+"</a></span></li>"
												+"</ul></div>"		
												
												+"<hr/>"
												+"<div class='project_info'><h5>车辆信息</h5><ul class='detailsUl'>"
												+"<li><label>承运车辆：</label><span>"+data.data.carPlateNumber+"</span></li>"
												+"<li><label>车辆类型：</label><span>货车</span></li>"
												+"<li><label>驾驶员：</label><span>"+data.data.driverName+"</span></li>"
												+"<li><label>联系方式：</label><span>"+data.data.driverPhone+"</span></li>"		
												+"<li><label>集装箱号：</label><span>"+data.data.containerNumber1+"</span></li>"		
												+"		<li><label>净重：</label><span>"+data.data.containerOneSendNet+"吨</span></li>"
												+"		<li><label>集装箱号：</label><span>"+data.data.containerNumber2+"</span></li>"
												+"		<li><label>净重：</label><span>"+data.data.containerTwoSendNet+"吨</span></li></ul></div>"
												+"<hr />"
												+"<h4 class='blueH4'>订单来源："+orgin+"</h4>"
												+"<hr />"
												+"<div class='project_info localdiv'><h5>位置信息</h5>"
												+"<div class='project_info localdiv' style='height:55px; overflow:auto'>";
												for(var i=0;d.data&&i<d.data.length;i++){
													htmlbulkInfo=htmlbulkInfo+"<ul class='localUl'><li>"+d.data[i].createDate+"</li><li>"+d.data[i].position+"</li><li>"+d.data[i].remark+"</li></ul>"
												}
												htmlbulkInfo=htmlbulkInfo+"</div></div>";
											$("#appendDiv").append(htmlbulkInfo);
										}
									});
										
										
									}
								})
							});
			});
		
		function lookSendImg(param){
			$("#lookImgs").modal();
   		 	$("#look_imgs").attr('src',"/upload/photo/"+param.name);
		}
		
		//异常运单驳回
		function rejectFun(){
			var id = $("input[type=checkbox]:checked").val();
			$.fd.ajax({  
	                type: "GET",  
	                url:"${pageContext.request.contextPath}/business/short/boxManager/exception/reject.do?id="+id,  
	                success: function(data) {  
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
		    $("#dispatchContent").empty();
		}
		
		//等待调度点击获取详情
		function dispatcher(id){
				$("#dispatchId")[0].reset();
				$('#dispatchModal').modal();
				$('#showMask', window.parent.document).show();
				$("#dispatchId [name=dispatch_containerNumber1]").find("option:selected").val("");
				$("#dispatchId [name=dispatch_containerNumber2]").find("option:selected").val("");
				$("#take_cargo_site_name2").empty();
				$("#take_cargo_place_id2").val("");
				$("#take_carogo_place_name4").val("");
				$("#take_cargo_site_id2").val("");
				$("#take_cargo_site_name4").val("");
				 $.fd.ajax({  
	   	                type: "GET",  
	   	                url:"${pageContext.request.contextPath}/business/short/boxManager/dispatch/getInfo.do?id="+id, 
	   	             	async:false,
	   	                success: function(data) { 
	   	                	$("#dispatchContent").empty();
	   	                	$("#dispatchContent").append("<input type='hidden' value='"+data.data.tbOrder.id+"' name='orderId'>"
	   	                			+"<li><label>申请项目：</label><span>"+data.data.tbOrder.projectCode+"</span></li>"
	   								+"<li><label>申请人：</label><span>"+data.data.tbOrder.driverName+"</span></li>"
	   								+"<li><label>联系方式：</label><span>"+data.data.tbOrder.driverPhone+"</span></li>"
	   								+"<li><label>承运车辆：</label><span>"+data.data.tbOrder.carPlateNumber+"</span></li>"
	   								+"<li><label>货品名称：</label><span>"+data.data.tbOrder.cargoName+"</span></li>");
	   	                	$("#dispatchHiddenProject").val(data.data.tbOrder.projectId);
	   	                	$("#dispatchHiddenStepSelect").val(data.data.tbOrder.stepSelectCode);
	   	                	$("#dispatchHiddenSendId").val(data.data.tbOrder.sendCompanyId);
	   	                	$("#dispatchHiddenReceiveId").val(data.data.tbOrder.receiptCompanyId);
	   	                	$("#take_carogo_place_name2").empty();
	   	                	$("#take_carogo_place_name2").append("<option value=''></option>");
	   	                	$.each(data.data.list, function(index, its) {
								$("#take_carogo_place_name2").append(
		    							"<option value="+its.id+">" + its.name + "</option>");
    						});
							//$("#take_carogo_place_name2").change();
	   	                	$().getDisptachRes();
	   	                }
	   	            }); 
				 
		}
		
		//等待发运点击获取详情
		function forwardinger(id){
			$("#forward_add_img").attr('src',"");
			$("#img_div").hide();
			$("#add_up_freightYardImg").val("");
			$("#sendId")[0].reset();
			$('#waitModal').modal();
			$('#showMask', window.parent.document).show();
			 $.fd.ajax({  
	                type: "GET",  
	                url:"${pageContext.request.contextPath}/business/short/boxManager/dispatch/getMsg.do?id="+id,  
	                success: function(data) {  
	                	
	                	$("#firContainer").html(data.data.containerNumber1);
	                	$("#secContainer").html(data.data.containerNumber2);
	                	$("#forwardings").append("<input type='hidden' value='"+data.data.id+"' name='orderId'>"
	                			+"<li><label>申请项目：</label><span>"+data.data.projectCode+"</span></li>"
								+"<li><label>申请人：</label><span>"+data.data.driverName+"</span></li>"
								+"<li><label>联系方式：</label><span>"+data.data.driverPhone+"</span></li>"
								+"<li><label>承运车辆：</label><span>"+data.data.carPlateNumber+"</span></li>"
								+"<li><label>货品名称：</label><span>"+data.data.cargoName+"</span></li>");
	                	
	                	//根据计价单位是都是件  显示件数文本框 
	                	/* if(data.data.valuationUnitType == 0){
	                		$("#jianshuByUnit").show();
	                	}else{
	                		$("#jianshuByUnit").hide();
	                	} */
	                	
	                	if(data.data.containerNumber1 == ""){
	                		$("#containerOneSendNet").val("0");
	                		$("#containerOneSendNet").attr("readOnly",true);
	                	}else{
	                		$("#containerOneSendNet").val("");
	                		$("#containerOneSendNet").attr("readOnly",false);
	                	}
	                	if(data.data.containerNumber2 == ""){
	                		$("#containerTwoSendNet").val("0");
	                		$("#containerTwoSendNet").attr("readOnly",true);
	                	}else{
	                		$("#containerTwoSendNet").val("");
	                		$("#containerTwoSendNet").attr("readOnly",false);
	                	}
	                }
	            });
			 
			 $("#forwardings").empty();
		}
		
		
		//在途运载获取详情
		function carryer(id){
			$("#carry_img").attr('src','');
			$("#carryId")[0].reset();
			$('#carryModal').modal();
			$('#showMask', window.parent.document).show();
			 $.fd.ajax({  
	                type: "GET",  
	                url:"${pageContext.request.contextPath}/business/short/boxManager/dispatch/getMsg.do?id="+id,  
	                success: function(data) {  
	                	$("#carryProject").append("<input type='hidden' value='"+data.data.id+"' name='orderId'>"
	                			+"<li><label>申请项目：</label><span>"+data.data.projectCode+"</span></li>"
								+"<li><label>申请人：</label><span>"+data.data.driverName+"</span></li>"
								+"<li><label>联系方式：</label><span>"+data.data.driverPhone+"</span></li>"
								+"<li><label>承运车辆：</label><span>"+data.data.carPlateNumber+"</span></li>"
								+"<li><label>货品名称：</label><span>"+data.data.cargoName+"</span></li>");
	                	
	                	$("#carryContent").append("<li><label>发货毛重：</label><span>"+data.data.sendGross+"</span></li>"
								+"<li><label>发货皮重：</label><span>"+data.data.sendTare+"</span></li>"
								+"<li><label>"+data.data.containerNumber1+"发货净重：</label><span>"+data.data.containerOneSendNet+"</span></li>"
								+"<li><label>"+data.data.containerNumber2+"发货净重：</label><span>"+data.data.containerTwoSendNet+"</span></li>");
	                	 //var url = "${pageContext.request.contextPath}/upload/photo/"+data.data.orderImg;
	                	 var url = "/upload/photo/"+data.data.orderImg;
	                	$("#carry_img").attr('src',url);
	                }
	            });
			 $("#carryProject").empty();
			 $("#carryContent").empty();
		}
		
		
	
		//货位引导获取详情
		function guideContent(id){
			$("#guideId")[0].reset();
			$('#guideModal').modal();
			$('#showMask', window.parent.document).show();
			 $.fd.ajax({  
	                type: "GET",  
	                url:"${pageContext.request.contextPath}/business/short/boxManager/dispatch/guideGetInfo.do?id="+id,  
	                success: function(data) { 
	                	$("#guideContentId").empty();
	       			 	$("#guideContentId2").empty();
	                	$("#guideContentId").append("<input type='hidden' value='"+data.data.tbOrder.id+"' name='orderId'>"
	                			+"<li><label>申请项目：</label><span>"+data.data.tbOrder.projectCode+"</span></li>"
								+"<li><label>申请人：</label><span>"+data.data.tbOrder.driverName+"</span></li>"
								+"<li><label>联系方式：</label><span>"+data.data.tbOrder.driverPhone+"</span></li>"
								+"<li><label>承运车辆：</label><span>"+data.data.tbOrder.carPlateNumber+"</span></li>"
								+"<li><label>货品名称：</label><span>"+data.data.tbOrder.cargoName+"</span></li>");
	                	
	                	$("#guideContentId2").append("<li><label>发货毛重：</label><span>"+data.data.tbOrder.sendGross+"</span></li>"
								+"<li><label>发货皮重：</label><span>"+data.data.tbOrder.sendTare+"</span></li>"
								+"<li><label>"+data.data.tbOrder.containerNumber1+"发货净重：</label><span>"+data.data.tbOrder.containerOneSendNet+"</span></li>"
								+"<li><label>"+data.data.tbOrder.containerNumber2+"发货净重：</label><span>"+data.data.tbOrder.containerTwoSendNet+"</span></li>");
	                	 //var url = "${pageContext.request.contextPath}/upload/photo/"+data.data.tbOrder.orderImg;
	                	 var url = "/upload/photo/"+data.data.tbOrder.orderImg;
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
			$("#hiddenDivByStstus").show();
			$("#imgType").val("");
			$("#add_up_freightYardImg2").val("");
			$("#forward_add_img2").attr('src',"");
			$("#img_div2").hide();
			$("#receiptId")[0].reset();
			$('#receiptModal').modal();
			$('#showMask', window.parent.document).show();
			$.fd.ajax({  
                type: "GET",  
                url:"${pageContext.request.contextPath}/business/short/boxManager/get.do?id="+id,  
                success: function(data) { 
                	$("#receiptContent").empty();
                	$("#receiptContent").append("<input type='hidden' value='"+data.data.id+"' name='orderId'>"
                			+"<li><label>申请项目：</label><span>"+data.data.projectCode+"</span></li>"
							+"<li><label>申请人：</label><span>"+data.data.driverName+"</span></li>"
							+"<li><label>联系方式：</label><span>"+data.data.driverPhone+"</span></li>"
							+"<li><label>承运车辆：</label><span>"+data.data.carPlateNumber+"</span></li>"
							+"<li><label>货品名称：</label><span>"+data.data.cargoName+"</span></li>");
                	$("#receipt_1").empty();
                	$("#receipt_2").empty();
                	$("#receipt_1").html(data.data.containerNumber1);
                	$("#receipt_2").html(data.data.containerNumber2);
                	
                	$("#receiveBodyModal input[name='receiptTare']").val("");
                	$("#receiveBodyModal input[name='receiptGross']").val("");
                	$("#receiveBodyModal input[name='containerOneReceiptNet']").val("");
                	$("#receiveBodyModal input[name='containerTwoReceiptNet']").val("");
                	
                	if(data.data.containerNumber1 == ""){
                		$("#receiveBodyModal input[name='containerOneReceiptNet']").val("0");
                		$("#receiveBodyModal input[name='containerOneReceiptNet']").attr("readOnly",true);
                	}else{
                		$("#receiveBodyModal input[name='containerOneReceiptNet']").attr("readOnly",false);
                	}
                	if(data.data.containerNumber2 == ""){
                		$("#receiveBodyModal input[name='containerTwoReceiptNet']").val("0");
                		$("#receiveBodyModal input[name='containerTwoReceiptNet']").attr("readOnly",true);
                	}else{
                		$("#receiveBodyModal input[name='containerTwoReceiptNet']").attr("readOnly",false);
                	}
                	
                	if(data.data.arriveredImg == "" || data.data.arriveredImg == null){
                		$("#img_div_arrive").show();
                		$("#forward_add_img2").attr('src',"");
                	}else{
                		$("#receiveBodyModal input[name='receiptTare']").val(data.data.receiptTare);
                    	$("#receiveBodyModal input[name='receiptGross']").val(data.data.receiptGross);
                    	$("#receiveBodyModal input[name='containerOneReceiptNet']").val(data.data.containerOneReceiptNet);
                    	$("#receiveBodyModal input[name='containerTwoReceiptNet']").val(data.data.containerTwoReceiptNet);
                    	
                		$("#img_div2").show();
                		$("#img_div_arrive").hide();
                		var url = "/upload/photo/"+data.data.arriveredImg;
	                	$("#forward_add_img2").attr('src',url);
	                	$("#add_up_freightYardImg2").val(data.data.arriveredImg);
	                	$("#imgType").val(0);
	                	if(data.data.receipterDate == null || data.data.receipterDate == ""){
	                		//$("#setGrayButton").attr("disabled",false);
	                		$("#hiddenDivByStstus").show();
	                	}else{
	                		//$("#setGrayButton").attr("disabled",true);
	                		$("#hiddenDivByStstus").hide();
	                	}
                	}
                }
            });
		}
		
		
		//货位引导 货场 下拉 
		function cargoLoExchange(){
			 var id = $("#select_freight_guide option:selected").val();
			 var  freight = $("#select_freight_guide option:selected").html();
			 $("#distributionCargoPlace").val(freight);
			 $("#distributionCargoPlaceId").val(id);
			 $.fd.ajax({  
	             type: "GET",  
	             url:"${pageContext.request.contextPath}/siteManager/cargo/location/getAll.do?id="+id,  
	             success: function(data) {  
	            		$("#select_cargo_guide").empty();
	 				$.each(data.data, function(index, its) {
	 							$("#select_cargo_guide").append(
			    							"<option value="+its.id+">" +its.code+ "  "+ its.name
			    									+ "</option>");
	 				});
	 				var  yardId = $("#select_cargo_guide option:selected").val();
	 				var  yardName = $("#select_cargo_guide option:selected").html();
	 				$("#distributionCargoSite").val(yardName);
	 				$("#distributionCargoSiteId").val(yardId);
	             }
	         });  
		} 
		
		//货位引导 货位 下拉 
		function cargoGuidExchange(){
			var  guidYardId = $("#select_cargo_guide option:selected").val();
			var  guidYardName = $("#select_cargo_guide option:selected").html();
			$("#distributionCargoSite").val(guidYardName);
			$("#distributionCargoSiteId").val(guidYardId);
		} 
	/* 	//货位引导中的货场下拉
		function guideGetAll(){
			 $.fd.ajax({  
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
	           });
			$("#select_cargo_guide").change();
		} */
		
		
		
		
		//切换效果获取集装箱号
		/* function selectValue1(){
			var  cont =$("#selectType1 option:selected").val();
			$("#selectVal1").val(cont);
		}
		function selectValue2(){
			var  cont = $("#selectType2 option:selected").val();
			$("#selectVal2").val(cont);
		} */
		
		
		/* //新建-切换效果
		function set_selectValue1(){
			var  cont =$("#set_container_one option:selected").text();
			$("#set_selectVal1").val(cont);
		}
		function set_selectValue2(){
			var  cont = $("#set_container_two option:selected").text();
			$("#set_selectVal2").val(cont);
		} */
		
		//新建-提货货场货位切换效果
		function set_selectValue1(param){
			var  cont =$("#take_carogo_place_name"+param+" option:selected").val();
			var  contName =$("#take_carogo_place_name"+param+" option:selected").text();
			$("#take_cargo_place_id"+param+"").val(cont);
			$("#take_carogo_place_name"+(param+2)+"").val(contName);
			$.fd.ajax({  
	             type: "GET",  
	             url:"${pageContext.request.contextPath}/siteManager/cargo/location/getAll.do?id="+cont,  
	             success: function(data) {  
	            		$("#take_cargo_site_name"+param+"").empty();
	 				$.each(data.data, function(index, its) {
	 							$("#take_cargo_site_name"+param+"").append(
			    							"<option value="+its.id+">" +its.code+ "  "+ its.name + "</option>");
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
		
		//等待调度提交
		function submits(){
			/* var  cont1 =$("#take_carogo_place_name2 option:selected").val();
			var  cont2 =$("#take_cargo_site_name2 option:selected").val();
			if(cont1 == "" || cont2 == ""){
				$("#msgNof").html("请把信息补全");
				$("#promptIdModalNof").modal();
				return;
			} */
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/business/short/boxManager/dispatch/add.do",
				data : $('#dispatchId').serialize(),
				dataType : "json",
				async : false,
				success : function(data) {
					if (data.status != 1) {
						$("#msgNof").html(data.msg);
						$("#promptIdModalNof").modal();
					} else {
						$("#msg").html(data.msg);
						$("#promptIdModal").modal();
						$('#dispatchModal').hide();
					}
				}
			});
		}
		
		
		//等待发运度提交
		function subForwarding(){
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/business/short/boxManager/subForwarding/add.do",
				data : $('#sendId').serialize(),
				dataType : "json",
				async : false,
				success : function(data) {
					//$("#forward_add_img").attr('src','');
					if (data.status != 1) {
						$("#msgNof").html(data.msg);
						$("#promptIdModalNof").modal();
					} else {
						$("#msg").html(data.msg);
						$("#promptIdModal").modal();
						$('#waitModal').hide();
					}
				}
			});
		}
		 
		//在途运载 提交
		function carrySub(){
			var isNo = $("#carryId input[name='isAgree']:checked").val();
			if(isNo == 0){
				$('#carryModal').modal("hide");
				return;
			}
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/business/short/boxManager/carry/add.do",
				data : $('#carryId').serialize(),
				dataType : "json",
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
		
		// 货位引导 提交
		function guideFuns(){
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/business/short/boxManager/guide/add.do",
				data : $('#guideId').serialize(),
				dataType : "json",
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
		
		
		// 等待回单 提交
		function receipterFuns(){
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/business/short/boxManager/receipter/add.do",
				data : $('#receiptId').serialize(),
				dataType : "json",
				async : false,
				success : function(data) {
					if (data.status != 1) {
						$("#msgNof").html(data.msg);
						$("#promptIdModalNof").modal();
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
				url : "${pageContext.request.contextPath}/business/short/boxManager/order/delete.do",
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
		
		
		//项目下拉切换 - 获取项目详情
		function exchangeProject(){
			$("#stepSelectCodes").empty();
			//清空车辆信息文本框
			$("#newModalBody input[name='carrierVehicleId']").val("");
        	$("#newModalBody input[name='carrierVehicleName']").val("");
        	$("#newModalBody input[name='driverName']").val("");
        	$("#newModalBody input[name='driverPhone']").val("");
			$("#newModalBody input[name='carType']").val("");
			$("#newModalBody input[name='carPlateNumber']").val("");
			$("#newModalBody input[name='shortBargeCost']").val("");
			$("#newModalBody input[name='deductionPrice']").val("");
			$("#newModalBody input[name='forwardingSiteId']").val("");
			$("#newModalBody input[name='transportType']").val("");
			
			var id = $("#select_project option:selected").val();
			$.fd.ajax({
				type : "GET",
				url : "${pageContext.request.contextPath}/business/short/boxManager/selectProject.do?id="+id,
				dataType : 'json',
				success : function(data) {
					if(data.data.projectType ==0){
						$("#newModalBody input[name='projectTypeName']").val("集装箱");	
					}else{
						$("#newModalBody input[name='projectTypeName']").val("散装");	
					}
					$("#newModalBody input[name='projectCode']").val(data.data.projectCode);
					$("#newModalBody input[name='projectType']").val(data.data.projectType);		 
					$("#newModalBody input[name='branchId']").val(data.data.branchGroupId);			
					$("#newModalBody input[name='branchGroupName']").val(data.data.branchGroupName);			
					$("#newModalBody input[name='cargoName']").val(data.data.cargoName);
					$("#newModalBody input[name='specifications']").val(data.data.cargoSpecifications);
					$("#newModalBody input[name='forwardingSiteId']").val(data.data.forwardingSiteId);
					$("#newModalBody input[name='transportType']").val(data.data.transportType);
					/* $.each(data.data.shortBarges, function(index, rate) {
						$("#newModalBody input[name='deductionRate']").val(rate.deductionRate);
					}); */
					if(data.data.transportType  == 1){
						//接取
						$("#newModalBody input[name='sendCompanyId']").val(data.data.sendCargoUnit.id);
						$("#newModalBody input[name='receiptCompanyId']").val(data.data.receiveTrainStation.id);
						$("#newModalBody input[name='sendCompany']").val(data.data.sendCargoUnit.companyName);
						$("#newModalBody input[name='pickupPlace']").val(data.data.sendCargoUnit.addressCode);
						$("#newModalBody input[name='pickupPlaceAddress']").val(data.data.sendCargoUnit.detailAddress);
						$("#newModalBody input[name='receiptCompany']").val(data.data.receiveTrainStation.stationName);
						$("#newModalBody input[name='arrivePlace']").val(data.data.receiveTrainStation.adressCode);
						$("#newModalBody input[name='arriveAddress']").val(data.data.receiveTrainStation.detailAddress);
					}
					//送达
					if(data.data.transportType  == 2){
						$("#newModalBody input[name='sendCompanyId']").val(data.data.sendTrainStation.id);
						$("#newModalBody input[name='receiptCompanyId']").val(data.data.receivingDepartment.id);
						$("#newModalBody input[name='sendCompany']").val(data.data.sendTrainStation.stationName);
						$("#newModalBody input[name='pickupPlace']").val(data.data.sendTrainStation.adressCode);
						$("#newModalBody input[name='pickupPlaceAddress']").val(data.data.sendTrainStation.detailAddress);
						$("#newModalBody input[name='receiptCompany']").val(data.data.receivingDepartment.companyName);
						$("#newModalBody input[name='arrivePlace']").val(data.data.receivingDepartment.addressCode);
						$("#newModalBody input[name='arriveAddress']").val(data.data.receivingDepartment.detailAddress);
					}
					//汽运
					if(data.data.transportType  == 0){
						$("#newModalBody input[name='sendCompanyId']").val(data.data.sendCargoUnit.id);
						$("#newModalBody input[name='receiptCompanyId']").val(data.data.receivingDepartment.id);
						$("#newModalBody input[name='sendCompany']").val(data.data.sendCargoUnit.companyName);
						$("#newModalBody input[name='pickupPlace']").val(data.data.sendCargoUnit.addressCode);
						$("#newModalBody input[name='pickupPlaceAddress']").val(data.data.sendCargoUnit.detailAddress);
						$("#newModalBody input[name='receiptCompany']").val(data.data.receivingDepartment.companyName);
						$("#newModalBody input[name='arrivePlace']").val(data.data.receivingDepartment.addressCode);
						$("#newModalBody input[name='arriveAddress']").val(data.data.receivingDepartment.detailAddress);
					}
					//接取+火运
					if(data.data.transportType  == 4){
						$("#newModalBody input[name='sendCompanyId']").val(data.data.sendCargoUnit.id);
						$("#newModalBody input[name='receiptCompanyId']").val(data.data.receiveTrainStation.id);
						$("#newModalBody input[name='sendCompany']").val(data.data.sendCargoUnit.companyName);
						$("#newModalBody input[name='pickupPlace']").val(data.data.sendCargoUnit.addressCode);
						$("#newModalBody input[name='pickupPlaceAddress']").val(data.data.sendCargoUnit.detailAddress);
						$("#newModalBody input[name='receiptCompany']").val(data.data.receiveTrainStation.stationName);
						$("#newModalBody input[name='arrivePlace']").val(data.data.receiveTrainStation.adressCode);
						$("#newModalBody input[name='arriveAddress']").val(data.data.receiveTrainStation.detailAddress);
					}
					
					//火运+送达
					if(data.data.transportType  == 5){
						$("#newModalBody input[name='sendCompanyId']").val(data.data.sendTrainStation.id);
						$("#newModalBody input[name='receiptCompanyId']").val(data.data.receivingDepartment.id);
						$("#newModalBody input[name='sendCompany']").val(data.data.sendTrainStation.stationName);
						$("#newModalBody input[name='pickupPlace']").val(data.data.sendTrainStation.adressCode);
						$("#newModalBody input[name='pickupPlaceAddress']").val(data.data.sendTrainStation.detailAddress);
						$("#newModalBody input[name='receiptCompany']").val(data.data.receivingDepartment.companyName);
						$("#newModalBody input[name='arrivePlace']").val(data.data.receivingDepartment.addressCode);
						$("#newModalBody input[name='arriveAddress']").val(data.data.receivingDepartment.detailAddress);
					}
					//联运
					if(data.data.transportType  == 6){
						$("#newModalBody input[name='sendCompanyId']").val(data.data.sendCargoUnit.id);
						$("#newModalBody input[name='receiptCompanyId']").val(data.data.receivingDepartment.id);
						$("#newModalBody input[name='sendCompany']").val(data.data.sendCargoUnit.companyName);
						$("#newModalBody input[name='pickupPlace']").val(data.data.sendCargoUnit.addressCode);
						$("#newModalBody input[name='pickupPlaceAddress']").val(data.data.sendCargoUnit.detailAddress);
						$("#newModalBody input[name='receiptCompany']").val(data.data.receiveTrainStation.stationName);
						$("#newModalBody input[name='arrivePlace']").val(data.data.receiveTrainStation.adressCode);
						$("#newModalBody input[name='arriveAddress']").val(data.data.receiveTrainStation.detailAddress);
					}
					
					//接取+送达
					if(data.data.transportType  == 7){
						$("#newModalBody input[name='sendCompanyId']").val(data.data.sendCargoUnit.id);
						$("#newModalBody input[name='receiptCompanyId']").val(data.data.receivingDepartment.id);
						$("#newModalBody input[name='sendCompany']").val(data.data.sendCargoUnit.companyName);
						$("#newModalBody input[name='pickupPlace']").val(data.data.sendCargoUnit.addressCode);
						$("#newModalBody input[name='pickupPlaceAddress']").val(data.data.sendCargoUnit.detailAddress);
						$("#newModalBody input[name='receiptCompany']").val(data.data.receiveTrainStation.stationName);
						$("#newModalBody input[name='arrivePlace']").val(data.data.receiveTrainStation.adressCode);
						$("#newModalBody input[name='arriveAddress']").val(data.data.receiveTrainStation.detailAddress);
					}
					if(data.data.valuationUnitName ==0){
						$("#valuationUnitTypeName").val("件");
						$("#newModalBody input[name='valuationUnitType']").val(data.data.valuationUnitName);
					}else if(data.data.valuationUnitName ==1){
						$("#valuationUnitTypeName").val("吨");
						$("#newModalBody input[name='valuationUnitType']").val(data.data.valuationUnitName);
					}
					$("#newModalBody input[name='carTeamId']").val(data.data.shortBargeId);
					
					if(data.data.transportType == 6 || data.data.transportType == 7){
						$("#stepSelectCodes").append("<option value='0'>接取</option> <option value='1'>送达</option>");
						$("#stepSelectCodes").change();
					} 
					if(data.data.transportType == 0){
						$("#stepSelectCodes").append("<option value='2'>汽运</option>");
						$("#stepSelectCodes").change();
					}
					if(data.data.transportType == 1 || data.data.transportType == 4){
						$("#stepSelectCodes").append("<option value='0'>接取</option>");
						$("#stepSelectCodes").change();
					}
					if(data.data.transportType == 2 || data.data.transportType == 5){
						$("#stepSelectCodes").append("<option value='1'>送达</option>");
						$("#stepSelectCodes").change();
					}
					var shortType = $("#stepSelectCodes option:selected").val();
					
					var model = data.data.shortBargeCarrierMode;
					if(shortType==0 || shortType==2 ){//接取
						model = data.data.shortBargeCarrierMode;
					}else{//送达
						model = data.data.sendShortBargeCarrierMode;			
					}
					$("#myModal input[name=model]").val(model);
					
					 //车辆下拉
					 $.fd.ajax({  
			                type: "GET",  
			                url:"${pageContext.request.contextPath}/business/short/boxManager/carTeam/select.do?id="+data.data.id+"&model="+model+"&shortType="+shortType,  
			                success: function(data) {  
			                	 $('#msgH5').html("");
			                	  $('#createOrderBtn').removeAttr('disabled');
			                	$("#car_select").html("");
			    				$.each(data.data, function(index, its) {
			    							$("#car_select").append(
					    							"<option value="+its.driverId+">" + its.plateNumber
					    									+ "</option>");
			    				});
			    				$("#car_select").change();	
			    				if(data.status==1001){
		                		 $('#msgH5').html("此项目今日未分配任务");
		                		 //确认按钮置灰
		                		  $('#createOrderBtn').attr('disabled',true);
		                 		}
			                }
			           }); 
					
					 
				}
			});
			
		}
		
		
		//阶段切换
		function stepChange(){
			$("#newModalBody input[name='sendCompanyId']").val("");
			$("#newModalBody input[name='receiptCompanyId']").val("");
			$("#newModalBody input[name='sendCompany']").val("");
			$("#newModalBody input[name='pickupPlace']").val("");
			$("#newModalBody input[name='pickupPlaceAddress']").val("");
			$("#newModalBody input[name='receiptCompany']").val("");
			$("#newModalBody input[name='arrivePlace']").val("");
			$("#newModalBody input[name='arriveAddress']").val("");
			var id = $("#select_project option:selected").val();
			$.fd.ajax({
				type : "GET",
				url : "${pageContext.request.contextPath}/business/short/boxManager/selectProject.do?id="+id,
				dataType : 'json',
				success : function(data) {
					var step = $("#stepSelectCodes option:selected").val();
					if(data.data.transportType  == 1){
						//接取
						$("#newModalBody input[name='sendCompanyId']").val(data.data.sendCargoUnit.id);
						$("#newModalBody input[name='receiptCompanyId']").val(data.data.receiveTrainStation.id);
						$("#newModalBody input[name='sendCompany']").val(data.data.sendCargoUnit.companyName);
						$("#newModalBody input[name='pickupPlace']").val(data.data.sendCargoUnit.addressCode);
						$("#newModalBody input[name='pickupPlaceAddress']").val(data.data.sendCargoUnit.detailAddress);
						$("#newModalBody input[name='receiptCompany']").val(data.data.receiveTrainStation.stationName);
						$("#newModalBody input[name='arrivePlace']").val(data.data.receiveTrainStation.adressCode);
						$("#newModalBody input[name='arriveAddress']").val(data.data.receiveTrainStation.detailAddress);
					}
					//送达
					if(data.data.transportType  == 2){
						$("#newModalBody input[name='sendCompanyId']").val(data.data.sendTrainStation.id);
						$("#newModalBody input[name='receiptCompanyId']").val(data.data.receivingDepartment.id);
						$("#newModalBody input[name='sendCompany']").val(data.data.sendTrainStation.stationName);
						$("#newModalBody input[name='pickupPlace']").val(data.data.sendTrainStation.adressCode);
						$("#newModalBody input[name='pickupPlaceAddress']").val(data.data.sendTrainStation.detailAddress);
						$("#newModalBody input[name='receiptCompany']").val(data.data.receivingDepartment.companyName);
						$("#newModalBody input[name='arrivePlace']").val(data.data.receivingDepartment.addressCode);
						$("#newModalBody input[name='arriveAddress']").val(data.data.receivingDepartment.detailAddress);
					}
					//汽运
					if(data.data.transportType  == 0){
						$("#newModalBody input[name='sendCompanyId']").val(data.data.sendCargoUnit.id);
						$("#newModalBody input[name='receiptCompanyId']").val(data.data.receivingDepartment.id);
						$("#newModalBody input[name='sendCompany']").val(data.data.sendCargoUnit.companyName);
						$("#newModalBody input[name='pickupPlace']").val(data.data.sendCargoUnit.addressCode);
						$("#newModalBody input[name='pickupPlaceAddress']").val(data.data.sendCargoUnit.detailAddress);
						$("#newModalBody input[name='receiptCompany']").val(data.data.receivingDepartment.companyName);
						$("#newModalBody input[name='arrivePlace']").val(data.data.receivingDepartment.addressCode);
						$("#newModalBody input[name='arriveAddress']").val(data.data.receivingDepartment.detailAddress);
					}
					//接取+火运
					if(data.data.transportType  == 4){
						$("#newModalBody input[name='sendCompanyId']").val(data.data.sendCargoUnit.id);
						$("#newModalBody input[name='receiptCompanyId']").val(data.data.receiveTrainStation.id);
						$("#newModalBody input[name='sendCompany']").val(data.data.sendCargoUnit.companyName);
						$("#newModalBody input[name='pickupPlace']").val(data.data.sendCargoUnit.addressCode);
						$("#newModalBody input[name='pickupPlaceAddress']").val(data.data.sendCargoUnit.detailAddress);
						$("#newModalBody input[name='receiptCompany']").val(data.data.receiveTrainStation.stationName);
						$("#newModalBody input[name='arrivePlace']").val(data.data.receiveTrainStation.adressCode);
						$("#newModalBody input[name='arriveAddress']").val(data.data.receiveTrainStation.detailAddress);
					}
					
					//火运+送达
					if(data.data.transportType  == 5){
						$("#newModalBody input[name='sendCompanyId']").val(data.data.sendTrainStation.id);
						$("#newModalBody input[name='receiptCompanyId']").val(data.data.receivingDepartment.id);
						$("#newModalBody input[name='sendCompany']").val(data.data.sendTrainStation.stationName);
						$("#newModalBody input[name='pickupPlace']").val(data.data.sendTrainStation.adressCode);
						$("#newModalBody input[name='pickupPlaceAddress']").val(data.data.sendTrainStation.detailAddress);
						$("#newModalBody input[name='receiptCompany']").val(data.data.receivingDepartment.companyName);
						$("#newModalBody input[name='arrivePlace']").val(data.data.receivingDepartment.addressCode);
						$("#newModalBody input[name='arriveAddress']").val(data.data.receivingDepartment.detailAddress);
					}
					//联运
					if(data.data.transportType  == 6){
						if(step == 0){
							$("#newModalBody input[name='sendCompanyId']").val(data.data.sendCargoUnit.id);
							$("#newModalBody input[name='receiptCompanyId']").val(data.data.receiveTrainStation.id);
							$("#newModalBody input[name='sendCompany']").val(data.data.sendCargoUnit.companyName);
							$("#newModalBody input[name='pickupPlace']").val(data.data.sendCargoUnit.addressCode);
							$("#newModalBody input[name='pickupPlaceAddress']").val(data.data.sendCargoUnit.detailAddress);
							$("#newModalBody input[name='receiptCompany']").val(data.data.receiveTrainStation.stationName);
							$("#newModalBody input[name='arrivePlace']").val(data.data.receiveTrainStation.adressCode);
							$("#newModalBody input[name='arriveAddress']").val(data.data.receiveTrainStation.detailAddress);
						}else if(step == 1){
							$("#newModalBody input[name='sendCompanyId']").val(data.data.sendTrainStation.id);
							$("#newModalBody input[name='receiptCompanyId']").val(data.data.receivingDepartment.id);
							$("#newModalBody input[name='sendCompany']").val(data.data.sendTrainStation.stationName);
							$("#newModalBody input[name='pickupPlace']").val(data.data.sendTrainStation.adressCode);
							$("#newModalBody input[name='pickupPlaceAddress']").val(data.data.sendTrainStation.detailAddress);
							$("#newModalBody input[name='receiptCompany']").val(data.data.receivingDepartment.companyName);
							$("#newModalBody input[name='arrivePlace']").val(data.data.receivingDepartment.addressCode);
							$("#newModalBody input[name='arriveAddress']").val(data.data.receivingDepartment.detailAddress);
						}
					
					}
					
					//接取+送达
					if(data.data.transportType  == 7){
						if(step == 0){
							$("#newModalBody input[name='sendCompanyId']").val(data.data.sendCargoUnit.id);
							$("#newModalBody input[name='receiptCompanyId']").val(data.data.receiveTrainStation.id);
							$("#newModalBody input[name='sendCompany']").val(data.data.sendCargoUnit.companyName);
							$("#newModalBody input[name='pickupPlace']").val(data.data.sendCargoUnit.addressCode);
							$("#newModalBody input[name='pickupPlaceAddress']").val(data.data.sendCargoUnit.detailAddress);
							$("#newModalBody input[name='receiptCompany']").val(data.data.receiveTrainStation.stationName);
							$("#newModalBody input[name='arrivePlace']").val(data.data.receiveTrainStation.adressCode);
							$("#newModalBody input[name='arriveAddress']").val(data.data.receiveTrainStation.detailAddress);
						}else if(step == 1){
							$("#newModalBody input[name='sendCompanyId']").val(data.data.sendTrainStation.id);
							$("#newModalBody input[name='receiptCompanyId']").val(data.data.receivingDepartment.id);
							$("#newModalBody input[name='sendCompany']").val(data.data.sendTrainStation.stationName);
							$("#newModalBody input[name='pickupPlace']").val(data.data.sendTrainStation.adressCode);
							$("#newModalBody input[name='pickupPlaceAddress']").val(data.data.sendTrainStation.detailAddress);
							$("#newModalBody input[name='receiptCompany']").val(data.data.receivingDepartment.companyName);
							$("#newModalBody input[name='arrivePlace']").val(data.data.receivingDepartment.addressCode);
							$("#newModalBody input[name='arriveAddress']").val(data.data.receivingDepartment.detailAddress);
						}
					}
					 //阶段选择为接取  查询所有独立货场 为送达 查询接取站点货场forwarding_site_id
					 if(step == 0){
						 $("#take_carogo_place_name1").empty();
						 $("#take_cargo_site_name1").empty();
					     var trainStationId = $("#sendCompanyId").val();
						 $.fd.ajax({
								type : "GET",
								url : "${pageContext.request.contextPath}/siteManager/getAllFreightYardOfIsolated.do?id="+trainStationId,
								dataType : 'json',
								success : function(data) {
									$.each(data.data, function(index, its) {
										$("#take_carogo_place_name1").append(
				    							"<option value="+its.id+">" + its.name + "</option>");
		    						});
									$("#take_carogo_place_name1").change();
								}
						 });
						 $("#changeNameByStep1").html("发货单位：");
						 $("#changeNameByStep2").html("收货站点：");
						 $().getRes();
					 }else if(step == 1){
						 $("#take_carogo_place_name1").empty();
						 $("#take_cargo_site_name1").empty();
						 var trainStationId = $("#forwardingSiteId").val();
						 $.ajax({
								type : "POST",
								url : "${pageContext.request.contextPath}/siteManager/getFreightYardByStationId.do",
								data :{id:trainStationId},
								dataType : 'json',
								success : function(data) {
									$.each(data.data, function(index, its) {
										$("#take_carogo_place_name1").append(
				    							"<option value="+its.id+">" + its.name + "</option>");
		    						});
									$("#take_carogo_place_name1").change();
								}
						 });
						 $("#changeNameByStep1").html("发货站点：");
						 $("#changeNameByStep2").html("收货单位：");
						 $().getRes();
					 }else{
						 $("#take_carogo_place_name1").empty();
						 $("#take_cargo_site_name1").empty();
						 var trainStationId = $("#sendCompanyId").val();
						 //汽运 
						 $.fd.ajax({
								type : "GET",
								url : "${pageContext.request.contextPath}/siteManager/getAllFreightYardOfIsolated.do?id="+trainStationId,
								dataType : 'json',
								success : function(data) {
									$.each(data.data, function(index, its) {
										$("#take_carogo_place_name1").append(
				    							"<option value="+its.id+">" + its.name + "</option>");
		    						});
									$("#take_carogo_place_name1").change();
								}
						 });
						 $("#changeNameByStep1").html("发货单位：");
						 $("#changeNameByStep2").html("收货单位：");
						 $().getRes();
					 }
						 var shortType = $("#stepSelectCodes option:selected").val();
					var model = data.data.shortBargeCarrierMode;
					if(shortType==0 || shortType==2){//接取
						model = data.data.shortBargeCarrierMode;
					}else{//送达
						model = data.data.sendShortBargeCarrierMode;			
					}
					$("#myModal input[name=model]").val(model);
					 
					 //车辆下拉
					 $.fd.ajax({  
			                type: "GET",  
			                url:"${pageContext.request.contextPath}/business/short/boxManager/carTeam/select.do?id="+data.data.id+"&model="+model+"&shortType="+shortType,   
			                success: function(data) {
			                		$('#msgH5').html("");
			                		 $('#createOrderBtn').removeAttr('disabled');
			                		$("#car_select").empty();
				    					$.each(data.data, function(index, its) {
				    							$("#car_select").append(
						    					"<option value="+its.driverId+">" + its.plateNumber
						    						+ "</option>");
				    				});
			    					$("#car_select").change();	
			                 		if(data.status==1001){
			                		 $('#msgH5').html("此项目今日未分配任务");
			                		 //确认按钮置灰
			                		  $('#createOrderBtn').attr('disabled',true);
			                 		}
			                	}
			           	});
	            }
	        }); 
			
		}
		
		
		//新增-车辆下拉信息详情
		function carSelect(){
			//清空车辆信息文本框
			$("#newModalBody input[name='carrierVehicleId']").val("");
        	$("#newModalBody input[name='carrierVehicleName']").val("");
        	$("#newModalBody input[name='driverName']").val("");
        	$("#newModalBody input[name='driverPhone']").val("");
			$("#newModalBody input[name='carType']").val("");
			$("#newModalBody input[name='carPlateNumber']").val("");
			$("#newModalBody input[name='shortBargeCost']").val("");
			$("#newModalBody input[name='deductionPrice']").val("");
			$("#newModalBody input[name='driverId']").val("");
			$("#newModalBody input[name='deductionRate']").val("");
			
			var driverId = $("#car_select option:selected").val(); 
			
			if(driverId==undefined || driverId == null  || driverId ==""){
					return;
				}
			
			var shortType = $("#stepSelectCodes option:selected").val();
			
			var model = $("#myModal input[name=model]").val();
			var id = $("#select_project").val();
			 $.fd.ajax({  
	                type: "GET",  
	                url:"${pageContext.request.contextPath}/business/short/boxManager/carTeam/select.do?id="+id+"&driverId="+driverId+"&model="+model+"&shortType="+shortType,  
	                success: function(data) {  
	                	$("#newModalBody input[name='carrierVehicleId']").val(data.data[0].driverId);
	                	$("#newModalBody input[name='carrierVehicleName']").val(data.data[0].driverName);
	                	$("#newModalBody input[name='driverId']").val(data.data[0].driverId);
	                	$("#newModalBody input[name='driverName']").val(data.data[0].driverName);
	                	$("#newModalBody input[name='driverPhone']").val(data.data[0].driverPhone);
	    				$("#newModalBody input[name='carType']").val(data.data[0].motorcycleType);
	    				$("#newModalBody input[name='carPlateNumber']").val(data.data[0].plateNumber);
	    				$("#newModalBody input[name='shortBargeCost']").val(data.data[0].transportPrice);
						$("#newModalBody input[name='deductionPrice']").val(data.data[0].deductionPrice);
						$("#newModalBody input[name='deductionRate']").val(data.data[0].deductionRate);
	                }
	            }); 
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
				url : "${pageContext.request.contextPath}/business/short/boxManager/order/revert.do",
				dataType : 'json',
				data : {
					"idList" : idList
				},
				success : function(data) {
					if (data.status != 1) {
						$("#deltialModal").hide();
						$("#msg").html(data.msg);
						$("#promptIdModal").modal();
					} else {
						$("#deltialModal").hide();
						$("#msg").html(data.msg);
						$("#promptIdModal").modal();
					}
				}
			});
			
		}
		
	$(function(){
		$.fn.getRes=function(){
			//根据项目阶段 获取集装箱号 接取：收货站点，送达：发货站点，汽运：无结果 
			var steps = $("#stepSelectCodes option:selected").val();
			// 0接取 1送达  2汽运
			var projectId = $("#select_project option:selected").val();
			var trainStationId = "";
			var kind = "";
			if(steps == 0){
				var trainStationIdAppend = $("#receiptCompanyId").val();
				//传阶段 然后在后台很久项目id查询出项目联运模式 设定集装箱状态值  0接取 1送达  2火运 
				kind = 0;
				//参数拼接 在后台处理 
				trainStationId = projectId+","+trainStationIdAppend+","+kind+","+""+","+1;
				$("#addId [name=set_containerNumber1]").FdSelect2({url:'/logistics-manage-web/api/basicdata/typesByTrain/testType/ids/'+trainStationId,type : 'trainStatus'});
				$("#addId [name=set_containerNumber2]").FdSelect2({url:'/logistics-manage-web/api/basicdata/typesByTrain/testType/ids/'+trainStationId,type : 'trainStatus'});
				$("#set_containerNumber1").change();
				$("#set_containerNumber2").change();
			}else if(steps == 1){
				var trainStationIdAppend = $("#sendCompanyId").val();
				kind = 1;
				trainStationId = projectId+","+trainStationIdAppend+","+kind+","+""+","+1;
				$("#addId [name=set_containerNumber1]").FdSelect2({url:'/logistics-manage-web/api/basicdata/typesByTrain/testType/ids/'+trainStationId,type : 'trainStatus'});
				$("#addId [name=set_containerNumber2]").FdSelect2({url:'/logistics-manage-web/api/basicdata/typesByTrain/testType/ids/'+trainStationId,type : 'trainStatus'});
				$("#set_containerNumber1").change();
				$("#set_containerNumber2").change();
			}else{
				$("#addId [name=set_containerNumber1]").empty();
				$("#addId [name=set_containerNumber2]").empty();
				$("#addId [name=set_containerNumber1]").append("<option value=''>汽运没有集装箱号</option>");
				$("#addId [name=set_containerNumber2]").append("<option value=''>汽运没有集装箱号</option>");
			}
	    }
		
		//等待调度 集装箱获取 
		$.fn.getDisptachRes=function(){
			//根据项目阶段 获取集装箱号 接取：收货站点，送达：发货站点，汽运：无结果 
			var stepds = $("#dispatchHiddenStepSelect").val();
			// 0接取 1送达  2汽运 
			var dprojectId = $("#dispatchHiddenProject").val();
			var dtrainStationId = "";
			var dkind = "";
			if(stepds == 0){
				var dtrainStationIdAppend = $("#dispatchHiddenReceiveId").val();
				//传阶段 然后在后台很久项目id查询出项目联运模式 设定集装箱状态值  0接取 1送达  2火运 
				dkind = 0;
				//参数拼接 在后台处理 
				dtrainStationId = dprojectId+","+dtrainStationIdAppend+","+dkind+","+""+","+1;
				$("#dispatchId [name=dispatch_containerNumber1]").FdSelect2({url:'/logistics-manage-web/api/basicdata/typesByTrain/testType/ids/'+dtrainStationId,type : 'trainStatus'});
				$("#dispatchId [name=dispatch_containerNumber2]").FdSelect2({url:'/logistics-manage-web/api/basicdata/typesByTrain/testType/ids/'+dtrainStationId,type : 'trainStatus'});
				$("#dispatch_containerNumber1").change();
				$("#dispatch_containerNumber2").change();
			}else if(stepds == 1){
				var dtrainStationIdAppend = $("#dispatchHiddenSendId").val();
				dkind = 1;
				dtrainStationId = dprojectId+","+dtrainStationIdAppend+","+dkind+","+""+","+1;
				$("#dispatchId [name=dispatch_containerNumber1]").FdSelect2({url:'/logistics-manage-web/api/basicdata/typesByTrain/testType/ids/'+dtrainStationId,type : 'trainStatus'});
				$("#dispatchId [name=dispatch_containerNumber2]").FdSelect2({url:'/logistics-manage-web/api/basicdata/typesByTrain/testType/ids/'+dtrainStationId,type : 'trainStatus'});
				$("#dispatch_containerNumber1").change();
				$("#dispatch_containerNumber2").change();
			}else{
				$("#dispatchId [name=dispatch_containerNumber1]").empty();
				$("#dispatchId [name=dispatch_containerNumber2]").empty();
				$("#dispatchId [name=dispatch_containerNumber1]").append("<option value=''>汽运没有集装箱号</option>");
				$("#dispatchId [name=dispatch_containerNumber2]").append("<option value=''>汽运没有集装箱号</option>");
			}
	    }
	})
	
		
		function jzxNum(param){
			var jzxArr = $("#addId [name=set_containerNumber"+param+"]");
			for(var i=0;i<jzxArr.length;i++){
				if(jzxArr.eq(i).val()){
					
				}else{
					jzxArr.eq(i).val(null);
					jzxArr.eq(i).text(null);
				}
			}
			var  text =$("#set_containerNumber"+param+" option:selected").text();
			$("#containerNumber"+param+"").val(text);
		}
		
	function dispatchJzxNum(param){
		var djzxArr = $("#dispatchId [name=dispatch_containerNumber"+param+"]");
		for(var i=0;i<djzxArr.length;i++){
			if(djzxArr.eq(i).val()){
				
			}else{
				djzxArr.eq(i).val(null);
				djzxArr.eq(i).text(null);
			}
		}
		var  dtext =$("#dispatch_containerNumber"+param+" option:selected").text();
		$("#selectType"+param+"").val(dtext);
	}
	
		function  orderAdds(){
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/business/short/boxManager/order/add.do",
				dataType : 'json',
				data : $("#addId").serialize(),
				success : function(data) {
					if (data.status != 1) {
						$("#msgNof").html(data.msg);
						$("#promptIdModalNof").modal();
					} else {
						$("#myModal").hide();
						$("#msg").html(data.msg);
						$("#promptIdModal").modal();
					}
				}
			});
		}
		
		//sendTare sendGross containerOneSendNet
		function accturelyWeight(){
			var sendTare = getFloatStr($("#sendTare").val());
			var sendGross = getFloatStr($("#sendGross").val());
			if(sendTare == "" || sendGross == ""){
				$("#sendGrossHide").val("");
			}else{
				var containerOneSendNet = FloatSub(parseFloat(sendGross),parseFloat(sendTare));
				var r = /^\d+(\.\d+)?$/;
				if(r.test(containerOneSendNet)){
					$("#sendGrossHide").val(containerOneSendNet);
				}else{
					$("#sendTare").val("");
					$("#msgNof").html("发货皮重不能大于发货毛重"); 
					$("#promptIdModalNof").modal();
					return;
				}
				
			}
			sumTwoAccturelyWeight();
		}
		
		//containerOneSendNet containerTwoSendNet sumTwoAccturelyWeight firContainer secContainer
		function sumTwoAccturelyWeight(){
			var containerOneSendNet = getFloatStr($("#containerOneSendNet").val());
			var containerTwoSendNet = getFloatStr($("#containerTwoSendNet").val());
			var firContainer = $("#firContainer").html();
			var secContainer = $("#secContainer").html();
			var sendGrossHide = getFloatStr($("#sendGrossHide").val());
			if(containerOneSendNet ==""){
				containerOneSendNet = 0;
			}
			if(containerTwoSendNet ==""){
				containerTwoSendNet = 0;
			}
			if(sendGrossHide ==""){
				//先填写的净重 不做校验 
			}else{
				var twoSumWeight = FloatAdd(containerOneSendNet,containerTwoSendNet);
				var copmpareWeight = FloatSub(sendGrossHide,twoSumWeight);
				var re = /^\d+(\.\d+)?$/;
				if(re.test(copmpareWeight)){
					
				}else{
					if(firContainer == ""){
						
					}else{
						$("#containerOneSendNet").val("");
					}
					if(secContainer == ""){
						
					}else{
						$("#containerTwoSendNet").val("");
					}
					$("#msgNof").html("发货净重异常"); 
					$("#promptIdModalNof").modal();
				}
			}
			
		}
		
		//receiptGross receiptTare containerOneReceiptNet containerTwoReceiptNet
		function receiveAccturelyWeight(){
			var receiptTare = getFloatStr($("#receiptTare").val());
			var receiptGross =getFloatStr($("#receiptGross").val());
			if(receiptTare == "" || receiptGross == ""){
				$("#receiveGrossHide").val("");
			}else{
				var containerOneRecNet = FloatSub(receiptGross,receiptTare);
				var r = /^\d+(\.\d+)?$/;
				if(r.test(containerOneRecNet)){
					$("#receiveGrossHide").val(containerOneRecNet);
				}else{
					$("#receiptTare").val("");
					$("#msgNof").html("收货皮重不能大于收货毛重"); 
					$("#promptIdModalNof").modal();
					return;
				}
				
			}
			receiveSumTwoAccturelyWeight();
		}
		
		//containerOneReceiptNet containerTwoReceiptNet sumTwoAccturelyWeight
		function receiveSumTwoAccturelyWeight(){
			var containerOneReceiptNet = getFloatStr($("#containerOneReceiptNet").val());
			var containerTwoReceiptNet = getFloatStr($("#containerTwoReceiptNet").val());
			var receipt_1 = $("#receipt_1").html();
			var receipt_2 = $("#receipt_2").html();
			var receiveGrossHide = $("#receiveGrossHide").val();
			if(containerOneReceiptNet ==""){
				containerOneReceiptNet = 0;
			}
			if(containerTwoReceiptNet ==""){
				containerTwoReceiptNet = 0;
			}
			if(receiveGrossHide ==""){
				//先填写的净重 不做校验 
			}else{
				var twoSumWeights = FloatAdd(containerOneReceiptNet,containerTwoReceiptNet);
				var copmpareWeights = FloatSub(getFloatStr(receiveGrossHide),twoSumWeights);
				var re = /^\d+(\.\d+)?$/;
				if(re.test(copmpareWeights)){
					
				}else{
					if(receipt_1 == ""){
						
					}else{
						$("#containerOneReceiptNet").val("");
					}
					if(receipt_2 == ""){
						
					}else{
						$("#containerTwoReceiptNet").val("");
					}
					$("#msgNof").html("收货净重异常"); 
					$("#promptIdModalNof").modal();
				}
			}
			
		}
		
		function getFloatStr(num){  
	        num += '';  
	        num = num.replace(/[^0-9|\.]/g, ''); //清除字符串中的非数字非.字符  
	          
	        if(/^0+/) //清除字符串开头的0  
	            num = num.replace(/^0+/, '');  
	        if(!/\./.test(num)) //为整数字符串在末尾添加.00  
	            num += '.00';  
	        if(/^\./.test(num)) //字符以.开头时,在开头添加0  
	            num = '0' + num;  
	        num += '00';        //在字符串末尾补零  
	        num = num.match(/\d+\.\d{2}/)[0];
	        return num;
	    };  
		
		//浮点数减法运算
		function FloatSub(arg1,arg2){
		    var r1,r2,m,n;
		    try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
		    try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
		    m=Math.pow(10,Math.max(r1,r2));
		    //动态控制精度长度
		    n=(r1=r2)?r1:r2;
		    return ((arg1*m-arg2*m)/m).toFixed(n);
		}
		
		 //浮点数加法运算
		function FloatAdd(arg1,arg2){
		    var r1,r2,m;
		    try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
		    try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
		    m=Math.pow(10,Math.max(r1,r2));
		    return (arg1*m+arg2*m)/m;
		}
	</script>
	
	
	<script type="text/javascript">
	function clickImg(param){
	    $(param).parent('div').children('input').click();    		
	}
	
	//等待回单
	function clickImgArrive(param){
	    $(param).parent('div').parent('div').children('input').click();    		
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
                $("#msgNof").html("");
     			$("#promptIdModalNof").modal();
     			$("#msgNof").html("抱歉，你的浏览器不支持 FileReader，不能将图片转换为Base64，请使用现代浏览器操作！");
            } else {  
                try {  
                    /*图片转Base64 核心代码*/  
                    var file = input_file.files[0];
                    //这里我们判断下类型如果不是图片就返回 去掉就可以上传任意文件  5242880 / 204800
                    if (!/image\/\w+/.test(file.type)) {  
                        $("#msgNof").html("");
             			$("#promptIdModalNof").modal();
             			$("#msgNof").html("请确保文件为图像类型");
             			$("#forward_add_img2").hide();
                        return false;  
                    } 
                    if(file.size>5242880){
                    	 $("#msgNof").html("");
             			$("#promptIdModalNof").modal();
             			$("#msgNof").html("请上传大小小于5M的图片");
             			$("#forward_add_img2").hide();
                    	 return false;  
                    }
                    var reader = new FileReader();  
                    reader.onload = function () {  
                        get_data(this.result);  
                    }  
                    reader.readAsDataURL(file);  
                } catch (e) {  
                	$("#msgNof").html("");
        			$("#promptIdModalNof").modal();
        			$("#msgNof").html('图片转Base64出错啦！');
        			$("#forward_add_img2").hide();
                   // alert( + e.toString())  
                }  
            }  
        }  
	
	</script>	
	</head>

<!-- 搜索 -->
	<script>
	function changeType(param) {
		$('#type').val("");
		$('#type').val(param);
	}
	
	function search(param) {
		var search = JSON.stringify($(param).parent('a').parent('div').parent('form').serializeJSON());
		var type = $('#type').val();
		if(type == 4){
			//回收站
			$.ajax({
				type: 'post',
				url: '${pageContext.request.contextPath}/business/short/boxManager/delete/byPage/list.do',
				data: {
					page: 1,
					order: search
				},
				cache: false,
				dataType: 'json',
				success: function(data) {
					if(data.status == 200) {
						$("#page_4").paging({
							pageNo:1, 
							totalPage:data.data.totalPage,
	        				totalSize:data.data.limit,
							callback: function(num) {
									$.ajax({  
						        	type:'post',      
						       		url:'${pageContext.request.contextPath}/business/short/boxManager/delete/byPage/list.do',  
							        data:{page:num,order:JSON.stringify($('#orderSearchForm').serializeJSON())},
							        cache:false,
							        dataType:'json',
						        	success:function(data){
						        		if(data.status==200){
						        		var boxOrderList = data.data.rows;
						        		$('#tbody_4').html("");
						        		$.each(boxOrderList,function(index,it){
						        			if(it.deleteFlag ==1){
						        				var sta = "";
						        				if(it.status ==1){
						        					sta = "等待调度";
						        				}
						        				if(it.status ==2){
						        					sta = "等待发运";
						        				}
						        				if(it.status ==3){
						        					sta = "在途运载";
						        				}
						        				if(it.status ==4){
						        					sta = "货物引导";
						        				}
						        				if(it.status ==5){
						        					if(it.receipterDate == ""){
						        						sta = "等待回单";
						       						}else{
						       							sta = "等待确认"; 
						       						}
						        				}
						        				if(it.status ==6){
						        					sta = "计费确认";
						        				}	
						        				if(it.status ==7){
						        					sta = "已完成";
						        				}
							       				$('#tbody_4').append("<tr id='"+it.id+"'><td style='text-align: center;'><label class='demo--label'>"
														 +" <input class='demo--checkbox'  type='checkbox' value='"+it.id+"'>"
							                        		 +" <span class='demo--checkboxInput'></span></label></td>"
							                  		+"<td>"+it.projectCode+"</td>"
							 						+"<td> <a href='#'>"+it.orderCode+"</a></td>"	
							 						+"<td>"+it.createDate+"</td>"	
												+"<td>"+sta+"</td>"
												+"<td>"+it.updateDate+"</td>"
												+"<td>"+it.userDispatchName+"</td>"	
												+"<td>"+it.branchGroupName+"</td>"	
												+"<td>"+it.cargoName +"</td>"	
												+"<td>"+it.testIndicators+"</td>"	
												+"<td>"+it.sendCompany+"</td>"	
												+"<td>"+it.exportSumSendNet+"吨</td>"	
												+"<td>"+it.receiptCompany+"</td>"	
												+"<td>"+it.exportSumReceiptNet+"吨</td>"	
												+"<td>"+it.carPlateNumber+"</td>"
												+"<td>"+it.containerNumber1+"</td>"
												+"<td>"+it.containerNumber2+"</td>"
												+"<td>"+it.deleteTime+"</td>"	
												+"<td>"+it.deleteName +"</td>"	
												+"<td>"+it.deleteReason+"</td></tr>");
						        			}
						        		})	
						        	}  
						    	}
							})
						}		
						})
						var deleteList = data.data.rows;
						$('#tbody_4').html("");
		        		$.each(deleteList,function(index,it){
		        			if(it.deleteFlag ==1){
		        				var sta = "";
		        				if(it.status ==1){
		        					sta = "等待调度";
		        				}
		        				if(it.status ==2){
		        					sta = "等待发运";
		        				}
		        				if(it.status ==3){
		        					sta = "在途运载";
		        				}
		        				if(it.status ==4){
		        					sta = "货物引导";
		        				}
		        				if(it.status ==5){
		        					if(it.receipterDate == ""){
		        						sta = "等待回单";
		       						}else{
		       							sta = "等待确认"; 
		       						}
		        				}
		        				if(it.status ==6){
		        					sta = "计费确认";
		        				}	
		        				if(it.status ==7){
		        					sta = "已完成";
		        				}
			       				$('#tbody_4').append("<tr id='"+it.id+"'><td style='text-align: center;'><label class='demo--label'>"
										 +" <input class='demo--checkbox'  type='checkbox' value='"+it.id+"'>"
			                        		 +" <span class='demo--checkboxInput'></span></label></td>"
			                  		+"<td>"+it.projectCode+"</td>"
			 						+"<td> <a href='#'>"+it.orderCode+"</a></td>"	
			 						+"<td>"+it.createDate+"</td>"	
								+"<td>"+sta+"</td>"
								+"<td>"+it.updateDate+"</td>"
								+"<td>"+it.userDispatchName+"</td>"	
								+"<td>"+it.branchGroupName+"</td>"	
								+"<td>"+it.cargoName +"</td>"	
								+"<td>"+it.testIndicators+"</td>"	
								+"<td>"+it.sendCompany+"</td>"	
								+"<td>"+it.exportSumSendNet+"吨</td>"	
								+"<td>"+it.receiptCompany+"</td>"	
								+"<td>"+it.exportSumReceiptNet+"吨</td>"	
								+"<td>"+it.carPlateNumber+"</td>"
								+"<td>"+it.containerNumber1+"</td>"
								+"<td>"+it.containerNumber2+"</td>"
								+"<td>"+it.deleteTime+"</td>"	
								+"<td>"+it.deleteName +"</td>"	
								+"<td>"+it.deleteReason+"</td></tr>");
		        			}
		        		})
					}
				}
			});
		}else{
			$.ajax({
				type: 'post',
				url: '${pageContext.request.contextPath}/business/short/boxManager/byPage/list.do',
				data: {
					page: 1,
					type: type,
					order: search
				},
				cache: false,
				dataType: 'json',
				success: function(data) {
					if(data.status == 200) {
						var boxOrderList = data.data.rows;
						var tbody = "tbody_1";
						if(type == 1) {
							tbody = "tbody_1";
						} else if(type == 2) {
							tbody = "tbody_2";
						} else if(type == 3) {
							tbody = "tbody_3";
						}
						$("#" + tbody + "").html("");
						if(type == 1) {
							$("#page_1").paging({
								pageNo:1, 
								totalPage:data.data.totalPage,
		        				totalSize:data.data.limit,
								callback: function(num) {
										$.ajax({  
							        	type:'post',      
							       		url:'${pageContext.request.contextPath}/business/short/boxManager/byPage/list.do',  
								        data:{page:num,type:1,order:JSON.stringify($('#orderSearchForm').serializeJSON())},
								        cache:false,
								        dataType:'json',
							        	success:function(data){
							        		if(data.status==200){
							        		var boxNormalList = data.data.rows;
							        		$('#tbody_1').html("");
							        		$.each(boxNormalList,function(index,it){
							        			if(it.exceptionStatus ==0 && it.status !=7){
							        				var sta = "";
							        				if(it.status ==1){
							        					sta = "<a href='javascript:void(0)' id='dispatch' onclick='dispatcher("+it.id+")'>等待调度</a>";
							        				}
							        				if(it.status ==2){
							        					sta = "<a href='javascript:void(0)' id='forwarding' onclick='forwardinger("+it.id+")'>等待发运</a>";
							        				}
							        				if(it.status ==3){
							        					sta = "<a href='javascript:void(0)'  id='carry' onclick='carryer("+it.id+")'>在途运载</a>";
							        				}
							        				if(it.status ==4){
							        					sta = "<a href='javascript:void(0)' id='guide' onclick='guideContent("+it.id+")'>货物引导</a>";
							        				}
							        				if(it.status ==5){
							        					if(it.receipterDate == ""){
						        							sta = "<a href='javascript:void(0)' id='waitReceipt' onclick='waitReceipter("+it.id+")'>等待回单</a>";
						        						}else{
						        							sta = "等待确认";
						        						}
							        				}
							        				if(it.status ==6){
							        					sta = "计费确认";
							        				}	
							        				if(it.status ==7){
							        					sta = "已完成";
							        				}
							        				$('#tbody_1').append("<tr id='"+it.id+"'><td style='text-align: center;'><label class='demo--label'>"
															 +" <input class='demo--checkbox' name='WaybillName' type='checkbox' value='"+it.id+"'>"
							                          		 +" <span class='demo--checkboxInput'></span></label></td>"
													+"<td>"+it.projectCode+"</td>"
													+"<td> <a href='javascript:void(0)'>"+it.orderCode+"</a></td> <td>"+it.createDate+"</td>"	
													+"<td>"+sta+"</td>"
													+"<td>"+it.updateDate+"</td>"
													+"<td>"+it.userDispatchName+"</td>"	
													+"<td>"+it.branchGroupName+"</td>"	
													+"<td>"+it.cargoName +"</td>"	
													+"<td>"+it.testIndicators+"</td>"	
													+"<td>"+it.sendCompany+"</td>"	
													+"<td>"+it.exportSumSendNet+"吨</td>"	
													+"<td>"+it.receiptCompany+"</td>"	
													+"<td>"+it.exportSumReceiptNet+"吨</td>"	
													+"<td>"+it.carPlateNumber+"</td>"	
													+"<td>"+it.containerNumber1 +"</td>"	
													+"<td>"+it.containerNumber2+"</td></tr>");
							        			}
							        			
							        		})	
							        	}  
							    	}
								})
							}		
							});
							$('#tbody_1').html("");
							$.each(boxOrderList, function(index, it) {
								if(it.exceptionStatus == 0 && it.status != 7) {
									var sta = "";
									if(it.status == 1) {
										sta = "<a href='javascript:void(0)' id='dispatch' onclick='dispatcher("+it.id+")'>等待调度</a>";
									}
									if(it.status == 2) {
										sta = "<a href='javascript:void(0)' id='forwarding' onclick='forwardinger("+it.id+")'>等待发运</a>";
									}
									if(it.status == 3) {
										sta = "<a href='javascript:void(0)'  id='carry' onclick='carryer("+it.id+")'>在途运载</a>";
									}
									if(it.status == 4) {
										sta = "<a href='javascript:void(0)' id='guide' onclick='guideContent("+it.id+")'>货物引导</a>";
									}
									if(it.status == 5) {
										if(it.receipterDate == ""){
											sta = "<a href='javascript:void(0)' id='waitReceipt' onclick='waitReceipter("+it.id+")'>等待回单</a>";
										}else{
											sta = "等待确认";
										}
									}
									if(it.status == 6) {
										sta = "计费确认";
									}
									if(it.status == 7) {
										sta = "已完成";
									}
									$("#tbody_1").append("<tr id='" + it.id + "'><td style='text-align: center;'><label class='demo--label'>" +
										" <input class='demo--checkbox' name='WaybillName' type='checkbox' value='" + it.id + "'>" +
										" <span class='demo--checkboxInput'></span></label></td>"
										+"<td>"+it.projectCode+"</td>"
				 						+"<td> <a href='#'>"+it.orderCode+"</a></td>"	
				 						+"<td>"+it.createDate+"</td>"	
										+"<td>"+sta+"</td>"+
										"<td>" + it.updateDate + "</td>" +
										"<td>" + it.userDispatchName + "</td>" +
										"<td>" + it.branchGroupName + "</td>" +
										"<td>" + it.cargoName + "</td>" +
										"<td>" + it.testIndicators + "</td>" +
										"<td>" + it.sendCompany + "</td>" +
										"<td>" + it.exportSumSendNet + "吨</td>" +
										"<td>" + it.receiptCompany + "</td>" +
										"<td>" + it.exportSumReceiptNet + "吨</td>" 
										+"<td>"+it.carPlateNumber+"</td>"	
										+"<td>"+it.containerNumber1 +"</td>"	
										+"<td>"+it.containerNumber2+"</td></tr>");
								}
							})
						} else if(type == 2) {
							$("#page_2").paging({
								pageNo:1, 
								totalPage:data.data.totalPage,
		        				totalSize:data.data.limit,
								callback: function(num) {
										$.ajax({  
							        	type:'post',      
							       		url:'${pageContext.request.contextPath}/business/short/boxManager/byPage/list.do',  
								        data:{page:num,type:2,order:JSON.stringify($('#orderSearchForm').serializeJSON())},
								        cache:false,
								        dataType:'json',
							        	success:function(data){
							        		if(data.status==200){
							        		var excList = data.data.rows;
							        		$('#tbody_2').html("");
							        		$.each(excList,function(index,it){
							        			if(it.exceptionStatus ==1){
							        				var sta = "";
							        				if(it.status ==1){
							        					sta = "等待调度";
							        				}
							        				if(it.status ==2){
							        					sta = "等待发运";
							        				}
							        				if(it.status ==3){
							        					sta = "在途运载";
							        				}
							        				if(it.status ==4){
							        					sta = "货物引导";
							        				}
							        				if(it.status ==5){
							        					if(it.receipterDate == ""){
							        						sta = "等待回单";
							       						}else{
							       							sta = "等待确认"; 
							       						}
							        				}
							        				if(it.status ==6){
							        					sta = "计费确认";
							        				}	
							        				if(it.status ==7){
							        					sta = "已完成";
							        				}
							        				$('#tbody_2').append("<tr id='"+it.id+"'><td style='text-align: center;'><label class='demo--label'>"
															 +" <input class='demo--checkbox' name='WaybillName' type='checkbox' value='"+it.id+"'>"
							                          		 +" <span class='demo--checkboxInput'></span></label></td>"
							                   		+"<td>"+it.projectCode+"</td>"
							 						+"<td> <a href='#'>"+it.orderCode+"</a></td>"	
							 						+"<td>"+it.createDate+"</td>"	
							 						+"<td>"+sta+"</td>"
													+"<td>"+it.updateDate+"</td>"
													+"<td>"+it.userDispatchName+"</td>"	
													+"<td>"+it.branchGroupName+"</td>"	
													+"<td>"+it.cargoName +"</td>"	
													+"<td>"+it.testIndicators+"</td>"	
													+"<td>"+it.sendCompany+"</td>"	
													+"<td>"+it.exportSumSendNet+"吨</td>"	
													+"<td>"+it.receiptCompany+"</td>"	
													+"<td>"+it.exportSumReceiptNet+"吨</td>"	
													+"<td>"+it.carPlateNumber+"</td>"	
													+"<td>"+it.containerNumber1 +"</td>"	
													+"<td>"+it.containerNumber2+"</td>"
													+"<td>"+it.exceptionTime+"</td>"
													+"<td>"+it.exceptionReoportName+"</td>"
													+"<td>"+it.exceptionReoportReason+"</td></tr>");
							        			}
							        		})	
							        	}  
							    	}
								})
							}		
							});
							$('#tbody_2').html("");
							$.each(boxOrderList, function(index, it) {
								if(it.exceptionStatus == 1) {
									var sta = "";
									if(it.status ==1){
			        					sta = "等待调度";
			        				}
			        				if(it.status ==2){
			        					sta = "等待发运";
			        				}
			        				if(it.status ==3){
			        					sta = "在途运载";
			        				}
			        				if(it.status ==4){
			        					sta = "货物引导";
			        				}
			        				if(it.status ==5){
			        					if(it.receipterDate == ""){
			        						sta = "等待回单";
			       						}else{
			       							sta = "等待确认"; 
			       						}
			        				}
			        				if(it.status ==6){
			        					sta = "计费确认";
			        				}	
			        				if(it.status ==7){
			        					sta = "已完成";
			        				}
									$('#tbody_2').append("<tr id='" + it.id + "'><td style='text-align: center;'><label class='demo--label'>" +
										" <input class='demo--checkbox' name='WaybillName' type='checkbox' value='" + it.id + "'>" +
										" <span class='demo--checkboxInput'></span></label></td>" +
										"<td>" + it.projectCode + "</td>" +
										"<td> <a href='javascript:void(0)'>" + it.orderCode + "</a></td> "+
										"<td>" + it.createDate + "</td>" +
										"<td>" + sta + "</td>" +
										"<td>" + it.updateDate + "</td>" +
										"<td>" + it.userDispatchName + "</td>" +
										"<td>" + it.branchGroupName + "</td>" +
										"<td>" + it.cargoName + "</td>" +
										"<td>" + it.testIndicators + "</td>" +
										"<td>" + it.sendCompany + "</td>" +
										"<td>" + it.exportSumSendNet + "吨</td>" +
										"<td>" + it.receiptCompany + "</td>" +
										"<td>" + it.exportSumReceiptNet + "吨</td>" 
										+"<td>"+it.carPlateNumber+"</td>"	
										+"<td>"+it.containerNumber1 +"</td>"	
										+"<td>"+it.containerNumber2+"</td>"
										+"<td>"+it.exceptionTime+"</td>"
										+"<td>"+it.exceptionReoportName+"</td>"
										+"<td>"+it.exceptionReoportReason+"</td></tr>");
								}
							})
						} else if(type == 3) {
							$("#page_3").paging({
								pageNo:1, 
								totalPage:data.data.totalPage,
		        				totalSize:data.data.limit,
								callback: function(num) {
										$.ajax({  
							        	type:'post',      
							       		url:'${pageContext.request.contextPath}/business/short/boxManager/byPage/list.do',  
								        data:{page:num,type:3,order:JSON.stringify($('#orderSearchForm').serializeJSON())},
								        cache:false,
								        dataType:'json',
							        	success:function(data){
							        		if(data.status==200){
							        		var boxOrderList = data.data.rows;
							        		$('#tbody_3').html("");
							        		$.each(boxOrderList,function(index,it){
							        			if(it.status ==7){
							        				$('#tbody_3').append("<tr id='"+it.id+"'><td style='text-align: center;'><label class='demo--label'>"
															 +" <input class='demo--checkbox' name='WaybillName' type='checkbox' value='"+it.id+"'>"
							                         		 +" <span class='demo--checkboxInput'></span></label></td>"
							                  		+"<td>"+it.projectCode+"</td>"
													+"<td> <a href='#'>"+it.orderCode+"</a></td>"	
													+"<td>"+it.createDate+"</td>"	
													+"<td>"+it.updateDate+"</td>"
													+"<td>"+it.receipterDate+"</td>"
													+"<td>"+it.userDispatchName+"</td>"	
													+"<td>"+it.branchGroupName+"</td>"	
													+"<td>"+it.cargoName +"</td>"	
													+"<td>"+it.testIndicators+"</td>"	
													+"<td>"+it.sendCompany+"</td>"	
													+"<td>"+it.exportSumSendNet+"吨</td>"	
													+"<td>"+it.receiptCompany+"</td>"	
													+"<td>"+it.exportSumReceiptNet+"吨</td>"	
													+"<td>"+it.carPlateNumber+"</td>"	
													+"<td>"+it.containerNumber1 +"</td>"	
													+"<td>"+it.containerNumber2+"</td></tr>");
							        			}
							        		})	
							        	}  
							    	}
								})
							}		
							});
							$('#tbody_3').html("");
							$.each(boxOrderList, function(index, it) {
								if(it.status ==7){
									$('#tbody_3').append("<tr id='"+it.id+"'><td style='text-align: center;'><label class='demo--label'>" +
											" <input class='demo--checkbox' name='WaybillName' type='checkbox' value='" + it.id + "'>" +
											" <span class='demo--checkboxInput'></span></label></td>" 
											+"<td>"+it.projectCode+"</td>"
											+"<td> <a href='#'>"+it.orderCode+"</a></td>"	
											+"<td>"+it.createDate+"</td>"	
											+"<td>"+it.updateDate+"</td>"
											+"<td>"+it.receipterDate+"</td>"
											+"<td>"+it.userDispatchName+"</td>"	
											+"<td>"+it.branchGroupName+"</td>"	
											+"<td>"+it.cargoName +"</td>"	
											+"<td>"+it.testIndicators+"</td>"	
											+"<td>"+it.sendCompany+"</td>"	
											+"<td>"+it.exportSumSendNet+"吨</td>"	
											+"<td>"+it.receiptCompany+"</td>"	
											+"<td>"+it.exportSumReceiptNet+"吨</td>"	
											+"<td>"+it.carPlateNumber+"</td>"	
											+"<td>"+it.containerNumber1 +"</td>"	
											+"<td>"+it.containerNumber2+"</td></tr>");
								}
							})
						}
					}
				}
			})
		}
	}
	
	//发送短信
	$(function(){
		$("#snedMsgBtn").click(function(){
			var flag = true;
			var idList = new Array(); 
			$.each($('.needLookDetail input:checkbox'), function() {
				if (this.checked) {
					idList.push($(this).val());
				}
			});
			var content = $("#sms_content").val();
			$.ajax({
				type : 'POST',
				url : "${pageContext.request.contextPath}/system/sms/checkBulkBussinss.do",
				data : {"ids":idList.toString()},
				dataType : "json",
				async: false,
				success : function(data) {
					var d = data.data;
					if(d == null || d == ""){

					}else{
						//$(".tips").show();
						$.each(d, function(index,it) {
							$("#noBussinss").append(it.orderCode+",");
						});
						$("#noBussinss").append("业务联系人为空,短信未发送,其余已发送");
					}
					if(d.length == idList.length){
						flag = false;
					}
					if(flag){
						$.ajax({
							type : 'POST',
							url : "${pageContext.request.contextPath}/system/sms/sendMsgOfBulk.do",
							data : {"ids":idList.toString(),"content":content},
							dataType : "json",
							async: false,
							success : function(data) {
								if (data.status != 1) {
									$("#msg").html(data.msg);
									$("#promptIdModal").modal();
								} else {
									//显示没有业务联系人订单编号
									var text = $("#noBussinss").html();
									if(text == "" || text == null){
										$("#messgageModal").modal("hide");
										$("#warnMsg").html(data.msg);
										$("#nullModal").modal();
									}else{
										$("#messgageModal").modal("hide");
										$("#warnMessage").html(text);
										$("#nullModalMsg").modal();
									}
								}
							},
						}); 
					}else{
						$("#messgageModal").modal("hide");
						$("#warnMessage").html("");
						$.each(d, function(index,it) {
							$("#warnMessage").append(it.orderCode+",");
						});
						$("#warnMessage").append("业务联系人为空,短信未发送,请查看");
						$("#nullModalMsg").modal();
					}
				},
			}); 
		});	
	})
	
	function closeAndfresh(){
		$("#nullModalMsg").modal("hide");
		window.location.reload();
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
					<div class="form-group" style="display: none;">
						<label>项目类型：</label>
						<select name="projectType">
							<option></option>
							<option value="0">集装箱</option>
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
						<input name="beginDate" id="d244" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
						<i class="fa fa-calendar-check-o " id="calendar2"></i>
					</div>
					<div class="form-group dataTimes">
						<label>至</label>
						<input id="d244" name='endDate' type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
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
						</select>
					</div>
					<div class="form-group">
						<label>承运车辆：</label>
						<input name='carPlateNumber' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>集装箱号：</label>
						<input name='containerNumber1' type="text" maxlength="30"/>
					</div>
					<div class="form-group" style="display: none;">
						<label>&emsp;&emsp;货场：</label>
						<select></select>
					</div>
					<div class="form-group" style="display: none;">
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
		<!-- 散堆装管理表格 -->
		<div class="container_bottom tableDiv">
			<div class="tabbable" id="tabs-529262">
				<ul class="nav nav-tabs">
					<li class="active" name='type1' >
						<a href="#panel1" data-toggle="tab" onclick="changeType(1)">运单列表</a>
					</li>
					<li name='type2'>
						<a href="#panel2" data-toggle="tab" onclick="changeType(2)">异常运单</a>
					</li>
					<li name='type3'>
						<a href="#panel3" data-toggle="tab" onclick="changeType(3)">历史运单</a>
					</li>
					<li>
						<a href="#panel4" data-toggle="tab" onclick="changeType(4)">运单回收站</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="panel1">
						<div class="domain">
							<div class="tableBg">
							<c:forEach items='${permissions }' var='permission' >
								<c:if test="${permission.code=='createTruckContainerOrder'}">
									<a href="javascript:void(0)" class="exportBtn add" id="waybill"><span>新建</span></a>
								</c:if>
								<c:if test="${permission.code=='exportTruckContainerOrder'}">
									<a href="javascript:void(0)" class="export" id="export1"><span>导出</span></a>
								</c:if>
								<c:if test="${permission.code=='msgByTruckContainerOrder'}">
									<a href="javascript:void(0)" class="exportBtn msgbtn msg" id="msgbtn1"><span>短信告知</span></a>
								</c:if>
							</c:forEach>
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
											<th>收货单位</th>
											<th>收货净重</th>
											<!-- <th>货场</th>
											<th>货位</th> -->
											<th>承运车辆</th>
											<th>集装箱号</th>
											<th>集装箱号</th>
										</tr>
									</thead>
									<tbody id="tbody_1" class="needLookDetail">
									<c:forEach items="${boxOrderList.rows}" var="it">
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
												<a href="javascript:void(0)">${it.orderCode}</a>
											</td>
											<td><fmt:formatDate value="${it.createDate}"
														pattern="yyyy-MM-dd HH:mm" />
											</td>
											<td>
												<c:if test="${it.status ==1}">
														<a href="javascript:void(0)" id="dispatch" onclick="dispatcher(${it.id})">等待调度</a>
													</c:if>
													<c:if test="${it.status ==2}">
														<a href="javascript:void(0)" id="forwarding" onclick="forwardinger(${it.id})">等待发运</a>
													</c:if>
													<c:if test="${it.status ==3}">
															<a href="javascript:void(0)"  id="carry" onclick="carryer(${it.id})">在途运载</a>
													</c:if>
													<c:if test="${it.status ==4}">
														<a href="javascript:void(0)" id="guide" onclick="guideContent(${it.id})">货物引导</a>
													</c:if>
													<c:if test="${it.status ==5}">
														<c:if test="${empty it.receipterDate}">
															<a href="javascript:void(0)" id="waitReceipt" onclick="waitReceipter(${it.id})">等待回单</a>
														</c:if>
														<c:if test="${not empty it.receipterDate}">
															等待确认
														</c:if>
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
											<td>${it.exportSumSendNet}吨</td>
											<td>${it.receiptCompany}</td>
											<td>${it.exportSumReceiptNet}吨</td>
											<%-- <td><c:if test="${it.stepSelectCode==0}">${it.distributionCargoPlace}</c:if>
											<c:if test="${it.stepSelectCode==1}">${it.takeCarogoPlaceName}</c:if>
											</td>
											<td><c:if test="${it.stepSelectCode==0}">${it.distributionCargoSite}</c:if>
											<c:if test="${it.stepSelectCode==1}">${it.takeCargoSiteName}</c:if>
											</td> --%>
											<td>${it.carPlateNumber}</td>
											<td>${it.containerNumber1}</td>
											<td>${it.containerNumber2}</td>
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
							<c:forEach items='${permissions }' var='permission' >
								<c:if test="${permission.code=='exceptionTruckContainerOrder'}">
									<a href="javascript:void(0)" class="exportBtn abnorm"><span>异常处理</span></a>
								</c:if>
							</c:forEach>	
							<c:forEach items='${permissions }' var='permission' >
								<c:if test="${permission.code=='exportExceptionTruckContainerOrder'}">
									<a href="javascript:void(0)" class="export" id="export2"><span>导出</span></a>
								</c:if>
							</c:forEach>
							<c:forEach items='${permissions }' var='permission' >		
								<c:if test="${permission.code=='msgByExceptionTruckContainerOrder'}">
									<a href="javascript:void(0)" class="exportBtn msgbtn msg" id="msgbtn2"><span>短信告知</span></a>
								</c:if>
							</c:forEach>
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
											<!-- <th>货场</th>
											<th>货位</th> -->
											<th>承运车辆</th>
											<th>集装箱号</th>
											<th>集装箱号</th>
											<th>提报时间</th>
											<th>异常提报人</th>
											<th>异常原因</th>
										</tr>
									</thead>
									<tbody id="tbody_2" class="needLookDetail">
										<c:forEach items="${exceptionResult.rows}" var="it">
										<c:if test="${it.exceptionStatus ==1}">
										<tr id="${it.id}">
											<td>
												<label class="demo--label">
													  <input class="demo--checkbox" name="WaybillName" type="checkbox" value="${it.id}">
                                                 <span class="demo--checkboxInput"></span>
	                                          </label>
											</td>
											<td>${it.projectCode}</td>
											<td>
												<a href="javascript:void(0)">${it.orderCode}</a>
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
													   	<c:if test="${empty it.receipterDate}">
															等待回单
														</c:if>
														<c:if test="${not empty it.receipterDate}">
															等待确认
														</c:if>
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
											<td>${it.exportSumSendNet}吨</td>
											<td>${it.receiptCompany}</td>
											<td>${it.exportSumReceiptNet}吨</td>
											<%-- <td><c:if test="${it.stepSelectCode==0}">${it.distributionCargoPlace}</c:if>
											<c:if test="${it.stepSelectCode==1}">${it.takeCarogoPlaceName}</c:if>
											</td>
											<td><c:if test="${it.stepSelectCode==0}">${it.distributionCargoSite}</c:if>
											<c:if test="${it.stepSelectCode==1}">${it.takeCargoSiteName}</c:if>
											</td> --%>
											<td>${it.carPlateNumber}</td>
											<td>${it.containerNumber1}</td>
											<td>${it.containerNumber2}</td>
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
							<c:forEach items='${permissions }' var='permission' >
								<c:if test="${permission.code=='exportHistoryTruckContainerOrder'}">
									<a href="#" class="export" id="export3"><span>导出</span></a>
								</c:if>
								<c:if test="${permission.code=='msgByHistoryTruckContainerOrder'}">
									<a href="#" class="exportBtn msgbtn msg" id="msgbtn3"><span>短信告知</span></a>
								</c:if>
							</c:forEach>
							</div>
							<div class="table-responsive">
								<!--历史运单开始-->
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th> <label class="demo--label">
											     <input class="demo--checkbox"  type="checkbox" >
                                                 <span class="demo--checkboxInput"></span>
	                                          </label></th>
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
											<!-- <th>货场</th>
											<th>货位</th> -->
											<th>承运车辆</th>
											<th>集装箱号</th>
											<th>集装箱号</th>

										</tr>
									</thead>
									<tbody id="tbody_3" class="needLookDetail">
									
									<c:forEach items="${historyResult.rows}" var="it">
										<c:if test="${it.exceptionStatus ==0 and it.status ==7}">
											<tr id="${it.id}">
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
											<td><fmt:formatDate value="${it.receipterDate}"
														pattern="yyyy-MM-dd HH:mm" /></td>
											<td>${it.userDispatchName}</td>
											<td>${it.branchGroupName}</td>
											<td>${it.cargoName}</td>
											<td>${it.testIndicators}</td>
											<td>${it.sendCompany}</td>
											<td>${it.exportSumSendNet}吨</td>
											<td>${it.receiptCompany}</td>
											<td>${it.exportSumReceiptNet}吨</td>
											<%-- <td><c:if test="${it.stepSelectCode==0}">${it.distributionCargoPlace}</c:if>
											<c:if test="${it.stepSelectCode==1}">${it.takeCarogoPlaceName}</c:if>
											</td>
											<td><c:if test="${it.stepSelectCode==0}">${it.distributionCargoSite}</c:if>
											<c:if test="${it.stepSelectCode==1}">${it.takeCargoSiteName}</c:if>
											</td> --%>
											<td>${it.carPlateNumber}</td>
											<td>${it.containerNumber1}</td>
											<td>${it.containerNumber2}</td>
										</tr>
										</c:if>
									</c:forEach>
										
									</tbody>
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
							<c:forEach items='${permissions }' var='permission' >
								<c:if test="${permission.code=='exportRecycleTruckContainerOrder'}">
									<a href="javascript:void(0)" class="export" id="export4"><span>导出</span></a>
								</c:if>
								<c:if test="${permission.code=='msgByRecycleTruckContainerOrder'}">
									<a href="javascript:void(0)" class="exportBtn msgbtn msg" id="msgbtn4"><span>短信告知</span></a>
								</c:if>
							</c:forEach>
							</div>
							<div class="table-responsive">
								<!-- 项目回收站开始-->
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th>
												<label class="demo--label">
											     <input class="demo--checkbox" name="recoveryName"  type="checkbox" >
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
											<!-- <th>货场</th>
											<th>货位</th> -->
											<th>承运车辆</th>
											<th>集装箱号</th>
											<th>集装箱号</th>
											<th>删除时间</th>
											<th>删除人</th>
											<th>删除原因</th>
										</tr>
									</thead>
									<tbody id="tbody_4" class="needLookDetail">
										<c:forEach items="${orderDeleteList.rows}" var="it">
											<tr id="${it.id}">
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
													   	<c:if test="${empty it.receipterDate}">
															等待回单
														</c:if>
														<c:if test="${not empty it.receipterDate}">
															等待确认
														</c:if>
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
											<td>${it.exportSumSendNet}吨</td>
											<td>${it.receiptCompany}</td>
											<td>${it.exportSumReceiptNet}吨</td>
											<%-- <td><c:if test="${it.stepSelectCode==0}">${it.distributionCargoPlace}</c:if>
											<c:if test="${it.stepSelectCode==1}">${it.takeCarogoPlaceName}</c:if>
											</td>
											<td><c:if test="${it.stepSelectCode==0}">${it.distributionCargoSite}</c:if>
											<c:if test="${it.stepSelectCode==1}">${it.takeCargoSiteName}</c:if>
											</td> --%>
											<td>${it.carPlateNumber}</td>
											<td>${it.containerNumber1}</td>
											<td>${it.containerNumber2}</td>
											<td>
											<fmt:formatDate value="${it.deleteTime}"
														pattern="yyyy-MM-dd HH:mm" /></td>
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
		<div class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" aria-hidden="true" id="lookModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" class="myModalLabel">
							运单详情
						</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					 <div id="appendDiv" class="modal-body">
						<!--<div class="project_info">
							<h5>项目信息</h5>
							<ul class="detailsUl">
								<li><label>项目编号：</label><span>J00001</span></li>
								<li><label>项目类型：</label><span>集装箱</span></li>
								<li><label>分支机构：</label><span>合肥物流分支</span></li>
								<li><label>调度员：</label><span>李三海</span></li>
							</ul>
						</div>
						<hr />
						<div class="project_info">
							<h5>运单信息</h5>
							<ul class="detailsUl">
								<li><label>运单编号：</label><span>WTE1289887</span></li>
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
								<li><label>集装箱号：</label><span>GTB 1234567</span></li>
								<li><label>净重：</label><span>100吨</span></li>
								<li><label>集装箱号：</label><span>GTB 1234567</span></li>
								<li><label>净重：</label><span>100吨</span></li>
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
						</div>-->
					</div> 
				</div>
			</div>
		</div>
		<!--运单新增模态框-->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" data-backdrop="static" aria-hidden="true" id="myModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<span data-dismiss="modal" aria-label="Close"></span>
						<h4 class="modal-title" class="myModalLabel">
							新建运单
						</h4>
					</div>
					<div class="modal-body" id="newModalBody">
						<form id="addId">
							<!--	项目信息-->
							<div class="project_info">
								<h5>项目信息<i class='requireds'>*</i></h5> <h5 style="color:red;" id='msgH5'></h5>
								<div class="form-inline">
									<div class="form-group">
										<label>项目编号：</label>
										<input  name="projectCode" type="hidden" />
										<select id="select_project" onchange="exchangeProject()" name="projectId"></select>
									</div>
									<div class="form-group">
										<label>项目类型：</label>
										<input id="projectTypeName" name="projectTypeName" type="text" unselectable="on" class="inputbg" readonly="readonly" />
										<input id="projectType" name="projectType" type="hidden" /> 
										<input id="transportType" name="transportType" type="hidden" />
									</div>
									<input type="hidden" name="model" />
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
										<input  type='text'id="branchGroupName" unselectable="on" class="inputbg" readonly="readonly" name="branchGroupName"/>
										<input id="branchGroupId" type="hidden" name="branchId" />
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
											<label id="changeNameByStep1">发货单位：</label>
											<input type='text' id="sendCompany" unselectable="on" class="inputbg" readonly="readonly" name="sendCompany"/>
											<input id="sendCompanyId" type="hidden" name="sendCompanyId" />
										</div>
										<div class="form-group">
											<label>&emsp;取货地：</label>
											<input type='text' id="" unselectable="on" class="inputbg" readonly="readonly" name="pickupPlace"/>
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
											<label id="changeNameByStep2">收货站点：</label>
											<input  id="receiptCompanyId" name="receiptCompanyId" type="hidden" />
											<input type='text' unselectable="on" class="inputbg" readonly="readonly" name="receiptCompany"/>
										</div>
										<div class="form-group">
											<label>&emsp;运抵地：</label>
											<input type="text" value="" unselectable="on" readonly="" class="inputbg" name="arrivePlace">
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
										<label>计价单位：</label>
										<input type="text" unselectable="on" class="inputbg" readonly="readonly" id="valuationUnitTypeName"/>
										<input type="hidden" name="valuationUnitType"/>
									</div>

								</div>
							</div>
							<hr />
							<!--车辆信息-->
							<div class="project_info">
								<h5>车辆信息<i class='requireds'>*</i></h5>
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
								<!-- <input type="hidden" id="set_selectVal1" name="containerNumber1" >
								<input type="hidden" id="set_selectVal2" name="containerNumber2"> -->
								<input type="hidden" id="forwardingSiteId" name="forwardingSiteId">
								<input type="hidden" id="deductionRate" name="deductionRate">
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
									<div class="form-group">
										<label>集装箱号：</label>
										<!-- <select id="set_container_one" onchange="set_selectValue1()" name="containerNumber1Id"></select> -->
										<select id="set_containerNumber1" name="set_containerNumber1" onchange="jzxNum(1)"></select>
										<input type="hidden" id="containerNumber1" name="containerNumber1">
										
									</div>
									<div class="form-group">
										<label>集装箱号：</label>
										<!-- <select id="set_container_two" onchange="set_selectValue2()" name="containeNumber2Id"></select> -->
										<select id="set_containerNumber2" name="set_containerNumber2" onchange="jzxNum(2)"></select>
										<input type="hidden" id="containerNumber2" name="containerNumber2">
									</div>

								</div>
							</div>
							<hr />
							<!--   费用信息-->
							<div class="project_info">
								<h5>费用信息<i class='requireds'>*</i></h5>
								<div class="form-inline">
									<div class="form-group">
										<label>短驳运费：</label>
										<input type="text" unselectable="on" class="inputbg" readonly="readonly" name="shortBargeCost"/>
									</div>
									<div class="form-group">
										<label>折损单价：</label>
										<input type="text" unselectable="on" class="inputbg" readonly="readonly" name="deductionPrice"/>
									</div>
									<div class="form-group">
										<label>&emsp;&emsp;补贴：</label>
										<input type="text" maxLength="30" name="subsidy"/><span class='noNeed'>(可不填)</span>
									</div>
								</div>
							</div>
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
									<input type="button"  id='createOrderBtn' class="btn sureBtn" value="确认" onclick="orderAdds()" />
									<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!--等待发运模态框-->
		<div class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" aria-hidden="true" id="waitModal">
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
								<h5>发运信息<i class='requireds'>*</i></h5>
								<div class="form-inline ">
									<div class="form-group">
										<label class="pLeft">发货毛重：</label>
										<input type="text" maxlength="30" id="sendGross" name="sendGross" onblur="accturelyWeight()"/>
										<input type="hidden" maxlength="30" id="sendGrossHide" />
									</div>
									<div class="form-group">
										<label >发货皮重：</label>
										<input type="text" maxlength="30" id="sendTare" name="sendTare" onblur="accturelyWeight()"/>
									</div>
									<div class="form-group"> 
										<label class="pLeft"><tb id="firContainer" style="color:#0477ed"></tb>发货净重：</label>
										<input type="text" maxlength="30" id="containerOneSendNet" name="containerOneSendNet" onblur="sumTwoAccturelyWeight()"/>
									</div>
									<div class="form-group">
										<label class="pLeft"><tb id="secContainer" style="color:#0477ed"></tb>发货净重：</label>
										<input type="text" maxlength="30" id="containerTwoSendNet" name="containerTwoSendNet" onblur="sumTwoAccturelyWeight()"/>
									</div>
									<div class="form-group">
										<label>化验指标：</label>
										<input type="text" maxlength="30" name="testIndicators"/>
									</div>
									<div class="form-group" id="jianshuByUnit" style="display: none;">
										<label>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;件数：</label>
										<!-- <input type="text" maxlength="30" name="pieceNumber" onkeyup="value=value.replace(/[^\d.]/g,'')"/> -->
									</div>
								</div>
							</div>
							<hr />
							<div class="project_info">
							
								<h5>发运单上传<i class='requireds'>*</i></h5>
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
        					<input class="demo--radio" type="radio" name="IsAgree" value="1">
        					<span class="demo--radioInput"></span>是
    						</label>
								<label class="demo--label">
        					<input class="demo--radio" type="radio" name="IsAgree" value="0" checked="checked">
       						<span class="demo--radioInput"></span>否
    						</label>
							</div>
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
									<!-- <button type="button" class="btn sureBtn" >确定</button> -->
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
		<div class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" aria-hidden="true" id="receiptModal">
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
							<div class="project_info" id="receiveBodyModal">
								<h5>发运信息<i class='requireds'>*</i></h5>
								<div class="form-inline ">
									<div class="form-group">
										<label class="pLeft">收货毛重：</label>
										<input type="text" maxlength="30" id="receiptGross" name="receiptGross" onblur="receiveAccturelyWeight()"/>
										<input type="hidden" maxlength="30" id="receiveGrossHide" />
									</div>
									<div class="form-group">
										<label >收货皮重：</label>
										<input type="text" maxlength="30" id="receiptTare" name="receiptTare" onblur="receiveAccturelyWeight()"/>
									</div>
									<div class="form-group">
										<label class="pLeft"><tb id="receipt_1" style="color:#0477ed"></tb>收货净重：</label>
										<input type="text" maxlength="30" id="containerOneReceiptNet" name="containerOneReceiptNet" onblur="receiveSumTwoAccturelyWeight()"/>
									</div>
								</div>
								<div class="form-inline">
									<div class="form-group">
										<label class="pLeft"><tb id="receipt_2" style="color:#0477ed"></tb>收货净重：</label>
										<input type="text" maxlength="30" id="containerTwoReceiptNet" name="containerTwoReceiptNet" onblur="receiveSumTwoAccturelyWeight()"/>
									</div>
								</div>
							</div>
							<hr />
							<div class="project_info">
								<h5>到货单上传<i class='requireds'>*</i></h5>
								<div id="img_div_arrive" style="display: inline-block;"><img  id="clickImgShow" onclick="clickImgArrive(this)" src="${pageContext.request.contextPath}/img/updownBg.png" /> </div>
								<input type="file" onchange="imgUpload(this)" style="display:none;" >
								<input id="add_up_freightYardImg2" type="hidden" name="img"/>
								<input type="hidden" id="imgType" name="imgType"/>
								<div id="img_div2" style="display: inline-block;">
									<img id="forward_add_img2" width="150px" height="150px"  src="" />
								</div>
							<!-- 	<ul class="updownUl"> $(param).parent('div').children('input').click(); 
									<li></li>
								</ul> -->
							</div>
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn" id="hiddenDivByStstus">
									<button id="setGrayButton" type="button" class="btn sureBtn" onclick="receipterFuns()">确定</button>
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
								<!-- dispatchContent
									<li><label>申请项目：</label><span>J00001</span></li>
									<li><label>申请人：</label><span>王大锤</span></li>
									<li><label>联系方式：</label><span>18855451245</span></li>
									<li><label>承运车辆：</label><span>皖A123456</span></li>
									<li><label>货品名称：</label><span>煤炭</span></li> -->
								</ul>
								<input type="hidden" id="dispatchHiddenProject" >
								<input type="hidden" id="dispatchHiddenStepSelect" >
								<input type="hidden" id="dispatchHiddenSendId" >
								<input type="hidden" id="dispatchHiddenReceiveId" >
							</div>
							<hr />
							<div class="project_info">
								<h5>承运信息<i class='requireds'>*</i></h5>
								<div class="form-inline ">
									<div class="form-group">
										<label>集装箱号：</label>
										<select id="dispatch_containerNumber1" name="dispatch_containerNumber1" onchange="dispatchJzxNum(1)"></select>
										<input type="hidden" id="selectType1" name="containerNumber1">
									</div>
									<div class="form-group">
										<label>集装箱号：</label>
										<!-- <select id="selectType2" name="containerNumber2Id" onchange="selectValue2()"></select> -->
										<select id="dispatch_containerNumber2" name="dispatch_containerNumber2" onchange="dispatchJzxNum(2)"></select>
										<input type="hidden" id="selectType2" name="containerNumber2">
									</div>
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

								<div class="project_info">
									<h5>调度审核</h5> 是否同意：
									<label class="demo--label">
        						<input class="demo--radio" type="radio" name="isAgree" checked="checked" id="checkboxagree" value="1">
        						<span class="demo--radioInput"></span>是
    							</label>
									<label class="demo--label"><!-- demo-radio -->
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
									<!-- <button type="button" class="btn sureBtn">确定</button> -->
									<input type="button"  class="btn sureBtn" value="确认" onclick="submits()" />
									<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!--货物引导模态框-->
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
								<ul id="guideContentId2" class="waitUl">
									<!-- <li><label>到货皮重：</label><span>2万吨</span></li>
									<li><label>到货毛重：</label><span>2万吨</span></li>
									<li><label>WTB 1234567到货净重：</label><span>2万吨</span></li>
									<li><label>WTB 1234567到货净重：</label><span>2万吨</span></li> -->
								</ul>
							</div>
							<hr />
							<div class="project_info">
								<h5>发运单上传<i class='requireds'>*</i></h5>
								<ul class="guideUl">
									<li><img id="guide_img" src="" width="150px" height="150px"/></li>
								</ul>
							</div>
							<hr />
							<div class="project_info">	
								<h5>分配货场货位<i class='requireds'>*</i></h5>
								<div class="form-inline guideForm">
									<div class="form-group">
										<label>货场选择：</label>
										<select id="select_freight_guide" onchange="cargoLoExchange()" ></select>
									</div>
									<div class="form-group">
										<label>货位选择：</label>
										<select id="select_cargo_guide" onchange="cargoGuidExchange()"></select>
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
		<!--在途运载模态框-->
		<div class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" aria-hidden="true" id="carryModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" class="myModalLabel">
							在途运载
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
									<!-- <li><label>发货皮重：</label><span>2万吨</span></li>
									<li><label>发货毛重：</label><span>2万吨</span></li>
									<li><label>WTB 1234567发货净重：</label><span>2万吨</span></li>
									<li><label>WTB 1234567发货净重：</label><span>2万吨</span></li> -->
								</ul>
							</div>
							<hr />
							<div class="project_info">
								<h5>发运运单<i class='requireds'>*</i></h5>
								<ul class="guideUl">
									<li><img id="carry_img" src="" width="150px" height="150px"/></li>
								</ul>
							</div>
							<hr />
							<div class="project_info">
								<h5>确认到货</h5> 是否到货：
								<label class="demo--label">
        					<input class="demo--radio" type="radio" name="isAgree"  checked="checked" value="1">
        					<span class="demo--radioInput"></span>是
    						</label>
								<label class="demo--label">
        					<input class="demo--radio" type="radio" name="isAgree" value="0">
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
		
		<div class="modal fade promptModal" tabindex="-1" role="dialog" id="promptIdModal">
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
		
		<div class="modal fade promptModal" tabindex="-1" role="dialog" id="promptIdModalNof">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body" style="text-align: center;font-size: 24px;color: #04A9ED;">
						<p id="msgNof"></p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn sureBtn" id="sure_msgNof">
							确定</button>
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
							<span>选择了<b id="">13</b>条记录，确认导出全部数据？</span>
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
		
		<!-- 回收站还原提醒模态框 -->
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
		
		<!-- 短信操作后提醒模态框 -->
		<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="nullModalMsg" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="body-content">
							<p id="warnMessage"></p>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" data-dismiss="modal" onclick="closeAndfresh()">确定</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--短信告知模态框-->
			<div class="modal fade " tabindex="-1" role="dialog" id="messgageModal" data-backdrop="static">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">短信告知</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="msgForm">
							<div class="form-inline">
								<label class="v-top">短信内容：</label>
								<textarea  id="sms_content" type="text" class="row3 remarkInput" maxlength="100"></textarea>
								<p>已选择<b id="chooseNum"></b>人</p>
								<p class="tips"><span id="noBussinss"></span></p>
							</div>
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
									<button type="button" class="btn sureBtn" id="snedMsgBtn">确定</button>
									<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 查看大图 -->
		<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="lookImgs" data-backdrop="static" style="z-index: 9999">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">查看图片</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body" style="text-align: center;">
						<img id="look_imgs" src="" class="img-responsive" style="width:100%;height: 100%;" />
					</div>
				</div>
			</div>
		</div>
		
		
	</body>

<script type="text/javascript">
//分页
$("#page_1").paging({
	pageNo:1, 
	totalPage: ${boxOrderList.totalPage},
	totalSize:${boxOrderList.limit},
	callback: function(num) {
			$.ajax({  
        	type:'post',      
       		url:'${pageContext.request.contextPath}/business/short/boxManager/byPage/list.do',  
	        data:{page:num,type:1,order:JSON.stringify($('#orderSearchForm').serializeJSON())},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
        		var boxOrderList = data.data.rows;
        		$('#tbody_1').html("");
        		$.each(boxOrderList,function(index,it){
        			if(it.exceptionStatus ==0 && it.status !=7){
        				var sta = "";
        				if(it.status ==1){
        					sta = "<a href='javascript:void(0)' id='dispatch' onclick='dispatcher("+it.id+")'>等待调度</a>";
        				}
        				if(it.status ==2){
        					sta = "<a href='javascript:void(0)' id='forwarding' onclick='forwardinger("+it.id+")'>等待发运</a>";
        				}
        				if(it.status ==3){
        					sta = "<a href='javascript:void(0)'  id='carry' onclick='carryer("+it.id+")'>在途运载</a>";
        				}
        				if(it.status ==4){
        					sta = "<a href='javascript:void(0)' id='guide' onclick='guideContent("+it.id+")'>货物引导</a>";
        				}
        				if(it.status ==5){
        					if(it.receipterDate == ""){
        						sta = "<a href='javascript:void(0)' id='waitReceipt' onclick='waitReceipter("+it.id+")'>等待回单</a>";
       						}else{
       							sta = "等待确认";
       						}
        				}
        				if(it.status ==6){
        					sta = "计费确认";
        				}	
        				if(it.status ==7){
        					sta = "已完成";
        				}
        				$('#tbody_1').append("<tr id='"+it.id+"'><td style='text-align: center;'><label class='demo--label' >"
								 +" <input class='demo--checkbox' name='WaybillName' type='checkbox' value='"+it.id+"'>"
                          		 +" <span class='demo--checkboxInput'></span></label></td>"
						+"<td>"+it.projectCode+"</td>"
						+"<td> <a href='javascript:void(0)'>"+it.orderCode+"</a></td> <td>"+it.createDate+"</td>"	
						+"<td>"+sta+"</td>"
						+"<td>"+it.updateDate+"</td>"
						+"<td>"+it.userDispatchName+"</td>"	
						+"<td>"+it.branchGroupName+"</td>"	
						+"<td>"+it.cargoName +"</td>"	
						+"<td>"+it.testIndicators+"</td>"	
						+"<td>"+it.sendCompany+"</td>"	
						+"<td>"+it.exportSumSendNet+"吨</td>"	
						+"<td>"+it.receiptCompany+"</td>"	
						+"<td>"+it.exportSumReceiptNet+"吨</td>"	
						/* +"<td name='resaonCargo"+index+"'></td>"
			            +"<td name='resaonSite"+index+"'></td>" */
						+"<td>"+it.carPlateNumber+"</td>"	
						+"<td>"+it.containerNumber1 +"</td>"	
						+"<td>"+it.containerNumber2+"</td></tr>");
        				/* $("td[name='resaonCargo"+index+"']").html("");
        				$("td[name='resaonSite"+index+"']").html("");
        				if(it.stepSelectCode==0){ 
        					$("td[name='resaonCargo"+index+"']").append(it.distributionCargoPlace); 
        					$("td[name='resaonSite"+index+"']").append(it.distributionCargoSite); 
        				}else if(it.stepSelectCode==1){
        					$("td[name='resaonCargo"+index+"']").append(it.takeCarogoPlaceName); 
        					$("td[name='resaonSite"+index+"']").append(it.takeCargoSiteName);
        				} */
        			}
        			
        		})	
        	}  
    	}
	})
}		
})
</script>

<script type="text/javascript">
//分页
$("#page_2").paging({
	pageNo:1, 
	totalPage: ${exceptionResult.totalPage},
	totalSize:${exceptionResult.limit},
	callback: function(num) {
			$.ajax({  
        	type:'post',      
       		url:'${pageContext.request.contextPath}/business/short/boxManager/byPage/list.do',  
	        data:{page:num,type:2,order:JSON.stringify($('#orderSearchForm').serializeJSON())},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
        		var boxOrderList = data.data.rows;
        		$('#tbody_2').html("");
        		$.each(boxOrderList,function(index,it){
        			if(it.exceptionStatus ==1){
        				var sta = "";
        				if(it.status ==1){
        					sta = "等待调度";
        				}
        				if(it.status ==2){
        					sta = "等待发运";
        				}
        				if(it.status ==3){
        					sta = "在途运载";
        				}
        				if(it.status ==4){
        					sta = "货物引导";
        				}
        				if(it.status ==5){
        					if(it.receipterDate == ""){
        						sta = "等待回单";
       						}else{
       							sta = "等待确认"; 
       						}
        				}
        				if(it.status ==6){
        					sta = "计费确认";
        				}	
        				if(it.status ==7){
        					sta = "已完成";
        				}
        				$('#tbody_2').append("<tr id='"+it.id+"'><td style='text-align: center;'><label class='demo--label'>"
								 +" <input class='demo--checkbox' name='WaybillName' type='checkbox' value='"+it.id+"'>"
                          		 +" <span class='demo--checkboxInput'></span></label></td>"
                   		+"<td>"+it.projectCode+"</td>"
 						+"<td> <a href='#'>"+it.orderCode+"</a></td>"	
 						+"<td>"+it.createDate+"</td>"	
 						+"<td>"+sta+"</td>"
						+"<td>"+it.updateDate+"</td>"
						+"<td>"+it.userDispatchName+"</td>"	
						+"<td>"+it.branchGroupName+"</td>"	
						+"<td>"+it.cargoName +"</td>"	
						+"<td>"+it.testIndicators+"</td>"	
						+"<td>"+it.sendCompany+"</td>"	
						+"<td>"+it.exportSumSendNet+"吨</td>"	
						+"<td>"+it.receiptCompany+"</td>"	
						+"<td>"+it.exportSumReceiptNet+"吨</td>"	
						+"<td>"+it.carPlateNumber+"</td>"	
						+"<td>"+it.containerNumber1 +"</td>"	
						+"<td>"+it.containerNumber2+"</td>"
						+"<td>"+it.exceptionTime+"</td>"
						+"<td>"+it.exceptionReoportName+"</td>"
						+"<td>"+it.exceptionReoportReason+"</td></tr>");
        			}
        		})	
        	}  
    	}
	})
}		
})
</script>
<script type="text/javascript">
//分页
$("#page_3").paging({
	pageNo:1, 
	totalPage: ${historyResult.totalPage},
	totalSize:${historyResult.limit},
	callback: function(num) {
			$.ajax({  
        	type:'post',      
       		url:'${pageContext.request.contextPath}/business/short/boxManager/byPage/list.do',  
	        data:{page:num,type:3,order:JSON.stringify($('#orderSearchForm').serializeJSON())},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
        		var boxOrderList = data.data.rows;
        		$('#tbody_3').html("");
        		$.each(boxOrderList,function(index,it){
        			if(it.status ==7){
        				$('#tbody_3').append("<tr id='"+it.id+"'><td style='text-align: center;'><label class='demo--label'>"
								 +" <input class='demo--checkbox' name='WaybillName' type='checkbox' value='"+it.id+"'>"
                         		 +" <span class='demo--checkboxInput'></span></label></td>"
                  		+"<td>"+it.projectCode+"</td>"
						+"<td> <a href='#'>"+it.orderCode+"</a></td>"	
						+"<td>"+it.createDate+"</td>"	
						+"<td>"+it.updateDate+"</td>"
						+"<td>"+it.receipterDate+"</td>"
						+"<td>"+it.userDispatchName+"</td>"	
						+"<td>"+it.branchGroupName+"</td>"	
						+"<td>"+it.cargoName +"</td>"	
						+"<td>"+it.testIndicators+"</td>"	
						+"<td>"+it.sendCompany+"</td>"	
						+"<td>"+it.exportSumSendNet+"吨</td>"	
						+"<td>"+it.receiptCompany+"</td>"	
						+"<td>"+it.exportSumReceiptNet+"吨</td>"	
						+"<td>"+it.carPlateNumber+"</td>"	
						+"<td>"+it.containerNumber1 +"</td>"	
						+"<td>"+it.containerNumber2+"</td>");
        			}
        		})	
        	}  
    	}
	})
}		
})



//分页
$("#page_4").paging({
	pageNo:1, 
	totalPage: ${orderDeleteList.totalPage},
	totalSize:${orderDeleteList.limit},
	callback: function(num) {
			$.ajax({  
        	type:'post',      
       		url:'${pageContext.request.contextPath}/business/short/boxManager/delete/byPage/list.do',  
	        data:{page:num,order:JSON.stringify($('#orderSearchForm').serializeJSON())},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
        		var boxOrderList = data.data.rows;
        		$('#tbody_4').html("");
        		$.each(boxOrderList,function(index,it){
        			if(it.deleteFlag ==1){
        				var sta = "";
        				if(it.status ==1){
        					sta = "等待调度";
        				}
        				if(it.status ==2){
        					sta = "等待发运";
        				}
        				if(it.status ==3){
        					sta = "在途运载";
        				}
        				if(it.status ==4){
        					sta = "货物引导";
        				}
        				if(it.status ==5){
        					if(it.receipterDate == ""){
        						sta = "等待回单";
       						}else{
       							sta = "等待确认"; 
       						}
        				}
        				if(it.status ==6){
        					sta = "计费确认";
        				}	
        				if(it.status ==7){
        					sta = "已完成";
        				}
	       				$('#tbody_4').append("<tr id='"+it.id+"'><td style='text-align: center;'><label class='demo--label'>"
								 +" <input class='demo--checkbox'  type='checkbox' value='"+it.id+"'>"
	                        		 +" <span class='demo--checkboxInput'></span></label></td>"
	                  		+"<td>"+it.projectCode+"</td>"
	 						+"<td> <a href='#'>"+it.orderCode+"</a></td>"	
	 						+"<td>"+it.createDate+"</td>"	
						+"<td>"+sta+"</td>"
						+"<td>"+it.updateDate+"</td>"
						+"<td>"+it.userDispatchName+"</td>"	
						+"<td>"+it.branchGroupName+"</td>"	
						+"<td>"+it.cargoName +"</td>"	
						+"<td>"+it.testIndicators+"</td>"	
						+"<td>"+it.sendCompany+"</td>"	
						+"<td>"+it.exportSumSendNet+"吨</td>"	
						+"<td>"+it.receiptCompany+"</td>"	
						+"<td>"+it.exportSumReceiptNet+"吨</td>"	
						+"<td>"+it.carPlateNumber+"</td>"
						+"<td>"+it.containerNumber1+"</td>"
						+"<td>"+it.containerNumber2+"</td>"
						+"<td>"+it.deleteTime+"</td>"	
						+"<td>"+it.deleteName +"</td>"	
						+"<td>"+it.deleteReason+"</td>");
        			}
        		})	
        	}  
    	}
	})
}		
})
</script>


</html>