package com.lq.lss.controller.stock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;

import com.lq.pager.PageParam;
import com.lq.pager.Pager;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;
import com.lq.easyui.view.AjaxModelAndViewUtils;
import com.lq.easyui.view.EasyUIObject;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.controller.util.LoginSessionUtils;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.lss.model.BMaterialType;
import com.lq.lss.model.CStockCenterTransferDetail;
import com.lq.lss.model.CStockOutDetail;
import com.lq.lss.model.CStockTotalflow;
import com.lq.lss.model.SessionUser;
import com.lq.lss.core.service.MaterialTypeService;
import com.lq.lss.core.service.StockCenterTransferDetailService;
import com.lq.lss.core.service.StockInDetailService;
import com.lq.lss.core.service.StockOutDetailService;
import com.lq.lss.core.service.StockTotalflowService;
import com.lq.lss.dto.StockTotalflowDto;
import com.lq.lss.enums.TradeType;

/**
 * 调拨流水汇总
 * @author  作者: hzx
 * @date 创建时间: 2016-11-23 15:27:44
 */
@Controller
@RequestMapping(value ="/user/stock/stockTransferTotalflow.htm")
public class StockTransferTotalflowController extends ShiroBaseController<CStockTotalflow, String, StockTotalflowService>{
	
	@Resource
	private StockTotalflowService stockTotalflowService;
	@Resource
	StockCenterTransferDetailService centerTransferDetailService;
	@Resource
	private StockOutDetailService stockOutDetailService;
	@Resource
	private StockInDetailService stockInDetailService;
	@Resource
	MaterialTypeService materialTypeService;
	
	@Value("/stock/stock_transfer_totalflow")
	private String indexView;
	@Value("/stock/stock_total_flow_detail")
	private String detailView;
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		List<BMaterialType> typeList=materialTypeService.loadAll();
		Map<String, Object> modelMap = new HashMap<String, Object>();
//		modelMap.put("add", PermResourceConst.CENTER_OUT_ADD);
//        modelMap.put("update", PermResourceConst.CENTER_OUT_UPDATE);
//        modelMap.put("del", PermResourceConst.CENTER_OUT_DEL);
//        modelMap.put("check", PermResourceConst.CENTER_OUT_CHECK);
//		modelMap.put("deptid", user.getDeptId());
		modelMap.put("typeList", typeList);
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	
	@RequestMapping(params = "method=transferQuery")
	public ModelAndView query(StockTotalflowDto stockTotalflowDto,HttpServletRequest request, HttpServletResponse response) {

		SessionUser user = LoginSessionUtils.getUserInSession(request);
		stockTotalflowDto.setDeptid(user.getCenterId());
		
		int pageNumber = ServletRequestUtils.getIntParameter(request, "page", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "rows", 10);
		
		PageParam pageParam = new PageParam(pageNumber, pageSize);
		
		//调拨
		String type1=String.valueOf(TradeType.STOCKCENTERTRANSFER_IN.getType());
		String type2=String.valueOf(TradeType.STOCKCENTERTRANSFER_OUT.getType());
		String type3=String.valueOf(TradeType.STOCK_TEMPORARY_IN.getType());
		String type4=String.valueOf(TradeType.STOCK_TEMPORARY_OUT.getType());
		String orderType=stockTotalflowDto.getOrderType();
		if(StringUtils.hasLength(orderType)){
			 orderType="("+orderType+")";
		}else{
			 orderType="("+type1+","+type2+","+type3+","+type4+")";
		}
		stockTotalflowDto.setOrderType(orderType);
		
		EasyUIObject<Map<String, Object>> easyui = new EasyUIObject<Map<String, Object>>();
		try {
			stockTotalflowDto.setQueryType(3);
			Pager<Map<String, Object>> page = stockTotalflowService
					.queryFlowTotalPager(pageParam, stockTotalflowDto);
			easyui.setRows(page.getResultList());
			easyui.setPageNumber(page.getCurPage());
			easyui.setPageSize(pageSize);
			easyui.setTotal(page.getTotalCount());
		} catch (Exception e) {
			logger.error("query查询出现异常", e);
		}
		return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(easyui));
	}
	
	
	@RequestMapping(params = "method=loadDetail")
	public ModelAndView loadDetail(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> modelMap = new HashMap<String, Object>();
		String serialno = ServletRequestUtils.getStringParameter(request, "id",
				null);
		String orderType = ServletRequestUtils.getStringParameter(request, "orderType",
				null);
		String type1=String.valueOf(TradeType.STOCKCENTERTRANSFER_IN.getType());
		String type2=String.valueOf(TradeType.STOCKCENTERTRANSFER_OUT.getType());
		String type3=String.valueOf(TradeType.STOCK_TEMPORARY_IN.getType());
		String type4=String.valueOf(TradeType.STOCK_TEMPORARY_OUT.getType());
		if(type1.equals(orderType) || type2.equals(orderType)){
			List<CStockCenterTransferDetail> centerTransferDetails = centerTransferDetailService
					.findCsctdBytsfSerialno(serialno);
			modelMap.put("centerTransferDetails", centerTransferDetails);
		}
		if(type3.equals(orderType))
		{
		List<Map<String, Object>> inDetails=stockInDetailService.getInDetailListById(serialno);
		modelMap.put("inDetails", inDetails);
		}
		if(type4.equals(orderType))
		{
		List<CStockOutDetail> ouDetails = stockOutDetailService
				.queryStockOutListById(serialno);
		modelMap.put("ouDetails", ouDetails);
		}
		
        modelMap.put("orderType", orderType);
        
		return new ModelAndView(detailView, modelMap);
	}
	
}
