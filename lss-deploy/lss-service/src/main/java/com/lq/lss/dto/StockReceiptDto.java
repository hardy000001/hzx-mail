package com.lq.lss.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-09 12:31:33
 */
public class StockReceiptDto{

	
	/** 中心收料流水号**/
    private String receiptSerialno;
	/** 所属中心**/
    private Integer deptid;
	/** 商户号**/
    private String mchcode;
	/** 合同编号**/
    private String htcode;
	/** 车号**/
    private String carNo;
	/** 司机**/
    private String carDriver;
	/** 承租经办人**/
    private String lessee;
	/** 出租经办人**/
    private String renter;
	/** 装卸费用**/
    private String zxFee;
	/** 运输费用**/
    private String transportFee;
	/** 杂费**/
    private String otherFee;
	/** 维修费**/
    private String repairFee;
	/** 状态 0：正常   1：等待复核     2：复核未通过      9 ：作废**/
    private String status;
	/** 交易汇总信息**/
    private String tradeinfo;
	/** 制单人**/
    private String createoper;
    /** 收料时间**/
    private String receipttime;
	/** 操作时间**/
    private String createtime;
	/** 复合操作员**/
    private String auditingoper;
	/** 复核时间**/
    private String auditingtime;
	/** 备注信息**/
    private String remark;
    /** 扣件是否维修：0 未修 1已修**/
    private String isrepair;
    private String dataList;
    private List<StockReceiptDetailDto> stockReceiptDetailDtos;
    private List<StockReceiptRepairinfoDto> stockReceiptRepairinfoDtos;
    
    private String loginName;
    private String operName;
    private String centerName;
    private String mchname;
    private String projectName;
    
    
    private Date jytime;
   /**
    * 获取属性:receiptSerialno
    * 中心收料流水号
    * @return receiptSerialno
    */
   public String getReceiptSerialno() {
       return receiptSerialno;
   }
   /**
    * 设置属性:receiptSerialno
    * 中心收料流水号
    * @param receiptSerialno
    */
   public void setReceiptSerialno(String receiptSerialno) {
       this.receiptSerialno = receiptSerialno;
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
    * 商户号
    * @return mchcode
    */
   public String getMchcode() {
       return mchcode;
   }
   /**
    * 设置属性:mchcode
    * 商户号
    * @param mchcode
    */
   public void setMchcode(String mchcode) {
       this.mchcode = mchcode;
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
    * 获取属性:carNo
    * 车号
    * @return carNo
    */
   public String getCarNo() {
       return carNo;
   }
   /**
    * 设置属性:carNo
    * 车号
    * @param carNo
    */
   public void setCarNo(String carNo) {
       this.carNo = carNo;
   }
	
   /**
    * 获取属性:carDriver
    * 司机
    * @return carDriver
    */
   public String getCarDriver() {
       return carDriver;
   }
   /**
    * 设置属性:carDriver
    * 司机
    * @param carDriver
    */
   public void setCarDriver(String carDriver) {
       this.carDriver = carDriver;
   }
	
   /**
    * 获取属性:lessee
    * 承租经办人
    * @return lessee
    */
   public String getLessee() {
       return lessee;
   }
   /**
    * 设置属性:lessee
    * 承租经办人
    * @param lessee
    */
   public void setLessee(String lessee) {
       this.lessee = lessee;
   }
	
   /**
    * 获取属性:renter
    * 出租经办人
    * @return renter
    */
   public String getRenter() {
       return renter;
   }
   /**
    * 设置属性:renter
    * 出租经办人
    * @param renter
    */
   public void setRenter(String renter) {
       this.renter = renter;
   }
	
   /**
    * 获取属性:zxFee
    * 装卸费用
    * @return zxFee
    */
   public String getZxFee() {
       return zxFee;
   }
   /**
    * 设置属性:zxFee
    * 装卸费用
    * @param zxFee
    */
   public void setZxFee(String zxFee) {
       this.zxFee = zxFee;
   }
	
   /**
    * 获取属性:transportFee
    * 运输费用
    * @return transportFee
    */
   public String getTransportFee() {
       return transportFee;
   }
   /**
    * 设置属性:transportFee
    * 运输费用
    * @param transportFee
    */
   public void setTransportFee(String transportFee) {
       this.transportFee = transportFee;
   }
	
   /**
    * 获取属性:otherFee
    * 杂费
    * @return otherFee
    */
   public String getOtherFee() {
       return otherFee;
   }
   /**
    * 设置属性:otherFee
    * 杂费
    * @param otherFee
    */
   public void setOtherFee(String otherFee) {
       this.otherFee = otherFee;
   }
	
   /**
    * 获取属性:repairFee
    * 维修费
    * @return repairFee
    */
   public String getRepairFee() {
       return repairFee;
   }
   /**
    * 设置属性:repairFee
    * 维修费
    * @param repairFee
    */
   public void setRepairFee(String repairFee) {
       this.repairFee = repairFee;
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
    * 获取属性:tradeinfo
    * 交易汇总信息
    * @return tradeinfo
    */
   public String getTradeinfo() {
       return tradeinfo;
   }
   /**
    * 设置属性:tradeinfo
    * 交易汇总信息
    * @param tradeinfo
    */
   public void setTradeinfo(String tradeinfo) {
       this.tradeinfo = tradeinfo;
   }
	
   /**
    * 获取属性:createoper
    * 制单人
    * @return createoper
    */
   public String getCreateoper() {
       return createoper;
   }
   /**
    * 设置属性:createoper
    * 制单人
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
   public String getCreatetime() {
       return createtime;
   }
   /**
    * 设置属性:createtime
    * 操作时间
    * @param createtime
    */
   public void setCreatetime(String createtime) {
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
   public String getAuditingtime() {
       return auditingtime;
   }
   /**
    * 设置属性:auditingtime
    * 复核时间
    * @param auditingtime
    */
   public void setAuditingtime(String auditingtime) {
       this.auditingtime = auditingtime;
   }
	
   /**
    * 获取属性:remark
    * 备注信息
    * @return remark
    */
   public String getRemark() {
       return remark;
   }
   /**
    * 设置属性:remark
    * 备注信息
    * @param remark
    */
   public void setRemark(String remark) {
       this.remark = remark;
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
	public String getDataList() {
		return dataList;
	}
	public void setDataList(String dataList) {
		this.dataList = dataList;
	}
	public List<StockReceiptDetailDto> getStockReceiptDetailDtos() {
		return stockReceiptDetailDtos;
	}
	public void setStockReceiptDetailDtos(
			List<StockReceiptDetailDto> stockReceiptDetailDtos) {
		this.stockReceiptDetailDtos = stockReceiptDetailDtos;
	}
	public List<StockReceiptRepairinfoDto> getStockReceiptRepairinfoDtos() {
		return stockReceiptRepairinfoDtos;
	}
	public void setStockReceiptRepairinfoDtos(
			List<StockReceiptRepairinfoDto> stockReceiptRepairinfoDtos) {
		this.stockReceiptRepairinfoDtos = stockReceiptRepairinfoDtos;
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
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getReceipttime() {
		return receipttime;
	}
	public void setReceipttime(String receipttime) {
		this.receipttime = receipttime;
	}
	public Date getJytime() {
		return jytime;
	}
	public void setJytime(Date jytime) {
		this.jytime = jytime;
	}
	
	
    
}