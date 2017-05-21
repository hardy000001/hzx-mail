package com.lq.lss.core.service;

import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.model.AdminResource;

import java.util.List;
import java.util.Map;

public interface AdminResourceService extends EasyUIService<AdminResource, Integer> {


    /**
     * 查询用户登录后左菜单
     * @param map
     * @return
     */
    List<AdminResource> queryUserResource(Map<String, Object> map);

    /**
     * 查询菜单资源---用于角色分配权限tree
     * @param adminResource
     * @return
     */
    List<AdminResource> queryAdminResource(AdminResource adminResource);

    String queryAdminResourceMaxId(Long pid);

    List<Map<String, Object>> queryAdminRoleResource(Map<String, Object> map);

    List<Map<String, Object>> queryAdminRoleResourceByPids(List<Long> pids, Long userId, Long roleId);

}
