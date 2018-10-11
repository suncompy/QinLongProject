<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
		<meta charset="UTF-8">
		<title>注册</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap-datetimepicker.css"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/public/css/register.css" />
		<script src="${pageContext.request.contextPath}/public/js/jquery-3.2.1.min.js"></script>
		<script src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap-datetimepicker.js"></script>
		<script src="${pageContext.request.contextPath}/public/js/register.js"></script>
		<script src="${pageContext.request.contextPath}/public/My97DatePicker/WdatePicker.js"></script>
		<!--shilvfei加的-->
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/js/city-picker.data.js" ></script>
		<script type="text/javascript"  src="${pageContext.request.contextPath}/public/js/city-picker.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/city-picker.css" />	
	<!--	<script src="${pageContext.request.contextPath}/public/js/valid.js"></script>-->
	</head>
<body>
	<div class="header">
	    <div class="container">
			<div class="register clearfix">
				<div class="pull-left">
				<a href=""><img src="" alt="">LOGO</a>
				<span>欢迎注册</span>
				</div>
				<div class="pull-right">已有账号？<a href="${pageContext.request.contextPath}/user/login">请登录</a></div>
			</div>
		</div>
	</div>
	<div class="container">
	    <div class="row">
	        <div class="col-md-9 col-sm-9 ">
		        <div class="rowLeft">
			        <div class="fleet ">
			            <h3>车队加入</h3>
			            <hr>
			            <h4>账号信息</h4>
			            <form class="form-horizontal" id="carTeamForm" >
							<div class="form-group">
							   <label  class="col-sm-3 control-label"><b>*</b>姓名：</label>
							   <div class="col-sm-7">
							        <!-- <input type="text" class="form-control"> -->
							        <input type="text" class="formcontrol" name="username" onblur="checkUserName(this)">
							   </div>
							    <span class="prompt"></span>
							</div>
							<div class="form-group">
							    <label  class="col-sm-3 control-label"><b>*</b>设置密码：</label>
							    <div class="col-sm-7">
							        <!-- <input type="password" class="form-control" > -->
							        <input type="password" class="formcontrol" name="passwd" onblur="checkForm(this)">
							    </div>
							     <span class="prompt"></span>
							</div>
							<div class="form-group">
							    <label  class="col-sm-3 control-label"><b>*</b>确认密码：</label>
							    <div class="col-sm-7">
							        <!-- <input type="password" class="form-control" > -->
							        <input type="password" class="formcontrol" name="repasswd" onblur="checkForm(this)">
							    </div>
							     <span class="prompt"></span>
							</div>
			            
			            <br>
			            <h4>负责人信息</h4>
							<div class="form-group">
							   <label  class="col-sm-3 control-label"><b>*</b>姓名：</label>
							   <div class="col-sm-7">
							        <!-- <input type="text" class="form-control"> -->
							        <input type="text" class="formcontrol" name="name" onblur="checkForm(this)">
							   </div>
							    <span class="prompt"></span>
							</div>
							
							<div class="form-group ">
									<label class="col-sm-3 control-label"><b>*</b>出生年月：</label>
									<div class="col-sm-7 dataTimes">
										<input id="cdbirth" type="text" class="Wdate formcontrol" onfocus="WdatePicker({dateFmt:'yyyy-M-d'})" name="birthday" onblur="checkForm(this)"/>
										<i class="fa fa-calendar" aria-hidden="true"></i>
									</div>
									<span class="prompt"></span>
								</div>
							<div class="form-group">
							    <label  class="col-sm-3 control-label"><b>*</b>性别：</label>
							    <div class="col-sm-6">
								    <div class="radio col-sm-2">
								        <input type="radio"  name="sex" value="1" checked><span>男</span>
								    </div>
								    <div class="radio col-sm-2">
								        <input type="radio"  name="sex" value="0"><span>女</span>
								    </div>
								</div>
							</div>
							<div class="form-group">
							    <label  class="col-sm-3 control-label"><b>*</b>出生地址：</label>
							    <div class="col-sm-9" style="position: relative;">
									<!-- container -->
									<input onblur="checkForm(this)"  type="text" name="areaCode" class="formcontrol" data-toggle="city-picker" placeholder="点击从下拉面板中选择省/市/区">
							    </div>
							     <span class="prompt"></span>
							</div>
							<div class="form-group">
							    <label  class="col-sm-3 control-label"><b>*</b>街道信息：</label>
							    <div class="col-sm-7">
							        <!-- <input type="tel" class="form-control" > -->
							        <input type="text" class="formcontrol" name="address"  onblur="checkForm(this)">
							    </div>
							     <span class="prompt"></span>
							</div>
							<div class="form-group">
							    <label  class="col-sm-3 control-label"><b>*</b>开户行：</label>
							    <div class="col-sm-7">
							        <!-- <input type="tel" class="form-control" > -->
							        <input type="text" class="formcontrol" name="openBank" id="openBank"  >
							    </div>
							    <span class="prompt"></span>
							</div>
							<div class="form-group">
							    <label  class="col-sm-3 control-label"><b>*</b>银行账号：</label>
							    <div class="col-sm-7">
							        <!-- <input type="tel" class="form-control" > -->
							        <input type="text" class="formcontrol" name="bankCardNumber" id="bankCardNumber"  >
							    </div>
							    <span class="prompt"></span>
							</div>
							<div class="form-group">
							    <label  class="col-sm-3 control-label"><b>*</b>手机号码：</label>
							    <div class="col-sm-7">
							        <!-- <input type="tel" class="form-control" > -->
							        <input type="text" class="formcontrol" name="phone" id="phone" onblur="checkUserPhone(this)" >
							    </div>
							    <span class="prompt"></span>
							</div>
							<div class="form-group">
							    <label  class="col-sm-3 control-label"><b>*</b>验证码：</label>
							    <div class="col-sm-7">
							        <input type="text" class="telnum" name="telcode" id="telcode" onblur="checkForm(this)">&nbsp;
							        <!-- <input type="text" class="form-control" > -->
							    <!-- </div>
							    <div class="col-sm-3"> -->
							        <!-- <input type="button" class="btn btn-default form-control" value="获取验证码" > -->
							        <input type="button" class="telcode" value="获取验证码" onclick="getMsg1('CAR_TEAM_REGISTER_MSG',this)" >
							    </div>
							    <span class="prompt"></span>
							</div>
							<div class="form-group">
							    <label  class="col-sm-3 control-label"><b>*</b>身份证号码：</label>
							    <div class="col-sm-7">
							        <!-- <input type="tel" class="form-control" > -->
							        <input type="text" class="formcontrol" name="idcardNumber" onblur="checkForm(this)">
							    </div>
							     <span class="prompt"></span>
							</div>
							<div class="form-group" >
								<label  class="col-sm-3 control-label"><b>*</b>身份证照片：</label>
								<div class="col-sm-4 "  id="idFront">
									<img  width=150 height=150 border=0 src='${pageContext.request.contextPath}/public/img/frontImg.png'>
									<input type="file" onchange="imgUpload(this)" >
									<textarea onblur="checkForm(this)"   id="carTeam_idcardPhotoFrontArea"  style="display: none; width: 100%;height: 30em;"></textarea>
									<input type="hidden" name="idcardPhotoFront" />
								</div>
								 <span class="prompt"></span>
								<div class="col-sm-4 " id="idBack">
									<img width=150 height=150 border=0 src='${pageContext.request.contextPath}/public/img/backImg.png'>
									<input type="file" onchange="imgUpload(this)">
									<textarea onblur="checkForm(this)"  id="carTeam_idcardPhotoBackArea" style="display: none; width: 100%;height: 30em;"></textarea>
									<input type="hidden" name="idcardPhotoBack"/>
								</div>
								 <span class="prompt"></span>
							</div>
			            <br>
			            <h4>车队信息：</h4>
			            	<div class="form-group">
							    <label  class="col-sm-3 control-label"><b>*</b>车队名称：</label>
							    <div class="col-sm-7">
							        <input type="text" class="formcontrol"  name="carItemName" onblur="checkCarTeamName(this)">
							        <!-- <input type="text" class="form-control" > -->
							    </div>
							    <span class="prompt"></span>
							</div>
							<div class="form-group">
							    <div class="col-sm-offset-3 col-sm-8">
							      <div class="checkbox">
							        <label>
							          <input type="checkbox" value="yes" name="userProtocol">阅读并同意<a href="">《用户注册协议》</a><a href="">《隐私政策》 </a> 
							          <textarea name="imgs" id="carTeam_totalImgs" style="display:none;"></textarea>
							        </label>
							      </div>
							    </div>
							</div>
							<div class="form-group">
							    <div class="col-sm-offset-3 col-sm-6">
							        <button type="button" class="btn btn-block btn-primary" id="btnCarTeamSubmit">提交并注册</button>
							    </div>
							</div>
			            </form>
			        </div>
			       <!-- 个人加入表单 -->
						<div class="personal ">
							
							<h3>个人加入</h3>
							<hr>
							<form id="driverForm" class="form-horizontal" method="post"  >
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b>我是驾驶员：</label>
									<div class="col-sm-6">
										<div class="radio col-sm-2">
											<input type="radio" value="yes" name="driverRadio" checked><span>是</span>
										</div>
										<div class="radio col-sm-2">
											<input type="radio" value="no" name="driverRadio"><span>否</span>
										</div>
									</div>
								</div>
								
							<h4>账号信息</h4>
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b>用户姓名：</label>
									<div class="col-sm-7">
										<input type="text" class="formcontrol" name="username" onblur="checkUserName(this)">
									</div>
									<span class="prompt"></span>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b>身份证号：</label>
									<div class="col-sm-7">
										<input type="text" class="formcontrol" name="username" onblur="checkUserName(this)">
									</div>
									<span class="prompt"></span>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b>设置密码：</label>
									<div class="col-sm-7">
										<input  onblur="checkForm(this)" type="password" class="formcontrol" name="passwd">
									</div>
									<span class="prompt"></span>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b>确认密码：</label>
									<div class="col-sm-7">
										<input  onblur="checkForm(this)" type="password" class="formcontrol" name="repasswd">
									</div>
									<span class="prompt"></span>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b>手机号码：</label>
									<div class="col-sm-7">
										<input type="text"  class="formcontrol"  id="ownerPhone" name="phone" onblur="checkUserPhone(this)">
									</div>
									<span class="prompt"></span>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b>验证码：</label>
									<div class="col-sm-7">
										<input type="text" name="ownerCheckCode"  onblur="checkForm(this)" class="telnum">&nbsp;
										<input type="button" class="telcode" value="获取验证码" onclick="getMsg2('PERSON_CAR_OWNER_MSG',this)" >
									</div>
									<span class="prompt"></span>
								</div>
							<br>
							<h4>车主信息</h4>
								<div class="owner">
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b>姓名：</label>
									<div class="col-sm-7">
										<input type="text"  onblur="checkForm(this)" class="formcontrol" name="name" >
									</div>
									<span class="prompt"></span>
								</div>

								<div class="form-group ">
									<label class="col-sm-3 control-label"><b>*</b>出生年月：</label>
									<div class="col-sm-7 dataTimes">
										<input id="userbirth"   onblur="checkForm(this)" type="text" class="Wdate formcontrol" onfocus="WdatePicker({dateFmt:'yyyy-M-d'})" name="birthday"/>
										<i class="fa fa-calendar" aria-hidden="true"></i>
									</div>
									<span class="prompt"></span>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b>性别：</label>
									<div class="col-sm-6">
										<div class="radio col-sm-2">
											<input type="radio" value="1" name="sex" checked><span>男</span>
										</div>
										<div class="radio col-sm-2">
											<input type="radio" value="0" name="sex"><span>女</span>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b>住址：</label>
									  <div class="col-sm-9" style="position: relative;">
									<!-- container -->
									<input type="text" onblur="checkForm(this)" class="formcontrol" data-toggle="city-picker" placeholder="点击从下拉面板中选择省/市/区" name="areaCode">
									</div>
									<span class="prompt"></span>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b>街道信息：</label>
									<div class="col-sm-7">

										<input type="text"  onblur="checkForm(this)" class="formcontrol"  name="address">
									</div>
									<span class="prompt"></span>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b>手机号码：</label>
									<div class="col-sm-7">
										<input type="text"  class="formcontrol"  id="ownerPhone" name="phone" onblur="checkUserPhone(this)">
									</div>
									<span class="prompt"></span>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b>验证码：</label>
									<div class="col-sm-7">
										<input type="text" name="ownerCheckCode"  onblur="checkForm(this)" class="telnum">&nbsp;
										<input type="button" class="telcode" value="获取验证码" onclick="getMsg2('PERSON_CAR_OWNER_MSG',this)" >
									</div>
									<span class="prompt"></span>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b>开户行：</label>
									<div class="col-sm-7">
										<input type="text"  class="formcontrol" name="openBank">
									</div>
									<span class="prompt"></span>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b>银行卡号：</label>
									<div class="col-sm-7">
										<input type="text" class="formcontrol" name="bankCardNumber">
									</div>
									<span class="prompt"></span>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b>身份证号码：</label>
									<div class="col-sm-7">
										<input type="text"  onblur="checkForm(this)" class="formcontrol" name="idCardNumber">
									</div>
									<span class="prompt"></span>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b>身份证照片：</label>
									<div class="col-sm-4 " id="idImage">
										<img id="idImg" width=150 height=150 border=0 src='${pageContext.request.contextPath}/public/img/frontImg.png'>
										<input type="file" onchange="imgUpload(this)" >
										<textarea  id="person_idcardImgFrontArea" name="person_idcardImgFrontArea" style="display:none; width: 100%;height: 30em;"></textarea>  
										<input type="hidden" id="idCardImgFront" name="idCardImgFront"/>
									</div>
									<span class="prompt"></span>
									<div class="col-sm-4 " id="idImages">
										<img id="idImgs" width=150 height=150 border=0 src='${pageContext.request.contextPath}/public/img/backImg.png'>
										<input type="file" onchange="imgUpload(this)">
										<textarea  id="person_idCardImgBackArea" name="person_idCardImgBackArea" style="display:none; width: 100%;height: 30em;"></textarea>  
										<input type="hidden" name="idCardImgBack"/>
									</div>
									<span class="prompt"></span>
								</div>
								<div class="form-group driver-license">
									<label class="col-sm-3 control-label"><b>*</b>驾驶证类型：</label>
									<div class="col-sm-7">

										<input type="text"  onblur="checkForm(this)" class="formcontrol"  name="driverLicenceType">
									</div>
									<span class="prompt"></span>
								</div>
								<div class="form-group driver-license">
										<label class="col-sm-3 control-label"><b>*</b>驾驶证照片：</label>
										<div class="col-sm-4 " id="licenseFront">
											<img id="licenseimg" width=150 height=150 border=0 src='${pageContext.request.contextPath}/public/img/frontImg.png'>
											<input type="file" onchange="imgUpload(this)">
											<textarea  id="person_driverLicenceImgFrontArea" style="display:none; width: 100%;height: 30em;"></textarea>  
											<input type="hidden" name="driverLicenceImgFront"/>
										</div>
										<span class="prompt"></span>
										<div class="col-sm-4 " id="licenseBack">
											<img id="licenseimgs" width=150 height=150 border=0 src='${pageContext.request.contextPath}/public/img/backImg.png'>
											<input type="file" onchange="imgUpload(this)">
											<textarea  id="person_driverLicenceImgBackArea" style="display:none; width: 100%;height: 30em;"></textarea>  
											<input type="hidden" name="driverLicenceImgBack"/>
										</div>
										<span class="prompt"></span>
								</div>
							</div>
							<br>
							<div class="driverInfo" >
								<h4>驾驶员信息</h4>
									<div class="form-group">
										<label class="col-sm-3 control-label"><b>*</b>姓名：</label>
										<div class="col-sm-7">
											<input type="text"  onblur="checkForm(this)" class="formcontrol" name="driverName">
										</div>
										<span class="prompt"></span>
									</div>

								<div class="form-group ">
									<label class="col-sm-3 control-label"><b>*</b>出生年月：</label>
									<div class="col-sm-7 dataTimes">
										<input id="driverbirth"  onblur="checkForm(this)" type="text" class="Wdate formcontrol" onfocus="WdatePicker({dateFmt:'yyyy-M-d'})" name="driverBirthday"/>
										<i class="fa fa-calendar" aria-hidden="true"></i>
									</div>
									<span class="prompt"></span>
								</div>
									<div class="form-group">
										<label class="col-sm-3 control-label"><b>*</b>性别：</label>
										<div class="col-sm-6">
											<div class="radio col-sm-2">
												<input type="radio" value="1" name="driverSex" checked><span>男</span>
											</div>
											<div class="radio col-sm-2">
												<input type="radio" value="0" name="driverSex"><span>女</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label"><b>*</b>住址：</label>
										 <div class="col-sm-9" style="position: relative;">
											<!-- container -->
										<input type="text"  onblur="checkForm(this)" name="driverAreaCode" class="formcontrol" data-toggle="city-picker" placeholder="点击从下拉面板中选择省/市/区">
									</div>
									<span class="prompt"></span>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label"><b>*</b>街道信息：</label>
										<div class="col-sm-7">
											<input type="text"  onblur="checkForm(this)" name="driverAddress" class="formcontrol">
										</div>
										<span class="prompt"></span>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label"><b>*</b>手机号码：</label>
										<div class="col-sm-7">
											<input type="text"  onblur="checkForm(this)" class="formcontrol" name="driverPhone">
										</div>
										<div class="" id="msg_phone"></div>
									</div>
									<!--<div class="form-group">
										<label class="col-sm-3 control-label"><b>*</b>验证码：</label>
										<div class="col-sm-7">
											<input type="text" class="telnum">&nbsp;
											<input type="button" class="telcode" value="获取验证码" name="driverCheckCode">
										</div>
									</div>-->
									<div class="form-group">
										<label class="col-sm-3 control-label"><b>*</b>身份证号码：</label>
										<div class="col-sm-7">
											<input type="text"  onblur="checkForm(this)" class="formcontrol" name="driverIdCardNumber">
										</div>
										<span class="prompt"></span>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label"><b>*</b>身份证照片：</label>
										<div class="col-sm-4 " id="idImage">
											<img id="idImg" width=150 height=150 border=0 src='${pageContext.request.contextPath}/public/img/frontImg.png'>
											<input type="file" onchange="imgUpload(this)">
											<textarea  id="person_driverIdCardImgFrontArea" style="display:none; width: 100%;height: 30em;"></textarea>  
											<input type="hidden" name="driverIdCardImgFront"/>
										</div>
										<div class="col-sm-4 " id="idImages">
											<img id="idImgs" width=150 height=150 border=0 src='${pageContext.request.contextPath}/public/img/backImg.png'>
											<input type="file" onchange="imgUpload(this)">
											<textarea  id="person_driverIdCardImgBackArea" style="display:none; width: 100%;height: 30em;"></textarea>  
											<input type="hidden" name="driverLicenceImgBack"/>
										</div>
									</div>
									
								<br>
						    </div>
							<h4>车辆信息</h4>
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b>车牌号：</label>
									<div class="col-sm-7">
										<input  onblur="checkForm(this)" type="text" class="formcontrol" name="plateNumber">
									</div>
									<span class="prompt"></span>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b>品牌：</label>
									<div class="col-sm-7">
										<input  onblur="checkForm(this)" type="text" class="formcontrol" name="brand">
									</div>
									<span class="prompt"></span>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b>车型：</label>
									<div class="col-sm-7">
										<input  onblur="checkForm(this)" type="text" class="formcontrol" name="motorcycleType">
									</div>
									<span class="prompt"></span>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b>型号：</label>
									<div class="col-sm-7">
										<input  onblur="checkForm(this)" type="text" class="formcontrol" name="model">
									</div>
									<span class="prompt"></span>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b>行驶证照片：</label>
									<div class="col-sm-4 " id="carImage">
										<img id="carImg" width=150 height=150 border=0 src='${pageContext.request.contextPath}/public/img/photo1.png'>
										<input type="file" onchange="imgUpload(this)">
										<textarea  id="person_travelLicenceImgFrontArea" style="display:none; width: 100%;height: 30em;"></textarea>  
										<input type="hidden" name="travelLicenceImgFront"/>
									</div>
									<span class="prompt"></span>
									<div class="col-sm-4 " id="carImages">
										<img id="carImgs" width=150 height=150 border=0 src='${pageContext.request.contextPath}/public/img/photo2.png'>
										<input type="file" onchange="imgUpload(this)">
										<textarea  id="person_travelLicenceImgBackArea" style="display:none; width: 100%;height: 30em;"></textarea>  
										<input type="hidden" name="travelLicenceImgBack"/>
									</div>
									<span class="prompt"></span>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b>车辆照片：</label>
									<div class="col-sm-4 " id="carImage">
										<img id="carImg" width=150 height=150 border=0 src='${pageContext.request.contextPath}/public/img/photo1.png'>
										<input type="file" onchange="imgUpload(this)" >
										<textarea  id="person_carPhoto1Area" style="display:none; width: 100%;height: 30em;"></textarea>  
										<input type="hidden" name="carPhoto1"/>
									</div>
									<span class="prompt"></span>
									<div class="col-sm-4 " id="carImages">
										<img id="carImgs" width=150 height=150 border=0 src='${pageContext.request.contextPath}/public/img/photo2.png'>
										<input type="file" onchange="imgUpload(this)" >
										<textarea  id="person_carPhoto2Area" style="display:none; width: 100%;height: 30em;"></textarea>  
										<input type="hidden" name="carPhoto2"/>
									</div>
									<span class="prompt"></span>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b>购置时间：</label>
									<div class="col-sm-7 dataTimes">
										<input  onblur="checkForm(this)" id="buydate" name="buyDate" type="text" class="Wdate formcontrol" onfocus="WdatePicker({dateFmt:'yyyy-M-d'})"/>
										<i class="fa fa-calendar" aria-hidden="true"></i>
									</div>
									<span class="prompt"></span>
								</div>
								<div class="form-group insure-time">
									<label class="col-sm-3 control-label"><b>*</b>保险时间：</label>
									<div class="col-sm-9">
										<div>
										<input  onblur="checkForm(this)" id="insuranceBeginDate" name="insuranceBeginDate" type="text" class="Wdate formcontrol" onfocus="WdatePicker({dateFmt:'yyyy-M-d'})"/>
										<i class="fa fa-calendar" aria-hidden="true"></i>
										</div>
									    <div >
										<input onblur="checkForm(this)" id="insuranceEndDate" name="insuranceEndDate" type="text" class="Wdate formcontrol" onfocus="WdatePicker({dateFmt:'yyyy-M-d'})"/>
										<i class="fa fa-calendar" aria-hidden="true"></i>
										</div>
										<span class="prompt"></span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b>保险资料照片：</label>
									<div class="col-sm-4 " id="insuranceImage">
										<img id="insuranceImg" width=150 height=150 border=0 src='${pageContext.request.contextPath}/public/img/photo1.png'>
										<input type="file" onchange="imgUpload(this)">
										<textarea  id="person_insuranceFilePhoto1Area" style="display:none; width: 100%;height: 30em;"></textarea>  
										<input type="hidden" name="insuranceFilePhoto1"/>
									</div>
									<span class="prompt"></span>
									<div class="col-sm-4 " id="insuranceImages">
										<img id="insuranceImgs" width=150 height=150 border=0 src='${pageContext.request.contextPath}/public/img/photo2.png'>
										<input type="file" onchange="imgUpload(this)">
										<textarea  id="person_insuranceFilePhoto2Area" style="display:none; width: 100%;height: 30em;"></textarea>  
										<input type="hidden" name="insuranceFilePhoto2"/>
									</div>
									<span class="prompt"></span>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-3 col-sm-8">
										<div class="checkbox">
											<label>
							         	   <input type="checkbox" value="yes" name="userProtocol">阅读并同意<a href="">《用户注册协议》</a><a href="">《隐私政策》 </a> 
											<textarea name="imgs" id="totalImgs" style="display:none;"></textarea>
											</label>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-3 col-sm-6">
										<button  id="btnDriverSubmit" type="button" class="btn btn-block btn-primary">提交并注册</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>

				<div class="col-md-3 col-sm-3 ">
					<div class="rowRight">
						<div class="personBtn"><img src="${pageContext.request.contextPath}/public/img/addperson.png" alt=""><span class="personJoin">个人加入</span></div>
						<div class="fleetBtn"><img src="${pageContext.request.contextPath}/public/img/addcarteam.png" alt=""><span class="fleetJoin">车队加入</span></div>
						<p>有任何问题请联系客服</p>
						<p>电话：<span class="serviceNum">001-65909999</span></p>
					</div>
				</div>
			</div>
		</div>

		<div class="footer">

			<p>合肥深合软件有限公司</p>

		</div>
