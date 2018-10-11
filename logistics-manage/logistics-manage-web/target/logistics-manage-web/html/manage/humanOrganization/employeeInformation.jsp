<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>员工信息</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/manage/humanOrganization/employeeInformation.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/public.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/humanOrganization/employeeInformation.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.validate.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/My97DatePicker/WdatePicker.js"></script>
			<!--shilvfei加的-->
		<script type="text/javascript"  src="${pageContext.request.contextPath}/public/js/jquery.cityselect.js"></script>	
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/js/city.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js" ></script>
		<link href="${pageContext.request.contextPath}/css/page/page.css" rel="stylesheet"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.serializejson.js"></script>
	</head>
	<!--搜索-->
	<script>
		function search(param){
			var status = $('#searchForm input[name=workStatus]').val();
			var page = 'employPage';
			if(status==0){
				$("#searchForm input[name=workStatus]").val(0);;//在职
				page = 'employPage';
			}else{
				$("#searchForm input[name=workStatus]").val(1);//离职
				page = 'employLeavePage';
			}
			$.ajax({
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/humanOrganization/listEmployByPage.do',  
		       	data:{page:1,search:JSON.stringify($('#searchForm').serializeJSON())},
		        cache:false,
		        dataType:'json',
	        	success:function(data){
	        		if(data.status==200){
	        			htmlTable(data.data.rows);
	        			$("#"+page+"").paging({
							pageNo:1, 
							totalPage: data.data.totalPage,
							totalSize: data.data.limit,
							callback: function(num) {
								searchByPage(num);
							}
						})	
	        		}
	        	}	
			})	
		}
	</script>
	
	<body>
		<!--员工信息搜索表单 -->
		<div class="form project_form container_top">
			<form class="form-inline maxWidth" id='searchForm'>
				<div id="wrap">
					<div class="form-group">
						<label>&emsp;&emsp;姓名：</label>
						<input name='name' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>&nbsp;性别：</label>
						<select name='sex'>
							<option></option>
							<option value="1">男</option>
							<option value="0">女</option>
						</select>
					</div>
					<div class="form-group">
						<label>婚姻状况：</label>
						<select name='isMarry'>
							<option></option>
							<option value="1">已婚</option>
							<option value="0">未婚</option>
						</select>
					</div>
					<div class="form-group">
						<label>学历：</label>
						<select name='education'>
							<option></option>
							<option value="0">小学</option>
							<option value="1">初中</option>
							<option value="2">高中</option>
							<option value="3">大专</option>
							<option value="4">本科</option>
							<option value="5">研究生</option>
						</select>
					</div>
					<div class="form-group">
						<label>角色：</label>
						<select name="roleId">
							<option></option>
							<c:forEach items="${roles}" var="role">
								<option  value='${role.id}'>${role.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label>联系方式：</label>
						<input name='phone' type="text" maxlength="11"/>
					</div>
					<div class="form-group">
						<label>所属机构：</label>
						<select name="branchGroupId">
							<option></option>
							<c:forEach items="${branchGroups}" var="branchGroup">
								<option value='${branchGroup.id}'>${branchGroup.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group ">
						<label>身份证号：</label>
						<input type="text" name="idcard" maxlength="30"/>
					</div>
					<div class="form-group dataTimes ">
						<label>入职日期:&nbsp;</label>
						<input name="beginDate" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy.M.d'})" />
						<i class="fa fa-calendar-check-o " id="calendar2"></i>
					</div>
					<div class="form-group dataTimes secTime">
						<label>至</label>
						<input name="endDate" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy.M.d'})" />
						<i class="fa  fa-calendar-check-o " id="calendar2"></i>
					</div>
					<input name="workStatus" type="hidden" value="0" />
				</div>
				<div class="foot">
					<a class="btn btn-success" href="javascript:;"><em class="search"></em> 
						<span onclick="search(this)">搜索</span>
					</a>
					<div id="lookMore"></div>
				</div>
		    </form>
		</div>
			
		<!-- 员工信息表格 -->
		<div class="container_bottom tableDiv">
			<div class="tabbable" id="tabs-529262">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#panel1" data-toggle="tab" onclick="listEmploy(0)">员工信息</a>
					</li>
					<li class="newLength">
						<a href="#panel2" data-toggle="tab" onclick="listEmploy(1)">已离职员工信息</a>
					</li>
				</ul>
				<div class="tab-content">
					<!--员工信息表格开始-->
					<div class="tab-pane active" id="panel1">
						<div class="domain">
							<div class="tableBg">
								<a href="javascript:void(0)" class="exportBtn addNew"  id="addemployee"><span>添加成员</span></a>
								<a href="javascript:void(0)" class="exportBtn revise" id="modify"><span>修改</span></a>
								<a href="javascript:void(0)" class="exportBtn del" id="delBtn"><span>禁用</span></a>
							</div>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox" disabled="disabled"   >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label> </th>
											<th>姓名</th>
											<th>性别</th>
											<th>年龄</th>
											<th>婚姻状况</th>
											<th>学历</th>
											<th>角色</th>											
											<th>手机号</th>
											<th>所属机构</th>
											<th>邮箱</th>
											<th>入职时间</th>
											<th>身份证号</th>
											<th>地址信息</th>
										</tr>
									</thead>
									<tbody id="employTbody">
									<c:forEach items="${employeeResult.rows}" var="employee">
										<tr>
											<td><label class="demo--label">
											     <input name="empCheckBox" value="${employee.id}" class="demo--checkbox" type="checkbox"   >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label></td>
											<td id="">${employee.name}</td>
											<c:if  test="${employee.sex==1}">
													<td>男</td>
											</c:if>
											<c:if  test="${employee.sex==0}">
													<td>女</td>
											</c:if>
											<td>${employee.age}</td>
											<c:if  test="${employee.isMarry==0}">
													<td>未婚</td>
											</c:if>
											<c:if  test="${employee.isMarry==1}">
													<td>已婚</td>
											</c:if>
											<c:if  test="${employee.isMarry==null}">
													<td></td>
											</c:if>
											<c:if  test="${employee.education==0}">
													<td>小学</td>
											</c:if>
											<c:if  test="${employee.education==1}">
													<td>初中</td>
											</c:if>
											<c:if  test="${employee.education==2}">
													<td>高中</td>
											</c:if>
											<c:if  test="${employee.education==3}">
													<td>大专</td>
											</c:if>
											<c:if  test="${employee.education==4}">
													<td>本科</td>
											</c:if>
											<c:if  test="${employee.education==5}">
													<td>研究生</td>
											</c:if>
											<c:if  test="${employee.education==null}">
													<td></td>
											</c:if>
											<td id="">${employee.roleName}</td>
											<td id="">${employee.phone}</td>
											<td>${employee.groupName}</td>
											<td id="">${employee.email}</td>
											<td id=""><fmt:formatDate value="${employee.startWorkDate}" pattern="yyyy.MM.dd"/> </td>
											<td>${employee.idcard}</td>
											<td>${employee.areaCode}${employee.address}</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-md-12 column paging page_div" id="employPage">
							</div>
						</div>
					</div>
					<div class="tab-pane" id="panel2">
						<div class="domain">
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox" disabled="disabled"   >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label> </th>
											<th>姓名</th>
											<th>性别</th>
											<th>年龄</th>
											<th>婚姻状况</th>
											<th>学历</th>
											<th>角色</th>											
											<th>手机号</th>
											<th>所属机构</th>
											<th>邮箱</th>
											<th>入职时间</th>
											<th>离职时间</th>
											<th>身份证号</th>
											<th>地址信息</th>
										</tr>
									</thead>
									<tbody id="employLeaveTbody">
										<c:forEach items="${employeeLeaveResult.rows}" var="employee">
										<tr>
											<td><label class="demo--label">
											     <input name="empCheckBox" value="${employee.id}" class="demo--checkbox" type="checkbox"   >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label></td>
											<td id="">${employee.name}</td>
											<c:if  test="${employee.sex==1}">
													<td>男</td>
											</c:if>
											<c:if  test="${employee.sex==0}">
													<td>女</td>
											</c:if>
											<td>${employee.age}</td>
											<c:if  test="${employee.isMarry==0}">
													<td>未婚</td>
											</c:if>
											<c:if  test="${employee.isMarry==1}">
													<td>已婚</td>
											</c:if>
											<c:if  test="${employee.isMarry==null}">
													<td></td>
											</c:if>
											<c:if  test="${employee.education==0}">
													<td>小学</td>
											</c:if>
											<c:if  test="${employee.education==1}">
													<td>初中</td>
											</c:if>
											<c:if  test="${employee.education==2}">
													<td>高中</td>
											</c:if>
											<c:if  test="${employee.education==3}">
													<td>大专</td>
											</c:if>
											<c:if  test="${employee.education==4}">
													<td>本科</td>
											</c:if>
											<c:if  test="${employee.education==5}">
													<td>研究生</td>
											</c:if>
											<c:if  test="${employee.education==null}">
													<td></td>
											</c:if>
											<td id="">${employee.roleName}</td>
											<td id="">${employee.phone}</td>
											<td>${employee.groupName}</td>
											<td id="">${employee.email}</td>
											<td id=""><fmt:formatDate value="${employee.startWorkDate}" pattern="yyyy.MM.dd"/> </td>
											<td id=""><fmt:formatDate value="${employee.leaveOfficeDate}" pattern="yyyy.MM.dd"/> </td>
											<td>${employee.idcard}</td>
											<td>${employee.areaCode}${employee.address}</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-md-12 column paging page_div" id="employLeavePage">
							</div>
						</div>
					</div>
				</div>	
			</div>		
		</div>	
				
		<!-- 添加分支模态框 -->
		<div class="modal fade addemployeeModal" id="addemployeeModal" tabindex="-1" role="dialog" data-backdrop="static">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">添加成员</h4>
					<span data-dismiss="modal" aria-label="Close"></span>
				</div>
				<div class="modal-body">
					<h5>成员信息</h5>
					<form id="addForm">
					<input type="hidden" name="id" id="addEmpId" />	
					<div class="form-inline">
						<div class="form-group">
							<label>&emsp;&emsp;姓名：</label>
							<input type="text" maxlength="30" name="name" id="userName"/>
						</div>
						<div class="form-group">
							<label>&emsp;&emsp;性别：</label>
							<select id="ender" name="sex">
								<option value="1">男</option>
								<option value="0">女</option>
							</select>
						</div>
						<div class="form-group">
							<label>&emsp;&emsp;年龄：</label>
							<input type="text" name="age" maxlength="30"/>
						</div>
						<div class="form-group">
							<label>婚姻状况：</label>
							<select name="isMarry">
								<option value="1">已婚</option>
								<option value="0">未婚</option>
							</select>
						</div>
						<div class="form-group">
							<label>&emsp;&emsp;学历：</label>
							<select name="education">
								<option value="0">小学</option>
								<option value="1">初中</option>
								<option value="2">高中</option>
								<option value="3">大专</option>
								<option value="4">本科</option>
								<option value="5">研究生</option>
							</select>
						</div>						
						<div class="form-group">
							<label>角色分配：</label>
							<select name="roleId" id="roleId">
								<c:forEach items="${roles}" var="role">
								<option  value='${role.id}'>${role.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label>所属机构：</label>
							<select name="branchGroupId">
								<option></option>
								<c:forEach items="${branchGroups}" var="branchGroup">
								<option value='${branchGroup.id}'>${branchGroup.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group dataTimes">
							<label>入职时间：</label>
							<input id="d244" type="text" name="startWorkDate" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy.MM.dd'})" />
						<i class="fa  fa-calendar-check-o " id="entryTime"></i>
						</div>
					</div>
					<div class="form-inline">
						<div class="form-group">
							<label>联系方式：</label>
							<input type="text" maxlength="30" name="phone"/>
      						
						</div>
						<div class="form-group">
							<label>&emsp;&emsp;邮箱：</label>
							<input type="text" maxlength="30" name="email"/>
						</div>
						<div class="form-group">
							<label>身份证号：</label>
							<input type="text" maxlength="30" name="idcard" id="idNumber"/>
						</div>
					</div>
					<div class="form-inline">
						<div class="form-group" id='cityAddress'>
							<label>地址信息：</label>
							<select name="province"  class="prov">
							</select>
							<select name="city"  class="city">
							</select>
							<select name="district"  class="dist">
							</select>
						</div>
						<input type="text" class="addressInput" name="address" maxlength="100"/>
					</div>
					<div class="row clearfix ">
						<div class="col-md-12 column modal_btn">
							<button type="button" class="btn sureBtn" id="addEmpBtn">确定</button>
							<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
						</div>
					</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
		<!-- 失败提醒模态框 -->
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
								<button type="button" class="btn cancleBtn" data-dismiss="modal" onclick="successSure()" >取消</button>
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
							<span>抱歉，不可批量禁用</span>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn cancleBtn" data-dismiss="modal">确定</button>
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
						<h4 class="modal-title">禁用原因</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="deletId">
						<div class="body-content">
							<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
							<span>你是否选择禁用此成员，禁用后不可还原</span>
						</div>
						<div class="delete_reason">
							<h4>禁用原因</h4>
							<textarea class="form-control" rows="3"></textarea>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="disableEmp()">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
					</form>
					</div>
				</div>
			</div>
		</div>
</body>
<!-- 新建员工信息后台-->		
<script type="text/javascript">
	$("#addEmpBtn").click(function(){
		var hideEmpId = $("#addEmpId").val();
		if(hideEmpId == null || hideEmpId ==""){
			var formParam = $("#addForm").serialize();//序列化表格内容为字符串 
			$.ajax({  
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/humanOrganization/addEmployee.do',  
		        data:formParam,
		        cache:false,
		        dataType:'json',
	        	success:function(data){
	        		if(data.status==200){
	        			$("#addemployeeModal").hide();
	        			$("#deletnullModal").modal();
	        			$("#successMsg").html("");
	        			$("#successMsg").html("新增员工信息成功");
	        			$("#successModal").modal();
						//window.location.reload();
	        		}else{
	        			$("#msgContent").html("");
	        			$("#msgContent").html(data.msg);
	        			$("#deletnullModal").modal();
	        		}
	        	}  
	    	})
		}else{
			var formParam = $("#addForm").serialize();//序列化表格内容为字符串 
			$.ajax({  
		        	type:'post',      
		       		url:'${pageContext.request.contextPath}/humanOrganization/updateEmployee.do',  
			        data:formParam,
			        cache:false,
			        dataType:'json',
		        	success:function(data){
		        		if(data.status==200){
		        			$("#addemployeeModal").hide();
		        			$("#successMsg").html("");
		        			$("#successMsg").html("修改员工信息成功");
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

</script>

<!-- 禁用员工	-->	
<script  type="text/javascript">
function disableEmp(){
	var ids = new Array(); 
	$("input[name='empCheckBox']:checked").each(function(){ 
	   ids.push($(this).val());
	})
	if(ids==""){
		$("#msgContent").html("请选择一条您要禁用的员工信息");
		$("#deletnullModal").modal();
		//alert("请勾选您要删除的角色");
		return ;
	}

	$.ajax({  
        	type:'post',      
       		url:'${pageContext.request.contextPath}/humanOrganization/delEmployee.do',  
	        data:{ids:ids},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
        			$("#deletReasonModal").hide();
        			$("#successMsg").html("");
        			$("#successMsg").html("禁用员工信息成功");
        			$("#successModal").modal();
        		}else{
        			$("#msgContent").html("");
        			$("#msgContent").html(data.msg);
        			$("#deletnullModal").modal();
        		}
        	}  
    	})
}

</script>

<!-- 修改员工 -->
<script  type="text/javascript">
$("#modify").click(function(){
		if($("input:checkbox[type='checkbox']:checked").length > 1) {
			$("#msgContent").html("抱歉，不可批量操作");
			$("#deletnullModal").modal();
			return ;
		} else if($("input:checkbox[type='checkbox']:checked").length == 0) {
			$("#msgContent").html("请选择一条您要修改的员工信息");
			$("#deletnullModal").modal();
			return ;
		} else {
			$("#addForm")[0].reset();
		}
	
		var ids = new Array(); 
		var i=0;
		$("input[name='empCheckBox']:checked").each(function(){
			i++;
			if(i>1){
				$("#deletnullModal").modal();
				return ;
			}
			ids.push($(this).val());
			$("#addEmpId").val($(this).val());
		})
		if(ids==""){
			$("#msgContent").html("请选择一条您要修改的员工信息");
			$("#deletnullModal").modal();
			return ;
		}
		 $('#myModalLabel').html('修改员工');
		$.ajax({  
        	type:'post',      
       		url:'${pageContext.request.contextPath}/humanOrganization/getEmployee.do',  
	        data:{ids:ids},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
        			var employInfo = data.data;
					$('#addemployeeModal input[name="id"]').val(employInfo.id);
					$('#addemployeeModal input[name="name"]').val(employInfo.name);
					$("#addemployeeModal select[name='sex'] option").each(function(){
						    $(this).attr("selected",false);
					})
					$("#addemployeeModal select[name='sex'] option[value='"+employInfo.sex+"']").attr("selected","selected");
					//婚姻状况
					$("#addemployeeModal select[name='isMarry'] option[value='"+employInfo.isMarry+"']").attr("selected","selected");
					//学历
					$("#addemployeeModal select[name='education'] option[value='"+employInfo.education+"']").attr("selected","selected");
					
					//对元素值xxx进行选定
					$("#addemployeeModal select[name='roleId'] option").each(function(){
						    $(this).attr("selected",false);
					})
					$("#addemployeeModal select[name='roleId'] option[value='"+employInfo.roleId+"']").attr("selected","selected");
					
					$("#addemployeeModal select[name='branchGroupId'] option").each(function(){
						    $(this).attr("selected",false);
					})
					$("#addemployeeModal select[name='branchGroupId'] option[value='"+employInfo.groupId+"']").attr("selected","selected");
        			$('#addemployeeModal input[name="phone"]').val(employInfo.phone);
        			
        			$('#addemployeeModal input[name="phone"]').attr('readonly','value');
        			$('#addemployeeModal input[name="phone"]').addClass('inputbg');
        			
        			$('#addemployeeModal input[name="email"]').val(employInfo.email);
        			$('#addemployeeModal input[name="age"]').val(employInfo.age);
        			$('#addemployeeModal input[name="remark"]').val(employInfo.remark);
        			//省市区
				    $("#cityAddress").citySelect({
						    	prov:employInfo.province, 
						    	city:employInfo.city,
								dist:employInfo.district,
								nodata:"none"
					}); 
        			$('#addemployeeModal input[name="address"]').val(employInfo.address);
        			//身份证号
        			$('#addemployeeModal input[name="idcard"]').val(employInfo.idcard);
        			$('#addemployeeModal input[name="idcard"]').attr('readonly','value');
        			$('#addemployeeModal input[name="idcard"]').addClass('inputbg');
        			$('#addemployeeModal input[name="startWorkDate"]').val(employInfo.startWorkDate);
        			$("#addemployeeModal").modal();
        		}else{
        			$("#msgContent").html("");
        			$("#msgContent").html(data.msg);
        			$("#deletnullModal").modal();
        		}
        	}  
    	})
		
	})
</script>
 
<!-- 校验手机号	-->	
<script  type="text/javascript">
function checkUserPhone(param){
	var phone=param.value;
	var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;  
	if(phone !=="" && phone !== undefined && phone !== null ){
		if(myreg.test(phone)){
			$("#phone_no").css("visibility","hidden");
			$("#phone_yes").css("visibility","visible");
			$.post("${pageContext.request.contextPath}/humanOrganization/check.do",{"param":phone,"type":1},function(data){
				if (data.data) {
            		//检查手机号是否存在
            		$("#phone_no").css("visibility","hidden");
        			$("#phone_yes").css("visibility","visible");
        		} else {
        			$("#phone_yes").css("visibility","hidden");
					$("#phone_no").css("visibility","visible");
        		}
			})
		}else{
			$("#phone_yes").css("visibility","hidden");
			$("#phone_no").css("visibility","visible");
		}
	}else{
		$("#phone_yes").css("visibility","hidden");
		$("#phone_no").css("visibility","visible");
	}
}

function successSure(){
		$("#successModal").modal();
		window.location.reload();
	}
</script>

<!--查看在职员工和 离职员工-->
<script>
	function listEmploy(status){
		var page = 'employPage';
		if(status==0){
			$("#searchForm input[name=workStatus]").val(0);;//在职
			page = 'employPage';
		}else{
			$("#searchForm input[name=workStatus]").val(1);//离职
			page = 'employLeavePage';
		}
		$.ajax({
	    	type:'post',      
	   		url:'${pageContext.request.contextPath}/humanOrganization/listEmployByPage.do',  
	        data:{page:1,search:JSON.stringify($('#searchForm').serializeJSON())},
	        cache:false,
	        dataType:'json',
	    	success:function(data){
	    		if(data.status==200){
	    			htmlTable(data.data.rows);
	        		$("#"+page+"").paging({
						pageNo:1, 
						totalPage: data.data.totalPage,
						totalSize: data.data.limit,
						callback: function(num) {
							searchByPage(num);
						}
					})	
	     		}else{
	      			$("#msgContent").html("");
	    			$("#msgContent").html(data.msg);
	    			$("#deletnullModal").modal();
	      		}
		 	}
		})
	}
</script>

<!--分页-->
<script  type="text/javascript">
	$("#employPage").paging({
		pageNo:1, 
		totalPage:${employeeResult.totalPage},
		totalSize:${employeeResult.limit},
		callback: function(num) {
			searchByPage(num);
		}
	})
</script>

<!--执行分页查询的方法 -->		
<script  type="text/javascript">
	function searchByPage(num){
		$.ajax({  
        	type:'post',      
       		url:'${pageContext.request.contextPath}/humanOrganization/listEmployByPage.do',  
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
</script>

<!--将查询到的结果写入表格-->
<script  type="text/javascript">
	function htmlTable(results){
		var employeeList = results;
		var tbody ="employTbody";
		if($('#searchForm input[name=workStatus]').val()==0){
			tbody ="employTbody";
		}else{
			tbody ="employLeaveTbody";
		}
		$("#"+tbody+"").html("");
		$.each(employeeList,function(index,employee){
			$("#"+tbody+"").append("<tr>"
				+"<td style='text-align: center;'><label class='demo--label'>"
				 +" <input class='demo--checkbox' name='cusCheckBox' value='"+employee.id+"' type='checkbox'   >"
                  +"<span class='demo--checkboxInput'></span>"
                 +"</label></td>"
			 +"<td id=''>"+employee.name+"</td>"
			  +"<td name='sex"+index+"'></td>"
			  +"<td id=''>"+employee.age+"</td>"
			   +"<td name='isMarry"+index+"'></td>"
			    +"<td name='education"+index+"'></td>"
			 +"<td id=''>"+employee.roleName+"</td>"
			 +"<td id=''>"+employee.phone+"</td>"
			  +"<td id=''>"+employee.groupName+"</td>"
			 +"<td id=''>"+employee.email+"</td>"
			  +"<td id=''>"+employee.startWorkDate+"</td>"
			  +"<td name='leaveOfficeDate'>"+employee.leaveOfficeDate+"</td>"
			  +"<td id=''>"+employee.idcard+"</td>"
			 +"<td id=''>"+employee.areaCode+employee.address+"</td>"
			 +"</tr>")
			if(employee.sex==1){
				$("#"+tbody+" td[name='sex"+index+"']").html("男"); 
			}else{
				$("#"+tbody+" td[name='sex"+index+"']").html("女"); 
			}
			if(employee.sex==1){
				$("#"+tbody+" td[name='sex"+index+"']").html("男"); 
			}else{
				$("#"+tbody+" td[name='sex"+index+"']").html("女"); 
			}
			if(employee.isMarry==0){
				$("#"+tbody+" td[name='isMarry"+index+"']").html("未婚"); 
			}else{
				$("#"+tbody+" td[name='isMarry"+index+"']").html("已婚"); 
			}
			if(employee.education==1){
				$("#"+tbody+" td[name='education"+index+"']").html("初中"); 
			}else if(employee.education==2){
				$("#"+tbody+" td[name='education"+index+"']").html("高中"); 
			}else if(employee.education==3){
				$("#"+tbody+" td[name='education"+index+"']").html("大专"); 
			}else if(employee.education==4){
				$("#"+tbody+" td[name='education"+index+"']").html("本科"); 
			}else if(employee.education==5){
				$("#"+tbody+" td[name='education"+index+"']").html("研究生"); 
			}else{
				$("#"+tbody+" td[name='education"+index+"']").html("小学"); 
			}
		})
		if($('#searchForm input[name=workStatus]').val()==0){
			$("#"+tbody+" td[name='leaveOfficeDate']").remove();
		}
	}
</script>

</html>