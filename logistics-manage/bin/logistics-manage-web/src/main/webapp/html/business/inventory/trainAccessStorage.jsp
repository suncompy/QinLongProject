<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="UTF-8">
        <title>火运出入库查询</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/public/public.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/business/inventory/inventory.css" /> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>       
        <script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/My97DatePicker/WdatePicker.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/business/inventory/trainAccessStorage.js"></script>
    	<script src="${pageContext.request.contextPath}/static/js/common.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/util.js"></script>
    	<!--分页-->
    	<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.serializejson.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js" ></script>
		<link href="${pageContext.request.contextPath}/css/page/page.css" rel="stylesheet"/>
    	<script type="text/javascript">
    		
	    	function closeModal(){
	    		$("#queryStatisticsModal").modal("hide");
	    		window.location.reload();
	    	}
	    	
    		function statisticsInfo(){
    			if($("#operatorTable input:checkbox[name='chekcProject']:checked").length > 1) {
    				$("#pointMsg").html("");
    				$("#pointMsg").html("不可批量操作");
    				$("#nullModal").modal();
    			}
    			else if($("#operatorTable input:checkbox[name='chekcProject']:checked").length == 0) {
    				$("#pointMsg").html("");
    				$("#pointMsg").html("请选择一条要操作的数据");
    				$("#nullModal").modal();
    			}else{
    				/* $('#showMask', window.parent.document).show(); */
    				$("#queryStatisticsModal").modal();
    				var projectId = $("#operatorTable input:checkbox[name='chekcProject']:checked").val();
    				$.fd.ajax({
    					type : "GET",
    					url : "${pageContext.request.contextPath}/trainAccessStorage/trainAccessStorageDteail.do?projectId="+projectId,
    					dataType : 'json',
    					success : function(data) {
    						var proType="";
							if(data.data.tp.projectType ==0){
								proType="集装箱";
							}else if(data.data.tp.projectType ==1){
								proType ="散装";
							}
							var sta = "";
	        				if(data.data.tp.transportType ==3){
	        					sta = "火运";
	        				}
	        				if(data.data.tp.transportType ==4){
	        					sta = "接取+火运";
	        				}
	        				if(data.data.tp.transportType ==5){
	        					sta = "火运+送达";
	        				}
	        				if(data.data.tp.transportType ==6){
	        					sta = "联运";
	        				}
	        				if(data.data.tp.transportType ==7){
	        					sta = "接取+送达";
	        				}
	        				var unitName = "";
	        				if(data.data.tp.valuationUnitName == 1){
	        					unitName="吨";
							}else if(data.data.tp.valuationUnitName == 0){
								unitName ="件"; 
							}
	        				$("#projectInfoDetail").empty();
							$("#projectInfoDetail").append(
	   	                			"<li><label>项目编号：</label><span>"+data.data.tp.projectCode+"</span></li>"
	   								+"<li><label>分支机构：</label><span>"+data.data.tp.branchGroupName+"</span></li>"
	   								+"<li><label>项目类型：</label><span>"+proType+"</span></li>"
	   								+"<li><label>联运模式：</label><span>"+sta+"</span></li>");
							$("#cargoInfoDetail").empty();
							$("#cargoInfoDetail").append(
	   	                			"<li><label>货物品名：</label><span>"+data.data.tp.cargoName+"</span></li>"
	   								+"<li><label>货物规格：</label><span>"+data.data.tp.cargoSpecifications+"</span></li>"
	   								+"<li><label>货物单价：</label><span>"+data.data.tp.cargoPrice+"</span></li>"
	   								+"<li><label>计价单位：</label><span>"+unitName+"</span></li>");
    					
							if(data.data.tp.projectType ==0){
								$("#containerTable").show();
								$("#notContainerTable").hide();
								$("#detailInfoBody").empty();
								var detailInfo = data.data["trainOrderDetailList"];
								$.each(detailInfo,function(index,details){
									$("#detailInfoBody").append(
											"<tr><td>"+ (index+1) +"</td><td>"+details.pleaseTrainNumber+"</td><td>"+details.carType+"</td><td>"+details.carNumber+"</td><td>"+details.containerNumber1+"</td><td>"+details.containerNumber2+"</td><td>"+details.sendDate+"</td>"
											+"<td>"+details.cargoPlaceName+"</td><td>"+details.cargoSiteName+"</td><td>"+details.sendWeight+"</td><td>"+details.conSendWeight2+"</td><td>"+details.arriveUnloadTime+"</td><td>"+details.arriveCargoPlaceName+"</td>"
											+"<td>"+details.arriveCargoSiteName+"</td><td>"+details.unloadWeight+"</td><td>"+details.conUnloadWeight2+"</td><td>"+details.wastage+"</td></tr>");
		   	                	});
							}else if(data.data.tp.projectType ==1){
								$("#containerTable").hide();
								$("#notContainerTable").show();
								$("#bulkDetailInfoBody").empty();
								var bulkDetailInfo = data.data["trainOrderDetailList"];
								$.each(bulkDetailInfo,function(index,bulkDetails){
									$("#bulkDetailInfoBody").append(
											"<tr><td>"+ (index+1) +"</td><td>"+bulkDetails.pleaseTrainNumber+"</td><td>"+bulkDetails.carType+"</td><td>"+bulkDetails.carNumber+"</td><td>"+bulkDetails.sendDate+"</td>"
											+"<td>"+bulkDetails.cargoPlaceName+"</td><td>"+bulkDetails.cargoSiteName+"</td><td>"+bulkDetails.sendWeight+"</td><td>"+bulkDetails.arriveUnloadTime+"</td><td>"+bulkDetails.arriveCargoPlaceName+"</td>"
											+"<td>"+bulkDetails.arriveCargoSiteName+"</td><td>"+bulkDetails.unloadWeight+"</td><td>"+bulkDetails.wastage+"</td></tr>");
		   	                	});
							}
							$("#totalInfoDetail").empty();
							$("#totalInfoDetail").append(
	   	                			"<li><label>总计请车次数：</label><span>"+data.data.sumDetailInfo.sumPleaseNum+"</span></li>"
	   								+"<li><label>总计请车数：</label><span>"+data.data.sumDetailInfo.sumCarNum+"</span></li>"
	   								+"<li><label>总计请车吨位：</label><span>"+data.data.sumDetailInfo.sumPleaseWeight+"吨</span></li>"
	   								+"<li><label>总计损耗：</label><span>"+data.data.sumDetailInfo.sumWastage+"吨</span></li>"
	   								+"<li><label>总计到货吨位：</label><span>"+data.data.sumDetailInfo.sumArriveWeight+"吨</span></li>");
    					}
    				});
    			}
    			
    		}
    	</script>
    </head>
    <body class="trainAccessStorage">
        <!-- 火运出入库查询表单 -->
        <div class="form project_form container_top">
            <form class="form-inline maxWidth" id="searchForm">
				<div id="wrap">
					<div class="form-group">
						<label>项目编号：</label>
						<input name='projectCode' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>分支机构：</label>
						<select name="branchId">
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
							<option value="1">散装箱</option>
						</select>
					</div>
					<div class="form-group">
						<label>请车单号：</label>
						<input name='pleaseTrainNumber' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>货物品名：</label>
						<select name="cargoId" onchange="setCargoName(this)">
							<option></option>
							<c:forEach items="${cargos}" var="cargo">
								<option value="${cargo.id}">${cargo.cargoName}</option>	
							</c:forEach>
						</select>
					</div>
					<!-- <div class="form-group dataTimes">
						<label>&emsp;日期从&emsp;</label>
						<input name="beginDate" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-M-d'})"/>
						<i class="fa fa-calendar-check-o"></i>
					</div>
					<div class="form-group dataTimes secTime">
						<label>至</label>
						<input name='endDate' id="d244" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-M-d'})"/>
						<i class="fa fa-calendar-check-o" ></i>
					</div> -->
					<div class="form-group">
						<label>发货企业：</label>
						<select name="sendCargoCompanyId">
							<option></option>
							<c:forEach items="${customers}" var="customers">
								<option value="${customers.id}">${customers.companyName}</option>	
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label>收货企业：</label>
						<select name="receiveCargoCompanyId">
							<option></option>
							<c:forEach items="${customers}" var="customers">
								<option value="${customers.id}">${customers.companyName}</option>	
							</c:forEach>
						</select>
					</div>
					<!-- <div class="form-group">
						<label>&emsp;&emsp;车号：</label>
						<select></select>
					</div> -->
					<div class="form-group">
						<label>始发站点：</label>
						<input name='beginSiteName' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>到达站点：</label>
						<input name='endSiteName' type="text" maxlength="30"/>
					</div>
					<div class="form-group dataTimes">
						<label>发货时间：</label>
						<input  name='newSendDate' id="sendDate" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
						<i class="fa  fa-calendar-check-o " id="calendar2"></i>
					</div>
					<div class="form-group dataTimes">
						<label>到达时间：</label>
						<input name='newArriveDate' id="arriveDate" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
						<i class="fa  fa-calendar-check-o " id="calendar2"></i>
					</div>
					<!-- <div class="form-group">
						<label>集装箱号：</label>
						<select></select>
					</div> -->
				</div>
              <div class="foot">
					<a class="btn btn-success" href="javascript:;"><em class="search"></em> 
						<span onclick="search(this)">搜索</span>
					</a>
					<div id="lookMore"></div>
				</div>
		    </form>
        </div>
        <!-- 库存管理表格 -->
        <div class="container_bottom tableDiv">
            <div class="tabbable" id="tabs-529262">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#panel1" data-toggle="tab">项目列表</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <!--项目列表表格开始-->
                    <div class="tab-pane active" id="panel1">
                        <div class="domain">
                            <div class="tableBg">
                             <c:forEach items='${permissions }' var='permission' >
								<c:if test="${permission.code=='queryTrainAccessStorage'}">
									 <a href="javascript:;" class="exportBtn query" id="queryStatistics" onclick="statisticsInfo()"><span>查询统计</span></a> 
								</c:if>
								<c:if test="${permission.code=='exportTrainAccessStorage'}">
									 <a href="javascript:;" class="exportBtn export checks" id="export"><span>导出</span></a>
								</c:if>
							</c:forEach>
                            </div>
                            <div class="table-responsive">
                                <table class="table" id="operatorTable">
                                    <thead>
                                        <tr class="tableTop">
                                            <th> <label class="demo--label">
								            <input class="demo--checkbox" type="checkbox"  >
							                  <span class="demo--checkboxInput"></span>
							                         	</label> </th>
                                            <th>项目编号</th>
                                            <th>项目类型</th>
                                            <th>请车单号</th>
                                            <th>联运模式</th>
                                            <th>分支机构</th>
                                            <th>发货企业</th>
                                            <th>收货企业</th>
                                            <th>货物品名</th>
                                            <th>车型</th>
                                            <th>车号</th>
                                            <th>始发站点</th>
                                            <th>装载吨位</th>
                                            <th>装载货场</th>
                                            <th>装载货位</th>
                                            <th>发送时间</th>
                                            <th>到达站点</th>
                                            <th>到货吨位</th>
                                            <th>到达货场</th>
                                            <th>到达货位</th>
                                            <th>到达时间</th>
                                            <th>耗损</th>
                                        </tr>
                                    </thead>
                                    <tbody id="accessStorageBody">
                                    	<c:forEach var="it" items="${trainAccessStorageList.rows}">
	                                        <tr>
	                                            <td><label class="demo--label">
									            <input class="demo--checkbox" type="checkbox" name="chekcProject" value="${it.projectId}">
								                  <span class="demo--checkboxInput"></span>
								                         	</label></td>
	                                            <td id="">${it.projectCode}</td>
	                                            <td id=""><c:if test="${it.projectType == 0}">集装箱</c:if>
	                                            		  <c:if test="${it.projectType == 1}">散装箱</c:if>
	                                            </td>
	                                            <td id="">${it.pleaseTrainNumber}</td>
	                                            <td id=""><c:if test="${it.transportType == 3}">火运</c:if>
	                                           		 	  <c:if test="${it.transportType == 4}">接取+火运</c:if>
	                                            		  <c:if test="${it.transportType == 5}">火运+送达</c:if>
	                                            		  <c:if test="${it.transportType == 6}">联运</c:if>
	                                            		  <c:if test="${it.transportType == 7}">接取+送达</c:if>
	                                            </td>
	                                            <td id="">${it.branchName}</td>
	                                            <td id="">${it.sendCargoCompanyName}</td>
	                                            <td id="">${it.receiveCargoCompanyName}</td>
	                                            <td id="">${it.cargoName}</td>
	                                            <td id="">${it.carType}</td>
	                                            <td id="">${it.carNumber}</td>
	                                            <td id="">${it.beginSiteName}</td>
	                                            <td id="">${it.entruckWeight}</td>
	                                            <td id="">${it.cargoPlaceName}</td>
	                                            <td id="">${it.cargoSiteName}</td>
	                                            <td id=""><fmt:formatDate value="${it.sendDate}" pattern="yyyy-MM-dd HH:mm" /></td>
	                                            <td id="">${it.endSiteName}</td>
	                                            <td id="">${it.arriveWeight}</td>
	                                            <td id="">${it.arriveCargoPlaceName}</td>
	                                            <td id="">${it.arriveCargoSiteName}</td>
	                                            <td id=""><fmt:formatDate value="${it.arriveDate}" pattern="yyyy-MM-dd HH:mm" /></td>
	                                            <td id="">${it.wastage}</td>
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

                    
                    <!-- 查询统计 -->

                    
                </div>
            </div>
        </div>
    
