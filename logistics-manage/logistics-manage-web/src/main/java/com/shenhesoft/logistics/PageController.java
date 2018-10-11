package com.shenhesoft.logistics;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @title page-控制层Action
 * @description page-控制层Action
 * @author LiuJiefeng
 * @date 2016-04-11
 */
@Controller
public class PageController {
  /**
   * 项目展示.
   */
  @RequestMapping("/exhibition")
  public String exhibition(){
     return"/app/exhibition";
  }
    /**
     * 运费支出.
     */
    @RequestMapping("/shortOrderFinance")
    public String shortOrderFinance(){
       return"/html/finance/shortOrderFinance";
    }
    
    /**
     * 司机对账
     */
    @RequestMapping("/driverCheckingShortOrderFinance")
    public String driverCheckingShortOrderFinance(){
    	return"/html/finance/driverChecking/driverCheckingShortOrderFinance";
    }
    
    /**
     * 司机对账-对账明细.
     */
    @RequestMapping("/driverCheckDetail")
    public String driverCheckDetail(){
       return"/html/finance/driverChecking/driverCheckingDetail";
    }

    /**
     * 客户对账 对账明细.
     */
    @RequestMapping("/customerCheckingConf")
    public String custCheck(){
    	return"/html/finance/custCheck/customerCheckingConf";
    }

    /**
     * 客户对账detail 汽运.
     */
    @RequestMapping("/custCheckCar")
    public String custCheckCar(){
       return"/html/finance/custCheck/custCheckCar";
    }
    /**
     * 客户对账detail 接取.
     */
    @RequestMapping("/custCheckRecive")
    public String custCheckRecive(){
       return"/html/finance/custCheck/custCheckRecive";
    }
    /**
     * 客户对账detail 接取-打包的运单列表.
     */
    @RequestMapping("/custCheckRecivePack")
    public String custCheckRecivePack(){
       return"/html/finance/custCheck/custCheckRecivePack";
    }
    /**
     * 客户对账detail 汽运-打包的运单列表.
     */
    @RequestMapping("/custCheckCarPack")
    public String custCheckCarPack(){
       return"/html/finance/custCheck/custCheckCarPack";
    }
    /**
     * 客户对账detail 送达-打包的运单列表.
     */
    @RequestMapping("/custCheckSendPack")
    public String custCheckSendPack(){
       return"/html/finance/custCheck/custCheckSendPack";
    }
    /**
     * 客户对账detail 火运-打包的运单列表.
     */
    @RequestMapping("/custCheckTrainPack")
    public String custCheckTrainPack(){
       return"/html/finance/custCheck/custCheckTrainPack";
    }
    /**
     * 客户对账detail 送达.
     */
    @RequestMapping("/custCheckSend")
    public String custCheckSend(){
       return"/html/finance/custCheck/custCheckSend";
    }
    /**
     * 客户对账detail 火运.
     */
    @RequestMapping("/custCheckTrain")
    public String custCheckTrain(){
       return"/html/finance/custCheck/custCheckTrain";
    }
    
    /**
     * 汽运对账detail .
     */
    @RequestMapping("/custCheckCarDetail")
    public String custCheckCarDetail(){
       return"/html/finance/custCheck/custCheckCarDetail";
    }
    /**
     * 接取对账detail .
     */
    @RequestMapping("/custCheckReciveDetail")
    public String custCheckReciveDetail(){
       return"/html/finance/custCheck/custCheckReciveDetail";
    }
    /**
     * 送达对账detail .
     */
    @RequestMapping("/custCheckSendDetail")
    public String custCheckSendDetail(){
       return"/html/finance/custCheck/custCheckSendDetail";
    }
    /**
     * 火运对账detail .
     */
    @RequestMapping("/custCheckTrainDetail")
    public String custCheckTrainDetail(){
       return"/html/finance/custCheck/custCheckTrainDetail";
    }
    
    /**
     * 司机结算.
     */
    @RequestMapping("/driverAccounts")
    public String driverAccounts(){
       return"/html/finance/driverAccounts";
    }
    
    /**
     * 司机结算历史明细.
     */
    @RequestMapping("/driverAccountsHistoryDetail")
    public String driverAccountsHistoryDetail(){
       return"/html/finance/driverAccountsHistory";
    }
    
