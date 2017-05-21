package com.lq.lss.dto;


/**
 * 
 * @author  作者: hzx
 * @date 创建时间: 2016-9-21下午5:30:01
 */
public class BusSaleDetailDto{

	
	/** 销售单据流水号 **/
    private String saleSerialno;
    /** 材料ID**/
    private Integer materialId;
	/** 销售单价 **/
    private String price;
	/** 单位 **/
    private String unit;
	/** 数量 **/
    private String quantity;
	/** 总金额 **/
    private String totalAmt;

    private String name;
	
	
   /**
    * 获取属性:saleSerialno
    * 销售单据流水号
    * @return saleSerialno
    */
   public String getSaleSerialno() {
       return saleSerialno;
   }
   /**
    * 设置属性:saleSerialno
    * 销售单据流水号
    * @param saleSerialno
    */
   public void setSaleSerialno(String saleSerialno) {
       this.saleSerialno = saleSerialno;
   }
	
   /**
    * 获取属性:materialId
    * 材料ID
    * @return materialId
    */
   public Integer getMaterialId() {
       return materialId;
   }
   /**
    * 设置属性:materialId
    * 材料ID
    * @param materialId
    */
   public void setMaterialId(Integer materialId) {
       this.materialId = materialId;
   }
	
   /**
    * 获取属性:price
    * 销售单价
    * @return price
    */
   public String getPrice() {
       return price;
   }
   /**
    * 设置属性:price
    * 销售单价
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
       this.totalAmt=totalAmt;
   }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
   
}