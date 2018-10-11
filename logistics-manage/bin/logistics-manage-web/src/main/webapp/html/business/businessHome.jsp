<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>业务首页</title>
				<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.min.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/business/businessHome.css"/>
		<style type="text/css">
		.projectForm select{width: 160px;height: 24px;border-radius: 4px; border: 1px solid #dde3ef;line-height: 24px !important;font-size: 12px; padding-left: 5px;}
		</style>
		<script src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>
		<script src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/public/bootstrap/My97DatePicker/WdatePicker.js"></script>
		<script src="${pageContext.request.contextPath}/static/js/invokeHome.js"></script>
		<script src="${pageContext.request.contextPath}/js/business/businessHome.js"></script>
	</head>
	<body>
		<div class="container-fluit">
			<div class=" row clearfix" >
				<div class="col-sm-7" >
		    		<div class="short-count" >
			    		<div class="title clearfix">
							<h4 class="pull-left">项目统计<!-- <span>本月</span> --></h4>
							<form class="form-inline projectForm" id="frmProjectDisp">
	    <div class="form-group">
	        <label>项目编号：</label>
	        <select name="projectId"></select>
        </div>
		<div class="form-group"> 
            <button class="btn-xs btn-primary btn-query pull-right" id="btnProjectQuery">查询</button>
	    </div>
	  </form>
						</div>
			    		<hr>
			    		<div id="trainLine">
			    			
			    		</div>
		    		</div>
		    	</div>
				<div class=" col-sm-5" >
					<div class="train-count" >
						<div class="title clearfix">
							<h4 class="pull-left">火运状态统计</h4>
						</div>
						<div id="circle"></div>
					</div>
				</div>
			</div>
		    
		    <div class="row clearfix">
		    	<div class="col-sm-7" >
		    		<div class="short-count" >
			    		<div class="title clearfix">
							<h4 class="pull-left">运输统计<!-- <span>本月</span> --></h4>
							<!-- <a href="businessQuery/vehicleQuery.html" class="more pull-right">查看更多>></a> -->
						</div>
			    		<hr>
			    		<div id="line">
			    			
			    		</div>
		    		</div>
		    	</div>
		    	<div class=" col-sm-5" >
					<div class="train-count" >
						<div class="title clearfix">
							<h4 class="pull-left">短驳状态统计</h4>
							<!-- <a href="businessQuery/waybillQuery.html" class="more pull-right">查看更多>></a> -->
						</div>
						<div id="circleBulk"></div>
					</div>
				</div>
		    </div>
		    <div class="row">
		    	<div class="col-sm-12">
		    		    <iframe name="mapPointFrame" scrolling="no" frameborder="0" style="padding: 0px; width: 100%; height: 1000px;"></iframe>		    		
		    	</div>
		    </div>
		
		
		
		</div>
	</body>
</html>