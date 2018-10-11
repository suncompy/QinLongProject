//# sourceURL=common.js
;
(function($) {

    'use strict';
    $(function() {
    	
    	if (window.history && window.history.pushState) {
            $(window).on('popstate', function () {
                     // 当点击浏览器的 后退和前进按钮 时才会被触发， 
                window.history.pushState('forward', null, ''); 
                window.history.forward(1);
            });
        }
    	
    	$.fd = $.fd || {};
    	/************* ajax *****************/
    	// 对服务端返回的信息，首先解析，查看是否为session timeout
        (function() {
            //备份jquery的ajax方法  
            var __ajax = $.ajax;
            //重写jquery的ajax方法  
            $.ajax = function(opt) {
                var _complete = opt && opt.complete || function(a, b) {};
                var _opt = $.extend(opt, {
                    complete: function(XHR, TS) {
                        var resText = XHR.responseText;
                        var sessionstatus = XHR.getResponseHeader("sessionstatus");
                        if (9999 == XHR.status && "timeout" == sessionstatus) {
                        	if($.confirm.timeout && $.confirm.timeout == 1){
                        		return;
                        	}
                        	$.confirm.timeout = 1;
                        	
                            $.confirm({
                                content: '您的登录时间过长或长时间没有操作, 5秒钟后将自动登出...',
                                autoClose: 'confirm|5000',
                                confirm: function() {
                                	$.fd.logout();
                                },
                                cancel :function(){
                                	$.confirm.timeout = 0;
                                }
                            });
                            return;
                        }else{
                        	_complete(XHR, TS);
                        }
                    }
                });
                return __ajax(_opt);
            };
        })();
        
       // 标签内容高度
		$.fd.bodyHeight = $("#contentWrapperBody").height()-50;
		$.fd.getHeight = function(offset){
		    if(offset){
		    	return $.fd.bodyHeight + offset;
		    }	
		    return $.fd.bodyHeight;
		}
		
        // ajax
        $.fd.ajax = function(options) {
            var defaults = {
                type: "post",
                dataType: "json",
                contentType: "application/json"
            };

            var success = options.success;
            options.success = function(XHR, TS) {
            	if (options.showMsg) {
                    if (XHR.message) {
                        $.fd.msg.success(XHR.message);
                    } else {
                        $.fd.msg.success(TS);
                    }
                }
                success(XHR, TS);
            };
            options.error = function(XHR, TS) {
            	if(9999 == XHR.status){
            		return;
            	}
                if (XHR.responseJSON) {
                    $.fd.msg.error(XHR.responseJSON.message);
                } else {
                    $.fd.msg.error(XHR.statusText);
                }
            }

            var settings = $.extend({}, defaults, options);

            // 如果数据为json对象，转为字符串
            var type = settings.type.toLowerCase();
            if ((type == "post" || type == "put") && (jQuery.isPlainObject(settings.data) || jQuery.isArray(settings.data))) {
                settings.data = JSON.stringify(settings.data);
            }

            if (type == "jsonget" || type == "get") {
                settings.type = "get";
                settings.cache = false;
                if (options.data) {
                    var pageQueryData = {};
                    $.each(options.data, function(name, value) {
                        if (jQuery.isPlainObject(value) || $.isArray(value)){
                            pageQueryData[name] = JSON.stringify(value);
                        } else {
                            pageQueryData[name] = value;
                        }
                    });

                    settings.data = pageQueryData;
                }
            } else if (type == "batchdel") {
                settings.type = "delete";
                if (options.data) {
                    var batchDelData = {};
                    $.each(options.data, function(name, value) {
                        if ($.isPlainObject(value) || $.isArray(value)) {
                            batchDelData[name] = JSON.stringify(value);
                        } else {
                            batchDelData[name] = value;
                        }
                    });
                    settings.url += ((/\?/).test(settings.url) ? "&" : "?") + jQuery.param(batchDelData);
                    delete settings["data"];
                }
            }

            $.ajax(settings);
        };
        
        // 画面唯一Id
        $.fd.guid = function() {
            var s4 = function() {
                return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
            };
            return ("G" + s4() + s4() + s4() + s4());
        };        

        /* 支持拖拽
        $.fn.fdDraggable = function() {
            this.draggable({
                handle: ".modal-header",
                cursor: 'move',
                refreshPositions: false
            });
        };*/
        
        $.fd.logout = function(){
        	// 单点登录-退出
        	// window.location.href = "http://192.168.0.200:8090/cas/logout?service=http://192.168.0.200:8090/fd-court-cube/login";
        	window.location.href = "login";
        }

        // 画面间传值
        $.fd.session = {};
        $.fd.session.set = function(data){
        	$.fd.session.data = $.extend({},data);
        };
        $.fd.session.get = function(){
        	var data = $.fd.session.data;
        	delete $.fd.session.data;
        	return data;
        }
        // 保存用户信息，供其他页面使用
        $.fd.session.user = {};

        /************ 数字格式化和计算 *********************/
        $.fd.parseFloat = function(num) {
            if ($.type(num) == "string") {
                num = num.replace(/,/g, '');
            }
            return parseFloat(num);
        }

        $.fd.formatNumber = function(num, precision, separator) {

            if ($.type(num) == "string") {
                num = num.replace(/,/g, '');
            }

            var parts;
            // 判断是否为数字
            if (!isNaN(parseFloat(num)) && isFinite(num)) {
                // 把类似 .5, 5. 之类的数据转化成0.5, 5, 为数据精度处理做准, 至于为什么
                // 不在判断中直接写 if (!isNaN(num = parseFloat(num)) && isFinite(num))
                // 是因为parseFloat有一个奇怪的精度问题, 比如 parseFloat(12312312.1234567119)
                // 的值变成了 12312312.123456713
                num = Number(num);
                // 处理小数点位数
                num = (typeof precision !== 'undefined' ? num.toFixed(precision) : num).toString();
                // 分离数字的小数部分和整数部分
                parts = num.split('.');
                // 整数部分加[separator]分隔, 借用一个著名的正则表达式
                parts[0] = parts[0].toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1' + (separator || ','));

                return parts.join('.');
            }
            return NaN;
        };

        $.fd.numMulti = function(num1, num2) {
            num1 = $.fd.parseFloat(num1);
            num2 = $.fd.parseFloat(num2);

            var baseNum = 0;
            try {
                baseNum += num1.toString().split(".")[1].length;
            } catch (e) {}
            try {
                baseNum += num2.toString().split(".")[1].length;
            } catch (e) {}
            return Number(num1.toString().replace(".", "")) * Number(num2.toString().replace(".", "")) / Math.pow(10, baseNum);
        };
        
        $.fd.numDiv = function(arg1,arg2){
        	var t1=0,t2=0,r1,r2;
        	
        	try{t1=arg1.toString().split(".")[1].length}catch(e){} 
        	try{t2=arg2.toString().split(".")[1].length}catch(e){}
        	 
        	r1=Number(arg1.toString().replace(".",""));
        	r2=Number(arg2.toString().replace(".","")); 
        	return (r1/r2) * Math.pow(10,t2-t1); 
       };
        
        /**************** message *********************/
        $.fd.msg = {};

        $.fd.msg.success = function(msg) {
        	Tips.alert({ message: msg }).on(function (e) {});
        };

        $.fd.msg.error = function(msg) {
        	Tips.alert({ message: msg }).on(function (e) {});
        };

        $.fd.msg.notice = function(msg) {
        	Tips.alert({ message: msg }).on(function (e) {});
        };

        $.fd.msg.warning = function(msg) {
        	Tips.alert({ message: msg }).on(function (e) {});
        };
        
        jconfirm.defaults = {
                title: '请确认',
                content: '请确认是否继续?',
                contentLoaded: function() {},
                icon: '',
                confirmButton: '确认',
                cancelButton: '取消',
                confirmButtonClass: 'btn-primary',
                cancelButtonClass: 'btn-danger',
                theme: 'white',
                animation: 'zoom',
                closeAnimation: 'scale',
                animationSpeed: 400,
                animationBounce: 1.2,
                keyboardEnabled: false,
                rtl: false,
                confirmKeys: [13, 32], // ENTER or SPACE key
                cancelKeys: [27], // ESC key
                container: 'body',
                confirm: function() {},
                cancel: function() {},
                backgroundDismiss: false,
                autoClose: false,
                closeIcon: null,
                columnClass: 'col-md-6 col-md-offset-3',
                onOpen: function() {},
                onClose: function() {},
                onAction: function() {}
            };

        /****************** form **********************/
        // 表单中的数据转为json对象
        $.fn.serializeJson = function() {
            var serializeObj = {};
            var array = this.serializeArray();
            var str = this.serialize();
            $(array).each(
                function() {
                    if (serializeObj[this.name]) {
                        if ($.isArray(serializeObj[this.name])) {
                            serializeObj[this.name].push(this.value);
                        } else {
                            serializeObj[this.name] = [
                                serializeObj[this.name], this.value
                            ];
                        }
                    } else {
                        serializeObj[this.name] = this.value;
                    }
                });

            return serializeObj;
        };

        // json数据绑定到表单
        $.fn.loadJson = function(data) {
            var form = $(this);
            $.each(data, function(name, value) {
                var o = form.find("[name='" + name + "']");
                if (o.length > 0) {
                	o.val(value);
                }
            });
        };
        
        // json数据绑定到表单
        $.fn.loadJson2Text = function(data) {
            var form = $(this);
            $.each(data, function(name, value) {
                var o = form.find("[name='" + name + "']");
                if (o.length > 0) {
                	o.text(value);
                }
            });
        };

        // 重置表单内容
        $.fn.reset = function() {
            // 通过调用 DOM 中的reset方法来重置表单
        	 this[0].reset();
        	 if(this.bootstrapValidator){
        		// 验证结果重置
    	        this.bootstrapValidator('resetForm');
        	 }
        };
        
        $.fn.resetText = function(){
        	this.find(".view span").each(function(i,e){
    		   if($(e).attr("name")){
    			   $(e).text("");
    		   }
    		});
        };

        /************** select2 ***********************/
        // 解决下拉框select2在modal中无法获得焦点的问题
        $.fn.modal.Constructor.prototype.enforceFocus = function() {};
        
        $.fd.select2 = $.fd.select2 || {};
        
        $.fd.select2.getText = function(opt) {
            var selector, language;
            if ($.type(opt) == "string") {
                selector = opt;
            } else {
                selector = opt.selector;
                language = opt.language;
            }
            if ($(selector).select2('data') && $(selector).select2('data')[0]) {
                var texts = $(selector).select2('data')[0].text.split("|");
                if (texts.length == 2 && language == "cn") {
                    return texts[1].replace(/(^\s*)|(\s*$)/g, '');
                }
                return texts[0];
            }
            return "";
        };

        $.fd.select2.getTexts = function(selector) {
            var texts = [];
            if ($(selector).select2('data')) {
                var datas = $(selector).select2('data');
                for (var i = 0, len = datas.length; i < len; i++) {
                    if ($(selector).select2('data')[i]) {
                        texts.push($(selector).select2('data')[i].text.split("|")[0]);
                    }
                }
            }
            return texts;
        };
        
        /************** file ***********************/
        $.fd.file = $.fd.file || {};
        // 文件大小格式化
        $.fd.file.formatFileSize = function (bytes) {
            if (typeof bytes !== 'number') {
                return '';
            }
            if (bytes >= 1000000000) {
                return (bytes / 1000000000).toFixed(2) + ' GB';
            }
            if (bytes >= 1000000) {
                return (bytes / 1000000).toFixed(2) + ' MB';
            }
            return (bytes / 1000).toFixed(2) + ' KB';
        };

        /************ DataTables ************/
        $.fd.DataTable = $.fd.DataTable || {};
        
        $.fd.DataTable.getPageOpts = function(data) {
            var opts = {};
            opts.start = data[3].value;
            opts.length = data[4].value;
            return opts;
        }
        $.fd.DataTable.loadPageOpts = function(opts,data) {
            data[3].value = opts.start;
            data[4].value = opts.length;
            return data;
        }
        
        $.fd.DataTable.ajax = function(source, data, callback, condition){
        	// 分页信息
			var queryData = $.fd.DataTable.getPageOpts(data);
			// 检索条件
			queryData.condition = condition || {};

			$.fd.ajax({
				type : "jsonGet",
				url : source,
				data : queryData,
				"success" : function(resp) {
					callback(resp.data);
				}
			});
        };
        
        // 语言
        $.extend(true, $.fn.dataTable.defaults, {
            language: {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "第 _START_ 至 _END_ 项，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "iDisplayLength":25,
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            }
        });
        
        // 配置
        $.extend($.fn.dataTable.defaults, {
            searching: false,
            ordering: false
        });

        $.extend({
        	alert:function(){
        		Tips.alert({ message: msg }).on(function (e) {});
            },
            dialog:function(){
            	Tips.dialog({ message: msg }).on(function (e) {});
            },
        	confirm:function(msgObj,opts){
        		Tips.confirm(msgObj).on(function (e) {  
                    if (!e) {return;}  
                    $.fd.ajax({url:opts.surl,type:opts.type,data:opts.data,showMsg:opts.showMsg,success:opts.success});
                });
            }
        })
        
        /************ DatePicker ***********
        // 配置
        $.extend(true, $.fn.datepicker.defaults,{
    		autoclose: true,
    		clearBtn: true,
    		format: 'yyyy/m/dd',
    		language: 'zh-CN',
    		todayHighlight: true,
    		multidate: 0
    	});*/
        
        /*********** validator *****************/
        $.extend(true, $.fn.bootstrapValidator.DEFAULT_OPTIONS, {
//            feedbackIcons: {
//                valid: 'glyphicon glyphicon-ok',
//                invalid: 'glyphicon glyphicon-remove',
//                validating: 'glyphicon glyphicon-refresh'
//            },
            group: '.validation-group'
        });
        
        // 下载
        $.fd.download = function(data, url) {
          var url = url || "api/attachments/owners/batch";
          if($.type(data) == "string"){
        	  url = data;
        	  data = {};
          }
          if( url && data ){
            var inputs = '';
              $.each(data, function(name, value) {
                  if ($.isPlainObject(value) || $.isArray(value)){
                      inputs += '<input type="hidden" name="'+ name +'" value='+ encodeURI(JSON.stringify(value)) +' />';
                  } else {
                    inputs += '<input type="hidden" name="'+ name +'" value='+ encodeURI(value) +' />';
                  }
              });
              
              // request发送请求
              $('<form action="'+ url +'" method="get">'+ inputs +'</form>')
              .appendTo('body').submit().remove();
          };
        };
        	
    });

})(jQuery);
