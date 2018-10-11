<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>项目运营管理</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/public.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/business/project/project.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/config/url.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/business/project/projectOperation.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.serializejson.js"></script>
		<script src="${pageContext.request.contextPath}/static/js/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/util.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js" ></script>
		<link href="${pageContext.request.contextPath}/css/page/page.css" rel="stylesheet"/>
	</head>
	<!--搜索-->
	<script type="text/javascript">
		function search(param){
			$.ajax({
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/project/getProjectOperationByPage.do', 
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
	
	<body class="projectOperation">
		<!-- 项目运营管理表单 -->
		<div class="form project_form container_top">
			<form class="form-inline project-form maxWidth" id="searchForm">
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
					<div class="form-group">
						<label>项目类型：</label>
						<select name="projectType">
							<option></option>
							<option value="0">集装箱</option>
							<option value="1">散堆装</option>
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
					<div class="form-group">
						<label>发货企业：</label>
						<input name='sendCargoCompanyName' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>收货企业：</label>
						<input name='receiveCargoCompanyName' type="text" maxlength="30"/>
					</div>
					<!--<div class="form-group">
						<label>&emsp;&emsp;货场：</label>
						<select></select>
					</div>
					<div class="form-group">
						<label>&emsp;&emsp;货位：</label>
						<select></select>
					</div>-->
				</div>
				<div class="foot">
					<a class="btn btn-success" href="#"><em class="search"></em>
						<span onclick="search(this)">搜索</span>
					</a>
					<div id="lookMore"></div>
				</div>
			</form>
			<!--仓位平面图表单-->
			<form class="form-inline depot_form maxWidth">
				<div class="form-group">
					<label>站点选择：</label>
					<select name="station" >
						<c:forEach items="${twoLevelStations}" var="station">
							<option value="${station.id}">${station.stationName}</option>
						</c:forEach>
						<c:forEach items="${threeLevelStations}" var="station">
							<option value="${station.id}">${station.stationName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<label>货场选择：</label>
					<select id='cargoYard'></select>
				</div>
				<div class="foot">
					<a class="btn btn-success" href="#"><em class="search"></em>
						<span>搜索</span>
					</a>
					<div id="lookMores"></div>
				</div>
			</form>
		</div>
		<!-- 项目运营管理表格 -->
		<div class="container_bottom tableDiv">
			<div class="tabbable" id="tabs-529262">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#panel1" data-toggle="tab" id="items">项目列表</a>
					</li>
					<li>
						<a class="clickBtn" href="#panel2" onclick="freightSpace()" data-toggle="tab">仓位平面图</a>
					</li>
				</ul>
				<div class="tab-content">
					<!--项目列表表格开始-->
					<div class="tab-pane active" id="panel1">
						<div class="domain">
							<div class="tableBg">
								<!-- <a href="#"  class="exportBtn nodeManagement" onclick="return false;"><span>节点管理</span></a> -->
								<!--<a href="#" class="exportBtn look"><span>查看</span></a>-->
								<c:forEach items='${permissions }' var='permission' >
									<c:if test="${permission.code=='exportByProjectOperation'}">
										<a href="#" class="export"  id="export"><span>导出</span></a>
									</c:if>
								</c:forEach>
							</div>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th> <label class="demo--label">
								            <input class="demo--checkbox" type="checkbox"  >
							                  <span class="demo--checkboxInput"></span>
							                         	</label> </th>
											<th>项目编号</th>
											<th>项目类型</th>
											<th>联运模式</th>
											<th>分支机构</th>
											<th>发货企业</th>
											<th>收货企业</th>
											<th>货物品名</th>
											<th>规格</th>
											<th>接取提货吨位</th>
											<th>接取提货车次</th>
											<th>接取到货吨位</th>
											<th>接取到货车次</th>
											<th>接取损耗吨位</th>
											<th>火运发送吨位</th>
											<th>火运发送车次</th>
											<th>火运到货吨位</th>
											<th>火运到货车次</th>
											<th>火运损耗吨位</th>
											<th>送达提货吨位</th>
											<th>送达提货车次</th>
											<th>送达到货吨位</th>
											<th>送达到货车次</th>
											<th>送达损耗吨位</th>
											<th>运输完成吨位</th>
											<th>在途运输吨位</th>
											<th>中转库存吨位</th>
										</tr>
									</thead>
									<tbody id='projectTbody'>
									<c:forEach items="${projectOperations.rows}" var="projectOperation">
										<tr id='${projectOperation.id}'>
											<td><label class="demo--label">
								            <input class="demo--checkbox" type="checkbox"  >
							                  <span class="demo--checkboxInput"></span>
							                         	</label></td>
											<td id="">
												<a href="#" >${projectOperation.projectCode}</a>
											</td>
											<td>
											<c:if  test="${projectOperation.projectType==0}">
													集装箱
											</c:if>
											<c:if  test="${projectOperation.projectType==1}">
													散堆装
											</c:if>
											</td>
											<td>
											<c:if  test="${projectOperation.transportType==0}">
														汽运
											</c:if>
											<c:if  test="${projectOperation.transportType==1}">
													接取
											</c:if>
											<c:if  test="${projectOperation.transportType==2}">
													送达
											</c:if>
											<c:if  test="${projectOperation.transportType==3}">
													火运
											</c:if>
											<c:if  test="${projectOperation.transportType==4}">
													接取+火运
											</c:if>
											<c:if  test="${projectOperation.transportType==5}">
													火运+送达
											</c:if>
											<c:if  test="${projectOperation.transportType==6}">
													联运
											</c:if>
											<c:if  test="${projectOperation.transportType==7}">
													接取+送达
											</c:if>
											</td>
											<td>${projectOperation.branchGroupName}</td>
											<td>${projectOperation.sendCargoCompanyName}</td>
											<td>${projectOperation.receiveCargoCompanyName}</td>
											<td>${projectOperation.cargoName}</td>
											<td>${projectOperation.cargoSpecifications}</td>
											<!-- 接取 -->
											<c:if  test="${projectOperation.transportType==0 
											|| projectOperation.transportType==1 || projectOperation.transportType==4 
											|| projectOperation.transportType==6 || projectOperation.transportType==7 }">
														<td id="">${projectOperation.receiveGetCargoWeight}吨</td>
														<td id="">${projectOperation.receiveGetCargoCarNum}</td>
														<td id="">${projectOperation.receiveArriveCargoWeight}吨</td>
														<td id="">${projectOperation.receiveArriveCargoCarNum}</td>
														<td id="">${projectOperation.receiveGetCargoWeight-projectOperation.receiveArriveCargoWeight}吨</td>
														
											</c:if>
											<c:if  test="${projectOperation.transportType==3 || projectOperation.transportType==2
											|| projectOperation.transportType==5 || projectOperation.transportType==3  }">
														<td id="">/</td>
														<td id="">/</td>
														<td id="">/</td>
														<td id="">/</td>
														<td id="">/</td>
											</c:if>
											
											<!-- 火运  -->
											<c:if  test="${projectOperation.transportType==3 || projectOperation.transportType== 6
											||  projectOperation.transportType==4 || projectOperation.transportType== 5}">
														<td id="">${projectOperation.trainSendCargoWeight}吨</td>
														<td id="">${projectOperation.trainSendCargoTrainNum}</td>
														<td id="">${projectOperation.trainArriveCargoWeight}吨</td>
														<td id="">${projectOperation.trainArriveCargoTrainNum}</td>
														<td id="">${projectOperation.trainSendCargoWeight-projectOperation.trainArriveCargoWeight}</td>
											</c:if>
											<c:if  test="${projectOperation.transportType==0 || projectOperation.transportType==1
											||  projectOperation.transportType==2 || projectOperation.transportType==7
											 }">
														<td id="">/</td>
														<td id="">/</td>
														<td id="">/</td>
														<td id="">/</td>
														<td id="">/</td>
											</c:if>
											<!-- 送达 -->
											<c:if  test="${projectOperation.transportType==0 || projectOperation.transportType==3 
											|| projectOperation.transportType==1 || projectOperation.transportType==4 }">
														<td id="">/</td>
														<td id="">/</td>
														<td id="">/</td>
														<td id="">/</td>
														<td id="">/</td>
											</c:if>
											<c:if  test="${projectOperation.transportType==2 || projectOperation.transportType==5
											|| projectOperation.transportType==6 || projectOperation.transportType==7 }">
														<td id="">${projectOperation.sendGetCargoWeight}吨</td>
														<td id="">${projectOperation.sendGetCargoCarNum}</td>
														<td id="">${projectOperation.sendArriveCargoWeight}吨</td>
														<td id="">${projectOperation.sendArriveCargoNum}</td>
														<td id="">${projectOperation.sendGetCargoWeight-projectOperation.sendArriveCargoWeight}吨</td>
											</c:if>
											
											<td id="">${projectOperation.finishCargoWeight}吨</td><!-- 运输完成吨位 -->
											<td id="">${projectOperation.runningCargoWeight}吨</td><!-- 在途运输吨位 -->
											<td id="">${projectOperation.transitCargoWeight}吨</td><!-- 中转库存吨位 -->
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
					<!--项目列表表格结束-->
					
					<!--仓位平面图表格开始-->
					<div class="tab-pane" id="panel2">
						<div class="depot">
							<ul class="clearfix" id='cargoLocation'>
							</ul>
							<!--</div>-->
						</div>
					</div>
					<!-- 仓位平面图结束 -->
				</div>
			</div>
		</div>
		<!-- 项目详情模态框 -->
		<div class="modal fade  detailModal" tabindex="-1" role="dialog" data-backdrop="static" id="detailModal">
			<div class="modal-dialog modal-lg" role="document">
				<!-- 项目详情 -->
				<div class="modal-content" id='modalContent'>
					<div class="modal-header">
						<h4 class="modal-title">项目详情</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="projectDetail_info">
							<div class="info_title">
								<h5>项目信息</h5>
								<ul class="clearfix">
									<li>
										<label>项目编号：</label>
										<span name='detailModalProjectCode'></span>
									</li>
									<li>
										<label>分支机构：</label>
										<span name="detailModalProjectGroup"></span>
									</li>
									<li>
										<label>项目类型：</label>
										<span name="detailModalProjectType"></span>
									</li>
									<li>
										<label>联运模式：</label>
										<span name="detailModalTransportType"></span>
									</li>
								</ul>
							</div>

						</div>
						<hr>
						<div class="receve_info">
							<h5>接取信息</h5>
							<ul class="clearfix">
								<li>
									<label>接取提货吨位：</label>
									<span name="receiveGetCargoWeight"></span>
								</li>
								<li>
									<label>接取提货车次：</label>
									<span name="receiveGetCargoCarNum"></span>
								</li>
								<li>
									<label>接取到货吨位：</label>
									<span name="receiveArriveCargoWeight"></span>
								</li>
								<li>
									<label>接取到货车次：</label>
									<span name="receiveArriveCargoCarNum"></span>
								</li>
								<!--<li>
									<label>货场：</label>
									<span>多货</span>
								</li>
								<li>
									<label>货位：</label>
									<span>多位</span>
								</li>
								<li>
									<label>库存：</label>
									<span></span>
								</li>-->
							</ul>
						</div>
						<hr>
						<div class="train_info">
							<h5>火运信息</h5>
							<ul class="clearfix">
								<li>
									<label>火运提货吨位：</label>
									<span name="trainSendCargoWeight"></span>
								</li>
								<li>
									<label>火运提货车次：</label>
									<span name="trainSendCargoTrainNum"></span>
								</li>
								<li>
									<label>火运到货吨位：</label>
									<span name="trainArriveCargoWeight"></span>
								</li>
								<li>
									<label>火运到货车次：</label>
									<span name="trainArriveCargoTrainNum"></span>
								</li>
								<!--<li>
									<label>货场：</label>
									<span>多货</span>
								</li>
								<li>
									<label>货位：</label>
									<span>多位</span>
								</li>
								<li>
									<label>库存：</label>
									<span></span>
								</li>-->
							</ul>
						</div>
						<hr>
						<div class="send_info">
							<h5>送达信息</h5>
							<ul class="clearfix">
								<li>
									<label>送达提货吨位：</label>
									<span name="sendGetCargoWeight"></span>
								</li>
								<li>
									<label>送达提货车次：</label>
									<span name="sendGetCargoCarNum"></span>
								</li>
								<li>
									<label>送达到货吨位：</label>
									<span name="sendArriveCargoWeight"></span>
								</li>
								<li>
									<label>送达到货车次：</label>
									<span name="sendArriveCargoNum"></span>
								</li>
								<li>
									<label>运输完成吨位：</label>
									<span name="finishCargoWeight"></span>
								</li>
								<li>
									<label>在途运载吨位：</label>
									<span name="runningCargoWeight"></span>
								</li>
								<li>
									<label>中转库存吨位：</label>
									<span name="transitCargoWeight"></span>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 失败提醒模态框 -->
		<div class="modal fade deletRemindModal" style="z-index:99999;" tabindex="-1" role="dialog" id="deletnullModal" data-backdrop="static">
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
								<button type="button" class="btn sureBtn" data-dismiss="modal">确定</button>
							</div>
						</div>
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
							<span>选择了<b id="">13</b>条记录，确认导出全部数据？</span>
						</div>
						<div class="row clearfix " >
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
<!--站点二级联动货场-->	
	<script>
		function freightSpace(){
			$("select[name='station']").change();
		}
		
		$("select[name='station']").change(function(){
			$('#cargoYard').html("");
			//var name = $(this).find("option:checked").text();
			var value = $(this).find("option:checked").val();
			$.ajax({
		        	type:'post',      
		       		url:'${pageContext.request.contextPath}/siteManager/getFreightYardByStationId.do', 
			        data:{id:value},
			        cache:false,
			        dataType:'json',
		        	success:function(data){
		        		if(data.status==200){
		        			var frightYards = data.data;
		        			$.each(frightYards,function(index,frightYard){
		        				$('#cargoYard').append("<option value='"+frightYard.id+"'>"+frightYard.name+"</option>");
		        			})
		        			$("#cargoYard").change();
			        	}  
				 	}
			})
		})
	</script>
	
