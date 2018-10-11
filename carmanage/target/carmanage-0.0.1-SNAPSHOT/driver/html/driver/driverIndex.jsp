<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>司机首页</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/driver/css/driver/driverIndex.css" />
	<script src="${pageContext.request.contextPath}/public/js/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/public/js/echarts.js"></script>
	<script src="${pageContext.request.contextPath}/driver/js/driver/driverIndex.js"></script>
</head>
<body>
	<div class="container-fluit">
		<div class="row">
			<div class="col-md-6 col-sm-6">
			<!-- 账号信息 -->
			    <!-- 挂靠车队	 -->	
				<div class="userInfo">
					<h4 ><i class="fa fa-user-circle-o" aria-hidden="true"></i><span>账号信息</span></h4>
					<hr>
					<ul class="clearfix">
						<li><label id="">用户名：</label><span id="username" ></span></li>
						<li><label id="">联系方式：</label><span id="phone"></li>
					</ul>
					<hr>
					<ul class="clearfix"> 
						<li><label id="anchoredNameLabel"></label><span id="anchoredName"></span></li>
						<li><label id="">注册时间：</label><span id="registerTime"></span></li>
					</ul>	
				</div>

            <!-- 挂靠车队信息-->
                <div id="anchoredCarTeam" class="anchored-fleet hidden">
                	<div class="clearfix">
					    <h4 class=" pull-left"><img src="${pageContext.request.contextPath}/driver/img/carInformation.png" alt=""><span>挂靠车队信息</span></h4>
					  <!--  <a class="more pull-right" href="">查看更多&gt;&gt;</a>-->
					</div>

					<hr>
					<ul class="clearfix">
						<li><label >车队名称：</label><span id="carTeam_name" ></span></li>
						<li><label >联系方式：</label><span  id="carTeam_phone"></li>
						<li><label >挂靠日期：</label><span  id="carTeam_anchoredDate"></li>
						<li><label >负责人：</label><span  id="carTeam_responsibler"></li>
					</ul>
				</div>
				

			   <!-- 挂靠公司信息    隐藏-->
			    <div id="anchoredCompany" class="anchored-company hidden" >
					<div class="clearfix">
						<h4 class="pull-left"><img src="${pageContext.request.contextPath}/driver/img/anchoredCompany.png" alt=""><span id="">挂靠公司信息</span></h4>
						<a class="more pull-right" href="">查看更多>></a>
					</div>
					
					<ul id="anchoredCompany_detail">
						<!--<li>
							<ul  class="secondary" >
								<li><label>公司名称：</label><span id="">合肥深合软件有限公司</span></li>
								<li ><label>挂靠时间：</label><span id="">2017.08.01 10：00</span></li>
								
								<div style="clear:both;"></div>
							</ul>
						</li>
						<li>
							<ul  class="secondary" >
								<li ><label>公司名称：</label><span id="">合肥深合软件有限公司</span></li>
								<li><label>挂靠时间：</label><span id="">2017.08.01 10：00</span></li>
								
								<div style="clear:both;"></div>
							</ul>
						</li>
						<li>
							<ul  class="secondary" >
								<li><label>公司名称：</label><span id="">合肥深合软件有限公司</span></li>
								<li><label>挂靠时间：</label><span id="">2017.08.01 10：00</span></li>
								
								<div style="clear:both;"></div>
							</ul>
						</li>
						<li>
							<ul  class="secondary" >
								<li ><label>公司名称：</label><span id="">合肥深合软件有限公司</span></li>
								<li ><label>挂靠时间：</label><span id="">2017.08.01 10：00</span></li>
								
								<div style="clear:both;"></div>
							</ul>
						</li>
						<li>
							<ul  class="secondary" >
								<li ><label>公司名称：</label><span id="">合肥深合软件有限公司</span></li>
								<li ><label>挂靠时间：</label><span id="">2017.08.01 10：00</span></li>
								
								<div style="clear:both;"></div>
							</ul>
						</li>-->
					</ul>
				</div>



			<!-- 订单数量 -->
			    <div class="orderNum " >
					<div class="clearfix">
						<h4 class="pull-left"><i class="fa fa-file-text-o" aria-hidden="true"></i><span>订单数量</span></h4>
						<ul class="pull-right">
							<li class="borderRight "><a href="#week" class="week active">按周查看</a></li>
							<li><a href="#month" class="month">按月查看</a></li>
						</ul>
					</div>
					<hr>
					<div id="line">
					</div>
				</div>
			</div>
			
			<div class="col-md-6 col-sm-6">
			    <!-- 车辆信息 -->
			    <div class="vehicle-info " >
					<div class="clearfix">
						<h4 class="pull-left"><img src="${pageContext.request.contextPath}/driver/img/carInformation.png" alt=""><span>车辆信息</span></h4>
						<a class="more pull-right" href="${pageContext.request.contextPath}/driver/driverAndCar.do">查看详情>></a>
					</div>
					<hr />
					<ul  class="clearfix" id="carInfo" >
						<li ><label>品牌：</label><span id="brand"></span></li>
						<li ><label>型号：</label><span id="model"></span></li>
						<li ><label>车牌号：</label><span class="special" id="plateNumber"></span></li>
						<li ><label>购买时间：</label><span id="buyDate"></span></li>
						<!-- <li ><label>驾龄：</label><span  id="driverAge"></span></li> -->
						<li ><label>驾驶证类型：</label><span  id="driverLicenceType"></span></li>
					</ul>
				</div>

				<!-- 消息中心 -->
				<div class="newsCenter">
					<h4 ><img src="${pageContext.request.contextPath}/driver/img/notice.png" alt=""><span>消息中心</span></h4>
					<ul id="notice">
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
		window.onload=function(){
			 $.ajax({
            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"",//${pageContext.request.contextPath}/driver/account.do
            //提交的数据
            //data:{Name:"sanmao",Password:"sanmaoword"},
            //cache:false,
            //返回数据的格式
            datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
            //成功返回之后调用的函数             
            success:function(data){
            	if(data.status==200){
            		$("#username").html(data.data.username);
            		$("#phone").html(data.data.phone);
            		if(data.data.anchoredCarTeam !== null){
            			$("#anchoredNameLabel").html("挂靠车队：");
            			$("#anchoredName").html(data.data.anchoredCarTeam.carItemName);
            			$("#carTeam_name").html(data.data.anchoredCarTeam.carItemName);
            			$("#carTeam_phone").html(data.data.anchoredCarTeam.phone);
            			$("#carTeam_anchoredDate").html(data.data.anchoredTime);
            			$("#carTeam_responsibler").html(data.data.anchoredCarTeam.name);
            			$("#anchoredCarTeam").removeClass("hidden");
            			
            		}else if(data.data.anchoredCompany !== null){
            			$("#anchoredNameLabel").html("挂靠公司：");
            			$("#anchoredName").html(data.data.anchoredCompany.name);
            			$("#anchoredCompany").removeClass("hidden");
            			/* // 获取挂靠公司信息 */
				         $.ajax({
				            //提交数据的类型 POST GET
				            type:"POST",
				            //提交的网址
				            url:"${pageContext.request.contextPath}/driver/anchoredcompany.do",
				            //返回数据的格式
				            datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
				            //成功返回之后调用的函数             
				            success:function(data){
				            	if(data.status==200){
				            		$.each(data.data,function(index,anchoredCompany){
				            			$("#anchoredCompany_detail").append("<li><ul  class='secondary' >"+
											"<li class='percent'><label>公司名称：</label><span id=''>"+anchoredCompany.name+"</span></li>"+
											"<li class='percent'><label>挂靠时间：</label><span id=''>"+anchoredCompany.time+"</span></li>"+
											"<div style='clear:both';></div></ul></li>"
				            			)
				            		})
				            	}
				            },
				         }); 
            			
            		}
            		$("#registerTime").html(data.data.registerTime);
            	}
            },
         });  
         
         
         /*消息中心*/
		$.ajax({
            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"",//${pageContext.request.contextPath}/carmanage/notice.do
            //提交的数据
            //data:{Name:"sanmao",Password:"sanmaoword"},
            //返回数据的格式
            datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
            //成功返回之后调用的函数             
            success:function(data){
            	if(data.status==200){
            		$.each(data.data,function(index,notice){
            			$("#notice").append("<li><span>"+notice.content+"</span></li>");
                        // alert(notice.content);
                	});  
            	}
            },
        });
        
        
        
        /*获取车辆信息*/
		$.ajax({
            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"",//${pageContext.request.contextPath}/driver/carmsg.do
            //提交的数据
            //data:{Name:"sanmao",Password:"sanmaoword"},
            //返回数据的格式
            datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
            //成功返回之后调用的函数             
            success:function(data){
            	if(data.status==200){
            		var userInfo = data.data;
            			$("#brand").html(userInfo.brand);
            			$("#model").html(userInfo.model);
            			$("#plateNumber").html(userInfo.plateNumber);
            			$("#buyDate").html(userInfo.buyDate);
            			$("#driverAge").html(userInfo.driverAge+"年");
            			$("#driverLicenceType").html(userInfo.driverLicenceType);
            	}
            },
         });
	}	
