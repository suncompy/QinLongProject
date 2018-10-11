package com.shenhesoft.logistics.manage.branchgroup.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shenhesoft.logistics.business.helpPojo.AreaHelpPojo;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.JsonUtils;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.common.util.PinyinUtils;
import com.shenhesoft.logistics.common.util.ResultCodeUtils;
import com.shenhesoft.logistics.common.util.StringUtil;
import com.shenhesoft.logistics.finance.BranchGroupLink;
import com.shenhesoft.logistics.finance.mapper.BranchGroupLinkMapper;
import com.shenhesoft.logistics.manage.branchgroup.BranchGroupService;
import com.shenhesoft.logistics.manage.employee.EmployeeInformationService;
import com.shenhesoft.logistics.manage.helpPojo.DotBranchDetail;
import com.shenhesoft.logistics.manage.helpPojo.EmployInfo;
import com.shenhesoft.logistics.manage.mapper.BranchGroupMapper;
import com.shenhesoft.logistics.manage.mapper.TbBranchGroupMapper;
import com.shenhesoft.logistics.manage.mapper.TbProjectMapper;
import com.shenhesoft.logistics.manage.mapper.TbSystemUserMapper;
import com.shenhesoft.logistics.manage.mapper.TbTrainStationMapper;
import com.shenhesoft.logistics.manage.mapper.TbUserBranchGroupMapper;
import com.shenhesoft.logistics.manage.pojo.anchoredCompany.TbAnchoredCompany;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroupExample;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbUserBranchGroup;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbUserBranchGroupExample;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.project.TbProjectExample;
import com.shenhesoft.logistics.manage.pojo.role.TbRole;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroupExample.Criteria;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUserExample;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStation;
import com.shenhesoft.logistics.manage.role.RoleService;
import com.shenhesoft.logistics.manage.search.TbBranchGroupSearch;
@Service
public class BranchGroupServiceImpl implements BranchGroupService {

	@Autowired
	private TbBranchGroupMapper tbBranchGroupMapper;
	
	@Autowired
	private TbUserBranchGroupMapper userBranchGroupMapper;
	
	@Autowired
	private TbTrainStationMapper stationMapper;

	@Autowired
	private TbSystemUserMapper systemUserMapper;
	
	@Autowired
	private TbProjectMapper projectMapper;
	
	@Autowired
	private BranchGroupLinkMapper branchGroupLinkMapper;
	
	@Autowired
	private BranchGroupMapper branchGroupMapper;
	
	@Autowired
	private EmployeeInformationService employeeInformationService ;

	@Autowired
	private RoleService roleService;
	
	/**
	 * @description 查询网点分支
	 * @date 2017年12月24日
	 * @author shilvfei
	 * @param page
	 * @param limit
	 * @param status
	 * @return
	 */
	@Override
	public DataGridResult getDotBranchs(Integer page, Integer limit,Map<String, Object> form) {
		//获取状态为可用的所有网点分支
		PageHelper.startPage(page,limit);
		List<DotBranchDetail> list = branchGroupMapper.getBranchGroups(form);
		convertDotBranch(list);
		 // 取分页信息
        PageInfo<DotBranchDetail> pageInfo = new PageInfo<DotBranchDetail>(list);
        long total = pageInfo.getTotal(); //获取总记录数
		return new DataGridResult(total, list,limit);
	}
	
	@Override
	public List<DotBranchDetail> getDotBranchs(Map<String, Object> form) {
		//获取状态为可用的所有网点分支
		form.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<DotBranchDetail> list = branchGroupMapper.getBranchGroups(form);
		convertDotBranch(list);
		return list;
	}
	
