/**
 * 文件上传
 * @author LiuJiefeng
 */
;
(function($, window, document, undefined) {
    'use strict';
    $(function() {
    	var defaults = {
			language: 'zh',  
	        uploadUrl: 'userMem/dddd', // you must set a valid URL here else you will get an error  
	        allowedFileExtensions : ['doc','xls','jpg', 'png','gif'],  
	        maxFileCount: 3   //同时最多上传3个文件  
	        //allowedFileTypes: ['image', 'video', 'flash'],  这是允许的文件类型 跟上面的后缀名还不是一回事  
	        //这是文件名替换
			//dataType: 'json',
		    //autoUpload: false,
		    //acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
		    //maxFileSize: 5242880
		    /*,
		    slugCallback: function(filename) {  
		    	return filename.replace('(', '_').replace(']', '_');  
	        }
	        */
		};
    	
    	var FileUpload = function(ele, options){
        	this.$element = ele;
        	this.options = options;
       	};
       	
       	FileUpload.prototype = {
       		init:function(){
       			var opts = $.extend({}, this.defaults, this.options),$elem = this.$element;
       			var elemName = $image.attr("id");
       			$("#"+elemName).fileinput(opts).on("fileuploaded", function (event, data, previewId, index) {  
       	         //top.location.href="login.jsp";  
       	         //alert(data.response.result)
       	         alert(data.response.meta.message)
       			});
       		}	
       	};
       	
    	$.fn.FileUpload = function(options) {
            var upload = new FileUpload(this, options);
            upload.init();
            return upload;
        };
    });
})(jQuery, window, document);