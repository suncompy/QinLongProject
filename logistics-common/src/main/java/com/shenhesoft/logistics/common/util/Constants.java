package com.shenhesoft.logistics.common.util;

/**
 * @description 常量定义类
 *
 * @author shilvfei
 *
 * @date 2017年5月19日
 */
public class Constants {
	
	/**
	 * 附件上传地址 
	 * 在fileUpload.properties配置
	 * 项目启动时赋值
	 */
	public static String FILE_PATH="E:/logistics/";
	/**
	 * 融云 appKey
	 * 在rongCloud.properties配置
	 * 项目启动时赋值
	 */
	public static String APP_KEY_RONG_CLOUD;
	/**
	 * 融云 appSecret
	 * 在rongCloud.properties配置
	 * 项目启动时赋值
	 */
	public static String APP_SECRET_RONG_CLOUD;
	/**
	 * 项目访问地址
	 * 在rongCloud.properties配置
	 * 项目启动时赋值
	 */
	public static String WEB_PATH="http://139.196.100.149:8081/logistics-manage-web/";
	
	public static String CAR_WEB_PATH="http://139.196.100.149:8081/logistics-carmanage-web/";
	
	/**
	 * 图片路径
	 */
	public static String IMG_PATH="upload/photo/";

	/**
	 * 项目编号前缀
	 */
	public static String PROJECT_CODE;
	
	
	/**
	 * 所有权限
	 */
	public static Integer[] PERSSION_ALL_ID;
	
	/**
	 * 重置的密码
	 */
	public static String RESET_PASSWD = "123456";
	
	
	//200 消息响应成功
	public static Integer STATUS_SUCCESS =200;
	
	/**
	 * 类型不匹配
	 */
	public static String TYPE_MISMATCH = "typeMismatch";
	
	// 正确
	public static final byte YES = 1;

	// 错误
	public static final byte NO = 0;

	// 百分比
	public static final byte PERCENT_VALUE = 100;

	// 小数点后保留位数
	public static final byte POINT_RETAIN = 2;

	// 用户状态 1:可用
	public static final byte DELETE_FLAG_DEFAULT = 1;

	// 用户状态 0:不可用
	public static final byte DELETE_FLAG_DELETE = 0;

	// POINT
	public static final char POINT = '.';

	// COMMA
	public static final String COMMA = ",";

	// 删除标志
	public static final String DELETE_FLAG = "deleteFlag";

	// 原始删除标识
	public static final String ORIG_DELETE_FLAG = "origDeleteFlag";

	// page
	public static final String STR_PAGE = "page";

	// ID
	public static final String STR_ID = "id";

	// name
	public static final String STR_NAME = "name";

	// passWord
	public static final String STR_PWD = "passWord";

	// user
	public static final String STR_USER = "user";

	// 默认的parentId
	public static final int PARENTID_DEFAULT = 0;

	// parentId
	public static final String STR_PARENTID = "parentId";

	// DB true
	public static final byte DB_YES = 1;

	// DB false
	public static final byte DB_NO = 0;

	/**
	 * 男
	 */
	public static final byte SEX_MALE = 1;

	/**
	 * 女
	 */
	public static final byte SEX_FEMALE = 2;
	
	/**
	 *挂靠状态 已取消
	 */
	public static final byte ANCHORED_CANCEL =0;
	
	/**
	 *挂靠状态 已挂靠
	 */
	public static final byte ANCHORED_YES =1;
	
	/**
	 *挂靠状态 被拒
	 */
	public static final byte ANCHORED_REFUSE =2;
	
	//
	/**
	 *挂靠状态 申请挂靠中
	 */
	public static final byte ANCHORED_APPLY = 3 ;
	
	/**
	 * 是车队负责人
	 */
	public static final byte CAR_TEAM_RESPONSIBLER=1;
	
	/**
	 * 是个人
	 */
	public static final byte DRIVER=0;
	
