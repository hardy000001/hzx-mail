package com.lq.lss.model;

import java.math.BigDecimal;
import java.util.Date;

import com.lq.easyui.model.easyui.EasyUiModel;
                                                    
/**
 *
 * @author  作者: Eric
 * @date 创建时间: 2016-10-08 09:26:28
 */
public class CStockFlow extends EasyUiModel<String>{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;


	/** 商户号**/
    private String mchcode;
	/** 自增序号**/
    private String id;
	/** 所属租赁中心的ID**/
    private Integer deptid;
	/** 物资类型编号**/
    private String materialcode;
	/** 数量**/
    private BigDecimal amt;
	/** 单据号**/
    private String orderNo;
	/** 交易类型**/
    private String orderType;
	/** 备注**/
    private String remark;
	/** 最后修改时间**/
    private Date creattime;
	/** 仓库Id**/
    private Integer depotId;
	/** 操作人**/
    private String createoper;
	/** 增减标示1增加，-1为减少**/
    private String changeType;
	/** 合同编号**/
    private String htcode;
    
    
    
    
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
    * 获取属性:id
    * 自增序号
    * @return id
    */
   public String getId() {
       return id;
   }
   /**
    * 设置属性:id
    * 自增序号
    * @param id
    */
   public void setId(String id) {
       this.id = id;
   }
	
   /**
    * 获取属性:deptid
    * 所属租赁中心的ID
    * @return deptid
    */
   public Integer getDeptid() {
       return deptid;
   }
   /**
    * 设置属性:deptid
    * 所属租赁中心的ID
    * @param deptid
    */
   public void setDeptid(Integer deptid) {
       this.deptid = deptid;
   }
	
   /**
    * 获取属性:materialcode
    * 物资类型编号
    * @return materialcode
    */
   public String getMaterialcode() {
       return materialcode;
   }
   /**
    * 设置属性:materialcode
    * 物资类型编号
    * @param materialcode
    */
   public void setMaterialcode(String materialcode) {
       this.materialcode = materialcode;
   }
	
   /**
    * 获取属性:amt
    * 数量
    * @return amt
    */
   public BigDecimal getAmt() {
       return amt;
   }
   /**
    * 设置属性:amt
    * 数量
    * @param amt
    */
   public void setAmt(BigDecimal amt) {
       this.amt = amt;
   }
	
   /**
    * 获取属性:orderNo
    * 单据号
    * @return orderNo
    */
   public String getOrderNo() {
       return orderNo;
   }
   /**
    * 设置属性:orderNo
    * 单据号
    * @param orderNo
    */
   public void setOrderNo(String orderNo) {
       this.orderNo = orderNo;
   }
	
   /**
    * 获取属性:orderType
    * 交易类型
    * @return orderType
    */
   public String getOrderType() {
       return orderType;
   }
   /**
    * 设置属性:orderType
    * 交易类型
    * @param orderType
    */
   public void setOrderType(String orderType) {
       this.orderType = orderType;
   }
	
   /**
    * 获取属性:remark
    * 备注
    * @return remark
    */
   public String getRemark() {
       return remark;
   }
   /**
    * 设置属性:remark
    * 备注
    * @param remark
    */
   public void setRemark(String remark) {
       this.remark = remark;
   }
	
   /**
    * 获取属性:creattime
    * 最后修改时间
    * @return creattime
    */
   public Date getCreattime() {
       return creattime;
   }
   /**
    * 设置属性:creattime
    * 最后修改时间
    * @param creattime
    */
   public void setCreattime(Date creattime) {
       this.creattime = creattime;
   }
	
   /**
    * 获取属性:depotId
    * 仓库Id
    * @return depotId
    */
   public Integer getDepotId() {
       return depotId;
   }
   /**
    * 设置属性:depotId
    * 仓库Id
    * @param depotId
    */
   public void setDepotId(Integer depotId) {
       this.depotId = depotId;
   }
	
   /**
    * 获取属性:createoper
    * 操作人
    * @return createoper
    */
   public String getCreateoper() {
       return createoper;
   }
   /**
    * 设置属性:createoper
    * 操作人
    * @param createoper
    */
   public void setCreateoper(String createoper) {
       this.createoper = createoper;
   }
	
   /**
    * 获取属性:changeType
    * 增减标示1增加，-1为减少
    * @return changeType
    */
   public String getChangeType() {
       return changeType;
   }
   /**
    * 设置属性:changeType
    * 增减标示1增加，-1为减少
    * @param changeType
    */
   public void setChangeType(String changeType) {
       this.changeType = changeType;
   }

}