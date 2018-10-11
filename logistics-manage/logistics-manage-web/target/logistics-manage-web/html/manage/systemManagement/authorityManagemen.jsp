<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>权限管理</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/public.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/manage/systemManagement/authorityManagement.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/systemManagement/authorityManagemen.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.validate.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js" ></script>
		<link href="${pageContext.request.contextPath}/css/page/page.css" rel="stylesheet"/>
	</head>
	<body >
		<!-- 权限管理表格 -->
		<div class="container_bottom tableDiv">
			<div class="tabbable" id="tabs-529262">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#panel1" data-toggle="tab">权限管理</a>
					</li>
				</ul>
				<div class="tab-content">
					<!--权限管理表格开始-->
					<div class="tab-pane active" id="panel1">
						<div class="domain">
							<div class="tableBg">
								<a href="javascript:void(0)" class="exportBtn add" id="addRole"><span>新建角色</span></a>
								<a href="javascript:void(0)" class="exportBtn revise" id="modify"><span>修改</span></a>
							<a href="javascript:void(0)" class="exportBtn del" id="delBtn"><span>删除</span></a>
							</div>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox" disabled="disabled"   >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label> </th>
											<th>角色名称</th>
											<th>权限</th>
										</tr>
									</thead>
									<tbody id="authorityTbody">
									<c:forEach items="${roleList.rows}" var="role">
										<tr id="${role.id}" name="${role.name}">
											<td class="checks"><label class="demo--label">
											<c:if  test="${role.isDefault==1}">
													<input name="roleCheckBox" disabled="disabled" value="${role.id}" class="demo--checkbox" type="checkbox"   >
											</c:if>
											<c:if  test="${role.isDefault==0}">
													<input name="roleCheckBox" value="${role.id}" class="demo--checkbox" type="checkbox"   >
											</c:if>
                                                 <span class="demo--checkboxInput"></span>
	                                        </label></td>
	                                        <input id="roleId" type="hidden" value="${role.id}" />
											<td id="">${role.name}</td>
											<td id="">
											<c:forEach items="${role.permissions}" var="per" >
													${per.description}&nbsp;&nbsp;
											</c:forEach></td>
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
		
<!--角色详情模态框-->
    <!--角色详情模态框-->
		<div class="modal fade roleDetail" id="roleDetail" tabindex="-1" role="dialog" data-backdrop="static">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">角色详情</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<ul class="clearfix ">
							<li><label>角色名称：</label><span id="roleName"></span></li>
							<li class="authority"><label>&emsp;&emsp;权限：</label>
								<dl id="roleDetailMsg">
									<dt>业务</dt>
									<dd class="clearfix" id="business">
                                   	</dd>
                                   	<br />
                                   	<dt>财务</dt>
									<dd class="clearfix" id="finance">
                                   	</dd>
                                   	<br />
                                   	<dt>设置</dt>
									<dd class="clearfix" id="set">
                                   	</dd>
								</dl>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>		
<!-- 新建角色模态框 -->
		<div class="modal fade addRoleModal" id="addRoleModal" tabindex="-1" role="dialog" data-backdrop="static">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">新建角色</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
 						<form  id="addForm">
						<ul class="clearfix ">
							<input type="hidden" name="id" id="addRoleId" />
							<li><label>角色名称：</label><input type="text" id="addRoleName" /></li>
							<li class="authority"><label>权限分配：</label>
								<dl id="permissionCheckBox">
									<c:forEach items="${permissionList}" var="perList">
								<dt>${perList.name}</dt>
								<dd class="clearfix">
									<c:forEach items="${perList.permissions}" var="permission" varStatus="status" >
									<div>
										<label class="demo--label">
									    <input class="demo--checkbox" name="permissionId" type="checkbox" value="${permission.id}" >
                                        <span class="demo--checkboxInput"></span>
                                    </label><span >${permission.description}</span>
									</div> 
									</c:forEach>
								</dd>
							</c:forEach>
								</dl>
							</li>
						</ul>
						
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" id="addRoleBtn" class="btn sureBtn">确定</button>
								<button type="button"  class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 成功提醒模态框 -->
		<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="successModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="body-content">
							<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
							<span id="successMsg"></span>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="successSure()" >确定</button>
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
						<h4 class="modal-title" id="msgTitle">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="body-content">
							<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
							<span id='msgContent'></span>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button"class="btn cancleBtn" data-dismiss="modal">确定</button>
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
							<span>你是否选择删除此角色，删除后不可还原</span>
						</div>
						<div class="delete_reason">
							<h4>删除原因</h4>
							<textarea class="form-control" rows="3"></textarea>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" id="delSureBtn">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
					    </form>
					</div>
				</div>
			</div>
		</div>
		
	</body>				
