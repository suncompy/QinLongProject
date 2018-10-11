<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="UTF-8">
        <title>项目核查</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/public/public.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/business/project/project.css" />    
        <script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/business/project/projectCheck.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.serializejson.js"></script>
   		<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js" ></script>
		<link href="${pageContext.request.contextPath}/css/page/page.css" rel="stylesheet"/>
    </head>
    <!--搜索-->
	<script>
		function search(param){
			$.ajax({
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/project/listProjectCheckByPage.do',  
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
    
    <body class="projectCheck">
        <!-- 项目核查表单 -->
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
					<a class="btn btn-success" href="#"><em class="search"></em> 
						<span onclick="search(this)">搜索</span>
					</a>
					<div id="lookMore"></div>
				</div>
		</div>
		    </form>
		</div>
        <!--项目核查表格-->
        <div class="container_bottom tableDiv">
            <div class="tabbable" id="tabs-529262">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#panel1" data-toggle="tab">项目明细</a>
                    </li>
                    <li>
                        <a href="#panel2" data-toggle="tab">项目核算</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <!--项目明细表格开始-->
                    <div class="tab-pane active" id="panel1">
                  
                        <div class="domain">
                            <div class="tableBg">
                               <!-- <a href="#" class="exportBtn look" ><span>查看</span></a> -->
                                <a href="#" onclick="return false;" class="exportBtn export" id="export"><span>导出</span></a>
                            </div>
                            <div class="table-responsive">
                                <table class="table" >
                                    <thead>
                                        <tr class="tableTop">
                                            <th><label class="demo--label">
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
                                            <th>接取提货吨位</th>
                                            <th>接取到货吨位</th>
                                            <th>接取损耗</th>
                                            <th>火运发送吨位</th>
                                            <th>火运到货吨位</th>
                                            <th>火运损耗</th>
                                            <th>送达提货吨位</th>
                                            <th>送达到货吨位</th>
                                            <th>送达损耗</th>
                                        </tr>
                                    </thead>
                                    <tbody id='projectTbody'>
                                      	<c:forEach items="${projectChecks.rows}" var="projectCheck">
										<tr>
											<td><label class="demo--label">
								            <input class="demo--checkbox" type="checkbox"  >
							                  <span class="demo--checkboxInput"></span>
							                         	</label></td>
											<td id="">
												<a href="#" >${projectCheck.projectCode}</a>

											</td>
											<c:if  test="${projectCheck.projectType==0}">
													<td>集装箱</td>
											</c:if>
											<c:if  test="${projectCheck.projectType==1}">
													<td>散装</td>
											</c:if>
											<c:if  test="${projectCheck.transportType==0}">
														<td>汽运</td>
											</c:if>
											<c:if  test="${projectCheck.transportType==1}">
													<td>接取</td>
											</c:if>
											<c:if  test="${projectCheck.transportType==2}">
													<td>送达</td>
											</c:if>
											<c:if  test="${projectCheck.transportType==3}">
													<td>火运</td>
											</c:if>
											<c:if  test="${projectCheck.transportType==4}">
													<td>接取+火运</td>
											</c:if>
											<c:if  test="${projectCheck.transportType==5}">
													<td>火运+送达</td>
											</c:if>
											<c:if  test="${projectCheck.transportType==6}">
													<td>联运</td>
											</c:if>
											<c:if  test="${projectCheck.transportType==7}">
													<td>接取+送达</td>
											</c:if>
											<td>${projectCheck.branchGroupName}</td>
											<td>${projectCheck.sendCargoCompanyName}</td>
											<td>${projectCheck.receiveCargoCompanyName}</td>
											<td>${projectCheck.cargoName}</td>
                                            	<!-- 接取 -->
											<c:if  test="${projectCheck.transportType==0 
											|| projectCheck.transportType==1 || projectCheck.transportType==4 
											|| projectCheck.transportType==6 || projectCheck.transportType==7 }">
														<td id="">${projectCheck.receiveGetCargoWeight}吨</td>
														<td id="">${projectCheck.receiveArriveCargoWeight}吨</td>
														<td id="">${projectCheck.receiveArriveCargoWeight-projectCheck.receiveGetCargoWeight}吨</td>
														
											</c:if>
											<c:if  test="${projectCheck.transportType==3 || projectCheck.transportType==2
											|| projectCheck.transportType==5 || projectCheck.transportType==3  }">
														<td id="">/</td>
														<td id="">/</td>
														<td id="">/</td>
											</c:if>
											
											<!-- 火运  -->
											<c:if  test="${projectCheck.transportType==3 || projectCheck.transportType== 6 
											||  projectCheck.transportType==4 || projectCheck.transportType== 5}">
														<td id="">${projectCheck.trainSendCargoWeight}吨</td>
														<td id="">${projectCheck.trainArriveCargoWeight}吨</td>
														<td id="">${projectCheck.trainArriveCargoWeight-projectCheck.trainSendCargoWeight}吨</td>
											</c:if>
											<c:if  test="${projectCheck.transportType==0 || projectCheck.transportType==1
											||  projectCheck.transportType==2 || projectCheck.transportType==7}">
														<td id="">/</td>
														<td id="">/</td>
														<td id="">/</td>
											</c:if>
											<!-- 送达 -->
											<c:if  test="${projectCheck.transportType==0 || projectCheck.transportType==3 
											|| projectCheck.transportType==1 || projectCheck.transportType==4 }">
														<td id="">/</td>
														<td id="">/</td>
														<td id="">/</td>
											</c:if>
											<c:if  test="${projectCheck.transportType==2 || projectCheck.transportType==5
											|| projectCheck.transportType==6 || projectCheck.transportType==7 }">
														<td id="">${projectCheck.sendGetCargoWeight}吨</td>
														<td id="">${projectCheck.sendArriveCargoWeight}吨</td>
														<td id="">${projectCheck.sendArriveCargoWeight-projectCheck.sendGetCargoWeight}吨</td>
											</c:if>
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
                    <!-- 项目核算结束 -->
                </div>
            </div>
        </div>
        </div>


