/**
 * 
 */
package com.lq.lss.controller.stock;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.lq.easyui.controller.easyui.EasyUIController;
import com.lq.easyui.dto.ResultDto;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.CStockRemodelingService;
import com.lq.lss.core.service.MaterialInfoService;
import com.lq.lss.core.service.MaterialTypeService;
import com.lq.lss.dto.CStockRemodelingDto;
import com.lq.lss.enums.TradeType;
import com.lq.lss.model.BMaterialInfo;
import com.lq.lss.model.CStockRemodeling;
import com.lq.lss.model.CStockTemporary;
import com.lq.lss.model.SessionUser;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;


@Controller
@RequestMapping(value = "/user/stock/stockRemodeling.htm")
public class StockRemodelingController extends ShiroBaseController<CStockRemodeling, String, CStockRemodelingService> {

	@Resource
	private MaterialTypeService materialTypeService;
	@Resource
	private MaterialInfoService materialInfoService;
	@Resource
	private CStockRemodelingService srlservice;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	
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

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=getMaInfoList")
	public ModelAndView getMaInfoList(HttpServletRequest request, HttpServletResponse response) {
	
		List<BMaterialInfo> tList = materialInfoService.loadAll();

		return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(tList));
	}
	
	
	
	@RequestMapping(params="method=removeAll")
	@RequiresPermissions(PermResourceConst.CENTER_REMODELING_DEL)
	public ModelAndView remvoeAll(HttpServletRequest request, HttpServletResponse response){
		ResultDto<String> result = null;
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		if (notDelete) {
			result = new ResultDto<String>(false, deleteAlertMsg);
			return AjaxModelAndViewUtils.writeMsgReturnNull(response,
					JSONUtil.toJSonString(result));
	}
	String idStr = ServletRequestUtils.getStringParameter(request, "id", null);
	if (!StringUtils.hasLength(idStr)) {
		result = new ResultDto<String>(false, "id或ids不能为空");
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(result));
	}try {
			result=srlservice.deleteCsrdlById(idStr,String.valueOf(user.getCenterId()));
			adminAuditLogService.log(user.getUserId(), TradeType.REMODELING.getType(),"删除加工改制单",idStr,0L);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		logger.error("删除采购数据出现异常"+e.getMessage());
		result=new ResultDto<String>(false,"删除数据出现异常");
	}
		
	return AjaxModelAndViewUtils.writeMsgReturnNull(response,
			JSONUtil.toJSonString(result));
	}
	
	
	/*
	 * 
	 * 审核
	 */
	@RequestMapping(params = "method=auditStockRemodeling")
	@RequiresPermissions(PermResourceConst.CENTER_REMODELING_CHECK)
	public ModelAndView auditStockRemodeling(HttpServletRequest request,HttpServletResponse response,CStockRemodelingDto cStockRemodelingDto) {
		
		 String remSerialNo= ServletRequestUtils.getStringParameter(request, "remSerialNo","");
		 String status= ServletRequestUtils.getStringParameter(request, "status","");
		 SessionUser user = LoginSessionUtils.getUserInSession(request);
			ResultDto<String> result = null;
			try {
				cStockRemodelingDto.setUserId(user.getUserId());
				cStockRemodelingDto.setDeptid(user.getCenterId());
				cStockRemodelingDto.setAuditingoper(user.getUserId()+"");
				result=srlservice.auditStockRemodelingByIdRdTx(cStockRemodelingDto);
				adminAuditLogService.log(user.getUserId(), TradeType.REMODELING.getType(),"审核加工改制单",cStockRemodelingDto,0L);
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("审核入库数据出现异常"+e.getMessage());
				result=new ResultDto<String>(false,"审核出现异常");

			}
			
			return AjaxModelAndViewUtils.writeMsgReturnNull(response,
					JSONUtil.toJSonString(result));
	}
	
}
