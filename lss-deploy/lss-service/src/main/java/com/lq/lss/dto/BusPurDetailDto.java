package com.lq.lss.dto;


/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-08-31 10:21:24
 */
public class BusPurDetailDto{

	
	/** 自增序号**/
    private String id;
	/** 采购流水号**/
    private String purSerialno;
    /** 材料id**/
    private Integer materialId;
	/** 采购单价**/
    private String price;
	/** 单位**/
    private String unit;
	/** 数量**/
    private String quantity;
	/** 总金额**/
    private String totalAmt;

 
	
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
    * 获取属性:purSerialno
    * 采购流水号
    * @return purSerialno
    */
   public String getPurSerialno() {
       return purSerialno;
   }
   /**
    * 设置属性:purSerialno
    * 采购流水号
    * @param purSerialno
    */
   public void setPurSerialno(String purSerialno) {
       this.purSerialno = purSerialno;
   }
	
   /**
    * 获取属性:materialId
    * 材料id
    * @return materialId
    */
   public Integer getMaterialId() {
       return materialId;
   }
   /**
    * 设置属性:materialId
    * 材料id
    * @param materialId
    */
   public void setMaterialId(Integer materialId) {
       this.materialId = materialId;
   }
	
   /**
    * 获取属性:price
    * 采购单价
    * @return price
    */
   public String getPrice() {
       return price;
   }
   /**
    * 设置属性:price
    * 采购单价
    * @param price
    */
   public void setPrice(String price) {
       this.price = price;
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
    * 获取属性:quantity
    * 数量
    * @return quantity
    */
   public String getQuantity() {
       return quantity;
   }
   /**
    * 设置属性:quantity
    * 数量
    * @param quantity
    */
   public void setQuantity(String quantity) {
       this.quantity = quantity;
   }
	
   /**
    * 获取属性:totalAmt
    * 总金额
    * @return totalAmt
    */
   public String getTotalAmt() {
       return totalAmt;
   }
   /**
    * 设置属性:totalAmt
    * 总金额
    * @param totalAmt
    */
   public void setTotalAmt(String totalAmt) {
       this.totalAmt = totalAmt;
   }

}