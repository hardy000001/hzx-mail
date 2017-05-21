package com.lq.lss.controller.stock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

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

import com.lq.lss.model.AdminDept;
import com.lq.lss.model.CStockInventory;
import com.lq.lss.model.SessionUser;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.AdminDeptService;
import com.lq.lss.core.service.StockInventoryDetailService;
import com.lq.lss.core.service.StockInventoryService;
import com.lq.lss.dto.StockInventoryDetailDto;
import com.lq.lss.dto.StockInventoryDto;

/**
 * 
 * @author  作者: hzx
 * @date 创建时间: 2016-12-29下午4:03:17
 */
@Controller
@RequestMapping(value ="/user/stock/stockInventoryUpdate.htm")
public class StockInventoryUpdateController extends ShiroBaseController<CStockInventory, String, StockInventoryService>{
	
	@Resource
	private StockInventoryService stockInventoryService;
	@Resource
	private StockInventoryDetailService stockInventoryDetailService;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	@Resource
	private AdminDeptService adminDeptService;
	
	
	@Value("/stock/stock_inventory_update")
	private String indexView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String inventorySerialno = ServletRequestUtils.getStringParameter(request, "id", null);
		String loginName=ServletRequestUtils.getStringParameter(request, "loginName", "");
		CStockInventory inventory=stockInventoryService.get(inventorySerialno);
		AdminDept adminDept= adminDeptService.get(user.getCenterId());
		if(adminDept==null){
			adminDept=new AdminDept();
		}
		modelMap.put("inventory", inventory);
		modelMap.put("adminDept", adminDept);
		modelMap.put("loginName", loginName);
		modelMap.put("user", user);
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	
	@RequestMapping(params = "method=getInventoryListByNo")
	public ModelAndView getInventoryListByNo(HttpServletRequest request,HttpServletResponse response) {
		
		String inventorySerialno = ServletRequestUtils.getStringParameter(request,
				"inventorySerialno", "");
		
		List<StockInventoryDetailDto> detailList = null;
		
		if(StringUtils.hasText(inventorySerialno)){
			    detailList = stockInventoryDetailService.queryInventoryDetailList(inventorySerialno);
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(detailList));
	}
	
	@RequestMapping(params = "method=updateApply")
	@RequiresPermissions(PermResourceConst.STOCK_INVENTORY_UPDATE)
	public ModelAndView updateApply(HttpServletRequest request,
			HttpServletResponse response, StockInventoryDto stockInventoryDto) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<String> result=null;
		
		try 
		{

				String inventorySerialno=stockInventoryDto.getInventorySerialno();
				stockInventoryDto.setInventorySerialno(inventorySerialno);
				stockInventoryDto.setDeptid(user.getCenterId());
				
				String listStr =stockInventoryDto.getDataList();
				JSONArray json = JSONArray.fromObject(listStr);
				@SuppressWarnings("unchecked")
				List<StockInventoryDetailDto> dataList = (List<StockInventoryDetailDto>) JSONArray
						.toCollection(json, StockInventoryDetailDto.class);
				stockInventoryDto.setStockInventoryDetailDtos(dataList);
		
			          ResultDto<String> validateResult=StockInventoryAddController.validataData(stockInventoryDto);
			          if(validateResult.isSuccess())
			          {
			        	       result=stockInventoryService.updateInventoryRdTx(stockInventoryDto);
			          }else
			          {
			        	       result=validateResult;
			          }
			    //操作日志
			    //adminAuditLogService.log(user.getUserId(), TradeType.STOCK_OUT.getType(),"出库单",stockInventoryDto,0L);    
			    
		} catch (Exception e) {
			    logger.error("修改盘点申请数据出现异常", e);
			    result=new ResultDto<String>(false,"数据提交失败");
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(result));
	}
	
	
	
}
