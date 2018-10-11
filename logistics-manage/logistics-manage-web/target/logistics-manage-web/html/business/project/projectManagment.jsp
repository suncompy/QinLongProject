<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="UTF-8">
		<title>项目管理</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/public.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/business/project/project.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/business/project/project.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.validate.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.serializejson.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js" ></script>
		<link href="${pageContext.request.contextPath}/css/page/page.css" rel="stylesheet"/>
	</head>
	<!--搜索-->
	<script>
		function search(param){
			var status = $('#searchForm input[name=status]').val();
			var page = 'projectPage';
			if(status==1){
				page = 'projectPage';
			}else if(status==2){
				page = 'historyPage';
			}else{
				page = 'recyclePage';
			}
			$.ajax({
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/projectManagment/getProjectPage.do',  
		       	data:{page:1,status:status,search:JSON.stringify($('#searchForm').serializeJSON())},
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
	
	<body class="projectManagment">
		<input type="text"  id="mtext" hidden="hidden"/>
		<!-- 项目管理表单 -->
		<div class="form project_form container_top">
			<form class="form-inline maxWidth" id="searchForm">
				<div id="wrap">
					<div class="form-group">
						<label>项目编号：</label>
						<input name='projectCode' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>分支机构：</label>
						<select name="branchGroupId">
							<option></option>
							<c:forEach items="${branchGroups}" var="branchGroup">
								<option value="${branchGroup.id}">${branchGroup.name}</option>	
							</c:forEach>
						</select>
					</div>
					<input name="status" type="hidden"  value="1"/>
					<div class="form-group">
						<label>项目类型：</label>
						<select name="projectType">
							<option></option>
							<option value="0">集装箱</option>
							<option value="1">散装</option>
						</select>
					</div>
					<div class="form-group">
						<label>联运模式：</label>
						<select name="transportType">
							<option></option>
							<option value="0" >汽运</option>
							<option value="1" >接取</option>
							<option value="2" >送达</option>
							<option value="3" >火运</option>
							<option value="4" >接取+火运</option>
							<option value="5" >火运+送达</option>
							<option value="7" >接取+送达</option>
							<option value="6" >联运</option>
						</select>
					</div>
					<div class="form-group">
						<label>货物品名：</label>
						<select name="cargoId">
							<option></option>
							<c:forEach items="${cargos}" var="cargo">
								<option value="${cargo.id}">${cargo.cargoName}</option>	
							</c:forEach>
						</select>
					</div>
					<div class="form-group dataTimes">
						<label>&emsp;日期从：</label>
						<input name="beginDate" id="d244" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy.M.d'})" />
						<i class="fa  fa-calendar-check-o " id="calendar2"></i>
					</div>
					<div class="form-group dataTimes secTime">
						<label>至</label>
						<input name="endDate" id="d244" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy.M.d'})" />
						<i class="fa  fa-calendar-check-o " id="calendar2"></i>
					</div>
					<div class="form-group">
						<label>发货企业：</label>
						<input name='sendCargoCompanyName' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>收货企业：</label>
						<input name='receiveCargoCompanyName' type="text" maxlength="30"/>
					</div>

				</div>
				<div class="foot">
					<a class="btn btn-success" href="javascript:;"><em class="search"></em>
						<span onclick="search(this)">搜索</span>
					</a>
					<div id="lookMore"></div>
				</div>
			</form>
		</div>
		<!-- 项目管理表格 -->
		<div class=" container_bottom tableDiv">
			<div class="tabbable" id="tabs-529262">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#panel1" data-toggle="tab" onclick="listProject(1)">项目列表</a>
					</li>
					<li>
						<a href="#panel2" data-toggle="tab" onclick="listProject(2)">历史项目</a>
					</li>
					<li>
						<a href="#panel3" data-toggle="tab" onclick="listProject(0)">项目回收站</a>
					</li>
				</ul>

				<div class="tab-content">
					<!--项目列表表格开始-->
					<div class="tab-pane active" id="panel1">

						<div class="domain" >
							<div class="tableBg">
								
									<a href="#" class="exportBtn add" onclick="return false;" id="creatProject"><span>新建</span></a>
								
									<a href="#" class="exportBtn revise" onclick="return false;" id="modify"><span>修改</span></a>
								
									<a href="#" class="exportBtn complete" onclick="return false;" id="complete"><span>完成</span></a>
								
									<a href="#" class="exportBtn export" onclick="return false;" id="export1"><span>导出</span></a>
								
									<a href="#" class="exportBtn del"  onclick="return false;" id="delBtn"><span>删除</span></a>
								
							</div>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th>
												<label class="demo--label">
								            <input class="demo--checkbox" type="checkbox"  >
							                <span class="demo--checkboxInput"></span>
							                </label>
											</th>
											<th>项目编号</th>
											<th>项目类型</th>
											<th>联运模式</th>
											<th>分支机构</th>
											<th>发货企业</th>
											<th>收货企业</th>
											<th>货物品名</th>
											<th>短驳承运方式</th>
											<th>短驳承运方</th>
											<th>计价单位</th>
											<th>备注信息</th>
										</tr>
									</thead>
									<tbody id="projectTbody">
										<input type="hidden" id="delProject" />
										<c:forEach items="${projectList.rows}" var="project">
											<tr id="${project.id}">
												<td>
												<label class="demo--label">
								            	<input class="demo--checkbox" type="checkbox" value="${project.id}" name="WaybillName">
							                  <span class="demo--checkboxInput"></span>
							                   </label>
												</td>
												<td>
													<a href="#">${project.projectCode}</a>
												</td>
												<c:if  test="${project.projectType==0}">
														<td>集装箱</td>
												</c:if>
												<c:if  test="${project.projectType==1}">
														<td>散装</td>
												</c:if>
												
												<c:if  test="${project.transportType==0}">
														<td>汽运</td>
												</c:if>
												<c:if  test="${project.transportType==1}">
														<td>接取</td>
												</c:if>
												<c:if  test="${project.transportType==2}">
														<td>送达</td>
												</c:if>
												<c:if  test="${project.transportType==3}">
														<td>火运</td>
												</c:if>
												<c:if  test="${project.transportType==4}">
														<td>接取+火运</td>
												</c:if>
												<c:if  test="${project.transportType==5}">
														<td>火运+送达</td>
												</c:if>
												<c:if  test="${project.transportType==6}">
														<td>联运</td>
												</c:if>
												<c:if  test="${project.transportType==7}">
														<td>接取+送达</td>
												</c:if>
												<td>${project.branchGroupName}</td>
												<td>${project.sendCargoCompanyName}</td>
												<td>${project.receiveCargoCompanyName}</td>
												<td>${project.cargoName}</td>
												<td>
												<c:if  test="${project.shortBargeCarrierMode==0}">
														平台;
												</c:if>
												<c:if  test="${project.shortBargeCarrierMode==1}">
														自选;
												</c:if>
												<c:if  test="${project.sendShortBargeCarrierMode==0}">
														平台
												</c:if>
												<c:if  test="${project.sendShortBargeCarrierMode==1}">
														自选
												</c:if>
												</td>
												
												<td>${project.shortBargeCarrierName}</td>
												<c:if  test="${project.valuationUnitName==0}">
														<td>件</td>
												</c:if>
												<c:if  test="${project.valuationUnitName==1}">
														<td>吨</td>
												</c:if>
												<td>${project.remark}</td>
											</tr>
										</c:forEach>

									</tbody>
								</table>
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-md-12 column paging page_div" id="projectPage">
							</div>
						</div>
					</div>
					<!--项目列表表格结束-->
					<!--历史项目表格开始-->
					<div class="tab-pane" id="panel2">
						<div class="domain">
							<div class="tableBg">
								
								<a href="#" class="exportBtn rest" id='recoverBtn'><span>还原</span></a>
								<!--<a href="#" class="exportBtn look"><span>查看</span></a>-->
								<a href="#" class="exportBtn export"><span>导出</span></a>
							</div>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th> <label class="demo--label">
								            <input class="demo--checkbox" type="checkbox"  >
							                  <span class="demo--checkboxInput"></span>
							                         	</label></th>
											<th>项目编号</th>
											<th>项目类型</th>
											<th>联运模式</th>
											<th>分支机构</th>
											<th>发货企业</th>
											<th>收货企业</th>
											<th>货物品名</th>
											<th>短驳承运方式</th>
											<th>短驳承运方</th>
											<th>计价单位</th>
											<th>备注信息</th>
											<th>完成时间</th>
										</tr>
									</thead>
									<tbody id="historyProjectTbody">
									<input type="hidden" id="restoreHistoryProjectId" />
									</tbody>
								</table>
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-md-12 column paging page_div" id="historyPage">
							</div>
						</div>
					</div>
					<!-- 历史项目结束 -->
					<!--项目回收站开始-->
					<div class="tab-pane" id="panel3">
						<div class="domain">
							<div class="tableBg">
								<a href="#" class="exportBtn rest" onclick="return false;"><span>还原</span></a>
								<a href="#" class="exportBtn export" onclick="return false;"><span>导出</span></a>
							</div>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th> <label class="demo--label">
								            <input class="demo--checkbox" type="checkbox"  >
							                  <span class="demo--checkboxInput"></span>
							                         	</label></th>
											<th>项目编号</th>
											<th>项目类型</th>
											<th>联运模式</th>
											<th>分支机构</th>
											<th>发货企业</th>
											<th>收货企业</th>
											<th>货物品名</th>
											<th>短驳承运方式</th>
											<th>短驳承运方</th>
											<th>计价单位</th>
											<th>备注信息</th>
											<th>删除人</th>
											<th>删除时间</th>
											<th>删除原因</th>
										</tr>
									</thead>
									<tbody id="recycleTbody">
									<input type="hidden" id="restoreRecycleWayBillId" />
									</tbody>
								</table>
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-md-12 column paging page_div" id="recyclePage">
							</div>
						</div>
					</div>
					<!-- 项目回收站结束 -->
				</div>
			</div>
		</div>
		<!-- 导出模态框 -->
		<div class="modal fade exportModal" tabindex="-1" role="dialog" id="exportModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="body-content">
							<i class="fa fa-question-circle" aria-hidden="true"></i>
							<span>选择了<b id="">13</b>条记录，确认导出全部数据？</span>
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
		<!-- 失败提醒模态框 -->
		<div style="z-index: 9999;" class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="deletnullModal" data-backdrop="static">
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
		<!-- 提醒模态框 -->
		<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="deltialModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="body-content">
							<div><span>您已选择</span><b class="errnums">1</b> <span id="msgSpan"></span></div>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="restProject()">确定</button>
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
			<input id="restId" type="hidden" />
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
							<span id="warnMsg">抱歉，不可批量删除</span>
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
							<span>你是否选择删除此数据，删除后不可还原</span>
						</div>
						<div class="delete_reason">
							<h4>删除原因</h4>
							<textarea class="form-control textareas" id="reason" rows="3"></textarea>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="delProject()">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
					    </form>
					</div>
				</div>
			</div>
		</div>

		<!--汽运项目详情修改模态框-->
		<div class="modal fade  detailModal modifyModal" tabindex="-1" role="dialog" data-backdrop="static" id="truckModal">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content  ">
					<div class="modal-header">
						<h4 class="modal-title">项目详情</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<input type="hidden" name='id'>
					<div class="modal-body">
						<!-- 项目信息 -->
						<div class="project_info">
							<h5>项目信息</h5>
							<ul class="clearfix">
								<li>
									<label>分支机构：</label>
									<span name="branchGroupName"></span>
								</li>
								<li>
									<label>项目类型：</label>
									<span name="projectType"></span>
								</li>
								<li>
									<label>联运模式：</label>
									<span name="transportType"></span>
								</li>
								<li>
									<label>委托方：</label>
									<span name="principal" ></span>
								</li>
								<li class="history_detail">
									<label>完成时间：</label>
									<span name="finishDate" ></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 货物信息 -->
						<div class="cargo_info">
							<h5>货物信息</h5>
							<ul class="clearfix">
								<li>
									<label>货物品名：</label>
									<span name="cargoName" ></span>
								</li>
								<li>
									<label>货物规格：</label>
									<span name="cargoSpecifications" ></span>
								</li>
								<li>
									<label>货物单价：</label>
									<span name="cargoPrice" ></span>
								</li>
								<li>
									<label>计价单位：</label>
									<span name="valuationUnitName"></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 收发货企业 -->
						<div class="detail_info cut_apart clearfix">
							<!--   发货企业  -->
							<div class="half splitters">
								<h5>发货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="sendCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="sendCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="sendCargoCompanyAddress"></span>
								</div>

							</div>
							<!--   收货企业    -->
							<div class="half">
								<h5>收货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="receiveCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="receiveCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="receiveCargoCompanyAddress"></span>
								</div>

							</div>
						</div>
						<hr>
						<!-- 汽运短驳  -->
						<div class="receve_truck cut_apart clearfix">
							<h5>汽运短驳</h5>
							<div class="half splitters">

									<ul class="clearfix pad">
									<li class="long">
										<label>发货单位：</label>
										<span name="sendCargoUnitName"></span>
									</li>
									<li>
										<label>&emsp;取货地：</label>
										<span name="sendCargoAreaCode"></span>
									</li>
								</ul>
								<div class="pad">
									<label>取货地址：</label>
									<span name="sendCargoAddress"></span>

								</div>

							</div>
							<div class="half">

								<ul class="clearfix">
									<li class="long">
										<label>收货单位：</label>
										<span name="receivingDepartmentName"></span>
									</li>
									<li>
										<label>运抵地：</label>
										<span name="receivingDepartmentAreaCode"></span>
									</li>
								</ul>
								<div>
									<label>运抵地址：</label>
									<span name="receivingDepartmentAddress"></span>
								</div>

							</div>
							<!--   短驳承运方  -->
							<div class="whole">
								<p>短驳承运方</p>
								<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
								<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
								<form class="form-inline ">
									<div class="form-group ">
										<label>短驳承运方式：</label>
										<select name="shortBargeCarrierMode1"  >
											<option value="0">平台</option>
											<option value="1">自选</option>
										</select>
									</div>
								</form>
								<div name="shortBargeCarrierName1">
								</div>
							</div>

						</div>
						<hr>
						<!-- 委托方费用 -->
						<div class="client_cost whole">
							<h5>委托方费用</h5>
							<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
							<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
							<form class="form-inline pad">
								<div class="form-group"><label>汽运单价：</label><input type="text" name="truckTransportPrice" maxlength="30" readonly="readonly"><span>元</span></div>
							</form>
						</div>
						<hr>
						<!-- 备注信息 -->
						<div class="remarks_info">
							<h5>备注信息</h5>
							<div name="remark" ></div>
						</div>
						<hr>
						<!-- 删除信息 -->
						<div class="del-info">
							<h5>删除信息</h5>
							<ul class="clearfix pad">
								<li>
									<label>删除人：</label>
									<span name='delUser'></span>
								</li>
								<li>
									<label>删除时间：</label>
									<span name='delDate'></span>
								</li>
							</ul>
							<div class="pad">
								<label>删除原因：</label>
								<span name='delReason' ></span>
							</div>
							<hr>
						</div>
						<!-- 历史修改记录 -->
						<div class="history_modify">
							<h5>历史修改记录</h5>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方:合肥深合软件有限公司的“运输单价”为1000元，原“运输单价”为2000元</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改送达短驳承运方:合肥深合软件有限公司的“扣损比率”为1‰，原“扣损比率”为21‰</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方为“合肥深合软件有限公司”，原承运方为“新疆秦龙矿业有限公司”</div>
						</div>
						
						<!--按钮-->
						<div class="row clearfix hidebtn">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="updateProject(0,'truckModal')">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>

		<!--接取项目详情和修改模态框-->
		<div class="modal fade  detailModal modifyModal" tabindex="-1" role="dialog" data-backdrop="static" id="receveModal">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content  ">
					<div class="modal-header">
						<h4 class="modal-title">项目详情（接取）</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<input type="hidden" name='id'>
					<div class="modal-body">
						<!-- 项目信息 -->
						<!-- 项目信息 -->
						<div class="project_info">
							<h5>项目信息</h5>
							<ul class="clearfix">
								<li>
									<label>分支机构：</label>
									<span name="branchGroupName"></span>
								</li>
								<li>
									<label>项目类型：</label>
									<span name="projectType"></span>
								</li>
								<li>
									<label>联运模式：</label>
									<span name="transportType"></span>
								</li>
								<li>
									<label>委托方：</label>
									<span name="principal" ></span>
								</li>
								<li class="history_detail">
									<label>完成时间：</label>
									<span name="finishDate" ></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 货物信息 -->
						<div class="cargo_info">
							<h5>货物信息</h5>
							<ul class="clearfix">
								<li>
									<label>货物品名：</label>
									<span name="cargoName" ></span>
								</li>
								<li>
									<label>货物规格：</label>
									<span name="cargoSpecifications" ></span>
								</li>
								<li>
									<label>货物单价：</label>
									<span name="cargoPrice" ></span>
								</li>
								<li>
									<label>计价单位：</label>
									<span name="valuationUnitName"></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 收发货企业 -->
						<div class="detail_info cut_apart clearfix">
							<!--   发货企业  -->
							<div class="half splitters">
								<h5>发货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="sendCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="sendCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="sendCargoCompanyAddress"></span>
								</div>

							</div>
							<!--   收货企业    -->
							<div class="half">
								<h5>收货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="receiveCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="receiveCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="receiveCargoCompanyAddress"></span>
								</div>
							</div>
						</div>
						<hr>
						<!-- 接取短驳  -->
						<!-- 接取短驳  -->
						<div class="receve_truck cut_apart clearfix">
							<h5>接取短驳</h5>
							<div class="half splitters">
								<ul class="clearfix pad">
									<li class="long">
										<label>发货单位：</label>
										<span id="" name="sendCargoUnitName"></span>
									</li>
									<li>
										<label>&emsp;取货地：</label>
										<span id="" name="sendCargoAreaCode"></span>
									</li>
								</ul>
								<div class="pad">
									<label>取货地址：</label>
									<span id="" name="sendCargoAddress" ></span>
								</div>
							</div>
							<div class="half">
								<ul class="clearfix pad">
									<li class="long">
										<label>收货站点：</label>
										<span name="receiveCargoSite"></span>
									</li>
									<li>
										<label>&emsp;运抵地：</label>
										<span name="receiveCargoSiteAreaCode"></span>
									</li>
								</ul>
								<div class="pad">
									<label>运抵地址：</label>
										<span name="receiveCargoSiteAddress"></span>
								</div>
							</div>
							<!--   短驳承运方  -->
							<div class="whole modify-whole">
								<p>短驳承运方</p>
								<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
								<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
								<form class="form-inline ">
									<div class="form-group ">
										<label>短驳承运方式：</label>
										<select name="shortBargeCarrierMode1">
											<option value="0">平台</option>
											<option value="1">自选</option>
										</select>
									</div>
								</form>
								<div name='shortBargeCarrierName1'></div>
							</div>
						</div>	
						<hr>
						<!-- 委托方费用 -->
						<div class="client_cost whole modify-whole">
							<h5>委托方费用</h5>
							<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
							<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
							<form class="form-inline pad">
								<div class="form-group"><label>接取单价：</label><input type="text" name='recevePickUpPrice' maxlength="30" readonly="readonly"><span>元</span></div>
							</form>
						</div>
						<hr>
						<!-- 备注信息 -->
						<div class="remarks_info">
							<h5>备注信息</h5>
							<div class="pad" name='receveRemark'></div>
						</div>
						<hr>
						<!-- 删除信息 -->
						<div class="del-info">
							<h5>删除信息</h5>
							<ul class="clearfix pad">
								<li>
									<label>删除人：</label>
									<span name='delUser'></span>
								</li>
								<li>
									<label>删除时间：</label>
									<span name='delDate'></span>
								</li>
							</ul>
							<div class="pad">
								<label>删除原因：</label>
								<span name='delReason' ></span>
							</div>
							<hr>
						</div>
							<!-- 历史修改记录 -->
						<div class="history_modify">
							<h5>历史修改记录</h5>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方:合肥深合软件有限公司的“运输单价”为1000元，原“运输单价”为2000元</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改送达短驳承运方:合肥深合软件有限公司的“扣损比率”为1‰，原“扣损比率”为21‰</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方为“合肥深合软件有限公司”，原承运方为“新疆秦龙矿业有限公司”</div>
						</div>
						<!--按钮-->
						<div class="row clearfix hidebtn">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick='updateProject(1,"receveModal")'>确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>

		<!--送达项目详情和修改模态框-->
		<div class="modal fade  detailModal modifyModal" tabindex="-1" role="dialog" data-backdrop="static" id="sendModal">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content  ">
					<div class="modal-header">
						<h4 class="modal-title">项目详情（送达）</h4>
						<input type="hidden" name='id'>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<!-- 项目信息 -->
						<!-- 项目信息 -->
						<div class="project_info">
							<h5>项目信息</h5>
							<ul class="clearfix">
								<li>
									<label>分支机构：</label>
									<span name="branchGroupName"></span>
								</li>
								<li>
									<label>项目类型：</label>
									<span name="projectType"></span>
								</li>
								<li>
									<label>联运模式：</label>
									<span name="transportType"></span>
								</li>
								<li>
									<label>委托方：</label>
									<span name="principal" ></span>
								</li>
								<li class="history_detail">
									<label>完成时间：</label>
									<span name="finishDate" ></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 货物信息 -->
						<div class="cargo_info">
							<h5>货物信息</h5>
							<ul class="clearfix">
								<li>
									<label>货物品名：</label>
									<span name="cargoName" ></span>
								</li>
								<li>
									<label>货物规格：</label>
									<span name="cargoSpecifications" ></span>
								</li>
								<li>
									<label>货物单价：</label>
									<span name="cargoPrice" ></span>
								</li>
								<li>
									<label>计价单位：</label>
									<span name="valuationUnitName"></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 收发货企业 -->
						<div class="detail_info cut_apart clearfix">
							<!--   发货企业  -->
							<div class="half splitters">
								<h5>发货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="sendCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="sendCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="sendCargoCompanyAddress"></span>
								</div>

							</div>
							<!--   收货企业    -->
							<div class="half">
								<h5>收货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="receiveCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="receiveCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="receiveCargoCompanyAddress"></span>
								</div>
							</div>
						</div>
						<hr>
						<!-- 送达短驳 -->
						<div class="send_truck cut_apart clearfix">
							<h5>送达短驳</h5>
							<div class="half splitters">

								<ul class="clearfix">
									<li class="long">
										<label>接取站点：</label>
										<span name="forwardingSiteName"></span>
									</li>
									<li>
										<label>取货地：</label>
										<span name="forwardingSiteAreaCode"></span>
									</li>
								</ul>
								<div>
									<label>取货地址：</label>
									<span name="forwardingSiteAddress"></span>
								</div>

							</div>
							<div class="half">
								<ul class="clearfix">
									<li class="long">
										<label>收货单位：</label>
										<span name="receivingDepartmentName"></span>
									</li>
									<li>
										<label>运抵地：</label>
										<span name="receivingDepartmentAreaCode"></span>
									</li>
								</ul>
								<div>
									<label>运抵地址：</label>
									<span name="receivingDepartmentAddress"></span>
								</div>
							</div>

							<!--   短驳承运方  -->
							<div class="whole modify-whole">
								<p>短驳承运方</p>
								<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
								<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
								<form class="form-inline ">
									<div class="form-group ">
										<label>短驳承运方式：</label>
										<select name="shortBargeCarrierMode2">
											<option value="0">平台</option>
											<option value="1">自选</option>
										</select>
									</div>
								</form>
								<div name='shortBargeCarrierName2'></div>
							</div>
						</div>
						<hr>
						<!-- 委托方费用 -->
						<div class="client_cost whole modify-whole">
							<h5>委托方费用</h5>
							<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
							<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
							<form class="form-inline pad">
								<div class="form-group"><label>送达单价：</label><input type="text" name='sendArrivePrice' maxlength="30" readonly="readonly"><span>元</span></div>
							</form>
						</div>
						<hr>
						<!-- 备注信息 -->
						<div class="remarks_info">
							<h5>备注信息</h5>
							<div name='sendRemark'></div>
						</div>
						<hr>
						<!-- 删除信息 -->
						<div class="del-info">
							<h5>删除信息</h5>
							<ul class="clearfix pad">
								<li>
									<label>删除人：</label>
									<span name='delUser'></span>
								</li>
								<li>
									<label>删除时间：</label>
									<span name='delDate'></span>
								</li>
							</ul>
							<div class="pad">
								<label>删除原因：</label>
								<span name='delReason' ></span>
							</div>
							<hr>
						</div>
							<!-- 历史修改记录 -->
						<div class="history_modify">
							<h5>历史修改记录</h5>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方:合肥深合软件有限公司的“运输单价”为1000元，原“运输单价”为2000元</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改送达短驳承运方:合肥深合软件有限公司的“扣损比率”为1‰，原“扣损比率”为21‰</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方为“合肥深合软件有限公司”，原承运方为“新疆秦龙矿业有限公司”</div>
						</div>
						<!--按钮-->
						<div class="row clearfix hidebtn">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="updateProject(2,'sendModal')">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!--火运项目详情模态框-->
		<div class="modal fade  detailModal modifyModal" tabindex="-1" role="dialog" data-backdrop="static" id="trainModal">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content  ">
					<div class="modal-header">
						<h4 class="modal-title">项目详情（火运）</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<!-- 项目信息 -->
						<!-- 项目信息 -->
						<div class="project_info">
							<h5>项目信息</h5>
							<ul class="clearfix">
								<li>
									<label>分支机构：</label>
									<span name="branchGroupName"></span>
								</li>
								<li>
									<label>项目类型：</label>
									<span name="projectType"></span>
								</li>
								<li>
									<label>联运模式：</label>
									<span name="transportType"></span>
								</li>
								<li>
									<label>委托方：</label>
									<span name="principal" ></span>
								</li>
								<li class="history_detail">
									<label>完成时间：</label>
									<span name="finishDate" ></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 货物信息 -->
						<div class="cargo_info">
							<h5>货物信息</h5>
							<ul class="clearfix">
								<li>
									<label>货物品名：</label>
									<span name="cargoName" ></span>
								</li>
								<li>
									<label>货物规格：</label>
									<span name="cargoSpecifications" ></span>
								</li>
								<li>
									<label>货物单价：</label>
									<span name="cargoPrice" ></span>
								</li>
								<li>
									<label>计价单位：</label>
									<span name="valuationUnitName"></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 收发货企业 -->
						<div class="detail_info cut_apart clearfix">
							<!--   发货企业  -->
							<div class="half splitters">
								<h5>发货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="sendCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="sendCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="sendCargoCompanyAddress"></span>
								</div>

							</div>
							<!--   收货企业    -->
							<div class="half">
								<h5>收货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="receiveCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="receiveCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="receiveCargoCompanyAddress"></span>
								</div>
							</div>
						</div>
						<hr>
						<!-- 火运干线  -->
						<div class="train_line cut_apart clearfix">
							<h5>火运干线</h5>
							<!-- 起运中心站 -->
							<div class="half splitters">
								<ul class="clearfix">
									<li class="long">
										<label>起运中心站：</label>
										<span name='beginCenterSiteName'></span>
									</li>
									<li>
										<label>始发站点：</label>
										<span name='beginSiteName'></span>
									</li>
								</ul>
								<div>
									<label>始发地：</label>
									<span name='beginAddress'></span>
								</div>
							</div>
							<!-- 到达中心站 -->
							<div class="half ">
								<ul class="clearfix">
									<li class="long">
										<label>到达中心站：</label>
											<span name='endCenterSiteName'></span>
									</li>
									<li>
										<label>到达站点：</label>
									<span name='endSiteName'></span>
									</li>
								</ul>
								<div>
									<label>到达地：</label>
									<span name='endAddress'></span>
								</div>
							</div>
								<!--  干线费用   -->
							<!--修改div-->
							<div class="whole modify-whole">
								<p>干线费用</p>
								<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
								<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
								<form class="form-inline" name="trainCost">
								<input type="hidden" name="id" />
									<div class="form-group">
										<label>&emsp;运费核计：</label>
										<input type="text" maxLength="10"  name='freight' ><span>元</span>
									</div>
									<div class="form-group">
										<label>加固材料费：</label>
										<input type="text" maxLength="10" name='materialCost'><span>元</span>
									</div>
									<div class="form-group">
										<label>篷布使用费：</label>
										<input type="text" maxLength="10" name='tarpaulinCost'><span>元</span>
									</div>
									<div class="form-group">
										<label>发站装卸费：</label>
										<input type="text" maxLength="10" name='beginStevedoringCost'><span>元</span>
									</div>
									<div class="form-group">
										<label>到站装卸费：</label>
										<input type="text" maxLength="10" name='endStevedoringCost' ><span>元</span>
									</div>
									<div class="form-group">
										<label>&emsp;运费合计：</label>
										<input type="text" name="freightSum" class="inputbg" readonly="readonly" maxLength="30"><span>元</span>
									</div>
									<input type="hidden" name="trainPrice" />
									<input type="hidden" name="transportType" value="3"/>
								</form>
							</div>
						</div>
						<hr>
						<div class="client_cost whole modify-whole">
							<h5>委托方费用</h5>
							<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
							<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
							<form class="form-inline pad">
								<div class="form-group"><label>火运单价：</label><input type="text" name='trainTrainPrice' maxlength="30" readonly="readonly"><span>元</span></div>
							</form>
						</div>
						<hr>
						<!-- 备注信息 -->
						<div class="remarks_info">
							<h5>备注信息</h5>
							<div name='trainRemark'></div>
						</div>
						<hr>
						<!-- 删除信息 -->
						<div class="del-info">
							<h5>删除信息</h5>
							<ul class="clearfix pad">
								<li>
									<label>删除人：</label>
									<span name='delUser'></span>
								</li>
								<li>
									<label>删除时间：</label>
									<span name='delDate'></span>
								</li>
							</ul>
							<div class="pad">
								<label>删除原因：</label>
								<span name='delReason' ></span>
							</div>
							<hr>
						</div>
							<!-- 历史修改记录 -->
						<div class="history_modify">
							<h5>历史修改记录</h5>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方:合肥深合软件有限公司的“运输单价”为1000元，原“运输单价”为2000元</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改送达短驳承运方:合肥深合软件有限公司的“扣损比率”为1‰，原“扣损比率”为21‰</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方为“合肥深合软件有限公司”，原承运方为“新疆秦龙矿业有限公司”</div>
						</div>
						<!--按钮-->
						<div class="row clearfix hidebtn">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="updateProject(3,'trainModal')">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!--接取+火运项目详情模态框-->
		<div class="modal fade  detailModal modifyModal" tabindex="-1" role="dialog" data-backdrop="static" id="receveAndTrainModal">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content  ">
					<div class="modal-header">
						<h4 class="modal-title">项目详情（接取+火运）</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
							<!-- 项目信息 -->
						<!-- 项目信息 -->
						<div class="project_info">
							<h5>项目信息</h5>
							<ul class="clearfix">
								<li>
									<label>分支机构：</label>
									<span name="branchGroupName"></span>
								</li>
								<li>
									<label>项目类型：</label>
									<span name="projectType"></span>
								</li>
								<li>
									<label>联运模式：</label>
									<span name="transportType"></span>
								</li>
								<li>
									<label>委托方：</label>
									<span name="principal" ></span>
								</li>
								<li class="history_detail">
									<label>完成时间：</label>
									<span name="finishDate" ></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 货物信息 -->
						<div class="cargo_info">
							<h5>货物信息</h5>
							<ul class="clearfix">
								<li>
									<label>货物品名：</label>
									<span name="cargoName" ></span>
								</li>
								<li>
									<label>货物规格：</label>
									<span name="cargoSpecifications" ></span>
								</li>
								<li>
									<label>货物单价：</label>
									<span name="cargoPrice" ></span>
								</li>
								<li>
									<label>计价单位：</label>
									<span name="valuationUnitName"></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 收发货企业 -->
						<div class="detail_info cut_apart clearfix">
							<!--   发货企业  -->
							<div class="half splitters">
								<h5>发货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="sendCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="sendCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="sendCargoCompanyAddress"></span>
								</div>

							</div>
							<!--   收货企业    -->
							<div class="half">
								<h5>收货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="receiveCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="receiveCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="receiveCargoCompanyAddress"></span>
								</div>
							</div>
						</div>
						<hr>
						<!-- 接取短驳  -->
						<div class="receve_truck cut_apart clearfix">
							<h5>接取短驳</h5>
							<div class="half splitters">
								<ul class="clearfix pad">
									<li class="long">
										<label>发货单位：</label>
										<span id="" name="sendCargoUnitName"></span>
									</li>
									<li>
										<label>&emsp;取货地：</label>
										<span id="" name="sendCargoAreaCode"></span>
									</li>
								</ul>
								<div class="pad">
									<label>取货地址：</label>
									<span id="" name="sendCargoAddress" ></span>
								</div>
							</div>
							<div class="half">
								<ul class="clearfix pad">
									<li class="long">
										<label>收货站点：</label>
										<span name="receiveCargoSite"></span>
									</li>
									<li>
										<label>&emsp;运抵地：</label>
										<span name="receiveCargoSiteAreaCode"></span>
									</li>
								</ul>
								<div class="pad">
									<label>运抵地址：</label>
										<span name="receiveCargoSiteAddress"></span>
								</div>
							</div>
							<!--   短驳承运方  -->
							<div class="whole modify-whole">
								<p>短驳承运方</p>
								<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
								<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
								<form class="form-inline ">
									<div class="form-group ">
										<label>短驳承运方式：</label>
										<select name="shortBargeCarrierMode1">
											<option value="0">平台</option>
											<option value="1">自选</option>
										</select>
									</div>
								</form>
								<div name='shortBargeCarrierName1'></div>
							</div>
						</div>	
						<hr>
						<!-- 火运干线  -->
						<div class="train_line cut_apart clearfix">
							<h5>火运干线</h5>
							<!-- 起运中心站 -->
							<div class="half splitters">
								<ul class="clearfix">
									<li class="long">
										<label>起运中心站：</label>
										<span name='beginCenterSiteName'></span>
									</li>
									<li>
										<label>始发站点：</label>
										<span name='beginSiteName'></span>
									</li>
								</ul>
								<div>
									<label>始发地：</label>
									<span name='beginAddress'></span>
								</div>
							</div>
							<!-- 到达中心站 -->
							<div class="half ">

								<ul class="clearfix">
									<li class="long">
										<label>到达中心站：</label>
											<span name='endCenterSiteName'></span>
									</li>
									<li>
										<label>到达站点：</label>
									<span name='endSiteName'></span>
									</li>
								</ul>
								<div>
									<label>到达地：</label>
									<span name='endAddress'></span>
								</div>
							</div>
							<!--  干线费用   -->
							<!--修改div-->
							<div class="whole modify-whole">
								<p>干线费用</p>
								<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
								<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
								<form class="form-inline" name="trainCost">
								<input type="hidden" name="id" />
									<div class="form-group">
										<label>&emsp;运费核计：</label>
										<input type="text" maxLength="10"  name='freight' ><span>元</span>
									</div>
									<div class="form-group">
										<label>加固材料费：</label>
										<input type="text" maxLength="10" name='materialCost'><span>元</span>
									</div>
									<div class="form-group">
										<label>篷布使用费：</label>
										<input type="text" maxLength="10" name='tarpaulinCost'><span>元</span>
									</div>
									<div class="form-group">
										<label>发站装卸费：</label>
										<input type="text" maxLength="10" name='beginStevedoringCost'><span>元</span>
									</div>
									<div class="form-group">
										<label>到站装卸费：</label>
										<input type="text" maxLength="10" name='endStevedoringCost' ><span>元</span>
									</div>
									<div class="form-group">
										<label>&emsp;运费合计：</label>
										<input type="text" name="freightSum" class="inputbg" readonly="readonly" maxLength="30"><span>元</span>
									</div>
									<input type="hidden" name="trainPrice" />
									<input type="hidden" name="pickUpPrice" />
									<input type="hidden" name="transportType" value="4"/>
									<!--短驳承运方式-->
									<input type="hidden" name="shortBargeCarrierMode" value="4"/>
									<!--短驳承运方-->
									<input type="hidden" name="shortBargeCarrierName" value="4"/>
								</form>
							</div>
						</div>
						<hr>
						<div class="client_cost whole modify-whole">
							<h5>委托方费用</h5>
							<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
							<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
							<form class="form-inline pad">
								<div class="form-group"><label>接取单价：</label><input type="text" name='recevePickUpPrice' maxlength="30" readonly="readonly"><span>元</span></div>
								<div class="form-group"><label>火运单价：</label><input type="text" name='trainTrainPrice' maxlength="30" readonly="readonly"><span>元</span></div>
							</form>
						</div>
						<hr>
						<!-- 备注信息 -->
						<div class="remarks_info">
							<h5>备注信息</h5>
							<div name='receveAndTrainRemark'></div>
						</div>
						<hr>
						<!-- 删除信息 -->
						<div class="del-info">
							<h5>删除信息</h5>
							<ul class="clearfix pad">
								<li>
									<label>删除人：</label>
									<span name='delUser'></span>
								</li>
								<li>
									<label>删除时间：</label>
									<span name='delDate'></span>
								</li>
							</ul>
							<div class="pad">
								<label>删除原因：</label>
								<span name='delReason' ></span>
							</div>
							<hr>
						</div>
							<!-- 历史修改记录 -->
						<div class="history_modify">
							<h5>历史修改记录</h5>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方:合肥深合软件有限公司的“运输单价”为1000元，原“运输单价”为2000元</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改送达短驳承运方:合肥深合软件有限公司的“扣损比率”为1‰，原“扣损比率”为21‰</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方为“合肥深合软件有限公司”，原承运方为“新疆秦龙矿业有限公司”</div>
						</div>
						<!--按钮-->
						<div class="row clearfix hidebtn">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="updateProject(4,'receveAndTrainModal')">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!--火运+送达项目详情模态框-->
		<div class="modal fade  detailModal modifyModal" tabindex="-1" role="dialog" data-backdrop="static" id="trainAndSendModal">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content  ">
					<div class="modal-header">
						<h4 class="modal-title">项目详情（火运+送达）</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<!-- 项目信息 -->
						<!-- 项目信息 -->
						<div class="project_info">
							<h5>项目信息</h5>
							<ul class="clearfix">
								<li>
									<label>分支机构：</label>
									<span name="branchGroupName"></span>
								</li>
								<li>
									<label>项目类型：</label>
									<span name="projectType"></span>
								</li>
								<li>
									<label>联运模式：</label>
									<span name="transportType"></span>
								</li>
								<li>
									<label>委托方：</label>
									<span name="principal" ></span>
								</li>
								<li class="history_detail">
									<label>完成时间：</label>
									<span >2017.12.12</span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 货物信息 -->
						<div class="cargo_info">
							<h5>货物信息</h5>
							<ul class="clearfix">
								<li>
									<label>货物品名：</label>
									<span name="cargoName" ></span>
								</li>
								<li>
									<label>货物规格：</label>
									<span name="cargoSpecifications" ></span>
								</li>
								<li>
									<label>货物单价：</label>
									<span name="cargoPrice" ></span>
								</li>
								<li>
									<label>计价单位：</label>
									<span name="valuationUnitName"></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 收发货企业 -->
						<div class="detail_info cut_apart clearfix">
							<!--   发货企业  -->
							<div class="half splitters">
								<h5>发货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="sendCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="sendCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="sendCargoCompanyAddress"></span>
								</div>

							</div>
							<!--   收货企业    -->
							<div class="half">
								<h5>收货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="receiveCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="receiveCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="receiveCargoCompanyAddress"></span>
								</div>
							</div>
						</div>
						<hr>
						<!-- 火运干线  -->
						<div class="train_line cut_apart clearfix">
							<h5>火运干线</h5>
							<!-- 起运中心站 -->
							<div class="half splitters">
								<ul class="clearfix">
									<li class="long">
										<label>起运中心站：</label>
										<span name='beginCenterSiteName'></span>
									</li>
									<li>
										<label>始发站点：</label>
										<span name='beginSiteName'></span>
									</li>
								</ul>
								<div>
									<label>始发地：</label>
									<span name='beginAddress'></span>
								</div>
							</div>
							<!-- 到达中心站 -->
							<div class="half ">

								<ul class="clearfix">
									<li class="long">
										<label>到达中心站：</label>
											<span name='endCenterSiteName'></span>
									</li>
									<li>
										<label>到达站点：</label>
									<span name='endSiteName'></span>
									</li>
								</ul>
								<div>
									<label>到达地：</label>
									<span name='endAddress'></span>
								</div>
							</div>
							<!--  干线费用   -->
							<!--修改div-->
							<div class="whole modify-whole">
								<p>干线费用</p>
								<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
								<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
								<form class="form-inline" name="trainCost">
								<input type="hidden" name="id" />
									<div class="form-group">
										<label>&emsp;运费核计：</label>
										<input type="text" maxLength="10" onkeyup="freightTotalSum()" name='freight' /><span>元</span>
									</div>
									<div class="form-group">
										<label>加固材料费：</label>
										<input type="text" maxLength="10" name='materialCost'/><span>元</span>
									</div>
									<div class="form-group">
										<label>篷布使用费：</label>
										<input type="text" maxLength="10" name='tarpaulinCost' /><span>元</span>
									</div>
									<div class="form-group">
										<label>发站装卸费：</label>
										<input type="text" maxLength="10" name='beginStevedoringCost' /><span>元</span>
									</div>
									<div class="form-group">
										<label>到站装卸费：</label>
										<input type="text" maxLength="10" name='endStevedoringCost' /><span>元</span>
									</div>
									<div class="form-group">
										<label>&emsp;运费合计：</label>
										<input type="text" name="freightSum" class="inputbg" readonly="readonly" maxLength="30" /><span>元</span>
									</div>
									<input type="hidden" name="trainPrice" />
									<input type="hidden" name="arrivePrice" />
									<input type="hidden" name="transportType" value="5"/>
									<!--短驳承运方式-->
									<input type="hidden" name="shortBargeCarrierMode" value="4"/>
									<!--短驳承运方-->
									<input type="hidden" name="shortBargeCarrierName" value="4"/>
								</form>
							</div>
						</div>
						<hr>
					<!-- 送达短驳 -->
						<div class="send_truck cut_apart clearfix">
							<h5>送达短驳</h5>
							<div class="half splitters">

								<ul class="clearfix">
									<li class="long">
										<label>接取站点：</label>
										<span name="forwardingSiteName"></span>
									</li>
									<li>
										<label>取货地：</label>
										<span name="forwardingSiteAreaCode"></span>
									</li>
								</ul>
								<div>
									<label>取货地址：</label>
									<span name="forwardingSiteAddress"></span>
								</div>

							</div>
							<div class="half">

								<ul class="clearfix">
									<li class="long">
										<label>收货单位：</label>
										<span name="receivingDepartmentName"></span>
									</li>
									<li>
										<label>运抵地：</label>
										<span name="receivingDepartmentAreaCode"></span>
									</li>
								</ul>
								<div>
									<label>运抵地址：</label>
									<span name="receivingDepartmentAddress"></span>
								</div>
							</div>

							<!--   短驳承运方  -->
							<div class="whole modify-whole">
								<p>短驳承运方</p>
								<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
								<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
								<form class="form-inline ">
									<div class="form-group ">
										<label>短驳承运方式：</label>
										<select name="shortBargeCarrierMode2">
											<option value="0">平台</option>
											<option value="1">自选</option>
										</select>
									</div>
								</form>
								<div name='shortBargeCarrierName2'></div>
							</div>
						</div>
		
						<hr>
						<!-- 委托方费用 -->
						<div class="client_cost whole modify-whole">
							<h5>委托方费用</h5>
							<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
							<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
							<form class="form-inline pad">
								<div class="form-group"><label>火运单价：</label><input type="text" name='trainTrainPrice' maxlength="30" readonly="readonly"><span>元</span></div>
								<div class="form-group"><label>送达单价：</label><input type="text" name='sendArrivePrice' maxlength="30" readonly="readonly"><span>元</span></div>
							</form>
						</div>
						<hr>
						<!-- 备注信息 -->
						<div class="remarks_info">
							<h5>备注信息</h5>
							<div name="remark"></div>
						</div>
						<hr>
						<!-- 删除信息 -->
						<div class="del-info">
							<h5>删除信息</h5>
							<ul class="clearfix pad">
								<li>
									<label>删除人：</label>
									<span name='delUser'></span>
								</li>
								<li>
									<label>删除时间：</label>
									<span name='delDate'></span>
								</li>
							</ul>
							<div class="pad">
								<label>删除原因：</label>
								<span name='delReason' ></span>
							</div>
							<hr>
						</div>
							<!-- 历史修改记录 -->
						<div class="history_modify">
							<h5>历史修改记录</h5>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方:合肥深合软件有限公司的“运输单价”为1000元，原“运输单价”为2000元</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改送达短驳承运方:合肥深合软件有限公司的“扣损比率”为1‰，原“扣损比率”为21‰</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方为“合肥深合软件有限公司”，原承运方为“新疆秦龙矿业有限公司”</div>
						</div>
						<!--按钮-->
						<div class="row clearfix hidebtn">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="updateProject(5,'trainAndSendModal')">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!--接取+送达项目详情模态框-->
		<div class="modal fade  detailModal modifyModal" tabindex="-1" role="dialog" data-backdrop="static" id="receveAndSendModal">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content  ">
					<div class="modal-header">
						<h4 class="modal-title">项目详情</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<!-- 项目信息 -->
						<!-- 项目信息 -->
						<div class="project_info">
							<h5>项目信息</h5>
							<ul class="clearfix">
								<li>
									<label>分支机构：</label>
									<span name="branchGroupName"></span>
								</li>
								<li>
									<label>项目类型：</label>
									<span name="projectType"></span>
								</li>
								<li>
									<label>联运模式：</label>
									<span name="transportType"></span>
								</li>
								<li>
									<label>委托方：</label>
									<span name="principal" ></span>
								</li>
								<li class="history_detail">
									<label>完成时间：</label>
									<span name="finishDate" ></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 货物信息 -->
						<div class="cargo_info">
							<h5>货物信息</h5>
							<ul class="clearfix">
								<li>
									<label>货物品名：</label>
									<span name="cargoName" ></span>
								</li>
								<li>
									<label>货物规格：</label>
									<span name="cargoSpecifications" ></span>
								</li>
								<li>
									<label>货物单价：</label>
									<span name="cargoPrice" ></span>
								</li>
								<li>
									<label>计价单位：</label>
									<span name="valuationUnitName"></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 收发货企业 -->
						<div class="detail_info cut_apart clearfix">
							<!--   发货企业  -->
							<div class="half splitters">
								<h5>发货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="sendCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="sendCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="sendCargoCompanyAddress"></span>
								</div>

							</div>
							<!--   收货企业    -->
							<div class="half">
								<h5>收货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="receiveCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="receiveCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="receiveCargoCompanyAddress"></span>
								</div>
							</div>
						</div>
						<hr>
						<!-- 接取短驳  -->
						<div class="receve_truck cut_apart clearfix">
							<h5>接取短驳</h5>
							<div class="half splitters">
								<ul class="clearfix pad">
									<li class="long">
										<label>发货单位：</label>
										<span id="" name="sendCargoUnitName"></span>
									</li>
									<li>
										<label>&emsp;取货地：</label>
										<span id="" name="sendCargoAreaCode"></span>
									</li>
								</ul>
								<div class="pad">
									<label>取货地址：</label>
									<span id="" name="sendCargoAddress" ></span>
								</div>
							</div>
							<div class="half">
								<ul class="clearfix pad">
									<li class="long">
										<label>收货站点：</label>
										<span name="receiveCargoSite"></span>
									</li>
									<li>
										<label>&emsp;运抵地：</label>
										<span name="receiveCargoSiteAreaCode"></span>
									</li>
								</ul>
								<div class="pad">
									<label>运抵地址：</label>
										<span name="receiveCargoSiteAddress"></span>
								</div>
							</div>
							<!--   短驳承运方  -->
							<div class="whole modify-whole">
								<p>短驳承运方</p>
								<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
								<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
								<form class="form-inline ">
									<div class="form-group ">
										<label>短驳承运方式：</label>
										<select name="shortBargeCarrierMode1">
											<option value="0">平台</option>
											<option value="1">自选</option>
										</select>
									</div>
								</form>
								<div name='shortBargeCarrierName1'></div>
							</div>
						</div>	
								<form class="form-inline" name="trainCost" hidden="hidden">
									<input type="hidden" name="id" />
									<input type="hidden" name="arrivePrice" />
									<input type="hidden" name="pickUpPrice" />
									<input type="hidden" name="transportType" value="7"/>
									<!--短驳承运方式-->
									<input type="hidden" name="shortBargeCarrierMode" />
									<!--短驳承运方-->
									<input type="hidden" name="shortBargeCarrierName" />	
										
									<!--短驳承运方式-->
									<input type="hidden" name="sendShortBargeCarrierMode" />
									<!--短驳承运方-->
									<input type="hidden" name="sendShortBargeCarrierName" />
								</form>
						
						<hr>
						<!-- 送达短驳 -->
						<div class="send_truck cut_apart clearfix">
							<h5>送达短驳</h5>
							<div class="half splitters">

								<ul class="clearfix">
									<li class="long">
										<label>接取站点：</label>
										<span name="forwardingSiteName"></span>
									</li>
									<li>
										<label>取货地：</label>
										<span name="forwardingSiteAreaCode"></span>
									</li>
								</ul>
								<div>
									<label>取货地址：</label>
									<span name="forwardingSiteAddress"></span>
								</div>

							</div>
							<div class="half">

								<ul class="clearfix">
									<li class="long">
										<label>收货单位：</label>
										<span name="receivingDepartmentName"></span>
									</li>
									<li>
										<label>运抵地：</label>
										<span name="receivingDepartmentAreaCode"></span>
									</li>
								</ul>
								<div>
									<label>运抵地址：</label>
									<span name="receivingDepartmentAddress"></span>
								</div>
							</div>

							<!--   短驳承运方  -->
							<div class="whole modify-whole">
								<p>短驳承运方</p>
								<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
								<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
								<form class="form-inline ">
									<div class="form-group ">
										<label>短驳承运方式：</label>
										<select name="shortBargeCarrierMode2">
											<option value="0">平台</option>
											<option value="1">自选</option>
										</select>
									</div>
								</form>
								<div name='shortBargeCarrierName2'></div>
							</div>
						</div>	
						<!-- 委托方费用 -->
						<div class="client_cost whole modify-whole">
							<h5>委托方费用</h5>
							<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
							<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
							<form class="form-inline pad">
								<div class="form-group"><label>接取单价：</label><input type="text" name='recevePickUpPrice' maxlength="30" readonly="readonly"><span>元</span></div>
								<div class="form-group"><label>送达单价：</label><input type="text" name='sendArrivePrice' maxlength="30" readonly="readonly"><span>元</span></div>
							</form>
						</div>
						<hr>
						<!-- 备注信息 -->
						<div class="remarks_info">
							<h5>备注信息</h5>
							<div class="pad" name="receveAndSendRemark"></div>
						</div>
						<hr>
						<!-- 删除信息 -->
						<div class="del-info">
							<h5>删除信息</h5>
							<ul class="clearfix pad">
								<li>
									<label>删除人：</label>
									<span name='delUser'></span>
								</li>
								<li>
									<label>删除时间：</label>
									<span name='delDate'></span>
								</li>
							</ul>
							<div class="pad">
								<label>删除原因：</label>
								<span name='delReason' ></span>
							</div>
							<hr>
						</div>
							<!-- 历史修改记录 -->
						<div class="history_modify">
							<h5>历史修改记录</h5>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方:合肥深合软件有限公司的“运输单价”为1000元，原“运输单价”为2000元</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改送达短驳承运方:合肥深合软件有限公司的“扣损比率”为1‰，原“扣损比率”为21‰</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方为“合肥深合软件有限公司”，原承运方为“新疆秦龙矿业有限公司”</div>
						</div>
						<!--按钮-->
						<div class="row clearfix hidebtn">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="updateProject(7,'receveAndSendModal')">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!--联运项目详情模态框-->
		<div class="modal fade  detailModal modifyModal" tabindex="-1" role="dialog" data-backdrop="static" id="unionModal">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content  ">
					<div class="modal-header">
						<h4 class="modal-title">项目详情（联运）</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<!-- 项目信息 -->
						<div class="project_info">
							<h5>项目信息</h5>
							<ul class="clearfix">
								<li>
									<label>分支机构：</label>
									<span name="branchGroupName"></span>
								</li>
								<li>
									<label>项目类型：</label>
									<span name="projectType"></span>
								</li>
								<li>
									<label>联运模式：</label>
									<span name="transportType"></span>
								</li>
								<li>
									<label>委托方：</label>
									<span name="principal" ></span>
								</li>
								<li class="history_detail">
									<label>完成时间：</label>
									<span name="finishDate" ></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 货物信息 -->
						<div class="cargo_info">
							<h5>货物信息</h5>
							<ul class="clearfix">
								<li>
									<label>货物品名：</label>
									<span name="cargoName" ></span>
								</li>
								<li>
									<label>货物规格：</label>
									<span name="cargoSpecifications" ></span>
								</li>
								<li>
									<label>货物单价：</label>
									<span name="cargoPrice" ></span>
								</li>
								<li>
									<label>计价单位：</label>
									<span name="valuationUnitName"></span>
								</li>
							</ul>
						</div>
						<hr>
						<!-- 收发货企业 -->
						<div class="detail_info cut_apart clearfix">
							<!--   发货企业  -->
							<div class="half splitters">
								<h5>发货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="sendCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="sendCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="sendCargoCompanyAddress"></span>
								</div>

							</div>
							<!--   收货企业    -->
							<div class="half">
								<h5>收货企业</h5>
								<ul class="clearfix">
									<li class="long">
										<label>企业名称：</label>
										<span name="receiveCargoCompanyName"></span>
									</li>
									<li>
										<label>联系方式：</label>
										<span name="receiveCargoCompanyPhone"></span>
									</li>
								</ul>
								<div>
									<label>地址：</label>
									<span name="receiveCargoCompanyAddress"></span>
								</div>

							</div>
						</div>
						<hr>
						<!-- 接取短驳  -->
						<div class="receve_truck cut_apart clearfix">
							<h5>接取短驳</h5>
							<div class="half splitters">

								<ul class="clearfix">
									<li class="long">
										<label>发货单位：</label>
											<span id="" name="sendCargoUnitName"></span>
									</li>
									<li>
										<label>取货地：</label>
										<span id="" name="sendCargoAreaCode"></span>
									</li>
								</ul>
								<div>
									<label>取货地址：</label>
									<span id="" name="sendCargoAddress" ></span>

								</div>

							</div>
							<div class="half">
								<ul class="clearfix">
									<li class="long">
										<label>收货站点：</label>
										<span name="receiveCargoSite"></span>
									</li>
									<li>
										<label>运抵地：</label>
										<span name="receiveCargoSiteAreaCode"></span>
									</li>
								</ul>
								<div>
									<label>运抵地址：</label>
									<span name="receiveCargoSiteAddress"></span>
								</div>

							</div>
							<!--   短驳承运方  -->
							<div class="whole modify-whole">
								<p>短驳承运方</p>
								<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
								<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
								<form class="form-inline ">
									<div class="form-group ">
										<label>短驳承运方式：</label>
										<select name="shortBargeCarrierMode1">
											<option value="0">平台</option>
											<option value="1">自选</option>
										</select>
									</div>
								</form>
								<div name='shortBargeCarrierName1'></div>
							</div>
						</div>
						<hr>
						<!-- 火运干线  -->
						<div class="train_line cut_apart clearfix">
							<h5>火运干线</h5>
							<!-- 起运中心站 -->
							<div class="half splitters">
								<ul class="clearfix">
									<li class="long">
										<label>起运中心站：</label>
										<span name='beginCenterSiteName'></span>
									</li>
									<li>
										<label>始发站点：</label>
										<span name='beginSiteName'></span>
									</li>
								</ul>
								<div>
									<label>始发地：</label>
									<span name='beginAddress'></span>
								</div>
							</div>
							<!-- 到达中心站 -->
							<div class="half ">

								<ul class="clearfix">
									<li class="long">
										<label>到达中心站：</label>
											<span name='endCenterSiteName'></span>
									</li>
									<li>
										<label>到达站点：</label>
									<span name='endSiteName'></span>
									</li>
								</ul>
								<div>
									<label>到达地：</label>
									<span name='endAddress'></span>
								</div>
							</div>
							<!--  干线费用   -->
							<!--修改div-->
							<div class="whole modify-whole">
								<p>干线费用</p>
								<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
								<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
								<form class="form-inline" name="trainCost">
								<input type="hidden" name="id" />
									<div class="form-group">
										<label>&emsp;运费核计：</label>
										<input type="text" maxLength="10"  name='freight' ><span>元</span>
									</div>
									<div class="form-group">
										<label>加固材料费：</label>
										<input type="text" maxLength="10" name='materialCost'><span>元</span>
									</div>
									<div class="form-group">
										<label>篷布使用费：</label>
										<input type="text" maxLength="10" name='tarpaulinCost'><span>元</span>
									</div>
									<div class="form-group">
										<label>发站装卸费：</label>
										<input type="text" maxLength="10" name='beginStevedoringCost'><span>元</span>
									</div>
									<div class="form-group">
										<label>到站装卸费：</label>
										<input type="text" maxLength="10" name='endStevedoringCost' ><span>元</span>
									</div>
									<div class="form-group">
										<label>&emsp;运费合计：</label>
										<input type="text" name="freightSum" class="inputbg" readonly="readonly" maxLength="30"><span>元</span>
									</div>
									<input type="hidden" name="trainPrice" />
									<input type="hidden" name="pickUpPrice" />
									<input type="hidden" name="arrivePrice" />
									<input type="hidden" name="transportType" value="6"/>
									<!--短驳承运方式-->
									<input type="hidden" name="shortBargeCarrierMode" />
									<!--短驳承运方-->
									<input type="hidden" name="shortBargeCarrierName" />
									<!--短驳承运方式-->
									<input type="hidden" name="sendShortBargeCarrierMode" />
									<!--短驳承运方-->
									<input type="hidden" name="sendShortBargeCarrierName" />
								</form>
							</div>
						</div>
						<hr>
						<!-- 送达短驳 -->
						<div class="send_truck cut_apart clearfix">
							<h5>送达短驳</h5>
							<div class="half splitters">

								<ul class="clearfix">
									<li class="long">
										<label>接取站点：</label>
										<span name="forwardingSiteName"></span>
									</li>
									<li>
										<label>取货地：</label>
										<span name="forwardingSiteAreaCode"></span>
									</li>
								</ul>
								<div>
									<label>取货地址：</label>
									<span name="forwardingSiteAddress"></span>
								</div>

							</div>
							<div class="half">

								<ul class="clearfix">
									<li class="long">
										<label>收货单位：</label>
										<span name="receivingDepartmentName"></span>
									</li>
									<li>
										<label>运抵地：</label>
										<span name="receivingDepartmentAreaCode"></span>
									</li>
								</ul>
								<div>
									<label>运抵地址：</label>
									<span name="receivingDepartmentAddress"></span>
								</div>

							</div>
							<!--   短驳承运方  -->
							<div class="whole modify-whole">
								<p>短驳承运方</p>
								<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
								<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
								<form class="form-inline ">
									<div class="form-group ">
										<label>短驳承运方式：</label>
										<select name="shortBargeCarrierMode2">
											<option value="0">平台</option>
											<option value="1">自选</option>
										</select>
									</div>
								</form>
								<div name='shortBargeCarrierName2'></div>
							</div>
						</div>	
						<hr>
						<!-- 委托方费用 -->
						<div class="client_cost whole modify-whole">
							<h5>委托方费用</h5>
							<button class="btn btn-sm btn-warning confirm-modify"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>确认修改</button>
							<button class="btn btn-sm btn-success modify-info"><em class="modifyBtn"><img src="${pageContext.request.contextPath}/img/modify.png" alt=""></em>修改信息</button>
							<form class="form-inline pad">
								<div class="form-group"><label>接取单价：</label><input type="text" name='recevePickUpPrice' maxlength="30" readonly="readonly"><span>元</span></div>
								<div class="form-group"><label>火运单价：</label><input type="text" name='trainTrainPrice' maxlength="30" readonly="readonly"><span>元</span></div>
								<div class="form-group"><label>送达单价：</label><input type="text" name='sendArrivePrice' maxlength="30" readonly="readonly"><span>元</span></div>
							</form>
						</div>
						<hr>
						<!-- 备注信息 -->
						<div class="remarks_info">
							<h5>备注信息</h5>
							<div name='remark'></div>
						</div>
						<hr>
						<!-- 删除信息 -->
						<div class="del-info">
							<h5>删除信息</h5>
							<ul class="clearfix pad">
								<li>
									<label>删除人：</label>
									<span name='delUser'></span>
								</li>
								<li>
									<label>删除时间：</label>
									<span name='delDate'></span>
								</li>
							</ul>
							<div class="pad">
								<label>删除原因：</label>
								<span name='delReason' ></span>
							</div>
							<hr>
						</div>
							<!-- 历史修改记录 -->
						<div class="history_modify">
							<h5>历史修改记录</h5>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方:合肥深合软件有限公司的“运输单价”为1000元，原“运输单价”为2000元</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改送达短驳承运方:合肥深合软件有限公司的“扣损比率”为1‰，原“扣损比率”为21‰</div>
							<div class="pad"><span id="">2017-10-18</span><span>陈凯歌</span> 修改接取短驳承运方为“合肥深合软件有限公司”，原承运方为“新疆秦龙矿业有限公司”</div>
						</div>
						<!--按钮-->
						<div class="row clearfix hidebtn">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="updateProject(6,'unionModal')">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>

		<!-- 切换       新建项目模态框 -->
		<div class="modal fade  creatProjectModal" tabindex="-1" role="dialog" data-backdrop="static" id="creatProjectModal">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content creat_project_a ">
					<div class="modal-header">
						<h4 class="modal-title">新建项目</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="createId">
							<div class="project_info">
								<div class="info_title">
									<h5>项目信息</h5>
									<div class="form-inline">
								<div class="form-group">
									<label>分支机构：</label>
									<select name="branchGroupId1" onchange='changeAndSetValue(this)'></select>
								</div>
								<div class="form-group">
									<label>项目类型：</label>
									<select name="projectType1">
									<option value="0">集装箱</option>
									<option value="1">散装</option>
									</select>
								</div>
								<div class="form-group">
									<label>联运模式：</label>
									<select id="selectField" name="transportType1">
										<option value="0" id="truck">汽运</option>
										<option value="1" id="receve">接取</option>
										<option value="2" id="send">送达</option>
										<option value="3" id="train">火运</option>
										<option value="4" id="receveAndTrain">接取+火运</option>
										<option value="5" id="trainAndSend">火运+送达</option>
										<option value="7" id="receveAndSend">接取+送达</option>
										<option value="6" id="union">联运</option>
									</select>
								</div>
								<div class="form-group">
									<label>&emsp;委托方：</label>
									<select name="principal1">
										<option value="0">收货企业</option>
										<option value="1">发货企业</option>
									</select>
								</div>
							</div>	
								</div>
							</div>
						</form>
							<hr>
							<form id="addTruck">
							<input type="hidden" name="branchGroupId"  />	
							<input type="hidden" name="branchGroupName"  />	
							<input type="hidden" name="projectType"  />	
							<input type="hidden" name="transportType"  />
							<input type="hidden" name="principal"  />
							<input type="hidden" name="shortBargeCarrierName"  /><!--短驳承运方-->
							<!--汽运模态框-->
							<div class="col truck">
								<!-- 货物信息 -->
								<div class="cargo_info">
									<h5>货物信息</h5>
									<div class="form-inline">
										<div class="form-group">
											<label>货物品名：</label>
											<select name="cargoId" onchange="getCargo('addTruck')"></select>
											<input type="hidden" name="cargoName"  />
										</div>
										<div class="form-group">
											<label>货物规格：</label>
											<select name="cargoSpecifications"></select>
										</div>
										<div class="form-group">
											<label>货物单价：</label>
											<input type="text"  name="cargoPrice" maxLength="30"><span>元</span>
										</div>
										<div class="form-group">
											<label>计价单位：</label>
											<select name="valuationUnitName">
												<option value="0">吨</option>
												<option value="1">位</option>
											</select>
										</div>
									</div>
								</div>
								<hr>
								<!-- 收发货企业 -->
								<div class="detail_info cut_apart clearfix">
									<!--   发货企业  -->
									<div class="half splitters">
										<h5>发货企业</h5>
										<div class="form-inline">
											<div class="form-group">
												<label>企业名称：</label>
												<select name="sendCargoCompanyId" ></select>
												<input type="hidden" name="sendCargoCompanyName" />
											</div>
											<div class="form-group">
												<label>联系方式：</label>
												<input type="text" name="sendCargoCompanyphone" unselectable="on" readonly class="inputbg">
											</div>
											<div class="form-group">
												<label class="sm-tag">地址：</label>
												<input type="text" name="sendCargoCompanyAddress" unselectable="on" readonly class="addr_info inputbg">
											</div>
										</div>
									</div>
									<!--   收货企业    -->
									<div class="half">
										<h5>收货企业</h5>
										<div class="form-inline">
											<div class="form-group">
												<label>企业名称：</label>
												<select name="receiveCargoCompanyId" ></select>
												<input type="hidden" name="receiveCargoCompanyName" />
											</div>
											<div class="form-group">
												<label>联系方式：</label>
												<input type="text" unselectable="on" name="receiveCargoCompanyAddress" readonly class="inputbg">
											</div>
											<div class="form-group">
												<label class="sm-tag">地址：</label>
												<input type="text" unselectable="on" name="receiveCargoCompanyAddress" readonly class="addr_info inputbg">
											</div>
										</div>
									</div>
								</div>
								<hr>
								<!-- 汽运短驳  -->
								<div class="receve_truck cut_apart clearfix">
									<h5>汽运短驳</h5>
									<div class="half splitters">
										<div class="form-inline">
											<div class="form-group">
												<label class="sm-tag">发货单位：</label>
												<select name="sendCargoUnitId" ></select>
												<input type="hidden" name="sendCargoUnitName" />
											</div>
											<div class="form-group">
												<label class="sm-tag">取货地：</label>
												<input class="inputbg" type="text" unselectable="on" readonly>
											</div>
											<div class="form-group">
												<label class="sm-tag">取货地址：</label>
												<input type="text" class="addr_info inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>
									<div class="half">
										<div class="form-inline ">
											<div class="form-group">
												<label class="sm-tag">收货单位：</label>
												<select name="receivingDepartmentId"></select>
												<input type="hidden" name="receivingDepartmentName" />
											</div>
											<div class="form-group">
												<label class="sm-tag">运抵地：</label>
												<input class="inputbg" type="text" unselectable="on" readonly>
											</div>
											<div class="form-group">
												<label class="sm-tag">运抵地址：</label>
												<input type="text" class="addr_info inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>
									<!--   短驳承运方  -->
									<div class="whole" id="forms1">
										<p>短驳承运方</p>
										<div class="form-inline ">
											<div class="form-group ">
												<label>短驳承运方式：</label>
												<select name="shortBargeCarrierMode"  class="shortSelect shortSelect1">
													<option value="0" selected="selected"  name="platform">平台</option>
													<option value="1" name="optional">自选</option>
												</select>
											</div>
										</div>
										<div class="form-inline" name="platformShortBarge1">
											
										</div>
										<div id="motor1" class="motor">
										</div>
										<button class="addBtns addBtn1 add-more btn btn-success btn-sm pull-right" type="button"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加更多承运方</button>
									</div>
								</div>
								<hr>
								<!-- 委托方费用 -->
								<div class="client_cost">
									<h5>委托方费用</h5>
									<div class="form-inline">
										<div class="form-group">
											<label>汽运单价：</label>
											<input type="text" name="transportPrice" maxLength="10"><span>元</span>
										</div>
									</div>
								</div>
								<hr>
								<!-- 备注信息 -->
								<div class="remarks_info">
									<h5>备注信息</h5>
									<textarea class="form-control" name="remark" rows="3"></textarea>
								</div>
							</div>
							</form>
							<!-- 接取模态框 -->
							<form id="addReceve">
							<input type="hidden" name="branchGroupId"  />	
							<input type="hidden" name="branchGroupName"  />	
							<input type="hidden" name="projectType"  />	
							<input type="hidden" name="transportType"  />
							<input type="hidden" name="principal"  />
							<input type="hidden" name="shortBargeCarrierName"  /><!--短驳承运方-->	
							<div class="col receve">
								<!-- 货物信息 -->
								<div class="cargo_info">
									<h5>货物信息</h5>
									<div class="form-inline">
										<div class="form-group">
											<label>货物品名：</label>
											<select name="cargoId" onchange="getCargo('addReceve')"></select>
											<input type="hidden" name="cargoName"  />
										</div>
										<div class="form-group">
											<label>货物规格：</label>
											<select name="cargoSpecifications"></select>
										</div>
										<div class="form-group">
											<label>货物单价：</label>
											<input type="text"  name="cargoPrice" maxLength="30"><span>元</span>
										</div>
										<div class="form-group">
											<label>计价单位：</label>
											<select name="valuationUnitName">
												<option value="0">吨</option>
												<option value="1">位</option>
											</select>
										</div>
									</div>
								</div>
								<hr>
								<!-- 收发货企业 -->
								<div class="detail_info cut_apart clearfix">
									<!--   发货企业  -->
									<div class="half splitters">
										<h5>发货企业</h5>
										<div class="form-inline">
											<div class="form-group">
												<label>企业名称：</label>
												<select name="sendCargoCompanyId" ></select>
												<input type="hidden" name="sendCargoCompanyName" />
											</div>
											<div class="form-group">
												<label>联系方式：</label>
												<input type="text" name="sendCargoCompanyphone" unselectable="on" readonly class="inputbg">
											</div>
											<div class="form-group">
												<label class="sm-tag">地址：</label>
												<input type="text" name="sendCargoCompanyAddress" unselectable="on" readonly class="addr_info inputbg">
											</div>
										</div>
									</div>
									<!--   收货企业    -->
									<div class="half">
										<h5>收货企业</h5>
										<div class="form-inline">
											<div class="form-group">
												<label>企业名称：</label>
												<select name="receiveCargoCompanyId" ></select>
												<input type="hidden" name="receiveCargoCompanyName" />
											</div>
											<div class="form-group">
												<label>联系方式：</label>
												<input type="text" unselectable="on" name="receiveCargoCompanyAddress" readonly class="inputbg">
											</div>
											<div class="form-group">
												<label class="sm-tag">地址：</label>
												<input type="text" unselectable="on" name="receiveCargoCompanyAddress" readonly class="addr_info inputbg">
											</div>
										</div>
									</div>
								</div>
								<hr>
								<!-- 接取短驳  -->
								<div class="receve_truck cut_apart clearfix">
									<h5>接取短驳</h5>
									<div class="half splitters">
										<div class="form-inline">
											<div class="form-group">
												<label class="sm-tag">发货单位：</label>
												<select name="sendCargoUnitId"" ></select>
												<input type="hidden" name="sendCargoUnitName" />
											</div>
											<div class="form-group">
												<label class="sm-tag">取货地：</label>
												<input class="inputbg" type="text" unselectable="on" readonly>
											</div>
											<div class="form-group">
												<label class="sm-tag">取货地址：</label>
												<input type="text" class="addr_info inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>
									<div class="half">
										<div class="form-inline ">
											<div class="form-group">
												<label class="sm-tag">收货站点：</label>
												<select name="receiveCargoSiteId" ></select>
												<input type="hidden" name="receiveCargoSite" />
											</div>
											<div class="form-group">
												<label class="sm-tag">运抵地：</label>
												<input class="inputbg" type="text" unselectable="on" readonly>
											</div>
											<div class="form-group">
												<label class="sm-tag">运抵地址：</label>
												<input type="text" class="addr_info inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>
									<!--   短驳承运方  -->
									<div class="whole" id="forms2">
										<p>短驳承运方</p>
										<div class="form-inline ">
											<div class="form-group ">
												<label>短驳承运方式：</label>
												<select name="shortBargeCarrierMode"  class="shortSelect shortSelect2">
													<option value="0" selected="selected"  name="platform">平台</option>
													<option value="1" name="optional">自选</option>
												</select>
											</div>
										</div>
										<div class="form-inline" name="platformShortBarge2">
											
										</div>
										<div id="motor2" class="motor">
										</div>
										<button class="addBtns addBtn2 add-more btn btn-success btn-sm pull-right" type="button"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加更多承运方</button>
									</div>
								</div>
								<hr>
								<!-- 委托方费用 -->
								<div class="client_cost">
									<h5>委托方费用</h5>
									<div class="form-inline">
										<div class="form-group">
											<label>接取单价：</label>
											<input type="text" name='pickUpPrice' maxLength="10"><span>元</span>
										</div>
									</div>
								</div>
								<hr>
								<!-- 备注信息 -->
								<div class="remarks_info">
									<h5>备注信息</h5>
									<textarea class="form-control" name="remark" rows="3"></textarea>
								</div>
							</div>
							</form>
							<!-- 送达模态框 -->
							<form id="addSend">
							<input type="hidden" name="branchGroupId"  />	
							<input type="hidden" name="branchGroupName"  />	
							<input type="hidden" name="projectType"  />	
							<input type="hidden" name="transportType"  />
							<input type="hidden" name="principal"  />
							<input type="hidden" name="shortBargeCarrierName"  /><!--短驳承运方-->	
							<div class="col send">
								<!-- 货物信息 -->
								<div class="cargo_info">
									<h5>货物信息</h5>
									<div class="form-inline">
										<div class="form-group">
											<label>货物品名：</label>
											<select name="cargoId" onchange="getCargo('addSend')"></select>
											<input type="hidden" name="cargoName"  />
										</div>
										<div class="form-group">
											<label>货物规格：</label>
											<select name="cargoSpecifications"></select>
										</div>
										<div class="form-group">
											<label>货物单价：</label>
											<input type="text"  name="cargoPrice" maxLength="30"><span>元</span>
										</div>
										<div class="form-group">
											<label>计价单位：</label>
											<select name="valuationUnitName">
												<option value="0">吨</option>
												<option value="1">位</option>
											</select>
										</div>
									</div>
								</div>
								<hr>
								<!-- 收发货企业 -->
								<div class="detail_info cut_apart clearfix">
									<!--   发货企业  -->
									<div class="half splitters">
										<h5>发货企业</h5>
										<div class="form-inline">
											<div class="form-group">
												<label>企业名称：</label>
												<select name="sendCargoCompanyId" ></select>
												<input type="hidden" name="sendCargoCompanyName" />
											</div>
											<div class="form-group">
												<label>联系方式：</label>
												<input type="text" name="sendCargoCompanyphone" unselectable="on" readonly class="inputbg">
											</div>
											<div class="form-group">
												<label class="sm-tag">地址：</label>
												<input type="text" name="sendCargoCompanyAddress" unselectable="on" readonly class="addr_info inputbg">
											</div>
										</div>
									</div>
									<!--   收货企业    -->
									<div class="half">
										<h5>收货企业</h5>
										<div class="form-inline">
											<div class="form-group">
												<label>企业名称：</label>
												<select name="receiveCargoCompanyId" ></select>
												<input type="hidden" name="receiveCargoCompanyName" />
											</div>
											<div class="form-group">
												<label>联系方式：</label>
												<input type="text" unselectable="on" name="receiveCargoCompanyAddress" readonly class="inputbg">
											</div>
											<div class="form-group">
												<label class="sm-tag">地址：</label>
												<input type="text" unselectable="on" name="receiveCargoCompanyAddress" readonly class="addr_info inputbg">
											</div>
										</div>
									</div>
								</div>
								<hr>
								<!-- 送达短驳 -->
								<div class="send_truck cut_apart clearfix">
									<h5>送达短驳</h5>
									<div class="half splitters">
										<div class="form-inline">
											<div class="form-group">
												<label class="sm-tag">接取站点：</label>
												<select name="forwardingSiteId" ></select>
												<input type="hidden" name="forwardingSiteName" />
											</div>
											<div class="form-group">
												<label class="sm-tag">取货地：</label>
												<input class="inputbg" type="text" unselectable="on" readonly>
											</div>
											<div class="form-group">
												<label class="sm-tag">取货地址：</label>
												<input type="text" class="addr_info inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>
									<div class="half">
										<div class="form-inline ">
											<div class="form-group">
												<label class="sm-tag">收货单位：</label>
												<select name="receivingDepartmentId" ></select>
												<input type="hidden" name="receivingDepartmentName" />
											</div>
											<div class="form-group">
												<label class="sm-tag">运抵地：</label>
												<input class="inputbg" type="text" unselectable="on" readonly>
											</div>
											<div class="form-group">
												<label class="sm-tag">运抵地址：</label>
												<input type="text" class="addr_info inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>

									<!--   短驳承运方  -->
									<div class="whole" id="forms3">
										<p>短驳承运方</p>
										<div class="form-inline ">
											<div class="form-group ">
												<label>短驳承运方式：</label>
												<select name="shortBargeCarrierMode"  class="shortSelect shortSelect3">
													<option value="0" selected="selected"  name="platform">平台</option>
													<option value="1" name="optional">自选</option>
												</select>
											</div>
										</div>
										<div class="form-inline" name="platformShortBarge3">
											
										</div>
										<div id="motor3" class="motor">
										</div>
										<button class="addBtns addBtn3 add-more btn btn-success btn-sm pull-right" type="button"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加更多承运方</button>
									</div>
								</div>
								<hr>
								<!-- 委托方费用 -->
								<div class="client_cost">
									<h5>委托方费用</h5>
									<div class="form-inline">
										<div class="form-group">
											<label>送达单价：</label>
											<input type="text" name='arrivePrice' maxLength="10"><span>元</span>
										</div>
									</div>
								</div>
								<hr>
								<!-- 备注信息 -->
								<div class="remarks_info">
									<h5>备注信息</h5>
									<textarea class="form-control" name="remark" rows="3"></textarea>
								</div>
							</div>
							</form>
							<!-- 火运模态框 -->
							<form id="addTrain">
							<input type="hidden" name="branchGroupId"  />	
							<input type="hidden" name="branchGroupName"  />	
							<input type="hidden" name="projectType"  />	
							<input type="hidden" name="transportType"  />
							<input type="hidden" name="principal"  />
							<input type="hidden" name="shortBargeCarrierName"  /><!--短驳承运方-->	
							<div class="col train">
								<!-- 货物信息 -->
								<div class="cargo_info">
									<h5>货物信息</h5>
									<div class="form-inline">
										<div class="form-group">
											<label>货物品名：</label>
											<select name="cargoId" onchange="getCargo('addTrain')"></select>
											<input type="hidden" name="cargoName"  />
										</div>
										<div class="form-group">
											<label>货物规格：</label>
											<select name="cargoSpecifications"></select>
										</div>
										<div class="form-group">
											<label>货物单价：</label>
											<input type="text"  name="cargoPrice" maxLength="30"><span>元</span>
										</div>
										<div class="form-group">
											<label>计价单位：</label>
											<select name="valuationUnitName">
												<option value="0">吨</option>
												<option value="1">位</option>
											</select>
										</div>
									</div>
								</div>
								<hr>
								<!-- 收发货企业 -->
								<div class="detail_info cut_apart clearfix">
									<!--   发货企业  -->
									<div class="half splitters">
										<h5>发货企业</h5>
										<div class="form-inline">
											<div class="form-group">
												<label>企业名称：</label>
												<select name="sendCargoCompanyId" ></select>
												<input type="hidden" name="sendCargoCompanyName" />
											</div>
											<div class="form-group">
												<label>联系方式：</label>
												<input type="text" name="sendCargoCompanyphone" unselectable="on" readonly class="inputbg">
											</div>
											<div class="form-group">
												<label class="sm-tag">地址：</label>
												<input type="text" name="sendCargoCompanyAddress" unselectable="on" readonly class="addr_info inputbg">
											</div>
										</div>
									</div>
									<!--   收货企业    -->
									<div class="half">
										<h5>收货企业</h5>
										<div class="form-inline">
											<div class="form-group">
												<label>企业名称：</label>
												<select name="receiveCargoCompanyId" ></select>
												<input type="hidden" name="receiveCargoCompanyName" />
											</div>
											<div class="form-group">
												<label>联系方式：</label>
												<input type="text" unselectable="on" name="receiveCargoCompanyAddress" readonly class="inputbg">
											</div>
											<div class="form-group">
												<label class="sm-tag">地址：</label>
												<input type="text" unselectable="on" name="receiveCargoCompanyAddress" readonly class="addr_info inputbg">
											</div>
										</div>
									</div>
								</div>
								<hr>
								<!-- 火运干线  -->
								<div class="train_line cut_apart clearfix">
									<h5>火运干线</h5>
									<!-- 起运中心站 -->
									<div class="half splitters">
										<div class="form-inline">
											<div class="form-group">
												<label class="lg-tag">起运中心站：</label>
												<select name="beginCenterSiteId"></select>
												<input name="beginCenterSiteName" type="hidden"/>
											</div>
											<div class="form-group">
												<label>始发站点：</label>
												<select name="beginSiteId"></select>
												<input name="beginSiteName" type="hidden"/>
											</div>
											<div class="form-group">
												<label class="lg-tag">始发地：</label>
												<input type="text" name="beginAddress" class="addr_info inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>
									<!-- 到达中心站 -->
									<div class="half ">
										<div class="form-inline ">
											<div class="form-group">
												<label class="lg-tag">到达中心站：</label>
												<select name="endCenterSiteId"></select>
												<input name="endCenterSiteName" type="hidden"/>
											</div>
											<div class="form-group">
												<label>到达站点：</label>
												<select name="endSiteId"></select>
												<input name="endSiteName" type="hidden"/>
											</div>
											<div class="form-group">
												<label class="lg-tag">到达地：</label>
												<input type="text" name="endAddress" class="addr_info inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>
									<!--  干线费用   -->
									<div class="whole">
										<p>干线费用</p>
										<div class="form-inline">
											<div class="form-group">
												<label>&emsp;运费核计：</label>
												<input type="text" name="freight" maxLength="30"><span>元</span>
											</div>
											<div class="form-group">
												<label>加固材料费：</label>
												<input type="text" name="materialCost" maxLength="30"><span>元</span>
											</div>
											<div class="form-group">
												<label>篷布使用费：</label>
												<input type="text" name="tarpaulinCost" maxLength="30"><span>元</span>
											</div>
											<div class="form-group">
												<label>发站装卸费：</label>
												<input type="text" name="beginStevedoringCost" maxLength="30"><span>元</span>
											</div>
											<div class="form-group">
												<label>到站装卸费：</label>
												<input type="text" name="endStevedoringCost" maxLength="30"><span>元</span>
											</div>
											<div class="form-group">
												<label>&emsp;运费合计：</label>
												<input type="text" name="freightSum" class="inputbg" readonly="readonly" maxLength="30"><span>元</span>
											</div>
										</div>
									</div>
								</div>
								<hr>
								<!-- 委托方费用 -->
								<div class="client_cost">
									<h5>委托方费用</h5>
									<div class="form-inline">
										<div class="form-group">
											<label>火运单价：</label>
											<input type="text" name="trainPrice" maxLength="10"><span>元</span>
										</div>
									</div>
								</div>
								<hr>
								<!-- 备注信息 -->
								<div class="remarks_info">
									<h5>备注信息</h5>
									<textarea class="form-control" name="remark" rows="3"></textarea>
								</div>

							</div>
							</form>
							<!-- 接取+火运模态框 -->
							<form id="addReceveAndTrain">
							<input type="hidden" name="branchGroupId"  />	
							<input type="hidden" name="branchGroupName"  />	
							<input type="hidden" name="projectType"  />	
							<input type="hidden" name="transportType"  />
							<input type="hidden" name="principal"  />
							<input type="hidden" name="shortBargeCarrierName"  /><!--短驳承运方-->	
							<div class="col receveAndTrain">
								<!-- 货物信息 -->
								<div class="cargo_info">
									<h5>货物信息</h5>
									<div class="form-inline">
										<div class="form-group">
											<label>货物品名：</label>
											<select name="cargoId" onchange="getCargo('addReceveAndTrain')"></select>
											<input type="hidden" name="cargoName"  />
										</div>
										<div class="form-group">
											<label>货物规格：</label>
											<select name="cargoSpecifications"></select>
										</div>
										<div class="form-group">
											<label>货物单价：</label>
											<input type="text"  name="cargoPrice" maxLength="30"><span>元</span>
										</div>
										<div class="form-group">
											<label>计价单位：</label>
											<select name="valuationUnitName">
												<option value="0">吨</option>
												<option value="1">位</option>
											</select>
										</div>
									</div>
								</div>
								<hr>
								<!-- 收发货企业 -->
								<div class="detail_info cut_apart clearfix">
									<!--   发货企业  -->
									<div class="half splitters">
										<h5>发货企业</h5>
										<div class="form-inline">
											<div class="form-group">
												<label>企业名称：</label>
												<select name="sendCargoCompanyId" ></select>
												<input type="hidden" name="sendCargoCompanyName" />
											</div>
											<div class="form-group">
												<label>联系方式：</label>
												<input type="text" name="sendCargoCompanyphone" unselectable="on" readonly class="inputbg">
											</div>
											<div class="form-group">
												<label class="sm-tag">地址：</label>
												<input type="text" name="sendCargoCompanyAddress" unselectable="on" readonly class="addr_info inputbg">
											</div>
										</div>
									</div>
									<!--   收货企业    -->
									<div class="half">
										<h5>收货企业</h5>
										<div class="form-inline">
											<div class="form-group">
												<label>企业名称：</label>
												<select name="receiveCargoCompanyId" ></select>
												<input type="hidden" name="receiveCargoCompanyName" />
											</div>
											<div class="form-group">
												<label>联系方式：</label>
												<input type="text" unselectable="on" name="receiveCargoCompanyAddress" readonly class="inputbg">
											</div>
											<div class="form-group">
												<label class="sm-tag">地址：</label>
												<input type="text" unselectable="on" name="receiveCargoCompanyAddress" readonly class="addr_info inputbg">
											</div>
										</div>
									</div>
								</div>
								<hr>
								<!-- 接取短驳  -->
								<div class="receve_truck cut_apart clearfix">
									<h5>接取短驳</h5>
									<div class="half splitters">
										<div class="form-inline">
											<div class="form-group">
												<label class="sm-tag">发货单位：</label>
												<select name="sendCargoUnitId"  ></select>
												<input type="hidden" name="sendCargoUnitName" />
											</div>
											<div class="form-group">
												<label class="sm-tag">取货地：</label>
												<input class="inputbg" type="text" unselectable="on" readonly>
											</div>
											<div class="form-group">
												<label class="sm-tag">取货地址：</label>
												<input type="text" class="addr_info inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>
										<div class="half">
										<div class="form-inline ">
											<div class="form-group">
												<label class="sm-tag">收货站点：</label>
												<select name="receiveCargoSiteId" ></select>
												<input type="hidden" name="receiveCargoSite" />
											</div>
											<div class="form-group">
												<label class="sm-tag">运抵地：</label>
												<input class="inputbg" type="text" unselectable="on" readonly>
											</div>
											<div class="form-group">
												<label class="sm-tag">运抵地址：</label>
												<input type="text" class="addr_info inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>
									<!--   短驳承运方  -->
									<!--   短驳承运方  -->
									<div class="whole" id="forms4">
										<p>短驳承运方</p>
										<div class="form-inline ">
											<div class="form-group ">
												<label>短驳承运方式：</label>
												<select name="shortBargeCarrierMode"  class="shortSelect shortSelect4">
													<option value="0" selected="selected"  name="platform">平台</option>
													<option value="1" name="optional">自选</option>
												</select>
											</div>
										</div>
										<div class="form-inline" name="platformShortBarge4">
										</div>
										<div id="motor4" class="motor">
										</div>
										<button class="addBtns addBtn4 add-more btn btn-success btn-sm pull-right" type="button"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加更多承运方</button>
									</div>

								</div>
								<hr>
								<!-- 火运干线  -->
								<div class="train_line cut_apart clearfix">
									<h5>火运干线</h5>
									<!-- 起运中心站 -->
									<div class="half splitters">
										<div class="form-inline">
											<div class="form-group">
												<label class="lg-tag">起运中心站：</label>
												<select name="beginCenterSiteId"></select>
												<input name="beginCenterSiteName" type="hidden"/>
											</div>
											<div class="form-group">
												<label>始发站点：</label>
												<select name="beginSiteId"></select>
												<input name="beginSiteName" type="hidden"/>
											</div>
											<div class="form-group">
												<label class="lg-tag">始发地：</label>
												<input type="text" name="beginAddress" class="addr_info inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>
									<!-- 到达中心站 -->
									<div class="half ">
										<div class="form-inline ">
											<div class="form-group">
												<label class="lg-tag">到达中心站：</label>
												<select name="endCenterSiteId"></select>
												<input name="endCenterSiteName" type="hidden"/>
											</div>
											<div class="form-group">
												<label>到达站点：</label>
												<select name="endSiteId"></select>
												<input name="endSiteName" type="hidden"/>
											</div>
											<div class="form-group">
												<label class="lg-tag">到达地：</label>
												<input type="text" name="endSiteAddress" class="addr_info inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>
									<!--  干线费用   -->
									<div class="whole">
										<p>干线费用</p>
										<div class="form-inline">
											<div class="form-group">
												<label>&emsp;运费核计：</label>
												<input type="text" name="freight" maxLength="30"><span>元</span>
											</div>
											<div class="form-group">
												<label>加固材料费：</label>
												<input type="text" name="materialCost" maxLength="30"><span>元</span>
											</div>
											<div class="form-group">
												<label>篷布使用费：</label>
												<input type="text" name="tarpaulinCost" maxLength="30"><span>元</span>
											</div>
											<div class="form-group">
												<label>发站装卸费：</label>
												<input type="text" name="beginStevedoringCost" maxLength="30"><span>元</span>
											</div>
											<div class="form-group">
												<label>到站装卸费：</label>
												<input type="text" name="endStevedoringCost" maxLength="30"><span>元</span>
											</div>
											<div class="form-group">
												<label>&emsp;运费合计：</label>
												<input type="text" name="freightSum" class="inputbg" readonly="readonly" maxLength="30"><span>元</span>
											</div>
										</div>
									</div>
								</div>
								<hr>
								<!-- 委托方费用 -->
								<div class="client_cost">
									<h5>委托方费用</h5>
									<div class="form-inline">
										<div class="form-group">
											<label>接取单价：</label>
											<input type="text" name="pickUpPrice"  maxLength="10"><span>元</span>
										</div>
										<div class="form-group">
											<label>火运单价：</label>
											<input type="text" name="trainPrice"  maxLength="10"><span>元</span>
										</div>
									</div>
								</div>
								<hr>
								<!-- 备注信息 -->
								<div class="remarks_info">
									<h5>备注信息</h5>
									<textarea class="form-control" name="remark" rows="3"></textarea>
								</div>
							</div>
							</form>
							<!-- 火运+送达模态框 -->
							<form id="addTrainAndSend">
							<input type="hidden" name="branchGroupId"  />	
							<input type="hidden" name="branchGroupName"  />	
							<input type="hidden" name="projectType"  />	
							<input type="hidden" name="transportType"  />
							<input type="hidden" name="principal"  />
							<input type="hidden" name="shortBargeCarrierName"  /><!--短驳承运方-->	
							<div class="col trainAndSend">
								<!-- 货物信息 -->
								<div class="cargo_info">
									<h5>货物信息</h5>
									<div class="form-inline">
										<div class="form-group">
											<label>货物品名：</label>
											<select name="cargoId" onchange="getCargo('addTrainAndSend')"></select>
											<input type="hidden" name="cargoName"  />
										</div>
										<div class="form-group">
											<label>货物规格：</label>
											<select name="cargoSpecifications"></select>
										</div>
										<div class="form-group">
											<label>货物单价：</label>
											<input type="text"  name="cargoPrice" maxLength="30"><span>元</span>
										</div>
										<div class="form-group">
											<label>计价单位：</label>
											<select name="valuationUnitName">
												<option value="0">吨</option>
												<option value="1">位</option>
											</select>
										</div>
									</div>
								</div>
								<hr>
								<!-- 收发货企业 -->
								<div class="detail_info cut_apart clearfix">
									<!--   发货企业  -->
									<div class="half splitters">
										<h5>发货企业</h5>
										<div class="form-inline">
											<div class="form-group">
												<label>企业名称：</label>
												<select name="sendCargoCompanyId" ></select>
												<input type="hidden" name="sendCargoCompanyName" />
											</div>
											<div class="form-group">
												<label>联系方式：</label>
												<input type="text" name="sendCargoCompanyphone" unselectable="on" readonly class="inputbg">
											</div>
											<div class="form-group">
												<label class="sm-tag">地址：</label>
												<input type="text" name="sendCargoCompanyAddress" unselectable="on" readonly class="addr_info inputbg">
											</div>
										</div>
									</div>
									<!--   收货企业    -->
									<div class="half">
										<h5>收货企业</h5>
										<div class="form-inline">
											<div class="form-group">
												<label>企业名称：</label>
												<select name="receiveCargoCompanyId" ></select>
												<input type="hidden" name="receiveCargoCompanyName" />
											</div>
											<div class="form-group">
												<label>联系方式：</label>
												<input type="text" unselectable="on" name="receiveCargoCompanyPhone" readonly class="inputbg">
											</div>
											<div class="form-group">
												<label class="sm-tag">地址：</label>
												<input type="text" unselectable="on" name="receiveCargoCompanyAddress" readonly class="addr_info inputbg">
											</div>
										</div>
									</div>
								</div>
								<hr>
								<!-- 火运干线  -->
								<div class="train_line cut_apart clearfix">
									<h5>火运干线</h5>
									<!-- 起运中心站 -->
									<div class="half splitters">
										<div class="form-inline">
											<div class="form-group">
												<label class="lg-tag">起运中心站：</label>
												<select name="beginCenterSiteId"></select>
												<input name="beginCenterSiteName" type="hidden"/>
											</div>
											<div class="form-group">
												<label>始发站点：</label>
												<select name="beginSiteId"></select>
												<input name="beginSiteName" type="hidden"/>
											</div>
											<div class="form-group">
												<label class="lg-tag">始发地：</label>
												<input type="text" name="beginAddress" class="addr_info inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>
									<!-- 到达中心站 -->
									<div class="half ">
										<div class="form-inline ">
											<div class="form-group">
												<label class="lg-tag">到达中心站：</label>
												<select name="endCenterSiteId"></select>
												<input name="endCenterSiteName" type="hidden"/>
											</div>
											<div class="form-group">
												<label>到达站点：</label>
												<select name="endSiteId"></select>
												<input name="endSiteName" type="hidden"/>
											</div>
											<div class="form-group">
												<label class="lg-tag">到达地：</label>
												<input type="text" name="endSiteName" class="addr_info inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>
									<!--  干线费用   -->
									<div class="whole">
										<p>干线费用</p>
										<div class="form-inline">
											<div class="form-group">
												<label>&emsp;运费核计：</label>
												<input type="text" name="freight" maxLength="30"><span>元</span>
											</div>
											<div class="form-group">
												<label>加固材料费：</label>
												<input type="text" name="materialCost" maxLength="30"><span>元</span>
											</div>
											<div class="form-group">
												<label>篷布使用费：</label>
												<input type="text" name="tarpaulinCost" maxLength="30"><span>元</span>
											</div>
											<div class="form-group">
												<label>发站装卸费：</label>
												<input type="text" name="beginStevedoringCost" maxLength="30"><span>元</span>
											</div>
											<div class="form-group">
												<label>到站装卸费：</label>
												<input type="text" name="endStevedoringCost" maxLength="30"><span>元</span>
											</div>
											<div class="form-group">
												<label>&emsp;运费合计：</label>
												<input type="text" name="freightSum" class="inputbg" readonly="readonly" maxLength="30"><span>元</span>
											</div>
										</div>
									</div>
								</div>
								<hr>
								<!-- 送达短驳 -->
								<div class="send_truck cut_apart clearfix">
									<h5>送达短驳</h5>
									<div class="half splitters">
										<div class="form-inline">
											<div class="form-group">
												<label class="sm-tag">接取站点：</label>
												<select name="forwardingSiteId" ></select>
												<input type="hidden" name="forwardingSiteName" />
											</div>
											<div class="form-group">
												<label class="sm-tag">取货地：</label>
												<input class="inputbg" type="text" unselectable="on" readonly>
											</div>
											<div class="form-group">
												<label class="sm-tag">取货地址：</label>
												<input type="text" class="addr_info inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>
									<div class="half">
										<div class="form-inline ">
											<div class="form-group">
												<label class="sm-tag">收货单位：</label>
												<select name="receivingDepartmentId" ></select>
												<input type="hidden" name="receivingDepartmentName" />
											</div>
											<div class="form-group">
												<label class="sm-tag">运抵地：</label>
												<input class="inputbg" type="text" unselectable="on" readonly>
											</div>
											<div class="form-group">
												<label class="sm-tag">运抵地址：</label>
												<input type="text" class="addr_info inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>
									<!--   短驳承运方  -->
									<div class="whole" id="forms5">
										<p>短驳承运方</p>
										<div class="form-inline ">
											<div class="form-group ">
												<label>短驳承运方式：</label>
												<select name="shortBargeCarrierMode"  class="shortSelect shortSelect5">
													<option value="0" selected="selected"  name="platform">平台</option>
													<option value="1" name="optional">自选</option>
												</select>
											</div>
										</div>
										<div class="form-inline" name="platformShortBarge5">
										</div>
										<div id="motor5" class="motor">
										</div>
										<button class="addBtns addBtn5 add-more btn btn-success btn-sm pull-right" type="button"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加更多承运方</button>
									</div>
								</div>
								<hr>
								<!-- 委托方费用 -->
								<div class="client_cost">
									<h5>委托方费用</h5>
									<div class="form-inline">
										<div class="form-group">
											<label>火运单价：</label>
											<input type="text" name="trainPrice" maxLength="10"><span>元</span>
										</div>
										<div class="form-group">
											<label>送达单价：</label>
											<input type="text"  name="arrivePrice" maxLength="10"><span>元</span>
										</div>
									</div>
								</div>
								<hr>
								<!-- 备注信息 -->
								<div class="remarks_info">
									<h5>备注信息</h5>
									<textarea class="form-control" name="remark" rows="3"></textarea>
								</div>
							</div>
							</form>
							 <!--接取+送达-->
							<form id="addReceveAndSend">
							<input type="hidden" name="branchGroupId"  />	
							<input type="hidden" name="branchGroupName"  />	
							<input type="hidden" name="projectType"  />	
							<input type="hidden" name="transportType"  />
							<input type="hidden" name="principal"  />
							<input type="hidden" name="shortBargeCarrierName"  /><!--短驳承运方-->	
							<input type="hidden" name="sendShortBargeCarrierName"  /><!--送达短驳承运方-->	
						    <div class="col receveAndSend" id="receveAndSendM">
						    <div class="project_info">
							</div>
							<hr>					
							<div class="">
									<!-- 货物信息 -->
								<div class="cargo_info">
									<h5>货物信息</h5>
									<div class="form-inline">
										<div class="form-group">
											<label>货物品名：</label>
											<select name="cargoId" onchange="getCargo('addReceveAndSend')"></select>
											<input type="hidden" name="cargoName"  />
										</div>
										<div class="form-group">
											<label>货物规格：</label>
											<select name="cargoSpecifications"></select>
										</div>
										<div class="form-group">
											<label>货物单价：</label>
											<input type="text"  name="cargoPrice" maxLength="30"><span>元</span>
										</div>
										<div class="form-group">
											<label>计价单位：</label>
											<select name="valuationUnitName">
												<option value="0">吨</option>
												<option value="1">位</option>
											</select>
										</div>
									</div>
								</div>
								<hr>
								<!-- 收发货企业 -->
								<div class="detail_info cut_apart clearfix">
									<!--   发货企业  -->
									<div class="half splitters">
										<h5>发货企业</h5>
										<div class="form-inline">
											<div class="form-group">
												<label>企业名称：</label>
												<select name="sendCargoCompanyId" ></select>
												<input type="hidden" name="sendCargoCompanyName" />
											</div>
											<div class="form-group">
												<label>联系方式：</label>
												<input type="text" name="sendCargoCompanyphone" unselectable="on" readonly class="inputbg">
											</div>
											<div class="form-group">
												<label class="sm-tag">地址：</label>
												<input type="text" name="sendCargoCompanyAddress" unselectable="on" readonly class="addr_info inputbg">
											</div>
										</div>
									</div>
									<!--   收货企业    -->
									<div class="half">
										<h5>收货企业</h5>
										<div class="form-inline">
											<div class="form-group">
												<label>企业名称：</label>
												<select name="receiveCargoCompanyId" ></select>
												<input type="hidden" name="receiveCargoCompanyName" />
											</div>
											<div class="form-group">
												<label>联系方式：</label>
												<input type="text" unselectable="on" name="receiveCargoCompanyAddress" readonly class="inputbg">
											</div>
											<div class="form-group">
												<label class="sm-tag">地址：</label>
												<input type="text" unselectable="on" name="receiveCargoCompanyAddress" readonly class="addr_info inputbg">
											</div>
										</div>
									</div>
								</div>
								<hr>
								<!-- 接取短驳  -->
								<div class="receve_truck cut_apart clearfix">
									<h5>接取短驳</h5>
									<div class="half splitters">
										<div class="form-inline">
											<div class="form-group">
												<label class="sm-tag">发货单位：</label>
												<select name="sendCargoUnitId"  ></select>
												<input type="hidden" name="sendCargoUnitName" />
											</div>
											<div class="form-group">
												<label class="sm-tag">取货地：</label>
												<input class="inputbg" type="text" unselectable="on" readonly>
											</div>
											<div class="form-group">
												<label class="sm-tag">取货地址：</label>
												<input type="text" class="addr_info inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>
									<div class="half">
										<div class="form-inline ">
											<div class="form-group">
												<label class="sm-tag">收货站点：</label>
												<select name="receiveCargoSiteId"  ></select>
												<input type="hidden" name="receiveCargoSite" />
											</div>
											<div class="form-group">
												<label class="sm-tag">运抵地：</label>
												<input class="inputbg" type="text" unselectable="on" readonly>
											</div>
											<div class="form-group">
												<label class="sm-tag">运抵地址：</label>
												<input type="text" class="addr_info inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>
									<!--   短驳承运方  -->
									<div class="whole" id="forms8">
										<p>短驳承运方</p>
										<div class="form-inline ">
											<div class="form-group ">
												<label>短驳承运方式：</label>
												<select name="shortBargeCarrierMode"  class="shortSelect shortSelect8">
													<option value="0" selected="selected"  name="platform">平台</option>
													<option value="1" name="optional">自选</option>
												</select>
											</div>
										</div>
										<div class="form-inline" name="platformShortBarge8">
										</div>
										<div id="motor8" class="motor">
										</div>
										<button class="addBtns addBtn8 add-more btn btn-success btn-sm pull-right" type="button"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加更多承运方</button>
									</div>

								</div>
								<hr>
								<!-- 送达短驳 -->
								<div class="send_truck cut_apart clearfix">
									<h5>送达短驳</h5>
									<div class="half splitters">
										<div class="form-inline">
											<div class="form-group">
												<label class="sm-tag">接取站点：</label>
												<select name="forwardingSiteId" ></select>
												<input type="hidden" name="forwardingSiteName" />
											</div>
											<div class="form-group">
												<label class="sm-tag">取货地：</label>
												<input class="inputbg" type="text" unselectable="on" readonly>
											</div>
											<div class="form-group">
												<label class="sm-tag">取货地址：</label>
												<input type="text" class="addr_info inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>
									<div class="half">
										<div class="form-inline ">
											<div class="form-group">
												<label class="sm-tag">收货单位：</label>
												<select name="receivingDepartmentId" ></select>
												<input type="hidden" name="receivingDepartmentName" />
											</div>
											<div class="form-group">
												<label class="sm-tag">运抵地：</label>
												<input class="inputbg" type="text" unselectable="on" readonly>
											</div>
											<div class="form-group">
												<label class="sm-tag">运抵地址：</label>
												<input type="text" class="addr_info inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>

									<!--   短驳承运方  -->
									<div class="whole" id="forms9">
										<p>短驳承运方</p>
										<div class="form-inline ">
											<div class="form-group ">
												<label>短驳承运方式：</label>
												<select name="sendShortBargeCarrierMode"  class="shortSelect shortSelect9">
													<option value="0" selected="selected"  name="platform">平台</option>
													<option value="1" name="optional">自选</option>
												</select>
											</div>
										</div>
										<div class="form-inline" name="platformShortBarge9">
										</div>
										<div id="motor9" class="motor">
										</div>
										<button class="addBtns addBtn9 add-more btn btn-success btn-sm pull-right" type="button"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加更多承运方</button>
									</div>
								</div>
								<hr>
								<!-- 委托方费用 -->
								<div class="client_cost">
									<h5>委托方费用</h5>
									<div class="form-inline pad">
										<div class="form-group">
											<label>接取单价：</label>
											<input type="text" maxlength="10" name="pickUpPrice"><span>元</span>
										</div>
										<div class="form-group">
											<label>送达单价：</label>
											<input type="text" maxlength="10" name="arrivePrice"><span>元</span>
										</div>
									</div>
								</div>
								<hr>
								<!-- 备注信息 -->
								<div class="remarks_info">
									<h5>备注信息</h5>
									<textarea class="form-control pad" name="remark" rows="3"></textarea>
								</div>
							</div>
						    </div>
							</form>
							<!-- 联运模态框 -->
							<form id="addUnion">
							<input type="hidden" name="branchGroupId"  />	
							<input type="hidden" name="branchGroupName"  />	
							<input type="hidden" name="projectType"  />	
							<input type="hidden" name="transportType"  />
							<input type="hidden" name="principal"  />
							<input type="hidden" name="shortBargeCarrierName"  /><!--短驳承运方-->	
							<input type="hidden" name="sendShortBargeCarrierName"  /><!--送达短驳承运方-->	
							
							<div class="col union">
								<!-- 货物信息 -->
								<div class="cargo_info">
									<h5>货物信息</h5>
									<div class="form-inline">
										<div class="form-group">
											<label>货物品名：</label>
											<select name="cargoId" onchange="getCargo('addUnion')"></select>
											<input type="hidden" name="cargoName"  />
										</div>
										<div class="form-group">
											<label>货物规格：</label>
											<select name="cargoSpecifications"></select>
										</div>
										<div class="form-group">
											<label>货物单价：</label>
											<input type="text"  name="cargoPrice" maxLength="30"><span>元</span>
										</div>
										<div class="form-group">
											<label>计价单位：</label>
											<select name="valuationUnitName">
												<option value="0">吨</option>
												<option value="1">位</option>
											</select>
										</div>
									</div>
								</div>
								<hr>
								<!-- 收发货企业 -->
								<div class="detail_info cut_apart clearfix">
									<!--   发货企业  -->
									<div class="half splitters">
										<h5>发货企业</h5>
										<div class="form-inline">
											<div class="form-group">
												<label>企业名称：</label>
												<select name="sendCargoCompanyId" ></select>
												<input type="hidden" name="sendCargoCompanyName" />
											</div>
											<div class="form-group">
												<label>联系方式：</label>
												<input type="text" name="sendCargoCompanyphone" unselectable="on" readonly class="inputbg">
											</div>
											<div class="form-group">
												<label class="sm-tag">地址：</label>
												<input type="text" name="sendCargoCompanyAddress" unselectable="on" readonly class="addr_info inputbg">
											</div>
										</div>
									</div>
									<!--   收货企业    -->
									<div class="half">
										<h5>收货企业</h5>
										<div class="form-inline">
											<div class="form-group">
												<label>企业名称：</label>
												<select name="receiveCargoCompanyId" ></select>
												<input type="hidden" name="receiveCargoCompanyName" />
											</div>
											<div class="form-group">
												<label>联系方式：</label>
												<input type="text" unselectable="on" name="receiveCargoCompanyAddress" readonly class="inputbg">
											</div>
											<div class="form-group">
												<label class="sm-tag">地址：</label>
												<input type="text" unselectable="on" name="receiveCargoCompanyAddress" readonly class="addr_info inputbg">
											</div>
										</div>
									</div>
								</div>
								<hr>
								<!-- 接取短驳  -->
								<div class="receve_truck cut_apart clearfix">
									<h5>接取短驳</h5>
									<div class="half splitters">
										<div class="form-inline">
											<div class="form-group">
												<label class="sm-tag">发货单位：</label>
												<select name="sendCargoUnitId"  ></select>
												<input type="hidden" name="sendCargoUnitName" />
											</div>
											<div class="form-group">
												<label class="sm-tag">取货地：</label>
												<input class="inputbg" type="text" unselectable="on" readonly>
											</div>
											<div class="form-group">
												<label class="sm-tag">取货地址：</label>
												<input type="text" class="addr_info inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>
									<div class="half">
										<div class="form-inline ">
											<div class="form-group">
												<label class="sm-tag">收货站点：</label>
												<select name="receiveCargoSiteId"  ></select>
												<input type="hidden" name="receiveCargoSite" />
											</div>
											<div class="form-group">
												<label class="sm-tag">运抵地：</label>
												<input class="inputbg" type="text" unselectable="on" readonly>
											</div>
											<div class="form-group">
												<label class="sm-tag">运抵地址：</label>
												<input type="text" class="addr_info inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>
									<!--   短驳承运方  -->
									<div class="whole" id="forms6">
										<p>短驳承运方</p>
										<div class="form-inline ">
											<div class="form-group ">
												<label>短驳承运方式：</label>
												<select name="shortBargeCarrierMode"  class="shortSelect shortSelect6">
													<option value="0" selected="selected"  name="platform">平台</option>
													<option value="1" name="optional">自选</option>
												</select>
											</div>
										</div>
										<div class="form-inline" name="platformShortBarge6">
										</div>
										<div id="motor6" class="motor">
										</div>
										<button class="addBtns addBtn6 add-more btn btn-success btn-sm pull-right" type="button"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加更多承运方</button>
									</div>

								</div>
								<hr>
								<!-- 火运干线  -->
								<div class="train_line cut_apart clearfix">
									<h5>火运干线</h5>
									<!-- 起运中心站 -->
									<div class="half splitters">
										<div class="form-inline">
											<div class="form-group">
												<label class="lg-tag">起运中心站：</label>
												<select name="beginCenterSiteId"></select>
												<input name="beginCenterSiteName" type="hidden"/>
											</div>
											<div class="form-group">
												<label>始发站点：</label>
												<select name="beginSiteId"></select>
												<input name="beginSiteName" type="hidden"/>
											</div>
											<div class="form-group">
												<label class="lg-tag">始发地：</label>
												<input type="text" name="beginAddress" class="addr_info inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>
									<!-- 到达中心站 -->
									<div class="half ">
										<div class="form-inline ">
											<div class="form-group">
												<label class="lg-tag">到达中心站：</label>
												<select name="endCenterSiteId"></select>
												<input name="endCenterSiteName" type="hidden"/>
											</div>
											<div class="form-group">
												<label>到达站点：</label>
												<select name="endSiteId"></select>
												<input name="endSiteName" type="hidden"/>
											</div>
											<div class="form-group">
												<label class="lg-tag">到达地：</label>
												<input type="text" name="endAddress" class="addr_info inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>
									<!--  干线费用   -->
									<div class="whole">
										<p>干线费用</p>
										<div class="form-inline">
											<div class="form-group">
												<label>&emsp;运费核计：</label>
												<input type="text" name="freight" maxLength="30"><span>元</span>
											</div>
											<div class="form-group">
												<label>加固材料费：</label>
												<input type="text" name="materialCost" maxLength="30"><span>元</span>
											</div>
											<div class="form-group">
												<label>篷布使用费：</label>
												<input type="text" name="tarpaulinCost" maxLength="30"><span>元</span>
											</div>
											<div class="form-group">
												<label>发站装卸费：</label>
												<input type="text" name="beginStevedoringCost" maxLength="30"><span>元</span>
											</div>
											<div class="form-group">
												<label>到站装卸费：</label>
												<input type="text" name="endStevedoringCost" maxLength="30"><span>元</span>
											</div>
											<div class="form-group">
												<label>&emsp;运费合计：</label>
												<input type="text" name="freightSum" class="inputbg" readonly="readonly" maxLength="30"><span>元</span>
											</div>
										</div>
									</div>
								</div>
								<hr>
								<!-- 送达短驳 -->
								<div class="send_truck cut_apart clearfix">
									<h5>送达短驳</h5>
									<div class="half splitters">
										<div class="form-inline">
											<div class="form-group">
												<label class="sm-tag">接取站点：</label>
												<select name="forwardingSiteId" ></select>
												<input type="hidden" name="forwardingSiteName" />
											</div>
											<div class="form-group">
												<label class="sm-tag">取货地：</label>
												<input class="inputbg" type="text" unselectable="on" readonly>
											</div>
											<div class="form-group">
												<label class="sm-tag">取货地址：</label>
												<input type="text" class="addr_info inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>
									<div class="half">
										<div class="form-inline ">
											<div class="form-group">
												<label class="sm-tag">收货单位：</label>
												<select name="receivingDepartmentId" ></select>
												<input type="hidden" name="receivingDepartmentName" />
											</div>
											<div class="form-group">
												<label class="sm-tag">运抵地：</label>
												<input class="inputbg" type="text" unselectable="on" readonly>
											</div>
											<div class="form-group">
												<label class="sm-tag">运抵地址：</label>
												<input type="text" class="addr_info inputbg" unselectable="on" readonly>
											</div>
										</div>
									</div>

									<!--   短驳承运方  -->
									<div class="whole" id="forms7">
										<p>短驳承运方</p>
										<div class="form-inline ">
											<div class="form-group ">
												<label>短驳承运方式：</label>
												<select name="sendShortBargeCarrierMode"  class="shortSelect shortSelect7">
													<option value="0" selected="selected"  name="platform">平台</option>
													<option value="1" name="optional">自选</option>
												</select>
											</div>
										</div>
										<div class="form-inline" name="platformShortBarge7">
										</div>
										<div id="motor7" class="motor">
										</div>
										<button class="addBtns addBtn7 add-more btn btn-success btn-sm pull-right" type="button"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加更多承运方</button>
									</div>
								</div>
								<hr>
								<!-- 委托方费用 -->
								<div class="client_cost">
									<h5>委托方费用</h5>
									<div class="form-inline">
										<div class="form-group">
											<label>接取单价：</label>
											<input type="text" maxlength="10" name="pickUpPrice"><span>元</span>
										</div>
										<div class="form-group">
											<label>火运单价：</label>
											<input type="text" maxlength="10" name="trainPrice"><span>元</span>
										</div>
										<div class="form-group">
											<label>送达单价：</label>
											<input type="text" maxlength="10" name="arrivePrice"><span>元</span>
										</div>
									</div>
								</div>
								<hr>
								<!-- 备注信息 -->
								<div class="remarks_info">
									<h5>备注信息</h5>
									<textarea class="form-control" name="remark" rows="3"></textarea>
								</div>
							</div>
							</form>
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
									<button type="button" class="btn sureBtn" onclick="addProject()">确定</button>
									<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
								</div>
							</div>
						
					</div>
				</div>
			</div>
		</div>

	</body>

<!--新建项目模态框-->
<script>
    $("#creatProject").click(function(){
    	$.ajax({  
        	type:'post',      
       		url:'${pageContext.request.contextPath}/projectManagment/getProjectSelect.do',  
	        data:{status:0},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
        			//获取所有分支机构
	        		var branchGroups = data.data['branchGroups'];
	        		$("select[name='branchGroupId1']").html("");
	        		$.each(branchGroups, function(index,branchGroup) {
	        			$("select[name='branchGroupId1']").append(
						"<option value='"+branchGroup.id+"'>"+branchGroup.name+"</option>"
	        			);
	        		});
	        		$("select[name='branchGroupId1']").change();
	        		
	        		//获取所有货物
	        		var cargos = data.data['cargos'];
	        		$("select[name='cargoId']").html("");
	        		$.each(cargos, function(index,cargo) {
	        			$("select[name='cargoId']").append(
						"<option value='"+cargo.id+"'>"+cargo.cargoName+"</option>"
	        			);
	        		});
	        		$("select[name='cargoId']").change();
	        		
	        		var customers = data.data['customers'];
	        		//发货企业
	        		$("select[name='sendCargoCompanyId']").html("");
	        		$("select[name='receiveCargoCompanyId']").html("");
	        		$("select[name='sendCargoUnitId']").html("");
	        		$("select[name='receiveCargoCompanyId']").html("");
	        		$.each(customers, function(index,customer) {
	        			$("select[name='sendCargoCompanyId']").append(
						"<option id='"+customer.id+"' value='"+customer.id+"'>"+customer.companyName+"</option>"
	        			);
	        			$("select[name='receiveCargoCompanyId']").append(
						"<option value='"+customer.id+"'>"+customer.companyName+"</option>"
	        			);
	        			$("select[name='sendCargoUnitId']").append(
						"<option value='"+customer.id+"'>"+customer.companyName+"</option>"
	        			);
	        			$("select[name='receivingDepartmentId']").append(
						"<option value='"+customer.id+"'>"+customer.companyName+"</option>"
	        			);
	        		});
	        		
	        		$("select[name='sendCargoCompanyId']").change();
	        		$("select[name='receiveCargoCompanyId']").change();
	        		$("select[name='sendCargoUnitId']").change();
	        		$("select[name='receivingDepartmentId']").change();
	        		
	        		//站点信息
	        		var threeLevelStations = data.data['threeLevelStations'];
	        		$("select[name='receiveCargoSiteId']").html("");
	        		$("select[name='forwardingSiteId']").html("");
	        		$.each(threeLevelStations, function(index,trainStation) {
	        			$("select[name='receiveCargoSiteId']").append(
						"<option value='"+trainStation.id+"'>"+trainStation.stationName+"</option>"
	        			);
	        			
	        			$("select[name='forwardingSiteId']").append(
						"<option value='"+trainStation.id+"'>"+trainStation.stationName+"</option>"
	        			);
	        			
	        		})
	        		$("select[name='receiveCargoSiteId']").change();
	        		$("select[name='forwardingSiteId']").change();
	        		$("select[name='beginSiteId']").change();
	        		$("select[name='endSiteId']").change();
	        		
	        		
	        		//中心站点赋值
	        		var twoLevelStations = data.data['twoLevelStations'];
	        		$("select[name='beginCenterSiteId']").html("");
	        		$("select[name='endCenterSiteId']").html("");
	        		$.each(twoLevelStations, function(index,trainStation) {
	        			$("select[name='beginCenterSiteId']").append(
						"<option value='"+trainStation.id+"'>"+trainStation.stationName+"</option>"
	        			);
	        			$("select[name='endCenterSiteId']").append(
						"<option value='"+trainStation.id+"'>"+trainStation.stationName+"</option>"
	        			);
	        		})
	        		$("select[name='beginCenterSiteId']").change();
	        		$("select[name='endCenterSiteId']").change();
	        		
	        		for(var i=1;i<=9;i++){
						$("div[name='platformShortBarge"+i+"']").html("<div class='form-inline'>"+
			    		"<div class='form-group'><label>&emsp;短驳承运方：</label><input type='text' readonly='readonly' name='shortBargeName' value='平台' /></div>"+
			        	"<div class='form-group short-client'><label>运输单价：</label><input name='transportPrice' type='text'><span>元</span></div>"+
			        	"<div class='form-group short-client'><label>扣损比率：</label><input name='deductionRate' type='text'><span>‰</span></div>"+
			        	"<div class='form-group short-client'><label>扣损单价：</label><input name='deductionPrice' type='text'><span>元</span></div>"+
			        	"<div class='form-group short-client'><label>付款周期：</label> <select name='payment'>"+
		        		"<option value='0'>日结</option>"+
		        		"<option value='1'>周结</option>"+
		        		"<option value='2'>月结</option>"+
		        		"</select></div>"+
		       			"</div >")
					} 
	        		
	        		
		       		}
		       	}	
	    	})
			$("#createId").validate().resetForm();
			$("#createId")[0].reset();
			$('.col').hide();
			$('.motor').children().remove();
			$('.truck').show();
			$("#creatProjectModal").modal();
		})
    
</script>	
<!--货物信息二级联动-->
<script>
	function getCargo(param){
		var cargoId="";
		if(param=='addTruck'){
			$("#addTruck select[name='cargoId']").next('input').val($("#addTruck select[name='cargoId'] option:selected").text());
	  		cargoId= $("#addTruck select[name='cargoId']").val();
		}
		if(param=='addReceve'){
			$("#addReceve select[name='cargoId']").next('input').val($("#addReceve select[name='cargoId'] option:selected").text());
	  		 cargoId= $("#addReceve select[name='cargoId']").val();
		}
		if(param=='addSend'){
			$("#addSend select[name='cargoId']").next('input').val($("#addSend select[name='cargoId'] option:selected").text());
	  		 cargoId= $("#addSend select[name='cargoId']").val();
		}
		if(param=='addTrain'){
			$("#addTrain select[name='cargoId']").next('input').val($("#addTrain select[name='cargoId'] option:selected").text());
	  		 cargoId= $("#addTrain select[name='cargoId']").val();
		}
		if(param=='addReceveAndTrain'){
			$("#addReceveAndTrain select[name='cargoId']").next('input').val($("#addReceveAndTrain select[name='cargoId'] option:selected").text());
	  		 cargoId= $("#addReceveAndTrain select[name='cargoId']").val();
		}
		if(param=='addTrainAndSend'){
			$("#addTrainAndSend select[name='cargoId']").next('input').val($("#addTrainAndSend select[name='cargoId'] option:selected").text());
	  		 cargoId= $("#addTrainAndSend select[name='cargoId']").val();
		}
		if(param=='addUnion'){
			$("#addUnion select[name='cargoId']").next('input').val($("#addUnion select[name='cargoId'] option:selected").text());
	  		 cargoId= $("#addUnion select[name='cargoId']").val();
		}
		if(param=='addReceveAndSend'){
			$("#addReceveAndSend select[name='cargoId']").next('input').val($("#addReceveAndSend select[name='cargoId'] option:selected").text());
	  		 cargoId= $("#addReceveAndSend select[name='cargoId']").val();
		}
		$.ajax({  
        	type:'post',      
       		url:'${pageContext.request.contextPath}/cargo/getOneCargoSpecificte.do',  
	        data:{cargoId:cargoId},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
	        		var specifictes = data.data;
	        		$("select[name='cargoSpecifications']").html("");
	        		$.each(specifictes, function(index,specificte) {
	        			$("select[name='cargoSpecifications']").append(
						"<option value='"+specificte.name+"'>"+specificte.name+"</option>"
	        			);
	        		});
	        		}
	        	}  
	    	})
	}
