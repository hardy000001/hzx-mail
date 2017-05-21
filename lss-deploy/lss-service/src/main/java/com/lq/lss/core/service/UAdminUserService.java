package com.lq.lss.core.service;

import java.util.List;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.model.UAdminUser;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2017-04-24 17:20:09
 */
public interface UAdminUserService extends EasyUIService<UAdminUser, String>{

	 /**
	  * 修改用户名密码
	  * @param userId
	  * @param oldPwd
	  * @param newPwd
	  * @return
	  */
	 ResultDto<String> updatePwd(String userId, String oldPwd, String newPwd);
	 /**
	  * 删除用户
	  * @param userId
	  */
	 void removeRdTx(String userId,String mchCode);
	 /**
	  * 保存和修改用户
	  * @param adminUser
	  * @param roleIds
	  * @return
	  */
	 ResultDto<String> saveUserAndRolesRdTx(UAdminUser adminUser, List<Integer> roleIds,Integer actionType);
	 /**
	  * 修改用户
	  * @param adminUser
	  */
	 void updateUser(UAdminUser adminUser);

}