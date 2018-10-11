<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
<head>
	<meta charset="UTF-8">
	<title>车辆授权</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/public/public.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/manage/transportationManagement/VehicleAuthorization.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/config/url.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/transportationManagement/VehicleAuthorization.js"></script>
	<!--分页-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.serializejson.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js"></script>
	<link href="${pageContext.request.contextPath}/css/page/page.css" rel="stylesheet" />
	
	<!--同意挂靠-->		
	<script type="text/javascript">
	  	function agree(){
	        var idList = new Array();  
	        $.each($('#panel2 input:checkbox'),function(){
	            if(this.checked){
	            		idList.push($(this).val());  
	            }
	        });
	        $.ajax({  
	             type: "POST",  
	             url: "${pageContext.request.contextPath}/transport/anchord/agree.do",  
	             dataType: 'json',  
	             data: {"ids":idList},  
	             success: function(data){  
	           		if(data.status != 1){
	        				$("#msgContent").html("");
	        				$("#msgContent").html("操作失败");
							$("#remindModal").modal();
	        				$("#remindModal input[name=param]").val("");
							$("#remindModal input[name=remindFlag]").val(1);//0刷新 1 不刷新
							remainFreash();
	        			}else{
	        				$("#msgContent").html("操作成功");
							$("#remindModal").modal();
	        				$("#remindModal input[name=param]").val("");
							$("#remindModal input[name=remindFlag]").val(0);//0刷新 1 不刷新
							remainFreash();
	        			}
	             }  
	         }); 
		    };
	</script>		
	
	<!--驳回挂靠-->
	<script type="text/javascript">
	  	function reject(){
	        var idList = new Array();  
	        $.each($('#panel2 input:checkbox'),function(){
	            if(this.checked){
	            		idList.push($(this).val());  
	            }
	        });
	    	 $.ajax({  
	             type: "POST",  
	             url: "${pageContext.request.contextPath}/transport/anchord/reject.do",  
	             dataType: 'json',  
	             data: {"ids":idList},  
	             success: function(data){  
	           		if(data.status != 1){
	        				$("#msgContent").html("");
	        				$("#msgContent").html("操作失败");
							$("#remindModal").modal();
	        				$("#remindModal input[name=param]").val("");
							$("#remindModal input[name=remindFlag]").val(1);//0刷新 1 不刷新
							remainFreash();
	        			}else{
	        				$("#msgContent").html("操作成功");
							$("#remindModal").modal();
	        				$("#remindModal input[name=param]").val("");
							$("#remindModal input[name=remindFlag]").val(0);//0刷新 1 不刷新
							remainFreash();
	        			}
	             }  
	         }); 
		    };
	</script>
	
	<!--删除驳回记录-->
	<script type="text/javascript">
	function deletes(){
	    var idList = new Array();  
	    $.each($('#panel3 input:checkbox'),function(){
	        if(this.checked){
	        		idList.push($(this).val());  
	        }
	    });
		 $.ajax({  
	         type: "POST",  
	         url: "${pageContext.request.contextPath}/transport/rejectRecord/delete.do",  
	         dataType: 'json',  
	         data: {"ids":idList},  
	         success: function(data){  
	       		if(data.status != 1){
	    				$("#msgContent").html("");
	    				$("#msgContent").html("操作失败");
						$("#remindModal").modal();
	    				$("#remindModal input[name=param]").val("");
						$("#remindModal input[name=remindFlag]").val(1);//0刷新 1 不刷新
						remainFreash();
	    			}else{
	    				$("#msgContent").html("操作成功");
						$("#remindModal").modal();
	    				$("#remindModal input[name=param]").val("");
						$("#remindModal input[name=remindFlag]").val(0);//0刷新 1 不刷新
						remainFreash();
	    			}
	         }  
	     }); 
	    };
	</script>
	
	<!--取消挂靠-->
	<script>
	function cancel(){
	    var idList = new Array();  
	    $.each($('#panel1 input:checkbox'),function(){
	        if(this.checked){
	        		idList.push($(this).val());  
	        }
	    });
		 $.ajax({  
	         type: "POST",  
	         url: "${pageContext.request.contextPath}/transport/anchord/an_cancel.do",  
	         dataType: 'json',  
	         data: {"ids":idList},  
	         success: function(data){  
	       		if(data.status != 1){
	    				$("#msgContent").html("");
	    				$("#msgContent").html("操作失败");
						$("#remindModal").modal();
	    				$("#remindModal input[name=param]").val("");
						$("#remindModal input[name=remindFlag]").val(1);//0刷新 1 不刷新
						remainFreash();
	    			}else{
	    				$("#msgContent").html("操作成功");
						$("#remindModal").modal();
	    				$("#remindModal input[name=param]").val("");
						$("#remindModal input[name=remindFlag]").val(0);//0刷新 1 不刷新
						remainFreash();
	    			}
	         }  
	     }); 
	};
	</script>
	
	<!--开始-->
	<script>
	function startOrStop(param){
	    var idList = new Array();  
	    $.each($('#panel1 input:checkbox'),function(){
	        if(this.checked){
	        		idList.push($(this).val());  
	        }
	    });
	    //param:  0,开始  1,暂停
	    if(param == 0){
	    	$.ajax({  
		         type: "POST",  
		         url: "${pageContext.request.contextPath}/transport/anchord/start.do",  
		         dataType: 'json',  
		         data: {"ids":idList},  
		         success: function(data){  
		       		if(data.status != 1){
		    				$("#msgContent").html("");
		    				$("#msgContent").html("操作失败");
							$("#remindModal").modal();
		    				$("#remindModal input[name=param]").val("");
							$("#remindModal input[name=remindFlag]").val(1);//0刷新 1 不刷新
							remainFreash();
		    			}else{
		    				$("#msgContent").html("操作成功");
							$("#remindModal").modal();
		    				$("#remindModal input[name=param]").val("");
							$("#remindModal input[name=remindFlag]").val(0);//0刷新 1 不刷新
							remainFreash();
		    			}
		         }  
		     }); 
	    }else{
	    	$.ajax({  
		         type: "POST",  
		         url: "${pageContext.request.contextPath}/transport/anchord/stop.do",  
		         dataType: 'json',  
		         data: {"ids":idList},  
		         success: function(data){  
		       		if(data.status != 1){
		    				$("#msgContent").html("");
		    				$("#msgContent").html("操作失败");
							$("#remindModal").modal();
		    				$("#remindModal input[name=param]").val("");
							$("#remindModal input[name=remindFlag]").val(1);//0刷新 1 不刷新
							remainFreash();
		    			}else{
		    				$("#msgContent").html("操作成功");
							$("#remindModal").modal();
		    				$("#remindModal input[name=param]").val("");
							$("#remindModal input[name=remindFlag]").val(0);//0刷新 1 不刷新
							remainFreash();
		    			}
		         }  
		     }); 
	    }
		 
	};
	</script>
