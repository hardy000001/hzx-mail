/**
 *
 */
package com.lq.lss.controller.main;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.exception.BusinessException;
import com.lq.easyui.view.AjaxModelAndViewUtils;
import com.lq.lss.core.service.AdminResourceService;
import com.lq.lss.core.service.AdminRoleResourceService;
import com.lq.lss.model.AdminRoleResource;
import com.lq.lss.model.EasyUITreeNew;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.utils.EasyuiUtil;
import com.lq.util.JSONUtil;
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
@RequestMapping(value = "/user/adminRoleResource.htm")
public class AdminRoleResourceController extends ShiroBaseController<AdminRoleResource, Integer, AdminRoleResourceService> {

    @Resource
    private AdminResourceService adminResourceService;

    @Resource
    private AdminRoleResourceService adminRoleResourceService;

    @Value("/system/admin_role_resource")
    private String roleView;

    @RequestMapping()
    public ModelAndView index(HttpServletRequest request , HttpServletResponse response) {

        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("roleId", ServletRequestUtils.getStringParameter(request,"id",""));
        return new ModelAndView(roleView, modelMap);
    }


    @RequestMapping(params = "method=queryRoleMenuList")
    public ModelAndView queryRoleMenuList(String roleId, HttpServletResponse response) {

        try {

            //自定义map
            Map<String, Object> mapTree = new HashMap<String, Object>();

            EasyUITreeNew tree = new EasyUITreeNew();
            tree.setId("1");
            tree.setText("功能列表");
            tree.setState("open");
            tree.setPid("0");
            mapTree.put("type","1");
            mapTree.put("menuLeafFlag","0");
            mapTree.put("appId","0");
            mapTree.put("layer","0");
            tree.setAttributes(mapTree);


            // 查询菜单
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("roleId", roleId);
            map.put("appId", 100);
            List<Long> typeList = new ArrayList<Long>();
            typeList.add(1L); // 菜单
            map.put("typeList", typeList);

            List<Map<String, Object>> resultList = adminResourceService.queryAdminRoleResource(map);

            List<EasyUITreeNew> easyUITreeNews = new ArrayList<EasyUITreeNew>();

            easyUITreeNews.add(tree);

            for (Map<String, Object> adminResource : resultList) {

                EasyUITreeNew treeData = new EasyUITreeNew();
                treeData.setId(adminResource.get("id") + "");
                treeData.setText(adminResource.get("name") + "");
                treeData.setState("open");
                if (adminResource.get("checked").toString().equals("1")) {
                    treeData.setChecked(true);
                } else {
                    treeData.setChecked(false);
                }

                if(adminResource.get("pid").toString().equals("0")){
                    treeData.setPid("1");
                }else{
                    treeData.setPid(adminResource.get("pid") + "");
                }

                mapTree = new HashMap<String, Object>();
                mapTree.put("layer", adminResource.get("layer") + "");
                mapTree.put("type", adminResource.get("type") + "");
                mapTree.put("menuLeafFlag", adminResource.get("menuLeafFlag") + "");
                mapTree.put("appId", adminResource.get("appId") + "");
                mapTree.put("url", adminResource.get("menuLeafUrl") + "");
                mapTree.put("menuLeafMode", adminResource.get("menuLeafMode") + "");
                mapTree.put("orderNo", adminResource.get("orderNo") + "");
                mapTree.put("code", adminResource.get("code") + "");
                treeData.setAttributes(mapTree);

                easyUITreeNews.add(treeData);
            }


            List<EasyUITreeNew> treeList = EasyuiUtil.getfatherNode("0", easyUITreeNews);

            return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(treeList));

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(null));

        }

    }
    @RequestMapping(params = "method=queryResourceListByIds")
    public ModelAndView queryResourceListByIds(String pids, Long roleId,HttpServletResponse response) {

        List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();

        try {
            String[] idArray = pids.split(",");
            List<Long> idList = new ArrayList<Long>();
            for (String id : idArray) {
                idList.add(Long.parseLong(id));
            }
            resList = adminResourceService.queryAdminRoleResourceByPids(idList, null, roleId);
        } catch (Exception e) {
            logger.error("AdminRoleResourceController.queryResourceList()处理异常：", e);
        }
        return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(resList));
    }


    @RequestMapping(params = "method=create")
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {

        String resourceIds = ServletRequestUtils.getStringParameter(request,"resourceIds","");
        Integer roleId =ServletRequestUtils.getIntParameter(request,"roleId",0);

        ResultDto resultDto = new ResultDto();

        try {
            List<AdminRoleResource> roleResList = new ArrayList<AdminRoleResource>();
            String[] idArray = resourceIds.split(",");
            for (int i = 0; i < idArray.length; i++) {
                int type=2;
                AdminRoleResource roleRes = new AdminRoleResource();
                roleRes.setRoleId(Long.parseLong(roleId+""));
                roleRes.setResourceId(Long.parseLong(idArray[i]));
                if(Long.parseLong(idArray[i])<1000L){
                    type=3;
                }
                roleRes.setType(type);
                roleRes.setCreateTime(new Date());
                roleResList.add(roleRes);
            }

            // 删除之前资源
             adminRoleResourceService.remove(roleId);
            // 插入
             adminRoleResourceService.saveList(roleResList);

            resultDto.setSuccess(true);

        } catch (BusinessException e) {
            resultDto.setErrorMsg(e.getMessage());
            resultDto.setSuccess(false);
            e.printStackTrace();
        }

        return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(resultDto));
    }


}
