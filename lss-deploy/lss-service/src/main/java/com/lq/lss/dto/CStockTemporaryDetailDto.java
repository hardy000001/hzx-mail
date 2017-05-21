package com.lq.lss.dto;


                                
/**
 *
 * @author  作者: CH
 * @date 创建时间: 2016-10-08 09:26:28
 */
public class CStockTemporaryDetailDto {


	/** 暂存单据流水号**/
    private String temSerialno;
	/** 物资编号 b_material_info.code**/
    private String materialcode;
	/** 入库总长度 m**/
    private String totalM;
	/** 入库总数量  **/
    private String totalS;
	/** 入库总总量**/
    private String totalT;
	/** unit**/
    private String unit;

   
	
  
   /**
    * 获取属性:temSerialno
    * 暂存单据流水号
    * @return temSerialno
    */
   public String getTemSerialno() {
       return temSerialno;
   }
   /**
    * 设置属性:temSerialno
    * 暂存单据流水号
    * @param temSerialno
    */
   public void setTemSerialno(String temSerialno) {
       this.temSerialno = temSerialno;
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
    * 入库总长度 m
    * @return totalM
    */
   public String getTotalM() {
       return totalM;
   }
   /**
    * 设置属性:totalM
    * 入库总长度 m
    * @param totalM
    */
   public void setTotalM(String totalM) {
       this.totalM = totalM;
   }
	
   /**
    * 获取属性:totalS
    * 入库总数量  
    * @return totalS
    */
   public String getTotalS() {
       return totalS;
   }
   /**
    * 设置属性:totalS
    * 入库总数量  
    * @param totalS
    */
   public void setTotalS(String totalS) {
       this.totalS = totalS;
   }
	
   /**
    * 获取属性:totalT
    * 入库总总量
    * @return totalT
    */
   public String getTotalT() {
       return totalT;
   }
   /**
    * 设置属性:totalT
    * 入库总总量
    * @param totalT
    */
   public void setTotalT(String totalT) {
       this.totalT = totalT;
   }
	
   /**
    * 获取属性:unit
    * unit
    * @return unit
    */
   public String getUnit() {
       return unit;
   }
   /**
    * 设置属性:unit
    * unit
    * @param unit
    */
   public void setUnit(String unit) {
       this.unit = unit;
   }

}