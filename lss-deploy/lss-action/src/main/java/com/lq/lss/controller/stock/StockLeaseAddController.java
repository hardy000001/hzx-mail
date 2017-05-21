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
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.lq.easyui.controller.easyui.EasyUIController;
import com.lq.easyui.dto.ResultDto;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.MaterialInfoService;
import com.lq.lss.core.service.MaterialTypeService;
import com.lq.lss.core.service.MchBaseinfoService;
import com.lq.lss.core.service.StockLeaseService;
import com.lq.lss.dto.CStockLeaseDetailDto;
import com.lq.lss.dto.CStockLeaseDto;
import com.lq.lss.model.BMaterialInfo;
import com.lq.lss.model.CStockLease;

import com.lq.lss.model.MchBaseinfo;
import com.lq.lss.model.SessionUser;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;

import net.sf.json.JSONArray;



@Controller
@RequestMapping(value = "/user/stock/stockLeaseAdd.htm")
public class StockLeaseAddController extends EasyUIController<CStockLease, String, StockLeaseService> {

	@Resource
	private MaterialTypeService materialTypeService;
	@Resource
	private MaterialInfoService materialInfoService;
	@Resource
	private MchBaseinfoService mchInfoService;
	@Resource
	private StockLeaseService slservice;
	
	@Value("/stock/stockLeaseAdd")
	private String indexView;
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		SessionUser user = LoginSessionUtils.getUserInSession(request);
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
		modelMap.put("user", user);
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}

	
	@RequestMapping(params = "method=getMaInfoList")
	public ModelAndView getMaInfoList(HttpServletRequest request, HttpServletResponse response) {
	
		List<BMaterialInfo> tList = materialInfoService.loadAll();

		return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(tList));
	}
	
	
	
		//加载公司
		@RequestMapping(params = "method=getMchInfoList")
		public ModelAndView getMchInfoList(HttpServletRequest request, HttpServletResponse response) {
		
			List<MchBaseinfo> tList = mchInfoService.loadAll();

			return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(tList));
		}

		
		@RequestMapping(params = "method=saveLeaseAll")
		public ModelAndView saveLeaseAll(HttpServletRequest request, HttpServletResponse response,CStockLeaseDto cStockLeaseDto) {
			SessionUser user = LoginSessionUtils.getUserInSession(request); 
			ResultDto<String> resultDto = null;
			try {
				 cStockLeaseDto.setDeptid(Integer.parseInt(user.getDeptId())+"");
				 String listStr=request.getParameter("rows");
				 String lsSerialno=request.getParameter("lsSerialno");
				 JSONArray json=JSONArray.fromObject(listStr);
				 List<CStockLeaseDetailDto> dataList =(List<CStockLeaseDetailDto>)JSONArray.toCollection(json, CStockLeaseDetailDto.class);
				 cStockLeaseDto.setCstockleasedetaildto(dataList);
				 if(cStockLeaseDto!=null){
					 resultDto= slservice.saveCStockLeaseRdTx(cStockLeaseDto, user);
					 
				 }
		
				} catch (Exception e) {
					 logger.error("提交暂存请数据出现异常", e);
					 resultDto=new ResultDto<String>(false,"数据提交失败");
			}
			
			return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(resultDto));
		}
		
	
}