</head>
<body>
	<!--搜索表单-->
	<div class="form project_form container_top">
			<form class="form-inline maxWidth" id="searchForm">
				<div id="wrap">
					<div class="form-group">
						<label>挂靠方：</label>
						<input name="userName" type="text" />
					</div>
					<div class="form-group">
						<label>车辆所属：</label>
						<select name="userType">
							<option></option>
							<option value="0">个人</option>
							<option value="1">车队</option>
						</select>
					</div>
					<div class="form-group">
						<label>联系方式：</label>
						<input type="text" name="phone" maxlength="11"/>
					</div>
					
					<div class="form-group dataTimes">
						<label>日期从&nbsp;</label>
						<input name="beginDate" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-M-d'})" />
						<i class="fa fa-calendar-check-o " id="calendar2"></i>
					</div>
					<div class="form-group dataTimes">
						<label class="secTime">至</label>
						<input name="endDate" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-M-d'})" />
						<i class="fa  fa-calendar-check-o " id="calendar2"></i>
					</div>
				</div>
				<div class="foot">
					<a class="btn btn-success" href="javascript:;"><em class="search"></em>
						<span onclick="search()">搜索</span>
					</a>
				</div>
			</form>
			<input id="orderStatus" name="orderStatus" type="hidden" value="1"/>
		</div>
	<!-- 车辆授权表单 -->
		
	<div class="container_top tableDiv">
		<div class="tabbable">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#panel1" data-toggle="tab" onclick="searchStatus(1)">挂靠列表</a></li>
				<li><a href="#panel2" data-toggle="tab" onclick="searchStatus(2)">申请列表</a></li>
				<li><a href="#panel3" data-toggle="tab" onclick="searchStatus(3)">已驳回</a></li>
			</ul>
			<div class="tab-content">
				<!--挂靠列表-->
				<div class="tab-pane active" id="panel1">
					<div class="domain">
						<div class="tableBg">
						<c:forEach items='${permissions }' var='permission' >
							<c:if test="${permission.code=='vehicleAuthorizationStart'}">
							<a href="javascript:void(0)" class="exportBtn start" id="start" onclick="modalFn(5)"><span>开始</span></a>
							</c:if>
							<c:if test="${permission.code=='vehicleAuthorizationPause'}">
						 	<a href="javascript:void(0)" class="exportBtn pause" id="pause" onclick="modalFn(6)"><span>暂停</span></a>
							</c:if>
							<c:if test="${permission.code=='vehicleAuthorizationCancelAnchored'}">
							<a href="javascript:void(0)" class="exportBtn cancel" id="cancelAnchored" onclick="modalFn(1)"><span>取消挂靠</span></a>
							</c:if>
						</c:forEach>
						</div>
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr class="tableTop">
										<th><label class="demo--label"> <input
												class="demo--checkbox" type="checkbox"> <span
												class="demo--checkboxInput"></span>
										</label></th>
										<th>挂靠方</th>
										<th>车辆所属</th>
										<th>状态</th>
										<th>负责人</th>
										<th>联系方式</th>
										<!-- <th>挂靠原因</th> -->
										<th>挂靠时间</th>
									</tr>
								</thead>
								<tbody id="tbody_1">
									<c:forEach var="it" items="${anList.rows}">
											<tr>
												<td><label class="demo--label"> <input
														class="demo--checkbox" type="checkbox" value="${it.recordId}" > <span
														class="demo--checkboxInput"></span>
												</label></td>
												<td>${it.name}</td>
												<td><%-- <c:if test="${it.type == 0}">
													个人
												</c:if> <c:if test="${it.type == 1}">
													车队
												</c:if> --%>${it.type}</td>
												<td>
													<c:if test="${it.status == 0}">
														挂靠中
													</c:if> 
													<c:if test="${it.status == 1}">
														暂停接单
													</c:if>
													<c:if test="${it.status == 2}">
														已锁定
													</c:if> 
												</td>
												<td>${it.changerName}</td>
												<td>${it.phone}</td>
												<%-- <td>${it.reason}</td> --%>
												<td><fmt:formatDate value="${it.anchoredDate}" pattern="yyyy-MM-dd" /></td>

											</tr>
									</c:forEach>

								</tbody>
							</table>
						</div>
					</div>
					<div class="row clearfix">
						<div class="col-md-12 column paging page_div" id="page_1">
							</div>
					</div>
				</div>
				<!--挂靠列表结束-->
				
				<!--申请列表-->
				<div class="tab-pane" id="panel2">
					<div class="domain">
						<div class="tableBg">
						<c:forEach items='${permissions }' var='permission' >
							<c:if test="${permission.code=='vehicleAuthorizationAgree'}">
							<a href="javascript:void(0)" class="exportBtn agree" id="agreeAnchored"  onclick="modalFn(2)"><span>同意挂靠</span></a>
							</c:if>
							<c:if test="${permission.code=='vehicleAuthorizationReject'}">
						 	<a href="javascript:void(0)" class="exportBtn reject" id="reject" onclick="modalFn(3)"><span>驳回</span></a>
							</c:if>
						</c:forEach>
								
								
						</div>
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr class="tableTop">
										<th><label class="demo--label"> <input
												class="demo--checkbox" type="checkbox" > <span
												class="demo--checkboxInput"></span>
										</label></th>
										<th>挂靠方</th>
										<th>类别</th>
										<th>负责人</th>
										<th>联系方式</th>
										<!-- <th>申请原因</th> -->
										<th>申请时间</th>
									</tr>
								</thead>
								<tbody id="tbody_2">
									<c:forEach var="it" items="${applyList.rows}">
										<c:if test="${it.status == 3}">
											<tr>
												<td><label class="demo--label"> <input
														class="demo--checkbox" type="checkbox" value="${it.recordId}"> <span
														class="demo--checkboxInput" ></span>
												</label></td>
												<td>${it.name}</td>
												<td><c:if test="${it.type == 0}">
													个人
												</c:if> <c:if test="${it.type == 1}">
													车队
												</c:if></td>
												<td>${it.changerName}</td>
												<td>${it.phone}</td>
												<%-- <td>${it.reason}</td> --%>
												<td><fmt:formatDate value="${it.anchoredDate}"
														pattern="yyyy-MM-dd"  /></td>
											</tr>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div class="row clearfix">
						<div class="col-md-12 column paging page_div" id="page_2"></div>
					</div>
				</div>
				<!--申请列表表格结束-->
				
				<!--已驳回表格开始-->
				<div class="tab-pane" id="panel3">
					<div class="domain">
						<div class="tableBg">
						<c:forEach items='${permissions }' var='permission' >
							<c:if test="${permission.code=='delVehicleAuthorization'}">
							<a href="javascript:void(0)" class="exportBtn del" id="delBtn" onclick="modalFn(4)"><span>删除</span></a>
							</c:if>
						</c:forEach>
								
						</div>
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr class="tableTop">
										<th><label class="demo--label"> <input
												class="demo--checkbox" type="checkbox"> <span
												class="demo--checkboxInput"></span>
										</label></th>
										<th>挂靠方</th>
										<th>类别</th>
										<th>负责人</th>
										<th>联系方式</th>
										<!-- <th>申请原因</th> -->
										<th>申请时间</th>
										<th>驳回人</th>
										<th>驳回时间</th>
									</tr>
								</thead>
								<tbody id="tbody_3">
								<c:forEach var="it" items="${rejectList.rows}">
											<tr>
										<td><label class="demo--label"> <input
												class="demo--checkbox" type="checkbox" value="${it.recordId}"> <span
												class="demo--checkboxInput"></span>
										</label></td>
										<td>${it.userName}</td>
										<td>
												<c:if test="${it.type == 0}">
													个人
												</c:if> <c:if test="${it.type == 1}">
													车队
												</c:if>
										</td>
										<td>${it.changerName}</td>
										<td>${it.phone}</td>
										<%-- <td>${it.reason}</td> --%>
										<td>
											${it.applydate}
										</td>
										<td>${it.operateName}</td>
										<td><fmt:formatDate value="${it.anchoredDate}"
														pattern="yyyy-MM-dd" /> </td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div class="row clearfix">
						<div class="col-md-12 column paging page_div" id="page_3"></div>
					</div>
				</div>
				<!--已驳回表格结束结束-->
				
			</div>
		</div>
	</div>
		<!--提醒模态框 -->
		<div class="modal fade deletRemindModal" style="z-index:99999;" tabindex="-1" role="dialog" id="remindModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="msgTitle">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="body-content">
							<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
							<span id='msgContent'></span>
							<input type="hidden" name="remindFlag" value="0" />
							<input type="hidden" name="param" />
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button"class="btn sureBtn" data-dismiss="modal" onclick="sureRemind(1)">确定</button>
								<!--<button type="button" class="btn cancleBtn" data-dismiss="modal" onclick="sureRemind(0)">取消</button>-->
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
							<span>你是否选择删除此条信息，删除后不可还原</span>
						</div>
						<div class="delete_reason">
							<h4>删除原因</h4>
							<textarea class="form-control" rows="3"></textarea>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="deletes()">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
					    </form>
					</div>
				</div>
			</div>
		</div>
		
 