	/**
	 * 车队注册获取验证码
	 */
	public static final String CAR_TEAM_REGISTER_MSG="CAR_TEAM_REGISTER_MSG";
	/**
	 * 修改车队负责人信息
	 */
	public static final String CAR_TEAM_UPDATE_MSG="CAR_TEAM_UPDATE_MSG";
	/**
	 *个人注册 车主获取验证码
	 */
	public static final String PERSON_CAR_OWNER_MSG="PERSON_CAR_OWNER_MSG";
	/**
	 *个人注册  驾驶员获取验证码
	 */
	public static final String PERSON_DRIVER_MSG="PERSON_DRIVER_MSG";
	/**
	 * 忘记密码获取验证码
	 */
	public static final String FORGET_PASSWD_MSG="FORGET_PASSWD_MSG";
	/**
	 * 修改银行卡号获取验证码
	 */
	public static final String CHANGE_BANK_CARD_MSG="CHANGE_BANK_CARD_MSG";
	/**
	 * 修改手机号获取验证码
	 */
	public static final String CHANGE_PHONE_MSG="CHANGE_PHONE_MSG";
	
	
	/** 车辆性质
    0 他人
    1 自有*/
	public static final Integer CAR_PROPERTY_OTHER=0;
	public static final Integer CAR_PROPERTY_SELF=1;
	
	/**
	 * 0查询本周的
	 */
	public static final Integer THIS_WEEK=0;
	/**
	 * 1查询每月的
	 */
	public static final Integer THIS_MONTH=1;
	
	/**
	 *所有权限 业务
	 */
	public static final Byte BUSINESS_PERMISSION=1;
	/**
	 *所有权限 财务
	 */
	public static final Byte FINANCE_PERMISSION=2;
	/**
	 *所有权限 设置
	 */
	public static final Byte SET_PERMISSION=3;
	
	//是否被删除
	/** 未删除
	*/
	public static final byte DELETE_FLAG_FALSE = 0;

	
	/** // 已删除
	*/
	public static final byte DELETE_FLAG_TRUE = 1;
	
	//
	/**
	 * 是否是默认角色 0不是
	 */ 
	public static final byte ROLE_DEFAULT_NO = 0;
	/**
	 * 是否是默认角色 1是
	 */
	public static final byte ROLE_DEFAULT_YES = 1;
	
	/**
	 * 管理员角色
	 */
	public static final byte ADMIN_ROLE = 1;
	/**
	 * 负责人角色
	 */
	public static final byte  RESPONSIBLER_ROLE = 2;
	
	//员工工作状态
	/**
	 * 在职
	 */
	public static final byte WORK_STATUS_YES = 0;
	/**
	 * 离职
	 */
	public static final byte WORK_STATUS_NO = 1;
	
	/**
	 * 休假
	 */
	public static final byte WORK_STATUS_VACATION = 2;
	
	/**
	 * 停职
	 */
	public static final byte WORK_STATUS_STOP = 3;
	
	
	/**
	 * 初始化管理员
	 */
	public static final byte WORK_STATUS_ADMIN = 6;
	

	//
	//
	
	/**
	 *客户状态: 正常使用
	 */
	public static final byte CUSTOMER_STATUS_YES = 0;
	
	/**
	 *客户状态: 禁用
	 */
	public static final byte  CUSTOMER_STATUS_NO = 1;
	
	/**
	 * 2:客户业务联系人
	 */
	public static final byte  CUSTOMER_RELATE_PERSON = 2;
	
	/**
	 *  1:车站业务联系人
	 */
	public static final byte  TRAIN_RELATE_PERSON =1;
	
	/**
	 * 网点分支状态 可用
	 */
	public static final byte  DOT_BRANCH_STATUS_YES = 0;
	
	/**
	 * 网点分支状态 不可用
	 */
	public static final byte  DOT_BRANCH_STATUS_NO = 1;
	
	/**
	 * 网点分支状态 已删除
	 */
	public static final byte  DOT_BRANCH_STATUS_DEL = 3;
	
	
	/**
	 * 短信计划-节点
	 *
	 *  等待调度
		等待发运
		在途运载
		货位引导
		等待回单
		等待计费
		已完成
	 * 
	 */
	/**
	 * 等待调度
	 */
	public static final byte  SMS_POINT_WAIT_DIS =1;
	
	/**
	 * 等待发运
	 */
	public static final byte  SMS_POINT_WAIT_SEND =2;
	
	/**
	 * 在途运载
	 */
	public static final byte  SMS_POINT_ON_ROAD =3;
	
	/**
	 * 货位引导
	 */
	public static final byte  SMS_POINT_LOCATION_GUIDE =4;
	
	/**
	 * 等待回单
	 */
	public static final byte  SMS_POINT_WAIT_RETURN =5;
	
	/**
	 * 等待计费
	 */
	public static final byte  SMS_POINT_WAIT_CHARGE =6;
	
	/**
	 * 已完成
	 */
	public static final byte  SMS_POINT_ORDER_DONE =7;
	
	
	

	/**
	 * 项目是否可以分配每日任务  - 0 否
	 */
	public static final byte PROJECT_IS_DISTRIBUTE_NO = 0;
	
