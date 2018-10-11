package pageTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shenhesoft.logistics.business.helpPojo.FreightSpace;
import com.shenhesoft.logistics.business.helpPojo.StuckHelpPojo;
import com.shenhesoft.logistics.business.mapper.TbOrderMapper;
import com.shenhesoft.logistics.business.mapper.TbStockMapper;
import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrder;
import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrderExample;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.JsonUtils;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.manage.helpPojo.CustomerInfo;
import com.shenhesoft.logistics.manage.helpPojo.DotBranchDetail;
import com.shenhesoft.logistics.manage.helpPojo.EmployInfo;
import com.shenhesoft.logistics.manage.helpPojo.TbStationBusinessHelp;
import com.shenhesoft.logistics.manage.mapper.TbBranchGroupMapper;
import com.shenhesoft.logistics.manage.mapper.TbBranchGroupTrainStationMapper;
import com.shenhesoft.logistics.manage.mapper.TbCustomerMapper;
import com.shenhesoft.logistics.manage.mapper.TbFreightYardMapper;
import com.shenhesoft.logistics.manage.mapper.TbProjectMapper;
import com.shenhesoft.logistics.manage.mapper.TbSystemUserMapper;
import com.shenhesoft.logistics.manage.mapper.TbTrainStationMapper;
import com.shenhesoft.logistics.manage.mapper.TbUserBranchGroupMapper;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroupExample;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomerExample;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.project.TbProjectExample;
import com.shenhesoft.logistics.manage.pojo.site.TbFreightYard;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUserExample;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUserExample.Criteria;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStation;

public class TestPageHelper {

