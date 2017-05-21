package com.lq.lss.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author 
 * @since 2016-08-31
 */
public class BChangeRule implements Serializable{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;

	/** id **/
    private Long id;
	/** 物资简码 **/
    private Integer materialcode;
	/** 换算前单位 **/
    private String unitOld;
	/** 换算后单位 **/
    private String unitNew;
	/** 换算比例    前/后     例如 ：300米/1吨  。 百分比为300 **/
    private BigDecimal percentage;

	
   /**
    * 获取属性:id
    * id
    * @return id
    */
   public Long getId() {
       return id;
   }
   /**
    * 设置属性:id
    * id
    * @param id
    */
   public void setId(Long id) {
       this.id = id;
   }
	
   /**
    * 获取属性:materialcode
    * 物资简码
    * @return materialcode
    */
   public Integer getMaterialcode() {
       return materialcode;
   }
   /**
    * 设置属性:materialcode
    * 物资简码
    * @param materialcode
    */
   public void setMaterialcode(Integer materialcode) {
       this.materialcode = materialcode;
   }
	
   /**
    * 获取属性:unitOld
    * 换算前单位
    * @return unitOld
    */
   public String getUnitOld() {
       return unitOld;
   }
   /**
    * 设置属性:unitOld
    * 换算前单位
    * @param unitOld
    */
   public void setUnitOld(String unitOld) {
       this.unitOld = unitOld;
   }
	
   /**
    * 获取属性:unitNew
    * 换算后单位
    * @return unitNew
    */
   public String getUnitNew() {
       return unitNew;
   }
   /**
    * 设置属性:unitNew
    * 换算后单位
    * @param unitNew
    */
   public void setUnitNew(String unitNew) {
       this.unitNew = unitNew;
   }
	
   /**
    * 获取属性:percentage
    * 换算比例    前/后     例如 ：300米/1吨  。 百分比为300
    * @return percentage
    */
   public BigDecimal getPercentage() {
       return percentage;
   }
   /**
    * 设置属性:percentage
    * 换算比例    前/后     例如 ：300米/1吨  。 百分比为300
    * @param percentage
    */
   public void setPercentage(BigDecimal percentage) {
       this.percentage = percentage;
   }

}