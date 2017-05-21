package com.lq.lss.core.dao;
                                                                
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lq.lss.model.UAdminUser;
import com.lq.lss.core.dao.base.LssSimpleBaseDao;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2017-04-24 17:20:09
 */
@Repository
public class UAdminUserDao extends LssSimpleBaseDao<UAdminUser, String>{

	/**
	 * 查询用户
	 * 
	 * @param loginName
	 * @return AdminUser 或null
	 */
	public UAdminUser findByLoginName(String loginName) {
		return (UAdminUser) findObjectByParams("findByLoginName", loginName);
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
     * 修改角色关联的用户个数
     * @param mchCode
     */
    public void updateUserCnt(String mchCode) {
    	
        Map<String, Object> paraMap=new HashMap<String, Object>();
    	paraMap.put("mchCode", mchCode);
		update("updateUserCnt", mchCode);
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