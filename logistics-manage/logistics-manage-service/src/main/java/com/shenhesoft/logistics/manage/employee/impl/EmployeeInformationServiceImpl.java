package com.shenhesoft.logistics.manage.employee.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shenhesoft.logistics.app.App;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.common.util.ResultCodeUtils;
import com.shenhesoft.logistics.common.util.ResultContentUtils;
import com.shenhesoft.logistics.finance.BranchGroupLink;
import com.shenhesoft.logistics.finance.mapper.BranchGroupLinkMapper;
import com.shenhesoft.logistics.manage.employee.EmployeeInformationService;
import com.shenhesoft.logistics.manage.helpPojo.EmployInfo;
import com.shenhesoft.logistics.manage.mapper.TbBranchGroupMapper;
import com.shenhesoft.logistics.manage.mapper.TbRolePerssionMapper;
import com.shenhesoft.logistics.manage.mapper.TbRoleSystemuserMapper;
import com.shenhesoft.logistics.manage.mapper.TbSystemUserMapper;
import com.shenhesoft.logistics.manage.pojo.anchoredCompany.TbAnchoredCompany;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.permission.TbRolePerssion;
import com.shenhesoft.logistics.manage.pojo.role.TbRoleSystemuser;
import com.shenhesoft.logistics.manage.pojo.role.TbRoleSystemuserExample;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUserExample;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUserExample.Criteria;
import com.shenhesoft.logistics.manage.search.TbSystemSearch;

/**
 * @description:员工服务
 * 
 * @author shilvfei
 * 
 * @date 2017年12月9日
 */
@Service
public class EmployeeInformationServiceImpl implements EmployeeInformationService{

	@Autowired
	private TbSystemUserMapper systemUserMapper;
	
	@Autowired
	private TbRoleSystemuserMapper roleSystemuserMapper;
	
	@Autowired
	private TbBranchGroupMapper branchGroupMapper;
	
	@Autowired
	private BranchGroupLinkMapper branchGroupLinkMapper;
	
