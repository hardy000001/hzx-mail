package com.lq.lss.controller.stock;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.controller.util.LoginSessionUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.lq.lss.model.SMchRentmaterial;
import com.lq.lss.model.SessionUser;
import com.lq.lss.core.service.SMchRentmaterialService;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-12-01 14:54:23
 */
@Controller
@RequestMapping(value ="/user/stock/smchrentmaterial.htm")
public class SMchRentmaterialController extends ShiroBaseController<SMchRentmaterial, String, SMchRentmaterialService>{
	
	@Resource
	private SMchRentmaterialService sMchRentmaterialService;
	
	
	@Value("/stock/smchrentmaterial")
	private String indexView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		
		modelMap.put("deptid", user.getDeptId());
	
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	
	
	
	
}
