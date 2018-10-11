(function ($) {//function($)匿名函数
   $.getUrl = function(options) {
    return getRealPath()+"/"+ options;  
   } 
   function getRealPath(){
	   var curWwwPath=window.document.location.href;
	   var pathName=window.document.location.pathname;
	   var pos=curWwwPath.indexOf(pathName);
	   var localhostPath=curWwwPath.substring(0,pos);
	   var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	   var realPath=localhostPath+projectName;
	   return realPath;
	 }
})(jQuery);