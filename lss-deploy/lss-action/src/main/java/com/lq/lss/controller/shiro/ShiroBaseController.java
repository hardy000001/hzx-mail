package com.lq.lss.controller.shiro;

import com.google.common.collect.Lists;
import com.lq.easyui.controller.easyui.EasyUIController;
import com.lq.easyui.model.easyui.EasyUiModel;
import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.model.ShiroUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;

public  abstract class ShiroBaseController <T extends EasyUiModel<ID>, ID extends Serializable, EasyUIService_ extends EasyUIService<T, ID>>  extends EasyUIController{

	 /* 获取当前登录用户对象
	 *
	 * @return
	 */
	public ShiroUser getCurrentUser() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user;
	}


	/**
	 * 获取当前登录用户id
	 *
	 * @return
	 */
	public Integer getUserId() {
		return this.getCurrentUser().getId();
	}

	/**
	 * 获取当前登录用户名
	 *
	 * @return
	 */
	public String getUserName() {
		return this.getCurrentUser().getName();
	}

	public String getUserRealName() {
		return this.getCurrentUser().getRealName();
	}

	public boolean hasAnyRoles(String... roles){
		Assert.notEmpty(roles);

		Subject subject = SecurityUtils.getSubject();
		List<String> roleList = Lists.newArrayList(roles);
		boolean[] hasRoles = subject.hasRoles(roleList);
		for (boolean hasRole : hasRoles) {
			if(hasRole){
				return true;
			}
		}
		return false;
	}

}
