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
import com.lq.lss.model.CStockReceiptDetail;
import com.lq.lss.model.CStockSendDetail;
import com.lq.lss.model.CStockTotalflow;
import com.lq.lss.model.SessionUser;
import com.lq.lss.core.service.MaterialTypeService;
import com.lq.lss.core.service.StockReceiptDetailService;
import com.lq.lss.core.service.StockSendDetailService;
import com.lq.lss.core.service.StockTotalflowService;
import com.lq.lss.dto.StockTotalflowDto;
import com.lq.lss.enums.TradeType;

/**
 * 租赁流水汇总
 * @author  作者: hzx
 * @date 创建时间: 2016-11-23 15:27:44
 */
@Controller
@RequestMapping(value ="/user/stock/stockTotalflow.htm")
public class StockTotalflowController extends ShiroBaseController<CStockTotalflow, String, StockTotalflowService>{
	
	@Resource
	private StockTotalflowService stockTotalflowService;
	@Resource
	StockSendDetailService stockSendDetailService;
	@Resource
	private StockReceiptDetailService stockReceiptDetailService;
	@Resource
	MaterialTypeService materialTypeService;
	
	@Value("/stock/stock_total_flow")
	private String indexView;
	@Value("/stock/stock_total_flow_detail")
	private String detailView;
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		List<BMaterialType> typeList=materialTypeService.loadAll();
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
	
	@RequestMapping(params = "method=leaseQuery")
	public ModelAndView leaseQuery(CStockTotalflow cStockTotalflow, HttpServletRequest request, HttpServletResponse response) {

		SessionUser user = LoginSessionUtils.getUserInSession(request);
		
		int pageNumber = ServletRequestUtils.getIntParameter(request, "page", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "rows", 10);
		String sort = ServletRequestUtils.getStringParameter(request, "sort", null);
		String order = ServletRequestUtils.getStringParameter(request, "order", "");
		PageParam pageParam = new PageParam(pageNumber, pageSize);
		if (StringUtils.hasLength(sort)) {
			cStockTotalflow.setOrderStr(sort + " " + order);
		}
		
		String type1=String.valueOf(TradeType.STOCK_SEND.getType());
		String type2=String.valueOf(TradeType.STOCK_RECEIPT.getType());
		
		String orderType=cStockTotalflow.getOrderType();
		if(StringUtils.hasLength(orderType)){
			orderType="("+orderType+")";
		}else{
			orderType="("+type1+","+type2+")";
		}
		cStockTotalflow.setOrderType(orderType);
		
		EasyUIObject<Map<String, Object>> easyui = new EasyUIObject<Map<String, Object>>();
		try {
			StockTotalflowDto stockTotalflowDto=new StockTotalflowDto();
			stockTotalflowDto.setDeptid(cStockTotalflow.getDeptid());
			stockTotalflowDto.setFromMchcode(cStockTotalflow.getFromMchcode());
			stockTotalflowDto.setToMchcode(cStockTotalflow.getToMchcode());
			stockTotalflowDto.setOrderType(orderType);
			stockTotalflowDto.setOrderNo(cStockTotalflow.getOrderNo());
			stockTotalflowDto.setHtcode(cStockTotalflow.getHtcode());
			stockTotalflowDto.setDealDate(cStockTotalflow.getDealDate());
			stockTotalflowDto.setCreateDate(cStockTotalflow.getCreateDate());
			stockTotalflowDto.setDeptid(user.getCenterId());
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
		String type1=String.valueOf(TradeType.STOCK_SEND.getType());
		String type2=String.valueOf(TradeType.STOCK_RECEIPT.getType());
		
		if(type1.equals(orderType)){
			List<CStockSendDetail> sendDetails = stockSendDetailService
					.findCStockSendDetailbySerialno(serialno);
			modelMap.put("sendDetails", sendDetails);
		}
        if(type2.equals(orderType)){
        	List<CStockReceiptDetail> receiptDetails = stockReceiptDetailService
    				.queryReceiptDetailListById(serialno);
        	modelMap.put("receiptDetails", receiptDetails);
		}
        modelMap.put("orderType", orderType);
        modelMap.put("type1", type1);
        modelMap.put("type2", type2);

		return new ModelAndView(detailView, modelMap);
	}
	
	
}
