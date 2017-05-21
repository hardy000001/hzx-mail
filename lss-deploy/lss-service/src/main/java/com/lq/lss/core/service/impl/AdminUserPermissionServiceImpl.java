package com.lq.lss.core.service.impl;


import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.core.dao.AdminUserPermissionDao;
import com.lq.lss.core.service.AdminUserPermissionService;
import com.lq.lss.model.AdminUserPermission;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserPermissionServiceImpl extends EasyUIServiceBase<AdminUserPermission, Integer, AdminUserPermissionDao> implements AdminUserPermissionService {


    @Override
    public List<Long> queryUserHasRoles(Integer userId) {
        return modelDao.selectUserHasRoles(userId);
    }

    @Override
    public List<Long> querytUserHasResources(Integer userId) {
        return modelDao.selectUserHasResources(userId);
    }
}
