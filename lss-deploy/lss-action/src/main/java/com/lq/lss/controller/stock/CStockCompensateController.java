package com.lq.lss.controller.stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.event.SwingPropertyChangeSupport;
import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;
import com.lq.easyui.dto.ResultDto;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.lss.model.BRepairInfo;
import com.lq.lss.model.CStockCompensate;
import com.lq.lss.model.CStockCompensateDetail;
import com.lq.lss.model.CStockSendDetail;
import com.lq.lss.model.CompensateDetail;
import com.lq.lss.model.SessionUser;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.CStockCompensateDetailService;
import com.lq.lss.core.service.CStockCompensateService;
import com.lq.lss.dto.BusHtRepairinfoDto;
import com.lq.lss.dto.CStockCompensateDetailDto;
import com.lq.lss.dto.CStockCompensateDto;
import com.lq.lss.dto.CStockSendDto;
import com.lq.lss.enums.TradeType;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-12-28 15:32:42
 */
@Controller
@RequestMapping(value ="/user/stock/stockcompensate.htm")
public class CStockCompensateController extends ShiroBaseController<CStockCompensate, String, CStockCompensateService>{
	
	@Resource
	private CStockCompensateService cStockCompensateService;
	
	@Resource
	private CStockCompensateDetailService cStockCompensateDetailService;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	
	@Value("/stock/stock_compensate")
	private String indexView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", PermResourceConst.STOCK_CMPENSATE_ADD);
        modelMap.put("update", PermResourceConst.STOCK_CMPENSATE_UPDATE);
        modelMap.put("del", PermResourceConst.STOCK_CMPENSATE_DEL);
        modelMap.put("check", PermResourceConst.STOCK_CMPENSATE_CHECK);
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	
	@RequestMapping(params = "method=getCompensateinfo")
	public ModelAndView getCompensateinfo(HttpServletRequest request,HttpServletResponse response) {
		
		String htCode = ServletRequestUtils.getStringParameter(request,
				"id", "");
	
		
		List<CompensateDetail> dataList = cStockCompensateDetailService.findStockCompensateDetailbyHtcode(htCode);
	  
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(dataList));
	}
	
	
	
	@RequestMapping(params = "method=getCStockCompensateDetailList")
	public ModelAndView getCStockCompensateDetailList(HttpServletRequest request,HttpServletResponse response) {
		
		String compensateSerialno = ServletRequestUtils.getStringParameter(request,
				"id", "");
		List<CStockCompensateDetailDto> detialList = null;
		
		if (StringUtils.hasText(compensateSerialno)) {
			                detialList = cStockCompensateDetailService.findCStockCompensateDetail(compensateSerialno);
		}

		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(detialList));
	}
	
	
	
	
	
	@RequestMapping(params = "method=removeCStockCompensate")
	@RequiresPermissions(PermResourceConst.STOCK_CMPENSATE_DEL)
	public ModelAndView removeCStockCompensate(HttpServletRequest request, HttpServletResponse response){
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
					result=cStockCompensateService.deleteCStockCompensate(idStr,String.valueOf(user.getCenterId()));
					adminAuditLogService.log(user.getUserId(), TradeType.STOCK_COMPENSATE.getType(),"删除赔偿单",idStr,0L);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logger.error("删除采购数据出现异常"+e.getMessage());
				result=new ResultDto<String>(false,"删除数据出现异常");
			}
				
			return AjaxModelAndViewUtils.writeMsgReturnNull(response,
					JSONUtil.toJSonString(result));
			}
	
	
	
	
	@RequestMapping(params = "method=auditStockCompensate")
	@RequiresPermissions(PermResourceConst.STOCK_CMPENSATE_CHECK)
	public ModelAndView auditStockCompensate(HttpServletRequest request,HttpServletResponse response
			,CStockCompensateDto cStockCompensateDto) {
		
		 String compensateSerialno= ServletRequestUtils.getStringParameter(request, "compensateSerialno","");
		 String status= ServletRequestUtils.getStringParameter(request, "status","");
		 SessionUser user = LoginSessionUtils.getUserInSession(request);
		 
			ResultDto<String> result = null;
			try {
			
				cStockCompensateDto.setUserId(user.getUserId());
				cStockCompensateDto.setDeptid(user.getCenterId());
				cStockCompensateDto.setAuditingoper(user.getUserId()+"");
				result=cStockCompensateService.auditCStockCompensateRdTx(cStockCompensateDto);
				adminAuditLogService.log(user.getUserId(), TradeType.STOCK_COMPENSATE.getType(),"审核赔偿单",cStockCompensateDto,0L);
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("审核赔偿数据出现异常"+e.getMessage());
				result=new ResultDto<String>(false,"审核出现异常");

			}
			
			return AjaxModelAndViewUtils.writeMsgReturnNull(response,
					JSONUtil.toJSonString(result));
	}
	
	
}
