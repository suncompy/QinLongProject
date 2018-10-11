package com.shenhesoft.logistics.manage.user.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.manage.mapper.TbBranchGroupMapper;
import com.shenhesoft.logistics.manage.mapper.TbSystemUserMapper;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUserExample;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUserExample.Criteria;
import com.shenhesoft.logistics.manage.user.UserService;

/**
 * @description: 用户
 * 
 * @author shilvfei
 * 
 * @date 2017年12月7日
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private TbSystemUserMapper systemUserMapper;
	@Autowired
    private TbBranchGroupMapper branchGroupMapper;
	
	@Override
	public TbSystemUser updateLoginTime(String username) {
		TbSystemUserExample  systemUserExample = new TbSystemUserExample();
		Criteria criteria = systemUserExample.createCriteria();
		criteria.andAccountEqualTo(username);
		List<TbSystemUser> list = systemUserMapper.selectByExample(systemUserExample);
		if(list!=null && list.size()!=0){
			TbSystemUser tbSystemUser = list.get(0);
			tbSystemUser.setLastLoginDate(new Date());
			systemUserMapper.updateByPrimaryKey(tbSystemUser);
			TbBranchGroup tbBranchGroup = branchGroupMapper.selectByPrimaryKey(tbSystemUser.getBranchGroupId());
			tbSystemUser.setSysOrgCode(tbBranchGroup.getSysOrgCode());
			return tbSystemUser;
		}else {
			return null;
		}
	}

	@Override
	public TbSystemUser getTbSystemUserByUserNamePassword(String userName, String password) {
		TbSystemUser tbSystemUser = new TbSystemUser();
		tbSystemUser.setAccount(userName.trim());
		tbSystemUser.setPasswd(DigestUtils.md5DigestAsHex(password.trim().getBytes()));
		return systemUserMapper.getTbSystemUserByUserNamePassword(tbSystemUser);
	}

	@Override
	public TbSystemUser getTbSystemUserById(Integer userId) {
		return systemUserMapper.getTbSystemUserById(userId);
	}
	

	//校验账号是否存在
	@Override
	public LogisticsResult checkUserInfo(String param,int type) {
		TbSystemUserExample systemUserExample  = new TbSystemUserExample();
	    Criteria criteria = systemUserExample.createCriteria();
		//判断校验的数据类型
		//1、phone
		if (type == 1) {
			criteria.andPhoneEqualTo(param);
		}
		//执行查询
		List<TbSystemUser> list = systemUserMapper.selectByExample(systemUserExample);
		if (list == null || list.size() == 0) {
			return LogisticsResult.ok(true);
		}
		return LogisticsResult.ok(false);
	}

	@Override
	public TbSystemUser getUserByUserName(String userName) {
		TbSystemUserExample systemUserExample  = new TbSystemUserExample();
	    Criteria criteria = systemUserExample.createCriteria();
	    criteria.andAccountEqualTo(userName);
	    List<Byte> workStatus = new ArrayList<>();
	    workStatus.add((byte)0);
	    workStatus.add((byte)6);
	    criteria.andWorkStatusIn(workStatus);
	    List<TbSystemUser> userList = systemUserMapper.selectByExample(systemUserExample);
	    if(userList!=null && userList.size()!=0){
	    	return userList.get(0);
	    }
		return null;
	}

	@Override
	public Set<String> getPermissions(String userName) {
		return systemUserMapper.getPermissions(userName);
	}
}