</script>

<!--企业二级联动-->
<script>
$("select[name=sendCargoCompanyId]").change(function(){
	var name = $(this).find("option:checked").text();
	var value = $(this).find("option:checked").val();
	$(this).next('input').val(name);
	
	$.ajax({  
        	type:'post',      
       		url:'${pageContext.request.contextPath}/customerManagement/getCustomerById.do', 
	        data:{id:value},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
	        		$("select[name=sendCargoCompanyId]").parent('div').next('div').children('input').val(data.data.stationPhone);
	        		$("select[name=sendCargoCompanyId]").parent('div').next('div').next('div').children('input').val(data.data.addressCode+data.data.detailAddress);
	        	}  
		 	}
	})
})
$("select[name=receiveCargoCompanyId]").change(function(){
	var name = $(this).find("option:checked").text();
	var value = $(this).find("option:checked").val();
	$(this).next('input').val(name);
	$.ajax({  
        	type:'post',      
       		url:'${pageContext.request.contextPath}/customerManagement/getCustomerById.do', 
	        data:{id:value},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
	        		$("select[name=receiveCargoCompanyId]").parent('div').next('div').children('input').val(data.data.stationPhone);
	        		$("select[name=receiveCargoCompanyId]").parent('div').next('div').next('div').children('input').val(data.data.addressCode+data.data.detailAddress);
	        	}  
		 	}
	})
})

