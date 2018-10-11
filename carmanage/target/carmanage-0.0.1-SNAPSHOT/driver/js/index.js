//iframe自适应
function frameresize() {
		var winheight = $(window).height();
		var iframeheight = winheight-80;
		$('#MyIframe').css('height', iframeheight+'px');
	};
	if(window.attachEvent) {
		document.getElementById("MyIframe").attachEvent('onload', frameresize);
	} else {
		document.getElementById("MyIframe").addEventListener('load', frameresize, false);
	}
	$(window).resize(frameresize);
	frameresize();
$(function(){
//左侧导航
	$("body").on("click",".left_nav li a",function(){
		$(this).addClass('leftClass').parent().siblings().children().removeClass('leftClass').parent().parent().parent().siblings().children().find("a").removeClass('leftClass');
	  $(".card-link").removeClass('actives');
	});
	$("body").on("click",".card-link",function(){
		$(this).addClass('actives').parent().siblings().find(".card-link").removeClass('actives');
		$(".left_nav li a").removeClass('leftClass');
})
	});