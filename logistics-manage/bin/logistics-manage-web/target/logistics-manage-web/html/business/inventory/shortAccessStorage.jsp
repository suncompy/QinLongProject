<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="UTF-8">
        <title>短驳出入库查询</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/public/public.css" />
        <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/business/inventory/inventory.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>       
        <script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
        <script src="${pageContext.request.contextPath}/public/bootstrap/My97DatePicker/WdatePicker.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/business/inventory/shortAccessStorage.js"></script>
   		<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js" ></script>
		<link href="${pageContext.request.contextPath}/css/page/page.css" rel="stylesheet"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.serializejson.js"></script>
    </head>
    
    <!--搜索-->
    <script>
	function search(param) {
		var search = JSON.stringify($(param).parent('a').parent('div').parent('form').serializeJSON());
		$.ajax({
			type: 'post',
			url:'${pageContext.request.contextPath}/inventory/shortAccessStorageByPage.do',  
			data: {
				page: 1,
				orderSearch: search
			},
			cache: false,
			dataType: 'json',
			success: function(data) {
	        		if(data.status==200){
	        			htmlTable(data.data.rows);
	        			//重新绑定分页
					    $("#page").paging({
							pageNo:1,
							totalPage:data.data.totalPage,
							totalSize:data.data.limit,
							callback: function(num) {
							 	searchByPage(num);
							}
						})
	        		}
	    	}
		})
	}
	</script>
    
    <body class="shortAccessStorage">
        <!-- 短驳出入库查询表单 -->
        <div class="form project_form container_top">
            <form class="form-inline maxWidth" id="orderSearchForm">
				<div id="wrap">
					<div class="form-group">
						<label>项目编号：</label>
						<input name='projectCode' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>分支机构：</label>
						<select name="branchGroupName">
							<option></option>
							<c:forEach items="${branchGroups}" var="branchGroup">
								<option value="${branchGroup.name}">${branchGroup.name}</option>	
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
						<label>运单编号：</label>
						<input name='orderCode' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>货物品名：</label>
						<select name="cargoName">
							<option></option>
							<c:forEach items="${cargos}" var="cargo">
								<option value="${cargo.cargoName}">${cargo.cargoName}</option>	
							</c:forEach>
						</select>
					</div>
					<div class="form-group dataTimes">
						<label>日期从</label>
						<input name='beginDate' type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-M-d'})"/>
						<i class="fa  fa-calendar-check-o" ></i>
					</div>
					<div class="form-group dataTimes secTime">
						<label>至</label>
						<input name="endDate" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-M-d'})"/>
						<i class="fa  fa-calendar-check-o"></i>
					</div>
					<div class="form-group">
						<label>发货单位：</label>
						<input name='sendCompany' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>收货单位：</label>
						<input name='receiptCompany' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>&emsp;&emsp;车号：</label>
						<input name="carPlateNumber" type="text" />
					</div>
					<div class="form-group dataTimes">
						<label>到货时间：</label>
						<input  id="d244" name="receipterDate" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy.MM.dd'})"/>
						<i class="fa  fa-calendar-check-o " id="calendar2"></i>
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
        <!-- 库存管理表格 -->
        <div class="container_bottom tableDiv">
            <div class="tabbable" id="tabs-529262">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#panel1" data-toggle="tab" data>项目列表</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <!--项目列表表格开始-->
                    <div class="tab-pane active" id="panel1">
                        <div class="domain">
                            <div class="tableBg">
                                <a href="javascript:;" class="exportBtn query"  id="queryStatistics"><span>查询统计</span></a> 
                                <!--<a href="javascript:;" class="exportBtn look"><span>查看</span></a> -->
                                <a href="javascript:;" class="exportBtn export" id="export"><span>导出</span></a>
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
                                            <th>运单编号</th>
                                            <th>联运模式</th>
                                            <th>分支机构</th>
                                            <th>阶段</th>
                                            <th>发货单位</th>
                                            <th>收货单位</th>
                                            <th>货物品名</th>
                                            <th>车号</th>
                                            <th>提货时间</th>
                                            <th>提货吨位</th>
                                            <th>到货时间</th>
                                            <th>到货吨位</th>
                                            <th>耗损</th>
                                            <th>货场</th>
                                            <th>货位</th>
                                        </tr>
                                    </thead>
                                    <tbody id="orderTbody">
                                    <c:forEach items="${orderList.rows}" var="order">
                                        <tr>
                                            <td><label class="demo--label">
								            <input class="demo--checkbox" type="checkbox"  value="${order.projectId}" >
							                  <span class="demo--checkboxInput"></span>
							                         	</label></td>
                                            <td id=""  >${order.projectCode}</td>
                                             <c:if  test="${order.type==1}">
													<td>集装箱</td>
											</c:if>
											<c:if  test="${order.type==2}">
													<td>散装箱</td>
											</c:if>
                                            <td id="">${order.orderCode}</td>
                                             <c:if  test="${order.transportType==0}">
													<td>汽运</td>
											</c:if>
											 <c:if  test="${order.transportType==1}">
													<td>接取</td>
											</c:if>
											 <c:if  test="${order.transportType==2}">
													<td>送达</td>
											</c:if>
											 <c:if  test="${order.transportType==3}">
													<td>火运</td>
											</c:if>
											 <c:if  test="${order.transportType==4}">
													<td>接取+火运</td>
											</c:if>
											<c:if  test="${order.transportType==5}">
													<td>火运+送达</td>
											</c:if>
											<c:if  test="${order.transportType==6}">
													<td>联运</td>
											</c:if>
											<c:if  test="${order.transportType==7}">
													<td>接取+送达</td>
											</c:if>
                                            <td id="">${order.branchGroupName}</td>
                                            
                                            <c:if  test="${order.stepSelectCode==0}">
													<td>接取</td>
											</c:if>
											<c:if  test="${order.stepSelectCode==1}">
													<td>送达</td>
											</c:if>
                                            
                                            <td id="">${order.sendCompany}</td>
                                            <td id="">${order.receiptCompany}</td>
                                            <td id="">${order.cargoName}</td>
                                            <td id="">${order.carPlateNumber}</td>
                                            <td id="">
                                            <fmt:formatDate value="${order.takeDeliveryDate}"
														pattern="yyyy-MM-dd HH:mm" />
											</td>
                                            <td id="">${order.sendGross}T</td>    
                                            <td id="">
                                            <fmt:formatDate value="${order.receipterDate}"
														pattern="yyyy-MM-dd HH:mm" />
											</td>
                                            <td id="">${order.receiptGross}T</td>
                                            <td id="">${order.sendGross-order.receiptGross}T</td>
                                              <c:if  test="${order.stepSelectCode==0}">
													  <td id="">${order.distributionCargoPlace}</td>
													  <td id="">${order.distributionCargoSite}</td>
											</c:if>
											<c:if  test="${order.stepSelectCode==1}">
													  <td id="">${order.takeCarogoPlaceName}</td>
													  <td id="">${order.takeCargoSiteName}</td>
											</c:if>
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
                </div>
            </div>
        </div>
     
		<!--查询统计模态框--> 
        <div class="modal fade queryStatisticsModal" tabindex="-1" role="dialog" id="queryStatisticsModal"  data-backdrop="static">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">查询统计</h4>
                        <span data-dismiss="modal" aria-label="Close"></span>
                    </div>
                    <div class="modal-body">
                        <div >
                            <h5>项目信息</h5>
                            <ul class="clearfix">
                        		<li >
                                	<label>项目编号：</label>
                                	<span name='projectCode'></span>
                           	 	</li>
                            	<li >
                                	<label>分支机构：</label>
                                	<span name="branchGroupName"></span>
                            	</li>
                            	<li >
                                	<label>项目类型：</label>
                                	<span name='projectType'></span>
                            	</li>
                            	<li >
                                	<label>联运模式：</label>
                                	<span name='transportType'></span>
                            	</li>
                            </ul>
                        </div>
                        <hr>
                        <div >
                            <h5>货物信息</h5>
                            <ul class="clearfix">
                        		<li >
                                	<label>货物品名：</label>
                                	<span name='cargoName'></span>
                           	 	</li>
                            	<li >
                                	<label>货物规格：</label>
                                	<span name='cargoSpecifications'></span>
                            	</li>
                            	<li >
                                	<label>货物单价：</label>
                                	<span name='cargoPrice'> </span>
                            	</li>
                            	<li >
                                	<label>计价单位：</label>
                                	<span name='valuationUnitName'></span>
                            	</li>
                            </ul>
                        </div>
                        <hr>
                        <div class="table-responsive">
                        	<table class="table " id="orderDetailTable" >
                        		<!--<tr>
                        			<th>序号</th>
                        			<th>运单编号</th>
                        			<th>车号</th>
                        			<th>阶段</th>
                        			<th>提货时间</th>
                        			<th>提货吨位</th>
                        			<th>到货时间</th>
                        			<th>到货吨位</th>
                        			<th>损耗</th>
                        			<th>货场</th>
                        			<th>货位</th>
                        		</tr>
                        		<tr>
                        			<td id="">1</td>
                        			<td id="">WTE6545656</td>
                        			<td id="">皖A12345</td>
                        			<td id="">接取</td>
                        			<td id="">2017.09.08 10:00</td>
                        			<td id="">200吨</td>
                        			<td id="">2017.09.08 10:00</td>
                        			<td id="">200吨</td>
                        			<td id="">0</td>
                        			<td id="">库里火车站</td>
                        			<td id="">货位A-12</td>
                        		</tr>-->
                        	</table>
                        </div>
                        <hr>
                        <div>
                            	<h5>总计</h5>
                            	<ul class="clearfix">
                            		<li >
                                    	<label>总计运单：</label>
                                    	<span name="sumOrderSize"></span>
                               	 	</li>
                                	<li >
                                    	<label>总计提货吨位：</label>
                                    	<span name="sumSendGross"></span>
                                	</li>
                                	<li >
                                    	<label>总计损耗：</label>
                                    	<span name="sumWastage"></span>
                                	</li>
                                	<li >
                                    	<label>总计到货吨位：</label>
                                    	<span name="sunReceiptGross"></span>
                                	</li>
                            	</ul>
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
	</body>

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
<!--查询统计-->
<script>
	$("#queryStatistics").click(function() {
		if($("input:checkbox[type='checkbox']:checked").length > 1) {
			$("#msgContent").html("");
        	$("#msgContent").html("抱歉,不可批量查询");
			$("#deletnullModal").modal();
			retun ;
		} else if($("input:checkbox[type='checkbox']:checked").length == 0) {
			$("#msgContent").html("");
        	$("#msgContent").html("请选择一条要查询的数据");
			$("#deletnullModal").modal();
			retun ;
		} 
		var projectId = $("input:checkbox[type='checkbox']:checked").val();
		$.ajax({  
			type:'post',      
			url:'${pageContext.request.contextPath}/inventory/shortAccessStorageStatistics.do', 
		    data:{projectId:projectId},
		    cache:false,
		    dataType:'json',
			success:function(data){
				if(data.status==200){
						var orderHelpPojo = data.data;
						$('span[name=projectCode]').html(orderHelpPojo.projectCode);
						$('span[name=branchGroupName]').html(orderHelpPojo.branchGroupName);
						
						//项目类型
		    			var projectType="";
		    			if(orderHelpPojo.projectType==0){
		    				projectType="集装箱";
		    			}else if(orderHelpPojo.projectType==1){
		    				projectType="散装";
		    			}
		    			//联运模式
		    			var transportType="";
		    			if(orderHelpPojo.transportType==0){
		    				transportType="汽运";
		    			}else if(orderHelpPojo.transportType==1){
		    				transportType="接取";
		    			}else if(orderHelpPojo.transportType==2){
		    				transportType="送达";
		    			}else if(orderHelpPojo.transportType==3){
		    				transportType="火运";
		    			}else if(orderHelpPojo.transportType==4){
		    				transportType="接取+火运";
		    			}else if(orderHelpPojo.transportType==5){
		    				transportType="火运+送达";
		    			}else if(orderHelpPojo.transportType==6){
		    				transportType="联运";
		    			}else if(orderHelpPojo.transportType==7){
		    				transportType="接取+送达";
		    			}
	        			//计价单位
		    			var  valuationUnitName="";
		    			if(orderHelpPojo.valuationUnitName==0){
		    				valuationUnitName="吨";
		    			}else if(orderHelpPojo.valuationUnitName==1){
		    				valuationUnitName="位";
		    			}
						
						$('span[name=cargoName]').html(orderHelpPojo.cargoName);
						$('span[name=cargoSpecifications]').html(orderHelpPojo.cargoSpecifications);
						$('span[name=cargoPrice]').html(orderHelpPojo.cargoPrice);
						$('span[name=valuationUnitName]').html(valuationUnitName);
						$('span[name=transportType]').html(transportType);
						$('span[name=projectType]').html(projectType);
						
						/*运单统计*/
						$('#orderDetailTable').html("<tr><th>序号</th><th>运单编号</th><th>车号</th><th>阶段</th><th>提货时间</th><th>提货吨位</th><th>到货时间</th><th>到货吨位</th><th>损耗</th><th>货场</th><th>货位</th></tr>");
						
						$.each(orderHelpPojo.orders, function(index,order) {
							var stepSelectCode="";
						
			  				if(order.stepSelectCode==0){
			  					stepSelectCode="接取";
			  				}else if(order.stepSelectCode==1){
			  					stepSelectCode="送达";
			  				}
							
							$('#orderDetailTable').append("<tr>"
                        		+"<td id='' style='text-align: center;'>"+(index+1)+"</td>"
                        		+"<td id=''>"+order.orderCode+"</td>"
                        		+"<td id=''>"+order.carPlateNumber+"</td>"
                        		+"<td id=''>"+stepSelectCode+"</td>"
                        		+"<td id=''>"+order.takeDeliveryDate+"</td>"			/*提货时间*/
                        		+"<td id=''>"+order.sendGross+"吨</td>"		/*提货吨位*/
                        		+"<td id=''>"+order.receipterDate+"</td>"			/*到货时间*/	
                        		+"<td id=''>"+order.receiptGross+"吨</td>"		/*到货吨位*/	
                        		+"<td id=''>"+(order.sendGross-order.receiptGross)+"</td>"			/*损耗*/
                        		+"<td name='resaonCargos"+index+"'></td>"		/*货场*/
                        		+"<td name='resaonSites"+index+"'></td>"			/*货位*/
                        		+"</tr>");
							$("td[name='resaonCargos"+index+"']").html("");
							$("td[name='resaonSites"+index+"']").html("");
							if(order.stepSelectCode==0){
								$("td[name='resaonCargos"+index+"']").append(""+order.distributionCargoPlace+""); 
								$("td[name='resaonSites"+index+"']").append(""+order.distributionCargoSite+""); 
							}else if(order.stepSelectCode==1){
								$("td[name='resaonCargos"+index+"']").append(""+order.takeCarogoPlaceName+""); 
								$("td[name='resaonSites"+index+"']").append(""+order.takeCargoSiteName+"");
							}
							
						});
						$('span[name=sumOrderSize]').html(orderHelpPojo.totalOrder);
						$('span[name=sumSendGross]').html(orderHelpPojo.totalPickUpWeight+"吨");
						$('span[name=sumWastage]').html(orderHelpPojo.totalWastageWeight+"吨");
						$('span[name=sunReceiptGross]').html(orderHelpPojo.totalArriveWeight+"吨");
						
		    			$("#queryStatisticsModal").modal();
		    		}else{
		    			$("#msgContent").html("");
		    			$("#msgContent").html(data.msg);
		    			$("#deletnullModal").modal();
		    		}
		   	 	}
		})
		
		
	});	
