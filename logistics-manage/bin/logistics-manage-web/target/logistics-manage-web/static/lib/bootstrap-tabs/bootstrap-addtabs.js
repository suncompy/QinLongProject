//@ sourceURL=Addtabs.js
/**
 * Website: http://git.oschina.net/hbbcs/bootStrap-addTabs
 *
 * Version : 0.6
 *
 * Created by joe on 2016-2-4.
 */

$.fn.addtabs = function (options) {
    obj = $(this);
    Addtabs.options = $.extend({
        content: '', //直接指定所有页面TABS内容
        close: true, //是否可以关闭
        monitor: 'body', //监视的区域
        iframeUse: true, //使用iframe还是ajax
        iframeHeight: $(document).height() - 80, //固定TAB中IFRAME高度,根据需要自己修改
        method: 'init',
        callback: function () { //关闭后回调函数
        }
    }, options || {});


    $(Addtabs.options.monitor).on('click', '[data-addtab]', function () {
    	$("[data-addtab].active").removeClass("active");
    	$(this).addClass("active");
        Addtabs.add({
            id: $(this).attr('data-addtab'),
            title: $(this).attr('title') ? $(this).attr('title') : $(this).html(),
            content: Addtabs.options.content ? Addtabs.options.content : $(this).attr('content'),
            url: $(this).attr('url'),
            ajax: $(this).attr('ajax') ? true : false
        });
        
        //{"hotspot-type:xxxxx"}
    });

    $("#tabsList").on('click', '.close-tab', function (){
        var id = $(this).prev("a").attr("aria-controls");
        Addtabs.close(id);
    });
    $("#tabsList").on('click', '[data-toggle]', function (){
    	var url = $(this).attr("aria-controls").replace("tab_","");
    	$("[data-addtab].active").removeClass("active");
    	$("[data-addtab = '"+url+"']").addClass("active");
    });


    //obj.on('mouseover', '.close-tab', function () {
    //	$(this).removeClass('glyphicon-remove-circle').addClass('glyphicon-remove');
    //})

    //obj.on('mouseout', '.close-tab', function () {
    //	$(this).removeClass('glyphicon-remove').addClass('glyphicon-remove-circle');
    //})

    $(window).resize(function () {
        obj.find('iframe').attr('height', Addtabs.options.iframeHeight);
        Addtabs.drop();
    });

};

function isURL(id) {
	var strRegex = "^((https|http|ftp|rtsp|mms)://)?[a-z0-9A-Z]{3}\.[a-z0-9A-Z][a-z0-9A-Z]{0,61}?[a-z0-9A-Z]\.com|net|cn|cc (:s[0-9]{1-4})?/$";
	var re = new RegExp(strRegex);
	if (re.test(id)) {
		return true;
	} else {
		return false;
	}
}
function indexOf( s1,  s2, time){
        if(time == 1)
            return s1.indexOf(s2);
        else
            return s1.indexOf(s2, indexOf(s1, s2, time - 1) + 1);
    }

