<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>火车车型</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/public/public.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/manage/transportationManagement/trainType.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/public.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/public/bootstrap/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/manage/transportationManagement/trainType.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/public/jquery.validate.js"></script>
<!--分页-->
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js" ></script>
		<link href="${pageContext.request.contextPath}/css/page/page.css" rel="stylesheet"/>	
<script type="text/javascript">

	 $(function(){
		//确认操作后页面刷新
			$("#sure_msg").click(function() {
				window.location.reload();
				$("#promptIdModal").modal("hide");
			}); 
		 
	 });
   	 function sub() {  
   	        $.ajax({  
   	                cache: true,  
   	                type: "POST",  
   	                url:"${pageContext.request.contextPath}/trainType/add.do",  
   	                data:$('#addTypeForm').serialize(),
   	                async: false,  
   	                success: function(data) {  
   	                	if(data.status != 1){
   	                		$("#msg").html(data.msg);
							$("#promptIdModal").modal();
   	        			}else{
   	        				$("#msg").html(data.msg);
							$("#promptIdModal").modal();
							$("#addTypeModal").modal("hide");
   	        				$("#typeModal").modal("hide");
   	        			}
   	                }  
   	            });  
   	    };  
   	    
   	    
   	    
   	    
/*    	 $("#dels").click(function(){
       	 alert(1)
         $.each($('input:checkbox'),function(){
             if(this.checked){
            	 alert(1);
                 alert("你选了："+
                     $('input[type=checkbox]:checked').length+"个，其中有："+$(this).val());
             }
         });
     }); */
   	    
   	function dels(){
    	$("#deletReasonModal").hide();
        var idList = new Array();  
        $.each($('input:checkbox'),function(){
            if(this.checked){
                    idList.push($(this).val());   
            }
        });
        
      	 $.ajax({  
             type: "POST",  
             url: "${pageContext.request.contextPath}/trainType/delete.do",  
             dataType: 'json',  
             data: {"idList":idList},  
             success: function(data){  
           		if(data.status != 1){
           			$("#msg").html(data.msg);
					$("#promptIdModal").modal();
        			}else{
        				$("#msg").html(data.msg);
						$("#promptIdModal").modal();
        				$("#typeModal").modal("hide");
        			}
             }  
         });
    };
    	 
	 function closeModal(){
		$("#promptModal").modal("hide");
		window.location.reload();
	}; 
	
	
	
	
