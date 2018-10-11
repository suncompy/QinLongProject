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
	//财务
	$("body").on('click', '#finance', function() {
		var a=$("#ul_finance li:first-child ul li:first-child a").attr("href");
		$("#contentIframe").attr("src",a);
		$(".left_nav").hide();
		if($("#ul_finance li:first-child ul").is(":hidden")){
		       $("#ul_finance li:first-child ul ").show();    //如果元素为隐藏,则将它显现
		}else{
		      $("#ul_finance li:first-child ul").hide();     //如果元素为显现,则将其隐藏
		}
		$("#ul_finance li:first-child .left_nav").css("height", "150px");
		$("#ul_finance li:first-child .card-link").addClass("navBg").parent().siblings().find(".card-link").removeClass("navBg");
		$("#ul_finance li:first-child .iconClass").addClass("arrowDowm").addClass("arrowDowm").parent().parent().siblings().find(".iconClass").addClass("arrow").removeClass("arrowDowm");
		$("#11 li:first-child a").addClass("leftClass").parent().siblings().find("a").removeClass("leftClass").parent().parent().parent().siblings().children().find("li").children().removeClass("leftClass");
	});
	//设置
	$("body").on('click', '#manage', function() {
		var a=$("#ul_manage li:first-child ul li:first-child a").attr("href");
		$("#contentIframe").attr("src",a);
		$("#ul_manage li:first-child .left_nav").css("height", "70px");
		$(".left_nav").hide();
		if($("#ul_manage li:first-child ul").is(":hidden")){
		       $("#ul_manage li:first-child ul").show();    //如果元素为隐藏,则将它显现
		}else{
		      $("#ul_manage li:first-child ul").hide();     //如果元素为显现,则将其隐藏
		}
		$("#ul_manage li:first-child .card-link").addClass("navBg").parent().siblings().find(".card-link").removeClass("navBg");
		$("#20 li:first-child a").addClass("leftClass").parent().siblings().find("a").removeClass("leftClass").parent().parent().parent().siblings().children().find("li").children().removeClass("leftClass");
		$("#ul_manage li:first-child .iconClass").addClass("arrowDowm").parent().parent().siblings().find(".iconClass").addClass("arrow").removeClass("arrowDowm");
	});
	//业务
	$("body").on('click', '#business', function() {
		$("#contentIframe").attr("src","businessHome.do");
		$("#ul_business li .left_nav").parent().siblings().find("ul").removeClass("in");
		$("#ul_business li .card-link").addClass("navBg").parent().siblings().find(".card-link").removeClass("navBg");
		$("#ul_business li .iconClass").parent().parent().siblings().find(".iconClass").addClass("arrow").removeClass("arrowDowm");
	});
	//左侧菜单折叠展开
	$("body").on('click', '.card-link', function() {
		$(this).addClass("navBg").parent().siblings().children().removeClass('navBg');
		$(this).parent().children('ul').slideToggle(500).parent().siblings().children('ul').slideUp(500);
		$(this).children("em").toggleClass('arrowDowm').parent().parent().siblings().children("a").find('em').removeClass("arrowDowm")

	});
//	自适应表格
	$("table").parent().addClass("table-responsive");
	
		$("box-tools button:not(.pull-right)").click(function(){
		$(this).addClass("tableActive").siblings().removeClass("tableActive")
	})
	


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
//页面刷新
	$("body").bind("keydown",function(e){  
        if (e && (e.keyCode == 116 || (e.ctrlKey && e.keyCode == 82))) {  
                  e.preventDefault(); //阻止默认刷新  
                  document.querySelector(".leftClass").click();
                  $('#showMask', window.parent.document).hide();
      }  
    }) 
    
    var iframe = document.getElementById("contentIframe");
	iframe.src = iframe.src;
	if (iframe.addEventListener){
	    iframe.addEventListener("onload", function(){
	        alert("Local iframe is now loaded1.");
	    });
	} else {
	    iframe.onload = function(){      
	        var action =  $(".leftClass").attr("href");
	        if(!action){
	        	return false;
	        }
			 $.fd.ajax({
					url : "api/initPage",
					type : "post",
					data : {action:action},
					success : function(d) {
						if(!d || !d.data){
							return false;
						}
						var data = d.data;
						for(var i=0;i<data.length;i++){
							$("#contentIframe").contents().find("#"+data[i].url).hide();
						}
					}
				});
	    };
	}
});

//页面刷新   
//window.onbeforeunload = function() {
//return false;
//}