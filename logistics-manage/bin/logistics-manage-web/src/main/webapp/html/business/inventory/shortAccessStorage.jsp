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
        <script type="text/javascript" src="${pageContext.request.contextPath}/public/config/url.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/public/search/search.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
        <script src="${pageContext.request.contextPath}/public/bootstrap/My97DatePicker/WdatePicker.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>   
   		
   		<script type="text/javascript" src="${pageContext.request.contextPath}/public/search/get-basic-data.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/business/inventory/shortAccessStorage.js"></script>
		<script src="${pageContext.request.contextPath}/static/js/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/util.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.serializejson.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js" ></script>
		<link href="${pageContext.request.contextPath}/css/page/page.css" rel="stylesheet"/>
        
    </head>
    <body class="shortAccessStorage">
        <!-- 短驳出入库查询表单 -->
        <div class="form project_form container_top">
            <form class="form-inline maxWidth" id="searchForm">
				<div id="wrap">
					<div class="form-group">
						<label>项目编号：</label>
						<input name='projectCode' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>分支机构：</label>
						<select name="branchGroupName">
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
						</select>
					</div>
					<div class="form-group dataTimes">
						<label>日期从</label>
						<input name="beginDate" id="d244" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
						<i class="fa  fa-calendar-check-o" ></i>
					</div>
					<div class="form-group dataTimes secTime">
						<label>至</label>
						<input name="endDate" id="d244" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
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
						<input  id="d244" name="arriveThingDate" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
						<i class="fa  fa-calendar-check-o " id="calendar2"></i>
					</div>
				</div>
               <div class="foot">
					<a class="btn btn-success" href="javascript:;"><em class="search"></em> 
						<span id="searchSpan" onclick="search('/inventory/shortAccessStorageByPage.do')" >搜索</span>
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
                            <c:forEach items='${permissions }' var='permission' >
								<c:if test="${permission.code=='queryShortAccessStorage'}">
									  <a href="javascript:;" class="exportBtn query"  id="queryStatistics"><span>查询统计</span></a> 
								</c:if>
								<c:if test="${permission.code=='exportShortAccessStorage'}">
									<a href="javascript:;" class="exportBtn export checks" id="export"><span>导出</span></a>
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
								<button type="button"class="btn sureBtn" data-dismiss="modal">确定</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</html>