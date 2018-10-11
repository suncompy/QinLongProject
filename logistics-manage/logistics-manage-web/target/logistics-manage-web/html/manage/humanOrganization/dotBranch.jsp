<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="UTF-8">
		<title>网点分支</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/manage/humanOrganization/dotBranch.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/public.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/humanOrganization/dotBranch.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.validate.js"></script>
			<!--shilvfei加的-->
		<script type="text/javascript"  src="${pageContext.request.contextPath}/public/js/jquery.cityselect.js"></script>	
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/js/city.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js" ></script>
		<link href="${pageContext.request.contextPath}/css/page/page.css" rel="stylesheet"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.serializejson.js"></script>
	</head>
	 <!--搜索-->
	<script>
		function search(param){
			$.ajax({
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/humanOrganization/listDotBranchByPage.do', 
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
		<!--网点分支表单 -->
		<div class="form project_form container_top">
			<form class="form-inline maxWidth" id="searchForm">
				<div id="wrap">
					<div class="form-group">
						<label>网点分支：</label>
						<input name='branchGroupName' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>网点简称：</label>
						<input name='shortName' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>简称代码：</label>
						<input name='shortCode' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>网点归属：</label>
						<input name='ascriptionName' type="text" maxlength="30"/>
					</div>				
				</div>
				<div class="foot">
					<a class="btn btn-success" href="javascript:;"><em class="search"></em>
						<span onclick="search(this)">搜索</span>
					</a>
				</div>
			</form>
		</div>
		<!-- 网点分支表格 -->
		<div class="container_bottom tableDiv">
			<div class="tabbable" id="tabs-529262">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#panel1" data-toggle="tab">网点管理</a>
					</li>
				</ul>
				<div class="tab-content">
					<!--网点管理表格开始-->
					<div class="tab-pane active" id="panel1">

						<div class="domain">
							<div class="tableBg">
								<a href="#" class="exportBtn addNew" id="addBranch"><span>添加分支</span></a>
								<a href="#" class="exportBtn revise" id="modify"><span>修改</span></a>
								<a href="#" class="exportBtn del" id="delBtn"><span>删除</span></a>
							</div>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox" disabled="disabled"  >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label> </th>
											<th>编码</th>
											<th>网点名称</th>
											<th>网点简称</th>
											<th>简称代码</th>
											<th>网点归属</th>
											<th>负责人</th>
											<th>地址信息</th>
											<th>关联火车站</th>
											<th>关联发运地</th>
											<th>备注</th>
										</tr>
									</thead>
									<tbody id='dotBranchTbody'>
									<c:forEach items="${dotBranchs.rows}" var="dotBranch">
										<tr>
											<td>
												<label class="demo--label">
											     <input class="demo--checkbox" type="checkbox"   value="${dotBranch.id}"  >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label>
											</td>
											<td>
												<a href="#">${dotBranch.code}</a>
											</td>
											<td>${dotBranch.name}</td>
											<td>${dotBranch.shortName}</td>
											<td>${dotBranch.shortCode}</td>
											<td>${dotBranch.branchGroup.name}</td>
											<td>
											<c:forEach items="${dotBranch.responsiblers}" var="responsibler">
													${responsibler.name}&nbsp;&nbsp;
											</c:forEach>
											</td>
											<td>${dotBranch.areaId}${dotBranch.address}</td>
											<td>${dotBranch.station.stationName}</td>
											<td>${dotBranch.relationBeginLocation}</td>
											<td>${dotBranch.comment}</td>
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

		<!-- 添加分支模态框 -->
		<div class="modal fade addBranchModal" id="addBranchModal" tabindex="-1" role="dialog" data-backdrop="static">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">添加分支</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form id="addForm">
							<input type="hidden" id="addBranchId" name="id"/>
						<div class="form-inline">
							<div class="form-group">
								<label>&emsp;&emsp;&emsp;编码：</label>
								<input type="text" maxlength="30" name="code"/>
							</div>
							<div class="form-group">
								<label>&emsp;网点名称：</label>
								<input type="text" maxlength="30" name="name" />
							</div>
							<div class="form-group">
								<label>&emsp;网点简称：</label>
								<input type="text" maxlength="2" name="shortName"/>
							</div>
							<div class="form-group">
								<label>&emsp;&emsp;负责人：</label>
								<select name="responsiblerid">
									<c:forEach items="${reponsiblers}" var="reponsibler">	
										<option value="${reponsibler.id}">${reponsibler.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-inline">
							<div class="form-group">
								<label>&emsp;网点归属：</label>
								<select name="ascriptionParentId">
									<c:forEach items="${brachGroups}" var="brachGroup">	
										<option value="${brachGroup.id}">${brachGroup.name}</option>
									</c:forEach>
								</select>
								<select name="ascriptionTwoId">
								</select>
							</div>
						</div>
						<div class="form-inline">
							<div class="form-group" id="city_first">
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
							<input type="text"  name="address"  class="addressInput" maxlength="100" />
							<div  id="searchResultPanel" style="z-index:999999999;border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
						</div>
						<div class="form-inline">
							<div class="form-group">
								<label>关联火车站：</label>
								<select name="relationTrainLocationId">
									<c:forEach items="${trainStations}" var="trainStation">	
										<option value="${trainStation.id}">${trainStation.stationName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<input type="hidden" name="relationBeginLocation"  />
						<input type="hidden" name="level"  />
						<input type="hidden" name="ascriptionId"  />
						<input type="hidden" name="ascriptionName"  />
						<div class="form-inline ">
							<div class="form-group relationClass">
								<label>关联发运地：</label>
								<ul id="relationUl">
								</ul>
							</div>
						</div>
						<div class="form-inline">
							<label>&emsp;&emsp;&emsp;&emsp;备注：</label>
							<input type="text" class="remarkInput" name="comment" maxlength="100" />
							<span class="limitSpan">0/20</span>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" id="addBranchBtn" >确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	
		<!-- 成功提醒模态框 -->
		<div class="modal fade deletRemindModal" style="z-index:99999;" tabindex="-1" role="dialog" id="successModal" data-backdrop="static">
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
								<button type="button" class="btn cancleBtn" data-dismiss="modal" onclick="successSure()" >取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	
	    <!-- 失败提醒模态框 -->
		<div class="modal fade deletRemindModal" style="z-index:99999;" tabindex="-1" role="dialog" id="deletnullModal" data-backdrop="static">
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
								<button type="button"class="btn cancleBtn" data-dismiss="modal">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
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
						<form id="deleteForm">
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
								<button type="button" class="btn sureBtn" onclick="delBranchGroup()">确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
							</div>
						</div>
					</form>
					</div>
				</div>
			</div>
		</div>
	</body>

<script>
	//添加分支模态框 
   $("#addBranch").click(function(){
   	
   	$('#myModalLabel').html("添加分支");
   	
   	$("#city_first").citySelect({
    	prov:"安徽", 
    	city:"合肥",
		dist:"蜀山区",
		nodata:"none"
	}); 
	
	$("select[name=ascriptionParentId]").change();
	
   	$("#addForm").validate().resetForm();
	$("#addForm")[0].reset();
   	$("#addBranchModal").modal();
 	$("#relationUl").html("");
   	$("#relationUl").append("<form><li name='cityAddress'>"
		+"<select name='province'  class='prov'></select>	&nbsp;&nbsp;"
		+"<select name='city'  class='city'></select>	&nbsp;&nbsp;"
		+"<select name='district'  class='dist'></select>"
		+"<img src='${pageContext.request.contextPath}/img/add1.png' class='addImg' />"
		+"<img src='${pageContext.request.contextPath}/img/reduce.png' />"
		+"</li></form>")
   	$("li[name='cityAddress']").citySelect({
    	prov:"安徽", 
    	city:"合肥",
		dist:"蜀山区",
		nodata:"none"
	}); 
   	
   })
</script>
<!--添加发运地-->
<script>
		//关联号增加
	var i = 0;	
	$("body").on("click",".addImg",function(){
		i++;
		$("#relationUl").append("<form><li name='cityAddress"+i+"'>"
		+"<select name='province'  class='prov'></select>	&nbsp;&nbsp;"
		+"<select name='city'  class='city'></select>	&nbsp;&nbsp;"
		+"<select name='district'  class='dist'></select>"
		+"<img src='${pageContext.request.contextPath}/img/add1.png' class='addImg' />"
		+"<img src='${pageContext.request.contextPath}/img/reduce.png' class='reduceImg' />"
		+"</li></form>")
		
		$("li[name='cityAddress"+i+"']").citySelect({
	    	prov:"安徽", 
	    	city:"合肥",
			dist:"蜀山区",
			nodata:"none"
		}); 
		
	});
	//关联号减少
	$("body").on("click",".reduceImg",function(){
		$(this).parent().remove();
	});
	
	
</script>

<!--新建网点分支-->
<script type="text/javascript">
	$("#addBranchBtn").click(function(){
		var hideBranchId = $("#addBranchId").val();
		if(hideBranchId == null || hideBranchId =="" || hideBranchId==undefined){
			var array = new Array();
			$("#relationUl form").each(function(j,item){
				var data = JSON.stringify($(this).serializeJSON());
				array[j]=data;
			})
			$('input[name="relationBeginLocation"]').val("["+array+"]");
			//alert($('input[name="relationBeginLocation"]').val());
			//判断是几级网点
			var flag1 =  $('select[name=ascriptionParentId]').val();
			var flag2 =  $('select[name=ascriptionTwoId]').val();
			if(flag1==null || flag1== undefined || flag1=="" || flag1==0){
				$('input[name=level]').val("0");//一级
			}else if(flag2==0 || flag2== undefined || flag2=="" ){
				$('input[name=level]').val("1");//二级
				$('input[name=ascriptionId]').val(flag1);//归属id
				$('input[name=ascriptionName]').val($('select[name=ascriptionParentId] option:selected').text());//归属名
			}else if(flag2!=null && flag2!= undefined && flag2!="" &&   flag2!=0 ){
				$('input[name=level]').val("2");//三级
				$('input[name=ascriptionId]').val(flag2);//归属id
				$('input[name=ascriptionName]').val($('select[name=ascriptionTwoId] option:selected').text());//归属名
			}
			var formParam = $("#addForm").serialize();//序列化表格内容为字符串 
			$.ajax({  
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/humanOrganization/addDotBranch.do',  
		        data:formParam,
		        cache:false,
		        dataType:'json',
	        	success:function(data){
	        		if(data.status==200){
	        			$("#successMsg").html("");
	        			$("#successMsg").html("新增网点分支成功");
	        			$("#successModal").modal();
						//window.location.reload();
	        		}else{
	        			$("#msgContent").html("");
	        			$("#msgContent").html(data.msg);
	        			$("#deletnullModal").modal();
	        		}
	        	}  
	    	})
		}else{
			var array = new Array();
			$("#relationUl form").each(function(j,item){
				var data = JSON.stringify($(this).serializeJSON());
				array[j]=data;
			})
			$('input[name="relationBeginLocation"]').val("["+array+"]");
			//alert($('input[name="relationBeginLocation"]').val());
			//判断是几级网点
			var flag1 =  $('select[name=ascriptionParentId]').val();
			var flag2 =  $('select[name=ascriptionTwoId]').val();
			if(flag1==null || flag1== undefined || flag1=="" || flag1==0){
				$('input[name=level]').val("0");//一级
				$('input[name=ascriptionId]').val("0");//归属id
			}else if(flag2==0 || flag2== undefined || flag2==""){
				$('input[name=level]').val("1");//二级
				$('input[name=ascriptionId]').val(flag1);//归属id
				$('input[name=ascriptionName]').val($('select[name=ascriptionParentId] option:selected').text());//归属名
			}else if(flag2!=null && flag2!= undefined && flag2!="" &&   flag2!=0 ){
				$('input[name=level]').val("2");//三级
				$('input[name=ascriptionId]').val(flag2);//归属id
				$('input[name=ascriptionName]').val($('select[name=ascriptionTwoId] option:selected').text());//归属名
			}
			var formParam = $("#addForm").serialize();//序列化表格内容为字符串 
			$.ajax({  
		        	type:'post',      
		       		url:'${pageContext.request.contextPath}/humanOrganization/updateDotBranch.do',  
			        data:formParam,
			        cache:false,
			        dataType:'json',
		        	success:function(data){
		        		if(data.status==200){
		        			$("#successMsg").html("");
		        			$("#successMsg").html("修改网点分支成功");
		        			$("#successModal").modal();
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

<!--网点分支二级联动-->
<script>
	$("select[name=ascriptionParentId]").change(function(){
		var value = $(this).find("option:checked").val();
		if(value==0 || value==null || value ==undefined || value==""){
			return;
		}
		$.ajax({  
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/humanOrganization/getDotBranchByFirstLevel.do', 
		        data:{id:value},
		        cache:false,
		        dataType:'json',
	        	success:function(data){
	        		if(data.status==200){
	        			var branchGroups = data.data;
	        			$("select[name=ascriptionTwoId]").html("<option value='0'></option>");
	        			$.each(branchGroups, function(index,branchGroup) {
	        				$("select[name=ascriptionTwoId]").append("<option value='"+branchGroup.id+"'>"+branchGroup.name+"</option>");
	        			});
		        	}
			 	}
		})
	})
</script>

<!--删除网点分支-->
<script>
	
	//删除
	$("#delBtn").click(function() {
		if($("input:checkbox[type='checkbox']:checked").length > 1) {
			$("#deletRemindModal").modal();
		} else if($("input:checkbox[type='checkbox']:checked").length == 0) {
			$("#msgContent").html("");
        	$("#msgContent").html("请选择一条要删除的数据");
			$("#deletnullModal").modal();
		} else {
			$("#deleteForm").validate().resetForm();
			$("#deleteForm")[0].reset();
			$("#deletReasonModal").modal();
		}
	});	
	
	 function delBranchGroup(){
	 	var id = $("input:checkbox[type='checkbox']:checked").val();
		$.ajax({  
    	type:'post',      
   		url:'${pageContext.request.contextPath}/humanOrganization/delDotBranch.do', 
        data:{id:id},
        cache:false,
        dataType:'json',
    	success:function(data){
    		if(data.status==200){
	    			$("#deletReasonModal").hide();
	        		$("#successMsg").html("");
	    			$("#successMsg").html("删除网点分支成功");
	    			$("#successModal").modal();
        		}else{
        			$("#msgContent").html("");
        			$("#msgContent").html(data.msg);
        			$("#deletnullModal").modal();
        		}
	   	 	}
		})
	}


function successSure(){
		$("#successModal").hide();
		window.location.reload();
	}
</script>
<!--网点分支的修改-->
<script>
	
	$('#modify').click(function(){
		//修改
		if($("input:checkbox[type='checkbox']:checked").length > 1) {
			$("#msgContent").html("抱歉，不可批量操作");
			$("#deletnullModal").modal();
			return ;
		} else if($("input:checkbox[type='checkbox']:checked").length == 0) {
			$("#msgContent").html("请选择一条要操作的数据");
			$("#deletnullModal").modal();
			return ;
		} else {
			$("#addForm").validate().resetForm();
			$("#addForm")[0].reset();
		}
		$('#myModalLabel').html("修改分支");
		var id = $("input:checkbox[type='checkbox']:checked").val();
		$("#addBranchModal select[name=ascriptionParentId]").change();
		$.ajax({  
		type:'post',      
		url:'${pageContext.request.contextPath}/humanOrganization/getDotBranchById.do', 
	    data:{id:id},
	    cache:false,
	    dataType:'json',
		success:function(data){
			if(data.status==200){
					var dotBranch = data.data;
					$('#addBranchModal input[name="id"]').val(dotBranch.id);
					$('#addBranchModal input[name="code"]').val(dotBranch.code);
					$('#addBranchModal input[name="name"]').val(dotBranch.name);
					$('#addBranchModal input[name="shortName"]').val(dotBranch.shortName);
					//判断级别
					if(dotBranch.level==1){
						$("#addBranchModal select[name='ascriptionParentId'] option[value='"+dotBranch.ascriptionParentId+"']").attr("selected","selected");
						$("#addBranchModal select[name='ascriptionTwoId'] option[value='"+dotBranch.ascriptionId+"']").attr("selected","selected");
					}else if(dotBranch.level==2){
						$("#addBranchModal select[name='ascriptionParentId'] option[value='"+dotBranch.ascriptionParentId+"']").attr("selected","selected");
						$("#addBranchModal select[name='ascriptionTwoId'] option[value='"+dotBranch.ascriptionId+"']").attr("selected","selected");
					}else if(dotBranch.level==0){
						$("select[name=ascriptionParentId]").change();
						$("select[name=ascriptionParentId]").append("<option value='0'></option>");
						$("#addBranchModal select[name='ascriptionParentId'] option[value=0]").attr("selected","selected");
					}
					
					$("#addBranchModal select[name='province'] option[value='"+dotBranch.province+"']").attr("selected","selected");
					$("#addBranchModal select[name='city'] option[value='"+dotBranch.city+"']").attr("selected","selected");
					$("#addBranchModal select[name='district'] option[value='"+dotBranch.district+"']").attr("selected","selected");
					$("#addBranchModal select[name='relationTrainLocationId'] option[value='"+dotBranch.relationTrainLocationId+"']").attr("selected","selected");
					
					$("#city_first").citySelect({
				    	prov:dotBranch.province, 
				    	city:dotBranch.city,
						dist:dotBranch.district,
						nodata:"none"
					}); 
					
					var relationBeginAddress =dotBranch.relationBeginAddress;
					$("#relationUl").html("");
					$.each(relationBeginAddress, function(index,city) {
						$("#relationUl").append("<form><li name='cityAddress"+index+"'>"
						+"<select name='province'  class='prov'></select>	&nbsp;&nbsp;"
						+"<select name='city'  class='city'></select>	&nbsp;&nbsp;"
						+"<select name='district'  class='dist'></select>"
						+"<img src='${pageContext.request.contextPath}/img/add1.png' class='addImg' />"
						+"<img src='${pageContext.request.contextPath}/img/reduce.png' class='reduceImg' />"
						+"</li></form>")
						$("li[name='cityAddress"+index+"']").citySelect({
					    	prov:city.province, 
					    	city:city.city,
							dist:city.district,
							nodata:"none"
						}); 
					});
					$('input[name="address"]').val(dotBranch.address);
        			$('input[name="comment"]').val(dotBranch.comment);
	    			$("#addBranchModal").modal();
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
<script >
	$("#page").paging({
		pageNo:1, 
		totalPage:${dotBranchs.totalPage},
		totalSize:${dotBranchs.limit},
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
       		url:'${pageContext.request.contextPath}/humanOrganization/listDotBranchByPage.do', 
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

<!--将分页返回结果写入表格-->
<script>
	function htmlTable(results){
		var dotBranchs = results;
		$('#dotBranchTbody').html("");
		$.each(dotBranchs,function(index,dotBranch){
			$('#dotBranchTbody').append("<tr>"
				+"<td style='text-align: center;'><label class='demo--label'>"
				 +" <input class='demo--checkbox' value='"+dotBranch.id+"' type='checkbox'   >"
                  +"<span class='demo--checkboxInput'></span>"
                 +"</label></td>"
			 +"<td id=''>"+dotBranch.code+"</td>"
			  +"<td id=''>"+dotBranch.name+"</td>"
			  +"<td >"+dotBranch.shortName+"</td>"
			  +"<td >"+dotBranch.shortCode+"</td>"
			 +"<td name='parentBotBranch"+index+"'></td>"
			  +"<td >"+dotBranch.responsibler+"</td>"
			  +"<td id=''>"+dotBranch.areaId+dotBranch.address+"</td>"
			 +"<td name='relationStation"+index+"'></td>"
			  +"<td id=''>"+dotBranch.relationBeginLocation+"</td>"
			  +"<td id=''>"+dotBranch.comment+"</td>"
			 +"</tr>")
			//判断上级分支是否为null
			if(dotBranch.branchGroup!=null){
				$("td[name='parentBotBranch"+index+"']").html(dotBranch.branchGroup.name);
			}
			//判断火车站点是否为null
			if(dotBranch.station!=null){
				$("td[name='relationStation"+index+"']").html(dotBranch.station.stationName);
			}
		})	
	}
</script>

</html>

