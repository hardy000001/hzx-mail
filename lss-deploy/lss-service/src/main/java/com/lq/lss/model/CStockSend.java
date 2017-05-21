package com.lq.lss.model;

import java.math.BigDecimal;
import java.util.Date;
import com.lq.easyui.model.easyui.EasyUiModel;
                                                                        
/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-09 12:31:33
 */
public class CStockSend extends EasyUiModel<String>{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;


    /** id **/
    private String id;
	/** 中心发料流水号**/
    private String sendSerialno;
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
    private BigDecimal zxFee;
	/** 运输费用**/
    private BigDecimal transportFee;
	/** 杂费**/
    private BigDecimal otherFee;
	/** 状态 0：正常   1：等待复核     2：复核未通过      9 ：作废**/
    private String status;
	/** 交易汇总信息**/
    private String tradeinfo;
    /** 发送时间**/
    private Date sendtime;
	/** 制单人**/
    private String createoper;
	/** 操作时间**/
    private Date createtime;
	/** 复合操作员**/
    private String auditingoper;
	/** 复核时间**/
    private Date auditingtime;
	/** 备注信息**/
    private String remark;

    private String beginTime;
    private String endTime;
    private String loginName;
    private String operName;
    private String centerName;
    private String mchcname;
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
    * 获取属性:sendSerialno
    * 中心发料流水号
    * @return sendSerialno
    */
   public String getSendSerialno() {
       return sendSerialno;
   }
   /**
    * 设置属性:sendSerialno
    * 中心发料流水号
    * @param sendSerialno
    */
   public void setSendSerialno(String sendSerialno) {
       this.sendSerialno = sendSerialno;
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
public Date getSendtime() {
	return sendtime;
}
public void setSendtime(Date sendtime) {
	this.sendtime = sendtime;
}
public String getMchcname() {
	return mchcname;
}
public void setMchcname(String mchcname) {
	this.mchcname = mchcname;
}
public String getProjectName() {
	return projectName;
}
public void setProjectName(String projectName) {
	this.projectName = projectName;
}

   
   
   

}