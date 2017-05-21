package com.lq.lss.model;

import java.util.Date;
import com.lq.easyui.model.easyui.EasyUiModel;
                        
/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-10-26 11:02:13
 */
public class CBusHtStopflow extends EasyUiModel<String>{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;


	/** id**/
    private String id;
	/** 合同编号**/
    private String htcode;
	/** 报停开始日期**/
    private Date beginDate;
	/** 报停结束日期**/
    private Date endDate;
	/** 报停总天数**/
    private Integer totalDays;
    /** 状态 0：正常   1：发起方申请**/
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
    * 获取属性:beginDate
    * 报停开始日期
    * @return beginDate
    */
   public Date getBeginDate() {
       return beginDate;
   }
   /**
    * 设置属性:beginDate
    * 报停开始日期
    * @param beginDate
    */
   public void setBeginDate(Date beginDate) {
       this.beginDate = beginDate;
   }
	
   /**
    * 获取属性:endDate
    * 报停结束日期
    * @return endDate
    */
   public Date getEndDate() {
       return endDate;
   }
   /**
    * 设置属性:endDate
    * 报停结束日期
    * @param endDate
    */
   public void setEndDate(Date endDate) {
       this.endDate = endDate;
   }
	
   /**
    * 获取属性:totalDays
    * 报停总天数
    * @return totalDays
    */
   public Integer getTotalDays() {
       return totalDays;
   }
   /**
    * 设置属性:totalDays
    * 报停总天数
    * @param totalDays
    */
   public void setTotalDays(Integer totalDays) {
       this.totalDays = totalDays;
   }
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getCreateoper() {
	return createoper;
}
public void setCreateoper(String createoper) {
	this.createoper = createoper;
}
public Date getCreatetime() {
	return createtime;
}
public void setCreatetime(Date createtime) {
	this.createtime = createtime;
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
   
   
   
}