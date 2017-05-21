/**
 * 
 */
package com.lq.lss.controller.stock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.core.service.CStockRemodelingDetailService;
import com.lq.lss.model.CStockRemodelingDetail;

import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;



@Controller
@RequestMapping(value = "/user/stock/stockRemodelingDetail.htm")
public class StockRemodelingDetailController extends ShiroBaseController<CStockRemodelingDetail, String, CStockRemodelingDetailService> {

	@Resource
	private CStockRemodelingDetailService cstockRemodelingDetailService;

	@Value("/stock/stockRemodeling")
	private String indexView;

	@RequestMapping
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
	 	modelMap.put("add", PermResourceConst.CENTER_REMODELING_ADD);
        modelMap.put("update", PermResourceConst.CENTER_REMODELING_UPDATE);
        modelMap.put("del", PermResourceConst.CENTER_REMODELING_DEL);
        modelMap.put("check", PermResourceConst.CENTER_REMODELING_CHECK);
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}

	@RequestMapping(params = "method=queryByremSerialNo")
	public ModelAndView findCstrd(HttpServletRequest request,
			HttpServletResponse response) {
		String remSerialNo = ServletRequestUtils.getStringParameter(request,
				"id", "");
		
		System.out.println("remSerialNo+++++++++++++++++++++"+remSerialNo);
		List<CStockRemodelingDetail> detialList= null;
		
		if (StringUtils.hasText(remSerialNo)) {
            detialList = cstockRemodelingDetailService.fidnCsrddById(remSerialNo);
}

//		Map<String, Object> modelMap = new HashMap<String, Object>();
//		modelMap.put("cStockRemodelingDetail", cStockRemodelingDetail);
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(detialList));
	}
	
}
