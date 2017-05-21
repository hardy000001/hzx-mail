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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.easyui.dto.ResultDto;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.StockCenterTransferDetailService;
import com.lq.lss.core.service.StockSendDetailService;
import com.lq.lss.core.service.StockSendService;
import com.lq.lss.dto.CStockSendDto;
import com.lq.lss.model.CStockSend;
import com.lq.lss.model.CStockSendDetail;
import com.lq.lss.model.SessionUser;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;


@Controller
@RequestMapping(value = "/user/stock/stocksend.htm")
public class StockSendController extends ShiroBaseController<CStockSend, String, StockSendService> {

	@Resource
	StockSendService stocksendService;
	@Resource
	StockSendDetailService stockSendDetailService;
	@Resource
	StockCenterTransferDetailService centerTransferDetailService;
	@Value("/stock/stock_send")
	private String indexView;

	@RequestMapping
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
	
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", PermResourceConst.CENTER_SEND_ADD);
        modelMap.put("update", PermResourceConst.CENTER_SEND_UPDATE);
        modelMap.put("del", PermResourceConst.CENTER_SEND_DEL);
        modelMap.put("check", PermResourceConst.CENTER_SEND_CHECK);
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}

	@RequestMapping(params = "method=getCStockSendDetailList")
	public ModelAndView getCStockSendDetailList(HttpServletRequest request,HttpServletResponse response) {
		
		String sendSerialno = ServletRequestUtils.getStringParameter(request,
				"id", "");
		System.out.println("2222222222222222222"+sendSerialno);
		List<CStockSendDetail> detialList = null;
		
		if (StringUtils.hasText(sendSerialno)) {
			                detialList = stockSendDetailService.findCStockSendDetailbySerialno(sendSerialno);
		}

		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(detialList));
	}
	
	@RequestMapping(params = "method=removeCStockSendAll")
	@RequiresPermissions(PermResourceConst.CENTER_SEND_DEL)
	public ModelAndView removeCStockSendAll(HttpServletRequest request, HttpServletResponse response){
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
					result=stocksendService.deleteCStockSend(idStr,String.valueOf(user.getCenterId()));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logger.error("删除采购数据出现异常"+e.getMessage());
				result=new ResultDto<String>(false,"删除数据出现异常");
			}
				
			return AjaxModelAndViewUtils.writeMsgReturnNull(response,
					JSONUtil.toJSonString(result));
			}
//	
//	
//	
//	@RequestMapping(params = "method=queryList")
//	public ModelAndView queryList(HttpServletRequest request, HttpServletResponse response,CStockCenterTransfer cStockCenterTransfer ) {
//
//		
//		int pageNumber = ServletRequestUtils.getIntParameter(request, "page", 1);
//		int pageSize = ServletRequestUtils.getIntParameter(request, "rows", 10);
//		String sort = ServletRequestUtils.getStringParameter(request, "sort", null);
//		String order = ServletRequestUtils.getStringParameter(request, "order", "");
//		PageParam pageParam = new PageParam(pageNumber, pageSize);
//		System.out.println("cStockCenterTransfer.getTradetype"+cStockCenterTransfer.getTradetype());
//		EasyUIObject<CStockCenterTransfer> easyui = new EasyUIObject<CStockCenterTransfer>();
//		try {
//			Pager<CStockCenterTransfer> page = centerTransferService.pagerList(pageParam, cStockCenterTransfer);
//			easyui.setRows(page.getResultList());
//			easyui.setPageNumber(page.getCurPage());
//			easyui.setPageSize(pageSize);
//			easyui.setTotal(page.getTotalCount());
//		} catch (Exception e) {
//			logger.error("query查询出现异常", e);
//		}
//		return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(easyui));
//	}
//	
//	
//	
//	
	/*
	 * 
	 * 审核
	 */
	@RequestMapping(params = "method=auditStockSend")
	@RequiresPermissions(PermResourceConst.CENTER_SEND_CHECK)
	public ModelAndView auditStockSend(HttpServletRequest request,HttpServletResponse response
			,CStockSendDto cStockSendDto) {
		
		 String sendSerialno= ServletRequestUtils.getStringParameter(request, "sendSerialno","");
		 System.out.println("sendSerialno"+sendSerialno);
		 System.out.println("cStockSendDto.getSendSerialno()"+cStockSendDto.getSendSerialno());
		 String status= ServletRequestUtils.getStringParameter(request, "status","");
		 SessionUser user = LoginSessionUtils.getUserInSession(request);
		 
			ResultDto<String> result = null;
			try {
			
				cStockSendDto.setUserId(user.getUserId());
				cStockSendDto.setDeptid(user.getCenterId());
				cStockSendDto.setAuditingoper(user.getUserId()+"");
				result=stocksendService.auditCStockSendByIdRdTx(cStockSendDto);
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("审核发料数据出现异常"+e.getMessage());
				result=new ResultDto<String>(false,"审核出现异常");

			}
			
			return AjaxModelAndViewUtils.writeMsgReturnNull(response,
					JSONUtil.toJSonString(result));
	}
	
	
	
	
	
	@RequestMapping(params = "method=antiAuditStockSend")
	@RequiresPermissions(PermResourceConst.CENTER_SEND_CHECK)
	public ModelAndView antiAuditStockSend(HttpServletRequest request,HttpServletResponse response
			,CStockSendDto cStockSendDto) {
		
		 String sendSerialno= ServletRequestUtils.getStringParameter(request, "sendSerialno","");
		 System.out.println("sendSerialno"+sendSerialno);
		 System.out.println("cStockSendDto.getSendSerialno()"+cStockSendDto.getSendSerialno());
		 String status= ServletRequestUtils.getStringParameter(request, "status","");
		 SessionUser user = LoginSessionUtils.getUserInSession(request);
		 
			ResultDto<String> result = null;
			try {
			
				cStockSendDto.setUserId(user.getUserId());
				cStockSendDto.setDeptid(user.getCenterId());
				cStockSendDto.setStatus("1");
				cStockSendDto.setAuditingoper(null);
				cStockSendDto.setAuditingtime(null);
				
				result=stocksendService.antiAuditCStockSendByIdRdTx(cStockSendDto);
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("反审核发料数据出现异常"+e.getMessage());
				result=new ResultDto<String>(false,"审核出现异常");

			}
			
			return AjaxModelAndViewUtils.writeMsgReturnNull(response,
					JSONUtil.toJSonString(result));
	}
}
