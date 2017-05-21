package com.lq.lss.core.dao;

import com.lq.lss.core.dao.base.LssSimpleBaseDao;
import com.lq.lss.model.AdminRole;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AdminRoleDao extends LssSimpleBaseDao<AdminRole, Integer> {


    /**
     * 查询角色--关联用户。用于给用户分配角色
     * @param userId
     * @return
     */
    public List<Map<String,Object>> selectUserRoles(Integer userId){

        return (List<Map<String,Object>>) findListByParams("selectUserRoles",userId);
    }
}