function isURLHttp(id) {
	
	if(id.length>4){
		var httpstr=id.substring(0,4);
		if(httpstr=='http'){
			var post = indexOf(id,"\/",2);
			id= id.substring(post,id.length);
			id = id.replace(/\//g, "");
			id = id.replace(/-/g, "");
			id = id.replace(/\./g, "");
		}
	}
	return id;
}
function toUrlId(id){
	return isURL(id)?id.replace(/\//g, ""):id;
}
window.Addtabs = {
    options:{},
    active: function(id){
    	$('#tabsList>.active:first').removeClass('active');
        obj.find('#tabsContent>.active:first').removeClass('active');
    	//激活TAB
        $("#tab_" + id).addClass('active');
        $("#" + id).addClass("active");
        $("[data-addtab = '"+(id.replace("tab_",""))+"']").addClass("active");
        Addtabs.drop();
    },
    activeAndRefresh: function(id){
    	var url = id.substring(4,id.length);
    	$.get(url, function (data) {
    		$("#" + id).empty().append(data);
    		Addtabs.active(id);
        });
    },
    add: function (opts) {
        var id = 'tab_' + isURLHttp(opts.id);
        var isExist = $("#" + id)[0]; 
        if(undefined!=opts.param && opts.param){
        	opts.url=opts.url+opts.param;
        }
        // 页签已存在，且再次打开前，需确认
        if(isExist && opts.needConfirm){
        	$.confirm({
                content: '<span style="color:#007dbc">'+opts.title+'</span>页面已经打开, 请确认是否刷新页签内容。',
                confirm: function() {
                	$.get(opts.url, function (data) {
                		$("#" + id).empty().append(data);
                		Addtabs.active(id);
                    });
                },
                cancel :function(){
                	Addtabs.active(id);
                }
            });
        	return false;
        }
        
        $('#tabsList>.active:first').removeClass('active');
        obj.find('#tabsContent>.active:first').removeClass('active');
        $("[data-addtab].active").removeClass("active");
        //如果TAB不存在，创建一个新的TAB
        if (!isExist) {
            //创建新TAB的title

            var title = $('<li>', {
                'role': 'presentation',
                'id': 'tab_' + id
            }).append(
                $('<a>', {
                    'href': '#' + id,
                    'aria-controls': id,
                    'role': 'tab',
                    'data-toggle': 'tab'
                }).html(opts.title)
            );

            //是否允许关闭
            //if (Addtabs.options.close) {
            if(opts.close||(undefined==opts.close &&Addtabs.options.close)){
                title.append($('<i>').addClass('close-tab fa fa-times-circle'));
            }
            //创建新TAB的内容
            var content = $('<div>', {
                'class': 'tab-pane',
                'id': id,
                'role': 'tabpanel'
            });

            //是否指定TAB内容
            if (opts.content) {
                content.append(opts.content);
            } else if ((Addtabs.options.iframeUse || opts.iframeUse) && !opts.ajax) {//没有内容，使用IFRAME打开链接
                content.append(
                    $('<iframe>', {
                        'class': 'iframeClass',
                        'height': Addtabs.options.iframeHeight,
                        'frameborder': "no",
                        'border': "0",
                        'src': opts.url
                    })
                );
            } else {
                $.get(opts.url, function (data) {
                    content.append(data);
                });
            }
            //加入TABS
            $('#tabsList').append(title);
            obj.children(".tab-content").append(content);
        } 
        
        //激活TAB
        $("#tab_" + id).addClass('active');
        $("#" + id).addClass("active");
        $("[data-addtab = '"+(id.replace("tab_",""))+"']").addClass("active");
        Addtabs.drop();
    },
    close: function (id) {
        //如果关闭的是当前激活的TAB，激活他的前一个TAB
        if ($("#tabsList").find("li.active").attr('id') == "tab_" + id && $("#tab_" + id).prev("[role]").length>0){
        	var prevId = $("#tab_" + id).prev().addClass('active').attr("id").replace("tab_tab_","");
            $("#" + id).prev().addClass('active');
            $("[data-addtab = '"+prevId+"']").addClass("active");
        }
        //关闭TAB
        $("#tab_" + id).remove();
        $("#" + id).remove();
        $("[data-addtab = '"+(id.replace("tab_",""))+"']").parent().removeClass("active");
        Addtabs.drop();
        Addtabs.options.callback();
    },
    drop: function () {
        element = $('#tabsList');
        //创建下拉标签
        var dropdown = $('<li>', {
            'class': 'dropdown pull-right hide tabdrop'
        }).append(
            $('<a>', {
                'class': 'dropdown-toggle',
                'data-toggle': 'dropdown',
                'href': '#'
            }).append(
                $('<i>', {'class': "glyphicon glyphicon-align-justify"})
            ).append(
                $('<b>', {'class': 'caret'})
            )
        ).append(
            $('<ul>', {'class': "dropdown-menu"})
        )

        //检测是否已增加
        if (!$('.tabdrop').html()) {
            dropdown.prependTo(element);
        } else {
            dropdown = element.find('.tabdrop');
        }
        //检测是否有下拉样式
        if (element.parent().is('.tabs-below')) {
            dropdown.addClass('dropup');
        }
        var collection = 0;

        //检查超过一行的标签页
        element.append(dropdown.find('li'))
            .find('>li')
            .not('.tabdrop')
            .each(function () {
                if (this.offsetTop > 0 || element.width() - $(this).position().left - $(this).width() < 54) {
                    dropdown.find('ul').append($(this));
                    collection++;
                }
            });

        //如果有超出的，显示下拉标签
        if (collection > 0) {
            dropdown.removeClass('hide');
            if (dropdown.find('.active').length == 1) {
                dropdown.addClass('active');
            } else {
                dropdown.removeClass('active');
            }
        } else {
            dropdown.addClass('hide');
        }
    }
};