<!--查询统计模态框--> 
        <div class="modal fade queryStatisticsModal" tabindex="-1" role="dialog" id="queryStatisticsModal"  data-backdrop="static">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">查询统计</h4>
                        <span  aria-label="Close" onclick="closeModal()"></span>
                    </div>
                    <div class="modal-body">
                        <div >
                            <h5>项目信息</h5>
                            <ul class="clearfix" id="projectInfoDetail">
                        		
                            </ul>
                        </div>
                        <hr>
                        <div >
                            <h5>货物信息</h5>
                            <ul class="clearfix" id="cargoInfoDetail">
                        		
                            </ul>
                        </div>
                        <hr>
                        <div class="table-responsive" id="containerTable">
                        	<table class="table " >
                        		<thead>
	                        		<tr>
	                        			<th>序号</th>
	                        			<th>请车单号</th>
	                        			<th>车型</th>
	                        			<th>车号</th>
	                        			<th>集装箱号1</th>
	                        			<th>集装箱号2</th>
	                        			<th>提货时间</th>
	                        			<th>装载货场</th>
	                        			<th>装载货位</th>
	                        			<th>提货吨位1</th>
	                        			<th>提货吨位2</th>
	                        			<th>到货时间</th>
	                        			<th>卸载货场</th>
	                        			<th>卸载货位</th>
	                        			<th>到货吨位1</th>
	                        			<th>到货吨位2</th>
	                        			<th>损耗</th>
	                        		</tr>
                        		</thead>
                        		<tbody id="detailInfoBody">
								</tbody>
                        	</table>
                        </div>
                        <div class="table-responsive" id="notContainerTable">
                        	<table class="table " >
                        		<thead>
	                        		<tr>
	                        			<th>序号</th>
	                        			<th>请车单号</th>
	                        			<th>车型</th>
	                        			<th>车号</th>
	                        			<th>提货时间</th>
	                        			<th>装载货场</th>
	                        			<th>装载货位</th>
	                        			<th>提货吨位</th>
	                        			<th>到货时间</th>
	                        			<th>卸载货场</th>
	                        			<th>卸载货位</th>
	                        			<th>到货吨位</th>
	                        			<th>损耗</th>
	                        		</tr>
                        		</thead>
                        		<tbody id="bulkDetailInfoBody">
								</tbody>
                        	</table>
                        </div>
                        <hr>
                        <div class="total">
                        	<h5>总计</h5>
                        	<ul class="clearfix" id="totalInfoDetail">
                        	</ul>
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
							<span id="pointMsg">请选择一条要操作的数据</span>
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
       <div class="modal fade exportModal" tabindex="-1" role="dialog"  id="exportModal"  data-backdrop="static">
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
</body>
<script>
	//分页
		$("#page").paging({
		pageNo:1,
		totalPage:${trainAccessStorageList.totalPage},
		totalSize:${trainAccessStorageList.limit},
		callback: function(num) {
					$.ajax({  
		        	type:'post',      
		       		url:'${pageContext.request.contextPath}/trainAccessStorage/trainAccessStorageListByPage.do',  
			        data:{page:num,trainOrderDetail:JSON.stringify($("#searchForm").serializeJSON())},
			        cache:false,
			        dataType:'json',
		        	success:function(data){
		        		if(data.status==200){
		        		var accessStorageList = data.data.rows;
		        		$('#accessStorageBody').html("");
		        		$.each(accessStorageList,function(index,list){
	        				$('#accessStorageBody').append("<tr>"
									+"<td style='text-align: center;'><label class='demo--label'>"
									 +" <input class='demo--checkbox'  name='chekcProject' type='checkbox' value='"+list.projectId+"'  >"
		                              +"<span class='demo--checkboxInput'></span>"
		                             +"</label></td>"
		                             +"<td id=''>"+list.projectCode+"</td>"
		                             +"<td name='projectType"+index+"'></td>"
									 +"<td id=''>"+list.pleaseTrainNumber+"</td>"
								 +"<td name='transportType"+index+"'></td>"
								 +"<td id=''>"+list.branchName+"</td>"
								 +"<td id=''>"+list.sendCargoCompanyName+"</td>"
								 +"<td id=''>"+list.receiveCargoCompanyName+"</td>"
								 +"<td id=''>"+list.cargoName+"</td>"
								 +"<td id=''>"+list.carType+"</td>"
								 +"<td id=''>"+list.carNumber+"</td>"
								 +"<td id=''>"+list.beginSiteName+"</td>"
								 +"<td id=''>"+list.entruckWeight+"</td>"
								 +"<td id=''>"+list.cargoPlaceName+"</td>"
								 +"<td id=''>"+list.cargoSiteName+"</td>"
								 +"<td id=''>"+list.sendDate+"</td>"
								 +"<td id=''>"+list.endSiteName+"</td>"
								 +"<td id=''>"+list.arriveWeight+"</td>"
								 +"<td id=''>"+list.arriveCargoPlaceName+"</td>"
								 +"<td id=''>"+list.arriveCargoSiteName+"</td>"
								 +"<td id=''>"+list.arriveDate+"</td>"
								 +"<td id=''>"+list.wastage+"</td>"
								 +"</tr>");
			        			$("td[name='projectType"+index+"']").html("");
			        			if(list.projectType==0){
			        				$("td[name='projectType"+index+"']").append("集装箱"); 
			        			}
			        			else if(list.projectType==1){
			        				$("td[name='projectType"+index+"']").append("散装箱"); 
			        			}
			        			$("td[name='transportType"+index+"']").html("");
			        			if(list.transportType==3){
			        				$("td[name='transportType"+index+"']").append("火运"); 
			        			}
			        			else if(list.transportType==4){
			        				$("td[name='transportType"+index+"']").append("接取+火运"); 
			        			}
			        			else if(list.transportType==5){
			        				$("td[name='transportType"+index+"']").append("火运+送达"); 
			        			}
			        			else if(list.transportType==6){
			        				$("td[name='transportType"+index+"']").append("联运"); 
			        			}
			        			else if(list.transportType==7){
			        				$("td[name='transportType"+index+"']").append("接取+送达"); 
			        			}
		        		});	
		    		}
		      	}
			})
					}
		});
	
		function search(param){
			var search =JSON.stringify($(param).parent('a').parent('div').parent('form').serializeJSON());
			var nums = 1;
			$.ajax({
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/trainAccessStorage/trainAccessStorageListByPage.do',  
		        data:{page:nums,trainOrderDetail:search},
		        dataType:'json',
	        	success:function(data){
	        		$("#page").paging({
	        			pageNo:1,
	        			totalPage:data.data.totalPage,
	        			totalSize:data.data.limit,
	        			callback: function(num) {
	        						$.ajax({  
	        			        	type:'post',      
	        			       		url:'${pageContext.request.contextPath}/trainAccessStorage/trainAccessStorageListByPage.do',  
	        				        data:{page:num,trainOrderDetail:JSON.stringify($("#searchForm").serializeJSON())},
	        				        cache:false,
	        				        dataType:'json',
	        			        	success:function(data){
	        			        		if(data.status==200){
	        			        		var accessStorageList = data.data.rows;
	        			        		$('#accessStorageBody').html("");
	        			        		$.each(accessStorageList,function(index,list){
	        		        				$('#accessStorageBody').append("<tr>"
	        										+"<td style='text-align: center;'><label class='demo--label'>"
	        										 +" <input class='demo--checkbox'  name='chekcProject' type='checkbox' value='"+list.projectId+"'  >"
	        			                              +"<span class='demo--checkboxInput'></span>"
	        			                             +"</label></td>"
	        			                             +"<td id=''>"+list.projectCode+"</td>"
	        			                             +"<td name='projectType"+index+"'></td>"
	        										 +"<td id=''>"+list.pleaseTrainNumber+"</td>"
	        									 +"<td name='transportType"+index+"'></td>"
	        									 +"<td id=''>"+list.branchName+"</td>"
	        									 +"<td id=''>"+list.sendCargoCompanyName+"</td>"
	        									 +"<td id=''>"+list.receiveCargoCompanyName+"</td>"
	        									 +"<td id=''>"+list.cargoName+"</td>"
	        									 +"<td id=''>"+list.carType+"</td>"
	        									 +"<td id=''>"+list.carNumber+"</td>"
	        									 +"<td id=''>"+list.beginSiteName+"</td>"
	        									 +"<td id=''>"+list.entruckWeight+"</td>"
	        									 +"<td id=''>"+list.cargoPlaceName+"</td>"
	        									 +"<td id=''>"+list.cargoSiteName+"</td>"
	        									 +"<td id=''>"+list.sendDate+"</td>"
	        									 +"<td id=''>"+list.endSiteName+"</td>"
	        									 +"<td id=''>"+list.arriveWeight+"</td>"
	        									 +"<td id=''>"+list.arriveCargoPlaceName+"</td>"
	        									 +"<td id=''>"+list.arriveCargoSiteName+"</td>"
	        									 +"<td id=''>"+list.arriveDate+"</td>"
	        									 +"<td id=''>"+list.wastage+"</td>"
	        									 +"</tr>");
	        				        			$("td[name='projectType"+index+"']").html("");
	        				        			if(list.projectType==0){
	        				        				$("td[name='projectType"+index+"']").append("集装箱"); 
	        				        			}
	        				        			else if(list.projectType==1){
	        				        				$("td[name='projectType"+index+"']").append("散装箱"); 
	        				        			}
	        				        			$("td[name='transportType"+index+"']").html("");
	        				        			if(list.transportType==3){
	        				        				$("td[name='transportType"+index+"']").append("火运"); 
	        				        			}
	        				        			else if(list.transportType==4){
	        				        				$("td[name='transportType"+index+"']").append("接取+火运"); 
	        				        			}
	        				        			else if(list.transportType==5){
	        				        				$("td[name='transportType"+index+"']").append("火运+送达"); 
	        				        			}
	        				        			else if(list.transportType==6){
	        				        				$("td[name='transportType"+index+"']").append("联运"); 
	        				        			}
	        				        			else if(list.transportType==7){
	        				        				$("td[name='transportType"+index+"']").append("接取+送达"); 
	        				        			}
	        			        		});	
	        			    		}
	        			      	}
	        				})
	        						}
	        			});
	        		var accessStorageList = data.data.rows;
	        		$('#accessStorageBody').html("");
	        		$.each(accessStorageList,function(index,list){
	        			$('#accessStorageBody').append("<tr id="+list.id+">"
								+"<td style='text-align: center;'><label class='demo--label'>"
								 +" <input class='demo--checkbox'  name='chekcProject' type='checkbox' value='"+list.projectId+"'  >"
	                              +"<span class='demo--checkboxInput'></span>"
	                             +"</label></td>"
	                             +"<td id=''>"+list.projectCode+"</td>"
	                             +"<td name='projectType"+index+"'></td>"
								 +"<td id=''>"+list.pleaseTrainNumber+"</td>"
							 +"<td name='transportType"+index+"'></td>"
							 +"<td id=''>"+list.branchName+"</td>"
							 +"<td id=''>"+list.sendCargoCompanyName+"</td>"
							 +"<td id=''>"+list.receiveCargoCompanyName+"</td>"
							 +"<td id=''>"+list.cargoName+"</td>"
							 +"<td id=''>"+list.carType+"</td>"
							 +"<td id=''>"+list.carNumber+"</td>"
							 +"<td id=''>"+list.beginSiteName+"</td>"
							 +"<td id=''>"+list.entruckWeight+"</td>"
							 +"<td id=''>"+list.cargoPlaceName+"</td>"
							 +"<td id=''>"+list.cargoSiteName+"</td>"
							 +"<td id=''>"+list.sendDate+"</td>"
							 +"<td id=''>"+list.endSiteName+"</td>"
							 +"<td id=''>"+list.arriveWeight+"</td>"
							 +"<td id=''>"+list.arriveCargoPlaceName+"</td>"
							 +"<td id=''>"+list.arriveCargoSiteName+"</td>"
							 +"<td id=''>"+list.arriveDate+"</td>"
							 +"<td id=''>"+list.wastage+"</td>"
							 +"</tr>");
		        			$("td[name='projectType"+index+"']").html("");
		        			if(list.projectType==0){
		        				$("td[name='projectType"+index+"']").append("集装箱"); 
		        			}
		        			else if(list.projectType==1){
		        				$("td[name='projectType"+index+"']").append("散装箱"); 
		        			}
		        			$("td[name='transportType"+index+"']").html("");
		        			if(list.transportType==3){
		        				$("td[name='transportType"+index+"']").append("火运"); 
		        			}
		        			else if(list.transportType==4){
		        				$("td[name='transportType"+index+"']").append("接取+火运"); 
		        			}
		        			else if(list.transportType==5){
		        				$("td[name='transportType"+index+"']").append("火运+送达"); 
		        			}
		        			else if(list.transportType==6){
		        				$("td[name='transportType"+index+"']").append("联运"); 
		        			}
		        			else if(list.transportType==7){
		        				$("td[name='transportType"+index+"']").append("接取+送达"); 
		        			}
	        		});	
	        	}
			});
		}
		</script>
</html>