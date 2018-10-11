<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="UTF-8">
		<title>货物信息</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/manage/cargoManagement/cargoInformation.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/public.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/config/url.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/cargoManagement/cargoInformation.js"></script>
		<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.validate.js"></script> --%>
		<!--分页-->
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js"></script>
		<link href="${pageContext.request.contextPath}/css/page/page.css" rel="stylesheet" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.serializejson.js"></script>

		<!--搜索-->
		<script>
			function search(param) {
				$.ajax({
					type: 'post',
					url: '${pageContext.request.contextPath}/cargo/listCargoByPage.do',
					data: {
						page: 1,
						search: JSON.stringify($('#searchForm').serializeJSON())
					},
					cache: false,
					dataType: 'json',
					success: function(data) {
						if(data.status == 200) {
							htmlTable(data.data.rows);
							$("#page").paging({
								pageNo: 1,
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
			function sendId(param) {
				if($(param).prop("checked")) {
					$("#operateId").val(param.value);
				} else {
					$("#operateId").val("");
				}
			}

			function deleteCargo() {
				//var id = $("#operateId").val();
				var id = $("#operatorTable input:checkbox[type='checkbox']:checked").val();
				if(id == null || id == "") {
					return;
				}
				$.ajax({
					type: 'POST',
					url: "${pageContext.request.contextPath}/cargo/deleteCargo.do",
					data: {
						"id": id
					},
					dataType: "json",
					success: function(data) {
						if(data.status == 200) {
							$("#deletReasonModal").modal("hide");
							$("#pointMsg").html("删除成功");
							$("#promptIdModal").modal();
						} else {
							$("#deletReasonModal").modal("hide");
							$("#pointMsg").html(data.msg);
							$("#promptIdModal").modal();
						}
					},
				});
			}

			$(function() {
				//货物详情模态框
				$("body").on("click", "#panel1 tbody tr td:not(:nth-child(1))", function() {
					$(this).parent().toggleClass("bgclass").siblings("#panel1 tbody tr").removeClass("bgclass");
					var cargoId = $(this).parent().attr("id");
					$.ajax({
						type: 'POST',
						url: "${pageContext.request.contextPath}/cargo/cargoInfoDetail.do",
						data: {
							"cargoId": cargoId
						},
						dataType: "json",
						cache: false,
						success: function(data) {
							$("#detailId")[0].reset();
							$("#cargoMapName").html(data.data.cargoMainPointDetail.cargoName);
							$("#cargoMapCode").html(data.data.cargoMainPointDetail.cargoCode);
							$("#cargoMapMainPointName").html(data.data.cargoMainPointDetail.pointName);
							$("#cargoMapMainPoint").html(data.data.cargoMainPointDetail.pointMin + "%" + " " + "~" + " " + data.data.cargoMainPointDetail.pointMax + "%");
							var cargoPointDetailList = data.data["cargoPointDetail"];
							$("#pointIdList").empty();
							$.each(cargoPointDetailList, function(index, cargoPointDetail) {
								$("#pointIdList").append("<dd><label >指标" + " " + cargoPointDetail.serial + ":</label><span id=''>" + "  " + cargoPointDetail.pointName + "&nbsp;&nbsp;" + cargoPointDetail.pointMin + "%" + " " + "~" + cargoPointDetail.pointMax + "%" + " " + "</span></dd>")
							});
							var cargoSpecificteDetailList = data.data["cargoSpecificteDetail"];
							$("#specificId").empty();
							$.each(cargoSpecificteDetailList, function(index, cargoSpecificteDetail) {
								$("#specificId").append("<dd><label >规格" + " " + cargoSpecificteDetail.serial + ":</label><span id=''>" + "  " + cargoSpecificteDetail.name + "&nbsp;&nbsp;</span></dd>")
							});
							$("#detailModal").modal();
							$('#showMask', window.parent.document).show();
						},
					});
				});
				//新增模态框
				$("body").on("change", ".moneyCheck", function() {
					$("#addMainPoint").val("");
					$("#addOtherPoint").val("");
					if($(this).is(':checked')) {
						$(this).val("0").parent().parent().parent().siblings().children("td").find("input").val("1");
						$(this).parent().parent().parent().siblings().children("td").find("input").prop("checked", false);
					} else {
						$(this).val("1");
					}
					//获取新增指标
					var ist = new Array();
					var trList = $("#tPointBod").children("tr");
					for(var i = 0; i < trList.length; i++) {
						var tdArr = trList.eq(i).find("td");
						var type = tdArr.find("input").eq(0).val();
						var point_name = tdArr.eq(2).html(); //指标名称
						var point_value = tdArr.eq(3).html(); //指标值
						var pArray = new Array();
						pArray = point_value.split("~");
						if(type == 0) {
							$("#addMainPoint").val(point_name);
							$("#addOtherPoint").val(pArray[0] + "%" + "~" + pArray[1] + "%");
						}
						ist.push({
							pointName: point_name,
							pointMin: pArray[0],
							pointMax: pArray[1],
							type: type
						});
					}
				});
				$("body").on("dblclick", "#pointTable td:not(:nth-child(1)):not(:nth-child(2))", function() {
					var td = $(this);
					// 根据表格文本创建文本框 并加入表表中--文本框的样式自己调整
					var text = td.text();
					var txt = $("<input type='text' style='text-align:center';>").val(text);
					txt.blur(function() {
						// 失去焦点，保存值。于服务器交互自己再写,最好ajax
						var newText = $(this).val();

						// 移除文本框,显示新值
						$(this).remove();
						td.text(newText);
					});
					td.text("");
					td.append(txt);
					txt.focus();
				});
				$("body").on("dblclick", "#secpiTable td:not(:nth-child(1))", function() {
					var td = $(this);
					// 根据表格文本创建文本框 并加入表表中--文本框的样式自己调整
					var text = td.text();
					var txt = $("<input type='text' style='text-align:center';>").val(text);
					txt.blur(function() {
						// 失去焦点，保存值。于服务器交互自己再写,最好ajax
						var newText = $(this).val();

						// 移除文本框,显示新值
						$(this).remove();
						td.text(newText);
					});
					td.text("");
					td.append(txt);
				});

			})

			function placeHtml(param) {
				var array = new Array();
				array = $(param).find("input").val().split("~");
				//array[0].trim().replace(/\d/g,"") 保留"."
				if(array[0].trim().replace(/\d/g, "")) {
					//去除"."
					array[0] = array[0].trim().replace(/[^\d,,]]*/g, "");
					$(param).find("input").val(array[0] + "~" + array[1]);
				}
				if(array[1].trim().replace(/\d/g, "")) {
					array[1] = array[1].trim().replace(/[^\d,,]]*/g, "");
					$(param).find("input").val(array[0] + "~" + array[1]);
				}
			}
			//新增货物
			function add() {
				//获取新增指标
				var ist = new Array();
				var trList = $("#tPointBod").children("tr");
				for(var i = 0; i < trList.length; i++) {
					var tdArr = trList.eq(i).find("td");
					var type = tdArr.find("input").eq(0).val();
					var point_name = tdArr.eq(2).html(); //指标名称
					var point_value = tdArr.eq(3).html(); //指标值
					var pArray = new Array();
					pArray = point_value.split("~");
					ist.push({
						pointName: point_name,
						pointMin: pArray[0],
						pointMax: pArray[1],
						type: type
					});
				}
				//校验指标信息是否填写
				var nullFlag = false;
				for(var i = 0; i < trList.length; i++) {
					var tdArr2 = trList.eq(i).find("td");
					var point_name2 = tdArr2.eq(2).html(); //指标名称
					var point_value2 = tdArr2.eq(3).html(); //指标值
					var pArray2 = new Array();
					pArray2 = point_value2.split("~");
					if(point_name2 == "" || pArray2[0] == "" || pArray2[1] == "") {
						nullFlag = true;
						break;
					}
				}

				var jsonArray = JSON.stringify(ist);
				$("#pointJsonIds").val(jsonArray);
				//获取规格
				var spist = new Array();
				var sptrList = $("#tSpecBod").children("tr");
				for(var i = 0; i < sptrList.length; i++) {
					var specTdArr = sptrList.eq(i).find("td");
					var spec_name = specTdArr.eq(1).html(); //规格
					spist.push({
						name: spec_name
					});
				}
				//校验规格信息是否填写
				var nullFalgSpec = false;
				for(var i = 0; i < sptrList.length; i++) {
					var specTdArr2 = sptrList.eq(i).find("td");
					var spec_name2 = specTdArr2.eq(1).html(); //规格
					if(spec_name2 == "") {
						nullFalgSpec = true;
						break;
					}
				}
				var specJsonArray = JSON.stringify(spist);
				$("#specJsonIds").val(specJsonArray);

				if($("#pointTable input:checkbox[type='checkbox']:checked").length == 0) {
					$("#errorPointMsg").html("请选择一个主要指标");
					$("#errorPromptIdModal").modal();
					return;
				}
				if(nullFlag) {
					$("#errorPointMsg").html("请完善指标信息");
					$("#errorPromptIdModal").modal();
					return;
				}
				if(nullFalgSpec) {
					$("#errorPointMsg").html("请完善规格信息");
					$("#errorPromptIdModal").modal();
					return;
				}
				var hideCargoId = $("#addCargoId").val();
				if(hideCargoId == null || hideCargoId == "") {
					$.ajax({
						cache: true,
						type: "POST",
						url: "${pageContext.request.contextPath}/cargo/addCargo.do",
						data: $('#addForm').serialize(),
						async: false,
						dataType: 'json',
						success: function(data) {
							if(data.status != 200) {
								//$("#addCargoModal").modal("hide");
								$("#errorPointMsg").html(data.msg);
								$("#errorPromptIdModal").modal();
							} else {
								$("#addCargoModal").modal("hide");
								$("#pointMsg").html("新增成功");
								$("#promptIdModal").modal();
							}
						}
					});
				} else {
					$.ajax({
						cache: true,
						type: "POST",
						url: "${pageContext.request.contextPath}/cargo/updateCargo.do",
						data: $('#addForm').serialize(),
						async: false,
						dataType: 'json',
						success: function(data) {
							if(data.status != 200) {
								//$("#addCargoModal").modal("hide");
								$("#errorPointMsg").html(data.msg);
								$("#errorPromptIdModal").modal();
							} else {
								$("#addCargoModal").modal("hide");
								$("#pointMsg").html("修改成功");
								$("#promptIdModal").modal();
							}
						}
					});
				}
			};

			function closeAndfresh() {
				$("#promptIdModal").modal("hide");
				window.location.reload();
			}

			function closeNofresh() {
				$("#errorPromptIdModal").modal("hide");
			}

			//修改
			function updCargo() {
				//var id = $("#operateId").val();
				var id = "";
				if($("#operatorTable input:checkbox[type='checkbox']:checked").length > 1) {
					$("#pointMsg").html("只能选择一项数据进行修改");
					$("#promptIdModal").modal();
					return;
				} else if($("#operatorTable input:checkbox[type='checkbox']:checked").length == 0) {
					$("#pointMsg").html("请选择一项数据进行修改");
					$("#promptIdModal").modal();
					return;
				} else {
					id = $("#operatorTable input:checkbox[type='checkbox']:checked").val();
				}
				$.ajax({
					type: 'POST',
					url: "${pageContext.request.contextPath}/cargo/getOneCargo.do",
					data: {
						"cargoId": id
					},
					dataType: "json",
					success: function(data) {
						if(data.status == 200) {
							$("#myModalLabels").html("修改货物");
							$("#addCargoName").attr("readOnly",true); 
							$("#tPointBod").empty();
							$("#tSpecBod").empty();
							$("#addCargoId").val(data.data.cargoMainPointDetails.id);
							$("#addCargoName").val(data.data.cargoMainPointDetails.cargoName);
							$("#addCargoCode").val(data.data.cargoMainPointDetails.cargoCode);
							$("#addMainPoint").val(data.data.cargoMainPointDetails.pointName);
							$("#addOtherPoint").val(data.data.cargoMainPointDetails.pointMin + "%" + "~" + data.data.cargoMainPointDetails.pointMax + "%");
							var cargoPointDetailLists = data.data["cargoPointDetails"];
							$.each(cargoPointDetailLists, function(index, cargoPointDetails) {
								if(cargoPointDetails.type == 0) {
									$("#tPointBod").append("<tr class='newTr'><td><label class='demo--label'>\<input class='demo--checkbox moneyCheck' type='checkbox' checked ='checked' name='checkname' value='" + cargoPointDetails.type + "' >\<span class='demo--checkboxInput'></span>\</label></td><td id=" + (index++) + ">" + index + "</td><td title='双击可编辑'>" + cargoPointDetails.pointName + "</td><td title='双击可编辑' onkeyup='placeHtml(this)'>" + cargoPointDetails.pointMin + "~" + cargoPointDetails.pointMax + "</td>"
											+"<td style='text-align:center;'><button type=button style='padding: 8px; border: 1px solid #dde3ef;' onclick='$(this).parent().parent().remove()'>删除<button></td></tr>")
								} else {
									$("#tPointBod").append("<tr class='newTr'><td><label class='demo--label'>\<input class='demo--checkbox moneyCheck' type='checkbox'  name='checkname' value='" + cargoPointDetails.type + "'>\<span class='demo--checkboxInput'></span>\</label></td><td id=" + (index++) + ">" + index + "</td><td title='双击可编辑'>" + cargoPointDetails.pointName + "</td><td title='双击可编辑' onkeyup='placeHtml(this)'>" + cargoPointDetails.pointMin + "~" + cargoPointDetails.pointMax + "</td>"
									+"<td style='text-align:center;'><button type=button style='padding: 8px; border: 1px solid #dde3ef;' onclick='$(this).parent().parent().remove()'>删除<button></td></tr>")
								}
							});
							$("#addSerial").val(cargoPointDetailLists.length);
							var cargoSpecificteDetailLists = data.data["cargoSpecificteDetails"];
							$.each(cargoSpecificteDetailLists, function(index, cargoSpecificteDetails) {
								$("#tSpecBod").append("<tr class='newTr'><td id=" + (index++) + ">" + index + "</td><td title='双击可编辑'>" + cargoSpecificteDetails.name + "</td><td style='text-align:center;'><button type=button style='padding: 8px; border: 1px solid #dde3ef;' onclick='$(this).parent().parent().remove()'>删除<button></td></tr>")
							});
							$("#addCargoModal").modal();
						}
					},
				});
			}
		</script>
	</head>

	<body>
		<!--货物信息表单 -->
		<div class="form project_form container_top">
			<form class="form-inline maxWidth" id="searchForm">
				<div id="wrap">
					<div class="form-group">
						<label>货物品名：</label>
						<input name='cargoName' type="text" maxlength="30" />
					</div>
					<div class="form-group">
						<label>编号：</label>
						<input name='cargoCode' type="text" maxlength="30" />
					</div>
				</div>
				<div class="foot">
					<a class="btn btn-success" href="javascript:;"><em class="search"></em>
						<span onclick="search(this)">搜索</span>
					</a>
				</div>
			</form>
		</div>
		<!-- 货物信息表格 -->
		<div class="container_bottom tableDiv">
			<div class="tabbable" id="tabs-529262">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#panel1" data-toggle="tab">货物信息</a>
					</li>
				</ul>
				<div class="tab-content">
					<!--货物信息表格开始-->
					<div class="tab-pane active" id="panel1">

						<div class="domain">
							<!-- <div class="tableBg">
								<a href="javascript:void(0)" class="exportBtn" data-toggle="modal" data-target="#addCargo"><em class="add"></em><span>新增货物</span></a>
								<a href="javascript:void(0)" class="exportBtn" ><em class="bill"></em><span>修改</span></a>
								<a href="javascript:void(0)" class="exportBtn" onclick="deleteCargo()"><em class="del"></em><span>删除</span></a>
							</div> -->
							<div class="tableBg">
								<c:forEach items='${permissions }' var='permission' >
									<c:if test="${permission.code=='addCargo'}">
										<a href="javascript:void(0)" class="exportBtn add" id="addCargo"><span>新增货物</span></a>
									</c:if>
									<c:if test="${permission.code=='updateCargo'}">
										<a href="javascript:void(0)" class="exportBtn revise" id="modify" onclick="updCargo()"><span>修改</span></a>
									</c:if>
									<c:if test="${permission.code=='delCargo'}">
										<a href="javascript:void(0)" class="exportBtn del" id="delBtn"><span>删除</span></a>
									</c:if>
								</c:forEach>
							</div>
							<div class="table-responsive">
								<input id="operateId" name="operateId" type="text" style="display:none;" />
								<table class="table" id="operatorTable">
									<thead>
										<tr class="tableTop">
											<th><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox" disabled="disabled"   >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label> </th>
											<th>货物品名</th>
											<th>编号</th>
											<th>规格 </th>
											<th>主要指标</th>
											<th>指标界定</th>
										</tr>
									</thead>
									<tbody id="cargoBody">
										<c:forEach items="${tbCargoMainPoint.rows}" var="it">
											<tr id="${it.id}">
												<td><label class="demo--label">
												     <input class="demo--checkbox" type="checkbox"  onclick="sendId(this)"  value="${it.id}">
	                                                 <span class="demo--checkboxInput"></span>
		                                        </label></td>
												<td>${it.cargoName}</td>
												<td>${it.cargoCode}</td>
												<td>
													<c:forEach items="${it.listName}" var="spec">
														${spec.name}&nbsp;&nbsp;
													</c:forEach>
												</td>
												<td>${it.pointName}</td>
												<td>${it.pointMin}%~${it.pointMax}%</td>
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

		<!--货物详情模态框-->
		<div class="modal fade detailModal" id="detailModal" tabindex="-1" role="dialog" data-backdrop="static">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">货物详情</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="detailId">
							<h5>货物信息</h5>
							<ul class="clearfix">
								<li>
									<label for="">货物品名:</label>
									<span id="cargoMapName"></span>
								</li>
								<li>
									<label for="">编号:</label>
									<span id="cargoMapCode"></span>
								</li>
								<li>
									<label for="">主要指标:</label>
									<span id="cargoMapMainPointName"></span>
								</li>
								<li>
									<label for="">界定指标:</label>
									<span id="cargoMapMainPoint"></span>
								</li>
							</ul>
							<hr>
							<h5>其它信息</h5>
							<ul class="clearfix">
								<li>
									<dl>
										<dt><label >化验指标:</label></dt>
										<span id="pointIdList"></span>
									</dl>
								</li>
								<li>
									<dl>
										<dt><label >规格:</label></dt>
										<span id="specificId"></span>
									</dl>
								</li>
							</ul>
						</form>
					</div>
				</div>
			</div>
		</div>

		<!-- 新增货物模态框 -->
		<div class="modal fade addCargo" id="addCargoModal" tabindex="-1" role="dialog" data-backdrop="static">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabels">新增货物</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
					<h5>货物信息<i class='requireds'>*</i></h5>
						<form class="form-inline" id="addForm">
							<input id="addCargoId" name="id" type="text" style="display:none;" />
							<input id="addSerial" name="addSerial" type="text" style="display:none;" />
							<div>
								<div class="form-inline">
									<div class="form-group">
										<label>货物品名：</label>
										<input type="text" id="addCargoName" name="cargoName" maxlength="30" />
									</div>
									<div class="form-group">
										<label>&emsp;&emsp;编号：</label>
										<input type="text" id="addCargoCode" name="cargoCode" maxlength="30" />
									</div>
									<div class="form-group">
										<label>主要指标：</label>
										<input type="text" id="addMainPoint" name="addMainPoint" maxlength="30" unselectable="on" readonly class="inputbg" />
									</div>
									<div class="form-group">
										<label>界定指标：</label>
										<input type="text" id="addOtherPoint" name="addOtherPoint" maxlength="30" unselectable="on" readonly class="inputbg" />
									</div>
								</div>
							</div>
							<div class="clearfix">
								<input id="pointJsonIds" type="hidden" name="pointJsonIds" />
								<label style="float: left;">化验指标：</label>
								<div class="table-responsive">
									<table class="table loadingTable" id="pointTable">
										<thead>
											<tr class="tableTop">
												<th></th>
												<th width="100">序号</th>
												<th>指标名称</th>
												<th>指标</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="tPointBod">
										</tbody>
									</table>
									<div class="moreAdd" id="addSerials"><img src="${pageContext.request.contextPath}/img/more.png" />添加更多指标信息</div>
								</div>
							</div>
							<div class="clearfix">
								<input id="specJsonIds" type="hidden" name="specJsonIds" />
								<label style="float: left;">货物规格：</label>
								<div class="table-responsive">
									<table class="table loadingTable" id="secpiTable">
										<thead>
											<tr class="tableTop">
												<th width="100px">序号</th>
												<th>规格 </th>
												<th>操作 </th>
											</tr>
										</thead>
										<tbody id="tSpecBod">
										</tbody>
									</table>
									<div class="moreAdds"><img src="${pageContext.request.contextPath}/img/more.png" />添加更多规格</div>
								</div>
							</div>
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
									<button type="button" class="btn sureBtn" onclick="add()">确定</button>
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
						<div class="body-content">
							<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
							<span id="pointMsg"></span>
						</div>
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
					<div class="modal-body" style="text-align: center;">
						<span id="errorPointMsg"></span>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn sureBtn" onclick="closeNofresh()">确定</button>
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
									<button type="button" class="btn sureBtn" onclick="deleteCargo()">确定</button>
									<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<!--分页-->
		<script>
			$("#page").paging({
				pageNo: 1,
				totalPage: ${tbCargoMainPoint.totalPage},
				totalSize: ${tbCargoMainPoint.limit},
				callback: function(num) {
					searchByPage(num);
				}
			})
		</script>

		<!--执行分页查询的方法 -->
		<script>
			function searchByPage(num) {
				$.ajax({
					type: 'post',
					url: '${pageContext.request.contextPath}/cargo/listCargoByPage.do',
					data: {
						page: num,
						search: JSON.stringify($('#searchForm').serializeJSON())
					},
					cache: false,
					dataType: 'json',
					success: function(data) {
						if(data.status == 200) {
							htmlTable(data.data.rows);
						}
					}
				})
			}
		</script>

		<!--将分页返回结果写入表格-->
		<script>
			function htmlTable(results) {
				var tbCargoMainPoint = results;
				$('#cargoBody').html("");
				$.each(tbCargoMainPoint, function(index, cargo) {
					$('#cargoBody').append("<tr id='" + cargo.id + "'>" +
						"<td style='text-align: center;'><label class='demo--label'>" +
						" <input class='demo--checkbox'  value='" + cargo.id + "' type='checkbox'   >" +
						"<span class='demo--checkboxInput'></span>" +
						"</label></td>" +
						"<td id=''>" + cargo.cargoName + "</td>" +
						"<td id=''>" + cargo.cargoCode + "</td>" +
						"<td name='resaon" + index + "'></td>" +
						"<td id=''>" + cargo.pointName + "</td>" +
						"<td id=''>" + cargo.pointMin + "%~" + cargo.pointMax + "%</td>" +
						"</tr>");
					$("td[name='resaon" + index + "']").html("");
					$.each(cargo.listName, function(indexs, it) {
						$("td[name='resaon" + index + "']").append("" + it.name + "&nbsp;&nbsp");
					});
				});
			}
		</script>

</html>