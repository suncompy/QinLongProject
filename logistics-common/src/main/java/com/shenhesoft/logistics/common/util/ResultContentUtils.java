package com.shenhesoft.logistics.common.util;
/**
 * @description:返回自定义错误码对应内容
 * 
 * @author shilvfei
 * 
 * @date 2017年12月4日
 */
public class ResultContentUtils {

	//请求接口未定义
	public static final String UNDEFIND_COMMAND="请求接口未定义";
	
	//请求包格式不合法
	public static final String INVALID_REQUEST_FORMATTER="请求包格式不合法";
	
	//数据库操作失败
	public static final String CONNECT_DB_FAIL="数据库操作失败";

	//"请求数据发生异常，请稍后重试"
	public static final String SERVICE_INNER_EXCEPTION="请求数据发生异常，请稍后重试";
	
	//"服务器数据库异常"
	public static final String SERVICE_DB_EXCEPTION="服务器数据库异常";

	//"时间格式化异常"
	public static final String DATE_FORMMATE_FAIL="时间格式化异常";

	//"更新系统日志出错~"
	public static final String INSERT_APP_LOG="更新系统日志出错~";
	
	//数据请求超时~请稍后再试~
	public static final String REQUEST_MSG_FAIL="更新系统日志出错~";
	/**
	 * 注册接口相关
	 */
	
	/**
	 * 对不起，用户手机号已经存在了，请尝试更换手机号重试~
	 */
	public static final String ACCOUNT_EXIST="对不起，用户手机号已经存在了，请尝试更换手机号重试~";
	/**
	 * "对不起，秦龙物流有点儿忙，请稍后再试~"
	 */
	public static final String REGISTER_EASEMOB_FAIL="对不起，秦龙物流有点儿忙，请稍后再试~";
	
	/**
	 * 对不起，操作异常，请稍后重试~
	 */
	public static final String ACCOUNT_INSERT_FAILED="对不起，操作异常，请稍后重试~";
	/**
	 * 密码MD5加密异常
	 */
	public static final String ACCOUNT_BUILD_PWD_FAILED="密码MD5加密异常";
	/**
	 * 对不起，手机验证码不正确~
	 */
	public static final String REG_CAPTCHA_IN_ERROR="对不起，手机验证码不正确~";
	/**
	 * 对不起，验证码已过期，请重新获取
	 */
	public static final String REG_CAPTCHA_IN_VOID="对不起，验证码已过期，请重新获取";
	/**
	 * 对不起，这个用户名已经被人使用了,请尝试更换注册用户名哦~
	 */
	public static final String ACCOUNT_LOGINNAME_EXIST="对不起，这个用户名已经被人使用了,请尝试更换注册用户名哦~";
	/**
	 * 对不起，图片上传失败,请稍后再试
	 */
	public static final String IMG_ADD_FAIL="对不起，图片上传失败,请稍后再试";
	/**
	 * 请把带*必填信息填上
	 */
	public static final String REG_CONTENT_EMPTY="请把带*必填信息填上";
	/**
	 * 两次输入的密码不一致
	 */
	public static final String REG_PASSWD_DISCORD="两次输入的密码不一致";
	/**
	 * 请勾选《用户注册协议》
	 */
	public static final String REG_USER_PROTOCOL="请勾选《用户注册协议》";
	/**
	 * 插入数据库异常
	 */
	public static final String REG_INSERT_DB_ERROR="插入数据库异常";
	/**
	 * 车辆保险时间有误~
	 */
	public static final String REG_INSURANCEDATE_ERROR="车辆保险时间有误~";
	
	// 登录接口相关
	/**
	 * "对不起，用户不存在，请尝试重新登陆~"
	 */
	public static final String MEMBER_ISNOT_EXIST="对不起，用户不存在，请尝试重新登陆~";