<!--确认提示模态框-->		
	    <div class="modal fade promptModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" id="promptModal" >
            <div class="modal-dialog " role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <span class="glyphicon glyphicon-remove"  aria-hidden="true"  data-dismiss="modal"></span>
                    </div>
                    <div class="modal-body" id="pointResult">
                    </div>
                    <div class="modal-footer">
       					<button type="button" class="btn" id="pointBtn">确认</button>
     				</div>
                </div>
            </div>
        </div>	
	</body>
<script>
$(function(){
	$(".telcode").attr({"disabled":"disabled"});  
});
	var countdown = 60; 
	function getMsg1(type,val){
			var phone = $("#phone").val();
			//校验手机号
			var myreg=/^[1][3,4,5,7,8,9][0-9]{9}$/;  
			if(phone !=="" && phone !== undefined && phone !== null && myreg.test(phone) ){
				$.post("${pageContext.request.contextPath}/telcode.do",{"type":type,"telephone":phone},function(data){
					if(data.status==200){
						settime(val);
					}
				})
			}else{
				$("#pointResult").html("");
	     		$("#promptModal").modal();
	     		$("#pointResult").html("手机号不符合规则!");
			}
			
    }
	function getMsg2(type,val){
			var phone = $("#ownerPhone").val();
			//校验手机号
			var myreg=/^[1][3,4,5,6,7,8,9][0-9]{9}$/;  
			if(phone !=="" && phone !== undefined && phone !== null && myreg.test(phone) ){
				$.post("${pageContext.request.contextPath}/telcode.do",{"type":type,"telephone":phone},function(data){
					if(data.status==200){
						settime(val);
					}
				})
			}else{
				$("#pointResult").html("");
	     		$("#promptModal").modal();
	     		$("#pointResult").html("手机号不符合规则!");
			}
			
    }
	
	
	
	
	function settime(val) { 
		if (countdown == 0) { 
		val.removeAttribute("disabled"); 
		val.value="获取验证码"; 
		countdown = 60;
		return;
		} else { 
		val.setAttribute("disabled", true); 
		val.value="重新发送(" + countdown + ")"; 
		countdown--; 
		} 
		setTimeout(function() { 
			settime(val) 
			},1000) 
		} 
	
