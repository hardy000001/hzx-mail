package com.lq.lss.model;

import java.util.Date;

import com.lq.easyui.model.easyui.EasyUiModel;

/**
 *
 * @author
 * @since 2016-08-31
 */
public class BMaterialType extends EasyUiModel<Integer> {

	/** 序列化ID */
	private static final long serialVersionUID = 1L;

	/** ID **/
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

	private Integer id;

	/**
	 * 获取属性:typeid ID
	 * 
	 * @return typeid
	 */
	public Integer getTypeid() {
		return typeid;
	}

	/**
	 * 设置属性:typeid ID
	 * 
	 * @param typeid
	 */
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	/**
	 * 获取属性:name 名称
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置属性:name 名称
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取属性:parentid 父ID
	 * 
	 * @return parentid
	 */
	public Integer getParentid() {
		return parentid;
	}

	/**
	 * 设置属性:parentid 父ID
	 * 
	 * @param parentid
	 */
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	/**
	 * 获取属性:position 同级排序
	 * 
	 * @return position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * 设置属性:position 同级排序
	 * 
	 * @param position
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * 获取属性:accountFlag 记账单位
	 * 
	 * @return accountFlag
	 */
	public String getAccountFlag() {
		return accountFlag;
	}

	/**
	 * 设置属性:accountFlag 记账单位
	 * 
	 * @param accountFlag
	 */
	public void setAccountFlag(String accountFlag) {
		this.accountFlag = accountFlag;
	}

	/**
	 * 获取属性:convertFlag 换算单位
	 * 
	 * @return convertFlag
	 */
	public String getConvertFlag() {
		return convertFlag;
	}

	/**
	 * 设置属性:convertFlag 换算单位
	 * 
	 * @param convertFlag
	 */
	public void setConvertFlag(String convertFlag) {
		this.convertFlag = convertFlag;
	}

	/**
	 * 获取属性:status 状态 0：正常 9：禁用
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 设置属性:status 状态 0：正常 9：禁用
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 获取属性:createtime 创建时间
	 * 
	 * @return createtime
	 */
	public Date getCreatetime() {
		return createtime;
	}

	/**
	 * 设置属性:createtime 创建时间
	 * 
	 * @param createtime
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}