	 /**
	 * "用户名或密码不正确"
	 */
	public static final String UNKNOWN_USERNAME="用户名或密码不正确";
	/**
	 * 密码加密失败
	 */
	public static final String NO_SUCH_ALGORITHM="密码加密失败";
	/**
	 * "您的账号已被禁用，如有疑问请联系客服"
	 */
	public static final String FORBIDDEN_USERNAME="您的账号已被禁用，如有疑问请联系客服";
	/**
	 * 对不起，数据异常，请联系管理员~
	 */
	public static final String LOGIN_ERROR="对不起，数据异常，请联系管理员~";
	/**
	 *  "对不起，该用户不存在~"
	 */
	public static final String MEMBERORTHER_ISNOT_EXIST="对不起，该用户不存在~";
	/**
	 * "对不起，更新登录时间失败~"
	 */
	public static final String UPDATE_LOAGINTIME_ERR="对不起，更新登录时间失败~";
	
	/**
	 * "用户名或密码不能为空"
	 */
	public static final String LOAGIN_EMPTY="用户名或密码不能为空";
	
	 // 用户修改密码接口相关
	/**
	 * "用户名或密码不正确"
	 */
	public static final String UPDATE_PWD_ERROR="用户名或密码不正确";
	/**
	 *  "密码加密失败"
	 */
	public static final String UPDATE_PWD_NO_SUCH_ALGORITHM= "密码加密失败";

		
	//用户忘记密码接口相关
	/**
	 * "对不起，用户不存在，请尝试重新登陆~"
	 */
	public static final String FORGET_PWD_MEMBER_EXIST="对不起，用户不存在，请尝试重新登陆~";	 
	
	/**
	 * 用户名或密码不正确
	 */
	public static final String FORGET_PWD_ERROR="用户名或密码不正确";	 
	/**
	 * 对不起，验证码不正确，请重新输入~
	 */
	public static final String CAPTCHA_IN_ERROR="对不起，验证码不正确，请重新输入~";	 
	/**
	 * 对不起，验证码已过期，请重新获取
	 */
	public static final String CAPTCHA_IN_VOID="对不起，验证码已过期，请重新获取";	 
	/**
	 * 对不起，手机号不存在，请重新输入
	 */
	public static final String FOGET_PWD_NOT_EXIST="对不起，手机号不存在，请重新输入";	 
	/**
	 * 您的账号已被禁用，如有疑问请联系客服
	 */
	public static final String FORGETPWD_USER_FORBIDDEN="您的账号已被禁用，如有疑问请联系客服";	 

		
	//用户资料接口相关
	/**
	 * 对不起，该用户不存在~
	 */
	public static final String MEMBER_NAME_NOT_EXISTED1="对不起，该用户不存在~";
	
	
	// 更新个人资料相关接口
	
	/**
	 *  "对不起，该用户不存在~"
	 */
	public static final String UPDATE_MEMBER_FAIL_NOT_EXIST= "对不起，该用户不存在~";
	
	/**
	 * 对不起，更新资料操作失败~
	 */
	public static final String UPDATE_USER_FAIL="对不起，更新资料操作失败~";
	
	/**
	 * "对不起，身份证号不能为空~"
	 */
	public static final String UPDATE_CARDNO_FAIL="对不起，身份证号不能为空~";	
	
	/**
	 * 对不起，该手机号已经被注册，请更换手机号重试~
	 */
	public static final String UPDATE_CARDN_CARDNO_EXIST="对不起，该手机号已经被注册，请更换手机号重试~";	 	 
		
	// 修改绑定银行卡信息相关接口
	
	/**
	 * 对不起，验证码输入不正确~
	 */
	public static final String UPDATE_BANK_CAPTCHA_ERROR="对不起，验证码输入不正确~";	 	 
		 
	// 发送验证码接口相关
	/**
	 * 对不起，该用户账号不存在，请返回刷新重试~
	 */
	public static final String SEND_MSG_PHONE_NOT_EXSIT="对不起，该用户账号不存在，请返回刷新重试~";	
	
