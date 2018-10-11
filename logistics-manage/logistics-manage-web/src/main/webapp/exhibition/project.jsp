<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
	<head>
		<meta charset="UTF-8">
		<title>项目详情</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/exhibition/public/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/exhibition/public/bootstrap/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/exhibition/css/show.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/exhibition/public/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/exhibition/public/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/exhibition/js/project.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/exhibition/public/bootstrap/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/exhibition/public/echarts.js"></script>
		<style>
			.project-list ul{
				display: inline-block;
			}
			.project-list li{
				float: left;
				list-style: none;
				margin-right: 10px;
				background-color: #fff;
				border-radius: 10px;
				padding: 10px;
			}
		</style>
	</head>
	<body>
		<!--头部-->
		<div class="head"></div>
		<!--中间-->
		<div class="container" style="overflow: auto;">
			<!--项目列表展示-->
			<!--<div class="project-list">
				<button class="btn btn-default">&lt;</button>
				<ul class="clearfix">
					<li><a href="">TJDAS45454DA44</a></li>
					<li><a href="">TJDAS45454DA45</a></li>
				</ul>	
				<button class="btn btn-default">&gt;</button>
			</div>-->
			
			<div class="box box-primary table-responsive">
				<select class="projectSelect">
					<option>${project.code }</option>
					<!--<option>TJDAS45454DA45</option>
					<option>TJDAS45454DA46</option>-->
				</select>
				<div class="msgTitle">
					<i class="fa fa-clock-o"></i>
					<span>发布于 ${project.createDate }</span>
				</div>
				<ul class="lisMsg">
					<li>
						<span>网点分支：</span>
						<span>${project.createDate }</span>
						<span>|</span>
						<span>模式：${project.transportTypeName }</span>
						<span>|</span>
						<span>类型：${project.transportTypeName }</span>
						<span>|</span>
						<span>委托方：${project.transportTypeName }</span>
					</li>
					<li>
						<span>发货企业：</span>
						<span>${project.sendCargoCompanyName }</span>
					</li>
					<li>
						<span>收货企业：</span>
						<span>${project.receiveCargoCompanyName }</span>
					</li>
					<li>
						<span>货物信息：</span>
						<span>${project.cargoName } &nbsp;&nbsp; ${project.cargoSpecifications }</span>
					</li>
					<li>
						<span>项目流程：${project.projectFlow }</span>
					</li>
				</ul>
			</div>
			<div class="row totalT">
				<div class="col-md-6">
					<div class='shortDiv'>
						<div>
							<label>352</label>
							<span>短驳完成运单</span>	
						</div>
						<div class="aDiv">
							<a href="shortBarge.html">查看短驳运单  	&gt;</a>	
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class='fireDiv'>
						<div>
							<label>47</label>
							<span>火运完成运单</span>	
						</div>
						<div class="aDiv">
							<a href="fileWaybill.html">查看火运运单  	&gt;</a>	
						</div>
					</div>
				</div>
			</div>
		
			<div class="row totalT" >
					<!--短驳状态统计表-->
				<div class="col-md-6 chartDiv">
					<div>
						<h4>短驳状态统计表</h4>
						<div id="circle"></div>
					</div>
				</div>
				<!--火运状态统计表-->
				<div class="col-md-6 chartDiv">
					<div>
						<h4>火运状态统计表</h4>
						<div id='circle2'></div>
					</div>
				</div>
			</div>
			<div class="row totalT" >
					
				<div class="col-md-12 chartDiv">
					<div>
						<h4>运输统计</h4>
						<div id="line"></div>
					</div>
				</div>
				
			</div>
			<div class="row">
		    	<div class="col-sm-12">
		    		    <iframe name="mapPointFrame" scrolling="no" frameborder="0" style="padding: 0px; width: 100%; height: 1000px;"></iframe>		    		
		    	</div>
		    </div>
		</div>
		<!--底部-->
		<div class='foot'></div>

	</body>

</html>