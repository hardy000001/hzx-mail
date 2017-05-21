package com.lq.lss.core.dao;

import com.lq.lss.core.dao.base.LssSimpleBaseDao;
import com.lq.lss.model.AdminUserPermission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminUserPermissionDao extends LssSimpleBaseDao<AdminUserPermission, Integer> {

    /**
     * 查询用户拥有角色
     * @param userId
     * @return
     */
    public List<Long> selectUserHasRoles(Integer userId) {
        return (List<Long>) findListByParams("selectUserHasRoles", userId);
    }

    /**
     * 查询用户拥有的资源
     * @param userId
     * @return
     */
    public List<Long> selectUserHasResources(Integer userId) {
        return (List<Long>) findListByParams("selectUserHasResources", userId);
    }
}
