<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
		<title>首页-司机</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/driver/css/index.css" />
		<script src="${pageContext.request.contextPath}/public/js/jquery-3.2.1.min.js"></script>
		<script src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.min.js"></script>

	</head>

	<body>

		<!-- star top -->
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">
						LOGO车辆信息平台
					</a>
				</div>
				<div class="collapse navbar-collapse">

					<ul class="nav navbar-nav navbar-right">
						<li>

							<a href=""><span class="glyphicon glyphicon-bell" aria-hidden="true"></span>通知</a>

						</li>
						<li role="separator" class="divider">
							<li>
								<a href="">${user.username}<span class="caret"></span></a>

							</li>
							<li role="separator" class="divider">
								<li>
									<a href="">车辆信息平台<span class="caret"></span></a>

								</li>
								<li role="separator" class="divider">
									<li>

										<a href="${pageContext.request.contextPath}/user/loginOut"><span class="glyphicon glyphicon-off" aria-hidden="true"></span>注销</a>
									</li>
					</ul>
				</div>
			</div>
		</nav>
		<!-- end top -->
		<div class="container-fluid">

			<div class="row">
				<!-- star sidebar -->
				<div class="col-sm-3 col-md-2 sidebar">
					<ul class="nav nav-sidebar" id="accordion">
						<li class="card">
							<a class="card-link actives" href="${pageContext.request.contextPath}/business/carManIndex" target="MyIframe">
								<span class="glyphicon glyphicon-home"></span><span class="mar">首页</span>
							</a>
						</li>
						<li class="card">
							<a class="card-link" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" status="1" id="userId">
								<i class="fa fa-user-circle-o"></i>
								<span class="mar">账号管理</span>
							</a>

							<ul id="collapseOne" class="collapse left_nav">
								<li>
									<a href="${pageContext.request.contextPath}/business/toAffiliated" target="MyIframe">挂靠信息</a>
								</li>
							</ul>
						</li>
					</ul>

				</div>
				<!-- end sidebar -->

				<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
					<iframe name="MyIframe" id="MyIframe" frameborder="0" width="100%" height="100%" src="${pageContext.request.contextPath}/business/carManIndex"></iframe>

				</div>
				<script type="text/javascript" src="${pageContext.request.contextPath}/driver/js/index.js"></script>
	</body>

</html>