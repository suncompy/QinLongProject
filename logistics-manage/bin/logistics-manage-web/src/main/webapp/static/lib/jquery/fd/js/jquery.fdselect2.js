;
(function($, window, document, undefined) {
    'use strict';
    $(function() {    	
    	$.fd.select2.addTypes = function(types){
    		$.extend($.fd.select2.types, types);
    	};
    	var dims = $.getDims();
    	var typeOpts = $.getTypeOpts();
    	//var typeOpts = $.fd.select2.types;
    	
    	var _ajax = {
			dataType : 'json',
			processResults : function(d, params) {
				return {results : d.data};
			},
			data: function(term, page) {
				return {
					text: term.term
				};
			},
			cache : true
		};
		
		
		var opts_all;
    	
    	var defaults = {
			language: "zh-CN",
			allowClear: true,
			placeholder:'',
			minimumResultsForSearch: Infinity
		};
    	
    	var FdSelect2 = function(ele, options){
        	this.$element = ele;
        	this.options = options;
       	};

        
        FdSelect2.prototype = {
        	val: function(value){
        		var $select = this.$element;
        		if(!arguments.length){
        			return $select.val();
        		}
        		
        		if(!value){
        			$select.val(null).trigger('change');
        			return;
        		}
        		
        		if($.isPlainObject(value)){
        			if(!value.id && !value.text){
            			$select.val(null).trigger('change');
            			return;
            		}
        			
        			if(value.id && !value.text){
            			$select.empty().append($('<option selected></option>').val(value.id).text("")).trigger('change');
            			return;
            		}
        			
        			if(value.id && value.text){
            			$select.empty().append($('<option selected></option>').val(value.id).text(value.text)).trigger('change');
            			return;
            		}
        			
        		}else if($.isArray(value)){
        			$select.empty();
        			$.each(value, function(i, d){
        				$select.append(new Option(d.text, d.id, true, true));
					});
        			$select.trigger('change');
        		}
        	},
        	off: function(action){
        		return this.$element.off(action);
        	},
        	on: function(action, callback){
        		return this.$element.on(action, callback);
        	},
        	text: function(){
        		
        		var $select = this.$element;
        		
        		if ($select.select2('data') && $select.select2('data')[0]){
        		    return $select.select2('data')[0].text;
        		}
        		
        		return "";
        	},
        	
        	init: function(){
        		
        		var options = this.options, $select = this.$element;
        		if(!options){
        			return $select.select2(defaults);
        		}
        		// 维度数据
    			//var opts_all = $.extend({},defaults, typeOpts[options.type]);
    			//opts_all.ajax = $.extend({}, _ajax, typeOpts[options.type]['ajax'], options.ajax);
    			
    			// 维度数据
    			if(dims[options.type]){
    				opts_all = $.extend({},defaults, dims[options.type]);
    				opts_all.ajax = $.extend({}, _ajax, {url : "api/basicdata/dims/" + dims[options.type].code}, options.ajax);
    			}else{
    				var reqObj = options.url?{url : options.url}:typeOpts[options.type]['ajax'];
    				opts_all = $.extend({},defaults, typeOpts[options.type]);
    				opts_all.ajax = $.extend({}, _ajax, reqObj, options.ajax);
    			}	
        		
        		if(options.language){
        			opts_all.itemLanguage = options.language;
        			if(options.language == "en"){
        				opts_all.templateSelection = opts_all.templateSelection || _templateSelectionEn;
        			}else if(options.language == "cn"){
        				opts_all.templateSelection = opts_all.templateSelection || _templateSelectionCn;
        			}
        		} 
        		
        		if(options.allowClear === false){
        			opts_all.allowClear = options.allowClear;	
        		}
        		if(options.closeOnSelect === false){
        			opts_all.closeOnSelect= false;
        		}
        		
        		return $select.select2(opts_all);
        	}
        };
        
        $.fn.FdSelect2 = function(options) {
            var select2 = new FdSelect2(this, options);
            select2.init();
            return select2;
        };
    });
})(jQuery, window, document);
