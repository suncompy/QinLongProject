/**
 * objToUrl
 * convert object to URLSTR
 */
function objToUrl(queryConditon) {
	var url = "";
	for (var colName in queryConditon) {
		url = url + "&" + colName + "=" + queryConditon[colName];
	}
	url = "?" + url.substring(1, url.length);
	return url;
}
function launchIntoFullscreen(element) { 
  if(element.requestFullscreen){ 
  element.requestFullscreen(); 
 }
 else if(element.mozRequestFullScreen) {
 element.mozRequestFullScreen(); 
 } 
else if(element.webkitRequestFullscreen) { 
  element.webkitRequestFullscreen(); 
 } 
else if(element.msRequestFullscreen) { 
  element.msRequestFullscreen(); 
 }
 }
 function myBrowser(){
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
    var isOpera = userAgent.indexOf("Opera") > -1;
    if (isOpera) {
        return "Opera"
    }; //判断是否Opera浏览器
    if (userAgent.indexOf("Firefox") > -1) {
        return "FF";
    } //判断是否Firefox浏览器
    if (userAgent.indexOf("Chrome") > -1){
  return "Chrome";
 }
    if (userAgent.indexOf("Safari") > -1) {
        return "Safari";
    } //判断是否Safari浏览器
    if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {
        return "IE";
    }; //判断是否IE浏览器
}

 function fullScreen(el) {
 
  //var el = document.documentElement;
 
  var rfs = el.requestFullScreen || el.webkitRequestFullScreen || 
 
      el.mozRequestFullScreen || el.msRequestFullScreen;
 
  if(typeof rfs != "undefined" && rfs) {
 
    rfs.call(el);
 
  } else if(typeof window.ActiveXObject != "undefined") {
 
    //for IE，这里其实就是模拟了按下键盘的F11，使浏览器全屏
 
    var wscript = new ActiveXObject("WScript.Shell");
 
    if(wscript != null) {
 
        wscript.SendKeys("{F11}");
 
    }
 
  }
}
//调用launchIntoFullscreen（full）就可以让这个div进入全屏了，进入全屏后还要退出全屏，退出全屏也要判断浏览器类型如下：
function exitFullscreen() {
 if(document.exitFullscreen) {
  document.exitFullscreen(); 
 } else if(document.mozCancelFullScreen) {
  document.mozCancelFullScreen(); 
  } else if(document.webkitExitFullscreen) { 
 document.webkitExitFullscreen(); 
 } 
 }
/**
*
*  Base64 encode / decode
*
*  @author haitao.tu
*  @date   2010-04-26
*  @email  tuhaitao@foxmail.com
*
*/
 
function Base64() {
 
	// private property
	_keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
 
	// public method for encoding
	this.encode = function (input) {
		var output = "";
		var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
		var i = 0;
		input = _utf8_encode(input);
		while (i < input.length) {
			chr1 = input.charCodeAt(i++);
			chr2 = input.charCodeAt(i++);
			chr3 = input.charCodeAt(i++);
			enc1 = chr1 >> 2;
			enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
			enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
			enc4 = chr3 & 63;
			if (isNaN(chr2)) {
				enc3 = enc4 = 64;
			} else if (isNaN(chr3)) {
				enc4 = 64;
			}
			output = output +
			_keyStr.charAt(enc1) + _keyStr.charAt(enc2) +
			_keyStr.charAt(enc3) + _keyStr.charAt(enc4);
		}
		return output;
	};
 
	// public method for decoding
	this.decode = function (input) {
		var output = "";
		var chr1, chr2, chr3;
		var enc1, enc2, enc3, enc4;
		var i = 0;
		input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
		while (i < input.length) {
			enc1 = _keyStr.indexOf(input.charAt(i++));
			enc2 = _keyStr.indexOf(input.charAt(i++));
			enc3 = _keyStr.indexOf(input.charAt(i++));
			enc4 = _keyStr.indexOf(input.charAt(i++));
			chr1 = (enc1 << 2) | (enc2 >> 4);
			chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
			chr3 = ((enc3 & 3) << 6) | enc4;
			output = output + String.fromCharCode(chr1);
			if (enc3 != 64) {
				output = output + String.fromCharCode(chr2);
			}
			if (enc4 != 64) {
				output = output + String.fromCharCode(chr3);
			}
		}
		output = _utf8_decode(output);
		return output;
	};
 
	// private method for UTF-8 encoding
	_utf8_encode = function (string) {
		string = string.replace(/\r\n/g,"\n");
		var utftext = "";
		for (var n = 0; n < string.length; n++) {
			var c = string.charCodeAt(n);
			if (c < 128) {
				utftext += String.fromCharCode(c);
			} else if((c > 127) && (c < 2048)) {
				utftext += String.fromCharCode((c >> 6) | 192);
				utftext += String.fromCharCode((c & 63) | 128);
			} else {
				utftext += String.fromCharCode((c >> 12) | 224);
				utftext += String.fromCharCode(((c >> 6) & 63) | 128);
				utftext += String.fromCharCode((c & 63) | 128);
			}
 
		}
		return utftext;
	};
 
	// private method for UTF-8 decoding
	_utf8_decode = function (utftext) {
		var string = "";
		var i = 0;
		var c = c1 = c2 = 0;
		while ( i < utftext.length ) {
			c = utftext.charCodeAt(i);
			if (c < 128) {
				string += String.fromCharCode(c);
				i++;
			} else if((c > 191) && (c < 224)) {
				c2 = utftext.charCodeAt(i+1);
				string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
				i += 2;
			} else {
				c2 = utftext.charCodeAt(i+1);
				c3 = utftext.charCodeAt(i+2);
				string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
				i += 3;
			}
		}
		return string;
	};
};

function loadForm(obj){
	obj.find('input').each(function() {
		$(this).val($.trim($(this).val()));
    });
}
/**
 *获取url参数 
 */
function getRequest(name) { 
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) 
		return unescape(r[2]); 
	return null; 
} 
/**
 * 显示隐藏
 */
var show = function(content,b){
  	if(b){
  		content.show();
  	}else{
  		content.hide();
  	}
};
var positiveInteger = /[^\d]/g;
/**
 * 正整数
 */
function positiveIntegerVal(obj,failture){
	if(obj.match(new RegExp(/^[0-9]*[1-9][0-9]*$/))==null){
		$.fd.msg.notice("只能输入正整数!");
		return obj.replace(positiveInteger, '').replace(000, '').replace(00, '').replace(0, '');
	}
	return obj;
}
