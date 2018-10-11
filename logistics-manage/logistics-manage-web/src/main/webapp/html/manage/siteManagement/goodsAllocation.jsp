<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>货场货位</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/public/public.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/manage/siteManagement/goodsAllocation.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/config/url.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/siteManagement/goodsAllocatiion.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.validate.js"></script>
	<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/public/city-picker.data.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/city-picker.js"></script> --%>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/city-picker.css" />
	<script src="${pageContext.request.contextPath}/public/js/jquery.cityselect.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/public/js/city.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/common.js"></script>
	<!--分页-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js"></script>
	<link href="${pageContext.request.contextPath}/css/page/page.css" rel="stylesheet" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.serializejson.js"></script>
	
	<!--搜索-->
	<script>
		function search(param){
			$.ajax({
		        	type:'post',      
		       		url:'${pageContext.request.contextPath}/siteManager/listFreightYardsByPage.do',  
			        data:{page:1,search:JSON.stringify($('#searchForm').serializeJSON())},
			        cache:false,
			        dataType:'json',
		        	success:function(data){
		        		if(data.status==200){
		        			htmlTable(data.data.rows);
		        			$("#page").paging({
								pageNo:1, 
								totalPage: data.data.totalPage,
								totalSize: data.data.limit,
								callback: function(num) {
									searchByPage(num);
								}
							})	
	        			}
		    		}
				})
			}
	</script>
	
	<script type="text/javascript">
	$(function() {
		$("#city_first").citySelect({
			prov:"新疆", 
			city:"乌鲁木齐",
			dist:"天山区",
			nodata:"none"
		});
		/* $("#city_first1").citySelect({
			prov:"新疆", 
			city:"乌鲁木齐",
			dist:"天山区",
			nodata:"none"
		}); */
		$("#add_img").hide();

		//确认操作后页面刷新
		$("#sure_msg").click(function() {
			window.location.reload();
			$("#promptIdModal").modal("hide");
		}); 
		$("#addBox").click(function() {
			$("#addBoxModals").modal()
		})
		$("#sure_sub_msg").click(function() {
			//window.location.reload();
			$("#promptIdModal2").modal("hide");
		}); 
		
		//下拉框
		$.fd.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/trainStation/getAll.do",
			dataType : 'json',
			success : function(data) {
				$("#selectType").empty();
				$("#searchForm select[name=trainStationId]").html("<option></option>");
				$("#selectType").append("<option></option>");
				$.each(data.data, function(index, its) {
					$("#selectType").append(
							"<option value="+its.id+">" + its.stationName
									+ "</option>");
					/*条件查询*/				
					$("#searchForm select[name=trainStationId]").append("<option value="+its.id+">" + its.stationName
									+ "</option>");				
				});
			}
		});
	});

	//新增集装箱
	function submits(type) {
		
		//获取新增货位
		var ist = new Array();
		
		var trList = null;
		if(type==1){
			//修改
			trList= $("#edit_tBod").children("tr");
		}else{
			//新增
			trList = $("#tBod").children("tr");
		}
		
		
		for (var i = 0; i < trList.length; i++) {
			var tdArr = trList.eq(i).find("td");
			/* var c1 = tdArr.eq(0).find('input').val();//收入类别
			 var tb_code = tdArr.eq(1).find('input').val();//编号
			 var tb_name = tdArr.eq(2).find('input').val();//名称  */
			// var c1 = tdArr.eq(0).html();//收入类别
			var tb_code = tdArr.eq(1).text();//编号
			var tb_name = tdArr.eq(2).text();//名称
			var tb_id = tdArr.eq(3).text();//id
			if(type==1){
				ist.push({
					code : tb_code,
					name : tb_name,
					id   : tb_id
				});
			}else{
				ist.push({
					code : tb_code,
					name : tb_name
				});
			}
		}
		if (trList.length != 0) {
			var jsonArray = JSON.stringify(ist);
			if(type==1){
				//修改
				$("#edit_jsonIds").val(jsonArray.toString());
			}else{
				//新增
				$("#jsonIds").val(jsonArray.toString());
			}
			
		} else {
			$("#edit_jsonIds").val(null);
			$("#jsonIds").val(null);
		}
		var ss =null;
		if(type==1){
			//修改
			ss= $('#addForm2').serialize();
		}else{
			//新增
			ss= $('#addForm').serialize();
		}
		$.ajax({
					type : "POST",
					url : "${pageContext.request.contextPath}/siteManager/freight/addOrUpdate.do",
					data : ss,
					dataType : 'json',
					async : false,
					success : function(data) {
						if (data.status != 1) {
							$("#msg2").html(data.msg);
							$("#promptIdModal2").modal();
						} else {
							$("#addYardModal").modal("hide");
							$("#editYardModal").modal("hide");
							$("#msg").html(data.msg);
							$("#promptIdModal").modal();
						}
					}
				});
	};
    
	//是否独立隐藏上级站点  1:是  0:否
	function stationHidden(param){
		if(param == 1){
			$("#hideStationByIsDuli").html("&emsp;企业站点");
			selectCustomer();
		}else{
			$("#hideStationByIsDuli").html("&emsp;上级站点");
			selectStation();
		}
	}
	
	function selectStation(){
		//下拉框
		$.fd.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/trainStation/getAll.do",
			dataType : 'json',
			async:false, 
			success : function(data) {
				$("#selectType").empty();
				$("#selectType").append("<option></option>");
				$.each(data.data, function(index, its) {
					$("#selectType").append(
							"<option value="+its.id+">" + its.stationName
									+ "</option>");
				});
			}
		});
	}
	
	function selectCustomer(){
		//下拉框
		$.fd.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/siteManager/getAllCustomter.do",
			dataType : 'json',
			async:false,
			success : function(data) {
				$("#selectType").empty();
				$("#selectType").append("<option></option>");
				$.each(data.data, function(index, its) {
					$("#selectType").append(
							"<option value="+its.id+">" + its.companyName
									+ "</option>");
				});
			}
		});
	}
	
	function editSelectStation(){
		//下拉框
		$.fd.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/trainStation/getAll.do",
			dataType : 'json',
			async:false, 
			success : function(data) {
				$("#edit_selectType").empty();
				$.each(data.data, function(index, its) {
					$("#edit_selectType").append(
							"<option value="+its.id+">" + its.stationName
									+ "</option>");
				});
			}
		});
	}
	
	function editSelectCustomer(){
		//下拉框
		$.fd.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/siteManager/getAllCustomter.do",
			dataType : 'json',
			async:false,
			success : function(data) {
				$("#edit_selectType").empty();
				$.each(data.data, function(index, its) {
					$("#edit_selectType").append(
							"<option value="+its.id+">" + its.companyName
									+ "</option>");
				});
			}
		});
	}
	
	function editStationHidden(param){
		if(param == 1){
			$("#editHideStationByIsDuli").html("&emsp;企业站点");
			editSelectCustomer();
		}else{
			$("#editHideStationByIsDuli").html("&emsp;上级站点");
			editSelectStation();
		}
	}
	
	//修改
	function change() {
		$("#addForm2")[0].reset();
		$("#edit_tBod").empty();
		$("#edit_img").attr('src',"");
		var le="";
		if ($("input[type=checkbox]:checked").length != 1) {
			$("#dbsajk").html("请选择一条要修改的数据");
			$("#deletnullModal").modal();
			return;
		} else {
			le = $("input[type=checkbox]:checked").val();
		}
		
		//下拉框
		/* $.fd.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/trainStation/getAll.do",
			dataType : 'json',
			success : function(data) {
				$("#edit_selectType").empty();
				$.each(data.data, function(index, its) {
					$("#edit_selectType").append("<option value="+its.id+">" + its.stationName+ "</option>");
				});
			}
		}); */
		
		
		$("#editYardModal").modal();
		
		$.fd.ajax({
					type : "GET",
					url : "${pageContext.request.contextPath}/siteManager/freight/yard/get.do?id="+le,
					success : function(data) {
						
						$("#edit_id").val(data.data.id);
						$("#edit_name").val(data.data.name);
						//$("#edit_isIsolated").val(data.data.isIsolated);
						$("#edit_linkman").val(data.data.linkman);
						$("#edit_phone").val(data.data.phone);
						$("#city_first1").citySelect({
							prov:data.data.province, 
							city:data.data.city,
							dist:data.data.district,
							nodata:"none"
						});
						$("#edit_address").val(data.data.address);
						$("#edit_remark").val(data.data.remark);
						$("#addForm2 input[name=isIsolated]").attr("checked", false);
						$("#addForm2 input[name=isIsolated][value=" + data.data.isIsolated + "]").attr("checked", true);
						if(data.data.isIsolated == 0){
							$("#editHideStationByIsDuli").html("&emsp;上级站点");
							editSelectStation();
						}else{
							$("#editHideStationByIsDuli").html("&emsp;企业站点");
							editSelectCustomer();
						} 
						//下拉框
						$("#edit_selectType").val(data.data.trainStationId);
						$("#edit_tBod").empty();
						$.each(data.data.clList,function(index, its) {
							$("#edit_tBod").append("<tr class='newTr'><td >"+(index+1)+"</td><td title='双击可编辑'>"+its.code+"</td><td title='双击可编辑'>"+its.name+"</td><td style='display:none;'>"+its.id +"</td>"
							+"<td style='text-align:center;'><button type=button style='padding: 8px; border: 1px solid #dde3ef;' onclick='checkCargoIsUse(this)'>删除<button></td></tr>");
							$("#up_ids").val(index+1);
							
						});
						 if(data.data.freightYardImg == null || data.data.freightYardImg == ""){
							$("#edit_img").hide();
						}else{
							$("#edit_img").attr('src',"/upload/photo/"+data.data.freightYardImg);
						} 
					
					}
				});
		
	
	};
	
	//修改货位时。如果点击删除货位 校验是否被使用 
	function checkCargoIsUse(param){
		var cId = $(param).parent().prev().text();
		if(cId == "" || cId == null || cId == undefined){
			$(param).parent().parent().remove();
			return;
		}else{
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/siteManager/checkCargoLocation.do",
				dataType : 'json',
				data : {
					"id" : cId
				},
				success : function(data) {
					if (data.status != 200) {
						$("#msg2").html(data.msg);
						$("#promptIdModal2").modal();
					} else {
						$.ajax({
							type : "POST",
							url : "${pageContext.request.contextPath}/siteManager/deleteCargoLocationById.do",
							dataType : 'json',
							data : {
								"id" : cId
							},
							success : function(data) {
								$(param).parent().parent().remove();
							}
						});
					}
				}
			});
		}
	}

	//删除
	function dels() {
		$("#deletReasonModal").hide();
		var idList = new Array();
		$.each($('input:checkbox'), function() {
			if (this.checked) {
				idList.push($(this).val());
			}
		});
		$.ajax({
					type : "POST",
					url : "${pageContext.request.contextPath}/siteManager/freight/delete.do",
					dataType : 'json',
					data : {
						"idList" : idList
					},
					success : function(data) {
						if (data.status != 1) {
							$("#msg").html(data.msg);
							$("#promptIdModal").modal();
						} else {
							$("#msg").html(data.msg);
							$("#promptIdModal").modal();
						}
					}
				});
	};
	function placeHtml(param) {
				var array = new Array();
				array = $(param).find("input").val().split("~");
				//array[0].trim().replace(/\d/g,"") 保留"."
				if(array[0].trim().replace(/\d/g, "")) {
					//去除"."
					array[0] = array[0].trim().replace(/[^\d,,]]*/g, "");
					$(param).find("input").val(array[0] + "~" + array[1]);
				}
				if(array[1].trim().replace(/\d/g, "")) {
					array[1] = array[1].trim().replace(/[^\d,,]]*/g, "");
					$(param).find("input").val(array[0] + "~" + array[1]);
				}
			}
	
	
	
	
 	<!--图片上传-->
        function imgUpload(file){
        	$("#add_img").show();
			$("#edit_img").show();
			run(file, function (data) {
				$("#add_up_freightYardImg").val(data);
				$("#edit_up_freightYardImg").val(data);
				$("#add_img").attr('src',data);
				$("#edit_img").attr('src',data);
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
             			$("#pointResult").html("请上传大小小于5M的图片");
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
   		function clickImg(param){
		    $(param).parent('div').children('input').click();    		
		}
   		
   		
   		function edit_address_add(){
		 		$("#plug_in").click();
		}
   		
   		
   		function lookImg(url){
   		 	$("#lookImg").modal();
   		 	$("#look_img").attr('src',"/upload/photo/"+url);
   		 	$('#showMask', window.parent.document).show();
   		}
   		function lookingImg(url){
   		 	$("#lookImg").modal();
   		 	$("#look_img").attr('src',"/upload/photo/"+url.name);
   		 	$('#showMask', window.parent.document).show();
   		}
   		
   		
   </script>
</head>

<body>
<input id="up_ids" value="0" type="hidden">
	<!-- 货场货位 -->
		<div class="form project_form container_top">
			<form class="form-inline maxWidth" id="searchForm">
				<div id="wrap">
					<div class="form-group">
						<label>货场名称：</label>
						<input name='name' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>上级站点：</label>
						<select name='trainStationId'></select>
					</div>
					<div class="form-group">
						<label>货场联系人：</label>
						<input name='linkman' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>联系方式：</label>
						<input name='phone' type="text" maxlength="11"/>
					</div>

				</div>
				<div class="foot">
					<a class="btn btn-success" href="javascript:;"><em class="search"></em>
						<span onclick="search(this)">搜索</span>
					</a>
				</div>
			</form>
		</div>
	<!--货场货位表格-->
	<div class="container_top tableDiv">
		<div class="tabbable">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#panel1" data-toggle="tab">货场货位</a>
				</li>
			</ul>
			<!--货场货位列表-->
			<div class="tab-content">
				<div class="tab-pane active" id="panel1">
					<div class="domain">
						<div class="tableBg">
						<c:forEach items='${permissions }' var='permission' >
									<c:if test="${permission.code=='addYard'}">
								<a href="javascript:void(0)" class="exportBtn add" id="addYard"><span>新建</span></a>
									</c:if>
									<c:if test="${permission.code=='updateYard'}">
								<a href="javascript:void(0)" class="exportBtn revise" id="modify" onclick="change()"><span>修改</span></a>
									</c:if>
									<c:if test="${permission.code=='delYard'}">
							 <a href="javascript:void(0)" class="exportBtn del" id="delBtn" ><span>删除</span></a>
									</c:if>
							</c:forEach>
							
						</div>
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr class="tableTop">
										<th><label class="demo--label"> <input
												class="demo--checkbox" type="checkbox" disabled="disabled" > <span
												class="demo--checkboxInput"></span>
										</label></th>
										<th>货场名称</th>
										<th>货场位置</th>
										<th>货位数量</th>
										<th>上级站点</th>
										<th>货场联系人</th>
										<th>联系方式</th>
										<th>货场平面图</th>
										<th>备注</th>
									</tr>
								</thead>
								<tbody id="goodAllTbody">
									<c:forEach items="${yardList.rows}" var="it">
										<tr>
											<td><label class="demo--label"> <input
													class="demo--checkbox" type="checkbox" value="${it.id}">
													<span class="demo--checkboxInput"></span>
											</label></td>
											<td>${it.name}</td>
											<td>${it.addressCode}-${it.address}</td>
											<td>${it.localCount}</td>
											<td>${it.stationName}</td>
											<td>${it.linkman}</td>
											<td>${it.phone}</td>
											<td class="textCenter"><a href="javascript:void(0)" class="lookImg" onclick="lookImg('${it.freightYardImg}')">查看</a></td>
											<td>${it.remark}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div class="row clearfix">
							<div class="col-md-12 column paging page_div" id="page"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 新建货场 -->
	<div class="modal fade addYardModal" id="addYardModal" tabindex="-1"
		role="dialog" data-backdrop="static">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">新建货场</h4>
					<span data-dismiss="modal" aria-label="Close"></span>
				</div>
				<div class="modal-body">
				<h5>货场货位<i class='requireds'>*</i></h5>
					<form id="addForm">
						<div class="project_info">
							<div class="form-inline">
								<div class="form-group">
									<label>&emsp;货场名称：</label> <input type="text" maxlength="30"
										name="name" />
								</div>
								<div class="form-group">
									&emsp;货场类型： <label class="demo--label"> <input
										class="demo--radio" type="radio" name="isIsolated" value="1"
										 onclick="stationHidden(1)"> <span class="demo--radioInput"></span>企业站点
									</label> <label class="demo--label"> <input class="demo--radio"
										type="radio" name="isIsolated" value="0" checked="checked" onclick="stationHidden(0)"> <span
										class="demo--radioInput"></span>干线站点
									</label>
								</div>
								<div class="form-group" >
									<label id="hideStationByIsDuli">&emsp;上级站点：</label> <select id="selectType"
										name="trainStationId"></select>
								</div>
								<div class="form-group">
									<label>货场联系人：</label> <input type="text" maxlength="30"
										name="linkman" />
								</div>
								<div class="form-group">
									<label>&emsp;联系方式：</label> <input type="text" maxlength="30"
										name="phone" /> <input id="jsonIds" type="hidden" name="json" />
								</div>
							</div>
							<!-- <div class="form-inline">
								<div class="form-group">
									<label>&emsp;企业地址：</label> <input onblur="checkForm(this)"
										type="text" name="addressCode" class="formcontrol"
										data-toggle="city-picker" placeholder="点击从下拉面板中选择省/市/区"
										style='width: 300px'>
								</div>
								<div class="form-group">
									<label>&emsp;详情：</label> <input type="text"
										class="addressInput" maxlength="100" name="address" />
								</div>

							</div> -->
							<div class="form-group" id="city_first">
								<label>&emsp;地址信息：</label>
								<select id="eidt_province" name="province"  class="prov">
								<!--<option id="province" >省</option>-->
								</select>
								&nbsp;
								<select id="edit_city" name="city"  class="city">
								<!--	<option id="city" >市</option>-->
								</select>
								&nbsp;
								<select id="edit_district" name="district"  class="dist">
								<!--<option id="district">县区</option>-->
								</select>
								
							</div>
								<div class="form-group">
									<label>&emsp;详情：</label> <input type="text"
										class="addressInput" maxlength="100" name="address" />
								</div>
							<div class="form-inline margin10">
								<div class="newtable clearfix">
									<label>&emsp;&emsp;&emsp;货位信息：</label>
									<div class="table-responsive ">
										<table class="table "  id="pointTable">
											<thead>
												<tr class="tableTop ">
													<th>序号</th>
													<th>编号</th>
													<th>货位名称</th>
													<th>操作</th>
												</tr>
											</thead>
											<tbody id="tBod">
												<!--<tr>
											 		<td contentEditable="true">1</td>
													<td contentEditable="true">xx</td>
													<td contentEditable="true">jj</td> 
												</tr>-->

											</tbody>
										</table>
										<div class="moreAdd">
											<img src="${pageContext.request.contextPath}/img/more.png" />添加更多
										</div>
									</div>
								</div>

							</div>
							<div class="form-inline margin10">
								<div class="">
									<label>&emsp;&emsp;&emsp;&emsp;&emsp;备注：</label> <input
										type="text" class="remarkInput" maxlength="100" name="remark" />
								</div>
							</div>
							<div class="form-inline margin10">
								<div class="upImg">
									<label>&emsp;&emsp;货场平面图：</label> 
									<input id="add_up_freightYardImg" type="hidden" name="freightYardImg"/>
									<div>
										<input type="file" onchange="imgUpload(this)" style="display:none;" >
										<img  onclick="clickImg(this)" src="${pageContext.request.contextPath}/img/updownBg.png" /> 
										<img id="add_img" width="150px" height="150px"  src="" />
									</div>
								</div>
							</div>
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
									<input type="button" class="btn sureBtn" value="确认" onclick="submits()" />
									<button type="button" class="btn cancleBtn"
										data-dismiss="modal">取消</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


	<!-- 修改 -->
	<div class="modal fade addYardModal" id="editYardModal" tabindex="-1"
		role="dialog" data-backdrop="static">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">修改货场</h4>
					<span data-dismiss="modal" aria-label="Close"></span>
				</div>
				<div class="modal-body">
					<form id="addForm2">
						<div class="project_info">
							<div class="form-inline">
								<div class="form-group">
								<input id="edit_id" type="hidden"  name="id"/>
									<label>&emsp;货场名称：</label> <input id="edit_name" type="text"
										maxlength="30" name="name" readonly="readonly"/>
								</div>
								<div class="form-group">
									&emsp;货场选择： <label class="demo--label"> <input
										id="edit_isIsolated" class="demo--radio" type="radio"
										name="isIsolated" value="1"> <span
										class="demo--radioInput" onclick="editStationHidden(1)"></span>企业站点
									</label> <label class="demo--label"> <input class="demo--radio"
										type="radio" name="isIsolated" value="0" onclick="editStationHidden(0)"> <span
										class="demo--radioInput"></span>干线站点
									</label>
								</div>
								<div class="form-group" >
									<label id="editHideStationByIsDuli">&emsp;上级站点：</label> <select id="edit_selectType"
										name="trainStationId"></select>
								</div>
								<div class="form-group">
									<label>货场联系人：</label> <input id="edit_linkman" type="text"
										maxlength="30" name="linkman" /> 
								</div>
								<div class="form-group">
									<label>&emsp;联系方式：</label> <input id="edit_phone" type="text"
										maxlength="30" name="phone" /> <input id="edit_jsonIds" 
										type="hidden" name="json" />
								</div>
							</div>
						<!-- 	<div class="form-inline">
								<div class="form-group">
									<label>&emsp;企业地址：</label>
										<input onblur="checkForm(this)"
										type="text" name="addressCode" class="formcontrol"
										data-toggle="city-picker" placeholder="点击从下拉面板中选择省/市/区"
										style='width: 300px'>
										
								<input id="edit_address_code" type="button" class="addressInput" 
									maxlength="150" />		
								</div>
								<div class="form-group">
									<label>&emsp;详情：</label> <input id="edit_address" type="text" class="addressInput"
									maxlength="150" name="address" />
								</div> 
								
							</div> -->
								<div class="form-group" id="city_first1">
								<label>&emsp;地址信息：</label>
								<select name="province"  class="prov">
								<!--<option id="province" >省</option>-->
								</select>
								&nbsp;
								<select name="city"  class="city">
								<!--	<option id="city" >市</option>-->
								</select>
								&nbsp;
								<select name="district"  class="dist">
								<!--<option id="district">县区</option>-->
								</select>
							</div>
							<div class="form-group">
									<label>&emsp;详情：</label> <input type="text"
										class="addressInput" maxlength="100" name="address" id="edit_address" />
								</div>
							<div class="form-inline margin10">
								<div class="newtable clearfix">
									<label>&emsp;&emsp;&emsp;货位信息：</label>
									<div class="table-responsive ">
										<table class="table " id='goodInfo'>
											<thead>
												<tr class="tableTop">
													<th>序号</th>
													<th style="display: none;">id</th>
													<th>编号</th>
													<th>货位名称</th>
													<th>操作</th>
												</tr>
											</thead>
											<tbody id="edit_tBod">

											</tbody>
										</table>
										<div class="moreAdd">
											<img src="${pageContext.request.contextPath}/img/more.png" />添加更多
										</div>
									</div>
								</div>

							</div>
							<div class="form-inline margin10">
								<div class="">
									<label>&emsp;&emsp;&emsp;&emsp;&emsp;备注：</label> <input id="edit_remark"
										type="text" class="remarkInput" maxlength="100" name="remark" />
								</div>
							</div>
							<div class="form-inline margin10">
								<div class="upImg">
									<label>&emsp;&emsp;货场平面图：</label> 
									<input id="edit_up_freightYardImg" type="hidden" name="freightYardImg"/>
									<div>
										<input type="file"  onchange="imgUpload(this)"  style="display:none;">
									 	<img onclick="clickImg(this)"  src="${pageContext.request.contextPath}/img/updownBg.png" /> 
										<img id="edit_img" width="150px" height="150px"  src="" />
									</div>
								</div>
							</div>
							<div class="row clearfix ">
								<div class="col-md-12 column modal_btn">
									<input type="button" class="btn sureBtn" value="确认" onclick="submits(1)" />
									<button type="button" class="btn cancleBtn"
										data-dismiss="modal">取消</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!--end 修改  -->


	<div class="modal fade promptModal" tabindex="-1" role="dialog"
		id="promptIdModal">
		<div class="modal-dialog " role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">提示</h4>
					<span data-dismiss="modal" aria-label="Close"></span>
				</div>
				<div class="modal-body">
					<p id="msg"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn sureBtn" id="sure_msg">
						确定</button>
				</div>
			</div>
		</div>
	</div>
	
	
		<div class="modal fade promptModal" tabindex="-1" role="dialog"
		id="promptIdModal2">
		<div class="modal-dialog " role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">提示</h4>
					<span data-dismiss="modal" aria-label="Close"></span>
				</div>
				<div class="modal-body" style="text-align: center;font-size: 24px;color: #04A9ED;">
					<p id="msg2"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn sureBtn" id="sure_sub_msg">
						确定</button>
				</div>
			</div>
		</div>
	</div>
	
			<!-- 未选择删除提醒模态框 -->
		<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="deletnullModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="body-content">
							<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
							<span id="dbsajk">请选择一条要删除的数据</span>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" data-dismiss="modal">确定</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- 删除提醒模态框 -->
		<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="deletRemindModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="body-content">
							<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
							<span>抱歉，不可批量删除</span>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<!-- <button type="button" class="btn sureBtn">确定</button> -->
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!--删除原因模态框-->
		<div class="modal fade deletReasonModal" tabindex="-1" role="dialog" id="deletReasonModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">删除原因</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="deletForm">
						<div class="body-content">
							<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
							<span>你是否选择删除此分支，删除后不可还原</span>
						</div>
						<div class="delete_reason">
							<h4>删除原因</h4>
							<textarea class="form-control" rows="3"></textarea>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="dels()">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
					    </form>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 查看大图 -->
		<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="lookImg" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">查看图片</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body" style="text-align: center;">
						
							<img id="look_img" src="" class="img-responsive" style="width:100%;height: 100%;"/>
					
					</div>
				</div>
			</div>
		</div>	
</body>

<!--分页-->
<script >
	$("#page").paging({
		pageNo:1, 
		totalPage:${yardList.totalPage},
		totalSize:${yardList.limit},
		callback: function(num) {
			searchByPage(num);
		}
	})
</script>

<!--执行分页查询的方法 -->		
<script>
	function searchByPage(num){
		$.ajax({  
        	type:'post',      
       		url:'${pageContext.request.contextPath}/siteManager/listFreightYardsByPage.do',  
	       	data:{page:num,search:JSON.stringify($('#searchForm').serializeJSON())},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
        			htmlTable(data.data.rows);
        		}
    		}
		})
	}
</script>

<script>
	function htmlTable(results){
		var yardList = results;
		$('#goodAllTbody').html("");
		$.each(yardList,function(index,it){
			$('#goodAllTbody').append("<tr>"
					+"<td style='text-align: center;'><label class='demo--label'>"
        			+"<input class='demo--checkbox' type='checkbox'  value='"+it.id+"'>"
                    +"<span class='demo--checkboxInput'></span>"            
                    +"</label></td>"
			+"<td>"+it.name+"</td>"
			+"<td>"+ it.addressCode+"-"+it.address+"</td>"
			+"<td>"+it.localCount+"</td>"
			+"<td>"+it.stationName+"</td>"
			+"<td>"+it.linkman+"</td>"
			+"<td>"+it.phone+"</td>"
			+"<td class='textCenter'><a href='#' class='lookImg' name='"+it.freightYardImg+"' onclick='lookingImg(this)' >查看</a></td>"
			+"<td></td></tr>")
		})	
	}
</script>

</html>