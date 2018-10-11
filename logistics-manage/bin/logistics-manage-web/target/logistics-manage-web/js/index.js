$(function() {
	//头部导航切换
	$("body").on("click", ".top_nav_ul li a", function() {
		$(this).addClass('actives').parent().siblings().children().removeClass('actives');
	});
	//左侧导航
	$("body").on("click", ".left_nav li a", function() {
		$(this).addClass('leftClass').parent().siblings().children().removeClass('leftClass').parent().parent().parent().siblings().children().find("a").removeClass('leftClass');
		$(this).parent().parent().parent().siblings().children().find("a").removeClass('leftClass');
	});
	//第一级菜单除了第一个 其他的默认隐藏掉
	for(var i=0;i<arrFirstMenu.length;i++){
		 if(i > 0) {
			 $("#ul_"+arrFirstMenu[i]).hide();
	    	 $("."+arrFirstMenuClass[i]+"s").addClass(arrFirstMenuClass[i]).removeClass(arrFirstMenuClass[i] + "s");
		 } else {
			 $("#ul_"+arrFirstMenu[i]).show();
			 $("."+arrFirstMenuClass[i]).addClass(arrFirstMenuClass[i] + "s").removeClass(arrFirstMenuClass[i]);
		 }
	}
	//点击一级菜单
	$("#firstMenuUlId li").click(function() {
	    var clickLiId = this.id;
	    for(var i=0;i<arrFirstMenu.length;i++){
	    	if(clickLiId == arrFirstMenu[i]) {
			 	$("#ul_"+arrFirstMenu[i]).show();
			 	$("."+arrFirstMenuClass[i]).addClass(arrFirstMenuClass[i] + "s").removeClass(arrFirstMenuClass[i]);
	    	} else {
	    		$("#ul_"+arrFirstMenu[i]).hide();
	    		$("."+arrFirstMenuClass[i]+"s").addClass(arrFirstMenuClass[i]).removeClass(arrFirstMenuClass[i] + "s");
	    	}
		}
	});
	
	$(".card-link").click(function() {
		$(this).addClass("navBg").parent().siblings().children().removeClass('navBg');
		if($(this).next().hasClass('in')) {
			$(this).children("em").addClass('arrow').removeClass("arrowDowm");
		} else {
			$(this).children("em").addClass('arrowDowm').removeClass("arrow");
		}
	});
//iframe自适应
	function frameresize() {
		var winheight = $(window).height();
		var iframeheight = winheight - 70;
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