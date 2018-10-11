//# sourceURL=pd-msg.js
/**
 * 全局工具
 * @LiuJiefeng
 * @date 2017-07-06
 */
;
(function($) {
	'use strict';
	$(function() {
		var msgtip = function(msg,type) {
			var options = {
				text : msg,
				type : type,
				position : 'top-center'
			};
			return $().toastmessage('showToast', options);
		};
		$.extend({
			//projectName/url……
			msgSuccess: function(msg) { 
				return msgtip(msg,"success"); 
			},
			//projectName
			msgError: function(msg) { 
				return msgtip(msg,"error"); 
			},
			///rootPath
			msgNotice: function(msg) { 
				return msgtip(msg,"notice"); 
			},
			msgWarning: function(msg) { 
				return msgtip(msg,"warning"); 
			}
		});

	});
}(jQuery));
