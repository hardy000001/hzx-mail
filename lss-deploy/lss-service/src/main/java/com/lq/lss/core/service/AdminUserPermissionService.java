package com.lq.lss.core.service;

import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.model.AdminUserPermission;

import java.util.List;

public interface AdminUserPermissionService extends EasyUIService<AdminUserPermission, Integer> {

    List<Long> queryUserHasRoles(Integer userId);

    List<Long> querytUserHasResources(Integer userId);

}
