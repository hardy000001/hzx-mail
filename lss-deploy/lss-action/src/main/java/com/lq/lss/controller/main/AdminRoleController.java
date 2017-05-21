/**
 * 
 */
package com.lq.lss.controller.main;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.exception.BusinessException;
import com.lq.easyui.view.AjaxModelAndViewUtils;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.core.service.AdminRoleService;
import com.lq.lss.model.AdminRole;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.util.JSONUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author lanbo
 */
@Controller
@RequestMapping(value = "/user/adminRole.htm")
public class AdminRoleController extends ShiroBaseController<AdminRole, Integer, AdminRoleService> {

	@Resource
	private AdminRoleService adminRoleService;

	@Value("/system/admin_role")
	private String roleView;

	@RequestMapping()
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 所有能显示有效菜单
        modelMap.put("add", PermResourceConst.SYS_ROLE_ADD);
        modelMap.put("update", PermResourceConst.SYS_ROLE_UPDATE);
        modelMap.put("del", PermResourceConst.SYS_ROLE_DEL);
        modelMap.put("resource", PermResourceConst.SYS_ROLE_RESOURCE);

		return new ModelAndView(roleView, modelMap);
	}

	@RequestMapping(params = "method=create")
	@RequiresPermissions(PermResourceConst.SYS_ROLE_ADD)
	public ModelAndView create(AdminRole adminRole, HttpServletResponse response) {

		ResultDto resultDto = new ResultDto();

		try {
			adminRole.setCreateTime(new Date());
			adminRole.setUserCnt(0);
			adminRoleService.save(adminRole);

			resultDto.setSuccess(true);

		} catch (BusinessException e) {
			resultDto.setErrorMsg(e.getMessage());
			resultDto.setSuccess(false);
			e.printStackTrace();
		}

		return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(resultDto));
	}
	@RequestMapping(params = "method=modify")
	public ModelAndView modify(AdminRole adminRole, HttpServletResponse response) {

		ResultDto resultDto = new ResultDto();

		try {
			adminRoleService.update(adminRole);

			resultDto.setSuccess(true);

		} catch (BusinessException e) {
			resultDto.setErrorMsg(e.getMessage());
			resultDto.setSuccess(false);
			e.printStackTrace();
		}

		return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(resultDto));
	}

	@RequestMapping(params = "method=delete")
	@RequiresPermissions(PermResourceConst.SYS_ROLE_DEL)
	public ModelAndView delete(Integer roleId, HttpServletResponse response) {

		ResultDto resultDto = new ResultDto();

		try {

			adminRoleService.remove(roleId);

			resultDto.setSuccess(true);

		} catch (BusinessException e) {
			resultDto.setErrorMsg(e.getMessage());
			resultDto.setSuccess(false);
			e.printStackTrace();
		}

		return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(resultDto));
	}

	/**
	 * 加载角色--关联用户。用于给用户分配角色
	 * @param userId
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=getUserRoleList")

	public ModelAndView getUserRoleList(Integer userId, HttpServletResponse response) {

		List<Map<String,Object>> roleList = new ArrayList<Map<String,Object>>();
		try {
			roleList = adminRoleService.queryUserRoles(userId);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(roleList));
	}

	/**
	 * 加载所有角色
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=getRoleList")
	public ModelAndView getRoleList(HttpServletResponse response) {

		List<AdminRole> roleList = new ArrayList<AdminRole>();
		try {
			roleList = adminRoleService.loadAll();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(roleList));
	}


}
