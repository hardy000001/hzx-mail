package com.lq.lss.controller.bus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.BusHtService;
import com.lq.lss.core.service.BusHtStopflowService;
import com.lq.lss.dto.BusHtStopflowDto;
import com.lq.lss.enums.TradeType;
import com.lq.lss.model.CBusHt;
import com.lq.lss.model.CBusHtStopflow;
import com.lq.lss.model.SessionUser;

import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;

/**
 * 租赁合同报停
 * @author  作者: hzx
 * @date 创建时间: 2016-10-26 11:02:13
 */
@Controller
@RequestMapping(value ="/user/bus/busHtStopflow.htm")
public class BusHtStopflowController extends ShiroBaseController<CBusHtStopflow, String, BusHtStopflowService>{
	
	@Resource
	private BusHtStopflowService busHtStopflowService;
	@Resource
	private AdminAuditLogService adminAuditLogService;

	@Resource
	private BusHtService busHtService;

	@Value("/bus/bus_ht_stopflow")
	private String indexView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", PermResourceConst.BUS_HTSTOPFLOW_ADD);
        modelMap.put("update", PermResourceConst.BUS_HTSTOPFLOW_UPDATE);
        modelMap.put("del", PermResourceConst.BUS_HTSTOPFLOW_DEL);
        modelMap.put("check", PermResourceConst.BUS_HTSTOPFLOW_CHECK);
		modelMap.put("user", user);
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	@RequestMapping(params = "method=getHtList")
	public ModelAndView getHtList(HttpServletRequest request, HttpServletResponse response) {
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		
		List<CBusHt> tList = busHtService.findCBusHtList(user.getCenterId()+"");

		return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(tList));
	}
	
	
	
	
	@RequestMapping(params = "method=removeBushtstopflow")
	@RequiresPermissions(PermResourceConst.BUS_HTSTOPFLOW_DEL)
	public ModelAndView removeBushtstopflow(HttpServletRequest request, HttpServletResponse response){
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
					result=busHtStopflowService.deleteHtStopflowById(idStr,String.valueOf(user.getCenterId()));
					adminAuditLogService.log(user.getUserId(), TradeType.BUS_STOP_FLOW.getType(),"删除合同报停",idStr,0L);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logger.error("删除采购数据出现异常"+e.getMessage());
				result=new ResultDto<String>(false,"删除数据出现异常");
			}
				
			return AjaxModelAndViewUtils.writeMsgReturnNull(response,
					JSONUtil.toJSonString(result));
			}
	
	
	@RequestMapping(params = "method=updateBushtstopflow")
	@RequiresPermissions(PermResourceConst.BUS_HTSTOPFLOW_UPDATE)
	public ModelAndView updateBushtstopflow(HttpServletRequest request, HttpServletResponse response,BusHtStopflowDto busHtStopflowDto){
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<String> result=null;
		
		if (notUpdate) {
			result = new ResultDto<String>(false, updateAlertMsg);
			return AjaxModelAndViewUtils.writeMsgReturnNull(response,
					JSONUtil.toJSonString(result));
		}
		try 
		{
			  
		
				ResultDto<String> validateResult =validataData(busHtStopflowDto);
			          if(validateResult.isSuccess())
			          {
			        	       result=busHtStopflowService.updateHtStopflowRdTx(busHtStopflowDto);
			        	       adminAuditLogService.log(user.getUserId(), TradeType.BUS_STOP_FLOW.getType(),"修改合同报停",busHtStopflowDto,0L);
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
	
	
	
	
	
	@RequestMapping(params = "method=auditCBusHtStopflow")
	@RequiresPermissions(PermResourceConst.BUS_HTSTOPFLOW_CHECK)
	public ModelAndView auditCBusHtStopflow(HttpServletRequest request, HttpServletResponse response,BusHtStopflowDto busHtStopflowDto){
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<String> result=null;
		
		if (notUpdate) {
			result = new ResultDto<String>(false, updateAlertMsg);
			return AjaxModelAndViewUtils.writeMsgReturnNull(response,
					JSONUtil.toJSonString(result));
		}
		try 
		{
			  
		
			
			        	       result=busHtStopflowService.auditHtStopflowRdTx(busHtStopflowDto,user);
			        	       adminAuditLogService.log(user.getUserId(), TradeType.BUS_STOP_FLOW.getType(),"审核合同报停",busHtStopflowDto,0L);
			    //操作日志
				
		} catch (Exception e) {
			    logger.error("修改合同数据出现异常", e);
			    result=new ResultDto<String>(false,"数据提交失败");
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(result));
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
			System.out.println("enddate"+enddate.getTime());
			System.out.println("cBusHt.getEndDate()"+cBusHt.getEndDate().getTime());
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
