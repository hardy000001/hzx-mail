/**
 * 
 */
package com.lq.lss.controller.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

/**
 * @author lanbo
 * 
 */
public class AjaxModelAndViewUtils {

	public static ModelAndView writeMsgReturnNull(HttpServletResponse response, String string) {

		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");
			System.out.println("jsonOrTxt-->" + string);
			response.getWriter().print(string);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
