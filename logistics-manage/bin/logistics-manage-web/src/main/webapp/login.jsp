<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
	<title>登录</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/>
    <script src="${pageContext.request.contextPath}/static/js/invoke.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
    <script src="${pageContext.request.contextPath}/js/login.js"></script>
	<script type="text/javascript">
	//在top页面中打开
	if(window.top != window.self) {
		window.top.location.href = window.self.location.href;
	}
	</script>
</head>
<body>

<div class="lg-content">
<div class="lg-title">
   <img src="${pageContext.request.contextPath}/img/lg-title.png"></div>
   <!-- 用户登录表单 -->
      <form class="lg-index form-horizontal" method="post" action="${pageContext.request.contextPath}/doLogin.do">
        <span class="lg-heading">用户登录</span>
          <div class="form-group">
            <div class="col-sm-12">
                <div class="input-group ">
					<span class="input-group-addon"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></span>
			    	<input type="text" name="username" class="form-control" placeholder="请输入用户名">
		    	</div>
		    	<span class="warning">${message}</span>
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-12">
                <div class="input-group">
    		    	<span class="input-group-addon"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span></span>
    				<input type="password" name="password" class="form-control" placeholder="请输入密码">
    			</div>
    		    <span class="warning"></span>
            </div>
          </div>
          
          <div class="form-group">
            <div class="col-sm-5 col-xs-5">
              <button type="submit" class="btn btn-warning btn-block">登录</button>
              
            </div>
             <div class="col-sm-5 col-xs-5">
               <a id="btnLogin" href="javascript:void(0)" >忘记密码？</a>
             </div>
          </div>
        </form>
</div>
</div>
<!-- 手机验证模态框 -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
      <div class="modal-dialog " role="document">
        <div class="modal-content">
          <div class="modal-body">
            <h4  id="myModalLabel">手机验证</h4>
            <span class="glyphicon  glyphicon-remove-sign" data-dismiss="modal" aria-label="Close"></span>
            <form class="form-horizontal" id="forgetId">
                  
                  <div class="form-group">
                     <label class="col-xs-3 control-label">手机号：</label>
                     <div class="col-xs-9">
                       <input type="mobile" class="form-control" name="phone" placeholder="请输入手机号">
                     </div>
                   </div>
                   <div class="form-group">
                      <label class="col-xs-3 control-label">验证码：</label>
                      <div class="col-xs-5">
                        <input type="text" class="form-control" name="checkCode" placeholder="请输入验证码">
                      </div>
                      <div class="col-xs-4">
                      <button type="button" name="btnSendCheckCode" class="btn btn-primary btn-block">发送验证码</button>
                      </div>
                    </div>
                     <div class="form-group">
                      <label class="col-xs-3 control-label">密码：</label>
                      <div class="col-xs-9">
                          <input name="passwd" class="form-control" type="password" placeholder="请输入密码"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-xs-3 control-label">再次输入密码：</label>
                      <div class="col-xs-9">
                          <input name="passwdAgain" class="form-control" type="password" placeholder="请再次输入密码"/>
                      </div>
                    </div>    
                    <div class="form-group">
                    <div class="col-xs-11 col-xs-offset-1 "> 
                       <button type="button" name="btnForgetPassSubmit" class="btn btn-warning btn-block">提交</button>
                    </div>
                    </div>
                  </form>
          </div>
        </div>
      </div>
    </div>
</body>
</html>