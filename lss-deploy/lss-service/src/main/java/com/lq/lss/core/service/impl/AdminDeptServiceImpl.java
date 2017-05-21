package com.lq.lss.core.service.impl;

import java.util.List;

import com.lq.lss.core.service.AdminDeptService;
import com.lq.lss.model.AdminDept;
import org.springframework.stereotype.Service;

import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.core.dao.AdminDeptDao;

@Service
public class AdminDeptServiceImpl extends EasyUIServiceBase<AdminDept, Integer, AdminDeptDao> implements AdminDeptService {

    @Override
    public List<AdminDept> findDepList(AdminDept adminDept) {
        return modelDao.findDeptList(adminDept);
    }

    @Override
    public Integer findRootId(Integer depId) {
        int rootId = -1;
        // 递归查询，往上5 ,查询到parentId=0 的根节点跳出
        for (int i = 0; i < 5; i++) {
            AdminDept adminDept = modelDao.findById(depId);
            if (adminDept == null) break;
            if (adminDept.getParentId() == 0) {
                rootId = adminDept.getDeptId();
                break;
            } else {
                depId = adminDept.getParentId();
            }
        }
        return rootId;
    }

}