<!-- 项目明细模态框 -->
        <div class="modal fade detailModal" tabindex="-1" role="dialog"  id="detailModal"  data-backdrop="static">
            <div class="modal-dialog modal-lg" role="document">
                <!-- 项目详情 -->
                <div class="modal-content">
                    <div class="modal-header">
                        <h4  class="modal-title">项目详情</h4>
                        <span data-dismiss="modal" aria-label="Close"></span>
                    </div>
                    <div class="modal-body">
                        <div class="project_info">
                            <div class="info_title">
                                <h5>项目信息</h5>
                                <ul class="clearfix">
                                    <li >
                                        <label>项目编号：</label>
                                        <span>J0001</span>
                                    </li >
                                    <li >
                                        <label>分支机构：</label>
                                        <span>合肥物流分支机构处</span>
                                    </li>
                                    <li >
                                        <label>项目类型：</label>
                                        <span>集装箱</span>
                                    </li >
                                    <li >
                                        <label>联运模式：</label>
                                        <span>联运</span>
                                    </li >
                                    <li>
                                        <label>发货企业：</label>
                                        <span>合肥深合软件有限公司</span>
                                    </li>
                                    <li>
                                        <label>收货企业：</label>
                                        <span>新疆秦龙矿业有限公司</span>
                                    </li>
                                    <li>
                                        <label>货物品名：</label>
                                        <span>煤炭</span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <hr>
                        <div class="pick_info">
                            <h5>接取信息</h5>
                            <ul class="clearfix">
                                <li>
                                    <label>接取提货吨位：</label>
                                    <span>200吨</span>
                                </li>
                                <li>
                                    <label>接取提货车次：</label>
                                    <span>4</span>
                                </li>
                                <li>
                                    <label>接取到货吨位：</label>
                                    <span>200吨</span>
                                </li>
                                <li >
                                    <label>接取到货车次：</label>
                                    <span>4</span>
                                </li>
                                <li >
                                    <label>接取损耗：</label>
                                    <span>0</span>
                                </li>
                                <li >
                                    <label>加权平均化验指标：</label>
                                    <span>GTB-87676</span>
                                </li>
                                
                            </form>
                        </div>
                        <hr>
                        <div class="train_info">
                            <h5>火运信息</h5>
                            <ul class="clearfix">
                                <li>
                                    <label>火运提货吨位：</label>
                                    <span>200吨</span>
                                </li>
                                <li>
                                    <label>火运提货车次：</label>
                                    <span>4</span>
                                </li>
                                <li>
                                    <label>火运到货吨位：</label>
                                    <span>200吨</span>
                                </li>
                                <li>
                                    <label>火运到货车次：</label>
                                    <span>4</span>
                                </li>
                                 <li>
                                    <label>火运损耗：</label>
                                    <span>0</span>
                                </li>
                                <li>
                                    <label>加权平均化验指标：</label>
                                    <span>GTB-87676</span>
                                </li>
                            </ul>
                        </div>
                        <hr>
                        <div class="send_info">
                            <h5>送达信息</h5>
                            <ul class="clearfix">
                                <li>
                                    <label>送达提货吨位：</label>
                                    <span>200吨</span>
                                </li>
                                <li>
                                    <label>送达提货车次：</label>
                                    <span>4</span>
                                </li>
                                <li>
                                    <label>送达到货吨位：</label>
                                    <span>200吨</span>
                                </li>
                                <li>
                                    <label>送达到货车次：</label>
                                    <span>4</span>
                                </li>
                                 <li>
                                    <label>送达损耗：</label>
                                    <span>0</span>
                                </li>
                                <li>
                                    <label>加权平均化验指标：</label>
                                    <span>GTB-87676</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

