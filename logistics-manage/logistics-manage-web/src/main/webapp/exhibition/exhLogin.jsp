<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<title>登录界面</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/exhibition/public/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/exhibition/css/login.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/exhibition/css/sweetalert2.min.css" />
    <script src="${pageContext.request.contextPath}/exhibition/public/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/exhibition/js/login.js"></script>
    <script src="${pageContext.request.contextPath}/exhibition/js/base.js"></script>
    <script src="${pageContext.request.contextPath}/exhibition/js/sweetalert2.min.js"></script>
</head>
<body>
	<div class="contains">
		<div class="shandow"></div>
		<div class="logoImg">
			<img src="${pageContext.request.contextPath}/exhibition/img/img.png" alt="logo" />
		</div>
		<div class="login">
			<div class="formDiv">
				<form id="loginForm"  method="post" onsubmit="return checkForm()">
					<div class="form-group">
						<input type="text" id="input1" name="username" class="form-control"  placeholder="请输入账号"/>
					</div>
					<div class="form-group">
						<input type="password" id="input2" name="password" class="form-control " placeholder="请输入密码"/>
					</div>
					<button type="submit" class=" btn btn-block" id="login"><a href="#" target="_self">登录</a></button>
				</form>
				<p>注：账号密码请联系管理员获取</p>
			</div>
		</div>
	</div>
<script type="text/javascript">
    /*<![CDATA[*/
    var tale = new $.tale();
    function checkForm() {
        tale.post({
            url: '${pageContext.request.contextPath}/exhibition/doLogin.do',
            data: $("#loginForm").serialize(),
            success: function (result) {
                if (result.status == 200) {
                    window.location.href = '${pageContext.request.contextPath}/exhibition/branchGroupManage.do';
                } else {
                    tale.alertError(result.msg || '登录失败');
                }
            }
        });
        return false;
    }
    /*]]>*/
</script>
</body>
</html>