package com.lq.lss.core.dao;

import java.util.List;

import com.lq.lss.model.AdminDept;
import org.springframework.stereotype.Repository;

import com.lq.lss.core.dao.base.LssSimpleBaseDao;

@Repository
public class AdminDeptDao extends LssSimpleBaseDao<AdminDept, Integer> {

	@SuppressWarnings("unchecked")
	public List<AdminDept> findDeptList(AdminDept adminDept) {
		return (List<AdminDept>) findListByParams("findDeptList", adminDept);
	}
	
}