<!-- 项目核算模态框 -->
        <div class="modal fade  accountingModal" tabindex="-1" role="dialog" id="accountingModal"  data-backdrop="static">
            <div class="modal-dialog modal-lg" role="document">
                <!-- 项目详情 -->
                <div class="modal-content">
                    <div class="modal-header">
                        <h4  class="modal-title">项目详情</h4>
                        <span  data-dismiss="modal" aria-label="Close"></span>
                    </div>
                    <div class="modal-body">
                        <div class="project_info">
                            <div class="info_title">
                                <h5>项目信息</h5>
                                <ul class="clearfix">
                                    <li>
                                        <label>项目编号：</label>
                                        <span>J0001</span>
                                    </li>
                                    <li>
                                        <label>分支机构：</label>
                                        <span>合肥物流分支机构处</span>
                                    </li>
                                    <li>
                                        <label>项目类型：</label>
                                        <span>集装箱</span>
                                    </li>
                                    <li>
                                        <label>联运模式：</label>
                                        <span>联运</span>
                                    </li>
                                    <li>
                                        <label>发货企业：</label>
                                        <span>合肥深合软件有限公司</span>
                                    </li>
                                    <li>
                                        <label>收货企业：</label>
                                        <span>新疆秦龙矿业有限公司</span>
                                    </li>
                                    <li>
                                        <label>货物品名：</label>
                                        <span>煤炭</span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <hr>
                        <div class="pick_info">
                            <h5>接取信息</h5>
                            <ul class="clearfix">
                                <li>
                                    <label>接取到货吨位：</label>
                                    <span>200吨</span>
                                </li>
                                <li>
                                    <label>接取费用：</label>
                                    <span>400元</span>
                                </li>
                                <li>
                                    <label>接取损耗：</label>
                                    <span>0</span>
                                </li>
                                <li>
                                    <label>扣损金额：</label>
                                    <span>200元</span>
                                </li>
                                <li>
                                    <label>应付现金：</label>
                                    <span>1000元</span>
                                </li>
                                <li>
                                    <label>应付油气：</label>
                                    <span>1000元</span>
                                </li>
                                <li>
                                    <label>已对账吨位：</label>
                                    <span>200吨</span>
                                </li>
                                <li>
                                    <label>已开票吨位：</label>
                                    <span>200吨</span>
                                </li>
                                <li>
                                    <label>已结算吨位：</label>
                                    <span>100吨</span>
                                </li>
                                
                            </form>
                        </div>
                        <hr>
                        <div class="train_info">
                            <h5>火运信息</h5>
                            <ul class="clearfix">
                                <li>
                                    <label>火运发送吨位：</label>
                                    <span>200吨</span>
                                </li>
                                <li>
                                    <label>火运费用：</label>
                                    <span>200元</span>
                                </li>
                                <li>
                                    <label>火运损耗：</label>
                                    <span>0</span>
                                </li>
                                <li>
                                    <label>耗损金额：</label>
                                    <span>200元</span>
                                </li>
                                <li>
                                    <label>运输亏损金额：</label>
                                    <span>1000元</span>
                                </li>
                                <li>
                                    <label>已对账吨位：</label>
                                    <span>100吨</span>
                                </li>
                                <li>
                                    <label>已开票吨位：</label>
                                    <span>100吨</span>
                                </li>
                                <li>
                                    <label>已结算吨位：</label>
                                    <span>100吨</span>
                                </li>
                            </ul>
                        </div>
                        <hr>
                        <div class="send_info">
                            <h5>送达信息</h5>

                            <ul class="clearfix">
                                <li>
                                    <label>送达到货吨位：</label>
                                    <span>200吨</span>
                                </li>
                                <li>
                                    <label>送达费用：</label>
                                    <span>200元</span>
                                </li>
                                <li>
                                    <label>送达损耗：</label>
                                    <span>0</span>
                                </li>
                                <li>
                                    <label>扣损金额：</label>
                                    <span>200元</span>
                                </li>
                                 <li>
                                    <label>应付现金：</label>
                                    <span>1000元</span>
                                </li>
                                <li>
                                    <label>应付油气：</label>
                                    <span>1000元</span>
                                </li>
                                <li>
                                    <label>已对账吨位：</label>
                                    <span>200吨</span>
                                </li>
                                <li>
                                    <label>已开票吨位：</label>
                                    <span>100吨</span>
                                </li>
                                <li>
                                    <label>已结算吨位：</label>
                                    <span>100吨</span>
                                </li>
                            </form>
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
    
    <!--分页-->
