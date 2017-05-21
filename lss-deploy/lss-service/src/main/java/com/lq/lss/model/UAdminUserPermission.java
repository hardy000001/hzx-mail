package com.lq.lss.model;

import java.util.Date;
import com.lq.easyui.model.easyui.EasyUiModel;
                        
/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2017-04-24 17:20:09
 */
public class UAdminUserPermission extends EasyUiModel<String>{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;


	/** 用户与权限关联ID(自增)**/
    private String id;
	/** 权限类型（1：角色；2：资源；3：系统;注意：如果是资源时，要级联保存）**/
    private Integer permissionType;
	/** role_id 或 resource_id 或 system_id**/
    private Long permissionId;
	/** 创建时间**/
    private Date createTime;

   
	
   /**
    * 获取属性:id
    * 用户与权限关联ID(自增)
    * @return id
    */
   public String getId() {
       return id;
   }
   /**
    * 设置属性:id
    * 用户与权限关联ID(自增)
    * @param id
    */
   public void setId(String id) {
       this.id = id;
   }
	
	
   /**
    * 获取属性:permissionType
    * 权限类型（1：角色；2：资源；3：系统;注意：如果是资源时，要级联保存）
    * @return permissionType
    */
   public Integer getPermissionType() {
       return permissionType;
   }
   /**
    * 设置属性:permissionType
    * 权限类型（1：角色；2：资源；3：系统;注意：如果是资源时，要级联保存）
    * @param permissionType
    */
   public void setPermissionType(Integer permissionType) {
       this.permissionType = permissionType;
   }
	
   /**
    * 获取属性:permissionId
    * role_id 或 resource_id 或 system_id
    * @return permissionId
    */
   public Long getPermissionId() {
       return permissionId;
   }
   /**
    * 设置属性:permissionId
    * role_id 或 resource_id 或 system_id
    * @param permissionId
    */
   public void setPermissionId(Long permissionId) {
       this.permissionId = permissionId;
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