	/**
	 * 项目是否可以分配每日任务  - 1 可以分配
	 */
	public static final byte PROJECT_IS_DISTRIBUTE_YES = 1;
	
	/*是否进行任务分配：  1 未分配 0 暂停分配
	4 汽运开始 5 汽运暂停*/
	/**
	 * 接取分配 送达暂停   
	 */
	public static final byte PROJECT_IS_DISTRIBUTE_RECEIVE = 2;
	
	/**
	 * 接取暂停 送达分配 
	 */
	public static final byte PROJECT_IS_DISTRIBUTE_SEND = 3;
	
	/**
	 * 汽运开始
	 */
	public static final byte PROJECT_BEGIN_DISTRIBUTE_TRUCK = 4;
	
	/**
	 * 汽运暂停
	 */
	public static final byte PROJECT_STOP_DISTRIBUTE_TRUCK = 5;
	
	//项目状态
	
	/**
	 * 项目状态
	 *   0  未过期取消(24小时之内可以在历史回收站可以看到的)
	 *   1 正在运行
	 *   2 已完成
	 *   3 未使用
	 */
	/**
	 *  1 正在运行 
	 */
	public static final byte PROJECT_STATUS_RUNNING=1;
	
	/**
	 * 项目状态
	 *   0  未过期取消(24小时之内可以在历史回收站可以看到的)
	 */
	public static final byte PROJECT_STATUS_CANCLE=0;
	
	
	/**
	 * 项目状态
	 *   2  已完成
	 */
	public static final byte PROJECT_STATUS_FINISH=2;
	
	/**
	 * 项目状态
	 *   3 未使用
	 */
	public static final byte PROJECT_STATUS_UNUSED=3;
	
	/** 联运模式
    0 汽运
    1 接取
    2 火运
    3 送达
    4 接取+火运
    5 火运+送达
    6 联运 
    7 接取+送达
     */
	
	/**
	 * 汽运
	 */
	public static final byte TRANSPORTTYPE_TRUCK=0;
	
	/**
	 * 接取
	 */
	public static final byte TRANSPORTTYPE_RECEIVE=1;
	/**
	 * 送达
	 */
	public static final byte TRANSPORTTYPE_DELIVERY=2;
	/**
	 * 火运
	 */
	public static final byte TRANSPORTTYPE_TRAIN=3;
	/**
	 * 接取+火运
	 */
	public static final byte TRANSPORTTYPE_RECEIVE_TRAIN=4;
	/**
	 * 火运+送达
	 */
	public static final byte TRANSPORTTYPE_TRAIN_DELIVERY=5;
	
	/**
	 *  联运
	 */
	public static final byte TRANSPORTTYPE_UNION=6;
	
	/**
	 *  接取+送达
	 */
	public static final byte TRANSPORTTYPE_RECEIVE_DELIVERY=7;
	//
	/**
	 * 短驳承运方式 平台
	 */
	public static final byte TYPE_PLATFORM=0;
	
	/**
	 * 短驳承运方式 自选
	 */
	public static final byte TYPE_OPTIONAL=1;
	
	
	//短驳承运类型 接取/送达
	/**
	 * 短驳承运类型  接取
	 */
	public static final byte SHORT_BRAGE_TYPE_RECEIVE = 1;
	
	/**
	 *短驳承运类型  送达
	 */
	public static final byte SHORT_BRAGE_TYPE_DELIVERY = 2;
	
	/**
	 *短驳承运类型 汽运
	 */
	public static final byte SHORT_BRAGE_TYPE_CARRUN = 3;
	
	//站点级别
	/**
	 * 火车站点级别 1级 铁路局
	 */
	public static final byte SITE_LEVEL_ONE=0;
	/**
	 * 火车站点级别 2级 货运总局
	 */
	public static final byte SITE_LEVEL_TWO=1;
	/**
	 * 火车站点级别 3级 营业厅
	 */
	public static final byte SITE_LEVEL_THREE=2;
	
	//网点分支级别
	/**
	 * 网点分支级别 一级
	 */
	public static final byte BRANCH_LEVEL_ONE=0;
	/**
	 *网点分支级别 二级
	 */
	public static final byte  BRANCH_LEVEL_TWO=1;
	
	/**
	 *网点分支级别  三级
	 */
	public static final byte  BRANCH_LEVEL_THREE=2;
	
	
	/**
	 * 项目双向 -全部暂停分配
	 */
	public static final byte PROJECT_DISTRIBUTION_ALL_STOP = 0;

