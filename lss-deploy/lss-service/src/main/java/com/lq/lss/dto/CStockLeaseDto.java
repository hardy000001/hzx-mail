package com.lq.lss.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.lq.easyui.model.easyui.EasyUiModel;

/**
 *
 * @author 
 * @since 
 */
public class CStockLeaseDto  {

	

	/** 租赁流水单号 **/
    private String lsSerialno;
	/** 所属中心 **/
    private String deptid;
	/** 租赁商户号 **/
    private String mchcode;
	/** 总米数 **/
    private String totalM;
	/** 总数量 **/
    private String totalS;
	/** 总重量 **/
    private String totalT;
	/** 装卸费用 **/
    private String zxFee;
	/** 杂费 **/
    private String otherFee;
	/** 租赁期限-开始 **/
    private String lsSdate;
	/** 租赁期限--结束 **/
    private String lsEdate;
	/** 状态 0: 正常（完成）   1：申请   2：通过，备料中 3：发货  4：对方签收  9 ：作废 **/
    private String status;
	/** 操作员 **/
    private String createoper;
	/** 操作时间 **/
    private String createtime;
	/** 复合操作员 **/
    private String auditingoper;
	/** 复核时间 **/
    private String auditingtime;
	/** 运费 **/
    private String transportFee;
    private String id;
    
    
    List<CStockLeaseDetailDto> cstockleasedetaildto;
    
	public String getLsSerialno() {
		return lsSerialno;
	}
	public void setLsSerialno(String lsSerialno) {
		this.lsSerialno = lsSerialno;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getMchcode() {
		return mchcode;
	}
	public void setMchcode(String mchcode) {
		this.mchcode = mchcode;
	}
	public String getTotalM() {
		return totalM;
	}
	public void setTotalM(String totalM) {
		this.totalM = totalM;
	}
	public String getTotalS() {
		return totalS;
	}
	public void setTotalS(String totalS) {
		this.totalS = totalS;
	}
	public String getTotalT() {
		return totalT;
	}
	public void setTotalT(String totalT) {
		this.totalT = totalT;
	}
	public String getZxFee() {
		return zxFee;
	}
	public void setZxFee(String zxFee) {
		this.zxFee = zxFee;
	}
	public String getOtherFee() {
		return otherFee;
	}
	public void setOtherFee(String otherFee) {
		this.otherFee = otherFee;
	}
	public String getLsSdate() {
		return lsSdate;
	}
	public void setLsSdate(String lsSdate) {
		this.lsSdate = lsSdate;
	}
	public String getLsEdate() {
		return lsEdate;
	}
	public void setLsEdate(String lsEdate) {
		this.lsEdate = lsEdate;
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
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getAuditingoper() {
		return auditingoper;
	}
	public void setAuditingoper(String auditingoper) {
		this.auditingoper = auditingoper;
	}
	public String getAuditingtime() {
		return auditingtime;
	}
	public void setAuditingtime(String auditingtime) {
		this.auditingtime = auditingtime;
	}
	public String getTransportFee() {
		return transportFee;
	}
	public void setTransportFee(String transportFee) {
		this.transportFee = transportFee;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<CStockLeaseDetailDto> getCstockleasedetaildto() {
		return cstockleasedetaildto;
	}
	public void setCstockleasedetaildto(List<CStockLeaseDetailDto> cstockleasedetaildto) {
		this.cstockleasedetaildto = cstockleasedetaildto;
	}
	
   
   
}