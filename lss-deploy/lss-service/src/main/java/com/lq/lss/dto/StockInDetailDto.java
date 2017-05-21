package com.lq.lss.dto;

import java.math.BigDecimal;


/**
 *
 * @author  作者: Eric
 * @date 创建时间: 2016-08-31 10:21:24
 */
public class StockInDetailDto{

	
	/** 自增序号**/
    private String id;
	/** 入库流水号**/
    private String inSerialno;
	/** 物资编号 b_material_info.code **/
    private String materialcode;
	/** 入库总数量   **/
    private BigDecimal totalS;
	/** 单位**/
    private String unit;
    
    private String totalM;

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
   public String getInSerialno() {
       return inSerialno;
   }
   /**
    * 设置属性:purSerialno
    * 采购流水号
    * @param purSerialno
    */
   public void setInSerialno(String inSerialno) {
       this.inSerialno = inSerialno;
   }
   /**
    * 获取属性:materialcode
    * 物资编号 b_material_info.code
    * @return materialcode
    */
   public String getMaterialCode() {
       return materialcode;
   }
   /**
    * 设置属性:materialcode
    * 物资编号 b_material_info.code
    * @param materialcode
    */
   public void setMaterialCode(String materialcode) {
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
    * 获取属性:totalS
    * 入库总数量  
    * @return totalS
    */
   public BigDecimal getTotalS() {
       return totalS;
   }
   /**
    * 设置属性:totalS
    * 入库总数量  
    * @param totalS
    */
   public void setTotalS(BigDecimal totalS) {
       this.totalS = totalS;
   }
public String getTotalM() {
	return totalM;
}
public void setTotalM(String totalM) {
	this.totalM = totalM;
}
	
   
   

}