<!-- 新建角色 后台-->		
<script type="text/javascript">
	$("#addRole").click(function(){
	 	$('#myModalLabel').html("新增角色");
	 	$('#addRoleId').val("");
   	    $("#addForm").validate().resetForm();
   	    $("[name=permissionId]:checkbox").attr("checked", false);
	    $("#addForm")[0].reset();
   	    $("#addRoleModal").modal();
   })
	
	$("#addRoleBtn").click(function(){
		var hideRoleId = $("#addRoleId").val();
		if(hideRoleId == null || hideRoleId ==""){
			var ids = new Array(); 
			$("input[name='permissionId']:checked").each(function(){ 
				 ids.push($(this).val());
			   //alert($(this).val()); 
			}) 
			
			if(ids==""){
				$("#msgContent").html("请勾选角色的权限");
				$("#deletnullModal").modal();
				return ;
			}
			var roleName=$("#addRoleName").val();
			$.ajax({  
		        	type:'post',      
		       		url:'${pageContext.request.contextPath}/systemManagement/addRole.do',  
			        data:{ids:ids,roleName:roleName},
			        cache:false,
			        dataType:'json',
		        	success:function(data){
		        		if(data.status==200){
		        			$("#addRoleModal").hide();
		        			$("#successMsg").html("新增角色成功");
	        				$("#successModal").modal();
		        		}else{
		        			$("#msgContent").html("");
        					$("#msgContent").html(data.msg);
        					$("#deletnullModal").modal();
		        		}
		        	}  
		    	})
		
	
		}else{
			var ids = new Array(); 
			$("input[name='permissionId']:checked").each(function(){ 
				 ids.push($(this).val());
			   //alert($(this).val()); 
			}) 
			
			if(ids==""){
				$("#msgContent").html("请勾选角色的权限");
				$("#deletnullModal").modal();
				return ;
			}
			var roleName=$("#addRoleName").val();
			$.ajax({  
		        	type:'post',      
		       		url:'${pageContext.request.contextPath}/systemManagement/updateRole.do',  
			        data:{ids:ids,roleName:roleName,roleId:hideRoleId},
			        cache:false,
			        dataType:'json',
		        	success:function(data){
		        		if(data.status==200){
		        			$("#successMsg").html("修改角色成功");
	        				$("#successModal").modal();
		        		}else{
		        			$("#msgContent").html("");
		        			$("#msgContent").html(data.msg);
		        			$("#deletnullModal").modal();
		        		}
		        	}  
		    	})
		
		}
		
	})

	function successSure(){
		$("#successModal").modal();
		window.location.reload();
	}
</script>
<!-- 显示角色详情 后台 -->
<script>
$(function(){
		//角色详情模态框
			$("body").on("click", "#panel1 tbody tr td:not(:nth-child(1))", function() {
				$(this).parent().toggleClass("bgclass").siblings("#panel1 tbody tr").removeClass("bgclass");
				 var roleId = $(this).parent().attr("id");
				// alert(roleId);
				 var roleName = $(this).parent().attr("name");
				 //alert(roleName);
			 $.ajax({
						type : 'POST',
						url : "${pageContext.request.contextPath}/systemManagement/getRole.do",
						data : {"roleId":roleId},
						dataType : "json",
						cache : false,
						success : function(data) {
							if(data.status==200){
							$("#business").html("");
							$("#finance").html("");
							$("#set").html("");
							$("#roleName").html(roleName);
								$.each(data.data,function(index,permissionInformation){
									 var permissionList = permissionInformation.permissions;
									// alert(permissionList);
									//alert(permissionInformation.name);
									if(permissionInformation.name =='business'){
										$.each(permissionList,function(index,permission){
										$("#business").append("<div><label class='demo--label'><input checked='checked' disabled='disabled' class='demo--checkbox' type='checkbox'><span class='demo--checkboxInput'></span>"+
	                                	    "</label><span>"+permission.description+"</span></div>");
										});
									}
									if(permissionInformation.name =='finance'){
										$.each(permissionList,function(index,permission){
										$("#finance").append("<div><label class='demo--label'><input checked='checked' disabled='disabled' class='demo--checkbox' type='checkbox'><span class='demo--checkboxInput'></span>"+
	                                	    "</label><span>"+permission.description+"</span></div>");
										});
									}
									if(permissionInformation.name =='set'){
										$.each(permissionList,function(index,permission){
										$("#set").append("<div><label class='demo--label'><input checked='checked' disabled='disabled' class='demo--checkbox' type='checkbox'><span class='demo--checkboxInput'></span>"+
	                                	    "</label><span>"+permission.description+"</span></div>");
										});
									}
									
								});
								$("#roleDetail").modal();
							}
						},
					});  
			});
	})
</script>