<!-- 项目列表查看详情 -->
<script type="text/javascript">
	$("body").on(
			"click",
			"#panel1 tbody tr td:not(:nth-child(1))",
			function() {
				var projectId = $(this).parent().attr("id");
				$.ajax({  
			    	type:'post',      
			   		url:'${pageContext.request.contextPath}/project/getProjectOperationByPid.do', 
			        data:{id:projectId},
			        cache:false,
			        dataType:'json',
			    	success:function(data){
			    		if(data.status==200){
			    				var project = data.data;
			    				
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
			    				
			    				
			    				$('#detailModal span[name=detailModalProjectCode]').html(project.projectCode);
			    				$('#detailModal span[name=detailModalProjectGroup]').html(project.branchGroupName);
			    				$('#detailModal span[name=detailModalProjectType]').html(projectType);
			    				$('#detailModal span[name=detailModalTransportType]').html(transportType);
			    				$('#detailModal span[name=receiveGetCargoCarNum]').html(project.receiveGetCargoCarNum);
			    				$('#detailModal span[name=receiveGetCargoWeight]').html(project.receiveGetCargoWeight);
			    				$('#detailModal span[name=receiveArriveCargoWeight]').html(project.receiveArriveCargoWeight);
			    				$('#detailModal span[name=receiveArriveCargoCarNum]').html(project.receiveArriveCargoCarNum);
			    				$('#detailModal span[name=trainSendCargoWeight]').html(project.trainSendCargoWeight);
			    				$('#detailModal span[name=trainSendCargoTrainNum]').html(project.trainSendCargoTrainNum);
			    				$('#detailModal span[name=trainArriveCargoWeight]').html(project.trainArriveCargoWeight);
			    				$('#detailModal span[name=trainArriveCargoTrainNum]').html(project.trainArriveCargoTrainNum);
			    				$('#detailModal span[name=sendGetCargoWeight]').html(project.sendGetCargoWeight);
			    				$('#detailModal span[name=sendGetCargoCarNum]').html(project.sendGetCargoCarNum);
			    				$('#detailModal span[name=sendArriveCargoWeight]').html(project.sendArriveCargoWeight);
			    				$('#detailModal span[name=sendArriveCargoNum]').html(project.sendArriveCargoNum);
			    				$('#detailModal span[name=finishCargoWeight]').html(project.finishCargoWeight);
			    				$('#detailModal span[name=runningCargoWeight]').html(project.runningCargoWeight);
			    				$('#detailModal span[name=transitCargoWeight]').html(project.transitCargoWeight);
			    				$("#detailModal").modal();
			    				$('#showMask', window.parent.document).show();
				    			$(this).parent().toggleClass("bgclass").siblings("#panel1 tbody tr").removeClass("bgclass");
			        		}else{
			        			$("#msgContent").html("");
			        			$("#msgContent").html(data.msg);
			        			$("#deletnullModal").modal();
			        		}
				   	 	}
					})
			}
	);
