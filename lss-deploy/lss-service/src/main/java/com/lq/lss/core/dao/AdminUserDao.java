package com.lq.lss.core.dao;

import java.util.HashMap;
import java.util.Map;

import com.lq.lss.model.AdminUser;
import org.springframework.stereotype.Repository;

import com.lq.lss.core.dao.base.LssSimpleBaseDao;

@Repository
public class AdminUserDao extends LssSimpleBaseDao<AdminUser, Integer> {

	/**
	 * 查询用户
	 * 
	 * @param loginName
	 * @return AdminUser 或null
	 */
	public AdminUser findByLoginName(String loginName) {
		return (AdminUser) findObjectByParams("findByLoginName", loginName);
	}

	/**
	 * 快速查询用户是否存在
	 * 
	 * @param userName
	 * @return true表示 存在
	 */
	public boolean existsLoginNameExcloudUserId(String loginName, Integer excludeUserId) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loginName", loginName);
		map.put("excludeUserId", excludeUserId);
		Integer count = (Integer) findObjectByParams("existsLoginName", map);
		return count != null && count >= 1;
	}

	/**
	 * 快速查询用户是否存在
	 * 
	 * @param userName
	 * @return true表示 存在
	 */
	public boolean existsLoginName(String loginName) {

		return existsLoginNameExcloudUserId(loginName, null);
	}

}
