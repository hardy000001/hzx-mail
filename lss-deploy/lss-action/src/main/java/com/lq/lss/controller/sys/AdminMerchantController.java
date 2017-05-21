
package com.lq.lss.controller.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.model.SessionUser;
import com.lq.lss.model.UAdminUser;
import com.lq.lss.controller.shiro.ShiroBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.exception.BusinessException;
import com.lq.easyui.view.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.UAdminUserService;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JsonUtil2;
/**
 * 
 * @author  作者: hzx
 * @date 创建时间: 2017-5-2下午4:09:59
 */
@Controller
@RequestMapping(value = "/user/sys/adminMerchant.htm")
public class AdminMerchantController extends ShiroBaseController<UAdminUser, String, UAdminUserService> {

    @Resource
    private UAdminUserService uAdminUserService;

    @Value("/base/merchant_user")
    private String indexView;

    @RequestMapping
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("add", PermResourceConst.SYS_USER_MGR_ADD);
        modelMap.put("update", PermResourceConst.SYS_USER_MGR_UPDATE);
        modelMap.put("del", PermResourceConst.SYS_USER_MGR_DEL);
        modelMap.put("lock", PermResourceConst.SYS_USER_MGR_LOCK);
        modelMap.put("unlock", PermResourceConst.SYS_USER_MGR_UNLOCK);
        
        SessionUser  user = LoginSessionUtils.getUserInSession(request);
        modelMap.put("deptId", user.getCenterId());

