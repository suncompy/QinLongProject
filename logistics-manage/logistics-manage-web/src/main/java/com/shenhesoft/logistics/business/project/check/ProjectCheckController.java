package com.shenhesoft.logistics.business.project.check;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shenhesoft.logistics.business.helpPojo.ProjectOperationDetail;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.JsonUtils;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.manage.branchgroup.BranchGroupService;
import com.shenhesoft.logistics.manage.cargo.interfaces.CargomanagementService;
import com.shenhesoft.logistics.manage.interfaces.TrainStationService;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.cargo.TbCargo;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * @description:项目核查
 * 
 * @author shilvfei
 * 
 * @date 2017年12月25日
 */
@Controller
@RequestMapping("/project")
public class ProjectCheckController {

	@Autowired
	private ProjectCheckService checkService;
	
	@Value("${PAGE_NUM}")
	private Integer PAGE_NUM;
	@Value("${PROJECT_PAGE_LIMIT}")
	private Integer PROJECT_PAGE_LIMIT;
	
	/**
	 * @description 获取项目信息
	 * @date 2017年12月9日
	 * @author shilvfei
	 * @param model
	 * @return
	 */
	@RequestMapping("/projectCheck")
	public String getProjectCheck(){
		//分发转向
		return "/html/business/project/projectCheck";
	}
	
	/**
	 * @description 分页获取项目核查的项目列表
	 * @date 2018年1月8日
	 * @author shilvfei
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping("/listProjectCheckByPage")
	@ResponseBody
	public LogisticsResult listProjectCheckByPage(HttpSession session,Integer page,String search){
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");
		TbProject project = JsonUtils.jsonToPojo(search, TbProject.class);
		DataGridResult projectChecks = checkService.listProjectCheckByPage(page,PROJECT_PAGE_LIMIT,user.getId(),project);
		return LogisticsResult.ok(projectChecks);
	}
	
	
	
}