</script>

<!--查看货位图-->
<script>
	$('#cargoYard').change(function(){
		var id = $('#cargoYard').val();
		$.ajax({  
	    	type:'post',      
	   		url:'${pageContext.request.contextPath}/project/getCargoLocation.do', 
	        data:{id:id},
	        cache:false,
	        dataType:'json',
	    	success:function(data){
	    		$('#cargoLocation').html("");
	    		if(data.status==200){
	    			var cargoLocations = data.data;
	    			$.each(cargoLocations,function(index,cargoLocation){
	    				var content ='<dl style="text-align: center;"><dd></dd></dl>'
	    				var flag = 0;
	    				var i  = 0;
	    				$.each(cargoLocation.stocks,function(index,stock){
		    				if(i==4){
		    					return;
		    				}
	    					/**
	    					如果现有库存为空
	    					*/
	    					if(stock.currentQty == "" || stock.currentQty==0){
	    						flag= flag + 1; 
	    						return;
	    					}
	    					if(stock.projectType===0){//集装箱
	    						i=i+1;	
								content=content+"<dl>"
									+"<dt>项目编号："+stock.projectCode+"</dt>"
									+"<dt>货位吨位："+stock.currentQty+"</dt>"
									+"<dt>集装箱组："+stock.containerNum+"</dt>"
									+"</dl>";	
		    				}else if(stock.projectType==1){//散装箱
		    					i=i+1;	
								content=content+"<dl>"
									+"<dt>项目编号："+stock.projectCode+"</dt>"
									+"<dt>货位吨位："+stock.currentQty+"</dt>"
									+"</dl>";	
		    				}
	    				})
    					if(flag == cargoLocation.length){
    						content ='<dl style="text-align: center;"><dd>货位为空</dd></dl>'
    					}
    					$('#cargoLocation').append("<li><div class='depot-num'>"+cargoLocation.cargoLocationName+"</div><div class='clearfix depot-detail'>"
   		    				+"<div class='border-right'>"+content+"</div></div></li>");
    				})
	        		}else{
	        			//$("#msgContent").html("");
	        			//$("#msgContent").html(data.msg);
	        			//$("#deletnullModal").modal();
	        		}
		   	 	}
			})
	})
	
