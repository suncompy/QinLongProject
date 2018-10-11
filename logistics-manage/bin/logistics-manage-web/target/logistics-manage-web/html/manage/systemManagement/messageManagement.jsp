<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
	<head>
		<meta charset="UTF-8">
		<title>短信管理</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/public.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/manage/systemManagement/messageManagement.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/systemManagement/messageManagement.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.validate.js"></script>
		<script type="text/javascript">
			$(function(){
				
				$("#sure_msg").click(function(){
					window.location.reload();			
				});
				
				//获取模板内容
				$("#select_model").click(function changeModel(){
					var code = $("#select_model option:selected").val();
					$.ajax({
						type : "GET",
						url : "${pageContext.request.contextPath}/system/sms/getModel.do?code="+code,
						success : function(data) {
								$("#sms_content").html(data.data.content);		
							}
					});
				})
				
				
			})
		
			//新增计划
			function SubPlan() {
				$.ajax({
					type : "POST",
					url : "${pageContext.request.contextPath}/system/sms/plan/add.do",
					data : $('#planForm').serialize(),
					success : function(data) {
						if (data.status != 1) {
							$("#msg").html(data.msg);
							$("#promptIdModal").modal();
						} else {
							$("#addPlansModal").modal("hide");
							$("#msg").html(data.msg);
							$("#promptIdModal").modal();
						}
					}
				});
			};
		
		
		
			
		</script>
	</head>
	<body >
		<!-- 短信管理表格 -->
		<div class="container_bottom tableDiv">
			<div class="tabbable" id="tabs-529262">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#panel1" data-toggle="tab">&emsp;列表&emsp;</a>
					</li>
					<li >
						<a href="#panel2" data-toggle="tab">短信计划</a>
					</li>
					<li >
						<a href="#panel3" data-toggle="tab">短信模板</a>
					</li>
					<li >
						<a href="#panel4" data-toggle="tab">短信记录</a>
					</li>

				</ul>
				<div class="tab-content">
					<!--列表表格开始-->
					<div class="tab-pane active" id="panel1">
						<div class="domain">
							<div class="tableBg">
								<a href="javascript:void(0)" class="exportBtn msgbtn msg" id="sendMessage"><span>发短信</span></a>
							</div>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox"   >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label> </th>
											<th>名称</th>
											<th>联系方式</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${customerList.rows}" var="it">
										<tr>
											<td class="checks"><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox"   >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label></td>
											<td id="">${it.companyContacts}</td>
											<td id="">${it.stationPhone}</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-md-12 column paging">
								<ul class="pagination clearfix">
									<li>
										<a href="javascript:void(0)" class="newA">上一页</a>
									</li>
									<li>
										<a href="javascript:void(0)" class="newA actives">1</a>
									</li>
									<li>
										<a href="javascript:void(0)" class="newA">2</a>
									</li>
									<li>
										<a href="javascript:void(0)" class="newA">3</a>
									</li>
									<li>
										<a href="javascript:void(0)" class="newA">4</a>
									</li>
									<li>
										<a href="javascript:void(0)" class="newA">5</a>
									</li>

									<li>
										<a href="javascript:void(0)" class="newA">...</a>
									</li>
									<li>
										<a href="javascript:void(0)" class="newA">10</a>
									</li>

									<li>
										<a href="javascript:void(0)" class="newA">下一页 </a>
									</li>

									<li>
										<a class="pageSpan">跳转到 <input type="text" id="pageInput" />页</a>
									</li>
									<li>

									</li>
									<li>
										<a href="javascript:void(0)" class="pageGo">GO</a>
									</li>

								</ul>
							</div>
						</div>
					</div>
					<!--列表表格结束-->
					<!--短信计划表格开始-->
					<div class="tab-pane" id="panel2">
						<div class="domain">
							<div class="tableBg">
								<a href="javascript:void(0)" class="exportBtn add"  id="addPlans"><span>新增计划</span></a>
								<a href="javascript:void(0)" class="exportBtn start" id="start"><span>开始</span></a>
								<a href="javascript:void(0)" class="exportBtn pause" id="pause"><span>暂停</span></a>
							</div>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox" disabled="disabled"   >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label> </th>
											<th>名称</th>
											<th>状态</th>
											<th>发送节点</th>
											<th>接收方</th>
											<th>固定号码</th>
											<th>短信模板</th>
											<th>针对网点</th>
											<th>备注</th>
										</tr>
									</thead>
									<tbody>
										<%-- <c:forEach  items="customerList" var="it">
											<tr>
												<td class="checks"><label class="demo--label">
												     <input class="demo--checkbox" type="checkbox"   >
	                                                 <span class="demo--checkboxInput"></span>
		                                        </label></td>
												<td id="">${it.name}</td>
												<td id="">
													<c:if test="${it.is_stop ==0}">
														执行中
													</c:if>
													<c:if test="${it.is_stop ==1}">
														暂停
													</c:if>
												</td>
												<td id="">
													<c:if test="${it.point ==1}">
														  等待调度
													</c:if>
													<c:if test="${it.point ==2}">
														 等待发运
													</c:if>
													<c:if test="${it.point ==3}">
														在途运载
													</c:if>
													<c:if test="${it.point ==4}">
														货位引导
													</c:if>
													<c:if test="${it.point ==5}">
														 等待回单
													</c:if>
													<c:if test="${it.point ==6}">
														  等待计费
													</c:if>
													<c:if test="${it.point ==7}">
														 已完成
													</c:if>
												</td>
												<td id="">发货方</td>
												<td id="">18312345678</td>
												<td id="">模板A</td>
												<td id="">XX网点</td>
												<td id=""></td>
											</tr>
										</c:forEach> --%>
										<tr>
											<td class="checks"><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox"   >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label></td>
											<td id="">普通计划1</td>
											<td id="">执行中</td>
											<td id="">项目创建成功</td>
											<td id="">发货方</td>
											<td id="">18312345678</td>
											<td id="">模板A</td>
											<td id="">XX网点</td>
											<td id=""></td>
										</tr>
										<tr>
											<td class="checks"><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox"   >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label></td>
											<td id="">普通计划2</td>
											<td id="">执行中</td>
											<td id="">已装车</td>
											<td id="">发货方</td>
											<td id="">18312345678</td>
											<td id="">模板A</td>
											<td id="">XX网点</td>
											<td id=""></td>
										</tr>
										<tr>
											<td class="checks"><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox"   >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label></td>
											<td id="">普通计划3</td>
											<td id="">执行中</td>
											<td id="">执行中</td>
											<td id="">发货方</td>
											<td id="">18312345678</td>
											<td id="">模板A</td>
											<td id="">XX网点</td>
											<td id=""></td>
										</tr>
										<tr>
											<td class="checks"><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox"   >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label></td>
											<td id="">普通计划4</td>
											<td id="">暂停中</td>
											<td id="">项目创建成功</td>
											<td id="">收货方</td>
											<td id="">18312345678</td>
											<td id="">模板A</td>
											<td id="">XX网点</td>
											<td id=""></td>
										</tr>
										<tr>
											<td class="checks"><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox"   >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label></td>
											<td id="">普通计划5</td>
											<td id="">执行中</td>
											<td id="">项目创建成功</td>
											<td id="">发货方</td>
											<td id="">18312345678</td>
											<td id="">模板A</td>
											<td id="">XX网点</td>
											<td id=""></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-md-12 column paging">
								<ul class="pagination clearfix">
									<li>
										<a href="javascript:void(0)" class="newA">上一页</a>
									</li>
									<li>
										<a href="javascript:void(0)" class="newA actives">1</a>
									</li>
									<li>
										<a href="javascript:void(0)" class="newA">2</a>
									</li>
									<li>
										<a href="javascript:void(0)" class="newA">3</a>
									</li>
									<li>
										<a href="javascript:void(0)" class="newA">4</a>
									</li>
									<li>
										<a href="javascript:void(0)" class="newA">5</a>
									</li>

									<li>
										<a href="javascript:void(0)" class="newA">...</a>
									</li>
									<li>
										<a href="javascript:void(0)" class="newA">10</a>
									</li>

									<li>
										<a href="javascript:void(0)" class="newA">下一页 </a>
									</li>

									<li>
										<a class="pageSpan">跳转到 <input type="text" id="pageInput" />页</a>
									</li>
									<li>

									</li>
									<li>
										<a href="javascript:void(0)" class="pageGo">GO</a>
									</li>

								</ul>
							</div>
						</div>
					</div>
					<!--短信计划表格结束-->
					<!--短信模板表格开始-->
					<div class="tab-pane " id="panel3">
						<div class="domain">
							<div class="tableBg">
								<a href="javascript:void(0)" class="exportBtn add" id="addTemplate"><span>新增模板</span></a>
							</div>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox"   >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label> </th>
											<th>名称</th>
											<th>状态</th>
											<th>分类</th>
											<th>内容</th>
											<th>创建人</th>
											<th>创建时间</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td class="checks"><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox"   >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label></td>
											<td id="">普通计划1</td>
											<td id="">执行中</td>
											<td id="">全部</td>
											<td id="">[发货人]，你好！[接单日期]发往[到货区域]的[件数]件[货物名称]正在派送中，请知悉</td>
											<td id="">王大锤</td>
											<td id="">2017.12.12 12：00</td>
										</tr>
										<tr>
											<td class="checks"><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox"   >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label></td>
											<td id="">普通计划2</td>
											<td id="">执行中</td>
											<td id="">全部</td>
											<td id="">[发货人]，你好！[接单日期]发往[到货区域]的[件数]件[货物名称]正在派送中，请知悉</td>
											<td id="">王大锤</td>
											<td id="">2017.12.12 12：00</td>
										</tr>
										<tr>
											<td class="checks"><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox"   >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label></td>
											<td id="">普通计划3</td>
											<td id="">执行中</td>
											<td id="">全部</td>
											<td id="">[发货人]，你好！[接单日期]发往[到货区域]的[件数]件[货物名称]正在派送中，请知悉</td>
											<td id="">王大锤</td>
											<td id="">2017.12.12 12：00</td>
										</tr>
										<tr>
											<td class="checks"><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox"   >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label></td>
											<td id="">普通计划4</td>
											<td id="">暂停中</td>
											<td id="">全部</td>
											<td id="">[发货人]，你好！[接单日期]发往[到货区域]的[件数]件[货物名称]正在派送中，请知悉</td>
											<td id="">王大锤</td>
											<td id="">2017.12.12 12：00</td>
										</tr>
										<tr>
											<td class="checks"><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox"   >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label></td>
											<td id="">普通计划5</td>
											<td id="">执行中</td>
											<td id="">全部</td>
											<td id="">[发货人]，你好！[接单日期]发往[到货区域]的[件数]件[货物名称]正在派送中，请知悉</td>
											<td id="">王大锤</td>
											<td id="">2017.12.12 12：00</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-md-12 column paging">
								<ul class="pagination clearfix">
									<li>
										<a href="javascript:void(0)" class="newA">上一页</a>
									</li>
									<li>
										<a href="javascript:void(0)" class="newA actives">1</a>
									</li>
									<li>
										<a href="javascript:void(0)" class="newA">2</a>
									</li>
									<li>
										<a href="javascript:void(0)" class="newA">3</a>
									</li>
									<li>
										<a href="javascript:void(0)" class="newA">4</a>
									</li>
									<li>
										<a href="javascript:void(0)" class="newA">5</a>
									</li>

									<li>
										<a href="javascript:void(0)" class="newA">...</a>
									</li>
									<li>
										<a href="javascript:void(0)" class="newA">10</a>
									</li>

									<li>
										<a href="javascript:void(0)" class="newA">下一页 </a>
									</li>

									<li>
										<a class="pageSpan">跳转到 <input type="text" id="pageInput" />页</a>
									</li>
									<li>

									</li>
									<li>
										<a href="javascript:void(0)" class="pageGo">GO</a>
									</li>

								</ul>
							</div>
						</div>
					</div>
					<!--短信模板表格结束-->
					<!--短信记录表格开始-->
					<div class="tab-pane " id="panel4">
						<div class="domain">
							<div class="tableBg">
								<a href="javascript:void(0)" class="exportBtn export" id="export"><span>导出</span></a>
							</div>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox"   >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label> </th>
											<th>发送时间</th>
											<th>接收号码</th>
											<th>内容</th>
											<th>接收人</th>
											<th>运单编号</th>
											<th>短信状态</th>
											<th>操作人</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td class="checks"><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox"   >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label></td>
											<td id="">2017.12.12 12：00</td>
											<td id="">18312345678</td>
											<td id="">[发货人]，你好！[接单日期]发往[到货区域]的[件数]件[货物名称]正在派送中，请知悉</td>
											<td id="">王大锤</td>
											<td id="">DSCS-12345678</td>
											<td id="">已发送</td>
											<td id="">王大锤</td>
										</tr>
										<tr>
											<td class="checks"><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox"   >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label></td>
											<td id="">2017.12.12 12：00</td>
											<td id="">18312345678</td>
											<td id="">[发货人]，你好！[接单日期]发往[到货区域]的[件数]件[货物名称]正在派送中，请知悉</td>
											<td id="">王大锤</td>
											<td id="">DSCS-12345678</td>
											<td id="">已发送</td>
											<td id="">王大锤</td>
										</tr>
										<tr>
											<td class="checks"><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox"   >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label></td>
											<td id="">2017.12.12 12：00</td>
											<td id="">18312345678</td>
											<td id="">[发货人]，你好！[接单日期]发往[到货区域]的[件数]件[货物名称]正在派送中，请知悉</td>
											<td id="">王大锤</td>
											<td id="">DSCS-12345678</td>
											<td id="">已发送</td>
											<td id="">王大锤</td>
										</tr>
										<tr>
											<td class="checks"><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox"   >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label></td>
											<td id="">2017.12.12 12：00</td>
											<td id="">18312345678</td>
											<td id="">[发货人]，你好！[接单日期]发往[到货区域]的[件数]件[货物名称]正在派送中，请知悉</td>
											<td id="">王大锤</td>
											<td id="">DSCS-12345678</td>
											<td id="">已发送</td>
											<td id="">王大锤</td>
										</tr>
										<tr>
											<td class="checks">
												<label class="demo--label">
											     <input class="demo--checkbox" type="checkbox"   >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label>
											</td>
											<td id="">2017.12.12 12：00</td>
											<td id="">18312345678</td>
											<td id="">[发货人]，你好！[接单日期]发往[到货区域]的[件数]件[货物名称]正在派送中，请知悉</td>
											<td id="">王大锤</td>
											<td id="">DSCS-12345678</td>
											<td id="">已发送</td>
											<td id="">王大锤</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-md-12 column paging">
								<ul class="pagination clearfix">
									<li>
										<a href="javascript:void(0)" class="newA">上一页</a>
									</li>
									<li>
										<a href="javascript:void(0)" class="newA actives">1</a>
									</li>
									<li>
										<a href="javascript:void(0)" class="newA">2</a>
									</li>
									<li>
										<a href="javascript:void(0)" class="newA">3</a>
									</li>
									<li>
										<a href="javascript:void(0)" class="newA">4</a>
									</li>
									<li>
										<a href="javascript:void(0)" class="newA">5</a>
									</li>

									<li>
										<a href="javascript:void(0)" class="newA">...</a>
									</li>
									<li>
										<a href="javascript:void(0)" class="newA">10</a>
									</li>

									<li>
										<a href="javascript:void(0)" class="newA">下一页 </a>
									</li>

									<li>
										<a class="pageSpan">跳转到 <input type="text" id="pageInput" />页</a>
									</li>
									<li>

									</li>
									<li>
										<a href="javascript:void(0)" class="pageGo">GO</a>
									</li>

								</ul>
							</div>
						</div>
					</div>
					<!--短信记录表格结束-->
				</div>	
			</div>
		</div>	
		
		
		<!--发短信模态框-->
	    <div class="modal fade sendMessageModal" id="sendMessageModal" tabindex="-1" role="dialog" data-backdrop="static">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">发短信</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<h5>短信内容</h5>
						<form id="MsgForm">
						<div class="form-inline">
							<div class="form-group">
								<label>短信签名：</label>
								<select >
									<option>合肥市深合软件</option>
								</select>
							</div>
							<div class="form-group">
								<label>短信模板：</label>
								<select id="select_model" >
									<option value="SMS_CODE_TWO">告知模板</option>
									<option value="SMS_CODE_ONE" >发运模板</option>
									<option value="SMS_CODE_TRE">回单模板</option>
								</select>
							</div>
						</div>
						<div class="form-inline">
							<label class="v-top">短信内容：</label>
							<textarea   id="sms_content" type="text" class="row2 remarkInput" maxlength="100"></textarea>
							<p>已选择<b id="chooseNum"></b>人</p>
						</div>
						<div class="row clearfix ">
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
		
		<!--新增计划模态框-->
	    <div class="modal fade addPlansModal" id="addPlansModal" tabindex="-1" role="dialog" data-backdrop="static">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">新增计划</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="planForm">
						<div class="form-inline">
							<div class="form-group">
								<label>&emsp;&emsp;名称：</label>
								<input type="text" maxlength="30"/>
							</div>
							<div class="form-group">
								<label>发送节点：</label>
								<select name="point">
									<option value="1">等待调度	</option>
									<option value="2">等待发运</option>
									<option value="3">在途运载</option>
									<option value="4">货位引导</option>
									<option value="5">等待回单</option>
									<option value="6">等待计费</option>
									<option value="7">已完成</option>
								</select>
							</div>
							<div class="form-group">
								<label>短信模板：</label>
								<select ></select>
							</div>
						</div>
						<div class="form-inline">
							<div class="form-group relationClass">
								<label>&emsp;接收方：</label>
								<ul id="relationUl">
									<li>
										<select></select>
										<img src="${pageContext.request.contextPath}/img/add1.png" class="addImg" />
										<img src="${pageContext.request.contextPath}/img/reduce.png"  />
									</li>
								</ul>
							</div>
						</div>
						<div class="form-inline">
							<label>&emsp;&emsp;备注：</label>
							<input type="text" class="remarkInput" maxlength="100"/>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<input type="button" value="确认" onclick="SubPlan()">
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
					    </div>
					    </form>
					</div>	
				</div>		
			</div>			
		</div>				
	
		<!--新增模板模态框-->	
	    <div class="modal fade addTemplateModal" id="addTemplateModal" tabindex="-1" role="dialog" data-backdrop="static">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">添加模板</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<h5>模板信息</h5>
						<form id="modalForm">
						<div class="form-inline">
							<div class="form-group">
								<label>模板名称：</label>
								<input type="text" maxlength="30"/>
							</div>
							<div class="form-group">
								<label>模板类型：</label>
								<select ></select>
							</div>
						</div>
						<div class="form-inline">
							<label  class="v-top">模板内容：</label>
							<textarea   type="text" class=" remarkInput" maxlength="100"></textarea>
						</div>
						<div  class="right-label">可用字段：</div>
						<div class="field">[运单号] [接单日期] [发货日期] [到货日期]  [发货联系人] [发货人联系方式] [发货人地址] [发货区域] [收货人联系方式] [收货人地址] [取件员] [取件员联系方式] [派件员] [派件员联系方式] [客户单号] [到货区域] [重量] [件数] [体积] [货物名称] [签收人] [签收时间] [服务商] [服务商单号] [总运费] [代收款] [减代收金额] [代收款手续费] [放款金额] [支装服务商] [联系电话] [送装费] [当前系统操作人] [到站电话]</div>
						<div class="row clearfix ">
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
							<span>选择了<b id="exportNum">13</b>条记录，确认导出全部数据？</span>
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

		<!-- 未选择数据提醒模态框 -->
		<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="nullModal" data-backdrop="static">
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
								<button type="button" class="btn sureBtn"  data-dismiss="modal">确定</button>
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
						<h4 class="modal-title">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="body-content">
							<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn"  data-dismiss="modal">确定</button>
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
									<button type="button" class="btn sureBtn" data-dismiss="modal">确定</button>
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
								<button type="button" class="btn sureBtn">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
					    </form>
					</div>
				</div>
			</div>
		</div>
		<!--开始模态框-->
		<div class="modal fade startModal" tabindex="-1" role="dialog" id="startModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="body-content">
							<i class="fa fa-question-circle" aria-hidden="true"></i>
							<span>你已选择<b id="">3</b>条数据，是否开始？</span>
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
		<!--暂停模态框-->
		<div class="modal fade pauseModal" tabindex="-1" role="dialog" id="pauseModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="body-content">
							<i class="fa fa-question-circle" aria-hidden="true"></i>
							<span>你已选择<b id="">3</b>条数据，是否暂停？</span>
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
					<p id="msg2"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn sureBtn" id="sure_msg">
						确定</button>
				</div>
			</div>
		</div>
	</div>	
	</body>
	
	<script>
	//分页
	$("#page").paging({
		pageNo:1, 
		totalPage: ${customerList.totalCount},
		totalSize:${customerList.limit},
		callback: function(num) {
				$.ajax({  
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/customerManagement/selectCustomer.do',  
		        data:{page:num},
		        cache:false,
		        dataType:'json',
	        	success:function(data){
	        		if(data.status==200){
	        		var customerList = data.data;
	        		$('#cusTbody').html("");
	        		$.each(customerList,function(index,customer){
	        			$('#cusTbody').append("<tr>"
							+"<td style='text-align: center;'><label class='demo--label'>"
							 +" <input class='demo--checkbox' name='cusCheckBox' value='"+customer.id+"' type='checkbox'   >"
                              +"<span class='demo--checkboxInput'></span>"
                             +"</label></td>"
							 +"<td id=''>"+customer.companyName+"</td>"
						 +"<td id=''>"+customer.shortName+"</td>"
						 /*分支机构*/
						 +"<td id=''>"+customer.brachIds+"</td>"
						 +"<td id=''>"+customer.detailAddress+"</td>"
						 +"<td id=''>"+customer.companyContacts+"</txd>"
						 +"<td id=''>"+customer.department+"</td>"
						 +"<td id=''>"+customer.stationPhone+"</td>"
						 +"<td id=''>"+customer.stationFax+"</td>"
						 +"<td id=''>"+customer.email+"</td>"
						 +"</tr>")
	        		})	
	        	}  
	    	}
		})
	}		
	})
</script>
	
</html>
