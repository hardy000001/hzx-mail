package com.lq.lss.dto;

/**
 * @author  作者: hzx
 * @date 创建时间: 2016-11-8下午3:58:23
 */
public class AuditDto{

	
	/** 编号**/
    private String id;
	/** 状态 **/
    private String status;
    private String deptId;
    private String userId;
    private Object obj;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
    
    
}