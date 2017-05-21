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
import com.lq.easyui.view.EasyUIObject;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.StockCenterTransferDetailService;
import com.lq.lss.core.service.StockCenterTransferInService;
import com.lq.lss.core.service.StockLeaseService;
import com.lq.lss.dto.CStockCenterTransferDto;
import com.lq.lss.dto.CStockSendDto;
import com.lq.lss.enums.TradeType;
import com.lq.lss.model.CStockCenterTransfer;
import com.lq.lss.model.CStockCenterTransferDetail;
import com.lq.lss.model.SessionUser;
import com.lq.pager.PageParam;
import com.lq.pager.Pager;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;


@Controller
@RequestMapping(value = "/user/stock/centertransfer_in.htm")
public class StockCenterTransferInController extends ShiroBaseController<CStockCenterTransfer, String, StockCenterTransferInService> {

	@Resource
	StockLeaseService slservice;
	@Resource
	StockCenterTransferInService centerTransferInService;
	@Resource
	StockCenterTransferDetailService centerTransferDetailService;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	
	@Value("/stock/stockCentertransfer_in")
	private String indexView;

	@RequestMapping
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", PermResourceConst.CENTER_CENTERTRANSFERIN_ADD);
        modelMap.put("update", PermResourceConst.CENTER_CENTERTRANSFERIN_UPDATE);
        modelMap.put("del", PermResourceConst.CENTER_CENTERTRANSFERIN_DEL);
        modelMap.put("check", PermResourceConst.CENTER_CENTERTRANSFERIN_CHECK);
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}

	@RequestMapping(params = "method=getCStockCenterTransferDetailList")
	public ModelAndView getCStockCenterTransferDetailList(HttpServletRequest request,HttpServletResponse response) {
		
		String tsfSerialno = ServletRequestUtils.getStringParameter(request,
				"id", "");
		
		List<CStockCenterTransferDetail> detialList = null;
		
		if (StringUtils.hasText(tsfSerialno)) {
			                detialList = centerTransferDetailService.findCsctdBytsfSerialno(tsfSerialno);
		}

		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(detialList));
	}
	
	@RequestMapping(params = "method=removeCStockCenterTransferInAll")
	@RequiresPermissions(PermResourceConst.CENTER_CENTERTRANSFERIN_DEL)
	public ModelAndView removeCStockCenterTransferAll(HttpServletRequest request, HttpServletResponse response){
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
					result=centerTransferInService.deleteCStockCenterTransferIn(idStr,String.valueOf(user.getCenterId()));
					adminAuditLogService.log(user.getUserId(), TradeType.STOCKCENTERTRANSFER_IN.getType(),"删除调入单",idStr,0L);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logger.error("删除采购数据出现异常"+e.getMessage());
				result=new ResultDto<String>(false,"删除数据出现异常");
			}
				
			return AjaxModelAndViewUtils.writeMsgReturnNull(response,
					JSONUtil.toJSonString(result));
			}
	
	
	@RequestMapping(params = "method=queryList")
	public ModelAndView queryList(HttpServletRequest request, HttpServletResponse response,CStockCenterTransfer cStockCenterTransfer ) {

		
		int pageNumber = ServletRequestUtils.getIntParameter(request, "page", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "rows", 10);
		String sort = ServletRequestUtils.getStringParameter(request, "sort", null);
		String order = ServletRequestUtils.getStringParameter(request, "order", "");
		PageParam pageParam = new PageParam(pageNumber, pageSize);
		System.out.println("cStockCenterTransfer.getTradetype"+cStockCenterTransfer.getTradetype());
		EasyUIObject<CStockCenterTransfer> easyui = new EasyUIObject<CStockCenterTransfer>();
		try {
			Pager<CStockCenterTransfer> page = centerTransferInService.pagerList(pageParam, cStockCenterTransfer);
			easyui.setRows(page.getResultList());
			easyui.setPageNumber(page.getCurPage());
			easyui.setPageSize(pageSize);
			easyui.setTotal(page.getTotalCount());
		} catch (Exception e) {
			logger.error("query查询出现异常", e);
		}
		return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(easyui));
	}
	
	
	
	/*
	 * 
	 * 审核
	 */
	@RequestMapping(params = "method=auditStockCenterTransferIn")
	@RequiresPermissions(PermResourceConst.CENTER_CENTERTRANSFERIN_CHECK)
	public ModelAndView auditStockCenterTransferIn(HttpServletRequest request,HttpServletResponse response
			,CStockCenterTransferDto stockCenterTransferDto){ 
		
		 String tsfSerialno= ServletRequestUtils.getStringParameter(request, "tsfSerialno","");
		 String status= ServletRequestUtils.getStringParameter(request, "status","");
		 SessionUser user = LoginSessionUtils.getUserInSession(request);
			ResultDto<String> result = null;
			try {
				stockCenterTransferDto.setUserId(user.getUserId());
				stockCenterTransferDto.setFromDeptid(user.getCenterId()+"");
				stockCenterTransferDto.setAuditingoper(user.getUserId()+"");
				result=centerTransferInService.auditStockCenterTransferInByIdRdTx(stockCenterTransferDto);
				adminAuditLogService.log(user.getUserId(), TradeType.STOCKCENTERTRANSFER_IN.getType(),"审核调入单",stockCenterTransferDto,0L);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("审核入库数据出现异常"+e.getMessage());
				result=new ResultDto<String>(false,"审核出现异常");

			}
			
			return AjaxModelAndViewUtils.writeMsgReturnNull(response,
					JSONUtil.toJSonString(result));
	}
	
	
	
	/*
	 * 
	 * 反审核
	 */
	
	@RequestMapping(params = "method=antiAuditStockCentertansferIn")
	@RequiresPermissions(PermResourceConst.CENTER_CENTERTRANSFERIN_CHECK)
	public ModelAndView antiAuditStockCentertansferIn(HttpServletRequest request,HttpServletResponse response
			,CStockCenterTransferDto stockCenterTransferDto) {
		
		 String tsfSerialno= ServletRequestUtils.getStringParameter(request, "tsfSerialno","");
		 System.out.println("tsfSerialno"+tsfSerialno);
		
		 String status= ServletRequestUtils.getStringParameter(request, "status","");
		 SessionUser user = LoginSessionUtils.getUserInSession(request);
		 
			ResultDto<String> result = null;
			try {
			
				stockCenterTransferDto.setUserId(user.getUserId());
				stockCenterTransferDto.setFromDeptid(user.getCenterId()+"");
				stockCenterTransferDto.setStatus("1");
				stockCenterTransferDto.setAuditingoper(null);
				stockCenterTransferDto.setAuditingtime(null);
				
				result=centerTransferInService.antiAuditStockCenterTransferInByIdRdTx(stockCenterTransferDto);
				adminAuditLogService.log(user.getUserId(), TradeType.ANTI_STOCK_CENTERTRANSFER_IN.getType(),"反审核中心外调入单",stockCenterTransferDto,0L);
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("反审核中心外调入数据出现异常"+e.getMessage());
				result=new ResultDto<String>(false,"审核出现异常");

			}
			
			return AjaxModelAndViewUtils.writeMsgReturnNull(response,
					JSONUtil.toJSonString(result));
	}
}