	@Override
	public DataGridResult listEmployByPage(Integer page, Integer limit,TbSystemSearch systemSearch) {
		
		TbSystemUserExample example = new TbSystemUserExample();
		
		// 按照创建时间排序
		example.setOrderByClause("createDate desc");
		Criteria criteria = example.createCriteria();
		
		// 分页处理，显示第一页的20条数据
		PageHelper.startPage(page, limit);
		criteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
		criteria.andTabNameEqualTo("tb_system_user");
		systemUserCriteria(systemSearch, criteria);// 条件查询
		List<EmployInfo> list = systemUserMapper.selectEmployInfoByExample(example);// 查询
		
		getAreaAndBranchGroup(list);
		
		// 取分页信息
		PageInfo<EmployInfo> pageInfo = new PageInfo<EmployInfo>(list);
		return new DataGridResult(pageInfo.getTotal(), list, limit);
	}

	
	/**
	 * @description 获取区域与网点分支
	 * @date 2018年1月5日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	private void getAreaAndBranchGroup(List<EmployInfo> list) {
		for (EmployInfo employInfo : list) {
			String addressCode = employInfo.getAreaCode();
			if(addressCode!=null){
				// 处理省市区
				String[] split = addressCode.split(",");
				if (split != null && split.length != 0) {
					employInfo.setProvince(split[0]);
					employInfo.setCity(split[1]);
					addressCode = split[0] + split[1];
					if (split.length == 3 && !split[2].equals("null")) {
						employInfo.setDistrict(split[2]);
						addressCode = addressCode + split[2];
					}
					employInfo.setAreaCode(addressCode);
				}
			}
			// 获取网点分支信息
			Integer branchGroupId = employInfo.getBranchGroupId();
			if (branchGroupId != null && branchGroupId != 0) {
				TbBranchGroup branchGroup = branchGroupMapper.selectByPrimaryKey(branchGroupId);
				employInfo.setGroupId(branchGroupId);
				employInfo.setGroupName(branchGroup.getName());
			}
		}
	}


	/**
	 * @description 添加员工信息
	 * @date 2018年1月5日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	@Override
	public LogisticsResult addEmployee(EmployInfo employ, Integer roleId) {
		// 校验账号是否存在
		LogisticsResult checkAccountPhone = checkUserInfo(employ.getPhone(), 1);
		boolean flag1 = (boolean) checkAccountPhone.getData();
		if (!flag1) {
			return LogisticsResult.build(400, "手机号已存在");
		}
		// 创建时间
		employ.setCreateDate(new Date());
		// 员工所在区域地址
		if(StringUtils.isNotBlank(employ.getProvince())){
			String addressCode = employ.getProvince() + "," + employ.getCity() + "," + employ.getDistrict();
			employ.setAreaCode(addressCode);
		}
		// 入职时间
		employ.setStartWorkDate(employ.getStartWorkDate());
		// 所属机构
		if (employ.getBranchGroupId() == null) {
			employ.setBranchGroupId(0);// 0代表没有网点分支
		}else{
			Integer branchGroupId = employ.getBranchGroupId();
			TbBranchGroup branchGroup = branchGroupMapper.selectByPrimaryKey(branchGroupId);
			if(branchGroup!=null && branchGroup.getLevel()==Constants.BRANCH_LEVEL_ONE){
				String name = branchGroup.getName();
				Map<String, Object> form = new HashMap<>();
				form.put("name", name);
				List<TbAnchoredCompany> anchoredCompanys = branchGroupMapper.getAnchoredCompanys(form);
				TbAnchoredCompany company = anchoredCompanys.get(0);
				employ.setCompanyId(company.getId());
			}
		}
		
		//登录账户不能重复
		String account = employ.getAccount();
		LogisticsResult checkAccount = checkUserInfo(account, 2);
		if (!(boolean) checkAccount.getData()) {
			return LogisticsResult.build(400, "此账户已存在!");
		}
		
		// 插入到数据库
		int result1 = systemUserMapper.insert(employ);
		if (result1 == 0) {
			return LogisticsResult.build(400);
		}
		Integer uid = employ.getId();
		try {
			// 插入到用户角色中间表
			TbRoleSystemuser roleSystemuser = new TbRoleSystemuser();
			roleSystemuser.setUserId(uid);
			roleSystemuser.setRoleId(roleId);
			int result2 = roleSystemuserMapper.insert(roleSystemuser);
			
			//插入BranchGroupLink表
			Integer branchGroupId = employ.getBranchGroupId();
			TbBranchGroup branchGroup = branchGroupMapper.selectByPrimaryKey(branchGroupId);
			BranchGroupLink branchGroupLink = new BranchGroupLink();
		    branchGroupLink.setId(AppUtils.randomUUID());
		    branchGroupLink.setRowId(uid.toString());
		    branchGroupLink.setTabName("tb_system_user");
		    branchGroupLink.setTabComment("公司内部用户");
		    if(branchGroupId == null || branchGroup == null || branchGroup.getCode()==null){
		    	branchGroupLink.setSysOrgCode(AppSession.getCurrentSysOrgCode());
		    }else{
		    	branchGroupLink.setSysOrgCode(branchGroup.getCode());
		    }
		    branchGroupLinkMapper.addBranchGroupLink(branchGroupLink);
			
			if (result2 == 0) {
				systemUserMapper.deleteByPrimaryKey(uid);
			}
		} catch (Exception e) {
			systemUserMapper.deleteByPrimaryKey(uid);
			e.printStackTrace();
			return LogisticsResult.build(ResultCodeUtils.CONNECT_DB_FAIL, ResultContentUtils.CONNECT_DB_FAIL);
		}
		return LogisticsResult.ok(employ.getId());
	}
	
	//校验账号是否存在
	@Override
	public LogisticsResult checkUserInfo(String param,int type) {
		TbSystemUserExample systemUserExample  = new TbSystemUserExample();
	    Criteria criteria = systemUserExample.createCriteria();
		//判断校验的数据类型
		//1、2分别代表username、phone
		if (type == 1) {
			criteria.andPhoneEqualTo(param);
		}else if(type==2){
			criteria.andAccountEqualTo(param);
		}
		
		//执行查询
		List<TbSystemUser> list = systemUserMapper.selectByExample(systemUserExample);
		if (list == null || list.size() == 0) {
			return LogisticsResult.ok(true);
		}
		return LogisticsResult.ok(false);
	}

	/**
	 * @description 删除员工信息
	 * @date 2018年1月5日
	 * @author shilvfei
	 * @param  
	 * @return
	 */
	@Override
	public LogisticsResult delEmployee(List<Integer> ids) {
		try {
			for (Integer uid : ids) {
				TbSystemUser systemUser = new TbSystemUser();
				systemUser.setId(uid);
				systemUser.setWorkStatus(Constants.WORK_STATUS_NO);
				systemUser.setLeaveOfficeDate(new Date());//更新离职时间
				systemUserMapper.updateByPrimaryKeySelective(systemUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return LogisticsResult.build(400);
		}
		return LogisticsResult.ok();
	}

	/**
	 * @description 更新员工信息
	 * @date 2018年1月5日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	@Override
	public LogisticsResult updateEmployee(EmployInfo employ, Integer roleId) {
		//校验手机号
		//校验账号是否存在
		LogisticsResult checkAccountTeam = checkUserInfo(employ.getPhone(), 1);
		
		boolean flag1 = (boolean) checkAccountTeam.getData();
		String phone = employ.getPhone();
		Integer id = employ.getId();
		TbSystemUser systemUser = systemUserMapper.selectByPrimaryKey(id);
		if(systemUser.getPhone().equals(phone)){//判断手机号是否与数据库一致
			flag1=true;
		}
		if (!flag1) {
			return LogisticsResult.build(400, "手机号重复");
		}
		//所属机构
		if(employ.getBranchGroupId()==null){
			employ.setBranchGroupId(0);//0代表没有网点分支
		}
		//更新省市区
		String areaCode = employ.getProvince()+","+employ.getCity()+","+employ.getDistrict();
		employ.setAreaCode(areaCode);
		TbSystemUser updateUser = employ;
		updateUser.setAccount(updateUser.getPhone());	//设置登录账户为手机会
		updateUser.setPasswd(DigestUtils.md5DigestAsHex(updateUser.getPhone().getBytes()));	//设置密码为手机号
		systemUserMapper.updateByPrimaryKeySelective(updateUser);
		//更新角色
		//插入到用户角色中间表
		TbRoleSystemuserExample systemUserExample  = new TbRoleSystemuserExample();
	    com.shenhesoft.logistics.manage.pojo.role.TbRoleSystemuserExample.Criteria criteria = systemUserExample.createCriteria();
	    criteria.andUserIdEqualTo(employ.getId());
	    List<TbRoleSystemuser> list = roleSystemuserMapper.selectByExample(systemUserExample);
	    if(list!=null && list.size()!=0){
	    	TbRoleSystemuser tbRoleSystemuser = list.get(0);
	    	tbRoleSystemuser.setRoleId(roleId);
	    	roleSystemuserMapper.updateByPrimaryKeySelective(tbRoleSystemuser);
	    }
		return LogisticsResult.ok();
	}

	@Override
	public LogisticsResult getEmployeeById(Integer id) {
		EmployInfo employInfo = systemUserMapper.selectEmployInfoBySid(id);
		if(employInfo==null){
			return LogisticsResult.build(404, "修改的员工信息不存在");
		}
		List<EmployInfo> list = new ArrayList<>();
		list.add(employInfo);
		getAreaAndBranchGroup(list);
		return LogisticsResult.ok(list.get(0));
	}

	@Override
	public List<TbSystemUser> selectReponsibler(byte status) {
		TbSystemUserExample systemUserExample  = new TbSystemUserExample();
	    Criteria criteria = systemUserExample.createCriteria();
		criteria.andWorkStatusEqualTo(status);
		criteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
		criteria.andTabNameEqualTo("tb_system_user");
		return systemUserMapper.selectReponsibler(systemUserExample);
	}
	
	/**
	 * @description 员工信息的 查询条件
	 * @date 2018年1月13日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	private void systemUserCriteria(TbSystemSearch search, Criteria criteria) {

		if(search==null){
			return;
		}
		
		if (search.getWorkStatus() != null) {// 在职还是离职?
			List<Byte> statusList = new ArrayList<>();
			statusList.add(search.getWorkStatus());
			Date beginDate = null;
			Date endDate=null;
			if (StringUtils.isNotBlank(search.getBeginDate())
					&& StringUtils.isNotBlank(search.getEndDate())) {// 创建时间
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
				beginDate = DateUtils.str2Date(search.getBeginDate(), sdf);
				endDate = DateUtils.str2Date(search.getEndDate(), sdf);
			}
			if(search.getWorkStatus()==Constants.WORK_STATUS_YES){
				if(beginDate!=null && endDate!=null){
					criteria.andStartWorkDateGreaterThan(beginDate);
					criteria.andStartWorkDateLessThan(endDate);
				}
				statusList.add(Constants.WORK_STATUS_VACATION);
				statusList.add(Constants.WORK_STATUS_STOP);
			}else{//离职
				if(beginDate!=null && endDate!=null){
					criteria.andLeaveOfficeDateGreaterThan(beginDate);
					criteria.andLeaveOfficeDateLessThan(endDate);
				}
			}
			criteria.andWorkStatusIn(statusList);
		}
		if (StringUtils.isNotBlank(search.getName())) {// 员工姓名
			criteria.andNameLike(search.getName());
		}
		if (search.getSex() != null) {// 性别
			criteria.andSexEqualTo(search.getSex());
		}
		if (search.getIsMarry() != null) {// 婚否
			criteria.andIsMarryEqualTo(search.getIsMarry());
		}
		if (search.getEducation() != null) {// 学历
			criteria.andEducationEqualTo(search.getEducation());
		}
		if (StringUtils.isNotBlank(search.getPhone())) {// 联系方式
			criteria.andPhoneEqualTo(search.getPhone());
		}
		if (search.getBranchGroupId() != null) {// 所属分支
			criteria.andBranchGroupIdEqualTo(search.getBranchGroupId());
		}
		if (StringUtils.isNotBlank(search.getIdcard())) {// 身份证号
			criteria.andIdcardLike(search.getIdcard());
		}
		if (search.getRoleId() != null) {// 所属权限
			criteria.andRoleIdEqualTo(search.getRoleId());
		}
	}


	@Override
	public TbSystemUser getUserByUserNamePassword(String username, String password) {
		TbSystemUserExample systemUserExample = new TbSystemUserExample();
		Criteria criteria = systemUserExample.createCriteria();
		criteria.andAccountEqualTo(username);
		criteria.andPasswdEqualTo(DigestUtils.md5DigestAsHex(password.trim().getBytes()));
		List<TbSystemUser> list = systemUserMapper.selectByExample(systemUserExample);
		if(list ==null || list.size()==0){
			return null;
		}
		return list.get(0);
	}
}
