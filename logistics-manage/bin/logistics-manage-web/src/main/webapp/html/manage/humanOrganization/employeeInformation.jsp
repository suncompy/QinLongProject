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
		<link rel="stylesheet" href="${pageContext.request.contextPath}/exhibition/css/sweetalert2.min.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/config/url.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.validate.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/My97DatePicker/WdatePicker.js"></script>
			<!--shilvfei加的-->
		<script type="text/javascript"  src="${pageContext.request.contextPath}/public/js/jquery.cityselect.js"></script>	
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/js/city.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js" ></script>
		<link href="${pageContext.request.contextPath}/css/page/page.css" rel="stylesheet"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.serializejson.js"></script>
		<script src="${pageContext.request.contextPath}/exhibition/js/base.js"></script>
    	<script src="${pageContext.request.contextPath}/exhibition/js/sweetalert2.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/humanOrganization/employeeInformation.js"></script>
	</head>
	
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
						<input name="beginDate" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy.MM.dd HH:mm'})" />
						<i class="fa fa-calendar-check-o " id="calendar2"></i>
					</div>
					<div class="form-group dataTimes secTime">
						<label>至</label>
						<input name="endDate" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy.MM.dd HH:mm'})" />
						<i class="fa  fa-calendar-check-o " id="calendar2"></i>
					</div>
					<input name="workStatus" type="hidden" value="0" />
				</div>
				<div class="foot">
					<a class="btn btn-success" href="javascript:;"><em class="search"></em> 
						<span onclick="search(this)" id='searchSpan'>搜索</span>
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
						<a href="#panel2" data-toggle="tab" onclick="listEmploy(1)">离职员工信息</a>
					</li>
				</ul>
				<div class="tab-content">
					<!--员工信息表格开始-->
					
					<!-- 在职员工 -->
					<div class="tab-pane active" id="panel1">
						<div class="domain">
							<div class="tableBg">
							<c:forEach items='${permissions }' var='permission' >
								<c:if test="${permission.code=='addEmployee'}">
									<a href="javascript:void(0)" class="exportBtn addNew"  id="addemployee"><span>添加成员</span></a>
								</c:if>
								<c:if test="${permission.code=='updateEmployee'}">
									<a href="javascript:void(0)" class="exportBtn revise" onclick="changeEmploy()"><span>修改</span></a>
								</c:if>
								<c:if test="${permission.code=='delEmployee'}">
									<a href="javascript:void(0)" class="exportBtn del" id="delBtn"><span>离职</span></a>
								</c:if>
							</c:forEach>
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
											<th>员工状态</th>
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
									</tbody>
								</table>
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-md-12 column paging page_div" id="employPage">
							</div>
						</div>
					</div>
					
					<!-- 离职员工 -->
					<div class="tab-pane" id="panel2">
						<div class="domain">
							<div class="tableBg">
							<c:forEach items='${permissions }' var='permission' >
								<c:if test="${permission.code=='updateEmployee'}">
									<a href="javascript:void(0)" class="exportBtn revise" onclick="changeEmploy()"><span>修改</span></a>
								</c:if>
							</c:forEach>
								
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
											<th>员工状态</th>
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
				
		<!-- 添加成员模态框 -->
		<div class="modal fade addemployeeModal" id="addemployeeModal" tabindex="-1" role="dialog" data-backdrop="static">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">添加成员</h4>
					<span data-dismiss="modal" aria-label="Close"></span>
				</div>
				<div class="modal-body">
					<h5>成员信息<i class='requireds'>*</i></h5>
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
								<option></option>
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
								<option></option>
								<option value="1">已婚</option>
								<option value="0">未婚</option>
							</select>
						</div>
						<div class="form-group">
							<label>&emsp;&emsp;学历：</label>
							<select name="education">
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
							<label>角色分配：</label>
							<select name="roleId" id="roleId">
								<option></option>
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
							<label>员工状态：</label>
							<select name="workStatus">
								<option></option>
								<option value="0">在职</option>
								<option value="1">离职</option>
								<option value="2">休假</option>
								<option value="3">停职</option>
							</select>
						</div>
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
</body>

</html>