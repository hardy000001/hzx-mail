package com.lq.lss.model;

import com.lq.easyui.model.easyui.EasyUiModel;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 
 * @since 2016-11-10
 */
public class AdminRole extends EasyUiModel<Integer> {

	/** 序列化ID */
	private static final long serialVersionUID = 1L;

	/** 角色ID（手动维护） **/
    private Integer id;
	/** 角色编号（大写字母，下划线，数字组成，不能以数字打头），不能修改 **/
    private String code;
	/** 角色名称 **/
    private String name;
	/** 关联用户数 **/
    private Integer userCnt;
	/** 备注 **/
    private String remark;
	/** 创建时间 **/
    private Date createTime;

	
   /**
    * 获取属性:id
    * 角色ID（手动维护）
    * @return id
    */
   public Integer getId() {
       return id;
   }
   /**
    * 设置属性:id
    * 角色ID（手动维护）
    * @param id
    */
   public void setId(Integer id) {
       this.id = id;
   }
	
   /**
    * 获取属性:code
    * 角色编号（大写字母，下划线，数字组成，不能以数字打头），不能修改
    * @return code
    */
   public String getCode() {
       return code;
   }
   /**
    * 设置属性:code
    * 角色编号（大写字母，下划线，数字组成，不能以数字打头），不能修改
    * @param code
    */
   public void setCode(String code) {
       this.code = code;
   }
	
   /**
    * 获取属性:name
    * 角色名称
    * @return name
    */
   public String getName() {
       return name;
   }
   /**
    * 设置属性:name
    * 角色名称
    * @param name
    */
   public void setName(String name) {
       this.name = name;
   }
	
   /**
    * 获取属性:userCnt
    * 关联用户数
    * @return userCnt
    */
   public Integer getUserCnt() {
       return userCnt;
   }
   /**
    * 设置属性:userCnt
    * 关联用户数
    * @param userCnt
    */
   public void setUserCnt(Integer userCnt) {
       this.userCnt = userCnt;
   }
	
   /**
    * 获取属性:remark
    * 备注
    * @return remark
    */
   public String getRemark() {
       return remark;
   }
   /**
    * 设置属性:remark
    * 备注
    * @param remark
    */
   public void setRemark(String remark) {
       this.remark = remark;
   }
	
   /**
    * 获取属性:createTime
    * 创建时间
    * @return createTime
    */
   public Date getCreateTime() {
       return createTime;
   }
   /**
    * 设置属性:createTime
    * 创建时间
    * @param createTime
    */
   public void setCreateTime(Date createTime) {
       this.createTime = createTime;
   }

}