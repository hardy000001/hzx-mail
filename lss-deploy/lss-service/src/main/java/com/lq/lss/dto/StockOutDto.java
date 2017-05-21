package com.lq.lss.dto;

import java.util.Date;
import java.util.List;


/**
 *
 * @author  作者: hzx
 * @String 创建时间: 2016-10-08 09:26:28
 */
public class StockOutDto{

	
	/** 中心出库单流水号**/
    private String outSerialno;
	/** 所属中心**/
    private Integer deptid;
	/** 所属租赁商户**/
    private String mchcode;
    /** 出库经办人**/
    private String outOperator;
	/** 入库经办人**/
    private String inOperator;
	/** 出库总米数**/
    private String totalM;
	/** 出库总数量**/
    private String totalS;
	/** 出库总重量**/
    private String totalT;
	/** 装卸费用**/
    private String zxFee;
	/** 杂费**/
    private String otherFee;
	/** 状态 0：正常   1：等待复核     2：复核未通过      9 ：作废**/
    private String status;
	/** 操作员**/
    private String createoper;
	/** 操作时间**/
    private String createtime;
	/** 复合操作员**/
    private String auditingoper;
    /** 备注信息**/
    private String remark;
    /** 交易时间 **/
    private String transDate;
    /** 车号**/
    private String carNo;
	/** 司机**/
    private String carDriver;
    /** 相关流水号**/
    private String relSerialno;
    /** 是否自动 **/
    private String auto;  
    private String transportFee;
    private String dataList;
    private List<StockOutDetailDto> stockOutDetailDtos;
    private Long userId;
    
    private String loginName;
    private String mchname;
    private String centerName;
	
    private Date jytime;
   /**
    * 获取属性:outSerialno
    * 中心出库单流水号
    * @return outSerialno
    */
   public String getOutSerialno() {
       return outSerialno;
   }
   /**
    * 设置属性:outSerialno
    * 中心出库单流水号
    * @param outSerialno
    */
   public void setOutSerialno(String outSerialno) {
       this.outSerialno = outSerialno;
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
   public void setMchcode(String mchcode) {
       this.mchcode = mchcode;
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
    * 获取属性:totalM
    * 出库总米数
    * @return totalM
    */
   public String getTotalM() {
       return totalM;
   }
   /**
    * 设置属性:totalM
    * 出库总米数
    * @param totalM
    */
   public void setTotalM(String totalM) {
       this.totalM = totalM;
   }
	
   /**
    * 获取属性:totalS
    * 出库总数量
    * @return totalS
    */
   public String getTotalS() {
       return totalS;
   }
   /**
    * 设置属性:totalS
    * 出库总数量
    * @param totalS
    */
   public void setTotalS(String totalS) {
       this.totalS = totalS;
   }
	
   /**
    * 获取属性:totalT
    * 出库总重量
    * @return totalT
    */
   public String getTotalT() {
       return totalT;
   }
   /**
    * 设置属性:totalT
    * 出库总重量
    * @param totalT
    */
   public void setTotalT(String totalT) {
       this.totalT = totalT;
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
    
	public String getDataList() {
	return dataList;
    }
	public void setDataList(String dataList) {
		this.dataList = dataList;
	}
	public List<StockOutDetailDto> getStockOutDetailDtos() {
		return stockOutDetailDtos;
	}
	public void setStockOutDetailDtos(List<StockOutDetailDto> stockOutDetailDtos) {
		this.stockOutDetailDtos = stockOutDetailDtos;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getTransportFee() {
		return transportFee;
	}
	public void setTransportFee(String transportFee) {
		this.transportFee = transportFee;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getMchname() {
		return mchname;
	}
	public void setMchname(String mchname) {
		this.mchname = mchname;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
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
	public Date getJytime() {
		return jytime;
	}
	public void setJytime(Date jytime) {
		this.jytime = jytime;
	}
	/**
	    * 获取属性:relSerialno
	    * 相关流水号
	    * @return relSerialno
	    */
	   public String getRelSerialno() {
	       return relSerialno;
	   }
	   /**
	    * 设置属性:relSerialno
	    * 相关流水号
	    * @param relSerialno
	    */
	   public void setRelSerialno(String relSerialno) {
	       this.relSerialno = relSerialno;
	   }
	public String getAuto() {
		return auto;
	}
	public void setAuto(String auto) {
		this.auto = auto;
	}
	
}