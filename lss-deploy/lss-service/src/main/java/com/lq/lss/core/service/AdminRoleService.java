package com.lq.lss.core.service;

import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.model.AdminRole;

import java.util.List;
import java.util.Map;

public interface AdminRoleService extends EasyUIService<AdminRole, Integer> {

    /**
     *查询用户拥有的角色
     * @param userId
     * @return
     */
    List<Map<String,Object>> queryUserRoles(Integer userId);

}
