<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
	<head>
		<meta charset="UTF-8">
		<title>后台分配管理</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/exhibition/public/bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/exhibition/public/bootstrap/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/exhibition/css/sweetalert2.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/exhibition/css/show.css" />
		<link href="${pageContext.request.contextPath}/css/page/page.css" rel="stylesheet"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/exhibition/public/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/exhibition/public/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/exhibition/js/public.js"></script>
		<script src="${pageContext.request.contextPath}/exhibition/js/base.js"></script>
    	<script src="${pageContext.request.contextPath}/exhibition/js/sweetalert2.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/config/url.js"></script>
		<script type="text/javascript"  src="${pageContext.request.contextPath}/public/js/jquery.cityselect.js"></script>	
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/js/city.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js" ></script>
		<%-- <script src="${pageContext.request.contextPath}/static/js/invoke.js"></script> --%>
	</head>
	<body>
		<!--头部-->
		<!-- <div class="head"></div> -->
		<div class="container">
				<div class="navbar-header">
					<div id="logo" class="clearfix">
						<div class="navLogo"><img src="${pageContext.request.contextPath}/exhibition/img/logo.png"></div>
						<div id="logo_name">
							<div class="title">后台分配管理</div>
							<div class="Cname">新疆秦龙物流有限公司</div>
						</div>
					</div>
				</div>
				<ul class="nav navbar-nav navbar-right menu headUl">
					
					<li class="dropdown menuDown">
						<a  style="background: none;" data-target="#" href="${pageContext.request.contextPath}/login.do">物流后台</a>
						<span class="caret" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"></span>
						<ul class="dropdown-menu" aria-labelledby="dLabel">
							<li class="dropdown">
								<a href="http://139.196.100.149:8081/logistics-carmanage-web/login.do"  target="_blank">车队平台 </a>
							</li>
						</ul>
					</li>
					<li class="dropdown">
						<a href=""  target="_blank">欢迎您 &emsp; ${systemUser.name} </a>
					</li>
					<li class="dropdown">
						<a href="${pageContext.request.contextPath}/exhibition/logout.do" class=" dropdown-toggle">
						  退出<!-- <span class="caret"></span> -->
					  	</a>
					  <!-- <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
					    <li><a href="exhRevise.html">修改密码</a></li>
					  </ul> -->
					</li>
				</ul>
			</div>
		
		<!--中间-->
		<div class="container" style="overflow: auto;">
			<div class="box box-primary table-responsive"  >
				<div class="box-header with-border">
					<h4 class="box-title">账号管理</h4>
				</div>
				<form class="form-inline maxWidth">
					<div id="wrap">
						<!-- <div class="form-group dataTimes">
							<label>时间从：</label>
							<input id="d244" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-M-d'})" />
							<i class="fa fa-calendar"></i></span>
						</div>
						<div class="form-group dataTimes secTime">
							<label>至</label>
							<input id="d244" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-M-d'})" />
							<i class="fa fa-calendar"></i></span>
						</div>
						<div class="form-group">
							<input type="text" class="waybillNumber" placeholder="请输入运单编号" />
						</div> -->
						<div class="form-group">
							<button type="button" id="addBtn" class="searchBtn">新增</button>
							<button type="button" id="beginBtn" class="exportBtn">开始</button>
							<button type="button" id="stopBtn"  class="exportBtn">暂停</button>
							<button type="button" id="restPassWdBtn" class="exportBtn">重置密码</button>
						</div>
					</div>
				</form>
				<div class="table-responsive" id='tabHeight'>
					<table class="table table-striped table-bordered dataTable no-footer" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th></th>
								<th>编码</th>
								<th>机构名称</th>
								<th>状态</th>
								<th>账号</th>
								<th>创建时间</th>
							</tr>
						</thead>
						<tbody id="branchGroupTbody">
							<c:forEach items="${branchGroupDataGridResult.rows}" var="branchGroup">
								<tr>
									<td>
									<label class="demo--label" style="text-align:center;">
									<input class="demo--checkbox" type="checkbox" value="${branchGroup.id}" name="branchGroupId">
									<span class="demo--checkboxInput"></span></label>
									</td>
									<td>${branchGroup.code}</td>
									<td>${branchGroup.name}</td>
									<c:if test="${branchGroup.status==0}">
										<td>使用中</td>
									</c:if>
									<c:if test="${branchGroup.status==1}">
										<td>暂停使用</td>
									</c:if>
									<td>${branchGroup.responsibler}</td>
									<td><fmt:formatDate value="${branchGroup.createDate}" pattern="yyyy/MM/dd  HH:mm:ss" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="container paginationul paginationuls">
				<div class="col-md-12 column paging page_div" id="page">
				</div>
			</div>
		</div>
		<!--底部-->
		<!-- <div class='foot'></div> -->
		<%-- <c:import url="food.jsp"></c:import>  --%>
		<div id='footCon'>
			<div class="container footConDiv clearfix">
				<div class="pFoot">
				<!-- 	<p>本平台仅作为项目列表的展示作用<br/>目前为测试阶段</p> -->
				</div>
				<div>
					<dl>
						<dt>
						<dd><h4>快捷导航</h4></dd>
						<dd><a href="http://139.196.100.149:8081/logistics-carmanage-web/login.do" target="_blank">车队平台</a></dd>					
						<dd><a href="${pageContext.request.contextPath}/login.do" target="_blank">物流后台</a></dd>
					</dt>
					</dl>
				</div>
			</div>
		</div>
		
		 <!-- 结算模态框-->
		<div class="modal fade" id="settlementModal" tabindex="-1" role="dialog" data-keyboard="false" data-backdrop="static" aria-hidden="true">
			<div class="modal-dialog" style="width: 1100px">
				<div class="modal-content">
					<div class="modal-body">
						<form class="form-horizontal" id="saveBranchGroupFormId">
							<fieldset>
								<legend>
									<span style="font-size: 12px; color: red;">机构信息<i class='requireds'>*</i></span>
								</legend>
								<div class="form-group">
									<label class="col-md-1 control-label">编码</label>
									<div class="col-md-2">
										<input name="code" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="只能输入数字！" maxlength="6"   />
									</div>
									<label class="col-md-1 control-label">机构名称</label>
									<div class="col-md-2">
										<input name="name"  />
									</div>
									<label class="col-md-1 control-label">机构简称</label>
									<div class="col-md-2">
										<input name="shortName" onkeyup="value=value.replace(/^[\u0391-\uFFE5]+$/,'')" placeholder="只能是英文字母！" maxlength="4"  />
									</div>
									<input type="hidden" name="level" value="0">
								</div>
								<div class="form-inline">
									<div class="form-group" id="city_first">
										<label>&emsp;&emsp;&emsp;地址信息：</label>
										<select name="province"  class="prov">
										</select>
										&nbsp;
										<select name="city"  class="city">
										</select>
										&nbsp;
										<select name="district"  class="dist">
										</select>
									</div>
									&nbsp;&nbsp;&nbsp;
									<input type="text"  name="address"  class="addressInput" maxlength="100" />
								</div>
							</fieldset>
							<fieldset>
								<legend>
									<span style="font-size: 12px; color: red;">管理员信息<i class='requireds'>*</i></span>
								</legend>
								<div class="form-group">
									<label class="col-md-1 control-label">账户</label>
									<div class="col-md-2">
										<input type="text" name="userName" />
									</div>
									<label class="col-md-1 control-label">密码</label>
									<div class="col-md-2">
										<input type="password"  name="passwd" />
									</div>
									<label class="col-md-1 control-label">手机号</label>
									<div class="col-md-2">
										<input type="text"  onkeyup="value=value.replace(/[^\d]/g,'')"  maxlength="11"   name="phone" />
									</div>
								</div>
							</fieldset>
						</form>
					</div>
					<div class="modal-footer">
						<button class="btn btn-primary" id="btnBranchGroupAccountsSave">确定</button>
						<button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">取消</button>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript">
	$("#city_first").citySelect({
		prov: "新疆",
		city: "乌鲁木齐",
		dist: "新市区",
		nodata: "none"
	});
	$("#addBtn").click(function(){
		$("#settlementModal").modal("show");
	})
	 
	</script>
	<script type="text/javascript">
		var tale = new $.tale();
		$("#btnBranchGroupAccountsSave").click(function(){
        tale.post({
            url: '${pageContext.request.contextPath}/humanOrganization/addDotBranch.do',
            data: $("#saveBranchGroupFormId").serialize(),
            success: function (result) {
                if (result.status == 200) {
                	$("#settlementModal").modal("hide");
                	tale.alertOkAndReload("新增顶级机构成功!");
                } else {
                    tale.alertError(result.msg || '登录失败');
                }
            }
        });
        return false;
    	})
    
    $("#beginBtn").click(function(){
    	//var ids = new Array(); 
		var id="";
		var i = 0;
		$("input[name='branchGroupId']:checked").each(function(){
			i++;
			id=$(this).val();
		})
		
		if(i>1){
			tale.alertWarn("抱歉，不可批量开始");
			return ;
		}
		
		if(id == undefined || id == null  || id=="" ){
			tale.alertWarn("请选择一条您要开始的顶级机构");
			return ;
		}
        tale.post({
            url: '${pageContext.request.contextPath}/exhibition/branchGroupBeginOrStop.do',
            data: {status:0,branchId:id},
            success: function (result) {
                if (result.status == 200) {
                	/* $("#settlementModal").modal("hide"); */
                	//tale.alertOk("开始成功!"); 不刷新 
                	tale.alertOkAndReload("开始成功!");//成功并刷新
                    //window.location.href = '${pageContext.request.contextPath}/exhibition/branchGroupManage.do';
                } else {
                    tale.alertError(result.msg || '登录失败');
                }
            }
        });
        return false;
    })
    
     $("#stopBtn").click(function(){
    	 var id="";
 		var i = 0;
 		$("input[name='branchGroupId']:checked").each(function(){
 			i++;
 			id=$(this).val();
 		})
 		
 		if(i>1){
 			tale.alertWarn("抱歉，不可批量暂停");
 			return ;
 		}
 		
 		if(id == undefined || id == null  || id=="" ){
 			tale.alertWarn("请选择一条您要暂停的顶级机构");
 			return ;
 		}
    	 
        tale.post({
            url: '${pageContext.request.contextPath}/exhibition/branchGroupBeginOrStop.do',
            data: {status:1,branchId:id},
            success: function (result) {
                if (result.status == 200) {
                	tale.alertOkAndReload("暂停成功!");//成功并刷新
                } else {
                    tale.alertError(result.msg || '登录失败');
                }
            }
        });
        return false;
    })
    
    $("#restPassWdBtn").click(function(){
    	 var id="";
 		var i = 0;
 		$("input[name='branchGroupId']:checked").each(function(){
 			i++;
 			id=$(this).val();
 		})
 		
 		if(i>1){
 			tale.alertWarn("抱歉，不可批量重置密码");
 			return ;
 		}
 		
 		if(id == undefined || id == null  || id=="" ){
 			tale.alertWarn("请选择一条您要重置密码的顶级机构");
 			return ;
 		}
        tale.post({
            url: '${pageContext.request.contextPath}/exhibition/restPassWd.do',
            data: {branchId:id},
            success: function (result) {
                if (result.status == 200) {
                	tale.alertOk("重置密码成功");
                    //window.location.href = '${pageContext.request.contextPath}/exhibition/branchGroupManage.do';
                } else {
                    tale.alertError(result.msg || '登录失败');
                }
            }
        });
        return false;
    })
	</script>
	
	
	<!--分页-->
