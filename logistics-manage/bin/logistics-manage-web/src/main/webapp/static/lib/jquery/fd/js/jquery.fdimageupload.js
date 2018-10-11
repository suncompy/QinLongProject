/**
 * 图片预览上传
 * haichao.sang
 */
;
(function($, window, document, undefined) {
    'use strict';
    $(function() {
		
    	var defaults = {
			dataType: 'json',
		    autoUpload: false,
		    acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
		    maxFileSize: 5242880,
		    previewMaxWidth: 240,
		    previewMinWidth: 240,
		    previewMaxHeight: 135,
		    previewCrop: false,
		    defaultImg:null
		};
    	
    	var FdImageupload = function(ele, options){
        	this.$element = ele;
        	this.options = options;
       	};
        
       	FdImageupload.prototype = {
   			submit: function(opts){
   				var $image = this.$element, data = $image.data();
   				if(data && data.submit){
   					data.url = "api/owners/"+ opts.ownerId +"/attachment/"+ opts.imageId;
   					data.submit().always(function () {
   						
   						if($image.next().is("canvas")){
   							$image.next().remove();
   						}
   						$image.removeData();
   						$image.show().attr("src","api/attachments/"+ opts.imageId +"/image?timestamp="+new Date().getTime());
   						
   						if(opts.imageEl && opts.imageEl.length > 0){
   							$.each(opts.imageEl, function(i, d){
   								$(d).attr("src","api/attachments/"+ opts.imageId +"/image");
   							});
   						}
			        });
   				}
   			},
   			show: function(opts){
   				var $image = this.$element, $opts = this.options;
   				
   				$image.show();
   				if(!opts || !opts.imageId){
   					if($opts.defaultImg){
   	   					$image.attr("src","api/attachments/default/"+ $opts.defaultImg+"/image");
   	   				}
   				}else if(opts.imageId){
   					$image.attr("src","api/attachments/"+ opts.imageId +"/default/"+ $opts.defaultImg+"/image?timestamp="+new Date().getTime());
   				}else if($opts.defaultImg){
   					$image.attr("src","api/attachments/default/"+ $opts.defaultImg+"/image");
   				}
   			},
        	init: function(){
        		var opts = $.extend({},defaults, this.options), $image = this.$element;
        		var fileupload = $image.attr("id")+"-fileupload";
        		$image.wrap('<span class="fileinput-button"></span>').after('<input id="' + fileupload +'" type="file" name="files[]">');
        		$("#"+fileupload).fileupload(opts).on('fileuploadadd', function (e, data) {
        			$image.hide();
        			$image.removeData().data(data);
        		}).on('fileuploadprocessalways', function (e, data) {
        		    var file = data.files[data.index];
        		    if($image.next().is("canvas")){
						$image.next().remove();
					}
        		    if($image.prev().is("div")){
						$image.prev().remove();
					}
        			if (file.error) {
	    				$image.before('<div style="color:red;height:80px;">'+ "请上传gif|jpeg|png类型的图片,大小5M." +'</div>');
	    	        }else{
	    	        	if (file.preview) {
	        		    	$image.after(file.preview);
	        		    }
	    	        }
        		});
        	}
        };
        
        $.fn.FdImageupload = function(options) {
            var imageupload = new FdImageupload(this, options);
            imageupload.init();
            return imageupload;
        };
    });
})(jQuery, window, document);
