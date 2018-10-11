<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>挂靠信息</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/driver/css/driver/anchoredInfo.css" />
<script
	src="${pageContext.request.contextPath}/public/js/jquery-3.2.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/driver/js/driver/anchoredInfo.js"></script>
<script type="text/javascript">
	function companyApply(comid,comname,comphone) {
		$.ajax({  
        	type:'post',      
       		url:'${pageContext.request.contextPath}/business/checkAffiliated?comid='+comid+'&comname='+comname+'&comphone='+comphone, 
	        cache:false,
	        dataType:'json',  
        	success:function(data){
        		if(data.rset.bool == false){
        			alert(data.rset.message);
        		}else{
        			alert(data.rset.message);
        			window.location.href="${pageContext.request.contextPath}/business/toAffiliated"; 
        		}
        	}  
    	});	
	
	};
	
	
	function carTeamApply(id) {
		$.ajax({
			type : 'get',
			url : "${pageContext.request.contextPath}/driver/carTeam/apply.do?id="+ id,
			dataType : "json",
			success : function(data) {
				if (data.status != 1) {
					$("#pointResult").html("");
					$("#promptModal").modal();
					$("#pointResult").html(data.msg);
				}else{
					$("#pointResult").html("");
					$("#promptModal").modal();
					$("#pointResult").html(data.msg);
				}
			},
		});
	};
	

	function reApply(id,recordId,type){
		$.ajax({
			type : 'get',
			url : "${pageContext.request.contextPath}/driver/reApply.do?id="+id+"&recordId="+recordId+"&type="+type,
			dataType : "json",
			success : function(data) {
				if(data.status != 1){
					$("#pointResult").html("");
					$("#promptModal").modal();
					$("#pointResult").html(data.msg);
				}else{
					$("#pointResult").html("");
					$("#promptModal").modal();
					$("#pointResult").html(data.msg);
					 window.location.reload();
				}
			},
		});
	};
	
	
	function cancel(id){
		$.ajax({
			type : 'get',
			url : "${pageContext.request.contextPath}/business/cancelAffiliated?id="+id,
			dataType : "json",
			success : function(data) {
				if(data.rset.state == 0){
					alert(data.rset.message);
        			window.location.href="${pageContext.request.contextPath}/business/toAffiliated"; 
				}else{
					alert(data.rset.message);
				}
			},
		});
	};
	
	function closeModal(){
		$("#promptModal").modal("hide");
		window.location.reload();
	}
	
