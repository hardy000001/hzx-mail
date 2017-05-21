package com.lq.lss.model;


import java.math.BigDecimal;
import java.util.Date;

import com.lq.easyui.model.easyui.EasyUiModel;

/**
 *
 * @author 
 * @since 2016-08-31
 */
public class CStockIn extends EasyUiModel<String>{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;

	/** 中心入库单流水号 **/
    private String inSerialno;
	/** 所属中心 **/
    private Integer deptid;
    /** 所属租赁商户 **/
    private String mchcode;
    /** 商户经办人**/
    private String outOperator;
	/** 入库经办人**/
    private String inOperator;
    /** 所属租赁中心名称 **/
    private String deptName;
	/** 所属租赁商户名称 **/
    private String mchName;
    
	/** 入库总米数 **/
    private BigDecimal totalM;
	/** total_s **/
    private BigDecimal totalS;
	/** 入库总重量 **/
    private BigDecimal totalT;
	/** 装卸费用 **/
    private BigDecimal zxFee;
	/** 杂费 **/
    private BigDecimal otherFee;
	/** 运输费 **/
    private BigDecimal transportFee;
    
    
	/** 状态 0：正常   1：等待复核     2：复核未通过      9 ：作废 **/
    private String status;
	/** 操作员 **/
    private String createoper;
    private String operId;
	/** 操作时间 **/
    private Date createtime;
	/** 复合操作员 **/
    private String auditingoper;
	/** 复核时间 **/
    private Date auditingtime;
    
    private String id;

    private String cusType;
    
    private String remark;
    /** 交易时间 **/
    private Date transDate;
    /** 车号**/
    private String carNo;
	/** 司机**/
    private String carDriver;
    /** 扣件是否维修：0 未修 1已修**/
    private String isrepair;
   /**
    * 获取属性:inSerialno
    * 中心入库单流水号
    * @return inSerialno
    */
   public String getInSerialno() {
       return inSerialno;
   }
   /**
    * 设置属性:inSerialno
    * 中心入库单流水号
    * @param inSerialno
    */
   public void setInSerialno(String inSerialno) {
       this.inSerialno = inSerialno;
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
    * 获取属性:deptName
    * 所属租赁中心名称
    * @return deptName
    */
   public String getDeptName() {
       return deptName;
   }
   /**
    * 设置属性:deptName
    * 所属中心名称
    * @param deptName
    */
   public void setDeptName(String deptName) {
       this.deptName = deptName;
   }
   
   /**
    * 获取属性:outOperator
    * 出库经办人
    * @return outOperator
    */
   public String getOutOperator() {
       return outOperator;
   }
   /**
    * 设置属性:outOperator
    * 出库经办人
    * @param outOperator
    */
   public void setOutOperator(String outOperator) {
       this.outOperator = outOperator;
   }
   /**
    * 获取属性:inOperator
    * 入库经办人
    * @return inOperator
    */
   public String getInOperator() {
       return inOperator;
   }
   /**
    * 设置属性:inOperator
    * 入库经办人
    * @param inOperator
    */
   public void setInOperator(String inOperator) {
       this.inOperator = inOperator;
   }
	
   /**
    * 获取属性:mchcode
    * 所属租赁商户
    * @return mchcode
    */
   public String getMchcode() {
       return mchcode;
   }
   /**
    * 设置属性:mchcode
    * 所属租赁商户
    * @param mchcode
    */
   public void setMchName(String mchName) {
       this.mchName = mchName;
   }
   
	
   /**
    * 获取属性:mchcode
    * 所属租赁商户
    * @return mchcode
    */
   public String getMchName() {
       return mchName;
   }
   /**
    * 设置属性:mchcode
    * 所属租赁商户
    * @param mchcode
    */
   public void setMchcode(String mchcode) {
       this.mchcode = mchcode;
   }
	
   /**
    * 获取属性:totalM
    * 入库总米数
    * @return totalM
    */
   public BigDecimal getTotalM() {
       return totalM;
   }
   /**
    * 设置属性:totalM
    * 入库总米数
    * @param totalM
    */
   public void setTotalM(BigDecimal totalM) {
       this.totalM = totalM;
   }
	
   /**
    * 获取属性:totalS
    * total_s
    * @return totalS
    */
   public BigDecimal getTotalS() {
       return totalS;
   }
   /**
    * 设置属性:totalS
    * total_s
    * @param totalS
    */
   public void setTotalS(BigDecimal totalS) {
       this.totalS = totalS;
   }
	
   /**
    * 获取属性:totalT
    * 入库总重量
    * @return totalT
    */
   public BigDecimal getTotalT() {
       return totalT;
   }
   /**
    * 设置属性:totalT
    * 入库总重量
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
    * 获取属性:transportFee
    * 运输费
    * @return transportFee
    */
   public BigDecimal getTransportFee() {
       return transportFee;
   }
   /**
    * 设置属性:transportFee
    * 运输费
    * @param transportFee
    */
   public void setTransportFee(BigDecimal transportFee) {
       this.transportFee = transportFee;
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
   
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCusType() {
		return cusType;
	}
	public void setCusType(String cusType) {
		this.cusType = cusType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getTransDate() {
		return transDate;
	}
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getCarDriver() {
		return carDriver;
	}
	public void setCarDriver(String carDriver) {
		this.carDriver = carDriver;
	}
	
	  /**
	    * 获取属性:isrepair
	    * 扣件是否维修：0 未修 1已修
	    * @return isrepair
	    */
	   public String getIsrepair() {
	       return isrepair;
	   }
	   /**
	    * 设置属性:isrepair
	    * 扣件是否维修：0 未修 1已修
	    * @param isrepair
	    */
	   public void setIsrepair(String isrepair) {
	       this.isrepair = isrepair;
	   }
	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	
}