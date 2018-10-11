package com.shenhesoft.logistics.enterprise.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import com.alibaba.fastjson.JSONArray;
import com.shenhesoft.logistics.common.GeneralResponse;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.CheckPhone;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.common.util.RandomNum;
import com.shenhesoft.logistics.common.util.SendMsgUtils;
import com.shenhesoft.logistics.common.util.StringUtil;
import com.shenhesoft.logistics.common.util.rongcloud.io.rong.RongCloud;
import com.shenhesoft.logistics.common.util.rongcloud.io.rong.models.TokenResult;
import com.shenhesoft.logistics.enterprise.service.AppSystemUserLoginService;
import com.shenhesoft.logistics.manage.helpPojo.MenuDetail;
import com.shenhesoft.logistics.manage.index.IndexService;
import com.shenhesoft.logistics.manage.mapper.TbBranchGroupMapper;
import com.shenhesoft.logistics.manage.mapper.TbSystemUserMapper;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.menu.TbMenu;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUserExample;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUserExample.Criteria;
import com.shenhesoft.logistics.manage.user.UserService;
import com.shenhesoft.logistics.utils.ImageUtil;

/**
 * app端 客户登录 service 实现
 * 
 * @author dusd
 * @date 2017年12月24日
 */
@Service
public class AppSystemUserLoginServiceImpl implements AppSystemUserLoginService {

	/**
	 * 企业用户 service 接口
	 */
	@Autowired
	private UserService userService;
	/**
	 * 企业用户信息
	 */
	@Autowired
	private TbSystemUserMapper tbSystemUserMapper;

	@Autowired
	private IndexService indexService;
	
	@Autowired
    private TbBranchGroupMapper branchGroupMapper;
	/**
	 * 通过用户id查询用户信息
	 * 
	 * @author dusd
	 * @date 2017年12月28日
	 * @return
	 */
	private TbSystemUser viewTbSystemUserDataMap(Map<String, String> dataMap) {
		if (dataMap == null)
			return null;
		String strUserId = dataMap.get("userId");
		if (StringUtil.isEmpty(strUserId)) {
			return null;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		return userService.getTbSystemUserById(userId);
	}

	@Override
	public GeneralResponse systemUserDoLogin(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String userName = dataMap.get("userName");
		String password = dataMap.get("password");
		if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("请求参数不能为空");
			generalResponse.setObj(null);
			return generalResponse;
		}
		// 通过userName password 查询
		TbSystemUser tbSystemUser = userService.getTbSystemUserByUserNamePassword(userName, password);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("用户名或密码错误");
			generalResponse.setObj(null);
			return generalResponse;
		}
		TbBranchGroup tbBranchGroup = branchGroupMapper.selectByPrimaryKey(tbSystemUser.getBranchGroupId());
		tbSystemUser.setSysOrgCode(tbBranchGroup.getSysOrgCode());
		
		// 登录成功 通过用户信息 获取融云聊天 token
		RongCloud rongCloud = RongCloud.getInstance(Constants.APP_KEY_RONG_CLOUD, Constants.APP_SECRET_RONG_CLOUD);
		// 获取 Token 方法
		TokenResult userGetTokenResult = rongCloud.user.getToken("Q" + tbSystemUser.getId(), tbSystemUser.getName(),
				AppSession.getBasePath() + "img/user.png");
		String rongCloudToken = userGetTokenResult.getToken();
		tbSystemUser.setRongCloudToken(rongCloudToken);
		// 获取用户头像
		if (tbSystemUser.getUserIcon() != null) {
			tbSystemUser.setUserIcon(AppSession.getBasePath() + Constants.IMG_PATH + tbSystemUser.getUserIcon());
		}

