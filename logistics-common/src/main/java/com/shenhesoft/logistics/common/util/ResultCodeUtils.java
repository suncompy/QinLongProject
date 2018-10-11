package com.shenhesoft.logistics.common.util;
/**
 * @description:返回自定义错误码
 * 
 * @author shilvfei
 * 
 * @date 2017年12月4日
 */
public class ResultCodeUtils {

	//请求接口未定义
	public static final Integer UNDEFIND_COMMAND=0;
	
	//请求包格式不合法
	public static final Integer INVALID_REQUEST_FORMATTER=1;
	
	//数据库操作失败
	public static final Integer CONNECT_DB_FAIL=2;

	//"请求数据发生异常，请稍后重试"
	public static final Integer SERVICE_INNER_EXCEPTION=3;
	
	//"服务器数据库异常"
	public static final Integer SERVICE_DB_EXCEPTION=4;

	//"时间格式化异常"
	public static final Integer DATE_FORMMATE_FAIL=5;

	//"更新系统日志出错~"
	public static final Integer INSERT_APP_LOG=6;
	
	//数据请求超时~请稍后再试~
	public static final Integer REQUEST_MSG_FAIL=7;
	
	 // 注册接口相关
	
	/**
	 * 对不起，用户手机号已经存在了，请尝试更换手机号重试~
	 */
	public static final Integer ACCOUNT_EXIST=100101;
	/**
	 * "对不起，秦龙物流有点儿忙，请稍后再试~"
	 */
	public static final Integer REGISTER_EASEMOB_FAIL=100102;
	
	/**
	 * 对不起，操作异常，请稍后重试~
	 */
	public static final Integer ACCOUNT_INSERT_FAILED=100103;
	/**
	 * 密码MD5加密异常
	 */
	public static final Integer ACCOUNT_BUILD_PWD_FAILED=100104;
	/**
	 * 对不起，手机验证码不正确~
	 */
	public static final Integer REG_CAPTCHA_IN_ERROR=100105;
	/**
	 * 对不起，验证码已过期，请重新获取
	 */
	public static final Integer REG_CAPTCHA_IN_VOID=100106;
	/**
	 * 对不起，这个用户名已经被人使用了,请尝试更换注册用户名哦~
	 */
	public static final Integer ACCOUNT_LOGINNAME_EXIST=100107;
	/**
	 * 对不起，图片上传失败,请稍后再试
	 */
	public static final Integer IMG_ADD_FAIL=100108;
	/**
	 * 请把带*必填信息填上
	 */
	public static final Integer REG_CONTENT_EMPTY=100109;
	/**
	 * 两次输入的密码不一致
	 */
	public static final Integer REG_PASSWD_DISCORD=100110;
	/**
	 * 请勾选《用户注册协议》
	 */
	public static final Integer REG_USER_PROTOCOL=100111;
	/**
	 * 插入数据库异常
	 */
	public static final Integer REG_INSERT_DB_ERROR=100112;
	/**
	 * 车辆保险时间有误~
	 */
	public static final Integer REG_INSURANCEDATE_ERROR=100112;
	
	
	// 登录接口相关
	/**
	 * "对不起，用户不存在，请尝试重新登陆~"
	 */
	public static final Integer MEMBER_ISNOT_EXIST=100201;

	 /**
	 * "用户名或密码不正确"
	 */
	public static final Integer UNKNOWN_USERNAME=100202;
	/**
	 * 密码加密失败
	 */
	public static final Integer NO_SUCH_ALGORITHM=100203;
	/**
	 * "您的账号已被禁用，如有疑问请联系客服"
	 */
	public static final Integer FORBIDDEN_USERNAME=100204;
	/**
	 * 对不起，数据异常，请联系管理员~
	 */
	public static final Integer LOGIN_ERROR=100205;
	/**
	 *  "对不起，该用户不存在~"
	 */
	public static final Integer MEMBERORTHER_ISNOT_EXIST=100206;
	/**
	 * "对不起，更新登录时间失败~"
	 */
	public static final Integer UPDATE_LOAGINTIME_ERR=100207;
	/**
	 * "用户名或密码不能为空"
	 */
	public static final Integer LOAGIN_EMPTY=100208;

	// 用户修改密码接口相关
	 
	/**
	 * 对不起，请输入正确的原始密码~
	 */
	public static final Integer UPDATE_PWD_PERROR=100301;
	
	/**
	 * "用户名或密码不正确"
	 */
	public static final Integer UPDATE_PWD_ERROR=100302;
	/**
	 *  "密码加密失败"
	 */
	public static final Integer UPDATE_PWD_NO_SUCH_ALGORITHM=100303;

		
	//用户忘记密码接口相关
	/**
	 * "对不起，用户不存在，请尝试重新登陆~"
	 */
	public static final Integer FORGET_PWD_MEMBER_EXIST=100401;	 
	
