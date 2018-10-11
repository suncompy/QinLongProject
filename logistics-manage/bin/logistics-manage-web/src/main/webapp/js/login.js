//# sourceURL=login.js
/**
 * @title 登录
 * @description 登录
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 */
;
(function($) {
  'use strict'; 
  $(function() {

    //忘记密码模态框
	$("#btnLogin").click(function(){
	   $("#forgetId")[0].reset();
	   $("#myModal").modal();
	});
	//反审核
  $("#forgetId [name=btnSendCheckCode]").click(function(e) {
        e.preventDefault();
        var sysUser = $("#forgetId").serializeJson();
        var type = "post";
        var url = "api/sendCHkCode";

        $.fd.ajax({
            url : url,
            type : type,
            data : sysUser,
            success : function(d) {
                console.log(d);
            }
        });

        
   });
	$("#forgetId [name=btnForgetPassSubmit]").click(function(e) {
        e.preventDefault();
        var sysUser = $("#forgetId").serializeJson();
        var type = "post";
        var url = "api/resetPasswd";

        $.fd.ajax({
            url : url,
            type : type,
            showMsg:true,
            data : sysUser,
            success : function(d) {
                console.log(d);
            }
        });

        
   });
	
  });
}(jQuery));