</script>

<script>
		/*校验表单不能为空*/
		function checkForm(param){
			var name=param.value;
			if(name !=="" && name !== undefined &&name !== null){
				$(param).parent('div').parent('div').children('span').html("");
			}else{
				$(param).parent('div').parent('div').children('span').html("*为必填项");
			}
		}

    	/*校验用户名*/
    	function checkUserName(param){
		var name=param.value;
		if(name !=="" && name !== undefined && name !== null){
			$.post("${pageContext.request.contextPath}/user/check.do",{"param":name,"type":1},function(data){
				if(data.data){
					$(param).parent('div').parent('div').children('span').html("用户名可以使用");
				}else{
					$(param).parent('div').parent('div').children('span').html("用户名已被使用");
				}
			})
		}else{
			$(param).parent('div').parent('div').children('span').html("*为必填项");
		}
	}
	/*校验手机号*/
	function checkUserPhone(param){
		var phone=param.value;
		var myreg=/^[1][3,4,5,7,8,9][0-9]{9}$/;  
		if(phone !=="" && phone !== undefined && phone !== null ){
			if(myreg.test(phone)){
				$.post("${pageContext.request.contextPath}/user/checkUserPhone",{"userphone":phone},function(data){
					if (data.cnt<1) {
	            		//检查手机号是否存在
	        			 $(param).parent('div').parent('div').children('span').html("手机号可以使用");
	        			 $(".telcode").removeAttr("disabled"); 
	        		} else {
	        			$(param).parent('div').parent('div').children('span').html("手机号已被使用");
	        			//验证码框置灰
	        			$(".telcode").attr({"disabled":"disabled"});  
	        		}
				})
			}else{
				$(param).parent('div').parent('div').children('span').html("手机号不符合规则");
				$(".telcode").attr({"disabled":"disabled"}); 
			}
		}else{
			$(param).parent('div').parent('div').children('span').html("*为必填项");
			 $(".telcode").attr({"disabled":"disabled"}); 
		}
	}
    /*校验车队名称*/
    function checkCarTeamName(param){
		var carTeamName=param.value;
		if(carTeamName !=='' && carTeamName !== undefined && carTeamName !== null){
			$.post("${pageContext.request.contextPath}/user/check.do",{"param":carTeamName,"type":3},function(data){
					if (data.data) {
	            		//检查车队名称是否存在
	        			$(param).parent('div').parent('div').children('span').html("车队名称可以使用");
	        		} else {
	        			$(param).parent('div').parent('div').children('span').html("车队名称已被使用");
	        		}
				})
		}else{
			$(param).parent('div').parent('div').children('span').html("*为必填项");
		}
	}
    
