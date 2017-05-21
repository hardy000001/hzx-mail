package com.lq.lss.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CStockCenterTransferDto {
		 /** id **/
	    private String id;
		/** 租赁商户内部调拨单号**/
		private String tsfSerialno;
		/** 发起方(申请调拨方)所属中心**/
	    private String fromDeptid;
		/** 发起方(申请调拨方)商户号**/
	    private String fromMchcode;
		/** 接收方(被调拨方)所属中心**/
	    private String toDepid;
		/** 接受方(被调拨方)商户号**/
	    private String toMchcode;
		/** 装卸费用**/
	    private String zxFee;
		/** 运输费用**/
	    private String transportFee;
		/** 杂费**/
	    private String otherFee;
		/** 使用期限-开始**/
	    private String tsfSdate;
		/** 使用期限--结束**/
	    private String tsfEdate;
		/** 状态 0：正常   1：发起方申请     2：接受方（被调方）审核通过，待定中心审核   3：中心审核通过，等待出库     9 ：作废**/
	    private String status;
		/** 备注摘要**/
	    private String others;
		/** 操作员**/
	    private String createoper;
		/** 操作时间**/
	    private String createtime;
		/** 复合操作员**/
	    private String auditingoper;
		/** 复核时间**/
	    private String auditingtime;
		/** 出库经办人**/
	    private String traOutOper;
		/** 入库经办人**/
	    private String traInOper;
		/** 交易类型**/
	    private String tradetype;
	    /** 交易类型**/
	    private String tradeinfo;
	    /** 商户订单号**/
	    private String mchOrderNo;
	    /** 备注信息**/
	    private String remark;
	    /** 车号**/
	    private String carNo;
		/** 司机**/
	    private String carDriver;
	    private Long userId;
	    
	    private String beginTime;
	    private String endTime;
	    private String loginName;
	    private String operName;
	    private String centerName;
	    private String fromname;
	    private String tomname;
	    private Date jytime;
	    
	    List<CStockCenterTransferDetailDto>  cStockCenterTransferDetaildtos;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTsfSerialno() {
			return tsfSerialno;
		}

		public void setTsfSerialno(String tsfSerialno) {
			this.tsfSerialno = tsfSerialno;
		}

		public String getFromDeptid() {
			return fromDeptid;
		}

		public void setFromDeptid(String fromDeptid) {
			this.fromDeptid = fromDeptid;
		}

		public String getFromMchcode() {
			return fromMchcode;
		}

		public void setFromMchcode(String fromMchcode) {
			this.fromMchcode = fromMchcode;
		}

		public String getToDepid() {
			return toDepid;
		}

		public void setToDepid(String toDepid) {
			this.toDepid = toDepid;
		}

		public String getToMchcode() {
			return toMchcode;
		}

		public void setToMchcode(String toMchcode) {
			this.toMchcode = toMchcode;
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

		public String getTradetype() {
			return tradetype;
		}

		public void setTradetype(String tradetype) {
			this.tradetype = tradetype;
		}
		

		public String getTradeinfo() {
			return tradeinfo;
		}

		public void setTradeinfo(String tradeinfo) {
			this.tradeinfo = tradeinfo;
		}

		public List<CStockCenterTransferDetailDto> getcStockCenterTransferDetaildtos() {
			return cStockCenterTransferDetaildtos;
		}

		public void setcStockCenterTransferDetaildtos(List<CStockCenterTransferDetailDto> cStockCenterTransferDetaildtos) {
			this.cStockCenterTransferDetaildtos = cStockCenterTransferDetaildtos;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public String getMchOrderNo() {
			return mchOrderNo;
		}

		public void setMchOrderNo(String mchOrderNo) {
			this.mchOrderNo = mchOrderNo;
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

		public String getFromname() {
			return fromname;
		}

		public void setFromname(String fromname) {
			this.fromname = fromname;
		}

		public String getTomname() {
			return tomname;
		}

		public void setTomname(String tomname) {
			this.tomname = tomname;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
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
	    
		
	    
	    
	    
}
