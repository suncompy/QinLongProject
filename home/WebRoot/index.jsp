<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
	<title>欢迎页</title>
	<link rel="stylesheet" href="./css/style.css">
</head>
  
<body>
    <div class='os-choose-container'>
        <div class='os-choose-top-box clearfix'>
            <div class='os-choose-top'>
                <div class='os-choose-logo-box f-l clearfix'>
                    <div class='logo-img f-l'>
                        <img src="./img/LOGO.png" alt="">
                    </div>
                    <div class='logo-content f-l'>
                        <p class='p-1'>系统选择</p>
                        <p class='p-2'>新疆秦龙物流有限公司</p>
                    </div>
                </div>
            </div>
        </div>
        <div class='os-choose-content-box'>
            <div class='os-choose-content'>
                <div class='os-choose-content-top'>
                    <div class='os-c-t-center'>
                        <div class='os-c-t-center-img-box'>
                            <img src="./img/logo2.png" alt="">
                        </div>
                        <h2>物流管理平台</h2>
                        <button onclick="pageforward('manage');">立即进入</button>
                    </div>
                </div>
                <div class='os-choose-content-bottom clearfix'>
                    <div class='os-choose-content-b-l f-l'>
                        <div class='os-c-t-center-2'>
                            <h2>车辆管理平台</h2>
                            <button onclick="pageforward('carmanage');">立即进入</button>
                        </div>
                    </div>
                    <div class='os-choose-content-b-r f-r'>
                        <div class='os-c-t-center-2'>
                            <h2>项目展示平台</h2>
                            <button onclick="pageforward('project');">立即进入</button>
                        </div>
                    </div>
                </div>
            </div>
           
        </div>
    </div>
    <script>
       window.onload=function(){
            changeDivHeight();
        }
        //当浏览器窗口大小改变时，设置显示内容的高度
        window.onresize=function(){
            changeDivHeight();
        }
        function changeDivHeight(){
            var h = document.documentElement.clientHeight;//获取页面可见高度
            console.log(h);
            document.querySelector(".os-choose-content-box").style.height=h-100+"px";
        }
        function pageforward(projecttype){
        	if(projecttype == 'carmanage'){
        		//alert("敬请期待");
        		location.href="${pageContext.request.scheme}${'://'}${pageContext.request.serverName}${':'}${pageContext.request.serverPort}/carmanage/user/tologin";
        	}else if(projecttype == 'project'){
        		alert("敬请期待");
        		//location.href='http://localhost:8080/logistics-project-web/login'
        	}else{
        		location.href="${pageContext.request.scheme}${'://'}${pageContext.request.serverName}${':'}${pageContext.request.serverPort}/logistics-manage-web/login.do";
        	}
        }
    </script>
</body>
</html>
