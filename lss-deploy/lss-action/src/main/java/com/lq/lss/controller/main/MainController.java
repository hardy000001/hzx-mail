package com.lq.lss.controller.main;

import com.lq.lss.constant.PermRoleConst;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.controller.util.SessionHelper;
import com.lq.lss.core.service.AdminResourceService;
import com.lq.lss.core.service.TNoticeService;
import com.lq.lss.dto.TNoticeDto;
import com.lq.lss.model.SessionUser;
import com.lq.lss.model.TNotice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lanbo
 */
@Controller
@RequestMapping(value = "/user/main.htm")
public class MainController {

    protected Log logger = LogFactory.getLog(this.getClass());

    @Value("/menu/main_top")
    private String mainTop;

    @Value("/menu/main")
    private String topMenu;

    @Value("/menu/menu")
    private String menuFrame;

    @Value("/menu/buttonFrame")
    private String buttonFrame;

    @Value("/menu/bodyFrame")
    private String bodyFrame;

    @Value("/menu/navigation")
    private String navigation;

    @Resource
    private AdminResourceService adminResourceService;
    @Resource
	private TNoticeService tNoticeService;

    @RequestMapping()
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("dd");
        return null;
    }

    @RequestMapping(params = "method=topFrame")
    public ModelAndView topFrame(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException, IOException {

        SessionUser sessionUser = LoginSessionUtils.getUserInSession(request);
        String userTicket = SessionHelper.getUserTicket(request);

        return new ModelAndView(mainTop).addObject("sessionUser", sessionUser).addObject("userTicket", userTicket);
    }

    @RequestMapping(params = "method=navigation")
    public ModelAndView navigation(HttpServletRequest req, HttpServletResponse res) throws ServletRequestBindingException, IOException {
    	
    	Map<String, Object> modelMap = new HashMap<String, Object>();
    	SessionUser sessionUser = LoginSessionUtils.getUserInSession(req);
    	
    	TNoticeDto tNoticeDto=new TNoticeDto();
    	tNoticeDto.setDeptid(sessionUser.getCenterId());
    	tNoticeDto.setType("1");
    	
    	List<TNotice> noticeList=tNoticeService.queryNoticeList(tNoticeDto);
    	
    	modelMap.put("noticeList", noticeList);
    	
		modelMap.put("userId", sessionUser.getUserId());
    	
        return new ModelAndView(navigation,modelMap);
    }

    @RequestMapping(params = "method=topMenu")
    public ModelAndView topMenu(HttpServletRequest req, HttpServletResponse res) throws ServletRequestBindingException, IOException {
        return new ModelAndView(topMenu);
    }

    @RequestMapping(params = "method=buttonFrame")
    public ModelAndView buttonFrame(HttpServletRequest req, HttpServletResponse res) throws ServletRequestBindingException, IOException {
        return new ModelAndView(buttonFrame);
    }

    @RequestMapping(params = "method=bodyFrame")
    public ModelAndView bodyFrame(HttpServletRequest req, HttpServletResponse res) throws ServletRequestBindingException, IOException {

        return new ModelAndView(bodyFrame);
    }

    @RequestMapping(params = "method=menuFrame")
    public ModelAndView menuFrame(HttpServletRequest request, HttpServletResponse res) throws ServletRequestBindingException, IOException {

        // 是否是管理员角色
        Boolean isAdmin = SecurityUtils.getSubject().hasRole(PermRoleConst.ADMIN);

        SessionUser sessionUser = LoginSessionUtils.getUserInSession(request);

        Map<String, Object> map = new HashMap<String, Object>();

        String userTicket = SessionHelper.getUserTicket(request);

        map.put("userTicket", userTicket);
        map.put("isAdmin",isAdmin?1:0);

        return new ModelAndView(menuFrame, map);
    }
}
