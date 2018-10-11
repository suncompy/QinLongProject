<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>个人资料</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/driver/css/driver/driverData.css" />
	<script src="${pageContext.request.contextPath}/public/js/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	
			function checkPhones(){
				var phone = $("#phone").val();
				var myreg=/^[1][3,4,5,7,8,9][0-9]{9}$/;
				if(myreg.test(phone)){
					$.ajax({
						type : 'POST',
						url : "${pageContext.request.contextPath}/carOrPerson/checkMobilePhone.do",
						data : {"mobilePhone":phone},
						dataType : "json",
						cache : false,
						success : function(data) {
							if(data.status==400){
								$("#pointPhone").html(data.msg);
								$("#basic-addon").attr("onclick","");
							}else{
								$("#basic-addon").attr("onclick","getMsg(this)");
								$("#pointPhone").html("");
							}
						},
					});
				}else{
					$("#pointPhone").html("手机号格式不正确");
				}
			}
			
			var countdown = 60; 
			function getMsg(e){
					var phone = $("#phone").val();
					$("#code").val("");
					$("#pointCode").html("");
					$.ajax({
						type : 'POST',
						url : "${pageContext.request.contextPath}/telcode.do",
						data : {"type":"FORGET_PASSWD_MSG","telephone":phone},
						dataType : "json",
						success : function(data) {
							if(data.status==200){
								settime(e);
							}else{
								$("#pointPhone").html("请输入正确手机号");
							}
						},
					});
		    }
			
			function settime(e) { 
				if (countdown == 0) { 
					$("#basic-addon").attr("onclick","getMsg(this)"); 
					$("#basic-addon").html("获取验证码"); 
					countdown = 60;
					return;
				} else { 
					$("#basic-addon").attr("onclick","");
					$("#basic-addon").html("重新发送(" + countdown + ")"); 
					countdown--; 
				} 
				setTimeout(function() { 
					settime(e);
					},1000);
				} 

			
			function check(){
				var phone = $("#phone").val();
				var code = $("#code").val();
				var newPassword = $("#newPassword").val();
				var reNewPassword = $("#reNewPassword").val();
				if(phone=="" || phone==null){
					$("#pointPhone").html("手机号不能为空");
					return;
				}
				if(code=="" || code==null){
					$("#pointCode").html("验证码不能为空");
					return;				
				}
				if(newPassword=="" || newPassword==null){
					$("#pointPassword").html("新密码不能为空");
					return;
				}
				if(reNewPassword=="" || reNewPassword==null){
					$("#pointrePassword").html("确认密码不能为空");
					return;
				}
				var formparam = $("#reset").serialize();
				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/carOrPerson/updPassword.do",
					data : formparam,
					dataType : "json",
					success : function(data) {
						if(data.status==200){
							$("#modifyPassword").modal("hide");
							$("#pointResult").html("");
							$("#promptModal").modal();
							$("#pointResult").html("密码修改成功！");
						}else if(data.status==401){
							$("#pointCode").html(data.msg);
						}else if(data.status==402){
							$("#pointrePassword").html(data.msg);
						}else if(data.status==400){
							$("#modifyPassword").modal("hide");
							$("#pointResult").html("");
							$("#promptModal").modal();
							$("#pointResult").html("密码修改失败！");
						}else if(data.status==403){
							$("#pointPhone").html(data.msg);
						}else if(data.status==404){
							$("#pointCode").html(data.msg);
						}
					},
				});
			}
			
			function phoneEmptyHtml(){
				var phone = $("#phone").val();
				if(phone!="" || phone!=null){
					$("#pointPhone").html("");
				}
			}
			
			function codeEmptyhtml(){
				var code = $("#code").val();
				if(code!="" || code!=null){
					$("#pointCode").html("");
				}
			}
			
			function passEmptyhtml(){
				var newPassword = $("#newPassword").val();
				if(newPassword!="" || newPassword!=null){
					$("#pointPassword").html("");
				}
			}
			
			function repassEmptyhtml(){
				var reNewPassword = $("#reNewPassword").val();
				if(reNewPassword!="" || reNewPassword!=null){
					$("#pointrePassword").html("");
				}
			}
			
			//修改手机
			function newPhoneEmpty(){
				var newMobilePhone = $("#newMobilePhone").val();
				if(newMobilePhone!="" || newMobilePhone!=null){
					$("#phoneErrorMsg").html("");
				}
			}
			
			function newCodeEmpty(){
				var phoneCode = $("#phoneCode").val();
				if(phoneCode!="" || phoneCode!=null){
					$("#codeErrorMsg").html("");
				}
			}
			
			function ckeckPhoneExist(){
				var newMobilePhone = $("#newMobilePhone").val();
				var phoneReg=/^[1][3,4,5,7,8,9][0-9]{9}$/;
				if(phoneReg.test(newMobilePhone)){
					$.ajax({
						type : 'POST',
						url : "${pageContext.request.contextPath}/driver/checkPhone.do",
						data : {"mobilePhone":newMobilePhone},
						dataType : "json",
						cache : false,
						success : function(data) {
							if(data.status==400){
								$("#phoneErrorMsg").html(data.msg);
								$("#basic-addon-phone").attr("onclick","");
							}else{
								$("#basic-addon-phone").attr("onclick","getMsgPhone(this)");
								$("#phoneErrorMsg").html("");
							}
						},
					});
				}else{
					$("#phoneErrorMsg").html("手机号格式不正确");
				}
			}
			
			function resetNewPhone(){
				var newMobilePhone = $("#newMobilePhone").val();
				var phoneCode = $("#phoneCode").val();
				if(newMobilePhone=="" || newMobilePhone==null){
					$("#phoneErrorMsg").html("手机号不能为空");
					return;
				}
				if(phoneCode=="" || phoneCode==null){
					$("#codeErrorMsg").html("验证码不能为空");
					return;				
				}
				var formparams = $("#resetPhone").serialize();
				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/driver/updatePhone.do",
					data : formparams,
					dataType : "json",
					success : function(data) {
						if(data.status==200){
							$("#modifyTel").modal("hide");
							$("#pointResult").html("");
							$("#promptModal").modal();
							$("#pointResult").html("修改手机号成功！");
						}else if(data.status==402){
							$("#codeErrorMsg").html(data.msg);
						}else if(data.status==403){
							$("#modifyTel").modal("hide");
							$("#pointResult").html("");
							$("#promptModal").modal();
							$("#pointResult").html("修改手机号失败！");
						}
					},
				});
			}
			
			var countdownPhone = 60; 
			function getMsgPhone(e){
				var newMobilePhone = $("#newMobilePhone").val();
				$("#phoneCode").val("");
				$("#codeErrorMsg").html("");
				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/telcode.do",
					data : {"type":"CHANGE_PHONE_MSG","telephone":newMobilePhone},
					dataType : "json",
					success : function(data) {
						if(data.status==200){
							settimePhone(e);
						}else{
							$("#phoneErrorMsg").html("请输入正确手机号");
						}
					},
				});
		    }
			
			function settimePhone(e) { 
				if (countdownPhone == 0) { 
					$("#basic-addon-phone").attr("onclick","getMsgPhone(this)"); 
					$("#basic-addon-phone").html("获取验证码"); 
					countdownPhone = 60;
					return;
				} else { 
					$("#basic-addon-phone").attr("onclick","");
					$("#basic-addon-phone").html("重新发送(" + countdownPhone + ")"); 
					countdownPhone--; 
				} 
				setTimeout(function() { 
					settimePhone(e);
				},1000);
			} 
			
			//修改银行卡号
			function ckeckBankPhoneSame(){
				var bankPhone = $("#bankPhone").val();
				var hideBankPhone = $("#hideBankPhone").val();
				var bankPhoneReg=/^[1][3,4,5,7,8,9][0-9]{9}$/;
				if(bankPhoneReg.test(bankPhone)){
					$.ajax({
						type : 'POST',
						url : "${pageContext.request.contextPath}/driver/checkBankPhone.do",
						data : {"bankPhone":bankPhone,"hideBankPhone":hideBankPhone},
						dataType : "json",
						cache : false,
						success : function(data) {
							if(data.status==403){
								$("#bankPhonePoint").html(data.msg);
								$("#basic-addon-bank").attr("onclick","");
							}else{
								$("#basic-addon-bank").attr("onclick","getMsgBank(this)");
								$("#bankPhonePoint").html("");
							}
						},
					});
				}else{
					$("#bankPhonePoint").html("手机号格式不正确");
				}
			}
			
			function bankNumberEmpty(){
				var newBankNumber = $("#newBankNumber").val();
				if(newBankNumber!="" || newBankNumber!=null){
					$("#newBankNumberPoint").html("");
				}
			}
			
			function bankPhoneEmpty(){
				var bankPhone = $("#bankPhone").val();
				if(bankPhone!="" || bankPhone!=null){
					$("#bankPhonePoint").html("");
				}
			}
			function bankCodeEmpty(){
				var bnakCode = $("#bnakCode").val();
				if(bnakCode!="" || bnakCode!=null){
					$("#bnakCodePoint").html("");
				}
			}
			
			function resetBankNumber(){
				var newBankNumber = $("#newBankNumber").val();
				var bankPhone = $("#bankPhone").val();
				var bnakCode = $("#bnakCode").val();
				if(newBankNumber=="" || newBankNumber==null){
					$("#newBankNumberPoint").html("银行卡号不能为空");
					return;
				}
				if(bankPhone=="" || bankPhone==null){
					$("#bankPhonePoint").html("手机号不能为空");
					return;				
				}
				if(bnakCode=="" || bnakCode==null){
					$("#bnakCodePoint").html("验证码不能为空");
					return;				
				}
				var formparams = $("#resetBank").serialize();
				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/driver/updateBankNumber.do",
					data : formparams,
					dataType : "json",
					success : function(data) {
						if(data.status==200){
							$("#modifyBankCard").modal("hide");
							$("#pointResult").html("");
							$("#promptModal").modal();
							$("#pointResult").html("修改开户行和银行卡成功！");
						}else if(data.status==402){
							$("#bnakCodePoint").html(data.msg);
						}else if(data.status==403){
							$("#bankPhonePoint").html(data.msg);
						}else if(data.status==400){
							$("#modifyBankCard").modal("hide");
							$("#pointResult").html("");
							$("#promptModal").modal();
							$("#pointResult").html("修改开户行和银行卡成功！");
						}
					},
				});
			}
			
			var countdownBank = 60; 
			function getMsgBank(e){
				var bankPhone = $("#bankPhone").val();
				$("#bnakCode").val("");
				$("#bnakCodePoint").html("");
				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/telcode.do",
					data : {"type":"CHANGE_BANK_CARD_MSG","telephone":bankPhone},
					dataType : "json",
					success : function(data) {
						if(data.status==200){
							settimeBank(e);
						}else{
							$("#bankPhonePoint").html("请输入正确手机号");
						}
					},
				});
		    }
			
			function settimeBank(e) { 
				if (countdownBank == 0) { 
					$("#basic-addon-bank").attr("onclick","getMsgBank(this)"); 
					$("#basic-addon-bank").html("获取验证码"); 
					countdownBank = 60;
					return;
				} else { 
					$("#basic-addon-bank").attr("onclick","");
					$("#basic-addon-bank").html("重新发送(" + countdownBank + ")"); 
					countdownBank--; 
				} 
				setTimeout(function() { 
					settimeBank(e);
				},1000);
			} 
			
			function closeModal(){
				$("#promptModal").modal("hide");
				window.location.reload();
			}
			//清除模态框数据
			$(function(){
				$("#editPwd").click(function(){
					$("#modifyPassword").modal();
					$("input").val("");
					$(".errorClass").html("");
				}); 	
			});
			
			$(function(){
				$("#editPhone").click(function(){
					var hostValue=$("#hostValue").text();
					$("#phoneValue").val(hostValue);
					$("#modifyTel").modal();
					$("#newMobilePhone").val("");
					$("#phoneCode").val("");
					$(".errorClass").html("");
				}); 	
			});

			$(function(){
				$("#editBank").click(function(){
					var hostValue=$("#hostValue").text();
					$("#hideBankPhone").val(hostValue);
					$("#modifyBankCard").modal();
					$("#newBankNumber").val("");
					$("#bankPhone").val("");
					$("#bnakCode").val("");
					$(".errorClass").html("");
				}); 	
			})
		</script>
