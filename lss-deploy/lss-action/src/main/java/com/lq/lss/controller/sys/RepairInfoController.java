/**
 * 
 */
package com.lq.lss.controller.sys;

import com.lq.easyui.controller.easyui.EasyUIController;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.core.service.RepairInfoService;
import com.lq.lss.model.BRepairInfo;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lanbo
 */
@Controller
@RequestMapping(value = "/user/sys/repairInfo.htm")
public class RepairInfoController extends ShiroBaseController<BRepairInfo, Integer, RepairInfoService> {
	
	@Resource
	private RepairInfoService repairInfoService;

	@Value("/base/repairInfo")
	private String indexView;

	@RequestMapping
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) {

		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", PermResourceConst.SYS_REP_ADD);
		modelMap.put("update", PermResourceConst.SYS_REP_UPDATE);
		modelMap.put("del", PermResourceConst.SYS_REP_DEL);

		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	@RequestMapping(params = "method=getRepairInfo")
	public ModelAndView getRepairInfo(HttpServletRequest request,HttpServletResponse response) {
		
		String typeid = ServletRequestUtils.getStringParameter(request,
				"id", "");
		
		List<BRepairInfo> dataList = repairInfoService.queryRepairInfoByTypeid(typeid);
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(dataList));
	}

}
