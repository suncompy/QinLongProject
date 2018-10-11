// 挂靠信息
	$(function() {
	var slideHeight = 100; // px 定义折叠的最小高度
	var defHeight = $('.wrap').height();
	if(defHeight >= slideHeight) {
		$('.wrap').css('height', slideHeight + 'px');
		$('.read-more').append('<a href="#">展开<i class="fa fa-chevron-down" aria-hidden="true"></i></a>');
		$('.read-more a').click(function() {
			var curHeight = $(this).parent().parent().next().next().height();
			if(curHeight == slideHeight) {
				$(this).parent().parent().next().next().animate({
					height: defHeight
				}, "normal");
				$(this).html('折叠<i class="fa fa-chevron-up" aria-hidden="true"></i>');
			} else {
				$(this).parent().parent().next().next().animate({
					height: slideHeight
				}, "normal");
				$(this).html('展开<i class="fa fa-chevron-down" aria-hidden="true"></i>');
			}
			return false;
		});
	}
});

// 申请挂靠公司信息

$(function() {
	var slideHeight = 100; // px 定义折叠的最小高度
	var defHeight = $('.wrap1').height();
	if(defHeight >= slideHeight) {
		$('.wrap1').css('height', slideHeight + 'px');
		$('.read-more1').append('<a href="#">展开<i class="fa fa-chevron-down" aria-hidden="true"></i></a>');
		$('.read-more1 a').click(function() {
			var curHeight = $(this).parent().parent().next().next().height();
			if(curHeight == slideHeight) {
				$(this).parent().parent().next().next().animate({
					height: defHeight
				}, "normal");
				$(this).html('折叠<i class="fa fa-chevron-up" aria-hidden="true"></i>');
			} else {
				$(this).parent().parent().next().next().animate({
					height: slideHeight
				}, "normal");
				$(this).html('展开<i class="fa fa-chevron-down" aria-hidden="true"></i>');
			}
			return false;
		});
	}
});


	// 申请挂靠车队信息
	$(function() {
	var slideHeight = 100; // px 定义折叠的最小高度
	var defHeight = $('.wrap2').height();
	if(defHeight >= slideHeight) {
		$('.wrap2').css('height', slideHeight + 'px');
		$('.read-more2').append('<a href="#">展开<i class="fa fa-chevron-down" aria-hidden="true"></i></a>');
		$('.read-more2 a').click(function() {
			var curHeight = $(this).parent().parent().next().next().height();
			if(curHeight == slideHeight) {
				$(this).parent().parent().next().next().animate({
					height: defHeight
				}, "normal");
				$(this).html('折叠<i class="fa fa-chevron-up" aria-hidden="true"></i>');
			} else {
				$(this).parent().parent().next().next().animate({
					height: slideHeight
				}, "normal");
				$(this).html('展开<i class="fa fa-chevron-down" aria-hidden="true"></i>');
			}
			return false;
		});
	}
});

	// 历史申请记录
	$(function() {
	var slideHeight = 100; // px 定义折叠的最小高度
	var defHeight = $('.wrap3').height();
	if(defHeight >= slideHeight) {
		$('.wrap3').css('height', slideHeight + 'px');
		$('.read-more3').append('<a href="#">展开<i class="fa fa-chevron-down" aria-hidden="true"></i></a>');
		$('.read-more3 a').click(function() {
			var curHeight = $(this).parent().parent().next().next().height();
			if(curHeight == slideHeight) {
				$(this).parent().parent().next().next().animate({
					height: defHeight
				}, "normal");
				$(this).html('折叠<i class="fa fa-chevron-up" aria-hidden="true"></i>');
			} else {
				$(this).parent().parent().next().next().animate({
					height: slideHeight
				}, "normal");
				$(this).html('展开<i class="fa fa-chevron-down" aria-hidden="true"></i>');
			}
			return false;
		});
	}
});