package com.lq.lss.core.service.impl;


import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.core.dao.AdminRoleDao;
import com.lq.lss.core.service.AdminRoleService;
import com.lq.lss.model.AdminRole;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminRoleServiceImpl extends EasyUIServiceBase<AdminRole, Integer, AdminRoleDao> implements AdminRoleService {

    @Override
    public List<Map<String, Object>> queryUserRoles(Integer userId) {
        return modelDao.selectUserRoles(userId);
    }
}