</script>

    <!--分页-->
<script >
	$("#page").paging({
		pageNo:1, 
		totalPage: ${projectOperations.totalPage},
		totalSize:${projectOperations.limit},
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
       		url:'${pageContext.request.contextPath}/project/getProjectOperationByPage.do', 
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

<!--将查询结果写入到Table中-->
<script>
	function htmlTable(results){
		var projectList = results;
		$('#projectTbody').html("");
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
		if(project.shortBargeCarrierMode==0){
			shortBargeCarrierMode="平台";
		}else if(project.shortBargeCarrierMode==1){
			shortBargeCarrierMode="自选";
		}
		
		var receiveGetCargoWeight="/";
		var receiveGetCargoCarNum="/";
		var receiveArriveCargoWeight="/";
		var receiveArriveCargoCarNum="/";
		var receiveArriveLossCargoWeight="/";
		
		if(project.transportType==0
			|| project.transportType==1
			|| project.transportType==4
			|| project.transportType==6
			|| project.transportType==7){
			receiveGetCargoWeight=project.receiveGetCargoWeight+"吨";
		 	receiveGetCargoCarNum=project.receiveGetCargoCarNum;
			receiveArriveCargoWeight=project.receiveArriveCargoWeight+"吨";
		 	receiveArriveCargoCarNum=project.receiveArriveCargoCarNum;
		 	receiveArriveLossCargoWeight=(project.receiveGetCargoWeight-project.receiveArriveCargoWeight)+"吨";
		}  
		
		
		var trainSendCargoWeight="/";
		var trainSendCargoTrainNum="/";
		var trainArriveCargoWeight="/";
		var trainArriveCargoTrainNum="/";
		var trainArriveLossCargoTrainWeight="/";
		
		if(project.transportType==3
			|| project.transportType==4
			|| project.transportType==5
			|| project.transportType==6){
			trainSendCargoWeight=project.trainSendCargoWeight+"吨";
		 	trainSendCargoTrainNum=project.trainSendCargoTrainNum;
			trainArriveCargoWeight=project.trainArriveCargoWeight+"吨";
		 	trainArriveCargoTrainNum=project.trainArriveCargoTrainNum;
		 	trainArriveLossCargoTrainWeight=(project.trainSendCargoWeight-project.trainArriveCargoWeight)+"吨";
		} 
		
		var sendGetCargoWeight="/";
		var sendGetCargoCarNum="/";
		var sendArriveCargoWeight="/";
		var sendArriveCargoNum="/";
		var sendArriveLossCargoWeight="/";
		
		if(project.transportType==2
			|| project.transportType==5
			|| project.transportType==6
			|| project.transportType==7){
			sendGetCargoWeight=project.sendGetCargoWeight+"吨";
		 	sendGetCargoCarNum=project.sendGetCargoCarNum;
			sendArriveCargoWeight=project.sendArriveCargoWeight+"吨";
		 	sendArriveCargoNum=project.sendArriveCargoNum;
		 	sendArriveLossCargoWeight=(project.sendGetCargoWeight-project.sendArriveCargoWeight)+"吨";
		} 
		
		$('#projectTbody').append("<tr id='"+project.id+"'><td><label class='demo--label'>"
		+"<input class='demo--checkbox' type='checkbox' value='"+project.id+"' name='WaybillName'>"
		+"<span class='demo--checkboxInput'></span></label></td>"
		+"<td ><a href='' data-toggle='modal' data-target='#detailModal-one'>"+project.projectCode+"</a></td>"	                   
		+"<td >"+projectType+"</td>"
		+"<td >"+transportType+"</td>"
		+"<td >"+project.branchGroupName+"</td>"
		+"<td >"+project.sendCargoCompanyName+"</td>"
		+"<td >"+project.receiveCargoCompanyName+"</td>"
		+"<td >"+project.cargoName+"</td>"
		+"<td >"+project.cargoSpecifications+"</td>"
		/*接取*/
		+"<td >"+receiveGetCargoWeight+"</td>"
		+"<td >"+receiveGetCargoCarNum+"</td>"
		+"<td >"+receiveArriveCargoWeight+"</td>"
		+"<td >"+receiveArriveCargoCarNum+"</td>"
		+"<td >"+receiveArriveLossCargoWeight+"</td>"
		/*火运*/
		+"<td >"+trainSendCargoWeight+"</td>"
		+"<td >"+trainSendCargoTrainNum+"</td>"
		+"<td >"+trainArriveCargoWeight+"</td>"
		+"<td >"+trainArriveCargoTrainNum+"</td>"
		+"<td >"+trainArriveLossCargoTrainWeight+"</td>"
		/*送达*/
		+"<td >"+sendGetCargoWeight+"</td>"
		+"<td >"+sendGetCargoCarNum+"</td>"
		+"<td >"+sendArriveCargoWeight+"</td>"
		+"<td >"+sendArriveCargoNum+"</td>"
		+"<td >"+sendArriveLossCargoWeight+"</td>"
		+"<td >"+project.finishCargoWeight+"吨</td>" 	 /*<!-- 运输完成吨位 -->*/
		+"<td >"+project.runningCargoWeight+"吨</td>"	 /*<!-- 在途运输吨位 -->*/
		+"<td >"+project.transitCargoWeight+"吨</td>" 	 /*<!-- 中转库存吨位 -->*/
		+"</tr>")
	})
	        	}	
</script>		

</html>