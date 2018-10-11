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
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/config/url.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/transportationManagement/boxManagement.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.validate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.serializejson.js"></script>
	<!--分页-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js"></script>
	<link href="${pageContext.request.contextPath}/css/page/page.css" rel="stylesheet" />
	
	<script type="text/javascript">
		//下拉选择厢型
		$(function() {
			
			//中心站点
			/* $.ajax({
				type: "POST",
				url: "${pageContext.request.contextPath}/trainStation/selectThreeTrainStationByLevel.do",
				dataType: 'json', 
				success: function(data) {
					$("#centerStaion").empty();
					$.each(data.data, function(index, its) {
						$("#centerStaion").append(
							"<option value=" + its.id + ">" + its.stationName +"</option>");
					});
					$("#centerStaion").change();
				}
			}); */
			//站点级别是营业厅下的站点 
			$.ajax({
				type: "POST",
				url: "${pageContext.request.contextPath}/trainStation/listTrainStationByLevel.do",
				data:{level:2},
				dataType: 'json', 
				async: false,  
				success: function(data) {
					$("#childerStation").empty();
                	$.each(data.data, function(index, its) {
						$("#childerStation").append(
    							"<option value="+its.id+">" + its.stationName
    									+ "</option>");
					});	
				}
			});
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
				$("#modelTitle").html("添加集装箱");
				$("#addForm").validate().resetForm();
				 $("#addForm")[0].reset();
				 $("#hiddenContainerId").val("");
			})

			$.ajax({
				type: "POST",
				url: "${pageContext.request.contextPath}/container/type/list.do",
				dataType: 'json', 
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
		
		/* function stationChange(){
			var id = $("#centerStaion option:selected").val();
			var name = $("#centerStaion option:selected").text();
			$("#cStaionName").val("");
			$("#cStaionName").val(name);
			$.ajax({  
                type: "POST",  
                url:"${pageContext.request.contextPath}/trainStation/getchildrenStationById.do", 
                data:{id:id},
                async: false,  
                dataType: 'json', 
                success: function(data) {  
                	$("#childerStation").empty();
                	$.each(data.data, function(index, its) {
						$("#childerStation").append(
    							"<option value="+its.id+">" + its.stationName
    									+ "</option>");
					});	
                	childerStationChange();
                }  
            });  
		} */
		
		/* function childerStationChange(){
			var id = $("#childerStation option:selected").val();
			var name = $("#childerStation option:selected").text();
			$("#chStaionName").val("");
			$("#chStaionName").val(name);
			var cs = $("#cStaionName").val();
			//$("#trainLocationId").val(cs+name);
			$("#trainLocationId").val(id);
		} */
		
		//厢型列表
		function typeList() {
			$('#tableCheckbox input[type=checkbox]').prop("checked", false);
			$("#boxManageForm input[type=checkbox]").prop("checked", false);
			$.ajax({
				type: "POST",
				url: "${pageContext.request.contextPath}/container/type/list.do",
				dataType: 'json', 
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
				dataType: 'json', 
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
			var boxTypeId = $("#boxTypeId").val();
			var boxTypeCode = $("#boxTypeCode").val();
			if(boxTypeId == null || boxTypeId == ""){
				$("#promptIdModal2").modal();
				$("#msg2").html("请补全信息");
				return;
			}
			if(boxTypeCode == null || boxTypeCode == ""){
				$("#promptIdModal2").modal();
				$("#msg2").html("请补全信息");
				return;
			}
			$.ajax({
				cache: true,
				type: "POST",
				url: "${pageContext.request.contextPath}/container/type/add.do",
				data: $('#addFormId').serialize(),
				dataType: 'json', 
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

		function keyUP(obj) {
			obj.value=obj.value.replace(/([^\u0000-\u00FF])/g,'');
			var ssHmtl = $("input[name=eastContainer]").val() + $("input[name=containerKind]").val() + $("input[name=containerCode]").val();
			$("#addBoxModals input[name=containerId]").val(ssHmtl);
		}
		
		function noInputZc(obj){
			obj.value=obj.value.replace(/([^\u0000-\u00FF])/g,'');
		}
		
		//修改集装箱获取单个详情 
		function getOneDetail(){
			var id="";
			if($("input:checkbox[type='checkbox']:checked").length > 1) {
				$("#deletRemindModal").modal();
			} else if($("input:checkbox[type='checkbox']:checked").length == 0) {
				$("#deletnullModal").modal();
			} else{
				$("#modelTitle").html("修改集装箱");
				$("#addBoxModals").modal();
				$("#addForm").validate().resetForm();
				$("#addForm")[0].reset();
				id = $("input:checkbox[type='checkbox']:checked").val();
				$.ajax({
					type: "POST",
					url: "${pageContext.request.contextPath}/container/getContainerDetail.do",
					data: {"id": id},
					dataType: 'json', 
					async: false,
					success: function(data) {
						$("#hiddenContainerId").val(data.data.id);
						$("#addBoxModals select[name=containerTypeId]").val(data.data.containerTypeId);
						$("#addBoxModals input[name=containerNum]").val(data.data.containerNum);
						$("#addBoxModals input[name=eastContainer]").val(data.data.eastContainer);
						$("#addBoxModals input[name=containerKind]").val(data.data.containerKind);
						$("#addBoxModals input[name=containerCode]").val(data.data.containerCode);
						$("#addBoxModals input[name=containerId]").val(data.data.containerId);
						$("#addBoxModals input[name=selfWeight]").val(data.data.selfWeight);
						$("#addBoxModals input[name=weight]").val(data.data.weight);
						$("#addBoxModals input[name=volume]").val(data.data.volume);
						$("#addBoxModals input[name=size]").val(data.data.size);
						$("#addBoxModals input[name=length]").val(data.data.length);
						$("#addBoxModals input[name=width]").val(data.data.width);
						$("#addBoxModals input[name=hight]").val(data.data.hight);
						$("#addBoxModals select[name=trainLocationId]").val(data.data.trainLocationId);
					}
				});
			}
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
							
								<c:forEach items='${permissions }' var='permission' >
									<c:if test="${permission.code=='boxManage'}">
										<a href="javascript:void(0)" class="exportBtn boxManage" id="boxManage" onclick="typeList()"><span>厢型管理</span></a> 
									</c:if>
									<c:if test="${permission.code=='addBox'}">
										<a href="javascript:void(0)" class="exportBtn addNew" id="addBox"><span>添加集装箱</span></a>
									</c:if>
									<c:if test="${permission.code=='updateBox'}">
										<a href="javascript:void(0)" class="exportBtn revise" onclick="getOneDetail()"><span>修改</span></a> 
									</c:if>
									<c:if test="${permission.code=='delBox'}">
										<a href="javascript:void(0)" class="exportBtn del" id="delBtn"><span>删除</span></a>
									</c:if>
								</c:forEach>
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
											<th>状态</th>
											<th>项目编号</th>
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
												<td>${it.length}mm</td>
												<td>${it.width}mm</td>
												<td>${it.hight}mm</td>
												<td>${it.volume}m³</td>
												<td>${it.selfWeight}吨</td>
												<td>${it.weight}吨</td>
												<td>${it.stationName}</td>
												<td><c:if test="${it.status eq 0}">空闲</c:if>
					        						<c:if test="${it.status eq 1}">使用中</c:if>
					        						<c:if test="${it.status eq 2}">运输中</c:if>
					        						<c:if test="${it.status eq 3}">装车中</c:if>
												</td>
												<td>${it.projectCode}</td>
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
						<h4 class="modal-title" id="modelTitle">添加集装箱</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="addForm">
							<div class="project_info">
								<h5>基本信息<i class='requireds'>*</i></h5>
								<input type="hidden"  name="id" id="hiddenContainerId"/>
								<div class="form-inline">
									<div class="form-group">
										<label>&emsp;厢型：</label> <select id="selectType" name="containerTypeId"></select>
									</div>
									<div class="form-group ">
										<label>编码：</label> <input type="text" maxlength="7" name="containerNum"  onkeyup="noInputZc(this)"/>
									</div>
									<div class="form-group">
										<label>厢东：</label> <input type="text" maxlength="30" name="eastContainer" onkeyup="keyUP(this)"/>
									</div>
									<div class="form-group">
										<label>厢类：</label> <input type="text" maxlength="30" name="containerKind" onkeyup="keyUP(this)"/>
									</div>
								</div>
								<div class="form-inline">
									<div class="form-group">
										<label>&emsp;厢码：</label> <input type="text" maxlength="30" name="containerCode" onkeyup="keyUP(this)"/>
									</div>
									<label>集装箱号：</label> <input type="text" maxlength="30"
										class="inputClass inputbg" unselectable="on"
										readonly="readonly" name="containerId"/>
								</div>
								<hr />
								<h5>其它信息<i class='requireds'>*</i></h5>
								<div class="form-inline">
									<div class="form-group">
										<label>&emsp;自重：</label> <input type="text" maxlength="30" name="selfWeight" onkeyup="value=value.replace(/[^\d.]/g,'')"/>
										<span>吨</span>
									</div>
									<div class="form-group">
										<label>载重：</label> <input type="text" maxlength="30" name="weight" onkeyup="value=value.replace(/[^\d.]/g,'')"/> <span>吨</span>
									</div>
									<div class="form-group">
										<label>容积：</label> <input type="text" maxlength="30" name="volume" onkeyup="value=value.replace(/[^\d.]/g,'')"/> <span>m³</span>
									</div>
									<div class="form-group">
										<label>尺寸：</label> <input type="text" maxlength="30" name="size" onkeyup="value=value.replace(/[^\d.]/g,'')"/>
									</div>
								</div>
								<div class="form-inline">
									<div class="form-group">
										<label>&emsp;&emsp;长：</label> <input type="text" maxlength="30" name="length" onkeyup="value=value.replace(/[^\d.]/g,'')"/>
										<span>mm</span>
									</div>
									<div class="form-group">
										<label>&emsp;宽：</label> <input type="text" maxlength="30" name="width" onkeyup="value=value.replace(/[^\d.]/g,'')"/> <span>mm</span>
									</div>
									<div class="form-group">
										<label>&emsp;高：</label> <input type="text" maxlength="30" name="hight" onkeyup="value=value.replace(/[^\d.]/g,'')"/> <span>mm</span>
									</div>
								</div>
								<hr />
								<h5>集装箱地址<i class='requireds'>*</i></h5>
								<div class="form-inline">
									<!-- <div class="form-group">
									<label>中心站点：</label>
									<select id="centerStaion" name="centerStaion" onchange="stationChange()"></select>
									</div> -->
									<div class="form-group">
										<label>站点：</label>
										<select id="childerStation" name="trainLocationId" ></select>
									</div>
									<!-- <input type="hidden"   id="cStaionName" onchange="childerStationChange()"/>
									<input type="hidden"   id="chStaionName"/>
									<input type="hidden"  name="trainLocationId" id="trainLocationId"/> -->
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-md-12 column modal_btn">
									<input type="button"  class="btn sureBtn"  value="确认" onclick="sub()">
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
										<label>&emsp;厢型：</label> <input type="text" maxlength="30" id="boxTypeId" name="name"/><i class='requireds'>*</i>
									</div>
									<div class="form-group ">
										<label>编码：</label> <input type="text" maxlength="30" id="boxTypeCode" name="code"/><i class='requireds'>*</i>
									</div>
								</div>
								<div class="row clearfix">
									<div class="col-md-12 column modal_btn">
										<input type="button" value="确认" style='background: #04a9ed !important;    border-radius: 0 !important;
    padding: 4px 15px !important;color: #FFFFFF !important;' onclick="typeSub()">
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
								<span>抱歉，不可批量操作</span>
							</div>
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
									<button type="button" class="btn sureBtn" data-dismiss="modal">确定</button>
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
						<p id="msg2" style="text-align: center;font-size: 24px;"></p>
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
			var sta = "";
			if(it.status == 0){
				sta = "空闲";
			}else if(it.status == 1){
				sta = "使用中";
			}else if(it.status == 2){
				sta = "运输中";
			}else{
				sta = "装车中";
			}
			$('#boxTbody').append("<tr><td style='text-align: center;'><label class='demo--label'> <input class='demo--checkbox'"
					+"type='checkbox' value='"+it.id+"'>"
					+"<span class='demo--checkboxInput'></span></label></td>"
					+"<td>"+it.name+"</td>"
					+"<td>"+it.containerNum+"</td>"
					+"<td>"+it.eastContainer+"</td>"
					+"<td>"+it.containerKind+"</td>"
					+"<td>"+it.containerCode+"</td>"
					+"<td>"+it.containerId+"</td>"
					+"<td>"+it.size+"</td>"
					+"<td>"+it.length+"mm</td>"
					+"<td>"+it.width+"mm</td>"
					+"<td>"+it.hight+"mm</td>"
					+"<td>"+it.volume+"m³</td>"
					+"<td>"+it.selfWeight+"吨</td>"
					+"<td>"+it.weight+"吨</td>"
					+"<td>"+it.stationName+"</td>"
					+"<td>"+sta+"</td>"
					+"<td>"+it.projectCode+"</td></tr>")
		})	
	}
</script>
</html>