</head>
<body>
	<div class="container-fluit">
		<div class="row">
			<div class="userInfo col-md-12 col-sm-12">
		
			<!-- 账号信息 -->
			    <div >
			        <h4>账号信息</h4>
			        <ul>
			            <li class=" pull-left">
					        <label>用户名：</label>
					        <span id="">${user.username}</span>
				        </li>
				        <li class=" width30 pull-right"><a id="editPwd" href="#">修改密码</a></li>
				        <div style="clear: both;"></div>
			        </ul>
			    </div>
			</div> 
		</div>
		<div class="row">	   
			<div class="directorInfo col-md-12 col-sm-12">    
	        <!-- 车主信息 --> 
	            <div >
	            	<h4>车主信息</h4>		        
			        <ul class="detailInfo">
			            <li >
					        <label>姓名：</label>
					        <span id="">${driverInfomation.name}</span>
				        </li>
				        <li >
				        	<label>出生年月：</label>
					        <span id=""><fmt:formatDate
										value="${driverInfomation.birthday}" pattern="yyyy.MM.dd" /></span>
				        </li>
				        <li >
				        	<label>性别：</label>
					        <span id=""><c:if test="${driverInfomation.sex eq 1}">男</c:if>
					        			<c:if test="${driverInfomation.sex eq 0}">女</c:if>
					        </span>
				        </li>
				        <li >
				        	<ul class="clearfix">
				        		<li class="pull-left">
				        			<label>绑定手机：</label>
					                <span id="hostValue">${driverInfomation.phone}</span>
				        		</li>
				        		<li class="width30 pull-right">
				        			<a  id="editPhone" href="#">修改手机号</a>
				        		</li>
				        		<div class="clear:both"></div>
				        	</ul>
				        </li>
				        <li >
				        	<label>住址：</label>
					        <span id="">${driverInfomation.areaCode}${driverInfomation.address}</span>
				        </li>
				        <li>
		        			<label>开户行：</label>
			                <span id="">${driverInfomation.openBank}</span>
				        </li>
				        <li >
				        	<ul class="clearfix">
				        		<li class="pull-left">
				        			<label>银行卡号：</label>
					                <span id="">${driverInfomation.bankCardNumber}</span>
				        		</li>
				        		<li class="width30 pull-right">
				        			<a id="editBank" href="#">修改开户行或银行卡</a>
				        		</li>
				        		<div class="clear:both"></div>
				        	</ul>
				        </li>
				        
				        <li >
				        	<label>身份证号码：</label>
					        <span id="">${driverInfomation.idCardNumber}</span>
				        </li>
				        <li class="photo  ">
				        	<label >身份证照片：</label><span id=""><img width="150" height="150" src="${pageContext.request.contextPath}/${driverInfomation.idCardImgFront}" alt=""></span><span id="">
				        	<img width="150" height="150" src="${pageContext.request.contextPath}/${driverInfomation.idCardImgBack}" alt=""></span>
				            
				        </li>
				      
			        </ul>
			    </div>
			</div>
		</div>
	</div>
	
	<!--修改密码模态框-->
