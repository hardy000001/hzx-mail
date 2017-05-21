package com.lq.lss.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CStockRemodelingDto {
	private String remSerialNo;
	/** 所属中心 **/
    private Integer deptid;
	/** 所属租赁公司 **/
    private String mchcode;
    /** 总数量 **/
    private String totalS;
	/** 总米数 **/
    private String totalM;
	/** 总重量 **/
    private String totalT;
	/** 创建时间 **/
    private String createtime;
    /** 复合操作员**/
    private String auditingoper;
	/** 复核时间**/
    private Date auditingtime;
    /** 状态 **/
    private String status;
    private Long userId;
    private String beginTime;
    private String endTime;
    private String loginName;
    private String operName;
    private String centerName;
    private String mchname;
    //加工改制从表
    private List<CStockRemodelingDetailDto> cstockRemodelingDetailDto;
	public String getRemSerialNo() {
		return remSerialNo;
	}
	public void setRemSerialNo(String remSerialNo) {
		this.remSerialNo = remSerialNo;
	}
	public Integer getDeptid() {
		return deptid;
	}
	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}
	public String getMchcode() {
		return mchcode;
	}
	public void setMchcode(String mchcode) {
		this.mchcode = mchcode;
	}
	public String getTotalS() {
		return totalS;
	}
	public void setTotalS(String totalS) {
		this.totalS = totalS;
	}
	public String getTotalM() {
		return totalM;
	}
	public void setTotalM(String totalM) {
		this.totalM = totalM;
	}
	public String getTotalT() {
		return totalT;
	}
	public void setTotalT(String totalT) {
		this.totalT = totalT;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public List<CStockRemodelingDetailDto> getCstockRemodelingDetailDto() {
		return cstockRemodelingDetailDto;
	}
	public void setCstockRemodelingDetailDto(List<CStockRemodelingDetailDto> cstockRemodelingDetailDto) {
		this.cstockRemodelingDetailDto = cstockRemodelingDetailDto;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