</script>

<script>  
 	<!--图片上传-->
        function imgUpload(file){
			run(file, function (data) {
				$(file).parent('div').children('textarea').val(data);
                $(file).parent('div').children('img').attr('src', data);  
              //  $('#carTeam_idcardPhotoFrontArea').val(data);  
            });  
		}
        function run(input_file, get_data) { 
            /*input_file：文件按钮对象*/  
            /*get_data: 转换成功后执行的方法*/  
            if (typeof (FileReader) === 'undefined') {  
                $("#pointResult").html("");
     			$("#promptModal").modal();
     			$("#pointResult").html("抱歉，你的浏览器不支持 FileReader，不能将图片转换为Base64，请使用现代浏览器操作！");
            } else {  
                try {  
                    /*图片转Base64 核心代码*/  
                    var file = input_file.files[0];
                    //这里我们判断下类型如果不是图片就返回 去掉就可以上传任意文件  
                    if (!/image\/\w+/.test(file.type)) {  
                        $("#pointResult").html("");
             			$("#promptModal").modal();
             			$("#pointResult").html("请确保文件为图像类型");
                        return false;  
                    } 
                    if(file.size>5242880){
                    	 $("#pointResult").html("");
             			$("#promptModal").modal();
             			$("#pointResult").html("请上传小于5M的图片");
                    	 return false;  
                    }
                    var reader = new FileReader();  
                    reader.onload = function () {  
                        get_data(this.result);  
                    }  
                    reader.readAsDataURL(file);  
                } catch (e) {  
                	$("#pointResult").html("");
        			$("#promptModal").modal();
        			$("#pointResult").html('图片转Base64出错啦！');
                   // alert( + e.toString())  
                }  
            }  
        }  
    </script>
    
