;
(function($, window, document, undefined) {
    'use strict';
    $(function() {
	$.fn.FdRaty=function(options){
		var defaultOpts={
				hints:['1','2','3','4','5'],
				path:"app/lib/jquery/plugin/raty/img",
				starOff: 'star-off-big.png',
				starOn: 'star-on-big.png',
				size:24,
				score:0,
				cancel: true
		}
		
		var opts=$.extend({},defaultOpts);
		if(options&& options.score){
			$.extend(true,opts,{score:options.score})
		}
		
		this.raty(opts);
	}
    });
	
})(jQuery, window, document);