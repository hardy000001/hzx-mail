package com.lq.lss.model;

import java.math.BigDecimal;

import com.lq.easyui.model.easyui.EasyUiModel;
                                                            
/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2017-04-20 13:55:11
 */
public class SMchTransmaterial extends EasyUiModel<String>{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;


	/** id**/
    private String id;
	/** 商户号 **/
    private String mchcode;
	/** 所属中心**/
    private Integer deptid;
	/** 材料类别**/
    private Integer typeid;
	/** 期初数量**/
    private BigDecimal beginNum;
	/** 期末数量**/
    private BigDecimal endNum;
	/** 调入数量**/
    private BigDecimal transferIn;
	/** 调出数量**/
    private BigDecimal transferOut;
	/** 入库数量**/
    private BigDecimal stockIn;
	/** 出库数量**/
    private BigDecimal stockOut;
	/** 销售数量**/
    private BigDecimal saleNum;
	/** 采购数量**/
    private BigDecimal purchaseNum;
	/** 单位**/
    private String unit;
	/** 结算月份**/
    private String settledate;
    
    
    private String mchName;
    private String centerName;
    private String typeName;
  

   
	
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
    * 获取属性:typeid
    * 材料类别
    * @return typeid
    */
   public Integer getTypeid() {
       return typeid;
   }
   /**
    * 设置属性:typeid
    * 材料类别
    * @param typeid
    */
   public void setTypeid(Integer typeid) {
       this.typeid = typeid;
   }
	
   /**
    * 获取属性:beginNum
    * 期初数量
    * @return beginNum
    */
   public BigDecimal getBeginNum() {
       return beginNum;
   }
   /**
    * 设置属性:beginNum
    * 期初数量
    * @param beginNum
    */
   public void setBeginNum(BigDecimal beginNum) {
       this.beginNum = beginNum;
   }
	
   /**
    * 获取属性:endNum
    * 期末数量
    * @return endNum
    */
   public BigDecimal getEndNum() {
       return endNum;
   }
   /**
    * 设置属性:endNum
    * 期末数量
    * @param endNum
    */
   public void setEndNum(BigDecimal endNum) {
       this.endNum = endNum;
   }
	
   /**
    * 获取属性:transferIn
    * 调入数量
    * @return transferIn
    */
   public BigDecimal getTransferIn() {
       return transferIn;
   }
   /**
    * 设置属性:transferIn
    * 调入数量
    * @param transferIn
    */
   public void setTransferIn(BigDecimal transferIn) {
       this.transferIn = transferIn;
   }
	
   /**
    * 获取属性:transferOut
    * 调出数量
    * @return transferOut
    */
   public BigDecimal getTransferOut() {
       return transferOut;
   }
   /**
    * 设置属性:transferOut
    * 调出数量
    * @param transferOut
    */
   public void setTransferOut(BigDecimal transferOut) {
       this.transferOut = transferOut;
   }
	
   /**
    * 获取属性:stockIn
    * 入库数量
    * @return stockIn
    */
   public BigDecimal getStockIn() {
       return stockIn;
   }
   /**
    * 设置属性:stockIn
    * 入库数量
    * @param stockIn
    */
   public void setStockIn(BigDecimal stockIn) {
       this.stockIn = stockIn;
   }
	
   /**
    * 获取属性:stockOut
    * 出库数量
    * @return stockOut
    */
   public BigDecimal getStockOut() {
       return stockOut;
   }
   /**
    * 设置属性:stockOut
    * 出库数量
    * @param stockOut
    */
   public void setStockOut(BigDecimal stockOut) {
       this.stockOut = stockOut;
   }
	
   /**
    * 获取属性:saleNum
    * 销售数量
    * @return saleNum
    */
   public BigDecimal getSaleNum() {
       return saleNum;
   }
   /**
    * 设置属性:saleNum
    * 销售数量
    * @param saleNum
    */
   public void setSaleNum(BigDecimal saleNum) {
       this.saleNum = saleNum;
   }
	
   /**
    * 获取属性:purchaseNum
    * 采购数量
    * @return purchaseNum
    */
   public BigDecimal getPurchaseNum() {
       return purchaseNum;
   }
   /**
    * 设置属性:purchaseNum
    * 采购数量
    * @param purchaseNum
    */
   public void setPurchaseNum(BigDecimal purchaseNum) {
       this.purchaseNum = purchaseNum;
   }
	
   /**
    * 获取属性:unit
    * 单位
    * @return unit
    */
   public String getUnit() {
       return unit;
   }
   /**
    * 设置属性:unit
    * 单位
    * @param unit
    */
   public void setUnit(String unit) {
       this.unit = unit;
   }
	
   /**
    * 获取属性:settledate
    * 结算月份
    * @return settledate
    */
   public String getSettledate() {
       return settledate;
   }
   /**
    * 设置属性:settledate
    * 结算月份
    * @param settledate
    */
   public void setSettledate(String settledate) {
       this.settledate = settledate;
   }
   
   public String getMchName() {
		return mchName;
	}
	public void setMchName(String mchName) {
		this.mchName = mchName;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


}