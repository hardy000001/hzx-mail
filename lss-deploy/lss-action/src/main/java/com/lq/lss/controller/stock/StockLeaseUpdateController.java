/**
 * 
 */
package com.lq.lss.controller.stock;

import java.net.URLDecoder;
import java.util.ArrayList;
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
import com.lq.easyui.controller.easyui.EasyUIController;
import com.lq.easyui.dto.ResultDto;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.CStockRemodelingService;
import com.lq.lss.core.service.MaterialInfoService;
import com.lq.lss.core.service.MaterialTypeService;
import com.lq.lss.core.service.StockLeaseDetailService;
import com.lq.lss.core.service.StockLeaseService;
import com.lq.lss.core.service.StockTemporaryDetailService;
import com.lq.lss.core.service.StockTemporaryService;
import com.lq.lss.dto.CStockLeaseDetailDto;
import com.lq.lss.dto.CStockLeaseDto;
import com.lq.lss.dto.CStockRemodelingDetailDto;
import com.lq.lss.dto.CStockTemporaryDetailDto;
import com.lq.lss.dto.CStockTemporaryDto;
import com.lq.lss.model.CStockLease;
import com.lq.lss.model.CStockLeaseDetail;
import com.lq.lss.model.CStockTemporary;
import com.lq.lss.model.CStockTemporaryDetail;

import com.lq.lss.model.SessionUser;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;

import net.sf.json.JSONArray;



@Controller
@RequestMapping(value = "/user/stock/stockLeaseEdit.htm")
public class StockLeaseUpdateController extends EasyUIController<CStockLease, String, StockLeaseService> {

	@Resource
	StockLeaseService slservice;
	@Resource
	StockLeaseDetailService sldservice;
	@Value("/stock/stockLeaseEdit")
	private String indexView;

	@RequestMapping
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		String lsSerialno=ServletRequestUtils.getStringParameter(request, "id", "");
		String loginName=ServletRequestUtils.getStringParameter(request, "loginName", "");
		int a = ServletRequestUtils.getIntParameter(request, "a", 0);
		int b = ServletRequestUtils.getIntParameter(request, "b", 0);
		int c = ServletRequestUtils.getIntParameter(request, "c", 0);
		int d = ServletRequestUtils.getIntParameter(request, "d", 0);
		int e = ServletRequestUtils.getIntParameter(request, "e", 0);
		if (a == 0) notAdd = true;
		if (b == 0) notUpdate = true;
		if (c == 0) notDelete = true;
		if (d == 0) notExport = true;
		if (e == 0) notAudit = true;
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", notAdd ? 0 : 1);
		modelMap.put("upd", notUpdate ? 0 : 1);
		modelMap.put("del", notDelete ? 0 : 1);
		modelMap.put("exp", notExport ? 0 : 1);
		modelMap.put("aud", notAudit ? 0 : 1);
		CStockLease  cStockLease   =slservice.get(lsSerialno);
		
		List<CStockLeaseDetail> detailList= sldservice.findCsldBylsSerialno(lsSerialno);
		
		cStockLease.setOperName(user.getLoginName());
		cStockLease.setLoginName(loginName);
		cStockLease.setLsSerialno(lsSerialno);
		modelMap.put("cStockLease", cStockLease);
		modelMap.put("detailList", detailList);
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}

	@RequestMapping(params = "method=stockLeaseupdate")
	public ModelAndView stockLeaseupdate(HttpServletRequest request, HttpServletResponse response,CStockLeaseDto cStockLeaseDto) {
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<String> result=null;
		try {
			String listStr=request.getParameter("rows");
			JSONArray json = JSONArray.fromObject(listStr);
			List<CStockLeaseDetailDto> dataList =(List<CStockLeaseDetailDto>)JSONArray.toCollection(json, CStockLeaseDetailDto.class);
			cStockLeaseDto.setCstockleasedetaildto(dataList);
			result=slservice.updateTemporaryRdTx(cStockLeaseDto, user);
		
		
		} catch (Exception e) {
			logger.error("修改加工改制数据出现异常", e);
			result=new ResultDto<String>(false,"数据提交失败");
		}
		
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(result));
	}
}
