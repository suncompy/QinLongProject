<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>车辆信息</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/driver/css/driver/driverVehicleInfo.css" />
	<script src="${pageContext.request.contextPath}/public/js/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/public/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		function closeModal(){
			$("#promptModal").modal("hide");
			window.location.reload();
		}

		$(function(){
			$("#editDriverInfo").click(function(){
				$("#modifyDriverInfo").modal();
				//$("input").val("");
				$(".errorClass").html("");
			}); 	
		});
		
		$(function(){
			$("#editCarInfo").click(function(){
				$("#modifyVehicle").modal();
				//$("input").val("");
				$(".errorClass").html("");
			}); 	
		});
	</script>
</head>
<body>
	<div class="container-fluit">
		
		<div class="row">
			<div class="driver-info col-md-12 col-sm-12">
		
			<!-- 驾驶员信息 -->
			    <div >
			        <div class="clearfix">
		            	<h4 class="pull-left">驾驶员信息</h4>
		            	<a class="width40 pull-right" id="editDriverInfo" href="#">修改驾驶员信息</a>	
	            	</div>
			        <ul class="detailInfo">
			            <li >
					        <label>姓名：</label>
					        <span id="">${driverAndCarInfomation.driverName}</span>
				        </li>
				        <li >
				        	<label>出生年月：</label>
					        <span id=""><fmt:formatDate
										value="${driverAndCarInfomation.driverBirthday}" pattern="yyyy-MM-dd" />
					        </span>
				        </li>
				        <li >
				        	<label>性别：</label>
					        <span id=""><c:if test="${driverAndCarInfomation.driverSex eq 1}">男</c:if>
					        			<c:if test="${driverAndCarInfomation.driverSex eq 0}">女</c:if>
					        </span>
				        </li>
				        <li >
				        	<ul class="clearfix">
				        		<li class="pull-left">
				        			<label>绑定手机：</label>
					                <span id="">${driverAndCarInfomation.driverPhone}</span>
				        		</li>
				        		<!-- <li class="width40 pull-right">
				        			<a class="" href="">修改手机号</a>
				        		</li> -->
				        		<div class="clear:both"></div>
				        	</ul>
				        </li>
				         <li >
					        <label>银行卡号：</label>
					        <span id="">${driverAndCarInfomation.driverBankCardNumber}</span>
				        </li>
				        <li >
					        <label>开户行：</label>
					        <span id="">${driverAndCarInfomation.driverOpenBank}</span>
				        </li>
				        <li >
					        <label>行号：</label>
					        <span id="">${driverAndCarInfomation.driverBankNum}</span>
				        </li>
				        <li >
				        	<label>住址：</label>
					        <span id="">${driverAndCarInfomation.driverAreaCode}${driverAndCarInfomation.driverAddress}</span>
				        </li>
				        
				        <li >
				        	<label>身份证号码：</label>
					        <span id="">${driverAndCarInfomation.driverIdCardNumber}</span>
				        </li>
				        <li class="photo  ">
					        <label >身份证照片：</label><span id=""><img width="150" height="150" src="${pageContext.request.contextPath}/${driverAndCarInfomation.driverIdCardImgFront}" alt=""></span><span id=""><img width="150" height="150" src="${pageContext.request.contextPath}/${driverAndCarInfomation.driverIdCardImgBack}" alt=""></span>
				            
				        </li>
				        <li class="photo  ">
				        	<label >驾驶证照片：</label><span id=""><img width="150" height="150" src="${pageContext.request.contextPath}/${driverAndCarInfomation.driverLicenceImgFront}" alt=""></span><span id=""><img width="150" height="150" src="${pageContext.request.contextPath}/${driverAndCarInfomation.driverLicenceImgBack}" alt=""></span>
				            
				        </li>

				      
			        </ul>
			    </div>
			</div> 
		</div>
		<div class="row">	   
			<div class="vehicle-info col-md-12 col-sm-12">    
	        <!-- 车辆信息 --> 
	            <div >
	            	<div class="clearfix">
		            	<h4 class="pull-left">车辆信息</h4>
		            	<a class="width40 pull-right" id="editCarInfo" href="#">变更车辆信息</a>	
	            	</div>	        
			        <ul class="detailInfo">
			            <li >
					        <label>车牌号：</label>
					        <span id="">${driverAndCarInfomation.plateNumber}</span>
				        </li>
				       <!--  <li >
				        	<label>出生年月：</label>
					        <span id=""><fmt:formatDate
										value="${driverAndCarInfomation.buyDate}" pattern="yyyy-MM-dd" />
					        </span>
				        </li> -->
				        <li >
				        	<label>品牌：</label>
					        <span id="">${driverAndCarInfomation.brand}</span>
				        </li>
				        <li >
				        	<label>型号：</label>
					        <span id="">${driverAndCarInfomation.model}</span>
				        </li>
				        <li >
				        	<label>车型：</label>
					        <span id="">${driverAndCarInfomation.motorcycleType}</span>
				        </li>
				        
				        <li class="photo  ">
				        	<label>车辆照片：&nbsp;</label>
					        <span ><img width="150" height="150" src="${pageContext.request.contextPath}/${driverAndCarInfomation.carPhoto1}" alt=""></span>
					        <span ><img width="150" height="150" src="${pageContext.request.contextPath}/${driverAndCarInfomation.carPhoto2}" alt=""></span>
				            
				        </li>
				        
				         <li class="photo  ">
				        	<label>行驶证照片：</label>
					        <span ><img width="150" height="150" src="${pageContext.request.contextPath}/${driverAndCarInfomation.travelLicenceImgFront}" alt=""></span>
					        <span ><img width="150" height="150" src="${pageContext.request.contextPath}/${driverAndCarInfomation.travelLicenceImgBack}" alt=""></span>
				            
				        </li>
				        <li >
				        	<label>购置时间：</label>
					        <span id=""><fmt:formatDate
										value="${driverAndCarInfomation.buyDate}" pattern="yyyy-MM-dd" />
					        </span>
				        </li>
				        <li >
				        	<label>保险时间：</label>
					        <span id=""><fmt:formatDate
										value="${driverAndCarInfomation.insuranceBeginDate}" pattern="yyyy-MM-dd" /> - 
										<fmt:formatDate
										value="${driverAndCarInfomation.insuranceEndDate}" pattern="yyyy-MM-dd" />
					         </span>
				        </li>
				       
				        <li class="photo  ">
				        	<label >保险资料：</label>
				        	<span id=""><img width="150" height="150" src="${pageContext.request.contextPath}/${driverAndCarInfomation.insuranceFilePhoto1}" alt=""></span>
				        	<span id=""><img width="150" height="150" src="${pageContext.request.contextPath}/${driverAndCarInfomation.insuranceFilePhoto2}" alt=""></span>
				            
				        </li>
				      
			        </ul>
			    </div>
			</div>


		</div>


		

	</div>
	
	<!--修改驾驶员信息模态框-->