<div class="modal fade" tabindex="-1" role="dialog" id="modifyPassword" data-backdrop="static">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <i class="fa fa-times" aria-hidden="true" data-dismiss="modal" aria-label="Close"></i>
        <h4 class="modal-title">修改密码</h4>
      </div>
      <div class="modal-body">
        <p>请输入注册时的手机号</p>
        <form id="reset" action="" method="post">
			<div class="form-group">
					<label >手机号：</label>
					<input id="phone" name="mobilePhone" type="text" class="form-control" placeholder="请输入注册时的手机号" maxlength="11" onfocus="phoneEmptyHtml()" onkeyup="checkPhones()" >
					<span id="pointPhone" class='errorClass'></span>
				</div>
				<div class="form-group">
					<label >验证码：</label>
					<div class="input-group">
  						<input id="code" name="code" type="text" class="form-control" aria-describedby="basic-addon" placeholder="请输入收到的验证码" onfocus="codeEmptyhtml()">
  						<span class="input-group-addon" id="basic-addon" onclick="getMsg(this)">点击获取</span>
  						
					</div>
					<span id="pointCode" class='errorClass'></span>
				</div>
				<div class="form-group">
					<label >新密码：</label>
					<input id="newPassword" name="newPassword" type="password" class="form-control" placeholder=" 请输入新密码" onfocus="passEmptyhtml()">
					<span id="pointPassword" class='errorClass'></span>
				</div>
				<div class="form-group">
					<label >确认密码：</label>
					<input id="reNewPassword" name="reNewPassword" type="password" class="form-control" placeholder="请重新输入新密码" onfocus="repassEmptyhtml()">
					<span id="pointrePassword" class='errorClass'></span>
				</div>
			
			<button type="button" class=" btn btn-block" onclick="check()">重置密码</button>
		</form>
      </div>
      
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
	
	<!--修改手机号模态框-->