</body>
<!--分页-->
<script>
/* $("#page_2").paging({
	pageNo:1, 
	totalPage: ${rejectList.totalPage},
	totalSize:${rejectList.limit},
	callback: function(num) {
			$.ajax({  
        	type:'post',      
       		url:'${pageContext.request.contextPath}/byPage/reject/list.do',  
	        data:{page:num},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
        		var rejectList = data.data;
        		$('#tbody_4').html("");
        		$.each(rejectList,function(index,it){
        			var types =it.type;
        			if(it.type == 1){
        				types="车队"
        			}
        			if(it.type ==0){
        				types="个人";
        			}
        			$('#tbody_4').append("<tr><td><label class='demo--label'> <input"
    						+"class='demo--checkbox' type='checkbox' value='"+it.recordId+"'> <span"
    						+"class='demo--checkboxInput'></span></label></td>"
    						+"<td>"+it.userName+"</td>"
    						+"<td>"+ types + "</td>" 
    						+"<td>" +it.changerName+ "</td>"
    						+"<td>"+it.phone+"</td>"
    						+"<td>"+it.reason+"</td>"
    						+"<td>"+ it.applydate+"</td>"
    						+"<td>"+ it.operateName+"</td>"
    						+"<td>"+it.anchoredDate+"</td> </tr>");
        		})	
        	}  
    	}
	})
}		
}) */
</script>

  <!--分页-->
