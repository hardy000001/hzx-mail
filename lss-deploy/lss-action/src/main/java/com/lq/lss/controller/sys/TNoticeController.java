package com.lq.lss.controller.sys;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;
import com.lq.util.JsonUtil2;
import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.exception.BusinessException;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.lss.model.AdminUser;
import com.lq.lss.model.SessionUser;
import com.lq.lss.model.TNotice;
import com.lq.lss.core.service.AdminUserService;
import com.lq.lss.core.service.TNoticeService;
import com.lq.lss.enums.NoticeStatus;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2017-03-14 15:55:45
 */
@Controller
@RequestMapping(value ="/user/sys/tNotice.htm")
public class TNoticeController extends ShiroBaseController<TNotice, String, TNoticeService>{
	
	@Resource
	private TNoticeService tNoticeService;
	@Resource
	private AdminUserService adminUserService;
	
	@Value("/base/notice")
	private String indexView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
        SessionUser sessionUser = LoginSessionUtils.getUserInSession(request);
    	
		modelMap.put("add", PermResourceConst.SYS_NOTICE_ADD);
        modelMap.put("update", PermResourceConst.SYS_NOTICE_UPDATE);
        modelMap.put("del", PermResourceConst.SYS_NOTICE_DEL);

		modelMap.put("userId", sessionUser.getUserId());
		modelMap.put("deptId", sessionUser.getDeptId());
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	@RequestMapping(params = "method=getUserList")
	public ModelAndView getUserList(HttpServletRequest request, HttpServletResponse response) {
		
		List<AdminUser> userList=adminUserService.loadAll();
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response, JsonUtil2.toJson(userList));
	}
	@RequestMapping(params = "method=create")
	@RequiresPermissions(PermResourceConst.SYS_NOTICE_ADD)
	public ModelAndView create(TNotice tNotice,HttpServletRequest request, HttpServletResponse response) {

		ResultDto<String> resultDto = new ResultDto<String>();
		SessionUser sessionUser = LoginSessionUtils.getUserInSession(request);

		try {
			tNotice.setCreatetime(new Date());
			Integer operId=Integer.parseInt(sessionUser.getUserId()+"");
			tNotice.setDeptid(sessionUser.getCenterId());
			tNotice.setCreateoper(operId);
			tNotice.setFromoper(operId);
			tNotice.setStatus(NoticeStatus.UNREAD.value()+"");
			tNotice.setAdminstatus(NoticeStatus.UNREAD.value()+"");
			String toId=String.valueOf(tNotice.getTooper());
			String toMerId=tNotice.getTomerchant();
			if(!toId.equals("null") || StringUtils.hasLength(toMerId)){
				tNotice.setType(NoticeStatus.INIT.value()+"");
			}else{
				tNotice.setType("1");
			}
			String level=tNotice.getLevel();
			if(!StringUtils.hasLength(level)){
				tNotice.setLevel(NoticeStatus.INIT.value()+"");
			}
			tNoticeService.save(tNotice);
			resultDto.setSuccess(true);

		} catch (Exception e) {
			resultDto.setErrorMsg(e.getMessage());
			resultDto.setSuccess(false);
			e.printStackTrace();
		}

		return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(resultDto));
	}
	@RequestMapping(params = "method=modify")
	@RequiresPermissions(PermResourceConst.SYS_NOTICE_UPDATE)
	public ModelAndView modify(TNotice tNotice,HttpServletRequest request, HttpServletResponse response) {

		ResultDto<String> resultDto = new ResultDto<String>();
		SessionUser sessionUser = LoginSessionUtils.getUserInSession(request);


		try {
			tNotice.setModifytime(new Date());
			Integer operId=Integer.parseInt(sessionUser.getUserId()+"");
			tNotice.setModifyoper(operId);
			
			String toId=String.valueOf(tNotice.getTooper());
			String toMerId=tNotice.getTomerchant();
			if(!toId.equals("null") || StringUtils.hasLength(toMerId)){
				tNotice.setType(NoticeStatus.INIT.value()+"");
			}else{
				tNotice.setType("1");
			}
			tNoticeService.update(tNotice);
			resultDto.setSuccess(true);

		} catch (BusinessException e) {
			resultDto.setErrorMsg(e.getMessage());
			resultDto.setSuccess(false);
			e.printStackTrace();
		}

		return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(resultDto));
	}
	@RequestMapping(params = "method=queryList")
	public ModelAndView queryList(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", notAdd ? 0 : 1);
		modelMap.put("upd", notUpdate ? 0 : 1);
		modelMap.put("del", notDelete ? 0 : 1);
		modelMap.put("exp", notExport ? 0 : 1);
		//List<TNotice> noticeList=tNoticeService.queryNoticeList(null);
		
		return new ModelAndView(indexView, modelMap);
	}
	@RequestMapping(params = "method=setNoticeRead")
	public ModelAndView setNoticeRead(HttpServletRequest request, HttpServletResponse response) {
		
		SessionUser user=LoginSessionUtils.getUserInSession(request);
		
		ResultDto<String> result=new ResultDto<String>();
		
		String nId = ServletRequestUtils.getStringParameter(request, "nId", "");
		TNotice notice=new TNotice();
		notice.setNid(nId);
		notice.setStatus(NoticeStatus.READ.value()+"");
		notice.setModifytime(new Date());
		notice.setModifyoper(Integer.parseInt(user.getUserId()+""));
		try {
			tNoticeService.update(notice);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("修改通知数据异常--改为已读");
			e.printStackTrace();
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(result));
	}
	@RequestMapping(params = "method=deleteNotice")
	public ModelAndView deleteNotice(HttpServletRequest request, HttpServletResponse response) {
		
		SessionUser user=LoginSessionUtils.getUserInSession(request);
		
		ResultDto<String> result=new ResultDto<String>();
		
		String nId = ServletRequestUtils.getStringParameter(request, "nId", "");
		TNotice notice=new TNotice();
		notice.setNid(nId);
		notice.setStatus(NoticeStatus.DELETE.value()+"");
		notice.setModifytime(new Date());
		notice.setModifyoper(Integer.parseInt(user.getUserId()+""));
		try {
			tNoticeService.update(notice);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("修改通知数据异常--改为删除");
			e.printStackTrace();
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(result));
	}
	@RequestMapping(params = "method=adminDelNotice")
	public ModelAndView adminDelNotice(HttpServletRequest request, HttpServletResponse response) {
		
		SessionUser user=LoginSessionUtils.getUserInSession(request);
		
		ResultDto<String> result=new ResultDto<String>();
		
		String nId = ServletRequestUtils.getStringParameter(request, "id", "");
		TNotice notice=new TNotice();
		notice.setNid(nId);
		notice.setAdminstatus("1");
		notice.setModifytime(new Date());
		notice.setModifyoper(Integer.parseInt(user.getUserId()+""));
		try {
			tNoticeService.update(notice);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("管理者删除通知数据异常--改为删除");
			e.printStackTrace();
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(result));
	}
	
	
}
