package com.lq.lss.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.controller.util.SessionHelper;
import com.lq.lss.model.SessionUser;



/**
 * 过滤用户登录权限
 * @author lanbo
 *
 */
public class AppLoginFilter implements Filter {

	private static final Log logger = LogFactory.getLog(AppLoginFilter.class);
	private static final String loginUrl = "/lss/login.htm";
	private static final String ajaxAuthUrl = "/ajaxAuth.htm";

	public void destroy() {

	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		String requestPath = request.getRequestURI();
		// String callBackUrl=AppConf.defaultCallBack;
		logger.info("ip:"+request.getRemoteAddr()+" 当前访问 地址:" + requestPath);

		// 判断用户是否登录
		SessionUser sessionUser = (LoginSessionUtils.getUserInSession(request));
		if (sessionUser == null) {
			logger.info("未得到用户session登录信息需要跳转到登录！" );
			//记录用户最后一次登录进的url,暂时只记录get的
			String loginedFirstUrl = request.getParameter("_redirectUrl");
			if( !StringUtils.hasText(loginedFirstUrl)){
				if(request.getMethod().equalsIgnoreCase("get")){
					String queryString = request.getQueryString();
					loginedFirstUrl = requestPath+(StringUtils.hasText(queryString)?("?"+queryString):"");
				}else{//POST
					loginedFirstUrl = request.getParameter("_redirectUrl");
				}
			}
			if(StringUtils.hasText(loginedFirstUrl)){
				String redirectUrlKey = "_redirectUrl";
				SessionHelper.removeKey(request,redirectUrlKey);
				SessionHelper.setValue(request,redirectUrlKey, loginedFirstUrl);
			}
			// 未登录需要跟到登录页面
			String redUrl = loginUrl;
			// 判断如果是ajax请求，则跳到ajax认证页面
			boolean isAjax = request.getParameter("_ajax")!=null?true:false;;
			if (requestPath.endsWith(".ax")||isAjax) {
				redUrl = ajaxAuthUrl;
				RequestDispatcher rd = servletRequest.getRequestDispatcher(redUrl);
				rd.forward(servletRequest, servletResponse);
			}else{
				response.sendRedirect(redUrl);
			}
			return;
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	public void init(FilterConfig arg0) throws ServletException {
	}
}
