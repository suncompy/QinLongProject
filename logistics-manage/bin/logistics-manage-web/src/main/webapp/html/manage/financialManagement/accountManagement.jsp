<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>账户管理</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/manage/financialManagement/accountManagement.css"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/public.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/config/url.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script src="${pageContext.request.contextPath}/js/manage/financialManagement/accountManagement.js"></script>
		<script src="${pageContext.request.contextPath}/public/jquery.validate.js"></script>
		<!--分页-->
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js" ></script>
		<link href="${pageContext.request.contextPath}/css/page/page.css" rel="stylesheet"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.serializejson.js"></script>
		
		 <!--搜索-->
		<script>

		$(function() {
			$("body").on("click", "#accountBody tr td:not(:nth-child(1))", function() {
				$(this).parent().toggleClass("bgclass").siblings("#accountBody tr").removeClass("bgclass");
				var accountId = $(this).parent().attr("id");
				$.ajax({
					type: 'POST',
					url: "${pageContext.request.contextPath}/account/lookAccountDetail.do",
					data: {
						"id": accountId
					},
					dataType: "json",
					cache: false,
					success: function(data) {
						var kind = "";
						var type = "";
						if(data.data.account.accountKind == 0){
							kind = "客户";
						}else if(data.data.account.accountKind == 1){
							kind = "中心站点";
						}else{
							kind = "公司";
						}
						if(data.data.account.accountType == 0){
							type = "日常收支";
						}else if(data.data.account.accountType == 1){
							type = "预付款账户";
						}else if(data.data.account.accountType == 2){
							type = "贷款账户";
						}else if(data.data.account.accountType == 3){
							type = "中转账户";
						}else if(data.data.account.accountType == 4){
							type = "财运通账户";
						}else if(data.data.account.accountType == 5){
							type = "现金账户";
						}else{
							type = "银行账户";
						}
						$("#accountForm")[0].reset();
						$("#accountForm [name=accountKind]").val(kind);
						$("#accountForm [name=accountType]").val(type);
						$("#accountForm [name=chooseAccountId]").val(data.data.account.relationName);
						$("#accountForm [name=name]").val(data.data.account.name);
						$("#accountForm [name=accountName]").val(data.data.account.accountName);
						$("#accountForm [name=accountNum]").val(data.data.account.accountNum);
						$("#accountForm [name=openBank]").val(data.data.account.openBank);
						$("#accountForm [name=bankNum]").val(data.data.account.bankNum);
						$("#accountForm [name=taxIdentificationNumber]").val(data.data.account.taxIdentificationNumber);
						$("#accountForm [name=vigilanceAmount]").val(data.data.account.vigilanceAmount);
						$("#accountForm [name=nonUseAmount]").val(data.data.account.nonUseAmount);
						$("#accountForm [name=startAccountBalance]").val(data.data.account.startAccountBalance);
						$("#accountForm [name=accountBalance]").val(data.data.account.accountBalance);
						if(data.data.account.accountType != 1){
							$("#otherInfo").hide();
							$("#otherDetailInfo").hide();
						}else{
							$("#otherInfo").show();
							$("#otherDetailInfo").show();
							$("#otherDetailInfo").empty();
							$.each(data.data.list, function(index, it) {
								$("#otherDetailInfo").append("<div class='form-group'>"
										+"<label>&emsp;项目编号：</label>"
										+"<input type='text'  name='projectCode' value="+it.projectCode+" maxlength='10' disabled='disabled'/>"
										+"<span></span>"
										+"</div>"
										+"<div class='form-group'>"
										+"<label>&emsp;存入金额：</label>"
										+"<input type='text'  name='depositAmount' value="+it.depositAmount+" maxlength='10' disabled='disabled'/>"
										+"<span>元</span>"
										+"</div>"
										+"<div class='form-group'>"
										+"<label>&emsp;抵用金额：</label>"
										+"<input type='text'  name='purposeAmount' value="+it.purposeAmount+" maxlength='10' disabled='disabled'/>"
										+"<span>元</span>"
										+"</div>"
										+"<div class='form-group'>"
										+"<label>&emsp;退款金额：</label>"
										+"<input type='text'  name='cashAmount' value="+it.cashAmount+" maxlength='10' disabled='disabled'/>"
										+"<span>元</span>"
										+"</div>"
										+"<div class='form-group'>"
										+"<label>&emsp;&emsp;&emsp;余额：</label>"
										+"<input type='text'  name='blance' value="+it.blance+" maxlength='10' disabled='disabled'/>"
										+"<span>元</span>"
										+"</div>"
										+"<div class='form-group'>"
										+"<label></label>"
										+"&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"
										+"<span></span>"
										+"</div>");
							});
						}
						$("#accountDteailModal").modal();
						$('#showMask', window.parent.document).show();
					}
				})
			})
		});
			function search(param){
				$.ajax({
		        	type:'post',      
		       		url:'${pageContext.request.contextPath}/account/listAccountByPage.do', 
			       	data:{page:1,search:JSON.stringify($('#searchForm').serializeJSON())},
			        cache:false,
			        dataType:'json',
		        	success:function(data){
		        		if(data.status==200){
		        			htmlTable(data.data.rows);
		        			$("#page").paging({
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
			
		<script type="text/javascript">
			/* $(function(){
				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/account/queryBranchGroup.do",
					dataType:'json',
					success : function(data) {
						if(data.status==200){
							$("#branchGroupId").empty();
							$("#searchForm select[name=branchGroupId]").html("<option></option>");
							$.each(data.data, function(index, its) {
								$("#branchGroupId").append(
										"<option value="+its.id+">" +its.name+ "</option>");
								$("#searchForm select[name=branchGroupId]").append("<option value="+its.id+">" + its.name
									+ "</option>");				
							});
						}
					},
				}); 
			});  */
			
			function addOrUpdAcount(){
				var hideId = $("#id").val();
				if(hideId == null || hideId == "") {
					$.ajax({
						cache : false,
						type : "POST",
						url : "${pageContext.request.contextPath}/account/addAccountManage.do",
						data : $('#userForm').serialize(),
						async : false,
						dataType:'json',
						success : function(data) {
							if (data.status != 200) {
								//$("#addAccountModal").modal("hide");
								$("#errorPointMsg").html(data.msg);
								$("#errorPromptIdModal").modal();
							} else {
								$("#addAccountModal").modal("hide");
								$("#pointMsg").html("新增成功");
								$("#promptIdModal").modal();
							}
						}
					});
				}else{
					$.ajax({
						cache : false,
						type : "POST",
						url : "${pageContext.request.contextPath}/account/updAccountManage.do",
						data : $('#userForm').serialize(),
						async : false,
						dataType:'json',
						success : function(data) {
							if (data.status != 200) {
								//$("#addAccountModal").modal("hide");
								$("#errorPointMsg").html(data.msg);
								$("#errorPromptIdModal").modal();
							} else {
								$("#addAccountModal").modal("hide");
								$("#pointMsg").html("修改成功");
								$("#promptIdModal").modal();
							}
						}
					});
				}
			}
			
			function sendId(param){
				if($(param).prop("checked")){
					$("#operateId").val(param.value);
				}else{
					$("#operateId").val("");
				}
			}
			
			function deleteAccount(){
				//var id = $("#operateId").val();
				var id = $("input:checkbox[type='checkbox']:checked").val();
				if(id==null || id==""){
					return;
				}
				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/account/deleteAccount.do",
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
			function closeAndfresh(){
				$("#promptIdModal").modal("hide");
				window.location.reload();
			}
			
			function closeNofresh(){
				$("#errorPromptIdModal").modal("hide");
			}
			
			//账户种类选择 0:客户  1:站点 2:公司
			function chooseKind(){
				 var type = $("#accountKind option:selected").val();
				 if(type == "" || type == null || type == undefined){
					 $("#chooseAccountId").empty();
					 $("#accountType").empty();
				 }else{
					 $("#accountType").empty();
					 if(type == 0 || type == 1){
						 $("#accountType").append("<option value=''></option><option value='0'>日常收支</option><option value='1'>预付款账户</option>"
								 +"<option value='2'>贷款账户</option><option value='3'>中转账户</option><option value='4'>财运通账户</option>"
								 +"<option value='5'>现金账户</option><option value='6'>银行账户</option>");
					 }else{
						 $("#accountType").append("<option value=''></option><option value='0'>日常收支</option>"
								 +"<option value='2'>贷款账户</option><option value='3'>中转账户</option><option value='4'>财运通账户</option>"
								 +"<option value='5'>现金账户</option><option value='6'>银行账户</option>");
					 }
					 $.ajax({
						type : 'POST',
						url : "${pageContext.request.contextPath}/account/getAccountNameByType.do",
						data : {"type":type},
						dataType : "json",
						async:false, 
						success : function(data) {
							if(data.status==200){
								$("#chooseAccountId").empty();
								if(type == 0){
									$.each(data.data.data, function(index, its) {
										$("#chooseAccountId").append("<option value="+its.id+">" + its.companyName+ "</option>");
			 						});
								}else if(type == 1){
									$.each(data.data.data, function(index, its) {
										$("#chooseAccountId").append("<option value="+its.id+">" + its.stationName+ "</option>");
			 						});
								}else{
									$("#chooseAccountId").append("<option value="+data.data.data.id+">" + data.data.data.name+ "</option>");
								}
							}else{
								$("#deletReasonModal").modal("hide");
								$("#pointMsg").html("获取失败");
								$("#promptIdModal").modal();
							}
						},
					}); 
				 }
				 getTaxById();
			}
			
			function getTaxById(){
				var type = $("#accountKind option:selected").val();
				var cId = $("#chooseAccountId option:selected").val();
				 if(cId == "" || cId == null || cId == undefined){
					 $("#taxIdentificationNumber").val("");
				 }else{
					 $.ajax({
						type : 'POST',
						url : "${pageContext.request.contextPath}/account/getTaxByTypeAndId.do",
						data : {"type":type,"id":cId},
						dataType : "json",
						async:false,
						success : function(data) {
							if(data.status==200){
								$("#taxIdentificationNumber").val("");
								if(type == 0){
									$("#taxIdentificationNumber").val(data.data.data.dutyParagraph);
								}else if(type == 1){
									$("#taxIdentificationNumber").val(data.data.data.dutyParagraph);
								}else{
									$("#taxIdentificationNumber").val(data.data.data.dutyParagraph);
								}
							}else{
								$("#deletReasonModal").modal("hide");
								$("#pointMsg").html("获取失败");
								$("#promptIdModal").modal();
							}
						},
					}); 
				 }
			}
			
			function updAccount(){
				if($("input:checkbox[type='checkbox']:checked").length > 1) {
					$("#deletRemindModal").modal();
					return;
				} else if($("input:checkbox[type='checkbox']:checked").length == 0) {
					$("#deletnullModal").modal();
					return;
				} else{
					$("#userForm").validate().resetForm();
				    $("#userForm")[0].reset();
					$("#addAccountModal").modal();
					$("#myModalLabel").html("修改账户");
					
					var id = $("input:checkbox[type='checkbox']:checked").val();
					$.ajax({
						type : 'POST',
						url : "${pageContext.request.contextPath}/account/getAccountDetailById.do",
						data : {"id":id},
						dataType : "json",
						async:false, 
						success : function(data) {
							if(data.status==200){
								$("#userForm input[name='name']").attr("readOnly",true);
								$("#userForm input[name='startAccountBalance']").attr("readOnly",true);
								$("#userForm input[name='id']").val(data.data.id);
								$("#userForm input[name='name']").val(data.data.name);
								$("#userForm input[name='accountName']").val(data.data.accountName);
								$("#userForm input[name='accountNum']").val(data.data.accountNum);
								$("#userForm input[name='openBank']").val(data.data.openBank);
								$("#userForm input[name='bankNum']").val(data.data.bankNum);
								$("#userForm input[name='vigilanceAmount']").val(data.data.vigilanceAmount);
								$("#userForm input[name='nonUseAmount']").val(data.data.nonUseAmount);
								$("#userForm input[name='startAccountBalance']").val(data.data.startAccountBalance);
								$("#userForm select[name='accountKind']").val(data.data.accountKind);
								chooseKind();
								$("#userForm select[name='accountType']").val(data.data.accountType);
								$("#userForm select[name='chooseAccountId']").val(data.data.chooseAccountId);
								$("#userForm input[name='taxIdentificationNumber']").val(data.data.taxIdentificationNumber);
							}else{
								$("#deletReasonModal").modal("hide");
								$("#pointMsg").html("获取详情失败");
								$("#promptIdModal").modal();
							}
						},
					}); 
				}
				
			}
			
		</script>
	</head>
	
	<body>
		<!-- 账户管理 -->
		<div class="form project_form container_top">
			<form class="form-inline maxWidth" id="searchForm">
				<div id="wrap">
					<div class="form-group">
						<label>账户名称：</label>
						<input name="name" type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>账户类型：</label>
						<select name="accountType">
							<option value=""></option>
							<option value="0">日常收支</option>
							<option value="1">预付款账户</option>
							<option value="2">贷款账户</option>
							<option value="3">中转账户</option>
							<option value="4">财运通账户</option>
							<option value="5">现金账户</option>
							<option value="6">银行账户</option>
						</select>
					</div>
					<div class="form-group">
						<label>开户行：</label>
						<input name="openBank" type="text" maxlength="30"/>
					</div>
					<!-- <div class="form-group">
						<label>所属网点：</label>
						<select name="branchGroupId"></select>
					</div> -->
				</div>
				<div class="foot">
					<a class="btn btn-success" href="javascript:;"><em class="search"></em> 
						<span onclick="search(this)">搜索</span>
					</a>
				</div>
			</form>
		</div>
		<!--账户管理表格-->
		<div class="container_top tableDiv">
			<div class="tabbable">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#panel1" data-toggle="tab">账户管理</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="panel1">
						<div class="domain">
							<div class="tableBg">
							<c:forEach items='${permissions }' var='permission' >
									<c:if test="${permission.code=='addAccount'}">
								<a href="javascript:void(0)" class="exportBtn add" id="addAccount"><span>新增账户</span></a>
									</c:if>
									<c:if test="${permission.code=='updateAccount'}">
								<a href="javascript:void(0)" class="exportBtn revise"  onclick="updAccount()"><span>修改</span></a>
									</c:if>
									<c:if test="${permission.code=='delAccount'}">
								<a href="javascript:void(0)" class="exportBtn del" id="delBtn"><span>删除</span></a>
									</c:if>
							</c:forEach>
							
							</div>
							<div class="table-responsive">
							<input id="operateId" name="operateId" type="text" style="display:none;" />
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th> 
												<label class="demo--label">
											     <input class="demo--checkbox" type="checkbox"  disabled="disabled"   >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label>
											</th>
												<th>账户名称</th>
												<th>账户种类</th>
												<th>账户类型</th>
												<th>关联</th>
												<th>账户余额</th>
												<th>期初余额</th>
												<th>警戒金额</th>
												<th>停用金额</th>
												<th>户名</th>
												<th>账号</th>
												<th>开户行</th>
												<th>行号</th>
												<th>纳税识别号</th>
												<th>开户时间</th>
										</tr>
									</thead>
									<tbody id="accountBody" class="needLookDetail">
									<c:forEach items="${accountList.rows}" var="account">
										<tr id="${account.id}">
											<td><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox"  onclick="sendId(this)" value="${account.id}" >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label></td>
											<td>${account.name}</td>
											<td><c:if test="${account.accountKind eq 0}">客户</c:if>
					        					<c:if test="${account.accountKind eq 1}">中心站点</c:if>
					        					<c:if test="${account.accountKind eq 2}">公司</c:if>
											</td>
											<td><c:if test="${account.accountType eq 0}">日常收支</c:if>
					        					<c:if test="${account.accountType eq 1}">预付款账户</c:if>
					        					<c:if test="${account.accountType eq 2}">贷款账户</c:if>
					        					<c:if test="${account.accountType eq 3}">中转账户</c:if>
					        					<c:if test="${account.accountType eq 4}">财运通账户</c:if>
					        					<c:if test="${account.accountType eq 5}">现金账户</c:if>
					        					<c:if test="${account.accountType eq 6}">银行账户</c:if>
											</td>
											<td>${account.relationName}</td>
											<td>${account.accountBalance}元</td>
											<td>${account.startAccountBalance}元</td>
											<td>${account.vigilanceAmount}元</td>
											<td>${account.nonUseAmount}元</td>
											<td>${account.accountName}</td>
											<td>${account.accountNum}</td>
											<td>${account.openBank}</td>
											<td>${account.bankNum}</td>
											<td>${account.taxIdentificationNumber}</td>
											<td><fmt:formatDate value="${account.accountOpeningTime}" pattern="yyyy-MM-dd" /></td>
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
		<!-- 新增账户 -->
		<div class="modal fade addAccountModal" id="addAccountModal" tabindex="-1" role="dialog" data-backdrop="static">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">新增账户</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<h5>账户信息<i class='requireds'>*</i></h5>
						<form id="userForm">
							<input id="id" name="id" type="text" style="display:none;" />
							<div class="form-inline">
								<!-- <div class="form-group">
									<label>开户时间：</label>
									<input type="text" id="accountOpeningTime" name="time" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  />
									<i class="fa  fa-calendar-check-o " id="calendar2"></i>
								</div> -->
								<!-- <div class="form-group">
									<label>账户类型：</label>
									<select id="accountType" name="accountType">
										<option value ="1">金卡</option>
										<option value ="2">银卡</option>
										<option value="3">VIP</option>
									</select>
								</div> -->
								<div class="form-group">
									<label>账户种类：</label>
									<select id="accountKind" name="accountKind" onchange="chooseKind()">
										<option value=""></option>
										<option value="0">客户</option>
										<option value="1">中心站点</option>
										<option value="2">公司</option>
									</select>
								</div>
								<div class="form-group">
									<label>账户类型：</label>
									<select id="accountType" name="accountType">
										
									</select>
								</div>
								<div class="form-group">
									<label>&emsp;选择单位：</label>
									<select id="chooseAccountId" name="chooseAccountId" onchange="getTaxById()"></select>
								</div>
							</div>
							<div class="form-inline">
								<div class="form-group">
									<label>账户名称：</label>
									<input type="text" id="name" name="name" maxlength="30" />
								</div>
								<div class="form-group">
									<label>&emsp;&emsp;户名：</label>
									<input type="text" id="accountName" name="accountName" maxlength="30" />
								</div>
								<div class="form-group">
									<label>&emsp;&emsp;&emsp;账号：</label>
									<input type="text" id="accountNum" name="accountNum" maxlength="30" />
								</div>
								<div class="form-group">
									<label>&emsp;开户行：</label>
									<input type="text"  id="openBank" name="openBank" maxlength="30"/>
								</div>
								<div class="form-group">
									<label>&emsp;&emsp;行号：</label>
									<input type="text"  id="bankNum" name="bankNum" maxlength="30"/>
								</div>
								<div class="form-group">
									<label>纳税识别号：</label>
									<input type="text" id="taxIdentificationNumber" name="taxIdentificationNumber" maxlength="30" />
								</div>
							</div>
							<hr />
							<h5>金额信息<i class='requireds'>*</i></h5>
							<div class="form-inline">
								<div class="form-group">
									<label>警戒金额：</label>
									<input type="text" id="vigilanceAmount" name="vigilanceAmount" maxlength="10" onkeyup="value=value.replace(/[^\d.]/g,'')"
									/>
									<span>元</span>
								</div>
								<div class="form-group">
									<label>停用金额：</label>
									<input type="text" id="nonUseAmount" name="nonUseAmount" maxlength="10" onkeyup="value=value.replace(/[^\d.]/g,'')"/>
									<span>元</span>
								</div>
								<div class="form-group">
									<label>&emsp;期初余额：</label>
									<input type="text" id="startAccountBalance" name="startAccountBalance" maxlength="10" onkeyup="value=value.replace(/[^\d.]/g,'')"/>
									<span>元</span>
								</div>
							</div>
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
									<button type="button" class="btn sureBtn" onclick="addOrUpdAcount()">确定</button>
									<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
								</div>
							</div>
					    </form>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 账户详情 -->
		<div class="modal fade addAccountModal" id="accountDteailModal" tabindex="-1" role="dialog" data-backdrop="static">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">账户详情</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<h5>账户信息<i class='requireds'>*</i></h5>
						<form id="accountForm">
							<div class="form-inline">
								<div class="form-group">
									<label>账户种类：</label>
									<input type="text"  name="accountKind" maxlength="30" disabled="disabled"/>
								</div>
								<div class="form-group">
									<label>账户类型：</label>
									<input type="text"  name="accountType" maxlength="30" disabled="disabled"/>
								</div>
								<div class="form-group">
									<label>&emsp;选择单位：</label>
									<input type="text"  name="chooseAccountId" maxlength="30" disabled="disabled"/>
								</div>
							</div>
							<div class="form-inline">
								<div class="form-group">
									<label>账户名称：</label>
									<input type="text"  name="name" maxlength="30" disabled="disabled"/>
								</div>
								<div class="form-group">
									<label>&emsp;&emsp;户名：</label>
									<input type="text"  name="accountName" maxlength="30" disabled="disabled"/>
								</div>
								<div class="form-group">
									<label>&emsp;&emsp;&emsp;账号：</label>
									<input type="text"  name="accountNum" maxlength="30" disabled="disabled"/>
								</div>
								<div class="form-group">
									<label>&emsp;开户行：</label>
									<input type="text"   name="openBank" maxlength="30" disabled="disabled"/>
								</div>
								<div class="form-group">
									<label>&emsp;&emsp;行号：</label>
									<input type="text"  name="bankNum" maxlength="30" disabled="disabled"/>
								</div>
								<div class="form-group">
									<label>纳税识别号：</label>
									<input type="text"  name="taxIdentificationNumber" maxlength="30" disabled="disabled"/>
								</div>
							</div>
							<hr />
							<h5>金额信息<i class='requireds'>*</i></h5>
							<div class="form-inline">
								<div class="form-group">
									<label>警戒金额：</label>
									<input type="text"  name="vigilanceAmount" maxlength="10" disabled="disabled"/> 
									<span>元</span>
								</div>
								<div class="form-group">
									<label>停用金额：</label>
									<input type="text"  name="nonUseAmount" maxlength="10" disabled="disabled"/>
									<span>元</span>
								</div>
								<div class="form-group">
									<label>&emsp;期初余额：</label>
									<input type="text"  name="startAccountBalance" maxlength="10" disabled="disabled"/>
									<span>元</span>
								</div>
								<div class="form-group">
									<label>&emsp;总余额：</label>
									<input type="text"  name="accountBalance" maxlength="10" disabled="disabled"/>
									<span>元</span>
								</div>
							</div>
							<hr />
							<h5 id="otherInfo">其他信息<i class='requireds'>*</i></h5>
							<div class="form-inline" id="otherDetailInfo" style="height: 100px" >
								
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
							<span>请选择一条要操作的数据</span>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="closeAndfresh()">确定</button>
								<!--<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>-->
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
							<span>抱歉，不可批量操作</span>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="closeAndfresh()">确定</button>
								<!--<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>-->
							</div>
						</div>
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
							<span>你是否选择删除此账户，删除后不可还原</span>
						</div>
						<div class="delete_reason">
							<h4>删除原因</h4>
							<textarea class="form-control" rows="3"></textarea>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="deleteAccount()">确定</button>
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
<script >
	$("#page").paging({
		pageNo:1, 
		totalPage:${accountList.totalPage},
		totalSize:${accountList.limit},
		callback: function(num) {
			searchByPage(num);
		}
	})
</script>

<!--执行分页查询的方法 -->		
<script>
	function searchByPage(num){
		$.ajax({  
        	type:'post',      
       		url:'${pageContext.request.contextPath}/account/listAccountByPage.do', 
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

<!--将分页返回结果写入表格-->
<script>
	function htmlTable(results){
		var accountList = results;
		$('#accountBody').html("");
		$.each(accountList,function(index,account){
			$('#accountBody').append("<tr id='"+account.id+"'>"
				+"<td style='text-align: center;'><label class='demo--label'>"
				 +" <input class='demo--checkbox'  value='"+account.id+"' type='checkbox'   >"
                  +"<span class='demo--checkboxInput'></span>"
                 +"</label></td>"
				 +"<td id=''>"+account.name+"</td>"
				 +"<td name='reasons"+index+"'></td>"
			 +"<td name='resaon"+index+"'></td>"
			 +"<td id=''>"+account.relationName+"</td>"
			 +"<td id=''>"+account.accountBalance+"元</td>"
			 +"<td id=''>"+account.startAccountBalance+"元</td>"
			 +"<td id=''>"+account.vigilanceAmount+"元</td>"
			 +"<td id=''>"+account.nonUseAmount+"元</td>"
			 +"<td id=''>"+account.accountName+"</td>"
			 +"<td id=''>"+account.accountNum+"</td>"
			 +"<td id=''>"+account.openBank+"</td>"
			 +"<td id=''>"+account.bankNum+"</td>"
			 +"<td id=''>"+account.taxIdentificationNumber+"</td>"
			 +"<td id=''>"+account.accountOpeningTime+"</td>"
			 +"</tr>");
			$("td[name='reasons"+index+"']").html("");
			if(account.accountKind==0){
				$("td[name='reasons"+index+"']").append("客户"); 
			}
			else if(account.accountKind==1){
				$("td[name='reasons"+index+"']").append("中心站点"); 
			}
			else if(account.accountKind==2){
				$("td[name='reasons"+index+"']").append("公司"); 
			}
			
			$("td[name='resaon"+index+"']").html("");
			if(account.accountType==0){
				$("td[name='resaon"+index+"']").append("日常收支"); 
			}
			else if(account.accountType==1){
				$("td[name='resaon"+index+"']").append("预付款账户"); 
			}
			else if(account.accountType==2){
				$("td[name='resaon"+index+"']").append("贷款账户"); 
			}
			else if(account.accountType==3){
				$("td[name='resaon"+index+"']").append("中转账户"); 
			}
			else if(account.accountType==4){
				$("td[name='resaon"+index+"']").append("财运通账户"); 
			}
			else if(account.accountType==5){
				$("td[name='resaon"+index+"']").append("现金账户"); 
			}
			else if(account.accountType==6){
				$("td[name='resaon"+index+"']").append("银行账户"); 
			}
		});	
	}
</script>

</html>