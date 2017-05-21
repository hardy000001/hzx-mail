package com.lq.lss.model;

import java.math.BigDecimal;
import java.util.Date;
import com.lq.easyui.model.easyui.EasyUiModel;
                                                                    
/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-24 17:06:41
 */
public class SContractMoney extends EasyUiModel<String>{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;


	/** 自增序号**/
    private String id;
	/** 所属租赁中心的ID**/
    private Integer deptid;
	/** 客户编号**/
    private String cuscode;
	/** 合同编号**/
    private String htcode;
	/** 租赁金额**/
    private BigDecimal rentFee;
	/** 维修金额**/
    private BigDecimal repairFee;
	/** 丢赔金额**/
    private BigDecimal redressFee;
	/** 运输费用**/
    private BigDecimal transportFee;
	/** 装卸费用**/
    private BigDecimal zxFee;
	/** 杂费**/
    private BigDecimal otherFee;
	/** 报停金额**/
    private BigDecimal stopFee;
	/** 合计费用**/
    private BigDecimal totalFee;
    
    /** 调拨费用**/
    private BigDecimal transferFee;
    
	/** 应收金额**/
    private BigDecimal dueFee;
	/** 已收金额**/
    private BigDecimal receivedFee;
	/** 结算月份**/
    private String settleMonth;
	/** 操作时间**/
    private Date creattime;

   
    private String cusName;
    private String centerName;
    private String projectName;
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
    * 所属租赁中心的ID
    * @return deptid
    */
   public Integer getDeptid() {
       return deptid;
   }
   /**
    * 设置属性:deptid
    * 所属租赁中心的ID
    * @param deptid
    */
   public void setDeptid(Integer deptid) {
       this.deptid = deptid;
   }
	
   /**
    * 获取属性:cuscode
    * 客户编号
    * @return cuscode
    */
   public String getCuscode() {
       return cuscode;
   }
   /**
    * 设置属性:cuscode
    * 客户编号
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
    * 获取属性:rentFee
    * 租赁金额
    * @return rentFee
    */
   public BigDecimal getRentFee() {
       return rentFee;
   }
   /**
    * 设置属性:rentFee
    * 租赁金额
    * @param rentFee
    */
   public void setRentFee(BigDecimal rentFee) {
       this.rentFee = rentFee;
   }
	
   /**
    * 获取属性:repairFee
    * 维修金额
    * @return repairFee
    */
   public BigDecimal getRepairFee() {
       return repairFee;
   }
   /**
    * 设置属性:repairFee
    * 维修金额
    * @param repairFee
    */
   public void setRepairFee(BigDecimal repairFee) {
       this.repairFee = repairFee;
   }
	
   /**
    * 获取属性:redressFee
    * 丢赔金额
    * @return redressFee
    */
   public BigDecimal getRedressFee() {
       return redressFee;
   }
   /**
    * 设置属性:redressFee
    * 丢赔金额
    * @param redressFee
    */
   public void setRedressFee(BigDecimal redressFee) {
       this.redressFee = redressFee;
   }
	
   /**
    * 获取属性:transportFee
    * 运输费用
    * @return transportFee
    */
   public BigDecimal getTransportFee() {
       return transportFee;
   }
   /**
    * 设置属性:transportFee
    * 运输费用
    * @param transportFee
    */
   public void setTransportFee(BigDecimal transportFee) {
       this.transportFee = transportFee;
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
    * 获取属性:stopFee
    * 报停金额
    * @return stopFee
    */
   public BigDecimal getStopFee() {
       return stopFee;
   }
   /**
    * 设置属性:stopFee
    * 报停金额
    * @param stopFee
    */
   public void setStopFee(BigDecimal stopFee) {
       this.stopFee = stopFee;
   }
	
   /**
    * 获取属性:totalFee
    * 合计费用
    * @return totalFee
    */
   public BigDecimal getTotalFee() {
       return totalFee;
   }
   /**
    * 设置属性:totalFee
    * 合计费用
    * @param totalFee
    */
   public void setTotalFee(BigDecimal totalFee) {
       this.totalFee = totalFee;
   }
	
	
   /**
    * 获取属性:trasnferFee
    * 合计费用
    * @return trasnferFee
    */
   public BigDecimal getTransferFee() {
       return transferFee;
   }
   /**
    * 设置属性:trasnferFee
    * 合计费用
    * @param trasnferFee
    */
   public void setTransferFee(BigDecimal transferFee) {
       this.transferFee = transferFee;
   }
   
   
   /**
    * 获取属性:dueFee
    * 应收金额
    * @return dueFee
    */
   public BigDecimal getDueFee() {
       return dueFee;
   }
   /**
    * 设置属性:dueFee
    * 应收金额
    * @param dueFee
    */
   public void setDueFee(BigDecimal dueFee) {
       this.dueFee = dueFee;
   }
	
   /**
    * 获取属性:receivedFee
    * 已收金额
    * @return receivedFee
    */
   public BigDecimal getReceivedFee() {
       return receivedFee;
   }
   /**
    * 设置属性:receivedFee
    * 已收金额
    * @param receivedFee
    */
   public void setReceivedFee(BigDecimal receivedFee) {
       this.receivedFee = receivedFee;
   }
	
   /**
    * 获取属性:settleMonth
    * 结算月份
    * @return settleMonth
    */
   public String getSettleMonth() {
       return settleMonth;
   }
   /**
    * 设置属性:settleMonth
    * 结算月份
    * @param settleMonth
    */
   public void setSettleMonth(String settleMonth) {
       this.settleMonth = settleMonth;
   }
	
   /**
    * 获取属性:creattime
    * 操作时间
    * @return creattime
    */
   public Date getCreattime() {
       return creattime;
   }
   /**
    * 设置属性:creattime
    * 操作时间
    * @param creattime
    */
   public void setCreattime(Date creattime) {
       this.creattime = creattime;
   }
public String getCusName() {
	return cusName;
}
public void setCusName(String cusName) {
	this.cusName = cusName;
}
public String getCenterName() {
	return centerName;
}
public void setCenterName(String centerName) {
	this.centerName = centerName;
}
public String getProjectName() {
	return projectName;
}
public void setProjectName(String projectName) {
	this.projectName = projectName;
}

   
}