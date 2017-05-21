package com.lq.lss.core.service;

import java.util.List;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.dto.LoginResultDto;
import com.lq.lss.model.AdminUser;

public interface AdminUserService extends EasyUIService<AdminUser, Integer> {

	public LoginResultDto login(String loginName, String pwd);

	/**
	 * 更新最后登录时间
	 *
	 * @param userId
	 * @param loginIp
	 */
	 void updateLastLogin(int userId, String loginIp);

	 void removeRdTx(int userId);

	 ResultDto updatePwd(int userId, String oldPwd, String newPwd);
	
	 AdminUser loginAuth(String username, String pwd);

	 AdminUser queryUserByLoginName(String loginName);

	 ResultDto saveUserAndRolesRdTx(AdminUser adminUser, List<Integer> roleIds,Integer actionType);

	 void updateUser(AdminUser adminUser);

}
