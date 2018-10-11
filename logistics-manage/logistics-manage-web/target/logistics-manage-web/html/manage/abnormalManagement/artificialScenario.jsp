<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>系统情景设定</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/manage/abnormalManagement/artificialScenario.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/public.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/abnormalManagement/artificialScenario.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.validate.js"></script>
		<!--分页-->
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js" ></script>
		<link href="${pageContext.request.contextPath}/css/page/page.css" rel="stylesheet"/>
		<script type="text/javascript">
		$(function(){
			$("#addScenarios").click(function(){
				$("#relationUl").empty();
				$("#addScenariosForm").validate().resetForm();
			    $("#addScenariosForm")[0].reset();
				$("#addScenariosModal").modal();
				$("#formula").hide();
				$("#relationUl").append("<li><select  name='brachId'>"+
						"<c:forEach items='${branchGroups }' var='branchGroup' >"+
							"<option  value='${branchGroup.id}'>${branchGroup.name}"+
						"</option></c:forEach></select>"+
					"<img src='${pageContext.request.contextPath}/img/add1.png' class='addImg'/><img src='${pageContext.request.contextPath}/img/reduce.png' /></li>");
			});
			
			//关联号增加
			$("body").on("click",".addImg",function(){
				$("#relationUl").append("<li><select  name='brachId'><c:forEach items='${branchGroups }' var='branchGroup'><option  value='${branchGroup.id}'>${branchGroup.name}</option></c:forEach></select>\
				<img src='${pageContext.request.contextPath}/img/add1.png' class='addImg'/>\
				<img src='${pageContext.request.contextPath}/img/reduce.png'  class='reduceImg'/></li>");
			});
		});
		
		function addAbnormal(){
			var branchArray = new Array();
			$('#relationUl select[name=brachId]').each(function(j,item){
				branchArray[j]=item.value;
			});	
			var abnormalJsonArray = JSON.stringify(branchArray);
		 	$("#groupIds").val(abnormalJsonArray);
		 	
		 	var abnormalId = $("#abnormalId").val();
			if(abnormalId == null || abnormalId ==""){
				$.ajax({
					cache : true,
					type : "POST",
					url : "${pageContext.request.contextPath}/abnormal/addAbnormal.do",
					data : $('#addScenariosForm').serialize(),
					async : false,
					success : function(data) {
						if (data.status != 200) {
							$("#errorPointMsg").html(data.msg);
							$("#errorPromptIdModal").modal();
						} else {
							$("#addScenariosModal").modal("hide");
							$("#pointMsg").html("新增成功");
							$("#promptIdModal").modal();
						}
					}
				});
			}else{
				$.ajax({
					cache : true,
					type : "POST",
					url : "${pageContext.request.contextPath}/abnormal/updAbnormal.do",
					data : $('#addScenariosForm').serialize(),
					async : false,
					success : function(data) {
						if (data.status != 200) {
							$("#errorPointMsg").html(data.msg);
							$("#errorPromptIdModal").modal();
						} else {
							$("#addScenariosModal").modal("hide");
							$("#pointMsg").html("修改成功");
							$("#promptIdModal").modal();
						}
					}
				});
			}
		}
		
		function deleteAbnormal(){
			var id = $("input:checkbox[type='checkbox']:checked").val();
			if(id==null || id==""){
				return;
			}
			$.ajax({
				type : 'POST',
				url : "${pageContext.request.contextPath}/abnormal/deleteAbnormal.do",
				data : {"id":id},
				dataType : "json",
				success : function(data) {
					if(data.status==200){
						$("#deletReasonModal").modal("hide");
						$("#pointMsg").html("删除成功");
						$("#promptIdModal").modal();
					}else{
						$("#deletReasonModal").modal("hide");
						$("#pointMsg").html(data.msg);
						$("#promptIdModal").modal();
					}
				},
			}); 
		}
		
		//修改
		function updAbnormal(){
			var id = "";
			if($("input:checkbox[type='checkbox']:checked").length > 1) {
				$("#errorPointMsg").html("只能选择一项数据进行修改");
				$("#errorPromptIdModal").modal();
				return;
			} else if($("input:checkbox[type='checkbox']:checked").length == 0) {
				$("#errorPointMsg").html("请选择一项数据进行修改");
				$("#errorPromptIdModal").modal();
				return;
			}else {
				id = $("input:checkbox[type='checkbox']:checked").val();
			}
			$.ajax({
				type : 'POST',
				url : "${pageContext.request.contextPath}/abnormal/getOneAbnormal.do",
				data : {"id":id},
				dataType : "json",
				success : function(data) {
					if(data.status==200){
						$("#myModalLabel").html("修改情景");
						$("#relationUl").empty();
						$("#abnormalId").val(data.data.id);
						$("#sceneName").val(data.data.sceneName);
						$("#resonId").val(data.data.reasonType);
						if(data.data.reasonType != null){
							$("#formula").show();
							if(data.data.reasonType==1){
								$("#newDate").html("卸载损耗:");
								$("#reasonScale").val(data.data.reasonScale);
								$("#unit").html("%");
							}
							else if(data.data.reasonType==2){
								$("#newDate").html("运单时长:");
								$("#reasonScale").val(data.data.reasonScale);
								$("#unit").html("时");
							}
							else if(data.data.reasonType==3){
								$("#newDate").html("停车时长:");
								$("#reasonScale").val(data.data.reasonScale);
								$("#unit").html("分");
							}
						}
						$("#relationUl").append("<li><select id='branchId' name='brachId'>"+
								"<c:forEach items='${branchGroups }' var='branchGroup' >"+
									"<option  value='${branchGroup.id}'>${branchGroup.name}"+
								"</option></c:forEach></select>");
						$("#branchId").val(data.data.branchGroupId);
						$("#remark").val(data.data.remark); 
						$("#addScenariosModal").modal();
					}
				},
			}); 
		}
		
		function closeAndfresh(){
			$("#promptIdModal").modal("hide");
			window.location.reload();
		}
		
		function closeNofresh(){
			$("#errorPromptIdModal").modal("hide");
		}

		</script>
	</head>
	<body >
		<!-- 系统情景 -->
		<div class="form project_form container_top">
			<form class="form-inline maxWidth">
				<div id="wrap">
					<div class="form-group">
						<label>关联项目：</label>
						<select></select>
					</div>
				</div>
				<div class="foot">
					<a class="btn btn-success" href="javascript:;"><em class="search"></em>
						<span>搜索</span>
					</a>
				</div>
			</form>
		</div>
		<!-- 系统情景 -->
		<div class="container_top tableDiv">
			<div class="tabbable" id="tabs-529262">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#panel1" data-toggle="tab">系统情景</a>
					</li>

				</ul>
				<div class="tab-content">
					<!--系统情景表格开始-->
					<div class="tab-pane active" id="panel1">

						<div class="domain">
							<div class="tableBg">
								<a href="javascript:void(0)" class="exportBtn addNew" id="addScenarios"><span>添加情景</span></a>
								<a href="javascript:void(0)" class="exportBtn revise" onclick="updAbnormal()"><span>修改</span></a>
								<a href="javascript:void(0)" class="exportBtn del" id="delBtn"><span>删除</span></a>
							</div>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th>
												<label class="demo--label">
											     <input class="demo--checkbox" type="checkbox" disabled="disabled" >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label>
											</th>
											<th>名称</th>
											<th>关联项目</th>
											<th>原因</th>
											<th>说明</th>
										</tr>
									</thead>
									<tbody id="abnormalBody">
									<c:forEach items="${abnormalList.rows}" var="abnormal">
										<tr>
											<td>
												<label class="demo--label">
											     <input class="demo--checkbox" type="checkbox"   value="${abnormal.id}">
                                                 <span class="demo--checkboxInput"></span>
	                                        </label>
											</td>
											<td>${abnormal.sceneName}</td>
											<td>${abnormal.branchGroupName}</td>
											<td><c:if test="${abnormal.reasonType eq 1}">卸货损耗超额≧${abnormal.reasonScale}%</c:if>
					        					<c:if test="${abnormal.reasonType eq 2}">运单总时长超额≧${abnormal.reasonScale}时</c:if>
					        					<c:if test="${abnormal.reasonType eq 3}">停车时间超额≧${abnormal.reasonScale}分</c:if>
											</td>
											<td>${abnormal.remark}</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-md-12 column paging page_div" id="page">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 添加情景 -->
		<div class="modal fade addScenariosModal" id="addScenariosModal" tabindex="-1" role="dialog" data-backdrop="static">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">添加情景</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<h5>情景信息</h5>
						<form id="addScenariosForm">
						<input id="abnormalId" type="hidden" name="id" />
							<div class="project_info">
								<div class="form-inline">
									<div class="form-group">
										<label>&emsp;情景名称：</label>
										<input type="text" maxlength="30" id="sceneName" name="sceneName"/>
									</div>
									<div class="form-group">
										<label>&emsp;&emsp;&emsp;原因：</label>
										<select id="resonId" name="reasonType">
											<option value=""></option>
											<option value="1">卸货损耗超额</option>
											<option value="2">运单总时长超额</option>
											<option value="3">停车时间超额</option>
										</select>
									</div>
									<div class="form-group" id="formula">
										<label id="newDate">&emsp;&emsp;&emsp;公式：</label>
										<input type="text" maxlength="30" id="reasonScale" name="reasonScale"/>
										<span id="unit">%</span>
									</div>
								</div>

							</div>
							<div class="relationClass">
								<label>&emsp;关联项目部：</label>
								<ul id="relationUl">
									<%-- <li>
										<select id="branchId" name="brachId"><c:forEach items="${branchGroups }" var="branchGroup">
										<option  value="${branchGroup.id}">${branchGroup.name}</option>
										</c:forEach>
										</select>
										<img src="${pageContext.request.contextPath}/img/add1.png" class="addImg" />
										<img src="${pageContext.request.contextPath}/img/reduce.png" />
									</li> --%>
								</ul>
								<input id="groupIds" type="hidden" name="groupIds" />
							</div>
							<div class="form-inline" class="remarkForm">
								<div class="form-group">
									<label>&emsp;&emsp;&emsp;说明：</label>
									<input type="text" class="remarkInput" maxlength="100" id="remark" name="remark"/>
								</div>
							</div>
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
									<button type="button" class="btn sureBtn" onclick="addAbnormal()">确定</button>
									<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
								</div>
							</div>
						</form>
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
								<button type="button" class="btn sureBtn" data-dismiss="modal" >确定</button>
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
							<textarea class="form-control" rows="3"></textarea>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="deleteAbnormal()">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
					    </form>
					</div>
				</div>
			</div>
		</div>

		<!--提示-->
		<div class="modal fade promptModal" tabindex="-1" role="dialog" id="promptIdModal">
			<div class="modal-dialog " role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h4 class="modal-title" id="myModalLabel">提示</h4>
					<span data-dismiss="modal" aria-label="Close"></span>
			      </div>
			      <div class="modal-body">
			        <p><span id="pointMsg"></span></p>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn sureBtn" onclick="closeAndfresh()">确定</button>
			      </div>
			    </div>
			</div>
		</div>
		<div class="modal fade promptModal" tabindex="-1" role="dialog" id="errorPromptIdModal">
			<div class="modal-dialog " role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h4 class="modal-title" id="myModalLabel">提示</h4>
					<span data-dismiss="modal" aria-label="Close"></span>
			      </div>
			      <div class="modal-body" style="text-align: center;color: #000000;font-size: 24px;">
			        <span id="errorPointMsg"></span>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn sureBtn" onclick="closeNofresh()">确定</button>
			      </div>
			    </div>
			</div>
		</div>
	
	</body>
	<script>
	//分页
		$("#page").paging({
		pageNo:1,
		totalPage:${abnormalList.totalPage},
		totalSize:${abnormalList.limit},
		callback: function(num) {
				$.ajax({  
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/abnormal/selectAbnormal.do',  
		        data:{page:num},
		        cache:false,
		        dataType:'json',
	        	success:function(data){
	        		if(data.status==200){
	        		var abnormalList = data.data;
	        		$('#abnormalBody').html("");
	        		$.each(abnormalList,function(index,abnormal){
	        			$('#abnormalBody').append("<tr>"
							+"<td style='text-align: center;'><label class='demo--label'>"
							 +" <input class='demo--checkbox'  value='"+abnormal.id+"' type='checkbox'   >"
                              +"<span class='demo--checkboxInput'></span>"
                             +"</label></td>"
							 +"<td id=''>"+abnormal.sceneName+"</td>"
						 +"<td id=''>"+abnormal.branchGroupName+"</td>"
						 +"<td name='resaon"+index+"'></td>"
						 +"<td id=''>"+abnormal.remark+"</td>"
						 +"</tr>");
	        			$("td[name='resaon"+index+"']").html("");
	        			if(abnormal.reasonType==1){
	        				$("td[name='resaon"+index+"']").append("卸货损耗超额≧"+abnormal.reasonScale+"%"); 
	        			}
	        			else if(abnormal.reasonType==2){
	        				$("td[name='resaon"+index+"']").append("运单总时长超额≧"+abnormal.reasonScale+"时"); 
	        			}
	        			else if(abnormal.reasonType==3){
	        				$("td[name='resaon"+index+"']").append("停车时间超额≧"+abnormal.reasonScale+"分"); 
	        			}
	        		});	
	        	}	
	    	}
	      })
				}
		});
</script>
</html>