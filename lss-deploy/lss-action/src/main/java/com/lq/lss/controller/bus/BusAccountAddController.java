package com.lq.lss.controller.bus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.lq.lss.model.CBusAccount;
import com.lq.lss.model.CBusHt;
import com.lq.lss.model.SessionUser;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.BusAccountService;
import com.lq.lss.core.service.BusHtService;
import com.lq.lss.dto.BusHtStopflowDto;
import com.lq.lss.dto.CBusAccountDto;
import com.lq.lss.enums.TradeType;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-18 09:56:42
 */
@Controller
@RequestMapping(value ="/user/bus/bus_account_add.htm")
public class BusAccountAddController extends ShiroBaseController<CBusAccount, String, BusAccountService>{
	
	@Resource
	private BusAccountService busAccountService;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	@Resource
	private  BusHtService busHtService;

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
	
	
	@RequestMapping(params = "method=saveBuAccount")
	@RequiresPermissions(PermResourceConst.BUS_ACCOUNT_ADD)
	public ModelAndView saveBuAccount(HttpServletRequest request, HttpServletResponse response,
			CBusAccountDto cBusAccountDto) {
		ResultDto<String> resultDto = null;
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		try{
			
			ResultDto<String> validateResult=validataData(cBusAccountDto);
	          if(validateResult.isSuccess())
	          {
	        	  resultDto= busAccountService.saveBusAccountRdTx(cBusAccountDto,user);
	        	  adminAuditLogService.log(user.getUserId(), TradeType.BUS_ACCOUNT.getType(),"新增收付款单",cBusAccountDto,0L);
	          }else
	          {
	        	  resultDto=validateResult;
	          }
			
		}catch (Exception e)
		{
			logger.error("提交收付款数据出现异常", e);
			 resultDto=new ResultDto<String>(false,"数据提交失败");
		}

		return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(resultDto));
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
	