<script >
	 $("#page_1").paging({
		pageNo:1, 
		totalPage:${anList.totalPage},
		totalSize:${anList.limit},
		callback: function(num) {
			//searchByPage1(num);
			$.ajax({  
        	type:'post',      
       		url:'${pageContext.request.contextPath}/transport/byPage/anchored/list.do',  
	       	data:{page:num,search:JSON.stringify($('#searchForm').serializeJSON())},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
        			//htmlTable(data.data.rows);
        			var anList = data.data.rows;
        			$('#tbody_1').empty();
        			$.each(anList,function(index,it){
        					var types = it.type; 
        	    		/* 	if(it.type == 1){
        	    				types="车队"
        	    			}
        	    			if(it.type ==0){
        	    				types="个人";
        	    			} */
        	    			var statu = "";
        	    			if(it.status == 0){
        	    				statu = "挂靠中"
        	    			}else if(it.status == 1){
        	    				statu = "暂停接单";
        	    			}else if(it.status == 2){
        	    				statu = "已锁定";
        	    			}
        	    			
        	    			$('#tbody_1').append("<tr><td style='text-align: center;'><label class='demo--label'>"
        							 +" <input class='demo--checkbox'  type='checkbox' value='"+it.recordId+"'>"
        	                  		 +" <span class='demo--checkboxInput'></span></label></td>"
        							+"<td>"+it.name+"</td> "
        							+"<td>"+types+"</td> "
        							+"<td>"+statu+"</td> "
        							+"<td>"+it.changerName+"</td> "
        							+"<td>"+it.phone+"</td> "
        							+"<td>"+it.reason+"</td> "
        							+"<td>"+it.anchoredDate+"</td> </tr>");	
        			})	
        		}
    		}
		})
		}
	}) 
	
	$("#page_2").paging({
		pageNo:1, 
		totalPage: ${applyList.totalPage},
		totalSize:${applyList.limit},
		callback: function(num) {
			$.ajax({  
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/transport/byPage/apply/list.do',  
		       	data:{page:num,search:JSON.stringify($('#searchForm').serializeJSON())},
		        cache:false,
		        dataType:'json',
	        	success:function(data){
	        		if(data.status==200){
	        			var applyList = data.data.rows;
	        			$('#tbody_2').empty();
	        			$.each(applyList,function(index,it){
	        					var types =it.type;
	        	    			 if(it.type == 1){
	        	    				types="车队"
	        	    			}
	        	    			if(it.type ==0){
	        	    				types="个人";
	        	    			} 
	        	    			$('#tbody_2').append("<tr><td style='text-align: center;'><label class='demo--label'>"
	        							 +" <input class='demo--checkbox'  type='checkbox' value='"+it.recordId+"'>"
	        	                  		 +" <span class='demo--checkboxInput'></span></label></td>"
	        							+"<td>"+it.name+"</td> "
	        							+"<td>"+types+"</td> "
	        							+"<td>"+it.changerName+"</td> "
	        							+"<td>"+it.phone+"</td> "
	        							+"<td>"+it.reason+"</td> "
	        							+"<td>"+it.anchoredDate+"</td> </tr>");	
	        			})	
	        		}
	    		}
			})
		}
	}) 
	
	$("#page_3").paging({
		pageNo:1, 
		totalPage: ${rejectList.totalPage},
		totalSize:${rejectList.limit},
		callback: function(num) {
			$.ajax({  
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/transport/byPage/reject/list.do',  
		       	data:{page:num,search:JSON.stringify($('#searchForm').serializeJSON())},
		        cache:false,
		        dataType:'json',
	        	success:function(data){
	        		if(data.status==200){
	        			var rejectList = data.data.rows;
	        			$('#tbody_3').empty();
	        			$.each(rejectList,function(index,it){
	        					var types =it.type;
	        	    		 if(it.type == 1){
	        	    				types="车队"
	        	    			}
	        	    			if(it.type ==0){
	        	    				types="个人";
	        	    			} 
	        	    			$('#tbody_3').append("<tr><td style='text-align: center;'><label class='demo--label'>"
	        							 +" <input class='demo--checkbox'  type='checkbox' value='"+it.recordId+"'>"
	        	                  		 +" <span class='demo--checkboxInput'></span></label></td>"
	        							+"<td>"+it.name+"</td> "
	        							+"<td>"+types+"</td> "
	        							+"<td>"+it.changerName+"</td> "
	        							+"<td>"+it.phone+"</td> "
	        							+"<td>"+it.reason+"</td> "
	        							+"<td>"+it.applydate+"</td> "
	        							+"<td>"+it.operateName+"</td> "
	        							+"<td>"+it.anchoredDate+"</td> </tr>");	
	        			})	
	        		}
	    		}
			})
		}
	}) 
