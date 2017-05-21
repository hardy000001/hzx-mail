package com.lq.lss.model;

import java.math.BigDecimal;
import java.util.Date;
import com.lq.easyui.model.easyui.EasyUiModel;
                                            
/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-18 09:56:42
 */
public class CBusAccount extends EasyUiModel<String>{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;


    /** id **/
    private String id;
	/** 单据号**/
    private String acSerialno;
	/** 所属中心**/
    private Integer deptid;
	/** 客户信息**/
    private String cuscode;
	/** 合同编号**/
    private String htcode;
	/** 金额**/
    private BigDecimal tradeAmt;
	/** 1收款，2付款**/
    private BigDecimal tradeType;
	/** 状态 0：正常   1：等待复核     2：复核未通过      9 ：作废**/
    private String status;
	/** 操作员**/
    private String createoper;
	/** 操作时间**/
    private Date createtime;
	/** 复合操作员**/
    private String auditingoper;
	/** 复核时间**/
    private Date auditingtime;

    private String beginTime;
    private String endTime;
    private String loginName;
    private String operName;
    private String deptName;
    private String projectName;
   /**
    * 获取属性:id
    * id
    * @return id
    */
   public String getId() {
       return id;
   }
   /**
    * 设置属性:id
    * id
    * @param id
    */
   public void setId(String id) {
       this.id = id;
   }
	
   /**
    * 获取属性:acSerialno
    * 单据号
    * @return acSerialno
    */
   public String getAcSerialno() {
       return acSerialno;
   }
   /**
    * 设置属性:acSerialno
    * 单据号
    * @param acSerialno
    */
   public void setAcSerialno(String acSerialno) {
       this.acSerialno = acSerialno;
   }
	
   /**
    * 获取属性:deptid
    * 所属中心
    * @return deptid
    */
   public Integer getDeptid() {
       return deptid;
   }
   /**
    * 设置属性:deptid
    * 所属中心
    * @param deptid
    */
   public void setDeptid(Integer deptid) {
       this.deptid = deptid;
   }
	
   /**
    * 获取属性:cuscode
    * 客户信息
    * @return cuscode
    */
   public String getCuscode() {
       return cuscode;
   }
   /**
    * 设置属性:cuscode
    * 客户信息
    * @param cuscode
    */
   public void setCuscode(String cuscode) {
       this.cuscode = cuscode;
   }
	
   /**
    * 获取属性:htcode
    * 合同编号
    * @return htcode
    */
   public String getHtcode() {
       return htcode;
   }
   /**
    * 设置属性:htcode
    * 合同编号
    * @param htcode
    */
   public void setHtcode(String htcode) {
       this.htcode = htcode;
   }
	
   /**
    * 获取属性:tradeAmt
    * 金额
    * @return tradeAmt
    */
   public BigDecimal getTradeAmt() {
       return tradeAmt;
   }
   /**
    * 设置属性:tradeAmt
    * 金额
    * @param tradeAmt
    */
   public void setTradeAmt(BigDecimal tradeAmt) {
       this.tradeAmt = tradeAmt;
   }
	
   /**
    * 获取属性:tradeType
    * 1收款，2付款
    * @return tradeType
    */
   public BigDecimal getTradeType() {
       return tradeType;
   }
   /**
    * 设置属性:tradeType
    * 1收款，2付款
    * @param tradeType
    */
   public void setTradeType(BigDecimal tradeType) {
       this.tradeType = tradeType;
   }
	
   /**
    * 获取属性:status
    * 状态 0：正常   1：等待复核     2：复核未通过      9 ：作废
    * @return status
    */
   public String getStatus() {
       return status;
   }
   /**
    * 设置属性:status
    * 状态 0：正常   1：等待复核     2：复核未通过      9 ：作废
    * @param status
    */
   public void setStatus(String status) {
       this.status = status;
   }
	
   /**
    * 获取属性:createoper
    * 操作员
    * @return createoper
    */
   public String getCreateoper() {
       return createoper;
   }
   /**
    * 设置属性:createoper
    * 操作员
    * @param createoper
    */
   public void setCreateoper(String createoper) {
       this.createoper = createoper;
   }
	
   /**
    * 获取属性:createtime
    * 操作时间
    * @return createtime
    */
   public Date getCreatetime() {
       return createtime;
   }
   /**
    * 设置属性:createtime
    * 操作时间
    * @param createtime
    */
   public void setCreatetime(Date createtime) {
       this.createtime = createtime;
   }
	
   /**
    * 获取属性:auditingoper
    * 复合操作员
    * @return auditingoper
    */
   public String getAuditingoper() {
       return auditingoper;
   }
   /**
    * 设置属性:auditingoper
    * 复合操作员
    * @param auditingoper
    */
   public void setAuditingoper(String auditingoper) {
       this.auditingoper = auditingoper;
   }
	
   /**
    * 获取属性:auditingtime
    * 复核时间
    * @return auditingtime
    */
   public Date getAuditingtime() {
       return auditingtime;
   }
   /**
    * 设置属性:auditingtime
    * 复核时间
    * @param auditingtime
    */
   public void setAuditingtime(Date auditingtime) {
       this.auditingtime = auditingtime;
   }
public String getBeginTime() {
	return beginTime;
}
public void setBeginTime(String beginTime) {
	this.beginTime = beginTime;
}
public String getEndTime() {
	return endTime;
}
public void setEndTime(String endTime) {
	this.endTime = endTime;
}
public String getLoginName() {
	return loginName;
}
public void setLoginName(String loginName) {
	this.loginName = loginName;
}
public String getOperName() {
	return operName;
}
public void setOperName(String operName) {
	this.operName = operName;
}
public String getDeptName() {
	return deptName;
}
public void setDeptName(String deptName) {
	this.deptName = deptName;
}
public String getProjectName() {
	return projectName;
}
public void setProjectName(String projectName) {
	this.projectName = projectName;
}

   
   
   
}