<div class="modal fade modifyDriverInfo" tabindex="-1" role="dialog" id="modifyDriverInfo" data-backdrop="static">
    <div class="modal-dialog" role="document">
    	<div class="modal-content">
        	<div class="modal-header">
           	    <h4 class="modal-title">修改驾驶员信息</h4>
            	<i class="fa fa-times" aria-hidden="true" data-dismiss="modal" aria-label="Close"></i>
        	</div>
       	    <div class="modal-body">
            	<form class="form-horizontal" id="driverForm">
					<div class="form-group">
						<label class="col-sm-3 control-label"><b>*</b>姓名：</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="name"  value="${driverAndCarInfomation.driverName}">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label"><b>*</b>出生年月：</label>
						<div class="col-sm-7 dataTimes">
							<input id="d244" type="text" name="driverBirthday" 
							  value="<fmt:formatDate value='${driverAndCarInfomation.driverBirthday}' pattern='yyyy-MM-dd'/>"
							
							class="Wdate form-control" onfocus="WdatePicker({dateFmt:'yyyy-M-d'})" />
							<i class="fa fa-calendar" aria-hidden="true"></i>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><b>*</b>性别：</label>
						<div class="col-sm-7">
					        <span id=""><c:if test="${driverAndCarInfomation.driverSex eq 1}">
					        	<label class="demo--label">
								<input class="demo--radio" type="radio" value="1" name="sex" checked="checked">
								<span class="demo--radioInput"></span>男
								</label>
								<label class="demo--label">
									<input class="demo--radio" type="radio" name="sex" value="0" >
									<span class="demo--radioInput"></span>女
								</label>
					        </c:if>
					        <c:if test="${driverAndCarInfomation.driverSex eq 0}">
					        	<label class="demo--label">
								<input class="demo--radio" type="radio" value="1"  name="sex" >
									<span class="demo--radioInput"></span>男
								</label>
								<label class="demo--label">
									<input class="demo--radio" type="radio" value="0"  name="sex" checked="checked">
									<span class="demo--radioInput"></span>女
								</label>
					        </c:if>
					        </span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><b>*</b>住址：</label>
						 <div class="col-sm-7" >
							<!-- container -->
							<input type="text" value="${driverAndCarInfomation.driverAreaCode}" name="areaCode" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><b>*</b>街道信息：</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="address" value="${driverAndCarInfomation.driverAddress}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><b>*</b>手机号码：</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="driverPhone" value="${driverAndCarInfomation.driverPhone}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><b>*</b>银行卡号：</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="driverBankCardNumber" value="${driverAndCarInfomation.driverBankCardNumber}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><b>*</b>开户行：</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="driverOpenBank" value="${driverAndCarInfomation.driverOpenBank}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><b>*</b>行号：</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="driverBankNum" value="${driverAndCarInfomation.driverBankNum}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><b>*</b>身份证号码：</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="idCardNum" value="${driverAndCarInfomation.driverIdCardNumber}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><b>*</b>身份证照片：</label>
						<div class="col-sm-4 " id="idImage">
							<img id="idImg" onclick="clickImg(this)" width=150 height=150 border=0 src='${pageContext.request.contextPath}/${driverAndCarInfomation.driverIdCardImgFront}'>
							<input type="file" onchange="imgUpload(this)" style="display:none;">
							<textarea  id="person_idcardImgFrontArea" style="display:none; width: 100%;height: 30em;"></textarea>  
						</div>
						<div class="col-sm-4 " id="idImages">
							<img id="idImgs" onclick="clickImg(this)"  width=150 height=150 border=0 src='${pageContext.request.contextPath}/${driverAndCarInfomation.driverIdCardImgBack}'>
							<input type="file" onchange="imgUpload(this)" style="display:none;" >
							<textarea  id="person_idcardImgBackArea" style="display:none; width: 100%;height: 30em;"></textarea>  
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><b>*</b>驾驶证照片：</label>
						<div class="col-sm-4 " id="licenseFront">
							<img id="licenseimg" onclick="clickImg(this)"  width=150 height=150 border=0 src='${pageContext.request.contextPath}/${driverAndCarInfomation.driverLicenceImgFront}'>
							<input type="file" onchange="imgUpload(this)" style="display:none;" >
							<textarea  id="person_driverLicenceImgFrontArea" style="display:none; width: 100%;height: 30em;"></textarea>  
						</div>
						<div class="col-sm-4 " id="licenseBack">
							<img id="licenseimgs" onclick="clickImg(this)" width=150 height=150 border=0 src='${pageContext.request.contextPath}/${driverAndCarInfomation.driverLicenceImgBack}'>
							<input type="file" onchange="imgUpload(this)" style="display:none;" style="display:none;" >
							<textarea  id="person_driverLicenceImgBackArea" style="display:none; width: 100%;height: 30em;"></textarea>  
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-10 col-sm-offset-1">
							<button type="button" class=" btn btn-block"  id="driInfoUp" >确定</button>
							<textarea name="imgs" id="totalImgs" style="display:none;"></textarea>
						</div>
					</div>
				</form>
        	</div>
    	</div>
    </div>
