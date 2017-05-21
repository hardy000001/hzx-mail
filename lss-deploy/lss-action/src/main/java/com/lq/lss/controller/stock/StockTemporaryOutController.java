package com.lq.lss.controller.stock;

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

import com.lq.lss.model.CStockOut;
import com.lq.lss.model.CStockOutDetail;
import com.lq.lss.model.SessionUser;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.StockOutDetailService;
import com.lq.lss.core.service.StockOutService;
import com.lq.lss.dto.StockOutDto;
import com.lq.lss.enums.TradeType;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-10-08 09:26:28
 */
@Controller
@RequestMapping(value ="/user/stock/stocktemporaryinOut.htm")
public class StockTemporaryOutController extends ShiroBaseController<CStockOut, String, StockOutService> {
	
	@Resource
	private StockOutService stockOutService;
	@Resource
	private StockOutDetailService stockOutDetailService;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	
	
	@Value("/stock/stock_temporary_out")
	private String indexView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", PermResourceConst.STOCK_TEMPORARY_OUT_ADD);
        modelMap.put("update", PermResourceConst.STOCK_TEMPORARY_OUT_UPDATE);
        modelMap.put("del", PermResourceConst.STOCK_TEMPORARY_OUT_DEL);
        modelMap.put("check", PermResourceConst.STOCK_TEMPORARY_OUT_CHECK);
		modelMap.put("deptid", user.getDeptId());
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	
	
	@RequestMapping(params = "method=remove")
	@RequiresPermissions(PermResourceConst.STOCK_TEMPORARY_OUT_DEL)
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
			result=stockOutService.deleteStockOutInfoById(idStr,String.valueOf(user.getCenterId()));
			  //TODO 删除操作日志  编号不是Long类型
			  adminAuditLogService.log(user.getUserId(), TradeType.STOCK_TEMPORARY_OUT.getType(),"删除提暂存单",idStr,0L); 
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除销售数据出现异常"+e.getMessage());
			result=new ResultDto<String>(false,"删除数据出现异常");
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(result));
	}
	
	@RequestMapping(params = "method=getStockOutDetailList")
	public ModelAndView gerSaleDetailList(HttpServletRequest request,HttpServletResponse response) {
		
		String outSerialno = ServletRequestUtils.getStringParameter(request,
				"id", "");
		
		List<CStockOutDetail> detialList = null;
		
		if (StringUtils.hasText(outSerialno)) {
			                detialList = stockOutDetailService.queryStockOutListById(outSerialno);
		}

		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(detialList));
	}
	
	@RequestMapping(params = "method=auditInfo")
	@RequiresPermissions(PermResourceConst.STOCK_TEMPORARY_OUT_CHECK)
	public ModelAndView auditInfo(HttpServletRequest request, HttpServletResponse response,
			StockOutDto stockOutDto) {
		
			SessionUser user=LoginSessionUtils.getUserInSession(request);
			stockOutDto.setUserId(user.getUserId());
			stockOutDto.setDeptid(user.getCenterId());
			String status=stockOutDto.getStatus();
			ResultDto<String> result=null;
			if(!StringUtils.hasLength(status)){
				result=new ResultDto<String>(false,"状态不能为空");
				return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(result));
			}
			
			try {
				  result=stockOutService.audiStockTemporaryOutRdTx(stockOutDto);
				  //TODO 审核操作日志  编号不是Long类型
				  adminAuditLogService.log(user.getUserId(), TradeType.STOCK_TEMPORARY_OUT.getType(),"审核提暂存单",stockOutDto,0L); 
			} catch (Exception e) {
					e.printStackTrace();
					logger.error("销售审核修改数据异常");
					result=new ResultDto<String>(false,"数据修改异常");
			}
		
		
		    return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(result));
	}
	
	
	
	
	
	@RequestMapping(params = "method=antiAudit")
	@RequiresPermissions(PermResourceConst.STOCK_TEMPORARY_OUT_CHECK)
	public ModelAndView antiAudit(HttpServletRequest request, HttpServletResponse response,
			StockOutDto stockOutDto) {
		
			SessionUser user=LoginSessionUtils.getUserInSession(request);
			stockOutDto.setUserId(user.getUserId());
			stockOutDto.setDeptid(user.getCenterId());
			String status=stockOutDto.getStatus();
			ResultDto<String> result=null;
			if(!StringUtils.hasLength(status)){
				result=new ResultDto<String>(false,"状态不能为空");
				return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(result));
			}
			
			try {
				  result=stockOutService.antiAudiStockTemporaryOutRdTx(stockOutDto);
				  //TODO 审核操作日志  编号不是Long类型
				  adminAuditLogService.log(user.getUserId(), TradeType.ANTI_STOCK_TEMPORARY_OUT.getType(),"反审核提暂存单",stockOutDto,0L);
			} catch (Exception e) {
					e.printStackTrace();
					logger.error("提暂存反审核修改数据异常");
					result=new ResultDto<String>(false,"数据修改异常");
			}
		
		
		    return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(result));
	}
	
	
}
