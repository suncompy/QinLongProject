var path = "http://192.168.0.88:8080/com.shenhesoft.footbath";
var token = "jbc2lDqZ3p/k5N6cCjicgz+gsrSBsVLnq592+FLTP9Fd9CDyOqabfx7asH+bseavzfcSIjC2hKLVgoXRRA35is5+MHT4f1LzyZ+zrTpbDBI=";
$(function() {
	//头部导航切换
	$("body").on("click",".top_nav_ul li a",function(){
		$(this).addClass('actives').parent().siblings().children().removeClass('actives');
	});
//var childs;
//	$.ajax({
//		url: path + "/base/menu.do",
//		headers: {
//			token: token
//		},
//		type: "GET",
//		dataType: "json",
//		success: function(data) {
//			if(data.state == 1) {					
//				$.each(data.obj, function(i, menu1) {
//					
//					var html = '<li class="topultxt" code="' + menu1.code + ' " id="num' + i + '"><em class="' + menu1.icon_class + '"></em><span>' + menu1.name + '</span></li>';
//					$(".top_nav_ul").append(html); //一级菜单	
//					
//					var ul1 = $('<ul class="slidermenu_ul ' + menu1.code + '" code1="' + menu1.code + '" ></ul>'); //二级菜单
//					$.each(menu1.childs, function(i, menu2) {
//						if(menu2.childs != null) {
//							var li = $('<li class="leftmenu"></li>');
//							var a = $('<a href="#" class="leftmenu_a">' + menu2.name + '</a>');
//							var em = $('<em class="left_em"></em>');
//							var ul3 = $('<ul class="no_circle" id="no_circle"></ul>'); // 三级菜单
//							$.each(menu2.childs, function(i, menu3) {
//								var a3 = $('<a href="#" class="no_circle_a no_circle_a1" code="' + menu1.code + "/" + menu2.code + "/" + menu3.code + '"></a>');
//								var li3 = $('<li class="no_circle_a_li" >' + menu3.name + '</li>');
//								a.append(em);
//								li.append(a);
//								a3.append(li3);
//								ul3.append(a3);
//								li.append(ul3);
//								ul1.append(li);
//							});
//						} else {
//							var li = $('<li class="leftmenu no_circle_a newli" code="' + menu1.code + "/" + menu2.code + '"></li>');
//							var a = $('<a href="#" class="leftmenu_a newli2" code="' + menu2.code + '">' + menu2.name + '</a>');
//							li.append(a);
//							ul1.append(li);
//						}
//						$(".left").append(ul1);
//					});
//				});
//			} else {
//				alert(data.msg);
//			}
//			
//
//		},
//
//	});
//	
//	$('body').on('click', '.no_circle_a1', function(e) {
//		$(this).addClass('activeb').siblings().removeClass('activeb');
//		$(".newli2").removeClass('activeb');
//
//	});
//	$('body').on('click', '.newli2', function(e) {
//		$(this).addClass('activeb').parent("li").siblings().children("a").removeClass('activeb');
//		$(".no_circle_a1").removeClass('activeb');
//		
//	});
//	
//
//	//左边切换效果
//	$('body').on('click', '.leftmenu_a', function(e) {
//		var nodes = $(this).next();
//		var w = $(this).children();
//		if(nodes.is(":hidden")) {
//			$(nodes).show().parents('.leftmenu').siblings('.leftmenu').children(".no_circle").hide();
//			$(w).addClass("left_em_yes");
//		} else {
//			$(nodes).hide(); //如果元素为显现,则将其隐藏
//			$(w).removeClass("left_em_yes");
//		}
//	});
//	$('body').on('click', '.topultxt', function(e) {
//		$('.sy').unbind();
//		var m = $(this).attr("code");
//		var s = $(".newli").attr("code");
//		$(".main1").hide();
//		$("." + m).show().siblings().hide();
//		$(".left").show();
//		$('.contant ').show();
////		$(".no_circle_a ,.no_circle_a1,.newli2 ").removeClass("activeb");
//		
//		$(this).addClass('actives').siblings().removeClass('actives');
//
//	});
//
//	//首页显示
//	$('body').on('click', '.sy', function(e) {
//		$(this).addClass("active");
//		$(".topultxt").removeClass("active");
//		if($(".main1").is(".visible")) {
//			$(".main1").hide();
//          
//
//		} else {
//			$(".main1").show();
//			$(".left").hide();
//			$('.contant ').hide()
//		}
//	});
//	//iframe显示
//	$('body').on('click', '.no_circle_a', function(e) {
//		var ur2 = $(this).attr("code") + ".html";
//		$('#contentIframe').attr('src', ur2);
//	});
//	//iframe自适应
	function frameresize() {
		var winheight = $(window).height();
		var iframeheight = winheight;
		$('#contentIframe').css('height', iframeheight + 'px');
	};
	if(window.attachEvent) {
		document.getElementById("contentIframe").attachEvent('onload', frameresize);
	} else {
		document.getElementById("contentIframe").addEventListener('load', frameresize, false);
	}
	$(window).resize(frameresize);
	frameresize();
});