<script>
	/*点击车队注册按钮*/	
	$("#btnCarTeamSubmit").click(function(){
   		  var base64Data = $('#carTeam_idcardPhotoFrontArea').val();
   		  if(base64Data == null || base64Data =="" || base64Data == undefined){
   			$("#pointResult").html("");
			$("#promptModal").modal();
			$("#pointResult").html("请选择上传的图片！");
   		  	return false;
   		  }
   		  base64Data=$('#carTeam_idcardPhotoBackArea').val();
   		  if(base64Data == null || base64Data =="" || base64Data == undefined){
   			$("#pointResult").html("");
			$("#promptModal").modal();
			$("#pointResult").html("请选择上传的图片！");
   		  	return false;
   		  }
   		  var array = new Array(2);
	 	  array[0]= $('#carTeam_idcardPhotoFrontArea').val();
	 	  array[1]= $('#carTeam_idcardPhotoBackArea').val();
	 	  var jsonData = JSON.stringify(array);
	 	  //alert(jsonData);
	 	  $("#carTeam_totalImgs").val(jsonData);
	 	  $("#btnCarTeamSubmit").val("正在注册,请稍后");
		  var formParam = $("#carTeamForm").serialize();//序列化表格内容为字符串 
		 	$.ajax({  
		        	type:'post',      
		       		url:'${pageContext.request.contextPath}/carteam/register.do',  
			        data:formParam,  
			        cache:false,  
			        dataType:'json',  
			        contentType: "application/x-www-form-urlencoded; charset=utf-8",   
		        	success:function(data){
		        		if(data.status==200){
		        			$("#pointResult").html("");
							$("#promptModal").modal();
							$("#pointResult").html("注册成功!即将跳转到登录页面！");
							window.setTimeout(toLogin,4000); 
		        		}else{
		        			$("#pointResult").html("");
							$("#promptModal").modal();
							$("#pointResult").html(data.msg);
		        			$("#btnCarTeamSubmit").val("提交并注册");
		        		}
		        	}  
		    	});
		})
