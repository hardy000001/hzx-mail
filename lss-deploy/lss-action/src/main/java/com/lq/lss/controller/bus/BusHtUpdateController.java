package com.lq.lss.controller.bus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import com.lq.lss.dto.AuditDto;
import com.lq.lss.dto.BusHtDto;
import com.lq.lss.dto.BusHtRentinfoDto;
import com.lq.lss.dto.BusHtRepairinfoDto;
import com.lq.lss.dto.BusHtSettlementDto;
import com.lq.lss.enums.TradeType;
import com.lq.lss.model.CBusHt;
import com.lq.lss.model.SessionUser;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;

/**
 * 租赁合同
 * @author  作者: hzx
 * @date 创建时间: 2016-10-20 17:22:18
 */
@Controller
@RequestMapping(value ="/user/bus/busHtUpdate.htm")
public class BusHtUpdateController extends ShiroBaseController<CBusHt, String, BusHtService>{

	
	@Resource
	private BusHtService  busHtService;
	
	@Resource
	private AdminAuditLogService adminAuditLogService;
	
	@Value("/bus/bus_ht_update")
	private String indexView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		String htcode=ServletRequestUtils.getStringParameter(request, "id", "");
		String loginName=ServletRequestUtils.getStringParameter(request, "loginName", "");
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		modelMap.put("loginName", loginName);
		modelMap.put("htcode", htcode);
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	
	@RequestMapping(params = "method=getBusHtData")
	public ModelAndView getBusHtData(HttpServletRequest request,HttpServletResponse response) {
		
		String htcode = ServletRequestUtils.getStringParameter(request,
				"id", "");
		String loginName=ServletRequestUtils.getStringParameter(request, "loginName", "");
		
		CBusHt busHt=busHtService.get(htcode);
		busHt.setCreateoper(loginName);

		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(busHt));
	}
	
	
	
	
	@RequestMapping(params = "method=updateHt")
	@RequiresPermissions(PermResourceConst.CENTER_HT_UPDATE)
	public ModelAndView updateHt(HttpServletRequest request,HttpServletResponse response, 
		BusHtDto busHtDto,BusHtSettlementDto busHtSettlementDto) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<String> result=null;
		
		try 
		{
			    busHtDto.setBusHtSettlementDto(busHtSettlementDto);
			    busHtDto.setDeptid(user.getCenterId()+"");
			    busHtDto.setCreateoper(user.getUserId()+"");
				
			    //租费
				String listStr =busHtDto.getDataList();
				JSONArray json = JSONArray.fromObject(listStr);
				@SuppressWarnings("unchecked")
				List<BusHtRentinfoDto> dataList = (List<BusHtRentinfoDto>) JSONArray
						.toCollection(json, BusHtRentinfoDto.class);
				busHtDto.setBusHtRentinfoDtos(dataList);
				//维修
				String listStr2 =busHtDto.getDataList2();
				JSONArray json2 = JSONArray.fromObject(listStr2);
				@SuppressWarnings("unchecked")
				List<BusHtRepairinfoDto> dataList2 = (List<BusHtRepairinfoDto>) JSONArray
						.toCollection(json2, BusHtRepairinfoDto.class);
				busHtDto.setBusHtRepairinfoDtos(dataList2);
		
				ResultDto<String> validateResult = BusHtAddController
						.validataData(busHtDto);
			          if(validateResult.isSuccess())
			          {
			        	       result=busHtService.updateBusHtRdTx(busHtDto);
			          }else
			          {
			        	       result=validateResult;
			          }
			    //TODO 修改操作日志  编号不是Long类型
				adminAuditLogService.log(user.getUserId(), TradeType.BUS_HT.getType(),"修改合同",busHtDto,0L);    
		} catch (Exception e) {
			    logger.error("修改合同数据出现异常", e);
			    result=new ResultDto<String>(false,"数据提交失败");
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(result));
	}
	
	@RequestMapping(params = "method=auditInfo")
	@RequiresPermissions(PermResourceConst.CENTER_HT_CHECK)
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
				    result=busHtService.auditInfoRdTx(auditDto);
				    //TODO 审核操作日志  编号不是Long类型
					adminAuditLogService.log(user.getUserId(), TradeType.BUS_HT.getType(),"审核合同",auditDto,0L);
			} catch (Exception e) {
					e.printStackTrace();
					logger.error("审核合同数据异常");
					result=new ResultDto<String>(false,"数据修改异常");
			}
		
		
		    return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(result));
	}
	
}
