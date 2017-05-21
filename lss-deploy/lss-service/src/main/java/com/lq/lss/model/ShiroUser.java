/**
 *
 */
package com.lq.lss.model;

import java.io.Serializable;
import java.util.List;

public class ShiroUser implements Serializable {

    private static final long serialVersionUID = -1373760761780840081L;
    private Integer id;
    private String name;
    private String realName;
    private SessionUser sessionUser;
    private List<Long> roleList;
    private List<Long> permissionList;


    public ShiroUser(Integer id, String name, String realName,SessionUser sessionUser,  List<Long> roleList ,List<Long> permissionList) {
        this.id = id;
        this.name = name;
        this.realName = realName;
        this.sessionUser = sessionUser;
        this.roleList = roleList;
        this.permissionList =permissionList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public List<Long> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Long> roleList) {
        this.roleList = roleList;
    }

    public List<Long> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Long> permissionList) {
        this.permissionList = permissionList;
    }

    public SessionUser getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(SessionUser sessionUser) {
        this.sessionUser = sessionUser;
    }
}
