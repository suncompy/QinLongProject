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




var GLOBAL = {
		typeOpts : {
			role:{
				ajax: {
					url : "api/basicdata/types/roles"
				}
			},

			//案号
			caseId:{
				ajax: {
					url : "api/basicdata/types/caseId"
				}
			},
			//单位
			company:{
				ajax: {
					url : "api/basicdata/types/company"
				},
				minimumResultsForSearch: 0
			},
			//部门
			depart:{
				ajax: {
					url : "api/basicdata/types/depart/ids/"
				},
				minimumResultsForSearch: 0
			},
			//职务
			workDuty:{
				ajax: {
					url : "api/basicdata/dims/1018"
				},
				minimumResultsForSearch: 0
			},
			//案件类型
			caseType:{
				ajax: {
					url : "api/basicdata/types/caseType"
				}
			},
			//所属法院
//			court:{
//				ajax: {
//					url : "api/basicdata/types/roles"
//				}
//			},
			//专业
			major:{
				ajax: {
					url : "api/basicdata/dims/1008"
				},
				cache : false,
//				minimumInputLength:0,
				minimumResultsForSearch: 0
			},
			//陪审员姓名
			jurymanName:{
				ajax: {
					url : "api/basicdata/types/jurymanName"
				},
				minimumResultsForSearch: 0
			},
			// 干部人员编号
			leaderNo:{
				ajax: {
					url : "api/basicdata/types/leaderNo"
				},
				minimumResultsForSearch: 0
			},
			// 系统用户编号
			sysUserId:{
				ajax: {
					url : "api/basicdata/types/sysUserId"
				},
				minimumInputLength:2,
				minimumResultsForSearch: 0
			},
			//自然数
			number:{
				ajax: {
					url : "api/basicdata/dims/1017"
				}
			},

			//民族
			nationType:{
				ajax: {
					url : "api/basicdata/dims/1001",
				},
				minimumResultsForSearch: 0
			},	
			
			//学历
			degreeType:{
				ajax: {
					url : "api/basicdata/dims/1003"
				}
			},	
			
			//党派
			partyType:{
				ajax: {
					url : "api/basicdata/dims/1005"
				}
			},	
			
			//职业
			jobType:{
				ajax: {
					url : "api/basicdata/dims/1006"
				}
			},	
			
			//状态-陪审员
			statusCType:{
				ajax: {
					url : "api/basicdata/dims/1011"
				}
			},	
			
			//单位性质
			companyType:{
				ajax: {
					url : "api/basicdata/dims/1007"
				}
			},	
			
			//职务
			jobDutyType:{
				ajax: {
					url : "api/basicdata/dims/1003"
				}
			},	
			
			//专业
			specialtyType:{
				ajax: {
					url : "api/basicdata/dims/1008"
				}
			},	
			
			//来源方式
			sourceType:{
				ajax: {
					url : "api/basicdata/dims/1009"
				}
			},	
			
			//人员类型
			identityType:{
				ajax: {
					url : "api/basicdata/dims/1010"
				}
			},	
			
			//性别
			sexType:{
				ajax: {
					url : "api/basicdata/dims/1002"
				}
			},	
			
			//婚姻
			marriageType:{
				ajax: {
					url : "api/basicdata/dims/1004"
				}
			},
			
			//政治面貌
			partyStyleType:{
				ajax: {
					url : "api/basicdata/dims/1014"
				}
			},
			
			/*********法官培训平台***********/
			//教师类型
			teacherType:{
				ajax: {
					url : "api/basicdata/dims/1004"
				}
			},
			//老师评价
			teacherScoreType:{
				ajax: {
					url : "api/basicdata/dims/1005"
				}
			},
			//报名方式
			registerType:{
				ajax: {
					url : "api/basicdata/dims/1006"
				}
			},
			//培训班类型
			trainClassType:{
				ajax: {
					url : "api/basicdata/dims/1007"
				}
			},
			//报名日期时间段
			arriveTimeType:{
				ajax: {
					url : "api/basicdata/dims/1008"
				}
			},
			//授课方式
			teachType:{
				ajax: {
					url : "api/basicdata/dims/1009"
				}
			},
			//审核状态
			checkStatus:{
				ajax: {
					url : "api/basicdata/dims/1010"
				}
			},
			//政治面貌
			politicalStatus:{
				ajax: {
					url : "api/basicdata/dims/1014"
				}
			},
			//状态-内容
			contentStatus:{
				ajax: {
					url : "api/basicdata/types/contentStatus"
				}
			},
			//报名中的培训班名称
			applyClassName:{
				ajax: {
					url : "api/basicdata/types/applyClassName"
				},
				minimumResultsForSearch: 0
			},
			//所有培训班名称
			groupClassName:{
				ajax: {
					url : "api/basicdata/types/groupClassName"
				},
				minimumResultsForSearch: 0
			},
			//可进行人员变更的培训班名称
			inTermTrainClassName:{
				ajax: {
					url : "api/basicdata/types/inTermTrainClassName"
				},
				minimumResultsForSearch: 0
			},
			//培训开始后的培训班名称
			statedTrainClassName:{
				ajax: {
					url : "api/basicdata/types/statedTrainClassName"
				},
				minimumResultsForSearch: 0
			},
			//计划培训班名称
			planTrainClassName:{
				ajax: {
					url : "api/basicdata/types/planTrainClassName"
				},
				minimumResultsForSearch: 0
			},
			//教师下所有培训班名称
			teacherGroupClassName:{
				ajax: {
					url : "api/basicdata/types/teacherGroupClassName"
				},
				minimumResultsForSearch: 0
			},
			//培训计划
			trainPlan:{
				ajax: {
					url : "api/basicdata/types/trainPlan"
				},
				minimumResultsForSearch: 0
			},
			//讲师
			teacher:{
				ajax: {
					url : "api/basicdata/types/teacher"
				},
				minimumResultsForSearch: 0
			},
			//班主任
			headmaster:{
				ajax: {
					url : "api/basicdata/types/headmaster"
				},
				minimumResultsForSearch: 0
			},
			//市中级法院
			court:{
				ajax: {
					url : "api/basicdata/types/court",
					contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				},
				minimumResultsForSearch: 0
			},
			//内容-栏目
			sectType:{
				ajax: {
					url : "api/basicdata/types/sectType"
				},
				minimumResultsForSearch: 0
			},
			//年份
			yearType:{
				ajax: {
					url : "api/basicdata/types/yearType"
				}
			},	
			//月份
			monthType:{
				ajax: {
					url : "api/basicdata/types/monthType"
				}
			},
			//报名状态
			checkStatusType:{
				ajax: {
					url : "api/basicdata/dims/1010"
				}
			},
			//升级级别
			upgradeLevel:{
				ajax: {
					url : "api/basicdata/dims/1031"
				}
			},
			//维度
			dimension:{
				ajax: {
					url : "api/basicdata/types/dimension"
				},
				minimumResultsForSearch: 0
			}
    	},
    	regexp:{
    		idcard:{
    			regexp: /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/,
                message: '请输入正确的身份证号码'
    		},
    		cellphone:{
    			regexp: /^1\d{10}$/,
                message: '请输入正确格式的手机号码'
    		},
    		telephone:{
    			regexp: /^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/,
                message: '请输入正确格式的固定电话号码'
    		}
    	}
};
