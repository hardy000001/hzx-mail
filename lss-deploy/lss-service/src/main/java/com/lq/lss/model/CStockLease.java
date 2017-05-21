package com.lq.lss.model;

import java.math.BigDecimal;
import java.util.Date;

import com.lq.easyui.model.easyui.EasyUiModel;

/**
 *
 * @author 
 * @since 2016-08-31
 */
public class CStockLease extends EasyUiModel<String> {

	/** 序列化ID */
	private static final long serialVersionUID = 1L;

	/** 租赁流水单号 **/
    private String lsSerialno;
	/** 所属中心 **/
    private Integer deptid;
	/** 租赁商户号 **/
    private String mchcode;
	/** 总米数 **/
    private BigDecimal totalM;
	/** 总数量 **/
    private BigDecimal totalS;
	/** 总重量 **/
    private BigDecimal totalT;
	/** 装卸费用 **/
    private BigDecimal zxFee;
	/** 杂费 **/
    private BigDecimal otherFee;
	/** 租赁期限-开始 **/
    private Date lsSdate;
	/** 租赁期限--结束 **/
    private Date lsEdate;
	/** 状态 0: 正常（完成）   1：申请   2：通过，备料中 3：发货  4：对方签收  9 ：作废 **/
    private String status;
	/** 操作员 **/
    private String createoper;
	/** 操作时间 **/
    private Date createtime;
	/** 复合操作员 **/
    private String auditingoper;
	/** 复核时间 **/
    private Date auditingtime;
	/** 运费 **/
    private BigDecimal transportFee;
    private String id;
	
    
    
    private String beginTime;
    private String endTime;
    private String loginName;
    private String operName;
    private String centerName;
    private String mchname;
   /**
    * 获取属性:lsSerialno
    * 租赁流水单号
    * @return lsSerialno
    */
   public String getLsSerialno() {
       return lsSerialno;
   }
   /**
    * 设置属性:lsSerialno
    * 租赁流水单号
    * @param lsSerialno
    */
   public void setLsSerialno(String lsSerialno) {
       this.lsSerialno = lsSerialno;
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
    * 获取属性:mchcode
    * 租赁商户号
    * @return mchcode
    */
   public String getMchcode() {
       return mchcode;
   }
   /**
    * 设置属性:mchcode
    * 租赁商户号
    * @param mchcode
    */
   public void setMchcode(String mchcode) {
       this.mchcode = mchcode;
   }
	
   /**
    * 获取属性:totalM
    * 总米数
    * @return totalM
    */
   public BigDecimal getTotalM() {
       return totalM;
   }
   /**
    * 设置属性:totalM
    * 总米数
    * @param totalM
    */
   public void setTotalM(BigDecimal totalM) {
       this.totalM = totalM;
   }
	
   /**
    * 获取属性:totalS
    * 总数量
    * @return totalS
    */
   public BigDecimal getTotalS() {
       return totalS;
   }
   /**
    * 设置属性:totalS
    * 总数量
    * @param totalS
    */
   public void setTotalS(BigDecimal totalS) {
       this.totalS = totalS;
   }
	
   /**
    * 获取属性:totalT
    * 总重量
    * @return totalT
    */
   public BigDecimal getTotalT() {
       return totalT;
   }
   /**
    * 设置属性:totalT
    * 总重量
    * @param totalT
    */
   public void setTotalT(BigDecimal totalT) {
       this.totalT = totalT;
   }
	
   /**
    * 获取属性:zxFee
    * 装卸费用
    * @return zxFee
    */
   public BigDecimal getZxFee() {
       return zxFee;
   }
   /**
    * 设置属性:zxFee
    * 装卸费用
    * @param zxFee
    */
   public void setZxFee(BigDecimal zxFee) {
       this.zxFee = zxFee;
   }
	
   /**
    * 获取属性:otherFee
    * 杂费
    * @return otherFee
    */
   public BigDecimal getOtherFee() {
       return otherFee;
   }
   /**
    * 设置属性:otherFee
    * 杂费
    * @param otherFee
    */
   public void setOtherFee(BigDecimal otherFee) {
       this.otherFee = otherFee;
   }
	
   /**
    * 获取属性:lsSdate
    * 租赁期限-开始
    * @return lsSdate
    */
   public Date getLsSdate() {
       return lsSdate;
   }
   /**
    * 设置属性:lsSdate
    * 租赁期限-开始
    * @param lsSdate
    */
   public void setLsSdate(Date lsSdate) {
       this.lsSdate = lsSdate;
   }
	
   /**
    * 获取属性:lsEdate
    * 租赁期限--结束
    * @return lsEdate
    */
   public Date getLsEdate() {
       return lsEdate;
   }
   /**
    * 设置属性:lsEdate
    * 租赁期限--结束
    * @param lsEdate
    */
   public void setLsEdate(Date lsEdate) {
       this.lsEdate = lsEdate;
   }
	
   /**
    * 获取属性:status
    * 状态 0: 正常（完成）   1：申请   2：通过，备料中 3：发货  4：对方签收  9 ：作废
    * @return status
    */
   public String getStatus() {
       return status;
   }
   /**
    * 设置属性:status
    * 状态 0: 正常（完成）   1：申请   2：通过，备料中 3：发货  4：对方签收  9 ：作废
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
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public BigDecimal getTransportFee() {
	return transportFee;
}
public void setTransportFee(BigDecimal transportFee) {
	this.transportFee = transportFee;
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
public String getCenterName() {
	return centerName;
}
public void setCenterName(String centerName) {
	this.centerName = centerName;
}
public String getMchname() {
	return mchname;
}
public void setMchname(String mchname) {
	this.mchname = mchname;
}
   
}