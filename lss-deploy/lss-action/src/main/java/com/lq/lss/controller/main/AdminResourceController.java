/**
 *
 */
package com.lq.lss.controller.main;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.exception.BusinessException;
import com.lq.easyui.view.AjaxModelAndViewUtils;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.constant.PermRoleConst;
import com.lq.lss.core.service.AdminResourceService;
import com.lq.lss.model.AdminResource;
import com.lq.lss.model.EasyUITreeNew;
import com.lq.lss.model.ShiroUser;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.utils.EasyuiUtil;
import com.lq.util.JSONUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
@RequestMapping(value = "/user/adminResource.htm")
public class AdminResourceController extends ShiroBaseController<AdminResource, Integer, AdminResourceService> {

    @Resource
    private AdminResourceService adminResourceService;


    /**
     * 查询用户菜单资源
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(params = "method=queryUserMenuList")
    public ModelAndView queryUserMenuList(HttpServletRequest request, HttpServletResponse response) {

        ShiroUser shiroUser = getCurrentUser();


        // 是否是管理员角色
        Boolean isAdmin = SecurityUtils.getSubject().hasRole(PermRoleConst.ADMIN);

        // 是否有某个资源权限
        // Boolean isPermitted =   SecurityUtils.getSubject().isPermitted(PermResourceConst.CENTER_TRANS_UPDATE);


        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId",shiroUser.getId());
            List<String> typeList = new ArrayList<String>();
            typeList.add("1");
            map.put("typeList",typeList);

            List<EasyUITreeNew> easyUITreeNews = new ArrayList<EasyUITreeNew>();

            List<AdminResource> adminResources =  new ArrayList<AdminResource>();
            if(isAdmin){
                AdminResource adminResource = new AdminResource();
                adminResource.setType(1);
                adminResources =  adminResourceService.queryAdminResource(adminResource);
            }else {
                adminResources =  adminResourceService.queryUserResource(map);
            }

            for (AdminResource resource : adminResources) {

                EasyUITreeNew easyUITreeNew = new EasyUITreeNew();

                easyUITreeNew.setPid(resource.getPid() + "");
                easyUITreeNew.setId(resource.getId() + "");
                easyUITreeNew.setText(resource.getName());

                if (resource.getLayer() <= 1) {
                    easyUITreeNew.setState("open");
                } else {
                    easyUITreeNew.setState("closed");
                }

                // 自定义map
                Map<String, Object> mapTree = new HashMap<String, Object>();

                mapTree.put("layer", resource.getLayer() + "");
                mapTree.put("code", resource.getCode() + "");
                mapTree.put("type", resource.getType() + "");
                mapTree.put("menuLeafFlag", resource.getMenuLeafFlag() + "");
                mapTree.put("appId", resource.getAppId() + "");
                mapTree.put("url", resource.getMenuLeafUrl() + "");
                mapTree.put("menuLeafMode", resource.getMenuLeafMode() + "");
                mapTree.put("orderNo", resource.getOrderNo() + "");
                mapTree.put("remark", resource.getRemark() + "");

                easyUITreeNew.setAttributes(mapTree);

                easyUITreeNews.add(easyUITreeNew);
            }

            List<EasyUITreeNew> treeList = EasyuiUtil.getfatherNode("0", easyUITreeNews);

            return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(treeList));

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(null));

        }

    }

    /**
     * 查询菜单拥有的操作按钮列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(params = "method=queryResourceList")
    public ModelAndView queryResourceList(HttpServletRequest request, HttpServletResponse response) {


        String pid = ServletRequestUtils.getStringParameter(request, "pid", "");

        try {

            AdminResource adminResource = new AdminResource();
            if(StringUtils.hasText(pid)){
                adminResource.setPid(Long.parseLong(pid));
            }

            List<AdminResource> adminResources = adminResourceService.queryAdminResource(adminResource);

            return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(adminResources));

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(null));

        }

    }


    /**
     * 创建菜单资源
     * @param adminResource
     * @param response
     * @return
     */
    @RequestMapping(params = "method=create")
    @RequiresRoles(PermRoleConst.ADMIN)
    public ModelAndView create(AdminResource adminResource, HttpServletResponse response) {

        ResultDto resultDto = new ResultDto();

        try {
            adminResource.setCreateTime(new Date());
            adminResource.setAppId(100L);

            adminResourceService.save(adminResource);

            resultDto.setSuccess(true);

        } catch (BusinessException e) {
            resultDto.setErrorMsg(e.getMessage());
            resultDto.setSuccess(false);
            e.printStackTrace();
        }

        return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(resultDto));
    }

    /**
     * 修改菜单资源
     * @param adminResource
     * @param response
     * @return
     */
    @RequestMapping(params = "method=modify")
    @RequiresRoles(PermRoleConst.ADMIN)
    public ModelAndView modify( AdminResource adminResource, HttpServletResponse response) {
        ResultDto resultDto = new ResultDto();
        try {
            adminResourceService.update(adminResource);
            resultDto.setSuccess(true);
        } catch (BusinessException e) {
            resultDto.setErrorMsg(e.getMessage());
            resultDto.setSuccess(false);
            e.printStackTrace();
        }

        return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(resultDto));
    }

    /**
     * 删除菜单资源
     * @param id
     * @param response
     * @return
     */
    @RequestMapping(params = "method=delete")
    @RequiresRoles(PermRoleConst.ADMIN)
    public ModelAndView delete(String id, HttpServletResponse response) {

        ResultDto resultDto = new ResultDto();

        try {
            adminResourceService.remove(Integer.parseInt(id));
            resultDto.setSuccess(true);
        } catch (BusinessException e) {
            resultDto.setErrorMsg(e.getMessage());
            resultDto.setSuccess(false);
            e.printStackTrace();
        }

        return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(resultDto));
    }

    /**
     * 查询最大resource id
     *
     * @param pid
     * @return
     */
    @RequestMapping(params = "method=queryResMaxId")
    public ModelAndView queryResMaxId(Long pid, HttpServletResponse response) {
        ResultDto<String> resultDto = new ResultDto<String>();
        try {
            String result = adminResourceService.queryAdminResourceMaxId(pid);
            resultDto.setParamObj(result);
            resultDto.setSuccess(true);

        } catch (Exception e) {
            resultDto.setSuccess(false);
            resultDto.setErrorMsg(e.getMessage());
            logger.error("ResourceController.queryResMaxId()异常：", e);
            e.printStackTrace();

        }
        return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(resultDto));
    }

}
