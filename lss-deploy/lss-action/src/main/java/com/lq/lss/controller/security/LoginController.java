package com.lq.lss.controller.security;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lq.lss.model.AdminUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.view.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.CookieUtil;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.controller.util.SessionHelper;
import com.lq.lss.core.service.AdminDeptService;
import com.lq.lss.core.service.AdminUserService;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.MD5Util;
import com.lq.util.JsonUtil2;

/**
 * @author lanbo
 */
@Controller
@RequestMapping(value ="/login.htm")
public class LoginController {
	
	private static final Log log= LogFactory.getLog(LoginController.class);
	
	
	@Value("/system/login")
	private String  loginView;
	
	@Value("/system/login_success")
	private String  loginSuccessView;
	
	@Value("/redirect_url")
	private String redirectView;
	
	@Resource
	private AdminUserService adminUserService;
	@Resource
	private AdminDeptService deptService;
	
	@RequestMapping()
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView(loginView);
	}
	
	@RequestMapping(params="method=loginSuccess")
	public ModelAndView loginSuccess(HttpServletRequest request, HttpServletResponse response) {
		SessionHelper.clearUserSessionMap(request);
		String loginName = CookieUtil.getCookie(request, "loginName");
		Map<String,Object> map  = new HashMap<String, Object>();
		map.put("loginName", loginName);
		return new ModelAndView(loginSuccessView, map);
	}
	
	@RequestMapping(params="method=loginAuth")
	public ModelAndView loginAuth(@RequestParam("username") String  username,
			@RequestParam("password") String  password,HttpServletRequest request,
			HttpServletResponse response) {
		@SuppressWarnings("rawtypes")
		ResultDto resultDto =new ResultDto();
		try {
			password = MD5Util.md5Hex(password);
			AdminUser adminUser = adminUserService.loginAuth(username, password);
			if (adminUser != null) {
				//1为启用  0：未激活  -1：为禁用
				String status=String.valueOf(adminUser.getStatus());
				if("-1".equals(status)){
					resultDto.setErrorMsg("该账户已被禁用");
					resultDto.setSuccess(false);
					return AjaxModelAndViewUtils.writeMsgReturnNull(response, JsonUtil2.toJson(resultDto));
				}
				if("0".equals(status)){
					resultDto.setErrorMsg("该账户未激活");
					resultDto.setSuccess(false);
					return AjaxModelAndViewUtils.writeMsgReturnNull(response, JsonUtil2.toJson(resultDto));
				}
				resultDto.setSuccess(true);
				SessionUser sessionUser = new SessionUser();
				sessionUser.setLoginName(adminUser.getLoginName());
				sessionUser.setDeptId(adminUser.getDeptId());
				// 根据部门ID查询所属中心
				int centerId=-1;
				centerId = deptService.findRootId(Integer.parseInt(adminUser.getDeptId()));
				sessionUser.setCenterId(centerId);
				
				sessionUser.setOperCode(adminUser.getOperCode());
				sessionUser.setUserId(Long.parseLong(adminUser.getUserId()+""));
				LoginSessionUtils.setLoginGetSessionId(request, response, sessionUser);

				// shiro 权限设置
				Subject user = SecurityUtils.getSubject();
				UsernamePasswordToken token = new UsernamePasswordToken(username, password);
				user.login(token);

			} else {
				resultDto.setErrorMsg("用户名或密码错误");
				resultDto.setSuccess(false);
				return AjaxModelAndViewUtils.writeMsgReturnNull(response, JsonUtil2.toJson(resultDto));
			}
		} catch (Exception e) {
			resultDto.setErrorMsg("连接服务器异常");
			resultDto.setSuccess(false);
			log.error("登陆异常："+e.getMessage());
		}
		return AjaxModelAndViewUtils.writeMsgReturnNull(response, JsonUtil2.toJson(resultDto));
	}
	
	@RequestMapping(params="method=loginOut")
	public ModelAndView loginOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginSessionUtils.clearSessionUser(request);
		SessionHelper.clearUserSessionMap(request);
		return new ModelAndView(loginView);
	}
}
