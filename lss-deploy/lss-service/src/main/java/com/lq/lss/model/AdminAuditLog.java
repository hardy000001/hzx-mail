package com.lq.lss.model;

import com.lq.easyui.model.easyui.EasyUiModel;

import java.util.Date;

/**
 *
 * @author 
 * @since 2016-10-28
 */
public class AdminAuditLog extends EasyUiModel<Long> {

	/** 序列化ID */
	private static final long serialVersionUID = 1L;

	/** 主键 **/
    private Long id;
	/** 分组标识(有时候，一个事件，分多条记录存储) **/
    private Long groupId;
	/** 引用ID(用来关联其它表) **/
    private Long refId;
	/** 操作用户ID **/
    private Long operatorId;
	/** 操作时间 **/
    private Date createTime;
	/** 操作类型 **/
    private Long operateType;
	/** 操作描述 **/
    private String operateDesc;
	/** 主要参数json格式 **/
    private String keyParas;
	/** 预留字段 **/
    private String field1;
	/** 预留字段 **/
    private String field2;

    private String name;
	
   /**
    * 获取属性:id
    * 主键
    * @return id
    */
   public Long getId() {
       return id;
   }
   /**
    * 设置属性:id
    * 主键
    * @param id
    */
   public void setId(Long id) {
       this.id = id;
   }
	
   /**
    * 获取属性:groupId
    * 分组标识(有时候，一个事件，分多条记录存储)
    * @return groupId
    */
   public Long getGroupId() {
       return groupId;
   }
   /**
    * 设置属性:groupId
    * 分组标识(有时候，一个事件，分多条记录存储)
    * @param groupId
    */
   public void setGroupId(Long groupId) {
       this.groupId = groupId;
   }
	
   /**
    * 获取属性:refId
    * 引用ID(用来关联其它表)
    * @return refId
    */
   public Long getRefId() {
       return refId;
   }
   /**
    * 设置属性:refId
    * 引用ID(用来关联其它表)
    * @param refId
    */
   public void setRefId(Long refId) {
       this.refId = refId;
   }
	
   /**
    * 获取属性:operatorId
    * 操作用户ID
    * @return operatorId
    */
   public Long getOperatorId() {
       return operatorId;
   }
   /**
    * 设置属性:operatorId
    * 操作用户ID
    * @param operatorId
    */
   public void setOperatorId(Long operatorId) {
       this.operatorId = operatorId;
   }
	
   /**
    * 获取属性:createTime
    * 操作时间
    * @return createTime
    */
   public Date getCreateTime() {
       return createTime;
   }
   /**
    * 设置属性:createTime
    * 操作时间
    * @param createTime
    */
   public void setCreateTime(Date createTime) {
       this.createTime = createTime;
   }
	
   /**
    * 获取属性:operateType
    * 操作类型
    * @return operateType
    */
   public Long getOperateType() {
       return operateType;
   }
   /**
    * 设置属性:operateType
    * 操作类型
    * @param operateType
    */
   public void setOperateType(Long operateType) {
       this.operateType = operateType;
   }
	
   /**
    * 获取属性:operateDesc
    * 操作描述
    * @return operateDesc
    */
   public String getOperateDesc() {
       return operateDesc;
   }
   /**
    * 设置属性:operateDesc
    * 操作描述
    * @param operateDesc
    */
   public void setOperateDesc(String operateDesc) {
       this.operateDesc = operateDesc;
   }
	
   /**
    * 获取属性:keyParas
    * 主要参数json格式
    * @return keyParas
    */
   public String getKeyParas() {
       return keyParas;
   }
   /**
    * 设置属性:keyParas
    * 主要参数json格式
    * @param keyParas
    */
   public void setKeyParas(String keyParas) {
       this.keyParas = keyParas;
   }
	
   /**
    * 获取属性:field1
    * 预留字段
    * @return field1
    */
   public String getField1() {
       return field1;
   }
   /**
    * 设置属性:field1
    * 预留字段
    * @param field1
    */
   public void setField1(String field1) {
       this.field1 = field1;
   }
	
   /**
    * 获取属性:field2
    * 预留字段
    * @return field2
    */
   public String getField2() {
       return field2;
   }
   /**
    * 设置属性:field2
    * 预留字段
    * @param field2
    */
   public void setField2(String field2) {
       this.field2 = field2;
   }
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
   
   

}