	/**
	 * @description 获取详细网点分支
	 * @date 2018年1月11日
	 * @author shilvfei
	 * @param list
	 */
	private void convertDotBranch(List<DotBranchDetail> list) {
		for (DotBranchDetail branchDetail : list) {
			Integer branchId = branchDetail.getId();
			//处理地址
			String addressCode = branchDetail.getAreaId();
			String[] split = addressCode.split(",");
			if(split!=null && split.length!=0){
				branchDetail.setProvince(split[0]);
				branchDetail.setCity(split[1]);
				addressCode=split[0]+split[1];
				if(split.length==3 && !split[2].equals("null")){
					branchDetail.setDistrict(split[2]);
					addressCode=addressCode+split[2];
				}
			}
			branchDetail.setAreaId(addressCode);
			//获取网点归属
			Integer ascriptionId = branchDetail.getAscriptionId();
			TbBranchGroup ascriptionBranchGroup = tbBranchGroupMapper.selectByPrimaryKey(ascriptionId);    
			branchDetail.setBranchGroup(ascriptionBranchGroup);
			//查询网点下的员工数
			TbSystemUserExample example = new TbSystemUserExample();
			com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUserExample.Criteria createCriteria = example.createCriteria();
			createCriteria.andBranchGroupIdEqualTo(branchId);
			createCriteria.andWorkStatusEqualTo(Constants.WORK_STATUS_YES);//在职
			int employeeNum = systemUserMapper.countByExample(example);
			branchDetail.setEmployeeNum(employeeNum);
			
			//查询负责人信息
			List<TbSystemUser> responsiblers = userBranchGroupMapper.selectResponsiblerByBranchId(branchId);
			String reponsibler="";
			for (TbSystemUser tbSystemUser : responsiblers) {
				reponsibler=reponsibler+tbSystemUser.getName()+"  ";
			}
			branchDetail.setResponsibler(reponsibler);
			branchDetail.setResponsiblers(responsiblers);
			//查询火车站
			Integer trainStationId =  branchDetail.getRelationTrainLocationId();
			TbTrainStation trainStation = stationMapper.selectByPrimaryKey(trainStationId);
			branchDetail.setStation(trainStation);
			//查询发运地
			String relationBeginLocation = branchDetail.getRelationBeginLocation();
			
			if(StringUtil.isNotEmpty(relationBeginLocation)){
				List<AreaHelpPojo> areaList = JsonUtils.jsonToList(relationBeginLocation, AreaHelpPojo.class);
				branchDetail.setRelationBeginAddress(areaList);
				relationBeginLocation="";
				for (AreaHelpPojo areaHelpPojo : areaList) {
					String areaAddress=areaHelpPojo.getProvince()+areaHelpPojo.getCity();
					if(areaHelpPojo.getDistrict()!=null){
						areaAddress = areaAddress+areaHelpPojo.getDistrict();
					}
					relationBeginLocation=relationBeginLocation+areaAddress+";";	
				}
			}
			branchDetail.setRelationBeginLocation(relationBeginLocation);
		}
	}

