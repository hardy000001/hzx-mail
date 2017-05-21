package com.lq.lss.model;

import java.math.BigDecimal;
import java.util.Date;

import com.lq.easyui.model.easyui.EasyUiModel;

/**
 *
 * @author 
 * @since 2016-08-31
 */
public class CBusPur extends EasyUiModel<String>{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;

	/** 采购流水号 **/
    private String purSerialno;
	/** 所属中心 **/
    private Integer deptid;
    /** 供应商户号**/
    private String fromMchcode;
	/** 采购总金额 **/
    private BigDecimal totalAmt;
	/** 备注信息 **/
    private String other;
	/** 状态 0：正常 **/
    private String status;
	/** 操作员 **/
    private String createoper;
	/** 创建时间 **/
    private Date createtime;
	/** 审核人 **/
    private String auditingoper;
	/** 审核时间 **/
    private Date auditingtime;
    
    private String beginTime;
    private String endTime;
    private String loginName;
    private String operName;
    private String centerName;
    private String mchname;
	
   /**
    * 获取属性:purSerialno
    * 采购流水号
    * @return purSerialno
    */
   public String getPurSerialno() {
       return purSerialno;
   }
   /**
    * 设置属性:purSerialno
    * 采购流水号
    * @param purSerialno
    */
   public void setPurSerialno(String purSerialno) {
       this.purSerialno = purSerialno;
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
    * 获取属性:fromMchcode
    * 供应商户号
    * @return fromMchcode
    */
   public String getFromMchcode() {
       return fromMchcode;
   }
   /**
    * 设置属性:fromMchcode
    * 供应商户号
    * @param fromMchcode
    */
   public void setFromMchcode(String fromMchcode) {
       this.fromMchcode = fromMchcode;
   }
   /**
    * 获取属性:totalAmt
    * 采购总金额
    * @return totalAmt
    */
   public BigDecimal getTotalAmt() {
       return totalAmt;
   }
   /**
    * 设置属性:totalAmt
    * 采购总金额
    * @param totalAmt
    */
   public void setTotalAmt(BigDecimal totalAmt) {
       this.totalAmt = totalAmt;
   }
	
   /**
    * 获取属性:other
    * 备注信息
    * @return other
    */
   public String getOther() {
       return other;
   }
   /**
    * 设置属性:other
    * 备注信息
    * @param other
    */
   public void setOther(String other) {
       this.other = other;
   }
	
   /**
    * 获取属性:status
    * 状态 0：正常
    * @return status
    */
   public String getStatus() {
       return status;
   }
   /**
    * 设置属性:status
    * 状态 0：正常
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
	
   /**
    * 获取属性:auditingoper
    * 审核人
    * @return auditingoper
    */
   public String getAuditingoper() {
       return auditingoper;
   }
   /**
    * 设置属性:auditingoper
    * 审核人
    * @param auditingoper
    */
   public void setAuditingoper(String auditingoper) {
       this.auditingoper = auditingoper;
   }
	
   /**
    * 获取属性:auditingtime
    * 审核时间
    * @return auditingtime
    */
   public Date getAuditingtime() {
       return auditingtime;
   }
   /**
    * 设置属性:auditingtime
    * 审核时间
    * @param auditingtime
    */
   public void setAuditingtime(Date auditingtime) {
       this.auditingtime = auditingtime;
   }
	@Override
	public String getId() {
		return null;
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