</div>
	
	<!--变更车辆信息模态框-->
<div class="modal fade modifyVehicle" tabindex="-1" role="dialog" id="modifyVehicle" data-backdrop="static">
    <div class="modal-dialog" role="document">
    	<div class="modal-content">
        	<div class="modal-header">
           	    <h4 class="modal-title">变更车辆信息</h4>
            	<i class="fa fa-times" aria-hidden="true" data-dismiss="modal" aria-label="Close"></i>
        	</div>
       	    <div class="modal-body">
            	<form class="form-horizontal" id="updCarInfo">
					<div class="form-group">
						<label class="col-sm-3 control-label"><b>*</b>车牌号：</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="plateNumber" value="${driverAndCarInfomation.plateNumber}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><b>*</b>品牌：</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="brand" value="${driverAndCarInfomation.brand}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><b>*</b>车型：</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="motorcycleType" value="${driverAndCarInfomation.model}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><b>*</b>型号：</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="model" value="${driverAndCarInfomation.motorcycleType}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><b>*</b>车辆照片：</label>
						<div class="col-sm-4 " id="carImage">
							<img id="carImg" onclick="clickImg(this)" width=150 height=150 border=0 src="${pageContext.request.contextPath}/${driverAndCarInfomation.carPhoto1}">
							<input type="file" onchange="imgUpload(this)" style="display:none;" >
							<textarea  id="person_carPhoto1Area" style="display:none; width: 100%;height: 30em;"></textarea>
						</div>
						<div class="col-sm-4 " id="carImages">
							<img id="carImgs" onclick="clickImg(this)" width=150 height=150 border=0 src="${pageContext.request.contextPath}/${driverAndCarInfomation.carPhoto2}">
							<input type="file" onchange="imgUpload(this)" style="display:none;" >
							<textarea  id="person_carPhoto2Area" style="display:none; width: 100%;height: 30em;"></textarea>  
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><b>*</b>行驶证照片：</label>
						<div class="col-sm-4 " id="carImage">
							<img id="carImg" onclick="clickImg(this)" width=150 height=150 border=0 src="${pageContext.request.contextPath}/${driverAndCarInfomation.travelLicenceImgFront}">
							<input type="file" onchange="imgUpload(this)" style="display:none;" >
							<textarea  id="person_travelLicenceImgFrontArea" style="display:none; width: 100%;height: 30em;"></textarea>
						</div>
						<div class="col-sm-4 " id="carImages">
							<img id="carImgs" onclick="clickImg(this)" width=150 height=150 border=0 src="${pageContext.request.contextPath}/${driverAndCarInfomation.travelLicenceImgBack}">
							<input type="file" onchange="imgUpload(this)" style="display:none;" >
							<textarea  id="person_travelLicenceImgBackArea" style="display:none; width: 100%;height: 30em;"></textarea>  
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><b>*</b>购置时间：</label>
						<div class="col-sm-7 dataTimes">
							<input id="d244" type="text" name="buyDate" value="<fmt:formatDate value='${driverAndCarInfomation.buyDate}' pattern='yyyy-MM-dd' />" class="Wdate form-control" onfocus="WdatePicker({dateFmt:'yyyy-M-d'})" />
							<i class="fa fa-calendar" aria-hidden="true"></i>
						</div>
					</div>
					<div class="form-group insure-time">
						<label class="col-sm-3 control-label"><b>*</b>保险时间：</label>
						<div class="col-sm-9">
							<div >
								<input id="d244" name="insuranceBeginDate" value="<fmt:formatDate value='${driverAndCarInfomation.insuranceBeginDate}' pattern='yyyy-MM-dd' />" type="text" class="Wdate form-control" onfocus="WdatePicker({dateFmt:'yyyy-M-d'})" />
								<i class="fa fa-calendar" aria-hidden="true"></i>
							</div>
							<div>
								<i class="fa fa-minus" aria-hidden="true"></i></div>
							<div>
								<input id="d244" type="text" name="insuranceEndDate" value="<fmt:formatDate value='${driverAndCarInfomation.insuranceEndDate}' pattern='yyyy-MM-dd' />" class="Wdate form-control" onfocus="WdatePicker({dateFmt:'yyyy-M-d'})" />
								<i class="fa fa-calendar" aria-hidden="true"></i>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><b>*</b>保险资料照片：</label>
						<div class="col-sm-4 " id="insuranceImage">
							<img id="insuranceImg" onclick="clickImg(this)" width=150 height=150 border=0 src="${pageContext.request.contextPath}/${driverAndCarInfomation.insuranceFilePhoto1}">
							<input type="file" onchange="imgUpload(this)" style="display:none;">
							<textarea  id="person_insuranceFilePhoto1Area" style="display:none; width: 100%;height: 30em;"></textarea>  
						</div>
						<div class="col-sm-4 " id="insuranceImages">
							<img id="insuranceImgs" onclick="clickImg(this)" width=150 height=150 border=0 src="${pageContext.request.contextPath}/${driverAndCarInfomation.insuranceFilePhoto2}">
							<input type="file" onchange="imgUpload(this)" style="display:none;">
							<textarea  id="person_insuranceFilePhoto2Area" style="display:none; width: 100%;height: 30em;"></textarea>  
						</div>
					</div>
					<textarea name="imgs" id="catTotalImgs" style="display:none;"></textarea>
					<div class="form-group">
						<div class="col-sm-10 col-sm-offset-1">
							<button id="carInfoUpd" type="button" class="btn btn-block" >确定</button>
						</div>
					</div>	
				</form>
        	</div>
    	</div>
    </div>
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
        
        <!--确认提示模态框-->		
	    <div class="modal fade promptModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" id="promptModalNofreash" >
            <div class="modal-dialog " role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <span class="glyphicon glyphicon-remove"  aria-hidden="true"  data-dismiss="modal"></span>
                    </div>
                    <div class="modal-body" id="pointResultNof">
                    </div>
                    <div class="modal-footer">
       					<button type="button" class="btn" id="pointBtnNof">确认</button>
     				</div>
                </div>
            </div>
        </div>	
        
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
                alert("抱歉，你的浏览器不支持 FileReader，不能将图片转换为Base64，请使用现代浏览器操作！");  
            } else {  
                try {  
                    /*图片转Base64 核心代码*/  
                    var file = input_file.files[0];
                    //这里我们判断下类型如果不是图片就返回 去掉就可以上传任意文件  
                    if (!/image\/\w+/.test(file.type)) {  
                        alert("请确保文件为图像类型");  
                        return false;  
                    } 
                    if(file.size>5242880){
                    	 alert("请上传大小小于5M的图片");
                    	 return false;  
                    }
                    var reader = new FileReader();  
                    reader.onload = function () {  
                        get_data(this.result);  
                    } 
                    reader.readAsDataURL(file);  
                } catch (e) {  
                    alert('图片转Base64出错啦！' + e.toString()) ; 
                }  
            }  
        }  
        
        //驾驶员信息修改
        $("#driInfoUp").click(function(){
 		var map = new Map();
 		var key1 = 'idcardImgFront';
		var key2 = 'idcardImgBack';
		var key3 = 'driverLicenceImgFront';
		var key4 = 'driverLicenceImgBack';
		
		map[key1] =$('#person_idcardImgFrontArea').val();
		map[key2] =$('#person_idcardImgBackArea').val();
		map[key3] =$('#person_driverLicenceImgFrontArea').val();
		map[key4] =$('#person_driverLicenceImgBackArea').val();
		
	 	 var jsonData = JSON.stringify(map);
	 	// alert(jsonData);
	 	 $("#totalImgs").val(jsonData);
	 	
	 	 var formParam = $("#driverForm").serialize();
	 	 //alert(formParam);
	 		$.ajax({  
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/person/driverMsgChange.do',  
		        data:formParam,
		        cache:false,
		        dataType:'json',
	        	success:function(data){
	        		if(data.status==200){
	        			//window.location.href="${pageContext.request.contextPath}/login.do"; 
	        			    $("#modifyDriverInfo").modal("hide");
							$("#pointResult").html("");
							$("#promptModal").modal();
							$("#pointResult").html("修改驾驶员信息成功！");
	        		}else{
	        			$("#pointResult").html("");
						$("#promptModalNofreash").modal();
						$("#pointResultNof").html(data.msg);
	        		}
	        	}  
	    	});
 	});
 	
 	
 	
 	
 	
 	$("#pointBtn").click(function(){
			  $("#promptModal").modal("hide");
			  window.location.reload();
    	})
    	
    	$("#pointBtnNof").click(function(){
			  $("#promptModalNofreash").modal("hide");
    	})
    </script>
    	<script>
    		//变更车辆信息
        $("#carInfoUpd").click(function(){
 		var map = new Map();
 		var key1 = 'carPhoto1';
		var key2 = 'carPhoto2';
		var key3 = 'travelLicenceImgFront';
		var key4 = 'travelLicenceImgBack';
		var key5 = 'insuranceFilePhoto1';
		var key6 = 'insuranceFilePhoto2';
		
		map[key1] =$('#person_carPhoto1Area').val();
		map[key2] =$('#person_carPhoto2Area').val();
		map[key3] =$('#person_travelLicenceImgFrontArea').val();
		map[key4] =$('#person_travelLicenceImgBackArea').val();
		map[key5] =$('#person_insuranceFilePhoto1Area').val();
		map[key6] =$('#person_insuranceFilePhoto2Area').val();
		
	 	 var jsonData = JSON.stringify(map);
	 	// alert(jsonData);
	 	 $("#catTotalImgs").val(jsonData);
	 	 var formParam = $("#updCarInfo").serialize();
	 	 console.log(formParam);
	 	// alert(formParam);
	 		$.ajax({  
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/person/updateDriverCarInfo.do',  
		        data:formParam,
		        cache:false,
		        dataType:'json',
	        	success:function(data){
	        		if(data.status==200){
	        			    $("#modifyVehicle").modal("hide");
							$("#pointResult").html("");
							$("#promptModal").modal();
							$("#pointResult").html("变更车辆信息成功！");
	        		}else{
	        			$("#pointResult").html("");
						$("#promptModalNofreash").modal();
						$("#pointResultNof").html(data.msg);
	        		}
	        	}  
	    	});
 	});
    	</script>
    	
    	<script>
    		function clickImg(param){
			    $(param).parent('div').children('input').click();    		
			}
    	
    	</script>
</body>
</html>