package com.lq.lss.core.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.model.UAdminUserPermission;
import com.lq.lss.core.dao.UAdminUserPermissionDao;
import com.lq.lss.core.service.UAdminUserPermissionService;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2017-04-24 17:20:09
 */
@Service
public class UAdminUserPermissionServiceImpl extends EasyUIServiceBase<UAdminUserPermission, String, UAdminUserPermissionDao> implements UAdminUserPermissionService{

    @Resource
	private UAdminUserPermissionDao uAdminUserPermissionDao;
}