</script>
</head>
<body>
	<div class="container-fluit">
		<div class="row">
			<div class="col-md-12 col-sm-12" style="min-width: 1000px;">
				<c:if test="${not empty belong_carTeam}">
					<!-- 挂靠车队信息 -->
					<div class="anchored-fleet">
						<div class="clearfix">
							<h4 class="pull-left">挂靠车队信息</h4>
							<div class="read-more pull-right"></div>

						</div>
						<hr>
						<div class="wrap fleet-info " style="overflow: hidden;">
							<ul class=" clearfix">
								<li><label>车队名称：</label> <span id="">${belong_carTeam.carItemName}</span></li>
								<li><label>负责人：</label> <span id="">${belong_carTeam.name}</span></li>
								<li><label>挂靠时间：</label> <span id=""> <fmt:formatDate
											value="${belong_carTeam.anchoredDate}" pattern="yyyy.MM.dd" />
								</span></li>
								<li><label>联系方式：</label> <span id="">${belong_carTeam.phone}</span></li>
								<li><a class="pull-right"  onclick="cancel(${belong_carTeam.id},1,${belong_carTeam.recordId})">取消挂靠</a></li>
							</ul>
							<hr>
						</div>
					</div>
				</c:if>
				<c:if test="${not empty affcompanylist}">
					<!-- 挂靠公司信息	  隐藏-->
					<c:forEach items="${affcompanylist}" var="it" varStatus="its">


						<div class="anchored-company">
							<div class="clearfix">
								<h4 class="pull-left">挂靠公司信息</h4>
								<div class="read-more pull-right"></div>
							</div>
							<hr>
							<div class="wrap company-info" style="overflow: hidden;">
								<ul class="clearfix">
									<li><label>公司名称：</label> <span id="">${it.anchored_id}</span></li>
									<li><label>挂靠时间：</label> <span id=""> <fmt:formatDate
												value="${it.anchored_date}" pattern="yyyy.MM.dd" />
									</span></li>
									<li><label>联系方式：</label> <span id="">${it.anchored_phone}</span></li>
									<li><label>当前状态：</label>
										<c:choose>
											<c:when test="${it.status == 1}">
												<span id="" style="color: green; font-weight: bold;">挂靠中</span>
											</c:when>
											<c:when test="${it.status == 2}">
												<span id="" style="color: red; font-weight: bold;">被拒绝</span>
											</c:when>
											<c:when test="${it.status == 3}">
												<span id="" style="color: blue; font-weight: bold;">申请中</span>
											</c:when>
											<c:otherwise>
												<span id="">错误状态</span>
											</c:otherwise>
										</c:choose>
									</li>
									<li><a  onclick="cancel(${it.id})">取消挂靠</a></li>
								</ul>
							</div>
						</div>
					</c:forEach>
				</c:if>


				<!-- 申请挂靠公司信息 -->
				<div class="askCompany">
					<div class="clearfix">
						<h4 class="pull-left">
							申请挂靠公司信息<small>申请挂靠前，请先取消已挂靠车队</small>
						</h4>
						<div class="read-more1 pull-right"></div>
					</div>
					<hr>
					<div class="wrap1" style="overflow: hidden;">
						<div class="detail-info clearfix">
							<c:forEach items="${companylist}" var="it" varStatus="its">
								<c:if test="${its.count%2 != 0}">
									<div class="left-part col-sm-6">
										<div class="clearfix">
											<div class="col-sm-5">
												<label>公司名称：</label> <span id="">${it.name}</span>
											</div>
											<div class="col-sm-5">
												<label>联系方式：</label> <span id="">${it.phone}</span>
											</div>
											<div class="col-sm-2">
												<c:if test="${empty it.anchored_id}">
													<span id=""><a onclick="companyApply('${it.id}','${it.name}','${it.phone}')">申请挂靠${it.anchored_id}</a></span>
												</c:if>
												<c:if test="${not empty it.anchored_id}">
													<span id="">已申请</span>
												</c:if>
											</div>
										</div>
									</div>
								</c:if>

								<c:if test="${its.count%2 == 0}">
									<div class="right-part col-sm-6">
										<div class="clearfix">
											<div class="col-sm-5">
												<label>公司名称：</label> <span id="">${it.name}</span>
											</div>
											<div class="col-sm-5">
												<label>联系方式：</label> <span id="">${it.phone}</span>
											</div>
											<div class="col-sm-2">
												<c:if test="${ empty it.anchored_id}">
													<span id=""><a onclick="companyApply('${it.id}','${it.name}','${it.phone}')">申请挂靠</a></span>
												</c:if>
												<c:if test="${not empty it.anchored_id}">
													<span id="">已申请</span>
												</c:if>
											</div>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</div>

				<!-- 申请挂靠车队信息 -->
				<div class="askFleet">
					<div class="clearfix">
						<h4 class="pull-left">
							申请挂靠车队信息<small>申请挂靠前，请先取消已挂靠公司</small>
						</h4>
						<div class="read-more2 pull-right"></div>
					</div>
					<hr>
					<div class="wrap2" style="overflow: hidden;">
						<div class="detail-info clearfix">
							<c:forEach items="${carteamlist}" var="it" varStatus="its">
								<c:if test="${its.count%2 != 0}">
									<div class="left-part col-sm-6">
										<div class="clearfix">
											<div class="col-sm-5">
												<label>车队名称：</label> <span id="">${it.car_item_name}</span>
											</div>
											<div class="col-sm-5">
												<label>联系方式：</label> <span id="">${it.phone}</span>
											</div>
											<div class="col-sm-2">
												<c:if test="${empty it.anchored_id}">
													<span id=""><a onclick="carTeamApply(${it.id})">申请挂靠</a></span>
												</c:if>
												<c:if test="${not empty  it.anchored_id}">
													<span id="">已申请</span>
												</c:if>
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${its.count%2 == 0}">
									<div class="right-part col-sm-6">
										<div class="clearfix">
											<div class="col-sm-5">
												<label>车队名称</label> <span id="">${it.car_item_name}</span>
											</div>
											<div class="col-sm-5">
												<label>联系方式：</label> <span id="">${it.phone}</span>
											</div>
											<div class="col-sm-2">
												<c:if test="${empty it.anchored_id}">
													<span id=""><a onclick="carTeamApply(${it.id})">申请挂靠</a></span>
												</c:if>
												<c:if test="${not empty  it.anchored_id}">
													<span id="">已申请</span>
												</c:if>
											</div>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>

				</div>

				<!-- 历史挂靠记录 -->
				<div class="anchoredHistory">
					<div class="clearfix">
						<h4 class="pull-left">历史挂靠记录</h4>
						<div class="read-more3 pull-right"></div>
					</div>
					<hr>
					<div class="wrap3 history-info" style="overflow: hidden;">
						<c:forEach items="${affhistorylist}" var="it" varStatus="its">

							<c:if test="${it.type == 1}">
								<ul class="clearfix">
									<li><label>车队名称：</label> <span id="">${it.anchored_name}</span></li>
									<li><label>
											<c:if test="${it.status ==2}">
												被拒时间：
											</c:if>
											<c:if test="${it.status ==0}">
												取消时间：
											</c:if>
										</label> <span id=""> <fmt:formatDate
												value="${it.anchored_date}" pattern="yyyy.MM.dd" />
									</span></li>
									<li><label>联系方式：</label> <span id="">${it.anchored_phone}</span></li>
									<li><a onclick="reApply(${it.anchoredId},${it.id},${it.type})">重新申请</a></li>
								</ul>
							</c:if>

							<c:if test="${it.type == 2}">
								<ul class="clearfix">
									<li><label>公司名称：</label> <span id="">${it.anchored_name}</span></li>
									<li><label>
										<c:choose>
											<c:when test="${it.status == 2}">被拒时间：</c:when>
											<c:when test="${it.status == 0}">取消时间：</c:when>
											<c:when test="${it.status == 3}">申请时间：</c:when>
											<c:otherwise>错误时间：</c:otherwise>
										</c:choose>
										</label> <span id=""> <fmt:formatDate
												value="${it.anchored_date}" pattern="yyyy.MM.dd" />
									</span></li>
									<li><label>联系方式：</label> <span id="">${it.anchored_phone}</span></li>
									<!-- 
									<li><a onclick="reApply(${it.anchoredId},${it.id},${it.type})">重新申请</a></li>
									 -->
								</ul>
							</c:if>
						</c:forEach>
					</div>
				</div>


			</div>
		</div>
	</div>
	
	
	
	
	
	
	<!--确认提示模态框-->		
	    <div class="modal fade promptModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" id="promptModal" >
            <div class="modal-dialog " role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"  data-dismiss="modal"></span>
                    </div>
                    <div class="modal-body">
                    	<span id="pointResult"></span>
                        
                    </div>
                    <div class="modal-footer">
       					<button type="button" class="btn " onclick="closeModal()">确认</button>
     				</div>
                    
                </div>
            </div>
        </div>
	
	
	

</body>
</html>