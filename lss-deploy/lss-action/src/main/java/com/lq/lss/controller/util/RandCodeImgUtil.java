package com.lq.lss.controller.util;

import javax.servlet.http.HttpServletRequest;

public class RandCodeImgUtil {

	/**
	 * @param request
	 * @param randcode
	 * @return
	 */
	public static boolean validateRandCode(HttpServletRequest request, String randcode) {

		// String sessionCode = (String)
		// request.getSession().getAttribute("rand");

		String sessionCode = (String) SessionHelper.getValue(request, "rand");

		// 取得以后清除
		// request.getSession().setAttribute("rand", "");
		if (randcode == null || sessionCode == null) {
			return false;
		}
		// 验证结束以后清除

		return randcode.equalsIgnoreCase(sessionCode);

	}
}