	/**
	 * "该手机号已经被注册，请更换手机号~"
	 */
	public static final String SEND_MSG_PHONE_EXSITED="该手机号已经被注册，请更换手机号~";	 	 
	/**
	 * 您的账号已被禁用，如有疑问请联系客服~
	 */
	public static final String SEND_MSG_PHONE_FORBIDDEN="您的账号已被禁用，如有疑问请联系客服~";	 
	/**
	 * 手机号码格式不正确
	 */
	public static final String SEND_MSG_PHONE_RULE_FAULT="手机号码格式不正确";
	/**
	 * 发送验证码异常
	 */
	public static final String SEND_MSG_PHONE_ERROR="对不起,发送验证码失败,请重新发送~";	
	
	/**
	 * "对不起，手机号不能为空~"
	 */
	public static final String SEND_MSG_PHONE_EMPTY="对不起，手机号不能为空~";	
	//修改车队负责人接口
	/**
	 * "对不起，用户不存在，请尝试重新登陆~"
	 */
	public static final String UPDATE_FLEET_LOGIN_VOID="对不起，用户不存在，请尝试重新登陆~";	
	/**
	 * ""
	 */
	public static final String UPDATE_FLEET_ACCOUNT_FAULT="对不起，该用户不属于车队负责人，请尝试切换用户~";
	/**
	 * 修改信息不能为空
	 */
	public static final String UPDATE_FLEET_CONTENT_EMPTY="修改信息不能为空";
	/**
	 * 对不起，验证码不正确，请重新输入~
	 */
	public static final String UPDATE_FLEET_CAPTCHA_IN_ERROR="对不起，验证码不正确，请重新输入~";	 
	/**
	 * 对不起，验证码已过期，请重新获取
	 */
	public static final String UPDATE_FLEET_CAPTCHA_IN_VOID="对不起，验证码已过期，请重新获取";
	/**
	 * 图片上传失败
	 */
	public static final String UPDATE_FLEET_IMG_ADD_FAIL="对不起, 图片上传失败,请重新上传~";
	
	//修改驾驶员信息接口
	/**
	 * "对不起，用户不存在，请尝试重新登陆~"
	 */
	public static final String UPDATE_DRIVER_LOGIN_VOID="对不起，用户不存在，请尝试重新登陆~";
	
	
	public static final String UPDATE_DRIVER_MSG="对不起，用户不存在，请尝试重新登陆~";
	
	/**
	 * "对不起，新增角色不能为空~"
	 */
	public static final String INSERT_ROLE_EMPTY="对不起，新增角色不能为空~";
	/**
	 * "id不能为空"
	 */
	public static final String SELECT_ROLE_ERROR="id不能为空";
	
	/**
	 * "对不起，您删除的角色不存在或该角色是默认角色~~"
	 */
	public static final String DEL_ROLE_EMPTY="对不起，您删除的角色不存在或该角色是默认角色~";
	
	/**
	 * "对不起，删除角色失败~"
	 */
	public static final String DEL_ROLE_ERROR="对不起，删除角色失败~";
	/**
	 * 员工信息不能为空
	 */
	public static final String INSERT_EMPLOYEE_EMPTY="员工信息不能为空";
	/**
	 * 禁用员工ID不能为空
	 */
	public static final String DEL_EMPLOYEE_EMPTY="禁用员工ID不能为空";
	
	/**
	 * 请把信息补全
	 */
	public static final String INSERT_EMPTY="请把信息补全";
	
	/**
	 * 删除项目信息不能为空
	 */
	public static final String DEL_PROJECT_EMPTY="删除项目信息不能为空";
	
	/**
	 * 还原项目信息不能为空
	 */
	public static final String REST_PROJECT_EMPTY="还原项目信息不能为空";
	
	/**
	 * 完成项目信息不能为空
	 */
	public static final String FINISH_PROJECT_EMPTY="完成项目信息不能为空";
	
}
