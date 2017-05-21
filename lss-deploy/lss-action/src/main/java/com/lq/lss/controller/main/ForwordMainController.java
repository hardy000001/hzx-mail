package com.lq.lss.controller.main;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * @author lanbo
 */
@Controller
@RequestMapping(value = "/user/forwordMain.htm")
public class ForwordMainController {

	@Value("/menu/main")
	private String mainView;

	@RequestMapping()
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String  easyuiCSS = "default" ;
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies){
		    if( cookie.getName().equals("easyuiCSS")){
		    	if(cookie.getValue()!=null && cookie.getValue()!="")
		    	easyuiCSS =  cookie.getValue();
		    }
		}
		return new ModelAndView(mainView).addObject("easyuiCSS", easyuiCSS);
	}

}
