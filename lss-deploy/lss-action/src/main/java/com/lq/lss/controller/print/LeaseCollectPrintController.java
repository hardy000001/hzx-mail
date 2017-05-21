package com.lq.lss.controller.print;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.annotation.Resource;
import com.lq.lss.controller.shiro.ShiroBaseController;
import org.springframework.beans.factory.annotation.Value;
import com.lq.springmvc.i18n.I18nModelAndView;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.lq.lss.model.CBusPur;
import com.lq.lss.model.SessionUser;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.BusPurService;

/**
 * 租赁汇总打印
 * @author  作者: hzx
 * @date 创建时间: 2016-12-6上午10:20:21
 */
@Controller
@RequestMapping(value ="/user/print/leaseCollectPrint.htm")
public class LeaseCollectPrintController extends ShiroBaseController<CBusPur, String, BusPurService> {
	
	@Resource
	private BusPurService busPurService;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	
	@Value("/print/index")
	private String indexView;
	@Value("/print/lease_collect_print")
	private String printView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("user", user);
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	
	
	@RequestMapping(params = "method=printInfo")
	public ModelAndView printInfo(HttpServletRequest request,
			HttpServletResponse response) {
		
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		return new ModelAndView(printView, modelMap);
	}
	
	
	
	
	
}
