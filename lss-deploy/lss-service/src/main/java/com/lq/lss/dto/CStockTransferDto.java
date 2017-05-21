package com.lq.lss.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Administrator
 *
 */
public class CStockTransferDto {

	 /**
     * 租赁商户内部调拨单号
     **/
    private String tsfSerialno;
    /**
     * 发起方(申请调拨方)所属中心
     **/
    private Integer fromDeptid;
    /**
     * 发起方(申请调拨方)商户号
     **/
    private String fromMchcode;
    /**
     * 接收方(被调拨方)所属中心
     **/
    private Integer toDeptid;
    /**
     * 接受方(被调拨方)商户号
     **/
    private String toMchcode;
    /**
     * 调拨总米数
     **/
    private String totalM;
    /**
     * 调拨总数量
     **/
    private String totalS;
    /**
     * 调拨总重量
     **/
    private String totalT;
    /**
     * 装卸费用
     **/
    private String zxFee;
    /**
     * 运输费
     **/
    private String transportFee;
    /**
     * 杂费
     **/
    private String otherFee;
    /**
     * 使用期限-开始
     **/
    private String tsfSdate;
    /**
     * 使用期限--结束
     **/
    private String tsfEdate;
    /**
     * 状态 0：正常   1：发起方申请     2：接受方（被调方）审核通过，待定中心审核   3：中心审核通过，等待出库     9 ：作废
     **/
    private String status;
    /**
     * 备注摘要
     **/
    private String others;
    /**
     * 操作员
     **/
    private String createoper;
    /**
     * 操作时间
     **/
    private String createtime;
    /**
     * 复合操作员
     **/
    private String auditingoper;
    /**
     * 复核时间
     **/
    private String auditingtime;
    /**
     * 出库经办人
     **/
    private String traOutOper;
    /**
     * 入库经办人
     **/
    private String traInOper;
    private String id;

    private String fromMchName;
    private String toMchName;
    private String fromDeptName;
    private String toDeptName;
    private String loginName;
    private String operName;
    
    private Date jytime;

	public String getTsfSerialno() {
		return tsfSerialno;
	}

	public void setTsfSerialno(String tsfSerialno) {
		this.tsfSerialno = tsfSerialno;
	}

	public Integer getFromDeptid() {
		return fromDeptid;
	}

	public void setFromDeptid(Integer fromDeptid) {
		this.fromDeptid = fromDeptid;
	}

	public String getFromMchcode() {
		return fromMchcode;
	}

	public void setFromMchcode(String fromMchcode) {
		this.fromMchcode = fromMchcode;
	}

	public Integer getToDeptid() {
		return toDeptid;
	}

	public void setToDeptid(Integer toDeptid) {
		this.toDeptid = toDeptid;
	}

	public String getToMchcode() {
		return toMchcode;
	}

	public void setToMchcode(String toMchcode) {
		this.toMchcode = toMchcode;
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

	public String getTransportFee() {
		return transportFee;
	}

	public void setTransportFee(String transportFee) {
		this.transportFee = transportFee;
	}

	public String getOtherFee() {
		return otherFee;
	}

	public void setOtherFee(String otherFee) {
		this.otherFee = otherFee;
	}

	public String getTsfSdate() {
		return tsfSdate;
	}

	public void setTsfSdate(String tsfSdate) {
		this.tsfSdate = tsfSdate;
	}

	public String getTsfEdate() {
		return tsfEdate;
	}

	public void setTsfEdate(String tsfEdate) {
		this.tsfEdate = tsfEdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
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

	public String getTraOutOper() {
		return traOutOper;
	}

	public void setTraOutOper(String traOutOper) {
		this.traOutOper = traOutOper;
	}

	public String getTraInOper() {
		return traInOper;
	}

	public void setTraInOper(String traInOper) {
		this.traInOper = traInOper;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFromMchName() {
		return fromMchName;
	}

	public void setFromMchName(String fromMchName) {
		this.fromMchName = fromMchName;
	}

	public String getToMchName() {
		return toMchName;
	}

	public void setToMchName(String toMchName) {
		this.toMchName = toMchName;
	}

	public String getFromDeptName() {
		return fromDeptName;
	}

	public void setFromDeptName(String fromDeptName) {
		this.fromDeptName = fromDeptName;
	}

	public String getToDeptName() {
		return toDeptName;
	}

	public void setToDeptName(String toDeptName) {
		this.toDeptName = toDeptName;
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

	public Date getJytime() {
		return jytime;
	}

	public void setJytime(Date jytime) {
		this.jytime = jytime;
	}
    
    
    
    
}
