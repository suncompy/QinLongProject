<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="UTF-8">
		<title>支付模式</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/manage/financialManagement/paymentMode.css"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/public.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/config/url.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.validate.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/financialManagement/paymentMode.js"></script>
		<!--分页-->
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js" ></script>
		<link href="${pageContext.request.contextPath}/css/page/page.css" rel="stylesheet"/>
		<script type="text/javascript">
				function deletePayment(){
					var idList = new Array(); 
					$.each($('.payement input:checkbox'), function() {
						if (this.checked) {
							idList.push($(this).val());
						}
					});
					$.ajax({
						type : 'POST',
						url : "${pageContext.request.contextPath}/payment/deletePayment.do",
						data : {"ids":idList.toString()},
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
			
			function addPayment(){
				var accountType = $("#accountType").val();
				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/payment/addPayment.do",
					data : {"name":accountType},
					dataType : "json",
					success : function(data) {
						if (data.status != 200) {
							$("#errorPointMsg").html(data.msg);
							$("#errorPromptIdModal").modal();
						} else {
							$("#addModeModal").modal("hide");
							$("#pointMsg").html("新增成功");
							$("#promptIdModal").modal();
						}
					},
				}); 
			}
			
			var chbs = document.getElementsByName("subCheck");
			//全选
			function checkAll() {
				for(var i = 0; i < chbs.length; i++) {
					chbs[i].checked = document.getElementById("checkFalse").checked;
				}
				 var ckbValue = "";
				$("input[name=subCheck]:checked").each(function() {
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
		</script>
	</head>
	<body>
		<!--支付模式表格-->
		<div class="container_top tableDiv">
			<div class="tabbable">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#panel1" data-toggle="tab">支付模式</a>
					</li>
				</ul>
				<!--支付模式列表-->
				<div class="tab-content">
					<div class="tab-pane active" id="panel1">
						<div class="domain">
							<div class="tableBg">
								<a href="javascript:void(0)" class="exportBtn add" id="addMode"><span>新建模式</span></a>
								<a href="javascript:void(0)" class="exportBtn del" id="delBtn"><span>删除</span></a>
							</div>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th> <label class="demo--label">
											     <input class="demo--checkbox" type="checkbox" disabled="disabled"  value='' id="checkFalse" onclick="checkAll()">
                                                 <span class="demo--checkboxInput"></span>
	                                        </label> </th>
											<th>序号</th>
											<th>支付模式</th>
										</tr>
									</thead>
									<tbody class="payement" id="paymentBody">
									<c:forEach items="${paymentList.rows}" var="payList" varStatus="status">
										<tr>
											<td><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox" name="subCheck" value="${payList.id}" onclick="checkitemIds(this)" >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label></td>
											<td>${payList.serial}</td>
											<td>${payList.name}</td>
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
		<!--新建模式-->	
	<div class="modal fade addModeModal" id="addModeModal" tabindex="-1" role="dialog" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">新建模式</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
                     	<form id="addModeForm">
							<div class="form-inline">
								<div class="form-group">
									<label>支付模式：</label>
									<input type="text" id="accountType" name="accountType" maxlength="30" /><i class='requireds'>*</i>
								</div>
							</div>
						
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
									<button type="button" class="btn sureBtn" onclick="addPayment()">确定</button>
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
								<button type="button" class="btn sureBtn" onclick="closeAndfresh()">确定</button>
								<!--<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>-->
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
								<button type="button" class="btn sureBtn" onclick="deletePayment()">确定</button>
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
	</body>
	<script>
	//分页
		$("#page").paging({
		pageNo:1,
		totalPage:${paymentList.totalPage},
		totalSize:${paymentList.limit},
		callback: function(num) {
				$.ajax({  
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/payment/selectPayment.do',  
		        data:{page:num},
		        cache:false,
		        dataType:'json',
	        	success:function(data){
	        		if(data.status==200){
	        		var paymentList = data.data;
	        		$('#paymentBody').html("");
	        		$.each(paymentList,function(index,payList){
	        			$('#paymentBody').append("<tr>"
								+"<td style='text-align: center;'><label class='demo--label'>"
								+"<input class='demo--checkbox' type='checkbox' name='subCheck' value='"+payList.id+"' >"
                                +"<span class='demo--checkboxInput'></span>"
                                +"</label></td>"
								+"<td>"+payList.serial+"</td>"
								+"<td>"+payList.name+"</td>"
								+"</tr>");
	        		});	
	        	}	
	    	}
	      })
		}
		});
</script>
</html>