	/**
	 * @description 根据级别获取网点分支
	 * @date 2018年1月11日
	 * @author shilvfei
	 * @param list
	 */
	@Override
	public List<TbBranchGroup> selectDotBranchByLevel(byte level) {
		TbBranchGroupExample example= new TbBranchGroupExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constants.DOT_BRANCH_STATUS_YES);
		if(level == Constants.BRANCH_LEVEL_ONE){
			List<Byte> levels = new ArrayList<>();
			levels.add(Constants.BRANCH_LEVEL_ONE);//一级
			levels.add(Constants.BRANCH_LEVEL_TWO);//二级
			criteria.andLevelIn(levels);
		}else{
			criteria.andLevelEqualTo(level);
		}
		return tbBranchGroupMapper.selectByExample(example);
	}

	/**
	 * @description 保存网点分支
	 * @date 2018年1月11日
	 * @author shilvfei
	 * @param list
	 */
	@Override
	public LogisticsResult saveBranchGroup(DotBranchDetail branchDetail) {
		//校验数据的合法性
		//校验用户名是否存在 //编号是否重复
		LogisticsResult checkBranchGroupName = checkDotBranchInfo(branchDetail);
		if (checkBranchGroupName.getStatus()!=200) {
			return checkBranchGroupName;
		}
		
		String addressCode = branchDetail.getProvince()+","+branchDetail.getCity()+","+branchDetail.getDistrict();
		//存储网点分支的区域
		branchDetail.setAreaId(addressCode);
		
		//设置网点分支的简称代码
		String shortCode = PinyinUtils.getAlpha(branchDetail.getShortName());
		branchDetail.setShortCode(shortCode);
		
		//获取归属id
		Integer ascriptionId = branchDetail.getAscriptionId();
		if(ascriptionId==null && AppSession.getCurrentUserId()!=1){//  不是超级管理员 不允许添加顶级机构
			return LogisticsResult.build(401, "请选择网点归属!");
		}
		if(ascriptionId!=null && ascriptionId!=0){
			TbBranchGroup ascriptionBranchGroup = tbBranchGroupMapper.selectByPrimaryKey(ascriptionId);
			if(ascriptionBranchGroup!=null){
				String name = ascriptionBranchGroup.getName();
				branchDetail.setAscriptionName(name);
				Byte level = (byte) (ascriptionBranchGroup.getLevel()+1);
				branchDetail.setLevel(level);
			}
		}/*else{
			branchDetail.setLevel((byte)0);//顶级机构
			branchDetail.setAscriptionId(0);//顶级机构
		}*/
		
		if(branchDetail.getLevel()==Constants.BRANCH_LEVEL_ONE){
			if(StringUtils.isBlank(branchDetail.getUserName())){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPLOYEE_EMPTY,"管理员信息 账户不能为空!");
			}
			if(StringUtils.isBlank(branchDetail.getPasswd())){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPLOYEE_EMPTY,"管理员信息 密码不能为空!");
			}
			if(StringUtils.isBlank(branchDetail.getPhone())){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPLOYEE_EMPTY,"管理员信息 手机号不能为空!");
			}
		}
		
		
		TbBranchGroup branchGroup = branchDetail;
		//发运地直接存储json
		String relationBeginLocation = branchGroup.getRelationBeginLocation();
		if(relationBeginLocation!=null){
			List<AreaHelpPojo> relationBeginLocationList = JsonUtils.jsonToList(relationBeginLocation, AreaHelpPojo.class);
			for (int i = 0; i < relationBeginLocationList.size(); i++) {
				if(relationBeginLocationList.get(i).getProvince()==null){
					relationBeginLocationList.remove(i);
				}
			}
			if(relationBeginLocationList== null || relationBeginLocationList.size()==0){
				// return LogisticsResult.build(404, "关联发运地不能为空");
			}
			
			branchGroup.setRelationBeginLocation( JsonUtils.toJson(relationBeginLocationList));
		}
		
		branchGroup.setStatus(Constants.DOT_BRANCH_STATUS_YES);
		Date date = new Date();
		branchGroup.setCreateDate(date);
		
		//插入到数据库
		tbBranchGroupMapper.insertSelective(branchGroup);
		
		boolean flag = false;
		
		Integer branchGroupId = branchGroup.getId();
		if(branchGroup.getLevel()==Constants.BRANCH_LEVEL_ONE){//如果新增的是顶级机构 同步到挂靠公司
			//校验挂靠公司名称是否存在
			Map<String, Object> form = new HashMap<>();
			form.put("name", branchGroup.getName());
			List<TbAnchoredCompany> anchoredCompanies = tbBranchGroupMapper.getAnchoredCompanys(form);
			
			if(anchoredCompanies!=null && anchoredCompanies.size()!=0){
				return LogisticsResult.build(404,"网点分支名称已存在!");
			}
			
			form.clear();
			form.put("phone",branchDetail.getPhone());
			List<TbAnchoredCompany> phoneCheck = tbBranchGroupMapper.getAnchoredCompanys(form);
			
			if(phoneCheck!=null && phoneCheck.size()!=0){
				return LogisticsResult.build(404,"网点分支联系方式已存在!");
			}
			
			TbAnchoredCompany anchoredCompany = new TbAnchoredCompany();
			anchoredCompany.setCreateDate(date);
			anchoredCompany.setName(branchGroup.getName());
			anchoredCompany.setAddress(branchGroup.getAreaId()+branchGroup.getAddress());
			anchoredCompany.setStatus("0");
			anchoredCompany.setBranchId(branchGroup.getId());
			anchoredCompany.setPhone(branchDetail.getPhone());
			int result = tbBranchGroupMapper.insertAnchoredCompany(anchoredCompany);
			
			if(result!=1){
				throw new RuntimeException();
			}
			
			//增加角色
			TbRole role = new TbRole();//管理员角色
			role.setPerssionIds(Constants.PERSSION_ALL_ID); //管理员角色
			role.setName("admin");
			role.setIsDefault(Constants.ROLE_DEFAULT_YES);
			role.setSysOrgCode(branchDetail.getCode());
			LogisticsResult insertRole = roleService.insertRole(role);
			
			if(insertRole.getStatus()!=200){
				throw new RuntimeException();
			}
			
			TbRole responsiblerRole = new TbRole();//增加负责人角色
			responsiblerRole.setPerssionIds(Constants.PERSSION_ALL_ID); 
			responsiblerRole.setName("网点分支负责人");
			responsiblerRole.setIsDefault(Constants.ROLE_DEFAULT_YES);
			responsiblerRole.setSysOrgCode(branchDetail.getCode());
			insertRole = roleService.insertRole(responsiblerRole);

			if(insertRole.getStatus()!=200){
				throw new RuntimeException();
			}
			
			//增加用户
			EmployInfo systemUser = new EmployInfo();
			systemUser.setName(branchDetail.getUserName());
			systemUser.setAccount(branchDetail.getUserName());
			systemUser.setPasswd(DigestUtils.md5DigestAsHex(branchDetail.getPasswd().getBytes()));
			systemUser.setPhone(branchDetail.getPhone());
			systemUser.setBranchGroupId(branchGroupId);
			systemUser.setSysOrgCode(branchDetail.getCode());
			systemUser.setWorkStatus(Constants.WORK_STATUS_ADMIN);
			systemUser.setStartWorkDate(date);
			systemUser.setCreateDate(date);
			LogisticsResult addUserResult =  employeeInformationService.addEmployee(systemUser, role.getId());
			
			if(addUserResult.getStatus()!=200){
				throw new RuntimeException();
			}
			
			branchGroup.setResponsiblerid(systemUser.getId());
			tbBranchGroupMapper.updateByPrimaryKeySelective(branchGroup);//绑定管理员
			
			if(addUserResult.getStatus()!=200){
				tbBranchGroupMapper.deleteByPrimaryKey(branchGroupId);
				tbBranchGroupMapper.deleteAnchoredCompanyByKey(anchoredCompany.getId());
				List<Integer> ids = new ArrayList<>();
				ids.add(role.getId());
				ids.add(responsiblerRole.getId());
				roleService.delRole(ids);
				flag = true;
			}
		}
		
		//维护用户与网点分支的中间表
		TbUserBranchGroup userBranchGroup = new TbUserBranchGroup();
		userBranchGroup.setBranchGroupId(branchGroupId);
		userBranchGroup.setUserId(branchGroup.getResponsiblerid());
		userBranchGroup.setType(Constants.DOT_BRANCH_RESPONSIBLER);
		userBranchGroupMapper.insert(userBranchGroup);
		
		if(flag){
			return  LogisticsResult.build(404,"增加顶级机构失败!");
		}
		
		BranchGroupLink branchGroupLink = new BranchGroupLink();
		branchGroupLink.setId(AppUtils.randomUUID());
		branchGroupLink.setRowId(branchGroupId.toString());
		branchGroupLink.setTabName("tb_branch_group");
		branchGroupLink.setTabComment("分支机构");
		branchGroupLink.setSysOrgCode(branchGroup.getCode());
		branchGroupLinkMapper.addBranchGroupLink(branchGroupLink);
		
		return LogisticsResult.ok();
	}

	/**
	 * @description 删除网点分支
	 * @date 2018年1月11日
	 * @author shilvfei
	 * @param list
	 */
	@Override
	public LogisticsResult del(Integer id) {
		//判断是否有子级网点 有的话 不能删除
		TbBranchGroupExample branchGroupExample = new TbBranchGroupExample();
		Criteria criteria = branchGroupExample.createCriteria();
		criteria.andAscriptionIdEqualTo(id);
		criteria.andStatusEqualTo(Constants.DOT_BRANCH_STATUS_YES);
		List<TbBranchGroup> list = tbBranchGroupMapper.selectByExample(branchGroupExample);
		if(list!=null && list.size()!=0){
			return LogisticsResult.build(404, "此网点有子级站点,不能删除！");
		}
		
		//判断是否关联项目
		TbProjectExample example=new TbProjectExample();
		com.shenhesoft.logistics.manage.pojo.project.TbProjectExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andDeleteFlagEqualTo((byte)0);
		createCriteria.andBranchGroupIdEqualTo(id);
		List<TbProject> projectList = projectMapper.selectByExample(example);
		if(projectList!=null && projectList.size()!=0){
			return LogisticsResult.build(404, "此网点有关联项目,不能删除！");
		}
		//逻辑删除
		TbBranchGroup branchGroup = tbBranchGroupMapper.selectByPrimaryKey(id);
		branchGroup.setStatus(Constants.DOT_BRANCH_STATUS_DEL);//已删除
		tbBranchGroupMapper.updateByPrimaryKeySelective(branchGroup);
		return LogisticsResult.ok();
	}

	/**
	 * @description 修改网点分支
	 * @date 2018年1月11日
	 * @author shilvfei
	 * @param list
	 */
	@Override
	public LogisticsResult update(DotBranchDetail branchDetail) {
		//判断修改的名称和编号是否和数据库一致
		//通过id查询此网点分支
		TbBranchGroup existGroup = tbBranchGroupMapper.selectByPrimaryKey(branchDetail.getId());
		
		if(!branchDetail.getName().equals(existGroup.getName())){
			LogisticsResult result = checkDotBranchInfo(branchDetail);
			if (result.getStatus()!=200) {//校验用户名是否存在 
				return result;
			}
		}
		
		String addressCode = branchDetail.getProvince()+","+branchDetail.getCity()+","+branchDetail.getDistrict();
		branchDetail.setAreaId(addressCode);
		String shortCode = PinyinUtils.getAlpha(branchDetail.getShortName());
		branchDetail.setShortCode(shortCode);
		
		TbBranchGroup branchGroup = branchDetail;
		branchGroup.setStatus(Constants.DOT_BRANCH_STATUS_YES);
		branchGroup.setCreateDate(new Date());
		Integer id = branchDetail.getId();
		
		// 自己不能归属自己
		if(branchDetail.getAscriptionId()==id){
			return LogisticsResult.build(404, "网点分支不能归属本身!请重新选择");
		}
		//判断是否归属自己的子级站点
		Integer ascriptionId = branchDetail.getAscriptionId();
		TbBranchGroupExample branchGroupExample = new TbBranchGroupExample();
		Criteria createCriteria = branchGroupExample.createCriteria();
		createCriteria.andAscriptionIdEqualTo(id);
		List<TbBranchGroup> list = tbBranchGroupMapper.selectByExample(branchGroupExample);
		boolean flag = false;
		if(list!=null && list.size()!=0){
			for (TbBranchGroup tbBranchGroup : list) {
				if(tbBranchGroup.getId()==ascriptionId){
					flag=true;
				}
			}
		}
		if(flag){
			return LogisticsResult.build(404, "网点分支不能归属本身的子级站点!请重新选择");
		}
		
		//获取归属id
		if(ascriptionId!=null && ascriptionId!=0){
			TbBranchGroup ascriptionBranchGroup = tbBranchGroupMapper.selectByPrimaryKey(ascriptionId);
			if(ascriptionBranchGroup!=null){
				String name = ascriptionBranchGroup.getName();
				branchDetail.setAscriptionName(name);
				Byte level = (byte) (ascriptionBranchGroup.getLevel()+1);
				branchDetail.setLevel(level);
			}
		}
		
		String relationBeginLocation = branchGroup.getRelationBeginLocation();
		List<AreaHelpPojo> relationBeginLocationList = JsonUtils.jsonToList(relationBeginLocation, AreaHelpPojo.class);
		for (int i = 0; i < relationBeginLocationList.size(); i++) {
			if(relationBeginLocationList.get(i).getProvince()==null){
				relationBeginLocationList.remove(i);
			}
		}
		if(relationBeginLocationList== null || relationBeginLocationList.size()==0){
			//return LogisticsResult.build(404, "关联发运地不能为空");
		}
		
		//删除之前所有绑定的负责人信息
		TbUserBranchGroupExample userBranchGroupExample = new TbUserBranchGroupExample();
		com.shenhesoft.logistics.manage.pojo.branchgroup.TbUserBranchGroupExample.Criteria criteria = userBranchGroupExample.createCriteria();
		criteria.andBranchGroupIdEqualTo(id);
		criteria.andTypeEqualTo(Constants.DOT_BRANCH_RESPONSIBLER);
		userBranchGroupMapper.deleteByExample(userBranchGroupExample);
		
		//更新负责人信息
		TbUserBranchGroup userBranchGroup = new TbUserBranchGroup();
		userBranchGroup.setBranchGroupId(id);
		userBranchGroup.setUserId(branchGroup.getResponsiblerid());
		userBranchGroup.setType(Constants.DOT_BRANCH_RESPONSIBLER);
		userBranchGroupMapper.insert(userBranchGroup);
		
		//更新网点分支
		int row  = tbBranchGroupMapper.updateByPrimaryKeySelective(branchGroup);
		//更新挂靠公司名称
		if(existGroup.getLevel()==Constants.BRANCH_LEVEL_ONE){//如果新增的是顶级机构 同步到挂靠公司
			Map<String, Object> form = new HashMap<>();
			form.put("name", branchDetail.getName());
			List<TbAnchoredCompany> companys = tbBranchGroupMapper.getAnchoredCompanys(form);
			
			if(companys!=null && companys.size()!=0){
				TbAnchoredCompany anchoredCompany = companys.get(0);
				anchoredCompany.setName(existGroup.getName());
				tbBranchGroupMapper.updateAnchoredCompany(anchoredCompany);
			}
		}
		
		/*if(branchGroup.getId()==1 && row != 0){//网点分支id必须为1
			tbBranchGroupMapper.updateCompanyName(branchGroup.getName());
		}*/
		
		return LogisticsResult.ok();
	}

	/**
	 * @description 通过id获取一级网点分支
	 * @date 2018年1月11日
	 * @author shilvfei
	 * @param list
	 */
	@Override
	public LogisticsResult getDotBranchByFirstLevel(Integer id) {
		//根据归属id查询子级分支机构
		TbBranchGroupExample branchGroupExample = new TbBranchGroupExample();
		Criteria criteria = branchGroupExample.createCriteria();
		criteria.andAscriptionIdEqualTo(id);
		criteria.andStatusEqualTo(Constants.DOT_BRANCH_STATUS_YES);
		List<TbBranchGroup> list = tbBranchGroupMapper.selectByExample(branchGroupExample);
		if(list==null || list.size()==0){
			return LogisticsResult.build(404,"下级无分支机构");
		}
		return LogisticsResult.ok(list);
	}
	
	/**
	 * @description 通过id获取网点分支明细
	 * @date 2018年1月11日
	 * @author shilvfei
	 * @param list
	 */
	@Override
	public LogisticsResult getDotBranchById(Integer id) {
		DotBranchDetail branchDetail = tbBranchGroupMapper.selectDotBranchDetailById(id);
		if(branchDetail==null ){
			return LogisticsResult.build(404,"无分支机构");
		}
		List<DotBranchDetail> branchDetails = new ArrayList<>();
		branchDetails.add(branchDetail);
		convertDotBranch(branchDetails);
		return LogisticsResult.ok(branchDetails.get(0));
	}
	
	/**
	 * @description 校验网点分支名称和编号是否重复
	 * @date 2018年1月11日
	 * @author shilvfei
	 * @param list
	 */
	public LogisticsResult checkDotBranchInfo(DotBranchDetail branchDetail) {
		
		Map<String, Object> map = new HashMap<>();
		
		//判断校验的数据类型
		if(branchDetail.getLevel()!=null && branchDetail.getLevel()==Constants.BRANCH_LEVEL_ONE){
			List<Byte> levels  = new ArrayList<>();
			levels.add(Constants.BRANCH_LEVEL_ONE);
			map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
			map.put("levels", levels);
		}
		map.put("userId", AppSession.getCurrentUserId());
		map.put("notContainStatus", Constants.DOT_BRANCH_STATUS_DEL);//不包含已删除的
		map.put("name", branchDetail.getName());
		//执行查询
		List<DotBranchDetail> list = branchGroupMapper.getBranchGroups(map);
		
		if (list != null && list.size() != 0) {
			return LogisticsResult.build(400, "网点分支名称已存在,请重新填写!");
		}
		
		map.remove("name");
		map.put("code", branchDetail.getCode());
		
		list = branchGroupMapper.getBranchGroups(map);
		if (list != null && list.size() != 0) {
			return LogisticsResult.build(400, "该编码已存在,请重新填写!");
		}
		
		map.remove("code");
		map.put("shortName", branchDetail.getShortName());
		list = branchGroupMapper.getBranchGroups(map);
		if (list != null && list.size() != 0) {
			return LogisticsResult.build(400, "该简称已存在,请重新填写!");
		}
		
		return LogisticsResult.ok();
	}
	
	@Override
	public List<TbBranchGroup> selectBranchGroup(Byte status) {
		TbBranchGroupExample example=new TbBranchGroupExample();
		if(status!=null){
			Criteria criteria = example.createCriteria();
			criteria.andStatusEqualTo(status);//获取正常使用的
		}
		return tbBranchGroupMapper.selectByExample(example);
	}

	/**
	 * @description 根据登录的用户获取网点分支
	 * @date 2018年1月5日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	@Override
	public List<TbBranchGroup> selectBranchGroupByUid(Integer id) {
		Map<String, Object> map = new HashMap<>();
		map.put("status", Constants.DOT_BRANCH_STATUS_YES);
		map.put("id", id);
		return tbBranchGroupMapper.selectDotBranchByUid(map);
	}

	@Override
	public boolean updateBranchGroupBeginOrStop(Map<String, Object> form) {
		int row = branchGroupMapper.updateBranchGroupBeginOrStop(form);
		
		//暂停挂靠公司
		
		if(row==0){
			return false;
		}
		return true;
	}

	@Override
	public LogisticsResult restPassWd(Map<String, Object> form) {
		int row = branchGroupMapper.restPassWd(form);
		if(row==0){
			return LogisticsResult.build(404);
		}
		return LogisticsResult.ok();
	}

	@Override
	public LogisticsResult insertTopDotBranch(DotBranchDetail branchGroup) {
		return saveBranchGroup(branchGroup);
	}
}