</script>

<!--分页-->
<script >
	$("#page").paging({
		pageNo:1,
		totalPage:${orderList.totalPage},
		totalSize:${orderList.limit},
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
       		url:'${pageContext.request.contextPath}/inventory/shortAccessStorageByPage.do',  
	       	data:{page:num,orderSearch:JSON.stringify($('#searchForm').serializeJSON())},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
        		var stocks = data.data.rows;
    			htmlTable(stocks);
        	}  
    	}
	  })
	}
	
	function htmlTable(results){
		var orders = results;
		$('#orderTbody').html("");
		$.each(orders,function(index,order){
			var type="";
			if(order.type==1){
				type="集装箱";
			}else if(order.type==2){
				type="散装箱";
			}
			
			var stepSelectCode="";
			if(order.stepSelectCode==0){
				stepSelectCode="接取";
			}else if(order.stepSelectCode==1){
				stepSelectCode="送达";
			}
			
			
			$('#orderTbody').append("<tr>"
				+"<td style='text-align: center;'><label class='demo--label'>"
				 +" <input class='demo--checkbox' value='"+order.projectId+"' type='checkbox'   >"
                  +"<span class='demo--checkboxInput'></span>"
                 +"</label></td>"
			  +"<td id=''>"+order.projectCode+"</td>"
			  +"<td id=''>"+type+"</td>"
			  +"<td >"+order.orderCode+"</td>"
			  +"<td name='resaonType"+index+"'></td>"
			   +"<td >"+order.branchGroupName+"</td>"
			     +"<td >"+stepSelectCode+"</td>"
			  +"<td >"+order.sendCompany+"</td>"
			  +"<td id=''>"+order.sendCompany+"</td>"
			  +"<td id=''>"+order.cargoName+"</td>"
			  +"<td id=''>"+order.carPlateNumber+"</td>"
			  +"<td id=''>"+order.takeDeliveryDate+"</td>"
			  +"<td id=''>"+order.sendGross+"T</td>"
			  +"<td id=''>"+order.receipterDate+"</td>"/*到货时间*/
              +"<td id=''>"+order.receiptGross+"</td>"
              +"<td id=''>"+(order.sendGross-order.receiptGross)+"T</td>"
              +"<td name='resaonCargo"+index+"'></td>"
              +"<td name='resaonSite"+index+"'></td>"
			 +"</tr>");
			$("td[name='resaonCargo"+index+"']").html("");
			$("td[name='resaonSite"+index+"']").html("");
			if(order.stepSelectCode==0){
				$("td[name='resaonCargo"+index+"']").append(""+order.distributionCargoPlace+""); 
				$("td[name='resaonSite"+index+"']").append(""+order.distributionCargoSite+""); 
			}else if(order.stepSelectCode==1){
				$("td[name='resaonCargo"+index+"']").append(""+order.takeCarogoPlaceName+""); 
				$("td[name='resaonSite"+index+"']").append(""+order.takeCargoSiteName+"");
			}
			$("td[name='resaonType"+index+"']").html("");
			if(order.transportType==0){
				$("td[name='resaonType"+index+"']").append("汽运"); 
			}else if(order.transportType==1){
				$("td[name='resaonType"+index+"']").append("接取"); 
			}else if(order.transportType==2){
				$("td[name='resaonType"+index+"']").append("送达"); 
			}else if(order.transportType==3){
				$("td[name='resaonType"+index+"']").append("火运"); 
			}else if(order.transportType==4){
				$("td[name='resaonType"+index+"']").append("接取+火运"); 
			}else if(order.transportType==5){
				$("td[name='resaonType"+index+"']").append("火运+送达"); 
			}else if(order.transportType==6){
				$("td[name='resaonType"+index+"']").append("联运"); 
			}else if(order.transportType==7){
				$("td[name='resaonType"+index+"']").append("接取+送达"); 
			}
		});	
	} 
</script>	

</html>