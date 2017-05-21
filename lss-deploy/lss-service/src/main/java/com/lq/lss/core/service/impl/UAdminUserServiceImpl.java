package com.lq.lss.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.exception.BusinessException;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.model.UAdminUser;
import com.lq.lss.model.UAdminUserPermission;
import com.lq.lss.utils.MD5Util;
import com.lq.lss.core.dao.UAdminUserDao;
import com.lq.lss.core.service.UAdminUserPermissionService;
import com.lq.lss.core.service.UAdminUserService;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2017-04-24 17:20:09
 */
@Service
public class UAdminUserServiceImpl extends EasyUIServiceBase<UAdminUser, String, UAdminUserDao> implements UAdminUserService{

	@Resource
	private UAdminUserPermissionService uAdminUserPermissionService;
	
	@Override
	public ResultDto<String> updatePwd(String userId, String oldPwd, String newPwd) {
		UAdminUser adminUser = modelDao.findById(userId);
		String oldPwdMd5 = MD5Util.md5Hex(oldPwd);
		if (!oldPwdMd5.equals(adminUser.getLoginPwd())) {
			return new ResultDto<String>(false, "原密码不正确");
		}
		adminUser.setLoginPwd(MD5Util.md5Hex(newPwd));
		modelDao.update(adminUser);
		return new ResultDto<String>(true);
	}

	@Override
	public ResultDto<String> saveUserAndRolesRdTx(UAdminUser adminUser,
			List<Integer> roleIds,Integer actionType) {
		ResultDto<String> resultDto = new ResultDto<String>();
		try {
			Integer userId = adminUser.getUserId() ;
			if(actionType==1){
				// 新增用户
				resultDto = save(adminUser);
				if (!resultDto.isSuccess()) {
					return resultDto;
				}
				
				adminUser = modelDao.findByLoginName(adminUser.getLoginName());
				userId = adminUser.getUserId();
			}else {
				UAdminUser oldAdminUser = modelDao.findByLoginName(adminUser.getLoginName());
				String oldPwd=oldAdminUser.getLoginPwd();
				String newPwd=adminUser.getLoginPwd();
				//如果新密码和原始密码不相同就更新密码，相同就不需要更新
				if(!newPwd.equals(oldPwd)){
					adminUser.setLoginPwd(MD5Util.md5Hex(adminUser.getLoginPwd()));
				}
				modelDao.update(adminUser);
			}

			List<UAdminUserPermission> adminUserPermissions = new ArrayList<UAdminUserPermission>();
			for (Integer roleId : roleIds) {
				UAdminUserPermission  adminUserPermission = new UAdminUserPermission();
				adminUserPermission.setUserId(userId);
				adminUserPermission.setPermissionType(1);
				adminUserPermission.setPermissionId(Long.parseLong(roleId+""));
				adminUserPermission.setCreateTime(new Date());
				adminUserPermissions.add(adminUserPermission);
			}
			// 删除之前的角色
			uAdminUserPermissionService.remove(userId+"");
			// 重新保存
			uAdminUserPermissionService.saveList(adminUserPermissions);
			
			modelDao.updateUserCnt(adminUser.getMchCode());

			resultDto.setSuccess(true);
			return resultDto;
		} catch (Exception e) {
			e.printStackTrace();
			resultDto.setSuccess(false);
			resultDto.setErrorMsg(e.getMessage());
			return resultDto;
		}
	}
	
	@Override
	public ResultDto<String> save(UAdminUser t) {
		if (t.getLoginName() != null) {
			t.setLoginName(t.getLoginName().trim().toLowerCase());
		}

		boolean ifAleadyEx = modelDao.existsLoginName(t.getLoginName());
		if (ifAleadyEx) {
			return new ResultDto<String>(false, "登录名已被占用");
		}
		if (StringUtils.hasLength(t.getLoginPwd())) {
			t.setLoginPwd(MD5Util.md5Hex(t.getLoginPwd()));
		}
		return super.save(t);
	}

	@Override
	public void removeRdTx(String userId,String mchCode) {
		try {
			uAdminUserPermissionService.remove(userId);
			modelDao.deleteById(userId);
			modelDao.updateUserCnt(mchCode);
			remove(userId + "");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());

		}
	}

	@Override
	public void updateUser(UAdminUser adminUser) {
		  modelDao.update(adminUser);
	}

    
}