<div class="modal fade modifyTel" tabindex="-1" role="dialog" id="modifyTel" data-backdrop="static">
    <div class="modal-dialog" role="document">
    	<div class="modal-content">
        	<div class="modal-header">
           	    <h4 class="modal-title">修改手机号</h4>
            	<i class="fa fa-times" aria-hidden="true" data-dismiss="modal" aria-label="Close"></i>
        	</div>
       	    <div class="modal-body">
            	<p>为保证您的信息安全，变更信息前需验证手机号码</p>
            	<form id="resetPhone" action="" method="post">
					<div class="form-group">
						<label >您当前的手机是：</label>
						<input id="phoneValue" type="text" class="form-control" placeholder="请输入完整的当前手机号" value="${driverInfomation.phone}" readonly="readonly">
						<span id="" class='errorClass'></span>
					</div>
					<div class="form-group">
						<label >请输入要绑定的手机：</label>
						<input id="newMobilePhone" name="newMobilePhone" type="text" class="form-control" placeholder="请输入完整的手机号" maxlength="11" onfocus="newPhoneEmpty()" onkeyup="ckeckPhoneExist()">
						<span id="phoneErrorMsg" class='errorClass'></span>
					</div>
					<div class="form-group">
						<label >验证码：</label>
						<div class="input-group">
  							<input id="phoneCode" name="phoneCode" type="text" class="form-control" aria-describedby="basic-addon" placeholder="请输入收到的验证码" onfocus="newCodeEmpty()">
  							<span class="input-group-addon" id="basic-addon-phone" onclick="getMsgPhone(this)">点击获取</span>
						</div>
						<span id="codeErrorMsg" class='errorClass'></span>
					</div>
					<button type="button" class=" btn btn-block" onclick="resetNewPhone()">确认修改</button>
				</form>
        	</div>
    	</div>
    </div>