	/**
	 * 用户名或密码不正确
	 */
	public static final Integer FORGET_PWD_ERROR=100402;	 
	/**
	 * 对不起，验证码不正确，请重新输入~
	 */
	public static final Integer CAPTCHA_IN_ERROR=100403;	 
	/**
	 * 对不起，验证码已过期，请重新获取
	 */
	public static final Integer CAPTCHA_IN_VOID=100404;	 
	/**
	 * 对不起，手机号不存在，请重新输入
	 */
	public static final Integer FOGET_PWD_NOT_EXIST=100405;	 
	/**
	 * 您的账号已被禁用，如有疑问请联系客服
	 */
	public static final Integer FORGETPWD_USER_FORBIDDEN=100406;	 

		
	//用户资料接口相关
	/**
	 * 对不起，该用户不存在~
	 */
	public static final Integer MEMBER_NAME_NOT_EXISTED1=100501;
	
	
	// 更新个人资料相关接口
	
	/**
	 *  "对不起，该用户不存在~"
	 */
	public static final Integer UPDATE_MEMBER_FAIL_NOT_EXIST=100601;
	
	/**
	 * 对不起，更新资料操作失败~
	 */
	public static final Integer UPDATE_USER_FAIL=100602;
	
	/**
	 * "对不起，身份证号不能为空~"
	 */
	public static final Integer UPDATE_CARDNO_FAIL=100603;	
	
	/**
	 * 对不起，该手机号已经被注册，请更换手机号重试~
	 */
	public static final Integer UPDATE_CARDN_CARDNO_EXIST=100604;	 	 
		
	// 修改绑定银行卡信息相关接口
	
	/**
	 * 对不起，验证码输入不正确~
	 */
	public static final Integer UPDATE_BANK_CAPTCHA_ERROR=100701;	 	 
		 
	// 发送验证码接口相关
	/**
	 * 对不起，该用户账号不存在，请返回刷新重试~
	 */
	public static final Integer SEND_MSG_PHONE_NOT_EXSIT=100801;	
	
	/**
	 * "该手机号已经被注册，请更换手机号~"
	 */
	public static final Integer SEND_MSG_PHONE_EXSITED=100802;	 
	
	/**
	 * 您的账号已被禁用，如有疑问请联系客服~
	 */
	public static final Integer SEND_MSG_PHONE_FORBIDDEN=100803;	 	 

	/**
	 * 手机号码格式不正确
	 */
	public static final Integer SEND_MSG_PHONE_RULE_FAULT=100804;	 
	
	/**
	 * 发送验证码异常
	 */
	public static final Integer SEND_MSG_PHONE_ERROR=100805;

	/**
	 * "对不起，手机号不能为空~"
	 */
	public static final Integer SEND_MSG_PHONE_EMPTY=100806;	

	//修改车队负责人接口
	/**
	 * "对不起，用户不存在，请尝试重新登陆~"
	 */
	public static final Integer UPDATE_FLEET_LOGIN_VOID=100901;
	
	/**
	 * "对不起，该用户不属于车队负责人，请尝试切换用户~"
	 */
	public static final Integer UPDATE_FLEET_ACCOUNT_FAULT=100902;
	/**
	 * 修改信息不能为空
	 */
	public static final Integer UPDATE_FLEET_CONTENT_EMPTY=100903;
	/**
	 * 对不起，验证码不正确，请重新输入~
	 */
	public static final Integer UPDATE_FLEET_CAPTCHA_IN_ERROR=100904;	 
	/**
	 * 对不起，验证码已过期，请重新获取
	 */
	public static final Integer UPDATE_FLEET_CAPTCHA_IN_VOID=100905;
	
	/**
	 * 图片上传失败
	 */
	public static final Integer UPDATE_FLEET_IMG_ADD_FAIL=100906;
	
	//修改驾驶员信息接口
	/**
	 * "对不起，用户不存在，请尝试重新登陆~"
	 */
	public static final Integer UPDATE_DRIVER_LOGIN_VOID=100901;
	
	
	public static final Integer UPDATE_DRIVER_MSG=100906;
	
	
	//权限管理
	/**
	 * "对不起，新增角色不能为空~"
	 */
	public static final Integer INSERT_ROLE_EMPTY=101001;
	
	/**
	 * "id不能为空"
	 */
	public static final Integer SELECT_ROLE_ERROR=101002;
	
	/**
	 * "对不起，您删除的角色不存在或该角色是默认角色~"
	 */
	public static final Integer DEL_ROLE_EMPTY=101003;
	
	/**
	 * "对不起，删除角色失败~"
	 */
	public static final Integer DEL_ROLE_ERROR=101003;
	//新增员工
	
	/**
	 * 员工信息不能为空
	 */
	public static final Integer INSERT_EMPLOYEE_EMPTY=101101;
	
	/**
	 * 禁用员工ID不能为空
	 */
	public static final Integer DEL_EMPLOYEE_EMPTY=101102;
	
	/**
	 * 新增信息不能为空
	 */
	public static final Integer INSERT_EMPTY=101201;
	
	//项目
	/**
	 * 删除项目信息不能为空
	 */
	public static final Integer DEL_PROJECT_EMPTY=102001;
	
	/**
	 * 还原项目信息不能为空
	 */
	public static final Integer REST_PROJECT_EMPTY=102002;
	
	/**
	 * 完成项目信息不能为空
	 */
	public static final Integer FINISH_PROJECT_EMPTY=102003;
	
}
