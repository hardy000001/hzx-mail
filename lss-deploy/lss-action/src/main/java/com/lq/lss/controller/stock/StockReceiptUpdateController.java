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

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.lss.model.AdminDept;
import com.lq.lss.model.BRepairInfo;
import com.lq.lss.model.CStockReceipt;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.SystemConst;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.AdminDeptService;
import com.lq.lss.core.service.RepairInfoService;
import com.lq.lss.core.service.StockReceiptDetailService;
import com.lq.lss.core.service.StockReceiptService;
import com.lq.lss.dto.AuditDto;
import com.lq.lss.dto.StockReceiptDetailDto;
import com.lq.lss.dto.StockReceiptDto;
import com.lq.lss.dto.StockReceiptRepairinfoDto;
import com.lq.lss.enums.TradeType;

/**
 * 收料
 * @author  作者: hzx
 * @date 创建时间: 2016-11-09 12:31:33
 */
@Controller
@RequestMapping(value ="/user/stock/stockReceiptUpdate.htm")
public class StockReceiptUpdateController extends ShiroBaseController<CStockReceipt, String, StockReceiptService>{
	
	@Resource
	private StockReceiptService stockReceiptService;
	@Resource
	private StockReceiptDetailService stockReceiptDetailService;
	@Resource
	private RepairInfoService repairInfoService;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	@Resource
	private AdminDeptService adminDeptService;
	
	
	@Value("/stock/stock_receipt_update")
	private String indexView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		String receiptSerialno=ServletRequestUtils.getStringParameter(request, "id", "");
		String loginName=ServletRequestUtils.getStringParameter(request, "loginName", "");
		List<BRepairInfo> repairList=repairInfoService.loadAll();
		List<Map<String, Object>> detailList = stockReceiptDetailService
				.getReceiptDetailListById(receiptSerialno);
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		AdminDept adminDept= adminDeptService.get(user.getCenterId());
		if(adminDept==null){
			adminDept=new AdminDept();
		}
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("receiptSerialno", receiptSerialno);
		modelMap.put("loginName", loginName);
		modelMap.put("repairList", repairList);
		modelMap.put("detailList", detailList);
		modelMap.put("adminDept", adminDept);
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	
	
	
	@RequestMapping(params = "method=updateApply")
	@RequiresPermissions(PermResourceConst.CENTER_RECEIPT_UPDATE)
	public ModelAndView updateApply(HttpServletRequest request,
			HttpServletResponse response, StockReceiptDto stockReceiptDto) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<String> result=null;
		
		try 
		{

				String receiptSerialno=stockReceiptDto.getReceiptSerialno();
				stockReceiptDto.setReceiptSerialno(receiptSerialno);
				stockReceiptDto.setAuditingoper(user.getUserId()+"");
				stockReceiptDto.setDeptid(user.getCenterId());
				stockReceiptDto.setMchcode(SystemConst.ZX_MCHCODE);
				
				String listStr =stockReceiptDto.getDataList();
				JSONArray json = JSONArray.fromObject(listStr);
				@SuppressWarnings("unchecked")
				List<StockReceiptDetailDto> dataList = (List<StockReceiptDetailDto>) JSONArray
						.toCollection(json, StockReceiptDetailDto.class);
				stockReceiptDto.setStockReceiptDetailDtos(dataList);
			    List<StockReceiptRepairinfoDto> repairs = StockReceiptAddController
					    .repairItemJsonDispose(listStr, receiptSerialno);
				stockReceiptDto.setStockReceiptRepairinfoDtos(repairs);
		
			          ResultDto<String> validateResult=StockReceiptAddController.validataData(stockReceiptDto);
			          if(validateResult.isSuccess())
			          {
			        	       result=stockReceiptService.updateStockReceiptRdTx(stockReceiptDto);
			          }else
			          {
			        	       result=validateResult;
			          }
			    //TODO 修改操作日志  编号不是Long类型
			    adminAuditLogService.log(user.getUserId(), TradeType.STOCK_RECEIPT.getType(),"修改收料单",stockReceiptDto,0L);    
		} catch (Exception e) {
			    logger.error("修改收料申请数据出现异常", e);
			    result=new ResultDto<String>(false,"数据提交失败");
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(result));
	}
	
	@RequestMapping(params = "method=getReceiptData")
	public ModelAndView getReceiptData(HttpServletRequest request,HttpServletResponse response) {
		
		String receiptSerialno = ServletRequestUtils.getStringParameter(request,
				"id", "");
		String loginName = ServletRequestUtils.getStringParameter(request,
				"loginName", "");
		
		CStockReceipt stockReceipt=stockReceiptService.get(receiptSerialno);
		stockReceipt.setLoginName(loginName);
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(stockReceipt));
	}
	@RequestMapping(params = "method=auditInfo")
	@RequiresPermissions(PermResourceConst.CENTER_RECEIPT_CHECK)
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
				    result=stockReceiptService.auditInfoRdTx(auditDto);
				    //TODO 审核操作日志  编号不是Long类型
					adminAuditLogService.log(user.getUserId(), TradeType.STOCK_RECEIPT.getType(),"审核收料单",auditDto,0L);
			} catch (Exception e) {
					e.printStackTrace();
					logger.error("审核收料单数据异常");
					result=new ResultDto<String>(false,"数据审核异常");
			}
		
		
		    return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(result));
	}
	@RequestMapping(params = "method=antiAudit")
	@RequiresPermissions(PermResourceConst.CENTER_RECEIPT_CHECK)
	public ModelAndView antiAudit(HttpServletRequest request, HttpServletResponse response,
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
				    result=stockReceiptService.antiAuditInfoRdTx(auditDto);
				    //TODO 审核操作日志  编号不是Long类型
					adminAuditLogService.log(user.getUserId(), TradeType.ANTI_STOCK_RECEIPT.getType(),"反审核收料单",auditDto,0L);
			} catch (Exception e) {
					e.printStackTrace();
					logger.error("反审核收料单数据异常");
					result=new ResultDto<String>(false,"数据反审核异常");
			}
		
		
		    return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(result));
	}
	
	
}
