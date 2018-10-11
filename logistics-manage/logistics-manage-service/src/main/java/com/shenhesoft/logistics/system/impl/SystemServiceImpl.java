package com.shenhesoft.logistics.system.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import com.shenhesoft.logistics.business.mapper.BussinessHomeMapper;
import com.shenhesoft.logistics.common.exception.ParameterException;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.FormUtil;
import com.shenhesoft.logistics.common.util.SendMsgUtils;
import com.shenhesoft.logistics.common.util.StringUtils;
import com.shenhesoft.logistics.manage.mapper.TbSystemUserMapper;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.system.SystemManage;
import com.shenhesoft.logistics.system.SystemService;

/**
 * 系统管理-业务实现.
 * <p>
 * <a href="SystemServiceImpl.java"><i>View Source</i></a>
 * </p>
 * 
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class SystemServiceImpl implements SystemService {
	@Autowired
	private TbSystemUserMapper systemUserMapper;
	@Autowired
    private BussinessHomeMapper bussinessHomeMapper;
	  /**
	   * 验证码.
	   * 
	   * @param system 实体
	   * @return 页面表单
	   */
	  public SystemManage alterPasswd(SystemManage system){
		  assertPasswd(system);
		  
		  TbSystemUser user= AppSession.getCurrentUser();
		  user.setPasswd(DigestUtils.md5DigestAsHex(system.getPasswd().trim().getBytes()));
		  systemUserMapper.updatePasswdByPhone(user);
		  
		  AppSession.logoutCurrentUser();
		  return system;
	  }
	  /**
	   * @description 
	   * @date 2018年2月26日
	   * @param 
	   * @return
	  */
	  public List<Map<String, Object>> initPage(SystemManage system){
	    int startLen = AppSession.getHttpServletRequest().getContextPath().length();
	    String url = system.getAction().substring(startLen,system.getAction().length());
	    List<Map<String, Object>> list = bussinessHomeMapper.getMenuFourthLevel(url);
	    return list;
	  }

	/**
	 * 验证码.
	 * 
	 * @param system
	 *            实体
	 * @return 页面表单
	 */
	public SystemManage getCHkCode(SystemManage system) {
		assertUserPhone(system);
		
		String chkCode = AppUtils.getCheckCode();
		String errorcode = SendMsgUtils.sendMsg(system.getPhone(), chkCode);
		if (errorcode == null || !errorcode.equals("ok")) {
			throw new ParameterException("发送验证码出现异常");
		}
		AppSession.setAttribute(system.getPhone(), chkCode);

		return system;
	}

	/**
	 * 验证码.
	 * 
	 * @param system
	 *            实体
	 * @return 页面表单
	 */
	public SystemManage updatePasswd(SystemManage system) {
		assertUserPhone(system);
		assertPasswd(system);
		
		String chkCodeSession = AppSession.getAttribute(system.getPhone());
		if (null == chkCodeSession
				|| !chkCodeSession.equals(system.getCheckCode())) {
			throw new ParameterException("没有输入验证码或和手机收到的不一致！");
		}
		
		Map<String,Object> map = FormUtil.populate(system);
		TbSystemUser user= FormUtil.populate(TbSystemUser.class, map, true);
		user.setPasswd(DigestUtils.md5DigestAsHex(system.getPasswd().trim().getBytes()));
		systemUserMapper.updatePasswdByPhone(user);
		AppSession.removeAttribute(system.getPhone());
		return system;
	}
	
	private void assertPasswd(SystemManage system){
		if(StringUtils.isBlank(system.getPasswd()) || StringUtils.isBlank(system.getPasswdAgain())){
			throw new ParameterException("两次输入的密码都不能为空！");
		}
		if(!system.getPasswd().equals(system.getPasswdAgain())){
			throw new ParameterException("两次输入的密码不一致！");
		}
	}
	private void assertUserPhone(SystemManage system){
		if(StringUtils.isBlank(system.getPhone())){
			throw new ParameterException("手机号不能为空！");
		}
		Map<String,Object> map = systemUserMapper.getUserValiByPhone(FormUtil.populate(system));
		if(CollectionUtils.isEmpty(map)){
			throw new ParameterException("手机号未绑定登录账号或不是系统用户手机号！");
		}
	}
}