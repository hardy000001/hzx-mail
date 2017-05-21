package com.lq.lss.controller.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author lanbo modify 2013-9-13 修改cookies有时取不到的bug 修改所有方法均为静态方法
 * @param
 */
public class CookieUtil {

	private static final int maxAge = 60 * 60 * 24 * 365;

	/*
	 * public static void setCookie(HttpServletResponse response,String
	 * cookieKey,String cookieValue){ Cookie newcookie; newcookie = new
	 * Cookie(cookieKey,cookieValue); newcookie.setMaxAge(maxAge);
	 * newcookie.setSecure(false); newcookie.setDomain("localhost");
	 * newcookie.setPath("/"); response.addCookie(newcookie); }
	 */
	private static final String path = "/";

	public static void setCookie(HttpServletResponse response, String cookieKey, String cookieValue) {
		setCookie(response, cookieKey, cookieValue, maxAge);
	}

	public static void setCookie(HttpServletResponse response, String cookieKey, String cookieValue, int timeSec) {

		Cookie newcookie = new Cookie(cookieKey, cookieValue);
		newcookie.setPath(path);
		newcookie.setSecure(false);
		newcookie.setMaxAge(timeSec);
		response.addCookie(newcookie);
	}

	public static String getCookie(HttpServletRequest request, String cookieKey) {
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				if (c.getName().equalsIgnoreCase(cookieKey)) {
					return c.getValue();
				}

			}
		}

		return null;
	}

	public static void clearCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		try {
			if (cookies != null && cookies.length > 0) {
				for (int i = 0; i < cookies.length; i++) {
					setCookie(response, cookies[i].getName(), null, 0);
				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void removeCookieValue(HttpServletRequest request, HttpServletResponse response, String key) {
		Cookie[] cookies = request.getCookies();
		try {
			if (cookies != null && cookies.length > 0) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie c = cookies[i];
					if (c.getName().equalsIgnoreCase(key)) {
						setCookie(response, key, null, 0);
						break;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
