package com.lq.lss.model;


import java.math.BigDecimal;
import java.util.Date;

import com.lq.easyui.model.easyui.EasyUiModel;

/**
 *
 * @author 
 * @since 2016-08-31
 */
public class CStockRemodeling extends EasyUiModel<String>{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;

	/** 自增序号 **/
    private String id;
    /** 流水号 **/
    private String remSerialNo;
	/** 所属中心 **/
    private Integer deptid;
	/** 所属租赁公司 **/
    private String mchcode;
	/** 总数量 **/
    private BigDecimal totalS;
	/** 总米数 **/
    private BigDecimal totalM;
	/** 总重量 **/
    private BigDecimal totalT;
	/** total_amt **/
    private BigDecimal totalAmt;
    /** 状态 **/
    private String status;
	/** 操作员 **/
    private String createoper;
	/** 创建时间 **/
    private Date createtime;
    /** 复合操作员**/
    private String auditingoper;
	/** 复核时间**/
    private Date auditingtime;

    private String beginTime;
    private String endTime;
    private String loginName;
    private String operName;
    private String centerName;
    private String mchname;
   /**
    * 获取属性:id
    * 自增序号
    * @return id
    */
   public String getId() {
       return id;
   }
   /**
    * 设置属性:id
    * 自增序号
    * @param id
    */
   public void setId(String id) {
       this.id = id;
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
    * 所属租赁公司
    * @return mchcode
    */
   public String getMchcode() {
       return mchcode;
   }
   /**
    * 设置属性:mchcode
    * 所属租赁公司
    * @param mchcode
    */
   public void setMchcode(String mchcode) {
       this.mchcode = mchcode;
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
    * 获取属性:totalAmt
    * total_amt
    * @return totalAmt
    */
   public BigDecimal getTotalAmt() {
       return totalAmt;
   }
   /**
    * 设置属性:totalAmt
    * total_amt
    * @param totalAmt
    */
   public void setTotalAmt(BigDecimal totalAmt) {
       this.totalAmt = totalAmt;
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
    * 创建时间
    * @return createtime
    */
   public Date getCreatetime() {
       return createtime;
   }
   /**
    * 设置属性:createtime
    * 创建时间
    * @param createtime
    */
   public void setCreatetime(Date createtime) {
       this.createtime = createtime;
   }
	
   public String getRemSerialNo() {
		return remSerialNo;
	}
	
   public void setRemSerialNo(String remSerialNo) {
		this.remSerialNo = remSerialNo;
	}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getAuditingoper() {
	return auditingoper;
}
public void setAuditingoper(String auditingoper) {
	this.auditingoper = auditingoper;
}
public Date getAuditingtime() {
	return auditingtime;
}
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