<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>散装箱管理</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/business/fireManagement/fireTrainCbulkLoading.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap-datetimepicker.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/public.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap-datetimepicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/business/fireManagement/fireTrainCbulkLoading.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/util.js"></script>
		<!--分页-->
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.serializejson.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js" ></script>
		<link href="${pageContext.request.contextPath}/static/lib/select2/css/select2.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/static/lib/select2/css/select2-bootstrap.css" rel="stylesheet"/>
		<script src="${pageContext.request.contextPath}/static/lib/common/select.js"></script>
		<script src="${pageContext.request.contextPath}/static/lib/select2/js/select2.full.js"></script>
		<script src="${pageContext.request.contextPath}/static/lib/select2/js/i18n/zh-CN.js"></script>
		<script src="${pageContext.request.contextPath}/js/baseData.js"></script>
		<script src="${pageContext.request.contextPath}/static/js/tips.js"></script>
		<script src="${pageContext.request.contextPath}/static/js/common.js"></script>
		<script src="${pageContext.request.contextPath}/static/lib/jquery/fd/js/jquery.fdselect2.js"></script>
		<link href="${pageContext.request.contextPath}/css/page/page.css" rel="stylesheet"/>
	
	<script type="text/javascript">
	
		 document.onkeydown = function () {
	         if (window.event && window.event.keyCode == 13) {
	             window.event.returnValue = false;
	         }
	     }
		 
		  function restrictLength(param){
			 var num=$(param).text().length;
				/* 	if (window.event && num >= 7) {
		            window.event.returnValue = false;
		        }  */
			 if (num >= 7) {
				var l = $(param).text().slice(0,7);
				$(param).text(l);
				//setCaretPosition(param,7);
		     } 
		 } 
		 
		// 设置光标位置
		  function setCaretPosition(ctrl, pos){
		      if(ctrl.setSelectionRange)
		      {
		          ctrl.focus();
		          ctrl.setSelectionRange(pos,pos);
		      }
		      else if (ctrl.createTextRange) {
		          var range = ctrl.createTextRange();
		          range.collapse(true);
		          range.moveEnd('character', pos);
		          range.moveStart('character', pos);
		          range.select();
		      }
		  }
		
		 function sumWeight(param){
			 /* var num=$(param).text();
			 var sumNum = parseFloat(num);
			 var oldWeight = $("#entruckWeight").val();
			 if($("#entruckWeight").val() == ""){
				 oldWeight = 0;
			 }
			 var sumOldWeight = parseFloat(oldWeight);
			 var newWeight = FloatAdd(sumNum,sumOldWeight);
			 $("#entruckWeight").val(newWeight); */
		 }
		 
		 function entruckWeightLength(obj){
			  /* var g =  /^\d+(?:\.\d{1,1})?$/; 
			  //var g =  /^[1-9]\d*(\.\d{1,1})?$/; 
			  var l = $(param).val();
			  if(l.length>2){
				  $(param).val($(param).val().slice(0,2));
			  } */
			   obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
			    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
			    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
			    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d).*$/,'$1$2.$3');//只能输入两个小数  
			    if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
			        obj.value= parseFloat(obj.value.slice(0,2)); 
			    } 
			    if(obj.value.indexOf(".")> 2 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
			        obj.value= parseFloat(""); 
			    } 
		  }
		 
		 function sumWeight2(param){
			 //var num=$(param).text();
			 var sumNum = parseFloat(param);
			 var oldWeight = $("#entruckWeight").val();
			 if($("#entruckWeight").val() == "" || $("#entruckWeight").val() == null){
				 oldWeight = 0;
			 }
			 var sumOldWeight = parseFloat(oldWeight);
			 var newWeight = FloatAdd(sumNum,sumOldWeight);
			 $("#entruckWeight").val(newWeight);
		 }
		 
		 //浮点数加法运算
		 function FloatAdd(arg1,arg2){
		     var r1,r2,m;
		     try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
		     try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
		     m=Math.pow(10,Math.max(r1,r2));
		     return (arg1*m+arg2*m)/m;
		 }
		 
			function addTrainOrder(){
				var pleaseCarNum = $("#pleaseCarNum").val();
				if(pleaseCarNum == "" || pleaseCarNum == null){
					$("#errorMsg").html("请车数不能为空");
					$("#promptIdModal").modal();
					return;
				}
				var estimateWeight = $("#estimateWeight").val();
				if(estimateWeight == "" || estimateWeight == null){
					$("#errorMsg").html("预载重不能为空");
					$("#promptIdModal").modal();
					return;
				}
				var estimateWeight = $("#estimateWeight").val();
				var kucun = $("#kucun").val();
				if(kucun == 0){
					$("#errorMsg").html("库存为0，无法新建");
					$("#promptIdModal").modal();
					return;
				}else{
					/* if(parseInt(estimateWeight)>parseInt(kucun)){
						$("#errorMsg").html("预载重不能大于库存");
						$("#promptIdModal").modal();
						return;
					} */
				}
				var chooseAdvanceChargeAccount = $("#chooseAdvanceChargeAccount").val();
				if(chooseAdvanceChargeAccount == ""){
					$("#errorMsg").html("请选择有效的预付款账户");
					$("#promptIdModal").modal();
					return;
				}
				/* var compareCost = $("#estimateCost").val();
				var advanceCharge = $("#advanceCharge").val();
				if(parseInt(compareCost)>parseInt(advanceCharge)){
					$("#errorMsg").html("预计费用不能大于账户款余额");
					$("#promptIdModal").modal();
					return;
				} */
				$.ajax({
					type : "POST",
					url : "${pageContext.request.contextPath}/fireTrainCbulkLoading/addTrainOrder.do",
					dataType : 'json',
					data : $("#addId").serialize(),
					success : function(data) {
						if (data.status != 200) {
							$("#errorMsg").html(data.msg);
							$("#promptIdModal").modal();
						} else {
							$("#warnMsg").html("操作成功");
							$("#nullModal").modal();
						}
					}
				});
			}
			
			function closeDRemindModal(){
				$("#deletRemindModal").modal("hide");
			}
			function closeDNullModal(){
				$("#deletnullModal").modal("hide");
			}
			
			//删除运单
			function delsTrainOrder(){
				var id = $("#operatorTable input:checkbox[type='checkbox']:checked").val();
				if(id==null || id==""){
					return;
				}
				var reason = $("#delete_reason").val();
				$.ajax({
					type : "POST",
					url : "${pageContext.request.contextPath}/fireTrainCbulkLoading/deleteTrainOrderByParam.do",
					dataType : 'json',
					data : {
						"id" : id,
						"reason":reason
					},
					success : function(data) {
						if (data.status != 200) {
							$("#errorMsg").html(data.msg);
							$("#promptIdModal").modal();
						} else {
							$("#warnMsg").html("操作成功");
							$("#nullModal").modal();
						}
					}
				});
			}
			
			//获取项目详情
			function exchangeProject(){
				//清空文本框的值
				$("#newModalBody input[name='projectTypeName']").val("");	
				$("#newModalBody input[name='projectType']").val("");	
				$("#newModalBody input[name='projectId']").val("");	
				$("#newModalBody input[name='projectCode']").val("");	
				$("#newModalBody input[name='branchId']").val("");	
				$("#newModalBody input[name='branchName']").val("");	
				$("#newModalBody input[name='beginSite']").val("");	
				$("#newModalBody input[name='beginPlace']").val("");	
				$("#newModalBody input[name='endSite']").val("");			
				$("#newModalBody input[name='endPlace']").val("");
				$("#newModalBody input[name='cargoName']").val("");	
				$("#newModalBody input[name='cargoSpecifications']").val("");	
				$("#newModalBody input[name='advanceChargeAccount']").val("");	
				$("#newModalBody input[name='advanceCharge']").val("");	
				$("#newModalBody input[name='hideEstimateCost']").val("");	
				$("#newModalBody input[name='moreHuo']").val("");	
				$("#newModalBody input[name='moreWei']").val("");	
				$("#newModalBody input[name='kuCun']").val("");	
				$("#newModalBody input[name='pleaseCarNum']").val("");
				$("#newModalBody input[name='estimateWeight']").val("");
				$("#newModalBody input[name='hideEstimateCost']").val("");
				$("#newModalBody input[name='estimateCost']").val("");
				$("#newModalBody input[name='sendCompany']").val("");
				$("#newModalBody input[name='receiptCompany']").val("");
				$("#chooseAdvanceChargeAccount").empty();
				
				var id = $("#select_project option:selected").val();
				if(id == "" || id == null || id == undefined){
					return;
				}
				$.fd.ajax({
					type : "GET",
					url : "${pageContext.request.contextPath}/fireTrainCbulkLoading/selectProject.do?id="+id,
					dataType : 'json',
					success : function(data) {
						if(data.data.tp.projectType ==0){
							$("#newModalBody input[name='projectTypeName']").val("集装箱");	
						}else{
							$("#newModalBody input[name='projectTypeName']").val("散装");	
						}
						$("#newModalBody input[name='projectType']").val(data.data.tp.projectType);		
						$("#newModalBody input[name='projectId']").val(data.data.tp.id);		
						$("#newModalBody input[name='projectCode']").val(data.data.tp.projectCode);	
						$("#newModalBody input[name='sendCompany']").val(data.data.tp.sendCargoCompanyName);		
						$("#newModalBody input[name='receiptCompany']").val(data.data.tp.receiveCargoCompanyName);
						$("#newModalBody input[name='branchId']").val(data.data.tp.branchGroupId);			
						$("#newModalBody input[name='branchName']").val(data.data.tp.branchGroupName);			
						$("#newModalBody input[name='beginSite']").val(data.data.tp.beginSiteName);			
						$("#newModalBody input[name='beginPlace']").val(data.data.tp.beginAddress);			
						$("#newModalBody input[name='endSite']").val(data.data.tp.endSiteName);			
						$("#newModalBody input[name='endPlace']").val(data.data.tp.endAddress);			
						$("#newModalBody input[name='cargoName']").val(data.data.tp.cargoName);
						$("#newModalBody input[name='cargoSpecifications']").val(data.data.tp.cargoSpecifications);
						//$("#newModalBody input[name='advanceChargeAccount']").val(data.data.trainStation.bankAccount);
						//$("#newModalBody input[name='advanceCharge']").val(data.data.trainStation.bankLastAmount);
						$("#newModalBody input[name='hideEstimateCost']").val(data.data.tp.freightSum);
						if(data.data.advance.receiveAccountId == null || data.data.advance.receiveAccountId == ""){
							//$("#chooseAdvanceChargeAccount").append("<option value=''></option>");
							$("#chooseAdvanceChargeAccount").append("<option value=''>该项目未在中心站点账户存入</option>");
							$("#newModalBody input[name='advanceCharge']").val("0");
						}else{
							//$("#chooseAdvanceChargeAccount").append("<option value=''></option>");
		    				$("#chooseAdvanceChargeAccount").append("<option value="+data.data.advance.receiveAccountId+">" + data.data.advance.receiveAccountName+ "</option>");
		    				$("#newModalBody input[name='advanceCharge']").val(data.data.advance.depositAmount);
		    				$("#newModalBody input[name='advanceChargeAccount']").val(data.data.advance.receiveAccountName);
						}
						if(data.data.stock == 1){
							$("#newModalBody input[name='moreHuo']").val("多货");
							$("#newModalBody input[name='moreWei']").val("多位");
							$("#newModalBody input[name='kuCun']").val(data.data.sumCurrentQty);
						}else if(data.data.stock == 2){
							$("#newModalBody input[name='moreHuo']").val(data.data.freightYardName);
							$("#newModalBody input[name='moreWei']").val(data.data.cargoLocationName);
							$("#newModalBody input[name='kuCun']").val(data.data.currentQty);	
						}else{
							$("#newModalBody input[name='kuCun']").val(0);
						}
					}
				});
			}
			
			//根据预付款账户查询余额
			function exchangeAdvanceNum(){
				var id = $("#chooseAdvanceChargeAccount option:selected").val();
				var blance = $("#chooseAdvanceChargeAccount option:selected").text();
				if(id == "" || id == null || id == undefined){
					$("#newModalBody input[name='advanceChargeAccount']").val("");
					$("#newModalBody input[name='advanceCharge']").val("");
				}else{
					$.ajax({
						type : 'POST',
						url : "${pageContext.request.contextPath}/account/getAccountDetailById.do",
						data : {"id":id},
						dataType : "json",
						async:false, 
						success : function(data) {
							if(data.status==200){
								$("#newModalBody input[name='advanceChargeAccount']").val("");
								$("#newModalBody input[name='advanceCharge']").val("");
								$("#newModalBody input[name='advanceChargeAccount']").val(data.data.name);
								$("#newModalBody input[name='advanceCharge']").val(data.data.accountBalance);
							}else{
								$("#errorMsg").html("预付款账户异常");
								$("#promptIdModal").modal();
							}
						},
					}); 
				}
			}
			
			//根据预载重 计算预计费用
			function advanceAmount(){
				var hideEstimateCost = $("#hideEstimateCost").val();
				//var pleaseTrainNum = $("#pleaseCarNum").val();
				var estimateWeight = $("#estimateWeight").val();
				if(estimateWeight == null || estimateWeight == ""){
					hideEstimateCost = 0;
				}
				$("#estimateCost").val((parseFloat(hideEstimateCost)*parseFloat(estimateWeight)));
				if(estimateWeight==null || estimateWeight==""){
					$("#estimateCost").val("");
				}
			}
			
			$(function(){
				//新建请车
				$("#add").click(function(){
					$("#addId")[0].reset();
					$("#addModal").modal();
					//$("#select_project").change();
				});
				
				//项目下拉
				 $.fd.ajax({  
		                type: "GET",  
		                url:"${pageContext.request.contextPath}/fireTrainCbulkLoading/getAllProject.do?projectType="+1,  
		                success: function(data) {  
		                	$("#select_project").empty();
		                	$("#select_project").append("<option value=''></option>");
		    				$.each(data.data, function(index, itPro) {
		    					$("#select_project").append(
		    							"<option value="+itPro.id+">" + itPro.projectCode+ "</option>");
		    					
		    				});
		                }
		            });
				
				//请车类型下拉
				$.fd.ajax({  
		                type: "POST",  
		                url:"${pageContext.request.contextPath}/fireTrainCbulkLoading/pleaseTrainType.do",  
		                success: function(data) {  
		                	$("#select_pleaseTrain").empty();
		                	$("#select_pleaseTrain").append("<option value=''></option>");
		    				$.each(data.data, function(index, itTr) {
		    					$("#select_pleaseTrain").append(
		    							"<option value="+itTr.id+">" + itTr.trainKind
		    									+ "</option>");
		    					
		    				});
		                }
		            });
				//运单查看
				$("body").on("click", ".needLookDetail tr td:not(:nth-child(1)):not(:nth-child(5))", function() {
						$(this).parent().toggleClass("bgclass").siblings("tbody tr").removeClass("bgclass");
						var id = $(this).parent().attr("id");
						$.fd.ajax({
							type : 'GET',
							url : "${pageContext.request.contextPath}/fireTrainCbulkLoading/trainOrderDetailById.do?id="+id+"&type="+1,
							success : function(data) {
								var sta = "";
		        				if(data.data.trainOrderdetail.status ==1){
		        					sta = "等待承认";
		        				}
		        				if(data.data.trainOrderdetail.status ==2){
		        					sta = "等待装车";
		        				}
		        				if(data.data.trainOrderdetail.status ==3){
		        					sta = "等待发运";
		        				}
		        				if(data.data.trainOrderdetail.status ==4){
		        					sta = "在途运载";
		        				}
		        				if(data.data.trainOrderdetail.status ==5){
		        					sta = "等待卸货";
		        				}
		        				if(data.data.trainOrderdetail.status ==6){
		        					sta = "等待回单";
		        				}
		        				if(data.data.trainOrderdetail.status ==7){
		        					sta = "已完成";
		        				}
								var proType="";
								if(data.data.trainOrderdetail.projectType ==0){
									proType="集装箱";
								}else if(data.data.trainOrderdetail.projectType ==1){
									proType ="散装";
								}
								var loseNums = "";
								if(data.data.trainOrderdetail.pleaseCarNum != null && data.data.trainOrderdetail.sureCarNum != null){
									loseNums= data.data.trainOrderdetail.pleaseCarNum - data.data.trainOrderdetail.sureCarNum;
								}
								var cargoPlaceDetail = "";
								var yardDetail = "";
								var kucunDetail = "";
								if(data.data.stocks == 1){
									cargoPlaceDetail="多货";
									yardDetail="多位";
									kucunDetail=data.data.sumCurrentQtys;
								}else if(data.data.stocks == 2){
									cargoPlaceDetail=data.data.freightYardNames;
									yardDetail=data.data.cargoLocationNames;
									kucunDetail=data.data.currentQtys;
								}else{
									
								} 
								$("#detailsUlProject").empty();
								$("#detailsUlProject").append(
		   	                			"<li>项目编号：<span>"+data.data.trainOrderdetail.projectCode+"</span></li>"
		   								+"<li>项目类型：<span>"+proType+"</span></li>"
		   								+"<li>分支机构：<span>"+data.data.trainOrderdetail.branchName+"</span></li>"
		   								+"<li>物流员：<span>"+data.data.trainOrderdetail.sendOperatorId+"</span></li>");
								$("#detailsUlTrainOrder").empty();
								$("#detailsUlTrainOrder").append(
		   	                			"<li>请车单号：<span>"+data.data.trainOrderdetail.pleaseTrainNumber+"</span></li>"
		   								+"<li>创建时间：<span>"+data.data.trainOrderdetail.createDate+"</span></li>"
		   								+"<li class='state'>运单状态：<span>"+sta+"</span></li>"
		   								+"<li>状态更新时间：<span>"+data.data.trainOrderdetail.updateDate+"</span></li>");
								$("#detailsUlCargo").empty();
								$("#detailsUlCargo").append(
		   	                			"<li>货物品名：<span>"+data.data.trainOrderdetail.cargoName+"</span></li>"
		   								+"<li>货物规格：<span>"+data.data.trainOrderdetail.cargoSpecifications+"</span></li>");
								$("#detailsUlPleaseCar").empty();
								$("#detailsUlPleaseCar").append(
		   	                			"<li>请车单号：<span>"+data.data.trainOrderdetail.pleaseTrainNumber+"</span></li>"
		   	                			+"<li>&emsp;请车数：<span>"+data.data.trainOrderdetail.pleaseCarNum+"</span></li>"
		   	                			+"<li>承认车数：<span>"+data.data.trainOrderdetail.sureCarNum+"</span></li>"
		   	                			+"<li>&emsp;落车数：<span>"+loseNums+"</span></li>"
		   	                			+"<li>装车时间：<span>"+data.data.trainOrderdetail.entruckDate+"</span></li>"
		   	                			+"<li>发车时间：<span>"+data.data.trainOrderdetail.sendDate+"</span></li>"
		   	                			+"<li>预载重：<span>"+data.data.trainOrderdetail.estimateWeight+"</span></li>"
		   								+"<li>运输费用：<span>"+data.data.trainOrderdetail.estimateCost+"</span></li>");
								$("#detailsUlThingInfo").empty();
								$("#detailsUlThingInfo").append(
		   	                			"<li>发货企业：<span>"+data.data.trainOrderdetail.beginPlace+"</span></li>"
		   	                			+"<li>始发站点：<span>"+data.data.trainOrderdetail.beginSite+"</span></li>"
		   	                			+"<li>收货企业：<span>"+data.data.trainOrderdetail.endPlace+"</span></li>"
		   	                			+"<li>到达站点：<span>"+data.data.trainOrderdetail.endSite+"</span></li>"
		   	                			+"<li>&emsp;&emsp;货场：<span>"+cargoPlaceDetail+"</span></li>"
		   	                			+"<li>&emsp;&emsp;货位：<span>"+yardDetail+"</span></li>"
		   	                			+"<li>&emsp;&emsp;库存：<span>"+kucunDetail+"</span></li>" 
		   	                			/* +"<li>&emsp;&emsp;货场：<span>多货</span></li>"
		   	                			+"<li>&emsp;&emsp;货位：<span>多位</span></li>"
		   	                			+"<li>&emsp;&emsp;库存：<span>50</span></li>" */
		   								+"<li>运输总费用：<span>"+data.data.cost+"</span></li>");
								$("#detailBody").empty();
								var details = data.data["detail"];
								$.each(details,function(index,details){
									$("#detailBody").append(
											"<tr><td>"+ (index+1) +"</td><td>"+details.carType+"</td><td>"+details.carNumber+"</td><td>"+details.sendWeight+"</td>"
											+"<td>"+details.unloadWeight+"</td><td>"+details.arriveCargoPlaceName+"</td><td>"+details.arriveCargoSiteName+"</td><td>"+details.arriveUnloadTime+"</td></tr>");
		   	                	});
								$("#detailsUlAmount").empty();
								$("#detailsUlAmount").append(
		   	                			"<li>预付款账户：<span>"+data.data.trainOrderdetail.advanceChargeAccount+"</span></li>"
		   								+"<li>预付款余额：<span>"+data.data.trainOrderdetail.advanceCharge+"</span></li>");
								$("#detailsUlHistory").empty();
								var historyLocationDetail = data.data["historyLocationDetail"];
		   	                	$.each(historyLocationDetail,function(index,locationDetail){
		   	                		$("#detailsUlHistory").append(
		   	   	                			"<li>"+locationDetail.time+"&nbsp;&nbsp;&nbsp"+locationDetail.location+"</li>");
		   	                	});
							},
						}); 
					$("#lookModal").modal();
					$('#showMask', window.parent.document).show();
				});	
				
				//异常处理
				$(".abnorm").click(function() {
					var id = $(this).parent().parent().parent().attr("id");
					if($("#" + id + "  input[type=checkbox]:checked").length > 1) {
						$("#nullModal").modal();
						$("#warnMsg").html("不可批量处理！");
						return;
					} else if($("#" + id + "  input[type=checkbox]:checked").length == 0) {
						$("#promptIdModal").modal();
						$("#errorMsg").html("请选择一条要操作的数据!");
						return;
					} else {
						$("#abnormalModal").modal();
						var id = $("#exceptionTable input:checkbox[type='checkbox']:checked").val();
						$.fd.ajax({  
		   	                type: "GET",  
		   	                url:"${pageContext.request.contextPath}/fireTrainCbulkLoading/getCarrierMsg.do?id="+id,  
		   	                success: function(data) {
		   	                	$("#abnormalUlContent").empty();
		   	                	$("#abnormalUlContent").append("<input type='hidden' value='"+data.data.id+"' id='handleEcxptionId' name='handleEcxptionId'>"
		   	                			+"<li>提报时间：<span>"+data.data.exceptionReportDate+"</span></li>"
		   								+"<li>异常提报人：<span>"+data.data.exceptionReportPerson+"</span></li>"
		   								+"<li>异常原因：<span>"+data.data.exceptionReason+"</span></li>");
		   	                }
		   	            }); 
					}
				});
			});
			
			//驳回异常运单
			function handleException(){
				var handleEcxptionId = $("#handleEcxptionId").val();
				$.ajax({  
   	                type: "POST",  
   	                url:"${pageContext.request.contextPath}/fireTrainCbulkLoading/handleExceptionById.do",
   	             	data : {"handleEcxptionId":handleEcxptionId},
					async : false,
   	                success: function(data) {
   	                	if (data.status != 200) {
							$("#errorMsg").html(data.msg);
							$("#promptIdModal").modal();
						} else {
							$("#abnormalModal").modal("hide");
							$("#warnMsg").html("操作成功");
							$("#nullModal").modal();
						}
   	                }
   	            }); 
			}
			
			//等待承认获取详情
			function admiter(id){
				$("#admitForm")[0].reset();
				$("#waitUlContent").empty();
				$("#waitModal").modal();
				$('#showMask', window.parent.document).show();
				$.fd.ajax({  
   	                type: "GET",  
   	                url:"${pageContext.request.contextPath}/fireTrainCbulkLoading/getCarrierMsg.do?id="+id,  
   	                success: function(data) {  
   	                	$("#waitUlContent").append("<input type='hidden' value='"+data.data.trainOrder.id+"' id='orderIdWait' name='orderIdWait'>"
   	                			+"<li>申请项目：<span>"+data.data.trainOrder.projectCode+"</span></li>"
   								+"<li>请车单号：<span>"+data.data.trainOrder.pleaseTrainNumber+"</span></li>"
   								+"<li>请车数：<span>"+data.data.trainOrder.pleaseCarNum+"</span></li>"
   								+"<input type='hidden' value='"+data.data.trainOrder.pleaseCarNum+"' id='hiddenPleaseNum'><i class='requireds'>*</i>"
   								+"<li><span class='v-top'>承认车数：</span><input type='text' id='sureNum' name='sureNum' maxlength='30' onkeyup='RepNumber(this)'/></li>");
   	                	
   	                }
   	            }); 
			}
			
			//等待承认
			function addWaitAdmit(){
				var id = $("#orderIdWait").val();
				var hiddenPleaseNum = $("#hiddenPleaseNum").val();
				var sureNum = $("#sureNum").val();
				$("#errorMsg").html("");
				if(parseInt(hiddenPleaseNum)<parseInt(sureNum)){
					$("#errorMsg").html("承认车数不能大于请车车数");
					$("#promptIdModal").modal();
					return;
				}
				$.ajax({
					type : "POST",
					url : "${pageContext.request.contextPath}/fireTrainCbulkLoading/addCarrier.do",
					data : {"id":id,"sureNum":sureNum},
					dataType : "json",
					async : false,
					success : function(data) {
						if (data.status != 200) {
							if(data.status == 1){
								$("#warnMsg").html(data.msg);
								$("#nullModal").modal();
							}else{
								$("#errorMsg").html(data.msg);
								$("#promptIdModal").modal();
							}
						} else {
							$("#waitModal").modal("hide");
							$("#warnMsg").html("操作成功");
							$("#nullModal").modal();
						}
					}
				});
			}
			
			function closeAndfresh(){
				$("#nullModal").modal("hide");
				window.location.reload();
			}
			//等待装车获取详情
			function loadingEntruck(id){
				$("#loadingForm")[0].reset();
				//$("#waitUlLoadContent").empty();
				$("#freightYard").empty();
				$("#entruckingModal").modal();
				$('#showMask', window.parent.document).show();
				$.fd.ajax({  
   	                type: "GET",  
   	                url:"${pageContext.request.contextPath}/fireTrainCbulkLoading/getCarrierMsg.do?id="+id,  
   	                success: function(data) {  
   	                	$('#waitUlLoadContent span[name="xmbhInfo"]').html("");
   	                	$('#waitUlLoadContent span[name="qcdhInfo"]').html("");
   	                	$('#waitUlLoadContent span[name="sfzdInfo"]').html("");
   						$('#waitUlLoadContent span[name="xmbhInfo"]').html(data.data.trainOrder.projectCode);
   						$('#waitUlLoadContent span[name="qcdhInfo"]').html(data.data.trainOrder.pleaseTrainNumber);
   						$('#waitUlLoadContent span[name="sfzdInfo"]').html(data.data.trainOrder.beginSite);
   						
   	                	$("#orderId").val(data.data.trainOrder.id);
   	                	$("#chengrencheshu").val(data.data.trainOrder.sureCarNum);
   	                	$("#hidenProjectId").val(data.data.trainOrder.projectId);
   	                	var freightYardLists = data.data["freightYardLists"];
   	                	$.each(freightYardLists,function(index,fylts){
	    						$("#freightYard").append(
		    							"<option value="+fylts.id+">" + fylts.name + "</option>");
	    				});
   	                	$("#freightYard").change();
   	                }
   	            }); 
			}
			
			$(function(){
				var newFa;
				$("body").on("change",".freightYard",function(){
					var checkValue=$(this).val(); 
					var checkText=$(this).find("option:selected").text();
					var arrtbody = $(this).parent().parent().parent().next().find('tbody');
					newFa = $(this).parent().next().children();
					$.each(arrtbody, function(i,val){      
					    var arrTr = $(val).find('tr');
					    for (var j = 0; j < arrTr.length; j++) {
					    	var arrTd = arrTr.eq(j).find('td');
					    	arrTd.eq(4).html(checkValue);
						    arrTd.eq(5).html(checkText);
					    };
					});
					//等待装车 货位下拉
					$.fd.ajax({  
			             type: "GET",  
			             url:"${pageContext.request.contextPath}/siteManager/cargo/location/getAll.do?id="+checkValue,  
			             success: function(data) {  
			            	 newFa.empty();
			 				$.each(data.data, function(index, its) {
			 					newFa.append("<option value="+its.id+">"+its.code+ "  " + its.name + "</option>");
			 				});
			 				newFa.change();
			             }
			         }); 
				});
				
				$("body").on("change",".freightAllocation",function(){
					var checkValue=$(this).val(); 
					var checkText=$(this).find("option:selected").text();
					var arrtbody = $(this).parent().parent().parent().next().find('tbody');
					$.each(arrtbody, function(i,val){      
					    var arrTr = $(val).find('tr');
					    for (var j = 0; j < arrTr.length; j++) {
					    	var arrTd = arrTr.eq(j).find('td');
					    	arrTd.eq(6).html(checkValue);
						    arrTd.eq(7).html(checkText);
					    }
					});
				});
			});
			
			//将下拉框的值赋值给td
			function gainSelectValue(param){
				var text = param.value;
				if(text == "" || text == null || text == undefined){
					
				}else{
					var fyId = $(param).parent().parent().parent().parent().prev().children().find("li").eq(0).find("select").val();
					var fyName = $(param).parent().parent().parent().parent().prev().children().find("li").eq(0).find("option:selected").text();
					$(param).parent().next().next().next().html(fyId);
					$(param).parent().next().next().next().next().html(fyName);
					
					var faId = $(param).parent().parent().parent().parent().prev().children().find("li").eq(1).find("select").val();
					var faName = $(param).parent().parent().parent().parent().prev().children().find("li").eq(1).find("option:selected").text();
					$(param).parent().next().next().next().next().next().html(faId);
					$(param).parent().next().next().next().next().next().next().html(faName);
				}
			}
			
			// 等待装车确认
			function saveWaitTrainInput() {
				//var allocation = '';
				var saveWait = new Array();
				var arrtbody = $("#waitUlLoadForm").find('tbody');
				var entruckWeight = 0;
				var carNum = 0;
				var cxArr = $(".newTr [name=chexing]");
				for(var i=0;i<cxArr.length;i++){
					if(cxArr.eq(i).val()){
						
					}else{
						cxArr.eq(i).val(null);
						cxArr.eq(i).text(null);
					}
				}
				$.each(arrtbody, function(i,val){      
				    var arrTr = $(val).find('tr');
				    $.each(arrTr, function(j,trVal){    
					    var arrTd = $(trVal).find('td');//每一行的td数组 赋值
					    var car_type = arrTd.eq(1).find("option:selected").text();
					    var car_number = arrTd.eq(2).find("input").val();
						var send_weight = arrTd.eq(3).find("input").val()?parseFloat(arrTd.eq(3).find("input").val()):0;
						var send_weights = arrTd.eq(3).find("input").val();
						var cargo_place_id = arrTd.eq(4).text();
						var cargo_place_name = arrTd.eq(5).text();
						var cargo_site_id = arrTd.eq(6).text();
						var cargo_site_name = arrTd.eq(7).text();
						saveWait.push({
							carType : car_type,
							carNumber : car_number,
							sendWeight :send_weights,
							cargoPlaceId : cargo_place_id,
							cargoPlaceName : cargo_place_name,
							cargoSiteId : cargo_site_id,
							cargoSiteName : cargo_site_name
						});
						/* if(send_weight == "" || send_weight == null){
							
						}else{
							sumWeight2(send_weight);
						} */
						var cari = arrTd.eq(2).find("input").val()?1:0;
						entruckWeight = entruckWeight + send_weight;
						carNum = carNum + cari;
				    });
				    $("#entruckWeight").val(entruckWeight);
				    $("#entruckNumbe").val(carNum);
				});
				//检验表格信息是否全都填写
				 var nullFlag = false;
				 $.each(arrtbody, function(i,val){      
				    var arrTr = $(val).find('tr');
				    $.each(arrTr, function(j,trVal){    
					    var arrTd = $(trVal).find('td');//每一行的td数组 赋值
					    var car_type = arrTd.eq(1).find("option:selected").text();
					    var car_number = arrTd.eq(2).find("input").val();
						var send_weight = arrTd.eq(3).find("input").val();
						if((car_type == "" || car_number == "" || send_weight == "")){
							nullFlag = true;
						}
						//td一行全为空 后台处理
						if((car_type == "" && car_number == "" && send_weight == "")){
							nullFlag = false;
						}
				    });
				});
				var saveWaitJson = JSON.stringify(saveWait);
				$("#entruckInfoJson").val(saveWaitJson);
				if(nullFlag){
					//$("#entruckWeight").val("");
					$("#errorMsg").html("请补全装车信息");
					$("#promptIdModal").modal();
					return;
				}else{
					$.ajax({
						type : 'POST',
						url : "${pageContext.request.contextPath}/fireTrainCbulkLoading/addWaitEntruck.do",
						data : $('#loadingForm').serialize(),
						dataType : "json",
						success : function(data) {
							if (data.status != 200) {
								//$("#entruckWeight").val("");
								$("#errorMsg").html(data.msg);
								$("#promptIdModal").modal();
							} else {
								$("#warnMsg").html("操作成功");
								$("#nullModal").modal();
							}
						},
					}); 
				}
			}
			
			//等待卸货
			function waitUnloading(id){
				$("#unloadingId")[0].reset();
				$('#showMask', window.parent.document).show();
				$("#unloadingModal").modal();
				$('#showMask', window.parent.document).show();
				$("#waitUlUnloadContent").empty();
				$("#unloadingBody").empty();
				$("#localtionUlContent2").empty();
				$(".select_freight_guide").empty();
				$(".select_cargo_guide").empty();
				$.fd.ajax({  
   	                type: "GET",  
   	                url:"${pageContext.request.contextPath}/fireTrainCbulkLoading/getSendMsg.do?id="+id,  
   	                success: function(data) {  
   	                	$("#waitUlUnloadContent").append("<input type='hidden' value='"+data.data.trainOrder.id+"' id='orderIdUnload' name='orderIdUnload'>"
   	                			+"<input type='hidden' value='"+data.data.trainOrder.projectId+"' id='projectIdUnload' name='projectIdUnload'>"
   	                			+"<li>项目编号：<span>"+data.data.trainOrder.projectCode+"</span></li>"
   	 							+"<li>请车单号：<span>"+data.data.trainOrder.pleaseTrainNumber+"</span></li>"
   	 							+"<li>装车数：</span>"+data.data.trainOrder.entruckNumbe+"</li>"
   	 							+"<li>装载吨位：</span>"+data.data.trainOrder.entruckWeight+"</li>"
   	 							+"<li>始发站点：<span>"+data.data.trainOrder.beginSite+"</span></li>");
   	                	var historyLocation = data.data["historyLocation"];
   	                	$.each(historyLocation,function(index,hlocationList){
   	                		$("#localtionUlContent2").append("<input type='hidden' value='"+hlocationList.orderId+"' id='orderIdLocation2' name='orderIdLocation2'>"
   	   	                			+"<li>"+hlocationList.time+"&nbsp;&nbsp;&nbsp"+hlocationList.location+"</li>");
   	                	});
   	                	var trainCargoList = data.data["trainCargoList"];
	   	 				$.each(trainCargoList,function(index,tcList){
	   	 					$("#unloadingBody").append("<tr><td style='text-align: center;'><label class='demo--label'>"
							 +" <input class='demo--checkbox'  name='unloadName' value='"+tcList.id+"' type='checkbox'   >"
                             +"<span class='demo--checkboxInput'></span>"
                            +"</label></td><td style='display:none'></td><td>"+ (index+1) +"</td><td>"+tcList.carType+"</td><td>"+tcList.carNumber+"</td>"
	   	 					+"<td>"+tcList.sendWeight+"</td><td><input type='text'  oninput='loadingValue(this)' style='height:28px;border:1px solid #dde3ef; border-radius: 4px;' value="+tcList.unloadWeight+"></td><td><select name='freight_guide"+index+"' class='select_freight_guide' onchange='cargoLoExchangetd(this)'></select></td><td><select name='yard_guide"+index+"' onchange='yardLoExchangetd(this)'></select></td><td name='resaonSend"+index+"'></td><td name='resaonArrive"+index+"'></td></tr>")
	   	 					$("td[name='resaonSend"+index+"']").html("");
		   	 				if(tcList.sendImg == null || tcList.sendImg ==""){
	   	         				$("td[name='resaonSend"+index+"']").append("<a href='javascript:void(0)' id='unloadSerial"+(index+1)+"' class='uploadImg' onclick='uploadUnloadImg("+(index+1)+",this)'>点击上传</a><input type='hidden' id='hiddenUnoladId"+(index+1)+"' value='' />");
	   	 					}else{
	   	 						$("td[name='resaonSend"+index+"']").append("<a href='javascript:void(0)' class='lookClass' name='"+tcList.sendImg+"' onclick='lookingImg(this)'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<a href='javascript:void(0)' class='delA' onclick='deleteSendImg("+tcList.id+","+tcList.trainOrderId+")'>删除</a>"); 
	   	         			}
		   	 				$("td[name='resaonArrive"+index+"']").html("");
			   	 			if(tcList.unloadImg == null || tcList.unloadImg ==""){
	   	         				$("td[name='resaonArrive"+index+"']").append("<a href='javascript:void(0)' id='unloadSerialTwo"+(index+1)+"' class='uploadImg' onclick='arriveUnloadImg("+(index+1)+",this)'>点击上传</a><input type='hidden' id='hiddenUnoladIdTwo"+(index+1)+"' value='' />");
	   	 					}else{
	   	 						$("td[name='resaonArrive"+index+"']").append("<a href='javascript:void(0)' class='lookClass' name='"+tcList.unloadImg+"' onclick='lookingImg(this)'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<a href='javascript:void(0)' class='delA' onclick='deleteArriveImg("+tcList.id+","+tcList.trainOrderId+")'>删除</a>"); 
	   	         			}
	   	 					//td中的货场货位联动
	   	 					var freightYardList = data.data["freightYardList"];
		   	 					$("select[name='freight_guide"+index+"']").empty();
		   	 					$("select[name='freight_guide"+index+"']").append("<option value=''></option>");
				                $.each(freightYardList,function(io,fylt){
				                	$("select[name='freight_guide"+index+"']").append(
				    						"<option value="+fylt.id+">" + fylt.name + "</option>");
			    				});
	   	 					if(tcList.arriveCargoPlaceId == null || tcList.arriveCargoPlaceId == ""){
	   	 						
	   	 					}else{
			                	$("select[name='freight_guide"+index+"']").val(tcList.arriveCargoPlaceId);
			                	//$("select[name='freight_guide"+index+"']").change();
			                	if(tcList.arriveCargoSiteId == null || tcList.arriveCargoSiteId == ""){
		   	 						
		   	 					}else{
				   	 				$.fd.ajax({  
				   	 		             type: "GET",  
				   	 		             url:"${pageContext.request.contextPath}/siteManager/cargo/location/getAll.do?id="+tcList.arriveCargoPlaceId,  
				   	 		             success: function(data) {  
				   	 		            	$("select[name='yard_guide"+index+"']").empty();
				   	 		 				$.each(data.data, function(j, itj) {
				   	 		 				$("select[name='yard_guide"+index+"']").append("<option value="+itj.id+">"+itj.code+ "  " + itj.name + "</option>");
				   	 		 				});
				   	 		 				$("select[name='yard_guide"+index+"']").val(tcList.arriveCargoSiteId);
				   	 		             }
				   	 		         }); 
		   	 					}
	   	 					}
		   	 				
	   	 				});
		   	 			var freightYardList = data.data["freightYardList"];
		   	 			$("#select_freight_guide").empty();
		   	 			$("#select_freight_guide").append("<option value=''></option>");
		                $.each(freightYardList,function(index,fylt){
	    					$("#select_freight_guide").append(
		    						"<option value="+fylt.id+">" + fylt.name + "</option>");
	    				});
		                	//$(".select_freight_guide").change();
	   	                }
   	            }); 
			}
			
			//卸货货场 下拉
			function cargoLoExchange(){
				 var id = $("#select_freight_guide option:selected").val();
				 $.fd.ajax({  
		             type: "GET",  
		             url:"${pageContext.request.contextPath}/siteManager/cargo/location/getAll.do?id="+id,  
		             success: function(data) {  
		            		$("#select_cargo_guide").empty();
		 				$.each(data.data, function(index, its) {
		 							$("#select_cargo_guide").append(
				    							"<option value="+its.id+">"+its.code+ "  " + its.name + "</option>");
		 				});
		             }
		         }); 
				$.each($('.unloadingChecked input:checkbox'), function() {
					if (this.checked) {
						var td_freight_guide = $(this).parent().parent().next().next().next().next().next().next().next().children();
						td_freight_guide.val(parseInt(id));
						td_freight_guide.change();
					}
				});
			} 
			
			//卸货货位 下拉
			function yardExchange(){
				 var id = $("#select_cargo_guide option:selected").val();
				 $.each($('.unloadingChecked input:checkbox'), function() {
						if (this.checked) {
							var td_cargo_guide = $(this).parent().parent().next().next().next().next().next().next().next().next().children();
							td_cargo_guide.val(parseInt(id));
						}
					});
			}
			
			//td中已经填写过卸货货场货位 页面加载联动
			function cargoLoExchangetd(param){
				var id = $(param).val();
				var location = $(param).parent().next().children();
				$.fd.ajax({  
		             type: "GET",  
		             url:"${pageContext.request.contextPath}/siteManager/cargo/location/getAll.do?id="+id,  
		             success: function(data) {  
		            	 location.empty();
		 				$.each(data.data, function(index, its) {
		 					location.append("<option value="+its.id+">"+its.code+ "  " + its.name + "</option>");
		 				});
		             }
		         }); 
				//改变td中卸货货场 给隐藏td赋值id
				if(id == "" || id == null || id == undefined){
					$(param).parent().prev().prev().prev().prev().prev().prev().html("");
				}else{
					var unloadIdc = $(param).parent().prev().prev().prev().prev().prev().prev().prev().find("input").val();
					var unloadHideIdc = $(param).parent().prev().prev().prev().prev().prev().prev();
					unloadHideIdc.html(unloadIdc);
				}
			}
			
			var chbs = document.getElementsByName("unloadName");
			//全选
			function checkAll() {
				for(var i = 0; i < chbs.length; i++) {
					chbs[i].checked = document.getElementById("checkFalse").checked;
				}
				 var ckbValue = "";
				$("input[name=unloadName]:checked").each(function() {
					ckbValue = ckbValue + $(this).val() + ",";
				});	
			}	
				
			//表格内勾选
			function checkitemIds(ss) {
				var isCheckAll = true;
					for(var i = 0; i < chbs.length; i++) {
						if(chbs[i].checked == false) {
							isCheckAll = false;
						}
					}
				document.getElementById("checkFalse").checked = isCheckAll;
				if(ss.checked == true) {
					$(ss).attr("checked", "checked");
				} else {
					$(ss).attr("checked", false);
				}
			}
			
			//td中卸货货位改变给隐藏td赋值id
			function yardLoExchangetd(param){
				var unloadIdy = $(param).parent().prev().prev().prev().prev().prev().prev().prev().prev().find("input").val();
				var unloadHideIdy = $(param).parent().prev().prev().prev().prev().prev().prev().prev();
				unloadHideIdy.html(unloadIdy);
			}
			
			//输入到货载重给隐藏td赋值id
			function loadingValue(param){
				var sendWeight = $(param).parent().prev().text();
				var unloadId = $(param).parent().prev().prev().prev().prev().prev().prev().find("input").val();
				var unloadHideId = $(param).parent().prev().prev().prev().prev().prev();
				if(param.value == null || param.value ==""){
					unloadHideId.html("");
				}else{
					unloadHideId.html(unloadId);
					if(parseFloat(param.value).toFixed(1)>parseFloat(sendWeight).toFixed(1)){
						$("#errorMsg").html("到货载重不能大于发货载重");
						$("#promptIdModal").modal();
						param.value="";
						unloadHideId.html("");
						return;
					}
					param.value = param.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
					param.value = param.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
					param.value = param.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
				    param.value = param.value.replace(/^(\-)*(\d+)\.(\d).*$/,'$1$2.$3');//只能输入两个小数  
				    if(param.value.indexOf(".")< 0 && param.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
				    	param.value= parseFloat(param.value.slice(0,2)); 
				    } 
				    if(param.value.indexOf(".")> 2 && param.value !=""){ 
				    	param.value= parseFloat(""); 
					}  
				}
			}
			
			// 确认分配卸货货场货位
			function allocatCargoAndYard(status) {
				var train_order_id = $("#orderIdUnload").val();
				var projectId = $("#projectIdUnload").val();
				var ist = new Array();
				var trList = $("#unloadingBody").children("tr");
				for (var i = 0; i < trList.length; i++) {
					var tdArr = trList.eq(i).find("td");
					if(tdArr.eq(1).html() == null || tdArr.eq(1).html() == ""){
					
					}else{
						var id = tdArr.eq(1).html();
						var unload_weight = tdArr.eq(6).find("input").val();
						var arrive_cargo_place_id = tdArr.eq(7).find("select").val();
						var arrive_cargo_place_name = tdArr.eq(7).find("option:selected").text();
						var arrive_cargo_site_id = tdArr.eq(8).find("select").val();
						var arrive_cargo_site_name = tdArr.eq(8).find("option:selected").text();
						var send_img = tdArr.eq(9).find("input").val();
						var unload_img = tdArr.eq(10).find("input").val();
						ist.push({
							id : id,
							trainOrderId : train_order_id,
							unloadWeight :unload_weight,
							arriveCargoPlaceId : arrive_cargo_place_id,
							arriveCargoPlaceName : arrive_cargo_place_name,
							arriveCargoSiteId : arrive_cargo_site_id,
							arriveCargoSiteName : arrive_cargo_site_name,
							sendImg : send_img,
							unloadImg : unload_img
						});
					}
				}
				 var jsonArray = JSON.stringify(ist);
				 $("#unloadInfoJson").val(jsonArray);
				 
				//勾选再次确认，检验表格信息是否全都填写
				 var nullFlag = false;
				 for(var i = 0; i < trList.length; i++){
					var tdArr2 = trList.eq(i).find("td");
					var unload_weight2 = tdArr2.eq(6).find("input").val();
					var arrive_cargo_place_name2 = tdArr2.eq(7).find("option:selected").text();
					var arrive_cargo_site_name2 = tdArr2.eq(8).find("option:selected").text();
					if(unload_weight2 == "" || arrive_cargo_place_name2 == "" || arrive_cargo_site_name2 == ""){
						nullFlag = true;
						break;
					}
				 }
				//再次确认勾选
					var flag = false;
					$.each($("input[name='isNext']:checkbox"), function() {
						if (this.checked) {
							flag = true;
						}
					});
					if(flag){
						if(nullFlag){
							$("#errorMsg").html("请补全信息");
							$("#promptIdModal").modal();
							return;
						}else{
							//假如填完信息忘记勾选再次确认按钮  那么就是保存操作  在点开弹窗勾选确认按钮  点击确定 进入下一个状态 
							var nullFlagTwo = false;
							var k = 0;
							for(var i = 0; i < trList.length; i++){
								var tdArr3 = trList.eq(i).find("td");
								if(tdArr3.eq(1).html() == null || tdArr3.eq(1).html() == ""){
									k = parseInt(i)+parseInt(1);
								}
							}
							if(k == trList.length){
								nullFlagTwo = true;
							}
							if(nullFlagTwo){
								$.ajax({
									type : 'POST',
									url : "${pageContext.request.contextPath}/fireTrainCbulkLoading/updOrderStatusByParam.do",
									data : {"id":train_order_id,"status":status},
									dataType : "json",
									success : function(data) {
										if(data.status==200){
											$("#deltialModal").modal("hide");
											$("#warnMsg").html("操作成功");
											$("#nullModal").modal();
										}else{
											$("#deltialModal").modal("hide");
											$("#errorMsg").html(data.msg);
											$("#promptIdModal").modal();
										}
									},
								});
							}else{
								//最后一次填完信息，勾选确认按钮，先保存卸货信息，在更改状态
								$.ajax({
									type : 'POST',
									url : "${pageContext.request.contextPath}/fireTrainCbulkLoading/updateUnloadWeight.do",
									data : {"jsonArray":jsonArray,"projectId":projectId},
									dataType : "json",
									success : function(data) {
										if(data.status == 200){
											$.ajax({
												type : 'POST',
												url : "${pageContext.request.contextPath}/fireTrainCbulkLoading/updOrderStatusByParam.do",
												data : {"id":train_order_id,"status":status},
												dataType : "json",
												success : function(data) {
													if(data.status==200){
														$("#deltialModal").modal("hide");
														$("#warnMsg").html("操作成功");
														$("#nullModal").modal();
													}else{
														$("#deltialModal").modal("hide");
														$("#errorMsg").html(data.msg);
														$("#promptIdModal").modal();
													}
												},
											}); 
										}
									}
								});
							}
						}
					}else{
						$.ajax({
							type : 'POST',
							url : "${pageContext.request.contextPath}/fireTrainCbulkLoading/updateUnloadWeight.do",
							data : {"jsonArray":jsonArray,"projectId":projectId},
							dataType : "json",
							success : function(data) {
								if (data.status != 200) {
									$("#errorMsg").html(data.msg);
									$("#promptIdModal").modal();
								} else {
									$("#unloadingBody").empty();
									$(".select_freight_guide").empty();
									$(".select_cargo_guide").empty();
									$.fd.ajax({  
					   	                type: "GET",  
					   	                url:"${pageContext.request.contextPath}/fireTrainCbulkLoading/getSendMsg.do?id="+train_order_id,  
					   	                success: function(data) {  
					   	                	var trainCargoList = data.data["trainCargoList"];
						   	 				$.each(trainCargoList,function(index,tcList){
						   	 					$("#unloadingBody").append("<tr><td style='text-align: center;'><label class='demo--label'>"
												 +" <input class='demo--checkbox'  name='unloadName' value='"+tcList.id+"' type='checkbox'   >"
					                             +"<span class='demo--checkboxInput'></span>"
					                            +"</label></td><td style='display:none'></td><td>"+ (index+1) +"</td><td>"+tcList.carType+"</td><td>"+tcList.carNumber+"</td>"
						   	 					+"<td>"+tcList.sendWeight+"</td><td><input type='text'  oninput='loadingValue(this)'  style='height:28px;border:1px solid #dde3ef; border-radius: 4px;' value="+tcList.unloadWeight+"></td><td><select name='freight_guide"+index+"' class='select_freight_guide' onchange='cargoLoExchangetd(this)'></select></td><td><select name='yard_guide"+index+"' onchange='yardLoExchangetd(this)'></select></td><td name='resaonSend"+index+"'></td><td name='resaonArrive"+index+"'></td></tr>")
						   	 					$("td[name='resaonSend"+index+"']").html("");
							   	 				if(tcList.sendImg == null || tcList.sendImg ==""){
						   	         				$("td[name='resaonSend"+index+"']").append("<a href='javascript:void(0)' id='unloadSerial"+(index+1)+"' class='uploadImg' onclick='uploadUnloadImg("+(index+1)+",this)'>点击上传</a><input type='hidden' id='hiddenUnoladId"+(index+1)+"' value='' />");
						   	 					}else{
						   	 						$("td[name='resaonSend"+index+"']").append("<a href='javascript:void(0)' class='lookClass' name='"+tcList.sendImg+"' onclick='lookingImg(this)'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<a href='javascript:void(0)' class='delA' onclick='deleteSendImg("+tcList.id+","+tcList.trainOrderId+")'>删除</a>"); 
						   	         			}
							   	 				$("td[name='resaonArrive"+index+"']").html("");
								   	 			if(tcList.unloadImg == null || tcList.unloadImg ==""){
						   	         				$("td[name='resaonArrive"+index+"']").append("<a href='javascript:void(0)' id='unloadSerialTwo"+(index+1)+"' class='uploadImg' onclick='arriveUnloadImg("+(index+1)+",this)'>点击上传</a><input type='hidden' id='hiddenUnoladIdTwo"+(index+1)+"' value='' />");
						   	 					}else{
						   	 						$("td[name='resaonArrive"+index+"']").append("<a href='javascript:void(0)' class='lookClass' name='"+tcList.unloadImg+"' onclick='lookingImg(this)'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<a href='javascript:void(0)' class='delA' onclick='deleteArriveImg("+tcList.id+","+tcList.trainOrderId+")'>删除</a>"); 
						   	         			}
						   	 					//td中的货场货位联动
						   	 					var freightYardList = data.data["freightYardList"];
							   	 					$("select[name='freight_guide"+index+"']").empty();
							   	 					$("select[name='freight_guide"+index+"']").append("<option value=''></option>");
									                $.each(freightYardList,function(io,fylt){
									                	$("select[name='freight_guide"+index+"']").append(
									    						"<option value="+fylt.id+">" + fylt.name + "</option>");
								    				});
						   	 					if(tcList.arriveCargoPlaceId == null || tcList.arriveCargoPlaceId == ""){
						   	 						
						   	 					}else{
								                	$("select[name='freight_guide"+index+"']").val(tcList.arriveCargoPlaceId);
								                	//$("select[name='freight_guide"+index+"']").change();
								                	if(tcList.arriveCargoSiteId == null || tcList.arriveCargoSiteId == ""){
							   	 						
							   	 					}else{
									   	 				$.fd.ajax({  
									   	 		             type: "GET",  
									   	 		             url:"${pageContext.request.contextPath}/siteManager/cargo/location/getAll.do?id="+tcList.arriveCargoPlaceId,  
									   	 		             success: function(data) {  
									   	 		            	$("select[name='yard_guide"+index+"']").empty();
									   	 		 				$.each(data.data, function(j, itj) {
									   	 		 				$("select[name='yard_guide"+index+"']").append("<option value="+itj.id+">"+itj.code+ "  " + itj.name + "</option>");
									   	 		 				});
									   	 		 				$("select[name='yard_guide"+index+"']").val(tcList.arriveCargoSiteId);
									   	 		             }
									   	 		         }); 
							   	 					}
						   	 					}
							   	 				
						   	 				});
							   	 			var freightYardList = data.data["freightYardList"];
							   	 			$("#select_freight_guide").empty();
							   	 			$("#select_freight_guide").append("<option value=''></option>");
							                $.each(freightYardList,function(index,fylt){
						    					$("#select_freight_guide").append(
							    						"<option value="+fylt.id+">" + fylt.name + "</option>");
						    				});
							                	//$(".select_freight_guide").change();
						   	                }
					   	            }); 
									$("#errorMsg").html("保存成功");
									$("#promptIdModal").modal();
								}
							},
						}); 
					}
			}
			
			//等待回单
			function receipting(id){
				$("#receiptId")[0].reset();
				$('#showMask', window.parent.document).show();
				$("#receiptModal").modal();
				$("#waitUlReceiptContent").empty();
				$("#receiptionBody").empty();
				$.fd.ajax({  
   	                type: "GET",  
   	                url:"${pageContext.request.contextPath}/fireTrainCbulkLoading/getSendMsg.do?id="+id,  
   	                success: function(data) {  
   	                	$("#waitUlReceiptContent").append("<input type='hidden' value='"+data.data.trainOrder.id+"' id='orderIdReceiption' name='orderIdReceiption'>"
   	                			+"<li>项目编号：<span>"+data.data.trainOrder.projectCode+"</span></li>"
   	 							+"<li>请车单号：<span>"+data.data.trainOrder.pleaseTrainNumber+"</span></li>"
   	 							+"<li>装车数：</span>"+data.data.trainOrder.entruckNumbe+"</li>"
   	 							+"<li>装载吨位：</span>"+data.data.trainOrder.entruckWeight+"</li>"
   	 							+"<li>始发站点：<span>"+data.data.trainOrder.beginSite+"</span></li>");
   	                	var trainCargoList = data.data["trainCargoList"];
	   	 				$.each(trainCargoList,function(index,tcList){
	   	 					$("#receiptionBody").append("<tr><td>"+ (index+1) +"</td><td>"+tcList.carType+"</td><td>"+tcList.carNumber+"</td>"
	   	 					+"<td>"+tcList.sendWeight+"</td><td>"+tcList.unloadWeight+"</td><td name='resaonSends"+index+"'></td><td name='resaonArrives"+index+"'></td></tr>");
	   	 					$("td[name='resaonSend"+index+"']").html("");
		   	 				$("td[name='resaonSends"+index+"']").html("");
	   	 					if(tcList.sendImg == null || tcList.sendImg ==""){
	   	         				$("td[name='resaonSends"+index+"']").append("<a href='javascript:void(0)'  class='uploadImg' onclick='uploadSendImg("+tcList.id+","+tcList.trainOrderId+")'>点击上传</a>");
	   	 					}else{
	   	 						$("td[name='resaonSends"+index+"']").append("<a href='javascript:void(0)' class='lookClass' name='"+tcList.sendImg+"' onclick='lookingImg(this)'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<a href='javascript:void(0)' class='delA' onclick='deleteReceiveSendImg("+tcList.id+","+tcList.trainOrderId+")'>删除</a>"); 
	   	         			}
		   	 				$("td[name='resaonArrive"+index+"']").html("");
	   	 					if(tcList.unloadImg == null || tcList.unloadImg ==""){
	   	         				$("td[name='resaonArrives"+index+"']").append("<a href='javascript:void(0)'  class='uploadImg' onclick='uploadArriveImg("+tcList.id+","+tcList.trainOrderId+")'>点击上传</a>");
	   	 					}else{
	   	 						$("td[name='resaonArrives"+index+"']").append("<a href='javascript:void(0)' class='lookClass' name='"+tcList.unloadImg+"' onclick='lookingImg(this)'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<a href='javascript:void(0)' class='delA' onclick='deleteReceiveArriveImg("+tcList.id+","+tcList.trainOrderId+")'>删除</a>"); 
	   	         			}
		   	 			});
   	                }
   	            }); 
			}
			
			//运单回收站还原运单
			function resetTrainOrder(){
				var id = $("input:checkbox[name='delOrderName']:checked").val();
				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/fireTrainCbulkLoading/resetDeleteOrder.do",
					data : {"id":id},
					dataType : "json",
					success : function(data) {
						if(data.status==200){
							$("#deltialModal").modal("hide");
							$("#warnMsg").html("操作成功");
							$("#nullModal").modal();
						}else{
							$("#deltialModal").modal("hide");
							$("#errorMsg").html(data.msg);
							$("#promptIdModal").modal();
						}
					},
				}); 
			}
			//等待发运获取详情
			function sending(id){
				$("#sendId")[0].reset();
				$("#waitUlSendContent").empty();
				$("#trainCargoBody").empty();
				$("#forwardingModal").modal();
				$('#showMask', window.parent.document).show();
				$.fd.ajax({  
   	                type: "GET",  
   	                url:"${pageContext.request.contextPath}/fireTrainCbulkLoading/getSendMsg.do?id="+id,  
   	                success: function(data) { 
   	                	$("#waitUlSendContent").append("<input type='hidden' value='"+data.data.trainOrder.id+"' id='orderIdSend' name='orderIdSend'>"
   	                			+"<li>项目编号：<span>"+data.data.trainOrder.projectCode+"</span></li>"
   								+"<li>请车单号：<span>"+data.data.trainOrder.pleaseTrainNumber+"</span></li>"
   								+"<li>装车数：</span>"+data.data.trainOrder.entruckNumbe+"</li>"
   								+"<li>装载吨位：</span>"+data.data.trainOrder.entruckWeight+"</li>"
   								+"<li>始发站点：<span>"+data.data.trainOrder.beginSite+"</span></li>");
   	                	var trainCargoList = data.data["trainCargoList"];
						$.each(trainCargoList,function(index,tcList){
							$("#trainCargoBody").append("<tr><td>"+ (index+1) +"</td><td>"+tcList.carType+"</td><td>"+tcList.carNumber+"</td>"
							+"<td>"+tcList.sendWeight+"</td><td name='resaons"+index+"'></td></tr>")
							$("td[name='resaons"+index+"']").html("");
							if(tcList.sendImg == null || tcList.sendImg ==""){
		        				$("td[name='resaons"+index+"']").append("<a href='javascript:void(0)'  class='uploadImg' onclick='uploadSendPicture("+tcList.id+","+tcList.trainOrderId+")'>点击上传</a>");
							}else{
								$("td[name='resaons"+index+"']").append("<a href='javascript:void(0)' class='lookClass' name='"+tcList.sendImg+"' onclick='lookingImg(this)'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<a href='javascript:void(0)' class='delA' onclick='deleteWaitSendImg("+tcList.id+","+tcList.trainOrderId+")'>删除</a>"); 
		        			}
						});
   	                }
   	            }); 
			}
			//在途运载获取详情
			function carrying(id){
				$("#carryId")[0].reset();
				$("#localtionUlContent").empty();
				$("#firstLocation").val(id);
				$("#localtionBody").empty();
				$("#locationModal").modal();
				$('#showMask', window.parent.document).show();
				$.fd.ajax({  
   	                type: "GET",  
   	                url:"${pageContext.request.contextPath}/fireTrainCbulkLoading/getHistoryLocationMsg.do?id="+id+"&type="+1,  
   	                success: function(data) { 
   	                	var historyLocation = data.data["historyLocation"];
   	                	$.each(historyLocation,function(index,hlocationList){
   	                		$("#localtionUlContent").append("<input type='hidden' value='"+hlocationList.orderId+"' id='orderIdLocation' name='orderIdLocation'>"
   	   	                			+"<li>"+hlocationList.time+"&nbsp;&nbsp;&nbsp"+hlocationList.location+"</li>");
   	                	});
   	                	var hlTrainCargoList = data.data["hlTrainCargoList"];
						$.each(hlTrainCargoList,function(index,tcList){
							$("#localtionBody").append("<tr><td>"+ (index+1) +"</td><td>"+tcList.carType+"</td><td>"+tcList.carNumber+"</td>"
							+"<td>"+tcList.sendWeight+"</td><td name='reason"+index+"'></td></tr>")
							$("td[name='reason"+index+"']").html("");
							if(tcList.sendImg == null || tcList.sendImg ==""){
		        				$("td[name='reason"+index+"']").append("<a href='javascript:void(0)' onclick='uploadCarringImg("+tcList.id+","+tcList.trainOrderId+")' class='uploadImg'>点击上传</a>");
							}else{
								$("td[name='reason"+index+"']").append("<a href='javascript:void(0)' class='lookClass' name='"+tcList.sendImg+"' onclick='lookingImg(this)'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<a href='javascript:void(0)' class='delA' onclick='deleteCarringSendImg("+tcList.id+","+tcList.trainOrderId+")'>删除</a>"); 
		        			}
						});
   	                }
   	            }); 
			}
			//更新位置
			function updHistoryLocation(){
				var location = $("#location").val();
				var orderIdLocation = $("#orderIdLocation").val();
				if(location==null || location==""){
					$("#errorMsg").html("请输入位置信息");
					$("#promptIdModal").modal();
					return;
				}
				//第一次更新位置
				if(orderIdLocation == null || orderIdLocation == ""){
					orderIdLocation = $("#firstLocation").val();
				}
				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/fireTrainCbulkLoading/updateLocation.do",
					data : {"orderIdLocation":orderIdLocation,"location":location},
					dataType : "json",
					success : function(data) {
						if(data.status==200){
							$("#deltialModal").modal("hide");
							$.fd.ajax({  
			   	                type: "GET",  
			   	                url:"${pageContext.request.contextPath}/fireTrainCbulkLoading/getHistoryLocationMsg.do?id="+orderIdLocation+"&type="+1,  
			   	                success: function(data) { 
			   	                	$("#localtionUlContent").empty();
			   	                	var historyLocation = data.data["historyLocation"];
			   	                	$.each(historyLocation,function(index,hlocationList){
			   	                		$("#localtionUlContent").append("<input type='hidden' value='"+hlocationList.orderId+"' id='orderIdLocation' name='orderIdLocation'>"
			   	   	                			+"<li>"+hlocationList.time+"&nbsp;&nbsp;&nbsp"+hlocationList.location+"</li>");
			   	                	});
			   	                	$("#location").val("");
			   	                }
			   	            }); 
						}else{
							$("#deltialModal").modal("hide");
							$("#errorMsg").html(data.msg);
							$("#promptIdModal").modal();
						}
					},
				}); 
			}
			
			//在途运载 勾选确认按钮 进入下一步
			function checkAndToNext(){
				//再次确认勾选
				var flag = false;
				$.each($("input[name='isArrivePlace']:checkbox"), function() {
					if (this.checked) {
						flag = true;
					}
				});
				if(flag){
					updOrderStatus(5);
				}else{
					$("#locationModal").modal("hide");
					window.location.reload();
				}
			}
			
			//等待卸货更新位置
			function updHistoryLocation2(){
				var location2 = $("#location2").val();
				var orderIdLocation2 = $("#orderIdLocation2").val();
				if(location2==null || location2==""){
					$("#errorMsg").html("请输入位置信息");
					$("#promptIdModal").modal();
					return;
				}
				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/fireTrainCbulkLoading/updateLocation.do",
					data : {"orderIdLocation":orderIdLocation2,"location":location2},
					dataType : "json",
					success : function(data) {
						if(data.status==200){
							$("#deltialModal").modal("hide");
							$.fd.ajax({  
			   	                type: "GET",  
			   	                url:"${pageContext.request.contextPath}/trainOrder/getHistoryLocationMsg.do?id="+orderIdLocation2+"&type="+1,  
			   	                success: function(data) { 
			   	                	$("#localtionUlContent2").empty();
			   	                	var historyLocation = data.data["historyLocation"];
			   	                	$.each(historyLocation,function(index,hlocationList){
			   	                		$("#localtionUlContent2").append("<input type='hidden' value='"+hlocationList.orderId+"' id='orderIdLocation2' name='orderIdLocation2'>"
			   	   	                			+"<li>"+hlocationList.time+"&nbsp;&nbsp;&nbsp"+hlocationList.location+"</li>");
			   	                	});
			   	                	$("#location2").val("");
			   	                }
							});
						}else{
							$("#deltialModal").modal("hide");
							$("#errorMsg").html(data.msg);
							$("#promptIdModal").modal();
						}
					},
				}); 
			}
			
			//等待发运删除运单
			function deleteWaitSendImg(id,trainId){
				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/fireTrainCbulkLoading/deleteSendImg.do",
					data : {"id":id},
					dataType : "json",
					success : function(data) {
						if(data.status==200){
							$("#deltialModal").modal("hide");
							$("#trainCargoBody").empty();
							$.fd.ajax({  
			   	                type: "GET",  
			   	                url:"${pageContext.request.contextPath}/fireTrainCbulkLoading/getSendMsg.do?id="+trainId,  
			   	                success: function(data) { 
			   	                	var trainCargoList = data.data["trainCargoList"];
									$.each(trainCargoList,function(index,tcList){
										$("#trainCargoBody").append("<tr><td>"+ (index+1) +"</td><td>"+tcList.carType+"</td><td>"+tcList.carNumber+"</td>"
										+"<td>"+tcList.sendWeight+"</td><td name='resaons"+index+"'></td></tr>")
										$("td[name='resaons"+index+"']").html("");
										if(tcList.sendImg == null || tcList.sendImg ==""){
					        				$("td[name='resaons"+index+"']").append("<a href='javascript:void(0)'  class='uploadImg' onclick='uploadSendPicture("+tcList.id+","+tcList.trainOrderId+")'>点击上传</a>");
										}else{
											$("td[name='resaons"+index+"']").append("<a href='javascript:void(0)' class='lookClass' name='"+tcList.sendImg+"' onclick='lookingImg(this)'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<a href='javascript:void(0)' class='delA' onclick='deleteWaitSendImg("+tcList.id+","+tcList.trainOrderId+")'>删除</a>"); 
					        			}
									});
			   	                }
			   	            }); 
						}else{
							$("#deltialModal").modal("hide");
							$("#errorMsg").html(data.msg);
							$("#promptIdModal").modal();
						}
					},
				}); 
			}
			
			//在途运载删除运单
			function deleteCarringSendImg(id,trainId){
				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/fireTrainCbulkLoading/deleteSendImg.do",
					data : {"id":id},
					dataType : "json",
					success : function(data) {
						if(data.status==200){
							$("#deltialModal").modal("hide");
							$("#localtionBody").empty();
							$.fd.ajax({  
			   	                type: "GET",  
			   	                url:"${pageContext.request.contextPath}/fireTrainCbulkLoading/getHistoryLocationMsg.do?id="+trainId+"&type="+0,  
			   	                success: function(data) { 
			   	                	var hlTrainCargoList = data.data["hlTrainCargoList"];
									$.each(hlTrainCargoList,function(index,tcList){
										$("#localtionBody").append("<tr><td>"+ (index+1) +"</td><td>"+tcList.carType+"</td><td>"+tcList.carNumber+"</td>"
										+"<td>"+tcList.sendWeight+"</td><td name='reason"+index+"'></td></tr>")
										$("td[name='reason"+index+"']").html("");
										if(tcList.sendImg == null || tcList.sendImg ==""){
					        				$("td[name='reason"+index+"']").append("<a href='javascript:void(0)' onclick='uploadCarringImg("+tcList.id+","+tcList.trainOrderId+")' class='uploadImg'>点击上传</a>");
										}else{
											$("td[name='reason"+index+"']").append("<a href='javascript:void(0)' class='lookClass' name='"+tcList.sendImg+"' onclick='lookingImg(this)'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<a href='javascript:void(0)' class='delA' onclick='deleteCarringSendImg("+tcList.id+","+tcList.trainOrderId+")'>删除</a>"); 
					        			}
									});
			   	                }
			   	            }); 
						}else{
							$("#deltialModal").modal("hide");
							$("#errorMsg").html(data.msg);
							$("#promptIdModal").modal();
						}
					},
				}); 
			}
			
			//等待回单删除发运运单
			function deleteReceiveSendImg(id,trainId){
				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/fireTrainCbulkLoading/deleteSendImg.do",
					data : {"id":id},
					dataType : "json",
					success : function(data) {
						if(data.status==200){
							$("#deltialModal").modal("hide");
							$("#receiptionBody").empty();
							$.fd.ajax({  
			   	                type: "GET",  
			   	                url:"${pageContext.request.contextPath}/fireTrainCbulkLoading/getSendMsg.do?id="+trainId,  
			   	                success: function(data) {  
			   	                	var trainCargoList = data.data["trainCargoList"];
				   	 				$.each(trainCargoList,function(index,tcList){
				   	 					$("#receiptionBody").append("<tr><td>"+ (index+1) +"</td><td>"+tcList.carType+"</td><td>"+tcList.carNumber+"</td>"
				   	 					+"<td>"+tcList.sendWeight+"</td><td>"+tcList.unloadWeight+"</td><td name='resaonSends"+index+"'></td><td name='resaonArrives"+index+"'></td></tr>");
				   	 					$("td[name='resaonSend"+index+"']").html("");
					   	 				$("td[name='resaonSends"+index+"']").html("");
				   	 					if(tcList.sendImg == null || tcList.sendImg ==""){
				   	         				$("td[name='resaonSends"+index+"']").append("<a href='javascript:void(0)'  class='uploadImg' onclick='uploadSendImg("+tcList.id+","+tcList.trainOrderId+")'>点击上传</a>");
				   	 					}else{
				   	 						$("td[name='resaonSends"+index+"']").append("<a href='javascript:void(0)' class='lookClass' name='"+tcList.sendImg+"' onclick='lookingImg(this)'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<a href='javascript:void(0)' class='delA' onclick='deleteReceiveSendImg("+tcList.id+","+tcList.trainOrderId+")'>删除</a>"); 
				   	         			}
					   	 				$("td[name='resaonArrive"+index+"']").html("");
				   	 					if(tcList.unloadImg == null || tcList.unloadImg ==""){
				   	         				$("td[name='resaonArrives"+index+"']").append("<a href='javascript:void(0)'  class='uploadImg' onclick='uploadArriveImg("+tcList.id+","+tcList.trainOrderId+")'>点击上传</a>");
				   	 					}else{
				   	 						$("td[name='resaonArrives"+index+"']").append("<a href='javascript:void(0)' class='lookClass' name='"+tcList.unloadImg+"' onclick='lookingImg(this)'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<a href='javascript:void(0)' class='delA' onclick='deleteReceiveArriveImg("+tcList.id+","+tcList.trainOrderId+")'>删除</a>"); 
				   	         			}
					   	 			});
			   	                }
			   	            }); 
						}else{
							$("#deltialModal").modal("hide");
							$("#errorMsg").html(data.msg);
							$("#promptIdModal").modal();
						}
					},
				}); 
			}
			//等待回单删除到货运单
			function deleteReceiveArriveImg(id,trainId){
				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/fireTrainCbulkLoading/deleteArriveImg.do",
					data : {"id":id},
					dataType : "json",
					success : function(data) {
						if(data.status==200){
							$("#deltialModal").modal("hide");
							$("#receiptionBody").empty();
							$.fd.ajax({  
			   	                type: "GET",  
			   	                url:"${pageContext.request.contextPath}/fireTrainCbulkLoading/getSendMsg.do?id="+trainId,  
			   	                success: function(data) {  
			   	                	var trainCargoList = data.data["trainCargoList"];
				   	 				$.each(trainCargoList,function(index,tcList){
				   	 					$("#receiptionBody").append("<tr><td>"+ (index+1) +"</td><td>"+tcList.carType+"</td><td>"+tcList.carNumber+"</td>"
				   	 					+"<td>"+tcList.sendWeight+"</td><td>"+tcList.unloadWeight+"</td><td name='resaonSends"+index+"'></td><td name='resaonArrives"+index+"'></td></tr>");
				   	 					$("td[name='resaonSend"+index+"']").html("");
					   	 				$("td[name='resaonSends"+index+"']").html("");
				   	 					if(tcList.sendImg == null || tcList.sendImg ==""){
				   	         				$("td[name='resaonSends"+index+"']").append("<a href='javascript:void(0)'  class='uploadImg' onclick='uploadSendImg("+tcList.id+","+tcList.trainOrderId+")'>点击上传</a>");
				   	 					}else{
				   	 						$("td[name='resaonSends"+index+"']").append("<a href='javascript:void(0)' class='lookClass' name='"+tcList.sendImg+"' onclick='lookingImg(this)'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<a href='javascript:void(0)' class='delA' onclick='deleteReceiveSendImg("+tcList.id+","+tcList.trainOrderId+")'>删除</a>"); 
				   	         			}
					   	 				$("td[name='resaonArrive"+index+"']").html("");
				   	 					if(tcList.unloadImg == null || tcList.unloadImg ==""){
				   	         				$("td[name='resaonArrives"+index+"']").append("<a href='javascript:void(0)'  class='uploadImg' onclick='uploadArriveImg("+tcList.id+","+tcList.trainOrderId+")'>点击上传</a>");
				   	 					}else{
				   	 						$("td[name='resaonArrives"+index+"']").append("<a href='javascript:void(0)' class='lookClass' name='"+tcList.unloadImg+"' onclick='lookingImg(this)'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<a href='javascript:void(0)' class='delA' onclick='deleteReceiveArriveImg("+tcList.id+","+tcList.trainOrderId+")'>删除</a>"); 
				   	         			}
					   	 			});
			   	                }
			   	            }); 
						}else{
							$("#deltialModal").modal("hide");
							$("#errorMsg").html(data.msg);
							$("#promptIdModal").modal();
						}
					},
				}); 
			}
			//等待卸货删除发运运单
			function deleteSendImg(id,trainId){
				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/fireTrainCbulkLoading/deleteSendImg.do",
					data : {"id":id},
					dataType : "json",
					success : function(data) {
						if(data.status==200){
							$("#deltialModal").modal("hide");
							$("#unloadingBody").empty();
							$(".select_freight_guide").empty();
							$(".select_cargo_guide").empty();
							$.fd.ajax({  
			   	                type: "GET",  
			   	                url:"${pageContext.request.contextPath}/fireTrainCbulkLoading/getSendMsg.do?id="+trainId,  
			   	                success: function(data) {  
			   	                	var trainCargoList = data.data["trainCargoList"];
				   	 				$.each(trainCargoList,function(index,tcList){
				   	 					$("#unloadingBody").append("<tr><td style='text-align: center;'><label class='demo--label'>"
										 +" <input class='demo--checkbox'  name='unloadName' value='"+tcList.id+"' type='checkbox'   >"
			                             +"<span class='demo--checkboxInput'></span>"
			                            +"</label></td><td style='display:none'></td><td>"+ (index+1) +"</td><td>"+tcList.carType+"</td><td>"+tcList.carNumber+"</td>"
				   	 					+"<td>"+tcList.sendWeight+"</td><td><input type='text'  oninput='loadingValue(this)'  style='height:28px;border:1px solid #dde3ef; border-radius: 4px;' value="+tcList.unloadWeight+"></td><td><select name='freight_guide"+index+"' class='select_freight_guide' onchange='cargoLoExchangetd(this)'></select></td><td><select name='yard_guide"+index+"' onchange='yardLoExchangetd(this)'></select></td><td name='resaonSend"+index+"'></td><td name='resaonArrive"+index+"'></td></tr>")
				   	 					$("td[name='resaonSend"+index+"']").html("");
					   	 				if(tcList.sendImg == null || tcList.sendImg ==""){
				   	         				$("td[name='resaonSend"+index+"']").append("<a href='javascript:void(0)' id='unloadSerial"+(index+1)+"' class='uploadImg' onclick='uploadUnloadImg("+(index+1)+",this)'>点击上传</a><input type='hidden' id='hiddenUnoladId"+(index+1)+"' value='' />");
				   	 					}else{
				   	 						$("td[name='resaonSend"+index+"']").append("<a href='javascript:void(0)' class='lookClass' name='"+tcList.sendImg+"' onclick='lookingImg(this)'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<a href='javascript:void(0)' class='delA' onclick='deleteSendImg("+tcList.id+","+tcList.trainOrderId+")'>删除</a>"); 
				   	         			}
					   	 				$("td[name='resaonArrive"+index+"']").html("");
						   	 			if(tcList.unloadImg == null || tcList.unloadImg ==""){
				   	         				$("td[name='resaonArrive"+index+"']").append("<a href='javascript:void(0)' id='unloadSerialTwo"+(index+1)+"' class='uploadImg' onclick='arriveUnloadImg("+(index+1)+",this)'>点击上传</a><input type='hidden' id='hiddenUnoladIdTwo"+(index+1)+"' value='' />");
				   	 					}else{
				   	 						$("td[name='resaonArrive"+index+"']").append("<a href='javascript:void(0)' class='lookClass' name='"+tcList.unloadImg+"' onclick='lookingImg(this)'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<a href='javascript:void(0)' class='delA' onclick='deleteArriveImg("+tcList.id+","+tcList.trainOrderId+")'>删除</a>"); 
				   	         			}
				   	 					//td中的货场货位联动
				   	 					var freightYardList = data.data["freightYardList"];
					   	 					$("select[name='freight_guide"+index+"']").empty();
					   	 					$("select[name='freight_guide"+index+"']").append("<option value=''></option>");
							                $.each(freightYardList,function(io,fylt){
							                	$("select[name='freight_guide"+index+"']").append(
							    						"<option value="+fylt.id+">" + fylt.name + "</option>");
						    				});
				   	 					if(tcList.arriveCargoPlaceId == null || tcList.arriveCargoPlaceId == ""){
				   	 						
				   	 					}else{
						                	$("select[name='freight_guide"+index+"']").val(tcList.arriveCargoPlaceId);
						                	//$("select[name='freight_guide"+index+"']").change();
						                	if(tcList.arriveCargoSiteId == null || tcList.arriveCargoSiteId == ""){
					   	 						
					   	 					}else{
							   	 				$.fd.ajax({  
							   	 		             type: "GET",  
							   	 		             url:"${pageContext.request.contextPath}/siteManager/cargo/location/getAll.do?id="+tcList.arriveCargoPlaceId,  
							   	 		             success: function(data) {  
							   	 		            	$("select[name='yard_guide"+index+"']").empty();
							   	 		 				$.each(data.data, function(j, itj) {
							   	 		 				$("select[name='yard_guide"+index+"']").append("<option value="+itj.id+">"+itj.code+ "  " + itj.name + "</option>");
							   	 		 				});
							   	 		 				$("select[name='yard_guide"+index+"']").val(tcList.arriveCargoSiteId);
							   	 		             }
							   	 		         }); 
					   	 					}
				   	 					}
					   	 				
				   	 				});
					   	 			var freightYardList = data.data["freightYardList"];
					   	 			$("#select_freight_guide").empty();
					   	 			$("#select_freight_guide").append("<option value=''></option>");
					                $.each(freightYardList,function(index,fylt){
				    					$("#select_freight_guide").append(
					    						"<option value="+fylt.id+">" + fylt.name + "</option>");
				    				});
					                	//$(".select_freight_guide").change();
				   	                }
			   	            }); 
						}else{
							$("#deltialModal").modal("hide");
							$("#errorMsg").html(data.msg);
							$("#promptIdModal").modal();
						}
					},
				}); 
			}
			
			function deleteArriveImg(id,trainId){
				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/fireTrainCbulkLoading/deleteArriveImg.do",
					data : {"id":id},
					dataType : "json",
					success : function(data) {
						if(data.status==200){
							$("#deltialModal").modal("hide");
							$("#unloadingBody").empty();
							$(".select_freight_guide").empty();
							$(".select_cargo_guide").empty();
							$.fd.ajax({  
			   	                type: "GET",  
			   	                url:"${pageContext.request.contextPath}/fireTrainCbulkLoading/getSendMsg.do?id="+trainId,  
			   	                success: function(data) {  
			   	                	var trainCargoList = data.data["trainCargoList"];
				   	 				$.each(trainCargoList,function(index,tcList){
				   	 					$("#unloadingBody").append("<tr><td style='text-align: center;'><label class='demo--label'>"
										 +" <input class='demo--checkbox'  name='unloadName' value='"+tcList.id+"' type='checkbox'   >"
			                             +"<span class='demo--checkboxInput'></span>"
			                            +"</label></td><td style='display:none'></td><td>"+ (index+1) +"</td><td>"+tcList.carType+"</td><td>"+tcList.carNumber+"</td>"
				   	 					+"<td>"+tcList.sendWeight+"</td><td><input type='text'  oninput='loadingValue(this)'  style='height:28px;border:1px solid #dde3ef; border-radius: 4px;' value="+tcList.unloadWeight+"></td><td><select name='freight_guide"+index+"' class='select_freight_guide' onchange='cargoLoExchangetd(this)'></select></td><td><select name='yard_guide"+index+"' onchange='yardLoExchangetd(this)'></select></td><td name='resaonSend"+index+"'></td><td name='resaonArrive"+index+"'></td></tr>")
				   	 					$("td[name='resaonSend"+index+"']").html("");
					   	 				if(tcList.sendImg == null || tcList.sendImg ==""){
				   	         				$("td[name='resaonSend"+index+"']").append("<a href='javascript:void(0)' id='unloadSerial"+(index+1)+"' class='uploadImg' onclick='uploadUnloadImg("+(index+1)+",this)'>点击上传</a><input type='hidden' id='hiddenUnoladId"+(index+1)+"' value='' />");
				   	 					}else{
				   	 						$("td[name='resaonSend"+index+"']").append("<a href='javascript:void(0)' class='lookClass' name='"+tcList.sendImg+"' onclick='lookingImg(this)'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<a href='javascript:void(0)' class='delA' onclick='deleteSendImg("+tcList.id+","+tcList.trainOrderId+")'>删除</a>"); 
				   	         			}
					   	 				$("td[name='resaonArrive"+index+"']").html("");
						   	 			if(tcList.unloadImg == null || tcList.unloadImg ==""){
				   	         				$("td[name='resaonArrive"+index+"']").append("<a href='javascript:void(0)' id='unloadSerialTwo"+(index+1)+"' class='uploadImg' onclick='arriveUnloadImg("+(index+1)+",this)'>点击上传</a><input type='hidden' id='hiddenUnoladIdTwo"+(index+1)+"' value='' />");
				   	 					}else{
				   	 						$("td[name='resaonArrive"+index+"']").append("<a href='javascript:void(0)' class='lookClass' name='"+tcList.unloadImg+"' onclick='lookingImg(this)'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<a href='javascript:void(0)' class='delA' onclick='deleteArriveImg("+tcList.id+","+tcList.trainOrderId+")'>删除</a>"); 
				   	         			}
				   	 					//td中的货场货位联动
				   	 					var freightYardList = data.data["freightYardList"];
					   	 					$("select[name='freight_guide"+index+"']").empty();
					   	 					$("select[name='freight_guide"+index+"']").append("<option value=''></option>");
							                $.each(freightYardList,function(io,fylt){
							                	$("select[name='freight_guide"+index+"']").append(
							    						"<option value="+fylt.id+">" + fylt.name + "</option>");
						    				});
				   	 					if(tcList.arriveCargoPlaceId == null || tcList.arriveCargoPlaceId == ""){
				   	 						
				   	 					}else{
						                	$("select[name='freight_guide"+index+"']").val(tcList.arriveCargoPlaceId);
						                	//$("select[name='freight_guide"+index+"']").change();
						                	if(tcList.arriveCargoSiteId == null || tcList.arriveCargoSiteId == ""){
					   	 						
					   	 					}else{
							   	 				$.fd.ajax({  
							   	 		             type: "GET",  
							   	 		             url:"${pageContext.request.contextPath}/siteManager/cargo/location/getAll.do?id="+tcList.arriveCargoPlaceId,  
							   	 		             success: function(data) {  
							   	 		            	$("select[name='yard_guide"+index+"']").empty();
							   	 		 				$.each(data.data, function(j, itj) {
							   	 		 				$("select[name='yard_guide"+index+"']").append("<option value="+itj.id+">"+itj.code+ "  " + itj.name + "</option>");
							   	 		 				});
							   	 		 				$("select[name='yard_guide"+index+"']").val(tcList.arriveCargoSiteId);
							   	 		             }
							   	 		         }); 
					   	 					}
				   	 					}
					   	 				
				   	 				});
					   	 			var freightYardList = data.data["freightYardList"];
					   	 			$("#select_freight_guide").empty();
					   	 			$("#select_freight_guide").append("<option value=''></option>");
					                $.each(freightYardList,function(index,fylt){
				    					$("#select_freight_guide").append(
					    						"<option value="+fylt.id+">" + fylt.name + "</option>");
				    				});
					                	//$(".select_freight_guide").change();
				   	                }
			   	            }); 
						}else{
							$("#deltialModal").modal("hide");
							$("#errorMsg").html(data.msg);
							$("#promptIdModal").modal();
						}
					},
				}); 
			}
			
			/* function lookingImg(param){
	   		 	$("#lookImg").modal();
	   		 	$("#look_img").attr('src',"${pageContext.request.contextPath}/upload/photo/"+param.name);
	   		} */
	   		function lookingImg(param){
	   		 	$("#lookImg").modal();
	   		 	$('#showMask', window.parent.document).show();
	   		 	$("#look_img").attr('src',"/upload/photo/"+param.name);
	   		}
			
			//等待回单上传图片模态框
			function uploadSendImg(id,trainOrderId){
				$("#uploadModal").modal();
				$('#showMask', window.parent.document).show();
				$("#testImg").attr('src',"");
				$(".preview").hide();
				$("#basePhoto").val("");
				$("#arriveImgId").val("");
				$("#sendImgId").val(id);
				$("#waitSendPhotoId").val("");
				$("#waitSendPhotoId").val(trainOrderId);
			}
			
			function uploadArriveImg(id,trainOrderId){
				$("#uploadModal").modal();
				$('#showMask', window.parent.document).show();
				$("#testImg").attr('src',"");
				$(".preview").hide();
				$("#basePhoto").val("");
				$("#sendImgId").val("");
				$("#arriveImgId").val(id);
				$("#waitSendPhotoId").val("");
				$("#waitSendPhotoId").val(trainOrderId);
			}
			//等待卸货上传图片模态框
			function uploadUnloadImg(par,param){
				$("#uploadUnloadModal").modal();
				$('#showMask', window.parent.document).show();
				$("#testUnloadImg").attr('src',"");
				$(".preview").hide();
				$("#baseUnloadPhoto").val("");
				$("#hiddenTdId").val("");
				$("#hiddenTdId").val(par);
				var unloadIdsi = $(param).parent().prev().prev().prev().prev().prev().prev().prev().prev().prev().find("input").val();
				var unloadHideIdsi = $(param).parent().prev().prev().prev().prev().prev().prev().prev().prev();
				unloadHideIdsi.html(unloadIdsi);
			}
			
			//等待卸货卸货运单上传图片模态框
			function arriveUnloadImg(par,param){
				$("#arriveUnloadModal").modal();
				$('#showMask', window.parent.document).show();
				$("#testArriveImg").attr('src',"");
				$(".preview").hide();
				$("#baseArrivePhoto").val("");
				$("#hiddenTdIdTwo").val("");
				$("#hiddenTdIdTwo").val(par);
				var unloadIdai = $(param).parent().prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().find("input").val();
				var unloadHideIdai = $(param).parent().prev().prev().prev().prev().prev().prev().prev().prev().prev();
				unloadHideIdai.html(unloadIdai);
			}
			function closeUnloadModal(){
				$("#uploadUnloadModal").modal("hide");
				var serial = $("#hiddenTdId").val();
				if($("#baseUnloadPhoto").val() != null || $("#baseUnloadPhoto").val() != ""){
					$("#unloadSerial"+serial+"").html("重新上传");
					$("#hiddenUnoladId"+serial+"").val($("#baseUnloadPhoto").val());
				}
			}
			
			function closeUnloadModal2(){
				$("#arriveUnloadModal").modal("hide");
				var serial = $("#hiddenTdIdTwo").val();
				if($("#baseArrivePhoto").val() != null || $("#baseArrivePhoto").val() != ""){
					$("#unloadSerialTwo"+serial+"").html("重新上传");
					$("#hiddenUnoladIdTwo"+serial+"").val($("#baseArrivePhoto").val());
				}
			}
			
			//等待发运上传图片模态框
			function uploadSendPicture(id,trainOrderId){
				$("#uploadSendModal").modal();
				$('#showMask', window.parent.document).show();
				$("#testSendImg").attr('src',"");
				$(".preview").hide();
				$("#baseSendPhoto").val("");
				$("#sendPictureId").val(id);
				$("#sendPhotoId").val("");
				$("#sendPhotoId").val(trainOrderId);
			}
			//在途运载上传图片模态框
			function uploadCarringImg(id,trainOrderId){
				$("#uploadCarringModal").modal();
				$('#showMask', window.parent.document).show();
				$("#testCarringImg").attr('src',"");
				$("#baseCarringPhoto").val("");
				$(".preview").hide();
				$("#carringImgId").val(id);
				$("#carringPhotoId").val("");
				$("#carringPhotoId").val(trainOrderId);
			}
			//在途运载上传运单图片
			function updCarringImg(){
				var id = "";
				var sendImgId = $("#carringImgId").val();
				var img =$("#baseCarringPhoto").val();
				var p =$("#carringPhotoId").val();
				if(sendImgId != null || sendImgId != ""){
					id = sendImgId;
					$.ajax({
						type : 'POST',
						url : "${pageContext.request.contextPath}/fireTrainCbulkLoading/updateSendImg.do",
						data : {"id":id,"sendImg":img},
						dataType : "json",
						success : function(data) {
							if(data.status==200){
								$("#uploadCarringModal").modal("hide");
								$("#deltialModal").modal("hide");
								$("#localtionBody").empty();
								$.fd.ajax({  
				   	                type: "GET",  
				   	                url:"${pageContext.request.contextPath}/fireTrainCbulkLoading/getSendMsg.do?id="+p,  
				   	                success: function(data) { 
				   	                	var hlTrainCargoList = data.data["trainCargoList"];
										$.each(hlTrainCargoList,function(index,tcList){
											$("#localtionBody").append("<tr><td>"+ (index+1) +"</td><td>"+tcList.carType+"</td><td>"+tcList.carNumber+"</td>"
											+"<td>"+tcList.sendWeight+"</td><td name='reason"+index+"'></td></tr>")
											$("td[name='reason"+index+"']").html("");
											if(tcList.sendImg == null || tcList.sendImg ==""){
						        				$("td[name='reason"+index+"']").append("<a href='javascript:void(0)' onclick='uploadCarringImg("+tcList.id+","+tcList.trainOrderId+")' class='uploadImg'>点击上传</a>");
											}else{
												$("td[name='reason"+index+"']").append("<a href='javascript:void(0)' class='lookClass' name='"+tcList.sendImg+"' onclick='lookingImg(this)'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<a href='javascript:void(0)' class='delA' onclick='deleteCarringSendImg("+tcList.id+","+tcList.trainOrderId+")'>删除</a>"); 
						        			}
										});
				   	                }
				   	            }); 
							}else{
								$("#uploadModal").modal("hide");
								$("#deltialModal").modal("hide");
								$("#errorMsg").html(data.msg);
								$("#promptIdModal").modal();
							}
						},
					}); 
				}
			}
			
			//等待发运上传运单图片
			function updWaitSendImg(){
				var id = "";
				var sendImgId = $("#sendPictureId").val();
				var img =$("#baseSendPhoto").val();
				var p =$("#sendPhotoId").val();
				if(sendImgId != null || sendImgId != ""){
					id = sendImgId;
					$.ajax({
						type : 'POST',
						url : "${pageContext.request.contextPath}/fireTrainCbulkLoading/updateSendImg.do",
						data : {"id":id,"sendImg":img},
						dataType : "json",
						success : function(data) {
							if(data.status==200){
								$("#uploadSendModal").modal("hide");
								$("#deltialModal").modal("hide");
								$("#trainCargoBody").empty();
								$.fd.ajax({  
				   	                type: "GET",  
				   	                url:"${pageContext.request.contextPath}/fireTrainCbulkLoading/getSendMsg.do?id="+p,  
				   	                success: function(data) { 
				   	                	var trainCargoList = data.data["trainCargoList"];
										$.each(trainCargoList,function(index,tcList){
											$("#trainCargoBody").append("<tr><td>"+ (index+1) +"</td><td>"+tcList.carType+"</td><td>"+tcList.carNumber+"</td>"
											+"<td>"+tcList.sendWeight+"</td><td name='resaons"+index+"'></td></tr>")
											$("td[name='resaons"+index+"']").html("");
											if(tcList.sendImg == null || tcList.sendImg ==""){
						        				$("td[name='resaons"+index+"']").append("<a href='javascript:void(0)'  class='uploadImg' onclick='uploadSendPicture("+tcList.id+","+tcList.trainOrderId+")'>点击上传</a>");
											}else{
												$("td[name='resaons"+index+"']").append("<a href='javascript:void(0)' class='lookClass' name='"+tcList.sendImg+"' onclick='lookingImg(this)'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<a href='javascript:void(0)' class='delA' onclick='deleteWaitSendImg("+tcList.id+","+tcList.trainOrderId+")'>删除</a>"); 
						        			}
										});
				   	                }
				   	            }); 
							}else{
								$("#uploadModal").modal("hide");
								$("#deltialModal").modal("hide");
								$("#errorMsg").html(data.msg);
								$("#promptIdModal").modal();
							}
						},
					}); 
				}
			}
			//等待回单
			function updSendImg(){
				var id = "";
				var arriveImgId = $("#arriveImgId").val();
				var sendImgId = $("#sendImgId").val();
				var img =$("#basePhoto").val();
				var p = $("#waitSendPhotoId").val();
				if((sendImgId != null || sendImgId != "") && (arriveImgId == null || arriveImgId == "")){
					id = sendImgId;
					$.ajax({
						type : 'POST',
						url : "${pageContext.request.contextPath}/fireTrainCbulkLoading/updateSendImg.do",
						data : {"id":id,"sendImg":img},
						dataType : "json",
						success : function(data) {
							if(data.status==200){
								$("#uploadModal").modal("hide");
								$("#deltialModal").modal("hide");
								$("#receiptionBody").empty();
								$.fd.ajax({  
				   	                type: "GET",  
				   	                url:"${pageContext.request.contextPath}/fireTrainCbulkLoading/getSendMsg.do?id="+p,  
				   	                success: function(data) {  
				   	                	var trainCargoList = data.data["trainCargoList"];
					   	 				$.each(trainCargoList,function(index,tcList){
					   	 					$("#receiptionBody").append("<tr><td>"+ (index+1) +"</td><td>"+tcList.carType+"</td><td>"+tcList.carNumber+"</td>"
					   	 					+"<td>"+tcList.sendWeight+"</td><td>"+tcList.unloadWeight+"</td><td name='resaonSends"+index+"'></td><td name='resaonArrives"+index+"'></td></tr>");
						   	 				$("td[name='resaonSends"+index+"']").html("");
					   	 					if(tcList.sendImg == null || tcList.sendImg ==""){
					   	         				$("td[name='resaonSends"+index+"']").append("<a href='javascript:void(0)'  class='uploadImg' onclick='uploadSendImg("+tcList.id+","+tcList.trainOrderId+")'>点击上传</a>");
					   	 					}else{
					   	 						$("td[name='resaonSends"+index+"']").append("<a href='javascript:void(0)' class='lookClass' name='"+tcList.sendImg+"' onclick='lookingImg(this)'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<a href='javascript:void(0)' class='delA' onclick='deleteReceiveSendImg("+tcList.id+","+tcList.trainOrderId+")'>删除</a>"); 
					   	         			}
						   	 				$("td[name='resaonArrive"+index+"']").html("");
					   	 					if(tcList.unloadImg == null || tcList.unloadImg ==""){
					   	         				$("td[name='resaonArrives"+index+"']").append("<a href='javascript:void(0)'  class='uploadImg' onclick='uploadArriveImg("+tcList.id+","+tcList.trainOrderId+")'>点击上传</a>");
					   	 					}else{
					   	 						$("td[name='resaonArrives"+index+"']").append("<a href='javascript:void(0)' class='lookClass' name='"+tcList.unloadImg+"' onclick='lookingImg(this)'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<a href='javascript:void(0)' class='delA' onclick='deleteReceiveArriveImg("+tcList.id+","+tcList.trainOrderId+")'>删除</a>"); 
					   	         			}
						   	 			});
				   	                }
				   	            }); 
							}else{
								$("#uploadModal").modal("hide");
								$("#deltialModal").modal("hide");
								$("#errorMsg").html(data.msg);
								$("#promptIdModal").modal();
							}
						},
					}); 
				}else{
					id = arriveImgId;
					$.ajax({
						type : 'POST',
						url : "${pageContext.request.contextPath}/fireTrainCbulkLoading/updateArriveImg.do",
						data : {"id":id,"arriveImg":img},
						dataType : "json",
						success : function(data) {
							if(data.status==200){
								$("#uploadModal").modal("hide");
								$("#deltialModal").modal("hide");
								$("#receiptionBody").empty();
								$.fd.ajax({  
				   	                type: "GET",  
				   	                url:"${pageContext.request.contextPath}/fireTrainCbulkLoading/getSendMsg.do?id="+p,  
				   	                success: function(data) {  
				   	                	var trainCargoList = data.data["trainCargoList"];
					   	 				$.each(trainCargoList,function(index,tcList){
					   	 					$("#receiptionBody").append("<tr><td>"+ (index+1) +"</td><td>"+tcList.carType+"</td><td>"+tcList.carNumber+"</td>"
					   	 					+"<td>"+tcList.sendWeight+"</td><td>"+tcList.unloadWeight+"</td><td name='resaonSends"+index+"'></td><td name='resaonArrives"+index+"'></td></tr>");
						   	 				$("td[name='resaonSends"+index+"']").html("");
					   	 					if(tcList.sendImg == null || tcList.sendImg ==""){
					   	         				$("td[name='resaonSends"+index+"']").append("<a href='javascript:void(0)'  class='uploadImg' onclick='uploadSendImg("+tcList.id+","+tcList.trainOrderId+")'>点击上传</a>");
					   	 					}else{
					   	 						$("td[name='resaonSends"+index+"']").append("<a href='javascript:void(0)' class='lookClass' name='"+tcList.sendImg+"' onclick='lookingImg(this)'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<a href='javascript:void(0)' class='delA' onclick='deleteReceiveSendImg("+tcList.id+","+tcList.trainOrderId+")'>删除</a>"); 
					   	         			}
						   	 				$("td[name='resaonArrive"+index+"']").html("");
					   	 					if(tcList.unloadImg == null || tcList.unloadImg ==""){
					   	         				$("td[name='resaonArrives"+index+"']").append("<a href='javascript:void(0)'  class='uploadImg' onclick='uploadArriveImg("+tcList.id+","+tcList.trainOrderId+")'>点击上传</a>");
					   	 					}else{
					   	 						$("td[name='resaonArrives"+index+"']").append("<a href='javascript:void(0)' class='lookClass' name='"+tcList.unloadImg+"' onclick='lookingImg(this)'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<a href='javascript:void(0)' class='delA' onclick='deleteReceiveArriveImg("+tcList.id+","+tcList.trainOrderId+")'>删除</a>"); 
					   	         			}
						   	 			});
				   	                }
				   	            }); 
							}else{
								$("#uploadModal").modal("hide");
								$("#deltialModal").modal("hide");
								$("#errorMsg").html(data.msg);
								$("#promptIdModal").modal();
							}
						},
					}); 
				}
			}
			
			function testFileChange(file){
				run(file, function(data) {
					$("#preview").show();
					$('#testImg').attr('src', data);
					$("#basePhoto").val($('#testImg').attr('src'));
				});
			}
			
			function unloadFileChange(file){
				run(file, function(data) {
					$(".preview").show();
					$('#testUnloadImg').attr('src', data);
					$("#baseUnloadPhoto").val($('#testUnloadImg').attr('src'));
				});
			}
			
			function arriveUnloadChange(file){
				run(file, function(data) {
					$(".preview").show();
					$('#testArriveImg').attr('src', data);
					$("#baseArrivePhoto").val($('#testArriveImg').attr('src'));
				});
			}
			
			function testFileCarringChange(file){
				run(file, function(data) {
					$(".preview").show();
					$('#testCarringImg').attr('src', data);
					$("#baseCarringPhoto").val($('#testCarringImg').attr('src'));
				});
			}
			
			function testFileSendChange(file){
				run(file, function(data) {
					$(".preview").show();
					$('#testSendImg').attr('src', data);
					$("#baseSendPhoto").val($('#testSendImg').attr('src'));
				});
			}
			
			function run(input_file, get_data) { 
	            /*input_file：文件按钮对象*/  
	            /*get_data: 转换成功后执行的方法*/  
	            if (typeof (FileReader) === 'undefined') {  
	            	$("#errorMsg").html("");
					$("#promptIdModal").modal();
	     			$("#errorMsg").html("抱歉，你的浏览器不支持 FileReader，不能将图片转换为Base64，请使用现代浏览器操作！");
	            } else {  
	                try {  
	                    /*图片转Base64 核心代码*/  
	                    var file = input_file.files[0];
	                    //这里我们判断下类型如果不是图片就返回 去掉就可以上传任意文件  
	                    if (!/image\/\w+/.test(file.type)) {  
	                    	$("#errorMsg").html("");
	    					$("#promptIdModal").modal();
	             			$("#errorMsg").html("请确保文件为图像类型");
	                        return false;  
	                    } 
	                    if(file.size>5242880){
	                    	$("#errorMsg").html("");
	    					$("#promptIdModal").modal();
	             			$("#errorMsg").html("请上传大小小于5M的图片");
	                    	 return false;  
	                    }
	                    var reader = new FileReader();  
	                    reader.onload = function () {  
	                        get_data(this.result);  
	                    }  
	                    reader.readAsDataURL(file);  
	                } catch (e) {  
	                	$("#errorMsg").html("");
						$("#promptIdModal").modal();
	        			$("#errorMsg").html('图片转Base64出错啦！');
	                   // alert( + e.toString())  
	                }  
	            }  
	        }  
			
			//等待发运 和在途运载 等待回单 确定后更新运单状态
			function updOrderStatus(param){
				var id = "";
				if(param == 4){
					id = $("#orderIdSend").val();
				}else if(param == 5){
					id = $("#orderIdLocation").val();
					if(id == null || id == "" || id == undefined){
						$("#errorMsg").html("请更新位置信息");
						$("#promptIdModal").modal();
						return;
					}
				}/* else if(param == 6){
					id = $("#orderIdUnload").val();
				} *//* else{
					id = $("#orderIdReceiption").val();
				} */
				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/fireTrainCbulkLoading/updOrderStatusByParam.do",
					data : {"id":id,"status":param},
					dataType : "json",
					success : function(data) {
						if(data.status==200){
							$("#deltialModal").modal("hide");
							$("#warnMsg").html("操作成功");
							$("#nullModal").modal();
						}else{
							$("#deltialModal").modal("hide");
							$("#errorMsg").html(data.msg);
							$("#promptIdModal").modal();
						}
					},
				}); 
			}
			
			function updOrderStatusByImg(){
				var id = $("#orderIdReceiption").val();
				var flag = true;
				$.fd.ajax({  
   	                type: "GET",  
   	                url:"${pageContext.request.contextPath}/fireTrainCbulkLoading/getSendMsg.do?id="+id,  
   	             	async:false, 
   	                success: function(data) {  
   	                	var trainCargoList = data.data["trainCargoList"];
	   	 				$.each(trainCargoList,function(index,tcList){
	   	 					if(tcList.sendImg == null || tcList.sendImg ==""){
	   	 						flag = false;
	   	 					}
	   	         			if(tcList.unloadImg == null || tcList.unloadImg ==""){
	   	         				flag = false;
	   	 					}
		   	 			});
   	                }
   	            }); 
				if(flag){
					$.ajax({
						type : 'POST',
						url : "${pageContext.request.contextPath}/fireTrainCbulkLoading/updOrderStatusByParam.do",
						data : {"id":id,"status":7},
						dataType : "json",
						async:false, 
						success : function(data) {
							if(data.status==200){
								$("#deltialModal").modal("hide");
								$("#warnMsg").html("操作成功");
								$("#nullModal").modal();
							}else{
								$("#deltialModal").modal("hide");
								$("#errorMsg").html(data.msg);
								$("#promptIdModal").modal();
							}
						},
					});
				}else{
					$("#errorMsg").html("运单必须全部上传");
					$("#promptIdModal").modal();
				}
			}
		</script>
	</head>
	
	<!--搜索-->
	<script>
		function searchStatus(param){
			$("#orderStatus").val("");
			$("#orderStatus").val(param);
		}
	
    	function search(param){
			//var search = $(param).parent('a').parent('div').parent('form').serialize();
			var nums = 1;
			var orderStatus = $("#orderStatus").val();
			if(orderStatus == 1){
				$('#normalOrderBody').empty();
				$.ajax({  
		        	type:'post',      
		       		url:'${pageContext.request.contextPath}/fireTrainCbulkLoading/selectTrainOrderByPage.do',  
			        data:{page:nums,trainOrderSearch:JSON.stringify($("#searchForm").serializeJSON())},
			        cache:false,
			        dataType:'json',
		        	success:function(data){
		        		if(data.status==200){
		        			$("#page_normal").paging({
		        				pageNo:1,
		        				totalPage:data.data.totalPage,
		        				totalSize:data.data.limit,
		        				callback: function(num) {
		        							$.ajax({  
		        				        	type:'post',      
		        				       		url:'${pageContext.request.contextPath}/fireTrainCbulkLoading/selectTrainOrderByPage.do',  
		        					        data:{page:num,trainOrderSearch:JSON.stringify($("#searchForm").serializeJSON())},
		        					        cache:false,
		        					        dataType:'json',
		        				        	success:function(data){
		        				        		if(data.status==200){
		        				        		var trainOrderList = data.data.rows;
		        				        		$('#normalOrderBody').empty();
		        				        		$.each(trainOrderList,function(index,trainOrders){
		        				        			if(trainOrders.status != 7){
		        				        				$('#normalOrderBody').append("<tr id="+trainOrders.id+">"
		        												+"<td style='text-align: center;'><label class='demo--label'>"
		        												 +" <input class='demo--checkbox'  name='WaybillName' value='"+trainOrders.id+"' type='checkbox'   >"
		        					                              +"<span class='demo--checkboxInput'></span>"
		        					                             +"</label></td>"
		        					                             +"<td id=''>"+trainOrders.projectCode+"</td>"
		        												 +"<td id=''><a href='javascript:void(0)'</a>"+trainOrders.pleaseTrainNumber+"</td>"
		        											 +"<td id=''>"+trainOrders.createDate+"</td>"
		        											 +"<td name='resaon"+index+"'></td>"
		        											 +"<td id=''>"+trainOrders.updateDate+"</td>"
		        											 +"<td id=''>"+trainOrders.sendOperatorId+"</td>"
		        											 +"<td id=''>"+trainOrders.branchName+"</td>"
		        											 +"<td id=''>"+trainOrders.cargoName+"</td>"
		        											 +"<td id=''>"+trainOrders.beginSite+"</td>"
		        											 +"<td id=''>"+trainOrders.entruckDate+"</td>"
		        											 +"<td id=''>"+trainOrders.sendDate+"</td>"
		        											 +"<td id=''>"+trainOrders.endSite+"</td>"
		        											 +"<td id=''>"+trainOrders.pleaseCarNum+"</td>"
		        											 +"<td id=''>"+trainOrders.sureCarNum+"</td>"
		        											 +"<td id=''>"+trainOrders.loseCarNum+"</td>"
		        											 +"</tr>");
		        						        			$("td[name='resaon"+index+"']").html("");
		        						        			if(trainOrders.status==1){
		        						        				$("td[name='resaon"+index+"']").append("<a href='javascript:void(0)'  id='admit' onclick='admiter("+trainOrders.id+")'>等待承认</a>"); 
		        						        			}
		        						        			else if(trainOrders.status==2){
		        						        				$("td[name='resaon"+index+"']").append("<a href='javascript:void(0)'  id='loading' onclick='loadingEntruck("+trainOrders.id+")'>等待装车</a>"); 
		        						        			}else if(trainOrders.status==3){
		        						        				$("td[name='resaon"+index+"']").append("<a href='javascript:void(0)'  id='send' onclick='sending("+trainOrders.id+")'>等待发运</a>"); 
		        						        			}else if(trainOrders.status==4){
		        						        				$("td[name='resaon"+index+"']").append("<a href='javascript:void(0)'  id='carry' onclick='carrying("+trainOrders.id+")'>在途运载</a>"); 
		        						        			}else if(trainOrders.status==5){
		        						        				$("td[name='resaon"+index+"']").append("<a href='javascript:void(0)'  id='unloading' onclick='waitUnloading("+trainOrders.id+")'>等待卸货</a>"); 
		        						        			}else if(trainOrders.status==6){
		        						        				$("td[name='resaon"+index+"']").append("<a href='javascript:void(0)'  id='receipt' onclick='receipting("+trainOrders.id+")'>等待回单</a>"); 
		        						        			}
		        				        			}
		        				        		});	
		        				    		}
		        				      	}
		        					});
		        							}
		        				});
		        		var trainOrderList = data.data.rows;
		        		$('#normalOrderBody').empty();
		        		$.each(trainOrderList,function(index,trainOrders){
		        			if(trainOrders.status != 7){
		        				$('#normalOrderBody').append("<tr id="+trainOrders.id+">"
										+"<td style='text-align: center;'><label class='demo--label'>"
										 +" <input class='demo--checkbox'  name='WaybillName' value='"+trainOrders.id+"' type='checkbox'   >"
			                              +"<span class='demo--checkboxInput'></span>"
			                             +"</label></td>"
			                             +"<td id=''>"+trainOrders.projectCode+"</td>"
										 +"<td id=''><a href='javascript:void(0)'</a>"+trainOrders.pleaseTrainNumber+"</td>"
									 +"<td id=''>"+trainOrders.createDate+"</td>"
									 +"<td name='resaon"+index+"'></td>"
									 +"<td id=''>"+trainOrders.updateDate+"</td>"
									 +"<td id=''>"+trainOrders.sendOperatorId+"</td>"
									 +"<td id=''>"+trainOrders.branchName+"</td>"
									 +"<td id=''>"+trainOrders.cargoName+"</td>"
									 +"<td id=''>"+trainOrders.beginSite+"</td>"
									 +"<td id=''>"+trainOrders.entruckDate+"</td>"
									 +"<td id=''>"+trainOrders.sendDate+"</td>"
									 +"<td id=''>"+trainOrders.endSite+"</td>"
									 +"<td id=''>"+trainOrders.pleaseCarNum+"</td>"
									 +"<td id=''>"+trainOrders.sureCarNum+"</td>"
									 +"<td id=''>"+trainOrders.loseCarNum+"</td>"
									 +"</tr>");
				        			$("td[name='resaon"+index+"']").html("");
				        			if(trainOrders.status==1){
				        				$("td[name='resaon"+index+"']").append("<a href='javascript:void(0)'  id='admit' onclick='admiter("+trainOrders.id+")'>等待承认</a>"); 
				        			}
				        			else if(trainOrders.status==2){
				        				$("td[name='resaon"+index+"']").append("<a href='javascript:void(0)'  id='loading' onclick='loadingEntruck("+trainOrders.id+")'>等待装车</a>"); 
				        			}else if(trainOrders.status==3){
				        				$("td[name='resaon"+index+"']").append("<a href='javascript:void(0)'  id='send' onclick='sending("+trainOrders.id+")'>等待发运</a>"); 
				        			}else if(trainOrders.status==4){
				        				$("td[name='resaon"+index+"']").append("<a href='javascript:void(0)'  id='carry' onclick='carrying("+trainOrders.id+")'>在途运载</a>"); 
				        			}else if(trainOrders.status==5){
				        				$("td[name='resaon"+index+"']").append("<a href='javascript:void(0)'  id='unloading' onclick='waitUnloading("+trainOrders.id+")'>等待卸货</a>"); 
				        			}else if(trainOrders.status==6){
				        				$("td[name='resaon"+index+"']").append("<a href='javascript:void(0)'  id='receipt' onclick='receipting("+trainOrders.id+")'>等待回单</a>"); 
				        			}
		        				}
		        			});	
		    			}
		      		}
				});
			}else if(orderStatus == 2){
				$('#exceptionBody').empty();
				$.ajax({  
		        	type:'post',      
		       		url:'${pageContext.request.contextPath}/fireTrainCbulkLoading/selectExceptionOrderByPage.do',  
			        data:{page:nums,trainOrderSearch:JSON.stringify($("#searchForm").serializeJSON())},
			        cache:false,
			        dataType:'json',
		        	success:function(data){
		        		if(data.status==200){
		        			$("#page_exception").paging({
		        				pageNo:1,
		        				totalPage:data.data.totalPage,
		        				totalSize:data.data.limit,
		        				callback: function(num) {
		        							$.ajax({  
		        				        	type:'post',      
		        				       		url:'${pageContext.request.contextPath}/fireTrainCbulkLoading/selectExceptionOrderByPage.do',  
		        					        data:{page:num,trainOrderSearch:JSON.stringify($("#searchForm").serializeJSON())},
		        					        cache:false,
		        					        dataType:'json',
		        				        	success:function(data){
		        				        		if(data.status==200){
		        				        		var trainOrderList = data.data.rows;
		        				        		$('#exceptionBody').empty();
		        				        		$.each(trainOrderList,function(index,exceptions){
		        				        			if(exceptions.status != 7){
		        				        				$('#exceptionBody').append("<tr id="+exceptions.id+">"
		        												+"<td style='text-align: center;'><label class='demo--label'>"
		        												 +" <input class='demo--checkbox'  name='WaybillName' value='"+exceptions.id+"' type='checkbox'   >"
		        					                              +"<span class='demo--checkboxInput'></span>"
		        					                             +"</label></td>"
		        					                             +"<td id=''>"+exceptions.projectCode+"</td>"
		        												 +"<td id=''><a href='javascript:void(0)'</a>"+exceptions.pleaseTrainNumber+"</td>"
		        											 +"<td id=''>"+exceptions.createDate+"</td>"
		        											 +"<td name='resaon"+index+"'></td>"
		        											 +"<td id=''>"+exceptions.updateDate+"</td>"
		        											 +"<td id=''>"+exceptions.sendOperatorId+"</td>"
		        											 +"<td id=''>"+exceptions.branchName+"</td>"
		        											 +"<td id=''>"+exceptions.cargoName+"</td>"
		        											 +"<td id=''>"+exceptions.beginSite+"</td>"
		        											 +"<td id=''>"+exceptions.entruckDate+"</td>"
		        											 +"<td id=''>"+exceptions.sendDate+"</td>"
		        											 +"<td id=''>"+exceptions.endSite+"</td>"
		        											 +"<td id=''>"+exceptions.pleaseCarNum+"</td>"
		        											 +"<td id=''>"+exceptions.sureCarNum+"</td>"
		        											 +"<td id=''>"+exceptions.loseCarNum+"</td>"
		        											 +"<td id=''>"+exceptions.exceptionReportDate+"</td>"
		        											 +"<td id=''>"+exceptions.exceptionReportPerson+"</td>"
		        											 +"<td id=''>"+exceptions.exceptionReason+"</td>"
		        											 +"</tr>");
		        						        			$("td[name='resaon"+index+"']").html("");
		        						        			if(exceptions.status==1){
		        						        				$("td[name='resaon"+index+"']").append("等待承认"); 
		        						        			}
		        						        			else if(exceptions.status==2){
		        						        				$("td[name='resaon"+index+"']").append("等待装车"); 
		        						        			}else if(exceptions.status==3){
		        						        				$("td[name='resaon"+index+"']").append("等待发运"); 
		        						        			}else if(exceptions.status==4){
		        						        				$("td[name='resaon"+index+"']").append("在途运载"); 
		        						        			}else if(exceptions.status==5){
		        						        				$("td[name='resaon"+index+"']").append("等待卸货"); 
		        						        			}else if(exceptions.status==6){
		        						        				$("td[name='resaon"+index+"']").append("等待回单"); 
		        						        			}
		        				        			}
		        				        		});	
		        				    		}
		        				      	}
		        					});
		        							}
		        				});
		        		var trainOrderList = data.data.rows;
		        		$('#exceptionBody').empty();
		        		$.each(trainOrderList,function(index,exceptions){
		        			if(exceptions.status != 7){
		        				$('#exceptionBody').append("<tr id="+exceptions.id+">"
										+"<td style='text-align: center;'><label class='demo--label'>"
										 +" <input class='demo--checkbox'  name='WaybillName' value='"+exceptions.id+"' type='checkbox'   >"
			                              +"<span class='demo--checkboxInput'></span>"
			                             +"</label></td>"
			                             +"<td id=''>"+exceptions.projectCode+"</td>"
										 +"<td id=''><a href='javascript:void(0)'</a>"+exceptions.pleaseTrainNumber+"</td>"
									 +"<td id=''>"+exceptions.createDate+"</td>"
									 +"<td name='resaon"+index+"'></td>"
									 +"<td id=''>"+exceptions.updateDate+"</td>"
									 +"<td id=''>"+exceptions.sendOperatorId+"</td>"
									 +"<td id=''>"+exceptions.branchName+"</td>"
									 +"<td id=''>"+exceptions.cargoName+"</td>"
									 +"<td id=''>"+exceptions.beginSite+"</td>"
									 +"<td id=''>"+exceptions.entruckDate+"</td>"
									 +"<td id=''>"+exceptions.sendDate+"</td>"
									 +"<td id=''>"+exceptions.endSite+"</td>"
									 +"<td id=''>"+exceptions.pleaseCarNum+"</td>"
									 +"<td id=''>"+exceptions.sureCarNum+"</td>"
									 +"<td id=''>"+exceptions.loseCarNum+"</td>"
									 +"<td id=''>"+exceptions.exceptionReportDate+"</td>"
									 +"<td id=''>"+exceptions.exceptionReportPerson+"</td>"
									 +"<td id=''>"+exceptions.exceptionReason+"</td>"
									 +"</tr>");
				        			$("td[name='resaon"+index+"']").html("");
				        			if(exceptions.status==1){
				        				$("td[name='resaon"+index+"']").append("等待承认"); 
				        			}
				        			else if(exceptions.status==2){
				        				$("td[name='resaon"+index+"']").append("等待装车"); 
				        			}else if(exceptions.status==3){
				        				$("td[name='resaon"+index+"']").append("等待发运"); 
				        			}else if(exceptions.status==4){
				        				$("td[name='resaon"+index+"']").append("在途运载"); 
				        			}else if(exceptions.status==5){
				        				$("td[name='resaon"+index+"']").append("等待卸货"); 
				        			}else if(exceptions.status==6){
				        				$("td[name='resaon"+index+"']").append("等待回单"); 
				        			}
		        				}
		        			});	
		    			}
		      		}
				});
			}else if(orderStatus == 3){
				$('#historyOrderBody').empty();
				$.ajax({  
		        	type:'post',      
		       		url:'${pageContext.request.contextPath}/fireTrainCbulkLoading/selectHistoryOrderByPage.do',  
			        data:{page:nums,trainOrderSearch:JSON.stringify($("#searchForm").serializeJSON())},
			        cache:false,
			        dataType:'json',
		        	success:function(data){
		        		if(data.status==200){
		        			$("#page_history").paging({
		        				pageNo:1,
		        				totalPage:data.data.totalPage,
		        				totalSize:data.data.limit,
		        				callback: function(num) {
		        							$.ajax({  
		        				        	type:'post',      
		        				       		url:'${pageContext.request.contextPath}/fireTrainCbulkLoading/selectHistoryOrderByPage.do',  
		        					        data:{page:num,trainOrderSearch:JSON.stringify($("#searchForm").serializeJSON())},
		        					        cache:false,
		        					        dataType:'json',
		        				        	success:function(data){
		        				        		if(data.status==200){
		        				        		var trainOrderList = data.data.rows;
		        				        		$('#historyOrderBody').empty();
		        				        		$.each(trainOrderList,function(index,historys){
		        				        			if(historys.status == 7){
		        				        				$('#historyOrderBody').append("<tr id="+historys.id+">"
		        												+"<td style='text-align: center;'><label class='demo--label'>"
		        												 +" <input class='demo--checkbox'  name='WaybillName' value='"+historys.id+"' type='checkbox'   >"
		        					                              +"<span class='demo--checkboxInput'></span>"
		        					                             +"</label></td>"
		        					                             +"<td id=''>"+historys.projectCode+"</td>"
		        												 +"<td id=''><a href='javascript:void(0)'</a>"+historys.pleaseTrainNumber+"</td>"
		        											 +"<td id=''>"+historys.createDate+"</td>"
		        											 +"<td id=''>"+historys.updateDate+"</td>"
		        											 +"<td id=''>"+historys.arriveDate+"</td>"
		        											 +"<td id=''>"+historys.sendOperatorId+"</td>"
		        											 +"<td id=''>"+historys.branchName+"</td>"
		        											 +"<td id=''>"+historys.cargoName+"</td>"
		        											 +"<td id=''>"+historys.beginSite+"</td>"
		        											 +"<td id=''>"+historys.entruckDate+"</td>"
		        											 +"<td id=''>"+historys.sendDate+"</td>"
		        											 +"<td id=''>"+historys.endSite+"</td>"
		        											 +"<td id=''>"+historys.pleaseCarNum+"</td>"
		        											 +"<td id=''>"+historys.sureCarNum+"</td>"
		        											 +"<td id=''>"+historys.loseCarNum+"</td>"
		        											 +"</tr>");
		        				        			}
		        				        		});	
		        				    		}
		        				      	}
		        					});
		        							}
		        				});
		        		var trainOrderList = data.data.rows;
		        		$('#historyOrderBody').empty();
		        		$.each(trainOrderList,function(index,historys){
		        			if(historys.status == 7){
		        				$('#historyOrderBody').append("<tr id="+historys.id+">"
										+"<td style='text-align: center;'><label class='demo--label'>"
										 +" <input class='demo--checkbox'  name='WaybillName' value='"+historys.id+"' type='checkbox'   >"
			                              +"<span class='demo--checkboxInput'></span>"
			                             +"</label></td>"
			                             +"<td id=''>"+historys.projectCode+"</td>"
										 +"<td id=''><a href='javascript:void(0)'</a>"+historys.pleaseTrainNumber+"</td>"
									 +"<td id=''>"+historys.createDate+"</td>"
									 +"<td id=''>"+historys.updateDate+"</td>"
									 +"<td id=''>"+historys.arriveDate+"</td>"
									 +"<td id=''>"+historys.sendOperatorId+"</td>"
									 +"<td id=''>"+historys.branchName+"</td>"
									 +"<td id=''>"+historys.cargoName+"</td>"
									 +"<td id=''>"+historys.beginSite+"</td>"
									 +"<td id=''>"+historys.entruckDate+"</td>"
									 +"<td id=''>"+historys.sendDate+"</td>"
									 +"<td id=''>"+historys.endSite+"</td>"
									 +"<td id=''>"+historys.pleaseCarNum+"</td>"
									 +"<td id=''>"+historys.sureCarNum+"</td>"
									 +"<td id=''>"+historys.loseCarNum+"</td>"
									 +"</tr>");
			        			}
			        		});	
			    		}
			      	}
				});
			}else if(orderStatus == 4){
				$('#deleteOrderBody').empty();
				$.ajax({  
		        	type:'post',      
		       		url:'${pageContext.request.contextPath}/fireTrainCbulkLoading/selectDeleteOrderByPage.do',  
			        data:{page:nums,trainOrderSearch:JSON.stringify($("#searchForm").serializeJSON())},
			        cache:false,
			        dataType:'json',
		        	success:function(data){
		        		if(data.status==200){
		        			$("#page_delete").paging({
		        				pageNo:1,
		        				totalPage:data.data.totalPage,
		        				totalSize:data.data.limit,
		        				callback: function(num) {
		        							$.ajax({  
		        				        	type:'post',      
		        				       		url:'${pageContext.request.contextPath}/fireTrainCbulkLoading/selectDeleteOrderByPage.do',  
		        					        data:{page:num,trainOrderSearch:JSON.stringify($("#searchForm").serializeJSON())},
		        					        cache:false,
		        					        dataType:'json',
		        				        	success:function(data){
		        				        		if(data.status==200){
		        				        		var trainOrderList = data.data.rows;
		        				        		$('#deleteOrderBody').empty();
		        				        		$.each(trainOrderList,function(index,deletes){
		        				        			if(deletes.status != 7){
		        				        				$('#deleteOrderBody').append("<tr id="+deletes.id+">"
		        												+"<td style='text-align: center;'><label class='demo--label'>"
		        												 +" <input class='demo--checkbox'  name='WaybillName' value='"+deletes.id+"' type='checkbox'   >"
		        					                              +"<span class='demo--checkboxInput'></span>"
		        					                             +"</label></td>"
		        					                             +"<td id=''>"+deletes.projectCode+"</td>"
		        												 +"<td id=''><a href='javascript:void(0)'</a>"+deletes.pleaseTrainNumber+"</td>"
		        											 +"<td id=''>"+deletes.createDate+"</td>"
		        											 +"<td name='resaon"+index+"'></td>"
		        											 +"<td id=''>"+deletes.updateDate+"</td>"
		        											 +"<td id=''>"+deletes.sendOperatorId+"</td>"
		        											 +"<td id=''>"+deletes.branchName+"</td>"
		        											 +"<td id=''>"+deletes.cargoName+"</td>"
		        											 +"<td id=''>"+deletes.beginSite+"</td>"
		        											 +"<td id=''>"+deletes.entruckDate+"</td>"
		        											 +"<td id=''>"+deletes.sendDate+"</td>"
		        											 +"<td id=''>"+deletes.endSite+"</td>"
		        											 +"<td id=''>"+deletes.pleaseCarNum+"</td>"
		        											 +"<td id=''>"+deletes.sureCarNum+"</td>"
		        											 +"<td id=''>"+deletes.loseCarNum+"</td>"
		        											 +"<td id=''>"+deletes.deleteDate+"</td>"
		        											 +"<td id=''>"+deletes.deletePerson+"</td>"
		        											 +"<td id=''>"+deletes.deleteReason+"</td>"
		        											 +"</tr>");
		        						        			$("td[name='resaon"+index+"']").html("");
		        						        			if(deletes.status==1){
		        						        				$("td[name='resaon"+index+"']").append("等待承认"); 
		        						        			}
		        						        			else if(deletes.status==2){
		        						        				$("td[name='resaon"+index+"']").append("等待装车"); 
		        						        			}else if(deletes.status==3){
		        						        				$("td[name='resaon"+index+"']").append("等待发运"); 
		        						        			}else if(deletes.status==4){
		        						        				$("td[name='resaon"+index+"']").append("在途运载"); 
		        						        			}else if(deletes.status==5){
		        						        				$("td[name='resaon"+index+"']").append("等待卸货"); 
		        						        			}else if(deletes.status==6){
		        						        				$("td[name='resaon"+index+"']").append("等待回单"); 
		        						        			}
		        				        			}
		        				        		});	
		        				    		}
		        				      	}
		        					});
		        							}
		        				});
		        		var trainOrderList = data.data.rows;
		        		$('#deleteOrderBody').empty();
		        		$.each(trainOrderList,function(index,deletes){
		        			if(deletes.status != 7){
		        				$('#deleteOrderBody').append("<tr id="+deletes.id+">"
										+"<td style='text-align: center;'><label class='demo--label'>"
										 +" <input class='demo--checkbox'  name='WaybillName' value='"+deletes.id+"' type='checkbox'   >"
			                              +"<span class='demo--checkboxInput'></span>"
			                             +"</label></td>"
			                             +"<td id=''>"+deletes.projectCode+"</td>"
										 +"<td id=''><a href='javascript:void(0)'</a>"+deletes.pleaseTrainNumber+"</td>"
									 +"<td id=''>"+deletes.createDate+"</td>"
									 +"<td name='resaon"+index+"'></td>"
									 +"<td id=''>"+deletes.updateDate+"</td>"
									 +"<td id=''>"+deletes.sendOperatorId+"</td>"
									 +"<td id=''>"+deletes.branchName+"</td>"
									 +"<td id=''>"+deletes.cargoName+"</td>"
									 +"<td id=''>"+deletes.beginSite+"</td>"
									 +"<td id=''>"+deletes.entruckDate+"</td>"
									 +"<td id=''>"+deletes.sendDate+"</td>"
									 +"<td id=''>"+deletes.endSite+"</td>"
									 +"<td id=''>"+deletes.pleaseCarNum+"</td>"
									 +"<td id=''>"+deletes.sureCarNum+"</td>"
									 +"<td id=''>"+deletes.loseCarNum+"</td>"
									 +"<td id=''>"+deletes.deleteDate+"</td>"
									 +"<td id=''>"+deletes.deletePerson+"</td>"
									 +"<td id=''>"+deletes.deleteReason+"</td>"
									 +"</tr>");
				        			$("td[name='resaon"+index+"']").html("");
				        			if(deletes.status==1){
				        				$("td[name='resaon"+index+"']").append("等待承认"); 
				        			}
				        			else if(deletes.status==2){
				        				$("td[name='resaon"+index+"']").append("等待装车"); 
				        			}else if(deletes.status==3){
				        				$("td[name='resaon"+index+"']").append("等待发运"); 
				        			}else if(deletes.status==4){
				        				$("td[name='resaon"+index+"']").append("在途运载"); 
				        			}else if(deletes.status==5){
				        				$("td[name='resaon"+index+"']").append("等待卸货"); 
				        			}else if(deletes.status==6){
				        				$("td[name='resaon"+index+"']").append("等待回单"); 
				        			}
		        				}
		        			});	
		    			}
		      		}
				});
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
					url : "${pageContext.request.contextPath}/system/sms/checkTrainBussinss.do",
					data : {"ids":idList.toString()},
					dataType : "json",
					async: false,
					success : function(data) {
						var d = data.data;
						if(d == null || d == ""){

						}else{
							//$(".tips").show();
							$.each(d, function(index,it) {
								$("#noBussinss").append(it.pleaseTrainNumber+",");
							});
							$("#noBussinss").append("业务联系人为空,短信未发送,其余已发送");
						}
						if(d.length == idList.length){
							flag = false;
						}
						if(flag){
							$.ajax({
								type : 'POST',
								url : "${pageContext.request.contextPath}/system/sms/sendMsgOfTrain.do",
								data : {"ids":idList.toString(),"content":content},
								dataType : "json",
								async: false,
								success : function(data) {
									if (data.status != 1) {
										$("#errorMsg").html(data.msg);
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
							$("#nullModalMsg").modal();
							$.each(d, function(index,it) {
								$("#warnMessage").append(it.pleaseTrainNumber+",");
							});
							$("#warnMessage").append("业务联系人为空,短信未发送,请查看");
						}
					},
				}); 
			});	
		})
    </script>
	<body>
				<!-- 火运散装箱管理表单 -->
		<div class="form project_form container_top">
			<form class="form-inline maxWidth" id="searchForm">
				<div id="wrap">
					<div class="form-group">
						<label>项目编号：</label>
						<input name='projectCode' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>分支机构：</label>
						<select name="branchId">
							<option></option>
							<c:forEach items="${branchGroups}" var="branchGroup">
								<option value="${branchGroup.id}">${branchGroup.name}</option>	
							</c:forEach>
						</select>
					</div>
					<div class="form-group" style="display: none;">
						<label>项目类型：</label>
						<select name="projectType">
							<option></option>
							<option value="1">散装</option>
						</select>
					</div>
					<div class="form-group">
						<label>请车单号：</label>
						<input name='pleaseTrainNumber' type="text" maxlength="30"/>
					</div>

					<div class="form-group">
						<label>货物品名：</label>
						<select name="cargoId" onchange="setCargoName(this)">
							<option></option>
							<c:forEach items="${cargos}" var="cargo">
								<option value="${cargo.id}">${cargo.cargoName}</option>	
							</c:forEach>
						</select>
						<input name="cargoName" type="hidden" />
					</div>
					
					<div class="form-group dataTimes">
						<label>&emsp;日期从：</label>
						<input name="beginDate"  type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
						<i class="fa  fa-calendar-check-o " id="calendar2"></i>
					</div>
					<div class="form-group dataTimes">
						<label>至</label>
						<input name='endDate' type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
						<i class="fa  fa-calendar-check-o " id="calendar2"></i>
					</div>
					<!--<div class="form-group">
						<label>发货企业：</label>
						<input name='sendCargoCompanyName' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>收货企业：</label>
						<input name='receiveCargoCompanyName' type="text" maxlength="30"/>
					</div>-->
					<div class="form-group">
						<label>始发站点：</label>
						<input name='beginSite' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>到达站点：</label>
						<input name='endSite' type="text" maxlength="30"/>
					</div>

					<div class="form-group dataTimes">
						<label>装车时间：</label>
						<input name='entruckDate' type="text"  class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
						<i class="fa  fa-calendar-check-o " id="calendar2"></i>
					</div>
					<div class="form-group dataTimes">
						<label>发车时间：</label>
						<input  name='sendDate' type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
						<i class="fa  fa-calendar-check-o " id="calendar2"></i>
					</div>
					<!-- <div class="form-group dataTimes">
						<label>到货时间：</label>
						<input name='arriveDate'  type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
						<i class="fa  fa-calendar-check-o " id="calendar2"></i>
					</div> -->
					<input id="orderStatus" name="orderStatus" type="hidden" value="1"/>
					<!--<div class="form-group">
						<label>&emsp;&emsp;车号：</label>
						<input name='receiveCargoCompanyName' type="text" maxlength="30"/>
					</div>-->
				</div>
				<div class="foot">
					<a class="btn btn-success" href="javascript:void(0)"><em class="search"></em>
						<span onclick="search(this)">搜索</span>
					</a>
					<div id="lookMore"></div>
				</div>
			</form>
		</div>
				<div class="container_top tableDiv">
			<div class="tabbable" id="tabs-529262">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#panel1" data-toggle="tab" onclick="searchStatus(1)">运单列表</a>
					</li>
					<li>
						<a href="#panel2" data-toggle="tab" onclick="searchStatus(2)">异常运单</a>
					</li>
					<li>
						<a href="#panel3" data-toggle="tab" onclick="searchStatus(3)">历史运单</a>
					</li>
					<li>
						<a href="#panel4" data-toggle="tab" onclick="searchStatus(4)">运单回收站</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="panel1">
						<div class="domain">
							<div class="tableBg">
							<c:forEach items='${permissions }' var='permission' >
								<c:if test="${permission.code=='createFireTrainCbulkOrder'}">
									<a href="javascript:void(0)" class="exportBtn add" id="add" ><span>新建</span></a>
								</c:if>
								<c:if test="${permission.code=='exportFireTrainCbulkOrder'}">
									<a href="javascript:void(0)" class="export" id="export1"><span>导出</span></a>
								</c:if>
								<c:if test="${permission.code=='delFireTrainCbulkOrder'}">
									<a href="javascript:void(0)" class="exportBtn del" id="delBtn"><span>删除</span></a>
								</c:if>
								<c:if test="${permission.code=='msgByFireTrainCbulkOrder'}">
									<a href="javascript:void(0)" class="exportBtn msgbtn msg" id="msgbtn1"><span>短信告知</span></a>
								</c:if>
							</c:forEach>
							</div>
							<div class="table-responsive">
								<!--运单列表表格开始-->
								<table class="table" id="operatorTable">
									<thead>
										<tr class="tableTop">
											<th>
												<label class="demo--label">
											     <input class="demo--checkbox"  name="WaybillName" type="checkbox">
                                                 <span class="demo--checkboxInput"></span>
	                                         </label>
											</th>
											<th>项目编号</th>
											<th>请车单号</th>
											<th>创建时间</th>
											<th>运单状态</th>
											<th>状态更新时间</th>
											<th>物流员</th>
											<th>分支机构</th>
											<th>货物品名</th>
											<th>始发站点</th>
											<th>装车时间</th>
											<th>发车时间</th>
											<th>到达站点</th>
											<th>请车数</th>
											<th>承认车数</th>
											<th>落车数</th>
										</tr>
									</thead>
									<tbody id="normalOrderBody" class="needLookDetail">
										<c:forEach var="it" items="${trainOrderList.rows}">
											<c:if test="${it.status !=7}">
												<tr id="${it.id}">
													<td>
														<label class="demo--label">
													     <input class="demo--checkbox"  name="WaybillName" type="checkbox" value="${it.id}">
		                                                 <span class="demo--checkboxInput"></span>
			                                          </label>
													</td>
													<td>${it.projectCode}</td>
													<td>
														<a href="javascript:void(0)">${it.pleaseTrainNumber}</a>
													</td>
													<td><fmt:formatDate value="${it.createDate}" pattern="yyyy.MM.dd HH:mm" /></td>
													<td>
														<c:if test="${it.status == 1}"><a href="javascript:void(0)"  id="admit" onclick="admiter(${it.id})">等待承认</a></c:if>
														<c:if test="${it.status == 2}"><a href="javascript:void(0)"  id="loading" onclick="loadingEntruck(${it.id})">等待装车</a></c:if>
														<c:if test="${it.status == 3}"><a href="javascript:void(0)"  id="send" onclick="sending(${it.id})">等待发运</a></c:if>
														<c:if test="${it.status == 4}"><a href="javascript:void(0)"  id="carry" onclick="carrying(${it.id})">在途运载</a></c:if>
														<c:if test="${it.status == 5}"><a href="javascript:void(0)"  id="unloading" onclick="waitUnloading(${it.id})">等待卸货</a></c:if>
														<c:if test="${it.status == 6}"><a href="javascript:void(0)"  id="receipt" onclick="receipting(${it.id})">等待回单</a></c:if>
													</td>
													<td><fmt:formatDate value="${it.updateDate}" pattern="yyyy.MM.dd HH:mm" /></td>
													<td>${it.sendOperatorId}</td>
													<td>${it.branchName}</td>
													<td>${it.cargoName}</td>
													<td>${it.beginSite}</td>
													<td><fmt:formatDate value="${it.entruckDate}" pattern="yyyy.MM.dd HH:mm" /></td>
													<td><fmt:formatDate value="${it.sendDate}" pattern="yyyy.MM.dd HH:mm" /></td>
													<td>${it.endSite}</td>
													<td>${it.pleaseCarNum}</td>
													<td>${it.sureCarNum}</td>
													<td>${it.loseCarNum}</td>
												</tr>
											</c:if>
										</c:forEach>
									</tbody>
								</table>
								<!--运单列表表格结束-->
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-md-12 column paging page_div" id="page_normal">
							</div>
						</div>
					</div>
					<!-- 异常处理表格-->
					<div class="tab-pane" id="panel2">
						<div class="domain">
							<div class="tableBg">
							<c:forEach items='${permissions }' var='permission' >
								<c:if test="${permission.code=='exceptionFireTrainCbulkOrder'}">
									<a href="javascript:void(0)" class="exportBtn abnormal_btn abnorm"><span>异常处理</span></a>
								</c:if>
							</c:forEach>	
							<c:forEach items='${permissions }' var='permission' >
								<c:if test="${permission.code=='exportExceptionFireTrainCbulkOrder'}">
									<a href="javascript:void(0)" class="export" id="export2"><span>导出</span></a>
								</c:if>
							</c:forEach>	
							<c:forEach items='${permissions }' var='permission' >	
								<c:if test="${permission.code=='msgByExceptionFireTrainCbulkOrder'}">
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
											     <input class="demo--checkbox"  type="checkbox" >
                                                 <span class="demo--checkboxInput"></span>
	                                          </label>
											</th>
											<th>项目编号</th>
											<th>请车单号</th>
											<th>创建时间</th>
											<th>运单状态</th>
											<th>状态更新时间</th>
											<th>物流员</th>
											<th>分支机构</th>
											<th>货物品名</th>
											<th>始发站点</th>
											<th>装车时间</th>
											<th>发车时间</th>
											<th>到达站点</th>
											<th>请车数</th>
											<th>承认车数</th>
											<th>落车数</th>
											<th>填报时间</th>
											<th>异常提报人</th>
											<th>异常原因</th>
										</tr>
									</thead>
									<tbody id="exceptionBody" class="needLookDetail"> 
										<c:forEach var="exception" items="${exceptionResult.rows}">
											<tr    id="${exception.id}">
												<td>
													<label class="demo--label">
												     <input class="demo--checkbox"   type="checkbox" value="${exception.id}">
	                                                 <span class="demo--checkboxInput"></span>
		                                          </label>
												</td>
												<td>${exception.projectCode}</td>
													<td>
														<a href="javascript:void(0)">${exception.pleaseTrainNumber}</a>
													</td>
													<td><fmt:formatDate value="${exception.createDate}" pattern="yyyy.MM.dd HH:mm" /></td>
													<td>
														<c:if test="${exception.status == 1}">等待承认</c:if>
														<c:if test="${exception.status == 2}">等待装车</c:if>
														<c:if test="${exception.status == 3}">等待发运</c:if>
														<c:if test="${exception.status == 4}">在途运载</c:if>
														<c:if test="${exception.status == 5}">等待卸货</c:if>
														<c:if test="${exception.status == 6}">等待回单</c:if>
													</td>
													<td><fmt:formatDate value="${exception.updateDate}" pattern="yyyy.MM.dd HH:mm" /></td>
													<td>${exception.sendOperatorId}</td>
													<td>${exception.branchName}</td>
													<td>${exception.cargoName}</td>
													<td>${exception.beginSite}</td>
													<td><fmt:formatDate value="${exception.entruckDate}" pattern="yyyy.MM.dd HH:mm" /></td>
													<td><fmt:formatDate value="${exception.sendDate}" pattern="yyyy.MM.dd HH:mm" /></td>
													<td>${exception.endSite}</td>
													<td>${exception.pleaseCarNum}</td>
													<td>${exception.sureCarNum}</td>
													<td>${exception.loseCarNum}</td>
													<td><fmt:formatDate value="${exception.exceptionReportDate}" pattern="yyyy.MM.dd HH:mm" /></td>
													<td>${exception.exceptionReportPerson}</td>
													<td>${exception.exceptionReason}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<!--异常运单列表表格结束-->
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-md-12 column paging page_div" id="page_exception">
							</div>
						</div>
					</div>
					<div class="tab-pane" id="panel3">
						<div class="domain">
							<div class="tableBg">
							<c:forEach items='${permissions }' var='permission' >
								<c:if test="${permission.code=='exportHistoryFireTrainCbulkOrder'}">
									<a href="javascript:void(0)" class="export" id="export3"><span>导出</span></a>
								</c:if>
								<c:if test="${permission.code=='msgByHistoryFireTrainCbulkOrder'}">
									<a href="javascript:void(0)" class="exportBtn msgbtn msg" id="msgbtn3"><span>短信告知</span></a>
								</c:if>
							</c:forEach>
							</div>
							<div class="table-responsive">
								<!--历史运单开始-->
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th>
												<label class="demo--label">
											     <input class="demo--checkbox"  type="checkbox" >
                                                 <span class="demo--checkboxInput"></span>
	                                          </label>
											</th>
											<th>项目编号</th>
											<th>请车单号</th>
											<th>创建时间</th>
											<th>状态更新时间</th>
											<th>到货时间</th>
											<th>物流员</th>
											<th>分支机构</th>
											<th>货物品名</th>
											<th>始发站点</th>
											<th>装车时间</th>
											<th>发车时间</th>
											<th>到达站点</th>
											<th>请车数</th>
											<th>承运车数</th>
											<th>落车数</th>
										</tr>
									</thead>
									<tbody id="historyOrderBody" class="needLookDetail">
										<c:forEach var="its" items="${historyResult.rows}">
											<c:if test="${its.status ==7}">
												<tr  id="${its.id}">
													<td>
														<label class="demo--label">
													     <input class="demo--checkbox"  name="WaybillName" type="checkbox" value="${its.id}">
		                                                 <span class="demo--checkboxInput"></span>
			                                          </label>
													</td>
													<td>${its.projectCode}</td>
													<td>
														<a href="javascript:void(0)">${its.pleaseTrainNumber}</a>
													</td>
													<td><fmt:formatDate value="${its.createDate}" pattern="yyyy.MM.dd HH:mm" /></td>
													<%-- <td>
														<a href="javascript:void(0)"  id="admit">${its.status}</a>
													</td> --%>
													<td><fmt:formatDate value="${its.updateDate}" pattern="yyyy.MM.dd HH:mm" /></td>
													<td><fmt:formatDate value="${its.arriveDate}" pattern="yyyy.MM.dd HH:mm" /></td>
													<td>${its.sendOperatorId}</td>
													<td>${its.branchName}</td>
													<td>${its.cargoName}</td>
													<td>${its.beginSite}</td>
													<td><fmt:formatDate value="${its.entruckDate}" pattern="yyyy.MM.dd HH:mm" /></td>
													<td><fmt:formatDate value="${its.sendDate}" pattern="yyyy.MM.dd HH:mm" /></td>
													<td>${its.endSite}</td>
													<td>${its.pleaseCarNum}</td>
													<td>${its.sureCarNum}</td>
													<td>${its.loseCarNum}</td>
												</tr>
											</c:if>
										</c:forEach>
									</tbody>
								</table>
								<!--历史运单结束-->
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-md-12 column paging page_div" id="page_history">
							</div>
						</div>
					</div>
					<div class="tab-pane" id="panel4">
						<div class="domain">
							<div class="tableBg">
							<c:forEach items='${permissions }' var='permission' >
								<c:if test="${permission.code=='recycleFireTrainCbulkOrder'}">
									<a href="javascript:void(0)" class="exportBtn rest" style="display: none;"><span>还原</span></a>
								</c:if>
								<c:if test="${permission.code=='exportRecycleFireTrainCbulkOrder'}">
									<a href="javascript:void(0)" class="export" id="export4"><span>导出</span></a>
								</c:if>
								<c:if test="${permission.code=='msgByRecycleFireTrainCbulkOrder'}">
									<a href="javascript:void(0)" class="exportBtn msgbtn msg" id="msgbtn4"><span>短信告知</span></a>
								</c:if>
							</c:forEach>
							</div>
							<div class="table-responsive">
								<!-- 项目回收站结束-->
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th> 
												<label class="demo--label">
											     <input class="demo--checkbox"  type="checkbox" >
                                                 <span class="demo--checkboxInput"></span>
	                                          </label>
											</th>
											<th>项目编号</th>
											<th>请车单号</th>
											<th>创建时间</th>
											<th>运单状态</th>
											<th>状态更新时间</th>
											<th>物流员</th>
											<th>分支机构</th>
											<th>货物品名</th>
											<th>始发站点</th>
											<th>装车时间</th>
											<th>发车时间</th>
											<th>到达站点</th>
											<th>请车数</th>
											<th>承认车数</th>
											<th>落车数</th>
											<th>删除时间</th>
											<th>删除人</th>
											<th>删除原因</th>
										</tr>
									</thead>
									<tbody id="deleteOrderBody" class="needLookDetail">
										<c:forEach var="delete" items="${deleteResult.rows}">
											<tr   id="${delete.id}">
												<td>
													<label class="demo--label">
												     <input class="demo--checkbox"   type="checkbox" name="delOrderName" value="${delete.id}">
	                                                 <span class="demo--checkboxInput"></span>
		                                          </label>
												</td>
												<td>${delete.projectCode}</td>
													<td>
														<a href="javascript:void(0)">${delete.pleaseTrainNumber}</a>
													</td>
													<td><fmt:formatDate value="${delete.createDate}" pattern="yyyy.MM.dd HH:mm" /></td>
													<td>
														<c:if test="${delete.status == 1}">等待承认</c:if>
														<c:if test="${delete.status == 2}">等待装车</c:if>
														<c:if test="${delete.status == 3}">等待发运</c:if>
														<c:if test="${delete.status == 4}">在途运载</c:if>
														<c:if test="${delete.status == 5}">等待卸货</c:if>
														<c:if test="${delete.status == 6}">等待回单</c:if>
													</td>
													<td><fmt:formatDate value="${delete.updateDate}" pattern="yyyy.MM.dd HH:mm" /></td>
													<td>${delete.sendOperatorId}</td>
													<td>${delete.branchName}</td>
													<td>${delete.cargoName}</td>
													<td>${delete.beginSite}</td>
													<td><fmt:formatDate value="${delete.entruckDate}" pattern="yyyy.MM.dd HH:mm" /></td>
													<td><fmt:formatDate value="${delete.sendDate}" pattern="yyyy.MM.dd HH:mm" /></td>
													<td>${delete.endSite}</td>
													<td>${delete.pleaseCarNum}</td>
													<td>${delete.sureCarNum}</td>
													<td>${delete.loseCarNum}</td>
													<td><fmt:formatDate value="${delete.deleteDate}" pattern="yyyy.MM.dd HH:mm" /></td>
													<td>${delete.deletePerson}</td>
													<td>${delete.deleteReason}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<!--  运单回收站结束-->
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-md-12 column paging page_div" id="page_delete">
							</div>
						</div>

					</div>

				</div>
			</div>
		</div>
		<!--新建请车模态框-->
		<div class="modal fade" id="addModal" tabindex="-1" role="dialog" data-backdrop="static" aria-hidden="true" >
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<span data-dismiss="modal" aria-label="Close"></span>
						<h4 class="modal-title" class="myModalLabel">
							新建请车
						</h4>
					</div>
					<div class="modal-body" id="newModalBody">
					    <form id="addId"> 
						<!--	项目信息-->
						<div class="project_info">
							<h5>项目信息<i class='requireds'>*</i></h5>
							<div class="form-inline">
								<div class="form-group">
									<label>项目编号：</label>
									<select id="select_project" onchange="exchangeProject()"></select>
								</div>
								<div class="form-group">
									<label>项目类型：</label>
									<input id="projectTypeName" name="projectTypeName" type="text" unselectable="on" class="inputbg" readonly="readonly" />
									<input id="projectType" name="projectType" type="hidden" />
									<input id="projectId" name="projectId" type="hidden" />
									<input id="projectCode" name="projectCode" type="hidden" />
									<input id="sendCompany" name="sendCompany" type="hidden" />
									<input id="receiptCompany" name="receiptCompany" type="hidden" />
								</div>
								<div class="form-group">
									<label>分支机构：</label>
									<input id="branchName" unselectable="on" class="inputbg" readonly="readonly" name="branchName"/>
									<input id="branchId" type="hidden" name="branchId" />
								</div>
							</div>
							<div class="form-inline">
								<div class="form-group">
									<label>始发站点：</label>
									<input id="beginSite" name="beginSite" type="text" unselectable="on" class="inputbg" readonly="readonly" />
								</div>
								<div class="form-group">
									<label>&emsp;始发地：</label>
									<input id="beginPlace" name="beginPlace" type="text" unselectable="on" class="inputbg" readonly="readonly" />
								</div>
								<div class="form-group">
									<label>到达站点：</label>
									<input id="endSite" name="endSite" type="text" unselectable="on" class="inputbg" readonly="readonly" />
								</div>
								<div class="form-group">
									<label>&emsp;到达地：</label>
									<input id="endPlace" name="endPlace" type="text" unselectable="on" class="inputbg" readonly="readonly" />
								</div>
							</div>
						</div>
						<hr />
						<!--   请车信息-->
						<div class="project_info">
							<h5>请车信息<i class='requireds'>*</i></h5>
							<div class="form-inline">
								<div class="form-group">
									<label>&emsp;请车数：</label>
									<input type="text" id="pleaseCarNum" name="pleaseCarNum" />
								</div>
								<div class="form-group">
									<label>请车类型：</label>
									<select id="select_pleaseTrain" name="pleaseCarTypeId"></select>
								</div>
								<div class="form-group">
									<label>&emsp;预载重：</label>
									<input id="estimateWeight" name="estimateWeight" type="text" onkeyup="advanceAmount()" />
								</div>
								<div class="form-group">
									<label>预计费用：</label>
									<input id="estimateCost" name="estimateCost" type="text" unselectable="on" class="inputbg" readonly="readonly" />
									<input id="hideEstimateCost" name="hideEstimateCost" type="hidden" />
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
									<input id="cargoName" name="cargoName" type="text" unselectable="on" class="inputbg" readonly="readonly" />
								</div>
								<div class="form-group">
									<label>货物规格：</label>
									<input id="cargoSpecifications" name="cargoSpecifications" type="text" unselectable="on" class="inputbg" readonly="readonly" />
								</div>
							</div>
						</div>
						<hr />
						<!--车辆信息-->
						<div class="project_info">
							<h5>库存信息</h5>
							<div class="form-inline">
								<div class="form-group">
									<label>&emsp;&emsp;货场：</label>
									<input name="moreHuo" type="text" unselectable="on" class="inputbg" readonly="readonly" />
								</div>
								<div class="form-group">
									<label>&emsp;&emsp;货位：</label>
									<input name="moreWei" type="text" unselectable="on" class="inputbg" readonly="readonly" />
								</div>
								<div class="form-group">
									<label>&emsp;&emsp;库存：</label>
									<input id="kucun" name="kuCun" type="text" unselectable="on" class="inputbg" readonly="readonly" />
								</div>
							</div>

						</div>
						<hr />
						<!--   onchange="exchangeAdvanceNum()"费用信息-->
						<div class="project_info">
							<h5>费用信息<i class='requireds'>*</i></h5>
							<div class="form-inline">
								<div class="form-group">
									<label>预付款账户：</label>
									<input id="advanceChargeAccount" name="advanceChargeAccount" type="hidden" unselectable="on" class="inputbg" readonly="readonly" />
									<select id="chooseAdvanceChargeAccount" ></select>
								</div>
								<div class="form-group">
									<label>预付款余额：</label>
									<input id="advanceCharge" name="advanceCharge" type="text" unselectable="on" class="inputbg" readonly="readonly" />
								</div>
							</div>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="addTrainOrder()">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<!--等待承认-->
		<div class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" aria-hidden="true" id="waitModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" class="myModalLabel">
							等待承认
						</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="admitForm">
						<div class="project_info" id="project_info_admit">
							<h5>承运信息</h5>
							<ul class="waitUl" id="waitUlContent">
							</ul>
						</div>
						<hr />
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="addWaitAdmit()">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<!--等待装车-->
		<div class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" aria-hidden="true" id="entruckingModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" class="myModalLabel">
							等待装车
						</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body" id="waitUlLoadForm">
						<form id="loadingForm">
						<div class="project_info">
							<h5>项目信息<i class='requireds'>*</i></h5>
							<ul class="waitUl" id="waitUlLoadContent">
							<li>项目编号：<span name="xmbhInfo"></span></li>
								<li>请车单号：<span name="qcdhInfo"></span></li>
								<li><span class="v-top">&emsp;装车数：</span><input type="text" maxlength="30" id="entruckNumbe" name="entruckNumbe" onkeyup="value=value.replace(/[^\d]/g,'') " readonly="readonly"/></li>
								<li><span class="v-top">装载吨位：</span><input type="text" maxlength="30" id="entruckWeight" name="entruckWeight" readonly="readonly"/></li>
								<li>始发站点：<span class="v-top" name="sfzdInfo"></span>
								</li>
							</ul>
							<input type="hidden"  id="orderId"  name="orderId" />
							<input type="hidden"  id="chengrencheshu" />
						</div>
						<hr />
						<div class="bigDiv table-responsive" >
						<input id="hidenProjectId" type="hidden" name="hidenProjectId" />
						<input id="freightYadJson" type="hidden" name="freightYadJson" />
						<input id="cargoLocationJson" type="hidden" name="cargoLocationJson" />
						<input id="entruckInfoJson" type="hidden" name="entruckInfoJson" />
							<div class="project_info">
								<ul class="waitUl">
									<li>装车货场：
										<select id="freightYard" name="freightYard"  class="freightYard"  ></select>
									</li>
									<li>装车货位：
										<select id="freightAllocation" name="freightAllocation" class="freightAllocation">
										</select>
									</li>
									<li class="addBtn pull-right" ><button class="btn btn-success" type="button"><img src="${pageContext.request.contextPath}/img/addbtn.png"/>添加更多货场货位</button></li>
								</ul>
							</div>
							<table class="table loadingTable">
								<thead>
									<tr class="tableTop">
										<th>序号</th>
										<th>车型</th>
										<th>车号</th>
										<th>装车载重</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody id="waitLoadBody">
								</tbody>
							</table>
							<div class="moreAdd"  name='moreAddBind' style='display:none'><img src="${pageContext.request.contextPath}/img/more.png" />添加更多装车信息</div>
							<div class="moreAdd"  name='moreAddVali'><img src="${pageContext.request.contextPath}/img/more.png" />添加更多装车信息</div>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="saveWaitTrainInput();">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal" onclick="closeAndfresh()">取消</button>
							</div>

						</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<!--等待发运-->
		<div class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" aria-hidden="true" id="forwardingModal">
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
							<ul class="waitUl" id="waitUlSendContent">
							</ul>
						</div>
						<hr />
						<div class="bigDivs table-responsive">
						<table class="table lastChild" >
							<thead>
								<tr class="tableTop">
									<th>序号</th>
									<th>车型</th>
									<th>车号</th>
									<th>发货载重</th>
									<th>发货运单上传</th>
								</tr>
							</thead>
							<tbody id="trainCargoBody">
							</tbody>
						</table>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="updOrderStatus(4)">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<!--在途位置-->
		<div class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" aria-hidden="true" id="locationModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" class="myModalLabel">
						在途位置
						</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="carryId">
							<h5>位置信息</h5><input type="hidden"  id="firstLocation" name="firstLocation">
							<div class="localtionDiv">
								<div>历史位置：</div>
								<div>
									<ul class="localtionUl" id="localtionUlContent">
									</ul>
								</div>

							</div>
							<div class="localtionDiv1">
								<div>更新位置：<input type="text" class="localInput" id="location" name="location"/>
									<img src="${pageContext.request.contextPath}/img/reflash.png" onclick="updHistoryLocation()"/></div>
							</div>
							<hr />
							<div class="bigDivs table-responsive">
							<table class="table lastChild">
								<thead>
									<tr class="tableTop">
										<th >序号</th>
										<th >车型</th>
										<th >车号</th>
										<th >装车载重</th>
										<th>发货运单上传</th>
									</tr>
								</thead>
								<tbody id="localtionBody">
								</tbody>
							</table>
							</div>
							<div class="checkDiv">
								<label class="demo--label">
											     <input class="demo--checkbox" type="checkbox" name="isArrivePlace">
                                                 <span class="demo--checkboxInput"></span>
                                             
	                                        </label>
	                                        <span>抵达卸货地点，可以开始卸货</span>
							</div>
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
									<button type="button" class="btn sureBtn" onclick="checkAndToNext()">确定</button>
									<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
								</div>

							</div>
							</form>
						<!--</div>-->
					</div>
				</div>
			</div>
		</div>
		<!--等待卸货-->
		<div class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" aria-hidden="true" id="unloadingModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" class="myModalLabel">
							等待卸货
						</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="unloadingId">
						<div class="project_info">
							<h5>项目信息<i class='requireds'>*</i></h5>
							<ul class="waitUl" id="waitUlUnloadContent">
							</ul>
						</div>
						<hr />
						<input id="unloadInfoJson" type="hidden" name="unloadInfoJson" />
						<div class="allocate">
							<button type="button" id="allocate"><span>分配货场货位</span></button>
						</div>
						<div class="bigDivs newDiv " hidden="hidden" id="bigDivs">
							<div class="localtionDiv">
								<div>历史位置：</div>
								<div>
									<ul class="localtionUl" id="localtionUlContent2">
									</ul>
								</div>

							</div>
							<div class="localtionDiv1">
								<div>更新位置：<input type="text" class="localInput" id="location2" name="location2"/>
									<img src="${pageContext.request.contextPath}/img/reflash.png" onclick="updHistoryLocation2()"/></div>
							</div>
						</div>
							<hr />
						<div class="bigDivs newDiv " hidden="hidden" id="bigDivs">
							<div class="project_info">
								<%-- <ul class="waitUl" id="waitUls">
									<li> <span class="v-top">卸货货场：</span>
										<select id="select_freight_guide" name="select_freight_guide" class="select_freight_guide" onchange="cargoLoExchange()" ></select>
									</li>
									<li><span class="v-top">卸货货位：</span>
										<select id="select_cargo_guide" name="select_cargo_guide" class="select_cargo_guide" onchange="yardExchange()"></select>
									</li>
									<li class="addBtns pull-right"><button class="btn btn-success" type="button"><img src="${pageContext.request.contextPath}/img/ok.png"/>确认分配</button></li>
									<li><button class="btn btn-success" type="button" onclick="allocatCargoAndYard()"><img src="${pageContext.request.contextPath}/img/ok.png"/>确认分配</button></li>
								</ul> --%>
							</div>
							<div class="table-responsive">
							<table class="table loadingTable" id="caogoAndYardTable">
								<thead>
									<tr class="tableTop">
										<th>
											<label class="demo--label">
											     <input class="demo--checkbox" id="checkFalse"  type="checkbox" onclick="checkAll()">
                                                 <span class="demo--checkboxInput"></span>
	                                        </label>
										</th>
										<th>序号</th>
										<th style="display: none;">序号</th>
										<th>车型</th>
										<th>车号</th>
										<th>发货载重</th>
										<th>到货载重</th>
										<th>卸货货场</th>
										<th>卸货货位</th>
										<th>发货运单上传</th>
										<th>到货运单上传</th>
									</tr>
								</thead>
								<tbody id="unloadingBody" class="unloadingChecked">
								</tbody>
							</table>
							</div>	
							<div class="checkDiv">
								<label class="demo--label">
											     <input class="demo--checkbox" type="checkbox" name="isNext">
                                                 <span class="demo--checkboxInput"></span>
                                             
	                                        </label>
	                                        <span>我已全部分配完成,可以进行下一步</span>
							</div>
						</div>
						<div class="row clearfix " hidden="hidden">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="allocatCargoAndYard(6)">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal" onclick="closeAndfresh()">取消</button>
							</div>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!--等待回单-->
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
							<ul class="waitUl" id="waitUlReceiptContent">
							</ul>
						</div>
						<hr />
						<div class="table-responsive">
						<table class="table ">
							<thead>
								<tr class="tableTop">
									<th>序号</th>
									<th>车型</th>
									<th>车号</th>
									<th>发货载重</th>
									<th>到货载重</th>
									<th>发货运单上传</th>
									<th>到货运单上传</th>
								</tr>
							</thead>
							<tbody id="receiptionBody">

							</tbody>
						</table>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="updOrderStatusByImg()">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
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
								<button type="button" class="btn sureBtn" onclick="closeDRemindModal()">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
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
								<button type="button" class="btn sureBtn" onclick="closeDNullModal()">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
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
							<span>你是否选择删除此分支，删除后不可还原</span>
						</div>
						<div class="delete_reason">
							<h4>删除原因</h4>
							<textarea id="delete_reason" class="form-control" rows="3"></textarea>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="delsTrainOrder()">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
					    </form>
					</div>
				</div>
			</div>
		</div>

		<!--查看-->
		<div class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" aria-hidden="true" id="lookModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" class="myModalLabel">
						运单详情
						</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div >
							<h5>项目信息</h5>
							<ul class="detailsUl" id="detailsUlProject">
							</ul>
						</div>
						<hr />
						<div >
							<h5>运单信息</h5>
							<ul class="detailsUl" id="detailsUlTrainOrder">
							</ul>
						</div>
						<hr />
						<div>
							<h5>货物信息</h5>
							<ul class="detailsUl" id="detailsUlCargo">
							</ul>
						</div>
						<hr />
						<div>
							<h5>请车信息</h5>
							<ul class="detailsUl" id="detailsUlPleaseCar">
							</ul>
						</div>
						<hr/>
						<div >
							<h5>收发货信息</h5>
							<ul class="detailsUl" id="detailsUlThingInfo">
							</ul>
						</div>
						<hr />
						<div >
							<h5>费用信息</h5>
							<ul class="detailsUl" id="detailsUlAmount">
							</ul>
							<hr />
							<div class="table-responsive">
							<table class="table detailTable">
								<thead>
									<tr >
										<th>序号</th>
										<th>车型</th>
										<th>车号</th>
										<th>载重</th>
										<th>到货载重</th>
										<th>到货货场</th>
										<th>到货货位</th>
										<th>到货时间</th>
									</tr>
								</thead>
								<tbody id="detailBody">
								</tbody>
							</table>
							</div>
						</div>
						<hr />
						<div >
							<h5>位置信息</h5>
							<div class="YdDiv">
								<ul class="YdlocaltionUl" id="detailsUlHistory">
								</ul>
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
							<span>选择了<b id="exportNum">13</b>条记录，确认导出全部数据？</span>
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
								<button type="button" class="btn sureBtn" data-dismiss="modal" onclick="closeAndfresh()">确定</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 提示 -->
		<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="promptIdModal" data-backdrop="static" style="z-index: 9999">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="body-content">
							<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
							<span id="errorMsg"></span>
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
							<div><span>您已选择</span><b class="errnums" id="resetOrder"></b> <span id="msgSpan"></span></div>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="resetTrainOrder()">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
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
								<ul class="abnormalUl" id="abnormalUlContent">
								</ul>
							</div>
							<hr/>
							<h5>补加信息</h5>
							<div class="project_info">
								<label class="newColor">须知:</label><span>请确认此异常是否有误，如果有误，请选择驳回，如果无误，请选择确认无误。</span>
							</div>
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
									<button type="button" class="btn sureBtn" onclick="handleException()">驳回</button>
									<button type="button" class="btn cancleBtn" data-dismiss="modal">确认无误</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
			<!-- 查看大图 -->
		<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="lookImg" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">查看图片</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body" style="text-align: center;">
						<img id="look_img" src="" class="img-responsive" style="width:100%;height: 100%;" />
					</div>
				</div>
			</div>
		</div>
		<!-- 等待回单上传发运图片 -->
		<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="uploadModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">上传图片</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body" style="text-align: center;">
						<div class="user_addpic">
							<div class="picbox">
								<input type="file"  name="photo" class="testFile" onchange="testFileChange(this)">
							</div>
							<div id="preview" class='preview'>
								<img id="testImg" border=0 src="" />
								<input type="hidden"  name="sendImgId" id="sendImgId">
								<input type="hidden"  name="arriveImgId" id="arriveImgId">
								<input type="hidden"  name="sendImg" id="basePhoto">
								<input type="hidden" id="waitSendPhotoId" name="waitSendPhotoId">
							</div>
						</div>
					</div>
					<div class="row clearfix ">
						<div class="col-md-12 column modal_btn">
							<button type="button" class="btn sureBtns" onclick="updSendImg()">确定</button>
							<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 等待卸货上传图片 -->
		<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="uploadUnloadModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">上传图片</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body" style="text-align: center;">
						<div class="user_addpic">
							<div class="picbox">
								<input type="file"  name="photo" class="testFile" onchange="unloadFileChange(this)">
							</div>
							<div id="previews" class='preview'>
								<img id="testUnloadImg" border=0 src="" />
								<input type="hidden"  name="unloadSendImgId" id="unloadSendImgId">
								<input type="hidden"  name="baseUnloadPhoto" id="baseUnloadPhoto">
								<input type="hidden" id="hiddenTdId" name="hiddenTdId">
							</div>
						</div>
					</div>
					<div class="row clearfix ">
						<div class="col-md-12 column modal_btn">
							<button type="button" class="btn sureBtns" onclick="closeUnloadModal()">确定</button>
							<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 等待卸货卸货运单上传图片 -->
		<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="arriveUnloadModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">上传图片</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body" style="text-align: center;">
						<div class="user_addpic">
							<div class="picbox">
								<input type="file"  name="photo" class="testFile" onchange="arriveUnloadChange(this)">
							</div>
							<div id="previewss" class='preview'>
								<img id="testArriveImg" border=0 src="" />
								<input type="hidden"  name="unloadArriveImgId" id="unloadArriveImgId">
								<input type="hidden"  name="baseArrivePhoto" id="baseArrivePhoto">
								<input type="hidden" id="hiddenTdIdTwo" name="hiddenTdIdTwo">
							</div>
						</div>
					</div>
					<div class="row clearfix ">
						<div class="col-md-12 column modal_btn">
							<button type="button" class="btn sureBtns" onclick="closeUnloadModal2()">确定</button>
							<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 在途运载上传图片 -->
		<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="uploadCarringModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">上传图片</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body" style="text-align: center;">
						<div class="user_addpic">
							<div class="picbox">
								<input type="file"  name="photoCarring" class="testFile" onchange="testFileCarringChange(this)">
							</div>
							<div id="previewCarring" class='preview'>
								<img id="testCarringImg" border=0 src="" />
								<input type="hidden"  name="sendImg" id="baseCarringPhoto">
								<input type="hidden" id="carringPhotoId" name="carringPhotoId">
								<input type="hidden" id="carringImgId" name="carringImgId">
							</div>
						</div>
					</div>
					<div class="row clearfix ">
						<div class="col-md-12 column modal_btn">
							<button type="button" class="btn sureBtns" onclick="updCarringImg()">确定</button>
							<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 等待发运上传图片 -->
		<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="uploadSendModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">上传图片</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body" style="text-align: center;">
						<div class="user_addpic">
							<div class="picbox">
								<input type="file"  name="photoCarring" class="testFile" onchange="testFileSendChange(this)">
							</div>
							<div id="previewSend" class='preview'>
								<img id="testSendImg" border=0 src="" />
								<input type="hidden"  name="sendImg" id="baseSendPhoto">
								<input type="hidden" id="sendPhotoId" name="sendPhotoId">
								<input type="hidden" id="sendPictureId" name="sendPictureId">
							</div>
						</div>
					</div>
					<div class="row clearfix ">
						<div class="col-md-12 column modal_btn">
							<button type="button" class="btn sureBtns" onclick="updWaitSendImg()">确定</button>
							<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
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
	</body>
	<script>
	//分页
		$("#page_normal").paging({
		pageNo:1,
		totalPage:${trainOrderList.totalPage},
		totalSize:${trainOrderList.limit},
		callback: function(num) {
					$.ajax({  
		        	type:'post',      
		       		url:'${pageContext.request.contextPath}/fireTrainCbulkLoading/selectTrainOrderByPage.do',  
			        data:{page:num,trainOrderSearch:JSON.stringify($("#searchForm").serializeJSON())},
			        cache:false,
			        dataType:'json',
		        	success:function(data){
		        		if(data.status==200){
		        		var trainOrderList = data.data.rows;
		        		$('#normalOrderBody').empty();
		        		$.each(trainOrderList,function(index,trainOrders){
		        			if(trainOrders.status != 7){
		        				$('#normalOrderBody').append("<tr id="+trainOrders.id+">"
										+"<td style='text-align: center;'><label class='demo--label'>"
										 +" <input class='demo--checkbox'  name='WaybillName' value='"+trainOrders.id+"' type='checkbox'   >"
			                              +"<span class='demo--checkboxInput'></span>"
			                             +"</label></td>"
			                             +"<td id=''>"+trainOrders.projectCode+"</td>"
										 +"<td id=''><a href='javascript:void(0)'</a>"+trainOrders.pleaseTrainNumber+"</td>"
									 +"<td id=''>"+trainOrders.createDate+"</td>"
									 +"<td name='resaon"+index+"'></td>"
									 +"<td id=''>"+trainOrders.updateDate+"</td>"
									 +"<td id=''>"+trainOrders.sendOperatorId+"</td>"
									 +"<td id=''>"+trainOrders.branchName+"</td>"
									 +"<td id=''>"+trainOrders.cargoName+"</td>"
									 +"<td id=''>"+trainOrders.beginSite+"</td>"
									 +"<td id=''>"+trainOrders.entruckDate+"</td>"
									 +"<td id=''>"+trainOrders.sendDate+"</td>"
									 +"<td id=''>"+trainOrders.endSite+"</td>"
									 +"<td id=''>"+trainOrders.pleaseCarNum+"</td>"
									 +"<td id=''>"+trainOrders.sureCarNum+"</td>"
									 +"<td id=''>"+trainOrders.loseCarNum+"</td>"
									 +"</tr>");
				        			$("td[name='resaon"+index+"']").html("");
				        			if(trainOrders.status==1){
				        				$("td[name='resaon"+index+"']").append("<a href='javascript:void(0)'  id='admit' onclick='admiter("+trainOrders.id+")'>等待承认</a>"); 
				        			}
				        			else if(trainOrders.status==2){
				        				$("td[name='resaon"+index+"']").append("<a href='javascript:void(0)'  id='loading' onclick='loadingEntruck("+trainOrders.id+")'>等待装车</a>"); 
				        			}else if(trainOrders.status==3){
				        				$("td[name='resaon"+index+"']").append("<a href='javascript:void(0)'  id='send' onclick='sending("+trainOrders.id+")'>等待发运</a>"); 
				        			}else if(trainOrders.status==4){
				        				$("td[name='resaon"+index+"']").append("<a href='javascript:void(0)'  id='carry' onclick='carrying("+trainOrders.id+")'>在途运载</a>"); 
				        			}else if(trainOrders.status==5){
				        				$("td[name='resaon"+index+"']").append("<a href='javascript:void(0)'  id='unloading' onclick='waitUnloading("+trainOrders.id+")'>等待卸货</a>"); 
				        			}else if(trainOrders.status==6){
				        				$("td[name='resaon"+index+"']").append("<a href='javascript:void(0)'  id='receipt' onclick='receipting("+trainOrders.id+")'>等待回单</a>"); 
				        			}
		        			}
		        		});	
		    		}
		      	}
			})
					}
		});
		</script>
		<script>
	//分页
		$("#page_history").paging({
		pageNo:1,
		totalPage:${historyResult.totalPage},
		totalSize:${historyResult.limit},
		callback: function(num) {
					$.ajax({  
		        	type:'post',      
		       		url:'${pageContext.request.contextPath}/fireTrainCbulkLoading/selectHistoryOrderByPage.do',  
			        data:{page:num,trainOrderSearch:JSON.stringify($("#searchForm").serializeJSON())},
			        cache:false,
			        dataType:'json',
		        	success:function(data){
		        		if(data.status==200){
		        		var trainOrderList = data.data.rows;
		        		$('#historyOrderBody').empty();
		        		$.each(trainOrderList,function(index,historys){
		        			if(historys.status == 7){
		        				$('#historyOrderBody').append("<tr id="+historys.id+">"
										+"<td style='text-align: center;'><label class='demo--label'>"
										 +" <input class='demo--checkbox'  name='WaybillName' value='"+historys.id+"' type='checkbox'   >"
			                              +"<span class='demo--checkboxInput'></span>"
			                             +"</label></td>"
			                             +"<td id=''>"+historys.projectCode+"</td>"
										 +"<td id=''><a href='javascript:void(0)'</a>"+historys.pleaseTrainNumber+"</td>"
									 +"<td id=''>"+historys.createDate+"</td>"
									 +"<td id=''>"+historys.updateDate+"</td>"
									 +"<td id=''>"+historys.arriveDate+"</td>"
									 +"<td id=''>"+historys.sendOperatorId+"</td>"
									 +"<td id=''>"+historys.branchName+"</td>"
									 +"<td id=''>"+historys.cargoName+"</td>"
									 +"<td id=''>"+historys.beginSite+"</td>"
									 +"<td id=''>"+historys.entruckDate+"</td>"
									 +"<td id=''>"+historys.sendDate+"</td>"
									 +"<td id=''>"+historys.endSite+"</td>"
									 +"<td id=''>"+historys.pleaseCarNum+"</td>"
									 +"<td id=''>"+historys.sureCarNum+"</td>"
									 +"<td id=''>"+historys.loseCarNum+"</td>"
									 +"</tr>");
		        			}
		        		});	
		    		}
		      	}
			})
					}
		});
		</script>
		<script>
	//分页
		$("#page_exception").paging({
		pageNo:1,
		totalPage:${exceptionResult.totalPage},
		totalSize:${exceptionResult.limit},
		callback: function(num) {
					$.ajax({  
		        	type:'post',      
		       		url:'${pageContext.request.contextPath}/fireTrainCbulkLoading/selectExceptionOrderByPage.do',  
			        data:{page:num,trainOrderSearch:JSON.stringify($("#searchForm").serializeJSON())},
			        cache:false,
			        dataType:'json',
		        	success:function(data){
		        		if(data.status==200){
		        		var trainOrderList = data.data.rows;
		        		$('#exceptionBody').empty();
		        		$.each(trainOrderList,function(index,exceptions){
		        			if(exceptions.status != 7){
		        				$('#exceptionBody').append("<tr id="+exceptions.id+">"
										+"<td style='text-align: center;'><label class='demo--label'>"
										 +" <input class='demo--checkbox'  name='WaybillName' value='"+exceptions.id+"' type='checkbox'   >"
			                              +"<span class='demo--checkboxInput'></span>"
			                             +"</label></td>"
			                             +"<td id=''>"+exceptions.projectCode+"</td>"
										 +"<td id=''><a href='javascript:void(0)'</a>"+exceptions.pleaseTrainNumber+"</td>"
									 +"<td id=''>"+exceptions.createDate+"</td>"
									 +"<td name='resaon"+index+"'></td>"
									 +"<td id=''>"+exceptions.updateDate+"</td>"
									 +"<td id=''>"+exceptions.sendOperatorId+"</td>"
									 +"<td id=''>"+exceptions.branchName+"</td>"
									 +"<td id=''>"+exceptions.cargoName+"</td>"
									 +"<td id=''>"+exceptions.beginSite+"</td>"
									 +"<td id=''>"+exceptions.entruckDate+"</td>"
									 +"<td id=''>"+exceptions.sendDate+"</td>"
									 +"<td id=''>"+exceptions.endSite+"</td>"
									 +"<td id=''>"+exceptions.pleaseCarNum+"</td>"
									 +"<td id=''>"+exceptions.sureCarNum+"</td>"
									 +"<td id=''>"+exceptions.loseCarNum+"</td>"
									 +"<td id=''>"+exceptions.exceptionReportDate+"</td>"
									 +"<td id=''>"+exceptions.exceptionReportPerson+"</td>"
									 +"<td id=''>"+exceptions.exceptionReason+"</td>"
									 +"</tr>");
				        			$("td[name='resaon"+index+"']").html("");
				        			if(exceptions.status==1){
				        				$("td[name='resaon"+index+"']").append("等待承认"); 
				        			}
				        			else if(exceptions.status==2){
				        				$("td[name='resaon"+index+"']").append("等待装车"); 
				        			}else if(exceptions.status==3){
				        				$("td[name='resaon"+index+"']").append("等待发运"); 
				        			}else if(exceptions.status==4){
				        				$("td[name='resaon"+index+"']").append("在途运载"); 
				        			}else if(exceptions.status==5){
				        				$("td[name='resaon"+index+"']").append("等待卸货"); 
				        			}else if(exceptions.status==6){
				        				$("td[name='resaon"+index+"']").append("等待回单"); 
				        			}
		        			}
		        		});	
		    		}
		      	}
			})
					}
		});
		</script>
		<script>
	//分页
		$("#page_delete").paging({
		pageNo:1,
		totalPage:${deleteResult.totalPage},
		totalSize:${deleteResult.limit},
		callback: function(num) {
					$.ajax({  
		        	type:'post',      
		       		url:'${pageContext.request.contextPath}/fireTrainCbulkLoading/selectDeleteOrderByPage.do',  
			        data:{page:num,trainOrderSearch:JSON.stringify($("#searchForm").serializeJSON())},
			        cache:false,
			        dataType:'json',
		        	success:function(data){
		        		if(data.status==200){
		        		var trainOrderList = data.data.rows;
		        		$('#deleteOrderBody').empty();
		        		$.each(trainOrderList,function(index,deletes){
		        			if(deletes.status != 7){
		        				$('#deleteOrderBody').append("<tr id="+deletes.id+">"
										+"<td style='text-align: center;'><label class='demo--label'>"
										 +" <input class='demo--checkbox'  name='WaybillName' value='"+deletes.id+"' type='checkbox'   >"
			                              +"<span class='demo--checkboxInput'></span>"
			                             +"</label></td>"
			                             +"<td id=''>"+deletes.projectCode+"</td>"
										 +"<td id=''><a href='javascript:void(0)'</a>"+deletes.pleaseTrainNumber+"</td>"
									 +"<td id=''>"+deletes.createDate+"</td>"
									 +"<td name='resaon"+index+"'></td>"
									 +"<td id=''>"+deletes.updateDate+"</td>"
									 +"<td id=''>"+deletes.sendOperatorId+"</td>"
									 +"<td id=''>"+deletes.branchName+"</td>"
									 +"<td id=''>"+deletes.cargoName+"</td>"
									 +"<td id=''>"+deletes.beginSite+"</td>"
									 +"<td id=''>"+deletes.entruckDate+"</td>"
									 +"<td id=''>"+deletes.sendDate+"</td>"
									 +"<td id=''>"+deletes.endSite+"</td>"
									 +"<td id=''>"+deletes.pleaseCarNum+"</td>"
									 +"<td id=''>"+deletes.sureCarNum+"</td>"
									 +"<td id=''>"+deletes.loseCarNum+"</td>"
									 +"<td id=''>"+deletes.deleteDate+"</td>"
									 +"<td id=''>"+deletes.deletePerson+"</td>"
									 +"<td id=''>"+deletes.deleteReason+"</td>"
									 +"</tr>");
				        			$("td[name='resaon"+index+"']").html("");
				        			if(deletes.status==1){
				        				$("td[name='resaon"+index+"']").append("等待承认"); 
				        			}
				        			else if(deletes.status==2){
				        				$("td[name='resaon"+index+"']").append("等待装车"); 
				        			}else if(deletes.status==3){
				        				$("td[name='resaon"+index+"']").append("等待发运"); 
				        			}else if(deletes.status==4){
				        				$("td[name='resaon"+index+"']").append("在途运载"); 
				        			}else if(deletes.status==5){
				        				$("td[name='resaon"+index+"']").append("等待卸货"); 
				        			}else if(deletes.status==6){
				        				$("td[name='resaon"+index+"']").append("等待回单"); 
				        			}
		        			}
		        		});	
		    		}
		      	}
			})
					}
		});
		</script>
	
	<!--给货物赋值-->
	<script>
		function setCargoName(param){
			var cargoName = $(param).find("option:checked").text();
			$(param).next('input[type=hidden]').val(cargoName);
		}
	</script>

</html>