/*收发货单位*/
$("select[name=sendCargoUnitId]").change(function(){
	var name = $(this).find("option:checked").text();
	var value = $(this).find("option:checked").val();
	$(this).next('input').val(name);
	$.ajax({  
        	type:'post',      
       		url:'${pageContext.request.contextPath}/customerManagement/getCustomerById.do', 
	        data:{id:value},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
	        		$("select[name=sendCargoUnitId]").parent('div').next('div').children('input').val(data.data.addressCode);
	        		$("select[name=sendCargoUnitId]").parent('div').next('div').next('div').children('input').val(data.data.addressCode+data.data.detailAddress);
	        	}  
		 	}
	})
})
$("select[name=receivingDepartmentId]").change(function(){
	var name = $(this).find("option:checked").text();
	var value = $(this).find("option:checked").val();
	$(this).next('input').val(name);
	$.ajax({  
        	type:'post',      
       		url:'${pageContext.request.contextPath}/customerManagement/getCustomerById.do', 
	        data:{id:value},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
	        		$("select[name=receivingDepartmentId]").parent('div').next('div').children('input').val(data.data.addressCode);
	        		$("select[name=receivingDepartmentId]").parent('div').next('div').next('div').children('input').val(data.data.addressCode+data.data.detailAddress);
	        	}  
		 	}
	})
})
</script>
<!--三级站点二级联动-->
<script>
	$("select[name=receiveCargoSiteId]").change(function(){
		var name = $(this).find("option:checked").text();
		var value = $(this).find("option:checked").val();
		$(this).next('input').val(name);
		$.ajax({  
	        	type:'get',      
	       		url:'${pageContext.request.contextPath}/trainStation/get.do', 
		        data:{id:value},
		        cache:false,
		        dataType:'json',
	        	success:function(data){
	        		if(data.status==200){
		        		$("select[name=receiveCargoSiteId]").parent('div').next('div').children('input').val(data.data.adressCode);
		        		$("select[name=receiveCargoSiteId]").parent('div').next('div').next('div').children('input').val(data.data.detailAddress);
		        	}  
			 	}
		})
	})
	
	$("select[name=forwardingSiteId]").change(function(){
		var name = $(this).find("option:checked").text();
		var value = $(this).find("option:checked").val();
		$(this).next('input').val(name);
		$.ajax({  
	        	type:'get',      
	       		url:'${pageContext.request.contextPath}/trainStation/get.do', 
		        data:{id:value},
		        cache:false,
		        dataType:'json',
	        	success:function(data){
	        		if(data.status==200){
		        		$("select[name=forwardingSiteId]").parent('div').next('div').children('input').val(data.data.stationPhone);
		        		$("select[name=forwardingSiteId]").parent('div').next('div').next('div').children('input').val(data.data.detailAddress);
		        	}  
			 	}
		})
	})
	
	$("select[name=beginSiteId]").change(function(){
		var name = $(this).find("option:checked").text();
		var value = $(this).find("option:checked").val();
		$(this).next('input').val(name);
		$.ajax({  
	        	type:'get',      
	       		url:'${pageContext.request.contextPath}/trainStation/get.do', 
		        data:{id:value},
		        cache:false,
		        dataType:'json',
	        	success:function(data){
	        		if(data.status==200){
		        		$("select[name=beginSiteId]").parent('div').next('div').children('input').val(data.data.detailAddress);
		        	}  
			 	}
		})
	})
	$("select[name=endSiteId]").change(function(){
		var name = $(this).find("option:checked").text();
		var value = $(this).find("option:checked").val();
		$(this).next('input').val(name);
		$.ajax({  
	        	type:'get',      
	       		url:'${pageContext.request.contextPath}/trainStation/get.do', 
		        data:{id:value},
		        cache:false,
		        dataType:'json',
	        	success:function(data){
	        		if(data.status==200){
		        		$("select[name=endSiteId]").parent('div').next('div').children('input').val(data.data.detailAddress);
		        	}  
			 	}
		})
	})    
