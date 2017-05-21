package com.lq.lss.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.lq.easyui.model.easyui.EasyUiModel;

/**
 * @author
 * @since 2016-09-07
 */
public class CStockTransfer extends EasyUiModel<String> {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

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
    private BigDecimal totalM;
    /**
     * 调拨总数量
     **/
    private BigDecimal totalS;
    /**
     * 调拨总重量
     **/
    private BigDecimal totalT;
    /**
     * 装卸费用
     **/
    private BigDecimal zxFee;
    /**
     * 运输费
     **/
    private BigDecimal transportFee;
    /**
     * 杂费
     **/
    private BigDecimal otherFee;
    /**
     * 使用期限-开始
     **/
    private Date tsfSdate;
    /**
     * 使用期限--结束
     **/
    private Date tsfEdate;
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
    private Date createtime;
    /**
     * 复合操作员
     **/
    private String auditingoper;
    /**
     * 复核时间
     **/
    private Date auditingtime;
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

    private Integer traType;
    
    private  List<CStockTransferDetail> cStockTransferDetail;
    /**
     * 获取属性:tsfSerialno
     * 租赁商户内部调拨单号
     *
     * @return tsfSerialno
     */
    public String getTsfSerialno() {
        return tsfSerialno;
    }

    /**
     * 设置属性:tsfSerialno
     * 租赁商户内部调拨单号
     *
     * @param tsfSerialno
     */
    public void setTsfSerialno(String tsfSerialno) {
        this.tsfSerialno = tsfSerialno;
    }

    /**
     * 获取属性:fromDeptid
     * 发起方(申请调拨方)所属中心
     *
     * @return fromDeptid
     */
    public Integer getFromDeptid() {
        return fromDeptid;
    }

    /**
     * 设置属性:fromDeptid
     * 发起方(申请调拨方)所属中心
     *
     * @param fromDeptid
     */
    public void setFromDeptid(Integer fromDeptid) {
        this.fromDeptid = fromDeptid;
    }

    /**
     * 获取属性:fromMchcode
     * 发起方(申请调拨方)商户号
     *
     * @return fromMchcode
     */
    public String getFromMchcode() {
        return fromMchcode;
    }

    /**
     * 设置属性:fromMchcode
     * 发起方(申请调拨方)商户号
     *
     * @param fromMchcode
     */
    public void setFromMchcode(String fromMchcode) {
        this.fromMchcode = fromMchcode;
    }

    /**
     * 获取属性:toDepid
     * 接收方(被调拨方)所属中心
     *
     * @return toDepid
     */
    public Integer getToDeptid() {
        return toDeptid;
    }

    /**
     * 设置属性:toDepid
     * 接收方(被调拨方)所属中心
     *
     * @param toDeptid
     */
    public void setToDeptid(Integer toDeptid) {
        this.toDeptid = toDeptid;
    }

    /**
     * 获取属性:toMchcode
     * 接受方(被调拨方)商户号
     *
     * @return toMchcode
     */
    public String getToMchcode() {
        return toMchcode;
    }

    /**
     * 设置属性:toMchcode
     * 接受方(被调拨方)商户号
     *
     * @param toMchcode
     */
    public void setToMchcode(String toMchcode) {
        this.toMchcode = toMchcode;
    }

    /**
     * 获取属性:totalM
     * 调拨总米数
     *
     * @return totalM
     */
    public BigDecimal getTotalM() {
        return totalM;
    }

    /**
     * 设置属性:totalM
     * 调拨总米数
     *
     * @param totalM
     */
    public void setTotalM(BigDecimal totalM) {
        this.totalM = totalM;
    }

    /**
     * 获取属性:totalS
     * 调拨总数量
     *
     * @return totalS
     */
    public BigDecimal getTotalS() {
        return totalS;
    }

    /**
     * 设置属性:totalS
     * 调拨总数量
     *
     * @param totalS
     */
    public void setTotalS(BigDecimal totalS) {
        this.totalS = totalS;
    }

    /**
     * 获取属性:totalT
     * 调拨总重量
     *
     * @return totalT
     */
    public BigDecimal getTotalT() {
        return totalT;
    }

    /**
     * 设置属性:totalT
     * 调拨总重量
     *
     * @param totalT
     */
    public void setTotalT(BigDecimal totalT) {
        this.totalT = totalT;
    }

    /**
     * 获取属性:zxFee
     * 装卸费用
     *
     * @return zxFee
     */
    public BigDecimal getZxFee() {
        return zxFee;
    }