</script>

<!--执行分页查询的方法 -->		
<script>
	function searchByPage1(num){
		$.ajax({  
        	type:'post',      
       		url:'${pageContext.request.contextPath}/transport/byPage/anchored/list.do',  
	       	data:{page:num,search:JSON.stringify($('#searchForm').serializeJSON())},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
        			htmlTable(data.data.rows);
        		}
    		}
		})
	}
	
	function searchByPage2(num){
		$.ajax({  
        	type:'post',      
       		url:'${pageContext.request.contextPath}/transport/byPage/apply/list.do',  
	       	data:{page:num,search:JSON.stringify($('#searchForm').serializeJSON())},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
        			htmlTable2(data.data.rows);
        		}
    		}
		})
	}
	
	function searchByPage3(num){
		$.ajax({  
        	type:'post',      
       		url:'${pageContext.request.contextPath}/transport/byPage/reject/list.do',  
	       	data:{page:num,search:JSON.stringify($('#searchForm').serializeJSON())},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
        			htmlTable3(data.data.rows);
        		}
    		}
		})
	}
</script>

<!--将分页返回结果写入表格-->
<script>
	function htmlTable(results){
		var anList = results;
		$('#tbody_1').empty();
		$.each(anList,function(index,it){
				var types =it.type;
    			/* if(it.type == 1){
    				types="车队"
    			}
    			if(it.type ==0){
    				types="个人";
    			} */
    			var statu = "";
    			if(it.status == 0){
    				statu = "已取消"
    			}else if(it.status == 1){
    				statu = "已挂靠";
    			}
    			$('#tbody_1').append("<tr><td style='text-align: center;'><label class='demo--label'>"
						 +" <input class='demo--checkbox'  type='checkbox' value='"+it.recordId+"'>"
                  		 +" <span class='demo--checkboxInput'></span></label></td>"
						+"<td>"+it.name+"</td> "
						+"<td>"+types+"</td> "
						+"<td>"+statu+"</td> "
						+"<td>"+it.changerName+"</td> "
						+"<td>"+it.phone+"</td> "
						+"<td>"+it.reason+"</td> "
						+"<td>"+it.anchoredDate+"</td> </tr>");	
		})	
    	
	}
	
	function htmlTable2(results){
		var applyList = results;
		$('#tbody_2').empty();
		$.each(applyList,function(index,it){
				var types ="";
    			if(it.type == 1){
    				types="车队"
    			}
    			if(it.type ==0){
    				types="个人";
    			}
    			$('#tbody_2').append("<tr><td style='text-align: center;'><label class='demo--label'>"
						 +" <input class='demo--checkbox'  type='checkbox' value='"+it.recordId+"'>"
                  		 +" <span class='demo--checkboxInput'></span></label></td>"
						+"<td>"+it.name+"</td> "
						+"<td>"+types+"</td> "
						+"<td>"+it.changerName+"</td> "
						+"<td>"+it.phone+"</td> "
						+"<td>"+it.reason+"</td> "
						+"<td>"+it.anchoredDate+"</td> </tr>");	
		})	
    	
	}
	
	function htmlTable3(results){
		var rejectList = results;
		$('#tbody_3').empty();
		$.each(rejectList,function(index,it){
				var types ="";
    			if(it.type == 1){
    				types="车队"
    			}
    			if(it.type ==0){
    				types="个人";
    			}
    			$('#tbody_3').append("<tr><td style='text-align: center;'><label class='demo--label'>"
						 +" <input class='demo--checkbox'  type='checkbox' value='"+it.recordId+"'>"
                  		 +" <span class='demo--checkboxInput'></span></label></td>"
						+"<td>"+it.name+"</td> "
						+"<td>"+types+"</td> "
						+"<td>"+it.changerName+"</td> "
						+"<td>"+it.phone+"</td> "
						+"<td>"+it.reason+"</td> "
						+"<td>"+it.applydate+"</td> "
						+"<td>"+it.operateName+"</td> "
						+"<td>"+it.anchoredDate+"</td> </tr>");	
		})	
    	
	}
			
			
