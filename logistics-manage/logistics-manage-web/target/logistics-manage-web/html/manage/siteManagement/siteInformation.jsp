<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>站点信息</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/public.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/manage/siteManagement/siteInformation.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/siteManagement/siteInformation.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.validate.js"></script>
		<!--分页-->
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js" ></script>
		<link href="${pageContext.request.contextPath}/css/page/page.css" rel="stylesheet"/>
		<script type="text/javascript"  src="${pageContext.request.contextPath}/public/js/jquery.cityselect.js"></script>	
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/js/city.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.serializejson.js"></script>
		
		<script type="text/javascript">
		 //父级站点
		 $(function(){
			//确认操作后页面刷新
				$("#sure_msg").click(function() {
					window.location.reload();
					$("#promptIdModal").modal("hide");
				}); 
			 
				$("#city_first").citySelect({
					prov:"安徽", 
					city:"合肥",
					dist:"蜀山区",
					nodata:"none"
				});
			 
			 $.ajax({  
	                type: "GET",  
	                url:"${pageContext.request.contextPath}/trainStation/parent/get.do?id="+0,  
	                async: false,  
	                success: function(data) {  
	                $.each(data.data, function(index, its) {
							$("#up_station_level").append(
	    							"<option value="+its.id+">" + its.stationName
	    									+ "</option>");
						});	
	                }  
	            });  
		 })
		
	   	 function sub() {  
	   	        $.ajax({  
	   	                type: "POST",  
	   	                url:"${pageContext.request.contextPath}/trainStation/addOrUpdate.do",  
	   	                data:$('#addForm').serialize(),
	   	                async: false,  
	   	                success: function(data) {  
	   	                	if(data.status != 1){
	   	        				$("#msg").html("");
	   	        				$("#promptIdModal").modal();
	   	        				$("#msg").html("操作失败");
	   	        			}else{
	   	        				$("#msg").html("");
	   	        				$("#promptIdModal").modal();
	   	        				$("#msg").html("操作成功");
	   	        				$("#addSiteModal").modal("hide");
	   	        			}
	   	                }  
	   	            });  
	   	    }; 
		
	   	    
	   	    
	   	 function closeModal(){
	 		$("#promptModal").modal("hide");
	 		window.location.reload();
	 	}; 
	 	
	 	
	 	
	 	//父级站点切换
	 	function upParent(){
	 		$("#up_station_level").empty();
	 		var id = $("#edit_level option:selected").val();
	 		 $.ajax({  
	                type: "GET",  
	                url:"${pageContext.request.contextPath}/trainStation/parent/get.do?id="+id,  
	                async: false,  
	                success: function(data) {  
	                $.each(data.data, function(index, its) {
							$("#up_station_level").append(
	    							"<option value="+its.id+">" + its.stationName
	    									+ "</option>");
						});	
	                }  
	            });
	 	}
	 	
	 	//修改展示
		function change() {
			var le="";
			if ($("input[type=checkbox]:checked").length != 1) {
				$("#asdf").html("请选择一条要修改的数据");
				$("#deletnullModal").modal();
				return;
			} else {
				le = $("input[type=checkbox]:checked").val();
			}
			$("#myModalLabel").html("修改站点");
			$("#addSiteModal").modal();
			
			$.ajax({
						type : "GET",
						url : "${pageContext.request.contextPath}/trainStation/get.do?id="+le,
						success : function(data) {
							$("#edit_id").val(data.data.id);
							$("#edit_stationName").val(data.data.stationName);
							 $("#edit_level").val(data.data.stationLevel);
							 $("#edit_responsibler").val(data.data.responsibler);
							 $("#edit_stationContacts").val(data.data.stationContacts);
							 $("#edit_department").val(data.data.department);
							 $("#edit_stationPhone").val(data.data.stationPhone);
							 $("#edit_stationFax").val(data.data.stationFax);
							 $("#edit_email").val(data.data.email);
							 $("#up_station_level").val(data.data.parentId);
							 $("#city_first").citySelect({
									prov:data.data.province, 
									city:data.data.city,
									dist:data.data.district,
									nodata:"none"
								});
							 $("#edit_detailAddress").val(data.data.detailAddress);
							 $("#edit_bankAccount").val(data.data.bankAccount);
							 $("#edit_accountName").val(data.data.accountName);
							 $("#edit_openBank").val(data.data.openBank);
							 $("#edit_openBankNum").val(data.data.openBankNum);
							 $("#edit_dutyParagraph").val(data.data.dutyParagraph);
						}
					});
		};
		
		
		function dels(){
	        var idList = new Array();  
	        $.each($('input:checkbox'),function(){
	            if(this.checked){
	                    idList.push($(this).val());   
	            }
	        });
	        
	      	 $.ajax({  
	             type: "POST",  
	             url: "${pageContext.request.contextPath}/trainStation/delete.do",  
	             dataType: 'json',  
	             data: {"idList":idList},  
	             success: function(data){  
	           		if(data.status != 1){
	        				$("#msg").html("");
	        				$("#promptIdModal").modal();
	        				$("#msg").html(data.msg);
	        			}else{
	        				$("#msg").html("");
	        				$("#promptIdModal").modal();
	        				$("#msg").html(data.msg);
	        				$("#typeModal").modal("hide");
	        			}
	             }  
	         });
	    };
		
		</script>
	</head>
	 <!--搜索-->
	<script>
		function search(param){
			$.ajax({
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/trainStation/byPage/list.do',  
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
		<!-- 站点信息表单 -->
		<div class="form project_form container_top">
			<form class="form-inline maxWidth" id="searchForm">
				<div id="wrap">
					<div class="form-group">
						<label>站点名称：</label>
						<input name='stationName' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>站点级别：</label>
						<select name="stationLevel">
							<option></option>
							<option value="0">铁路局</option>
							<option value="1">货运总局</option>
							<option value="2">营业厅</option>
						</select>
					</div>
					<div class="form-group">
						<label>负责人：</label>
						<input name='responsibler' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>联系方式：</label>
						<input name='stationPhone' type="text" maxlength="11" />
					</div>
				</div>
				<div class="foot">
					<a class="btn btn-success" href="javascript:;"><em class="search"></em>
						<span onclick="search(this)">搜索</span>
					</a>
				</div>
			</form>
		</div>
		<!-- 站点信息表格 -->
		<div class="container_top tableDiv">
			<div class="tabbable">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#panel1" data-toggle="tab">站点信息</a>
					</li>
				</ul>
				<!--站点列表-->
				<div class="tab-content">
					<div class="tab-pane active" id="panel1">
						<div class="domain">
							<div class="tableBg">
								<a href="javascript:void(0)" class="exportBtn add" id="addSite"><span>新建</span></a>
							    <a href="javascript:void(0)" class="exportBtn revise" id="modify" onclick="change()"><span>修改</span></a>
								<a href="javascript:void(0)" class="exportBtn del" id="delBtn" ><span>删除</span></a>
							</div>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th> <label class="demo--label">
											     <input class="demo--checkbox" type="checkbox"  disabled="disabled"  >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label> </th>
											<th>站点名称</th>
											<th>站点级别</th>
											<th>子节点</th>
											<th>负责人</th>
											<th>站点地址</th>
											<th>联系人</th>
											<th>部门</th>
											<th>联系方式</th>
											<th>邮箱</th>
											<th>传真</th>
										</tr>
									</thead>
									<tbody id="siteTbody">
										<c:forEach items="${stationList.rows}" var="it">
											<tr>
												<td><label class="demo--label">
												     <input class="demo--checkbox" type="checkbox"  value="${it.id}">
	                                                 <span class="demo--checkboxInput"></span>
		                                        </label></td>
												<td>${it.stationName}</td>
												<td>
														<c:if test="${it.stationLevel ==0}">
															铁路局
														</c:if>
														<c:if test="${it.stationLevel ==1}">
															货运总局
														</c:if>
														<c:if test="${it.stationLevel ==2}">
															营业厅
														</c:if>
												</td>
												<td>8</td>
												<td>${it.responsibler}</td>
												<td>${it.adressCode}-${it.detailAddress}</td>
												<td>${it.stationContacts}</td>
												<td>${it.department}</td>
												<td>${it.stationPhone}</td>
												<td>${it.email}</td>
												<td>${it.stationFax}</td>
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
<!-- 新建站点 -->
	<div class="modal fade addBilling" id="addSiteModal" tabindex="-1" role="dialog" data-backdrop="static">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">新建站点</h4>
					<span data-dismiss="modal" aria-label="Close"></span>
				</div>
				<div class="modal-body">
					<form id="addForm">
						<h5>基本信息</h5>
						<div class="form-inline">
							<div class="form-group">
								<label>&emsp;&emsp;站点名称：</label>
								<input id="edit_id" type="hidden"  name="id"/>
								<input id="edit_stationName" type="text" maxlength="30" name="stationName"/>
							</div>
                           <div class="form-group">
								<label>站点级别：</label>
								<select id="edit_level" name="stationLevel" onchange="upParent()">
									<option value="0">铁路局</option>
									<option value="1">货运总局</option>
									<option value="2">营业厅</option>
								</select>
							</div>

							<div class="form-group">
								<label>&emsp;&emsp;&emsp;负责人：</label>
								<input id="edit_responsibler" type="text" maxlength="30" name="responsibler"/>
							</div>
						</div>
						<div class="form-inline">
							<div class="form-group">
								<label>&emsp;企业联系人：</label>
								<input id="edit_stationContacts" type="text" maxlength="30" name="stationContacts"/>
							</div>
                           <div class="form-group">
								<label>&emsp;&emsp;部门：</label>
								<!-- <input type="text" maxlength="30"/> -->
								<select id="edit_department" name="department">
									<option value="财务">财务</option>
									<option value="运营">运营</option>
								</select>
							</div>
							<div class="form-group">
								<label>企业联系方式：</label>
								<input id="edit_stationPhone" type="text" maxlength="30" name="stationPhone"/>
							</div>
							<div class="form-group">
								<label>&emsp;&emsp;传真：</label>
								<input id="edit_stationFax" type="text" maxlength="30" name="stationFax"/>
							</div>
						</div>
						<div class="form-inline">
							<div class="form-group">
								<label>&emsp;&emsp;&emsp;&emsp;邮箱：</label>
								<input id="edit_email"type="text" maxlength="30" name="email"/>
								<label>&emsp;&emsp;&emsp;&emsp;上级站点：</label>
								<select id="up_station_level" name="parentId">
									<!-- <option value="0">铁路局</option>
									<option value="1">货运总局</option>
									<option value="2">营业厅</option> -->
								</select>
							</div>
						</div>
						<div class="form-inline">
							<!-- <div class="form-group">
								<label>&emsp;&emsp;企业地址：</label>
								<input id="edit_address_code" 
										onblur="checkForm(this)" type="text" name="addressCode"
										class="formcontrol" data-toggle="city-picker"
										placeholder="点击从下拉面板中选择省/市/区">
								<select>
									<option>省</option>
								</select>
								<select>
									<option>市</option>
								</select>
								<select>
									<option>县</option>
								</select>
							</div> -->
							<div class="form-group" id="city_first">
								<label>&emsp;地址信息：</label>
								<select id="edit_province" name="province"  class="prov">
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
							<input id="edit_detailAddress" type="text" class="addressInput" maxlength="100" name="detailAddress"/>
						</div>
						<hr/>
						<div class="project_info">
							<h5>结算相关信息</h5>
							<div class="form-inline">
								<div class="form-group">
									<label>&emsp;&emsp;银行账户：</label>
									<input id="edit_bankAccount"type="text" maxlength="30" name="bankAccount"/>
								</div>
								<div class="form-group">
									<label>&emsp;&emsp;户名：</label>
									<input id="edit_accountName" type="text" maxlength="30" name="accountName"/>
								</div>
								<div class="form-group">
									<label>&emsp;&emsp;&emsp;开户行：</label>
									<input id="edit_openBank" type="text" maxlength="30" name="openBank"/>
								</div>
								<div class="form-group">
									<label>&emsp;&emsp;行号：</label>
									<input id="edit_openBankNum" type="text" maxlength="30" name="openBankNum"/>
								</div>
							</div>
							<div class="form-inline">
								<div class="form-group">
									<label>&emsp;&emsp;&emsp;&emsp;税号：</label>
									<input id="edit_dutyParagraph" type="text" name="dutyParagraph"/>
							    </div>
							</div>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<input type="button" value="确认" onclick="sub()" />
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
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
						<h4 class="modal-title">提示</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="body-content">
							<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
							<span id="asdf">请选择一条要删除的数据</span>
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
							<button type="button" class="btn sureBtn">确定</button>
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
	
	
<!-- 	
	确认提示模态框		
	    <div class="modal fade promptModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" id="promptModal" >
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
        </div> -->
        	<!--确认提示模态框-->		
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
	</body>

<!--分页-->
<script >
	$("#page").paging({
		pageNo:1, 
		totalPage:${stationList.totalPage},
		totalSize:${stationList.limit},
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
       		url:'${pageContext.request.contextPath}/trainStation/byPage/list.do',  
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
    		var stationList =results;
    		$('#siteTbody').html("");
    		var nm ="";
    		$.each(stationList,function(index,it){
    			 if(it.stationLevel ==0){
					  nm = "铁路局";
				 }else if(it.stationLevel ==1){
					  nm = " 货运总局";
				 }else{
					  nm = "营业厅";
				 }
    			$('#siteTbody').append("<tr><td><label class='demo--label'>"
        			+"<input class='demo--checkbox' type='checkbox'  value='"+it.id+"'>"
                     +"<span class='demo--checkboxInput'></span>"            
                     +"</label></td>"
					 +"<td>"+it.stationName+"</td><td>"+nm+"</td>"
					+"<td>8</td>"
					+"<td>"+it.responsibler+"</td>"
					+"<td>"+it.adressCode+"-"+it.detailAddress+"</td>"	
					+"<td>"+it.stationContacts+"</td>"		
					+"<td>"+it.department+"</td>"		
					+"<td>"+it.stationPhone+"</td>"		
					+"<td>"+it.email+"</td>"
					+"<td>"+it.stationFax+"</td></tr>")
    		})	
	    }
</script>
</html>