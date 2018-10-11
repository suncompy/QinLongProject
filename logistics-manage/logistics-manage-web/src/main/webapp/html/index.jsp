<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<c:set var="ctxPath1" value='${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/' />
<c:set var="ctxPath" value='//${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/' />
<base href="${ctxPath1 }" />
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="shortcut icon" href="${ctxPath }favicon.ico" type="image/x-icon" />
		<title>首页</title>
		<!-- Bootstrap core CSS -->
		<link href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.css" rel="stylesheet">
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<link href="${pageContext.request.contextPath}/public/bootstrap/css/ie10-viewport-bug-workaround.css" rel="stylesheet ">
		<link href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet " />
		<link href="${pageContext.request.contextPath}/public/public.css" rel="stylesheet" />
		<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
		<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js "></script><![endif]-->
		<script src="${pageContext.request.contextPath}/public/bootstrap/js/ie10-viewport-bug-workaround.js"></script>

		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js "></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js "></script>
    <![endif]-->
    <script type="text/javascript">
		//定义一级菜单数组
		var arrFirstMenu=new Array();
		//定义一级菜单样式
		var arrFirstMenuClass = new Array();
		<c:forEach items="${firstMenuDetailList}" var="tbMenu" varStatus="st">
			arrFirstMenu[${st.index}] = '${tbMenu.code }';
			arrFirstMenuClass[${st.index}] = '${tbMenu.iconClass }';
		</c:forEach>
    </script>
	</head>
	<body>
		<nav class="navbar navbar-inverse navbar-fixed-top nav_top">
			<div class="container-fluid" style="height: 70px;">
				<div class="navbar-header">
					<!--<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                   </button>-->
					<a class="navbar-brand" href="javascript:void(0);">
						<div id="logo" class="clearfix">
							<div id="logo_img"><img src="${pageContext.request.contextPath}/img/logo.png " /></div>
							<div id="logo_name"></div>
						</div>
					</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-left top_nav_ul" id="firstMenuUlId">
						<c:forEach items="${firstMenuDetailList}" var="tbMenu" varStatus="st">
							<li class="nav_left" id="${tbMenu.code }">
								<a  <c:if test="${st.index == 0 }">class="actives" href="${tbMenu.url }" target="contentIframe"</c:if>><em class="${tbMenu.iconClass }s"  ></em>${tbMenu.name }</a>
							</li>
						</c:forEach>
					</ul>
					<ul class="nav navbar-nav navbar-right top_left_ul  clearfix">
						<li>
							<a href="javascript:void(0);">
								<img src="${pageContext.request.contextPath}/img/user.png" />
								<span class="nav_right">您好！<em id="userName">${userName }</em></span>
							</a>
						</li>
						<li class="tree_line">
							<a href="javascript:void(0);">|</a>
						</li>
						<li class="tree_line">
							<a>
								<img src="${pageContext.request.contextPath}/img/pwd.png" />
								<span id="alterPasswd" class="nav_right">修改密码</span>
							</a>
						</li>
						<!--  
						<li class="tree_line">
							<a href="javascript:void(0);">|</a>
						</li>
						<li>
							<a href="javascript:void(0);">
								<img src="${pageContext.request.contextPath}/img/messg.png" />
								<span class="nav_right">未读<em id="unreadMsg">0</em>封</span>
							</a>
						</li>
						-->
						<li class="tree_line">
							<a href="javascript:void(0);">|</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/logout.do">
								<img src="${pageContext.request.contextPath}/img/quit.png" />
								<span id="quit" class="nav_right">注销</span>
							</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		</nav>
		<div class="sidebar">
			<c:forEach items="${firstMenuDetailList}" var="menuDetail" varStatus="st">
				<ul class="nav nav-sidebar" id="ul_${menuDetail.code }" >
				<c:forEach items="${menuDetail.menus}" var="secondMenuDetail" varStatus="st2">
					<li class="card">
						<a class="card-link" data-toggle="collapse" data-parent="#ul_${menuDetail.code}" href="#${secondMenuDetail.id}">
							<i class="${secondMenuDetail.iconClass}"></i>
							<span class="mar">${secondMenuDetail.name}</span>
							<em class="arrow iconClass"></em>
						</a>
						<ul id="${secondMenuDetail.id}" class="collapse left_nav">
							<c:forEach items="${secondMenuDetail.menus}" var="thirdMenuDetail" varStatus="st3">
								<li>
									<c:choose>
									   <c:when test="${fn:contains(thirdMenuDetail.url,'.html')}"> 
									   	<a target="contentIframe" href="${ctxPath}${thirdMenuDetail.url}">${thirdMenuDetail.name}</a>
									   </c:when>
									   <c:otherwise>
									       <a target="contentIframe" href="${pageContext.request.contextPath}${thirdMenuDetail.url}">${thirdMenuDetail.name}</a>
									   </c:otherwise>
									</c:choose>
								</li>
							</c:forEach>
						</ul>
					</li>
				</c:forEach>
			</ul>
			</c:forEach>

		</div>
		</div>
		<div id="showMask"></div>
		<div class="main ">
			<div class="right_div" id="allRight">
				<iframe src="businessHome.do" id="contentIframe" name="contentIframe" frameborder="0" width="100%" height="100%">		
  	</iframe>
			</div>
		</div>
		</div>
