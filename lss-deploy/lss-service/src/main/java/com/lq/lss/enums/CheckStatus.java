package com.lq.lss.enums;

public enum CheckStatus {
	
	CANCEL(-1),//已取消
	INIT(1), //初始 待审核状态 小于等于0的状态的数据都是可以修改的
	OK(0), //审核通过
	REJECT(2),////审批拒绝
	AUDIT_DISABLED(3);////废除
	
	
	int code = 1;
	int  confirm= 0;

	private CheckStatus(int code) {
		this.code = code;
	}
	
	public int getCode(){
		return code;
	}
	
	
	public int getConfirm(){
		return confirm;
	}
	
	
}
