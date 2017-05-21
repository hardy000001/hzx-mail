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

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.easyui.dto.ResultDto;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.StockTemporaryDetailService;
import com.lq.lss.core.service.StockTemporaryService;
import com.lq.lss.dto.CStockTemporaryDetailDto;
import com.lq.lss.dto.CStockTemporaryDto;
import com.lq.lss.enums.TradeType;
import com.lq.lss.model.CStockTemporary;
import com.lq.lss.model.CStockTemporaryDetail;
import com.lq.lss.model.SessionUser;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;

import net.sf.json.JSONArray;



@Controller
@RequestMapping(value = "/user/stock/stockTemporaryEdit.htm")
public class StockTemporaryUpdateController extends ShiroBaseController<CStockTemporary, String, StockTemporaryService> {

	@Resource
	private StockTemporaryService srrservice;
	@Resource
	private StockTemporaryDetailService sttdservice;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	
	@Value("/stock/stockTemporaryEdit")
	private String indexView;

	@RequestMapping
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		String temSerialno=ServletRequestUtils.getStringParameter(request, "id", "");
		String loginName=ServletRequestUtils.getStringParameter(request, "loginName", "");
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", PermResourceConst.CENTER_TEMPORARY_ADD);
        modelMap.put("update", PermResourceConst.CENTER_TEMPORARY_UPDATE);
        modelMap.put("del", PermResourceConst.CENTER_TEMPORARY_DEL);
        modelMap.put("check", PermResourceConst.CENTER_TEMPORARY_CHECK);
		CStockTemporary  cStockTemporary   =srrservice.get(temSerialno);
		List<CStockTemporaryDetail> detailList= sttdservice.findCstdpBytemSerialno(temSerialno);
		cStockTemporary.setOperName(user.getLoginName());
		cStockTemporary.setLoginName(loginName);
		cStockTemporary.setTemSerialno(temSerialno);
		modelMap.put("cStockTemporary", cStockTemporary);
		modelMap.put("detailList", detailList);
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}

	@RequestMapping(params = "method=stockTemporaryupdate")
	@RequiresPermissions(PermResourceConst.CENTER_TEMPORARY_UPDATE)
	public ModelAndView stockRemodelingupdate(HttpServletRequest request, HttpServletResponse response,CStockTemporaryDto cStockTemporaryDto ) {
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<String> result=null;
		try {
			String listStr=request.getParameter("rows");
			JSONArray json = JSONArray.fromObject(listStr);
			List<CStockTemporaryDetailDto> dataList =(List<CStockTemporaryDetailDto>)JSONArray.toCollection(json, CStockTemporaryDetailDto.class);
			cStockTemporaryDto.setcStockTemporaryDetailDtos(dataList);
			result=srrservice.updateTemporaryRdTx(cStockTemporaryDto, user);
			adminAuditLogService.log(user.getUserId(), TradeType.TEMPORARY_IN.getType(),"修改暂存制单",cStockTemporaryDto,0L);
		
		} catch (Exception e) {
			logger.error("修改加工改制数据出现异常", e);
			result=new ResultDto<String>(false,"数据提交失败");
		}
		
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(result));
	}
}
