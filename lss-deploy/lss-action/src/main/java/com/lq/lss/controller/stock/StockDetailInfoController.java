/**
 * 
 */
package com.lq.lss.controller.stock;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.easyui.controller.easyui.EasyUIController;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.MaterialInfoService;
import com.lq.lss.core.service.MaterialTypeService;
import com.lq.lss.core.service.MchBaseinfoService;
import com.lq.lss.core.service.StockDetailInfoService;
import com.lq.lss.model.CStockDetailInfo;
import com.lq.lss.model.CStockInfo;
import com.lq.lss.model.SessionUser;
import com.lq.springmvc.i18n.I18nModelAndView;

/**
 * @author Eric
 */
@Controller
@RequestMapping(value = "/user/stock/stockdetailinfo.htm")
public class StockDetailInfoController extends EasyUIController<CStockDetailInfo, Integer,StockDetailInfoService > {

	
	@Value("/stock/stockdetailinfo")
	
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

}
