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
				<div class="pull-right">已有账号？<a href="${pageContext.request.contextPath}/user/tologin">请登录</a></div>
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
									<label class="col-sm-3 control-label"><b>*</b><span>用户姓名</span>：</label>
									<div class="col-sm-7">
										<input type="text" class="formcontrol" id="driverusername" name="username" onblur="checkUserRepeatData(this,'username')">
									</div>
									<span class="prompt"></span>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b><span>身份证号</span>：</label>
									<div class="col-sm-7">
										<input type="text" class="formcontrol" id="dirveruseridcard" name="userIdcard" onblur="checkUserRepeatData(this,'userIdCard')">
									</div>
									<span class="prompt"></span>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b><span>设置密码</span>：</label>
									<div class="col-sm-7">
										<input  onblur="checkForm(this)" type="password" class="formcontrol" id="dirveruserpass" name="userpasswd">
									</div>
									<span class="prompt"></span>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b><span>确认密码</span>：</label>
									<div class="col-sm-7">
										<input  onblur="checkRePassWord(this)" type="password" class="formcontrol" id="dirveruserrepass" name="userrepasswd">
									</div>
									<span class="prompt"></span>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b><span>手机号码</span>：</label>
									<div class="col-sm-7">
										<input type="text"  class="formcontrol"  id="ownerPhone" name="phone" onblur="checkUserRepeatData(this,'phone')">
									</div>
									<span class="prompt"></span>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><b>*</b><span>验证码</span>：</label>
									<div class="col-sm-7">
										<input type="text" name="ownerCheckCode" id="dirverownerCheckCode"  onblur="checkForm(this)" class="telnum">&nbsp;
										<input type="button" class="telcode" value="获取验证码" onclick="getMsg2('PERSON_CAR_OWNER_MSG',this)" >
									</div>
									<span class="prompt"></span>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-3 col-sm-8">
										<div class="checkbox">
											<label>
							         	   		<input type="checkbox" value="yes" name="userProtocol">阅读并同意<a href="">《用户注册协议》</a><a href="">《隐私政策》 </a> 
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
						<p>电话：<span class="serviceNum">xxxx-xxxxxxxx</span></p>
					</div>
				</div>
			</div>
		</div>

		<div class="footer">
			<p>秦龙百易信息有限公司</p>

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
				$.post("${pageContext.request.contextPath}/user/sendMessage",{"type":type,"telephone":phone},function(data){
					if(data.ssr.code=="OK"){
						settime(val);
					}else{
						alert("获取验证码失败，请联系管理员！错误码："+data.ssr.code);
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
			$(param).parent('div').parent('div').children('span').html("<img src = '${pageContext.request.contextPath}/images/greenright.png' width = '20px' height='20px' />");
		}else{
			//alert(param.id);
			var tip = $(param).parent('div').parent('div').children('label').children('span').text();
			$(param).parent('div').parent('div').children('span').html("<font color = 'red'>"+tip+"不能为空</font>");
		}
	}

    /*校验用户名*/
   	function checkUserRepeatData(param,type){
		var userdata=param.value;
		var myreg=/^[1][3,4,5,7,8,9][0-9]{9}$/;  
		var sfzpattern = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
		if(userdata !=="" && userdata !== undefined && userdata !== null){
			$.post("${pageContext.request.contextPath}/user/checkUserRepeatData",{"userdata":userdata,"type":type},function(data){
				var elsestr = "";
				if(data.cnt<1){
					if(type == 'phone'){
						if(myreg.test(userdata)){
							$(param).parent('div').parent('div').children('span').html("<img src = '${pageContext.request.contextPath}/images/greenright.png' width = '20px' height='20px' />");
							$(".telcode").removeAttr("disabled"); 
						}else{
							$(param).parent('div').parent('div').children('span').html("<font color = 'red'>手机号输入有误</font>");
		        			$(".telcode").attr({"disabled":"disabled"});  
		        			$(param).focus();
						}
					}else if(type == 'userIdCard'){
						if(sfzpattern.test(userdata)){
							$(param).parent('div').parent('div').children('span').html("<img src = '${pageContext.request.contextPath}/images/greenright.png' width = '20px' height='20px' />");
							$(".telcode").removeAttr("disabled"); 
						}else{
							$(param).parent('div').parent('div').children('span').html("<font color = 'red'>身份证号输入有误</font>");
		        			$(".telcode").attr({"disabled":"disabled"});  
		        			$(param).focus();
						}
					}else{
						$(param).parent('div').parent('div').children('span').html("<img src = '${pageContext.request.contextPath}/images/greenright.png' width = '20px' height='20px' />");
					}
				}else{
					if(type == 'phone'){
						if(myreg.test(userdata)){
							elsestr = "手机号已被注册";
						}else{
							elsestr = "手机号输入有误";
						}
					}else if(type == 'username'){
						elsestr = "用户名已被注册";
					}else if(type == 'userIdCard'){
						if(sfzpattern.test(userdata)){
							elsestr = "身份证已被注册";
						}else{
							elsestr = "身份证号输入有误";
						}
					}
					$(param).parent('div').parent('div').children('span').html("<font color = 'red'>"+elsestr+"</font>");
					$(param).focus();
        			$(".telcode").attr({"disabled":"disabled"});  
				}
			})
		}else{
			var tip = $(param).parent('div').parent('div').children('label').children('span').text();
			$(param).parent('div').parent('div').children('span').html("<font color = 'red'>"+tip+"不能为空</font>");
			if(type == 'phone') {
				$(".telcode").attr({"disabled":"disabled"}); 
			}
		}
	}
	
	/*确认密码校验*/
   	function checkRePassWord(param){
		var name=param.value;
		if(name !=="" && name !== undefined && name !== null){
			var pass = $("#dirveruserpass").val();
			if(pass == param.value){
				$(param).parent('div').parent('div').children('span').html("<img src = '${pageContext.request.contextPath}/images/greenright.png' width = '20px' height='20px' />");
			}else{
				$(param).parent('div').parent('div').children('span').html("<font color = 'red'>两次密码不一致</font>");
			}
		}else{
			$(param).parent('div').parent('div').children('span').html("<font color = 'red'>确认密码不能为空</font>");
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
 		if($("#driverusername").val() == null || $("#driverusername").val() == "" || $("#driverusername").val() == "null"){
 			alert("用户姓名不能为空！");
 			$("#driverusername").focus();
 			return;
 		}else if($("#dirveruseridcard").val() == null || $("#dirveruseridcard").val() == "" || $("#dirveruseridcard").val() == "null"){
 			$("#dirveruseridcard").focus();
 			alert("用户身份证号码不能为空！");
 			return;
 		}else if($("#dirveruserpass").val() == null || $("#dirveruserpass").val() == "" || $("#dirveruserpass").val() == "null"){
 			$("#dirveruserpass").focus();
 			alert("设置密码不能为空！");
 			return;
 		}else if($("#dirveruserrepass").val() == null || $("#dirveruserrepass").val() == "" || $("#dirveruserrepass").val() == "null"){
 			$("#dirveruserrepass").focus();
 			alert("确认密码不能为空！");
 			return;
 		}else if($("#ownerPhone").val() == null || $("#ownerPhone").val() == "" || $("#ownerPhone").val() == "null"){
 			$("#ownerPhone").focus();
 			alert("手机号码不能为空！");
 			return;
 		}else if($("#dirverownerCheckCode").val() == null || $("#dirverownerCheckCode").val() == "" || $("#dirverownerCheckCode").val() == "null"){
 			$("#dirverownerCheckCode").focus();
 			alert("验证码不能为空！");
 			return;
 		}else{
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
		        	if(data.rset.state == 0){
		        		alert("验证码已过期，请重新获取！");
		        		return;
		        	}else if(data.rset.state == 2){
		        		alert("验证码输入错误！");
		        		return;
		        	}else{
		        		$("#pointResult").html("");
						$("#promptModal").modal();
						$("#pointResult").html("注册成功!即将跳转到登录页面！");
						window.setTimeout(toLogin,4000); 
		        	}
	        	}  
	    	})
 		}
 	})
 	function toLogin(){
				window.location.href="${pageContext.request.contextPath}/user/tologin";
			}
 	
 	$("#pointBtn").click(function(){
		  $("#promptModal").modal("hide");
	})
 </script>
 
 
    </body>  
</html>