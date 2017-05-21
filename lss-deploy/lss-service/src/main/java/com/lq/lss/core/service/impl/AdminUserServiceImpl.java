package com.lq.lss.core.service.impl;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.exception.BusinessException;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.core.dao.AdminUserDao;
import com.lq.lss.core.service.AdminUserPermissionService;
import com.lq.lss.core.service.AdminUserService;
import com.lq.lss.dto.LoginResultDto;
import com.lq.lss.model.AdminUser;
import com.lq.lss.model.AdminUserPermission;
import com.lq.lss.utils.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class AdminUserServiceImpl extends EasyUIServiceBase<AdminUser, Integer, AdminUserDao> implements AdminUserService {


	@Resource
	private AdminUserDao adminUserDao;
	@Resource
	private AdminUserPermissionService  adminUserPermissionService;


	public LoginResultDto login(String loginName, String pwd) {

		AdminUser adminUser = adminUserDao.findByLoginName(loginName);
		if (adminUser == null) {
			return new LoginResultDto(false, "不存在登录名为 " + loginName + " 的用户");
		} else {
			// 如果用户密码是空，则不需要验证码
			boolean loginAuth = false;
			if (adminUser.getLoginPwd() == null || adminUser.getLoginPwd().isEmpty()) {
				loginAuth = true;
			}
			// 如果密码比对
			else if (pwd.equalsIgnoreCase(adminUser.getLoginPwd())) {
				if (adminUser.getStatus() != 1) {
					loginAuth = false;
					return new LoginResultDto(false, adminUser.getStatus() == 0 ? "用户被锁定" : "用户已销户");
				} else {
					loginAuth = true;
				}
			}

			if (loginAuth) {
				// 验证 成功

				// 2.更新最后登录时间

				LoginResultDto resultDto = new LoginResultDto(true);
				resultDto.setAdminUser(adminUser);

				return resultDto;
			} else {
				return new LoginResultDto(false, "用户名密码不正确");
			}

		}

	}

	@Override
	public void updateLastLogin(int userId, String loginIp) {
		AdminUser lo = new AdminUser();
		lo.setUserId(userId);
//		lo.setLastLoginIp(loginIp);
//		lo.setLastLoginTime(new Date());
		adminUserDao.update(lo);
	}

	public ResultDto remove(String id) {
		// id为admin不能被删除
		if (id.equals("1")) {
			return new ResultDto(false, "admin不允许被删除");
		}
		return new ResultDto(true, "删除数据成功");
		// return remove(id);
	}

	@Override
	public ResultDto update(AdminUser t) {

		if (t.getLoginName() != null) {
			t.setLoginName(t.getLoginName().trim().toLowerCase());
		}
		if (t.getUserId().intValue() == 1) {
			if (!t.getLoginName().toLowerCase().equals("admin")) {
				return new ResultDto(false, "管理员登录名不能修改");
			}
		} else {
			if (t.getLoginName().toLowerCase().equals("admin")) {
				return new ResultDto(false, "登录名已被占用");
			}
			boolean ifAleadyEx = adminUserDao.existsLoginNameExcloudUserId(t.getLoginName(), t.getUserId());
			if (ifAleadyEx) {
				return new ResultDto(false, "登录名已被占用");
			}
		}

		if (StringUtils.hasLength(t.getLoginPwd())) {
			t.setLoginPwd(MD5Util.md5Hex(t.getLoginPwd()));
		}

		return super.update(t);
	}

	@Override
	public ResultDto save(AdminUser t) {
		if (t.getLoginName() != null) {
			t.setLoginName(t.getLoginName().trim().toLowerCase());
		}

		boolean ifAleadyEx = adminUserDao.existsLoginName(t.getLoginName());
		if (ifAleadyEx) {
			return new ResultDto(false, "登录名已被占用");
		}
		if (StringUtils.hasLength(t.getLoginPwd())) {
			t.setLoginPwd(MD5Util.md5Hex(t.getLoginPwd()));
		}
		return super.save(t);
	}



	public void removeRdTx(int userId) throws BusinessException {
		try {
			adminUserDao.deleteById(userId);
			remove(userId + "");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());

		}
	}

	@Override
	public ResultDto updatePwd(int userId, String oldPwd, String newPwd) {
		AdminUser adminUser = adminUserDao.findById(userId);
		String oldPwdMd5 = MD5Util.md5Hex(oldPwd);
		if (!oldPwdMd5.equals(adminUser.getLoginPwd())) {
			return new ResultDto(false, "原密码不正确");
		}
		adminUser.setLoginPwd(MD5Util.md5Hex(newPwd));
		int i = adminUserDao.update(adminUser);
		return new ResultDto(true);
	}

	@Override
	public AdminUser loginAuth(String username, String pwd) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loginName", username);
		map.put("loginPwd", pwd);
		return (AdminUser) adminUserDao.findObjectByParams("findByUserNameAndPwd", map);
	}

	@Override
	public AdminUser queryUserByLoginName(String loginName) {
		return  adminUserDao.findByLoginName(loginName);
	}

	@Override
	public ResultDto saveUserAndRolesRdTx(AdminUser adminUser, List<Integer> roleIds,Integer actionType) {

		ResultDto resultDto = new ResultDto();
		try {
			Integer userId = adminUser.getUserId() ;
			if(actionType==1){
				// 新增用户
				resultDto = save(adminUser);
				if (!resultDto.isSuccess()) {
					return resultDto;
				}
				
				adminUser = adminUserDao.findByLoginName(adminUser.getLoginName());
				userId = adminUser.getUserId();
			}else {
				AdminUser oldAdminUser = adminUserDao.findByLoginName(adminUser.getLoginName());
				String oldPwd=oldAdminUser.getLoginPwd();
				String newPwd=adminUser.getLoginPwd();
				//如果新密码和原始密码不相同就更新密码，相同就不需要更新
				if(!newPwd.equals(oldPwd)){
					adminUser.setLoginPwd(MD5Util.md5Hex(adminUser.getLoginPwd()));
				}
				adminUserDao.update(adminUser);
			}

			List<AdminUserPermission> adminUserPermissions = new ArrayList<AdminUserPermission>();
			for (Integer roleId : roleIds) {
				AdminUserPermission  adminUserPermission = new AdminUserPermission();
				adminUserPermission.setUserId(userId);
				adminUserPermission.setPermissionType(1);
				adminUserPermission.setPermissionId(Long.parseLong(roleId+""));
				adminUserPermission.setCreateTime(new Date());
				adminUserPermissions.add(adminUserPermission);
			}
			// 删除之前的角色
			adminUserPermissionService.remove(userId);
			// 重新保存
			adminUserPermissionService.saveList(adminUserPermissions);

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
	public void updateUser(AdminUser adminUser) {
		    modelDao.update(adminUser);
	}


}
