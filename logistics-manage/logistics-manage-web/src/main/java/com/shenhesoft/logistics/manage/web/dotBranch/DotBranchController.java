package com.shenhesoft.logistics.manage.web.dotBranch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.FormUtil;
import com.shenhesoft.logistics.common.util.JsonUtils;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.common.util.ResultCodeUtils;
import com.shenhesoft.logistics.common.util.ResultContentUtils;
import com.shenhesoft.logistics.manage.branchgroup.BranchGroupService;
import com.shenhesoft.logistics.manage.employee.EmployeeInformationService;
import com.shenhesoft.logistics.manage.helpPojo.DotBranchDetail;
import com.shenhesoft.logistics.manage.interfaces.TrainStationService;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStation;
import com.shenhesoft.logistics.manage.search.TbBranchGroupSearch;

/**
 * @description:网点分支
 * 
 * @author shilvfei
 * 
 * @date 2017年12月12日
 */
@Controller
@RequestMapping("/humanOrganization")
public class DotBranchController {
	
	/**
	 * 网点分支Mapper
	 */
	@Autowired
	private BranchGroupService doBranchService;
	
	@Autowired
	private TrainStationService trainStationService;
	
	@Autowired
	private EmployeeInformationService employeeInformationService ;
	
	@Value("${PAGE_NUM}")
	private Integer PAGE_NUM;
	@Value("${DOT_BRANCH_PAGE_LIMIT}")
	private Integer DOT_BRANCH_PAGE_LIMIT;
	
	@RequestMapping("/dotBranch")
	public String dotBanch(Model model){
	 	Map<String, Object> map = new HashMap<>();
	 	map.put("status", Constants.DOT_BRANCH_STATUS_YES);
/*	    DataGridResult dataGridResult =doBranchService.getDotBranchs(PAGE_NUM, DOT_BRANCH_PAGE_LIMIT, map);
	 	
	 	model.addAttribute("dotBranchs", dataGridResult);*/
	 	//获取所有一级和二级的网点分支
	 	List<Byte> levels = new ArrayList<>();
	 	levels.add(Constants.BRANCH_LEVEL_ONE);
	 	levels.add(Constants.BRANCH_LEVEL_TWO);//二级
	 	map.put("levels",levels);
	 	List<DotBranchDetail> brachGroups =doBranchService.getDotBranchs(map);
		model.addAttribute("brachGroups", brachGroups);
		
	 	//获取所有火车站点
		List<TbTrainStation> trainStations = trainStationService.selectThreeTrainStationByLevel(Constants.SITE_LEVEL_THREE);
		model.addAttribute("trainStations", trainStations);
		//获取所有的负责人
		List<TbSystemUser> reponsiblers = employeeInformationService.selectReponsibler(Constants.CUSTOMER_STATUS_YES);
		model.addAttribute("reponsiblers", reponsiblers);
		return "/html/manage/humanOrganization/dotBranch";
	}
	