</script>

<!--二级联动赋值-->
<script>
function changeAndSetValue(param){
//给隐藏域赋值
	$("select[name="+param.name+"]").next('input').val("");
	$("select[name="+param.name+"]").next('input').val($("select[name="+param.name+"] option:selected").text());
	
}
function successSure(){
		$("#successModal").modal();
		window.location.reload();
	}

</script>

<!--中心站联动三级站点-->
<script>
	$("select[name=beginCenterSiteId]").change(function(){
		var name = $(this).find("option:checked").text();
		var value = $(this).find("option:checked").val();
		$(this).next('input').val(name);
		//赋值三级站点
		
		//$(this).parent('div').next('div').children('select').append("<option value='123'>456</option>");
		var flag = this;
		
		$.ajax({  
        	type:'post',      
       		url:'${pageContext.request.contextPath}/trainStation/getchildrenStationById.do', 
	        data:{id:value},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
        			var stations = data.data;
        			$(flag).parent('div').next('div').children('select').html("");
        			$.each(stations, function(index,station) {
        				$(flag).parent('div').next('div').children('select[name=beginSiteId]').append(
        					"<option value='"+station.id+"'>"+station.stationName+"</option>"
        				);
        			});
        			$(flag).parent('div').next('div').children('select[name=beginSiteId]').change();
	        	}  
		 	}
		})
	})
		
		
	$("select[name=endCenterSiteId]").change(function(){
		var name = $(this).find("option:checked").text();
		var value = $(this).find("option:checked").val();
		$(this).next('input').val(name);
		
		var flag = this;
		
		$.ajax({  
        	type:'post',      
       		url:'${pageContext.request.contextPath}/trainStation/getchildrenStationById.do', 
	        data:{id:value},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
        			var stations = data.data;
        			$.each(stations, function(index,station) {
        				$(flag).parent('div').next('div').children('select[name=endSiteId]').html("");
        				$(flag).parent('div').next('div').children('select[name=endSiteId]').append("<option value='"+station.id+"'>"+station.stationName+"</option>");
        			});
        			$(flag).parent('div').next('div').children('select[name=endSiteId]').change();
	        	}  
		 	}
		})
	})
	
	
