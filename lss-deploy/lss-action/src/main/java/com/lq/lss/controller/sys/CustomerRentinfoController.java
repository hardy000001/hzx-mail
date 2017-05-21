package com.lq.lss.controller.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;

import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.lss.model.CustomerRentinfo;
import com.lq.lss.model.MchRule;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.SystemConst;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.CustomerInfoService;
import com.lq.lss.core.service.CustomerRentinfoService;
import com.lq.lss.core.service.MchRuleService;
import com.lq.lss.dto.CustomerRentinfoDto;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-12-15 14:03:12
 */
@Controller
@RequestMapping(value ="/user/sys/customerRentinfo.htm")
public class CustomerRentinfoController extends ShiroBaseController<CustomerRentinfo, String, CustomerRentinfoService>{
	
	@Resource
	private CustomerInfoService customerInfoService;
	@Resource
	private MchRuleService mchRuleService;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	@Resource
	private CustomerRentinfoService customerRentinfoService;
	
	
	@Value("/base/customer_rentinfo")
	private String indexView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		SessionUser user=LoginSessionUtils.getUserInSession(request);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", PermResourceConst.SYS_CUSTOMER_RENTINFO_ADD);
        modelMap.put("update", PermResourceConst.SYS_CUSTOMER_RENTINFO_UPDATE);
        modelMap.put("del", PermResourceConst.SYS_CUSTOMER_RENTINFO_DEL);
        modelMap.put("check", PermResourceConst.SYS_CUSTOMER_RENTINFO_CHEKE);
        
		List<MchRule> mchRuleList=mchRuleService.loadAll();
		modelMap.put("mchRuleList", mchRuleList);
		
		if(!SystemConst.ADMIN_CENTER_ID.equals(String.valueOf(user.getCenterId())))
		{
			modelMap.put("deptId", user.getCenterId());
		}
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	
	
	
	/**
	 * 获取租价信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=getRentinfoList")
	public ModelAndView getRentinfoList(HttpServletRequest request, HttpServletResponse response) {
		
		String cuscode = ServletRequestUtils.getStringParameter(request, "id", "");

		List<CustomerRentinfoDto> cList =customerRentinfoService.queryRentinfoById(cuscode);

		return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(cList));
	}
	
	
}
