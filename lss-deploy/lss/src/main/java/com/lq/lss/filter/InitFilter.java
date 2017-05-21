package com.lq.lss.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lq.lss.controller.util.SessionHelper;
import com.lq.session.TicketUtil;
import com.lq.util.IpUtils;

public class InitFilter implements Filter {
	private Log log = LogFactory.getLog("InitFilter");

	private String encoding = "UTF-8";// 默认是UTF-8

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public void destroy() {

	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		// 字符集编码
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		Map<String,Object> userMap = SessionHelper.getUserSessionMap(request);
		if(userMap==null){
			SessionHelper.initUserSessionMap(request, response, TicketUtil.getTicket());
		}

		if (this.encoding != null && (request.getCharacterEncoding() == null)) {
			request.setCharacterEncoding(this.encoding);
			response.setCharacterEncoding(this.encoding);
		}

		String queryString = request.getQueryString();
		queryString = queryString == null ? "" : ("?" + queryString);
		String requestPath = request.getRequestURI();
		
		
		long timeBegin = System.currentTimeMillis();
		log.info("ip:" + IpUtils.getIp(request) + ",访问url:"
				+ requestPath+queryString + ",提交方式:" + request.getMethod());
		
		
		servletResponse.setCharacterEncoding("UTF-8");
		filterChain.doFilter(servletRequest, servletResponse);
		log.info(request.getRequestURL() + ",use time:"
				+ (System.currentTimeMillis() - timeBegin) + " ms");

		return;

	}

	public void init(FilterConfig arg0) throws ServletException {

	}
}