<!--修改角色信息-->
<script>
	$("#modify").click(function(){
		if($("input[name='roleCheckBox']:checked").length > 1) {
			$("#msgContent").html("抱歉，不可批量操作");
			$("#deletnullModal").modal();
			return ;
		} else if($("input[name='roleCheckBox']:checked").length == 0) {
			$("#msgContent").html("请选择一条要操作的数据");
			$("#deletnullModal").modal();
			return ;
		} else{
			$("#addForm")[0].reset();
		}
		$('#myModalLabel').html("修改角色");
		
		var ids = new Array(); 
		var i=0;
		var roleName;
		$("input[name='roleCheckBox']:checked").each(function(){ 
			roleName=$(this).parent().parent().parent().attr('name');
			//alert(roleName);
			i++;
			if(i>1){
				$("#deletnullModal").modal();
				return ;
			}
		   ids.push($(this).val());
		})
		if(ids==""){
			$("#msgContent").html("请勾选您要修改的角色");
			$("#deletnullModal").modal();
			return ;
		}
		var roleId=$("input[name='roleCheckBox']:checked").val();
		$("#addRoleId").val(roleId);
		$.ajax({  
        	type:'post',      
       		url:'${pageContext.request.contextPath}/systemManagement/getRole.do',  
	        data : {"roleId":roleId},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
					//alert(roleName);
					$("#addRoleName").val(roleName);
					//取消选中
					$("[name=permissionId]:checkbox").attr("checked", false);
					
					$.each(data.data,function(index,permissionInformation){
						 var permissionList = permissionInformation.permissions;
						// alert(permissionList);
						//alert(permissionInformation.name);
						if(permissionInformation.name =='business'){
							$.each(permissionList,function(index,permission){
								$("input[name='permissionId'][value='"+permission.id+"']").attr("checked",true);
							});
						}
						if(permissionInformation.name =='finance'){
							$.each(permissionList,function(index,permission){
								$("input[name='permissionId'][value='"+permission.id+"']").attr("checked",true);
							});
						}
						if(permissionInformation.name =='set'){
							$.each(permissionList,function(index,permission){
								$("input[name='permissionId'][value='"+permission.id+"']").attr("checked",true);
							});
						}
						
					});
					$("#addRoleModal").modal();
        		}else{
        			$("#msgContent").html("");
        			$("#msgContent").html(data.msg);
        			$("#deletnullModal").modal();
        		}
        	}  
    	})
		
	})
</script>

<!--删除角色详情 后台-->
<script type="text/javascript">
	
	$('#delBtn').click(function(){
		if($("input[name='roleCheckBox']:checked").length > 1) {
			$("#msgContent").html("抱歉，不可批量操作");
			$("#deletnullModal").modal();
			return ;
		} else if($("input[name='roleCheckBox']:checked").length == 0) {
			$("#msgContent").html("请选择一条要操作的数据");
			$("#deletnullModal").modal();
			return ;
		} else{
			$("#addForm")[0].reset();
		}
		$('#deletReasonModal').modal();
	})

	$("#delSureBtn").click(function(){
		var ids = new Array(); 
		$("input[name='roleCheckBox']:checked").each(function(){ 
		   ids.push($(this).val());
		})
		if(ids==""){
			$("#msgContent").html("请勾选您要删除的角色");
			$("#deletnullModal").modal();
			return ;
		}
		$.ajax({  
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/systemManagement/delRole.do',  
		        data:{ids:ids},
		        cache:false,
		        dataType:'json',
	        	success:function(data){
	        		if(data.status==200){
	        			$("#deletReasonModal").hide();
	        			$("#successMsg").html("删除角色成功");
	        			$("#successModal").modal();
	        		}else{
		        		$("#msgContent").html(data.msg);
						$("#deletnullModal").modal();
	        		}
	        	}  
	    	})
	})

</script>
<script>
	 
</script>

<!--分页-->
<script>
	//分页
	$("#page").paging({
		pageNo:1, 
		totalPage: ${roleList.totalPage},
		totalSize:${roleList.limit},
		callback: function(num) {
				$.ajax({  
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/systemManagement/selectRole.do',  
		        data:{page:num},
		        cache:false,
		        dataType:'json',
	        	success:function(data){
	        		if(data.status==200){
	        		var roleList = data.data;
	        		$('#authorityTbody').html("");
	        		$.each(roleList,function(index,role){
	        			$('#authorityTbody').append("<tr id='"+role.id+"' name='"+role.name+"'>"
						+"<td class='checks'><label class='demo--label'>"
						+"<input name='roleCheckBox' value='"+role.id+"' class='demo--checkbox' type='checkbox'   >"
                        +"<span class='demo--checkboxInput'></span></label></td>"
	                    +"<input id='roleId' type='hidden' value='"+role.id+"' />"
						+"<td id=''>"+role.name+"</td>"
						+"<td name='per"+index+"'></td></tr>")
	        			$("td[name='per"+index+"']").html(""); 
	        			$.each(role.permissions,function(j,permission){
	        				$("td[name='per"+index+"']").append(permission.description+"&nbsp;&nbsp;"); 
	        			})
	        		})	
	        	}  
	    	}
		})
	}		
	})
</script>
</html>