    /**
     * 客户结算.
     */
    @RequestMapping("/customerAccounts")
    public String customerAccounts(){
       return"/html/finance/custSettle";
    }
    /**
     * 客户结算.
     */
    @RequestMapping("/custSettleHistory")
    public String custSettleHistory(){
       return"/html/finance/custSettleHistory";
    }
    
    /**
     * test.
     */
    @RequestMapping("/shortPack")
    public String shortPack(){
       return"/html/finance/shortPack";
    }
    
    /**
     * 网点结算.
     */
    @RequestMapping("/dotAccounts")
    public String dotAccounts(){
       return"/html/finance/dotAccounts";
    }
    
    /**
     * 网点历史结算.
     */
    @RequestMapping("/dotAccountsHistory")
    public String dotAccountsHistory(){
       return"/html/finance/dotAccountsHistory";
    }
    
    /**
     * 发票管理.
     */
    @RequestMapping("/invoiceManagement")
    public String invoiceManagement(){
       return"/html/finance/invoice";
    }
    
    /**
     * 油气卡管理.
     */
    @RequestMapping("/oilGasCardManagement")
    public String oilGasCardManagement(){
       return"/html/finance/materialManagement/oilGasCardManagement";
    }
    
    /**
     * 业务首页.
     */
    @RequestMapping("/businessHome")
    public String businessHome(){
       return"/html/business/businessHome";
    }
    /**
     * 业务首页.
     */
    @RequestMapping("/mapPoint")
    public String mapPoint(){
       return"/html/business/mapPoint";
    }
    
    /**
     * 费用对账
     * */
    @RequestMapping("/costReconciliation")
    public String costReconciliation(){
       return"/html/finance/billReconciliation/costReconciliation";
    }
    
    /**
     * 费用对账-对账明细.
     */
    @RequestMapping("/costCheckDetail")
    public String costCheckDetail(){
       return"/html/finance/billReconciliation/costCheckingDetail";
    }
    
    /**
     * 预付款结算.
     */
    @RequestMapping("/advanceDetail")
    public String advanceDetail(){
       return"/html/finance/prepaymentSettlement/imprestPayment";
    }
    
    /**
     * 物料管理.
     */
    @RequestMapping("/materialManagement")
    public String materialManagement(){
       return"/html/finance/materialManagement/materialManagement";
    }
    
    /**
     * 收支序时账.
     */
    @RequestMapping("/financeAccountDetail")
    public String financeAccountDetail(){
       return"/html/finance/financeAccountDetail";
    }
    
    /**
     * 企业应收款.
     */
    @RequestMapping("/enterpriseReceivables")
    public String enterpriseReceivables(){
       return"/html/finance/receivablesSettlement/enterpriseReceivables";
    }
    
    /**
     * 企业应收款历史明细.
     */
    @RequestMapping("/enterpriseReceivablesHistory")
    public String enterpriseReceivablesHistory(){
       return"/html/finance/receivablesSettlement/enterpriseReceivablesHistory";
    }
    
    /**
     * 账户调整.
     */
    @RequestMapping("/accountAdjustment")
    public String accountAdjustment(){
       return"/html/finance/financialAdjustment/accountAdjustment/accountAdjustment";
    }
    
    /**
     * 网点交账.
     */
    @RequestMapping("/dotAccountability")
    public String dotAccountability(){
       return"/html/finance/dotAccountability";
    }
    
    /**
     * 网点交账明细.
     */
    @RequestMapping("/dotAccountabilityDetail")
    public String dotAccountabilityDetail(){
       return"/html/finance/dotAccountabilityDetail";
    }
    
    /**
     * 三方应收款管理.
     */
    @RequestMapping("/threePartiesReceivables")
    public String threePartiesReceivables(){
       return"/html/finance/receivablesSettlement/threePartiesReceivables";
    }
    
    /**
     * 三方应收款历史明细.
     */
    @RequestMapping("/threePartiesReceivablesHistory")
    public String threePartiesReceivablesHistory(){
       return"/html/finance/receivablesSettlement/threePartiesReceivablesHistory";
    }
    
    /**
     * 项目核查.
     */
    @RequestMapping("/projectCheck2")
    public String projectCheck2(){
       return"/html/business/project/projectCheck2";
    }
    /**
     * 项目明细.
     */
    @RequestMapping("/projectCheck")
    public String projectCheck(){
       return"/html/business/project/projectCheck";
    }
    
    /**
     * 项目展示平台登录界面	
     */
    @RequestMapping("/exhLogin")
    public String exhLogin(){
       return"/exhibition/exhLogin";
    }
    
}