package com.lq.lss.model;

import java.math.BigDecimal;

import com.lq.easyui.model.easyui.EasyUiModel;

/**
 *
 * @author 
 * @since 2016-08-31
 */
public class CStockFromDetail extends EasyUiModel<Integer>{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;

	/** id **/
    private Integer id;
	/** 外借单据流水号 **/
    private String froSerialno;
	/** 物资编号 b_material_info.code **/
    private String materialcode;
	/** 总长度 m **/
    private BigDecimal totalM;
	/** 总数量   **/
    private BigDecimal totalS;
	/** 总量 **/
    private BigDecimal totalT;
	/** 状态  0：正常   **/
    private String status;
	/** 租赁的单价  **/
    private BigDecimal price;
	/** 单位 **/
    private String unit;

	
   /**
    * 获取属性:id
    * id
    * @return id
    */
   public Integer getId() {
       return id;
   }
   /**
    * 设置属性:id
    * id
    * @param id
    */
   public void setId(Integer id) {
       this.id = id;
   }
	
   /**
    * 获取属性:froSerialno
    * 外借单据流水号
    * @return froSerialno
    */
   public String getFroSerialno() {
       return froSerialno;
   }
   /**
    * 设置属性:froSerialno
    * 外借单据流水号
    * @param froSerialno
    */
   public void setFroSerialno(String froSerialno) {
       this.froSerialno = froSerialno;
   }
	
   /**
    * 获取属性:materialcode
    * 物资编号 b_material_info.code
    * @return materialcode
    */
   public String getMaterialcode() {
       return materialcode;
   }
   /**
    * 设置属性:materialcode
    * 物资编号 b_material_info.code
    * @param materialcode
    */
   public void setMaterialcode(String materialcode) {
       this.materialcode = materialcode;
   }
	
   /**
    * 获取属性:totalM
    * 总长度 m
    * @return totalM
    */
   public BigDecimal getTotalM() {
       return totalM;
   }
   /**
    * 设置属性:totalM
    * 总长度 m
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
    * 总量
    * @return totalT
    */
   public BigDecimal getTotalT() {
       return totalT;
   }
   /**
    * 设置属性:totalT
    * 总量
    * @param totalT
    */
   public void setTotalT(BigDecimal totalT) {
       this.totalT = totalT;
   }
	
   /**
    * 获取属性:status
    * 状态  0：正常  
    * @return status
    */
   public String getStatus() {
       return status;
   }
   /**
    * 设置属性:status
    * 状态  0：正常  
    * @param status
    */
   public void setStatus(String status) {
       this.status = status;
   }
	
   /**
    * 获取属性:price
    * 租赁的单价 
    * @return price
    */
   public BigDecimal getPrice() {
       return price;
   }
   /**
    * 设置属性:price
    * 租赁的单价 
    * @param price
    */
   public void setPrice(BigDecimal price) {
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

}