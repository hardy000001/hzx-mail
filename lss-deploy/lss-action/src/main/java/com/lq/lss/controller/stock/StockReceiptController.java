package com.lq.lss.controller.stock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.annotation.Resource;

import com.lq.lss.controller.print.StockReceiptPrintController;
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

import com.lq.lss.model.BRepairInfo;
import com.lq.lss.model.CStockReceipt;
import com.lq.lss.model.CStockReceiptDetail;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.SystemConst;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.RepairInfoService;
import com.lq.lss.core.service.StockReceiptDetailService;
import com.lq.lss.core.service.StockReceiptService;
import com.lq.lss.enums.TradeType;

/**
 * 收料
 * @author  作者: hzx
 * @date 创建时间: 2016-11-09 12:31:33
 */
@Controller
@RequestMapping(value ="/user/stock/stockReceipt.htm")
public class StockReceiptController extends ShiroBaseController<CStockReceipt, String, StockReceiptService> {
	
	@Resource
	private StockReceiptService stockReceiptService;
	@Resource
	private StockReceiptDetailService stockReceiptDetailService;
	@Resource
	private RepairInfoService repairInfoService;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	
	
	@Value("/stock/stock_receipt")
	private String indexView;
	@Value("/stock/stock_receipt_detail")
	private String detailView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		List<BRepairInfo> repairList=repairInfoService.loadAll();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", PermResourceConst.CENTER_RECEIPT_ADD);
        modelMap.put("update", PermResourceConst.CENTER_RECEIPT_UPDATE);
        modelMap.put("del", PermResourceConst.CENTER_RECEIPT_DEL);
        modelMap.put("check", PermResourceConst.CENTER_TRANS_CHECK);
		modelMap.put("deptId", user.getCenterId());
		modelMap.put("repairList", repairList);
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	
	@RequestMapping(params = "method=receiptDetail")
	public ModelAndView receiptDetail(HttpServletRequest request, HttpServletResponse response) {
		
		
		String index = ServletRequestUtils.getStringParameter(request, "index", null);
		String typeid = ServletRequestUtils.getStringParameter(request, "typeid", null);
		List<BRepairInfo> repairList=repairInfoService.queryRepairInfoByTypeid(typeid);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		modelMap.put("index", index);
		modelMap.put("repairList", repairList);
		
		return new ModelAndView(detailView, modelMap);
	}
	@RequestMapping(params = "method=getFormData")
	public ModelAndView getFormData(HttpServletRequest request, HttpServletResponse response) {
		
		List<BRepairInfo> repairList=repairInfoService.loadAll();
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		for (BRepairInfo bRepairInfo : repairList) {
			String index=SystemConst.PROPERTY_PREFIX+bRepairInfo.getId()+"";
			String value = ServletRequestUtils.getStringParameter(request, index, "");
			modelMap.put(index, value);
		}
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(modelMap));
	}
	
	
	@RequestMapping(params = "method=remove")
	@RequiresPermissions(PermResourceConst.CENTER_RECEIPT_DEL)
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
			  result=stockReceiptService.deleteStockReceiptById(idStr, user.getCenterId()+"");
			  //TODO 删除操作日志  编号不是Long类型
			  adminAuditLogService.log(user.getUserId(), TradeType.STOCK_RECEIPT.getType(),"删除收料单",idStr,0L); 
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除收料单数据出现异常"+e.getMessage());
			result=new ResultDto<String>(false,"删除数据出现异常");
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(result));
	}
	@RequestMapping(params = "method=getReceiptDetailList")
	public ModelAndView getReceiptDetailList(HttpServletRequest request,HttpServletResponse response) {
		
		String receiptSerialno = ServletRequestUtils.getStringParameter(request,
				"id", "");
		
//		List<CStockReceiptDetail> detialList = null;
//		
//		if (StringUtils.hasText(receiptSerialno)) {
//			detialList= stockReceiptDetailService.queryReceiptDetailListById(receiptSerialno);
//		}
		List<Map<String, Object>> detailList = null;
		
		if (StringUtils.hasText(receiptSerialno)) {
			detailList=stockReceiptDetailService.getReceiptDetailListById(receiptSerialno);
		}
		
		List<BRepairInfo> repairInfos=repairInfoService.queryRepairInfoByTypeid("");
		List<Map<String, Object>> maps=StockReceiptPrintController.totalReceiptList(detailList, repairInfos);
		
		detailList.addAll(maps);

		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(detailList));
	}
	@RequestMapping(params = "method=getReceiptList")
	public ModelAndView getReceiptList(HttpServletRequest request,HttpServletResponse response) {
		
		String receiptSerialno = ServletRequestUtils.getStringParameter(request,
				"id", "");
		
		List<Map<String, Object>> detailList = null;
		
		if (StringUtils.hasText(receiptSerialno)) {
			detailList= stockReceiptDetailService.getReceiptDetailListById(receiptSerialno);
		}
		
		List<BRepairInfo> repairInfos=repairInfoService.queryRepairInfoByTypeid("");
		List<Map<String, Object>> maps=StockReceiptPrintController.totalReceiptList(detailList, repairInfos);
		
		detailList.add(maps.get(maps.size()-1));

		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(detailList));
	}
	
}
