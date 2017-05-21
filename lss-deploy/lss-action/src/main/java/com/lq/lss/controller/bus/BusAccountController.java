package com.lq.lss.controller.bus;

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
import com.lq.lss.model.CBusAccount;
import com.lq.lss.model.SessionUser;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.BusAccountService;
import com.lq.lss.dto.BusHtStopflowDto;
import com.lq.lss.dto.CBusAccountDto;
import com.lq.lss.enums.TradeType;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-18 09:56:42
 */
@Controller
@RequestMapping(value ="/user/bus/bus_account.htm")
public class BusAccountController extends ShiroBaseController<CBusAccount, String, BusAccountService>{
	
	@Resource
	private BusAccountService cBusAccountService;
	
	@Resource
	private AdminAuditLogService adminAuditLogService;
	@Value("/bus/bus_account")
	private String indexView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", PermResourceConst.BUS_ACCOUNT_ADD);
        modelMap.put("update", PermResourceConst.BUS_ACCOUNT_UPDATE);
        modelMap.put("del", PermResourceConst.BUS_ACCOUNT_DEL);
        modelMap.put("check", PermResourceConst.BUS_ACCOUNT_CHECK);
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	
	@RequestMapping(params = "method=removeBusAccount")
	@RequiresPermissions(PermResourceConst.BUS_ACCOUNT_DEL)
	public ModelAndView removeBusAccount(HttpServletRequest request, HttpServletResponse response){
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
					result=cBusAccountService.deleteBusAccountById(idStr,String.valueOf(user.getCenterId()));
					adminAuditLogService.log(user.getUserId(), TradeType.BUS_ACCOUNT.getType(),"删除收付款单",idStr,0L);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logger.error("删除采购数据出现异常"+e.getMessage());
				result=new ResultDto<String>(false,"删除数据出现异常");
			}
				
			return AjaxModelAndViewUtils.writeMsgReturnNull(response,
					JSONUtil.toJSonString(result));
			}
	
	
	
	
	@RequestMapping(params = "method=updateBusaccount")
	@RequiresPermissions(PermResourceConst.BUS_ACCOUNT_UPDATE)
	public ModelAndView updateBusaccount(HttpServletRequest request, HttpServletResponse response,
			CBusAccountDto cBusAccountDto){
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<String> result=null;
		
		if (notUpdate) {
			result = new ResultDto<String>(false, updateAlertMsg);
			return AjaxModelAndViewUtils.writeMsgReturnNull(response,
					JSONUtil.toJSonString(result));
		}
		try 
		{
			  
		
				ResultDto<String> validateResult =validataData(cBusAccountDto);
			          if(validateResult.isSuccess())
			          {
			        	       result=cBusAccountService.updateBusAccountRdTx(cBusAccountDto);
			        	       adminAuditLogService.log(user.getUserId(), TradeType.BUS_ACCOUNT.getType(),"修改收付款单",cBusAccountDto,0L);
			          }else
			          {
			        	       result=validateResult;
			          }
			    //操作日志
				
		} catch (Exception e) {
			    logger.error("修改合同数据出现异常", e);
			    result=new ResultDto<String>(false,"数据提交失败");
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(result));
	}
	
	@RequestMapping(params = "method=auditBusaccount")
	@RequiresPermissions(PermResourceConst.BUS_ACCOUNT_CHECK)
	public ModelAndView auditCBusHtStopflow(HttpServletRequest request, HttpServletResponse response,
			CBusAccountDto cBusAccountDto){
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<String> result=null;
		
		if (notUpdate) {
			result = new ResultDto<String>(false, updateAlertMsg);
			return AjaxModelAndViewUtils.writeMsgReturnNull(response,
					JSONUtil.toJSonString(result));
		}
		try 
		{
			  
		
			
			        	       result=cBusAccountService.auditBusAccountRdTx(cBusAccountDto,user);
			        	       adminAuditLogService.log(user.getUserId(), TradeType.BUS_ACCOUNT.getType(),"审核收付款单",cBusAccountDto,0L);
			          
			    //操作日志
				
		} catch (Exception e) {
			    logger.error("审核收付款数据出现异常", e);
			    result=new ResultDto<String>(false,"数据提交失败");
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(result));
	}
	
	
	protected  ResultDto<String> validataData( CBusAccountDto cBusAccountDto){
		System.out.println("cBusAccountDto.getTradeType()"+cBusAccountDto.getTradeType());
		if(cBusAccountDto.getHtcode()==""){
			return new ResultDto<String>(false,"合同号不能为空");
		}
		if(cBusAccountDto.getTradeAmt()==""){
			return new ResultDto<String>(false,"金额不能为空");
		}
		if(cBusAccountDto.getTradeType()=="" || cBusAccountDto.getTradeType()==null){
			return new ResultDto<String>(false,"收费款类型不能为空");
		}
		
		
			
			 return new ResultDto<String>(true,"数据通过验证");
		 }
	
}