    /**
     * 设置属性:zxFee
     * 装卸费用
     *
     * @param zxFee
     */
    public void setZxFee(BigDecimal zxFee) {
        this.zxFee = zxFee;
    }

    /**
     * 获取属性:otherFee
     * 杂费
     *
     * @return otherFee
     */
    public BigDecimal getOtherFee() {
        return otherFee;
    }

    /**
     * 设置属性:otherFee
     * 杂费
     *
     * @param otherFee
     */
    public void setOtherFee(BigDecimal otherFee) {
        this.otherFee = otherFee;
    }

    public BigDecimal getTransportFee() {
        return transportFee;
    }

    public void setTransportFee(BigDecimal transportFee) {
        this.transportFee = transportFee;
    }

    /**
     * 获取属性:tsfSdate
     * 使用期限-开始
     *
     * @return tsfSdate
     */
    public Date getTsfSdate() {
        return tsfSdate;
    }

    /**
     * 设置属性:tsfSdate
     * 使用期限-开始
     *
     * @param tsfSdate
     */
    public void setTsfSdate(Date tsfSdate) {
        this.tsfSdate = tsfSdate;
    }

    /**
     * 获取属性:tsfEdate
     * 使用期限--结束
     *
     * @return tsfEdate
     */
    public Date getTsfEdate() {
        return tsfEdate;
    }

    /**
     * 设置属性:tsfEdate
     * 使用期限--结束
     *
     * @param tsfEdate
     */
    public void setTsfEdate(Date tsfEdate) {
        this.tsfEdate = tsfEdate;
    }

    /**
     * 获取属性:status
     * 状态 0：正常   1：发起方申请     2：接受方（被调方）审核通过，待定中心审核   3：中心审核通过，等待出库     9 ：作废
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置属性:status
     * 状态 0：正常   1：发起方申请     2：接受方（被调方）审核通过，待定中心审核   3：中心审核通过，等待出库     9 ：作废
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取属性:others
     * 备注摘要
     *
     * @return others
     */
    public String getOthers() {
        return others;
    }

    /**
     * 设置属性:others
     * 备注摘要
     *
     * @param others
     */
    public void setOthers(String others) {
        this.others = others;
    }

    /**
     * 获取属性:createoper
     * 操作员
     *
     * @return createoper
     */
    public String getCreateoper() {
        return createoper;
    }

    /**
     * 设置属性:createoper
     * 操作员
     *
     * @param createoper
     */
    public void setCreateoper(String createoper) {
        this.createoper = createoper;
    }

    /**
     * 获取属性:createtime
     * 操作时间
     *
     * @return createtime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置属性:createtime
     * 操作时间
     *
     * @param createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取属性:auditingoper
     * 复合操作员
     *
     * @return auditingoper
     */
    public String getAuditingoper() {
        return auditingoper;
    }

    /**
     * 设置属性:auditingoper
     * 复合操作员
     *
     * @param auditingoper
     */
    public void setAuditingoper(String auditingoper) {
        this.auditingoper = auditingoper;
    }

    /**
     * 获取属性:auditingtime
     * 复核时间
     *
     * @return auditingtime
     */
    public Date getAuditingtime() {
        return auditingtime;
    }

    /**
     * 设置属性:auditingtime
     * 复核时间
     *
     * @param auditingtime
     */
    public void setAuditingtime(Date auditingtime) {
        this.auditingtime = auditingtime;
    }

    /**
     * 获取属性:traOutOper
     * 出库经办人
     *
     * @return traOutOper
     */
    public String getTraOutOper() {
        return traOutOper;
    }

    /**
     * 设置属性:traOutOper
     * 出库经办人
     *
     * @param traOutOper
     */
    public void setTraOutOper(String traOutOper) {
        this.traOutOper = traOutOper;
    }

    /**
     * 获取属性:traInOper
     * 入库经办人
     *
     * @return traInOper
     */
    public String getTraInOper() {
        return traInOper;
    }

    /**
     * 设置属性:traInOper
     * 入库经办人
     *
     * @param traInOper
     */
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

	public Integer getTraType() {
		return traType;
	}

	public void setTraType(Integer traType) {
		this.traType = traType;
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

	public List<CStockTransferDetail> getcStockTransferDetail() {
		return cStockTransferDetail;
	}

	public void setcStockTransferDetail(List<CStockTransferDetail> cStockTransferDetail) {
		this.cStockTransferDetail = cStockTransferDetail;
	}
    
	
    
}