</div>

<!--修改银行卡模态框-->
<div class="modal fade" tabindex="-1" role="dialog" id="modifyBankCard" data-backdrop="static">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <i class="fa fa-times" aria-hidden="true" data-dismiss="modal" aria-label="Close"></i>
        <h4 class="modal-title">修改银行卡</h4>
      </div>
      <div class="modal-body">
        <p>为保证您的信息安全，变更信息前需验证当前手机</p>
        <form id="resetBank" action="" method="post">
        	<input type="hidden" id="IdHidden" name="IdHidden" value="${driverInfomation.id}"/>
        	<input type="hidden" id="hideBankPhone" name="hideBankPhone" value="${driverInfomation.phone}"/>
			<div class="form-group">
				<label >请填写新的开户行：</label>
				<input id="newOpenBank" name="newOpenBank" type="text" class="form-control" placeholder="请填写新的开户行" >
				<span id="newOpenBankPoint" class='errorClass'></span>
			</div>
			<div class="form-group">
				<label >请输入新的银行卡号：</label>
				<input id="newBankNumber" name="newBankNumber" type="text" class="form-control" placeholder="请输入完整的银行卡号" onfocus="bankNumberEmpty()">
				<span id="newBankNumberPoint" class='errorClass'></span>
			</div>
			
			<div class="form-group">
				<label >请输入注册预留手机号：</label>
				<input id="bankPhone" name="bankPhone" type="text" class="form-control" placeholder=" 请输入完整的手机号" maxlength="11" onfocus="bankPhoneEmpty()" onkeyup="ckeckBankPhoneSame()">
				<span id="bankPhonePoint" class='errorClass'></span>
			</div>
			<div class="form-group">
				<label >验证码：</label>
				<div class="input-group">
					<input id="bnakCode" name="bnakCode" type="text" class="form-control" aria-describedby="basic-addon" placeholder="请输入收到的验证码" onfocus="bankCodeEmpty()">
					<span class="input-group-addon"  id="basic-addon-bank" onclick="getMsgBank(this)">点击获取</span>
				</div>
				<span id="bnakCodePoint" class='errorClass'></span>
			</div>
			
			<button type="button" class=" btn btn-block" onclick="resetBankNumber()">确认修改</button>
		</form>
      </div>
      
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->	
	
<!--确认提示模态框-->		
	    <div class="modal fade promptModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" id="promptModal" data-backdrop="static">
            <div class="modal-dialog " role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"  data-dismiss="modal"></span>
                    </div>
                    <div class="modal-body">
                    	<span id="pointResult"></span>
                        
                    </div>
                    <div class="modal-footer">
       					<button type="button" class="btn " onclick="closeModal()">确认</button>
        				
     				</div>
                    
                </div>
            </div>
        </div>
</body>
</html>