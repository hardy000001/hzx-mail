package com.lq.lss.controller.stock;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.lq.lss.model.CStockInventory;
import com.lq.lss.model.SessionUser;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.StockInventoryService;
import com.lq.lss.dto.AuditDto;
import com.lq.lss.enums.TradeType;

/**
 * 库存盘点
 * @author  作者: hzx
 * @date 创建时间: 2016-12-29 13:26:15
 */
@Controller
@RequestMapping(value ="/user/stock/stockInventory.htm")
public class StockInventoryController extends ShiroBaseController<CStockInventory, String, StockInventoryService>{
	
	@Resource
	private StockInventoryService stockInventoryService;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	
	
	@Value("/stock/stock_inventory")
	private String indexView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", PermResourceConst.STOCK_INVENTORY_ADD);
        modelMap.put("update", PermResourceConst.STOCK_INVENTORY_UPDATE);
        modelMap.put("del", PermResourceConst.STOCK_INVENTORY_DEL);
        modelMap.put("check", PermResourceConst.STOCK_INVENTORY_CHECK);
		modelMap.put("deptid", user.getCenterId());
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	
	@RequestMapping(params = "method=remove")
	@RequiresPermissions(PermResourceConst.STOCK_INVENTORY_DEL)
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
			  result=stockInventoryService.deleteInventoryById(idStr,String.valueOf(user.getCenterId()));
			  //TODO 删除操作日志  编号不是Long类型
			  //adminAuditLogService.log(user.getUserId(), TradeType.STOCK_OUT.getType(),"删除出库单",idStr,0L); 
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除销售数据出现异常"+e.getMessage());
			result=new ResultDto<String>(false,"删除数据出现异常");
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(result));
	}
	
	@RequestMapping(params = "method=auditInfo")
	@RequiresPermissions(PermResourceConst.STOCK_INVENTORY_CHECK)
	public ModelAndView auditInfo(HttpServletRequest request, HttpServletResponse response,
			AuditDto auditDto) {
		
			SessionUser user=LoginSessionUtils.getUserInSession(request);
			
			String status=auditDto.getStatus();
			ResultDto<String> result=null;
			if(!StringUtils.hasLength(status)){
				result=new ResultDto<String>(false,"状态不能为空");
				return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(result));
			}
			
			try {
				    auditDto.setUserId(user.getUserId()+"");
				    auditDto.setDeptId(user.getCenterId()+"");
				    result=stockInventoryService.auditInfoRdTx(auditDto);
				    //TODO 审核操作日志  编号不是Long类型
					adminAuditLogService.log(user.getUserId(), TradeType.STOCK_INVENTORY.getType(),"审核盘点单",auditDto,0L);
			} catch (Exception e) {
					e.printStackTrace();
					logger.error("审核收料单数据异常");
					result=new ResultDto<String>(false,"数据审核异常");
			}
		
		
		    return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(result));
	}
}
