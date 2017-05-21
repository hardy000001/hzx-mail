package com.lq.lss.core.service.impl;


import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.core.dao.AdminResourceDao;
import com.lq.lss.core.service.AdminResourceService;
import com.lq.lss.model.AdminResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminResourceServiceImpl extends EasyUIServiceBase<AdminResource, Integer, AdminResourceDao> implements AdminResourceService {


    @Override
    public List<AdminResource> queryUserResource(Map<String, Object> map) {
        return modelDao.selectUserRoleResource(map);
    }

    @Override
    public List<AdminResource> queryAdminResource(AdminResource adminResource) {
        return  modelDao.selectResource(adminResource);
    }

    @Override
    public String queryAdminResourceMaxId(Long pid) {
        String result = modelDao.selectAdminResourceMaxId(pid);
        if (!StringUtils.hasText(result)) {
            result = pid + "01";
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> queryAdminRoleResource(Map<String, Object> map) {
        return modelDao.selectAdminRoleResource(map);
    }

    @Override
    public List<Map<String, Object>> queryAdminRoleResourceByPids(List<Long> pids, Long userId, Long roleId){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pids", pids);
        map.put("roleId", roleId);
        map.put("userId", userId);
        return modelDao.selectAdminRoleResourceByPids(map);
    }


}
