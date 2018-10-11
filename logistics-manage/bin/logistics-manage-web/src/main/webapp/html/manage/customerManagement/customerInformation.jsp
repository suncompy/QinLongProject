<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>客户信息</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/manage/customerManagement/customerInformation.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/public.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/config/url.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
		<!--<script type="text/javascript" src="../../../public/bootstrap/My97DatePicker/WdatePicker.js"></script>-->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<!--<script type="text/javascript" src="../../../public/jquery.validate.js"></script>-->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/customerManagement/customerInformation.js"></script>
		<script type="text/javascript"  src="${pageContext.request.contextPath}/public/js/jquery.cityselect.js"></script>	
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/js/city.min.js" ></script>
		<!--分页-->
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js" ></script>
		<link href="${pageContext.request.contextPath}/css/page/page.css" rel="stylesheet"/>
		<script src="${pageContext.request.contextPath}/public/jquery.serializejson.js"></script>	
	</head>
	
	<!--搜索-->
	<script type="text/javascript">
		function search(param){
			$.ajax({
		        	type:'post',      
		       		url:'${pageContext.request.contextPath}/customerManagement/listCustomerByPage.do',  
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
	
	<body>
		<!-- 搜索条件表单 -->
		<div class="form project_form container_top">
			<form class="form-inline maxWidth" id="searchForm">
				<div id="wrap">
					<div class="form-group">
						<label>公司名称：</label>
						<input name='companyName' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>公司简称：</label>
						<input name='shortName' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>所属分支：</label>
						<select name='branchId'>
							<option></option>
							<c:forEach items="${branchGroups }" var="branchGroup" >
								<option value="${branchGroup.id }">${branchGroup.name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label>企业联系人：</label>
						<input name='companyContacts' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>联系方式：</label>
						<input name='stationPhone' type="text" maxlength="11"/>
					</div>
				</div>
				<div class="foot">
					<a class="btn btn-success" href="javascript:;"><em class="search"></em> 
						<span onclick="search(this)">搜索</span>
					</a>
				</div>
			</form>
		</div>
		
		<!-- 客户信息表单 -->
		<div class="container_bottom tableDiv">
			<div class="tabbable" id="tabs-529262">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#panel1" data-toggle="tab">客户信息</a>
					</li>
				</ul>
				<div class="tab-content">
					<!--项目列表表格开始-->
					<div class="tab-pane active" id="panel1">
						<div class="domain">
							<div class="tableBg">
							<c:forEach items='${permissions }' var='permission' >
								<c:if test="${permission.code=='addCustomer'}">
									<a href="javascript:void(0)" class="exportBtn addNew" id="addUser"><span>添加客户</span></a>
								</c:if>
								<c:if test="${permission.code=='updateCustomer'}">
									<a href="javascript:void(0)" class="exportBtn revise" id="modify"><span>修改</span></a>
								</c:if>
								<c:if test="${permission.code=='delCustomer'}">
										<a href="javascript:void(0)" class="exportBtn del" id="delBtn"><span>删除</span></a>
								</c:if>
							</c:forEach>
							
							</div>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox"  disabled="disabled"  >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label> </th>
											<th>公司名称</th>
											<th>简称</th>
											<th>所属分支</th>
											<th>企业地址</th>
											<th>企业联系人</th>
											<th>所属部门</th>
											<th>企业联系方式</th>
											<th>传真</th>
											<th>邮箱</th>
										</tr>
									</thead>
									<tbody id="cusTbody">
									<c:forEach items="${customerList.rows}" var="customer">
										<tr id="${customer.id}">
											<td><label class="demo--label">
											     <input class="demo--checkbox" name="cusCheckBox"  value="${customer.id}" type="checkbox"   >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label></td>
											<td id="">${customer.companyName}</td>
											<td id="">${customer.shortName}</td>
											<td id="">
												<c:forEach items="${customer.branchGroups}" var="branchGroup" varStatus="status">
													${branchGroup.name} &nbsp;
												</c:forEach>
											</td>
											<td id="">${customer.province}${customer.city}${customer.district}${customer.detailAddress}</td>
											<td id="">${customer.companyContacts}</txd>
											<td id="">${customer.department}</td>
											<td id="">${customer.stationPhone}</td>
											<td id="">${customer.stationFax}</td>
											<td id="">${customer.email}</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-md-12 column paging page_div" id="page">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 添加用户模态框 -->
		<div class="modal fade addUserModal" id="addUserModal" tabindex="-1" role="dialog" data-backdrop="static">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel"></h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
                     <form id="addUserForm">
							<h5>基本信息<i class='requireds'>*</i></h5>
							<div class="form-inline">
								<div class="form-group">
									<input type="hidden" name="id" id="addCusId" />
									<label>&emsp;&emsp;公司名称：</label>
									<input type="text" name="companyName" maxlength="30" />
								</div>
								<div class="form-group">
									<label>&emsp;&emsp;&emsp;简称：</label>
									<input type="text" name="shortName" maxlength="30" />
								</div>
								<div class="form-group">
									<label>&emsp;企业联系人：</label>
									<input type="text" name="companyContacts" maxlength="30" />
								</div>
								<div class="form-group">
									<label>&emsp;&emsp;所属部门：</label>
									<input type="text" name="department" maxlength="30" />
								</div>
							</div>
							<div class="form-inline">
								<div class="form-group">
									<label>企业联系方式：</label>
									<input type="text" name="stationPhone" maxlength="30" />
								</div>
								<div class="form-group">
									<label>&emsp;&emsp;&emsp;传真：</label>
									<input type="text" name="stationFax" maxlength="30" />
								</div>
								<div class="form-group">
									<label>&emsp;&emsp;&emsp;&emsp;邮箱：</label>
									<input type="text" name="email" maxlength="30" />
								</div>
							</div>
							<div class="form-inline" >
								<div class="form-group"  name='cityCompanyAddress' >
									<label>&emsp;收发货地址：</label>
									<select class="prov" name="province">
									</select>
									&nbsp;
									<select  class="city" name="city">
									</select>
									&nbsp;
									<select  class="dist" name="district">
									</select>
								</div>
								<input type="text" name="detailAddress" class="addressInput" maxlength="100" />
							</div>
						<input type="hidden" name="relationBeginLocation" />
							<!-- <div class="relationClass">
								<label>&emsp;关联项目部：</label>
								<ul id="relationUl" >
								</ul>
							</div> -->
						
						<div class="form-inline ">
							<div class="form-group">
								<label>关联发运地：</label>
								<ul id="relationAddressUl">
								</ul>
							</div>
						</div>
						
						<hr/>
							<h5>结算相关信息<i class='requireds'>*</i></h5>
							<div class="form-inline">
								<div class="form-group">
									<label>&emsp;&emsp;银行账户：</label>
									<input type="text" name="bankAccount" maxlength="30" />
								</div>
							<!--	<div class="form-group">
									<label>&emsp;&emsp;&emsp;&emsp;户号：</label>
									<input type="text" name="accountName" maxlength="30" />
								</div>-->
								<div class="form-group">
									<label>&emsp;&emsp;&emsp;开户行：</label>
									<input type="text" name="openBank" maxlength="30" />
								</div>
								<div class="form-group">
									<label>&emsp;&emsp;&emsp;&emsp;行号：</label>
									<input type="text" name="openBankNum" maxlength="30" />
								</div>
								<div class="form-group">
									<label>&emsp;纳税识别号：</label>
									<input type="text" name="dutyParagraph" maxlength="30" />
								</div>
								<input type="hidden" name="businessContact" />
								<input type="hidden" name="brachIds" />
							</div>
					
						</form>
						<hr/>
						<div class="whole clearfix">
							<p>业务信息<i class='requireds'>*</i></p>
							<div class="adds" id="forms" ></div>
							<button class="add-more btn btn-success btn-sm pull-right " id="addBusinessMsg" type="button" >
								<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加更多业务信息
							</button>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" id="addCusBtn">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
						</form>    
					</div>
				</div>
			</div>
		</div>
		
		<!--客户详情模态框-->
		<div class="modal fade UserDetailModal" id="userDetailModal" tabindex="-1" role="dialog" data-backdrop="static">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">客户详情</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<h5>基本信息</h5>
						<ul class="clearfix">
							<li>
								<label>&emsp;&emsp;公司名称：</label>
								<span name='companyName'></span>
							</li>
							<li>
								<label>&emsp;&emsp;&emsp;简称：</label>
								<span name="shortName"></span>
							</li>
							<li>
								<label>&emsp;企业联系人：</label>
								<span  name="companyContacts"></span>
							</li>
							<li>
								<label>&emsp;&emsp;&emsp;所属部门：</label>
								<span name="department"></span>
							</li>
							<li>
								<label>企业联系方式：</label>
								<span name="stationPhone"></span>
							</li>
							<li>
								<label>&emsp;&emsp;&emsp;传真：</label>
								<span name="stationFax"></span>
							</li>
							<li>
								<label>&emsp;&emsp;&emsp;&emsp;邮箱：</label>
								<span name="email"></span>
							</li>
						</ul>
						<ul	class="clearfix">
							<li>
								<label>&emsp;&emsp;企业地址：</label>
								<span name='detailAddress' ></span>
							</li>
						</ul>
						<ul class="clearfix" id="relationProject">
						</ul>
						<hr/>
						<h5>结算相关信息</h5>
						<ul class="clearfix">
							<li>
								<label>&emsp;&emsp;银行账户：</label>
								<span name="bankAccount"></span>
							</li>
							<li>
								<label>&emsp;&emsp;&emsp;开户行：</label>
								<span name="openBank"></span>
							</li>
						</ul>
						<ul class="clearfix">	
							<li>
								<label>&emsp;&emsp;&emsp;&emsp;行号：</label>
								<span name="openBankNum"></span>
							</li>
							<li>
								<label>&emsp;纳税识别号：</label>
								<span name="dutyParagraph"></span>
							</li>
						</ul>
						<hr/>
						<h5>业务信息</h5>
						<form id='linkMan'>
						</form>
					</div>
				</div>
			</div>
		</div>	
		
		<!-- 未选择删除提醒模态框 -->
		<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="deletnullModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="msgTitle">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="body-content">
							<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
							<span id='msgContent'></span>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button"class="btn sureBtn" data-dismiss="modal">确定</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 成功提醒模态框 -->
		<div class="modal fade deletRemindModal" tabindex="-1" role="dialog" id="successModal" data-backdrop="static">
			<div class="modal-dialog " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="body-content">
							<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
							<span id="successMsg"></span>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" onclick="successSure()" >确定</button>
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
							<span>你是否选择删除此客户信息，删除后不可还原</span>
						</div>
						<div class="delete_reason">
							<h4>删除原因</h4>
							<textarea class="form-control" rows="3"></textarea>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<input type="hidden" id="delId" />
								<button type="button" class="btn sureBtn" onclick="delCustomer()">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
					    </form>
					</div>
				</div>
			</div>
		</div>
	</body>

	<!-- 新建客户信息后台-->		
	<script type="text/javascript">
		$("#addCusBtn").click(function(){
		//获取id是否为空?
		 var hideCusId = $("#addCusId").val();
		
		// alert(hideCusId);
		 if(hideCusId == null || hideCusId ==""){
			 
			var array = new Array();
			$("#relationAddressUl form").each(function(j, item) {
				var data = JSON.stringify($(this).serializeJSON());
				array[j] = data;
			})
			$('input[name="relationBeginLocation"]').val("[" + array + "]");
			 
			//将项目部id赋值到隐藏域
			var branchArray=new Array();
			$('#relationUl select[name=brachId]').each(function(j,item){
				branchArray[j]=item.value;
			})	
			var branchJsonData = JSON.stringify(branchArray);
			$('input[name="brachIds"]').val(branchJsonData);
			
			//将业务联系人赋值到隐藏域
			var array = new Array();
			$("#forms form").each(function(j,item){
				var data = JSON.stringify($(this).serializeJSON());
				array[j]=data;
			})
			
			$('input[name="businessContact"]').val("["+array+"]");
			var formParam = $("#addUserForm").serialize();//序列化表格内容为字符串 
			$.ajax({  
		        	type:'post',      
		       		url:'${pageContext.request.contextPath}/customerManagement/addCustomer.do',  
			        data:formParam,
			        cache:false,
			        dataType:'json',
		        	success:function(data){
		        		if(data.status==200){
		        			$("#addUserModal").modal("hide");
		        			$("#successMsg").html("新增客户信息成功");
		        			$("#successModal").modal();
		        		}else{
		        			$("#msgContent").html("");
		        			$("#msgContent").html(data.msg);
		        			$("#deletnullModal").modal();
		        		}
		        	}  
		    	})
		 	}else{
		 		
		 		var array = new Array();
				$("#relationAddressUl form").each(function(j, item) {
					var data = JSON.stringify($(this).serializeJSON());
					array[j] = data;
				})
				$('input[name="relationBeginLocation"]').val("[" + array + "]");
		 		
		 		//将网点分支id赋值到隐藏域
				/*var branchArray=new Array();
				 $('#relationUl select[name=brachId]').each(function(j,item){
					branchArray[j]=item.value;
				})	
				var branchJsonData = JSON.stringify(branchArray);
				$('input[name="brachIds"]').val(branchJsonData); */
				
				//将业务联系人赋值到隐藏域
				var array = new Array();
				$("#forms form").each(function(j,item){
					var data = JSON.stringify($(this).serializeJSON());
					array[j]=data;
				})
				
				$('input[name="businessContact"]').val("["+array+"]");
				var formParam = $("#addUserForm").serialize();//序列化表格内容为字符串 
				$.ajax({  
			        	type:'post',      
			       		url:'${pageContext.request.contextPath}/customerManagement/updateCustomer.do',  
				        data:formParam,
				        cache:false,
				        dataType:'json',
			        	success:function(data){
			        		if(data.status==200){
			        			$("#addUserModal").modal("hide");
			        			$("#msgContent").html("");
			        			$("#successMsg").html("修改客户信息成功");
			        			$("#successModal").modal();
			        			//window.location.reload();
			        		}else{
			        			$("#msgContent").html("");
			        			$("#msgContent").html(data.msg);
			        			$("#deletnullModal").modal();
			        		}
			        	}  
			    	})
		 	}
		
		})
	</script>
	
	<!--修改客户信息-->
	<script type="text/javascript">
		$("#modify").click(function(){
			if($("#panel1 input[name='cusCheckBox']:checked").length > 1) {
				$("#msgContent").html("抱歉，不可批量操作");
				$("#deletnullModal").modal();
				return ;
			} else if($("#panel1 input[name='cusCheckBox']:checked").length == 0) {
				$("#msgContent").html("请选择一条您要修改的客户信息");
				$("#deletnullModal").modal();
				return ;
			} else{
				$("#addUserForm")[0].reset();
			}
			var id =$("input[name='cusCheckBox']:checked").val();
			
			$('#myModalLabel').html('修改客户');
			$.ajax({  
		        	type:'post',      
		       		url:'${pageContext.request.contextPath}/customerManagement/getCustomerByIds.do',  
			        data:{id:id},
			        cache:false,
			        dataType:'json',
		        	success:function(data){
		        		if(data.status==200){
						    var customerInfo = data.data;
							$('#addUserModal input[name="id"]').val(customerInfo.id);
							$('#addUserModal input[name="companyName"]').val(customerInfo.companyName);
							$('#addUserModal input[name="shortName"]').val(customerInfo.shortName);
							$('#addUserModal input[name="companyContacts"]').val(customerInfo.companyContacts);
							$('#addUserModal input[name="department"]').val(customerInfo.department);
							$('#addUserModal input[name="stationPhone"]').val(customerInfo.stationPhone);
							$('#addUserModal input[name="stationFax"]').val(customerInfo.stationFax);
							$('#addUserModal input[name="email"]').val(customerInfo.email);
							$('#addUserModal input[name="detailAddress"]').val(customerInfo.detailAddress);
							$('#addUserModal input[name="bankAccount"]').val(customerInfo.bankAccount);
							$('#addUserModal input[name="accountName"]').val(customerInfo.accountName);
							$('#addUserModal input[name="openBank"]').val(customerInfo.openBank);
							$('#addUserModal input[name="openBankNum"]').val(customerInfo.openBankNum);
							$('#addUserModal input[name="dutyParagraph"]').val(customerInfo.dutyParagraph);
							
							 $("#addUserModal div[name='cityCompanyAddress']").citySelect({
							    	prov:customerInfo.province, 
							    	city:customerInfo.city,
									dist:customerInfo.district,
									nodata:"none"
								}); 
							 
							 $("#relationAddressUl").html("");
							 
							 $("#relationAddressUl").append("<li>" +
									"<img src='${pageContext.request.contextPath}/img/add1.png' class='addImg' />" +
									"<img src='${pageContext.request.contextPath}/img/reduce.png' />" +
									"</li>")
							 
							 if(customerInfo.relationBeginLocation == undefined || customerInfo.relationBeginLocation == null || 
									 customerInfo.relationBeginLocation == ""){
								 
							 }else{
								 var relationBeginAddress = JSON.parse(customerInfo.relationBeginLocation);
								 var  i = 10000;
									$.each(relationBeginAddress, function(index,city) {
										$("#relationAddressUl").append("<form><li name='cityAddress"+i+"'>"
										+"<select name='province'  class='prov'></select>	&nbsp;&nbsp;"
										+"<select name='city'  class='city'></select>	&nbsp;&nbsp;"
										+"<select name='district'  class='dist'></select>"
										+"<img src='${pageContext.request.contextPath}/img/add1.png' class='addImg' />"
										+"<img src='${pageContext.request.contextPath}/img/reduce.png' class='reduceImg' />"
										+"</li></form>")
										$("li[name='cityAddress"+i+"']").citySelect({
									    	prov:city.province, 
									    	city:city.city,
											dist:city.district,
											nodata:"none"
										}); 
										i++;
									});
							 }
							 
							 
							/* $("#relationUl").html("<li><select name='brachId'><option></option>"+
									"<c:forEach items='${branchGroups }' var='branchGroup' >"+
									"<option  value='${branchGroup.id }'>${branchGroup.name }"+
								"</option></c:forEach></select>"+
							"<img src='${pageContext.request.contextPath}/img/add1.png' class='addImg'/><img src='${pageContext.request.contextPath}/img/reduce.png' /></li>");
							
							
							var i=0;
							//遍历分支机构
							$.each(customerInfo.branchGroups,function(index,branchGroup){
								if(index==0){
									$("#relationUl").html("<li name='branchLi"+branchGroup.id+"'><select name='brachId'>"+
									"<c:forEach items='${branchGroups }' var='branchGroup' >"+
										"<option  value='${branchGroup.id }'>${branchGroup.name }"+
									"</option></c:forEach></select>"+
								"<img src='${pageContext.request.contextPath}/img/add1.png' class='addImg'/><img src='${pageContext.request.contextPath}/img/reduce.png' /></li>")	
								}else{
									$("#relationUl").append("<li name='branchLi"+branchGroup.id+"'><select name='brachId'>"+
									"<c:forEach items='${branchGroups }' var='branchGroup' >"+
										"<option  value='${branchGroup.id }'>${branchGroup.name }"+
									"</option></c:forEach></select>"+
								"<img src='${pageContext.request.contextPath}/img/add1.png' class='addImg'/><img src='${pageContext.request.contextPath}/img/reduce.png'  class='reduceImg'/></li>")
								}
								$("#addUserModal li[name='branchLi"+branchGroup.id+"'] select[name='brachId']  option[value='"+branchGroup.id+"']").attr("selected","selected");
								i++;
							}) */
							
							
							
							
							
							$(".adds").html("");
							//遍历联系人
							$.each(customerInfo.businessContacts,function(index,businessContact){
								$(".adds").append("<form name='busForm"+businessContact.id+"'><div class='whole clearfix' ><div class='form-inline'><div class='form-group'> <label><input type='hidden' name='id' value='"+businessContact.id+"'  />业务联系人：</label> <input type='text'  name='name' value='"+businessContact.name+"' maxlength='30' /></div>"
								+"<div class='form-group'> <label>&emsp;联系方式：</label> <input type='text' name='phone'  maxlength='30' value='"+businessContact.phone+"' /></div>"
								+"<div class='form-group'> <label>&emsp;关联业务：</label> <select  name='relateProjectId' onchange='relateProject(this)'><c:forEach items='${projects }' var='project' >"
								+"<option></option><option name='projectId' value='${project.id }'>${project.projectCode }</option></c:forEach></select><input type='hidden' name='relateProjectCode' /></div>"
								+"<div class='form-group'> <label>&emsp;&emsp;&emsp;阶段：</label> <select name='stage'><option></option><option value='0'>接取</option><option value='1'>送达</option><option value='2'>汽运</option><option value='3'>火运</option><option value='4'>其他</option></select></div></div>"
								+"<div class='form-inline' class='remarkForm'><label>&emsp;&emsp;&emsp;备注：</label> <input name='remark' type='text' class='remarkInput maxlength='100' value='"+businessContact.remark+"'/>"
								+"<span class='limitSpan''>0/20</span></div>"
								+"</div></form>")
								$("form[name='busForm"+businessContact.id+"'] select[name='relateProjectId']  option[value='"+businessContact.relateProjectId+"']").attr("selected","selected");
								$("form[name='busForm"+businessContact.id+"'] select[name='relateProjectId']").change();
								$("form[name='busForm"+businessContact.id+"'] select[name='stage']  option[value='"+businessContact.stage+"']").attr("selected","selected");
								})
								
							$('#addUserModal input[name="companyName"]').attr("readonly",true);
							$('#addUserModal input[name="shortName"]').attr("readonly",true);	
								
							$("#addUserModal").modal();
		        		}else{
		        			$("#msgContent").html("");
		        			$("#msgContent").html(data.msg);
		        			$("#deletnullModal").modal();
		        		}
		        	}  
		    })
			
		})
	</script>
	
	<!--删除客户信息-->
	<script type="text/javascript">
		$("#delBtn").click(function(){
			var ids = new Array(); 
			var i=0;
			$("input[name='cusCheckBox']:checked").each(function(){
				i++;
				if(i>1){
					$("#msgContent").html("抱歉，不可批量删除");
					$("#deletnullModal").modal();
					return ;
				}
				ids.push($(this).val());
			})
			if(ids==""){
				$("#msgContent").html("请选择一条您要删除的客户信息");
				$("#deletnullModal").modal();
				return ;
			}
			$("#delId").val(ids);
		})
		
		function delCustomer(){
			var id = $("#delId").val();
			$.ajax({  
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/customerManagement/delCustomerById.do',  
		        data:{id:id},
		        cache:false,
		        dataType:'json',
	        	success:function(data){
	        		if(data.status==200){
	        			$("#deletReasonModal").modal("hide");
	        			$("#msgContent").html("");
	        			$("#successMsg").html("删除客户信息成功");
	        			$("#successModal").modal();
	        			//window.location.reload();
	        		}else{
	        			$("#msgContent").html("");
	        			$("#msgContent").html(data.msg);
	        			$("#deletnullModal").modal();
	        		}
	        	}  
	    	})
		}
	</script>
	
	<!--添加更多的业务联系人-->
	<script type="text/javascript">
		$(function(){
		/*<!--关联增加 减少-->*/
			//关联号增加
			$("body").on("click",".addImg",function(){
				$("#relationUl").append("<li><select name='brachId'><option></option>"+
					"<c:forEach items='${branchGroups }' var='branchGroup' >"+
						"<option  value='${branchGroup.id }'>${branchGroup.name }"+
					"</option></c:forEach></select>"+
				"<img src='${pageContext.request.contextPath}/img/add1.png' class='addImg'/><img src='${pageContext.request.contextPath}/img/reduce.png'  class='reduceImg'/></li>")
			});
			//关联号减少
			$("body").on("click",".reduceImg",function(){
				$(this).parent().remove()
			});
			$(".add-more").click(function(){
				$(".adds").append("<form><div class='whole clearfix' ><div class='form-inline'><div class='form-group'> <label><input type='hidden' name='id' />业务联系人：</label> <input type='text'  name='name' maxlength='30' /></div>"
				+"<div class='form-group'> <label>&emsp;联系方式：</label> <input type='text' name='phone'   maxlength='30' /></div>"
				+"<div class='form-group'> <label>&emsp;关联业务：</label> <select  name='relateProjectId' onchange='relateProject(this)'><option></option>"
				+"<c:forEach items='${projects }' var='project' >"
				+"<option value='${project.id }'>${project.projectCode }</option>"
				+"</c:forEach></select><input type='hidden' name='relateProjectCode' /></div>"
				+"<div class='form-group'> <label>&emsp;&emsp;&emsp;阶段：</label> <select name='stage'><option></option><option value='0'>接取</option><option value='1'>送达</option><option value='2'>汽运</option><option value='3'>火运</option><option value='4'>其他</option></select></div></div>"
				+"<div class='form-inline' class='remarkForm'><label>&emsp;&emsp;&emsp;备注：</label> <input type='text' name='remark' class='remarkInput' maxlength='100' />"
				+"<span class='limitSpan''>0/20</span></div>"
				+"</div></form>")
				$("select[name=relateProjectId]").change();
				})
		})
		
		function relateProject(param){
			var relateProjectCode = $(param).find("option:checked").text();
			$(param).next('input[name=relateProjectCode]').val(relateProjectCode);
		}
	</script>
	
	<!--添加客户弹出模态框-->
	<script type="text/javascript">
		$("#addUser").click(function(){
			$("#addCusId").val("");
			$("#addUserForm")[0].reset();
			//$("#relationUl").html("");
			
			//绑定城市
			 $("div[name=cityCompanyAddress]").citySelect({
					prov: "新疆",
					city: "乌鲁木齐",
					dist: "新市区",
					nodata: "none"
					});
			$('#addUserModal input[name="companyName"]').attr("readonly",false);
			$('#addUserModal input[name="shortName"]').attr("readonly",false);
			
			/* $("#relationUl").append("<li><select name='brachId'>"+
			"<option></option><c:forEach items='${branchGroups }' var='branchGroup' >"+
				"<option  value='${branchGroup.id }'>${branchGroup.name }"+
			"</option></c:forEach></select>"+
		"<img src='${pageContext.request.contextPath}/img/add1.png' class='addImg'/><img src='${pageContext.request.contextPath}/img/reduce.png' /></li>")	 */
			
			$("#relationAddressUl").html("");
			$("#relationAddressUl").append("<li>" +
				"<img src='${pageContext.request.contextPath}/img/add1.png' class='addImg' />" +
				//"<img src='${pageContext.request.contextPath}/img/reduce.png' />" +
				"</li>")
		
			$(".adds").html("");
		    //$(".add-more").click();
		    $('#myModalLabel').html('添加客户');
			$("#addUserModal").modal();
		});
		
		function successSure(){
			$("#successModal").modal();
			window.location.reload();
		}
	</script>
	
	<!--客户详情  -->
	<script type="text/javascript">
		//详情
		$("body").on("click", "#panel1 tbody tr td:not(:nth-child(1))", function() {
			$('#showMask', window.parent.document).show();
			var id = $(this).parent().attr("id");
			$.ajax({  
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/customerManagement/getCustomerByIds.do',  
		        data:{id:id},
		        cache:false,
		        dataType:'json',
	        	success:function(data){
	        		if(data.status==200){
					    var customerInfo = data.data;
						$('#userDetailModal span[name="id"]').html(customerInfo.id);
						$('#userDetailModal span[name="companyName"]').html(customerInfo.companyName);
						$('#userDetailModal span[name="shortName"]').html(customerInfo.shortName);
						$('#userDetailModal span[name="companyContacts"]').html(customerInfo.companyContacts);
						$('#userDetailModal span[name="department"]').html(customerInfo.department);
						$('#userDetailModal span[name="stationPhone"]').html(customerInfo.stationPhone);
						$('#userDetailModal span[name="stationFax"]').html(customerInfo.stationFax);
						$('#userDetailModal span[name="email"]').html(customerInfo.email);
						$('#userDetailModal span[name="detailAddress"]').html(customerInfo.province+customerInfo.city+customerInfo.district+customerInfo.detailAddress);
						$('#userDetailModal span[name="bankAccount"]').html(customerInfo.bankAccount);
						$('#userDetailModal span[name="accountName"]').html(customerInfo.accountName);
						$('#userDetailModal span[name="openBank"]').html(customerInfo.openBank);
						$('#userDetailModal span[name="openBankNum"]').html(customerInfo.openBankNum);
						$('#userDetailModal span[name="dutyParagraph"]').html(customerInfo.dutyParagraph);
						
						$("#relationProject").html("");
						//遍历分支机构
						$.each(customerInfo.branchGroups,function(index,branchGroup){
							$("#relationProject").append("<li><label>&emsp;关联项目部：</label>"
							+"<span>"+branchGroup.name+"</span></li>");
						})
						
						$('#linkMan').html("");
						
						if(customerInfo.businessContacts != undefined && customerInfo.businessContacts !=null && customerInfo.businessContacts!=""){
							//遍历联系人
							$.each(customerInfo.businessContacts,function(index,businessContact){
								var stage=""
								if(businessContact.stage==0){
									stage="接取";
								}else if(businessContact.stage==1){
									stage="送达"
								}else if(businessContact.stage==2){
									stage="汽运"
								}else if(businessContact.stage==3){
									stage="火运"
								}else if(businessContact.stage==4){
									stage="其他"
								}
								$('#linkMan').append("<div class='whole clearfix' >"
									+"<ul class='clearfix'>"
									+"<li><label>业务联系人：</label>"
									+"<span>"+businessContact.name+"</span>"
									+"</li><li><label>&emsp;联系方式：</label>"
									+"<span>"+businessContact.phone+"</span></li>"
									+"<li><label>&emsp;关联业务：</label>"
									+"<span>"+businessContact.relateProjectCode+"</span></li>"
									+"<li><label>&emsp;&emsp;阶段：</label>"
									+"<span>"+stage+"</span></li></ul>"
									+"<ul class='clearfix'><label>&emsp;&emsp;&emsp;备注：</label>"
									+"<span>"+businessContact.remark+"</span></ul></div>");						
		        				})
							}
						$('#userDetailModal').modal();
						$(this).parent().toggleClass("bgclass").siblings("#panel1 tbody tr").removeClass("bgclass");
					}else{
	        			$("#msgContent").html("");
	        			$("#msgContent").html(data.msg);
	        			$("#deletnullModal").modal();
	        		}
	        	}
	    })
	})
	</script>
	
	<!--分页-->
	<script type="text/javascript">
		$("#page").paging({
			pageNo:1, 
			totalPage:${customerList.totalPage},
			totalSize:${customerList.limit},
			callback: function(num) {
				searchByPage(num);
			}
		})
	</script>
	
	<!--执行分页查询的方法 -->		
	<script type="text/javascript">
		function searchByPage(num){
			$.ajax({  
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/customerManagement/listCustomerByPage.do',  
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
	
	<!--将后台返回结果写入表格-->
	<script type="text/javascript">
		function htmlTable(results){
			var customerList = results;
			$('#cusTbody').html("");
			$.each(customerList,function(index,customer){
				$('#cusTbody').append("<tr id='"+customer.id+"'>"
					+"<td style='text-align: center;'><label class='demo--label'>"
					 +" <input class='demo--checkbox' name='cusCheckBox' value='"+customer.id+"' type='checkbox'   >"
	                  +"<span class='demo--checkboxInput'></span>"
	                 +"</label></td>"
					 +"<td id=''>"+customer.companyName+"</td>"
				 +"<td id=''>"+customer.shortName+"</td>"
				 /*分支机构*/
				 +"<td id=''>"+customer.brachIds+"</td>"
				 +"<td id=''>"+customer.province+customer.city+customer.district+customer.detailAddress+"</td>"
				 +"<td id=''>"+customer.companyContacts+"</txd>"
				 +"<td id=''>"+customer.department+"</td>"
				 +"<td id=''>"+customer.stationPhone+"</td>"
				 +"<td id=''>"+customer.stationFax+"</td>"
				 +"<td id=''>"+customer.email+"</td>"
				 +"</tr>")
			})	
		}
	</script>

<script type="text/javascript">
/**
 * -添加发运地-
 * */
var i = 0;
$("body").on("click", ".addImg", function() { //关联号增加
	i++;
	/* if(i==1){
		$("#relationUl").html("");
	} */
	$("#relationAddressUl").append("<form><li name='cityAddress" + i + "'>" +
		"<select name='province'  class='prov'></select>	&nbsp;&nbsp;" +
		"<select name='city'  class='city'></select>	&nbsp;&nbsp;" +
		"<select name='district'  class='dist'></select>" +
		"<img src='${pageContext.request.contextPath}/img/add1.png' class='addImg' />" +
		"<img src='${pageContext.request.contextPath}/img/reduce.png' class='reduceImg' />" +
		"</li></form>")

	$("li[name='cityAddress" + i + "']").citySelect({
		prov: "新疆",
		city: "乌鲁木齐",
		dist: "新市区",
		nodata: "none"
	});

});
/**
 * -减少发运地-
 * */
$("body").on("click", ".reduceImg", function() { //关联号减少
	i--;
	if(i==0){
		$("#relationAddressUl").html("");
		$("#relationAddressUl").append("<li>" +
			"<img src='${pageContext.request.contextPath}/img/add1.png' class='addImg' />" +
			//"<img src='${pageContext.request.contextPath}/img/reduce.png' />" +
			"</li>")
	}
	$(this).parent().parent('form').remove();
});
</script>

</html>
