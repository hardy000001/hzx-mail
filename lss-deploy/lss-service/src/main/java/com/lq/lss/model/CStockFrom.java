package com.lq.lss.model;

import java.math.BigDecimal;
import java.util.Date;

import com.lq.easyui.model.easyui.EasyUiModel;

/**
 *
 * @author 
 * @since 2016-08-31
 */
public class CStockFrom extends EasyUiModel<Integer>{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;

	/** 中心外部借调流水单号 **/
    private Long froSerialno;
	/** 所属中心 **/
    private Integer deptid;
	/** 被借方名称  **/
    private String mchname;
	/** 联系人 **/
    private String mchlinkname;
	/** 联系方式 **/
    private String mchtel;
	/** 地址 **/
    private String mchaddress;
	/** 总米数 **/
    private BigDecimal totalM;
	/** 总数量 **/
    private BigDecimal totalS;
	/** 总重量 **/
    private BigDecimal totalT;
	/** 装卸费用 **/
    private BigDecimal zxFee;
	/** 杂费 **/
    private BigDecimal otherFee;
	/** 使用期限-开始 **/
    private Date froSdate;
	/** 使用期限--结束 **/
    private Date froEdate;
	/** 状态 0：正常   1：发起方申请     2：接受方（被调方）审核通过，待定中心审核   3：中心审核通过，等待出库     9 ：作废 **/
    private String status;
	/** 操作员 **/
    private String createoper;
	/** 操作时间 **/
    private Date createtime;
	/** 复合操作员 **/
    private String auditingoper;
	/** 复核时间 **/
    private Date auditingtime;
    private Integer id;
	
   /**
    * 获取属性:froSerialno
    * 中心外部借调流水单号
    * @return froSerialno
    */
   public Long getFroSerialno() {
       return froSerialno;
   }
   /**
    * 设置属性:froSerialno
    * 中心外部借调流水单号
    * @param froSerialno
    */
   public void setFroSerialno(Long froSerialno) {
       this.froSerialno = froSerialno;
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
    * 获取属性:mchname
    * 被借方名称 
    * @return mchname
    */
   public String getMchname() {
       return mchname;
   }
   /**
    * 设置属性:mchname
    * 被借方名称 
    * @param mchname
    */
   public void setMchname(String mchname) {
       this.mchname = mchname;
   }
	
   /**
    * 获取属性:mchlinkname
    * 联系人
    * @return mchlinkname
    */
   public String getMchlinkname() {
       return mchlinkname;
   }
   /**
    * 设置属性:mchlinkname
    * 联系人
    * @param mchlinkname
    */
   public void setMchlinkname(String mchlinkname) {
       this.mchlinkname = mchlinkname;
   }
	
   /**
    * 获取属性:mchtel
    * 联系方式
    * @return mchtel
    */
   public String getMchtel() {
       return mchtel;
   }
   /**
    * 设置属性:mchtel
    * 联系方式
    * @param mchtel
    */
   public void setMchtel(String mchtel) {
       this.mchtel = mchtel;
   }
	
   /**
    * 获取属性:mchaddress
    * 地址
    * @return mchaddress
    */
   public String getMchaddress() {
       return mchaddress;
   }
   /**
    * 设置属性:mchaddress
    * 地址
    * @param mchaddress
    */
   public void setMchaddress(String mchaddress) {
       this.mchaddress = mchaddress;
   }
	
   /**
    * 获取属性:totalM
    * 总米数
    * @return totalM
    */
   public BigDecimal getTotalM() {
       return totalM;
   }
   /**
    * 设置属性:totalM
    * 总米数
    * @param totalM
    */
   public void setTotalM(BigDecimal totalM) {
       this.totalM = totalM;
   }
	
   /**
    * 获取属性:totalS
    * 总数量
    * @return totalS
    */
   public BigDecimal getTotalS() {
       return totalS;
   }
   /**
    * 设置属性:totalS
    * 总数量
    * @param totalS
    */
   public void setTotalS(BigDecimal totalS) {
       this.totalS = totalS;
   }
	
   /**
    * 获取属性:totalT
    * 总重量
    * @return totalT
    */
   public BigDecimal getTotalT() {
       return totalT;
   }
   /**
    * 设置属性:totalT
    * 总重量
    * @param totalT
    */
   public void setTotalT(BigDecimal totalT) {
       this.totalT = totalT;
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
    * 获取属性:froSdate
    * 使用期限-开始
    * @return froSdate
    */
   public Date getFroSdate() {
       return froSdate;
   }
   /**
    * 设置属性:froSdate
    * 使用期限-开始
    * @param froSdate
    */
   public void setFroSdate(Date froSdate) {
       this.froSdate = froSdate;
   }
	
   /**
    * 获取属性:froEdate
    * 使用期限--结束
    * @return froEdate
    */
   public Date getFroEdate() {
       return froEdate;
   }
   /**
    * 设置属性:froEdate
    * 使用期限--结束
    * @param froEdate
    */
   public void setFroEdate(Date froEdate) {
       this.froEdate = froEdate;
   }
	
   /**
    * 获取属性:status
    * 状态 0：正常   1：发起方申请     2：接受方（被调方）审核通过，待定中心审核   3：中心审核通过，等待出库     9 ：作废
    * @return status
    */
   public String getStatus() {
       return status;
   }
   /**
    * 设置属性:status
    * 状态 0：正常   1：发起方申请     2：接受方（被调方）审核通过，待定中心审核   3：中心审核通过，等待出库     9 ：作废
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
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
  
}