</script>
 <!-- 司机注册  -->
 
 <script>
 	  
 	$("#btnDriverSubmit").click(function(){
 		var array = new Array(10);
 		var is_driver = $('input[name="driverRadio"]:checked').val();
 		if(is_driver=="no"){
 			array = new Array(12);
 			array[10]= $('#person_driverIdCardImgFrontArea').val();
	 	    array[11]= $('#person_driverIdCardImgBackArea').val();
 		}
	 	 array[0]= $('#person_idcardImgFrontArea').val();
	 	 array[1]= $('#person_idCardImgBackArea').val();
	 	 array[2]= $('#person_driverLicenceImgFrontArea').val();
	 	 array[3]= $('#person_driverLicenceImgBackArea').val();
	 	 array[4]= $('#person_travelLicenceImgFrontArea').val();
	 	 array[5]= $('#person_travelLicenceImgBackArea').val();
	 	 array[6]= $('#person_carPhoto1Area').val();
	 	 array[7]= $('#person_carPhoto2Area').val();
	 	 array[8]= $('#person_insuranceFilePhoto1Area').val();
	 	 array[9]= $('#person_insuranceFilePhoto2Area').val();
	 	 var flag = true;
	 	 $.each(array,function(index,base64Data){
	 	 		 if(base64Data == null || base64Data =="" || base64Data == undefined){
		   		  	flag = false;
		   		  	return false;
		   		  }
			     //alert(i+"..."+value);
		});
	 	 if(!flag){
	 		$("#pointResult").html("");
			$("#promptModal").modal();
			$("#pointResult").html("请选择上传的图片！");
	 	 	return false;
	 	 }
	 	 
	 	 var jsonData = JSON.stringify(array);
	 	 //alert(jsonData);
	 	 $("#totalImgs").val(jsonData);
	 	 // alert("正在上传图片,请稍等!");
	 	 var formParam = $("#driverForm").serialize();
	 	 //alert(formParam);
	 	  $("#btnCarTeamSubmit").val("正在注册,请稍后");
	 		$.ajax({  
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/user/register',  
		        data:formParam,
		        cache:false,
		        dataType:'json',
	        	success:function(data){
	        		if(data.status==200){
	        			$("#pointResult").html("");
						$("#promptModal").modal();
						$("#pointResult").html("注册成功!即将跳转到登录页面！");
						window.setTimeout(toLogin,4000); 
	        		}else{
	        			$("#pointResult").html("");
						$("#promptModal").modal();
						$("#pointResult").html(data.msg);
	        			$("#btnCarTeamSubmit").val("提交并注册");
	        		}
	        	}  
	    	})
 	})
 	function toLogin(){
				window.location.href="${pageContext.request.contextPath}/user";
			}
 	
 	$("#pointBtn").click(function(){
		  $("#promptModal").modal("hide");
	})
 </script>
 
 
    </body>  
</html>