  /*  @Test
    public void testPageHelper() {
        // 创建一个spring容器
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-*");
        TbProjectExample projectExample = new TbProjectExample();
        TbProjectMapper projectMapper=context.getBean(TbProjectMapper.class);;
		List<TbProject> list = projectMapper.selectByExample(projectExample);
		for (TbProject tbProject : list) {
			
		}
    }
    
    @Test
    public void testPageHelper2() {
        // 创建一个spring容器
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-*");
        // 从spring容器中获取mapper代理对象
        TbSystemUserMapper mapper = context.getBean(TbSystemUserMapper.class);
        // 执行查询并分页,TbItemExample是逆向工程自动生成的，用来进行条件查询，这里不设置则表示无条件
        TbSystemUserExample example = new TbSystemUserExample();
        example.setOrderByClause("createDate desc");
        Criteria criteria = example.createCriteria();
        criteria.andWorkStatusEqualTo((byte)0);
        //分页处理，显示第一页的10条数据
        PageHelper.startPage(3, 1);
        List<EmployInfo> list = mapper.selectEmployInfoByExample(example);//查询
        // 取商品列表
        for(EmployInfo order : list) {
            System.out.println(order.getName());
        }
        // 取分页信息
        PageInfo<EmployInfo> pageInfo = new PageInfo<EmployInfo>(list);
        long total = pageInfo.getTotal(); //获取总记录数
    }
    @Test
    public void testPageHelper3() {
    	// 创建一个spring容器
    	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-*");
    	TbCustomerMapper mapper = context.getBean(TbCustomerMapper.class);
    	 TbBranchGroupMapper mapper2 = context.getBean(TbBranchGroupMapper.class);
    	TbCustomerExample example = new TbCustomerExample();
    	example.setOrderByClause("createDate desc");
        com.shenhesoft.logistics.manage.pojo.customer.TbCustomerExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo((byte)0);
        //分页处理，显示第一页的10条数据
        PageHelper.startPage(1, 2);
        List<CustomerInfo> list = mapper.selectCustomerInfoByExample(example);//查询
        // 取分页信息
        PageInfo<CustomerInfo> pageInfo = new PageInfo<CustomerInfo>(list);
        long total = pageInfo.getTotal(); //获取总记录数
    	System.out.println("共有商品信息：" + total);
        for (CustomerInfo customerInfo : list) {
        	System.out.println(customerInfo.getCompanyName());
        	List<TbBranchGroup> list2 = mapper2.selectBranchGroupByCusId(customerInfo.getId());//查询
        	customerInfo.setBranchGroups(list2);
        	System.out.println(customerInfo.getCompanyName());
        	//method(customerInfo);
		}
        for (CustomerInfo customerInfo : list) {
        	List<TbBranchGroup> branchGroups = customerInfo.getBranchGroups();
        	for (TbBranchGroup tbBranchGroup : branchGroups) {
				System.out.println(tbBranchGroup.getId()+tbBranchGroup.getName());
			}
		}
       
    }

    @Test
    public void testPageHelper4() {
        // 创建一个spring容器
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-*");
        // 从spring容器中获取mapper代理对象
        TbBranchGroupMapper mapper = context.getBean(TbBranchGroupMapper.class);
        // 执行查询并分页,TbItemExample是逆向工程自动生成的，用来进行条件查询，这里不设置则表示无条件
        //分页处理，显示第一页的10条数据
        PageHelper.startPage(1, 2);
        List<TbBranchGroup> list = mapper.selectBranchGroupByCusId(2);//查询
        // 取商品列表
        for(TbBranchGroup order : list) {
            System.out.println(order.getName());
        }
        // 取分页信息
        PageInfo<TbBranchGroup> pageInfo = new PageInfo<TbBranchGroup>(list);
        long total = pageInfo.getTotal(); //获取总记录数
        System.out.println("共有商品信息：" + total);
    }
    @Test
    public void test10(){
    	TbStationBusinessHelp businessHelp = new TbStationBusinessHelp();
    	List<TbStationBusinessHelp> businessHelps = new ArrayList<>();
    	businessHelps.add(businessHelp);
    	String string = JsonUtils.objectToJson(businessHelps);
    	System.out.println(string);
    }
    
    @Test
    public void test11(){
    	 // 创建一个spring容器
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-*");
    	List<DotBranchDetail> branchDetails = new ArrayList<>();
    	//获取状态为可用的所有网点分支
    	 TbBranchGroupMapper branchGroupMapper = context.getBean(TbBranchGroupMapper.class);
    	 TbUserBranchGroupMapper userBranchGroupMapper = context.getBean(TbUserBranchGroupMapper.class);
    	 TbBranchGroupTrainStationMapper branchGroupTrainStationMapper = context.getBean(TbBranchGroupTrainStationMapper.class);
    	 TbTrainStationMapper stationMapper = context.getBean(TbTrainStationMapper.class);
    	TbBranchGroupExample example= new TbBranchGroupExample();
    	com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroupExample.Criteria criteria = example.createCriteria();
    	criteria.andStatusEqualTo((byte)1);
    	List<TbBranchGroup> list = branchGroupMapper.selectByExample(example);
    	for (TbBranchGroup tbBranchGroup : list) {
    		DotBranchDetail branchDetail = new DotBranchDetail();
    		Integer branchId = tbBranchGroup.getId();
    		//查询负责人信息
    		List<TbSystemUser> responsiblers = userBranchGroupMapper.selectResponsiblerByBranchId(branchId);
    		branchDetail.setResponsiblers(responsiblers);
    		//查询发运地
    		List<TbTrainStation> relationBeginStations = branchGroupTrainStationMapper.selectTrainStationByBranchId(branchId);
    		branchDetail.setRelationBeginStations(relationBeginStations);
    		//查询火车站
    		Integer trainStationId =  tbBranchGroup.getRelationTrainLocationId();
    		TbTrainStation trainStation = stationMapper.selectByPrimaryKey(trainStationId);
    		branchDetail.setStation(trainStation);
    	}
    }*/
  /*  @Test
    public void test12(){
    	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-*");
        TbProjectMapper projectMapper=context.getBean(TbProjectMapper.class);
        TbStockMapper stockMapper=context.getBean(TbStockMapper.class);
    	TbProjectExample example=new TbProjectExample();
    	com.shenhesoft.logistics.manage.pojo.project.TbProjectExample.Criteria criteria = example.createCriteria();
    	criteria.andStatusNotEqualTo(Constants.PROJECT_STATUS_CANCLE);//不包含未取消
    	criteria.andStatusNotEqualTo(Constants.PROJECT_STATUS_UNUSED);//不包含未使用的
    	//不包含汽运的
    	criteria.andTransportTypeNotEqualTo(Constants.TRANSPORTTYPE_TRUCK);
    	//PageHelper.startPage(1, 20);
		List<TbProject> list = projectMapper.selectByExample(example);
		List<StuckHelpPojo> helpPojos =  convertObject(list);
		for (StuckHelpPojo stuckHelpPojo : helpPojos) {
			Integer projectId = 46;
			//通过projectId获取它的入库和出库总库存 总的库存 总的调整库存
			//判断项目类型
			if(stuckHelpPojo.getTransportType()==Constants.TRANSPORTTYPE_RECEIVE
			||  stuckHelpPojo.getTransportType()==Constants.TRANSPORTTYPE_RECEIVE_TRAIN
			|| 	stuckHelpPojo.getTransportType()==Constants.TRANSPORTTYPE_UNION
			|| 	stuckHelpPojo.getTransportType()==Constants.TRANSPORTTYPE_RECEIVE_DELIVERY){//接取 接取+火运 联运 接取+送达
				//入库
				BigDecimal enterQty = stockMapper.selectEnterQtyByProjectId(Constants.STOCK_TYPE_END, projectId);
				stuckHelpPojo.setEndEnterQty(enterQty);
				//出库
				BigDecimal outQty = stockMapper.selectOutQtyByProjectId(Constants.STOCK_TYPE_END, projectId);
				stuckHelpPojo.setEndOutQty(outQty);
				//库存
				BigDecimal currentQty = stockMapper.selectCurrentQtyByProjectId(Constants.STOCK_TYPE_END, projectId);
				stuckHelpPojo.setEndCurrentQty(currentQty);
				//调整库存
				BigDecimal adjustQty = stockMapper.selectAdjustQtyByProjectId(Constants.STOCK_TYPE_END, projectId);
				stuckHelpPojo.setEndAdjustQty(adjustQty);
			}
			
			if(stuckHelpPojo.getTransportType()==Constants.TRANSPORTTYPE_DELIVERY
					||  stuckHelpPojo.getTransportType()==Constants.TRANSPORTTYPE_TRAIN_DELIVERY
					|| 	stuckHelpPojo.getTransportType()==Constants.TRANSPORTTYPE_UNION
					|| 	stuckHelpPojo.getTransportType()==Constants.TRANSPORTTYPE_RECEIVE_DELIVERY){
				//入库
				BigDecimal enterQty = stockMapper.selectEnterQtyByProjectId(Constants.STOCK_TYPE_BEGIN, projectId);
				stuckHelpPojo.setBeginEnterQty(enterQty);
				//出库
				BigDecimal outQty = stockMapper.selectOutQtyByProjectId(Constants.STOCK_TYPE_BEGIN, projectId);
				stuckHelpPojo.setBeginOutQty(outQty);
				//库存
				BigDecimal currentQty = stockMapper.selectCurrentQtyByProjectId(Constants.STOCK_TYPE_BEGIN, projectId);
				stuckHelpPojo.setBeginCurrentQty(currentQty);
				//调整库存
				BigDecimal adjustQty = stockMapper.selectAdjustQtyByProjectId(Constants.STOCK_TYPE_BEGIN, projectId);
				stuckHelpPojo.setBeginAdjustQty(adjustQty);
			}
		}
		PageInfo<TbProject> pageInfo = new PageInfo<TbProject>(list);
        long total = pageInfo.getTotal(); //获取总记录数
    }
    
    private List<StuckHelpPojo> convertObject(List<TbProject> list) {
		List<StuckHelpPojo> helpPojos = new ArrayList<StuckHelpPojo>();
		for (TbProject tbProject : list) {
			StuckHelpPojo helpPojo = new StuckHelpPojo();
			helpPojo.setProjectId(tbProject.getId());
			helpPojo.setProjectCode(tbProject.getProjectCode());
			helpPojo.setProjectType(tbProject.getProjectType());
			helpPojo.setBranchGroupName(tbProject.getBranchGroupName());
			helpPojo.setTransportType(tbProject.getTransportType());
			helpPojo.setSendCargoCompanyName(tbProject.getSendCargoCompanyName());
			helpPojo.setReceiveCargoCompanyName(tbProject.getReceiveCargoCompanyName());
			helpPojos.add(helpPojo);
		}
		return helpPojos;
	}*/
    