</script>
<script>
	function searchStatus(param){
		$("#orderStatus").val("");
		$("#orderStatus").val(param);
	}
	
	function search(){
		var orderStatus = $("#orderStatus").val();
		if(orderStatus == 1){
			$('#tbody_1').html("");
			$.ajax({  
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/transport/byPage/anchored/list.do',  
		        data:{page:1,search:JSON.stringify($("#searchForm").serializeJSON())},
		        cache:false,
		        dataType:'json',
	        	success:function(data){
	        		if(data.status==200){
	        			$("#page_1").paging({
	        				pageNo:1, 
	        				totalPage:data.data.totalPage,
	        				totalSize:data.data.limit,
	        				callback: function(num) {
	        					searchByPage1(num);
	        				}
	        			}) 
	        			htmlTable(data.data.rows);
	        		}
	        	}
			})
		}else if(orderStatus == 2){
			$('#tbody_2').html("");
			$.ajax({  
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/transport/byPage/apply/list.do',  
		        data:{page:1,search:JSON.stringify($("#searchForm").serializeJSON())},
		        cache:false,
		        dataType:'json',
	        	success:function(data){
	        		if(data.status==200){
	        			$("#page_2").paging({
	        				pageNo:1, 
	        				totalPage:data.data.totalPage,
	        				totalSize:data.data.limit,
	        				callback: function(num) {
	        					searchByPage2(num);
	        				}
	        			}) 
	        			htmlTable2(data.data.rows);
	        		}
	        	}
			})
		}else{
			$('#tbody_3').html("");
			$.ajax({  
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/transport/byPage/reject/list.do',  
		        data:{page:1,search:JSON.stringify($("#searchForm").serializeJSON())},
		        cache:false,
		        dataType:'json',
	        	success:function(data){
	        		if(data.status==200){
	        			$("#page_3").paging({
	        				pageNo:1, 
	        				totalPage:data.data.totalPage,
	        				totalSize:data.data.limit,
	        				callback: function(num) {
	        					searchByPage3(num);
	        				}
	        			}) 
	        			htmlTable3(data.data.rows);
	        		}
	        	}
			})
		}
	}
