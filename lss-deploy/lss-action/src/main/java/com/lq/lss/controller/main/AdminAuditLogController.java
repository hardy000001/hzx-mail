package com.lq.lss.controller.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.shiro.ShiroBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.lq.lss.model.AdminAuditLog;
import com.lq.lss.core.service.AdminAuditLogService;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-12-28 15:32:42
 */
@Controller
@RequestMapping(value ="/user/adminlog.htm")
public class AdminAuditLogController extends ShiroBaseController<AdminAuditLog, Long, AdminAuditLogService>{
	
	@Resource
	private AdminAuditLogService adminAuditLogService;
	
	
	@Value("/base/admin_log")
	private String indexView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", PermResourceConst.CENTER_SEND_ADD);
        modelMap.put("update", PermResourceConst.CENTER_SEND_UPDATE);
        modelMap.put("del", PermResourceConst.CENTER_SEND_DEL);
        modelMap.put("check", PermResourceConst.CENTER_SEND_CHECK);
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	
	
	
	
}
