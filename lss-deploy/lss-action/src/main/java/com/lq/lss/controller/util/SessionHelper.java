package com.lq.lss.controller.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lq.session.SessionClient;

/**
 * 
 * @author lanbo
 * 
 */
public class SessionHelper {
	
	private static SessionClient sessionClient = new SessionClient(1440);

	public static final String USER_TICKET = "user_ticket_mem_";

	public static final String LAST_REQUEST = "LAST_REQUEST";

	public static final String SESSION_ID_PARAM = "_SESSION_ID";

	private static int DEF_SESSION_SEC = 24 * 3600; // cookies是1天

	public static final String SESSION_TOKEN_SECRETKEY = "ABED88DE112266EFABED88DE112266EF";

	public static String encode(String str) {
		try {
			return Des3.encode(str, SESSION_TOKEN_SECRETKEY);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String decode(String encStr) {
		try {
			return Des3.decode(encStr, SESSION_TOKEN_SECRETKEY);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getUserTicket(HttpServletRequest request) {
		String sessionId = CookieUtil.getCookie(request, USER_TICKET);
		return sessionId;
	}

	public static Map<String, Object> getUserSessionMap(HttpServletRequest request) {
		String ticketId = getUserTicket(request);
		if (ticketId == null) {
			return null;
		}
		return (Map<String, Object>) sessionClient.getSessionContext(ticketId);
	}

	// 初始化session
	public static void initUserSessionMap(HttpServletRequest request, HttpServletResponse response, String userTicket) {

		CookieUtil.setCookie(response, USER_TICKET, userTicket, DEF_SESSION_SEC);
		Map<String, Object> map = null;
		try {
			map = (Map<String, Object>) sessionClient.getSessionContext(userTicket);
			if (map != null) {
				sessionClient.putSessionContext(userTicket, map);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> newMap = new HashMap<String, Object>();
		sessionClient.putSessionContext(userTicket, newMap);
	}

	public static void clearUserSessionMap(HttpServletRequest request) {
		String userTicket = getUserTicket(request);
		Map<String, Object> map = getUserSessionMap(request);
		if (map != null) {
			map.clear();
			try {
				sessionClient.delSessionContext(userTicket);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void setValue(String ticketId, String key, Object obj) {

		Map<String, Object> map = (Map<String, Object>) sessionClient.getSessionContext(ticketId);
		if (map != null) {
			map.put(key, obj);
			sessionClient.putSessionContext(ticketId, map);
		}

	}

	public static void setValue(HttpServletRequest request, String key, Object obj) {

		Map<String, Object> map = getUserSessionMap(request);
		if (map == null) {
			return;
		}
		String ticketId = getUserTicket(request);
		map.put(key, obj);
		sessionClient.putSessionContext(ticketId, map);
	}

	public static Object getValue(HttpServletRequest request, String key) {
		Map<String, Object> map = getUserSessionMap(request);
		if (map == null) {
			return null;
		}
		try {
			Object obj = map.get(key);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static void removeKey(HttpServletRequest request, String key) {

		Map<String, Object> map = getUserSessionMap(request);
		if (map == null) {
			return;
		}
		String ticketId = getUserTicket(request);
		;
		map.remove(key);
		sessionClient.putSessionContext(ticketId, map);

	}

}