</script>

<!--新增项目-->
<script>
	function addProject(){
	//将隐藏域的表单填值
	$('input[name=branchGroupId]').val($("select[name=branchGroupId1] option:selected").val());
	$('input[name=branchGroupName]').val($("select[name=branchGroupId1] option:selected").text());
	$('input[name=projectType]').val($("select[name=projectType1] option:selected").val());
	$('input[name=transportType]').val($("select[name=transportType1] option:selected").val());
	$('input[name=principal]').val($("select[name=principal1] option:selected").val());
	//将短驳承运方赋值到隐藏域
	//判断联运模式
	var transportType = $('input[name=transportType]').val();
	
	if(transportType == null || transportType==undefined || transportType==""){
		$("#msgContent").html("");
		$("#msgContent").html("请选择联运模式");
		$("#deletnullModal").modal();
	}
	var formParam="";
	if(transportType==0){//汽运
		//判断承运方式
		var shortBargeCarrierMode = $("#addTruck select[name=shortBargeCarrierMode] option:selected").val();
		if(shortBargeCarrierMode==1){
			var array = new Array();
			$("#forms1 form").each(function(j,item){
				var data = JSON.stringify($(this).serializeJSON());
				array[j]=data;
			})
			$('#addTruck input[name="shortBargeCarrierName1"]').val("["+array+"]");
		}else{
			var transportPrice= $("#addTruck input[name=transportPrice]:eq(0)").val();
		 	var deductionRate= $("#addTruck input[name=deductionRate]:eq(0)").val();
		  	var deductionPrice= $("#addTruck input[name=deductionPrice]:eq(0)").val();
		    var payment= $("#addTruck  select[name=payment]:eq(0) option:selected").val();
			var shortBargeJson="{shortBargeName: '平台',transportPrice: '"+transportPrice+"',deductionRate: '"+deductionRate+"',deductionPrice:'"+deductionPrice+"',payment:'"+payment+"'}";  
			shortBargeJson = eval("(" + shortBargeJson + ")"); 
			var data =  JSON.stringify(shortBargeJson);
			$('#addTruck input[name="shortBargeCarrierName"]').val("["+data+"]");
		}
		//序列化表单
		formParam = $('#addTruck').serialize();//序列化表格内容为字符串 
	}
	if(transportType==1){//接取
		//判断承运方式
		var shortBargeCarrierMode = $("#addReceve select[name=shortBargeCarrierMode] option:selected").val();
		if(shortBargeCarrierMode==1){
			var array = new Array();
			$("#forms2 form").each(function(j,item){
				var data = JSON.stringify($(this).serializeJSON());
				array[j]=data;
			})
			$('#addReceve input[name="shortBargeCarrierName"]').val("["+array+"]");
		}else{
			var transportPrice= $("#addReceve input[name=transportPrice]:eq(0)").val();
		 	var deductionRate= $("#addReceve input[name=deductionRate]:eq(0)").val();
		  	var deductionPrice= $("#addReceve input[name=deductionPrice]:eq(0)").val();
		    var payment= $("#addReceve  select[name=payment]:eq(0) option:selected").val();
			var shortBargeJson="{shortBargeName: '平台',transportPrice: '"+transportPrice+"',deductionRate: '"+deductionRate+"',deductionPrice:'"+deductionPrice+"',payment:'"+payment+"'}";  
			shortBargeJson = eval("(" + shortBargeJson + ")"); 
			var data =  JSON.stringify(shortBargeJson);
			$('#addReceve input[name="shortBargeCarrierName"]').val("["+data+"]");
		}
	
		//序列化表单
		formParam = $('#addReceve').serialize();//序列化表格内容为字符串 
	}
	if(transportType==2){//送达
		//判断承运方式
		var shortBargeCarrierMode = $("#addSend select[name=shortBargeCarrierMode] option:selected").val();
		if(shortBargeCarrierMode==1){
			var array = new Array();
			$("#forms3 form").each(function(j,item){
				var data = JSON.stringify($(this).serializeJSON());
				array[j]=data;
			})
			$('#addSend input[name="shortBargeCarrierName"]').val("["+array+"]");
		}else{
			var transportPrice= $("#addSend input[name=transportPrice]:eq(0)").val();
		 	var deductionRate= $("#addSend input[name=deductionRate]:eq(0)").val();
		  	var deductionPrice= $("#addSend input[name=deductionPrice]:eq(0)").val();
		    var payment= $("#addSend  select[name=payment]:eq(0) option:selected").val();
			var shortBargeJson="{shortBargeName: '平台',transportPrice: '"+transportPrice+"',deductionRate: '"+deductionRate+"',deductionPrice:'"+deductionPrice+"',payment:'"+payment+"'}";  
			shortBargeJson = eval("(" + shortBargeJson + ")"); 
			var data =  JSON.stringify(shortBargeJson);
			$('#addSend input[name="shortBargeCarrierName"]').val("["+data+"]");
		}
	
		//序列化表单
		formParam = $('#addSend').serialize();//序列化表格内容为字符串 
	}
	if(transportType==3){//火运
		//序列化表单
		formParam = $('#addTrain').serialize();//序列化表格内容为字符串 
	}
	if(transportType==4){//接取+火运
		//判断承运方式
		var shortBargeCarrierMode = $("#addReceveAndTrain select[name=shortBargeCarrierMode] option:selected").val();
		if(shortBargeCarrierMode==1){
			var array = new Array();
			$("#forms4 form").each(function(j,item){
				var data = JSON.stringify($(this).serializeJSON());
				array[j]=data;
			})
			$('#addReceveAndTrain input[name="shortBargeCarrierName"]').val("["+array+"]");
		}else{
			var transportPrice= $("#addReceveAndTrain input[name=transportPrice]:eq(0)").val();
		 	var deductionRate= $("#addReceveAndTrain input[name=deductionRate]:eq(0)").val();
		  	var deductionPrice= $("#addReceveAndTrain input[name=deductionPrice]:eq(0)").val();
		    var payment= $("#addReceveAndTrain select[name=payment]:eq(0) option:selected").val();
			var shortBargeJson="{shortBargeName: '平台',transportPrice: '"+transportPrice+"',deductionRate: '"+deductionRate+"',deductionPrice:'"+deductionPrice+"',payment:'"+payment+"'}";  
			shortBargeJson = eval("(" + shortBargeJson + ")"); 
			var data =  JSON.stringify(shortBargeJson);
			$('#addReceveAndTrain input[name="shortBargeCarrierName"]').val("["+data+"]");
		}
	
		//序列化表单
		formParam = $('#addReceveAndTrain').serialize();//序列化表格内容为字符串 
	}
	if(transportType==5){//火运+送达
		//判断承运方式
		var shortBargeCarrierMode = $("#addTrainAndSend select[name=shortBargeCarrierMode] option:selected").val();
		if(shortBargeCarrierMode==1){
			var array = new Array();
			$("#forms5 form").each(function(j,item){
				var data = JSON.stringify($(this).serializeJSON());
				array[j]=data;
			})
			$('#addTrainAndSend input[name="shortBargeCarrierName"]').val("["+array+"]");
		}else{
			var transportPrice= $("#addTrainAndSend input[name=transportPrice]:eq(0)").val();
		 	var deductionRate= $("#addTrainAndSend input[name=deductionRate]:eq(0)").val();
		  	var deductionPrice= $("#addTrainAndSend input[name=deductionPrice]:eq(0)").val();
		    var payment= $("#addTrainAndSend  select[name=payment]:eq(0) option:selected").val();
			var shortBargeJson="{shortBargeName: '平台',transportPrice: '"+transportPrice+"',deductionRate: '"+deductionRate+"',deductionPrice:'"+deductionPrice+"',payment:'"+payment+"'}";  
			shortBargeJson = eval("(" + shortBargeJson + ")"); 
			var data =  JSON.stringify(shortBargeJson);
			$('#addTrainAndSend input[name="shortBargeCarrierName"]').val("["+data+"]");
		}
		//序列化表单
		formParam = $('#addTrainAndSend').serialize();//序列化表格内容为字符串 
	}
	if(transportType==6){//联运
		//判断承运方式
		var shortBargeCarrierMode = $("#addUnion select[name=shortBargeCarrierMode] option:selected").val();
		if(shortBargeCarrierMode==1){
			var array1 = new Array();
			$("#forms6 form").each(function(j,item){
				var data = JSON.stringify($(this).serializeJSON());
				array1[j]=data;
			})
			$('#addUnion input[name="shortBargeCarrierName"]').val("["+array1+"]");
		}else{
			var transportPrice= $("#addUnion input[name=transportPrice]:eq(0)").val();
		 	var deductionRate= $("#addUnion input[name=deductionRate]:eq(0)").val();
		  	var deductionPrice= $("#addUnion input[name=deductionPrice]:eq(0)").val();
		    var payment= $("#addUnion  select[name=payment]:eq(0) option:selected").val();
			var shortBargeJson="{shortBargeName: '平台',transportPrice: '"+transportPrice+"',deductionRate: '"+deductionRate+"',deductionPrice:'"+deductionPrice+"',payment:'"+payment+"'}";  
			shortBargeJson = eval("(" + shortBargeJson + ")"); 
			var data =  JSON.stringify(shortBargeJson);
			$('#addUnion input[name="shortBargeCarrierName"]').val("["+data+"]");
		}
		//判断承运方式
		var sendShortBargeCarrierMode = $("#addReceveAndTrain select[name=sendShortBargeCarrierMode] option:selected").val();
		if(sendShortBargeCarrierMode==1){
			var array2 = new Array();
			$("#forms7 form").each(function(j,item){
				var data = JSON.stringify($(this).serializeJSON());
				array2[j]=data;
			})
			$('#addUnion input[name="sendShortBargeCarrierName"]').val("["+array2+"]");
		}else{
			var transportPrice= $("#addUnion input[name=transportPrice]:eq(1)").val();
		 	var deductionRate= $("#addUnion input[name=deductionRate]:eq(1)").val();
		  	var deductionPrice= $("#addUnion input[name=deductionPrice]:eq(1)").val();
		    var payment= $("#addUnion  select[name=payment]:eq(1) option:selected").val();
			var shortBargeJson="{shortBargeName: '平台',transportPrice: '"+transportPrice+"',deductionRate: '"+deductionRate+"',deductionPrice:'"+deductionPrice+"',payment:'"+payment+"'}";  
			shortBargeJson = eval("(" + shortBargeJson + ")"); 
			var data =  JSON.stringify(shortBargeJson);
			$('#addUnion input[name="sendShortBargeCarrierName"]').val("["+data+"]");
		}
		//序列化表单
		formParam = $('#addUnion').serialize();//序列化表格内容为字符串 
	}
	if(transportType==7){//接取+送达
		//alert("接取+送达");
		//判断承运方式
		var shortBargeCarrierMode = $("#addReceveAndSend select[name=shortBargeCarrierMode] option:selected").val();
		if(shortBargeCarrierMode==1){
			var array1 = new Array();
			$("#forms8 form").each(function(j,item){
				var data = JSON.stringify($(this).serializeJSON());
				array1[j]=data;
			})
			$('#addReceveAndSend input[name="shortBargeCarrierName"]').val("["+array1+"]");
		}else{
			var transportPrice= $("#addReceveAndSend input[name=transportPrice]:eq(0)").val();
		 	var deductionRate= $("#addReceveAndSend input[name=deductionRate]:eq(0)").val();
		  	var deductionPrice= $("#addReceveAndSend input[name=deductionPrice]:eq(0)").val();
		    var payment= $("#addReceveAndSend  select[name=payment]:eq(0) option:selected").val();
			var shortBargeJson="{shortBargeName: '平台',transportPrice: '"+transportPrice+"',deductionRate: '"+deductionRate+"',deductionPrice:'"+deductionPrice+"',payment:'"+payment+"'}";  
			shortBargeJson = eval("(" + shortBargeJson + ")"); 
			var data =  JSON.stringify(shortBargeJson);
			$('#addReceveAndSend input[name="shortBargeCarrierName"]').val("["+data+"]");
		}
		//判断承运方式
		var sendShortBargeCarrierMode = $("#addReceveAndSend select[name=sendShortBargeCarrierMode] option:selected").val();
		if(sendShortBargeCarrierMode==1){
			var array2 = new Array();
			$("#forms9 form").each(function(j,item){
				var data = JSON.stringify($(this).serializeJSON());
				array2[j]=data;
			})
			$('#addReceveAndSend input[name="sendShortBargeCarrierName"]').val("["+array2+"]");
		}else{
			var transportPrice= $("#addReceveAndSend input[name=transportPrice]:eq(1)").val();
		 	var deductionRate= $("#addReceveAndSend input[name=deductionRate]:eq(1)").val();
		  	var deductionPrice= $("#addReceveAndSend input[name=deductionPrice]:eq(1)").val();
		    var payment= $("#addReceveAndSend  select[name=payment]:eq(1) option:selected").val();
			var shortBargeJson="{shortBargeName: '平台',transportPrice: '"+transportPrice+"',deductionRate: '"+deductionRate+"',deductionPrice:'"+deductionPrice+"',payment:'"+payment+"'}";  
			shortBargeJson = eval("(" + shortBargeJson + ")"); 
			var data =  JSON.stringify(shortBargeJson);
			$('#addReceveAndSend input[name="sendShortBargeCarrierName"]').val("["+data+"]");
		}
		//序列化表单
		formParam = $('#addReceveAndSend').serialize();//序列化表格内容为字符串 
	}
	$.ajax({  
    	type:'post',      
   		url:'${pageContext.request.contextPath}/projectManagment/addProject.do', 
        data:formParam,
        cache:false,
        dataType:'json',
    	success:function(data){
    		if(data.status==200){
    			$("#creatProjectModal").modal("hide");
    			$("#successMsg").html("");
				$("#successMsg").html("新增项目成功");
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

<!--运费合计-->
<!--<script>
	function freightTotalSum(){
		
		var freight = parseInt($('#trainAndSend form[name=trainCost] input[name=freight]').val());
		var materialCost = parseInt($('#trainAndSend form[name=trainCost] input[name=materialCost]').val());
		var tarpaulinCost = parseInt($('#trainAndSend form[name=trainCost] input[name=tarpaulinCost]').val());
		var beginStevedoringCost = parseInt($('#trainAndSend form[name=trainCost] input[name=beginStevedoringCost]').val());
		var endStevedoringCost = parseInt($('#trainAndSend form[name=trainCost] input[name=endStevedoringCost]').val());
		
		$('#trainAndSend form[name=trainCost] input[name=freightSum]').val(freight+materialCost+tarpaulinCost+beginStevedoringCost+endStevedoringCost);
	}
	
</script>-->


<!--添加短驳承运方-->
<script>
	 $('.addBtns').on("click", function(){
			var className=this.className.split(" ")[1];
			var type=className.substr(className.length-1,1);
    	$("#motor"+type).append("<form><div class='form-inline'>"+
    		"<div class='form-group'><label>&emsp;短驳承运方：</label> <select name='shortBargeId' onchange='changeAndSetValue(this)' >"+
   			"<c:forEach items='${carTeams }' var='carTeam' >"+
			"<option  value='${carTeam.id }'>${carTeam.carItemName }"+
		"</option></c:forEach></select>	<input type='hidden' name='shortBargeName' value='${carTeams[0].carItemName }' /></div>"+
        	"<div class='form-group short-client'><label>运输单价：</label><input name='transportPrice' type='text'><span>元</span></div>"+
        	"<div class='form-group short-client'><label>扣损比率：</label><input name='deductionRate' type='text'><span>‰</span></div>"+
        	"<div class='form-group short-client'><label>扣损单价：</label><input name='deductionPrice' type='text'><span>元</span></div>"+
        	"<div class='form-group short-client'><label>付款周期：</label> <select name='payment'><option value='0'>现金</option></select></div>"+
       "</div ></form>")
    })
</script>
 <!--新建项目添加更多承运方式-->
<script>
		for(var i=1;i<=9;i++){
			$(".addBtn"+i).hide();
			$("#select"+i).addClass("bgclass");
		} 
	 $(".shortSelect").change(function(){
		var className=this.className.split(" ")[1];
		var type=className.substr(className.length-1,1);	 
		var mValue = $(this).val();
	 	if(mValue=="0"){
	 		$("div[name='platformShortBarge"+type+"']").html("<div class='form-inline'>"+
		    		"<div class='form-group'><label>&emsp;短驳承运方：</label><input type='text' readonly='readonly' name='shortBargeName' value='平台' /></div>"+
		        	"<div class='form-group short-client'><label>运输单价：</label><input name='transportPrice' type='text'><span>元</span></div>"+
		        	"<div class='form-group short-client'><label>扣损比率：</label><input name='deductionRate' type='text'><span>‰</span></div>"+
		        	"<div class='form-group short-client'><label>扣损单价：</label><input name='deductionPrice' type='text'><span>元</span></div>"+
		        	"<div class='form-group short-client'><label>付款周期：</label> <select name='payment'>"+
		        	"<option value='0'>日结</option>"+
		        	"<option value='1'>周结</option>"+
		        	"<option value='2'>月结</option>"+
		        	"</select></div>"+
		       		"</div >")
	 		$("#select"+type).attr("readOnly","true");
	 		$("#select"+type).addClass("bgclass")
	 		$(".addBtn"+type).hide();
	 		$("#motor"+type).children().remove();
	 	}
	 	else if(mValue=="1"){
	 		$("div[name='platformShortBarge"+type+"']").html("");
	 		$("#select"+type).attr("readOnly","false");
	 		$("#select"+type).removeClass("bgclass")
	 		$(".addBtn"+type).show();
	 		$("#motor"+type).children().remove();
	 	}
	})
	
</script>

<!--逻辑删除项目-->
<script>
	function delProject(){
		var id = $("#delProject").val();
		var reason=$("#reason").val();
		$.ajax({  
	    	type:'post',      
	   		url:'${pageContext.request.contextPath}/projectManagment/delProject.do', 
	        data:{id:id,reason:reason},
	        cache:false,
	        dataType:'json',
	    	success:function(data){
	    		if(data.status==200){
	    			$("#deletReasonModal").modal("hide");
	    			$("#successMsg").html("");
					$("#successMsg").html("删除项目成功");
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

<!--完成-->
<script>
	$("#complete").click(function(){
		$("#delProject").val("");
		if($("input:checkbox[name='WaybillName']:checked").length > 1) {
			//$("#deletRemindModal").modal();
		} else if($("input:checkbox[name='WaybillName']:checked").length == 0) {
			//$("#nullModal").modal();
		} else {
			//$(".textareas").val("");
			$("#delProject").val($("input:checkbox[name='WaybillName']:checked").val());
			//$("#deletReasonModal").modal();
		}
		var id = $("#delProject").val();
		$.ajax({
	    	type:'post',      
	   		url:'${pageContext.request.contextPath}/projectManagment/completeProject.do', 
	        data:{id:id},
	        cache:false,
	        dataType:'json',
	    	success:function(data){
	    		if(data.status==200){
	    			$("#deletReasonModal").modal("hide");
	    			$("#successMsg").html("");
					$("#successMsg").html("项目确认完成!");
					$("#successModal").modal();
	     		}else{
	      			$("#msgContent").html("");
	    			$("#msgContent").html(data.msg);
	    			$("#deletnullModal").modal();
	      		}
		 	}
		})
		
	})
	
</script>

<!--项目还原-->
<script>
    function restProject(){
    	var id = $("#restId").val();
		$.ajax({
	    	type:'post',      
	   		url:'${pageContext.request.contextPath}/projectManagment/restoreProject.do', 
	        data:{id:id},
	        cache:false,
	        dataType:'json',
	    	success:function(data){
	    		if(data.status==200){
	    			$("#deletRemindModal").modal("hide");
	    			$("#successMsg").html("");
					$("#successMsg").html("项目还原成功!");
					$("#successModal").modal();
	     		}else{
	     			$("#deltialModal").modal("hide");
	      			$("#msgContent").html("");
	    			$("#msgContent").html(data.msg);
	    			$("#deletnullModal").modal();
	      		}
		 	}
		})
    }
</script>

<!--修改项目信息  -->
<script type="text/javascript">
//修改
	$("#modify").click(function() {
			//获取当前选中的对象
		
		if($("input:checkbox[name='WaybillName']:checked").length > 1) {
			$('#warnMsg').html('抱歉,不能批量修改!');
			$("#deletRemindModal").modal();
			return;
		} else if($("input:checkbox[name='WaybillName']:checked").length == 0) {
			$("#msgContent").html("请选择一条您要操作的数据");
   			$("#deletnullModal").modal();
			return;
		} 
		$("input:checkbox[name='WaybillName']:checked").parent().parent().parent().find("td").click();
  		var mtext =	$("input:checkbox[name='WaybillName']:checked").parent().parent().parent().find("td").eq(3).html();
  		$("#mtext").val(mtext);
  		$(".modifyModal .modal-title").html("项目修改");
//		$(".detail-whole").hide();
//		$(".modify-whole").show();
		$(".history_modify").hide();
		$(".confirm-modify").show();
		$(".modify-info").show();
		$('.hidebtn').show();
	
		if($("#mtext").val() == "汽运"){
			$("#truckModal").modal();
			 $("#mtext").val("");
		}else if($("#mtext").val() == "接取"){
					$("#receveModal").modal();
		}
		else if($("#mtext").val() == "送达"){
					$("#sendModal").modal();
		}
		else if($("#mtext").val() == "火运"){
					$("#trainModal").modal();
		}
		else if($("#mtext").val() == "接取+火运"){
					$("#receveAndTrainModal").modal();
		}
		else if($("#mtext").val() == "火运+送达"){
					$("#trainAndSendModal").modal();
		}
		else if($("#mtext").val() == "接取+送达"){
					$("#receveAndSendModal").modal();
		}
		else if($("#mtext").val() == "联运"){
					$("#unionModal").modal();
		}
		});
//	修改
   $(".modifyModal input").attr("readonly",true);
   $(".modifyModal input").addClass("disabledBg");
   $(".modifyModal select").attr("readonly",true);
   $(".modifyModal select").addClass("disabledBg");
   $(".modify-info").click(function(){
   	$(this).parent().find("input").removeAttr("readonly");
	$(this).parent().find("input").removeAttr("readonly").removeClass("disabledBg");
   	$(this).parent().find("select").removeAttr("readonly").removeClass("disabledBg");
   			$(this).hide().prev().show();
   })

   $(".confirm-modify").click(function(){
   	$(this).hide().next().show();
  	$(this).parent().find("input").attr("readonly","true");
	$(this).parent().find("input").attr("readonly","true").addClass("disabledBg");
   	$(this).parent().find("select").attr("readonly","true").addClass("disabledBg");
   })
  
</script>

<!--获取项目详情-->
<script>
	function getProjectDetail(projectId){
    	$.ajax({
    	type:'post',
   		url:'/projectManagment/getProject.do', 
        data:{id:projectId},
        cache:false,
        dataType:'json',
    	success:function(data){
    		if(data.status==200){
    			var projectDetail = data.data;
    			
    			//项目类型
    			var projectType="";
    			if(projectDetail.projectType==0){
    				projectType="集装箱";
    			}else if(projectDetail.projectType==1){
    				projectType="散装";
    			}
    			
    			//模态框id
    			var modal = "";
    			
    			//联运模式
    			var transportType="";
    			if(projectDetail.transportType==0){
    				transportType="汽运";
    				modal="truckModal";
    			}else if(projectDetail.transportType==1){
    				transportType="接取";
    				modal="receveModal";
    			}else if(projectDetail.transportType==2){
    				transportType="送达";
    				modal="sendModal";
    			}else if(projectDetail.transportType==3){
    				transportType="火运";
    				modal="trainModal";
    			}else if(projectDetail.transportType==4){
    				transportType="接取+火运";
    				modal="receveAndTrainModal";
    			}else if(projectDetail.transportType==5){
    				transportType="火运+送达";
    				modal="trainAndSendModal";
    			}else if(projectDetail.transportType==6){
    				transportType="联运";
    				modal="unionModal";
    			}else if(projectDetail.transportType==7){
    				transportType="接取+送达";
    				modal="receveAndSendModal";
    			}
    			if(modal==""){
    				return;
    			}
    			$("#"+modal+" span[name=branchGroupName]").html(projectDetail.branchGroupName);
    			
    			$("#"+modal+" span[name=projectType").html(projectType);
    			
    			$("#"+modal+" input[name=id]").val(projectDetail.id);
    			
    			$("#"+modal+" span[name=branchGroupName]").html(projectDetail.branchGroupName);
    			
    			$("#"+modal+" span[name=branchGroupName]").html(projectDetail.branchGroupName);
    			
    			$("#"+modal+" span[name=transportType]").html(transportType);
    			//委托方
    			var principal="";
    			if(projectDetail.principal==0){
    				principal="收货企业";
    			}else if(projectDetail.principal==1){
    				principal="发货企业";
    			}
    			$("#"+modal+" span[name=principal]").html(principal);
    			
    			//完成时间
    			$("#"+modal+" span[name=finishDate]").html(projectDetail.finishDate);
    			
    			//货物品名
    			$("#"+modal+" span[name=cargoName]").html(projectDetail.cargoName);
    			//货物规格
    			$("#"+modal+" span[name=cargoSpecifications]").html(projectDetail.cargoSpecifications);
    			//货物价格
    			$("#"+modal+" span[name=cargoPrice]").html(projectDetail.cargoPrice);
    			//计价单位
    			var  valuationUnitName="";
    			if(projectDetail.valuationUnitName==0){
    				valuationUnitName="吨";
    			}else if(projectDetail.valuationUnitName==1){
    				valuationUnitName="位";
    			}
    			$("#"+modal+" span[name=valuationUnitName]").html(valuationUnitName);
    			
    			//发货企业
    			$("#"+modal+" span[name=sendCargoCompanyName]").html(projectDetail.sendCargoCompanyName);
    			$("#"+modal+" span[name=sendCargoCompanyPhone]").html(projectDetail.sendCargoCompany.stationPhone);
    			$("#"+modal+" span[name=sendCargoCompanyAddress]").html(projectDetail.sendCargoCompany.addressCode+projectDetail.sendCargoCompany.detailAddress);
    			//收货企业
    			$("#"+modal+" span[name=receiveCargoCompanyName]").html(projectDetail.receiveCargoCompanyName);
    			$("#"+modal+" span[name=receiveCargoCompanyPhone]").html(projectDetail.receiveCargoCompany.stationPhone);
    			$("#"+modal+" span[name=receiveCargoCompanyAddress]").html(projectDetail.receiveCargoCompany.addressCode+projectDetail.receiveCargoCompany.detailAddress);
    			//发货单位
    			$("#"+modal+" span[name=sendCargoUnitName]").html(projectDetail.sendCargoUnitName);
    			$("#"+modal+" span[name=sendCargoAreaCode]").html(projectDetail.sendCargoUnit.addressCode);
    			$("#"+modal+" span[name=sendCargoAddress]").html(projectDetail.sendCargoUnit.detailAddress);
    			//收货站点
    			$("#"+modal+" span[name=receiveCargoSite]").html(projectDetail.receiveCargoSite);
    			$("#"+modal+" span[name=receiveCargoSiteAreaCode]").html(projectDetail.receiveTrainStation.adressCode);
    			$("#"+modal+" span[name=receiveCargoSiteAddress]").html(projectDetail.receiveTrainStation.detailAddress);
    			
    		//短驳承运方式
			$("#"+modal+" select[name=shortBargeCarrierMode1] option[value='"+projectDetail.shortBargeCarrierMode+"']").attr("selected","selected");
			//短驳承运方
			$("#"+modal+" div[name=shortBargeCarrierName1]").html("");
				$.each(projectDetail.shortBarges, function(index,shortBarge) {
					var shortBarges="<select name='shortBargeId' onchange='changeAndSetValue(this)'><option  value='0'>平台</option></select>";
					if(projectDetail.shortBargeCarrierMode==1){
						shortBarges="<select name='shortBargeId' onchange='changeAndSetValue(this)' ><c:forEach items='${carTeams }' var='carTeam' >"
						+"<option  value='${carTeam.id }'>${carTeam.carItemName }"
						+"</option></c:forEach></select>"
					}
					if(shortBarge.type==0 || shortBarge.type==2){
						$("#"+modal+" div[name=shortBargeCarrierName1]").append("<form class='form-inline' name='"+modal+"Form1"+index+"'><div class='form-group '><label>&emsp;短驳承运方：</label>"
							+shortBarges
							+"<input type='hidden' name='shortBargeName'  /></div>"
							+"<div class='form-group short-client'><label>运输单价：</label><input type='text' name='transportPrice' value='"+shortBarge.transportPrice+"' maxlength='30' ><span>元</span></div>"
							+"<div class='form-group short-client'><label>扣损比率：</label><input type='text'  name='deductionRate'  value='"+shortBarge.deductionRate+"' maxlength='30' ><span>‰</span></div>"
							+"<div class='form-group short-client'><label>扣损单价：</label><input type='text'  name='deductionPrice' value='"+shortBarge.deductionPrice+"' maxlength='30' ><span>元</span></div>"
							+"<div class='form-group short-client'><label>付款周期：</label><select name='payment' ><option value='0'>日结</option>"
			        		+"<option value='1'>周结</option>"
			        		+"<option value='2'>月结</option></select></div></form>");
							$("#"+modal+" form[name='"+modal+"Form1"+index+"'] select[name=payment] option[value='"+shortBarge.payment+"']").attr("selected","selected");
							$("#"+modal+" form[name='"+modal+"Form1"+index+"'] select[name=shortBargeId] option[value='"+shortBarge.shortBargeId+"']").attr("selected","selected");
					
					}
					
				});	
				$("#"+modal+" select[name=shortBargeId]").change();
    			
	    			//起始中心站
				$("#"+modal+" span[name=beginCenterSiteName]").html(projectDetail.beginCenterSiteName);
				$("#"+modal+" span[name=beginSiteName]").html(projectDetail.beginSiteName);
				$("#"+modal+" span[name=beginAddress]").html(projectDetail.beginAddress);
				
				//到达中心站
				$("#"+modal+" span[name=endCenterSiteName]").html(projectDetail.endCenterSiteName);
				$("#"+modal+" span[name=endSiteName]").html(projectDetail.endSiteName);
				$("#"+modal+" span[name=endAddress]").html(projectDetail.endAddress);
			
			
				//运费
				$("#"+modal+" input[name=freight]").val(projectDetail.freight);
				$("#"+modal+" input[name=materialCost]").val(projectDetail.materialCost);
				$("#"+modal+" input[name=tarpaulinCost]").val(projectDetail.tarpaulinCost);
				$("#"+modal+" input[name=beginStevedoringCost]").val(projectDetail.beginStevedoringCost);
				$("#"+modal+" input[name=endStevedoringCost]").val(projectDetail.endStevedoringCost);
				$("#"+modal+" input[name=freightSum]").val(projectDetail.freightSum);
	    			
	    		//接取站点
				$("#"+modal+" span[name=forwardingSiteName]").html(projectDetail.forwardingSiteName);
				$("#"+modal+" span[name=forwardingSiteAreaCode]").html(projectDetail.sendTrainStation.adressCode);
				$("#"+modal+" span[name=forwardingSiteAddress]").html(projectDetail.sendTrainStation.detailAddress);
				//收货单位
				$("#"+modal+" span[name=receivingDepartmentName]").html(projectDetail.receivingDepartmentName);
				$("#"+modal+" span[name=receivingDepartmentAreaCode]").html(projectDetail.receivingDepartment.addressCode);
				$("#"+modal+" span[name=receivingDepartmentAddress]").html(projectDetail.receivingDepartment.detailAddress);
    		
				//短驳承运方式
				$("#Modal select[name=shortBargeCarrierMode2] option[value='"+projectDetail.sendShortBargeCarrierMode+"']").attr("selected","selected");
				//短驳承运方
				$("#"+modal+" div[name=shortBargeCarrierName2]").html("");
				$.each(projectDetail.shortBarges, function(index,shortBarge) {
					var shortBarges="<select name='shortBargeId' onchange='changeAndSetValue(this)'><option  value='0'>平台</option></select>";
					if(projectDetail.sendShortBargeCarrierMode==1){
						shortBarges="<select name='shortBargeId' onchange='changeAndSetValue(this)' ><c:forEach items='${carTeams }' var='carTeam' >"
						+"<option  value='${carTeam.id }'>${carTeam.carItemName }"
						+"</option></c:forEach></select>"
					}
					
					if(shortBarge.type==1){
						$("#"+modal+" div[name=shortBargeCarrierName2]").append("<form class='form-inline' name='"+modal+"Form2"+index+"'><div class='form-group '><label>&emsp;短驳承运方：</label>"
							+shortBarges
							+"<input type='hidden' name='shortBargeName'  /></div>"
							+"<div class='form-group short-client'><label>运输单价：</label><input type='text' name='transportPrice' value='"+shortBarge.transportPrice+"' maxlength='30'  id='select02'><span>元</span></div>"
							+"<div class='form-group short-client'><label>扣损比率：</label><input type='text'  name='deductionRate'  value='"+shortBarge.deductionRate+"' maxlength='30' id='sele03'><span>‰</span></div>"
							+"<div class='form-group short-client'><label>扣损单价：</label><input type='text'  name='deductionPrice' value='"+shortBarge.deductionPrice+"' maxlength='30' ><span>元</span></div>"
							+"<div class='form-group short-client'><label>付款周期：</label><select name='payment' ><option value='0'>日结</option>"
			        		+"<option value='1'>周结</option>"
			        		+"<option value='2'>月结</option></select></div></form>");
							$("#"+modal+"  form[name='"+modal+"Form2"+index+"'] select[name=payment] option[value='"+shortBarge.payment+"']").attr("selected","selected");
							$("#"+modal+"  form[name='"+modal+"Form2"+index+"'] select[name=shortBargeId] option[value='"+shortBarge.shortBargeId+"']").attr("selected","selected");
					}
				});
				$("#"+modal+" select[name=shortBargeId]").change();
	    			
				//送达单价
				$("#"+modal+" input[name=sendArrivePrice]").val(projectDetail.arrivePrice);
			
				//接取单价
				$("#"+modal+" input[name=recevePickUpPrice]").val(projectDetail.pickUpPrice);
				
				//火运单价
				$("#"+modal+" input[name=trainTrainPrice]").val(projectDetail.trainPrice);
				
				//汽运单价
				$("#"+modal+" input[name=truckTransportPrice]").val(projectDetail.transportPrice);
				
				//备注
				$("#"+modal+" div[name=remark]").html(projectDetail.remark);
			
				if(projectDetail.status==0){
					var operatorName="";//操作人
	    			var createDate="";//删除时间
	    			var content="";//删除原因
					$.each(projectDetail.operationLogs,function(index,operator){
	    				if(index==0){
	    					operatorName=operator.operatorName;
	    					createDate=operator.createDate;
	    					content=operator.content;
	    				}
    				})
					$("#"+modal+" span[name=delUser]").html(operatorName);
					$("#"+modal+" span[name=delDate]").html(createDate);
					$("#"+modal+" span[name=delReason]").html(content);
				}
			
			   $(".modifyModal input").attr("readonly",true);
			   $(".modifyModal input").addClass("disabledBg");
			   $(".modifyModal select").attr("readonly",true);
			   $(".modifyModal select").addClass("disabledBg");
				if($("#mtext").val()==null || $("#mtext").val()==undefined || $("#mtext").val()==""   ){
					$("#"+modal).modal();
				}
     		}else{
      			$("#msgContent").html("");
    			$("#msgContent").html(data.msg);
    			$("#deletnullModal").modal();
      		}
	 	}
	})	
}

	
</script>

<!--更新项目-->
<script>
	function updateProject(type,modal){
   	
   		var projectJson = "";//提交到后台的json数据
   		//获取项目id的value
   		var projectId = $("#"+modal+" input[name=id]").val();
   		//获取短驳承运方式
   		var shortBargeCarrierMode = $("#"+modal+"  select[name=shortBargeCarrierMode1]").val();
   	 
   		//获取短驳承运方
		var array = new Array();
		$("#" + modal + " div[name=shortBargeCarrierName1] form").each(function(j, item) {
		   	var json = $(item).serialize();
		   	var data = JSON.stringify($(this).serializeJSON());
		   	array[j] = data;
	   	})
	   	var shortBargeCarrierName ="[" + array + "]";
	   
  	 	if(type==0){//汽运
			//获取汽运单价
			var transportPrice = $('#truckModal input[name=truckTransportPrice]').val();
			projectJson = "{id: " + projectId + ",transportType: '" + type + "',shortBargeCarrierMode: '" + shortBargeCarrierMode + "',shortBargeCarrierName:'" + shortBargeCarrierName + "',transportPrice:'" + transportPrice + "'}";
			projectJson = eval("(" + projectJson + ")");
			projectJson = JSON.stringify(projectJson);
		}else if(type == 1) {//更新接取项目
			//获取接取单价
			var pickUpPrice =  $('#receveModal input[name=recevePickUpPrice]').val();
			var projectJson="{id: "+projectId+",transportType: '"+type+"',shortBargeCarrierMode: '"+shortBargeCarrierMode+"',shortBargeCarrierName:'"+shortBargeCarrierName+"',pickUpPrice:'"+pickUpPrice+"'}";  
			projectJson = eval("(" + projectJson + ")"); 
			projectJson =  JSON.stringify(projectJson);
	  	}else if(type==2 ){//更新送达项目
	 	 	//获取短驳承运方式
	 	    shortBargeCarrierMode = $('#sendModal select[name=shortBargeCarrierMode2]').val();
	 	   	//获取短驳承运方
	 	    $('#sendModal   div[name=shortBargeCarrierName2] form').each(function(j,item){
	 			var data = JSON.stringify($(this).serializeJSON());
	 			array[j]=data;
	 		})
	 		shortBargeCarrierName ="["+array+"]";
	 		//获取送达单价
	 		var arrivePrice =  $('#sendModal input[name=sendArrivePrice]').val();
	 		var projectJson="{id: "+projectId+",transportType: '"+type+"',shortBargeCarrierMode: '"+shortBargeCarrierMode+"',shortBargeCarrierName:'"+shortBargeCarrierName+"',arrivePrice:'"+arrivePrice+"'}";  
	 		projectJson = eval("(" + projectJson + ")"); 
	 		projectJson =  JSON.stringify(projectJson);
	  	}else if(type==3){//更新火运项目
	 	   //获取项目id的value
	 	    var trainTrainPrice = $('#trainModal input[name=trainTrainPrice]').val();
	 	    $('#trainModal input[name=trainPrice]').val(trainTrainPrice);
	 	 	projectJson = JSON.stringify($('#trainModal form[name=trainCost]').serializeJSON());   
	  	}else if(type==4){//更新接取+火运项目
		 	$('#receveAndTrainModal input[name=shortBargeCarrierName]').val(shortBargeCarrierName);
		  	
		  	/*接取单价*/
		  	var pickUpPrice = $('#receveAndTrainModal input[name=recevePickUpPrice]').val();
		 	$('#receveAndTrainModal input[name=pickUpPrice]').val(pickUpPrice);
		  	/*火运单价*/
		  	var trainTrainPrice = $('#receveAndTrainModal input[name=trainTrainPrice]').val();
		 	$('#receveAndTrainModal input[name=trainPrice]').val(trainTrainPrice);
	  		/*表单序列化*/
	 	 	projectJson = JSON.stringify($('#receveAndTrainModal form[name=trainCost]').serializeJSON());   
	  	}else if(type==5){//更新火运+送达
		  	/*短驳承运方式*/
		  	shortBargeCarrierMode = $('#trainAndSendModal select[name=shortBargeCarrierMode2]').val();
		 	$('#trainAndSendModal input[name=shortBargeCarrierMode]').val(shortBargeCarrierMode);
		  	
		  	/*短驳承运方*/
	 	    $('#trainAndSendModal   div[name=shortBargeCarrierName2] form').each(function(j,item){
	 			var data = JSON.stringify($(this).serializeJSON());
	 			array[j]=data;
	 		})
	 		shortBargeCarrierName ="["+array+"]";
		 	$('#trainAndSendModal input[name=shortBargeCarrierName]').val(shortBargeCarrierName);
		  	/*送达单价*/
		  	var arrivePrice = $('#trainAndSendModal input[name=sendArrivePrice]').val();
		 	$('#trainAndSendModal input[name=arrivePrice]').val(arrivePrice);
		  	/*火运单价*/
		  	var trainTrainPrice = $('#trainAndSendModal input[name=trainTrainPrice]').val();
		 	$('#trainAndSendModal input[name=trainPrice]').val(trainTrainPrice);
	  		/*表单序列化*/
	 	 	var projectJson = JSON.stringify($('#trainAndSendModal form[name=trainCost]').serializeJSON());   
	  	}else if(type==6){//更新联运
		 	$('#unionModal input[name=shortBargeCarrierName]').val(shortBargeCarrierName);
		 	/*送达短驳承运方式*/
		  	var sendShortBargeCarrierMode = $('#unionModal select[name=shortBargeCarrierMode2]').val();
		 	$('#unionModal input[name=sendShortBargeCarrierMode]').val(sendShortBargeCarrierMode);
		  	/*送达短驳承运方*/
	 	  	var sendArray = new Array();
	 	    $('#unionModal   div[name=shortBargeCarrierName2] form').each(function(j,item){
	 			var data = JSON.stringify($(this).serializeJSON());
	 			sendArray[j]=data;
	 		})
	 		var sendShortBargeCarrierName ="["+sendArray+"]";
		 	$('#unionModal input[name=sendShortBargeCarrierName]').val(sendShortBargeCarrierName);
		 	/*接取单价*/
		  	var pickUpPrice = $('#unionModal input[name=recevePickUpPrice]').val();
		 	$('#unionModal input[name=pickUpPrice]').val(pickUpPrice);
		  	/*送达单价*/
		  	var arrivePrice = $('#unionModal input[name=sendArrivePrice]').val();
		 	$('#unionModal input[name=arrivePrice]').val(arrivePrice);
		  	/*火运单价*/
		  	var trainTrainPrice = $('#unionModal input[name=trainTrainPrice]').val();
		 	$('#unionModal input[name=trainPrice]').val(trainTrainPrice);
	  		/*表单序列化*/
	 	 	projectJson = JSON.stringify($('#unionModal form[name=trainCost]').serializeJSON());   
	  	}else if(type==7){//更新接取+送达
		 	$('#receveAndSendModal input[name=shortBargeCarrierName]').val(shortBargeCarrierName);
		 	
		 	/*送达短驳承运方式*/
		  	var sendShortBargeCarrierMode = $('#receveAndSendModal select[name=shortBargeCarrierMode2]').val();
		 	$('#receveAndSendModal input[name=sendShortBargeCarrierMode]').val(sendShortBargeCarrierMode);
		  	/*短驳承运方*/
	 	  	var sendArray = new Array();
	 	    $('#receveAndSendModal   div[name=shortBargeCarrierName2] form').each(function(j,item){
	 			var data = JSON.stringify($(this).serializeJSON());
	 			sendArray[j]=data;
	 		})
	 		var sendShortBargeCarrierName ="["+sendArray+"]";
		 	$('#receveAndSendModal input[name=sendShortBargeCarrierName]').val(sendShortBargeCarrierName);
		 	/*接取单价*/
		  	var pickUpPrice = $('#receveAndSendModal input[name=recevePickUpPrice]').val();
		 	$('#receveAndSendModal input[name=pickUpPrice]').val(pickUpPrice);
		  	/*送达单价*/
		  	var arrivePrice = $('#receveAndSendModal input[name=sendArrivePrice]').val();
		 	$('#receveAndSendModal input[name=arrivePrice]').val(arrivePrice);
	  		/*表单序列化*/
	 	 	projectJson = JSON.stringify($('#receveAndSendModal form[name=trainCost]').serializeJSON());   
	  	}
	  	
	  	/*异步提交*/
	  	$.ajax({
        	type:'post',      
       		url:'${pageContext.request.contextPath}/projectManagment/updateProject.do',  
	        data:{projectJson:projectJson},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
        			$("#" + modal).hide();
	        		$("#successMsg").html("");
	    			$("#successMsg").html("更新项目信息成功!");
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


<!--查看项目列表 或历史项目或项目回收站-->
<script>
	function listProject(status){
		var page = 'projectPage';
		if(status==1){
			$("#searchForm input[name=status]").val("1");//项目列表
			page = 'projectPage';
		}else if(status==2){
			$("#searchForm input[name=status]").val("2");//历史项目
			page = 'historyPage';
		}else{
			$("#searchForm input[name=status]").val("0");//项目回收站
			page = 'recyclePage';
		}
		
		$.ajax({
	    	type:'post',      
	   		url:'${pageContext.request.contextPath}/projectManagment/getProjectPage.do', 
	        data:{page:1,status:status,search:JSON.stringify($('#searchForm').serializeJSON())},
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

<!--项目列表分页-->
<script>
	//分页
	$("#projectPage").paging({
		pageNo:1, 
		totalPage: ${projectList.totalPage},
		totalSize:${projectList.limit},
		callback: function(num) {
			searchByPage(num);
		}
	})
</script>

<!--执行分页查询的方法 -->		
<script>
	function searchByPage(num){
		var status = $('#searchForm input[name=status]').val();
		$.ajax({
        	type:'post',      
       		url:'${pageContext.request.contextPath}/projectManagment/getProjectPage.do',  
	       	data:{page:num,status:status,search:JSON.stringify($('#searchForm').serializeJSON())},
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

<!--将查询结果写入到Table中-->
<script>
	function htmlTable(results){
		var projectList = results;
		var tbody = "projectTbody";
		if($('#searchForm input[name=status]').val()==1){/*项目列表*/
			tbody ="projectTbody";
		}else if($('#searchForm input[name=status]').val()==2){/*历史项目*/
			tbody ="historyProjectTbody";
		}else{/*项目回收站*/
			tbody ="recycleTbody"
		}
		
		$("#"+tbody+"").html("");
		
		$.each(projectList,function(index,project){
			//项目类型
			var projectType="";
			if(project.projectType==0){
				projectType="集装箱";
			}else if(project.projectType==1){
				projectType="散装";
			}
			//联运模式
			var transportType="";
			if(project.transportType==0){
				transportType="汽运";
			}else if(project.transportType==1){
				transportType="接取";
			}else if(project.transportType==2){
				transportType="送达";
			}else if(project.transportType==3){
				transportType="火运";
			}else if(project.transportType==4){
				transportType="接取+火运";
			}else if(project.transportType==5){
				transportType="火运+送达";
			}else if(project.transportType==6){
				transportType="联运";
			}else if(project.transportType==7){
				transportType="接取+送达";
			}
			//计价单位
			var  valuationUnitName="";
			if(project.valuationUnitName==0){
				valuationUnitName="吨";
			}else if(project.valuationUnitName==1){
				valuationUnitName="位";
			}
			//短驳承运方式
			var shortBargeCarrierMode=""
			if(parseInt(project.shortBargeCarrierMode)==0){
				shortBargeCarrierMode="平台; ";
			}else if(parseInt(project.shortBargeCarrierMode)==1){
				shortBargeCarrierMode="自选; ";
			}
			
			var sendShortBargeCarrierMode=""
			if(parseInt(project.sendShortBargeCarrierMode)==0){
				sendShortBargeCarrierMode="平台";
			}else if(parseInt(project.sendShortBargeCarrierMode)==1){
				sendShortBargeCarrierMode="自选";
			}
			
			
			var operatorName="";//操作人
			var operatorDate="";//删除时间
			var content="";//删除原因
			
			if($('#searchForm input[name=status]').val()==0){/*项目回收站*/
    			$.each(project.operationLogs,function(index,operator){
					if(index==0){
						operatorName=operator.operatorName;
						operatorDate=operator.createDate;
						content=operator.content;
					}
				})
   			}else{
   				operatorDate = project.finishDate;
   			}
			
    			$("#"+tbody+"").append("<tr id='"+project.id+"'><td><label class='demo--label'>"
					+"<input class='demo--checkbox' type='checkbox' value='"+project.id+"' name='WaybillName'>"
					+"<span class='demo--checkboxInput'></span></label></td>"
					+"<td ><a href='' data-toggle='modal' data-target='#detailModal-one'>"+project.projectCode+"</a></td>"	                   
					+"<td >"+projectType+"</td>"
					+"<td >"+transportType+"</td>"
					+"<td >"+project.branchGroupName+"</td>"
					+"<td >"+project.sendCargoCompanyName+"</td>"
					+"<td >"+project.receiveCargoCompanyName+"</td>"
					+"<td >"+project.cargoName+"</td>"
					+"<td >"+shortBargeCarrierMode+sendShortBargeCarrierMode+"</td>"
					+"<td >"+project.shortBargeCarrierName+"</td>"
					+"<td >"+valuationUnitName+"</td>"
					+"<td >"+project.remark+"</td>"
					+"<td name='delUser'>"+operatorName+"</td>" 				/*删除人*/
					+"<td name='operatedate'>"+operatorDate+"</td>" 				/*完成时间或删除时间*/
					+"<td name='delReason'>"+content+"</td>" 				/*删除原因*/
					+"</tr>")					
    			if($('#searchForm input[name=status]').val()==1){
        			$("#"+tbody+" td[name='delUser']").remove();
        			$("#"+tbody+" td[name='operatedate']").remove();
        			$("#"+tbody+" td[name='delReason']").remove();
      			}else if($('#searchForm input[name=status]').val()==2){
	       			$("#"+tbody+" td[name='delUser']").remove();
	       			$("#"+tbody+" td[name='delReason']").remove();
       			}
  			})
	     }   		
	
</script>

</html>