   //@Test
    public void test29(){
    	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-*");
    	TbOrderMapper orderMapper= context.getBean(TbOrderMapper.class);
    	int page=1;
		int limit=10;
		PageHelper.startPage(page, limit);
		DataGridResult dataGridResult = new DataGridResult();
		TbOrderExample example = new TbOrderExample();
		example.setOrderByClause("id desc");
		// 查询条件
		com.shenhesoft.logistics.business.pojo.TbOrder.TbOrderExample.Criteria criteria = example.createCriteria();
		criteria.andStatusNotEqualTo(Constants.SMS_POINT_WAIT_DIS);//不包含等待调度的
		criteria.andStepSelectCodeNotEqualTo(Constants.SHORT_BRAGE_TYPE_CARRUN);//不包含汽运
		criteria.andDeleteFlagEqualTo((byte) 0);//未删除的
		criteria.andExceptionStatusEqualTo((byte)0);//全部（异常，非异常）
		
		List<TbOrder> list = orderMapper.selectByExample(example);// 查询
		// 取分页信息
		PageInfo<TbOrder> pageInfo = new PageInfo<TbOrder>(list);
		long total = pageInfo.getTotal(); // 获取总记录数

		dataGridResult.setRows(list);
		dataGridResult.setTotalCount(total);
		dataGridResult.setLimit(limit);
		}
    }
