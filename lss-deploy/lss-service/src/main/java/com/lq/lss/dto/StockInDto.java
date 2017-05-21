package com.lq.lss.dto;

import java.util.Date;

/**
 * 
 * @author  作者: hzx
 * @date 创建时间: 2016-12-26下午3:34:55
 */
public class StockInDto{

	
	/** 中心入库单流水号**/
    private String inSerialno;
	/** 所属中心**/
    private Integer deptid;
	/** 所属租赁商户**/
    private String mchcode;
    /** 入库经办人**/
    private String outOperator;
	/** 入库经办人**/
    private String inOperator;
	/** 入库总米数**/
    private String totalM;
	/** 入库总数量**/
    private String totalS;
	/** 入库总重量**/
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
    private String transportFee;
    private String dataList;
   
    private Long userId;
    
    private String loginName;
    private String mchName;
    private String deptName;
    
    /** 交易时间 **/
    private String transDate;
    /** 车号**/
    private String carNo;
	/** 司机**/
    private String carDriver;
    /** 扣件是否维修：0 未修 1已修**/
    private String isrepair;
    private Date jytime;
	
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
    * 入库经办人
    * @return outOperator
    */
   public String getOutOperator() {
       return outOperator;
   }
   /**
    * 设置属性:outOperator
    * 入库经办人
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
    * 入库总米数
    * @return totalM
    */
   public String getTotalM() {
       return totalM;
   }
   /**
    * 设置属性:totalM
    * 入库总米数
    * @param totalM
    */
   public void setTotalM(String totalM) {
       this.totalM = totalM;
   }
	
   /**
    * 获取属性:totalS
    * 入库总数量
    * @return totalS
    */
   public String getTotalS() {
       return totalS;
   }
   /**
    * 设置属性:totalS
    * 入库总数量
    * @param totalS
    */
   public void setTotalS(String totalS) {
       this.totalS = totalS;
   }
	
   /**
    * 获取属性:totalT
    * 入库总重量
    * @return totalT
    */
   public String getTotalT() {
       return totalT;
   }
   /**
    * 设置属性:totalT
    * 入库总重量
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
	public String getMchName() {
		return mchName;
	}
	public void setMchName(String mchName) {
		this.mchName = mchName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Date getJytime() {
		return jytime;
	}
	public void setJytime(Date jytime) {
		this.jytime = jytime;
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
	public String getIsrepair() {
		return isrepair;
	}
	public void setIsrepair(String isrepair) {
		this.isrepair = isrepair;
	}
	
	
    
}