</script>
<script>
	
      $(document).ready(function(){ 
        $(".orderNum ul li a").click(function(){
            //切换选中的按钮高亮状态
                    $(this).addClass("active").parent().siblings().children().removeClass("active");
                });
                var myChart = echarts.init(document.getElementById("line")); 
               myChart.setOption ({  
                    tooltip : {  
                        trigger: 'axis'  
                    },  
                    legend: {  
                        show:false  
                    },  
                    toolbox: {  
                       show:false
                    },  
                    grid: {  
                        left: '3%',  
                        right: '4%',  
                        top: '10%', 
                        bottom:'3%' ,  
                        containLabel: true  
                    },  
                    xAxis : [  
                        {  
                            type : 'category',  
                            boundaryGap : false,  
                            data : [ ' ','周一','周二','周三','周四','周五','周六','周日','']  ,
                            splitLine:{show:true}
                        }  
                    ],  
                    yAxis : [  
                        {  
                            type : 'value' ,
                            splitNumber:5,
                        }  
                    ],  
                    series : [  
                        {  
                            name:'订单数量',
                            color:['#4C9AEA'],
                            type:'line',
                            symbol:'circle',
                            symbolSize:8,  
                            stack: '数量', 
                            itemStyle : {  
                                normal : {  
                                    lineStyle:{  
                                        color:'#4C9AEA'  
                                    }  
                                }  
                            },   
                          //  data:[null,10, 34,28 , 35, 16, 30,38]  
                        }  
                    ]  
                });  
                // chart.showLoading();    //数据加载完之前先显示一段简单的loading动画
         
         var nums=[];    //运单量数组（实际用来盛放Y坐标值）
         
         $.ajax({
         type : "get",
         async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
         url : "",    //请求发送到TestServlet处        ${pageContext.request.contextPath}/driver/orderNum.do?type=0
         data : {},
         dataType : "json",        //返回数据形式为json
         success : function(result) {
             //请求成功时执行该函数内容，result即为服务器返回的json对象
             if (result.status==200) {
             		nums.push(null);
             		$.each(result.data,function(index,orderNum){
             			nums.push(orderNum.num);    //挨个取出类别并填入类别数组
				    })
                    myChart.hideLoading();    //隐藏加载动画
                    myChart.setOption({        //加载数据图表
                        series: [{
                            // 根据名字对应到相应的系列
                          //  name: '销量',
                            data: nums
                        }]
                    });
                    
             }
         
        },
         error : function(errorMsg) {
		         //请求失败时执行该函数
		         //alert("图表请求数据失败!");
		         myChart.hideLoading();
        }
    })
                
               
              //  chart.setOption(option1,true); 
//           按月查看
         $('.week').click(function(){
             myChart.setOption ({  
                    tooltip : {  
                        trigger: 'axis'  
                    },  
                    legend: {  
                        show:false  
                    },  
                    toolbox: {  
                       show:false
                    },  
                    grid: {  
                        left: '3%',  
                        right: '4%',  
                        top: '10%', 
                        bottom:'3%' ,  
                        containLabel: true  
                    },  
                    xAxis : [  
                        {  
                            type : 'category',  
                            boundaryGap : false,  
                            data : [ ' ','周一','周二','周三','周四','周五','周六','周日','']  ,
                            splitLine:{show:true}
                        }  
                    ],  
                    yAxis : [  
                        {  
                            type : 'value' ,
                            splitNumber:5,
                        }  
                    ],  
                    series : [  
                        {  
                            name:'订单数量',
                            color:['#4C9AEA'],
                            type:'line',
                            symbol:'circle',
                            symbolSize:8,  
                            stack: '数量', 
                            itemStyle : {  
                                normal : {  
                                    lineStyle:{  
                                        color:'#4C9AEA'  
                                    }  
                                }  
                            },   
                          //  data:[null,10, 34,28 , 35, 16, 30,38]  
                        }  
                    ]  
                });  
                // chart.showLoading();    //数据加载完之前先显示一段简单的loading动画
         
         var nums=[];    //运单量数组（实际用来盛放Y坐标值）
         
         $.ajax({
         type : "get",
         async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
         url : "${pageContext.request.contextPath}/driver/orderNum.do?type=0",    //请求发送到TestServlet处
         data : {},
         dataType : "json",        //返回数据形式为json
         success : function(result) {
             //请求成功时执行该函数内容，result即为服务器返回的json对象
             if (result.status==200) {
             		nums.push(null);
             		$.each(result.data,function(index,orderNum){
             			nums.push(orderNum.num);    //挨个取出类别并填入类别数组
				    })
                    myChart.hideLoading();    //隐藏加载动画
                    myChart.setOption({        //加载数据图表
                        series: [{
                            // 根据名字对应到相应的系列
                          //  name: '销量',
                            data: nums
                        }]
                    });
                    
             }
         
        },
         error : function(errorMsg) {
		         //请求失败时执行该函数
		         alert("图表请求数据失败!");
		         myChart.hideLoading();
        }
    })
         });
        
         $('.month').click(function(){
         	myChart.setOption({  
                  
                    tooltip : {  
                        trigger: 'axis'  
                    },  
                    legend: {  
                        show:false  
                    },  
                    toolbox: {  
                       show:false
                    },  
                    grid: {  
                        left: '3%',  
                        right: '4%',  
                        top: '10%', 
                        bottom:'3%' ,  
                        containLabel: true  
                    },  
                    xAxis : [  
                        {  
                            type : 'category',  
                            boundaryGap : false,  
                            data : [ ' ','一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月','']  ,
                            splitLine:{show:true},
                            axisLabel :{
                                    interval:0
                                    }
                        }  
                    ],  
                    yAxis : [  
                        {  
                            type : 'value' ,
                            splitNumber:5,
                        }  
                    ],  
                    series : [  
                        {  
                            name:'订单数量',
                            color:['#4C9AEA'],
                            type:'line',
                            symbol:'circle',
                            symbolSize:8,  
                            stack: '数量', 
                            itemStyle : {  
                                normal : {  
                                    lineStyle:{  
                                        color:'#4C9AEA'  
                                    }  
                                }  
                            },   
                           // data:[null,10, 34,28 , 35, 16, 30,38,50,34,54,74,58]  
                        }  
                    ]  
                });  
         	  var orderNums=[]; 
         	
            $.ajax({
         type : "get",
         async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
         url : "${pageContext.request.contextPath}/driver/orderNum.do?type=1",    //请求发送到TestServlet处
         data : {},
         dataType : "json",        //返回数据形式为json
         success : function(result) {
             //请求成功时执行该函数内容，result即为服务器返回的json对象
             if (result.status==200) {
             		orderNums.push(null);
             		$.each(result.data,function(index,orderNum){
             			orderNums.push(orderNum.num);    //挨个取出类别并填入类别数组
				    })
                    myChart.hideLoading();    //隐藏加载动画
                    myChart.setOption({        //加载数据图表
                        series: [{
                            // 根据名字对应到相应的系列
                          //  name: '销量',
                            data: orderNums
                        }]
                    });
                    
             }
         
        },
         error : function(errorMsg) {
		         //请求失败时执行该函数
		         alert("图表请求数据失败!");
		         myChart.hideLoading();
        }
    })
         });
            });
     
            
</script>
</html>