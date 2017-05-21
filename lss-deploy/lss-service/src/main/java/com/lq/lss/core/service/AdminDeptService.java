package com.lq.lss.core.service;

import java.util.List;

import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.model.AdminDept;




public interface AdminDeptService extends EasyUIService<AdminDept,Integer> {

	/**
	 * 查询所有
	 * @return
	 */
	public List<AdminDept> loadAll();
	
	public List<AdminDept> findDepList(AdminDept adminDept);
	
	public Integer findRootId(Integer depId);
	
}
