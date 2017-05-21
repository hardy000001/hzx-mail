package com.lq.lss.model;

import java.math.BigDecimal;
import com.lq.easyui.model.easyui.EasyUiModel;
                                
/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-12-15 14:03:12
 */
public class CustomerRentinfo extends EasyUiModel<String>{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;


	/** id**/
    private String id;
	/** 客户编号**/
    private String cuscode;
	/** 物资简码**/
    private String materialcode;
	/** 单位**/
    private String unit;
	/** 日租费**/
    private BigDecimal rentalDay;
	/** 每吨数量**/
    private Integer tonQantity;
	/** 约租量**/
    private BigDecimal quantity;

   
	
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
    * 获取属性:cuscode
    * 客户编号
    * @return cuscode
    */
   public String getCuscode() {
       return cuscode;
   }
   /**
    * 设置属性:cuscode
    * 客户编号
    * @param cuscode
    */
   public void setCuscode(String cuscode) {
       this.cuscode = cuscode;
   }
	
   /**
    * 获取属性:materialcode
    * 物资简码
    * @return materialcode
    */
   public String getMaterialcode() {
       return materialcode;
   }
   /**
    * 设置属性:materialcode
    * 物资简码
    * @param materialcode
    */
   public void setMaterialcode(String materialcode) {
       this.materialcode = materialcode;
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
    * 获取属性:rentalDay
    * 日租费
    * @return rentalDay
    */
   public BigDecimal getRentalDay() {
       return rentalDay;
   }
   /**
    * 设置属性:rentalDay
    * 日租费
    * @param rentalDay
    */
   public void setRentalDay(BigDecimal rentalDay) {
       this.rentalDay = rentalDay;
   }
	
   /**
    * 获取属性:tonQantity
    * 每吨数量
    * @return tonQantity
    */
   public Integer getTonQantity() {
       return tonQantity;
   }
   /**
    * 设置属性:tonQantity
    * 每吨数量
    * @param tonQantity
    */
   public void setTonQantity(Integer tonQantity) {
       this.tonQantity = tonQantity;
   }
	
   /**
    * 获取属性:quantity
    * 约租量
    * @return quantity
    */
   public BigDecimal getQuantity() {
       return quantity;
   }
   /**
    * 设置属性:quantity
    * 约租量
    * @param quantity
    */
   public void setQuantity(BigDecimal quantity) {
       this.quantity = quantity;
   }

}