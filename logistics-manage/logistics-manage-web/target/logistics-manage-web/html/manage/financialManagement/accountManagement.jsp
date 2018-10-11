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
			$(function(){
				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/account/queryBranchGroup.do",
					success : function(data) {
						if(data.status==200){
							$("#branchGroupId").empty();
							$("#searchForm select[name=branchGroupId]").html("<option></option>");
							$.each(data.data, function(index, its) {
								$("#branchGroupId").append(
										"<option value="+its.id+">" +its.name+ "</option>");
								/*条件查询*/				
								$("#searchForm select[name=branchGroupId]").append("<option value="+its.id+">" + its.name
									+ "</option>");				
							});
						}
					},
				}); 
			});
			
			function addAcount(){
				$.ajax({
					cache : false,
					type : "POST",
					url : "${pageContext.request.contextPath}/account/addAccountManage.do",
					data : $('#userForm').serialize(),
					async : false,
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
							<option></option>
							<option value ="1">金卡</option>
							<option value ="2">银卡</option>
							<option value="3">VIP</option>
						</select>
					</div>
					<div class="form-group">
						<label>开户行：</label>
						<input name="openBank" type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>所属网点：</label>
						<select name="branchGroupId"></select>
					</div>
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
								<a href="javascript:void(0)" class="exportBtn add" id="addAccount"><span>新增账户</span></a>
								<a href="javascript:void(0)" class="exportBtn del" id="delBtn"><span>删除</span></a>
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
											<th>账户类型</th>
											<th>开户日期</th>
											<th>账户余额</th>
											<th>警戒金额</th>
											<th>停用金额</th>
											<th>账户名</th>
											<th>账号</th>
											<th>开户行</th>
											<th>行号</th>
											<th>所属网点</th>
											<th>所在区域</th>
										</tr>
									</thead>
									<tbody id="accountBody">
									<c:forEach items="${accountList.rows}" var="account">
										<tr id="${account.id}">
											<td><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox"  onclick="sendId(this)" value="${account.id}" >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label></td>
											<td>${account.name}</td>
											<td><c:if test="${account.accountType eq 1}">金卡</c:if>
					        					<c:if test="${account.accountType eq 2}">银卡</c:if>
					        					<c:if test="${account.accountType eq 3}">VIP</c:if>
											</td>
											<td><fmt:formatDate value="${account.accountOpeningTime}" pattern="yyyy-MM-dd" /></td>
											<td>${account.accountBalance}元</td>
											<td>${account.vigilanceAmount}元</td>
											<td>${account.nonUseAmount}元</td>
											<td>${account.accountName}</td>
											<td>${account.accountNum}</td>
											<td>${account.openBank}</td>
											<td>${account.bankNum}</td>
											<td>${account.branchGroupName}</td>
											<td>${account.branchGroupAddress}</td>
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
						<h5>账户信息</h5>
						<form id="userForm">
							<div class="form-inline">
								<div class="form-group">
									<label>账户名称：</label>
									<input type="text" id="name" name="name" maxlength="30" />
								</div>
								<div class="form-group">
									<label>开户时间：</label>
									<input type="text" id="accountOpeningTime" name="time" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  />
									<i class="fa  fa-calendar-check-o " id="calendar2"></i>
								</div>
								<div class="form-group">
									<label>账户类型：</label>
									<select id="accountType" name="accountType">
										<option value ="1">金卡</option>
										<option value ="2">银卡</option>
										<option value="3">VIP</option>
									</select>
								</div>
								<div class="form-group">
									<label>所属网点：</label>
									<select id="branchGroupId" name="branchGroupId"></select>
								</div>
							</div>
							<div class="form-inline">
								<div class="form-group">
									<label>警戒金额：</label>
									<input type="text" id="vigilanceAmount" name="vigilanceAmount" maxlength="30" />
									<span>元</span>
								</div>
								<div class="form-group">
									<label>停用金额：</label>
									<input type="text" id="nonUseAmount" name="nonUseAmount" maxlength="30" />
									<span>元</span>
								</div>
								<div class="form-group">
									<label>&emsp;账户名：</label>
									<input type="text" id="accountName" name="accountName" maxlength="30" />
								</div>
								<div class="form-group">
									<label>&emsp;&emsp;账号：</label>
									<input type="text" id="accountNum" name="accountNum" maxlength="30" />
								</div>
								
							</div>
							<div class="form-inline">
									<div class="form-group">
									<label>&emsp;开户行：</label>
									<input type="text"  id="openBank" name="openBank"/>
								</div>
								<div class="form-group">
									<label>&emsp;&emsp;行号：</label>
									<input type="text"  id="bankNum" name="bankNum"/>
								</div>
							</div>
							<div class="form-inline ">
								<div class="form-group">
									<label>&emsp;&emsp;备注：</label>
									<input type="text" id="remark" name="remark" class="remarkInput" maxlength="20"/>
									<span >0/20</span>
								</div>
							</div>
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
									<button type="button" class="btn sureBtn" onclick="addAcount()">确定</button>
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
								<button type="button" class="btn sureBtn" onclick="closeAndfresh()">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
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
								<button type="button" class="btn sureBtn" onclick="closeAndfresh()">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
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
							<span>你是否选择删除此分支，删除后不可还原</span>
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
			 +"<td name='resaon"+index+"'></td>"
			 +"<td name='openTime"+index+"'>"+account.accountOpeningTime+"</td>"
			 +"<td id=''>"+account.accountBalance+"元</td>"
			 +"<td id=''>"+account.vigilanceAmount+"元</td>"
			 +"<td id=''>"+account.nonUseAmount+"元</td>"
			 +"<td id=''>"+account.accountName+"</td>"
			 +"<td id=''>"+account.accountNum+"</td>"
			 +"<td id=''>"+account.openBank+"</td>"
			 +"<td id=''>"+account.bankNum+"</td>"
			 +"<td id=''>"+account.branchGroupName+"</td>"
			 +"<td id=''>"+account.branchGroupAddress+"</td>"
			 +"</tr>");
			$("td[name='resaon"+index+"']").html("");
			if(account.accountType==1){
				$("td[name='resaon"+index+"']").append("金卡"); 
			}
			else if(account.accountType==2){
				$("td[name='resaon"+index+"']").append("银卡"); 
			}
			else if(account.accountType==3){
				$("td[name='resaon"+index+"']").append("VIP"); 
			}
		});	
	}
</script>

</html>