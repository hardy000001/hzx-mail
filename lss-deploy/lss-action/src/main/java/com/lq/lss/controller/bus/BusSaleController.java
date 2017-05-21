package com.lq.lss.controller.bus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.annotation.Resource;

import com.lq.lss.controller.shiro.ShiroBaseController;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;

import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;
import com.lq.easyui.dto.ResultDto;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.lss.model.CBusSale;
import com.lq.lss.model.CBusSaleDetail;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.SystemConst;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.BusSaleDetailService;
import com.lq.lss.core.service.BusSaleService;
import com.lq.lss.dto.BusSaleDto;
import com.lq.lss.enums.TradeType;

/**
 * 销售
 * @author  作者: hzx
 * @date 创建时间: 2016-08-31 10:21:24
 */
@Controller
@RequestMapping(value ="/user/bus/busSale.htm")
public class BusSaleController extends ShiroBaseController<CBusSale, String, BusSaleService> {
	
	@Resource
	private BusSaleService busSaleService;
	@Resource
	private BusSaleDetailService busSaleDetailService;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	
	
	@Value("/bus/bus_sale")
	private String indexView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", PermResourceConst.CENTER_SALE_ADD);
        modelMap.put("update", PermResourceConst.CENTER_SALE_UPDATE);
        modelMap.put("del", PermResourceConst.CENTER_SALE_DEL);
        modelMap.put("check", PermResourceConst.CENTER_SALE_CHECK);
		modelMap.put("deptid", user.getCenterId());
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	
	
	@RequestMapping(params = "method=remove")
	@RequiresPermissions(PermResourceConst.CENTER_SALE_DEL)
	public ModelAndView remove(HttpServletRequest request, HttpServletResponse response) {

		ResultDto<String> result = null;
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		
		String idStr = ServletRequestUtils.getStringParameter(request, "id", null);
		
		if (!StringUtils.hasLength(idStr)) {
				result = new ResultDto<String>(false, "id或ids不能为空");
				return AjaxModelAndViewUtils.writeMsgReturnNull(response,
						JSONUtil.toJSonString(result));
		}
		
		try {
			result=busSaleService.deleteSaleInfoById(idStr, String.valueOf(user.getCenterId()));
			//TODO 删除操作日志  编号不是Long类型
			adminAuditLogService.log(user.getUserId(), TradeType.STOCK_XS.getType(),"删除销售单",idStr,0L);    
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除销售数据出现异常"+e.getMessage());
			result=new ResultDto<String>(false,"删除数据出现异常");
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(result));
	}
	
	@RequestMapping(params = "method=gerSaleDetailList")
	public ModelAndView gerSaleDetailList(HttpServletRequest request,HttpServletResponse response) {
		
		String saleSerialno = ServletRequestUtils.getStringParameter(request,
				"id", "");
		
		List<CBusSaleDetail> detialList = null;
		
		if (StringUtils.hasText(saleSerialno)) {
			                detialList = busSaleDetailService.querySaleDetailListById(saleSerialno);
		}

		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(detialList));
	}
	@RequestMapping(params = "method=auditInfo")
	@RequiresPermissions(PermResourceConst.CENTER_SALE_CHECK)
	public ModelAndView auditInfo(HttpServletRequest request, HttpServletResponse response,
			BusSaleDto busSaleDto) {
		
			SessionUser user=LoginSessionUtils.getUserInSession(request);
			busSaleDto.setUserId(user.getUserId());
			busSaleDto.setDeptId(user.getCenterId()+"");
			busSaleDto.setMchcode(SystemConst.ZX_MCHCODE);
			String status=busSaleDto.getStatus();
			ResultDto<String> result=null;
			if(!StringUtils.hasLength(status)){
				result=new ResultDto<String>(false,"状态不能为空");
				return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(result));
			}
			
			try {
				    result=busSaleService.auditInfoRdTx(busSaleDto);
				    //TODO 审核操作日志  编号不是Long类型
					adminAuditLogService.log(user.getUserId(), TradeType.STOCK_XS.getType(),"审核销售单",busSaleDto,0L);    
			} catch (Exception e) {
					e.printStackTrace();
					logger.error("销售审核修改数据异常");
					result=new ResultDto<String>(false,"数据修改异常");
			}
		
		
		    return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(result));
	}
}
