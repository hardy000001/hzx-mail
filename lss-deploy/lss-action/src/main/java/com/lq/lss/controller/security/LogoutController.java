/**
 * 
 */
package com.lq.lss.controller.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.controller.util.SessionHelper;

/**
 * @author lanbo
 */
@Controller
@RequestMapping(value = "/logout.htm")
public class LogoutController {

	@Value("/system/logout_success")
	private String logoutView;

	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

		LoginSessionUtils.clearSessionUser(request);
		SessionHelper.clearUserSessionMap(request);
		return null;
	}

}
