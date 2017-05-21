package com.lq.lss.dto;

import com.lq.easyui.dto.ResultDto;
import com.lq.lss.model.AdminUser;

public class LoginResultDto extends ResultDto<Object> {

	private AdminUser adminUser;

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public LoginResultDto() {
		super();
	}

	public LoginResultDto(boolean success, String errorMsg) {
		super(success, errorMsg);
	}

	public LoginResultDto(boolean success) {
		super(success);
	}

}