<!-- 模态框（Modal） -->
    <div class="modal fade" id="mdlPasswd" tabindex="-1" style="z-index: 100003 ;" role="dialog" data-keyboard="false" data-backdrop="static" aria-hidden="true">
        <div class="modal-dialog large">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">修改密码</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="frmPasswd">
                        <div class="form-group">
                            <label class="col-md-2 control-label">密码</label>
                            <div class="col-md-3">
                                <input name="passwd" type="password"/>
                            </div>
                            <label class="col-md-3 control-label">再次输入密码</label>
                            <div class="col-md-3">
                                <input name="passwdAgain" type="password"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" id="btnPasswdSave">确定</button>
                    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                </div>
            </div>
        </div>
    </div>
		<!-- Bootstrap core JavaScript
    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script>
			window.jQuery || document.write('<script src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"><\/script>')
		</script>
		<script src="${pageContext.request.contextPath}/static/js/invokeHome.js"></script>
		<script src="${pageContext.request.contextPath}/js/index.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/public.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
		<script src="${pageContext.request.contextPath}/public/bootstrap/js/holder.min.js"></script>
        <script src="${pageContext.request.contextPath}/public/jquery-nicescroll-master-141203232303/jquery.nicescroll.js"></script>
		<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
		<script src="${pageContext.request.contextPath}/public/bootstrap/js/holder.min.js"></script>
		<script src="${pageContext.request.contextPath}/public/bootstrap/My97DatePicker/WdatePicker.js"></script>
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<script src="${pageContext.request.contextPath}/public/bootstrap/js/ie10-viewport-bug-workaround.js"></script>
		<script type="text/javascript">
		$(function() { 
		/*
		 $.fd.ajax({
				url : "bussinessCount/curOrg",
				type : "get",
				success : function(d) {
					if(d.data && d.data.name){
						$("#logo_name").html(d.data.name);
					}else{
						$("#logo_name").html('第一物流中心');
					}
				}
		 });*/
		 $.fd.ajax({
				url : 'api/basicdata/types/sysOrgTop',
				type : 'get',
				data : {},
				success : function(d) {
					if(!d.data || !d.data[0] || !d.data[0].text){
						$("#logo_name").html('第一物流中心');
						return false;
					}
					$("#logo_name").html(d.data[0].text);
				}
		 });
		 $("#alterPasswd").click(function(e) {
			 e.preventDefault();
			 $("#frmPasswd")[0].reset();
			 $("#mdlPasswd").modal("show");
		 });
		 $("#btnPasswdSave").click(function(e) {
			 e.preventDefault();
			 var sysPass = $("#frmPasswd").serializeJson();
			 var type = "post";
			 var url = "api/alterPasswd";
			 $.fd.ajax({
				url : url,
				type : type,
				data : sysPass,
				//showMsg:true,
				success : function(d) {
					if(d.code=="201"){
						$("#mdlPasswd").modal("hide");
						$.alert("密码修改成功!",function(){
							$.fd.logout();
						});
					}else{
						$.alert("密码修改失败!");
					}
				}
			});
		 });
		 
		});
		</script>
	</body>

</html>