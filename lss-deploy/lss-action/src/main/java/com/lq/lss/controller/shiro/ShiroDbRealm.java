package com.lq.lss.controller.shiro;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.lq.lss.constant.PermRoleConst;
import com.lq.lss.core.service.AdminUserPermissionService;
import com.lq.lss.core.service.AdminUserService;
import com.lq.lss.core.service.AdminDeptService;
import com.lq.lss.model.AdminUser;
import com.lq.lss.model.SessionUser;
import com.lq.lss.model.ShiroUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * Created by lanbo on 16/11/7.
 */
@Component
public class ShiroDbRealm extends AuthorizingRealm {

    @Resource
    private AdminUserService adminUserService;

    @Resource
    private AdminUserPermissionService adminUserPermissionService;

    @Resource
    AdminDeptService adminDeptService;

    private static final Logger LOG = LoggerFactory.getLogger(ShiroDbRealm.class);

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

        LOG.info("Shiro开始登录认证");
        //  认证沿用老版本，此处不做验证用户处理。只初始化资源2
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        AdminUser adminUser = adminUserService.queryUserByLoginName(token.getUsername());

        SessionUser sessionUser = new SessionUser();

        sessionUser.setUserId(Long.parseLong(adminUser.getUserId()+""));
        sessionUser.setDeptId(adminUser.getDeptId());
        sessionUser.setLoginName(adminUser.getLoginName());
        sessionUser.setOperCode(adminUser.getOperCode());
        sessionUser.setPyCode(adminUser.getPyCode());
        sessionUser.setRealName(adminUser.getRealName());
        // 根据部门ID查询所属中心
        int centerId=-1;
        centerId = adminDeptService.findRootId(Integer.parseInt(adminUser.getDeptId()));
        sessionUser.setCenterId(centerId);


        // 用户角色列表
        List<Long> roleList = adminUserPermissionService.queryUserHasRoles(adminUser.getUserId());
        // 用户角色包含资源列表
        List<Long> permissionList = adminUserPermissionService.querytUserHasResources(adminUser.getUserId());

        ShiroUser shiroUser = new ShiroUser(adminUser.getUserId(), adminUser.getLoginName(), adminUser.getRealName(), sessionUser, roleList, permissionList);

        // 认证缓存信息
        return new SimpleAuthenticationInfo(shiroUser, adminUser.getLoginPwd().toCharArray(), getName());
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        LOG.info("Shiro开始查询授权");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        boolean hasAdminRole = false;
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        List<Long> roleList = shiroUser.getRoleList();
        if (roleList != null) {
            info.addRoles(Lists.transform(roleList, new Function<Long, String>() {
                @Override
                public String apply(Long input) {
                    return input.toString();
                }
            }));

            hasAdminRole = roleList.contains(Long.valueOf(PermRoleConst.ADMIN));
        }
        LOG.info("用户“{}”拥有的角色有：{}", shiroUser.getRealName(), roleList);

        List<Long> rescIds = shiroUser.getPermissionList();

        Set<String> resourceSet = null;

        if (hasAdminRole) {
            resourceSet = Sets.newHashSetWithExpectedSize(1);
            resourceSet.add("*"); //所有权限
        } else if (rescIds != null) {
            resourceSet = Sets.newHashSetWithExpectedSize(rescIds.size());
            for (Long rescId : rescIds) {
                resourceSet.add(rescId.toString());
            }

        }

        info.addStringPermissions(resourceSet);
        LOG.info("用户“{}”拥有的资源权限有：{}", shiroUser.getRealName(), resourceSet);


        return info;
    }
}