</script> 
</head>
<body>
	<!--火车车型列表-->
	<div class="container_top tableDiv">
		<div class="tabbable">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#panel1" data-toggle="tab">火车车型</a>
				</li>
			</ul>
			<!--火车车型列表-->
			<div class="tab-content">
				<div class="tab-pane active" id="panel1">
					<div class="domain">
						<div class="tableBg">
							<a href="javascript:void(0)" class="exportBtn addNew"  id="addType"><span>添加车型</span></a>
							<a href="javascript:void(0)" class="exportBtn del" id="delBtn"><span>删除</span></a>
						</div>
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr class="tableTop">
										<th><label class="demo--label"> <input
												class="demo--checkbox" type="checkbox" disabled="disabled"  > <span
												class="demo--checkboxInput"></span>
										</label></th>
										<th>火车车型</th>
										<th>车种代码</th>
										<th>车型代码</th>
										<th>自重</th>
										<th>载重</th>
										<th>容积</th>
										<th>计费载重</th>
										<th>长</th>
										<th>宽</th>
										<th>高</th>
										<th>备注</th>
									</tr>
								</thead>
								<tbody id="tyBody">
									<c:forEach var="it" items="${tyList.rows}">
										<tr>
											<td><label class="demo--label"> <input
													class="demo--checkbox" type="checkbox"  value="${it.id}"> <span
													class="demo--checkboxInput"></span>
											</label></td>
											<td>${it.trainKind}</td>
											<td>${it.trainKindCode}</td>
											<td>${it.trainTypeCode}</td>
											<td>${it.selfWeight}吨</td>
											<td>${it.weight}吨</td>
											<td>${volume}m³</td>
											<td>${it.loadPrice}吨</td>
											<td>${it.length}cm</td>
											<td>${it.width}cm</td>
											<td>${it.hight}cm</td>
											<td>${it.remark}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div class="row clearfix">
						<!-- <div class="col-md-12 column paging">
							<ul class="pagination clearfix">
								<li><a href="javascript:void(0)" class="newA">上一页</a></li>
								<li><a href="javascript:void(0)" class="newA actives">1</a></li>
								<li><a href="javascript:void(0)" class="newA">2</a></li>
								<li><a href="javascript:void(0)" class="newA">3</a></li>
								<li><a href="javascript:void(0)" class="newA">4</a></li>
								<li><a href="javascript:void(0)" class="newA">5</a></li>

								<li><a href="javascript:void(0)" class="newA">...</a></li>
								<li><a href="javascript:void(0)" class="newA">10</a></li>

								<li><a href="javascript:void(0)" class="newA">下一页 </a></li>

								<li><a class="pageSpan">跳转到 <input type="text"
										id="pageInput" />页
								</a></li>
								<li></li>
								<li><a href="javascript:void(0)" class="pageGo">GO</a></li>

							</ul>
						</div> -->
						<div class="col-md-12 column paging page_div" id="page">
							</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 添加车型 模态框-->
	<div class="modal fade addTypeModal" id="addTypeModal" tabindex="-1" role="dialog" data-backdrop="static">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">添加车型</h4>
					<span data-dismiss="modal" aria-label="Close"></span>
				</div>
				<div class="modal-body">
				<h5>车型信息</h5>
	            <form id="addTypeForm">
					<div class="project_info">
						<div class="form-inline">
							<div class="form-group">
								<label>&emsp;火车车型：</label>
								<input type="text" maxlength="30" name="trainKind"/>
							</div>
							<div class="form-group">
								<label>&emsp;车种代码：</label>
								<input type="text" maxlength="30" name="trainKindCode"/>
							</div>
							<div class="form-group">
								<label>&emsp;车型代码：</label>
								<input type="text" maxlength="30" name="trainTypeCode"/>
							</div>
						</div>
						<div class="form-inline">
							<div class="form-group">
								<label>&emsp;&emsp;&emsp;自重：</label>
								<input type="text" maxlength="30" name="selfWeight"/>
								<span>吨</span>
							</div>
							<div class="form-group">
								<label>&emsp;&emsp;&emsp;载重：</label>
								<input type="text" maxlength="30" name="weight"/>
								<span>吨</span>
							</div>
							<div class="form-group">
								<label>&emsp;&emsp;&emsp;容积：</label>
								<input type="text" maxlength="30" name="volume"/>
								<span>㎡</span>
							</div>
							<div class="form-group">
								<label>&emsp;计费单位：</label>
								<input type="text" maxlength="30" name="loadPrice"/>
								<span>吨</span>
							</div>
						</div>
						<div class="form-inline">
							<div class="form-group">
								<label>&emsp;&emsp;&emsp;&emsp;长：</label>
								<input type="text" maxlength="30" name="length"/>
								<span>cm</span>
							</div>
							<div class="form-group">
								<label>&emsp;&emsp;&emsp;&emsp;宽：</label>
								<input type="text" maxlength="30" name="width"/>
								<span>cm</span>
							</div>
							<div class="form-group">
								<label>&emsp;&emsp;&emsp;&emsp;高：</label>
								<input type="text" maxlength="30" name="hight"/>
								<span>cm</span>
							</div>
						</div>
						<div class="form-inline ">
							<div class="form-group">
								<label>&emsp;&emsp;&emsp;备注：</label>
								<input type="text" class="remarkInput" maxlength="20" name="remark"/>
								<span >0/20</span>
							</div>
						</div>
					</div>
					</form>
					<div class="row clearfix ">
						<div class="col-md-12 column modal_btn">
							<input type="button" class="btn" value="确认" onclick="sub()">
							<button type="button" class="btn cancleBtn" data-dismiss="modal">取消</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
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
							<span>请选择一条要删除的数据</span>
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
								<button type="button" class="btn sureBtn" data-dismiss="modal">确定</button>
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

	
</body>

<script>
	//分页
	$("#page").paging({
		pageNo:1, 
		totalPage: ${tyList.totalPage},
		totalSize:${tyList.limit},
		callback: function(num) {
				$.ajax({  
	        	type:'post',      
	       		url:'${pageContext.request.contextPath}/trainType/byPage/list.do',  
		        data:{page:num},
		        cache:false,
		        dataType:'json',
	        	success:function(data){
	        		if(data.status==200){
	        		var tyList = data.data;
	        		$('#tyBody').html("");
	        		$.each(tyList,function(index,it){
	        			$('#tyBody').append("<tr><td><label class='demo--label'>"
	        					+"<input class='demo--checkbox' type='checkbox' value='"+ it.id +"'>"
	        					+"<span class='demo--checkboxInput'></span>"
						+"</label></td>"
						+"<td>"+ it.trainKind +"</td>"
						+"<td>"+it.trainKindCode+"</td> <td>"+it.trainTypeCode +"</td>"
						+"<td>"+ it.selfWeight+"吨</td> <td>"+it.weight+"吨</td> <td>"+it.volume+"m³</td> <td>"+it.loadPrice+"吨</td><td>"+it.length+"cm</td> <td>"+ it.width+"cm</td> <td>"+it.hight +"cm</td> <td>"+it.remark+"</td> </tr>")
	        		})	
	        	}  
	    	}
		})
	}		
})
</script>

</html>