		//获取用户所有的权限list
    	List<TbMenu> menuDetailList = indexService.listMenuDetailByUserId(tbSystemUser.getId());
    	//一级菜单信息
    	//List<MenuDetail> firstMenuDetailList = indexService.getFirstMenuDetailList(menuDetailList);
    	//AppSession.setAttribute("AppPermissions", menuDetailList);
    	tbSystemUser.setMeanList(menuDetailList);
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("企业登录成功");
		generalResponse.setObj(tbSystemUser);
		return generalResponse;
	}

	@Override
	public GeneralResponse viewVerificationByregisterPhoneApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String phoneNum = dataMap.get("phoneNum");// 手机号码
		// 判断手机号是否为空
		if (StringUtil.isEmpty(phoneNum)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("手机号码为空-发送验证码失败");
			return generalResponse;
		}
		// 手机号校验
		boolean flag = CheckPhone.isPhone(phoneNum);
		if (!flag) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("手机号码格式不正确-发送验证码失败");
			return generalResponse;
		}

		// 根据手机号判断用户是否存在
		TbSystemUserExample systemUserExample = new TbSystemUserExample();

		Criteria createCriteria = systemUserExample.createCriteria();

		createCriteria.andPhoneEqualTo(phoneNum);

		List<TbSystemUser> list = tbSystemUserMapper.selectByExample(systemUserExample);

		if (list == null || list.size() == 0) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("您输入的手机号未注册-发送验证码失败");
			return generalResponse;
		}

		TbSystemUser tbSystemUser = list.get(0);

		if (!phoneNum.trim().equals(tbSystemUser.getPhone())) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("输入的手机号码和注册时的不一致-发送验证码失败");
			return generalResponse;
		}

		// 发送短信验证码
		String checkedCode = RandomNum.getCheckCode().toString();// 随机产生验证码
		String resultCode = SendMsgUtils.sendMsg(phoneNum, checkedCode);// 发送验证码
		if (resultCode.equals("error")) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("调用短信接口失败 - 发送验证码失败");
			return generalResponse;
		}
		// 保存到数据库
		tbSystemUser.setCheckedCode(checkedCode);
		tbSystemUser.setCheckedCodeDate(new Date());
		tbSystemUserMapper.updateByPrimaryKeySelective(tbSystemUser);

		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("发送验证码成功");
		return generalResponse;
	}

	@Override
	public GeneralResponse changeTbSystemUserPasswordApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String checkedCode = dataMap.get("checkedCode");// 验证码
		String phoneNum = dataMap.get("phoneNum");// 手机号
		String newPassword = dataMap.get("newPassword");// 新密码
		String newPasswordAgain = dataMap.get("newPasswordAgain");// 新重复密码
		// 判断登录信息 start
		// 根据手机号判断用户是否存在
		TbSystemUserExample systemUserExample = new TbSystemUserExample();

		Criteria createCriteria = systemUserExample.createCriteria();

		createCriteria.andPhoneEqualTo(phoneNum);

		List<TbSystemUser> list = tbSystemUserMapper.selectByExample(systemUserExample);

		if (list == null || list.size() == 0) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("您输入的手机号未注册-用户修改密码失败");
			return generalResponse;
		}

		TbSystemUser tbSystemUser = list.get(0);

		if (!phoneNum.trim().equals(tbSystemUser.getPhone())) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("输入的手机号码和注册时的不一致-用户修改密码失败");
			return generalResponse;
		}

		// 判断登录信息 end
		if (StringUtil.isEmpty(checkedCode)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("验证码为空-用户修改密码失败");
			return generalResponse;
		}
		if (!checkedCode.trim().equals(tbSystemUser.getCheckedCode())) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("验证码不正确-用户修改密码失败");
			return generalResponse;
		}

		if (StringUtil.isEmpty(newPassword) || StringUtil.isEmpty(newPasswordAgain)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("密码不能为空-用户修改密码失败");
			return generalResponse;
		}
		newPassword = newPassword.trim();// 新密码
		newPasswordAgain = newPasswordAgain.trim();// 新重复密码

		if (!newPassword.equals(newPasswordAgain)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("两次密码输入不一致-用户修改密码失败");
			return generalResponse;
		}

		// 保存到数据库
		tbSystemUser.setPasswd(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
		tbSystemUserMapper.updateByPrimaryKeySelective(tbSystemUser);
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("用户修改密码成功");
		return generalResponse;
	}

	@Override
	public GeneralResponse changeTbSystmUserPhoneApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String checkedCode = dataMap.get("checkedCode");// 验证码
		String oldPhoneNum = dataMap.get("oldPhoneNum");// 原手机号码
		String newPhoneNum = dataMap.get("newPhoneNum");// 新手机号码
		// 判断登录信息 start
		// 根据手机号判断用户是否存在
		TbSystemUserExample systemUserExample = new TbSystemUserExample();

		Criteria createCriteria = systemUserExample.createCriteria();

		createCriteria.andPhoneEqualTo(oldPhoneNum);

		List<TbSystemUser> list = tbSystemUserMapper.selectByExample(systemUserExample);

		if (list == null || list.size() == 0) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("您输入的当前手机号未注册-用户修改手机号码失败");
			return generalResponse;
		}

		TbSystemUserExample systemUserExample2 = new TbSystemUserExample();

		Criteria createCriteria2 = systemUserExample2.createCriteria();

		createCriteria2.andPhoneEqualTo(newPhoneNum);

		List<TbSystemUser> list2 = tbSystemUserMapper.selectByExample(systemUserExample2);

		if (!CollectionUtils.isEmpty(list2)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("您输入的新手机号已注册-用户修改手机号码失败");
			return generalResponse;
		}

		// 判断登录信息 end
		if (StringUtil.isEmpty(checkedCode)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("验证码为空-用户修改手机号码失败");
			return generalResponse;
		}
		TbSystemUser tbSystemUser = list.get(0);

		if (!checkedCode.trim().equals(tbSystemUser.getCheckedCode())) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("验证码不正确-用户修改手机号码失败");
			return generalResponse;
		}

		if (StringUtil.isEmpty(oldPhoneNum)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("原手机号码为空-用户修改手机号码失败");
			return generalResponse;
		}
		if (StringUtil.isEmpty(newPhoneNum)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("新手机号码为空-用户修改手机号码失败");
			return generalResponse;
		}
		// 手机号校验
		boolean flag = CheckPhone.isPhone(oldPhoneNum);
		if (!flag) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("原手机号码格式不正确-用户修改手机号码失败");
			return generalResponse;
		}
		flag = CheckPhone.isPhone(newPhoneNum);
		if (!flag) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("新手机号码格式不正确-用户修改手机号码失败");
			return generalResponse;
		}
		// 保存到数据库
		tbSystemUser.setPhone(newPhoneNum);
		tbSystemUserMapper.updateByPrimaryKeySelective(tbSystemUser);

		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("用户修改手机号码成功");
		return generalResponse;
	}

	@Override
	public GeneralResponse changeTbSystmUserIconApp(Map<String, String> dataMap) {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");// 用户id
		String img = dataMap.get("img");// 图片 base64
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-修改用户头像失败");
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-修改用户头像失败");
			return generalResponse;
		}
		if (!StringUtil.isEmpty(img)) {// 运单不为空
			LogisticsResult base64UpLoad = ImageUtil.base64UpLoad("upload/photo", img.trim(), null);
			if (base64UpLoad.getStatus() == 200) {
				img = base64UpLoad.getData().toString();
			} else {
				generalResponse.setState(Constants.NO);
				generalResponse.setObj(null);
				generalResponse.setMsg("头像上传失败-修改用户头像失败");
				return generalResponse;
			}
		}
		tbSystemUser.setUserIcon(img);

		int row = tbSystemUserMapper.updateByPrimaryKeySelective(tbSystemUser);

		// 更新用户头像
		if (row != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("修改用户头像失败");
			return generalResponse;
		} else {
			generalResponse.setState(Constants.YES);
			generalResponse.setMsg("修改用户头像成功");
			return generalResponse;
		}
	}
}
