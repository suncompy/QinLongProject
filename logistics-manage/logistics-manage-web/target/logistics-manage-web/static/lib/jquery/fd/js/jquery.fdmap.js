;
(function($, window, document, undefined) {
    'use strict';
    $(function() {
		
		var opts_all;
    	
    	var defaults = {
    		    title: {
    		        text: 'iphone销量',
    		        subtext: '纯属虚构',
    		        left: 'center'
    		    },
    		    tooltip: {
    		        trigger: 'item'
    		    },
    		    legend: {
    		        orient: 'vertical',
    		        left: 'left',
    		        data:['iphone3','iphone4','iphone5']
    		    },
    		    visualMap: {
    		        min: 0,
    		        max: 2500,
    		        left: 'left',
    		        top: 'bottom',
    		        text: ['高','低'],           // 文本，默认为数值文本
    		        calculable: true
    		    },
    		    toolbox: {
    		        show: true,
    		        orient: 'vertical',
    		        left: 'right',
    		        top: 'center',
    		        feature: {
    		            dataView: {readOnly: false},
    		            restore: {},
    		            saveAsImage: {}
    		        }
    		    },
    		    series: [
    		        {
    		            name: 'iphone3',
    		            type: 'map',
    		            mapType: '安徽',
    		            selectedMode : 'single',
    		            roam: false,
    		            label: {
    		                normal: {
    		                    show: true
    		                },
    		                emphasis: {
    		                    show: true
    		                }
    		            },
    		            data:[
    		                {name: '合肥市',value: 123 },
    		                {name: '淮北市',value: 123 },
    		                {name: '淮南市',value: 3423 }
    		            ]
    		        }
    		    ]
    		};
    	
    	var FdMap = function(ele, options){
        	this.$element = ele;
        	this.options = options;
       	};

        
       	FdMap.prototype = {
        	val: function(value){
        		
        	},
        	
        	text: function(){
        		
        		
        	},
        	
        	init: function(){
        		
        		var options = this.options, $select = this.$element;
        		var myChart = echarts.init($select[0]);
        		
        
        	}
        };
        
        $.fn.FdMap = function(options) {
            var map = new FdMap(this, options);
            return map.init();
            
        };
    });
})(jQuery, window, document);
