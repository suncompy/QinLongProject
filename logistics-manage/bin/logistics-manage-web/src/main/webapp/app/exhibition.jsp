<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>项目展示</title>
		<script src="${pageContext.request.contextPath}/static/js/invokeProject.js"></script>
	    <script src="${pageContext.request.contextPath}/static/lib/jquery/twbsPagination/jquery.twbsPagination.js"></script>
		<script src="${pageContext.request.contextPath}/app/js/exhibition.js"></script>
	</head>

	<body>
		<nav class="navbar navbar-default navbar-fixed-top navbg">
			<div class="container">
				<div class="navbar-header">
					<div id="logo" class="clearfix">
						<div class="navLogo"><img src="img/logo.png"></div>
						<div id="logo_name">
							<div class="title">项目查询</div>
							<div class="Cname">新疆秦龙物流有限公司</div>
						</div>
					</div>
				</div>
				<ul class="nav navbar-nav navbar-right menu">
					<li class="dropdown">
						<a href="/logistics-carmanage-web" class="menucolor" target="_blank">车队平台 </a>
					</li>
					<li>
						<a href="/logistics-manage-web" class="menucolor" target="_blank">物流后台</a>
					</li>
				</ul>
				<form name="frmProjectQuery" class="navbar-form navbar-right">
					<div class="form-group searchdiv">
						<input type="text" name="projectCode" class="searchInput" placeholder="请输入你要查询的项目">
						<span class="searchIcon"></span>
					</div>
				</form>
			</div>
		</nav>
		<div class="mainBox">
		<div class="banner-img"><img src="img/projects.png"  /></div>
			
			<div class="conTop">
				<div class="container cons">
					<div class="conTitle">
						<p class="titleName">Latest project</p>
						<p class="titleNames">最新项目</p>
					</div>
				</div>
			</div>
			<div class="container">
				<ul class="projectList clearfix" name="LatestProjects"></ul>
			</div>
			<div class="conTop">
				<div class="container cons">
					<div class="conTitle">
						<p class="titleName">Project information</p>
						<p class="titleNames">项目列表</p>
					</div>
				</div>
			</div>
			<div class="container">
				<ul class="projectList clearfix" name="allProjects"></ul>
			</div>
			<div class="container paginationul">
				<ul class="pagination" id="listPagination"></ul>
			</div>
		</div>
		<div id='footCon'>
			<div class="container footConDiv clearfix">
				<div class="pFoot">
					<p>本平台仅作为项目列表的展示作用<br/>目前为测试阶段</p>
				</div>
				<div>
					<dl>
						<dt>
						<dd><h4>快捷导航</h4></dd>
						<dd><a href="/logistics-carmanage-web" target="_blank">车队平台</a></dd>					
						<dd><a href="/logistics-manage-web" target="_blank">物流后台</a></dd>
					</dt>
					</dl>
				</div>
			</div>
		</div>
		<script>
			$(function() {
				$(".pagination > li a").click(function() {
					$(this).addClass("click").parent().siblings().children().find('a').removeClass("click");
				});
				$("body").on('mouseenter', ".projectList li", function() {
					$(this).children().find('label').css("color", "#FFFFFF");
				});
				$("body").on('mouseleave', ".projectList li", function() {
					$(this).children().find('label').css("color", "#666666");
				});
			})
			$(window).scroll(function ()
	{
		var st = $(this).scrollTop();
		if(st>=90){
			$(".navbar").addClass("navs").removeClass("navbg");
           $(".menu li a").addClass("menubg").removeClass("menucolor");
          $(".searchdiv input").addClass('searchInputs').removeClass("searchInput");
           $(".searchdiv span").addClass('searchIcons').removeClass("searchIcon");
           $(".title").css("font-weight","700");
		}
		else{
			$(".navbar").addClass("navbg").removeClass("navs");
			$(".menu li a").addClass("menucolor").removeClass("menubg");
			$(".searchdiv input").addClass('searchInput').removeClass("searchInputs");
			$(".searchdiv span").addClass('searchIcon').removeClass("searchIcons");
			 $(".title").css("font-weight","500");
		}
	});
		</script>
	</body>

</html>