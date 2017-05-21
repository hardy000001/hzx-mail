package com.lq.lss.controller.bus;

import java.util.ArrayList;
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

import com.lq.lss.model.BRepairInfo;
import com.lq.lss.model.CBusHt;
import com.lq.lss.model.CBusHtSettlement;
import com.lq.lss.model.SessionUser;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.BusHtRentinfoService;
import com.lq.lss.core.service.BusHtRepairinfoService;
import com.lq.lss.core.service.BusHtService;
import com.lq.lss.core.service.BusHtSettlementService;
import com.lq.lss.core.service.RepairInfoService;
import com.lq.lss.dto.BusHtRentinfoDto;
import com.lq.lss.dto.BusHtRepairinfoDto;
import com.lq.lss.enums.TradeType;

/**
 * 租赁合同
 * @author  作者: hzx
 * @date 创建时间: 2016-10-20 17:22:18
 */
@Controller
@RequestMapping(value ="/user/bus/busHt.htm")
public class BusHtController extends ShiroBaseController<CBusHt, String, BusHtService> {

	
	@Resource
	private BusHtService  busHtService;
	@Resource
	private BusHtRentinfoService busHtRentinfoService;
	@Resource
	private BusHtRepairinfoService busHtRepairinfoService;
	@Resource
	private BusHtSettlementService busHtSettlementService;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	@Resource
	private RepairInfoService repairInfoService;
	
	@Value("/bus/bus_ht")
	private String indexView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);

		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", PermResourceConst.CENTER_HT_ADD);
        modelMap.put("update", PermResourceConst.CENTER_HT_UPDATE);
        modelMap.put("del", PermResourceConst.CENTER_HT_DEL);
        modelMap.put("check", PermResourceConst.CENTER_HT_CHECK);
		modelMap.put("user", user);
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	
	@RequestMapping(params = "method=remove")
	@RequiresPermissions(PermResourceConst.CENTER_HT_DEL)
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
			result=busHtService.deleteBusHtById(idStr, user.getCenterId()+"");
			//TODO 删除操作日志  编号不是Long类型
			adminAuditLogService.log(user.getUserId(), TradeType.BUS_HT.getType(),"删除合同",idStr,0L);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除合同数据出现异常"+e.getMessage());
			result=new ResultDto<String>(false,"删除数据出现异常");
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(result));
	}
	/**
	 * 维修项目表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=getRepairInfo")
	public ModelAndView getRepairInfo(HttpServletRequest request,HttpServletResponse response) {
		
		String typeid = ServletRequestUtils.getStringParameter(request,
				"id", "");
		
		List<BusHtRepairinfoDto> htRepairinfoDtos=new ArrayList<BusHtRepairinfoDto>();
		
		List<BRepairInfo> dataList = repairInfoService.queryRepairInfoByTypeid(typeid);
		for (BRepairInfo bRepairInfo : dataList) {
			BusHtRepairinfoDto busHtRepairinfoDto=new BusHtRepairinfoDto();
			busHtRepairinfoDto.setId(bRepairInfo.getId()+"");
			busHtRepairinfoDto.setRepairId(bRepairInfo.getId());
			busHtRepairinfoDto.setMaterialcode(bRepairInfo.getTypeid()+"");
			busHtRepairinfoDto.setTypeid(bRepairInfo.getTypeid());
			busHtRepairinfoDto.setPrice(bRepairInfo.getFeePercant()+"");
			busHtRepairinfoDto.setUnit(bRepairInfo.getFeeUnit());
			busHtRepairinfoDto.setName(bRepairInfo.getName());
			busHtRepairinfoDto.settName(bRepairInfo.gettName());
			htRepairinfoDtos.add(busHtRepairinfoDto);
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(htRepairinfoDtos));
	}
	/**
	 * 租费列表信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=getHtRentinfoList")
	public ModelAndView getHtRentinfoList(HttpServletRequest request,
			HttpServletResponse response) {
		String htcode = ServletRequestUtils.getStringParameter(request,
				"id", "");
		
		List<BusHtRentinfoDto> rentinfoList=busHtRentinfoService.queryHtRentinfoListById(htcode);
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(rentinfoList));
	}
	/**
	 * 维修费列表信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=getHtRepairinfoList")
	public ModelAndView getHtRepairinfoList(HttpServletRequest request,HttpServletResponse response) {
		
		String htcode = ServletRequestUtils.getStringParameter(request,
				"id", "");
		
		List<BusHtRepairinfoDto> busHtRepairinfos=busHtRepairinfoService.queryHtRepairinfoListById(htcode);

		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(busHtRepairinfos));
	}
	
	/**
	 * 获取结算信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=getHtSettlementData")
	public ModelAndView getHtSettlementData(HttpServletRequest request,HttpServletResponse response) {
		
		String htcode = ServletRequestUtils.getStringParameter(request,
				"id", "");
		
		CBusHtSettlement busHtSettlement=busHtSettlementService.get(htcode);

		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(busHtSettlement));
	}
	
}
