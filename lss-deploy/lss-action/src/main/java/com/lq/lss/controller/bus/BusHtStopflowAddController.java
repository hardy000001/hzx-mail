package com.lq.lss.controller.bus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.easyui.controller.easyui.EasyUIController;
import com.lq.easyui.dto.ResultDto;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.BusHtService;
import com.lq.lss.core.service.BusHtStopflowService;
import com.lq.lss.model.CBusHt;
import com.lq.lss.model.CBusHtStopflow;
import com.lq.lss.model.SessionUser;

import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;
import com.lq.lss.dto.BusHtStopflowDto;
import com.lq.lss.dto.BusPurDto;
import com.lq.lss.enums.TradeType;

/**
 * 租赁合同报停
 * @author  作者: hzx
 * @date 创建时间: 2016-10-26 11:02:13
 */
@Controller
@RequestMapping(value ="/user/bus/busHtStopflowadd.htm")
public class BusHtStopflowAddController extends ShiroBaseController<CBusHtStopflow, String, BusHtStopflowService>{
	
	@Resource
	private BusHtStopflowService busHtStopflowService;
	
	@Resource
	private AdminAuditLogService adminAuditLogService;
	@Resource
	private  BusHtService busHtService;

	@Value("/bus/bus_ht_stopflow")
	private String indexView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", PermResourceConst.CENTER_SEND_ADD);
        modelMap.put("update", PermResourceConst.CENTER_SEND_UPDATE);
        modelMap.put("del", PermResourceConst.CENTER_SEND_DEL);
        modelMap.put("check", PermResourceConst.CENTER_SEND_CHECK);
		modelMap.put("user", user);
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	@RequestMapping(params = "method=savebushtstopflow")
	@RequiresPermissions(PermResourceConst.BUS_HTSTOPFLOW_ADD)
	public ModelAndView savebushtstopflow(HttpServletRequest request, HttpServletResponse response,BusHtStopflowDto busHtStopflowDto) {
		ResultDto<String> resultDto = null;
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		try{
			
			ResultDto<String> validateResult=validataData(busHtStopflowDto);
	          if(validateResult.isSuccess())
	          {
	        	  resultDto= busHtStopflowService.saveHtStopflowRdTx(busHtStopflowDto,user);
	        	  adminAuditLogService.log(user.getUserId(), TradeType.BUS_STOP_FLOW.getType(),"新增合同报停",busHtStopflowDto,0L);
	          }else
	          {
	        	  resultDto=validateResult;
	          }
			
		}catch (Exception e)
		{
			logger.error("提交暂存请数据出现异常", e);
			 resultDto=new ResultDto<String>(false,"数据提交失败");
		}

		return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(resultDto));
	}
	
	protected  ResultDto<String> validataData( BusHtStopflowDto busHtStopflowDto){
		String htcode=busHtStopflowDto.getHtcode();
		CBusHt cBusHt= busHtService.findCBusHtbyHtcode(htcode);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(busHtStopflowDto.getHtcode()==""){
			return new ResultDto<String>(false,"合同号不能为空");
		}
		if(busHtStopflowDto.getBeginDate()==""){
			return new ResultDto<String>(false,"开始时间不能为空");
		}
		if(busHtStopflowDto.getEndDate()==""){
			return new ResultDto<String>(false,"结束时间不能为空");
		}
		if(busHtStopflowDto.getTotalDays()==""){
			return new ResultDto<String>(false,"总天数不能为空");
		}
		
		Date begindate = null;
		Date enddate= null;;
		try {
			begindate = sdf.parse(busHtStopflowDto.getBeginDate());
			enddate = sdf.parse(busHtStopflowDto.getEndDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 if(cBusHt!=null){
			 if(begindate.getTime()<cBusHt.getBeginDate().getTime()){
				 return new ResultDto<String>(false,"开始时间不能小于合同开始时间");
			 }
			 if(begindate.getTime()>cBusHt.getEndDate().getTime()){
				 return new ResultDto<String>(false,"开始时间不能大于合同结束时间");
			 }
			 if(enddate.getTime()<cBusHt.getBeginDate().getTime()){
				 return new ResultDto<String>(false,"结束时间不能小于合同开始时间");
			 }
			 if(enddate.getTime()>cBusHt.getEndDate().getTime()){
				 return new ResultDto<String>(false,"结束时间不能大于合同结束时间");
			 }
			 if(enddate.getTime()<begindate.getTime()){
				 return new ResultDto<String>(false,"结束时间不能小于开始日期");
			 }
			
			 return new ResultDto<String>(true,"数据通过验证");
		 }else{
			 return new ResultDto<String>(false,"数据不存在");
		 }
		
	}
	
}