</script>
<!--弹出框-->
<script>
function modalFn(param){
	var panel = "panel1";
	if(param==1 || param ==5 || param ==6){ //1取消挂靠 //5 开始 //6 暂停
		panel='panel1'
	}else if(param==2 || param ==3){//2 同意挂靠 //3 驳回
		panel='panel2'
	}else{ //4  删除
		panel='panel3'
	}
	
	var checkLength = $("#"+panel+" input:checkbox[type='checkbox']:checked").length;
	if(checkLength > 0) {
		if(param==6 && checkLength >1){
			$("#msgContent").html("抱歉,不可批量删除");
			$("#remindModal").modal();
			$("#remindModal input[name=remindFlag]").val(1);
			return;
		}
		var i = 0;
	  	$.each($("#"+panel+" input:checkbox"),function(){
            if(this.checked){
            		i++;
            }
        });
        var sureMsg ="";
        if(param==1){
        	 sureMsg ="你已选择"+i+"条数据，是否取消挂靠！";
        }else if(param==2){
         	sureMsg ="你已选择"+i+"条数据，是否同意挂靠申请！";
        }else if(param==3){
         	sureMsg ="你已选择"+i+"条数据，是否驳回挂靠申请！";
        }else if(param==4){
        	sureMsg ="你已选择"+i+"条数据，是否删除驳回记录！";
        }else if(param==5){
        	sureMsg ="你已选择"+i+"条数据，是否开始！";
        }else if(param==6){
        	//sureMsg ="你已选择"+i+"条数据，是否删除驳回记录！";
        	sureMsg ="你已选择"+i+"条数据，是否暂停！";
        }
        $('#msgContent').html("");
	    $('#msgContent').html(sureMsg);
	    $('#remindModal input[name=param]').val(param);
		$("#remindModal").modal();
	} else if(checkLength == 0) {
		$("#msgContent").html("请选择一条要操作的数据");
		$("#remindModal").modal();
		$("#remindModal input[name=remindFlag]").val(1);
		return ;
	}
}

</script>

<!--提醒js-->
<script>
	function sureRemind(sureFlag){
		if(sureFlag==0){
			$("#remindModal").hide();
			return;
		}
		var param =	$("#remindModal input[name=param]").val();
		
		$("#remindModal").hide();
		 if(param==1){
	        cancel();//取消挂靠
        }else if(param==2){
        	agree();//同意挂靠
        }else if(param==3){
       		reject();//驳回挂靠
        }else if(param==4){
        	deletes();
        }else if(param==5){
        	startOrStop(0);
        }else if(param==6){
        	startOrStop(1);
        }else{
	        
        }
	}
	
	function remainFreash(){
		var flag = $("#remindModal input[name=remindFlag]").val();
		if(flag==0){
			window.location.reload();
		}
	}
</script>


</html>