        if (useI18n) {
            return new I18nModelAndView(request, indexView, modelMap);
        }
        return new ModelAndView(indexView, modelMap);
    }

    // 用户新增
    @RequestMapping(params = "method=saveOrUpdate")
    @RequiresPermissions(PermResourceConst.SYS_USER_MGR_ADD)
    public ModelAndView saveOrUpdate(HttpServletRequest request, HttpServletResponse response, UAdminUser adminUser) {
    	
    	Integer actionType = ServletRequestUtils.getIntParameter(request, "actionType", 0);
        String checkRoleIds = ServletRequestUtils.getStringParameter(request, "checkRoleIds", null);
        String[] roleIds = checkRoleIds.split(",");
        List<Integer> checkedIds = new ArrayList<Integer>();
        if (roleIds != null && roleIds.length > 0) {
            for (String chId : roleIds) {
                if (chId != null && chId.length() != 0) {
                    checkedIds.add(Integer.valueOf(chId));
                }
            }
        }
        ResultDto<String> dto = null;
        String centerId="";
        if(!StringUtils.hasLength(adminUser.getDeptId()))
        {

            SessionUser  user = LoginSessionUtils.getUserInSession(request);
            centerId=user.getCenterId()+"";
            if(!StringUtils.hasLength(centerId) || centerId.equals("-1"))
            {
            	dto = new ResultDto<String>(false, "中心id不存在");
            	return AjaxModelAndViewUtils.writeMsgReturnNull(response, JsonUtil2.toJson(dto));
            }
        }
        try {
        	adminUser.setDeptId(centerId);
            dto = uAdminUserService.saveUserAndRolesRdTx(adminUser, checkedIds,actionType);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e.getErrorMsg());
            dto = new ResultDto<String>(false, "保存数据出现异常");
        }
        return AjaxModelAndViewUtils.writeMsgReturnNull(response, JsonUtil2.toJson(dto));
    }

    @RequiresPermissions(PermResourceConst.SYS_USER_MGR_DEL)
    public ModelAndView remove(HttpServletRequest request, HttpServletResponse response) {
        String id = ServletRequestUtils.getStringParameter(request, "id", null);
        String mchCode = ServletRequestUtils.getStringParameter(request, "mchCode", null);
        
        ResultDto<String> resultDto = null;
        if (StringUtils.hasLength(id)) {
            try {
            	uAdminUserService.removeRdTx(id,mchCode);
                resultDto = new ResultDto<String>(true);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                resultDto = new ResultDto<String>(false, "删除数据出现异常");

            }
        } else {
            resultDto = new ResultDto<String>(false, "id或ids不能为空");
            return AjaxModelAndViewUtils.writeMsgReturnNull(response, JsonUtil2.toJson(resultDto));
        }

        return AjaxModelAndViewUtils.writeMsgReturnNull(response, JsonUtil2.toJson(resultDto));
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(params = "method=updatePwd")
    public ModelAndView updatePwd(HttpServletRequest request, HttpServletResponse response) {
        String oldPwd = ServletRequestUtils.getStringParameter(request, "oldPwd", null);
        String newPwd = ServletRequestUtils.getStringParameter(request, "newPwd", "");
        String newPwd2 = ServletRequestUtils.getStringParameter(request, "newPwd2", null);
        Long loginId = LoginSessionUtils.getUserInSession(request).getUserId();
        ResultDto resultDto = null;
        newPwd = newPwd.trim();
        if (newPwd.length() == 0) {
            resultDto = new ResultDto("新密码不能为空");
            return AjaxModelAndViewUtils.writeMsgReturnNull(response, JsonUtil2.toJson(resultDto));
        }
        if (!newPwd2.equals(newPwd)) {
            resultDto = new ResultDto("两次密码输入不一致");
            return AjaxModelAndViewUtils.writeMsgReturnNull(response, JsonUtil2.toJson(resultDto));
        }
        if (newPwd.equals(oldPwd)) {
            resultDto = new ResultDto("原密码与新密码相同！");
            return AjaxModelAndViewUtils.writeMsgReturnNull(response, JsonUtil2.toJson(resultDto));
        } else {
            resultDto = uAdminUserService.updatePwd(loginId+"", oldPwd, newPwd);
            return AjaxModelAndViewUtils.writeMsgReturnNull(response, JsonUtil2.toJson(resultDto));
        }
    }
    @RequiresPermissions(PermResourceConst.SYS_USER_MGR_UNLOCK)
    @RequestMapping(params = "method=enableUser")
    public ModelAndView enableUser(HttpServletRequest request, HttpServletResponse response) {
        String id = ServletRequestUtils.getStringParameter(request, "id", null);
        ResultDto<String> resultDto = null;
        if (StringUtils.hasLength(id)) {
            try {
            	UAdminUser adminUser=new UAdminUser();
            	adminUser.setUserId(Integer.valueOf(id));
            	adminUser.setStatus(1);
            	uAdminUserService.updateUser(adminUser);
                resultDto = new ResultDto<String>(true);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                resultDto = new ResultDto<String>(false, "解锁修改数据出现异常");

            }
        } else {
            resultDto = new ResultDto<String>(false, "id或ids不能为空");
            return AjaxModelAndViewUtils.writeMsgReturnNull(response, JsonUtil2.toJson(resultDto));
        }

        return AjaxModelAndViewUtils.writeMsgReturnNull(response, JsonUtil2.toJson(resultDto));
    }
    @RequiresPermissions(PermResourceConst.SYS_USER_MGR_LOCK)
    @RequestMapping(params = "method=lockUser")
    public ModelAndView lockUser(HttpServletRequest request, HttpServletResponse response) {
        String id = ServletRequestUtils.getStringParameter(request, "id", null);
        ResultDto<String> resultDto = null;
        if (StringUtils.hasLength(id)) {
            try {
            	UAdminUser adminUser=new UAdminUser();
            	adminUser.setUserId(Integer.valueOf(id));
            	adminUser.setStatus(-1);
            	uAdminUserService.updateUser(adminUser);
                resultDto = new ResultDto<String>(true);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                resultDto = new ResultDto<String>(false, "锁定修改数据出现异常");

            }
        } else {
            resultDto = new ResultDto<String>(false, "id或ids不能为空");
            return AjaxModelAndViewUtils.writeMsgReturnNull(response, JsonUtil2.toJson(resultDto));
        }

        return AjaxModelAndViewUtils.writeMsgReturnNull(response, JsonUtil2.toJson(resultDto));
    }

}