	/**
	 * @description 更新网点分支
	 * @date 2017年12月21日
	 * @author shilvfei
	 * @param project
	 * @param result
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/updateDotBranch", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult updateDotBranch(@Valid DotBranchDetail branchGroup,BindingResult result){
		if (result.hasErrors()) {
			FieldError error = result.getFieldError();
			if(error.getCode().equals("typeMismatch")){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"数据类型不匹配!请检查您填写的数据"); 
			}
			return LogisticsResult.build(ResultCodeUtils.INSERT_EMPLOYEE_EMPTY, result.getFieldError().getDefaultMessage()); 
		}
		LogisticsResult logisticsResult =  doBranchService.update(branchGroup);
		
		return logisticsResult;
	}
	
	/**
	 * @description 删除网点分支
	 * @date 2017年12月21日
	 * @author shilvfei
	 * @param project
	 * @param result
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/delDotBranch", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult delDotBranch(Integer id){
		if(id==null){
			return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,ResultContentUtils.INSERT_EMPTY);
		}
		LogisticsResult logisticsResult =  doBranchService.del(id);
		
		return logisticsResult;
	}
	
	/**
	 * @description 新建网点分支
	 * @date 2017年12月21日
	 * @author shilvfei
	 * @param project
	 * @param result
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/addDotBranch", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult addDotBranch(@Valid DotBranchDetail branchGroup,BindingResult result){
		if (result.hasErrors()) {
			FieldError error = result.getFieldError();
			if(error.getCode().equals("typeMismatch")){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"数据类型不匹配!请检查您填写的数据"); 
			}
			return LogisticsResult.build(ResultCodeUtils.INSERT_EMPLOYEE_EMPTY, result.getFieldError().getDefaultMessage()); 
		}
		LogisticsResult logisticsResult =  doBranchService.saveBranchGroup(branchGroup);
		
		return logisticsResult;
	}
	
	/**
	 * @description 根据一级站点获取二级站点
	 * @date 2017年12月24日
	 * @author shilvfei
	 * @param branchGroup
	 * @param result
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/getDotBranchByFirstLevel", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult getDotBranchByFirstLevel(Integer id){
		if(id==null){
			return LogisticsResult.build(404,ResultContentUtils.INSERT_EMPTY);
		}
		LogisticsResult logisticsResult =  doBranchService.getDotBranchByFirstLevel(id);
		return logisticsResult;
	}
	
	/**
	 * @description 根据id查询分支
	 * @date 2017年12月24日
	 * @author shilvfei
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getDotBranchById", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult getDotBranchById(Integer id) {
		if(id==null){
			return LogisticsResult.build(404, "查询不到该网点分支");
		}
		return doBranchService.getDotBranchById(id);
	}
	
	/**
	 * @description 分页获取网点分支
	 * @date 2017年12月24日
	 * @author shilvfei
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/listDotBranchByPage", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult listDotBranchByPage(Integer page,String search) {
		TbBranchGroupSearch branchGroupSearch = JsonUtils.jsonToPojo(search, TbBranchGroupSearch.class);
		Map<String, Object> map = FormUtil.populate(branchGroupSearch);
		map.put("status", Constants.DOT_BRANCH_STATUS_YES);
	    map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		return LogisticsResult.ok(doBranchService.getDotBranchs(page, DOT_BRANCH_PAGE_LIMIT, map));
	}
	
	/**
	 * @description 根据级别获取网点分支
	 * @date 2017年12月24日
	 * @author shilvfei
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/listDotBranchByLevel", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult listDotBranchByLevel(Byte level) {
		if(level==null){
			return LogisticsResult.build(404, "查询不到此级别的网点分支");
		}
		List<TbBranchGroup> list = doBranchService.selectDotBranchByLevel(level);
		return LogisticsResult.ok(list);
	}
	
	/**
	 * @description 根据用户id获取网点分支
	 * @date 2017年12月24日
	 * @author shilvfei
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/listDotBranchByUserId", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult listDotBranchByUserId() {
		//Integer userId = AppSession.getCurrentUserId();
		Map<String, Object> map = new HashMap<>();
		map.put("status", Constants.DOT_BRANCH_STATUS_YES);
	    map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<DotBranchDetail> branchGroups = doBranchService.getDotBranchs(map);
		return LogisticsResult.ok(branchGroups);
	}
	
	/**
	 * @description 新建网点分支
	 * @date 2017年12月21日
	 * @author shilvfei
	 * @param project
	 * @param result
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/addTopLevelDotBranch", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult addTopLevelDotBranch(@Valid DotBranchDetail branchGroup,BindingResult result){
		if (result.hasErrors()) {
			FieldError error = result.getFieldError();
			if(error.getCode().equals("typeMismatch")){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"数据类型不匹配!请检查您填写的数据"); 
			}
			return LogisticsResult.build(ResultCodeUtils.INSERT_EMPLOYEE_EMPTY, result.getFieldError().getDefaultMessage()); 
		}
		LogisticsResult logisticsResult =  doBranchService.saveBranchGroup(branchGroup);
		
		return logisticsResult;
	}
	
	
}
