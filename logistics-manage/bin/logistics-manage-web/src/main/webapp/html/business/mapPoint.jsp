<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>给多个点添加信息窗体</title>
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <script type="text/javascript"
            src="http://webapi.amap.com/maps?v=1.4.3&key=sfci50a7s86fiLfgSvijWYxv76a"></script>
    <!--  
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>-->
    <script src="${pageContext.request.contextPath}/static/js/invoke.js"></script>
    <!--  -->
	<script src="${pageContext.request.contextPath}/js/business/mapPoint.js"></script>
</head>
<body>
    <div class="panel panel-default" style="margin-top: -20px;">
         <div class="panel-body">
	  <form class="form-inline maxWidth" id="frmMapPointQuery">
	    <div class="form-group">
	        <label>车辆编号：</label>
	        <input name="carNo" type="text">
        </div>
		<div class="form-group"> 
            <button class="btn-xs btn-primary btn-query pull-right" id="btnMapPointQuery">查询</button>
	    </div>
	  </form>
        </div>
        
    </div>
<div id="container" style="margin-top: 40px;"></div>
</body>
<script type="text/javascript">
    //初始化地图对象，加载地图
    /*
 	var map = new AMap.Map("container", {
        resizeEnable: true,
        center: [114.306206, 38.975468],
 		zoom:5
    });
    var lnglats = [
        [116.368904, 39.923423],
        [116.382122, 39.921176],
        [116.387271, 39.922501],
        [116.398258, 39.914600],
        [104.747402,31.46131]
    ];
    var infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(0, -30)});
    for (var i = 0, marker; i < lnglats.length; i++) {
        var marker = new AMap.Marker({
            position: lnglats[i],
            map: map
        });
        marker.content = '我是第' + (i + 1) + '个Marker';
        marker.on('click', markerClick);
        marker.emit('click', {target: marker});
    }
    function markerClick(e) {
        infoWindow.setContent(e.target.content);
        infoWindow.open(map, e.target.getPosition());
    }
    
    map.setFitView();
    map.setZoom(5);*/
</script>
</html>