	/**
	 * 项目双向 -全部分配中
	 */
	public static final byte PROJECT_DISTRIBUTION_ALL_START = 1;
		
	/**
	 * 项目 -接取分配 送达暂停
	 */
	public static final byte PROJECT_DISTRIBUTION_REC_START_SEND_STOP = 2;
	
	/**
	 * 项目  -接取暂停 送达分配
	 */
	public static final byte PROJECT_DISTRIBUTION_SEND_START_REC_STOP = 3;
		
	/**
	 * //项目日志状态  删除日志
	 */
	public static final byte  PROJECT_LOG_DEL=0;
	/**
	 * //项目日志状态 修改日志
	 */
	public static final byte  PROJECT_LOG_UPDATE=1;
	/**
	 * //项目日志状态 新增日志
	 */
	public static final byte  PROJECT_LOG_ADD=2;
	/**
	 * //项目日志状态 还原日志
	 */
	public static final byte  PROJECT_LOG_RESTORE=3;
	 
	/**
	 * 订单来源 PC端
	 */
	public static final byte ORDER_ORIGIN_PC = 1;
	
	/**
	 * 订单来源  APP端
	 */
	public static final byte ORDER_ORIGIN_APP = 2;
	

	/**
	 * 网点分支 负责人
	 */
	public static final byte  DOT_BRANCH_RESPONSIBLER =2;
	
	/**
	 * 网点分支员工
	 */
	public static final byte  DOT_BRANCH_PERSON =1;
	
	/**
	 * 短驳订单类型   1：集装箱   2:散堆装
	 */
	public static final byte  SHORT_ORDER_TYPE_BOX =1;
	
	/**
	 * 短驳订单类型   1：集装箱   2:散堆装
	 */
	public static final byte  SHORT_ORDER_TYPE_BULK = 2;
	
	//库存盘查
	
	/**
	 *库存 始发点
	 */
	public static final byte  STOCK_TYPE_BEGIN = 0;
	
	/**
	 *库存 到达点
	 */
	public static final byte  STOCK_TYPE_END = 1;
	
	
	//发布任务
	
	/**
	 * 接取
	 */
	public static final byte PROJECT_TASK_TYPE_RECEIVE = 1;
	
	/**
	 * 送达
	 */
	public static final byte PROJECT_TASK_TYPE_SEND = 2;
	
	/**
	 * 汽运
	 */
	public static final byte PROJECT_TASK_TYPE_TRUCK = 3;
	
	//运单取消状态
	
	/**
	 * 运单取消状态： 已取消 1
	 */
	public static final byte ORDER_CANCLE_YES=1 ;
	
	/**
	 * 运单取消状态 ：未取消 0
	 */
	public static final byte ORDER_CANCLE_DEFAULT=0 ;
	
	//任务分配状态
	
	/**
	 * 分配中		1
	 */
	public static final byte DISTRIBUTION_STATUS_RUNNING =1;
	
	/**
	 * 暂停分配状态 2
	 */
	public static final byte DISTRIBUTION_STATUS_STOP= 2;
	
	
	//司机运单 状态
	/**
	 *  0  未接单 （可接单）
	 */
	public static final byte DRIVER_ORDER_STATUS_YES = 0 ;
	
	/**
	 *  1  已接单 （不可接单）
	 */
	public static final byte DRIVER_ORDER_STATUS_NO = 1 ;
	
	/**
	 * 0油卡 '
	 * */
	public static final byte CARD_TYPE_OIL = 0 ;
	
	/**
	 *  1气卡
	 * */
	public static final byte CARD_TYPE_GAS = 1 ;
	
	/**
	 * 预付款
	 */
	public static final Integer ACCOUNT_TYPE_ADVANCE =1;
	
	/**
	 * 非预付款
	 */
	public static final Integer ACCOUNT_TYPE_NON_ADVANCE =-1;
	
	/**
	 * 挂靠记录表
	 * 0:已取消
	 */
	public static final Integer ANCHORE_RECORD_STATUS_CANCLE = 0;
	/**
	 * 挂靠记录表
	 * 1:已挂靠          
	 */
	public static final Integer ANCHORE_RECORD_STATUS_ANCHORED = 1;
	
	/**
	 * 挂靠记录表
	 * 2:被拒          
	 */
	public static final Integer ANCHORE_RECORD_STATUS_REJECT = 2;
	
	/**
	 * 挂靠记录表
	 * 3:申请挂靠中
	 */
	public static final Integer ANCHORE_RECORD_STATUS_APPLY = 3;
	
}