<script >
	$("#page").paging({
		pageNo:1, 
		totalPage:${projectChecks.totalPage},
		totalSize:${projectChecks.limit},
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
       		url:'${pageContext.request.contextPath}/project/listProjectCheckByPage.do', 
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
			$('#projectTbody').html("");
			$.each(results,function(index,project){
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
			var receiveArriveCargoWeight="/";
			var receiveWastageCargoWeight="/";
			
			if(project.transportType==0
				|| project.transportType==1
				|| project.transportType==4
				|| project.transportType==6
				|| project.transportType==7){
				receiveGetCargoWeight=project.receiveGetCargoWeight+"吨";
				receiveArriveCargoWeight=project.receiveArriveCargoWeight+"吨";
				receiveWastageCargoWeight=(project.receiveArriveCargoWeight-project.receiveGetCargoWeight)+"吨";
			}  
			
			var trainSendCargoWeight="/";
			var trainArriveCargoWeight="/";
			var trainWastageCargoWeight="/";
			
			if(project.transportType==3
				|| project.transportType==4
				|| project.transportType==5
				|| project.transportType==6){
				trainSendCargoWeight=project.trainSendCargoWeight+"吨";
				trainArriveCargoWeight=project.trainArriveCargoWeight+"吨";
				trainWastageCargoWeight=(project.trainSendCargoWeight-project.trainSendCargoWeight)+"吨";
			} 
			
			var sendGetCargoWeight="/";
			var sendArriveCargoWeight="/";
			var sendWastageCargoWeight="/";
			
			if(project.transportType==2
				|| project.transportType==5
				|| project.transportType==6
				|| project.transportType==7){
				sendGetCargoWeight=project.sendGetCargoWeight+"吨";
				sendArriveCargoWeight=project.sendArriveCargoWeight+"吨";
				sendWastageCargoWeight=(project.sendArriveCargoWeight-project.sendGetCargoWeight)+"吨";
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
			+"<td >"+receiveArriveCargoWeight+"</td>"
			+"<td >"+receiveWastageCargoWeight+"</td>"
			/*火运*/
			+"<td >"+trainSendCargoWeight+"</td>"
			+"<td >"+trainArriveCargoWeight+"</td>"
			+"<td >"+trainWastageCargoWeight+"</td>"
			/*送达*/
			+"<td >"+sendGetCargoWeight+"</td>"
			+"<td >"+sendArriveCargoWeight+"</td>"
			+"<td >"+sendWastageCargoWeight+"</td>"
			+"</tr>")
		})
	
	}	
</script>		
  
</html>