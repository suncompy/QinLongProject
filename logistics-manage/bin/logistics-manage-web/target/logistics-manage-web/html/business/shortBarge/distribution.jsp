<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>分配任务</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/public.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/business/shortBarge/distribtion.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/business/shortBarge/distribution.js"></script>
		<!--分页-->
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js" ></script>
		<link href="${pageContext.request.contextPath}/css/page/page.css" rel="stylesheet"/>
		<script type="text/javascript">
		
		 $(function(){
			//确认操作后页面刷新
				$("#sure_msg").click(function() {
					window.location.reload();
					$("#promptIdModal").modal("hide");
				}); 
		  });
			//分配get详情
			function openDis(id,curType){
				   $.ajax({  
	   	                type: "GET",  
	   	                url:"${pageContext.request.contextPath}/business/short/job/distribute/getMsg.do?id="+id,  
	   	                success: function(data) {  
	   	                	var unit ="";
	   	                	if(data.data.valuationUnitName ==1){
	   	                		unit = "吨";
	   	                	}else if(data.data.valuationUnitName ==0){
	   	                		unit = "件";
	   	                	}
	   	                	$("#showPro").append("<li>项目编号：<span>"+data.data.projectCode+"</span></li>"
									+"<li>货物品名：<span>"+ data.data.cargoName+"</span></li>"
									+"<li>货物规格：<span>"+data.data.cargoSpecifications+"</span></li>"
									+"<li>货物单价：<span>"+data.data.cargoPrice+"元</span></li>"
									+"<li>计价单位：<span>"+unit +"</span></li>");
	   	             	 	$("#projectId").val(data.data.id);
	   	             		$("#projectType").val(curType);
	   	                	$("#distributionModal").modal();
	   	                }
	   	            }); 
				   
					$("#showPro").html("");
			}
			
			
			// 分配
			function  subPut(){
				$.ajax({
					cache: true,  
					type : "POST",
					url : "${pageContext.request.contextPath}/business/short/job/distribute/put.do",
					data : $('#distributionId').serialize(),
					async: false,  
					success : function(data) {
						if (data.status != 1) {
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
			
			function controller(type){
				//开始
				if(type == 1){
					    var idList = new Array();  
					    var typeList = new Array(); 
				        $.each($('input:checkbox'),function(){
				            if(this.checked){
				            	var str = $(this).val();
			            		var arr =new Array();
			            		arr = str.split(",");
				            	//取，判断是否是暂停状态
				            	// arr[2]:接取还是送达
			            		if(arr[2] == 0 && (arr[1] ==0 || arr[1] ==3)){
			            			//接取阶段下的开始
			            			typeList.push(1);
				            		idList.push(arr[0]);   
				            	}else if(arr[2] == 1 && (arr[1] ==0 || arr[1] ==2)){
				            		typeList.push(2);
				            		//送达阶段下的开始
				            		idList.push(arr[0]);   
				            	}else{
				            		$("#msg").html("勾选有误,请勾选正在分配状态下的项目");
									$("#promptIdModal").modal();
									return;
				            	}
				            }
				        });
				      	 $.ajax({  
				             type: "POST",  
				             url: "${pageContext.request.contextPath}/business/short/job/distribute/begin.do",  
				             dataType: 'json',  
				             data: {"idList":idList,"typeList":typeList},  
				             success: function(data){  
				           		if(data.status != 1){
				           			$("#msg").html(data.msg);
									$("#promptIdModal").modal();
				        			}else{
				        				$("#msg").html(data.msg);
										$("#promptIdModal").modal();
				        			}
				             }  
				         });
				}
				//暂停
				if(type == 0){
					   var idList = new Array();  
					   var typeList = new Array(); 
				        $.each($('input:checkbox'),function(){
				            if(this.checked){
				            	var str = $(this).val();
			            		var arr =new Array();
			            		arr = str.split(",");
				            	//取素，判断是否是开启状态
				            	if(arr[2] == 0 && (arr[1] ==1 || arr[1] ==2)){
				            		typeList.push(1);
				            		idList.push(arr[0]);   
				            	}else if(arr[2] == 1 && (arr[1] ==1 || arr[1] ==3)){
				            		typeList.push(2);
				            		idList.push(arr[0]);   
				            	}else{
				            		$("#msg").html("勾选有误,请勾选正在分配状态下的项目");
									$("#promptIdModal").modal();
									return;
				            	}
				            }
				        });
				      	 $.ajax({  
				             type: "POST",  
				             url: "${pageContext.request.contextPath}/business/short/job/distribute/stop.do",  
				             dataType: 'json',  
				             data: {"idList":idList,"typeList":typeList},  
				             success: function(data){  
				           		if(data.status != 1){
					           			$("#msg").html(data.msg);
										$("#promptIdModal").modal();
				        			}else{
				        				$("#msg").html(data.msg);
										$("#promptIdModal").modal();
				        			}
				             }  
				         });
				}
				
			}
		    
			
		</script>
	</head>
	<!--搜索-->
	<script>
		function search(param){
			var search = $(param).parent('a').parent('div').parent('form').serialize();
			$.ajax({
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/business/short/job/listPublishJobByCriteria.do',  
		        data:search,
		        cache:false,
		        dataType:'json',
	        	success:function(data){
	        		if(data.status==200){
	        		var projectList = data.data;
	        		$('#listbody').html("");
	        		$.each(projectList,function(index,it){
	        			var proType ="";
	        			if(it.projectType==0){
	        				proType = "集装箱";
	        			}else if(it.projectType==1){
	        				proType = "散装";
	        			}
	        			var stu = "";
	        			if(it.isDistribution==0){
	        				stu ="暂停分配";
	        			}
	        			if(it.isDistribution==1){
	        				stu ="分配中";
	        			}
	        			if(it.isDistribution==2){
	        				if(it.type ==1){
	        					stu ="分配中";
	        				}
	        				if(it.type ==2){
	        					stu ="暂停分配";
	        				}
	        			}
	        			if(it.isDistribution==3){
	        				if(it.type ==1){
	        					stu ="暂停分配";
	        				}
	        				if(it.type ==2){
	        					stu ="分配中";
	        				}
	        			}
	        			
	        			var types="";
	        			if(it.type == 0){
	        				types ="接取";
	        			}
	        			if(it.type ==1){
	        				types ="送达";
	        			}
	        			var plat = "";
	        			if(it.shortBargeCarrierMode ==0){
	        				plat = "平台";
	        			}
	        			if(it.shortBargeCarrierMode ==1){
	        				plat = "自选";
	        			}
	        			$('#listbody').append("<tr><td><label class='demo--label'>"
	        								+"<input class='demo--checkbox' type='checkbox' value='"+it.id+","+it.isDistribution+","+it.type+"'>"
							           		+"<span class='demo--checkboxInput'></span></label></td>"
											+"<td><a href='javascript:;'>"+it.projectCode+"</a></td>"
											+"<td>"+proType+"</td>"
											+"<td>" + stu+"</td>"
											+"<td>"+ it.branchGroupName+"</td>"
											+"<td>" +types+"</td>"
											+"<td>"+ it.sendCargoCompanyName+"</td>"
											+"<td>"+it.receiveCargoCompanyName+"</td>"
											+"<td>"+it.cargoName+"</td>"
											+"<td>"+plat+"</td>"
											+"<td>"+it.shortBargeCarrierName+"</td>"
											+"<td><a href='#' class='distribtion' onclick='openDis("+it.id+","+it.type+")'>分配</a></td>"
											+"<td>"+it.alreadyRecNum+"</td>"
											+"<td>"+(it.carNum-it.alreadyRecNum)+"</td>"
											+"<td>--</td></tr>");
											
							/*"<tr><td><label class='demo--label'>"+
					           +"<input class='demo--checkbox' type='checkbox'  value='"+it.id,it.isDistribution,it.type+"'>"
				               +"   <span class='demo--checkboxInput'></span></label></td>"
								+"<td><a href='#'>"+it.projectCode+"</a></td>"
								+"<td>"	+ proType+"</td>"
								+"<td >" + stu+"</td>"
								+"<td>"+ it.branchGroupName+"</td>"
								+"<td>" +types+"</td>"
								+"<td>"+ it.sendCargoCompanyName+"</td>"
								+"<td>"+it.receiveCargoCompanyName+"</td>"
								+"<td>"+it.cargoName+"</td>"
								+"<td>"+plat+"</td>"
								+"<td>"+it.shortBargeCarrierName+"</td>"
								+"<td><a href='#' class='distribtion' onclick='openDis("+it.id+","+it.type+")'>分配</a></td>"
								+"<td>"+it.alreadyRecNum+"</td>"
								+"<td>"+it.carNum-it.alreadyRecNum+"</td>"
								+"<td>--</td></tr>"	*/
	        		})	
	        	}   
		    	}
			})
		}
	</script>
	<body>
		<!-- 分配任务表单 -->
		<div class="form project_form container_top">
			<form class="form-inline maxWidth">
				<div id="wrap">
					<div class="form-group">
						<label>项目编号：</label>
						<input name='projectCode' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>分支机构：</label>
						<select name="branchGroupId">
							<option></option>
							<c:forEach items="${branchGroups}" var="branchGroup">
								<option value="${branchGroup.id}">${branchGroup.name}</option>	
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label>项目类型：</label>
						<select name="projectType">
							<option></option>
							<option value="0">集装箱</option>
							<option value="1">散装</option>
						</select>
					</div>
					<div class="form-group">
						<label>阶段选择：</label>
						<select>
							<option></option> 
							<option value='0'>接取</option> 
							<option value='1'>送达</option>
						</select>
					</div>

					<div class="form-group">
						<label>货物品名：</label>
						<select name="cargoId">
							<option></option>
							<c:forEach items="${cargos}" var="cargo">
								<option value="${cargo.id}">${cargo.cargoName}</option>	
							</c:forEach>
						</select>
					</div>
					<div class="form-group dataTimes">
						<label>&emsp;日期从：</label>
						<input type="text" name='beginDate' class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy.M.d'})" />
						<i class="fa fa-calendar-check-o " id="calendar2"></i>
					</div>
					<div class="form-group dataTimes">
						<label>至</label>
						<input type="text" name='endDate' class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy.M.d'})" />
						<i class="fa  fa-calendar-check-o " id="calendar2"></i>
					</div>
					<div class="form-group">
						<label>发货企业：</label>
						<input name='sendCargoCompanyName' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>收货企业：</label>
						<input name='receiveCargoCompanyName' type="text" maxlength="30"/>
					</div>
				</div>
				<div class="foot">
					<a class="btn btn-success" href="javascript:void(0)"><em class="search"></em>
						<span  onclick="search(this)">搜索</span>
					</a>
					<div id="lookMore"></div>
				</div>
			</form>
		</div>
		<!-- 分配任务表格 -->
		<div class="container_bottom tableDiv">
			<div class="tabbable" id="tabs-529262">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#panel1" data-toggle="tab" >分配列表</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="panel1">
						<div class="domain">
							<div class="tableBg">
								<a href="javascript:void(0)" class="exportBtn start" id="waybill" onclick="return false;"><span>开始</span></a>
								<a href="javascript:void(0)" class="exportBtn pause" onclick="return false;"><span>暂停</span></a>
								<!-- <a href="javascript:void(0)" class="exportBtn complete" onclick="return false;"><span>完成</span></a> -->
							</div>
							<div class="table-responsive">
								<!--运单列表表格开始-->
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th>
												<label class="demo--label">
								            <input class="demo--checkbox" type="checkbox" name="WaybillName" >
							                  <span class="demo--checkboxInput"></span>
							                         	</label>
											</th>
											<th data-field="id">项目编号</th>
											<th>项目类型</th>
											<th>状态</th>
											<th>分支机构</th>
											<th>阶段</th>
											<th>发货企业</th>
											<th>收货企业</th>
											<th>货物品名</th>
											<th>短驳承运方式</th>
											<th>短驳承运方</th>
											<th>今日分配</th>
											<th>已领任务</th>
											<th>待领任务</th>
											<th>完成任务</th>
										</tr>
									</thead>
									<tbody id='listbody'>
										<c:forEach var="it" items="${projectList.rows}">
											<tr>
												<td><label class="demo--label">
									            <input class="demo--checkbox" type="checkbox"  value="${it.id},${it.isDistribution},${it.type}">
								                  <span class="demo--checkboxInput"></span>
								                         	</label></td>
												<td>
													<a href="javascript:void(0)">${it.projectCode}</a>
												</td>
												<td>
													<c:if test="${it.projectType==0}">
															集装箱
													</c:if>
													<c:if test="${it.projectType==1}">
															散装
													</c:if>
												</td>
												<td >
													<c:if test="${it.isDistribution==0}">
														暂停分配
													</c:if>
													<c:if test="${it.isDistribution==1}">
														分配中
													</c:if>
													<c:if test="${it.isDistribution==2}">
														<c:if test="${it.type ==0}">
															分配中
														</c:if>
														<c:if test="${it.type ==1}">
															暂停分配
														</c:if>
													</c:if>
													<c:if test="${it.isDistribution==3}">
														<c:if test="${it.type ==0}">
															暂停分配
														</c:if>
														<c:if test="${it.type ==1}">
															分配中
														</c:if>
													</c:if>
												</td>
												<td>${it.branchGroupName}</td>
												<td>
													<c:if test="${it.type ==0}">
														接取
													</c:if>
													<c:if test="${it.type ==1}">
														送达
													</c:if>
												</td>
												<td>${it.sendCargoCompanyName}</td>
												<td>${it.receiveCargoCompanyName}</td>
												<td>${it.cargoName}</td>
												<td>
													<c:if test="${it.shortBargeCarrierMode ==0}">
														平台
													</c:if>
													<c:if test="${it.shortBargeCarrierMode ==1}">
														自选
													</c:if>
												</td>
												<td>${it.shortBargeCarrierName}</td>
												<td>
													<a href="javascript:void(0)" class="distribtion" onclick="openDis(${it.id},${it.type})">分配</a>
												</td>
												<td>${it.alreadyRecNum}</td>
												<td>${it.carNum-it.alreadyRecNum}</td>
												<td>--</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<!--运单列表表格结束-->
							</div>
						</div>
						<div class="row clearfix">
								<div class="col-md-12 column paging page_div" id="page"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--分配任务模态框-->
		<div class="modal fade distributionModal" tabindex="-1" role="dialog" aria-hidden="true" id="distributionModal" data-backdrop="static">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" class="myModalLabel">
							每日任务分配
						</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="distributionId">
							<input type="hidden" id="projectId" name="projectId"/>
							<input type="hidden" id="projectType" name="projectType"/>
							<div class="project_info">
								<h5>运单信息</h5>
								<ul id="showPro" class="waitUl">
									<!-- <li>项目编号：<span>J00001</span></li>
									<li>货物品名：<span>煤炭</span></li>
									<li>货物规格：<span>4*5</span></li>
									<li>货物单价：<span>1000元</span></li>
									<li>计价单位：<span>吨</span></li> -->
								</ul>
							</div>
							<hr />
							<div class="project_info">
								<h5>任务分配</h5>
								<div class="form-inline pLeft">
									<div class="form-group">
										<label>今日分配车辆：</label>
										<input type="text" maxlength="20" name="num"/>
										<span class="vehicle">辆</span>
									</div>
								</div>
							</div>
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
								    <button type="button" class="btn sureBtn" onclick="subPut()">确定</button>
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
							<span>请选择一条要操作的数据</span>
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
									<div><span>您已选择</span><b class="errnums">13</b> <span id="msgSpan"></span></div>
								</div>
								<div class="row clearfix ">
									<div class="col-md-12 column modal_btn">
										<button type="button" class="btn sureBtn" onclick="controller(1)">确定</button>
										<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
		
		<!-- 提醒模态框 -->
				<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="deltialModal2" data-backdrop="static">
					<div class="modal-dialog " role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">提示</h4>
								<span data-dismiss="modal" aria-label="Close"></span>
							</div>
							<div class="modal-body">
								<div class="body-content">
									<div><span>您已选择</span><b class="errnums">13</b> <span id="msgSpan2"></span></div>
								</div>
								<div class="row clearfix ">
									<div class="col-md-12 column modal_btn">
										<button type="button" class="btn sureBtn" onclick="controller(0)">确定</button>
										<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<!--确认提示模态框-->		
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
			 
			 
	</body>


<script>
	//分页
	$("#page").paging({
		pageNo:1, 
		totalPage: ${projectList.totalPage},
		totalSize:${projectList.limit},
		callback: function(num) {
				$.ajax({  	
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/business/short/job/byPage/list.do',  
		        data:{page:num},
		        cache:false,
		        dataType:'json',
	        	success:function(data){
	        		if(data.status==200){
	        		var projectList = data.data;
	        		$('#listbody').html("");
	        		$.each(projectList,function(index,it){
	        			
	        			var proType ="";
	        			if(it.projectType==0){
	        				proType = "集装箱";
	        			}else if(it.projectType==1){
	        				proType = "散装";
	        			}
	        			var stu = "";
	        			if(it.isDistribution==0){
	        				stu ="暂停分配";
	        			}
	        			if(it.isDistribution==1){
	        				stu ="分配中";
	        			}
	        			if(it.isDistribution==2){
	        				if(it.type ==1){
	        					stu ="分配中";
	        				}
	        				if(it.type ==2){
	        					stu ="暂停分配";
	        				}
	        			}
	        			if(it.isDistribution==3){
	        				if(it.type ==1){
	        					stu ="暂停分配";
	        				}
	        				if(it.type ==2){
	        					stu ="分配中";
	        				}
	        			}
	        			
	        			var types="";
	        			if(it.type ==0){
	        				types ="接取";
	        			}
	        			if(it.type ==1){
	        				types ="送达";
	        			}
	        			var plat = "";
	        			if(it.shortBargeCarrierMode ==0){
	        				plat = "平台";
	        			}
	        			if(it.shortBargeCarrierMode ==1){
	        				plat = "自选";
	        			}
        				$('#listbody').append("<tr><td><label class='demo--label'>"
        								+"<input class='demo--checkbox' type='checkbox' value='"+it.id+","+it.isDistribution+","+it.type+"'>"
						           		+"<span class='demo--checkboxInput'></span></label></td>"
										+"<td><a href='javascript:;'>"+it.projectCode+"</a></td>"
										+"<td>"+proType+"</td>"
										+"<td>" + stu+"</td>"
										+"<td>"+ it.branchGroupName+"</td>"
										+"<td>" +types+"</td>"
										+"<td>"+ it.sendCargoCompanyName+"</td>"
										+"<td>"+it.receiveCargoCompanyName+"</td>"
										+"<td>"+it.cargoName+"</td>"
										+"<td>"+plat+"</td>"
										+"<td>"+it.shortBargeCarrierName+"</td>"
										+"<td><a href='#' class='distribtion' onclick='openDis("+it.id+","+it.type+")'>分配</a></td>"
										+"<td>"+it.alreadyRecNum+"</td>"
										+"<td>"+(it.carNum-it.alreadyRecNum)+"</td>"
										+"<td>--</td></tr>");
	        		})	
	        	}  
	    	}
		})
	}		
})
</script>
</html>