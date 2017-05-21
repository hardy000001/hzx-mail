/**
 * 
 */
package com.lq.lss.controller.settlement;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.easyui.controller.easyui.EasyUIController;
import com.lq.easyui.dto.ResultDto;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.CStockFlowService;
import com.lq.lss.core.service.MaterialInfoService;
import com.lq.lss.core.service.MaterialTypeService;
import com.lq.lss.core.service.MchBaseinfoService;
import com.lq.lss.core.service.SContractMoneyService;
import com.lq.lss.core.service.StockInfoService;
import com.lq.lss.model.CStockInfo;
import com.lq.lss.model.SessionUser;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;

/**
 * @author Eric
 */
@Controller
@RequestMapping(value = "/user/settle/monthset.htm")
public class MonthSettleController extends EasyUIController<CStockInfo, Integer,StockInfoService > {

	@Resource
	private MaterialTypeService materialTypeService;
	@Resource
	private MaterialInfoService materialInfoService;
	@Resource
	private MchBaseinfoService mchInfoService;
	
	@Resource
	private SContractMoneyService sContractMoneyService;
	
	@Value("/settle/monthset")
	
	private String indexView;

	@RequestMapping
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		
		modelMap.put("deptid", user.getDeptId());
	
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	
	
    @RequestMapping(params = "method=settle")
    public ModelAndView settle(HttpServletRequest request, HttpServletResponse response) {

    	ResultDto<?> result = null;
        //materialInfoService.bachcreatMerTest();
        SessionUser user = LoginSessionUtils.getUserInSession(request);
        
        
		//获取入库流水号
		String settleDate = ServletRequestUtils.getStringParameter(request,
				"settleDate", "");
		 
		result=sContractMoneyService.settleMentDealRdTx(settleDate,user.getCenterId());
       

        return AjaxModelAndViewUtils.writeMsgReturnNull(response,
                JSONUtil.toJSonString(result));
    }
	
	

}
