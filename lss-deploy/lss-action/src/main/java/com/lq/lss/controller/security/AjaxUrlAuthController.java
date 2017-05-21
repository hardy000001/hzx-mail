package com.lq.lss.controller.security;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.lq.easyui.view.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.model.SessionUser;
import com.lq.util.JSONUtil;
import com.lq.util.MD5Util;
/**
 * @author lanbo
 */
@Controller
@RequestMapping(value = "/ajaxAuth.htm")
public class AjaxUrlAuthController extends MultiActionController {

	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String authType = ServletRequestUtils.getStringParameter(request, "authType", "");

		SessionUser sessionUser = (LoginSessionUtils.getUserInSession(request));
		String json = "";
		if (sessionUser == null) {
			json = "USER_DIDNT_LOGINED";
			json = MD5Util.get().md5Hex(json);
			if (authType.equalsIgnoreCase("JSON")) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("errorMsg", json);
				json = JSONUtil.toJSonString(map);
			}
		} else {
			json = "\"OK\"";
		}
		logger.info(sessionUser);
		return AjaxModelAndViewUtils.writeMsgReturnNull(response, json);
	}

}