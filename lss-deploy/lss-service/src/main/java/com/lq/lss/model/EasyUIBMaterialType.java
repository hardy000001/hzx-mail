package com.lq.lss.model;


import java.util.Date;
import java.util.List;

public class EasyUIBMaterialType {
	
	private Integer id;
	
	private Integer typeid;
	/** 名称 **/
	private String name;
	/** 父ID **/
	private Integer parentid;
	/** 同级排序 **/
	private String position;
	/** 记账单位 **/
	private String accountFlag;
	/** 换算单位 **/
	private String convertFlag;
	/** 状态 0：正常 9：禁用 **/
	private String status;
	/** 创建时间 **/
	private Date createtime;
    
    private String state;
    
    
    private String pid;  // 父id
    private List<EasyUIBMaterialType> children;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAccountFlag() {
		return accountFlag;
	}

	public void setAccountFlag(String accountFlag) {
		this.accountFlag = accountFlag;
	}

	public String getConvertFlag() {
		return convertFlag;
	}

	public void setConvertFlag(String convertFlag) {
		this.convertFlag = convertFlag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<EasyUIBMaterialType> getChildren() {
		return children;
	}

	public void setChildren(List<EasyUIBMaterialType> children) {
		this.children = children;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

    
}
