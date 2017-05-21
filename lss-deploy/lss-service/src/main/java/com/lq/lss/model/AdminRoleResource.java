package com.lq.lss.model;

import com.lq.easyui.model.easyui.EasyUiModel;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 
 * @since 2016-11-10
 */
public class AdminRoleResource extends EasyUiModel<Integer> {

	/** 序列化ID */
	private static final long serialVersionUID = 1L;

	/** 角色与资源关联id（自增） **/
    private Integer id;
	/** 角色ID **/
    private Long roleId;
	/** 2：资源；3：系统 **/
    private Integer type;
	/** 资源id **/
    private Long resourceId;
	/** 创建时间 **/
    private Date createTime;

	
   /**
    * 获取属性:id
    * 角色与资源关联id（自增）
    * @return id
    */
   public Integer getId() {
       return id;
   }
   /**
    * 设置属性:id
    * 角色与资源关联id（自增）
    * @param id
    */
   public void setId(Integer id) {
       this.id = id;
   }
	
   /**
    * 获取属性:roleId
    * 角色ID
    * @return roleId
    */
   public Long getRoleId() {
       return roleId;
   }
   /**
    * 设置属性:roleId
    * 角色ID
    * @param roleId
    */
   public void setRoleId(Long roleId) {
       this.roleId = roleId;
   }
	
   /**
    * 获取属性:type
    * 2：资源；3：系统
    * @return type
    */
   public Integer getType() {
       return type;
   }
   /**
    * 设置属性:type
    * 2：资源；3：系统
    * @param type
    */
   public void setType(Integer type) {
       this.type = type;
   }
	
   /**
    * 获取属性:resourceId
    * 资源id
    * @return resourceId
    */
   public Long getResourceId() {
       return resourceId;
   }
   /**
    * 设置属性:resourceId
    * 资源id
    * @param resourceId
    */
   public void setResourceId(Long resourceId) {
       this.resourceId = resourceId;
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