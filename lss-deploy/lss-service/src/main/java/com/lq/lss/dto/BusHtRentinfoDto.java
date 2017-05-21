package com.lq.lss.dto;


/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-04 13:27:03
 */
public class BusHtRentinfoDto{

	
	/** id**/
    private String id;
	/** 合同编号**/
    private String htcode;
	/** 物资简码**/
    private String materialcode;
	/** 单位**/
    private String unit;
	/** 日租费**/
    private String rentalDay;
	/** 每吨数量**/
    private Integer tonQantity;
	/** 约租量**/
    private String quantity;
    
    private String typeid;
    private String tName;
    private String name;

 
	
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
   public String getRentalDay() {
       return rentalDay;
   }
   /**
    * 设置属性:rentalDay
    * 日租费
    * @param rentalDay
    */
   public void setRentalDay(String rentalDay) {
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
   public String getQuantity() {
       return quantity;
   }
   /**
    * 设置属性:quantity
    * 约租量
    * @param quantity
    */
   public void setQuantity(String quantity) {
       this.quantity = quantity;
   }
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
   
}