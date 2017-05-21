package com.lq.lss.controller.stock;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.lss.controller.shiro.ShiroBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.lq.lss.model.CStockCompensateDetail;
import com.lq.lss.core.service.CStockCompensateDetailService;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-12-28 15:32:42
 */
@Controller
@RequestMapping(value ="/user/pe/code/cStockCompensateDetail.htm")
public class CStockCompensateDetailController extends ShiroBaseController<CStockCompensateDetail, String, CStockCompensateDetailService>{
	
	@Resource
	private CStockCompensateDetailService cStockCompensateDetailService;
	
	
	@Value("/pe/code/CStockCompensateDetail")
	private String indexView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", notAdd ? 0 : 1);
		modelMap.put("upd", notUpdate ? 0 : 1);
		modelMap.put("del", notDelete ? 0 : 1);
		modelMap.put("exp", notExport ? 0 : 1);
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	
	
	
	
}