<script  type="text/javascript">
	$("#page").paging({
		pageNo:1, 
		totalPage:${branchGroupDataGridResult.totalPage},
		totalSize:${branchGroupDataGridResult.limit},
		callback: function(num) {
			searchByPage(num);
		}
	})
</script>

<!--执行分页查询的方法 -->		
<script  type="text/javascript">
	function searchByPage(num){
		$.ajax({  
        	type:'GET',      
       		url:'${pageContext.request.contextPath}/exhibition/branchGroupManageByPage.do',  
	       	data:{page:num},
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


<!--将查询到的结果写入表格-->
<script  type="text/javascript">
	function htmlTable(results){
		var branchGroupList = results;
		var tbody ="branchGroupTbody";
		$("#"+tbody+"").html("");
		$.each(branchGroupList,function(index,branchGroup){
			var status="";
			
			if(branchGroup.status==0){
				status="使用中";
			}else
			if(branchGroup.status==1){
				status="暂停使用";
			}
			
			$("#"+tbody+"").append('<tr><td><label class="demo--label" style="text-align:center;">'
			+'<input class="demo--checkbox" type="checkbox" value="'+branchGroup.id+'" name="branchGroupId">'
			+'<span class="demo--checkboxInput"></span></label></td>'
			+'<td>'+branchGroup.code+'</td>'
			+'<td>'+branchGroup.name+'</td>'
				+'<td>'+status+'</td>'
			+'<td>'+branchGroup.responsibler+'</td>'
			+'<td>'+branchGroup.createDate+'</td></tr>')
		})
	}
</script>


</html>