package com.lq.lss.core.dao;

import com.lq.lss.core.dao.base.LssSimpleBaseDao;
import com.lq.lss.model.AdminResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AdminResourceDao extends LssSimpleBaseDao<AdminResource, Integer> {


    /** 查询用户登录左菜单树
     * @param map
     * @return
     */
    public List<AdminResource> selectUserRoleResource(Map<String, Object> map) {
        return (List<AdminResource>) findListByParams("selectUserRoleResource", map);
    }

    /**
     * 查询资源列表
     *
     * @param adminResource model
     * @return 列表
     */

    public List<AdminResource> selectResource(AdminResource adminResource) {
        return (List<AdminResource>) findListByParams("selectResource", adminResource);
    }

    /**
     * @param pid
     * @return
     */
    public String selectAdminResourceMaxId(Long pid) {
        return (String) findObjectByParams("selectAdminResourceMaxId", pid);
    }

    /**
     * 查询角色的菜单资源
     *
     * @param map
     * @return
     */
    public List<Map<String, Object>> selectAdminRoleResource(Map<String, Object> map) {
        return (List<Map<String, Object>>) findListByParams("selectAdminRoleResource", map);
    }

    /**
     * 查询角色选中菜单的 操作按钮
     *
     * @param map
     * @return
     */
    public List<Map<String, Object>> selectAdminRoleResourceByPids(Map<String, Object> map) {
        return (List<Map<String, Object>>) findListByParams("selectAdminRoleResourceByPids", map);
    }
}
