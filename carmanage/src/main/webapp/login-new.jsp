<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>登录</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/iconfont.css">
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
<body>
	<div class="login-nav fix">
		<!-- 
		<ul class="f-r">
			<li><a href="#">首页</a></li>
			<li><a href="#">协同</a></li>
			<li><a href="#">应用</a></li>
			<li><a href="#">案例</a></li>
			<li><a href="#">开发者</a></li>
			<li><a href="#">企业版</a></li>
		</ul>
	 -->
	</div>
	<div class="login-banner"></div>
	<div class="login-box">
		<div class="box-con tran">
			<div class="login-con f-l">
				<div class="form-group">
					<input type="text" placeholder="手机号码" /> <span class="error-notic">手机号码不正确</span>
				</div>
				<div class="form-group">
					<input type="password" placeholder="密码"> <span
						class="error-notic">密码不正确</span>
				</div>
				<div class="form-group">
					<button type="submit" class="tran pr">
						<a href="javascript:;" class="tran">登录</a>
					</button>
				</div>
				<div class="from-line"></div>
				<div class="form-group">
					<a href="javascript:;" class="move-signup a-tag tran blue-border">还没有帐号？免费注册<i
						class="iconfont tran">&#xe606;</i></a>
				</div>
				<div class="form-group">
					<a href="javascript:;" class="move-reset a-tag tran">忘记密码？重置 <i
						class="iconfont tran">&#xe606;</i></a>
				</div>
				<!-- 
					<div class="form-group">
						<a href="javascript:;" class="move-other a-tag tran">使用第三方帐号登录<i class="iconfont tran">&#xe606;</i></a>
					</div>
				 -->
			</div>

			<div class="signup f-l">
				<div class="form-group">
					<div class="signup-form">
						<input type="text" placeholder="用户姓名" class="email-mobile" 
							id="driverusername" name="username" onblur="verify.verifyEmail(this)">
					</div>
					<span class="error-notic">邮箱格式不正确</span>
				</div>
				<div class="signup-email">
					<div class="form-group">
						<input type="text" placeholder="身份证号">
					</div>
					<div class="form-group">
						<input type="password" placeholder="密码（字母、数字，至少6位）"
							onblur="verify.PasswordLenght(this)"> <span
							class="error-notic">密码长度不够</span>
					</div>
					<div class="form-group">
						<input type="password" placeholder="确认密码（字母、数字，至少6位）"
							onblur="verify.PasswordLenght(this)"> <span
							class="error-notic">密码长度不够</span>
					</div>
					<div class="form-group" style="width: 260px;">
						<div class="phone-input"  style="width: 200px; float: left;">
							<input type="text" placeholder="手机号码" class="email-mobile"
								onblur="verify.verifyEmail(this)" style="width: 180px;">
						</div>
						<div class="sms-code"  style="width: 60px;float: left;">
								<button type="submit" class="tran pr" style="width: 100px;">
								<a href="javascript:;" class="tran">获取验证码</a>
							</button>
						</div>
						<span class="error-notic">邮箱格式不正确</span>
					</div>
					<div class="form-group">
						<button type="submit" class="tran pr">
							<a href="javascript:;" class="tran">注册</a>
						</button>
					</div>
					<p class="view-clause">
						点击注册，即同意我们的 <a href="#">用户隐私条款</a>
					</p>
				</div>
				<div class="signup-tel" style="display:none">
					<div class="signup-form" id="message-inf" style="display:none">
						<input type="text" placeholder="短信验证码" style="width:180px;"
							onblur="verify.VerifyCount(this)"> <a href="javascript:;"
							class="reacquire">重新获取（59）</a> <span class="error-notic">验证码输入错误</span>
					</div>
					<div class="form-group">
						<button type="submit" class="tran get-message pr">
							<a href="javascript:;" class="tran">获取短信验证码</a>
						</button>
					</div>
				</div>
				<div class="from-line"></div>
				<div class="form-group">
					<a href="javascript:;" class="move-login a-tag tran blue-border">已有帐号？登录<i
						class="iconfont tran">&#xe606;</i></a>
				</div>
				<!-- 
					<div class="form-group">
						<a href="javascript:;" class="move-other a-tag tran">使用第三方帐号登录<i class="iconfont tran">&#xe606;</i></a>
					</div>
				 -->
			</div>

			<div class="mimachongzhi f-l">
				<div class="form-group">
					<input type="text" placeholder="请输入您的邮箱地址"> <span
						class="error-notic">邮箱格式不正确</span>
				</div>
				<div class="form-group">
					<button type="submit" class="tran pr">
						<a href="javascript:;" class="tran">发送重置密码邮件</a>
					</button>
				</div>
				<div class="from-line"></div>
				<div class="form-group">
					<a href="javascript:;" class="move-signup	a-tag tran blue-border">还没有帐号？免费注册<i
						class="iconfont tran">&#xe606;</i></a>
				</div>
				<div class="form-group">
					<a href="javascript:;" class="move-login a-tag tran">已有帐号？登录<i
						class="iconfont tran">&#xe606;</i></a>
				</div>
			</div>

			<div class="mobile-success f-l">
				<p>
					手机号 <span>186****7580</span> 验证成功
				</p>
				<p>
					请完善您的账号信息，您也可以<a href="#">绑定现有账号</a>
				</p>
				<div class="form-group">
					<input type="text" placeholder="邮箱" class="email-mobile"
						onblur="verify.verifyEmail(this)" /> <span class="error-notic">邮箱格式不正确</span>
				</div>
				<div class="form-group">
					<input type="text" placeholder="您的名字">
				</div>
				<div class="form-group">
					<input type="password" placeholder="密码（字母、数字，至少6位）"
						onblur="verify.PasswordLenght(this)" /> <span class="error-notic">密码长度不够</span>
				</div>
				<div class="form-group">
					<button type="submit" class="tran pr">
						<a href="javascript:;" class="tran">注册</a>
					</button>
				</div>
				<p class="view-clause">
					点击注册，即同意我们的 <a href="#">用户隐私条款</a>
				</p>
			</div>

		</div>
	</div>
	<div class="login-footer">
		<h1>jQuary插件库</h1>
		<p>Copyright© 2014-2015 Powered by http://www.jq22.com/</p>
		<p>沪ICP备13043785号-1</p>
	</div>
	<script>
		var _handle = ''; //储存电话是否填写正确
		$(function() {
			$(".signup-form input").on("focus", function() {
				$(this).parent().addClass("border");
			});
			$(".signup-form input").on("blur", function() {
				$(this).parent().removeClass("border");
			})
	
			//注册方式切换
			$(".signup-select").on("click", function() {
				var _text = $(this).text();
				var $_input = $(this).prev();
				$_input.val('');
				if (_text == "手机注册") {
					$(".signup-tel").fadeIn(200);
					$(".signup-email").fadeOut(180);
					$(this).text("邮箱注册");
					$_input.attr("placeholder", "手机号码");
					$_input.attr("onblur", "verify.verifyMobile(this)");
					$(this).parents(".form-group").find(".error-notic").text("手机号码格式不正确")
				}
				if (_text == "邮箱注册") {
					$(".signup-tel").fadeOut(180);
					$(".signup-email").fadeIn(200);
					$(this).text("手机注册");
					$_input.attr("placeholder", "邮箱");
					$_input.attr("onblur", "verify.verifyEmail(this)");
					$(this).parents(".form-group").find(".error-notic").text("邮箱格式不正确")
				}
			});
	
			//步骤切换
			var _boxCon = $(".box-con");
			$(".move-login").on("click", function() {
				$(_boxCon).css({
					'marginLeft' : 0
				})
			});
			$(".move-signup").on("click", function() {
				$(_boxCon).css({
					'marginLeft' : -320
				})
			});
			$(".move-other").on("click", function() {
				$(_boxCon).css({
					'marginLeft' : -640
				})
			});
			$(".move-reset").on("click", function() {
				$(_boxCon).css({
					'marginLeft' : -960
				})
			});
			$("body").on("click", ".move-addinf", function() {
				$(_boxCon).css({
					'marginLeft' : -1280
				})
			});
	
			//获取短信验证码
			var messageVerify = function() {
				$(".get-message").on("click", function() {
					if (_handle) {
						$("#message-inf").fadeIn(100)
						$(this).html('<a href="javascript:;">下一步</a>').addClass("move-addinf");
					}
				});
			}();
		});
	
		//表单验证
		function showNotic(_this) {
			$(_this).parents(".form-group").find(".error-notic").fadeIn(100);
			$(_this).focus();
		}
	
		//错误提示显示
		function hideNotic(_this) {
			$(_this).parents(".form-group").find(".error-notic").fadeOut(100);
		} //错误提示隐藏
	
		var verify = {
			verifyEmail : function(_this) {
				$.post("${pageContext.request.contextPath}/user/checkUserRepeatData",{"userdata":$(_this).val(),"type":"username"},function(data){
					if(data.cnt<1){
						hideNotic(_this);
					}else{
						showNotic(_this);
					}
				});
			}, //验证邮箱
			verifyMobile : function(_this) {
				var validateReg = /^((\+?86)|(\(\+86\)))?1\d{10}$/;
				var _value = $(_this).val();
				if (!validateReg.test(_value)) {
					showNotic(_this);
					_handle = false;
				} else {
					hideNotic(_this);
					_handle = true;
				}
				return _handle
			}, //验证手机号码
			PasswordLenght : function(_this) {
				var _length = $(_this).val().length;
				if (_length < 6) {
					showNotic(_this)
				} else {
					hideNotic(_this)
				}
			}, //验证设置密码长度
			VerifyCount : function(_this) {
				var _count = "123456";
				var _value = $(_this).val();
				console.log(_value)
				if (_value != _count) {
					showNotic(_this)
				} else {
					hideNotic(_this)
				}
			} //验证验证码
		}
	</script>
</body>
</html>