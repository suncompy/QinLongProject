<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
	<meta charset="UTF-8">
	<title>集装箱管理</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap-datetimepicker.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/public/public.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/manage/transportationManagement/boxManagement.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/transportationManagement/boxManagement.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.validate.js"></script>
	<!--分页-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js"></script>
	<link href="${pageContext.request.contextPath}/css/page/page.css" rel="stylesheet" />
	
	<script type="text/javascript">
		//下拉选择厢型
		$(function() {

			//确认操作后页面刷新
			$("#sure_msg").click(function() {
				window.location.reload();
			});

			//新增失败提示  不刷新
			$("#sure_msg2").click(function() {
				$("#promptIdModal2").modal("hide");
			});

			$("#addBox").click(function() {
				$("#addBoxModals").modal();
			})

			$.ajax({
				type: "POST",
				url: "${pageContext.request.contextPath}/container/type/list.do",
				success: function(data) {
					$("#selectType").empty();
					$("#searchForm select[name=containerTypeId]").html("<option></option>");
					$.each(data.data, function(index, its) {
						$("#selectType").append(
							"<option value=" + its.id + ">" + its.name +
							"</option>");
						/*条件查询*/
						$("#searchForm select[name=containerTypeId]").append("<option value=" + its.id + ">" + its.name +
							"</option>");
					});
				}
			});
			$("#myModalLabel").click(function() {

				$(".tabbable .nav li").click(function() {
					$('input[type=checkbox]').prop("checked", false);
				})
			});
		});

		//厢型列表
		function typeList() {
			$('#tableCheckbox input[type=checkbox]').prop("checked", false);
			$("#boxManageForm input[type=checkbox]").prop("checked", false);
			$.ajax({
				type: "POST",
				url: "${pageContext.request.contextPath}/container/type/list.do",
				success: function(data) {
					$("#ths").empty();
					$
						.each(
							data.data,
							function(index, its) {
								$("#ths")
									.append(
										"<tr><td class='tdClass'><label class='demo--label'><input class='demo--checkbox' type='checkbox' value='" + its.id + "'><span class='demo--checkboxInput'></span></label></td><td class='secondtd'>" +
										its.name +
										"</td><td class='secondtd'>" +
										its.code +
										"</td></tr>");
							});
				}
			});
		};

		//新增集装箱
		function sub() {
			$.ajax({
				cache: true,
				type: "POST",
				url: "${pageContext.request.contextPath}/container/add.do",
				data: $('#addForm').serialize(),
				async: false,
				success: function(data) {
					if(data.status != 1) {
						//$("#addBoxModals").modal("hide");
						$("#msg2").html(data.msg);
						$("#promptIdModal2").modal();
					} else {
						$("#addBoxModals").modal("hide");
						$("#msg").html(data.msg);
						$("#promptIdModal").modal();
					}
				}
			});
		};

		//新增厢型
		function typeSub() {
			$.ajax({
				cache: true,
				type: "POST",
				url: "${pageContext.request.contextPath}/container/type/add.do",
				data: $('#addFormId').serialize(),
				async: false,
				success: function(data) {
					if(data.status != 1) {
						$("#msg").html(data.msg);
						$("#promptIdModal").modal();
					} else {
						$("#addBoxModals").modal("hide");
						$("#msg").html(data.msg);
						$("#promptIdModal").modal();
					}
				}
			});
		};

		//删除
		function dels() {
			$("#deletReasonModal").hide();
			var idList = new Array();
			$.each($('input:checkbox'), function() {
				if(this.checked) {
					idList.push($(this).val());
				}
			});

			$.ajax({
				type: "POST",
				url: "${pageContext.request.contextPath}/container/delete.do",
				dataType: 'json',
				data: {
					"idList": idList
				},
				success: function(data) {
					if(data.status != 1) {
						$("#msg").html(data.msg);
						$("#promptIdModal").modal();
					} else {
						$("#msg").html(data.msg);
						$("#promptIdModal").modal();
					}
				}
			});
		};

		//删除厢型类别
		function typeDel() {
			$("#boxDelModal").hide();
			var idList = new Array();
			$.each($('input:checkbox'), function() {
				if(this.checked) {
					idList.push($(this).val());
				}
			});

			$.ajax({
				type: "POST",
				url: "${pageContext.request.contextPath}/container/type/delete.do",
				dataType: 'json',
				data: {
					"idList": idList
				},
				success: function(data) {
					if(data.status != 1) {
						$("#msg").html(data.msg);
						$("#promptIdModal").modal();
					} else {
						$("#msg").html(data.msg);
						$("#promptIdModal").modal();
					}
				}
			});
		};

		function closeModal() {
			$("#promptIdModal").modal("hide");
			window.location.reload();
		};

		function keyUP() {
			var ssHmtl = $("input[name=eastContainer]").val() + $("input[name=containerKind]").val() + $("input[name=containerCode]").val();
			$("input[name=containerId]").val(ssHmtl);
		}
	</script></head>
 	<!--搜索-->
	<script>
		function search(param){
			$.ajax({
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/container/byPage/list.do',  
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

	<body>
		<!-- 集装箱管理表单-->
			<div class="form project_form container_top">
				<form class="form-inline maxWidth" id='searchForm'>
					<div id="wrap">
						<div class="form-group">
							<label>厢型：</label>
							<select name='containerTypeId'></select>
						</div>
						<div class="form-group">
							<label>集装箱号：</label>
							<input name='containerId' type="text" maxlength="30"/>
						</div>
					</div>
					<div class="foot">
						<a class="btn btn-success" href="javascript:;"><em class="search"></em>
							<span onclick="search(this)">搜索</span>
						</a>
					</div>
				</form>
			</div>
		<!-- 集装箱管理表格-->
		<div class="container_top tableDiv">
			<div class="tabbable">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#panel1" data-toggle="tab">集装箱管理</a>
					</li>
				</ul>
				<!--集装箱列表-->
				<div class="tab-content">
					<div class="tab-pane active" id="panel1">
						<div class="domain">
							<div class="tableBg">
								<a href="javascript:void(0)" class="exportBtn boxManage" id="boxManage"
									onclick="typeList()"><span>厢型管理</span></a> <a href="javascript:void(0)"
									class="exportBtn addNew" id="addBox"><span>添加集装箱</span></a> <a
									href="javascript:void(0)" class="exportBtn del" id="delBtn"><span>删除</span></a>
							</div>
							<div class="table-responsive">
								<table class="table" id="tableCheckbox">
									<thead>
										<tr class="tableTop">
											<th><label class="demo--label"> <input
													class="demo--checkbox" type="checkbox" disabled="disabled" > <span
													class="demo--checkboxInput"></span>
											</label></th>
											<th>厢型</th>
											<th>编码</th>
											<th>厢东</th>
											<th>厢类</th>
											<th>厢码</th>
											<th>集装箱号</th>
											<th>尺寸</th>
											<th>长</th>
											<th>宽</th>
											<th>高</th>
											<th>容积</th>
											<th>自重</th>
											<th>载重</th>
											<th>当前位置</th>
										</tr>
									</thead>
									<tbody id="boxTbody">
										<c:forEach items="${boxList.rows}" var="it">
											<tr>
												<td><label class="demo--label"> <input
														class="demo--checkbox" type="checkbox" value="${it.id}">
														<span class="demo--checkboxInput"></span>
												</label></td>
												<td>${it.name}</td>
												<td>${it.containerNum}</td>
												<td>${it.eastContainer}</td>
												<td>${it.containerKind}</td>
												<td>${it.containerCode}</td>
												<td>${it.containerId}</td>
												<td>${it.size}</td>
												<td>${it.length}</td>
												<td>${it.width}</td>
												<td>${it.hight}</td>
												<td>${it.volume}㎡</td>
												<td>${it.selfWeight}吨</td>
												<td>${it.weight}吨</td>
												<td><a href="javascript:void(0)">安徽省合肥市XX站点</a></td>
											</tr>
										</c:forEach>
	
									</tbody>
								</table>
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-md-12 column paging page_div" id="page"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 厢型管理 -->
		<div class="modal fade boxManageModal" id="boxManageModal"
			tabindex="-1 " role="dialog" data-backdrop="static">
			<div class="modal-dialog modal-lg " role="document ">
				<div class="modal-content ">
					<div class="modal-header ">
						<h4 class="modal-title" id="myModalLabel">厢型管理</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body ">
						<form id="boxManageForm">
							<div class="opritionClass btnbg">
								<a href="javascript:void(0)" class="exportBtn addNew" id="addbox"><span>添加</span></a>
								<a href="javascript:void(0)" class="exportBtn del" id="modalDel"></em><span>删除</span></a>
							</div>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr class="tableTop ">
											<th><label class="demo--label"> <input
													class="demo--checkbox" type="checkbox"> <span
													class="demo--checkboxInput"></span>
											</label></th>
											<th>厢型</th>
											<th>编码</th>
										</tr>
									</thead>
									<tbody id="ths">
										<!-- <tr>
										<td class="tdClass"><label class="demo--label"> <input
												class="demo--checkbox" type="checkbox"> <span
												class="demo--checkboxInput"></span>
										</label></td>
										<td class="secondtd"></td>
										<td class="secondtd "></td>
									</tr> -->
									</tbody>
								</table>
							</div>
							<div class="row clearfix">
								<div class="col-md-12 column modal_btn">
									<button type="button" class="btn sureBtn">确定</button>
									<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	
		<!--添加集装箱 -->
		<div class="modal fade addBoxModal " id="addBoxModals" tabindex="-1"
			role="dialog" data-backdrop="static">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">添加集装箱</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="addForm">
							<div class="project_info">
								<div class="form-inline">
									<div class="form-group">
										<label>&emsp;厢型：</label> <select id="selectType" name="containerTypeId"></select>
									</div>
									<div class="form-group ">
										<label>编码：</label> <input type="text" maxlength="30" name="containerNum"/>
									</div>
									<div class="form-group">
										<label>厢东：</label> <input type="text" maxlength="30" name="eastContainer"/>
									</div>
									<div class="form-group">
										<label>厢类：</label> <input type="text" maxlength="30" name="containerKind"/>
									</div>
								</div>
								<div class="form-inline">
									<div class="form-group">
										<label>&emsp;厢码：</label> <input type="text" maxlength="30" name="containerCode" onkeyup="keyUP()"/>
									</div>
									<label>集装箱号：</label> <input type="text" maxlength="30"
										class="inputClass inputbg" unselectable="on"
										readonly="readonly" name="containerId"/>
								</div>
								<div class="form-inline">
									<div class="form-group">
										<label>&emsp;自重：</label> <input type="text" maxlength="30" name="selfWeight"/>
										<span>吨</span>
									</div>
									<div class="form-group">
										<label>载重：</label> <input type="text" maxlength="30" name="weight"/> <span>吨</span>
									</div>
									<div class="form-group">
										<label>容积：</label> <input type="text" maxlength="30" name="volume"/> <span>㎡</span>
									</div>
									<div class="form-group">
										<label>尺寸：</label> <input type="text" maxlength="30" name="size"/>
									</div>
								</div>
								<div class="form-inline">
									<div class="form-group">
										<label>&emsp;&emsp;长：</label> <input type="text" maxlength="30" name="length"/>
										<span>cm</span>
									</div>
									<div class="form-group">
										<label>&emsp;宽：</label> <input type="text" maxlength="30" name="width"/> <span>cm</span>
									</div>
									<div class="form-group">
										<label>&emsp;高：</label> <input type="text" maxlength="30" name="hight"/> <span>cm</span>
									</div>
								</div>
								<div class="form-inline">
									<label>集装箱地址：</label> <select id="addressSelect"></select>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-md-12 column modal_btn">
									<input type="button" value="确认" onclick="sub()">
									<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	
		<!--厢型管理添加集装箱 -->
		<div class="modal fade addboxModal " id="addboxModal" tabindex="-1"
			role="dialog" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">添加厢型</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="addFormId">
							<div class="project_info">
								<div class="form-inline">
									<div class="form-group">
										<label>&emsp;厢型：</label> <input type="text" maxlength="30" name="name"/>
									</div>
									<div class="form-group ">
										<label>编码：</label> <input type="text" maxlength="30" name="code"/>
									</div>
								</div>
								<div class="row clearfix">
									<div class="col-md-12 column modal_btn">
										<input type="button" value="确认" onclick="typeSub()">
										<button type="button" class="btn cancleBtn"
											data-dismiss="modal">取消</button>
									</div>
								</div>
						</form>
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
									<button type="button" class="btn sureBtn">确定</button>
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
								<textarea class="form-control" rows="3"></textarea>
							</div>
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
									<button type="button" class="btn sureBtn" onclick="dels()">确定</button>
									<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
								</div>
							</div>
						    </form>
						</div>
					</div>
				</div>
			</div>
			
				
	<!--确认提示模态框-->		
		     <div class="modal fade promptModal" tabindex="-1" role="dialog"
			id="promptIdModal">
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
		
		<!--删除原因厢型管理模态框-->
			<div class="modal fade deletReasonModal" tabindex="-1" role="dialog" id="boxDelModal" data-backdrop="static">
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
									<button type="button" class="btn sureBtn" onclick="typeDel()">确定<tton>
									<button type="button" class="btn cancleBtn" data-dismiss="modal">取消<tton>
								</div>
							</div>
						    </form>
						</div>
					</div>
				</div>
			</div>
	
		
		
		
		
					
	<!--确认提示模态框-->		
		     <div class="modal fade promptModal" tabindex="-1" role="dialog"
			id="promptIdModal2">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<p id="msg2"></p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn sureBtn" id="sure_msg2">
							确定</button>
					</div>
				</div>
			</div>
		</div>
	
	</body>

  <!--分页-->
<script >
	$("#page").paging({
		pageNo:1, 
		totalPage:${boxList.totalPage},
		totalSize:${boxList.limit},
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
       		url:'${pageContext.request.contextPath}/container/byPage/list.do',  
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

<script>
	function htmlTable(results){
		var boxList = results;
		$('#boxTbody').html("");
		$.each(boxList,function(index,it){
			$('#boxTbody').append("<tr><td><label class='demo--label'> <input class='demo--checkbox'"
					+"type='checkbox' value='"+it.id+"'>"
					+"<span class='demo--checkboxInput'></span></label></td>"
					+"<td>"+it.name+"</td>"
					+"<td>"+it.containerNum+"</td>"
					+"<td>"+it.eastContainer+"</td>"
					+"<td>"+it.containerKind+"</td>"
					+"<td>"+it.containerCode+"</td>"
					+"<td>"+it.containerId+"</td>"
					+"<td>"+it.size+"</td>"
					+"<td>"+it.length+"</td>"
					+"<td>"+it.width+"</td>"
					+"<td>"+it.hight+"</td>"
					+"<td>"+it.volume+"㎡</td>"
					+"<td>"+it.selfWeight+"吨</td>"
					+"<td>"+it.weight+"吨</td>"
					+"<td><a href='#'>安徽省合肥市XX站点</a></td></tr>")
		})	
	}
</script>
</html>